package erebus.registries.blocks.providers;

import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public class UmberstoneBlocks extends ModBlockHelpers {
    public static final DeferredBlock<Block> UMBERSTONE;
    public static final DeferredBlock<Block> UMBERSTONE_BRICKS;
    public static final DeferredBlock<Block> UMBERCOBBLE;
    public static final DeferredBlock<Block> UMBERCOBBLE_MOSSY;
    public static final DeferredBlock<Block> UMBERCOBBLE_WEBBED;
    public static final DeferredBlock<Block> UMBERTILE_SMOOTH;
    public static final DeferredBlock<Block> UMBERTILE_SMOOTH_SMALL;
    public static final DeferredBlock<Block> UMBERGRAVEL;
    public static final DeferredBlock<Block> UMBERPAVER;
    public static final DeferredBlock<Block> UMBERPAVER_MOSSY;
    public static final DeferredBlock<Block> UMBERPAVER_WEBBED;
    public static final DeferredBlock<RotatedPillarBlock> UMBERSTONE_PILLAR;
    public static final DeferredBlock<Block> VOLCANIC_ROCK;
    public static final DeferredBlock<Block> DUST;
    public static final DeferredBlock<Block> DUST_LAYER;
    public static final DeferredBlock<RotatedPillarBlock> PETRIFIED_WOOD_ROCK;
    public static final DeferredBlock<RotatedPillarBlock> PETRIFIED_WOOD_ROCK_2;
    public static final DeferredBlock<RotatedPillarBlock> PETRIFIED_WOOD_ROCK_3;
    public static final DeferredBlock<RotatedPillarBlock> PETRIFIED_WOOD_ROCK_4;
    public static final DeferredBlock<RotatedPillarBlock> PETRIFIED_WOOD_ROCK_5;
    public static final DeferredBlock<RotatedPillarBlock> PETRIFIED_WOOD_ROCK_6;
    public static final DeferredBlock<Block> PETRIFIED_BARK_RED;
    public static final DeferredBlock<Block> PETRIFIED_BARK_BROWN;
    public static final DeferredBlock<Block> PETRIFIED_LOG_INNER;
    public static final DeferredBlock<Block> DUNG;
    public static final DeferredBlock<Block> MIR_BRICKS;
    public static final DeferredBlock<Block> MUD_BRICKS;

    static {

        UMBERSTONE = registerSimpleBlock("umberstone", Properties.of().strength(1.5F, 10.0F).requiresCorrectToolForDrops());
        UMBERSTONE_BRICKS = registerSimpleBlock("umberstone_bricks", Properties.ofFullCopy(Blocks.STONE_BRICKS));
        UMBERCOBBLE = registerSimpleBlock("umbercobble", Properties.ofFullCopy(Blocks.COBBLESTONE));
        UMBERCOBBLE_MOSSY = registerSimpleBlock("umbercobble_mossy", Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE));
        UMBERCOBBLE_WEBBED = registerSimpleBlock("umbercobble_webbed", Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE));
        UMBERTILE_SMOOTH = registerSimpleBlock("umbertile_smooth", Properties.ofFullCopy(Blocks.SMOOTH_STONE));
        UMBERTILE_SMOOTH_SMALL = registerSimpleBlock("umbertile_smooth_small", Properties.ofFullCopy(Blocks.SMOOTH_STONE));
        UMBERGRAVEL = registerSimpleBlock("umbergravel", Properties.ofFullCopy(Blocks.GRAVEL));
        UMBERPAVER = registerSimpleBlock("umberpaver", Properties.ofFullCopy(Blocks.STONE));
        UMBERPAVER_MOSSY = registerSimpleBlock("umberpaver_mossy", Properties.ofFullCopy(Blocks.STONE));
        UMBERPAVER_WEBBED = registerSimpleBlock("umberpaver_webbed", Properties.ofFullCopy(Blocks.STONE));
        UMBERSTONE_PILLAR = registerBlock("umberstone_pillar", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.STONE)));
        VOLCANIC_ROCK = registerSimpleBlock("volcanic_rock", Properties.of().mapColor(MapColor.STONE));
        DUST = registerSimpleBlock("dust", Properties.of().mapColor(MapColor.STONE));
        DUST_LAYER = registerBlock("dust_layer", () -> new SnowLayerBlock(Properties.ofFullCopy(Blocks.SNOW)));
        PETRIFIED_WOOD_ROCK = registerBlock("petrified_wood_rock", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.STONE)));
        PETRIFIED_WOOD_ROCK_2 = registerBlock("petrified_wood_rock_2", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.STONE)));
        PETRIFIED_WOOD_ROCK_3 = registerBlock("petrified_wood_rock_3", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.STONE)));
        PETRIFIED_WOOD_ROCK_4 = registerBlock("petrified_wood_rock_4", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.STONE)));
        PETRIFIED_WOOD_ROCK_5 = registerBlock("petrified_wood_rock_5", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.STONE)));
        PETRIFIED_WOOD_ROCK_6 = registerBlock("petrified_wood_rock_6", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.STONE)));
        PETRIFIED_BARK_RED = registerBlock("petrified_bark_red", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        PETRIFIED_BARK_BROWN = registerBlock("petrified_bark_brown", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        PETRIFIED_LOG_INNER = registerSimpleBlock("petrified_log_inner", Properties.of().mapColor(MapColor.STONE));
        DUNG = registerSimpleBlock("dung", Properties.of().mapColor(MapColor.STONE));
        MIR_BRICKS = registerSimpleBlock("mir_bricks", Properties.of().mapColor(MapColor.STONE));
        MUD_BRICKS = registerSimpleBlock("mud_bricks", Properties.of().mapColor(MapColor.STONE));
    }

    public static void init() {
    }
}
