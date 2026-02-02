package erebus.registries.blocks;

import erebus.Erebus;
import erebus.block.entity.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;


public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Erebus.MODID);
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ExperienceAltarBlockEntity>> ALTAR_EXPERIENCE = BLOCK_ENTITIES.register("altar_experience", () -> new BlockEntityType<>(ExperienceAltarBlockEntity::new, Set.of(ModBlocks.ALTAR_EXPERIENCE.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HealingAltarBlockEntity>> ALTAR_HEALING = BLOCK_ENTITIES.register("altar_healing", () -> new BlockEntityType<>(HealingAltarBlockEntity::new, Set.of(ModBlocks.ALTAR_HEALING.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LightningAltarBlockEntity>> ALTAR_LIGHTNING = BLOCK_ENTITIES.register("altar_lightning", () -> new BlockEntityType<>(LightningAltarBlockEntity::new, Set.of(ModBlocks.ALTAR_LIGHTNING.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RepairAltarBlockEntity>> ALTAR_REPAIR = BLOCK_ENTITIES.register("altar_repair", () -> new BlockEntityType<>(RepairAltarBlockEntity::new, Set.of(ModBlocks.ALTAR_REPAIR.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BambooBridgeBlockEntity>> BAMBOO_BRIDGE = BLOCK_ENTITIES.register("bamboo_bridge", () -> new BlockEntityType<>(BambooBridgeBlockEntity::new, Set.of(ModBlocks.BAMBOO_BRIDGE.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BambooCrateBlockEntity>> BAMBOO_CRATE = BLOCK_ENTITIES.register("bamboo_crate", () -> new BlockEntityType<>(BambooCrateBlockEntity::new, Set.of(ModBlocks.BAMBOO_CRATE.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BambooExtenderBlockEntity>> BAMBOO_EXTENDER = BLOCK_ENTITIES.register("bamboo_extender", () -> new BlockEntityType<>(BambooExtenderBlockEntity::new, Set.of(ModBlocks.BAMBOO_EXTENDER.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BambooPipeBlockEntity>> BAMBOO_PIPE = BLOCK_ENTITIES.register("bamboo_pipe", () -> new BlockEntityType<>(BambooPipeBlockEntity::new, Set.of(ModBlocks.BAMBOO_PIPE.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BambooPipeExtractBlockEntity>> BAMBOO_PIPE_EXTRACT = BLOCK_ENTITIES.register("bamboo_pipe_extract", () -> new BlockEntityType<>(BambooPipeExtractBlockEntity::new, Set.of(ModBlocks.BAMBOO_PIPE_EXTRACT.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BlenderBlockEntity>> BLENDER = BLOCK_ENTITIES.register("blender", () -> new BlockEntityType<>(BlenderBlockEntity::new, Set.of(ModBlocks.BLENDER.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BlockOfBonesBlockEntity>> BLOCK_OF_BONES = BLOCK_ENTITIES.register("block_of_bones", () -> new BlockEntityType<>(BlockOfBonesBlockEntity::new, Set.of(ModBlocks.BLOCK_OF_BONES.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<FluidJarBlockEntity>> FLUID_JAR = BLOCK_ENTITIES.register("fluid_jar", () -> new BlockEntityType<>(FluidJarBlockEntity::new, Set.of(ModBlocks.FLUID_JAR.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GlowingJarBlockEntity>> GLOWING_JAR = BLOCK_ENTITIES.register("glowing_jar", () -> new BlockEntityType<>(GlowingJarBlockEntity::new, Set.of(ModBlocks.GLOWING_JAR.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<GaeanKeystoneBlockEntity>> GAEAN_KEYSTONE = BLOCK_ENTITIES.register("gaean_keystone", () -> new BlockEntityType<>(GaeanKeystoneBlockEntity::new, Set.of(ModBlocks.GAEAN_KEYSTONE.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<HoneyCombBlockEntity>> HONEY_COMB = BLOCK_ENTITIES.register("honey_comb", () -> new BlockEntityType<>(HoneyCombBlockEntity::new, Set.of(ModBlocks.HONEY_COMB.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LiquifierBlockEntity>> LIQUIFIER = BLOCK_ENTITIES.register("liquifier", () -> new BlockEntityType<>(LiquifierBlockEntity::new, Set.of(ModBlocks.LIQUIFIER.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<OfferingAltarBlockEntity>> OFFERING_ALTAR = BLOCK_ENTITIES.register("altar_offering", () -> new BlockEntityType<>(OfferingAltarBlockEntity::new, Set.of(ModBlocks.OFFERING_ALTAR.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PreservedBlockEntity>> PRESERVED_BLOCK = BLOCK_ENTITIES.register("preserved_block", () -> new BlockEntityType<>(PreservedBlockEntity::new, Set.of(ModBlocks.PRESERVED_AMBER.get(), ModBlocks.PRESERVED_AMBER_GLASS.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<UmberFurnaceBlockEntity>> UMBERFURNACE = BLOCK_ENTITIES.register("umberfurnace", () -> new BlockEntityType<>(UmberFurnaceBlockEntity::new, Set.of(ModBlocks.UMBER_FURNACE.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ComposterBlockEntity>> COMPOSTER = BLOCK_ENTITIES.register("composter", () -> new BlockEntityType<>(ComposterBlockEntity::new, Set.of(ModBlocks.COMPOSTER.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<SiloTankBlockEntity>> SILO_TANK = BLOCK_ENTITIES.register("silo_tank", () -> new BlockEntityType<>(SiloTankBlockEntity::new, Set.of(ModBlocks.SILO_TANK.get())));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ErebusChestBlockEntity>> EREBUS_CHEST = BLOCK_ENTITIES.register("erebus_chest", () -> new BlockEntityType<>(ErebusChestBlockEntity::new, Set.of(
            ModBlocks.CHEST_ASPER.get(),
            ModBlocks.CHEST_BAOBAB.get(),
            ModBlocks.CHEST_BAMBOO.get(),
            ModBlocks.CHEST_BALSAM.get(),
            ModBlocks.CHEST_CYPRESS.get(),
            ModBlocks.CHEST_EUCALYPTUS.get(),
            ModBlocks.CHEST_MAHOGANY.get(),
            ModBlocks.CHEST_MARSHWOOD.get(),
            ModBlocks.CHEST_MOSSBARK.get(),
            ModBlocks.CHEST_PETRIFIED.get(),
            ModBlocks.CHEST_ROTTEN.get(),
            ModBlocks.CHEST_SCORCHED.get(),
            ModBlocks.CHEST_VARNISHED.get(),
            ModBlocks.CHEST_WHITE.get()
    )));
}
