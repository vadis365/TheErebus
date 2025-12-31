package erebus.registries.blocks.providers;

import erebus.registries.blocks.properties.UmberstoneBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SnowLayerBlock;
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
        UMBERSTONE = registerSimpleBlock("umberstone", UmberstoneBlockProperties.UMBERSTONE);
        UMBERSTONE_BRICKS = registerSimpleBlock("umberstone_bricks", UmberstoneBlockProperties.UMBERSTONE_BRICKS);
        UMBERCOBBLE = registerSimpleBlock("umbercobble", UmberstoneBlockProperties.UMBERCOBBLE);
        UMBERCOBBLE_MOSSY = registerSimpleBlock("umbercobble_mossy", UmberstoneBlockProperties.UMBERCOBBLE_MOSSY);
        UMBERCOBBLE_WEBBED = registerSimpleBlock("umbercobble_webbed", UmberstoneBlockProperties.UMBERCOBBLE_WEBBED);
        UMBERTILE_SMOOTH = registerSimpleBlock("umbertile_smooth", UmberstoneBlockProperties.UMBERTILE_SMOOTH);
        UMBERTILE_SMOOTH_SMALL = registerSimpleBlock("umbertile_smooth_small", UmberstoneBlockProperties.UMBERTILE_SMOOTH_SMALL);
        UMBERGRAVEL = registerSimpleBlock("umbergravel", UmberstoneBlockProperties.UMBERGRAVEL);
        UMBERPAVER = registerSimpleBlock("umberpaver", UmberstoneBlockProperties.UMBERPAVER);
        UMBERPAVER_MOSSY = registerSimpleBlock("umberpaver_mossy", UmberstoneBlockProperties.UMBERPAVER_MOSSY);
        UMBERPAVER_WEBBED = registerSimpleBlock("umberpaver_webbed", UmberstoneBlockProperties.UMBERPAVER_WEBBED);
        UMBERSTONE_PILLAR = registerBlock("umberstone_pillar", () -> new RotatedPillarBlock(UmberstoneBlockProperties.UMBERSTONE_PILLAR));
        VOLCANIC_ROCK = registerSimpleBlock("volcanic_rock", UmberstoneBlockProperties.VOLCANIC_ROCK);
        DUST = registerSimpleBlock("dust", UmberstoneBlockProperties.DUST);
        DUST_LAYER = registerBlock("dust_layer", () -> new SnowLayerBlock(UmberstoneBlockProperties.DUST_LAYER));
        PETRIFIED_WOOD_ROCK = registerBlock("petrified_wood_rock", () -> new RotatedPillarBlock(UmberstoneBlockProperties.PETRIFIED_WOOD_ROCK));
        PETRIFIED_WOOD_ROCK_2 = registerBlock("petrified_wood_rock_2", () -> new RotatedPillarBlock(UmberstoneBlockProperties.PETRIFIED_WOOD_ROCK_2));
        PETRIFIED_WOOD_ROCK_3 = registerBlock("petrified_wood_rock_3", () -> new RotatedPillarBlock(UmberstoneBlockProperties.PETRIFIED_WOOD_ROCK_3));
        PETRIFIED_WOOD_ROCK_4 = registerBlock("petrified_wood_rock_4", () -> new RotatedPillarBlock(UmberstoneBlockProperties.PETRIFIED_WOOD_ROCK_4));
        PETRIFIED_WOOD_ROCK_5 = registerBlock("petrified_wood_rock_5", () -> new RotatedPillarBlock(UmberstoneBlockProperties.PETRIFIED_WOOD_ROCK_5));
        PETRIFIED_WOOD_ROCK_6 = registerBlock("petrified_wood_rock_6", () -> new RotatedPillarBlock(UmberstoneBlockProperties.PETRIFIED_WOOD_ROCK_6));
        PETRIFIED_BARK_RED = registerBlock("petrified_bark_red", () -> new RotatedPillarBlock(UmberstoneBlockProperties.PETRIFIED_BARK_RED));
        PETRIFIED_BARK_BROWN = registerBlock("petrified_bark_brown", () -> new RotatedPillarBlock(UmberstoneBlockProperties.PETRIFIED_BARK_BROWN));
        PETRIFIED_LOG_INNER = registerSimpleBlock("petrified_log_inner", UmberstoneBlockProperties.PETRIFIED_LOG_INNER);
        DUNG = registerSimpleBlock("dung", UmberstoneBlockProperties.DUNG);
        MIR_BRICKS = registerSimpleBlock("mir_bricks", UmberstoneBlockProperties.MIR_BRICKS);
        MUD_BRICKS = registerSimpleBlock("mud_bricks", UmberstoneBlockProperties.MUD_BRICKS);
    }

    public static void init() {
    }
}
