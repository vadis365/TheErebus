package erebus.registries.world.structure;

import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModStructureTypes {

    public static DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, Erebus.MODID);

    //public static final DeferredHolder<StructureType<?>, StructureType<ErebusStructure>> EREBUS_STRUCTURE = STRUCTURE_TYPES.register("erebus", () -> explicitStructureTypeTyping(ErebusStructure.CODEC));

    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(MapCodec<T> codec) {
        return () -> codec;
    }
}
