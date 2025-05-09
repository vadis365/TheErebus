package erebus.registries.blocks.providers;

import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
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
        SLAB_PLANKS_BAOBAB = registerSlab("slab_planks_baobab", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_EUCALYPTUS = registerSlab("slab_planks_eucalyptus", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_MAHOGANY = registerSlab("slab_planks_mahogany", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_MOSSBARK = registerSlab("slab_planks_mossbark", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_ASPER = registerSlab("slab_planks_asper", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_CYPRESS = registerSlab("slab_planks_cypress", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_BALSAM = registerSlab("slab_planks_balsam", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_WHITE = registerSlab("slab_planks_white", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_BAMBOO = registerSlab("slab_planks_bamboo", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_ROTTEN = registerSlab("slab_planks_rotten", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_MARSHWOOD = registerSlab("slab_planks_marshwood", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_SCORCHED = registerSlab("slab_planks_scorched", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_VARNISHED = registerSlab("slab_planks_varnished", Properties.ofFullCopy(Blocks.OAK_SLAB));
        SLAB_PLANKS_PETRIFIED = registerSlab("slab_planks_petrified", Properties.ofFullCopy(Blocks.OAK_SLAB));

        SLAB_UMBERSTONE = registerSlab("slab_umberstone", Properties.ofFullCopy(Blocks.STONE_SLAB));
        SLAB_UMBERCOBBLE = registerSlab("slab_umbercobble", Properties.ofFullCopy(Blocks.STONE_SLAB));
        SLAB_UMBERCOBBLE_MOSSY = registerSlab("slab_umbercobble_mossy", Properties.ofFullCopy(Blocks.STONE_SLAB));
        SLAB_UMBERCOBBLE_WEBBED = registerSlab("slab_umbercobble_webbed", Properties.ofFullCopy(Blocks.STONE_SLAB));
        SLAB_UMBERSTONE_BRICKS = registerSlab("slab_umberstone_bricks", Properties.ofFullCopy(Blocks.STONE_SLAB));
        SLAB_UMBERTILE_SMOOTH = registerSlab("slab_umberstone_smooth", Properties.ofFullCopy(Blocks.STONE_SLAB));
        SLAB_UMBERTILE_SMOOTH_SMALL = registerSlab("slab_umberstone_smooth_small", Properties.ofFullCopy(Blocks.STONE_SLAB));
        SLAB_UMBERPAVER = registerSlab("slab_umberpaver", Properties.ofFullCopy(Blocks.STONE_SLAB));
        SLAB_UMBERPAVER_MOSSY = registerSlab("slab_umberpaver_mossy", Properties.ofFullCopy(Blocks.STONE_SLAB));
        SLAB_UMBERPAVER_WEBBED = registerSlab("slab_umberpaver_webbed", Properties.ofFullCopy(Blocks.STONE_SLAB));
        SLAB_AMBER = registerSlab("slab_amber", Properties.ofFullCopy(Blocks.GLASS).strength(1.5F).noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false).sound(SoundType.GLASS).mapColor(MapColor.GOLD));
        SLAB_AMBER_BRICKS = registerSlab("slab_amber_bricks", Properties.ofFullCopy(Blocks.GLASS).strength(1.5F).noOcclusion().isViewBlocking((blockState, blockGetter, blockPos) -> false).sound(SoundType.GLASS).mapColor(MapColor.GOLD));
        SLAB_MUD_BRICKS = registerSlab("slab_mud_bricks", Properties.ofFullCopy(Blocks.STONE_SLAB));
        SLAB_MIR_BRICKS = registerSlab("slab_mir_bricks", Properties.ofFullCopy(Blocks.STONE_SLAB));
    }

    public static void init() {
    }
}
