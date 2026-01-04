package erebus.datagen;

import erebus.block.HoneyTreatBlock;
import erebus.block.plants.DarkFruitVineBlock;
import erebus.block.plants.ModBerryBushBlock;
import erebus.block.plants.ModCropBlock;
import erebus.registries.ModBlockFamilies;
import erebus.registries.blocks.providers.*;
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
        blockModels.createTrivialCube(UmberstoneBlocks.UMBERSTONE.get());
        blockModels.createTrivialCube(UmberstoneBlocks.UMBERSTONE_BRICKS.get());
        blockModels.createTrivialCube(UmberstoneBlocks.UMBERCOBBLE.get());
        blockModels.createTrivialCube(UmberstoneBlocks.UMBERCOBBLE_MOSSY.get());
        blockModels.createTrivialCube(UmberstoneBlocks.UMBERCOBBLE_WEBBED.get());
        blockModels.createTrivialCube(UmberstoneBlocks.UMBERTILE_SMOOTH.get());
        blockModels.createTrivialCube(UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL.get());
        blockModels.createTrivialCube(UmberstoneBlocks.UMBERGRAVEL.get());
        blockModels.createTrivialCube(UmberstoneBlocks.UMBERPAVER.get());
        blockModels.createTrivialCube(UmberstoneBlocks.UMBERPAVER_MOSSY.get());
        blockModels.createTrivialCube(UmberstoneBlocks.UMBERPAVER_WEBBED.get());
        blockModels.createRotatedPillarWithHorizontalVariant(UmberstoneBlocks.UMBERSTONE_PILLAR.get(), TexturedModel.COLUMN_ALT, TexturedModel.COLUMN_HORIZONTAL_ALT);
        blockModels.createTrivialCube(UmberstoneBlocks.VOLCANIC_ROCK.get());
        blockModels.createTrivialCube(UmberstoneBlocks.DUST.get());
        blockModels.createTrivialCube(UmberstoneBlocks.DUST_LAYER.get());
        blockModels.createTrivialCube(UmberstoneBlocks.PETRIFIED_WOOD_ROCK.get());
        blockModels.createTrivialCube(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_2.get());
        blockModels.createTrivialCube(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_3.get());
        blockModels.createTrivialCube(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_4.get());
        blockModels.createTrivialCube(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_5.get());
        blockModels.createTrivialCube(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_6.get());
        blockModels.createTrivialCube(UmberstoneBlocks.PETRIFIED_BARK_RED.get());
        blockModels.createTrivialCube(UmberstoneBlocks.PETRIFIED_BARK_BROWN.get());
        blockModels.createTrivialCube(UmberstoneBlocks.PETRIFIED_LOG_INNER.get());
        blockModels.createTrivialCube(UmberstoneBlocks.DUNG.get());

        blockModels.createTrivialCube(UmberstoneBlocks.MIR_BRICKS.get());
        blockModels.createTrivialCube(UmberstoneBlocks.MUD_BRICKS.get());

        // MARK: Amber
        blockModels.createTrivialCube(AmberBlocks.AMBER.get());
        blockModels.createTrivialCube(AmberBlocks.AMBER_BRICKS.get());
        blockModels.createTrivialCube(AmberBlocks.PRESERVED_AMBER.get());
        blockModels.createTrivialCube(AmberBlocks.PRESERVED_AMBER_GLASS.get());
        blockModels.createDoor(AmberBlocks.AMBER_DOOR.get());

        // MARK: Ores
        blockModels.createTrivialCube(OreBlocks.IRON.get());
        blockModels.createTrivialCube(OreBlocks.GOLD.get());
        blockModels.createTrivialCube(OreBlocks.COAL.get());
        blockModels.createTrivialCube(OreBlocks.DIAMOND.get());
        blockModels.createTrivialCube(OreBlocks.EMERALD.get());
        blockModels.createTrivialCube(OreBlocks.LAPIS.get());
        blockModels.createTrivialCube(OreBlocks.QUARTZ.get());
        blockModels.createTrivialCube(OreBlocks.PETRIFIED_QUARTZ.get());
        blockModels.createTrivialCube(OreBlocks.COPPER.get());
        blockModels.createTrivialCube(OreBlocks.SILVER.get());
        blockModels.createTrivialCube(OreBlocks.TIN.get());
        blockModels.createTrivialCube(OreBlocks.LEAD.get());
        blockModels.createTrivialCube(OreBlocks.ALUMINUM.get());
        blockModels.createTrivialCube(OreBlocks.JADE.get());
        blockModels.createTrivialCube(OreBlocks.ENCRUSTED_DIAMOND.get());
        blockModels.createTrivialCube(OreBlocks.FOSSIL.get());
        blockModels.createTrivialCube(OreBlocks.GNEISS.get());
        blockModels.createTrivialCube(OreBlocks.PETRIFIED_WOOD.get());
        blockModels.createTrivialCube(OreBlocks.TEMPLE.get());

        // MARK: Logs
        blockModels.woodProvider(WoodBlocks.LOG_BAOBAB.get()).log(WoodBlocks.LOG_BAOBAB.get());
        blockModels.woodProvider(WoodBlocks.LOG_EUCALYPTUS.get()).log(WoodBlocks.LOG_EUCALYPTUS.get());
        blockModels.woodProvider(WoodBlocks.LOG_MAHOGANY.get()).log(WoodBlocks.LOG_MAHOGANY.get());
        blockModels.woodProvider(WoodBlocks.LOG_MOSSBARK.get()).log(WoodBlocks.LOG_MOSSBARK.get());
        blockModels.woodProvider(WoodBlocks.LOG_ASPER.get()).log(WoodBlocks.LOG_ASPER.get());
        blockModels.woodProvider(WoodBlocks.LOG_CYPRESS.get()).log(WoodBlocks.LOG_CYPRESS.get());
        blockModels.woodProvider(WoodBlocks.LOG_BALSAM.get()).log(WoodBlocks.LOG_BALSAM.get());
        blockModels.woodProvider(WoodBlocks.LOG_BALSAM_RESINLESS.get()).log(WoodBlocks.LOG_BALSAM_RESINLESS.get());
        blockModels.woodProvider(WoodBlocks.LOG_ROTTEN.get()).log(WoodBlocks.LOG_ROTTEN.get());
        blockModels.woodProvider(WoodBlocks.LOG_MARSHWOOD.get()).log(WoodBlocks.LOG_MARSHWOOD.get());
        blockModels.woodProvider(WoodBlocks.LOG_SCORCHED.get()).log(WoodBlocks.LOG_SCORCHED.get());

        // MARK: Saplings
        blockModels.createCrossBlock(WoodBlocks.SAPLING_BAOBAB.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(WoodBlocks.SAPLING_EUCALYPTUS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(WoodBlocks.SAPLING_MAHOGANY.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(WoodBlocks.SAPLING_MOSSBARK.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(WoodBlocks.SAPLING_ASPER.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(WoodBlocks.SAPLING_CYPRESS.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(WoodBlocks.SAPLING_BALSAM.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(WoodBlocks.SAPLING_MARSHWOOD.get(), BlockModelGenerators.PlantType.NOT_TINTED);
        blockModels.createCrossBlock(WoodBlocks.SAPLING_BAMBOO.get(), BlockModelGenerators.PlantType.NOT_TINTED);

        // MARK: Leaves
        blockModels.createTintedLeaves(WoodBlocks.LEAVES_BAOBAB.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(WoodBlocks.LEAVES_EUCALYPTUS.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(WoodBlocks.LEAVES_MAHOGANY.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(WoodBlocks.LEAVES_MOSSBARK.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(WoodBlocks.LEAVES_ASPER.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(WoodBlocks.LEAVES_CYPRESS.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(WoodBlocks.LEAVES_BALSAM.get(), TexturedModel.LEAVES, -50000);
        blockModels.createTintedLeaves(WoodBlocks.LEAVES_MARSHWOOD.get(), TexturedModel.LEAVES, -50000);

        // MARK: Planks
        blockModels.createTrivialCube(WoodBlocks.PLANKS_BAOBAB.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_EUCALYPTUS.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_MAHOGANY.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_MOSSBARK.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_ASPER.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_CYPRESS.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_BALSAM.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_WHITE.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_BAMBOO.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_ROTTEN.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_MARSHWOOD.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_SCORCHED.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_VARNISHED.get());
        blockModels.createTrivialCube(WoodBlocks.PLANKS_PETRIFIED.get());

        // MARK: Slabs Wood
        blockModels.family(SlabBlocks.BAOBAB.get()).slab(WoodBlocks.PLANKS_BAOBAB.get());
        blockModels.family(SlabBlocks.EUCALYPTUS.get()).slab(WoodBlocks.PLANKS_EUCALYPTUS.get());
        blockModels.family(SlabBlocks.MAHOGANY.get()).slab(WoodBlocks.PLANKS_MAHOGANY.get());
        blockModels.family(SlabBlocks.MOSSBARK.get()).slab(WoodBlocks.PLANKS_MOSSBARK.get());
        blockModels.family(SlabBlocks.ASPER.get()).slab(WoodBlocks.PLANKS_ASPER.get());
        blockModels.family(SlabBlocks.CYPRESS.get()).slab(WoodBlocks.PLANKS_CYPRESS.get());
        blockModels.family(SlabBlocks.BALSAM.get()).slab(WoodBlocks.PLANKS_BALSAM.get());
        blockModels.family(SlabBlocks.WHITE.get()).slab(WoodBlocks.PLANKS_WHITE.get());
        blockModels.family(SlabBlocks.BAMBOO.get()).slab(WoodBlocks.PLANKS_BAMBOO.get());
        blockModels.family(SlabBlocks.ROTTEN.get()).slab(WoodBlocks.PLANKS_ROTTEN.get());
        blockModels.family(SlabBlocks.MARSHWOOD.get()).slab(WoodBlocks.PLANKS_MARSHWOOD.get());
        blockModels.family(SlabBlocks.SCORCHED.get()).slab(WoodBlocks.PLANKS_SCORCHED.get());
        blockModels.family(SlabBlocks.VARNISHED.get()).slab(WoodBlocks.PLANKS_VARNISHED.get());
        blockModels.family(SlabBlocks.PETRIFIED.get()).slab(WoodBlocks.PLANKS_PETRIFIED.get());

        // MARK: Slabs Stone
        blockModels.family(SlabBlocks.UMBERSTONE.get()).slab(UmberstoneBlocks.UMBERSTONE.get());
        blockModels.family(SlabBlocks.UMBERCOBBLE.get()).slab(UmberstoneBlocks.UMBERCOBBLE.get());
        blockModels.family(SlabBlocks.UMBERCOBBLE_MOSSY.get()).slab(UmberstoneBlocks.UMBERCOBBLE_MOSSY.get());
        blockModels.family(SlabBlocks.UMBERCOBBLE_WEBBED.get()).slab(UmberstoneBlocks.UMBERCOBBLE_WEBBED.get());
        blockModels.family(SlabBlocks.UMBERSTONE_BRICKS.get()).slab(UmberstoneBlocks.UMBERSTONE_BRICKS.get());
        blockModels.family(SlabBlocks.UMBERTILE_SMOOTH.get()).slab(UmberstoneBlocks.UMBERTILE_SMOOTH.get());
        blockModels.family(SlabBlocks.UMBERTILE_SMOOTH_SMALL.get()).slab(UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL.get());
        blockModels.family(SlabBlocks.UMBERPAVER.get()).slab(UmberstoneBlocks.UMBERPAVER.get());
        blockModels.family(SlabBlocks.UMBERPAVER_MOSSY.get()).slab(UmberstoneBlocks.UMBERPAVER_MOSSY.get());
        blockModels.family(SlabBlocks.UMBERPAVER_WEBBED.get()).slab(UmberstoneBlocks.UMBERPAVER_WEBBED.get());
        blockModels.family(SlabBlocks.AMBER.get()).slab(AmberBlocks.AMBER.get());
        blockModels.family(SlabBlocks.AMBER_BRICKS.get()).slab(AmberBlocks.AMBER_BRICKS.get());
        blockModels.family(SlabBlocks.MIR_BRICKS.get()).slab(UmberstoneBlocks.MIR_BRICKS.get());
        blockModels.family(SlabBlocks.MUD_BRICKS.get()).slab(UmberstoneBlocks.MUD_BRICKS.get());

        // MARK: Stairs Wood
        stairs(StairBlocks.WHITE, WoodBlocks.PLANKS_WHITE);
        stairs(StairBlocks.VARNISHED, WoodBlocks.PLANKS_VARNISHED);
        stairs(StairBlocks.PETRIFIED, WoodBlocks.PLANKS_PETRIFIED);

        // MARK: Stairs
        stairs(StairBlocks.UMBERSTONE, UmberstoneBlocks.UMBERSTONE);
        stairs(StairBlocks.UMBERCOBBLE, UmberstoneBlocks.UMBERCOBBLE);
        stairs(StairBlocks.UMBERCOBBLE_MOSSY, UmberstoneBlocks.UMBERCOBBLE_MOSSY);
        stairs(StairBlocks.UMBERCOBBLE_WEBBED, UmberstoneBlocks.UMBERCOBBLE_WEBBED);
        stairs(StairBlocks.UMBERSTONE_BRICKS, UmberstoneBlocks.UMBERSTONE_BRICKS);
        stairs(StairBlocks.UMBERTILE_SMOOTH, UmberstoneBlocks.UMBERTILE_SMOOTH);
        stairs(StairBlocks.UMBERTILE_SMOOTH_SMALL, UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL);
        stairs(StairBlocks.UMBERPAVER, UmberstoneBlocks.UMBERPAVER);
        stairs(StairBlocks.UMBERPAVER_MOSSY, UmberstoneBlocks.UMBERPAVER_MOSSY);
        stairs(StairBlocks.UMBERPAVER_WEBBED, UmberstoneBlocks.UMBERPAVER_WEBBED);
        stairsTranslucent(StairBlocks.AMBER, AmberBlocks.AMBER);
        stairsTranslucent(StairBlocks.AMBER_BRICKS, AmberBlocks.AMBER_BRICKS);
        stairs(StairBlocks.MUD_BRICKS, UmberstoneBlocks.MUD_BRICKS);
        stairs(StairBlocks.MIR_BRICKS, UmberstoneBlocks.MIR_BRICKS);

        // MARK: Doors
        door(DoorBlocks.WHITE);

        // MARK: Fences
        fence(FenceBlocks.FENCE_WHITE, WoodBlocks.PLANKS_WHITE);
        fence(FenceBlocks.FENCE_VARNISHED, WoodBlocks.PLANKS_VARNISHED);

        // MARK: Fence Gates
        fenceGate(FenceBlocks.FENCE_GATE_WHITE, WoodBlocks.PLANKS_WHITE);
        fenceGate(FenceBlocks.FENCE_GATE_VARNISHED, WoodBlocks.PLANKS_VARNISHED);

        // MARK: Walls
        wall(WallBlocks.WALL_UMBERSTONE, UmberstoneBlocks.UMBERSTONE);
        wall(WallBlocks.WALL_UMBERCOBBLE, UmberstoneBlocks.UMBERCOBBLE);
        wall(WallBlocks.WALL_UMBERCOBBLE_MOSSY, UmberstoneBlocks.UMBERCOBBLE_MOSSY);
        wall(WallBlocks.WALL_UMBERCOBBLE_WEBBED, UmberstoneBlocks.UMBERCOBBLE_WEBBED);
        wall(WallBlocks.WALL_UMBERSTONE_BRICKS, UmberstoneBlocks.UMBERSTONE_BRICKS);
        wall(WallBlocks.WALL_UMBERTILE_SMOOTH, UmberstoneBlocks.UMBERTILE_SMOOTH);
        wall(WallBlocks.WALL_UMBERTILE_SMOOTH_SMALL, UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL);
        wall(WallBlocks.WALL_UMBERPAVER, UmberstoneBlocks.UMBERPAVER);
        wall(WallBlocks.WALL_UMBERPAVER_MOSSY, UmberstoneBlocks.UMBERPAVER_MOSSY);
        wall(WallBlocks.WALL_UMBERPAVER_WEBBED, UmberstoneBlocks.UMBERPAVER_WEBBED);
        wallTranslucent(WallBlocks.WALL_AMBER, AmberBlocks.AMBER);
        wallTranslucent(WallBlocks.WALL_AMBER_BRICKS, AmberBlocks.AMBER_BRICKS);

        // MARK: Plants
        blockModels.createCropBlock(PlantBlocks.CROP_TURNIP.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(PlantBlocks.CROP_CABBAGE.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(PlantBlocks.CROP_MANDRAKE.get(), ModCropBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(PlantBlocks.JADE_BERRY_BUSH.get(), ModBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(PlantBlocks.HEART_BERRY_BUSH.get(), ModBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(PlantBlocks.SWAMP_BERRY_BUSH.get(), ModBerryBushBlock.AGE, 0, 1, 2, 3);
        blockModels.createCropBlock(PlantBlocks.DARK_FRUIT_VINE.get(), DarkFruitVineBlock.AGE, 0, 1, 2, 3);
        //crossBlock(MIRE_CORAL); // TODO: I don't think this is implemented
        blockModels.createCrossBlock(PlantBlocks.NETTLE.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(PlantBlocks.NETTLE_FLOWERED.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(PlantBlocks.SWAMP_PLANT.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(PlantBlocks.FIRE_BLOOM.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(PlantBlocks.FIDDLE_HEAD.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.registerSimpleTintedItemModel(PlantBlocks.THORNS.get(), blockModels.createFlatItemModelWithBlockTexture(PlantBlocks.THORNS.get().asItem(), PlantBlocks.THORNS.get()), new GrassColorSource());
        simpleBlock(PlantBlocks.ALGAE.get(), models().getExistingFile(modLoc("block/algae")));
        crossBlock(PlantBlocks.HANGING_WEB.get());
        hugeMushroom(PlantBlocks.DARK_CAPPED_MUSHROOM_BLOCK.get());
        hugeMushroom(PlantBlocks.DARK_CAPPED_MUSHROOM_STEM.get());
        hugeMushroom(PlantBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK.get());
        hugeMushroom(PlantBlocks.SARCASTIC_CZECH_MUSHROOM_STEM.get());
        hugeMushroom(PlantBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK.get());
        hugeMushroom(PlantBlocks.GRANDMAS_SHOES_MUSHROOM_STEM.get());
        hugeMushroom(PlantBlocks.DUTCH_CAP_MUSHROOM_BLOCK.get());
        hugeMushroom(PlantBlocks.DUTCH_CAP_MUSHROOM_STEM.get());
        hugeMushroom(PlantBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK.get());
        hugeMushroom(PlantBlocks.KAIZERS_FINGERS_MUSHROOM_STEM.get());
        blockModels.createTrivialCube(PlantBlocks.GIANT_LILY_PAD.get());

        blockModels.createTrivialCube(PlantBlocks.PETAL_BLACK.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_RED.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_BROWN.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_BLUE.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_PURPLE.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_CYAN.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_LIGHT_GRAY.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_GRAY.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_PINK.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_YELLOW.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_LIGHT_BLUE.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_MAGENTA.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_ORANGE.get());
        blockModels.createTrivialCube(PlantBlocks.PETAL_WHITE.get());

        stigma(PlantBlocks.EXPLODING_STIGMA.get());
        blockModels.createTrivialCube(PlantBlocks.STEM.get());
        stigma(PlantBlocks.STIGMA_BLACK.get());
        stigma(PlantBlocks.STIGMA_RED.get());
        stigma(PlantBlocks.STIGMA_BROWN.get());
        stigma(PlantBlocks.STIGMA_BLUE.get());
        stigma(PlantBlocks.STIGMA_PURPLE.get());
        stigma(PlantBlocks.STIGMA_CYAN.get());
        stigma(PlantBlocks.STIGMA_LIGHT_GRAY.get());
        stigma(PlantBlocks.STIGMA_GRAY.get());
        stigma(PlantBlocks.STIGMA_PINK.get());
        stigma(PlantBlocks.STIGMA_YELLOW.get());
        stigma(PlantBlocks.STIGMA_LIGHT_BLUE.get());
        stigma(PlantBlocks.STIGMA_MAGENTA.get());
        stigma(PlantBlocks.STIGMA_ORANGE.get());
        stigma(PlantBlocks.STIGMA_WHITE.get());

        doubleCrossBlock(PlantBlocks.BULLRUSH.get());
        doubleCrossBlock(PlantBlocks.WEEPING_BLUEBELL.get());
        doubleCrossBlock(PlantBlocks.SUNDEW.get());
        doubleCrossBlock(PlantBlocks.TALL_BLOOM.get());
        doubleCrossBlock(PlantBlocks.TANGLED_STALK.get());
        doubleCrossBlock(PlantBlocks.HIGH_CAPPED_MUSHROOM.get());

        // MARK: Other
        blockModels.createTrivialCube(OtherBlocks.PORTAL.get());
        blockModels.createTrivialCube(OtherBlocks.JADE_BLOCK.get());
        blockModels.createTrivialCube(OtherBlocks.MUD.get());
        blockModels.createTrivialCube(OtherBlocks.QUICK_SAND.get());
        blockModels.createTrivialCube(OtherBlocks.GHOST_SAND.get());
        blockModels.createTrivialCube(OtherBlocks.RED_GEM_BLOCK.get());
        lamp(OtherBlocks.RED_GEM_LAMP);
        crossBlock(OtherBlocks.WITHER_WEB);
        crossBlock(OtherBlocks.LAVA_WEB);
        blockModels.createTrivialCube(OtherBlocks.GNEISS.get());
        blockModels.createTrivialCube(OtherBlocks.GNEISS_CARVED.get());
        blockModels.createTrivialCube(OtherBlocks.GNEISS_RELIEF.get());
        blockModels.createTrivialCube(OtherBlocks.GNEISS_BRICKS.get());
        blockModels.createTrivialCube(OtherBlocks.GNEISS_SMOOTH.get());
        blockModels.createTrivialCube(OtherBlocks.GNEISS_TILES.get());
        blockModels.createTrivialCube(OtherBlocks.GNEISS_TILES_CRACKED.get());
        blockModels.createTrivialCube(OtherBlocks.TEMPLE_BRICK.get());
        blockModels.createTrivialCube(OtherBlocks.TEMPLE_PILLAR.get());
        blockModels.createTrivialCube(OtherBlocks.TEMPLE_TILE.get());
        blockModels.createTrivialCube(OtherBlocks.SILK.get());
        blockModels.createTrivialCube(OtherBlocks.REIN_EXO.get());
      //  log(OtherBlocks.VELOCITY_BLOCK);
       // log(OtherBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED);
        createHoneyTreat(blockModels);
        blockModels.createCandleAndCandleCake(Blocks.CANDLE, OtherBlocks.CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.WHITE_CANDLE, OtherBlocks.WHITE_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.ORANGE_CANDLE, OtherBlocks.ORANGE_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.MAGENTA_CANDLE, OtherBlocks.MAGENTA_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.LIGHT_BLUE_CANDLE, OtherBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.YELLOW_CANDLE, OtherBlocks.YELLOW_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.LIME_CANDLE, OtherBlocks.LIME_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.PINK_CANDLE, OtherBlocks.PINK_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.GRAY_CANDLE, OtherBlocks.GRAY_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.LIGHT_GRAY_CANDLE, OtherBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.CYAN_CANDLE, OtherBlocks.CYAN_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.PURPLE_CANDLE, OtherBlocks.PURPLE_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.BLUE_CANDLE, OtherBlocks.BLUE_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.BROWN_CANDLE, OtherBlocks.BROWN_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.GREEN_CANDLE, OtherBlocks.GREEN_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.RED_CANDLE, OtherBlocks.RED_CANDLE_HONEY_TREAT.get());
        blockModels.createCandleAndCandleCake(Blocks.BLACK_CANDLE, OtherBlocks.BLACK_CANDLE_HONEY_TREAT.get());
        blockModels.createTrivialCube(OtherBlocks.WASP_NEST.get());
        stairs(OtherBlocks.STAIRS_WASP_NEST, OtherBlocks.WASP_NEST);

        // MARK: Spawners
        blockModels.createTrivialCube(OtherBlocks.ANTLION_SPAWNER.get());
        blockModels.createTrivialCube(OtherBlocks.DRAGON_FLY_SPAWNER.get());
        blockModels.createTrivialCube(OtherBlocks.JUMPING_SPIDER_SPAWNER.get());
        blockModels.createTrivialCube(OtherBlocks.SPIDER_SPAWNER.get());
        blockModels.createTrivialCube(OtherBlocks.TARANTULA_SPAWNER.get());
        blockModels.createTrivialCube(OtherBlocks.WASP_SPAWNER.get());
        blockModels.createTrivialCube(OtherBlocks.ZOMBIE_ANT_SPAWNER.get());
        blockModels.createTrivialCube(OtherBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER.get());
        blockModels.createTrivialCube(OtherBlocks.MAGMA_CRAWLER_SPAWNER.get());
        blockModels.createTrivialCube(OtherBlocks.LOCUST_SPAWNER.get());

        // MARK: Utility Blocks
        craftingTable(OtherBlocks.PETRIFIED_CRAFTING_TABLE.get());
        furnace(OtherBlocks.UMBER_FURNACE.get());
        button(OtherBlocks.UMBERSTONE_BUTTON.get(), UmberstoneBlocks.UMBERSTONE.get());

        // MARK: Antlion Dungeon
        blockModels.createTrivialCube(OtherBlocks.CAPSTONE.get());
        blockModels.createTrivialCube(OtherBlocks.CAPSTONE_MUD.get());
        blockModels.createTrivialCube(OtherBlocks.CAPSTONE_IRON.get());
        blockModels.createTrivialCube(OtherBlocks.CAPSTONE_GOLD.get());
        blockModels.createTrivialCube(OtherBlocks.CAPSTONE_JADE.get());
        blockModels.createTrivialCube(OtherBlocks.TEMPLE_BRICK_UNBREAKING.get());
        blockModels.createTrivialCube(OtherBlocks.TEMPLE_BRICK_UNBREAKING_JADE.get());
        blockModels.createTrivialCube(OtherBlocks.TEMPLE_BRICK_UNBREAKING_EXO.get());
        blockModels.createTrivialCube(OtherBlocks.TEMPLE_BRICK_UNBREAKING_CREAM.get());
        blockModels.createTrivialCube(OtherBlocks.TEMPLE_BRICK_UNBREAKING_EYE.get());
        blockModels.createTrivialCube(OtherBlocks.TEMPLE_BRICK_UNBREAKING_STRING.get());
        blockModels.createTrivialCube(OtherBlocks.TEMPLE_TELEPORTER.get());
        blockModels.createTrivialCube(OtherBlocks.FORCE_FIELD.get());
        blockModels.createTrivialCube(OtherBlocks.FORCE_LOCK.get());
        blockModels.createTrivialCube(OtherBlocks.ANT_HILL_BLOCK.get());
    }

    public void createHoneyTreat(BlockModelGenerators blockModels) {
        blockModels.registerSimpleFlatItemModel(OtherBlocks.HONEY_TREAT.get().asItem());
        blockModels.blockStateOutput.accept(MultiVariantGenerator
                .dispatch(OtherBlocks.HONEY_TREAT.get())
                .with(PropertyDispatch
                        .initial(HoneyTreatBlock.BITES)
                        .select(0, plainVariant(ModelLocationUtils.getModelLocation(OtherBlocks.HONEY_TREAT.get())))
                        .select(1, plainVariant(ModelLocationUtils.getModelLocation(OtherBlocks.HONEY_TREAT.get(), "_slice1")))
                        .select(2, plainVariant(ModelLocationUtils.getModelLocation(OtherBlocks.HONEY_TREAT.get(), "_slice2")))
                        .select(3, plainVariant(ModelLocationUtils.getModelLocation(OtherBlocks.HONEY_TREAT.get(), "_slice3")))
                        .select(4, plainVariant(ModelLocationUtils.getModelLocation(OtherBlocks.HONEY_TREAT.get(), "_slice4")))
                        .select(5, plainVariant(ModelLocationUtils.getModelLocation(OtherBlocks.HONEY_TREAT.get(), "_slice5")))
                        .select(6, plainVariant(ModelLocationUtils.getModelLocation(OtherBlocks.HONEY_TREAT.get(), "_slice6")))
                )
        );
    }
}
