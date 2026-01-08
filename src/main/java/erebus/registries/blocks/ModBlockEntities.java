package erebus.registries.blocks;

import erebus.Erebus;
import erebus.block.entity.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;
import java.util.function.Supplier;


public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Erebus.MODID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ExperienceAltarBlockEntity>> ALTAR_EXPERIENCE;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HealingAltarBlockEntity>> ALTAR_HEALING;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LightningAltarBlockEntity>> ALTAR_LIGHTNING;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RepairAltarBlockEntity>> ALTAR_REPAIR;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BambooBridgeBlockEntity>> BAMBOO_BRIDGE;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BambooCrateBlockEntity>> BAMBOO_CRATE;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BambooExtenderBlockEntity>> BAMBOO_EXTENDER;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BambooPipeBlockEntity>> BAMBOO_PIPE;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BambooPipeExtractBlockEntity>> BAMBOO_PIPE_EXTRACT;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BlenderBlockEntity>> BLENDER;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BlockOfBonesBlockEntity>> BLOCK_OF_BONES;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FluidJarBlockEntity>> FLUID_JAR;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GlowingJarBlockEntity>> GLOWING_JAR;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GaeanKeystoneBlockEntity>> GAEAN_KEYSTONE;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HoneyCombBlockEntity>> HONEY_COMB;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LiquifierBlockEntity>> LIQUIFIER;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<OfferingAltarBlockEntity>> OFFERING_ALTAR;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PreservedBlockEntity>> PRESERVED_BLOCK;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UmberFurnaceBlockEntity>> UMBERFURNACE;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ComposterBlockEntity>> COMPOSTER;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SiloTankBlockEntity>> SILO_TANK;
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ErebusChestBlockEntity>> EREBUS_CHEST;

    static {
        ALTAR_EXPERIENCE = register("altar_experience", ExperienceAltarBlockEntity::new, ModBlocks.ALTAR_EXPERIENCE);
        ALTAR_HEALING = register("altar_healing", HealingAltarBlockEntity::new, ModBlocks.ALTAR_HEALING);
        ALTAR_LIGHTNING = register("altar_lightning", LightningAltarBlockEntity::new, ModBlocks.ALTAR_LIGHTNING);
        ALTAR_REPAIR = register("altar_repair", RepairAltarBlockEntity::new, ModBlocks.ALTAR_REPAIR);
        BAMBOO_BRIDGE = register("bamboo_bridge", BambooBridgeBlockEntity::new, ModBlocks.BAMBOO_BRIDGE);
        BAMBOO_CRATE = register("bamboo_crate", BambooCrateBlockEntity::new, ModBlocks.BAMBOO_CRATE);
        BAMBOO_EXTENDER = register("bamboo_extender", BambooExtenderBlockEntity::new, ModBlocks.BAMBOO_EXTENDER);
        BAMBOO_PIPE = register("bamboo_pipe", BambooPipeBlockEntity::new, ModBlocks.BAMBOO_PIPE);
        BAMBOO_PIPE_EXTRACT = register("bamboo_pipe_extract", BambooPipeExtractBlockEntity::new, ModBlocks.BAMBOO_PIPE_EXTRACT);
        BLENDER = register("blender", BlenderBlockEntity::new, ModBlocks.BLENDER);
        BLOCK_OF_BONES = register("block_of_bones", BlockOfBonesBlockEntity::new, ModBlocks.BLOCK_OF_BONES);
        FLUID_JAR = register("fluid_jar", FluidJarBlockEntity::new, ModBlocks.FLUID_JAR);
        GLOWING_JAR = register("glowing_jar", GlowingJarBlockEntity::new, ModBlocks.GLOWING_JAR);
        GAEAN_KEYSTONE = register("gaean_keystone", GaeanKeystoneBlockEntity::new, ModBlocks.GAEAN_KEYSTONE);
        HONEY_COMB = register("honey_comb", HoneyCombBlockEntity::new, ModBlocks.HONEY_COMB);
        LIQUIFIER = register("liquifier", LiquifierBlockEntity::new, ModBlocks.LIQUIFIER);
        OFFERING_ALTAR = register("altar_offering", OfferingAltarBlockEntity::new, ModBlocks.OFFERING_ALTAR);
        PRESERVED_BLOCK = register("preserved_block", PreservedBlockEntity::new, ModBlocks.PRESERVED_AMBER, ModBlocks.PRESERVED_AMBER_GLASS);
        UMBERFURNACE = register("umberfurnace", UmberFurnaceBlockEntity::new, ModBlocks.UMBER_FURNACE);
        COMPOSTER = register("composter", ComposterBlockEntity::new, ModBlocks.COMPOSTER);
        SILO_TANK = register("silo_tank", SiloTankBlockEntity::new, ModBlocks.SILO_TANK);
        EREBUS_CHEST = register("erebus_chest", ErebusChestBlockEntity::new,
                ModBlocks.CHEST_ASPER,
                ModBlocks.CHEST_BAOBAB,
                ModBlocks.CHEST_BAMBOO,
                ModBlocks.CHEST_BALSAM,
                ModBlocks.CHEST_CYPRESS,
                ModBlocks.CHEST_EUCALYPTUS,
                ModBlocks.CHEST_MAHOGANY,
                ModBlocks.CHEST_MARSHWOOD,
                ModBlocks.CHEST_MOSSBARK,
                ModBlocks.CHEST_PETRIFIED,
                ModBlocks.CHEST_ROTTEN,
                ModBlocks.CHEST_SCORCHED,
                ModBlocks.CHEST_VARNISHED,
                ModBlocks.CHEST_WHITE
        );
    }

    /**
     * Generic method to register a block entity type with one or more blocks
     *
     * @param name    The registry name for the block entity type
     * @param factory The block entity factory (constructor reference)
     * @param blocks  One or more block suppliers
     * @return A DeferredHolder of the registered BlockEntityType
     */
    @SafeVarargs
    private static <T extends BlockEntity> DeferredHolder<BlockEntityType<?>, BlockEntityType<T>> register(
            String name,
            BlockEntityType.BlockEntitySupplier<T> factory,
            Supplier<? extends Block>... blocks) {
        return BLOCK_ENTITIES.register(name, () -> {
            Block[] blockArray = new Block[blocks.length];
            for (int i = 0; i < blocks.length; i++) {
                blockArray[i] = blocks[i].get();
            }
            return new BlockEntityType<>(factory, Set.of(blockArray));
        });
    }
}
