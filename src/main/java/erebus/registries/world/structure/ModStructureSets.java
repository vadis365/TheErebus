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

    public static final ResourceKey<StructureSet> LOCUST_SHRINE = createKey("locust_shrine");

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

        register(LOCUST_SHRINE, ModStructures.LOCUST_SHRINE, new RandomSpreadStructurePlacement(2, 1, RandomSpreadType.LINEAR, 1435132));
    }

    private static void register(ResourceKey<StructureSet> key, ResourceKey<Structure> structure, StructurePlacement placement) {
        context.register(key, new StructureSet(structures.getOrThrow(structure), placement));
    }
}
