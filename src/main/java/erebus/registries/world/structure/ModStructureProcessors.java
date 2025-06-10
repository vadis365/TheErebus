package erebus.registries.world.structure;

import erebus.Erebus;
import erebus.world.feature.structure.processors.CobbleProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModStructureProcessors {

    public static final DeferredRegister<StructureProcessorType<?>> STRUCTURE_PROCESSORS = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, Erebus.MODID);

    public static final DeferredHolder<StructureProcessorType<?>, StructureProcessorType<CobbleProcessor>> COBBLE = STRUCTURE_PROCESSORS.register("cobble", () -> () -> CobbleProcessor.CODEC);
}
