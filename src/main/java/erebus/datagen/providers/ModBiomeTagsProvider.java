package erebus.datagen.providers;

import erebus.Erebus;
import erebus.registries.data.ModTags;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsProvider extends BiomeTagsProvider {
    public ModBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, Erebus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ModTags.IS_EREBUS)
                .add(ModBiomes.ELYSIAN_FIELDS.getResourceKey())
                .add(ModBiomes.ELYSIAN_FOREST.getResourceKey())
                .add(ModBiomes.FUNGAL_FOREST.getResourceKey())
                .add(ModBiomes.PETRIFIED_FOREST.getResourceKey())
                .add(ModBiomes.SUBMERGED_SWAMP.getResourceKey())
                .add(ModBiomes.SUBTERRANEAN_SAVANNAH.getResourceKey())
                .add(ModBiomes.ULTERIOR_OUTBACK.getResourceKey())
                .add(ModBiomes.UNDERGROUND_JUNGLE.getResourceKey())
                .add(ModBiomes.VOLCANIC_DESERT.getResourceKey());

        tag(ModTags.HAS_ANTLION_DUNGEON)
                .add(ModBiomes.VOLCANIC_DESERT.getResourceKey());
        tag(ModTags.HAS_ANTLION_LAIR)
                .add(ModBiomes.VOLCANIC_DESERT.getResourceKey());
        tag(ModTags.HAS_DRAGONFLY_DUNGEON)
                .add(ModBiomes.SUBMERGED_SWAMP.getResourceKey());
        tag(ModTags.HAS_DUNG_PILE)
                .add(ModBiomes.ULTERIOR_OUTBACK.getResourceKey());
        tag(ModTags.HAS_LOCUST_SHRINE)
                .add(ModBiomes.SUBTERRANEAN_SAVANNAH.getResourceKey());
        tag(ModTags.HAS_SPIDER_DUNGEON);
        tag(ModTags.HAS_SWAMP_HUT)
                .add(ModBiomes.SUBMERGED_SWAMP.getResourceKey());
        tag(ModTags.HAS_WASP_DUNGEON)
                .add(ModBiomes.UNDERGROUND_JUNGLE.getResourceKey());
        tag(ModTags.HAS_GIANT_FLOWERS)
                .add(ModBiomes.ELYSIAN_FIELDS.getResourceKey())
                .add(ModBiomes.ELYSIAN_FOREST.getResourceKey());
        tag(ModTags.HAS_ROTTEN_STUMPS)
                .add(ModBiomes.FUNGAL_FOREST.getResourceKey());
        tag(ModTags.HAS_BIG_LOGS)
                .add(ModBiomes.FUNGAL_FOREST.getResourceKey());
    }
}
