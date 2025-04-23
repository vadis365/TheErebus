package erebus.registries.world;

import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModStructures {

    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Erebus.MODID);

    public static void bootstrapStructures(BootstrapContext<Structure> context) {

    }

    public static void bootstrapSets(BootstrapContext<StructureSet> context) {

    }

    public static void bootstrapPools(BootstrapContext<StructureTemplatePool> context) {

    }

    public static void bootstrapProcessors(BootstrapContext<StructureProcessorList> context) {

    }
}
