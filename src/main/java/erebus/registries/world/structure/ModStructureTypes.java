package erebus.registries.world.structure;

import erebus.Erebus;
import erebus.world.feature.structure.LocustShrine;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModStructureTypes {

    public static DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Erebus.MODID);

    public static final DeferredHolder<StructureType<?>, StructureType<LocustShrine>> LOCUST_SHRINE = STRUCTURE_TYPES.register("locust_shrine", () -> () -> LocustShrine.CODEC);
}
