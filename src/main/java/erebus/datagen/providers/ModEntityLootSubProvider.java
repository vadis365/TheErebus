package erebus.datagen.providers;

import erebus.registries.entity.ModEntities;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class ModEntityLootSubProvider extends EntityLootSubProvider {

    public ModEntityLootSubProvider(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    /**
     * Creates a standard loot pool with a single item
     * @param item The item to drop
     * @param countRange The range of items to drop (min, max)
     * @return A LootPool builder
     */
    protected LootPool.Builder createStandardPool(ItemLike item, NumberProvider countRange) {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(createLootItem(item, countRange));
    }

    /**
     * Creates a LootItem builder with count and looting multiplier
     * @param item The item to drop
     * @param countRange The range of items to drop (min, max)
     * @return A LootItem builder
     */
    protected LootItem.Builder<?> createLootItem(ItemLike item, NumberProvider countRange) {
        return LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(countRange))
                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(registries, UniformGenerator.between(0, 1)));
    }

    /**
     * Adds multiple items to a loot pool with the same count and looting multiplier
     * @param builder The pool builder
     * @param countRange The range of items to drop (min, max)
     * @param items The items to add
     * @return The pool builder
     */
    protected LootPool.Builder addItemsToPool(LootPool.Builder builder, NumberProvider countRange, ItemLike... items) {
        for (ItemLike item : items) {
            builder.add(createLootItem(item, countRange));
        }
        return builder;
    }

    /**
     * Creates a standard loot pool with a single item and a chance condition
     * @param item The item to drop
     * @param countRange The range of items to drop (min, max)
     * @param chance The chance of dropping (0.0-1.0)
     * @return A LootPool builder
     */
    protected LootPool.Builder createChancePool(ItemLike item, NumberProvider countRange, float chance) {
        LootPool.Builder pool = createStandardPool(item, countRange);
        return pool.when(LootItemRandomChanceCondition.randomChance(chance));
    }

    /**
     * Creates a standard loot pool with a single item that requires player kill
     * @param item The item to drop
     * @param countRange The range of items to drop (min, max)
     * @return A LootPool builder
     */
    protected LootPool.Builder createPlayerKillPool(ItemLike item, NumberProvider countRange) {
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
    protected LootPool.Builder createPlayerKillChancePool(ItemLike item, NumberProvider countRange, float chance) {
        LootPool.Builder pool = createPlayerKillPool(item, countRange);
        return pool.when(LootItemRandomChanceCondition.randomChance(chance));
    }

    /**
     * Creates a standard loot table with a single pool
     * @param item The item to drop
     * @param countRange The range of items to drop (min, max)
     * @return A LootTable builder
     */
    protected LootTable.Builder createSimpleLootTable(ItemLike item, NumberProvider countRange) {
        return LootTable.lootTable()
                .withPool(createStandardPool(item, countRange));
    }

    /**
     * Creates a standard loot table with multiple pools
     * @param pools The loot pools to add
     * @return A LootTable builder
     */
    protected LootTable.Builder createMultiPoolLootTable(LootPool.Builder... pools) {
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
    protected LootPool.Builder createCookableFoodPool(ItemLike rawItem, NumberProvider countRange) {
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
