package erebus.datagen.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;

import java.util.function.BiConsumer;

import static erebus.registries.data.ModLootTables.FERN_SEEDS;

public record ModHarvestLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(FERN_SEEDS, LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(Items.WHEAT_SEEDS))));
    }
}
