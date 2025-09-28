package erebus.registries.blocks.properties;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class FenceBlockProperties {
    
    // MARK: Fences
    public static final Properties FENCE_ASPER;
    public static final Properties FENCE_BAMBOO;
    public static final Properties FENCE_BAOBAB;
    public static final Properties FENCE_BALSAM;
    public static final Properties FENCE_CYPRESS;
    public static final Properties FENCE_EUCALYPTUS;
    public static final Properties FENCE_MAHOGANY;
    public static final Properties FENCE_MARSHWOOD;
    public static final Properties FENCE_MOSSBARK;
    public static final Properties FENCE_ROTTEN;
    public static final Properties FENCE_SCORCHED;
    public static final Properties FENCE_VARNISHED;
    public static final Properties FENCE_WHITE;

    // MARK: Fence Gates
    public static final Properties FENCE_GATE_ASPER;
    public static final Properties FENCE_GATE_BALSAM;
    public static final Properties FENCE_GATE_BAMBOO;
    public static final Properties FENCE_GATE_BAOBAB;
    public static final Properties FENCE_GATE_CYPRESS;
    public static final Properties FENCE_GATE_EUCALYPTUS;
    public static final Properties FENCE_GATE_MAHOGANY;
    public static final Properties FENCE_GATE_MARSHWOOD;
    public static final Properties FENCE_GATE_MOSSBARK;
    public static final Properties FENCE_GATE_ROTTEN;
    public static final Properties FENCE_GATE_SCORCHED;
    public static final Properties FENCE_GATE_VARNISHED;
    public static final Properties FENCE_GATE_WHITE;

    private static final Properties DEFAULT_FENCE = Properties.of()
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava();

    private static final Properties DEFAULT_FENCE_GATE = Properties.of()
            .forceSolidOn()
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .ignitedByLava();
    
    static {
        FENCE_ASPER = DEFAULT_FENCE.mapColor(MapColor.WOOD);
        FENCE_BALSAM = DEFAULT_FENCE.mapColor(MapColor.TERRACOTTA_PINK);
        FENCE_BAMBOO = DEFAULT_FENCE.mapColor(MapColor.SAND);
        FENCE_BAOBAB = DEFAULT_FENCE.mapColor(MapColor.TERRACOTTA_WHITE);
        FENCE_CYPRESS = DEFAULT_FENCE.mapColor(MapColor.TERRACOTTA_WHITE);
        FENCE_EUCALYPTUS = DEFAULT_FENCE.mapColor(MapColor.TERRACOTTA_PINK);
        FENCE_MAHOGANY = DEFAULT_FENCE.mapColor(MapColor.COLOR_BROWN);
        FENCE_MARSHWOOD = DEFAULT_FENCE.mapColor(MapColor.TERRACOTTA_GREEN);
        FENCE_MOSSBARK = DEFAULT_FENCE.mapColor(MapColor.COLOR_BROWN);
        FENCE_ROTTEN = DEFAULT_FENCE.mapColor(MapColor.COLOR_BLACK);
        FENCE_SCORCHED = DEFAULT_FENCE.mapColor(MapColor.COLOR_BLACK);
        FENCE_VARNISHED = DEFAULT_FENCE.mapColor(MapColor.WOOD);
        FENCE_WHITE = DEFAULT_FENCE.mapColor(MapColor.TERRACOTTA_WHITE);

        FENCE_GATE_ASPER = DEFAULT_FENCE_GATE.mapColor(MapColor.WOOD);
        FENCE_GATE_BALSAM = DEFAULT_FENCE_GATE.mapColor(MapColor.TERRACOTTA_PINK);
        FENCE_GATE_BAMBOO = DEFAULT_FENCE_GATE.mapColor(MapColor.SAND);
        FENCE_GATE_BAOBAB = DEFAULT_FENCE_GATE.mapColor(MapColor.TERRACOTTA_WHITE);
        FENCE_GATE_CYPRESS = DEFAULT_FENCE_GATE.mapColor(MapColor.TERRACOTTA_WHITE);
        FENCE_GATE_EUCALYPTUS = DEFAULT_FENCE_GATE.mapColor(MapColor.TERRACOTTA_PINK);
        FENCE_GATE_MAHOGANY = DEFAULT_FENCE_GATE.mapColor(MapColor.COLOR_BROWN);
        FENCE_GATE_MARSHWOOD = DEFAULT_FENCE_GATE.mapColor(MapColor.TERRACOTTA_GREEN);
        FENCE_GATE_MOSSBARK = DEFAULT_FENCE_GATE.mapColor(MapColor.COLOR_BROWN);
        FENCE_GATE_ROTTEN = DEFAULT_FENCE_GATE.mapColor(MapColor.COLOR_BLACK);
        FENCE_GATE_SCORCHED = DEFAULT_FENCE_GATE.mapColor(MapColor.COLOR_BLACK);
        FENCE_GATE_VARNISHED = DEFAULT_FENCE_GATE.mapColor(MapColor.WOOD);
        FENCE_GATE_WHITE = DEFAULT_FENCE_GATE.mapColor(MapColor.TERRACOTTA_WHITE);
    }
}
