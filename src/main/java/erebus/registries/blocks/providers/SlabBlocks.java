package erebus.registries.blocks.providers;

import erebus.registries.blocks.properties.SlabBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.SlabBlock;
import net.neoforged.neoforge.registries.DeferredBlock;

public class SlabBlocks extends ModBlockHelpers {

    // MARK: Slabs Wood
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_BAOBAB;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_EUCALYPTUS;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_MAHOGANY;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_MOSSBARK;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_ASPER;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_CYPRESS;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_BALSAM;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_WHITE;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_BAMBOO;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_ROTTEN;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_MARSHWOOD;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_SCORCHED;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_VARNISHED;
    public static final DeferredBlock<SlabBlock> SLAB_PLANKS_PETRIFIED;

    // MARK: Slabs Stone
    public static final DeferredBlock<SlabBlock> SLAB_UMBERSTONE;
    public static final DeferredBlock<SlabBlock> SLAB_UMBERCOBBLE;
    public static final DeferredBlock<SlabBlock> SLAB_UMBERCOBBLE_MOSSY;
    public static final DeferredBlock<SlabBlock> SLAB_UMBERCOBBLE_WEBBED;
    public static final DeferredBlock<SlabBlock> SLAB_UMBERSTONE_BRICKS;
    public static final DeferredBlock<SlabBlock> SLAB_UMBERTILE_SMOOTH;
    public static final DeferredBlock<SlabBlock> SLAB_UMBERTILE_SMOOTH_SMALL;
    public static final DeferredBlock<SlabBlock> SLAB_UMBERPAVER;
    public static final DeferredBlock<SlabBlock> SLAB_UMBERPAVER_MOSSY;
    public static final DeferredBlock<SlabBlock> SLAB_UMBERPAVER_WEBBED;
    public static final DeferredBlock<SlabBlock> SLAB_AMBER;
    public static final DeferredBlock<SlabBlock> SLAB_AMBER_BRICKS;
    public static final DeferredBlock<SlabBlock> SLAB_MUD_BRICKS;
    public static final DeferredBlock<SlabBlock> SLAB_MIR_BRICKS;

    static {
        SLAB_PLANKS_BAOBAB = registerSlab("slab_planks_baobab", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_EUCALYPTUS = registerSlab("slab_planks_eucalyptus", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_MAHOGANY = registerSlab("slab_planks_mahogany", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_MOSSBARK = registerSlab("slab_planks_mossbark", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_ASPER = registerSlab("slab_planks_asper", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_CYPRESS = registerSlab("slab_planks_cypress", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_BALSAM = registerSlab("slab_planks_balsam", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_WHITE = registerSlab("slab_planks_white", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_BAMBOO = registerSlab("slab_planks_bamboo", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_ROTTEN = registerSlab("slab_planks_rotten", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_MARSHWOOD = registerSlab("slab_planks_marshwood", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_SCORCHED = registerSlab("slab_planks_scorched", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_VARNISHED = registerSlab("slab_planks_varnished", SlabBlockProperties.SLAB_PLANKS);
        SLAB_PLANKS_PETRIFIED = registerSlab("slab_planks_petrified", SlabBlockProperties.SLAB_PLANKS);

        SLAB_UMBERSTONE = registerSlab("slab_umberstone", SlabBlockProperties.SLAB_STONE);
        SLAB_UMBERCOBBLE = registerSlab("slab_umbercobble", SlabBlockProperties.SLAB_STONE);
        SLAB_UMBERCOBBLE_MOSSY = registerSlab("slab_umbercobble_mossy", SlabBlockProperties.SLAB_STONE);
        SLAB_UMBERCOBBLE_WEBBED = registerSlab("slab_umbercobble_webbed", SlabBlockProperties.SLAB_STONE);
        SLAB_UMBERSTONE_BRICKS = registerSlab("slab_umberstone_bricks", SlabBlockProperties.SLAB_STONE);
        SLAB_UMBERTILE_SMOOTH = registerSlab("slab_umberstone_smooth", SlabBlockProperties.SLAB_STONE);
        SLAB_UMBERTILE_SMOOTH_SMALL = registerSlab("slab_umberstone_smooth_small", SlabBlockProperties.SLAB_STONE);
        SLAB_UMBERPAVER = registerSlab("slab_umberpaver", SlabBlockProperties.SLAB_STONE);
        SLAB_UMBERPAVER_MOSSY = registerSlab("slab_umberpaver_mossy", SlabBlockProperties.SLAB_STONE);
        SLAB_UMBERPAVER_WEBBED = registerSlab("slab_umberpaver_webbed", SlabBlockProperties.SLAB_STONE);
        SLAB_AMBER = registerSlab("slab_amber", SlabBlockProperties.SLAB_AMBER);
        SLAB_AMBER_BRICKS = registerSlab("slab_amber_bricks", SlabBlockProperties.SLAB_AMBER_BRICKS);
        SLAB_MUD_BRICKS = registerSlab("slab_mud_bricks", SlabBlockProperties.SLAB_STONE);
        SLAB_MIR_BRICKS = registerSlab("slab_mir_bricks", SlabBlockProperties.SLAB_STONE);
    }

    public static void init() {
    }
}
