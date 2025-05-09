package erebus.registries.blocks;

import erebus.Erebus;
import erebus.registries.blocks.providers.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Erebus.MODID);

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);

        UmberstoneBlocks.init();
        AmberBlocks.init();
        OreBlocks.init();
        WoodBlocks.init();
        SlabBlocks.init();
        StairBlocks.init();
        WallBlocks.init();
        OtherBlocks.init();
        PlantBlocks.init();
        DoorBlocks.init();
        FenceBlocks.init();
        FluidBlocks.init();
    }
}
