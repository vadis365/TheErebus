package erebus.registries.blocks.providers;

import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredBlock;

public class FenceBlocks extends ModBlockHelpers {

    // MARK: Fences
    public static final DeferredBlock<FenceBlock> FENCE_ASPER;
    public static final DeferredBlock<FenceBlock> FENCE_BAMBOO;
    public static final DeferredBlock<FenceBlock> FENCE_BAOBAB;
    public static final DeferredBlock<FenceBlock> FENCE_BALSAM;
    public static final DeferredBlock<FenceBlock> FENCE_CYPRESS;
    public static final DeferredBlock<FenceBlock> FENCE_EUCALYPTUS;
    public static final DeferredBlock<FenceBlock> FENCE_MAHOGANY;
    public static final DeferredBlock<FenceBlock> FENCE_MARSHWOOD;
    public static final DeferredBlock<FenceBlock> FENCE_MOSSBARK;
    public static final DeferredBlock<FenceBlock> FENCE_ROTTEN;
    public static final DeferredBlock<FenceBlock> FENCE_SCORCHED;
    public static final DeferredBlock<FenceBlock> FENCE_VARNISHED;
    public static final DeferredBlock<FenceBlock> FENCE_WHITE;

    // MARK: Fence Gates
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_ASPER;
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_BALSAM;
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_BAMBOO;
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_BAOBAB;
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_CYPRESS;
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_EUCALYPTUS;
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_MAHOGANY;
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_MARSHWOOD;
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_MOSSBARK;
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_ROTTEN;
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_SCORCHED;
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_VARNISHED;
    public static final DeferredBlock<FenceGateBlock> FENCE_GATE_WHITE;

    static {
        FENCE_ASPER = registerFence("fence_asper", Properties.ofFullCopy(Blocks.OAK_FENCE));
        FENCE_BALSAM = registerFence("fence_balsam", Properties.ofFullCopy(Blocks.OAK_FENCE));
        FENCE_BAMBOO = registerFence("fence_bamboo", Properties.ofFullCopy(Blocks.OAK_FENCE));
        FENCE_BAOBAB = registerFence("fence_baobab", Properties.ofFullCopy(Blocks.OAK_FENCE));
        FENCE_CYPRESS = registerFence("fence_cypress", Properties.ofFullCopy(Blocks.OAK_FENCE));
        FENCE_EUCALYPTUS = registerFence("fence_eucalyptus", Properties.ofFullCopy(Blocks.OAK_FENCE));
        FENCE_MAHOGANY = registerFence("fence_mahogany", Properties.ofFullCopy(Blocks.OAK_FENCE));
        FENCE_MARSHWOOD = registerFence("fence_marshwood", Properties.ofFullCopy(Blocks.OAK_FENCE));
        FENCE_MOSSBARK = registerFence("fence_mossbark", Properties.ofFullCopy(Blocks.OAK_FENCE));
        FENCE_ROTTEN = registerFence("fence_rotten", Properties.ofFullCopy(Blocks.OAK_FENCE));
        FENCE_SCORCHED = registerFence("fence_scorched", Properties.ofFullCopy(Blocks.OAK_FENCE));
        FENCE_VARNISHED = registerFence("fence_varnished", Properties.ofFullCopy(Blocks.OAK_FENCE));
        FENCE_WHITE = registerFence("fence_white", Properties.ofFullCopy(Blocks.OAK_FENCE));

        FENCE_GATE_ASPER = registerSimpleFenceGate("fence_gate_asper");
        FENCE_GATE_BALSAM = registerSimpleFenceGate("fence_gate_balsam");
        FENCE_GATE_BAMBOO = registerSimpleFenceGate("fence_gate_bamboo");
        FENCE_GATE_BAOBAB = registerSimpleFenceGate("fence_gate_baobab");
        FENCE_GATE_CYPRESS = registerSimpleFenceGate("fence_gate_cypress");
        FENCE_GATE_EUCALYPTUS = registerSimpleFenceGate("fence_gate_eucalyptus");
        FENCE_GATE_MAHOGANY = registerSimpleFenceGate("fence_gate_mahogany");
        FENCE_GATE_MARSHWOOD = registerSimpleFenceGate("fence_gate_marshwood");
        FENCE_GATE_MOSSBARK = registerSimpleFenceGate("fence_gate_mossbark");
        FENCE_GATE_ROTTEN = registerSimpleFenceGate("fence_gate_rotten");
        FENCE_GATE_SCORCHED = registerSimpleFenceGate("fence_gate_scorched");
        FENCE_GATE_VARNISHED = registerSimpleFenceGate("fence_gate_varnished");
        FENCE_GATE_WHITE = registerSimpleFenceGate("fence_gate_white");
    }

    public static void init() {
    }
}
