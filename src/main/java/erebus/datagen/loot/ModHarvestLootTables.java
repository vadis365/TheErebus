package erebus.datagen.loot;

import erebus.Erebus;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;

import java.util.function.BiConsumer;

public record ModHarvestLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {
    public static final ResourceKey<LootTable> FERN_SEEDS = ResourceKey.create(Registries.LOOT_TABLE, Erebus.prefix("harvesting/fern_seeds"));

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(FERN_SEEDS, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.WHEAT_SEEDS))));
    }
}
