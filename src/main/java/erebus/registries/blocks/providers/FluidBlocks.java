package erebus.registries.blocks.providers;

import erebus.block.fluid.FormicAcidFluidBlock;
import erebus.registries.ModFluids;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FluidBlocks extends ModBlockHelpers {

    public static final DeferredBlock<LiquidBlock> FORMIC_ACID_BLOCK, HONEY_BLOCK, BEETLE_JUICE_BLOCK, ANTI_VENOM_BLOCK;
    private static final Properties FORMIC_ACID_PROPERTIES, HONEY_PROPERTIES, BEETLE_JUICE_PROPERTIES, ANTI_VENOM_PROPERTIES;
    // These are all the same atm - need to work out some different properties
    static {
        FORMIC_ACID_PROPERTIES = Properties.of()
                .mapColor(MapColor.WATER)
                .replaceable()
                .noCollission()
                .strength(100.0F)
                .pushReaction(PushReaction.DESTROY)
                .noLootTable()
                .liquid()
                .sound(SoundType.EMPTY);
        
        HONEY_PROPERTIES = Properties.of()
                .mapColor(MapColor.WATER)
                .replaceable()
                .noCollission()
                .strength(100.0F)
                .pushReaction(PushReaction.DESTROY)
                .noLootTable()
                .liquid()
                .sound(SoundType.EMPTY);
        
        BEETLE_JUICE_PROPERTIES = Properties.of()
                .mapColor(MapColor.WATER)
                .replaceable()
                .noCollission()
                .strength(100.0F)
                .pushReaction(PushReaction.DESTROY)
                .noLootTable()
                .liquid()
                .sound(SoundType.EMPTY);
        
        ANTI_VENOM_PROPERTIES = Properties.of()
                .mapColor(MapColor.WATER)
                .replaceable()
                .noCollission()
                .strength(100.0F)
                .pushReaction(PushReaction.DESTROY)
                .noLootTable()
                .liquid()
                .sound(SoundType.EMPTY);
        
        FORMIC_ACID_BLOCK = registerBlock("formic_acid", () -> new FormicAcidFluidBlock(ModFluids.FORMIC_ACID_STILL.get(), FORMIC_ACID_PROPERTIES));
        HONEY_BLOCK = registerBlock("honey", () -> new LiquidBlock(ModFluids.HONEY_STILL.get(), HONEY_PROPERTIES));
        BEETLE_JUICE_BLOCK = registerBlock("beetle_juice", () -> new LiquidBlock(ModFluids.BEETLE_JUICE_STILL.get(), BEETLE_JUICE_PROPERTIES));
        ANTI_VENOM_BLOCK = registerBlock("anti_venom", () -> new LiquidBlock(ModFluids.ANTI_VENOM_STILL.get(), ANTI_VENOM_PROPERTIES));
    }

    public static void init() {
    }
}
