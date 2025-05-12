package erebus.registries.blocks.providers;

import erebus.registries.helpers.ModBlockHelpers;
import erebus.registries.world.tree.ModTreeGrowers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.neoforged.neoforge.registries.DeferredBlock;

public class WoodBlocks extends ModBlockHelpers {
    // Logs
    public static final DeferredBlock<RotatedPillarBlock> LOG_BAOBAB;
    public static final DeferredBlock<RotatedPillarBlock> LOG_EUCALYPTUS;
    public static final DeferredBlock<RotatedPillarBlock> LOG_MAHOGANY;
    public static final DeferredBlock<RotatedPillarBlock> LOG_MOSSBARK;
    public static final DeferredBlock<RotatedPillarBlock> LOG_ASPER;
    public static final DeferredBlock<RotatedPillarBlock> LOG_CYPRESS;
    public static final DeferredBlock<RotatedPillarBlock> LOG_BALSAM;
    public static final DeferredBlock<RotatedPillarBlock> LOG_BALSAM_RESINLESS;
    public static final DeferredBlock<RotatedPillarBlock> LOG_ROTTEN;
    public static final DeferredBlock<RotatedPillarBlock> LOG_MARSHWOOD;
    public static final DeferredBlock<RotatedPillarBlock> LOG_SCORCHED;
    public static final DeferredBlock<RotatedPillarBlock> LOG_BAMBOO;
    public static final DeferredBlock<RotatedPillarBlock> LOG_HOLLOW;

    // Saplings
    public static final DeferredBlock<SaplingBlock> SAPLING_MOSSBARK;
    public static final DeferredBlock<SaplingBlock> SAPLING_ASPER;
    public static final DeferredBlock<SaplingBlock> SAPLING_EUCALYPTUS;
    public static final DeferredBlock<SaplingBlock> SAPLING_MAHOGANY;
    public static final DeferredBlock<SaplingBlock> SAPLING_BALSAM;
    public static final DeferredBlock<SaplingBlock> SAPLING_BAOBAB;
    public static final DeferredBlock<SaplingBlock> SAPLING_MARSHWOOD;
    public static final DeferredBlock<SaplingBlock> SAPLING_CYPRESS;
    public static final DeferredBlock<SaplingBlock> SAPLING_BAMBOO;

    // Leaves
    public static final DeferredBlock<Block> LEAVES_MOSSBARK;
    public static final DeferredBlock<Block> LEAVES_ASPER;
    public static final DeferredBlock<Block> LEAVES_EUCALYPTUS;
    public static final DeferredBlock<Block> LEAVES_MAHOGANY;
    public static final DeferredBlock<Block> LEAVES_BALSAM;
    public static final DeferredBlock<Block> LEAVES_BAOBAB;
    public static final DeferredBlock<Block> LEAVES_MARSHWOOD;
    public static final DeferredBlock<Block> LEAVES_CYPRESS;

    // Planks
    public static final DeferredBlock<Block> PLANKS_BAOBAB;
    public static final DeferredBlock<Block> PLANKS_EUCALYPTUS;
    public static final DeferredBlock<Block> PLANKS_MAHOGANY;
    public static final DeferredBlock<Block> PLANKS_MOSSBARK;
    public static final DeferredBlock<Block> PLANKS_ASPER;
    public static final DeferredBlock<Block> PLANKS_CYPRESS;
    public static final DeferredBlock<Block> PLANKS_BALSAM;
    public static final DeferredBlock<Block> PLANKS_WHITE;
    public static final DeferredBlock<Block> PLANKS_BAMBOO;
    public static final DeferredBlock<Block> PLANKS_ROTTEN;
    public static final DeferredBlock<Block> PLANKS_MARSHWOOD;
    public static final DeferredBlock<Block> PLANKS_SCORCHED;
    public static final DeferredBlock<Block> PLANKS_VARNISHED;
    public static final DeferredBlock<Block> PLANKS_PETRIFIED;

    static {
        // Logs
        LOG_BAOBAB = registerBlock("log_baobab", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        LOG_EUCALYPTUS = registerBlock("log_eucalyptus", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        LOG_MAHOGANY = registerBlock("log_mahogany", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        LOG_MOSSBARK = registerBlock("log_mossbark", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        LOG_ASPER = registerBlock("log_asper", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        LOG_CYPRESS = registerBlock("log_cypress", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        LOG_BALSAM = registerBlock("log_balsam", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        LOG_BALSAM_RESINLESS = registerBlock("log_balsam_resinless", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        LOG_ROTTEN = registerBlock("log_rotten", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        LOG_MARSHWOOD = registerBlock("log_marshwood", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        LOG_SCORCHED = registerBlock("log_scorched", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        LOG_BAMBOO = registerBlock("log_bamboo", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG)));
        LOG_HOLLOW = registerBlock("log_hollow", () -> new RotatedPillarBlock(Properties.ofFullCopy(Blocks.OAK_LOG).noOcclusion()));

        // Saplings
        SAPLING_MOSSBARK = registerSapling("sapling_mossbark", ModTreeGrowers.MOSSBARK);
        SAPLING_ASPER = registerSapling("sapling_asper", ModTreeGrowers.ASPER);
        SAPLING_EUCALYPTUS = registerSapling("sapling_eucalyptus", ModTreeGrowers.EUCALYPTUS);
        SAPLING_MAHOGANY = registerSapling("sapling_mahogany", ModTreeGrowers.MAHOGANY);
        SAPLING_BALSAM = registerSapling("sapling_balsam", ModTreeGrowers.BALSAM);
        SAPLING_BAOBAB = registerSapling("sapling_baobab", ModTreeGrowers.BAOBAB);
        SAPLING_MARSHWOOD = registerSapling("sapling_marshwood", ModTreeGrowers.MARSHWOOD);
        SAPLING_CYPRESS = registerSapling("sapling_cypress", ModTreeGrowers.CYPRESS);
        SAPLING_BAMBOO = registerSapling("sapling_bamboo", ModTreeGrowers.BAMBOO);

        // Leaves
        LEAVES_MOSSBARK = registerSimpleBlock("leaves_mossbark", Properties.ofFullCopy(Blocks.OAK_LEAVES));
        LEAVES_ASPER = registerSimpleBlock("leaves_asper", Properties.ofFullCopy(Blocks.OAK_LEAVES));
        LEAVES_EUCALYPTUS = registerSimpleBlock("leaves_eucalyptus", Properties.ofFullCopy(Blocks.OAK_LEAVES));
        LEAVES_MAHOGANY = registerSimpleBlock("leaves_mahogany", Properties.ofFullCopy(Blocks.OAK_LEAVES));
        LEAVES_BALSAM = registerSimpleBlock("leaves_balsam", Properties.ofFullCopy(Blocks.OAK_LEAVES));
        LEAVES_BAOBAB = registerSimpleBlock("leaves_baobab", Properties.ofFullCopy(Blocks.OAK_LEAVES));
        LEAVES_MARSHWOOD = registerSimpleBlock("leaves_marshwood", Properties.ofFullCopy(Blocks.OAK_LEAVES));
        LEAVES_CYPRESS = registerSimpleBlock("leaves_cypress", Properties.ofFullCopy(Blocks.OAK_LEAVES));

        // Planks
        PLANKS_BAOBAB = registerSimpleBlock("planks_baobab", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_EUCALYPTUS = registerSimpleBlock("planks_eucalyptus", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_MAHOGANY = registerSimpleBlock("planks_mahogany", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_MOSSBARK = registerSimpleBlock("planks_mossbark", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_ASPER = registerSimpleBlock("planks_asper", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_CYPRESS = registerSimpleBlock("planks_cypress", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_BALSAM = registerSimpleBlock("planks_balsam", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_WHITE = registerSimpleBlock("planks_white", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_BAMBOO = registerSimpleBlock("planks_bamboo", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_ROTTEN = registerSimpleBlock("planks_rotten", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_MARSHWOOD = registerSimpleBlock("planks_marshwood", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_SCORCHED = registerSimpleBlock("planks_scorched", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_VARNISHED = registerSimpleBlock("planks_varnished", Properties.ofFullCopy(Blocks.OAK_PLANKS));
        PLANKS_PETRIFIED = registerSimpleBlock("planks_petrified", Properties.ofFullCopy(Blocks.OAK_PLANKS));
    }

    public static void init() {
    }
}