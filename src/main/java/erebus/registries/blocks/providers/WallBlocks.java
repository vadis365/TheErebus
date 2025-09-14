package erebus.registries.blocks.providers;

import erebus.registries.blocks.properties.WallBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class WallBlocks extends ModBlockHelpers {

    public static final DeferredBlock<WallBlock> WALL_UMBERSTONE;
    public static final DeferredBlock<WallBlock> WALL_UMBERCOBBLE;
    public static final DeferredBlock<WallBlock> WALL_UMBERCOBBLE_MOSSY;
    public static final DeferredBlock<WallBlock> WALL_UMBERCOBBLE_WEBBED;
    public static final DeferredBlock<WallBlock> WALL_UMBERSTONE_BRICKS;
    public static final DeferredBlock<WallBlock> WALL_UMBERTILE_SMOOTH;
    public static final DeferredBlock<WallBlock> WALL_UMBERTILE_SMOOTH_SMALL;
    public static final DeferredBlock<WallBlock> WALL_AMBER;
    public static final DeferredBlock<WallBlock> WALL_AMBER_BRICKS;
    public static final DeferredBlock<WallBlock> WALL_UMBERPAVER;
    public static final DeferredBlock<WallBlock> WALL_UMBERPAVER_MOSSY;
    public static final DeferredBlock<WallBlock> WALL_UMBERPAVER_WEBBED;

    static {
        WALL_UMBERSTONE = registerBlock("wall_umberstone", () -> new WallBlock(WallBlockProperties.WALL_UMBERSTONE));
        WALL_UMBERCOBBLE = registerBlock("wall_umbercobble", () -> new WallBlock(WallBlockProperties.WALL_UMBERCOBBLE));
        WALL_UMBERCOBBLE_MOSSY = registerBlock("wall_umbercobble_mossy", () -> new WallBlock(WallBlockProperties.WALL_UMBERCOBBLE_MOSSY));
        WALL_UMBERCOBBLE_WEBBED = registerBlock("wall_umbercobble_webbed", () -> new WallBlock(WallBlockProperties.WALL_UMBERCOBBLE_WEBBED));
        WALL_UMBERSTONE_BRICKS = registerBlock("wall_umberstone_bricks", () -> new WallBlock(WallBlockProperties.WALL_UMBERSTONE_BRICKS));
        WALL_UMBERTILE_SMOOTH = registerBlock("wall_umbertile_smooth", () -> new WallBlock(WallBlockProperties.WALL_UMBERTILE_SMOOTH));
        WALL_UMBERTILE_SMOOTH_SMALL = registerBlock("wall_umbertile_smooth_small", () -> new WallBlock(WallBlockProperties.WALL_UMBERTILE_SMOOTH_SMALL));
        WALL_AMBER = registerBlock("wall_amber", () -> new WallBlock(WallBlockProperties.WALL_AMBER));
        WALL_AMBER_BRICKS = registerBlock("wall_amber_bricks", () -> new WallBlock(WallBlockProperties.WALL_AMBER_BRICKS));
        WALL_UMBERPAVER = registerBlock("wall_umberpaver", () -> new WallBlock(WallBlockProperties.WALL_UMBERPAVER));
        WALL_UMBERPAVER_MOSSY = registerBlock("wall_umberpaver_mossy", () -> new WallBlock(WallBlockProperties.WALL_UMBERPAVER_MOSSY));
        WALL_UMBERPAVER_WEBBED = registerBlock("wall_umberpaver_webbed", () -> new WallBlock(WallBlockProperties.WALL_UMBERPAVER_WEBBED));
    }

    public static void init() {
    }
}
