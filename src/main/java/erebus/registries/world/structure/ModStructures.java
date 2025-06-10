package erebus.registries.world.structure;

import erebus.Erebus;
import erebus.world.feature.structure.LocustShrine;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ModStructures {
    public static final ResourceKey<Structure> ANTLION_DUNGEON = createKey("antlion_dungeon");
    public static final ResourceKey<Structure> LOCUST_SHRINE = createKey("locust_shrine");

    public static void registerStructures(BootstrapContext<Structure> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(LOCUST_SHRINE, LocustShrine.buildConfig(context));

        //.register(ANTLION_DUNGEON, new AntlionDungeon(biomes));
    }

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, Erebus.prefix(name));
    }
}
