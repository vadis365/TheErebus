package erebus.registries.blocks.providers;

import erebus.block.fluid.FormicAcidFluidBlock;
import erebus.registries.ModFluids;
import erebus.registries.blocks.properties.FluidBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.LiquidBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FluidBlocks extends ModBlockHelpers {

    public static final DeferredBlock<LiquidBlock> FORMIC_ACID_BLOCK;
    public static final DeferredBlock<LiquidBlock> HONEY_BLOCK;
    public static final DeferredBlock<LiquidBlock> BEETLE_JUICE_BLOCK;
    public static final DeferredBlock<LiquidBlock> ANTI_VENOM_BLOCK;

    static {
        FORMIC_ACID_BLOCK = registerBlock("formic_acid", () -> new FormicAcidFluidBlock(ModFluids.FORMIC_ACID_STILL.get(), FluidBlockProperties.FORMIC_ACID));
        HONEY_BLOCK = registerBlock("honey", () -> new LiquidBlock(ModFluids.HONEY_STILL.get(), FluidBlockProperties.HONEY));
        BEETLE_JUICE_BLOCK = registerBlock("beetle_juice", () -> new LiquidBlock(ModFluids.BEETLE_JUICE_STILL.get(), FluidBlockProperties.BEETLE_JUICE));
        ANTI_VENOM_BLOCK = registerBlock("anti_venom", () -> new LiquidBlock(ModFluids.ANTI_VENOM_STILL.get(), FluidBlockProperties.ANTI_VENOM));
    }

    public static void init() {
    }
}
