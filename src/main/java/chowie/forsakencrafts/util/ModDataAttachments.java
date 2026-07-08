package chowie.forsakencrafts.util;

import chowie.forsakencrafts.ForsakenCrafts;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.LinkedList;
import java.util.List;

public class ModDataAttachments {
    public static final AttachmentType<List<Item>> ITEMS_FOUND = AttachmentRegistry.create(
            ForsakenCrafts.id("items_found"),
            builder -> builder
                    .copyOnDeath()
                    .persistent(BuiltInRegistries.ITEM.byNameCodec().listOf())
                    .initializer(() -> {
                        List<Item> list = new LinkedList<>();
                        list.add(Items.AIR);
                        return list;
                    })
    );

    public static final AttachmentType<List<Item>> ITEMS_UNLOCKED = AttachmentRegistry.create(
            ForsakenCrafts.id("items_unlocked"),
            builder -> builder
                    .copyOnDeath()
                    .persistent(BuiltInRegistries.ITEM.byNameCodec().listOf())
                    .initializer(() -> {
                        List<Item> list = new LinkedList<>();
                        list.add(Items.AIR);
                        return list;
                    })
    );

    public static void register() {
        ForsakenCrafts.LOGGER.info("Registering ModDataAttachments for {}", ForsakenCrafts.MOD_ID);
    }
}
