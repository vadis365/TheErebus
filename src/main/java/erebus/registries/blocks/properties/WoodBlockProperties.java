package erebus.registries.blocks.properties;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class WoodBlockProperties {

    // Logs
    public static final Properties LOG;
    public static final Properties LOG_HOLLOW;

    // Leaves
    public static final Properties LEAVES;

    // Planks
    public static final Properties PLANKS;

    static {
        LOG = Properties.ofFullCopy(Blocks.OAK_LOG);
        LOG_HOLLOW = Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion();
        LEAVES = Properties.ofFullCopy(Blocks.OAK_LEAVES);
        PLANKS = Properties.ofFullCopy(Blocks.OAK_PLANKS);
    }
}
