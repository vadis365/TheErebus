package erebus.registries.blocks.providers;

import erebus.registries.blocks.properties.SlabBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public class SlabBlocks extends ModBlockHelpers {

    // MARK: Slabs Wood
    public static final DeferredBlock<SlabBlock> ASPER;
    public static final DeferredBlock<SlabBlock> BAOBAB;
    public static final DeferredBlock<SlabBlock> BALSAM;
    public static final DeferredBlock<SlabBlock> BAMBOO;
    public static final DeferredBlock<SlabBlock> CYPRESS;
    public static final DeferredBlock<SlabBlock> EUCALYPTUS;
    public static final DeferredBlock<SlabBlock> MAHOGANY;
    public static final DeferredBlock<SlabBlock> MARSHWOOD;
    public static final DeferredBlock<SlabBlock> MOSSBARK;
    public static final DeferredBlock<SlabBlock> PETRIFIED;
    public static final DeferredBlock<SlabBlock> ROTTEN;
    public static final DeferredBlock<SlabBlock> SCORCHED;
    public static final DeferredBlock<SlabBlock> VARNISHED;
    public static final DeferredBlock<SlabBlock> WHITE;

    // MARK: Slabs Stone
    public static final DeferredBlock<SlabBlock> AMBER;
    public static final DeferredBlock<SlabBlock> AMBER_BRICKS;
    public static final DeferredBlock<SlabBlock> MIR_BRICKS;
    public static final DeferredBlock<SlabBlock> MUD_BRICKS;
    public static final DeferredBlock<SlabBlock> UMBERCOBBLE;
    public static final DeferredBlock<SlabBlock> UMBERCOBBLE_MOSSY;
    public static final DeferredBlock<SlabBlock> UMBERCOBBLE_WEBBED;
    public static final DeferredBlock<SlabBlock> UMBERPAVER;
    public static final DeferredBlock<SlabBlock> UMBERPAVER_MOSSY;
    public static final DeferredBlock<SlabBlock> UMBERPAVER_WEBBED;
    public static final DeferredBlock<SlabBlock> UMBERSTONE;
    public static final DeferredBlock<SlabBlock> UMBERSTONE_BRICKS;
    public static final DeferredBlock<SlabBlock> UMBERTILE_SMOOTH;
    public static final DeferredBlock<SlabBlock> UMBERTILE_SMOOTH_SMALL;

    static {
        ASPER = registerSlab("slab_planks_asper", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.WOOD));
        BAOBAB = registerSlab("slab_planks_baobab", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_WHITE));
        BALSAM = registerSlab("slab_planks_balsam", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_PINK));
        BAMBOO = registerSlab("slab_planks_bamboo", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.SAND));
        CYPRESS = registerSlab("slab_planks_cypress", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_WHITE));
        EUCALYPTUS = registerSlab("slab_planks_eucalyptus", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_PINK));
        MAHOGANY = registerSlab("slab_planks_mahogany", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.COLOR_BROWN));
        MARSHWOOD = registerSlab("slab_planks_marshwood", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_GREEN));
        MOSSBARK = registerSlab("slab_planks_mossbark", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.COLOR_BROWN));
        PETRIFIED = registerSlab("slab_planks_petrified", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_BROWN));
        ROTTEN = registerSlab("slab_planks_rotten", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.COLOR_BLACK));
        SCORCHED = registerSlab("slab_planks_scorched", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.COLOR_BLACK));
        VARNISHED = registerSlab("slab_planks_varnished", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.WOOD));
        WHITE = registerSlab("slab_planks_white", SlabBlockProperties.SLAB_PLANKS.mapColor(MapColor.TERRACOTTA_WHITE));

        AMBER = registerSlab("slab_amber", SlabBlockProperties.SLAB_AMBER);
        AMBER_BRICKS = registerSlab("slab_amber_bricks", SlabBlockProperties.SLAB_AMBER_BRICKS);
        MIR_BRICKS = registerSlab("slab_mir_bricks", SlabBlockProperties.SLAB_STONE);
        MUD_BRICKS = registerSlab("slab_mud_bricks", SlabBlockProperties.SLAB_STONE);
        UMBERCOBBLE = registerSlab("slab_umbercobble", SlabBlockProperties.SLAB_STONE);
        UMBERCOBBLE_MOSSY = registerSlab("slab_umbercobble_mossy", SlabBlockProperties.SLAB_STONE);
        UMBERCOBBLE_WEBBED = registerSlab("slab_umbercobble_webbed", SlabBlockProperties.SLAB_STONE);
        UMBERPAVER = registerSlab("slab_umberpaver", SlabBlockProperties.SLAB_STONE);
        UMBERPAVER_MOSSY = registerSlab("slab_umberpaver_mossy", SlabBlockProperties.SLAB_STONE);
        UMBERPAVER_WEBBED = registerSlab("slab_umberpaver_webbed", SlabBlockProperties.SLAB_STONE);
        UMBERSTONE = registerSlab("slab_umberstone", SlabBlockProperties.SLAB_STONE);
        UMBERSTONE_BRICKS = registerSlab("slab_umberstone_bricks", SlabBlockProperties.SLAB_STONE);
        UMBERTILE_SMOOTH = registerSlab("slab_umberstone_smooth", SlabBlockProperties.SLAB_STONE);
        UMBERTILE_SMOOTH_SMALL = registerSlab("slab_umberstone_smooth_small", SlabBlockProperties.SLAB_STONE);
    }

    public static void init() {
    }
}
