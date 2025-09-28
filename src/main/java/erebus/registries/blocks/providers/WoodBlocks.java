package erebus.registries.blocks.providers;

import erebus.block.HollowLogBlock;
import erebus.registries.blocks.properties.WoodBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import erebus.registries.world.tree.ModTreeGrowers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public class WoodBlocks extends ModBlockHelpers {
    // Logs
    public static final DeferredBlock<RotatedPillarBlock> LOG_ASPER;
    public static final DeferredBlock<RotatedPillarBlock> LOG_BALSAM;
    public static final DeferredBlock<RotatedPillarBlock> LOG_BALSAM_RESINLESS;
    public static final DeferredBlock<RotatedPillarBlock> LOG_BAOBAB;
    public static final DeferredBlock<RotatedPillarBlock> LOG_CYPRESS;
    public static final DeferredBlock<RotatedPillarBlock> LOG_EUCALYPTUS;
    public static final DeferredBlock<RotatedPillarBlock> LOG_MAHOGANY;
    public static final DeferredBlock<RotatedPillarBlock> LOG_MARSHWOOD;
    public static final DeferredBlock<RotatedPillarBlock> LOG_MOSSBARK;
    public static final DeferredBlock<RotatedPillarBlock> LOG_ROTTEN;
    public static final DeferredBlock<RotatedPillarBlock> LOG_SCORCHED;

    public static final DeferredBlock<Block> LOG_HOLLOW;

    // Saplings
    public static final DeferredBlock<SaplingBlock> SAPLING_ASPER;
    public static final DeferredBlock<SaplingBlock> SAPLING_BAOBAB;
    public static final DeferredBlock<SaplingBlock> SAPLING_BAMBOO;
    public static final DeferredBlock<SaplingBlock> SAPLING_BALSAM;
    public static final DeferredBlock<SaplingBlock> SAPLING_CYPRESS;
    public static final DeferredBlock<SaplingBlock> SAPLING_EUCALYPTUS;
    public static final DeferredBlock<SaplingBlock> SAPLING_MAHOGANY;
    public static final DeferredBlock<SaplingBlock> SAPLING_MARSHWOOD;
    public static final DeferredBlock<SaplingBlock> SAPLING_MOSSBARK;

    // Leaves
    public static final DeferredBlock<Block> LEAVES_ASPER;
    public static final DeferredBlock<Block> LEAVES_BAOBAB;
    public static final DeferredBlock<Block> LEAVES_BALSAM;
    public static final DeferredBlock<Block> LEAVES_CYPRESS;
    public static final DeferredBlock<Block> LEAVES_EUCALYPTUS;
    public static final DeferredBlock<Block> LEAVES_MAHOGANY;
    public static final DeferredBlock<Block> LEAVES_MARSHWOOD;
    public static final DeferredBlock<Block> LEAVES_MOSSBARK;

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
        LOG_ASPER = registerLog("log_asper", WoodBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
        LOG_BALSAM = registerLog("log_balsam", WoodBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
        LOG_BALSAM_RESINLESS = registerLog("log_balsam_resinless", WoodBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
        LOG_BAOBAB = registerLog("log_baobab", WoodBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
        LOG_CYPRESS = registerLog("log_cypress", WoodBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
        LOG_EUCALYPTUS = registerLog("log_eucalyptus", WoodBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
        LOG_MAHOGANY = registerLog("log_mahogany", WoodBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
        LOG_MARSHWOOD = registerLog("log_marshwood", WoodBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
        LOG_MOSSBARK = registerLog("log_mossbark", WoodBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
        LOG_ROTTEN = registerLog("log_rotten", WoodBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
        LOG_SCORCHED = registerLog("log_scorched", WoodBlockProperties.log(MapColor.WOOD, MapColor.PODZOL));
        LOG_HOLLOW = registerBlock("log_hollow", () -> new HollowLogBlock(WoodBlockProperties.LOG_HOLLOW));

        // Saplings
        SAPLING_ASPER = registerSapling("sapling_asper", ModTreeGrowers.ASPER);
        SAPLING_BAOBAB = registerSapling("sapling_baobab", ModTreeGrowers.BAOBAB);
        SAPLING_BAMBOO = registerSapling("sapling_bamboo", ModTreeGrowers.BAMBOO);
        SAPLING_BALSAM = registerSapling("sapling_balsam", ModTreeGrowers.BALSAM);
        SAPLING_CYPRESS = registerSapling("sapling_cypress", ModTreeGrowers.CYPRESS);
        SAPLING_EUCALYPTUS = registerSapling("sapling_eucalyptus", ModTreeGrowers.EUCALYPTUS);
        SAPLING_MAHOGANY = registerSapling("sapling_mahogany", ModTreeGrowers.MAHOGANY);
        SAPLING_MARSHWOOD = registerSapling("sapling_marshwood", ModTreeGrowers.MARSHWOOD);
        SAPLING_MOSSBARK = registerSapling("sapling_mossbark", ModTreeGrowers.MOSSBARK);

        // Leaves
        LEAVES_ASPER = registerSimpleBlock("leaves_asper", WoodBlockProperties.LEAVES.mapColor(MapColor.PLANT));
        LEAVES_BAOBAB = registerSimpleBlock("leaves_baobab", WoodBlockProperties.LEAVES.mapColor(MapColor.PLANT));
        LEAVES_BALSAM = registerSimpleBlock("leaves_balsam", WoodBlockProperties.LEAVES.mapColor(MapColor.PLANT));
        LEAVES_CYPRESS = registerSimpleBlock("leaves_cypress", WoodBlockProperties.LEAVES.mapColor(MapColor.PLANT));
        LEAVES_EUCALYPTUS = registerSimpleBlock("leaves_eucalyptus", WoodBlockProperties.LEAVES.mapColor(MapColor.PLANT));
        LEAVES_MAHOGANY = registerSimpleBlock("leaves_mahogany", WoodBlockProperties.LEAVES.mapColor(MapColor.PLANT));
        LEAVES_MARSHWOOD = registerSimpleBlock("leaves_marshwood", WoodBlockProperties.LEAVES.mapColor(MapColor.PLANT));
        LEAVES_MOSSBARK = registerSimpleBlock("leaves_mossbark", WoodBlockProperties.LEAVES.mapColor(MapColor.PLANT));

        // Planks
        PLANKS_ASPER = registerSimpleBlock("planks_asper", WoodBlockProperties.PLANKS.mapColor(MapColor.WOOD));
        PLANKS_BAMBOO = registerSimpleBlock("planks_bamboo", WoodBlockProperties.PLANKS.mapColor(MapColor.SAND));
        PLANKS_BAOBAB = registerSimpleBlock("planks_baobab", WoodBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_WHITE));
        PLANKS_BALSAM = registerSimpleBlock("planks_balsam", WoodBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_PINK));
        PLANKS_CYPRESS = registerSimpleBlock("planks_cypress", WoodBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_WHITE));
        PLANKS_EUCALYPTUS = registerSimpleBlock("planks_eucalyptus", WoodBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_PINK));
        PLANKS_MAHOGANY = registerSimpleBlock("planks_mahogany", WoodBlockProperties.PLANKS.mapColor(MapColor.COLOR_BROWN));
        PLANKS_MARSHWOOD = registerSimpleBlock("planks_marshwood", WoodBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_GREEN));
        PLANKS_MOSSBARK = registerSimpleBlock("planks_mossbark", WoodBlockProperties.PLANKS.mapColor(MapColor.COLOR_BROWN));
        PLANKS_PETRIFIED = registerSimpleBlock("planks_petrified", WoodBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_BROWN));
        PLANKS_ROTTEN = registerSimpleBlock("planks_rotten", WoodBlockProperties.PLANKS.mapColor(MapColor.COLOR_BLACK));
        PLANKS_SCORCHED = registerSimpleBlock("planks_scorched", WoodBlockProperties.PLANKS.mapColor(MapColor.COLOR_BLACK));
        PLANKS_VARNISHED = registerSimpleBlock("planks_varnished", WoodBlockProperties.PLANKS.mapColor(MapColor.WOOD));
        PLANKS_WHITE = registerSimpleBlock("planks_white", WoodBlockProperties.PLANKS.mapColor(MapColor.TERRACOTTA_WHITE));
    }

    public static void init() {
    }
}