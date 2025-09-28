package erebus.registries.blocks.properties;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class StairBlockProperties {

    // MARK: Stairs Wood
    public static final Properties ASPER;
    public static final Properties BAMBOO;
    public static final Properties BALSAM;
    public static final Properties BAOBAB;
    public static final Properties CYPRESS;
    public static final Properties EUCALYPTUS;
    public static final Properties MAHOGANY;
    public static final Properties MARSHWOOD;
    public static final Properties MOSSBARK;
    public static final Properties PETRIFIED;
    public static final Properties ROTTEN;
    public static final Properties SCORCHED;
    public static final Properties VARNISHED;
    public static final Properties WHITE;

    // MARK: Stairs Stone
    public static final Properties AMBER;
    public static final Properties AMBER_BRICKS;
    public static final Properties MIR_BRICKS;
    public static final Properties MUD_BRICKS;
    public static final Properties UMBERCOBBLE;
    public static final Properties UMBERCOBBLE_MOSSY;
    public static final Properties UMBERCOBBLE_WEBBED;
    public static final Properties UMBERPAVER;
    public static final Properties UMBERPAVER_MOSSY;
    public static final Properties UMBERPAVER_WEBBED;
    public static final Properties UMBERSTONE;
    public static final Properties UMBERSTONE_BRICKS;
    public static final Properties UMBERTILE_SMOOTH;
    public static final Properties UMBERTILE_SMOOTH_SMALL;

    private static final Properties WOOD_STAIRS = Properties.of()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava();

    private static final Properties STONE_STAIRS = Properties.of()
            .mapColor(MapColor.STONE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresCorrectToolForDrops()
            .strength(2.0F, 6.0F);


    static {
        // Wood
        ASPER = WOOD_STAIRS.mapColor(MapColor.WOOD);
        BAMBOO = WOOD_STAIRS.mapColor(MapColor.SAND);
        BALSAM = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_PINK);
        BAOBAB = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_WHITE);
        CYPRESS = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_WHITE);
        EUCALYPTUS = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_PINK);
        MAHOGANY = WOOD_STAIRS.mapColor(MapColor.COLOR_BROWN);
        MARSHWOOD = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_GREEN);
        MOSSBARK = WOOD_STAIRS.mapColor(MapColor.COLOR_BROWN);
        PETRIFIED = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_BROWN);
        ROTTEN = WOOD_STAIRS.mapColor(MapColor.COLOR_BLACK);
        SCORCHED = WOOD_STAIRS.mapColor(MapColor.COLOR_BLACK);
        VARNISHED = WOOD_STAIRS.mapColor(MapColor.WOOD);
        WHITE = WOOD_STAIRS.mapColor(MapColor.TERRACOTTA_WHITE);

        // Stone-like
        AMBER = STONE_STAIRS;
        AMBER_BRICKS = STONE_STAIRS;
        MIR_BRICKS = STONE_STAIRS;
        MUD_BRICKS = STONE_STAIRS;
        UMBERCOBBLE = STONE_STAIRS;
        UMBERCOBBLE_MOSSY = STONE_STAIRS;
        UMBERCOBBLE_WEBBED = STONE_STAIRS;
        UMBERPAVER = STONE_STAIRS;
        UMBERPAVER_MOSSY = STONE_STAIRS;
        UMBERPAVER_WEBBED = STONE_STAIRS;
        UMBERSTONE = STONE_STAIRS;
        UMBERSTONE_BRICKS = STONE_STAIRS;
        UMBERTILE_SMOOTH = STONE_STAIRS;
        UMBERTILE_SMOOTH_SMALL = STONE_STAIRS;
    }
}
