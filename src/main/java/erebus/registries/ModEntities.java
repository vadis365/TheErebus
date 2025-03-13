package erebus.registries;

import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Erebus.MODID);

    /*public static final Supplier<EntityType<GrassHopperEntity>> GRASSHOPPER = ENTITY_TYPES.register(
            "grasshopper",
            () -> EntityType.Builder.of(
                            GrassHopperEntity::new,
                            MobCategory.CREATURE)
                    .sized(1.3F, 0.75F)
                    .build("grasshopper"));*/

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
