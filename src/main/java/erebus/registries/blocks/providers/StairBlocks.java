package erebus.registries.blocks.providers;

import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredBlock;

public class StairBlocks extends ModBlockHelpers {

    // MARK: Stairs Wood
    public static final DeferredBlock<StairBlock> STAIRS_BAOBAB;
    public static final DeferredBlock<StairBlock> STAIRS_EUCALYPTUS;
    public static final DeferredBlock<StairBlock> STAIRS_MAHOGANY;
    public static final DeferredBlock<StairBlock> STAIRS_MOSSBARK;
    public static final DeferredBlock<StairBlock> STAIRS_ASPER;
    public static final DeferredBlock<StairBlock> STAIRS_CYPRESS;
    public static final DeferredBlock<StairBlock> STAIRS_BALSAM;
    public static final DeferredBlock<StairBlock> STAIRS_WHITE;
    public static final DeferredBlock<StairBlock> STAIRS_BAMBOO;
    public static final DeferredBlock<StairBlock> STAIRS_ROTTEN;
    public static final DeferredBlock<StairBlock> STAIRS_MARSHWOOD;
    public static final DeferredBlock<StairBlock> STAIRS_SCORCHED;
    public static final DeferredBlock<StairBlock> STAIRS_VARNISHED;
    public static final DeferredBlock<StairBlock> STAIRS_PETRIFIED;

    // MARK: Stairs Stone
    public static final DeferredBlock<StairBlock> STAIRS_UMBERSTONE;
    public static final DeferredBlock<StairBlock> STAIRS_UMBERCOBBLE;
    public static final DeferredBlock<StairBlock> STAIRS_UMBERCOBBLE_MOSSY;
    public static final DeferredBlock<StairBlock> STAIRS_UMBERCOBBLE_WEBBED;
    public static final DeferredBlock<StairBlock> STAIRS_UMBERSTONE_BRICKS;
    public static final DeferredBlock<StairBlock> STAIRS_UMBERTILE_SMOOTH;
    public static final DeferredBlock<StairBlock> STAIRS_UMBERTILE_SMOOTH_SMALL;
    public static final DeferredBlock<StairBlock> STAIRS_UMBERPAVER;
    public static final DeferredBlock<StairBlock> STAIRS_UMBERPAVER_MOSSY;
    public static final DeferredBlock<StairBlock> STAIRS_UMBERPAVER_WEBBED;
    public static final DeferredBlock<StairBlock> STAIRS_AMBER;
    public static final DeferredBlock<StairBlock> STAIRS_AMBER_BRICKS;
    public static final DeferredBlock<StairBlock> STAIRS_MUD_BRICKS;
    public static final DeferredBlock<StairBlock> STAIRS_MIR_BRICKS;

    static {
        // MARK: Stairs Wood
        STAIRS_BAOBAB = registerStairs("stairs_baobab", WoodBlocks.PLANKS_BAOBAB, Properties.of().strength(2.0F));
        STAIRS_EUCALYPTUS = registerStairs("stairs_eucalyptus", WoodBlocks.PLANKS_EUCALYPTUS, Properties.of().strength(2.0F));
        STAIRS_MAHOGANY = registerStairs("stairs_mahogany", WoodBlocks.PLANKS_MAHOGANY, Properties.of().strength(2.0F));
        STAIRS_MOSSBARK = registerStairs("stairs_mossbark", WoodBlocks.PLANKS_MOSSBARK, Properties.of().strength(2.0F));
        STAIRS_ASPER = registerStairs("stairs_asper", WoodBlocks.PLANKS_ASPER, Properties.of().strength(2.0F));
        STAIRS_CYPRESS = registerStairs("stairs_cypress", WoodBlocks.PLANKS_CYPRESS, Properties.of().strength(2.0F));
        STAIRS_BALSAM = registerStairs("stairs_balsam", WoodBlocks.PLANKS_BALSAM, Properties.of().strength(2.0F));
        STAIRS_WHITE = registerStairs("stairs_white", WoodBlocks.PLANKS_WHITE, Properties.of().strength(2.0F));
        STAIRS_BAMBOO = registerStairs("stairs_bamboo", WoodBlocks.PLANKS_BAMBOO, Properties.of().strength(2.0F));
        STAIRS_ROTTEN = registerStairs("stairs_rotten", WoodBlocks.PLANKS_ROTTEN, Properties.of().strength(2.0F));
        STAIRS_MARSHWOOD = registerStairs("stairs_marshwood", WoodBlocks.PLANKS_MARSHWOOD, Properties.of().strength(2.0F));
        STAIRS_SCORCHED = registerStairs("stairs_scorched", WoodBlocks.PLANKS_SCORCHED, Properties.of().strength(2.0F));
        STAIRS_VARNISHED = registerStairs("stairs_varnished", WoodBlocks.PLANKS_VARNISHED, Properties.of().strength(2.0F));
        STAIRS_PETRIFIED = registerStairs("stairs_petrified", WoodBlocks.PLANKS_PETRIFIED, Properties.of().strength(2.0F));

        // MARK: Stairs Stone
        STAIRS_UMBERSTONE = registerStairs("stairs_umberstone", UmberstoneBlocks.UMBERSTONE, Properties.of().strength(2.0F));
        STAIRS_UMBERCOBBLE = registerStairs("stairs_umbercobble", UmberstoneBlocks.UMBERCOBBLE, Properties.of().strength(2.0F));
        STAIRS_UMBERCOBBLE_MOSSY = registerStairs("stairs_umbercobble_mossy", UmberstoneBlocks.UMBERCOBBLE_MOSSY, Properties.of().strength(2.0F));
        STAIRS_UMBERCOBBLE_WEBBED = registerStairs("stairs_umbercobble_webbed", UmberstoneBlocks.UMBERCOBBLE_WEBBED, Properties.of().strength(2.0F));
        STAIRS_UMBERSTONE_BRICKS = registerStairs("stairs_umberstone_bricks", UmberstoneBlocks.UMBERSTONE_BRICKS, Properties.of().strength(2.0F));
        STAIRS_UMBERTILE_SMOOTH = registerStairs("stairs_umberstone_smooth", UmberstoneBlocks.UMBERTILE_SMOOTH, Properties.of().strength(2.0F));
        STAIRS_UMBERTILE_SMOOTH_SMALL = registerStairs("stairs_umberstone_smooth_small", UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL, Properties.of().strength(2.0F));
        STAIRS_UMBERPAVER = registerStairs("stairs_umberpaver", UmberstoneBlocks.UMBERPAVER, Properties.of().strength(2.0F));
        STAIRS_UMBERPAVER_MOSSY = registerStairs("stairs_umberpaver_mossy", UmberstoneBlocks.UMBERPAVER_MOSSY, Properties.of().strength(2.0F));
        STAIRS_UMBERPAVER_WEBBED = registerStairs("stairs_umberpaver_webbed", UmberstoneBlocks.UMBERPAVER_WEBBED, Properties.of().strength(2.0F));
        STAIRS_AMBER = registerStairs("stairs_amber", AmberBlocks.AMBER, Properties.of().strength(2.0F));
        STAIRS_AMBER_BRICKS = registerStairs("stairs_amber_bricks", AmberBlocks.AMBER_BRICKS, Properties.of().strength(2.0F));
        STAIRS_MUD_BRICKS = registerStairs("stairs_mud_bricks", UmberstoneBlocks.MUD_BRICKS, Properties.of().strength(2.0F));
        STAIRS_MIR_BRICKS = registerStairs("stairs_mir_bricks", UmberstoneBlocks.MIR_BRICKS, Properties.of().strength(2.0F));
    }

    public static void init() {
    }
}
