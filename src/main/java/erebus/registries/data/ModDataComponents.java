package erebus.registries.data;

import erebus.Erebus;
import erebus.network.data.DeathCompassData;
import erebus.network.data.DeathCompassDataHolder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {

    public static final DeferredRegister.DataComponents REGISTRY = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Erebus.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<DeathCompassData>> DEATH_COMPASS;

    static {
        DEATH_COMPASS = REGISTRY.registerComponentType(
                "death_compass",
                builder -> builder
                        .persistent(DeathCompassDataHolder.CODEC)
                        .networkSynchronized(DeathCompassDataHolder.STREAM_CODEC)
        );
    }
}
