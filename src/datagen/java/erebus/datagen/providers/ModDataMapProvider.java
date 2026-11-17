package erebus.datagen.providers;

import erebus.registries.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.FurnaceFuel;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModDataMapProvider extends DataMapProvider {
    public ModDataMapProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    protected void gather(HolderLookup.@NonNull Provider provider) {
        var fuels = builder(NeoForgeDataMaps.FURNACE_FUELS);
        for (ItemLike block : new ItemLike[]{
                ModBlocks.BAMBOO_CRATE, ModBlocks.BAMBOO_BRIDGE, ModBlocks.BAMBOO_NERD_POLE,
                ModBlocks.BAMBOO_EXTENDER, ModBlocks.BAMBOO_TORCH, ModBlocks.BAMBOO_PIPE, ModBlocks.BAMBOO_PIPE_EXTRACT,
                ModBlocks.SILO_ROOF, ModBlocks.SILO_SUPPORTS, ModBlocks.GIANT_LILY_PAD,
                ModBlocks.LOG_HOLLOW, ModBlocks.GLOWSHROOM_STALK,
                ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK, ModBlocks.DARK_CAPPED_MUSHROOM_STEM,
                ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK, ModBlocks.DUTCH_CAP_MUSHROOM_STEM,
                ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK, ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM,
                ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK, ModBlocks.KAIZERS_FINGERS_MUSHROOM_STEM,
                ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK, ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM}) {
            fuels.add(block.asItem().builtInRegistryHolder(), new FurnaceFuel(300), false);
        }
        // Modern wooden chest variants follow the ordinary chest fuel value.
        for (ItemLike chest : new ItemLike[]{ModBlocks.CHEST_ASPER, ModBlocks.CHEST_BAMBOO,
                ModBlocks.CHEST_BAOBAB, ModBlocks.CHEST_BALSAM, ModBlocks.CHEST_CYPRESS,
                ModBlocks.CHEST_EUCALYPTUS, ModBlocks.CHEST_MAHOGANY, ModBlocks.CHEST_MARSHWOOD,
                ModBlocks.CHEST_MOSSBARK, ModBlocks.CHEST_ROTTEN, ModBlocks.CHEST_SCORCHED,
                ModBlocks.CHEST_VARNISHED, ModBlocks.CHEST_WHITE}) {
            fuels.add(chest.asItem().builtInRegistryHolder(), new FurnaceFuel(300), false);
        }
    }
}
