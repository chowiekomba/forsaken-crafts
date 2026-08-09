package chowie.forsakencrafts.util;

import chowie.forsakencrafts.ForsakenCrafts;
import chowie.forsakencrafts.datagen.ModItemTagProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Helpers {
    public static Item giveValidRandomItem(ServerPlayer player) {
        List<Item> immutableItemsUnlocked = player.getAttachedOrCreate(ModDataAttachments.ITEMS_UNLOCKED);
        List<Item> immutableItemsFound = player.getAttachedOrCreate(ModDataAttachments.ITEMS_FOUND);
        List<Item> itemsUnlocked = new LinkedList<>(immutableItemsUnlocked);
        List<Item> itemsFound = new LinkedList<>(immutableItemsFound);
        Optional<Holder.Reference<Item>> randomItem = BuiltInRegistries.ITEM.getRandom(RandomSource.create());
        int iterations = 0;
        int max = 2000;

        if (randomItem.isPresent()) {
            while (randomItem.isPresent() &&
                    randomItem.get().is(ModItemTagProvider.UNOBTAINABLE_ITEMS) &&
                    !itemsUnlocked.contains(randomItem.get().value())) {
                randomItem = BuiltInRegistries.ITEM.getRandom(RandomSource.create());
                iterations++;
                if (iterations == max) {
                    return Items.AIR;
                }
            }

            if (randomItem.isEmpty()) {
                return Items.AIR;
            }

            Item item = randomItem.get().value();
            itemsUnlocked.add(item);
            player.setAttached(ModDataAttachments.ITEMS_FOUND, itemsFound);
            player.setAttached(ModDataAttachments.ITEMS_UNLOCKED, itemsUnlocked);

            if (!ForsakenCrafts.CONFIG.unlockCommand.isEmpty()) {
                ConfigCommandExecuter.runCommand(player.level().getServer(), ForsakenCrafts.CONFIG.unlockCommand,
                        player);
            }
            return item;
        }
        return Items.AIR;
    }
}
