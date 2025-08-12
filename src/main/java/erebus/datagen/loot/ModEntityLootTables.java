package erebus.datagen.loot;

import java.util.function.Supplier;
import java.util.stream.Stream;

import org.jetbrains.annotations.NotNull;

import erebus.datagen.loot.predicates.DragonflyPredicate;
import erebus.datagen.loot.predicates.WaspPredicate;
import erebus.registries.ModItems;
import erebus.registries.blocks.providers.PlantBlocks;
import erebus.registries.entity.ModEntities;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModEntityLootTables extends EntityLootSubProvider {

    public ModEntityLootTables(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
        //TODO work out how TF this all works now
        //this.noLoot(ModEntities.ANIMATED_BLOCK);

        add(
                ModEntities.BLACK_WIDOW.get(),
                createMultiPoolLootTable(
                        createPlayerKillPool(Items.SPIDER_EYE, UniformGenerator.between(-1, 1)),
                        createStandardPool(ModItems.POISON_GLAND, ConstantValue.exactly(1))
                )
        );

        add(
                ModEntities.LAVA_WEB_SPIDER.get(),
                createMultiPoolLootTable(
                        createPlayerKillPool(Items.SPIDER_EYE, UniformGenerator.between(-1, 1)),
                        createStandardPool(Items.FIRE_CHARGE, ConstantValue.exactly(1))
                )
        );

        add(
                ModEntities.SCYTODES.get(),
                createMultiPoolLootTable(
                        createPlayerKillPool(Items.SPIDER_EYE, UniformGenerator.between(-1, 1)),
                        createStandardPool(Items.STRING, ConstantValue.exactly(1))
                )
        );

        add(
                ModEntities.MONEY_SPIDER.get(),
                createMultiPoolLootTable(
                        createPlayerKillChancePool(Items.GOLD_INGOT, UniformGenerator.between(-1, 1), 0.1F),
                        createStandardPool(Items.GOLD_NUGGET, ConstantValue.exactly(1))
                )
        );

        // Special case for WASP with boss condition
        add(
                ModEntities.WASP.get(),
                LootTable.lootTable()
                        .withPool(
                                createPlayerKillChancePool(ModItems.WASP_STING, UniformGenerator.between(-1, 1), 0.1F)
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(ModItems.ANTI_VENOM_BOTTLE)
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1)))
                                        )
                                        .when(
                                                LootItemEntityPropertyCondition.hasProperties(
                                                        LootContext.EntityTarget.THIS,
                                                        EntityPredicate.Builder.entity()
                                                                .subPredicate(WaspPredicate.isBoss(true))
                                                )
                                        )
                        )
        );

        add(
                ModEntities.WORKER_BEE.get(),
                createSimpleLootTable(ModItems.NECTAR, UniformGenerator.between(-1, 1))
        );

        add(
                ModEntities.MOTH.get(),
                LootTable.lootTable()
                        .withPool(
                                createChancePool(Items.GLOWSTONE_DUST, UniformGenerator.between(-1, 1), 0.2F)
                        )
        );

        add(
                ModEntities.VELVET_WORM.get(),
                createSimpleLootTable(Items.SLIME_BALL, UniformGenerator.between(-1, 2))
        );

        add(
                ModEntities.ANTLION.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.PLATE_EXO, UniformGenerator.between(-1, 1)),
                        createStandardPool(Blocks.SAND, ConstantValue.exactly(1))
                )
        );

        add(
                ModEntities.BOT_FLY.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.FLY_WING, UniformGenerator.between(-1, 1)),
                        createChancePool(ModItems.COMPOUND_EYES, UniformGenerator.between(-1, 1), 0.2F)
                )
        );

        add(
                ModEntities.BOT_FLY_LARVA.get(),
                createSimpleLootTable(Items.SLIME_BALL, UniformGenerator.between(-1, 1))
        );

        add(
                ModEntities.FLY.get(),
                createMultiPoolLootTable(
                        createChancePool(ModItems.FLY_WING, UniformGenerator.between(-1, 1), 0.1F),
                        createChancePool(ModItems.COMPOUND_EYES, UniformGenerator.between(-1, 1), 0.05F)
                )
        );

        // Special case for DRAGON_FLY with skin condition
        add(
                ModEntities.DRAGON_FLY.get(),
                LootTable.lootTable()
                        .withPool(
                                createStandardPool(ModItems.DRAGONFLY_WING, UniformGenerator.between(-1, 1))
                        )
                        .withPool(
                                createChancePool(ModItems.COMPOUND_EYES, UniformGenerator.between(0, 2), 0.2F)
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.ENDER_PEARL)
                                                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1)))
                                        )
                                        .when(
                                                LootItemEntityPropertyCondition.hasProperties(
                                                        LootContext.EntityTarget.THIS,
                                                        EntityPredicate.Builder.entity()
                                                                .subPredicate(DragonflyPredicate.skin(0))
                                                )
                                        )
                        )
        );

        add(
                ModEntities.CENTIPEDE.get(),
                createMultiPoolLootTable(
                        createStandardPool(ModItems.BIO_VELOCITY, UniformGenerator.between(-1, 1)),
                        createStandardPool(ModItems.POISON_GLAND, UniformGenerator.between(-1, 1))
                )
        );

        add(
                ModEntities.GRASSHOPPER.get(),
                LootTable.lootTable()
                        .withPool(
                                createCookableFoodPool(ModItems.GRASSHOPPER_LEG_RAW, UniformGenerator.between(1, 4))
                        )
        );

        add(
                ModEntities.LOCUST.get(),
                createSimpleLootTable(ModItems.ELASTIC_FIBER, UniformGenerator.between(1, 4))
        );

        add(
                ModEntities.BEETLE_LARVA.get(),
                LootTable.lootTable()
                        .withPool(
                                createCookableFoodPool(ModItems.BEETLE_LARVA_RAW, ConstantValue.exactly(1))
                        )
        );

        add(
                ModEntities.BOMBARDIER_BEETLE_LARVA.get(),
                LootTable.lootTable()
                        .withPool(
                                createCookableFoodPool(ModItems.BEETLE_LARVA_RAW, ConstantValue.exactly(1))
                        )
        );

        add(
                ModEntities.BOMBARDIER_BEETLE.get(),
                createMultiPoolLootTable(
                        createStandardPool(Items.GUNPOWDER, ConstantValue.exactly(1)),
                        createStandardPool(Items.BLAZE_POWDER, ConstantValue.exactly(1)),
                        createStandardPool(ModItems.PLATE_EXO, UniformGenerator.between(1, 3))
                )
        );

        add(
                ModEntities.PUNCHROOM.get(),
                LootTable.lootTable()
                        .withPool(
                                createChancePool(ModItems.ELASTIC_FIBER, ConstantValue.exactly(1), 0.2F)
                        )
        );

        // CROP_WEEVIL has complex loot tables with many options
        // First pool: various stigma blocks
        // Second pool: various seeds
        // Third pool: various crops with a chance condition
        add(
                ModEntities.CROP_WEEVIL.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        // Using multiple add() calls for different stigma blocks
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_BLACK)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_RED)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_BROWN)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_BLUE)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_PURPLE)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_CYAN)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_LIGHT_GRAY)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_GRAY)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_PINK)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_YELLOW)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_LIGHT_BLUE)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_MAGENTA)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_ORANGE)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.STIGMA_WHITE)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        // Seeds pool
                                        .add(LootItem.lootTableItem(Items.PUMPKIN_SEEDS)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(Items.MELON_SEEDS)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(Items.WHEAT_SEEDS)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(ModItems.CABBAGE_SEEDS)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        // Crops pool with chance condition
                                        .add(LootItem.lootTableItem(ModItems.TURNIP)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(Items.NETHER_WART)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(Items.WHEAT)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(Items.SUGAR_CANE)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(ModItems.BAMBOO_SHOOT)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(Items.CARROT)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(Items.POTATO)
                                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .when(LootItemRandomChanceCondition.randomChance(0.1F))
                        )
        );

        // FUNGAL_WEEVIL has a pool with multiple mushroom options
        add(
                ModEntities.FUNGAL_WEEVIL.get(),
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        // Standard mushrooms
                                        .add(LootItem.lootTableItem(Items.BROWN_MUSHROOM)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(Items.RED_MUSHROOM)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        // Custom mushrooms
                                        .add(LootItem.lootTableItem(PlantBlocks.DARK_CAPPED_MUSHROOM)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.DUTCH_CAP_MUSHROOM)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.GRANDMAS_SHOES_MUSHROOM)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.KAIZERS_FINGERS_MUSHROOM)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                                        .add(LootItem.lootTableItem(PlantBlocks.SARCASTIC_CZECH_MUSHROOM)
                                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))))
                        )
        );

        add(
                ModEntities.BED_BUG.get(),
                createSimpleLootTable(Items.WHITE_WOOL, UniformGenerator.between(1, 2))
        );

		add(ModEntities.HONEY_POT_ANT.get(), createSimpleLootTable(ModItems.NECTAR, ConstantValue.exactly(1))); //temp should dropped tamed amount if tamed

        // Entities with no loot ATM
        noLoot(ModEntities.BLACK_ANT);
        noLoot(ModEntities.BEETLE);
        noLoot(ModEntities.ZOMBIE_ANT);
        noLoot(ModEntities.ZOMBIE_ANT_SOLDIER);
    }
    
    /**
     * Creates a standard loot pool with a single item
     * @param item The item to drop
     * @param countRange The range of items to drop (min, max)
     * @return A LootPool builder
     */
    private LootPool.Builder createStandardPool(ItemLike item, NumberProvider countRange) {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(countRange))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1)))
                );
    }
    
    /**
     * Creates a standard loot pool with a single item and a chance condition
     * @param item The item to drop
     * @param countRange The range of items to drop (min, max)
     * @param chance The chance of dropping (0.0-1.0)
     * @return A LootPool builder
     */
    private LootPool.Builder createChancePool(ItemLike item, NumberProvider countRange, float chance) {
        LootPool.Builder pool = createStandardPool(item, countRange);
        return pool.when(LootItemRandomChanceCondition.randomChance(chance));
    }
    
    /**
     * Creates a standard loot pool with a single item that requires player kill
     * @param item The item to drop
     * @param countRange The range of items to drop (min, max)
     * @return A LootPool builder
     */
    private LootPool.Builder createPlayerKillPool(ItemLike item, NumberProvider countRange) {
        LootPool.Builder pool = createStandardPool(item, countRange);
        return pool.when(LootItemKilledByPlayerCondition.killedByPlayer());
    }
    
    /**
     * Creates a standard loot pool with a single item that requires player kill and has a chance
     * @param item The item to drop
     * @param countRange The range of items to drop (min, max)
     * @param chance The chance of dropping (0.0-1.0)
     * @return A LootPool builder
     */
    private LootPool.Builder createPlayerKillChancePool(ItemLike item, NumberProvider countRange, float chance) {
        LootPool.Builder pool = createPlayerKillPool(item, countRange);
        return pool.when(LootItemRandomChanceCondition.randomChance(chance));
    }
    
    /**
     * Creates a standard loot table with a single pool
     * @param item The item to drop
     * @param countRange The range of items to drop (min, max)
     * @return A LootTable builder
     */
    private LootTable.Builder createSimpleLootTable(ItemLike item, NumberProvider countRange) {
        return LootTable.lootTable()
                .withPool(createStandardPool(item, countRange));
    }
    
    /**
     * Creates a standard loot table with multiple pools
     * @param pools The loot pools to add
     * @return A LootTable builder
     */
    private LootTable.Builder createMultiPoolLootTable(LootPool.Builder... pools) {
        LootTable.Builder table = LootTable.lootTable();
        for (LootPool.Builder pool : pools) {
            table.withPool(pool);
        }
        return table;
    }
    
    /**
     * Creates a food item loot pool that can be cooked if the entity is on fire
     * @param rawItem The raw food item
     * @param countRange The range of items to drop (min, max)
     * @return A LootPool builder
     */
    private LootPool.Builder createCookableFoodPool(ItemLike rawItem, NumberProvider countRange) {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(rawItem)
                        .apply(SetItemCountFunction.setCount(countRange))
                        .apply(SmeltItemFunction.smelted().when(shouldSmeltLoot()))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1))));
    }

    public <T extends Entity> void noLoot(DeferredHolder<EntityType<?>, EntityType<T>> type) {
        add(type.get(), LootTable.lootTable());
    }

    @Override
    protected @NotNull Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntities.ENTITY_TYPES.getEntries().stream().map(Supplier::get);
    }
}
