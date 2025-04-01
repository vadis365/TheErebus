package erebus.registries;

import erebus.Erebus;
import erebus.block.entity.UmberFurnaceBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Erebus.MODID);

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }    public static final Supplier<BlockEntityType<UmberFurnaceBlockEntity>> UMBERFURNACE = BLOCK_ENTITIES.register(
            "umberfurnace",
            () -> BlockEntityType.Builder.of(
                    UmberFurnaceBlockEntity::new, ModBlocks.UMBER_FURNACE.get()
            ).build(null)
    );


}
