package erebus.registries.blocks.properties;

import erebus.registries.blocks.providers.AmberBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class WallBlockProperties {

    public static final Properties WALL_UMBERSTONE;
    public static final Properties WALL_UMBERCOBBLE;
    public static final Properties WALL_UMBERCOBBLE_MOSSY;
    public static final Properties WALL_UMBERCOBBLE_WEBBED;
    public static final Properties WALL_UMBERSTONE_BRICKS;
    public static final Properties WALL_UMBERTILE_SMOOTH;
    public static final Properties WALL_UMBERTILE_SMOOTH_SMALL;
    public static final Properties WALL_AMBER;
    public static final Properties WALL_AMBER_BRICKS;
    public static final Properties WALL_UMBERPAVER;
    public static final Properties WALL_UMBERPAVER_MOSSY;
    public static final Properties WALL_UMBERPAVER_WEBBED;

    static {
        Properties base = Properties.ofFullCopy(Blocks.STONE_BRICK_WALL);
        WALL_UMBERSTONE = base;
        WALL_UMBERCOBBLE = base;
        WALL_UMBERCOBBLE_MOSSY = base;
        WALL_UMBERCOBBLE_WEBBED = base;
        WALL_UMBERSTONE_BRICKS = base;
        WALL_UMBERTILE_SMOOTH = base;
        WALL_UMBERTILE_SMOOTH_SMALL = base;
        WALL_AMBER = Properties.ofFullCopy(AmberBlocks.AMBER.get());
        WALL_AMBER_BRICKS = Properties.ofFullCopy(AmberBlocks.AMBER.get());
        WALL_UMBERPAVER = base;
        WALL_UMBERPAVER_MOSSY = base;
        WALL_UMBERPAVER_WEBBED = base;
    }
}
