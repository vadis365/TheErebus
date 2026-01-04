package erebus.datagen.tags;

import erebus.Erebus;
import erebus.registries.blocks.providers.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTags extends IntrinsicHolderTagsProvider<Block> {

    public ModBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.BLOCK, lookupProvider, block -> block.builtInRegistryHolder().key(), Erebus.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
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
                        UmberstoneBlocks.UMBERPAVER_WEBBED.get(), UmberstoneBlocks.UMBERSTONE_PILLAR.get(), OreBlocks.IRON.get(),
                        OreBlocks.GOLD.get(), OreBlocks.COAL.get(), OreBlocks.DIAMOND.get(), OreBlocks.EMERALD.get(),
                        OreBlocks.LAPIS.get(), OreBlocks.QUARTZ.get(), OreBlocks.PETRIFIED_QUARTZ.get(), OreBlocks.COPPER.get(),
                        OreBlocks.SILVER.get(), OreBlocks.TIN.get(), OreBlocks.LEAD.get(), OreBlocks.ALUMINUM.get(), OreBlocks.JADE.get(),
                        OreBlocks.FOSSIL.get(), OreBlocks.GNEISS.get(), OreBlocks.PETRIFIED_WOOD.get(), OreBlocks.TEMPLE.get(),
                        OreBlocks.ENCRUSTED_DIAMOND.get(), AmberBlocks.AMBER.get(), AmberBlocks.AMBER_BRICKS.get()
                );

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(OtherBlocks.QUICK_SAND.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(
                        WoodBlocks.LOG_ASPER.get(), WoodBlocks.LOG_BALSAM.get(), WoodBlocks.LOG_BALSAM_RESINLESS.get(), WoodBlocks.LOG_BAOBAB.get(), WoodBlocks.LOG_CYPRESS.get(), WoodBlocks.LOG_EUCALYPTUS.get(), WoodBlocks.LOG_MAHOGANY.get(), WoodBlocks.LOG_MARSHWOOD.get(), WoodBlocks.LOG_MOSSBARK.get(), WoodBlocks.LOG_ROTTEN.get(), WoodBlocks.LOG_SCORCHED.get(),
                        WoodBlocks.LOG_HOLLOW.get(),
                        WoodBlocks.PLANKS_ASPER.get(), WoodBlocks.PLANKS_BAMBOO.get(), WoodBlocks.PLANKS_BAOBAB.get(), WoodBlocks.PLANKS_BALSAM.get(), WoodBlocks.PLANKS_CYPRESS.get(), WoodBlocks.PLANKS_EUCALYPTUS.get(), WoodBlocks.PLANKS_MAHOGANY.get(), WoodBlocks.PLANKS_MARSHWOOD.get(), WoodBlocks.PLANKS_MOSSBARK.get(), WoodBlocks.PLANKS_ROTTEN.get(), WoodBlocks.PLANKS_SCORCHED.get(),
                        WoodBlocks.PLANKS_VARNISHED.get(), WoodBlocks.PLANKS_WHITE.get()
                );

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(OreBlocks.IRON.get(), OreBlocks.LAPIS.get(), OreBlocks.COPPER.get(), OreBlocks.TIN.get(), OreBlocks.LEAD.get(), OreBlocks.ALUMINUM.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        OreBlocks.GOLD.get(), OreBlocks.DIAMOND.get(), OreBlocks.EMERALD.get(), OreBlocks.SILVER.get(), OreBlocks.JADE.get(), OreBlocks.ENCRUSTED_DIAMOND.get(),
                        OtherBlocks.QUICK_SAND.get()
                );


        // MARK: Ores
        tag(BlockTags.COAL_ORES).add(OreBlocks.COAL.get());
        tag(BlockTags.IRON_ORES).add(OreBlocks.IRON.get());
        tag(BlockTags.GOLD_ORES).add(OreBlocks.GOLD.get());
        tag(BlockTags.DIAMOND_ORES).add(OreBlocks.DIAMOND.get(), OreBlocks.ENCRUSTED_DIAMOND.get());
        tag(BlockTags.EMERALD_ORES).add(OreBlocks.EMERALD.get());
        tag(BlockTags.COPPER_ORES).add(OreBlocks.COPPER.get());
        tag(BlockTags.LAPIS_ORES).add(OreBlocks.LAPIS.get());

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

        tag(BlockTags.FENCES)
                .add(FenceBlocks.FENCE_ASPER.get())
                .add(FenceBlocks.FENCE_BAMBOO.get())
                .add(FenceBlocks.FENCE_BAOBAB.get())
                .add(FenceBlocks.FENCE_BALSAM.get())
                .add(FenceBlocks.FENCE_CYPRESS.get())
                .add(FenceBlocks.FENCE_EUCALYPTUS.get())
                .add(FenceBlocks.FENCE_MAHOGANY.get())
                .add(FenceBlocks.FENCE_MARSHWOOD.get())
                .add(FenceBlocks.FENCE_MOSSBARK.get())
                .add(FenceBlocks.FENCE_ROTTEN.get())
                .add(FenceBlocks.FENCE_SCORCHED.get())
                .add(FenceBlocks.FENCE_VARNISHED.get())
                .add(FenceBlocks.FENCE_WHITE.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(FenceBlocks.FENCE_ASPER.get())
                .add(FenceBlocks.FENCE_BAMBOO.get())
                .add(FenceBlocks.FENCE_BAOBAB.get())
                .add(FenceBlocks.FENCE_BALSAM.get())
                .add(FenceBlocks.FENCE_CYPRESS.get())
                .add(FenceBlocks.FENCE_EUCALYPTUS.get())
                .add(FenceBlocks.FENCE_MAHOGANY.get())
                .add(FenceBlocks.FENCE_MARSHWOOD.get())
                .add(FenceBlocks.FENCE_MOSSBARK.get())
                .add(FenceBlocks.FENCE_ROTTEN.get())
                .add(FenceBlocks.FENCE_SCORCHED.get())
                .add(FenceBlocks.FENCE_VARNISHED.get())
                .add(FenceBlocks.FENCE_WHITE.get());

        tag(BlockTags.FENCE_GATES)
                .add(FenceBlocks.FENCE_GATE_ASPER.get())
                .add(FenceBlocks.FENCE_GATE_BAMBOO.get())
                .add(FenceBlocks.FENCE_GATE_BAOBAB.get())
                .add(FenceBlocks.FENCE_GATE_BALSAM.get())
                .add(FenceBlocks.FENCE_GATE_CYPRESS.get())
                .add(FenceBlocks.FENCE_GATE_EUCALYPTUS.get())
                .add(FenceBlocks.FENCE_GATE_MAHOGANY.get())
                .add(FenceBlocks.FENCE_GATE_MARSHWOOD.get())
                .add(FenceBlocks.FENCE_GATE_MOSSBARK.get())
                .add(FenceBlocks.FENCE_GATE_ROTTEN.get())
                .add(FenceBlocks.FENCE_GATE_SCORCHED.get())
                .add(FenceBlocks.FENCE_GATE_VARNISHED.get())
                .add(FenceBlocks.FENCE_GATE_WHITE.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(
                        WoodBlocks.LOG_ASPER.get(),
                        WoodBlocks.LOG_BALSAM.get(),
                        WoodBlocks.LOG_BAOBAB.get(),
                        WoodBlocks.LOG_CYPRESS.get(),
                        WoodBlocks.LOG_EUCALYPTUS.get(),
                        WoodBlocks.LOG_BALSAM_RESINLESS.get(),
                        PlantBlocks.COLOSSAL_BAMBOO.get(),
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

        tag(ModTags.EREBUS_CARVER_REPLACEABLES)
                .add(UmberstoneBlocks.UMBERSTONE.get());
        
        // Stigma Blocks for Bees
        tag(ModTags.BEE_POLLINATION_BLOCKS)
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
        
		// Bamboo Stuff?
		tag(BlockTags.CLIMBABLE)
                .add(OtherBlocks.BAMBOO_LADDER.get())
                .add(OtherBlocks.BAMBOO_NERD_POLE.get());
        		
    }
}
