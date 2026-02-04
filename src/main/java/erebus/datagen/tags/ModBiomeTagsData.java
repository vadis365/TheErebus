package erebus.datagen.tags;

import erebus.Erebus;
import erebus.registries.data.tags.ModBiomeTags;
import erebus.registries.world.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagsData extends BiomeTagsProvider {
    public ModBiomeTagsData(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, Erebus.MODID);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ModBiomeTags.IS_FUNGAL_FOREST)
                .add(ModBiomes.FUNGAL_FOREST_KEY);

        tag(ModBiomeTags.HAS_ANTLION_DUNGEON)
                .add(ModBiomes.VOLCANIC_DESERT_KEY);
        tag(ModBiomeTags.HAS_ANTLION_LAIR)
                .add(ModBiomes.VOLCANIC_DESERT_KEY);
        tag(ModBiomeTags.HAS_DRAGONFLY_DUNGEON)
                .add(ModBiomes.SUBMERGED_SWAMP_KEY);
        tag(ModBiomeTags.HAS_DUNG_PILE)
                .add(ModBiomes.ULTERIOR_OUTBACK_KEY);
        tag(ModBiomeTags.HAS_LOCUST_SHRINE)
                .add(ModBiomes.SUBTERRANEAN_SAVANNAH_KEY);
        tag(ModBiomeTags.HAS_SPIDER_DUNGEON);
        tag(ModBiomeTags.HAS_SWAMP_HUT)
                .add(ModBiomes.SUBMERGED_SWAMP_KEY);
        tag(ModBiomeTags.HAS_WASP_DUNGEON)
                .add(ModBiomes.UNDERGROUND_JUNGLE_KEY);
        tag(ModBiomeTags.HAS_GIANT_FLOWERS)
                .add(ModBiomes.ELYSIAN_FIELDS_KEY)
                .add(ModBiomes.ELYSIAN_FOREST_KEY);
        tag(ModBiomeTags.HAS_ROTTEN_STUMPS)
                .add(ModBiomes.FUNGAL_FOREST_KEY);
        tag(ModBiomeTags.HAS_BIG_LOGS)
                .add(ModBiomes.FUNGAL_FOREST_KEY);

        tag(ModBiomeTags.IS_EREBUS)
                .add(ModBiomes.ELYSIAN_FIELDS_KEY)
                .add(ModBiomes.ELYSIAN_FOREST_KEY)
                .add(ModBiomes.FUNGAL_FOREST_KEY)
                .add(ModBiomes.PETRIFIED_FOREST_KEY)
                .add(ModBiomes.SUBMERGED_SWAMP_KEY)
                .add(ModBiomes.SUBTERRANEAN_SAVANNAH_KEY)
                .add(ModBiomes.ULTERIOR_OUTBACK_KEY)
                .add(ModBiomes.UNDERGROUND_JUNGLE_KEY)
                .add(ModBiomes.VOLCANIC_DESERT_KEY);
    }
}
