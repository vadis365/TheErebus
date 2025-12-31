package erebus.datagen.loot;

import erebus.Erebus;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public record ModChestLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {

    public static final ResourceKey<LootTable> ROTTEN_LOG = createKey("chests/rotten_log");
    public static final ResourceKey<LootTable> ANTLION_DUNGEON = createKey("chests/antlion_dungeon");
    public static final ResourceKey<LootTable> ANTLION_LAIR = createKey("chests/antlion_lair");
    public static final ResourceKey<LootTable> DRAGONFLY_DUNGEON = createKey("chests/dragonfly_dungeon");
    public static final ResourceKey<LootTable> DUNG_PILE = createKey("chests/dung_pile");
    public static final ResourceKey<LootTable> LOCUST_SHRINE = createKey("chests/locust_shrine");
    public static final ResourceKey<LootTable> SPIDER_DUNGEON = createKey("chests/spider_dungeon");

    private static ResourceKey<LootTable> createKey(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, Erebus.prefix(name));
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        consumer.accept(ROTTEN_LOG, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(3, 10))
                        .add(item(Items.BOOK, 18, 2, 4))
                        .add(item(Items.PAPER, 16, 2, 6))
                        .add(item(Items.COBWEB, 13, 2, 7))
                        .add(item(ModItems.JADE, 10, 1, 3))
                        .add(item(ModItems.PLATE_EXO, 9, 4, 8))
                        .add(itemEnchanted(Items.ENCHANTED_BOOK, 8))
                        .add(itemEnchanted(Items.GOLDEN_PICKAXE, 3))
                        .add(itemEnchanted(Items.IRON_PICKAXE, 2))
                        .add(itemEnchanted(ModItems.JADE_PICKAXE, 1))
                        .add(itemEnchanted(Items.STONE_PICKAXE, 1))
                        .add(itemEnchanted(Items.GOLDEN_SHOVEL, 3))
                        .add(itemEnchanted(Items.IRON_SHOVEL, 2))
                        .add(itemEnchanted(ModItems.JADE_SHOVEL, 1))
                        .add(itemEnchanted(Items.STONE_SHOVEL, 1))
                        .add(itemEnchanted(Items.GOLDEN_AXE, 3))
                        .add(itemEnchanted(Items.IRON_AXE, 2))
                        .add(itemEnchanted(ModItems.JADE_AXE, 1))
                        .add(itemEnchanted(Items.STONE_AXE, 1))
                        .add(itemEnchanted(Items.GOLDEN_SWORD, 3))
                        .add(itemEnchanted(Items.IRON_SWORD, 2))
                        .add(itemEnchanted(ModItems.JADE_SWORD, 1))
                        .add(itemEnchanted(Items.STONE_SWORD, 1))
                        .add(itemEnchanted(Items.IRON_CHESTPLATE, 2))
                        .add(itemEnchanted(ModItems.JADE_CHESTPLATE, 1))
                        .add(itemEnchanted(Items.GOLDEN_CHESTPLATE, 1))
                        .add(itemEnchanted(Items.IRON_HELMET, 2))
                        .add(itemEnchanted(ModItems.JADE_HELMET, 1))
                        .add(itemEnchanted(Items.GOLDEN_HELMET, 1))
                        .add(itemEnchanted(Items.IRON_LEGGINGS, 2))
                        .add(itemEnchanted(ModItems.JADE_LEGGINGS, 1))
                        .add(itemEnchanted(Items.GOLDEN_LEGGINGS, 1))
                        .add(itemEnchanted(Items.IRON_BOOTS, 2))
                        .add(itemEnchanted(ModItems.JADE_BOOTS, 1))
                        .add(itemEnchanted(Items.GOLDEN_BOOTS, 1))
                )
        );

        consumer.accept(ANTLION_DUNGEON, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(3, 10))
                        .add(item(Items.BOOK, 18, 1, 4))
                        .add(item(Items.PAPER, 16, 2, 6))
                        .add(item(Blocks.COBWEB, 13, 2, 7))
                        .add(item(ModItems.JADE, 10, 1, 3))
                        .add(item(ModItems.PLATE_EXO, 9, 4, 8))
                        .add(item(Items.ENCHANTED_BOOK, 8))
                        .add(item(OtherBlocks.UMBER_GOLEM_STATUE, 1))
                        .add(item(ModItems.WEB_SLINGER, 1))
                        .add(item(Items.GOLDEN_PICKAXE, 3))
                        .add(item(Items.IRON_PICKAXE, 2))
                        .add(item(ModItems.JADE_PICKAXE, 1))
                        .add(item(Items.GOLDEN_SHOVEL, 3))
                        .add(item(Items.IRON_SHOVEL, 2))
                        .add(item(ModItems.JADE_SHOVEL, 1))
                        .add(item(Items.GOLDEN_AXE, 3))
                        .add(item(Items.IRON_AXE, 2))
                        .add(item(ModItems.JADE_AXE, 1))
                        .add(item(Items.GOLDEN_SWORD, 3))
                        .add(item(Items.IRON_SWORD, 2))
                        .add(item(ModItems.JADE_SWORD, 1))
                        .add(item(Items.GOLDEN_CHESTPLATE, 1))
                        .add(item(Items.IRON_CHESTPLATE, 2))
                        .add(item(ModItems.JADE_CHESTPLATE, 1))
                        .add(item(Items.GOLDEN_HELMET, 1))
                        .add(item(Items.IRON_HELMET, 2))
                        .add(item(ModItems.JADE_HELMET,1))
                        .add(item(Items.GOLDEN_LEGGINGS, 1))
                        .add(item(Items.IRON_LEGGINGS, 2))
                        .add(item(ModItems.JADE_LEGGINGS, 1))
                        .add(item(Items.GOLDEN_BOOTS, 1))
                        .add(item(Items.IRON_BOOTS, 2))
                        .add(item(ModItems.JADE_BOOTS, 1))
                        .add(item(ModItems.ALTAR_FRAGMENT, 1))
                        .add(item(ModItems.REINFORCED_PLATE_EXO, 1))
                        .add(item(ModItems.SCORPION_PINCER, 1))
                        .add(item(ModItems.WHETSTONE_POWDER, 1))
                        .add(item(ModItems.PLATE_EXO_RHINO, 1))
                        .add(item(ModItems.HONEY_SANDWICH, 3, 1, 3))
                        .add(item(ModItems.CABBAGE_SEEDS, 2, 1, 3))
                        .add(item(ModItems.WHETSTONE, 1))
                        .add(item(ModItems.LIFE_BLOOD, 4, 1, 2))
                        .add(item(ModItems.ROLLED_NEWSPAPER, 1))
                        .add(item(ModItems.BAMBUCKET, 6))
                        .add(item(ModItems.HOMING_BEECON, 1))
                        .add(item(OtherBlocks.GLOW_GEM_INACTIVE, 5, 1, 3))
                        .add(item(ModItems.GIVIN_ME_THE_BLUES, 3, 1, 3))
                        .add(item(ModItems.BRYUFS_BREW, 1))
                        .add(item(ModItems.WASP_DAGGER, 2, 1, 3))
                )
        );

        consumer.accept(ANTLION_LAIR, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(10, 14))
                        .add(item(Items.BOOK, 18, 1, 4))
                        .add(item(Items.PAPER, 16, 2, 6))
                        .add(item(Blocks.COBWEB, 13, 2, 7))
                        .add(item(ModItems.JADE, 10, 1, 3))
                        .add(item(ModItems.PLATE_EXO, 9, 4, 8))
                        .add(item(Items.ENCHANTED_BOOK, 8))
                        .add(item(Items.GOLDEN_PICKAXE, 3))
                        .add(item(Items.IRON_PICKAXE, 2))
                        .add(item(ModItems.JADE_PICKAXE, 1))
                        .add(item(Items.GOLDEN_SHOVEL, 3))
                        .add(item(Items.IRON_SHOVEL, 2))
                        .add(item(ModItems.JADE_SHOVEL, 1))
                        .add(item(Items.GOLDEN_AXE, 3))
                        .add(item(Items.IRON_AXE, 2))
                        .add(item(ModItems.JADE_AXE, 1))
                        .add(item(Items.GOLDEN_SWORD, 3))
                        .add(item(Items.IRON_SWORD, 2))
                        .add(item(ModItems.JADE_SWORD, 1))
                        .add(item(Items.GOLDEN_CHESTPLATE, 1))
                        .add(item(Items.IRON_CHESTPLATE, 2))
                        .add(item(ModItems.JADE_CHESTPLATE, 1))
                        .add(item(Items.GOLDEN_HELMET, 1))
                        .add(item(Items.IRON_HELMET, 2))
                        .add(item(ModItems.JADE_HELMET,1))
                        .add(item(Items.GOLDEN_LEGGINGS, 1))
                        .add(item(Items.IRON_LEGGINGS, 2))
                        .add(item(ModItems.JADE_LEGGINGS, 1))
                        .add(item(Items.GOLDEN_BOOTS, 1))
                        .add(item(Items.IRON_BOOTS, 2))
                        .add(item(ModItems.JADE_BOOTS, 1))
                        .add(item(OtherBlocks.UMBER_GOLEM_STATUE, 1))
                        .add(item(ModItems.WEB_SLINGER, 1))
                )
        );

        consumer.accept(DRAGONFLY_DUNGEON, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(5, 15))
                        .add(item(Items.BOOK, 18, 1, 4))
                        .add(item(Items.PAPER, 16, 2, 6))
                        .add(item(ModItems.WATER_REPELLENT, 3, 1, 2))
                        .add(item(ModItems.PLATE_EXO, 9, 4, 8))
                        .add(item(Items.ENCHANTED_BOOK, 8))
                        .add(item(Items.GOLDEN_PICKAXE, 3))
                        .add(item(Items.IRON_PICKAXE, 2))
                        .add(item(ModItems.JADE_PICKAXE, 1))
                        .add(item(Items.GOLDEN_SHOVEL, 3))
                        .add(item(Items.IRON_SHOVEL, 2))
                        .add(item(ModItems.JADE_SHOVEL, 1))
                        .add(item(Items.GOLDEN_AXE, 3))
                        .add(item(Items.IRON_AXE, 2))
                        .add(item(ModItems.JADE_AXE, 1))
                        .add(item(Items.GOLDEN_SWORD, 3))
                        .add(item(Items.IRON_SWORD, 2))
                        .add(item(ModItems.JADE_SWORD, 1))
                        .add(item(Items.GOLDEN_CHESTPLATE, 1))
                        .add(item(Items.IRON_CHESTPLATE, 2))
                        .add(item(ModItems.JADE_CHESTPLATE, 1))
                        .add(item(Items.GOLDEN_HELMET, 1))
                        .add(item(Items.IRON_HELMET, 2))
                        .add(item(ModItems.JADE_HELMET,1))
                        .add(item(Items.GOLDEN_LEGGINGS, 1))
                        .add(item(Items.IRON_LEGGINGS, 2))
                        .add(item(ModItems.JADE_LEGGINGS, 1))
                        .add(item(Items.GOLDEN_BOOTS, 1))
                        .add(item(Items.IRON_BOOTS, 2))
                        .add(item(ModItems.JADE_BOOTS, 1))
                        .add(item(ModItems.ALTAR_FRAGMENT, 1))
                        .add(item(ModItems.REINFORCED_PLATE_EXO, 1))
                        .add(item(ModItems.HYDROFUGE, 3, 1, 3))
                        .add(item(ModItems.PLATE_EXO_RHINO, 1))
                        .add(item(ModItems.HONEY_SANDWICH, 3, 1, 3))
                        .add(item(ModItems.CABBAGE_SEEDS, 2, 1, 3))
                        .add(item(ModItems.LIFE_BLOOD, 4, 1, 2))
                        .add(item(ModItems.ROLLED_NEWSPAPER, 1))
                        .add(item(ModItems.BAMBUCKET, 6))
                        .add(item(ModItems.WHETSTONE, 1))
                        .add(item(OtherBlocks.GLOW_GEM_INACTIVE, 5, 1, 3))
                        .add(item(ModItems.NOTHING_IN_THE_MIDDLE, 3, 1, 3))
                        .add(item(ModItems.BRYUFS_BREW, 1))
                        .add(item(ModItems.WASP_DAGGER, 2, 1, 3))
                )
        );

        consumer.accept(DUNG_PILE, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(8, 14))
                        .add(item(ModItems.JADE, 10, 1, 3))
                        .add(item(ModItems.PLATE_EXO, 9, 4, 8))
                        .add(item(Items.ENCHANTED_BOOK, 8))
                        .add(item(Items.IRON_PICKAXE, 2))
                        .add(item(ModItems.JADE_PICKAXE, 1))
                        .add(item(Items.IRON_SHOVEL, 2))
                        .add(item(ModItems.JADE_SHOVEL, 1))
                        .add(item(Items.IRON_AXE, 2))
                        .add(item(ModItems.JADE_AXE, 1))
                        .add(item(Items.IRON_SWORD, 2))
                        .add(item(ModItems.JADE_SWORD, 1))
                        .add(item(Items.IRON_CHESTPLATE, 2))
                        .add(item(ModItems.JADE_CHESTPLATE, 1))
                        .add(item(Items.IRON_HELMET, 2))
                        .add(item(ModItems.JADE_HELMET, 1))
                        .add(item(Items.IRON_LEGGINGS, 2))
                        .add(item(ModItems.JADE_LEGGINGS, 1))
                        .add(item(Items.IRON_BOOTS, 2))
                        .add(item(ModItems.JADE_BOOTS, 1))
                        .add(item(ModItems.EXOSKELETON_SHIELD, 3))
                        .add(item(ModItems.BAMBOO_SHIELD, 3))
                        .add(item(ModItems.ANTI_VENOM_BOTTLE, 8, 1, 3))
                        .add(item(ModItems.SPRAY_CAN, 6, 3, 6))
                        .add(item(ModItems.SCORPION_PINCER, 1))
                        .add(item(ModItems.REINFORCED_PLATE_EXO, 4, 1, 3))
                        .add(item(ModItems.AMBER_STAR, 6, 3, 9))
                        .add(item(ModItems.BEETLE_RIDING_KIT, 1))
                        .add(item(ModItems.TITAN_CHOP_RAW, 6, 1, 3))
                        .add(item(ModItems.TITAN_CHOP_COOKED, 4, 1, 2))
                        .add(item(ModItems.STAG_HEART_RAW, 1))
                        .add(item(ModItems.BAMBUCKET, 3))
                        .add(item(ModItems.CABBAGE_SEEDS, 5, 3))
                        .add(item(ModItems.CABBAGE, 10, 1, 3))
                )
        );

        consumer.accept(LOCUST_SHRINE, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(2, 3))
                        .add(item(ModItems.JADE, 10, 1, 3))
                        .add(item(ModItems.PLATE_EXO, 9, 4, 8))
                        .add(item(Items.ENCHANTED_BOOK, 8))
                        .add(item(Items.IRON_PICKAXE, 2))
                        .add(item(ModItems.JADE_PICKAXE, 1))
                        .add(item(Items.IRON_AXE, 2))
                        .add(item(ModItems.JADE_AXE, 1))
                        .add(item(ModItems.BAMBOO_CHESTPLATE, 2))
                        .add(item(ModItems.JADE_CHESTPLATE, 1))
                        .add(item(ModItems.BAMBOO_HELMET, 2))
                        .add(item(ModItems.JADE_HELMET, 1))
                        .add(item(ModItems.BAMBOO_LEGGINGS, 2))
                        .add(item(ModItems.JADE_LEGGINGS, 1))
                        .add(item(ModItems.BAMBOO_BOOTS, 2))
                        .add(item(ModItems.EXOSKELETON_BOOTS, 3))
                        .add(item(ModItems.BAMBOO_SHIELD, 3))
                        .add(item(ModItems.ANTI_VENOM_BOTTLE, 8, 1, 3))
                        .add(item(ModItems.SPRAY_CAN, 5, 3, 6))
                        .add(item(ModItems.FLY_WING, 1))
                        .add(item(ModItems.REINFORCED_PLATE_EXO, 4, 1, 3))
                        .add(item(ModItems.BEETLE_TAMING_AMULET, 1))
                        .add(item(ModItems.BAMBOO_SOUP, 4, 1, 3))
                        .add(item(ModItems.LARVAE_ON_STICK, 6, 1, 2))
                        .add(item(ModItems.BAMBUCKET, 3))
                        .add(item(ModItems.CABBAGE_SEEDS, 5, 1, 3))
                        .add(item(ModItems.CABBAGE, 10, 1, 3))
                        .add(item(ModItems.TURNIP, 10, 1, 3))
                )
        );

        consumer.accept(SPIDER_DUNGEON, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(UniformGenerator.between(3, 10))
                        .add(item(Items.STRING, 13, 5, 10))
                        .add(item(ModItems.DARK_FRUIT_PIE, 13, 1, 2))
                        .add(item(Blocks.COBWEB, 13, 3, 8))
                        .add(item(Items.STICK, 12, 1, 8))
                        .add(item(Items.GOLD_NUGGET, 12, 3, 11))
                        .add(item(ModItems.SHARD_BONE, 12, 3, 8))
                        .add(item(Items.BONE, 11, 1, 3))
                        .add(item(Items.IRON_INGOT, 10, 1, 3))
                        .add(item(Items.GOLD_INGOT, 10, 1, 2))
                        .add(item(ModItems.FLY_WING, 10, 1, 5))
                        .add(item(ModItems.JADE, 9))
                        .add(item(ModItems.PLATE_EXO, 8, 3, 6))
                        .add(item(ModItems.COMPOUND_EYES, 7, 2, 6))
                        .add(item(ModItems.COMPOUND_LENS, 2))
                        .add(item(OtherBlocks.UMBER_GOLEM_STATUE, 1))
                        .add(item(ModItems.MAX_SPEED_BOW, 1))
                )
        );
    }

    private LootPoolSingletonContainer.Builder<?> item(ItemLike item, int weight, int min, int max) {
        return LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(min, max)));
    }

    private LootPoolSingletonContainer.Builder<?> item(ItemLike item, int weight, int quantity) {
        return LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(quantity)));
    }

    private LootPoolSingletonContainer.Builder<?> item(ItemLike item, int weight) {
        return item(item, weight, 1);
    }

    private LootPoolSingletonContainer.Builder<?> itemEnchanted(ItemLike item, int weight) {
        return LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(1, 10))
                        .fromOptions(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.TREASURE)));
    }
}
