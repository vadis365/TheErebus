package erebus.registries.blocks.properties;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

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
    
    static {
        FENCE_ASPER = Properties.ofFullCopy(Blocks.OAK_FENCE);
        FENCE_BALSAM = Properties.ofFullCopy(Blocks.OAK_FENCE);
        FENCE_BAMBOO = Properties.ofFullCopy(Blocks.OAK_FENCE);
        FENCE_BAOBAB = Properties.ofFullCopy(Blocks.OAK_FENCE);
        FENCE_CYPRESS = Properties.ofFullCopy(Blocks.OAK_FENCE);
        FENCE_EUCALYPTUS = Properties.ofFullCopy(Blocks.OAK_FENCE);
        FENCE_MAHOGANY = Properties.ofFullCopy(Blocks.OAK_FENCE);
        FENCE_MARSHWOOD = Properties.ofFullCopy(Blocks.OAK_FENCE);
        FENCE_MOSSBARK = Properties.ofFullCopy(Blocks.OAK_FENCE);
        FENCE_ROTTEN = Properties.ofFullCopy(Blocks.OAK_FENCE);
        FENCE_SCORCHED = Properties.ofFullCopy(Blocks.OAK_FENCE);
        FENCE_VARNISHED = Properties.ofFullCopy(Blocks.OAK_FENCE);
        FENCE_WHITE = Properties.ofFullCopy(Blocks.OAK_FENCE);

        FENCE_GATE_ASPER = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
        FENCE_GATE_BALSAM = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
        FENCE_GATE_BAMBOO = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
        FENCE_GATE_BAOBAB = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
        FENCE_GATE_CYPRESS = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
        FENCE_GATE_EUCALYPTUS = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
        FENCE_GATE_MAHOGANY = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
        FENCE_GATE_MARSHWOOD = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
        FENCE_GATE_MOSSBARK = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
        FENCE_GATE_ROTTEN = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
        FENCE_GATE_SCORCHED = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
        FENCE_GATE_VARNISHED = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
        FENCE_GATE_WHITE = Properties.ofFullCopy(Blocks.OAK_FENCE_GATE);
    }
}
