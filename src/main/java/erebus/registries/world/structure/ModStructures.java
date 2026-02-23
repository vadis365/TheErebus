package erebus.registries.world.structure;

import erebus.Erebus;
import erebus.world.feature.structure.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class ModStructures {
    public static final ResourceKey<Structure> ANTLION_DUNGEON = createKey("antlion_dungeon");
    public static final ResourceKey<Structure> ANTLION_LAIR = createKey("antlion_lair");
    public static final ResourceKey<Structure> DRAGONFLY_DUNGEON = createKey("dragonfly_dungeon");
    public static final ResourceKey<Structure> DUNG_PILE = createKey("dung_pile");
    public static final ResourceKey<Structure> LOCUST_SHRINE = createKey("locust_shrine");
    public static final ResourceKey<Structure> SPIDER_DUNGEON = createKey("spider_dungeon");
    public static final ResourceKey<Structure> SWAMP_HUT = createKey("swamp_hut");
    public static final ResourceKey<Structure> WASP_DUNGEON = createKey("wasp_dungeon");
    public static final ResourceKey<Structure> TARANTULA_DUNGEON = createKey("tarantula_dungeon");

    public static void registerStructures(BootstrapContext<Structure> context) {
        context.register(ANTLION_DUNGEON, AntlionDungeon.buildConfig(context));
        context.register(ANTLION_LAIR, AntlionLair.buildConfig(context));
        context.register(DRAGONFLY_DUNGEON, DragonflyDungeon.buildConfig(context));
        context.register(DUNG_PILE, DungPile.buildConfig(context));
        context.register(LOCUST_SHRINE, LocustShrine.buildConfig(context));
        context.register(SPIDER_DUNGEON, SpiderDungeon.buildConfig(context));
        context.register(SWAMP_HUT, SwampHut.buildConfig(context));
        context.register(WASP_DUNGEON, WaspDungeon.buildConfig(context));
        context.register(TARANTULA_DUNGEON, TarantulaDungeon.buildConfig(context));
    }

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, Erebus.prefix(name));
    }
}
