package erebus.registries;

import erebus.Erebus;
import erebus.block.entity.GaeanKeystoneBlockEntity;
import erebus.block.entity.UmberFurnaceBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Erebus.MODID);

    public static final Supplier<BlockEntityType<UmberFurnaceBlockEntity>> UMBERFURNACE = BLOCK_ENTITIES.register(
            "umberfurnace",
            () -> BlockEntityType.Builder.of(
                    UmberFurnaceBlockEntity::new, ModBlocks.UMBER_FURNACE.get()
            ).build(null)
    );

    public static final Supplier<BlockEntityType<GaeanKeystoneBlockEntity>> GAEAN_KEYSTONE = BLOCK_ENTITIES.register(
            "gaean_keystone",
            () -> BlockEntityType.Builder.of(
                    GaeanKeystoneBlockEntity::new, ModBlocks.GAEAN_KEYSTONE.get()
            ).build(null)
    );
}
