package erebus.registries.blocks.providers;

import erebus.block.util.ModWoodTypes;
import erebus.registries.blocks.properties.FenceBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
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
        FENCE_ASPER = registerFence("fence_asper", FenceBlockProperties.FENCE_ASPER);
        FENCE_BALSAM = registerFence("fence_balsam", FenceBlockProperties.FENCE_BALSAM);
        FENCE_BAMBOO = registerFence("fence_bamboo", FenceBlockProperties.FENCE_BAMBOO);
        FENCE_BAOBAB = registerFence("fence_baobab", FenceBlockProperties.FENCE_BAOBAB);
        FENCE_CYPRESS = registerFence("fence_cypress", FenceBlockProperties.FENCE_CYPRESS);
        FENCE_EUCALYPTUS = registerFence("fence_eucalyptus", FenceBlockProperties.FENCE_EUCALYPTUS);
        FENCE_MAHOGANY = registerFence("fence_mahogany", FenceBlockProperties.FENCE_MAHOGANY);
        FENCE_MARSHWOOD = registerFence("fence_marshwood", FenceBlockProperties.FENCE_MARSHWOOD);
        FENCE_MOSSBARK = registerFence("fence_mossbark", FenceBlockProperties.FENCE_MOSSBARK);
        FENCE_ROTTEN = registerFence("fence_rotten", FenceBlockProperties.FENCE_ROTTEN);
        FENCE_SCORCHED = registerFence("fence_scorched", FenceBlockProperties.FENCE_SCORCHED);
        FENCE_VARNISHED = registerFence("fence_varnished", FenceBlockProperties.FENCE_VARNISHED);
        FENCE_WHITE = registerFence("fence_white", FenceBlockProperties.FENCE_WHITE);

        FENCE_GATE_ASPER = registerFenceGate("fence_gate_asper", ModWoodTypes.ASPER, FenceBlockProperties.FENCE_GATE_ASPER);
        FENCE_GATE_BALSAM = registerFenceGate("fence_gate_balsam", ModWoodTypes.BALSAM, FenceBlockProperties.FENCE_GATE_BALSAM);
        FENCE_GATE_BAMBOO = registerFenceGate("fence_gate_bamboo", ModWoodTypes.BAMBOO, FenceBlockProperties.FENCE_GATE_BAMBOO);
        FENCE_GATE_BAOBAB = registerFenceGate("fence_gate_baobab", ModWoodTypes.BAOBAB, FenceBlockProperties.FENCE_GATE_BAOBAB);
        FENCE_GATE_CYPRESS = registerFenceGate("fence_gate_cypress", ModWoodTypes.CYPRESS, FenceBlockProperties.FENCE_GATE_CYPRESS);
        FENCE_GATE_EUCALYPTUS = registerFenceGate("fence_gate_eucalyptus", ModWoodTypes.EUCALYPTUS, FenceBlockProperties.FENCE_GATE_EUCALYPTUS);
        FENCE_GATE_MAHOGANY = registerFenceGate("fence_gate_mahogany", ModWoodTypes.MAHOGANY, FenceBlockProperties.FENCE_GATE_MAHOGANY);
        FENCE_GATE_MARSHWOOD = registerFenceGate("fence_gate_marshwood", ModWoodTypes.MARSHWOOD, FenceBlockProperties.FENCE_GATE_MARSHWOOD);
        FENCE_GATE_MOSSBARK = registerFenceGate("fence_gate_mossbark", ModWoodTypes.MOSSBARK, FenceBlockProperties.FENCE_GATE_MOSSBARK);
        FENCE_GATE_ROTTEN = registerFenceGate("fence_gate_rotten", ModWoodTypes.ROTTEN, FenceBlockProperties.FENCE_GATE_ROTTEN);
        FENCE_GATE_SCORCHED = registerFenceGate("fence_gate_scorched", ModWoodTypes.SCORCHED, FenceBlockProperties.FENCE_GATE_SCORCHED);
        FENCE_GATE_VARNISHED = registerFenceGate("fence_gate_varnished", ModWoodTypes.VARNISHED, FenceBlockProperties.FENCE_GATE_VARNISHED);
        FENCE_GATE_WHITE = registerFenceGate("fence_gate_white", ModWoodTypes.WHITE, FenceBlockProperties.FENCE_GATE_WHITE);
    }

    public static void init() {
    }
}
