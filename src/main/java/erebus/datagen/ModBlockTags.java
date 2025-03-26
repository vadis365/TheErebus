package erebus.datagen;

import erebus.Erebus;
import static erebus.registries.ModBlocks.*;
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
        tag(ModTags.MINEABLE_WITH_PAXEL).addTags(BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.MINEABLE_WITH_AXE, BlockTags.MINEABLE_WITH_SHOVEL);

        // MARK: Walls
        tag(BlockTags.WALLS)
                .add(
                        WALL_UMBERSTONE.get(),
                        WALL_UMBERCOBBLE.get(),
                        WALL_UMBERCOBBLE_MOSSY.get(),
                        WALL_UMBERCOBBLE_WEBBED.get(),
                        WALL_UMBERSTONE_BRICKS.get(),
                        WALL_UMBERTILE_SMOOTH.get(),
                        WALL_UMBERTILE_SMOOTH_SMALL.get(),
                        WALL_UMBERPAVER.get(),
                        WALL_UMBERPAVER_MOSSY.get(),
                        WALL_UMBERPAVER_WEBBED.get(),
                        WALL_AMBER.get(),
                        WALL_AMBER_BRICKS.get()
                );

        // MARK: Stone
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(
                        UMBERSTONE.get(), UMBERSTONE_BRICKS.get(), UMBERCOBBLE.get(),
                        UMBERCOBBLE_MOSSY.get(), UMBERCOBBLE_WEBBED.get(), UMBERTILE_SMOOTH.get(),
                        UMBERTILE_SMOOTH_SMALL.get(), UMBERPAVER.get(), UMBERPAVER_MOSSY.get(),
                        UMBERPAVER_WEBBED.get(), UMBERSTONE_PILLAR.get(), ORE_IRON.get(),
                        ORE_GOLD.get(), ORE_COAL.get(), ORE_DIAMOND.get(), ORE_EMERALD.get(),
                        ORE_LAPIS.get(), ORE_QUARTZ.get(), ORE_PETRIFIED_QUARTZ.get(), ORE_COPPER.get(),
                        ORE_SILVER.get(), ORE_TIN.get(), ORE_LEAD.get(), ORE_ALUMINUM.get(), ORE_JADE.get(),
                        ORE_FOSSIL.get(), ORE_GNEISS.get(), ORE_PETRIFIED_WOOD.get(), ORE_TEMPLE.get(),
                        ORE_ENCRUSTED_DIAMOND.get()
                );

        // MARK: Ores
        tag(BlockTags.COAL_ORES).add(ORE_COAL.get());
        tag(BlockTags.IRON_ORES).add(ORE_IRON.get());
        tag(BlockTags.GOLD_ORES).add(ORE_GOLD.get());
        tag(BlockTags.DIAMOND_ORES).add(ORE_DIAMOND.get(), ORE_ENCRUSTED_DIAMOND.get());
        tag(BlockTags.EMERALD_ORES).add(ORE_EMERALD.get());
        tag(BlockTags.COPPER_ORES).add(ORE_COPPER.get());
        tag(BlockTags.LAPIS_ORES).add(ORE_LAPIS.get());

        tag(BlockTags.CANDLE_CAKES)
                .add(
                        CANDLE_HONEY_TREAT.get(),
                        WHITE_CANDLE_HONEY_TREAT.get(),
                        ORANGE_CANDLE_HONEY_TREAT.get(),
                        MAGENTA_CANDLE_HONEY_TREAT.get(),
                        LIGHT_BLUE_CANDLE_HONEY_TREAT.get(),
                        YELLOW_CANDLE_HONEY_TREAT.get(),
                        LIME_CANDLE_HONEY_TREAT.get(),
                        PINK_CANDLE_HONEY_TREAT.get(),
                        GRAY_CANDLE_HONEY_TREAT.get(),
                        LIGHT_GRAY_CANDLE_HONEY_TREAT.get(),
                        CYAN_CANDLE_HONEY_TREAT.get(),
                        PURPLE_CANDLE_HONEY_TREAT.get(),
                        BLUE_CANDLE_HONEY_TREAT.get(),
                        BROWN_CANDLE_HONEY_TREAT.get(),
                        GREEN_CANDLE_HONEY_TREAT.get(),
                        RED_CANDLE_HONEY_TREAT.get(),
                        BLACK_CANDLE_HONEY_TREAT.get()
                );

        tag(BlockTags.LOGS_THAT_BURN)
                .add(
                        LOG_ASPER.get(),
                        LOG_BALSAM.get(),
                        LOG_BAOBAB.get(),
                        LOG_CYPRESS.get(),
                        LOG_EUCALYPTUS.get(),
                        LOG_BALSAM_RESINLESS.get(),
                        LOG_BAMBOO.get(),
                        LOG_HOLLOW.get(),
                        LOG_MAHOGANY.get(),
                        LOG_MARSHWOOD.get(),
                        LOG_MOSSBARK.get(),
                        LOG_ROTTEN.get(),
                        LOG_SCORCHED.get()
                );

        tag(BlockTags.LEAVES)
                .add(

                        LEAVES_ASPER.get(),
                        LEAVES_BALSAM.get(),
                        LEAVES_BAOBAB.get(),
                        LEAVES_CYPRESS.get(),
                        LEAVES_EUCALYPTUS.get(),
                        LEAVES_MAHOGANY.get(),
                        LEAVES_MARSHWOOD.get(),
                        LEAVES_MOSSBARK.get()
                );
    }
}
