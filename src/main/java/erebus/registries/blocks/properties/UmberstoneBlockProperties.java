package erebus.registries.blocks.properties;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.material.MapColor;

public class UmberstoneBlockProperties {

    public static final Properties UMBERSTONE;
    public static final Properties UMBERSTONE_BRICKS;
    public static final Properties UMBERCOBBLE;
    public static final Properties UMBERCOBBLE_MOSSY;
    public static final Properties UMBERCOBBLE_WEBBED;
    public static final Properties UMBERTILE_SMOOTH;
    public static final Properties UMBERTILE_SMOOTH_SMALL;
    public static final Properties UMBERGRAVEL;
    public static final Properties UMBERPAVER;
    public static final Properties UMBERPAVER_MOSSY;
    public static final Properties UMBERPAVER_WEBBED;
    public static final Properties UMBERSTONE_PILLAR;
    public static final Properties VOLCANIC_ROCK;
    public static final Properties DUST;
    public static final Properties DUST_LAYER;
    public static final Properties PETRIFIED_WOOD_ROCK;
    public static final Properties PETRIFIED_WOOD_ROCK_2;
    public static final Properties PETRIFIED_WOOD_ROCK_3;
    public static final Properties PETRIFIED_WOOD_ROCK_4;
    public static final Properties PETRIFIED_WOOD_ROCK_5;
    public static final Properties PETRIFIED_WOOD_ROCK_6;
    public static final Properties PETRIFIED_BARK_RED;
    public static final Properties PETRIFIED_BARK_BROWN;
    public static final Properties PETRIFIED_LOG_INNER;
    public static final Properties DUNG;
    public static final Properties MIR_BRICKS;
    public static final Properties MUD_BRICKS;

    static {
        UMBERSTONE = Properties.of().strength(1.5F, 10.0F).requiresCorrectToolForDrops();
        UMBERSTONE_BRICKS = Properties.ofFullCopy(Blocks.STONE_BRICKS);
        UMBERCOBBLE = Properties.ofFullCopy(Blocks.COBBLESTONE);
        UMBERCOBBLE_MOSSY = Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE);
        UMBERCOBBLE_WEBBED = Properties.ofFullCopy(Blocks.MOSSY_COBBLESTONE);
        UMBERTILE_SMOOTH = Properties.ofFullCopy(Blocks.SMOOTH_STONE);
        UMBERTILE_SMOOTH_SMALL = Properties.ofFullCopy(Blocks.SMOOTH_STONE);
        UMBERGRAVEL = Properties.ofFullCopy(Blocks.GRAVEL);
        UMBERPAVER = Properties.ofFullCopy(Blocks.STONE);
        UMBERPAVER_MOSSY = Properties.ofFullCopy(Blocks.STONE);
        UMBERPAVER_WEBBED = Properties.ofFullCopy(Blocks.STONE);
        UMBERSTONE_PILLAR = Properties.ofFullCopy(Blocks.STONE);
        VOLCANIC_ROCK = Properties.of().mapColor(MapColor.STONE);
        DUST = Properties.of().mapColor(MapColor.STONE);
        DUST_LAYER = Properties.ofFullCopy(Blocks.SNOW);
        PETRIFIED_WOOD_ROCK = Properties.ofFullCopy(Blocks.STONE);
        PETRIFIED_WOOD_ROCK_2 = Properties.ofFullCopy(Blocks.STONE);
        PETRIFIED_WOOD_ROCK_3 = Properties.ofFullCopy(Blocks.STONE);
        PETRIFIED_WOOD_ROCK_4 = Properties.ofFullCopy(Blocks.STONE);
        PETRIFIED_WOOD_ROCK_5 = Properties.ofFullCopy(Blocks.STONE);
        PETRIFIED_WOOD_ROCK_6 = Properties.ofFullCopy(Blocks.STONE);
        PETRIFIED_BARK_RED = Properties.ofFullCopy(Blocks.OAK_LOG);
        PETRIFIED_BARK_BROWN = Properties.ofFullCopy(Blocks.OAK_LOG);
        PETRIFIED_LOG_INNER = Properties.of().mapColor(MapColor.STONE);
        DUNG = Properties.of().mapColor(MapColor.STONE);
        MIR_BRICKS = Properties.of().mapColor(MapColor.STONE);
        MUD_BRICKS = Properties.of().mapColor(MapColor.STONE);
    }
}
