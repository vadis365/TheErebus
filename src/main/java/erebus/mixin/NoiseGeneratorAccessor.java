package erebus.mixin;

import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

@Mixin(NoiseBasedChunkGenerator.class)
public interface NoiseGeneratorAccessor {
    @Accessor("globalFluidPicker")
    Supplier<Aquifer.FluidPicker> erebus$fluidPicker();
}
