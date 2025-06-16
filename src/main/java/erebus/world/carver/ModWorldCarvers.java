package erebus.world.carver;

import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModWorldCarvers {
    public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(BuiltInRegistries.CARVER, Erebus.MODID);
    public static final DeferredHolder<WorldCarver<?>, WorldCarver<ErebusCaveCarverConfiguration>> CAVE = CARVERS.register("cave", () -> new ErebusCaveCarver(ErebusCaveCarverConfiguration.CODEC));
    public static final DeferredHolder<WorldCarver<?>, WorldCarver<ErebusCanyonCarverConfiguration>> CANYON = CARVERS.register("canyon", () -> new ErebusCanyonCarver(ErebusCanyonCarverConfiguration.CODEC));
}
