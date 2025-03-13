package erebus.registries;

import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Erebus.MODID);

    /*public static final Supplier<EntityType<GrassHopperEntity>> GRASSHOPPER = ENTITY_TYPES.register(
            "grasshopper",
            () -> EntityType.Builder.of(
                            GrassHopperEntity::new,
                            MobCategory.CREATURE)
                    .sized(1.3F, 0.75F)
                    .build(prefix("grasshopper")));*/
    
    // just calls a helper in the main mod because it'll be used all over probably
	private static String prefix(String name) {
		return Erebus.prefix(name).toString();
	}
	
	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {

	}
	
	public static void initializeAttributes(EntityAttributeCreationEvent event) {
	}

	public static DeferredRegister<EntityType<?>> getEntityTypes() {
		return ENTITY_TYPES;
	}
}
