package erebus.registries.blocks.providers;

import erebus.block.FluidJarBlock;
import erebus.block.GlowingJarBlock;
import erebus.block.PreservedBlock;
import erebus.block.util.ConnectedTextureBlock;
import erebus.block.util.ModBlockSetTypes;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

import static erebus.registries.blocks.properties.AmberBlockProperties.*;

public class AmberBlocks extends ModBlockHelpers {
    public static final DeferredBlock<TransparentBlock> AMBER;
    public static final DeferredBlock<TransparentBlock> AMBER_BRICKS;
    public static final DeferredBlock<ConnectedTextureBlock> AMBER_GLASS;
    public static final DeferredBlock<Block> PRESERVED_AMBER;
    public static final DeferredBlock<Block> PRESERVED_AMBER_GLASS;

    public static final DeferredBlock<GlowingJarBlock> GLOWING_JAR;
    public static final DeferredBlock<FluidJarBlock> FLUID_JAR;

    public static final DeferredBlock<DoorBlock> AMBER_DOOR;

    static {
        AMBER = registerTransparentBlock("amber", AMBER_PROPERTIES.strength(1.5F));
        AMBER_BRICKS = registerTransparentBlock("amber_bricks", AMBER_PROPERTIES.strength(2.0F));
        AMBER_GLASS = registerConnectedTextureBlock("amber_glass", AMBER_PROPERTIES.strength(1.5F));
        PRESERVED_AMBER = registerBlock("preserved_amber", () -> new PreservedBlock(AMBER_PROPERTIES.strength(10)));
        PRESERVED_AMBER_GLASS = registerBlock("preserved_amber_glass", () -> new PreservedBlock(AMBER_PROPERTIES.strength(10)));
        GLOWING_JAR = registerBlock("glowing_jar", () -> new GlowingJarBlock(GLOWING_JAR_PROPERTIES));
        FLUID_JAR = registerBlockWithoutBlockItem("fluid_jar", () -> new FluidJarBlock(FLUID_JAR_PROPERTIES));
        AMBER_DOOR = registerDoor("amber_door", ModBlockSetTypes.AMBER, AMBER_DOOR_PROPERTIES);
    }

    public static void init() {
    }
}
