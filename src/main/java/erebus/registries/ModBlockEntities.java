package erebus.registries;

import erebus.Erebus;
import erebus.block.entity.*;
import erebus.registries.blocks.providers.AmberBlocks;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Erebus.MODID);

    public static final Supplier<BlockEntityType<UmberFurnaceBlockEntity>> UMBERFURNACE = BLOCK_ENTITIES.register(
            "umberfurnace",
            () -> BlockEntityType.Builder.of(
                    UmberFurnaceBlockEntity::new, OtherBlocks.UMBER_FURNACE.get()
            ).build(null)
    );

    public static final Supplier<BlockEntityType<GaeanKeystoneBlockEntity>> GAEAN_KEYSTONE = BLOCK_ENTITIES.register(
            "gaean_keystone",
            () -> BlockEntityType.Builder.of(
                    GaeanKeystoneBlockEntity::new, OtherBlocks.GAEAN_KEYSTONE.get()
            ).build(null)
    );
    
    public static final Supplier<BlockEntityType<OfferingAltarBlockEntity>> OFFERING_ALTAR = BLOCK_ENTITIES.register(
            "altar_offering",
            () -> BlockEntityType.Builder.of(
                    OfferingAltarBlockEntity::new, OtherBlocks.OFFERING_ALTAR.get()
            ).build(null)
    );

    public static final Supplier<BlockEntityType<BlockOfBonesBlockEntity>> BLOCK_OF_BONES = BLOCK_ENTITIES.register(
            "block_of_bones",
            () -> BlockEntityType.Builder.of(
                    BlockOfBonesBlockEntity::new, OtherBlocks.BLOCK_OF_BONES.get()
            ).build(null)
    );
    
    public static final Supplier<BlockEntityType<HealingAltarBlockEntity>> ALTAR_HEALING = BLOCK_ENTITIES.register(
            "altar_healing",
            () -> BlockEntityType.Builder.of(
                    HealingAltarBlockEntity::new, OtherBlocks.ALTAR_HEALING.get()
            ).build(null)
    );

	    public static final Supplier<BlockEntityType<LightningAltarBlockEntity>> ALTAR_LIGHTNING = BLOCK_ENTITIES.register(
            "altar_lightning",
            () -> BlockEntityType.Builder.of(
                    LightningAltarBlockEntity::new, OtherBlocks.ALTAR_LIGHTNING.get()
            ).build(null)
    );

	    public static final Supplier<BlockEntityType<RepairAltarBlockEntity>> ALTAR_REPAIR = BLOCK_ENTITIES.register(
	            "altar_repair",
	            () -> BlockEntityType.Builder.of(
                        RepairAltarBlockEntity::new, OtherBlocks.ALTAR_REPAIR.get()
	            ).build(null)
	    );

	    public static final Supplier<BlockEntityType<ExperienceAltarBlockEntity>> ALTAR_EXPERIENCE = BLOCK_ENTITIES.register(
	            "altar_experience",
	            () -> BlockEntityType.Builder.of(
                        ExperienceAltarBlockEntity::new, OtherBlocks.ALTAR_EXPERIENCE.get()
	            ).build(null)
	    );

    public static final Supplier<BlockEntityType<BlenderBlockEntity>> BLENDER = BLOCK_ENTITIES.register(
            "blender",
            () -> BlockEntityType.Builder.of(BlenderBlockEntity::new, OtherBlocks.BLENDER.get())
                    .build(null)
    );
    
    public static final Supplier<BlockEntityType<FluidJarBlockEntity>> FLUID_JAR = BLOCK_ENTITIES.register(
            "fluid_jar",
            () -> BlockEntityType.Builder.of(FluidJarBlockEntity::new, AmberBlocks.FLUID_JAR.get())
                    .build(null)
    );
}
