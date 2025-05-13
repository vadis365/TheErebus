package erebus.datagen.loot;

import erebus.Erebus;
import erebus.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
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

    private static ResourceKey<LootTable> createKey(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, Erebus.prefix(name));
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
        consumer.accept(ROTTEN_LOG, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1))
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
        return LootItem.lootTableItem(item)
                .setWeight(weight);
    }

    private LootPoolSingletonContainer.Builder<?> itemEnchanted(ItemLike item, int weight) {
        return LootItem.lootTableItem(item)
                .setWeight(weight)
                .apply(EnchantWithLevelsFunction.enchantWithLevels(registries, UniformGenerator.between(1, 10))
                        .fromOptions(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.TREASURE)));
    }
}
