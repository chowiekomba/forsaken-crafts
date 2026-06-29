package chowie.forsakencrafts.mixin;

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

	@Inject(method = "add(Lnet/minecraft/world/item/ItemStack;)Z", at = @At(value = "HEAD"), cancellable = true)
	private void checkItemAdd(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
		if (player instanceof ServerPlayer serverPlayer) {
			List<Item> immutableItems = serverPlayer.getAttachedOrCreate(ModDataAttachments.ITEMS_FOUND);
			List<Item> items = new LinkedList<>(immutableItems);
			if (!items.contains(itemStack.getItem())) {
				items.add(itemStack.getItem());
				serverPlayer.setAttached(ModDataAttachments.ITEMS_FOUND, items);
				serverPlayer.sendSystemMessage(Component.literal("Unlocked " + itemStack.getItemName().getString() +
						", find it again to pick it up"));
				itemStack.setCount(0);
                cir.setReturnValue(false);
			}
		}
	}

	@Inject(method = "setItem", at = @At(value = "HEAD"), cancellable = true)
	private void checkHasItemUnlocked(int slot, ItemStack itemStack, CallbackInfo ci) {
		if (player instanceof ServerPlayer serverPlayer) {
			List<Item> immutableItems = serverPlayer.getAttachedOrCreate(ModDataAttachments.ITEMS_FOUND);
			List<Item> items = new LinkedList<>(immutableItems);
			if (!items.contains(itemStack.getItem())) {
				items.add(itemStack.getItem());
				serverPlayer.setAttached(ModDataAttachments.ITEMS_FOUND, items);
				serverPlayer.sendSystemMessage(Component.literal("Unlocked " + itemStack.getItemName().getString() +
						", find it again to pick it up"));
				itemStack.setCount(0);
				ci.cancel();
			}
		}
	}
}