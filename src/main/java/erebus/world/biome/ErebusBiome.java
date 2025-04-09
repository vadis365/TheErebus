package erebus.world.biome;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

public class ErebusBiome {

    protected int COLOR;
    protected int FOG_COLOR;

    public ErebusBiome(int color, int fogColor) {
        COLOR = color;
        FOG_COLOR = fogColor;
    }

    protected static ResourceKey<Biome> makeKey(String name) {
        return ResourceKey.create(Registries.BIOME, Erebus.prefix(name));
    }

    public Biome getBiome() {
        return null;
    }

    public ResourceKey<Biome> getResourceKey() {
        return null;
    }
}
