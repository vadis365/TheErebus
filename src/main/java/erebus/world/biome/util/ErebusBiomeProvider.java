package erebus.world.biome.util;

import com.mojang.serialization.MapCodec;
import erebus.datagen.ModRegistries;
import erebus.world.layer.biome.BiomeDensitySource;
import net.minecraft.core.Holder;
import net.minecraft.resources.RegistryFileCodec;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Stream;

public class ErebusBiomeProvider extends BiomeSource {

    public static final MapCodec<ErebusBiomeProvider> CODEC = RegistryFileCodec
            .create(ModRegistries.BIOME_TERRAIN_DATA, BiomeDensitySource.CODEC, false)
            .xmap(ErebusBiomeProvider::new, ErebusBiomeProvider::getBiomeConfig)
            .fieldOf("terrain_data");

    private final Holder<BiomeDensitySource> biomeConfig;

    public ErebusBiomeProvider(Holder<BiomeDensitySource> biomeConfig) {
        super();
        this.biomeConfig = biomeConfig;
    }

    private Holder<BiomeDensitySource> getBiomeConfig() {
        return biomeConfig;
    }

    @Override
    protected @NotNull MapCodec<? extends BiomeSource> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull Stream<Holder<Biome>> collectPossibleBiomes() {
        return biomeConfig.value().collectPossibleBiomes();
    }

    @Override
    public @NotNull Holder<Biome> getNoiseBiome(int x, int y, int z, Climate.@NotNull Sampler sampler) {
        return biomeConfig.value().getNoiseBiome(x, y, z);
    }

    public Holder<Biome> getMainBiome(int x, int z) {
        return biomeConfig.value().getBiomeColumnKey(x, z);
    }
}
