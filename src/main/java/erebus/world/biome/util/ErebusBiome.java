package erebus.world.biome.util;

import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ErebusBiome {

    protected int COLOR;
    protected int FOG_COLOR;

    public ErebusBiome(int color, int fogColor) {
        COLOR = color;
        FOG_COLOR = fogColor;
    }

    public Biome getBiome(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        return null;
    }

    public ResourceKey<Biome> getResourceKey() {
        return null;
    }
}
