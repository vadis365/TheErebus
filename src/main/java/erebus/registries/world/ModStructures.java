package erebus.registries.world;

import erebus.Erebus;
import erebus.world.structure.SkyStructure;
import erebus.world.structure.WaterStructure;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModStructures {

    public static final DeferredRegister<StructureType<?>> STRUCTURES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Erebus.MODID);

    public static final DeferredHolder<StructureType<?>, StructureType<WaterStructure>> WATER_STRUCTURES;
    public static final DeferredHolder<StructureType<?>, StructureType<SkyStructure>> SKY_STRUCTURES;

    static {
        WATER_STRUCTURES = STRUCTURES.register("water_structures", () -> () -> WaterStructure.CODEC);
        SKY_STRUCTURES = STRUCTURES.register("sky_structures", () -> () -> SkyStructure.CODEC);
    }
}
