package erebus.registries.entity;

import java.util.function.Supplier;

import erebus.Erebus;
import erebus.entity.AnimatedBlock;
import erebus.entity.BlackWidow;
import erebus.entity.LavaWebSpider;
import erebus.entity.MoneySpider;
import erebus.entity.Scytodes;
import erebus.entity.Wasp;
import erebus.entity.projectile.ThrownBlockAsItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
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

	public static final Supplier<EntityType<Wasp>> WASP = registerWithEgg("wasp", EntityType.Builder.of(Wasp::new, MobCategory.MONSTER).sized(0.5F, 0.4F), 0xFECD09, 0x141414);
	public static final Supplier<EntityType<AnimatedBlock>> ANIMATED_BLOCK = registerNoEgg("animated_block", EntityType.Builder.of(AnimatedBlock::new, MobCategory.MISC).fireImmune().sized(1F, 1.25F).clientTrackingRange(4).updateInterval(10));
	public static final Supplier<EntityType<Scytodes>> SCYTODES = registerWithEgg("scytodes", EntityType.Builder.of(Scytodes::new, MobCategory.MONSTER).sized(2F, 1F).fireImmune(), 0xC2833C, 0x520D06);
	public static final Supplier<EntityType<MoneySpider>> MONEY_SPIDER = registerWithEgg("money_spider", EntityType.Builder.of(MoneySpider::new, MobCategory.MONSTER).sized(0.6F, 0.4F), 0xC2872F, 0xF9FF00);
	public static final Supplier<EntityType<BlackWidow>> BLACK_WIDOW = registerWithEgg("black_widow", EntityType.Builder.of(BlackWidow::new, MobCategory.MONSTER).sized(0.7F, 0.325F).fireImmune(), 0x101010, 0xFF0000);
	public static final Supplier<EntityType<LavaWebSpider>> LAVA_WEB_SPIDER = registerWithEgg("lava_web_spider", EntityType.Builder.of(LavaWebSpider::new, MobCategory.MONSTER).sized(3F, 1.5F).fireImmune(), 0xD36617, 0x342522);
	
	public static final Supplier<EntityType<ThrownBlockAsItem>> WEB_SLING = registerNonMobEntity("web_sling", EntityType.Builder.<ThrownBlockAsItem>of(ThrownBlockAsItem::new, MobCategory.MISC).fireImmune().sized(0.5F, 0.5F));
	// just calls a helper in the main mod because it'll be used all over probably
	private static String prefix(String name) {
		return Erebus.prefix(name).toString();
	}

	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(WASP.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Wasp::canSpawnHere, null);
		event.register(SCYTODES.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Scytodes::canSpawnHere, null);
		event.register(BLACK_WIDOW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BlackWidow::canSpawnHere, null);
		event.register(LAVA_WEB_SPIDER.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LavaWebSpider::canSpawnHere, null);
	}
	
	public static void initializeAttributes(EntityAttributeCreationEvent event) {
		event.put(WASP.get(), Wasp.createAttributes().build());
		event.put(ANIMATED_BLOCK.get(), AnimatedBlock.createAttributes().build());
		event.put(SCYTODES.get(), Scytodes.createAttributes().build());
		event.put(MONEY_SPIDER.get(), MoneySpider.createAttributes().build());
		event.put(BLACK_WIDOW.get(), BlackWidow.createAttributes().build());
		event.put(LAVA_WEB_SPIDER.get(), LavaWebSpider.createAttributes().build());
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

	private static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerNonMobEntity(String name, EntityType.Builder<E> builder) {
		DeferredHolder<EntityType<?>, EntityType<E>> ret = ENTITY_TYPES.register(name, () -> builder.build(prefix(name)));
		return ret;
	}

}
