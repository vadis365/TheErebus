package erebus.registries.blocks.providers;

import erebus.registries.ModFluids;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FluidBlocks extends ModBlockHelpers {

    public static final DeferredBlock<LiquidBlock> BEETLE_JUICE_BLOCK;
    private static final Properties BEETLE_JUICE_PROPERTIES;

    static {
        BEETLE_JUICE_PROPERTIES = Properties.of()
                .mapColor(MapColor.WATER)
                .replaceable()
                .noCollission()
                .strength(100.0F)
                .pushReaction(PushReaction.DESTROY)
                .noLootTable()
                .liquid()
                .sound(SoundType.EMPTY);

        BEETLE_JUICE_BLOCK = registerBlock("beetle_juice", () -> new LiquidBlock(ModFluids.BEETLE_JUICE_STILL.get(), BEETLE_JUICE_PROPERTIES));
    }

    public static void init() {
    }
}
