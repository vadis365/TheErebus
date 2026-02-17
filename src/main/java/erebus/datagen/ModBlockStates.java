package erebus.datagen;

import erebus.Erebus;
import erebus.block.plants.ModCropBlock;
import erebus.client.render.block.renderer.stack.*;
import erebus.registries.ModBlockFamilies;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import erebus.utils.ModBlockStateHelpers;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;
import static net.minecraft.client.data.models.model.TextureMapping.craftingTable;

public class ModBlockStates extends ModBlockStateHelpers {

    public ModBlockStates(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        super(blockModels, itemModels);
    }

    protected void registerModels() {
        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(family -> blockModels.family(family.getBaseBlock()).generateFor(family));

        // MARK: Umberstone
        createBlock(ModBlocks.UMBERGRAVEL);
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.UMBERSTONE_PILLAR.get(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        createBlock(ModBlocks.VOLCANIC_ROCK);
        createDustBlocks();
        createBlock(ModBlocks.PETRIFIED_WOOD_ROCK);
        createBlock(ModBlocks.PETRIFIED_WOOD_ROCK_2);
        createBlock(ModBlocks.PETRIFIED_WOOD_ROCK_3);
        createBlock(ModBlocks.PETRIFIED_WOOD_ROCK_4);
        createBlock(ModBlocks.PETRIFIED_WOOD_ROCK_5);
        createBlock(ModBlocks.PETRIFIED_WOOD_ROCK_6);
        createBlock(ModBlocks.PETRIFIED_BARK_RED);
        createBlock(ModBlocks.PETRIFIED_BARK_BROWN);
        createBlock(ModBlocks.PETRIFIED_LOG_INNER);
        createBlock(ModBlocks.DUNG);

        // MARK: Amber
        createBlock(ModBlocks.PRESERVED_AMBER);
        createBlock(ModBlocks.PRESERVED_AMBER_GLASS);

        // MARK: Ores
        createBlock(ModBlocks.ORE_IRON);
        createBlock(ModBlocks.ORE_GOLD);
        createBlock(ModBlocks.ORE_COAL);
        createBlock(ModBlocks.ORE_DIAMOND);
        createBlock(ModBlocks.ORE_EMERALD);
        createBlock(ModBlocks.ORE_LAPIS);
        createBlock(ModBlocks.ORE_QUARTZ);
        createBlock(ModBlocks.ORE_PETRIFIED_QUARTZ);
        createBlock(ModBlocks.ORE_COPPER);
        createBlock(ModBlocks.ORE_SILVER);
        createBlock(ModBlocks.ORE_TIN);
        createBlock(ModBlocks.ORE_LEAD);
        createBlock(ModBlocks.ORE_ALUMINUM);
        createBlock(ModBlocks.ORE_JADE);
        createBlock(ModBlocks.ORE_ENCRUSTED_DIAMOND);
        createBlock(ModBlocks.ORE_FOSSIL);
        createBlock(ModBlocks.ORE_GNEISS);
        createBlock(ModBlocks.ORE_PETRIFIED_WOOD);
        createBlock(ModBlocks.ORE_TEMPLE);

        // MARK: Logs
        blockModels.woodProvider(ModBlocks.LOG_BAOBAB.get()).logWithHorizontal(ModBlocks.LOG_BAOBAB.get());
        blockModels.woodProvider(ModBlocks.LOG_EUCALYPTUS.get()).logWithHorizontal(ModBlocks.LOG_EUCALYPTUS.get());
        blockModels.woodProvider(ModBlocks.LOG_MAHOGANY.get()).logWithHorizontal(ModBlocks.LOG_MAHOGANY.get());
        blockModels.woodProvider(ModBlocks.LOG_MOSSBARK.get()).logWithHorizontal(ModBlocks.LOG_MOSSBARK.get());
        blockModels.woodProvider(ModBlocks.LOG_ASPER.get()).logWithHorizontal(ModBlocks.LOG_ASPER.get());
        blockModels.woodProvider(ModBlocks.LOG_CYPRESS.get()).logWithHorizontal(ModBlocks.LOG_CYPRESS.get());
        blockModels.woodProvider(ModBlocks.LOG_BALSAM.get()).logWithHorizontal(ModBlocks.LOG_BALSAM.get());
        blockModels.woodProvider(ModBlocks.LOG_BALSAM_RESINLESS.get()).logWithHorizontal(ModBlocks.LOG_BALSAM_RESINLESS.get());
        blockModels.woodProvider(ModBlocks.LOG_ROTTEN.get()).logWithHorizontal(ModBlocks.LOG_ROTTEN.get());
        blockModels.woodProvider(ModBlocks.LOG_MARSHWOOD.get()).logWithHorizontal(ModBlocks.LOG_MARSHWOOD.get());
        blockModels.woodProvider(ModBlocks.LOG_SCORCHED.get()).logWithHorizontal(ModBlocks.LOG_SCORCHED.get());

        // MARK: Saplings
        createCrossBlock(ModBlocks.SAPLING_ASPER);
        createCrossBlock(ModBlocks.SAPLING_BALSAM);
        createCrossBlock(ModBlocks.SAPLING_BAMBOO);
        createCrossBlock(ModBlocks.SAPLING_BAOBAB);
        createCrossBlock(ModBlocks.SAPLING_CYPRESS);
        createCrossBlock(ModBlocks.SAPLING_EUCALYPTUS);
        createCrossBlock(ModBlocks.SAPLING_MAHOGANY);
        createCrossBlock(ModBlocks.SAPLING_MARSHWOOD);
        createCrossBlock(ModBlocks.SAPLING_MOSSBARK);

        // MARK: Leaves
        createBlock(ModBlocks.LEAVES_ASPER);
        createBlock(ModBlocks.LEAVES_BALSAM);
        createBlock(ModBlocks.LEAVES_BAOBAB);
        createBlock(ModBlocks.LEAVES_CYPRESS);
        createBlock(ModBlocks.LEAVES_EUCALYPTUS);
        createBlock(ModBlocks.LEAVES_MAHOGANY);
        createBlock(ModBlocks.LEAVES_MARSHWOOD);
        createBlock(ModBlocks.LEAVES_MOSSBARK);

        // MARK: Plants
        blockModels.createCropBlock(ModBlocks.CROP_TURNIP.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.CROP_CABBAGE.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.CROP_MANDRAKE.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        createBush(ModBlocks.JADE_BERRY_BUSH, ModItems.JADE_BERRIES);
        createBush(ModBlocks.HEART_BERRY_BUSH, ModItems.HEART_BERRIES);
        createBush(ModBlocks.SWAMP_BERRY_BUSH, ModItems.SWAMP_BERRIES);
        createCrossBlock(ModBlocks.NETTLE);
        createCrossBlock(ModBlocks.NETTLE_FLOWERED);
        createCrossBlock(ModBlocks.SWAMP_PLANT);
        createCrossBlock(ModBlocks.FIRE_BLOOM);
        createCrossBlockTinted(ModBlocks.FIDDLE_HEAD);

        createCrossBlock(ModBlocks.HANGING_WEB);
        createMushroomBlock(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.DARK_CAPPED_MUSHROOM_STEM, ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM, ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM, ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.DUTCH_CAP_MUSHROOM_STEM, ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK);
        createMushroomBlock(ModBlocks.KAIZERS_FINGERS_MUSHROOM_STEM, ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK);
        createBlock(ModBlocks.GIANT_LILY_PAD);

        createBlock(ModBlocks.PETAL_BLACK);
        createBlock(ModBlocks.PETAL_RED);
        createBlock(ModBlocks.PETAL_BROWN);
        createBlock(ModBlocks.PETAL_BLUE);
        createBlock(ModBlocks.PETAL_PURPLE);
        createBlock(ModBlocks.PETAL_CYAN);
        createBlock(ModBlocks.PETAL_LIGHT_GRAY);
        createBlock(ModBlocks.PETAL_GRAY);
        createBlock(ModBlocks.PETAL_PINK);
        createBlock(ModBlocks.PETAL_YELLOW);
        createBlock(ModBlocks.PETAL_LIGHT_BLUE);
        createBlock(ModBlocks.PETAL_MAGENTA);
        createBlock(ModBlocks.PETAL_ORANGE);
        createBlock(ModBlocks.PETAL_WHITE);

        createStigma(ModBlocks.EXPLODING_STIGMA);
        createBlock(ModBlocks.STEM);
        createStigma(ModBlocks.STIGMA_BLACK);
        createStigma(ModBlocks.STIGMA_RED);
        createStigma(ModBlocks.STIGMA_BROWN);
        createStigma(ModBlocks.STIGMA_BLUE);
        createStigma(ModBlocks.STIGMA_PURPLE);
        createStigma(ModBlocks.STIGMA_CYAN);
        createStigma(ModBlocks.STIGMA_LIGHT_GRAY);
        createStigma(ModBlocks.STIGMA_GRAY);
        createStigma(ModBlocks.STIGMA_PINK);
        createStigma(ModBlocks.STIGMA_YELLOW);
        createStigma(ModBlocks.STIGMA_LIGHT_BLUE);
        createStigma(ModBlocks.STIGMA_MAGENTA);
        createStigma(ModBlocks.STIGMA_ORANGE);
        createStigma(ModBlocks.STIGMA_WHITE);

        createDoublePlant(ModBlocks.BULLRUSH);
        createDoublePlant(ModBlocks.WEEPING_BLUEBELL);
        createDoublePlant(ModBlocks.SUNDEW);
        createDoublePlant(ModBlocks.TALL_BLOOM);
        createDoublePlant(ModBlocks.TANGLED_STALK);
        createDoublePlant(ModBlocks.HIGH_CAPPED_MUSHROOM);

        // MARK: Other
        createGaeanKeystone();
        createChests();
        createBlock(ModBlocks.PORTAL);
        createBlock(ModBlocks.JADE_BLOCK);
        createBlock(ModBlocks.MUD);
        createBlock(ModBlocks.QUICK_SAND);
        createBlock(ModBlocks.GHOST_SAND);
        createBlock(ModBlocks.RED_GEM_BLOCK);

        MultiVariant off = plainVariant(TexturedModel.CUBE.create(ModBlocks.RED_GEM_LAMP.get(), blockModels.modelOutput));
        MultiVariant on = plainVariant(blockModels.createSuffixedVariant(ModBlocks.RED_GEM_LAMP.get(), "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.RED_GEM_LAMP.get()).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, on, off)));

        createCrossBlock(ModBlocks.WITHER_WEB);
        createCrossBlock(ModBlocks.LAVA_WEB);
        createBlock(ModBlocks.GNEISS);
        createBlock(ModBlocks.GNEISS_CARVED);
        createBlock(ModBlocks.GNEISS_RELIEF);
        createBlock(ModBlocks.GNEISS_BRICKS);
        createBlock(ModBlocks.GNEISS_SMOOTH);
        createBlock(ModBlocks.GNEISS_TILES);
        createBlock(ModBlocks.GNEISS_TILES_CRACKED);
        createBlock(ModBlocks.TEMPLE_BRICK);
        createBlock(ModBlocks.TEMPLE_PILLAR);
        createBlock(ModBlocks.TEMPLE_TILE);
        createBlock(ModBlocks.SILK);
        createBlock(ModBlocks.REIN_EXO);

        createHoneyTreat();
        createCandleHoneyTreat(Blocks.CANDLE, ModBlocks.CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.WHITE_CANDLE, ModBlocks.WHITE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.ORANGE_CANDLE, ModBlocks.ORANGE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.MAGENTA_CANDLE, ModBlocks.MAGENTA_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.LIGHT_BLUE_CANDLE, ModBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.YELLOW_CANDLE, ModBlocks.YELLOW_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.LIME_CANDLE, ModBlocks.LIME_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.PINK_CANDLE, ModBlocks.PINK_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.GRAY_CANDLE, ModBlocks.GRAY_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.LIGHT_GRAY_CANDLE, ModBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.CYAN_CANDLE, ModBlocks.CYAN_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.PURPLE_CANDLE, ModBlocks.PURPLE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.BLUE_CANDLE, ModBlocks.BLUE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.BROWN_CANDLE, ModBlocks.BROWN_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.GREEN_CANDLE, ModBlocks.GREEN_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.RED_CANDLE, ModBlocks.RED_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(Blocks.BLACK_CANDLE, ModBlocks.BLACK_CANDLE_HONEY_TREAT.get());

        // MARK: Spawners
        createBlock(ModBlocks.ANTLION_SPAWNER);
        createBlock(ModBlocks.DRAGON_FLY_SPAWNER);
        createBlock(ModBlocks.JUMPING_SPIDER_SPAWNER);
        createBlock(ModBlocks.SPIDER_SPAWNER);
        createBlock(ModBlocks.TARANTULA_SPAWNER);
        createBlock(ModBlocks.WASP_SPAWNER);
        createBlock(ModBlocks.ZOMBIE_ANT_SPAWNER);
        createBlock(ModBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER);
        createBlock(ModBlocks.MAGMA_CRAWLER_SPAWNER);
        createBlock(ModBlocks.LOCUST_SPAWNER);

        // MARK: Utility Blocks
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.PETRIFIED_CRAFTING_TABLE.get(), plainVariant(ModelTemplates.CUBE.create(ModBlocks.PETRIFIED_CRAFTING_TABLE.get(), craftingTable(ModBlocks.PETRIFIED_CRAFTING_TABLE.get(), ModBlocks.PLANKS_PETRIFIED.get()), blockModels.modelOutput))));
        blockModels.createFurnace(ModBlocks.UMBER_FURNACE.get(), TexturedModel.ORIENTABLE_ONLY_TOP);

        // MARK: Antlion Dungeon
        createBlock(ModBlocks.CAPSTONE);
        createBlock(ModBlocks.CAPSTONE_MUD);
        createBlock(ModBlocks.CAPSTONE_IRON);
        createBlock(ModBlocks.CAPSTONE_GOLD);
        createBlock(ModBlocks.CAPSTONE_JADE);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_JADE);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_EXO);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_CREAM);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_EYE);
        createBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_STRING);
        createBlock(ModBlocks.TEMPLE_TELEPORTER);
        createBlock(ModBlocks.FORCE_FIELD);
        createBlock(ModBlocks.FORCE_LOCK);
        createBlock(ModBlocks.ANT_HILL_BLOCK);

        // MARK: Custom
        createCustomBlock(ModBlocks.ANTLION_EGG);
        createCustomBlock(ModBlocks.TARANTULA_EGG);
        createCustomHorizontalBlock(ModBlocks.ALTAR_BASE);
        createCustomHorizontalBlock(ModBlocks.ALTAR_EXPERIENCE);
        createSpecialItem(ModBlocks.ALTAR_EXPERIENCE, "altar_experience_special", new ExperienceAltarSpecialRenderer.Unbaked(Erebus.prefix("")));
        createCustomHorizontalBlock(ModBlocks.ALTAR_HEALING);
        createSpecialItem(ModBlocks.ALTAR_HEALING, "altar_healing_special", new HealingAltarSpecialRenderer.Unbaked(Erebus.prefix("")));
        createCustomHorizontalBlock(ModBlocks.ALTAR_LIGHTNING);
        createSpecialItem(ModBlocks.ALTAR_LIGHTNING, "altar_lightning_special", new LightningAltarSpecialRenderer.Unbaked(Erebus.prefix("")));
        createCustomHorizontalBlock(ModBlocks.ALTAR_REPAIR);
        createSpecialItem(ModBlocks.ALTAR_REPAIR, "altar_repair_special", new RepairAltarSpecialRenderer.Unbaked(Erebus.prefix("")));
        createCustomHorizontalBlock(ModBlocks.BAMBOO_BRIDGE);
        createCustomBlock(ModBlocks.OFFERING_ALTAR);
        createSpecialItem(ModBlocks.OFFERING_ALTAR, "offering_altar_special", new OfferingAltarSpecialRenderer.Unbaked(Erebus.prefix("offering_altar")));
        createCustomBlock(ModBlocks.FLOWER_BLACK);
        createCustomBlock(ModBlocks.FLOWER_BLUE);
        createCustomBlock(ModBlocks.FLOWER_BROWN);
        createCustomBlock(ModBlocks.FLOWER_CYAN);
        createCustomBlock(ModBlocks.FLOWER_GRAY);
        createCustomBlock(ModBlocks.FLOWER_LIGHT_BLUE);
        createCustomBlock(ModBlocks.FLOWER_LIGHT_GRAY);
        createCustomBlock(ModBlocks.FLOWER_MAGENTA);
        createCustomBlock(ModBlocks.FLOWER_ORANGE);
        createCustomBlock(ModBlocks.FLOWER_PINK);
        createCustomBlock(ModBlocks.FLOWER_PURPLE);
        createCustomBlock(ModBlocks.FLOWER_RED);
        createCustomBlock(ModBlocks.FLOWER_WHITE);
        createCustomBlock(ModBlocks.FLOWER_YELLOW);
        createCustomBlock(ModBlocks.FLOWER_RAINBOW);
        createCustomBlock(ModBlocks.FLUID_ANTI_VENOM_BLOCK);
        createCustomBlock(ModBlocks.FLUID_BEETLE_JUICE_BLOCK);
        createCustomBlock(ModBlocks.FLUID_FORMIC_ACID_BLOCK);
        createCustomBlock(ModBlocks.FLUID_HONEY_BLOCK);
        createCustomBlock(ModBlocks.BAMBOO_NERD_POLE);
        createCustomHorizontalBlock(ModBlocks.BAMBOO_LADDER);
        createCustomHorizontalBlock(ModBlocks.BLENDER);
        createCustomBlock(ModBlocks.COMPOSTER);
        createCustomBlock(ModBlocks.DESERT_SHRUB);
        createCustomBlock(ModBlocks.FERN);
        createCustomBlock(ModBlocks.FLUID_JAR);
        createCustomBlock(ModBlocks.GLOWING_JAR);
        createCustomBlock(ModBlocks.GLOWSHROOM_BLOCK);
        createCustomBlock(ModBlocks.HONEY_COMB);
        createCustomBlock(ModBlocks.SWAMP_VENT);

        createBlockOfBones();
    }
}
