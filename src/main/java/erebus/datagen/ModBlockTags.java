package erebus.datagen;

import erebus.Erebus;
import erebus.registries.blocks.providers.*;
import erebus.registries.data.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTags extends IntrinsicHolderTagsProvider<Block> {

    public ModBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.BLOCK, lookupProvider, block -> block.builtInRegistryHolder().key(), Erebus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        // MARK: Paxel
        tag(ModTags.MINEABLE_WITH_PAXEL)
                .addTags(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_AXE, BlockTags.MINEABLE_WITH_SHOVEL);

        // MARK: Walls
        tag(BlockTags.WALLS)
                .add(
                        WallBlocks.WALL_UMBERSTONE.get(),
                        WallBlocks.WALL_UMBERCOBBLE.get(),
                        WallBlocks.WALL_UMBERCOBBLE_MOSSY.get(),
                        WallBlocks.WALL_UMBERCOBBLE_WEBBED.get(),
                        WallBlocks.WALL_UMBERSTONE_BRICKS.get(),
                        WallBlocks.WALL_UMBERTILE_SMOOTH.get(),
                        WallBlocks.WALL_UMBERTILE_SMOOTH_SMALL.get(),
                        WallBlocks.WALL_UMBERPAVER.get(),
                        WallBlocks.WALL_UMBERPAVER_MOSSY.get(),
                        WallBlocks.WALL_UMBERPAVER_WEBBED.get(),
                        WallBlocks.WALL_AMBER.get(),
                        WallBlocks.WALL_AMBER_BRICKS.get()
                );

        // MARK: Stone
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        UmberstoneBlocks.UMBERSTONE.get(), UmberstoneBlocks.UMBERSTONE_BRICKS.get(), UmberstoneBlocks.UMBERCOBBLE.get(),
                        UmberstoneBlocks.UMBERCOBBLE_MOSSY.get(), UmberstoneBlocks.UMBERCOBBLE_WEBBED.get(), UmberstoneBlocks.UMBERTILE_SMOOTH.get(),
                        UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL.get(), UmberstoneBlocks.UMBERPAVER.get(), UmberstoneBlocks.UMBERPAVER_MOSSY.get(),
                        UmberstoneBlocks.UMBERPAVER_WEBBED.get(), UmberstoneBlocks.UMBERSTONE_PILLAR.get(), OreBlocks.ORE_IRON.get(),
                        OreBlocks.ORE_GOLD.get(), OreBlocks.ORE_COAL.get(), OreBlocks.ORE_DIAMOND.get(), OreBlocks.ORE_EMERALD.get(),
                        OreBlocks.ORE_LAPIS.get(), OreBlocks.ORE_QUARTZ.get(), OreBlocks.ORE_PETRIFIED_QUARTZ.get(), OreBlocks.ORE_COPPER.get(),
                        OreBlocks.ORE_SILVER.get(), OreBlocks.ORE_TIN.get(), OreBlocks.ORE_LEAD.get(), OreBlocks.ORE_ALUMINUM.get(), OreBlocks.ORE_JADE.get(),
                        OreBlocks.ORE_FOSSIL.get(), OreBlocks.ORE_GNEISS.get(), OreBlocks.ORE_PETRIFIED_WOOD.get(), OreBlocks.ORE_TEMPLE.get(),
                        OreBlocks.ORE_ENCRUSTED_DIAMOND.get()
                );

        // MARK: Ores
        tag(BlockTags.COAL_ORES).add(OreBlocks.ORE_COAL.get());
        tag(BlockTags.IRON_ORES).add(OreBlocks.ORE_IRON.get());
        tag(BlockTags.GOLD_ORES).add(OreBlocks.ORE_GOLD.get());
        tag(BlockTags.DIAMOND_ORES).add(OreBlocks.ORE_DIAMOND.get(), OreBlocks.ORE_ENCRUSTED_DIAMOND.get());
        tag(BlockTags.EMERALD_ORES).add(OreBlocks.ORE_EMERALD.get());
        tag(BlockTags.COPPER_ORES).add(OreBlocks.ORE_COPPER.get());
        tag(BlockTags.LAPIS_ORES).add(OreBlocks.ORE_LAPIS.get());

        tag(BlockTags.CANDLE_CAKES)
                .add(
                        OtherBlocks.CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.WHITE_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.ORANGE_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.MAGENTA_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.YELLOW_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.LIME_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.PINK_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.GRAY_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.CYAN_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.PURPLE_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.BLUE_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.BROWN_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.GREEN_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.RED_CANDLE_HONEY_TREAT.get(),
                        OtherBlocks.BLACK_CANDLE_HONEY_TREAT.get()
                );

        tag(BlockTags.LOGS_THAT_BURN)
                .add(
                        WoodBlocks.LOG_ASPER.get(),
                        WoodBlocks.LOG_BALSAM.get(),
                        WoodBlocks.LOG_BAOBAB.get(),
                        WoodBlocks.LOG_CYPRESS.get(),
                        WoodBlocks.LOG_EUCALYPTUS.get(),
                        WoodBlocks.LOG_BALSAM_RESINLESS.get(),
                        WoodBlocks.LOG_BAMBOO.get(),
                        WoodBlocks.LOG_HOLLOW.get(),
                        WoodBlocks.LOG_MAHOGANY.get(),
                        WoodBlocks.LOG_MARSHWOOD.get(),
                        WoodBlocks.LOG_MOSSBARK.get(),
                        WoodBlocks.LOG_ROTTEN.get(),
                        WoodBlocks.LOG_SCORCHED.get()
                );

        tag(BlockTags.LEAVES)
                .add(
                        WoodBlocks.LEAVES_ASPER.get(),
                        WoodBlocks.LEAVES_BALSAM.get(),
                        WoodBlocks.LEAVES_BAOBAB.get(),
                        WoodBlocks.LEAVES_CYPRESS.get(),
                        WoodBlocks.LEAVES_EUCALYPTUS.get(),
                        WoodBlocks.LEAVES_MAHOGANY.get(),
                        WoodBlocks.LEAVES_MARSHWOOD.get(),
                        WoodBlocks.LEAVES_MOSSBARK.get()
                );

        tag(ModTags.UMBERSTONE_ORE_REPLACEABLES)
                .add(UmberstoneBlocks.UMBERSTONE.get());
        
        // Stigma Blocks for Bees
        tag(ModTags.BEE_POLINATION_BLOCKS)
        .add(
                PlantBlocks.STIGMA_BLACK.get(),
                PlantBlocks.STIGMA_BLUE.get(),
                PlantBlocks.STIGMA_BROWN.get(),
                PlantBlocks.STIGMA_CYAN.get(),
                PlantBlocks.STIGMA_GRAY.get(),
                PlantBlocks.STIGMA_LIGHT_BLUE.get(),
                PlantBlocks.STIGMA_LIGHT_GRAY.get(),
                PlantBlocks.STIGMA_MAGENTA.get(),
                PlantBlocks.STIGMA_ORANGE.get(),
                PlantBlocks.STIGMA_PINK.get(),
                PlantBlocks.STIGMA_PURPLE.get(),
                PlantBlocks.STIGMA_RED.get(),
                PlantBlocks.STIGMA_WHITE.get(),
                PlantBlocks.STIGMA_YELLOW.get(),
                PlantBlocks.EXPLODING_STIGMA.get()
        );
    }
}
