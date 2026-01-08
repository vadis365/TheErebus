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
        tag(ModBiomeTags.IS_EREBUS)
                .add(ModBiomes.ELYSIAN_FIELDS.getResourceKey())
                .add(ModBiomes.ELYSIAN_FOREST.getResourceKey())
                .add(ModBiomes.FUNGAL_FOREST.getResourceKey())
                .add(ModBiomes.PETRIFIED_FOREST.getResourceKey())
                .add(ModBiomes.SUBMERGED_SWAMP.getResourceKey())
                .add(ModBiomes.SUBTERRANEAN_SAVANNAH.getResourceKey())
                .add(ModBiomes.ULTERIOR_OUTBACK.getResourceKey())
                .add(ModBiomes.UNDERGROUND_JUNGLE.getResourceKey())
                .add(ModBiomes.VOLCANIC_DESERT.getResourceKey());

        tag(ModBiomeTags.IS_FUNGAL_FOREST)
        .add(ModBiomes.FUNGAL_FOREST.getResourceKey());

        tag(ModBiomeTags.HAS_ANTLION_DUNGEON)
                .add(ModBiomes.VOLCANIC_DESERT.getResourceKey());
        tag(ModBiomeTags.HAS_ANTLION_LAIR)
                .add(ModBiomes.VOLCANIC_DESERT.getResourceKey());
        tag(ModBiomeTags.HAS_DRAGONFLY_DUNGEON)
                .add(ModBiomes.SUBMERGED_SWAMP.getResourceKey());
        tag(ModBiomeTags.HAS_DUNG_PILE)
                .add(ModBiomes.ULTERIOR_OUTBACK.getResourceKey());
        tag(ModBiomeTags.HAS_LOCUST_SHRINE)
                .add(ModBiomes.SUBTERRANEAN_SAVANNAH.getResourceKey());
        tag(ModBiomeTags.HAS_SPIDER_DUNGEON);
        tag(ModBiomeTags.HAS_SWAMP_HUT)
                .add(ModBiomes.SUBMERGED_SWAMP.getResourceKey());
        tag(ModBiomeTags.HAS_WASP_DUNGEON)
                .add(ModBiomes.UNDERGROUND_JUNGLE.getResourceKey());
        tag(ModBiomeTags.HAS_GIANT_FLOWERS)
                .add(ModBiomes.ELYSIAN_FIELDS.getResourceKey())
                .add(ModBiomes.ELYSIAN_FOREST.getResourceKey());
        tag(ModBiomeTags.HAS_ROTTEN_STUMPS)
                .add(ModBiomes.FUNGAL_FOREST.getResourceKey());
        tag(ModBiomeTags.HAS_BIG_LOGS)
                .add(ModBiomes.FUNGAL_FOREST.getResourceKey());
    }
}
