package erebus.datagen;

import erebus.block.HoneyTreatBlock;
import erebus.block.plants.DarkFruitVineBlock;
import erebus.block.plants.ModBerryBushBlock;
import erebus.block.plants.ModCropBlock;
import erebus.registries.ModBlockFamilies;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.client.color.item.GrassColorSource;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Blocks;

import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;
import static net.minecraft.client.data.models.model.TextureMapping.craftingTable;

public class ModBlockStates {

    protected void registerModels(BlockModelGenerators blockModels) {
        ModBlockFamilies.getAllFamilies()
                .filter(BlockFamily::shouldGenerateModel)
                        .forEach(family -> blockModels.family(family.getBaseBlock()).generateFor(family));


        // MARK: Umberstone
        blockModels.createTrivialCube(ModBlocks.UMBERSTONE.get());
        blockModels.createTrivialCube(ModBlocks.UMBERSTONE_BRICKS.get());
        blockModels.createTrivialCube(ModBlocks.UMBERCOBBLE.get());
        blockModels.createTrivialCube(ModBlocks.UMBERCOBBLE_MOSSY.get());
        blockModels.createTrivialCube(ModBlocks.UMBERCOBBLE_WEBBED.get());
        blockModels.createTrivialCube(ModBlocks.UMBERTILE_SMOOTH.get());
        blockModels.createTrivialCube(ModBlocks.UMBERTILE_SMOOTH_SMALL.get());
        blockModels.createTrivialCube(ModBlocks.UMBERGRAVEL.get());
        blockModels.createTrivialCube(ModBlocks.UMBERPAVER.get());
        blockModels.createTrivialCube(ModBlocks.UMBERPAVER_MOSSY.get());
        blockModels.createTrivialCube(ModBlocks.UMBERPAVER_WEBBED.get());
        blockModels.createRotatedPillarWithHorizontalVariant(ModBlocks.UMBERSTONE_PILLAR.get(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        blockModels.createTrivialCube(ModBlocks.VOLCANIC_ROCK.get());
        blockModels.createTrivialCube(ModBlocks.DUST.get());
        blockModels.createTrivialCube(ModBlocks.DUST_LAYER.get());
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

        blockModels.createTrivialCube(ModBlocks.MIR_BRICKS.get());
        blockModels.createTrivialCube(ModBlocks.MUD_BRICKS.get());

        // MARK: Amber
        blockModels.createTrivialCube(ModBlocks.AMBER.get());
        blockModels.createTrivialCube(ModBlocks.AMBER_BRICKS.get());
        blockModels.createTrivialCube(ModBlocks.PRESERVED_AMBER.get());
        blockModels.createTrivialCube(ModBlocks.PRESERVED_AMBER_GLASS.get());
        blockModels.createDoor(ModBlocks.AMBER_DOOR.get());

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
        blockModels.woodProvider(ModBlocks.LOG_BAOBAB.get()).log(ModBlocks.LOG_BAOBAB.get());
        blockModels.woodProvider(ModBlocks.LOG_EUCALYPTUS.get()).log(ModBlocks.LOG_EUCALYPTUS.get());
        blockModels.woodProvider(ModBlocks.LOG_MAHOGANY.get()).log(ModBlocks.LOG_MAHOGANY.get());
        blockModels.woodProvider(ModBlocks.LOG_MOSSBARK.get()).log(ModBlocks.LOG_MOSSBARK.get());
        blockModels.woodProvider(ModBlocks.LOG_ASPER.get()).log(ModBlocks.LOG_ASPER.get());
        blockModels.woodProvider(ModBlocks.LOG_CYPRESS.get()).log(ModBlocks.LOG_CYPRESS.get());
        blockModels.woodProvider(ModBlocks.LOG_BALSAM.get()).log(ModBlocks.LOG_BALSAM.get());
        blockModels.woodProvider(ModBlocks.LOG_BALSAM_RESINLESS.get()).log(ModBlocks.LOG_BALSAM_RESINLESS.get());
        blockModels.woodProvider(ModBlocks.LOG_ROTTEN.get()).log(ModBlocks.LOG_ROTTEN.get());
        blockModels.woodProvider(ModBlocks.LOG_MARSHWOOD.get()).log(ModBlocks.LOG_MARSHWOOD.get());
        blockModels.woodProvider(ModBlocks.LOG_SCORCHED.get()).log(ModBlocks.LOG_SCORCHED.get());

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

        // MARK: Walls
        wall(ModBlocks.WALL_UMBERSTONE, ModBlocks.UMBERSTONE);
        wall(ModBlocks.WALL_UMBERCOBBLE, ModBlocks.UMBERCOBBLE);
        wall(ModBlocks.WALL_UMBERCOBBLE_MOSSY, ModBlocks.UMBERCOBBLE_MOSSY);
        wall(ModBlocks.WALL_UMBERCOBBLE_WEBBED, ModBlocks.UMBERCOBBLE_WEBBED);
        wall(ModBlocks.WALL_UMBERSTONE_BRICKS, ModBlocks.UMBERSTONE_BRICKS);
        wall(ModBlocks.WALL_UMBERTILE_SMOOTH, ModBlocks.UMBERTILE_SMOOTH);
        wall(ModBlocks.WALL_UMBERTILE_SMOOTH_SMALL, ModBlocks.UMBERTILE_SMOOTH_SMALL);
        wall(ModBlocks.WALL_UMBERPAVER, ModBlocks.UMBERPAVER);
        wall(ModBlocks.WALL_UMBERPAVER_MOSSY, ModBlocks.UMBERPAVER_MOSSY);
        wall(ModBlocks.WALL_UMBERPAVER_WEBBED, ModBlocks.UMBERPAVER_WEBBED);
        wallTranslucent(ModBlocks.WALL_AMBER, ModBlocks.AMBER);
        wallTranslucent(ModBlocks.WALL_AMBER_BRICKS, ModBlocks.AMBER_BRICKS);

        // MARK: Plants
        blockModels.createCropBlock(ModBlocks.CROP_TURNIP.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.CROP_CABBAGE.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.CROP_MANDRAKE.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.JADE_BERRY_BUSH.get(), ModBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.HEART_BERRY_BUSH.get(), ModBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.SWAMP_BERRY_BUSH.get(), ModBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(ModBlocks.DARK_FRUIT_VINE.get(), DarkFruitVineBlock.AGE, 0, 1, 2, 3);
        //crossBlock(MIRE_CORAL); // TODO: I don't think this is implemented
        blockModels.createCrossBlock(ModBlocks.NETTLE.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(ModBlocks.NETTLE_FLOWERED.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(ModBlocks.SWAMP_PLANT.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(ModBlocks.FIRE_BLOOM.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(ModBlocks.FIDDLE_HEAD.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.registerSimpleTintedItemModel(ModBlocks.THORNS.get(), blockModels.createFlatItemModelWithBlockTexture(ModBlocks.THORNS.get().asItem(), ModBlocks.THORNS.get()), new GrassColorSource());
        simpleBlock(ModBlocks.ALGAE.get(), models().getExistingFile(modLoc("block/algae")));
        crossBlock(ModBlocks.HANGING_WEB.get());
        hugeMushroom(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK.get());
        hugeMushroom(ModBlocks.DARK_CAPPED_MUSHROOM_STEM.get());
        hugeMushroom(ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK.get());
        hugeMushroom(ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM.get());
        hugeMushroom(ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK.get());
        hugeMushroom(ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM.get());
        hugeMushroom(ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK.get());
        hugeMushroom(ModBlocks.DUTCH_CAP_MUSHROOM_STEM.get());
        hugeMushroom(ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK.get());
        hugeMushroom(ModBlocks.KAIZERS_FINGERS_MUSHROOM_STEM.get());
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

        stigma(ModBlocks.EXPLODING_STIGMA.get());
        blockModels.createTrivialCube(ModBlocks.STEM.get());
        stigma(ModBlocks.STIGMA_BLACK.get());
        stigma(ModBlocks.STIGMA_RED.get());
        stigma(ModBlocks.STIGMA_BROWN.get());
        stigma(ModBlocks.STIGMA_BLUE.get());
        stigma(ModBlocks.STIGMA_PURPLE.get());
        stigma(ModBlocks.STIGMA_CYAN.get());
        stigma(ModBlocks.STIGMA_LIGHT_GRAY.get());
        stigma(ModBlocks.STIGMA_GRAY.get());
        stigma(ModBlocks.STIGMA_PINK.get());
        stigma(ModBlocks.STIGMA_YELLOW.get());
        stigma(ModBlocks.STIGMA_LIGHT_BLUE.get());
        stigma(ModBlocks.STIGMA_MAGENTA.get());
        stigma(ModBlocks.STIGMA_ORANGE.get());
        stigma(ModBlocks.STIGMA_WHITE.get());

        doubleCrossBlock(ModBlocks.BULLRUSH.get());
        doubleCrossBlock(ModBlocks.WEEPING_BLUEBELL.get());
        doubleCrossBlock(ModBlocks.SUNDEW.get());
        doubleCrossBlock(ModBlocks.TALL_BLOOM.get());
        doubleCrossBlock(ModBlocks.TANGLED_STALK.get());
        doubleCrossBlock(ModBlocks.HIGH_CAPPED_MUSHROOM.get());

        // MARK: Other
        blockModels.createTrivialCube(ModBlocks.PORTAL.get());
        blockModels.createTrivialCube(ModBlocks.JADE_BLOCK.get());
        blockModels.createTrivialCube(ModBlocks.MUD.get());
        blockModels.createTrivialCube(ModBlocks.QUICK_SAND.get());
        blockModels.createTrivialCube(ModBlocks.GHOST_SAND.get());
        blockModels.createTrivialCube(ModBlocks.RED_GEM_BLOCK.get());
        lamp(ModBlocks.RED_GEM_LAMP);
        crossBlock(ModBlocks.WITHER_WEB);
        crossBlock(ModBlocks.LAVA_WEB);
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
      //  log(ModBlocks.VELOCITY_BLOCK);
       // log(ModBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED);
        createHoneyTreat(blockModels);
        blockModels.createCandleAndCandleCake(Blocks.CANDLE, ModBlocks.CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.WHITE_CANDLE, ModBlocks.WHITE_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.ORANGE_CANDLE, ModBlocks.ORANGE_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.MAGENTA_CANDLE, ModBlocks.MAGENTA_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.LIGHT_BLUE_CANDLE, ModBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.YELLOW_CANDLE, ModBlocks.YELLOW_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.LIME_CANDLE, ModBlocks.LIME_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.PINK_CANDLE, ModBlocks.PINK_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.GRAY_CANDLE, ModBlocks.GRAY_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.LIGHT_GRAY_CANDLE, ModBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.CYAN_CANDLE, ModBlocks.CYAN_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.PURPLE_CANDLE, ModBlocks.PURPLE_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.BLUE_CANDLE, ModBlocks.BLUE_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.BROWN_CANDLE, ModBlocks.BROWN_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.GREEN_CANDLE, ModBlocks.GREEN_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.RED_CANDLE, ModBlocks.RED_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.BLACK_CANDLE, ModBlocks.BLACK_CANDLE_HONEY_TREAT.get());
        blockModels.createTrivialCube(ModBlocks.WASP_NEST.get());
        stairs(ModBlocks.STAIRS_WASP_NEST, ModBlocks.WASP_NEST);

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
        craftingTable(ModBlocks.PETRIFIED_CRAFTING_TABLE.get());
        furnace(ModBlocks.UMBER_FURNACE.get());
        button(ModBlocks.UMBERSTONE_BUTTON.get(), ModBlocks.UMBERSTONE.get());

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
}
