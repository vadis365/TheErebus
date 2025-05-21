package erebus.registries.blocks.providers;

import erebus.block.FluidJarBlock;
import erebus.block.util.ConnectedTextureBlock;
import erebus.block.util.ModBlockSetTypes;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
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

    private static final Properties AMBER_PROPERTIES = Properties.of()
            .noOcclusion()
            .isViewBlocking((blockState, blockGetter, blockPos) -> false)
            .mapColor(MapColor.GOLD)
            .explosionResistance(10.0F)
            .sound(SoundType.GLASS);

    static {
        AMBER = registerTransparentBlock("amber", AMBER_PROPERTIES.strength(1.5F));
        AMBER_BRICKS = registerTransparentBlock("amber_bricks", AMBER_PROPERTIES.strength(2.0F));
        AMBER_GLASS = registerConnectedTextureBlock("amber_glass", AMBER_PROPERTIES.strength(1.5F));
        PRESERVED_AMBER = registerTransparentBlock("preserved_amber", AMBER_PROPERTIES.strength(10));
        PRESERVED_AMBER_GLASS = registerConnectedTextureBlock("preserved_amber_glass", AMBER_PROPERTIES.strength(10));
        GLOWING_JAR = registerSimpleBlock(
                "glowing_jar",
                Properties.of()
                        .strength(0.5F, 10.0F)
                        .sound(SoundType.GLASS)
                        .lightLevel(value -> 1)
                        .noOcclusion()
                        .isViewBlocking((state, getter, pos) -> false)
        );
        FLUID_JAR = registerBlockWithoutBlockItem(
                "fluid_jar",
                () -> new FluidJarBlock(
                        Properties.ofFullCopy(Blocks.GLASS)
                                .mapColor(MapColor.STONE)
                                .strength(1.0F, 2000.0F)
                                .sound(SoundType.GLASS)
                                .noOcclusion()
                                .isViewBlocking((state, getter, pos) -> false)
                )
        );
        AMBER_DOOR = registerDoor("amber_door", ModBlockSetTypes.AMBER, Properties.ofFullCopy(Blocks.OAK_DOOR));
    }

    public static void init() {
    }
}
