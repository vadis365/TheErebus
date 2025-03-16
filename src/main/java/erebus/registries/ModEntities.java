package erebus.registries;

import java.util.function.Supplier;

import erebus.Erebus;
import erebus.entity.AnimatedBlock;
import erebus.entity.WaspEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Erebus.MODID);
    public static final DeferredRegister.Items SPAWN_EGGS = DeferredRegister.createItems(Erebus.MODID);

    /*public static final Supplier<EntityType<GrassHopperEntity>> GRASSHOPPER = ENTITY_TYPES.register(
            "grasshopper",
            () -> EntityType.Builder.of(
                            GrassHopperEntity::new,
                            MobCategory.CREATURE)
                    .sized(1.3F, 0.75F)
                    .build(prefix("grasshopper")));*/

	public static final Supplier<EntityType<WaspEntity>> WASP = registerWithEgg("wasp", EntityType.Builder.of(WaspEntity::new, MobCategory.MONSTER).sized(0.5F, 0.4F), 0xFECD09, 0x141414);
	public static final Supplier<EntityType<AnimatedBlock>> ANIMATED_BLOCK = registerNoEgg("animated_block", EntityType.Builder.of(AnimatedBlock::new, MobCategory.MISC).fireImmune().sized(0.5F, 1.5F).clientTrackingRange(4).updateInterval(10));
    
	// just calls a helper in the main mod because it'll be used all over probably
	private static String prefix(String name) {
		return Erebus.prefix(name).toString();
	}
	
	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(WASP.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WaspEntity::canSpawnHere, null);
	}
	
	public static void initializeAttributes(EntityAttributeCreationEvent event) {
		event.put(WASP.get(), WaspEntity.createAttributes().build());
	}

	public static DeferredRegister<EntityType<?>> getEntityTypes() {
		return ENTITY_TYPES;
	}

	public static <E extends Mob> DeferredHolder<EntityType<?>, EntityType<E>> registerWithEgg(String name, EntityType.Builder<E> builder, int primaryColor, int secondaryColor) {
		DeferredHolder<EntityType<?>, EntityType<E>> ret = ENTITY_TYPES.register(name, () -> builder.build(prefix(name)));
		SPAWN_EGGS.register(name + "_spawn_egg", () -> new DeferredSpawnEggItem(ret, primaryColor, secondaryColor, new Item.Properties()));
		return ret;
	}
	
	public static <E extends Mob> DeferredHolder<EntityType<?>, EntityType<E>> registerNoEgg(String name, EntityType.Builder<E> builder) {
		DeferredHolder<EntityType<?>, EntityType<E>> ret = ENTITY_TYPES.register(name, () -> builder.build(prefix(name)));
		return ret;
	}
}
