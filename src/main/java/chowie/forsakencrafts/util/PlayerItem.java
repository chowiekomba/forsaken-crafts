package chowie.forsakencrafts.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;

public record PlayerItem(ServerPlayer player, Item item) {
}
