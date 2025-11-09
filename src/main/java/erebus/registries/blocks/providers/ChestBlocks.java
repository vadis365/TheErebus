package erebus.registries.blocks.providers;

import erebus.registries.blocks.properties.WoodBlockProperties;
import erebus.registries.helpers.ModBlockHelpers;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ChestBlocks extends ModBlockHelpers {

    // Planks
    public static final DeferredBlock<ChestBlock> CHEST_ASPER;
    public static final DeferredBlock<ChestBlock> CHEST_BAMBOO;
    public static final DeferredBlock<ChestBlock> CHEST_BAOBAB;
    public static final DeferredBlock<ChestBlock> CHEST_BALSAM;
    public static final DeferredBlock<ChestBlock> CHEST_CYPRESS;
    public static final DeferredBlock<ChestBlock> CHEST_EUCALYPTUS;
    public static final DeferredBlock<ChestBlock> CHEST_MAHOGANY;
    public static final DeferredBlock<ChestBlock> CHEST_MARSHWOOD;
    public static final DeferredBlock<ChestBlock> CHEST_MOSSBARK;
    public static final DeferredBlock<ChestBlock> CHEST_PETRIFIED;
    public static final DeferredBlock<ChestBlock> CHEST_ROTTEN;
    public static final DeferredBlock<ChestBlock> CHEST_SCORCHED;
    public static final DeferredBlock<ChestBlock> CHEST_VARNISHED;
    public static final DeferredBlock<ChestBlock> CHEST_WHITE;

    static {
        CHEST_ASPER = registerChest("chest_asper", WoodBlockProperties.CHEST.mapColor(MapColor.WOOD));
        CHEST_BAMBOO = registerChest("chest_bamboo", WoodBlockProperties.CHEST.mapColor(MapColor.SAND));
        CHEST_BAOBAB = registerChest("chest_baobab", WoodBlockProperties.CHEST.mapColor(MapColor.TERRACOTTA_WHITE));
        CHEST_BALSAM = registerChest("chest_balsam", WoodBlockProperties.CHEST.mapColor(MapColor.TERRACOTTA_PINK));
        CHEST_CYPRESS = registerChest("chest_cypress", WoodBlockProperties.CHEST.mapColor(MapColor.TERRACOTTA_WHITE));
        CHEST_EUCALYPTUS = registerChest("chest_eucalyptus", WoodBlockProperties.CHEST.mapColor(MapColor.TERRACOTTA_PINK));
        CHEST_MAHOGANY = registerChest("chest_mahogany", WoodBlockProperties.CHEST.mapColor(MapColor.COLOR_BROWN));
        CHEST_MARSHWOOD = registerChest("chest_marshwood", WoodBlockProperties.CHEST.mapColor(MapColor.TERRACOTTA_GREEN));
        CHEST_MOSSBARK = registerChest("chest_mossbark", WoodBlockProperties.CHEST.mapColor(MapColor.COLOR_BROWN));
        CHEST_PETRIFIED = registerChest("chest_petrified", BlockBehaviour.Properties.of()
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.5F)
                .sound(SoundType.WOOD)
                .mapColor(MapColor.TERRACOTTA_BROWN)
        );
        CHEST_ROTTEN = registerChest("chest_rotten", WoodBlockProperties.CHEST.mapColor(MapColor.COLOR_BLACK));
        CHEST_SCORCHED = registerChest("chest_scorched", WoodBlockProperties.CHEST.mapColor(MapColor.COLOR_BLACK));
        CHEST_VARNISHED = registerChest("chest_varnished", WoodBlockProperties.CHEST.mapColor(MapColor.WOOD));
        CHEST_WHITE = registerChest("chest_white", WoodBlockProperties.CHEST.mapColor(MapColor.TERRACOTTA_WHITE));
    }

    public static void init() {
    }
}