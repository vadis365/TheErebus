package erebus.world.layer.biome;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import erebus.Erebus;
import erebus.datagen.ModRegistries;
import erebus.registries.world.ModBiomeLayerTypes;
import erebus.registries.world.ModBiomes;
import erebus.world.biome.util.TerrainBuilder;
import erebus.world.layer.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

public class BiomeLayerStack {
    public static final Codec<BiomeLayerFactory> DISPATCH_CODEC = ModBiomeLayerTypes.CODEC.dispatch("layer_type", BiomeLayerFactory::getType, BiomeLayerType::getCodec);
    public static final Codec<Holder<BiomeLayerFactory>> HOLDER_CODEC = RegistryFileCodec.create(ModRegistries.BIOME_STACK, DISPATCH_CODEC, true);

    public static final ResourceKey<BiomeLayerFactory> RANDOM_EREBUS_BIOMES = registerKey(ModRegistries.BIOME_STACK, "random_erebus_biomes");
    public static final ResourceKey<BiomeDensitySource> BIOME_GRID = registerKey(ModRegistries.BIOME_TERRAIN_DATA, "biome_grid");

    public static <T> ResourceKey<T> registerKey(ResourceKey<Registry<T>> registry, String name) {
        return ResourceKey.create(registry, Erebus.prefix(name));
    }

    public static void bootstrap(BootstrapContext<BiomeLayerFactory> context) {
        BiomeLayerFactory biomes = new RandomBiomeLayer.Factory(1L, 15, ImmutableList.of(
                ModBiomes.ELYSIAN_FIELDS.getResourceKey(),
                ModBiomes.FUNGAL_FOREST.getResourceKey(),
                ModBiomes.SUBMERGED_SWAMP.getResourceKey(),
                ModBiomes.SUBTERRANEAN_SAVANNAH.getResourceKey(),
                ModBiomes.UNDERGROUND_JUNGLE.getResourceKey()
        ), ImmutableList.of(
                ModBiomes.ULTERIOR_OUTBACK.getResourceKey(),
                ModBiomes.PETRIFIED_FOREST.getResourceKey(),
                ModBiomes.VOLCANIC_DESERT.getResourceKey()
        ));

        biomes = new KeyBiomeLayer.Factory(1000L, List.of(
                ModBiomes.ULTERIOR_OUTBACK.getResourceKey(),
                ModBiomes.VOLCANIC_DESERT.getResourceKey(),
                ModBiomes.ELYSIAN_FOREST.getResourceKey(),
                ModBiomes.PETRIFIED_FOREST.getResourceKey()
        ), Holder.direct(biomes));
        biomes = new CompanionBiomeLayer.Factory(1000L, List.of(
                Pair.of(ModBiomes.ELYSIAN_FIELDS.getResourceKey(), ModBiomes.ELYSIAN_FOREST.getResourceKey())
        ), Holder.direct(biomes));

        biomes = new ZoomLayer.Factory(1000L, false, Holder.direct(biomes));
        biomes = new ZoomLayer.Factory(1001L, false, Holder.direct(biomes));

        biomes = new StabilizeLayer.Factory(700L, Holder.direct(biomes));

        biomes = new ZoomLayer.Factory(1002L, false, Holder.direct(biomes));
        biomes = new ZoomLayer.Factory(1003L, false, Holder.direct(biomes));
        biomes = new ZoomLayer.Factory(1004L, false, Holder.direct(biomes));
        biomes = new ZoomLayer.Factory(1005L, false, Holder.direct(biomes));

        context.register(RANDOM_EREBUS_BIOMES, biomes);
    }

    public static void bootstrapData(BootstrapContext<BiomeDensitySource> context) {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);

        context.register(BIOME_GRID, new BiomeDensitySource(
                TerrainBuilder.getBiomeColumns(biomeRegistry),
                context.lookup(ModRegistries.BIOME_STACK).getOrThrow(BiomeLayerStack.RANDOM_EREBUS_BIOMES)
        ));
    }
}
