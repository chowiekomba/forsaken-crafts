package chowie.forsakencrafts.datagen;

import chowie.forsakencrafts.ForsakenCrafts;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public static final TagKey<Item> UNOBTAINABLE_ITEMS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(
            ForsakenCrafts.MOD_ID, "unobtainable_items"
    ));
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(UNOBTAINABLE_ITEMS)
                .add(ItemIds.CHICKEN_SPAWN_EGG)
                .add(ItemIds.COW_SPAWN_EGG)
                .add(ItemIds.PIG_SPAWN_EGG)
                .add(ItemIds.SHEEP_SPAWN_EGG)
                .add(ItemIds.CAMEL_SPAWN_EGG)
                .add(ItemIds.DONKEY_SPAWN_EGG)
                .add(ItemIds.HORSE_SPAWN_EGG)
                .add(ItemIds.MULE_SPAWN_EGG)
                .add(ItemIds.CAT_SPAWN_EGG)
                .add(ItemIds.PARROT_SPAWN_EGG)
                .add(ItemIds.WOLF_SPAWN_EGG)
                .add(ItemIds.ARMADILLO_SPAWN_EGG)
                .add(ItemIds.BAT_SPAWN_EGG)
                .add(ItemIds.BEE_SPAWN_EGG)
                .add(ItemIds.FOX_SPAWN_EGG)
                .add(ItemIds.GOAT_SPAWN_EGG)
                .add(ItemIds.LLAMA_SPAWN_EGG)
                .add(ItemIds.OCELOT_SPAWN_EGG)
                .add(ItemIds.PANDA_SPAWN_EGG)
                .add(ItemIds.POLAR_BEAR_SPAWN_EGG)
                .add(ItemIds.RABBIT_SPAWN_EGG)
                .add(ItemIds.AXOLOTL_SPAWN_EGG)
                .add(ItemIds.COD_SPAWN_EGG)
                .add(ItemIds.DOLPHIN_SPAWN_EGG)
                .add(ItemIds.FROG_SPAWN_EGG)
                .add(ItemIds.GLOW_SQUID_SPAWN_EGG)
                .add(ItemIds.NAUTILUS_SPAWN_EGG)
                .add(ItemIds.PUFFERFISH_SPAWN_EGG)
                .add(ItemIds.SALMON_SPAWN_EGG)
                .add(ItemIds.SQUID_SPAWN_EGG)
                .add(ItemIds.TADPOLE_SPAWN_EGG)
                .add(ItemIds.TROPICAL_FISH_SPAWN_EGG)
                .add(ItemIds.TURTLE_SPAWN_EGG)
                .add(ItemIds.ALLAY_SPAWN_EGG)
                .add(ItemIds.MOOSHROOM_SPAWN_EGG)
                .add(ItemIds.SNIFFER_SPAWN_EGG)
                .add(ItemIds.SULFUR_CUBE_SPAWN_EGG)
                .add(ItemIds.COPPER_GOLEM_SPAWN_EGG)
                .add(ItemIds.IRON_GOLEM_SPAWN_EGG)
                .add(ItemIds.SNOW_GOLEM_SPAWN_EGG)
                .add(ItemIds.TRADER_LLAMA_SPAWN_EGG)
                .add(ItemIds.VILLAGER_SPAWN_EGG)
                .add(ItemIds.WANDERING_TRADER_SPAWN_EGG)
                .add(ItemIds.BOGGED_SPAWN_EGG)
                .add(ItemIds.CAMEL_HUSK_SPAWN_EGG)
                .add(ItemIds.DROWNED_SPAWN_EGG)
                .add(ItemIds.HUSK_SPAWN_EGG)
                .add(ItemIds.PARCHED_SPAWN_EGG)
                .add(ItemIds.SKELETON_SPAWN_EGG)
                .add(ItemIds.SKELETON_HORSE_SPAWN_EGG)
                .add(ItemIds.STRAY_SPAWN_EGG)
                .add(ItemIds.WITHER_SPAWN_EGG)
                .add(ItemIds.WITHER_SKELETON_SPAWN_EGG)
                .add(ItemIds.ZOMBIE_SPAWN_EGG)
                .add(ItemIds.ZOMBIE_HORSE_SPAWN_EGG)
                .add(ItemIds.ZOMBIE_NAUTILUS_SPAWN_EGG)
                .add(ItemIds.ZOMBIE_VILLAGER_SPAWN_EGG)
                .add(ItemIds.CAVE_SPIDER_SPAWN_EGG)
                .add(ItemIds.SPIDER_SPAWN_EGG)
                .add(ItemIds.BREEZE_SPAWN_EGG)
                .add(ItemIds.CREAKING_SPAWN_EGG)
                .add(ItemIds.CREEPER_SPAWN_EGG)
                .add(ItemIds.ELDER_GUARDIAN_SPAWN_EGG)
                .add(ItemIds.GUARDIAN_SPAWN_EGG)
                .add(ItemIds.PHANTOM_SPAWN_EGG)
                .add(ItemIds.SILVERFISH_SPAWN_EGG)
                .add(ItemIds.SLIME_SPAWN_EGG)
                .add(ItemIds.WARDEN_SPAWN_EGG)
                .add(ItemIds.WITCH_SPAWN_EGG)
                .add(ItemIds.EVOKER_SPAWN_EGG)
                .add(ItemIds.PILLAGER_SPAWN_EGG)
                .add(ItemIds.RAVAGER_SPAWN_EGG)
                .add(ItemIds.VINDICATOR_SPAWN_EGG)
                .add(ItemIds.VEX_SPAWN_EGG)
                .add(ItemIds.BLAZE_SPAWN_EGG)
                .add(ItemIds.GHAST_SPAWN_EGG)
                .add(ItemIds.HAPPY_GHAST_SPAWN_EGG)
                .add(ItemIds.HOGLIN_SPAWN_EGG)
                .add(ItemIds.MAGMA_CUBE_SPAWN_EGG)
                .add(ItemIds.PIGLIN_SPAWN_EGG)
                .add(ItemIds.PIGLIN_BRUTE_SPAWN_EGG)
                .add(ItemIds.STRIDER_SPAWN_EGG)
                .add(ItemIds.ZOGLIN_SPAWN_EGG)
                .add(ItemIds.ZOMBIFIED_PIGLIN_SPAWN_EGG)
                .add(ItemIds.ENDER_DRAGON_SPAWN_EGG)
                .add(ItemIds.ENDERMAN_SPAWN_EGG)
                .add(ItemIds.ENDERMITE_SPAWN_EGG)
                .add(ItemIds.SHULKER_SPAWN_EGG)
                .add(ItemIds.COMMAND_BLOCK_MINECART)
                .add(BlockItemIds.CHAIN_COMMAND_BLOCK)
                .add(BlockItemIds.REPEATING_COMMAND_BLOCK)
                .add(BlockItemIds.STRUCTURE_BLOCK)
                .add(BlockItemIds.STRUCTURE_VOID)
                .add(BlockItemIds.JIGSAW)
                .add(BlockItemIds.BARRIER)
                .add(BlockItemIds.LIGHT)
                .add(ItemIds.DEBUG_STICK)
                .add(ItemIds.KNOWLEDGE_BOOK)
                .add(BlockItemIds.BEDROCK)
                .add(BlockItemIds.END_PORTAL_FRAME)
                .add(BlockItemIds.PLAYER_HEAD);
    }
}
