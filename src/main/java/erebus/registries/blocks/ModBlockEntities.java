package erebus.registries.blocks;

import erebus.Erebus;
import erebus.block.entity.*;
import erebus.registries.blocks.providers.AmberBlocks;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Erebus.MODID);

	public static final Supplier<BlockEntityType<ExperienceAltarBlockEntity>> ALTAR_EXPERIENCE;
	public static final Supplier<BlockEntityType<HealingAltarBlockEntity>> ALTAR_HEALING;
	public static final Supplier<BlockEntityType<LightningAltarBlockEntity>> ALTAR_LIGHTNING;
	public static final Supplier<BlockEntityType<RepairAltarBlockEntity>> ALTAR_REPAIR;
	public static final Supplier<BlockEntityType<BambooBridgeBlockEntity>> BAMBOO_BRIDGE;
	public static final Supplier<BlockEntityType<BambooCrateBlockEntity>> BAMBOO_CRATE;
	public static final Supplier<BlockEntityType<BambooExtenderBlockEntity>> BAMBOO_EXTENDER;
	public static final Supplier<BlockEntityType<BambooPipeBlockEntity>> BAMBOO_PIPE;
	public static final Supplier<BlockEntityType<BambooPipeExtractBlockEntity>> BAMBOO_PIPE_EXTRACT;
	public static final Supplier<BlockEntityType<BlenderBlockEntity>> BLENDER;
	public static final Supplier<BlockEntityType<BlockOfBonesBlockEntity>> BLOCK_OF_BONES;
	public static final Supplier<BlockEntityType<FluidJarBlockEntity>> FLUID_JAR;
	public static final Supplier<BlockEntityType<GlowingJarBlockEntity>> GLOWING_JAR;
	public static final Supplier<BlockEntityType<GaeanKeystoneBlockEntity>> GAEAN_KEYSTONE;
	public static final Supplier<BlockEntityType<HoneyCombBlockEntity>> HONEY_COMB;
	public static final Supplier<BlockEntityType<LiquifierBlockEntity>> LIQUIFIER;
	public static final Supplier<BlockEntityType<OfferingAltarBlockEntity>> OFFERING_ALTAR;
	public static final Supplier<BlockEntityType<PreservedBlockEntity>> PRESERVED_BLOCK;
	public static final Supplier<BlockEntityType<UmberFurnaceBlockEntity>> UMBERFURNACE;
	public static final Supplier<BlockEntityType<ComposterBlockEntity>> COMPOSTER;
	public static final Supplier<BlockEntityType<SiloTankBlockEntity>> SILO_TANK;

	static {
		ALTAR_EXPERIENCE = register("altar_experience", ExperienceAltarBlockEntity::new, OtherBlocks.ALTAR_EXPERIENCE);
		ALTAR_HEALING = register("altar_healing", HealingAltarBlockEntity::new, OtherBlocks.ALTAR_HEALING);
		ALTAR_LIGHTNING = register("altar_lightning", LightningAltarBlockEntity::new, OtherBlocks.ALTAR_LIGHTNING);
		ALTAR_REPAIR = register("altar_repair", RepairAltarBlockEntity::new, OtherBlocks.ALTAR_REPAIR);
		BAMBOO_BRIDGE = register("bamboo_bridge", BambooBridgeBlockEntity::new, OtherBlocks.BAMBOO_BRIDGE);
		BAMBOO_CRATE = register("bamboo_crate", BambooCrateBlockEntity::new, OtherBlocks.BAMBOO_CRATE);
		BAMBOO_EXTENDER = register("bamboo_extender", BambooExtenderBlockEntity::new, OtherBlocks.BAMBOO_EXTENDER);
		BAMBOO_PIPE = register("bamboo_pipe", BambooPipeBlockEntity::new, OtherBlocks.BAMBOO_PIPE);
		BAMBOO_PIPE_EXTRACT = register("bamboo_pipe_extract", BambooPipeExtractBlockEntity::new, OtherBlocks.BAMBOO_PIPE_EXTRACT);
		BLENDER = register("blender", BlenderBlockEntity::new, OtherBlocks.BLENDER);
		BLOCK_OF_BONES = register("block_of_bones", BlockOfBonesBlockEntity::new, OtherBlocks.BLOCK_OF_BONES);
		FLUID_JAR = register("fluid_jar", FluidJarBlockEntity::new, AmberBlocks.FLUID_JAR);
		GLOWING_JAR = register("glowing_jar", GlowingJarBlockEntity::new, AmberBlocks.GLOWING_JAR);
		GAEAN_KEYSTONE = register("gaean_keystone", GaeanKeystoneBlockEntity::new, OtherBlocks.GAEAN_KEYSTONE);
		HONEY_COMB = register("honey_comb", HoneyCombBlockEntity::new, OtherBlocks.HONEY_COMB);
		LIQUIFIER = register("liquifier", LiquifierBlockEntity::new, OtherBlocks.LIQUIFIER);
		OFFERING_ALTAR = register("altar_offering", OfferingAltarBlockEntity::new, OtherBlocks.OFFERING_ALTAR);
		PRESERVED_BLOCK = register("preserved_block", PreservedBlockEntity::new, AmberBlocks.PRESERVED_AMBER, AmberBlocks.PRESERVED_AMBER_GLASS);
		UMBERFURNACE = register("umberfurnace", UmberFurnaceBlockEntity::new, OtherBlocks.UMBER_FURNACE);
		COMPOSTER = register("composter", ComposterBlockEntity::new, OtherBlocks.COMPOSTER);
		SILO_TANK = register("silo_tank", SiloTankBlockEntity::new, OtherBlocks.SILO_TANK);
	}

	/**
	 * Generic method to register a block entity type with one or more blocks
	 * @param name The registry name for the block entity type
	 * @param factory The block entity factory (constructor reference)
	 * @param blocks One or more block suppliers
	 * @return A supplier of the registered BlockEntityType
	 */
	@SafeVarargs
    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String name, BlockEntityType.BlockEntitySupplier<T> factory, Supplier<? extends Block>... blocks) {
		return BLOCK_ENTITIES.register(name, () -> {
			Block[] blockArray = new Block[blocks.length];
			for (int i = 0; i < blocks.length; i++) {
				blockArray[i] = blocks[i].get();
			}

			return BlockEntityType.Builder.of(factory, blockArray).build(null);
		});
	}
}
