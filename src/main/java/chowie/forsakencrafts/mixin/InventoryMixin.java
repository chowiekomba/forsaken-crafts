package chowie.forsakencrafts.mixin;

import chowie.forsakencrafts.ForsakenCrafts;
import chowie.forsakencrafts.util.ConfigCommandExecuter;
import chowie.forsakencrafts.util.Helpers;
import chowie.forsakencrafts.util.timer.ItemDisplayTimer;
import chowie.forsakencrafts.util.ModDataAttachments;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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

@Mixin(Inventory.class)
public class InventoryMixin {

    @Shadow
    @Final
    public Player player;

	// prevents picking up the item
	@Inject(method = "add(Lnet/minecraft/world/item/ItemStack;)Z", at = @At(value = "HEAD"), cancellable = true)
	private void checkItemAdd(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
		if (player instanceof ServerPlayer serverPlayer) {
			List<Item> immutableItemsUnlocked = player.getAttachedOrCreate(ModDataAttachments.ITEMS_UNLOCKED);
			List<Item> immutableItemsFound = player.getAttachedOrCreate(ModDataAttachments.ITEMS_FOUND);
			List<Item> itemsUnlocked = new LinkedList<>(immutableItemsUnlocked);

			if (!immutableItemsFound.contains(itemStack.getItem())) {
				ItemDisplayTimer.INSTANCE.setTimer(serverPlayer, Helpers.giveValidRandomItem(serverPlayer), 60);
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
			List<Item> immutableItemsUnlocked = player.getAttachedOrCreate(ModDataAttachments.ITEMS_UNLOCKED);
			List<Item> immutableItemsFound = player.getAttachedOrCreate(ModDataAttachments.ITEMS_FOUND);
			List<Item> itemsUnlocked = new LinkedList<>(immutableItemsUnlocked);

			if (!immutableItemsFound.contains(itemStack.getItem())) {
				ItemDisplayTimer.INSTANCE.setTimer(serverPlayer, Helpers.giveValidRandomItem(serverPlayer), 60);
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
                ci.cancel();
			}

		}
	}
}