package erebus.registries.blocks.properties;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class DoorBlockProperties {

    public static final Properties ASPER;
    public static final Properties BALSAM;
    public static final Properties BAOBAB;
    public static final Properties CYPRESS;
    public static final Properties EUCALYPTUS;
    public static final Properties MAHOGANY;
    public static final Properties MARSHWOOD;
    public static final Properties MOSSBARK;
    public static final Properties ROTTEN;
    public static final Properties SCORCHED;
    public static final Properties WHITE;

    private static final Properties DEFAULT_DOOR = Properties.of()
            .instrument(NoteBlockInstrument.BASS)
            .strength(3.0F)
            .noOcclusion()
            .ignitedByLava()
            .pushReaction(PushReaction.DESTROY);

    static {
        ASPER = DEFAULT_DOOR.mapColor(MapColor.WOOD);
        BALSAM = DEFAULT_DOOR.mapColor(MapColor.TERRACOTTA_PINK);
        BAOBAB = DEFAULT_DOOR.mapColor(MapColor.TERRACOTTA_WHITE);
        CYPRESS = DEFAULT_DOOR.mapColor(MapColor.TERRACOTTA_WHITE);
        EUCALYPTUS = DEFAULT_DOOR.mapColor(MapColor.TERRACOTTA_PINK);
        MAHOGANY = DEFAULT_DOOR.mapColor(MapColor.COLOR_BROWN);
        MARSHWOOD = DEFAULT_DOOR.mapColor(MapColor.TERRACOTTA_GREEN);
        MOSSBARK = DEFAULT_DOOR.mapColor(MapColor.COLOR_BROWN);
        ROTTEN = DEFAULT_DOOR.mapColor(MapColor.COLOR_BLACK);
        SCORCHED = DEFAULT_DOOR.mapColor(MapColor.COLOR_BLACK);
        WHITE = DEFAULT_DOOR.mapColor(MapColor.TERRACOTTA_WHITE);
    }
}
