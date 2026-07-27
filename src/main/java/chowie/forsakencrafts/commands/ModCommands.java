package chowie.forsakencrafts.commands;

import chowie.forsakencrafts.datagen.ModItemTagProvider;
import chowie.forsakencrafts.screens.ItemsUnlockedGui;
import chowie.forsakencrafts.util.ModDataAttachments;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ModCommands {
    public static int unlockedBlocks(CommandContext<CommandSourceStack> commandContext) {
        if (commandContext.getSource().getEntity() instanceof ServerPlayer player) {
            ItemsUnlockedGui.INSTANCE.openGui(player, 0);
        }
        return 1;
    }

    public static int giveUnlockedItem(CommandContext<CommandSourceStack> context) {
        if (context.getSource().getEntity() instanceof ServerPlayer player) {
            List<Item> immutableItemsUnlocked = player.getAttachedOrCreate(ModDataAttachments.ITEMS_UNLOCKED);
            List<Item> itemsUnlocked = new LinkedList<>(immutableItemsUnlocked);


            Optional<Holder.Reference<Item>> randomItem = BuiltInRegistries.ITEM.getRandom(RandomSource.create());
            if (randomItem.isPresent()) {
                while (randomItem.get().is(ModItemTagProvider.UNOBTAINABLE_ITEMS) && !itemsUnlocked.contains(randomItem.get().value())) {
                    randomItem = BuiltInRegistries.ITEM.getRandom(RandomSource.create());
                }
                itemsUnlocked.add(randomItem.get().value());
            }
            player.setAttached(ModDataAttachments.ITEMS_UNLOCKED, itemsUnlocked);
        }
        return 1;
    }
}
