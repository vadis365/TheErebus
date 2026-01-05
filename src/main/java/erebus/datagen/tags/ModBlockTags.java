package erebus.datagen.tags;

import erebus.Erebus;
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
                        ModBlocks.WALL_UMBERSTONE.get(),
                        ModBlocks.WALL_UMBERCOBBLE.get(),
                        ModBlocks.WALL_UMBERCOBBLE_MOSSY.get(),
                        ModBlocks.WALL_UMBERCOBBLE_WEBBED.get(),
                        ModBlocks.WALL_UMBERSTONE_BRICKS.get(),
                        ModBlocks.WALL_UMBERTILE_SMOOTH.get(),
                        ModBlocks.WALL_UMBERTILE_SMOOTH_SMALL.get(),
                        ModBlocks.WALL_UMBERPAVER.get(),
                        ModBlocks.WALL_UMBERPAVER_MOSSY.get(),
                        ModBlocks.WALL_UMBERPAVER_WEBBED.get(),
                        ModBlocks.WALL_AMBER.get(),
                        ModBlocks.WALL_AMBER_BRICKS.get()
                );

        // MARK: Stone
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        ModBlocks.UMBERSTONE.get(), ModBlocks.UMBERSTONE_BRICKS.get(), ModBlocks.UMBERCOBBLE.get(),
                        ModBlocks.UMBERCOBBLE_MOSSY.get(), ModBlocks.UMBERCOBBLE_WEBBED.get(), ModBlocks.UMBERTILE_SMOOTH.get(),
                        ModBlocks.UMBERTILE_SMOOTH_SMALL.get(), ModBlocks.UMBERPAVER.get(), ModBlocks.UMBERPAVER_MOSSY.get(),
                        ModBlocks.UMBERPAVER_WEBBED.get(), ModBlocks.UMBERSTONE_PILLAR.get(), ModBlocks.ORE_IRON.get(),
                        ModBlocks.ORE_GOLD.get(), ModBlocks.ORE_COAL.get(), ModBlocks.ORE_DIAMOND.get(), ModBlocks.ORE_EMERALD.get(),
                        ModBlocks.ORE_LAPIS.get(), ModBlocks.ORE_QUARTZ.get(), ModBlocks.ORE_PETRIFIED_QUARTZ.get(), ModBlocks.ORE_COPPER.get(),
                        ModBlocks.ORE_SILVER.get(), ModBlocks.ORE_TIN.get(), ModBlocks.ORE_LEAD.get(), ModBlocks.ORE_ALUMINUM.get(), ModBlocks.ORE_JADE.get(),
                        ModBlocks.ORE_FOSSIL.get(), ModBlocks.ORE_GNEISS.get(), ModBlocks.ORE_PETRIFIED_WOOD.get(), ModBlocks.ORE_TEMPLE.get(),
                        ModBlocks.ORE_ENCRUSTED_DIAMOND.get(), ModBlocks.AMBER.get(), ModBlocks.AMBER_BRICKS.get()
                );

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.QUICK_SAND.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(
                        ModBlocks.LOG_ASPER.get(), ModBlocks.LOG_BALSAM.get(), ModBlocks.LOG_BALSAM_RESINLESS.get(), ModBlocks.LOG_BAOBAB.get(), ModBlocks.LOG_CYPRESS.get(), ModBlocks.LOG_EUCALYPTUS.get(), ModBlocks.LOG_MAHOGANY.get(), ModBlocks.LOG_MARSHWOOD.get(), ModBlocks.LOG_MOSSBARK.get(), ModBlocks.LOG_ROTTEN.get(), ModBlocks.LOG_SCORCHED.get(),
                        ModBlocks.LOG_HOLLOW.get(),
                        ModBlocks.PLANKS_ASPER.get(), ModBlocks.PLANKS_BAMBOO.get(), ModBlocks.PLANKS_BAOBAB.get(), ModBlocks.PLANKS_BALSAM.get(), ModBlocks.PLANKS_CYPRESS.get(), ModBlocks.PLANKS_EUCALYPTUS.get(), ModBlocks.PLANKS_MAHOGANY.get(), ModBlocks.PLANKS_MARSHWOOD.get(), ModBlocks.PLANKS_MOSSBARK.get(), ModBlocks.PLANKS_ROTTEN.get(), ModBlocks.PLANKS_SCORCHED.get(),
                        ModBlocks.PLANKS_VARNISHED.get(), ModBlocks.PLANKS_WHITE.get()
                );

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.ORE_IRON.get(), ModBlocks.ORE_LAPIS.get(), ModBlocks.ORE_COPPER.get(), ModBlocks.ORE_TIN.get(), ModBlocks.ORE_LEAD.get(), ModBlocks.ORE_ALUMINUM.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        ModBlocks.ORE_GOLD.get(), ModBlocks.ORE_DIAMOND.get(), ModBlocks.ORE_EMERALD.get(), ModBlocks.ORE_SILVER.get(), ModBlocks.ORE_JADE.get(), ModBlocks.ORE_ENCRUSTED_DIAMOND.get(),
                        ModBlocks.QUICK_SAND.get()
                );


        // MARK: Ores
        tag(BlockTags.COAL_ORES).add(ModBlocks.ORE_COAL.get());
        tag(BlockTags.IRON_ORES).add(ModBlocks.ORE_IRON.get());
        tag(BlockTags.GOLD_ORES).add(ModBlocks.ORE_GOLD.get());
        tag(BlockTags.DIAMOND_ORES).add(ModBlocks.ORE_DIAMOND.get(), ModBlocks.ORE_ENCRUSTED_DIAMOND.get());
        tag(BlockTags.EMERALD_ORES).add(ModBlocks.ORE_EMERALD.get());
        tag(BlockTags.COPPER_ORES).add(ModBlocks.ORE_COPPER.get());
        tag(BlockTags.LAPIS_ORES).add(ModBlocks.ORE_LAPIS.get());

        tag(BlockTags.CANDLE_CAKES)
                .add(
                        ModBlocks.CANDLE_HONEY_TREAT.get(),
                        ModBlocks.WHITE_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.ORANGE_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.MAGENTA_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.YELLOW_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.LIME_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.PINK_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.GRAY_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.CYAN_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.PURPLE_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.BLUE_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.BROWN_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.GREEN_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.RED_CANDLE_HONEY_TREAT.get(),
                        ModBlocks.BLACK_CANDLE_HONEY_TREAT.get()
                );

        tag(BlockTags.FENCES)
                .add(ModBlocks.FENCE_ASPER.get())
                .add(ModBlocks.FENCE_BAMBOO.get())
                .add(ModBlocks.FENCE_BAOBAB.get())
                .add(ModBlocks.FENCE_BALSAM.get())
                .add(ModBlocks.FENCE_CYPRESS.get())
                .add(ModBlocks.FENCE_EUCALYPTUS.get())
                .add(ModBlocks.FENCE_MAHOGANY.get())
                .add(ModBlocks.FENCE_MARSHWOOD.get())
                .add(ModBlocks.FENCE_MOSSBARK.get())
                .add(ModBlocks.FENCE_ROTTEN.get())
                .add(ModBlocks.FENCE_SCORCHED.get())
                .add(ModBlocks.FENCE_VARNISHED.get())
                .add(ModBlocks.FENCE_WHITE.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.FENCE_ASPER.get())
                .add(ModBlocks.FENCE_BAMBOO.get())
                .add(ModBlocks.FENCE_BAOBAB.get())
                .add(ModBlocks.FENCE_BALSAM.get())
                .add(ModBlocks.FENCE_CYPRESS.get())
                .add(ModBlocks.FENCE_EUCALYPTUS.get())
                .add(ModBlocks.FENCE_MAHOGANY.get())
                .add(ModBlocks.FENCE_MARSHWOOD.get())
                .add(ModBlocks.FENCE_MOSSBARK.get())
                .add(ModBlocks.FENCE_ROTTEN.get())
                .add(ModBlocks.FENCE_SCORCHED.get())
                .add(ModBlocks.FENCE_VARNISHED.get())
                .add(ModBlocks.FENCE_WHITE.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.FENCE_GATE_ASPER.get())
                .add(ModBlocks.FENCE_GATE_BAMBOO.get())
                .add(ModBlocks.FENCE_GATE_BAOBAB.get())
                .add(ModBlocks.FENCE_GATE_BALSAM.get())
                .add(ModBlocks.FENCE_GATE_CYPRESS.get())
                .add(ModBlocks.FENCE_GATE_EUCALYPTUS.get())
                .add(ModBlocks.FENCE_GATE_MAHOGANY.get())
                .add(ModBlocks.FENCE_GATE_MARSHWOOD.get())
                .add(ModBlocks.FENCE_GATE_MOSSBARK.get())
                .add(ModBlocks.FENCE_GATE_ROTTEN.get())
                .add(ModBlocks.FENCE_GATE_SCORCHED.get())
                .add(ModBlocks.FENCE_GATE_VARNISHED.get())
                .add(ModBlocks.FENCE_GATE_WHITE.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(
                        ModBlocks.LOG_ASPER.get(),
                        ModBlocks.LOG_BALSAM.get(),
                        ModBlocks.LOG_BAOBAB.get(),
                        ModBlocks.LOG_CYPRESS.get(),
                        ModBlocks.LOG_EUCALYPTUS.get(),
                        ModBlocks.LOG_BALSAM_RESINLESS.get(),
                        ModBlocks.COLOSSAL_BAMBOO.get(),
                        ModBlocks.LOG_HOLLOW.get(),
                        ModBlocks.LOG_MAHOGANY.get(),
                        ModBlocks.LOG_MARSHWOOD.get(),
                        ModBlocks.LOG_MOSSBARK.get(),
                        ModBlocks.LOG_ROTTEN.get(),
                        ModBlocks.LOG_SCORCHED.get()
                );

        tag(BlockTags.LEAVES)
                .add(
                        ModBlocks.LEAVES_ASPER.get(),
                        ModBlocks.LEAVES_BALSAM.get(),
                        ModBlocks.LEAVES_BAOBAB.get(),
                        ModBlocks.LEAVES_CYPRESS.get(),
                        ModBlocks.LEAVES_EUCALYPTUS.get(),
                        ModBlocks.LEAVES_MAHOGANY.get(),
                        ModBlocks.LEAVES_MARSHWOOD.get(),
                        ModBlocks.LEAVES_MOSSBARK.get()
                );

        tag(ModTags.UMBERSTONE_ORE_REPLACEABLES)
                .add(ModBlocks.UMBERSTONE.get());

        tag(ModTags.EREBUS_CARVER_REPLACEABLES)
                .add(ModBlocks.UMBERSTONE.get());
        
        // Stigma Blocks for Bees
        tag(ModTags.BEE_POLLINATION_BLOCKS)
        .add(
                ModBlocks.STIGMA_BLACK.get(),
                ModBlocks.STIGMA_BLUE.get(),
                ModBlocks.STIGMA_BROWN.get(),
                ModBlocks.STIGMA_CYAN.get(),
                ModBlocks.STIGMA_GRAY.get(),
                ModBlocks.STIGMA_LIGHT_BLUE.get(),
                ModBlocks.STIGMA_LIGHT_GRAY.get(),
                ModBlocks.STIGMA_MAGENTA.get(),
                ModBlocks.STIGMA_ORANGE.get(),
                ModBlocks.STIGMA_PINK.get(),
                ModBlocks.STIGMA_PURPLE.get(),
                ModBlocks.STIGMA_RED.get(),
                ModBlocks.STIGMA_WHITE.get(),
                ModBlocks.STIGMA_YELLOW.get(),
                ModBlocks.EXPLODING_STIGMA.get()
        );
        
		// Bamboo Stuff?
		tag(BlockTags.CLIMBABLE)
                .add(ModBlocks.BAMBOO_LADDER.get())
                .add(ModBlocks.BAMBOO_NERD_POLE.get());
        		
    }
}
