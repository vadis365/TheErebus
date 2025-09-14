package erebus.registries.blocks.providers;

import erebus.block.HollowLogBlock;
import erebus.registries.blocks.properties.WoodBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import erebus.registries.world.tree.ModTreeGrowers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
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

    public static final DeferredBlock<Block> LOG_HOLLOW;

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
    public static final DeferredBlock<Block> PLANKS_ASPER;
    public static final DeferredBlock<Block> PLANKS_BAMBOO;
    public static final DeferredBlock<Block> PLANKS_BAOBAB;
    public static final DeferredBlock<Block> PLANKS_BALSAM;
    public static final DeferredBlock<Block> PLANKS_CYPRESS;
    public static final DeferredBlock<Block> PLANKS_EUCALYPTUS;
    public static final DeferredBlock<Block> PLANKS_MAHOGANY;
    public static final DeferredBlock<Block> PLANKS_MARSHWOOD;
    public static final DeferredBlock<Block> PLANKS_MOSSBARK;
    public static final DeferredBlock<Block> PLANKS_PETRIFIED;
    public static final DeferredBlock<Block> PLANKS_ROTTEN;
    public static final DeferredBlock<Block> PLANKS_SCORCHED;
    public static final DeferredBlock<Block> PLANKS_VARNISHED;
    public static final DeferredBlock<Block> PLANKS_WHITE;

    static {
        // Logs
        LOG_BAOBAB = registerBlock("log_baobab", () -> new RotatedPillarBlock(WoodBlockProperties.LOG));
        LOG_EUCALYPTUS = registerBlock("log_eucalyptus", () -> new RotatedPillarBlock(WoodBlockProperties.LOG));
        LOG_MAHOGANY = registerBlock("log_mahogany", () -> new RotatedPillarBlock(WoodBlockProperties.LOG));
        LOG_MOSSBARK = registerBlock("log_mossbark", () -> new RotatedPillarBlock(WoodBlockProperties.LOG));
        LOG_ASPER = registerBlock("log_asper", () -> new RotatedPillarBlock(WoodBlockProperties.LOG));
        LOG_CYPRESS = registerBlock("log_cypress", () -> new RotatedPillarBlock(WoodBlockProperties.LOG));
        LOG_BALSAM = registerBlock("log_balsam", () -> new RotatedPillarBlock(WoodBlockProperties.LOG));
        LOG_BALSAM_RESINLESS = registerBlock("log_balsam_resinless", () -> new RotatedPillarBlock(WoodBlockProperties.LOG));
        LOG_ROTTEN = registerBlock("log_rotten", () -> new RotatedPillarBlock(WoodBlockProperties.LOG));
        LOG_MARSHWOOD = registerBlock("log_marshwood", () -> new RotatedPillarBlock(WoodBlockProperties.LOG));
        LOG_SCORCHED = registerBlock("log_scorched", () -> new RotatedPillarBlock(WoodBlockProperties.LOG));
        LOG_HOLLOW = registerBlock("log_hollow", () -> new HollowLogBlock(WoodBlockProperties.LOG_HOLLOW));

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
        LEAVES_MOSSBARK = registerSimpleBlock("leaves_mossbark", WoodBlockProperties.LEAVES);
        LEAVES_ASPER = registerSimpleBlock("leaves_asper", WoodBlockProperties.LEAVES);
        LEAVES_EUCALYPTUS = registerSimpleBlock("leaves_eucalyptus", WoodBlockProperties.LEAVES);
        LEAVES_MAHOGANY = registerSimpleBlock("leaves_mahogany", WoodBlockProperties.LEAVES);
        LEAVES_BALSAM = registerSimpleBlock("leaves_balsam", WoodBlockProperties.LEAVES);
        LEAVES_BAOBAB = registerSimpleBlock("leaves_baobab", WoodBlockProperties.LEAVES);
        LEAVES_MARSHWOOD = registerSimpleBlock("leaves_marshwood", WoodBlockProperties.LEAVES);
        LEAVES_CYPRESS = registerSimpleBlock("leaves_cypress", WoodBlockProperties.LEAVES);

        // Planks
        PLANKS_BAOBAB = registerSimpleBlock("planks_baobab", WoodBlockProperties.PLANKS);
        PLANKS_EUCALYPTUS = registerSimpleBlock("planks_eucalyptus", WoodBlockProperties.PLANKS);
        PLANKS_MAHOGANY = registerSimpleBlock("planks_mahogany", WoodBlockProperties.PLANKS);
        PLANKS_MOSSBARK = registerSimpleBlock("planks_mossbark", WoodBlockProperties.PLANKS);
        PLANKS_ASPER = registerSimpleBlock("planks_asper", WoodBlockProperties.PLANKS);
        PLANKS_CYPRESS = registerSimpleBlock("planks_cypress", WoodBlockProperties.PLANKS);
        PLANKS_BALSAM = registerSimpleBlock("planks_balsam", WoodBlockProperties.PLANKS);
        PLANKS_WHITE = registerSimpleBlock("planks_white", WoodBlockProperties.PLANKS);
        PLANKS_BAMBOO = registerSimpleBlock("planks_bamboo", WoodBlockProperties.PLANKS);
        PLANKS_ROTTEN = registerSimpleBlock("planks_rotten", WoodBlockProperties.PLANKS);
        PLANKS_MARSHWOOD = registerSimpleBlock("planks_marshwood", WoodBlockProperties.PLANKS);
        PLANKS_SCORCHED = registerSimpleBlock("planks_scorched", WoodBlockProperties.PLANKS);
        PLANKS_VARNISHED = registerSimpleBlock("planks_varnished", WoodBlockProperties.PLANKS);
        PLANKS_PETRIFIED = registerSimpleBlock("planks_petrified", WoodBlockProperties.PLANKS);
    }

    public static void init() {
    }
}