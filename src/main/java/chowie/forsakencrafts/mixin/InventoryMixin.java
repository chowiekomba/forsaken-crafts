package chowie.forsakencrafts.mixin;

import chowie.forsakencrafts.ForsakenCrafts;
import chowie.forsakencrafts.datagen.ModItemTagProvider;
import chowie.forsakencrafts.util.ConfigCommandExecuter;
import chowie.forsakencrafts.util.ItemDisplayTimer;
import chowie.forsakencrafts.util.ModDataAttachments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Mixin(Inventory.class)
public class InventoryMixin {

    @Shadow
    @Final
    public Player player;

	// prevents picking up the item
	@Inject(method = "add(Lnet/minecraft/world/item/ItemStack;)Z", at = @At(value = "HEAD"), cancellable = true)
	private void checkItemAdd(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
		if (player instanceof ServerPlayer serverPlayer) {
			List<Item> immutableItemsUnlocked = serverPlayer.getAttachedOrCreate(ModDataAttachments.ITEMS_UNLOCKED);
			List<Item> immutableItemsFound = serverPlayer.getAttachedOrCreate(ModDataAttachments.ITEMS_FOUND);
			List<Item> itemsUnlocked = new LinkedList<>(immutableItemsUnlocked);
			List<Item> itemsFound = new LinkedList<>(immutableItemsFound);

			if (!itemsFound.contains(itemStack.getItem())) {
				Optional<Holder.Reference<Item>> randomItem = BuiltInRegistries.ITEM.getRandom(RandomSource.create());
				if (randomItem.isPresent()) {
					while (randomItem.get().is(ModItemTagProvider.UNOBTAINABLE_ITEMS) && !itemsUnlocked.contains(randomItem.get().value())) {
						randomItem = BuiltInRegistries.ITEM.getRandom(RandomSource.create());
					}
					Item item = randomItem.get().value();
					itemsFound.add(itemStack.getItem());
					itemsUnlocked.add(item);
					serverPlayer.setAttached(ModDataAttachments.ITEMS_FOUND, itemsFound);
					serverPlayer.setAttached(ModDataAttachments.ITEMS_UNLOCKED, itemsUnlocked);
					ItemDisplayTimer.INSTANCE.setTimer(serverPlayer, item, 60);

					if (!ForsakenCrafts.CONFIG.unlockCommand.isEmpty()) {
						ConfigCommandExecuter.runCommand(serverPlayer.level().getServer(), ForsakenCrafts.CONFIG.unlockCommand,
								serverPlayer);
					}
				}
			}

			if (!itemsUnlocked.contains(itemStack.getItem())) {
				if (!(serverPlayer.level().getServer().getPlayerList().getPlayer(serverPlayer.getUUID()) == null)) {
					serverPlayer.sendSystemMessage(Component.literal(itemStack.getItemName().getString() +
							" isn't unlocked yet!"));
				}
				itemStack.setCount(0);

				if (!ForsakenCrafts.CONFIG.pickUpCommand.isEmpty()) {
					ConfigCommandExecuter.runCommand(serverPlayer.level().getServer(), ForsakenCrafts.CONFIG.pickUpCommand,
							serverPlayer);
				}
                cir.setReturnValue(false);
			}

		}
	}

	// prevents new items from crafting/gui
	@Inject(method = "setItem", at = @At(value = "HEAD"), cancellable = true)
	private void checkHasItemUnlocked(int slot, ItemStack itemStack, CallbackInfo ci) {
		if (player instanceof ServerPlayer serverPlayer) {
			List<Item> immutableItemsUnlocked = serverPlayer.getAttachedOrCreate(ModDataAttachments.ITEMS_UNLOCKED);
			List<Item> immutableItemsFound = serverPlayer.getAttachedOrCreate(ModDataAttachments.ITEMS_FOUND);
			List<Item> itemsUnlocked = new LinkedList<>(immutableItemsUnlocked);
			List<Item> itemsFound = new LinkedList<>(immutableItemsFound);

			if (!itemsFound.contains(itemStack.getItem())) {
				Optional<Holder.Reference<Item>> randomItem = BuiltInRegistries.ITEM.getRandom(RandomSource.create());
				if (randomItem.isPresent()) {
					while (randomItem.get().is(ModItemTagProvider.UNOBTAINABLE_ITEMS) && !itemsUnlocked.contains(randomItem.get().value())) {
						randomItem = BuiltInRegistries.ITEM.getRandom(RandomSource.create());
					}
					Item item = randomItem.get().value();
					itemsFound.add(itemStack.getItem());
					itemsUnlocked.add(item);
					serverPlayer.setAttached(ModDataAttachments.ITEMS_FOUND, itemsFound);
					serverPlayer.setAttached(ModDataAttachments.ITEMS_UNLOCKED, itemsUnlocked);
					ItemDisplayTimer.INSTANCE.setTimer(serverPlayer, item, 60);

					if (!ForsakenCrafts.CONFIG.unlockCommand.isEmpty()) {
						ConfigCommandExecuter.runCommand(serverPlayer.level().getServer(), ForsakenCrafts.CONFIG.unlockCommand,
								serverPlayer);
					}
				}
			}

			if (!itemsUnlocked.contains(itemStack.getItem())) {
				if (!(serverPlayer.level().getServer().getPlayerList().getPlayer(serverPlayer.getUUID()) == null)) {
					serverPlayer.sendSystemMessage(Component.literal(itemStack.getItemName().getString() +
							" isn't unlocked yet!"));
				}
				itemStack.setCount(0);
                ci.cancel();
			}
		}
	}
}