package erebus.registries.blocks.providers;

import erebus.registries.blocks.properties.StairBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.StairBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class StairBlocks extends ModBlockHelpers {

    // MARK: Stairs Wood
    public static final DeferredBlock<StairBlock> ASPER;
    public static final DeferredBlock<StairBlock> BALSAM;
    public static final DeferredBlock<StairBlock> BAMBOO;
    public static final DeferredBlock<StairBlock> BAOBAB;
    public static final DeferredBlock<StairBlock> CYPRESS;
    public static final DeferredBlock<StairBlock> EUCALYPTUS;
    public static final DeferredBlock<StairBlock> MAHOGANY;
    public static final DeferredBlock<StairBlock> MARSHWOOD;
    public static final DeferredBlock<StairBlock> MOSSBARK;
    public static final DeferredBlock<StairBlock> PETRIFIED;
    public static final DeferredBlock<StairBlock> ROTTEN;
    public static final DeferredBlock<StairBlock> SCORCHED;
    public static final DeferredBlock<StairBlock> VARNISHED;
    public static final DeferredBlock<StairBlock> WHITE;

    // MARK: Stairs Stone
    public static final DeferredBlock<StairBlock> AMBER;
    public static final DeferredBlock<StairBlock> AMBER_BRICKS;
    public static final DeferredBlock<StairBlock> MIR_BRICKS;
    public static final DeferredBlock<StairBlock> MUD_BRICKS;
    public static final DeferredBlock<StairBlock> UMBERCOBBLE;
    public static final DeferredBlock<StairBlock> UMBERCOBBLE_MOSSY;
    public static final DeferredBlock<StairBlock> UMBERCOBBLE_WEBBED;
    public static final DeferredBlock<StairBlock> UMBERPAVER;
    public static final DeferredBlock<StairBlock> UMBERPAVER_MOSSY;
    public static final DeferredBlock<StairBlock> UMBERPAVER_WEBBED;
    public static final DeferredBlock<StairBlock> UMBERSTONE;
    public static final DeferredBlock<StairBlock> UMBERSTONE_BRICKS;
    public static final DeferredBlock<StairBlock> UMBERTILE_SMOOTH;
    public static final DeferredBlock<StairBlock> UMBERTILE_SMOOTH_SMALL;

    static {
        // MARK: Stairs Wood
        ASPER = registerStairs("stairs_asper", WoodBlocks.PLANKS_ASPER, StairBlockProperties.ASPER);
        BALSAM = registerStairs("stairs_balsam", WoodBlocks.PLANKS_BALSAM, StairBlockProperties.BALSAM);
        BAMBOO = registerStairs("stairs_bamboo", WoodBlocks.PLANKS_BAMBOO, StairBlockProperties.BAMBOO);
        BAOBAB = registerStairs("stairs_baobab", WoodBlocks.PLANKS_BAOBAB, StairBlockProperties.BAOBAB);
        CYPRESS = registerStairs("stairs_cypress", WoodBlocks.PLANKS_CYPRESS, StairBlockProperties.CYPRESS);
        EUCALYPTUS = registerStairs("stairs_eucalyptus", WoodBlocks.PLANKS_EUCALYPTUS, StairBlockProperties.EUCALYPTUS);
        MAHOGANY = registerStairs("stairs_mahogany", WoodBlocks.PLANKS_MAHOGANY, StairBlockProperties.MAHOGANY);
        MARSHWOOD = registerStairs("stairs_marshwood", WoodBlocks.PLANKS_MARSHWOOD, StairBlockProperties.MARSHWOOD);
        MOSSBARK = registerStairs("stairs_mossbark", WoodBlocks.PLANKS_MOSSBARK, StairBlockProperties.MOSSBARK);
        PETRIFIED = registerStairs("stairs_petrified", WoodBlocks.PLANKS_PETRIFIED, StairBlockProperties.PETRIFIED);
        ROTTEN = registerStairs("stairs_rotten", WoodBlocks.PLANKS_ROTTEN, StairBlockProperties.ROTTEN);
        SCORCHED = registerStairs("stairs_scorched", WoodBlocks.PLANKS_SCORCHED, StairBlockProperties.SCORCHED);
        VARNISHED = registerStairs("stairs_varnished", WoodBlocks.PLANKS_VARNISHED, StairBlockProperties.VARNISHED);
        WHITE = registerStairs("stairs_white", WoodBlocks.PLANKS_WHITE, StairBlockProperties.WHITE);

        // MARK: Stairs Stone
        AMBER = registerStairs("stairs_amber", AmberBlocks.AMBER, StairBlockProperties.AMBER);
        AMBER_BRICKS = registerStairs("stairs_amber_bricks", AmberBlocks.AMBER_BRICKS, StairBlockProperties.AMBER_BRICKS);
        MIR_BRICKS = registerStairs("stairs_mir_bricks", UmberstoneBlocks.MIR_BRICKS, StairBlockProperties.MIR_BRICKS);
        MUD_BRICKS = registerStairs("stairs_mud_bricks", UmberstoneBlocks.MUD_BRICKS, StairBlockProperties.MUD_BRICKS);
        UMBERCOBBLE = registerStairs("stairs_umbercobble", UmberstoneBlocks.UMBERCOBBLE, StairBlockProperties.UMBERCOBBLE);
        UMBERCOBBLE_MOSSY = registerStairs("stairs_umbercobble_mossy", UmberstoneBlocks.UMBERCOBBLE_MOSSY, StairBlockProperties.UMBERCOBBLE_MOSSY);
        UMBERCOBBLE_WEBBED = registerStairs("stairs_umbercobble_webbed", UmberstoneBlocks.UMBERCOBBLE_WEBBED, StairBlockProperties.UMBERCOBBLE_WEBBED);
        UMBERPAVER = registerStairs("stairs_umberpaver", UmberstoneBlocks.UMBERPAVER, StairBlockProperties.UMBERPAVER);
        UMBERPAVER_MOSSY = registerStairs("stairs_umberpaver_mossy", UmberstoneBlocks.UMBERPAVER_MOSSY, StairBlockProperties.UMBERPAVER_MOSSY);
        UMBERPAVER_WEBBED = registerStairs("stairs_umberpaver_webbed", UmberstoneBlocks.UMBERPAVER_WEBBED, StairBlockProperties.UMBERPAVER_WEBBED);
        UMBERSTONE = registerStairs("stairs_umberstone", UmberstoneBlocks.UMBERSTONE, StairBlockProperties.UMBERSTONE);
        UMBERSTONE_BRICKS = registerStairs("stairs_umberstone_bricks", UmberstoneBlocks.UMBERSTONE_BRICKS, StairBlockProperties.UMBERSTONE_BRICKS);
        UMBERTILE_SMOOTH = registerStairs("stairs_umberstone_smooth", UmberstoneBlocks.UMBERTILE_SMOOTH, StairBlockProperties.UMBERTILE_SMOOTH);
        UMBERTILE_SMOOTH_SMALL = registerStairs("stairs_umberstone_smooth_small", UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL, StairBlockProperties.UMBERTILE_SMOOTH_SMALL);
    }

    public static void init() {
    }
}
