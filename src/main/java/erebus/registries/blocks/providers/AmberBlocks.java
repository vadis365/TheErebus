package erebus.registries.blocks.providers;

import erebus.block.ConnectedTextureBlock;
import erebus.block.FluidJarBlock;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public class AmberBlocks extends ModBlockHelpers {
    public static final DeferredBlock<TransparentBlock> AMBER;
    public static final DeferredBlock<TransparentBlock> AMBER_BRICKS;
    public static final DeferredBlock<ConnectedTextureBlock> AMBER_GLASS;
    public static final DeferredBlock<TransparentBlock> PRESERVED_AMBER;
    public static final DeferredBlock<ConnectedTextureBlock> PRESERVED_AMBER_GLASS;

    public static final DeferredBlock<Block> GLOWING_JAR;
    public static final DeferredBlock<FluidJarBlock> FLUID_JAR;

    public static final DeferredBlock<DoorBlock> AMBER_DOOR;

    static {
        AMBER = registerTransparentBlock("amber", Properties.ofFullCopy(Blocks.GLASS).strength(1.5F).noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false).sound(SoundType.GLASS).mapColor(MapColor.GOLD));
        AMBER_BRICKS = registerTransparentBlock("amber_bricks", Properties.ofFullCopy(Blocks.GLASS).strength(2.5F).noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false).sound(SoundType.GLASS).mapColor(MapColor.GOLD));
        AMBER_GLASS = registerConnectedTextureBlock("amber_glass", Properties.ofFullCopy(Blocks.GLASS).strength(2.5F).sound(SoundType.GLASS).mapColor(MapColor.GOLD));
        PRESERVED_AMBER = registerTransparentBlock("preserved_amber", Properties.ofFullCopy(Blocks.GLASS).strength(2.5F).noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false).sound(SoundType.GLASS).mapColor(MapColor.GOLD));
        PRESERVED_AMBER_GLASS = registerConnectedTextureBlock("preserved_amber_glass", Properties.ofFullCopy(Blocks.GLASS).strength(2.5F).sound(SoundType.GLASS).mapColor(MapColor.GOLD));
        GLOWING_JAR = registerSimpleBlock("glowing_jar", Properties.of().mapColor(MapColor.STONE));
        FLUID_JAR = registerBlockWithoutBlockItem("fluid_jar", () -> new FluidJarBlock(Properties.ofFullCopy(Blocks.GLASS).mapColor(MapColor.STONE).strength(1.0F, 2000.0F).sound(SoundType.GLASS).noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false)));
        AMBER_DOOR = registerDoor("amber_door", BlockSetType.STONE, Properties.ofFullCopy(Blocks.OAK_DOOR));
    }

    public static void init() {
    }
}
