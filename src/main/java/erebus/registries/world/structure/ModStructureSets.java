package erebus.registries.world.structure;

import erebus.Erebus;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

public class ModStructureSets {

    public static final ResourceKey<StructureSet> ANTLION_DUNGEON = createKey("antlion_dungeon");
    public static final ResourceKey<StructureSet> ANTLION_LAIR = createKey("antlion_lair");
    public static final ResourceKey<StructureSet> DRAGONFLY_DUNGEON = createKey("dragonfly_dungeon");
    public static final ResourceKey<StructureSet> DUNG_PILE = createKey("dung_pile");
    public static final ResourceKey<StructureSet> LOCUST_SHRINE = createKey("locust_shrine");
    public static final ResourceKey<StructureSet> SPIDER_DUNGEON = createKey("spider_dungeon");
    public static final ResourceKey<StructureSet> SWAMP_HUT = createKey("swamp_hut");
    public static final ResourceKey<StructureSet> WASP_DUNGEON = createKey("wasp_dungeon");

    private static HolderGetter<Structure> structures;
    private static HolderGetter<StructureSet> structureSets;
    private static BootstrapContext<StructureSet> context;

    private static ResourceKey<StructureSet> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE_SET, Erebus.prefix(name));
    }

    public static void bootstrap(BootstrapContext<StructureSet> ctx) {
        structures = ctx.lookup(Registries.STRUCTURE);
        structureSets = ctx.lookup(Registries.STRUCTURE_SET);
        context = ctx;

        register(ANTLION_DUNGEON, ModStructures.ANTLION_DUNGEON, new RandomSpreadStructurePlacement(2, 1, RandomSpreadType.LINEAR, 1435132));
        register(ANTLION_LAIR, ModStructures.ANTLION_LAIR, new RandomSpreadStructurePlacement(2, 1, RandomSpreadType.LINEAR, 1435132));
        register(DRAGONFLY_DUNGEON, ModStructures.DRAGONFLY_DUNGEON, new RandomSpreadStructurePlacement(2, 1, RandomSpreadType.LINEAR, 1435132));
        register(DUNG_PILE, ModStructures.DUNG_PILE, new RandomSpreadStructurePlacement(2, 1, RandomSpreadType.LINEAR, 1435132));
        register(LOCUST_SHRINE, ModStructures.LOCUST_SHRINE, new RandomSpreadStructurePlacement(2, 1, RandomSpreadType.LINEAR, 1435132));
        register(SPIDER_DUNGEON, ModStructures.SPIDER_DUNGEON, new RandomSpreadStructurePlacement(2, 1, RandomSpreadType.LINEAR, 1435132));
        register(SWAMP_HUT, ModStructures.SWAMP_HUT, new RandomSpreadStructurePlacement(2, 1, RandomSpreadType.LINEAR, 1435132));
        register(WASP_DUNGEON, ModStructures.WASP_DUNGEON, new RandomSpreadStructurePlacement(2, 1, RandomSpreadType.LINEAR, 1435132));
    }

    private static void register(ResourceKey<StructureSet> key, ResourceKey<Structure> structure, StructurePlacement placement) {
        context.register(key, new StructureSet(structures.getOrThrow(structure), placement));
    }
}
