package erebus.registries.data;

import com.mojang.serialization.Codec;
import erebus.Erebus;
import erebus.network.data.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.transfer.fluid.FluidResource;

import java.util.function.Supplier;

public class ModDataComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_REGISTRY = DeferredRegister.create(BuiltInRegistries.DATA_COMPONENT_TYPE, Erebus.MODID);

    public static final Supplier<DataComponentType<DeathCompassData>> DEATH_COMPASS = DATA_COMPONENT_REGISTRY.register(
            "death_compass",
            () -> DataComponentType.<DeathCompassData>builder().persistent(DeathCompassDataHolder.CODEC)
                    .networkSynchronized(DeathCompassDataHolder.STREAM_CODEC).build());


    public static final Supplier<DataComponentType<FluidResource>> FLUID = DATA_COMPONENT_REGISTRY.register("fluid",
            () -> DataComponentType.<FluidResource>builder().persistent(FluidResource.CODEC)
                    .networkSynchronized(FluidResource.STREAM_CODEC).build());

    public static final Supplier<DataComponentType<GliderData>> GLIDER = DATA_COMPONENT_REGISTRY.register("glider",
            () -> DataComponentType.<GliderData>builder().persistent(GliderData.CODEC)
                    .networkSynchronized(GliderData.STREAM_CODEC).build());

    public static final Supplier<DataComponentType<Integer>> WHETSTONE_LEVEL = DATA_COMPONENT_REGISTRY.register("whetstone_level",
            () -> DataComponentType.<Integer>builder().persistent(Codec.intRange(0, 5))
                    .networkSynchronized(ByteBufCodecs.VAR_INT).build());

    public static final Supplier<DataComponentType<Integer>> FLUID_AMOUNT = DATA_COMPONENT_REGISTRY.register("fluid_amount",
            () -> DataComponentType.<Integer>builder().persistent(Codec.intRange(0, Integer.MAX_VALUE))
                    .networkSynchronized(ByteBufCodecs.VAR_INT).build());

    public static final Supplier<DataComponentType<BlockPos>> BEE_TAMING_AMULET = DATA_COMPONENT_REGISTRY.register("bee_taming_amulet",
            () -> DataComponentType.<BlockPos>builder().persistent(BlockPos.CODEC)
                    .networkSynchronized(BlockPos.STREAM_CODEC).build());

    public static final Supplier<DataComponentType<BlockPos>> ANT_TAMING_AMULET = DATA_COMPONENT_REGISTRY.register("ant_taming_amulet",
            () -> DataComponentType.<BlockPos>builder().persistent(BlockPos.CODEC)
                    .networkSynchronized(BlockPos.STREAM_CODEC).build());

    public static final Supplier<DataComponentType<SprintLeggingsData>> SPRINT_LEGGINGS = DATA_COMPONENT_REGISTRY.register(
            "sprint_leggings",
            () -> DataComponentType.<SprintLeggingsData>builder().persistent(SprintLeggingsDataHolder.CODEC)
                    .networkSynchronized(SprintLeggingsDataHolder.STREAM_CODEC).build());

    public static final Supplier<DataComponentType<QuakeHammerData>> QUAKE_HAMMER = DATA_COMPONENT_REGISTRY.register(
            "quake_hammer",
            () -> DataComponentType.<QuakeHammerData>builder().persistent(QuakeHammerDataHolder.CODEC)
                    .networkSynchronized(QuakeHammerDataHolder.STREAM_CODEC).build());
}
