package erebus.datagen;

import erebus.block.CandleHoneyTreatBlock;
import erebus.block.HoneyTreatBlock;
import erebus.block.plants.ModBerryBushBlock;
import erebus.block.plants.ModCropBlock;
import erebus.registries.ModBlockFamilies;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;
import static net.minecraft.client.data.models.model.TextureMapping.craftingTable;

public class ModBlockStates {

    protected void registerModels(BlockModelGenerators blockModels) {
        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                .forEach(family -> blockModels.family(family.getBaseBlock()).generateFor(family));

        // MARK: Umberstone
        blockModels.createTrivialCube(ModBlocks.UMBERGRAVEL.get());
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.UMBERSTONE_PILLAR.get(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        blockModels.createTrivialCube(ModBlocks.VOLCANIC_ROCK.get());
        blockModels.createTrivialCube(ModBlocks.DUST.get());
        blockModels.createTrivialCube(ModBlocks.PETRIFIED_WOOD_ROCK.get());
        blockModels.createTrivialCube(ModBlocks.PETRIFIED_WOOD_ROCK_2.get());
        blockModels.createTrivialCube(ModBlocks.PETRIFIED_WOOD_ROCK_3.get());
        blockModels.createTrivialCube(ModBlocks.PETRIFIED_WOOD_ROCK_4.get());
        blockModels.createTrivialCube(ModBlocks.PETRIFIED_WOOD_ROCK_5.get());
        blockModels.createTrivialCube(ModBlocks.PETRIFIED_WOOD_ROCK_6.get());
        blockModels.createTrivialCube(ModBlocks.PETRIFIED_BARK_RED.get());
        blockModels.createTrivialCube(ModBlocks.PETRIFIED_BARK_BROWN.get());
        blockModels.createTrivialCube(ModBlocks.PETRIFIED_LOG_INNER.get());
        blockModels.createTrivialCube(ModBlocks.DUNG.get());

        // MARK: Amber
        blockModels.createTrivialCube(ModBlocks.PRESERVED_AMBER.get());
        blockModels.createTrivialCube(ModBlocks.PRESERVED_AMBER_GLASS.get());

        // MARK: Ores
        blockModels.createTrivialCube(ModBlocks.ORE_IRON.get());
        blockModels.createTrivialCube(ModBlocks.ORE_GOLD.get());
        blockModels.createTrivialCube(ModBlocks.ORE_COAL.get());
        blockModels.createTrivialCube(ModBlocks.ORE_DIAMOND.get());
        blockModels.createTrivialCube(ModBlocks.ORE_EMERALD.get());
        blockModels.createTrivialCube(ModBlocks.ORE_LAPIS.get());
        blockModels.createTrivialCube(ModBlocks.ORE_QUARTZ.get());
        blockModels.createTrivialCube(ModBlocks.ORE_PETRIFIED_QUARTZ.get());
        blockModels.createTrivialCube(ModBlocks.ORE_COPPER.get());
        blockModels.createTrivialCube(ModBlocks.ORE_SILVER.get());
        blockModels.createTrivialCube(ModBlocks.ORE_TIN.get());
        blockModels.createTrivialCube(ModBlocks.ORE_LEAD.get());
        blockModels.createTrivialCube(ModBlocks.ORE_ALUMINUM.get());
        blockModels.createTrivialCube(ModBlocks.ORE_JADE.get());
        blockModels.createTrivialCube(ModBlocks.ORE_ENCRUSTED_DIAMOND.get());
        blockModels.createTrivialCube(ModBlocks.ORE_FOSSIL.get());
        blockModels.createTrivialCube(ModBlocks.ORE_GNEISS.get());
        blockModels.createTrivialCube(ModBlocks.ORE_PETRIFIED_WOOD.get());
        blockModels.createTrivialCube(ModBlocks.ORE_TEMPLE.get());

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
        blockModels.createCrossBlock(ModBlocks.SAPLING_BAOBAB.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_EUCALYPTUS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_MAHOGANY.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_MOSSBARK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_ASPER.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_CYPRESS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_BALSAM.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_MARSHWOOD.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SAPLING_BAMBOO.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        // MARK: Leaves
        blockModels.createTintedLeaves(ModBlocks.LEAVES_BAOBAB.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_EUCALYPTUS.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_MAHOGANY.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_MOSSBARK.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_ASPER.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_CYPRESS.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_BALSAM.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(ModBlocks.LEAVES_MARSHWOOD.get(), TexturedModel.LEAVES, -50000);

        // MARK: Plants
        blockModels.createCropBlock(ModBlocks.CROP_TURNIP.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.CROP_CABBAGE.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.CROP_MANDRAKE.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.JADE_BERRY_BUSH.get(), ModBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.HEART_BERRY_BUSH.get(), ModBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.SWAMP_BERRY_BUSH.get(), ModBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModels.createCrossBlock(ModBlocks.NETTLE.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.NETTLE_FLOWERED.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.SWAMP_PLANT.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(ModBlocks.FIRE_BLOOM.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(ModBlocks.FIDDLE_HEAD.get(), BlockModelGenerators.PlantType.TINTED);

        blockModels.createCrossBlock(ModBlocks.HANGING_WEB.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createMushroomBlock(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK.get());
        blockModels.createMushroomBlock(ModBlocks.DARK_CAPPED_MUSHROOM_STEM.get());
        blockModels.createMushroomBlock(ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK.get());
        blockModels.createMushroomBlock(ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM.get());
        blockModels.createMushroomBlock(ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK.get());
        blockModels.createMushroomBlock(ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM.get());
        blockModels.createMushroomBlock(ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK.get());
        blockModels.createMushroomBlock(ModBlocks.DUTCH_CAP_MUSHROOM_STEM.get());
        blockModels.createMushroomBlock(ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK.get());
        blockModels.createMushroomBlock(ModBlocks.KAIZERS_FINGERS_MUSHROOM_STEM.get());
        blockModels.createTrivialCube(ModBlocks.GIANT_LILY_PAD.get());

        blockModels.createTrivialCube(ModBlocks.PETAL_BLACK.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_RED.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_BROWN.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_BLUE.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_PURPLE.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_CYAN.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_LIGHT_GRAY.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_GRAY.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_PINK.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_YELLOW.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_LIGHT_BLUE.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_MAGENTA.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_ORANGE.get());
        blockModels.createTrivialCube(ModBlocks.PETAL_WHITE.get());

        blockModels.createTrivialCube(ModBlocks.EXPLODING_STIGMA.get());
        blockModels.createTrivialCube(ModBlocks.STEM.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_BLACK.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_RED.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_BROWN.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_BLUE.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_PURPLE.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_CYAN.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_LIGHT_GRAY.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_GRAY.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_PINK.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_YELLOW.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_LIGHT_BLUE.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_MAGENTA.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_ORANGE.get());
        blockModels.createTrivialCube(ModBlocks.STIGMA_WHITE.get());

        blockModels.createDoublePlant(ModBlocks.BULLRUSH.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createDoublePlant(ModBlocks.WEEPING_BLUEBELL.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createDoublePlant(ModBlocks.SUNDEW.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createDoublePlant(ModBlocks.TALL_BLOOM.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createDoublePlant(ModBlocks.TANGLED_STALK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createDoublePlant(ModBlocks.HIGH_CAPPED_MUSHROOM.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        // MARK: Other
        blockModels.createTrivialCube(ModBlocks.PORTAL.get());
        blockModels.createTrivialCube(ModBlocks.JADE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.MUD.get());
        blockModels.createTrivialCube(ModBlocks.QUICK_SAND.get());
        blockModels.createTrivialCube(ModBlocks.GHOST_SAND.get());
        blockModels.createTrivialCube(ModBlocks.RED_GEM_BLOCK.get());

        MultiVariant off = plainVariant(TexturedModel.CUBE.create(ModBlocks.RED_GEM_LAMP.get(), blockModels.modelOutput));
        MultiVariant on = plainVariant(blockModels.createSuffixedVariant(ModBlocks.RED_GEM_LAMP.get(), "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube));
        blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.RED_GEM_LAMP.get()).with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, on, off)));

        blockModels.createCrossBlock(ModBlocks.WITHER_WEB.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(ModBlocks.LAVA_WEB.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createTrivialCube(ModBlocks.GNEISS.get());
        blockModels.createTrivialCube(ModBlocks.GNEISS_CARVED.get());
        blockModels.createTrivialCube(ModBlocks.GNEISS_RELIEF.get());
        blockModels.createTrivialCube(ModBlocks.GNEISS_BRICKS.get());
        blockModels.createTrivialCube(ModBlocks.GNEISS_SMOOTH.get());
        blockModels.createTrivialCube(ModBlocks.GNEISS_TILES.get());
        blockModels.createTrivialCube(ModBlocks.GNEISS_TILES_CRACKED.get());
        blockModels.createTrivialCube(ModBlocks.TEMPLE_BRICK.get());
        blockModels.createTrivialCube(ModBlocks.TEMPLE_PILLAR.get());
        blockModels.createTrivialCube(ModBlocks.TEMPLE_TILE.get());
        blockModels.createTrivialCube(ModBlocks.SILK.get());
        blockModels.createTrivialCube(ModBlocks.REIN_EXO.get());

        createHoneyTreat(blockModels);
        createCandleHoneyTreat(blockModels, Blocks.CANDLE, ModBlocks.CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.WHITE_CANDLE, ModBlocks.WHITE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.ORANGE_CANDLE, ModBlocks.ORANGE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.MAGENTA_CANDLE, ModBlocks.MAGENTA_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.LIGHT_BLUE_CANDLE, ModBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.YELLOW_CANDLE, ModBlocks.YELLOW_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.LIME_CANDLE, ModBlocks.LIME_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.PINK_CANDLE, ModBlocks.PINK_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.GRAY_CANDLE, ModBlocks.GRAY_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.LIGHT_GRAY_CANDLE, ModBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.CYAN_CANDLE, ModBlocks.CYAN_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.PURPLE_CANDLE, ModBlocks.PURPLE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.BLUE_CANDLE, ModBlocks.BLUE_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.BROWN_CANDLE, ModBlocks.BROWN_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.GREEN_CANDLE, ModBlocks.GREEN_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.RED_CANDLE, ModBlocks.RED_CANDLE_HONEY_TREAT.get());
        createCandleHoneyTreat(blockModels, Blocks.BLACK_CANDLE, ModBlocks.BLACK_CANDLE_HONEY_TREAT.get());

        // MARK: Spawners
        blockModels.createTrivialCube(ModBlocks.ANTLION_SPAWNER.get());
        blockModels.createTrivialCube(ModBlocks.DRAGON_FLY_SPAWNER.get());
        blockModels.createTrivialCube(ModBlocks.JUMPING_SPIDER_SPAWNER.get());
        blockModels.createTrivialCube(ModBlocks.SPIDER_SPAWNER.get());
        blockModels.createTrivialCube(ModBlocks.TARANTULA_SPAWNER.get());
        blockModels.createTrivialCube(ModBlocks.WASP_SPAWNER.get());
        blockModels.createTrivialCube(ModBlocks.ZOMBIE_ANT_SPAWNER.get());
        blockModels.createTrivialCube(ModBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER.get());
        blockModels.createTrivialCube(ModBlocks.MAGMA_CRAWLER_SPAWNER.get());
        blockModels.createTrivialCube(ModBlocks.LOCUST_SPAWNER.get());

        // MARK: Utility Blocks
        blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(ModBlocks.PETRIFIED_CRAFTING_TABLE.get(), plainVariant(ModelTemplates.CUBE.create(ModBlocks.PETRIFIED_CRAFTING_TABLE.get(), craftingTable(ModBlocks.PETRIFIED_CRAFTING_TABLE.get(), ModBlocks.PLANKS_PETRIFIED.get()), blockModels.modelOutput))));
        blockModels.createFurnace(ModBlocks.UMBER_FURNACE.get(), TexturedModel.ORIENTABLE_ONLY_TOP);

        // MARK: Antlion Dungeon
        blockModels.createTrivialCube(ModBlocks.CAPSTONE.get());
        blockModels.createTrivialCube(ModBlocks.CAPSTONE_MUD.get());
        blockModels.createTrivialCube(ModBlocks.CAPSTONE_IRON.get());
        blockModels.createTrivialCube(ModBlocks.CAPSTONE_GOLD.get());
        blockModels.createTrivialCube(ModBlocks.CAPSTONE_JADE.get());
        blockModels.createTrivialCube(ModBlocks.TEMPLE_BRICK_UNBREAKING.get());
        blockModels.createTrivialCube(ModBlocks.TEMPLE_BRICK_UNBREAKING_JADE.get());
        blockModels.createTrivialCube(ModBlocks.TEMPLE_BRICK_UNBREAKING_EXO.get());
        blockModels.createTrivialCube(ModBlocks.TEMPLE_BRICK_UNBREAKING_CREAM.get());
        blockModels.createTrivialCube(ModBlocks.TEMPLE_BRICK_UNBREAKING_EYE.get());
        blockModels.createTrivialCube(ModBlocks.TEMPLE_BRICK_UNBREAKING_STRING.get());
        blockModels.createTrivialCube(ModBlocks.TEMPLE_TELEPORTER.get());
        blockModels.createTrivialCube(ModBlocks.FORCE_FIELD.get());
        blockModels.createTrivialCube(ModBlocks.FORCE_LOCK.get());
        blockModels.createTrivialCube(ModBlocks.ANT_HILL_BLOCK.get());

        // MARK: Custom
        blockModels.createNonTemplateModelBlock(ModBlocks.ANTLION_EGG.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.TARANTULA_EGG.get());
        blockModels.createNonTemplateHorizontalBlock(ModBlocks.ALTAR_BASE.get());
        blockModels.createNonTemplateHorizontalBlock(ModBlocks.ALTAR_EXPERIENCE.get());
        blockModels.createNonTemplateHorizontalBlock(ModBlocks.ALTAR_HEALING.get());
        blockModels.createNonTemplateHorizontalBlock(ModBlocks.ALTAR_LIGHTNING.get());
        blockModels.createNonTemplateHorizontalBlock(ModBlocks.ALTAR_REPAIR.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_BLACK.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_BLUE.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_BROWN.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_CYAN.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_GRAY.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_LIGHT_BLUE.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_LIGHT_GRAY.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_MAGENTA.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_ORANGE.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_PINK.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_PURPLE.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_RED.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_WHITE.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_YELLOW.get());
        blockModels.createNonTemplateModelBlock(ModBlocks.FLOWER_RAINBOW.get());
    }

    public void createHoneyTreat(BlockModelGenerators blockModels) {
        blockModels.registerSimpleFlatItemModel(ModBlocks.HONEY_TREAT.get().asItem());
        blockModels.blockStateOutput.accept(MultiVariantGenerator
                .dispatch(ModBlocks.HONEY_TREAT.get())
                .with(PropertyDispatch
                        .initial(HoneyTreatBlock.BITES)
                        .select(0, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get())))
                        .select(1, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice1")))
                        .select(2, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice2")))
                        .select(3, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice3")))
                        .select(4, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice4")))
                        .select(5, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice5")))
                        .select(6, plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.HONEY_TREAT.get(), "_slice6")))
                )
        );
    }

    public void createCandleHoneyTreat(BlockModelGenerators blockModels, Block candleBlock, CandleHoneyTreatBlock candleCakeBlock) {
        MultiVariant candleCake = plainVariant(
                ModelTemplates.CANDLE_CAKE.create(
                        candleCakeBlock,
                        candleHoneyTreat(candleBlock, false),
                        blockModels.modelOutput
                )
        );
        MultiVariant litCandleCake = plainVariant(
                ModelTemplates.CANDLE_CAKE.createWithSuffix(
                        candleCakeBlock,
                        "_lit",
                        candleHoneyTreat(candleBlock, true),
                        blockModels.modelOutput
                )
        );

        blockModels.blockStateOutput.accept(
                MultiVariantGenerator
                        .dispatch(candleCakeBlock)
                        .with(BlockModelGenerators.createBooleanModelDispatch(BlockStateProperties.LIT, litCandleCake, candleCake))
        );
    }

    public static TextureMapping candleHoneyTreat(Block block, boolean lit) {
        return (new TextureMapping())
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(ModBlocks.HONEY_TREAT.get(), "_side"))
                .put(TextureSlot.BOTTOM, TextureMapping.getBlockTexture(ModBlocks.HONEY_TREAT.get(), "_bottom"))
                .put(TextureSlot.TOP, TextureMapping.getBlockTexture(ModBlocks.HONEY_TREAT.get(), "_top"))
                .put(TextureSlot.SIDE, TextureMapping.getBlockTexture(ModBlocks.HONEY_TREAT.get(), "_side"))
                .put(TextureSlot.CANDLE, TextureMapping.getBlockTexture(block, lit ? "_lit" : ""));
    }
}
