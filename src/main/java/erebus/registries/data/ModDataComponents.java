package erebus.registries.data;

import java.util.function.Supplier;

import erebus.Erebus;
import erebus.network.data.DeathCompassData;
import erebus.network.data.DeathCompassDataHolder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {

	public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_REGISTRY = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, Erebus.MODID);

	public static final Supplier<DataComponentType<DeathCompassData>> DEATH_COMPASS = DATA_COMPONENT_REGISTRY.register(
			"death_compass",
			() -> DataComponentType.<DeathCompassData>builder().persistent(DeathCompassDataHolder.CODEC)
					.networkSynchronized(DeathCompassDataHolder.STREAM_CODEC).build());
    
    
	public static final Supplier<DataComponentType<FluidContents>> FLUID = DATA_COMPONENT_REGISTRY.register("fluid",
			() -> DataComponentType.<FluidContents>builder().persistent(FluidContents.CODEC)
					.networkSynchronized(FluidContents.STREAM_CODEC).build());
}
