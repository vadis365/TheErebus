package erebus.datagen;

import erebus.block.plants.DarkFruitVineBlock;
import erebus.block.plants.ModBerryBushBlock;
import erebus.datagen.providers.ModBlockStateProvider;
import erebus.registries.blocks.providers.*;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockStates extends ModBlockStateProvider {

    public ModBlockStates(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // MARK: Umberstone
        block(UmberstoneBlocks.UMBERSTONE);
        block(UmberstoneBlocks.UMBERSTONE_BRICKS);
        block(UmberstoneBlocks.UMBERCOBBLE);
        block(UmberstoneBlocks.UMBERCOBBLE_MOSSY);
        block(UmberstoneBlocks.UMBERCOBBLE_WEBBED);
        block(UmberstoneBlocks.UMBERTILE_SMOOTH);
        block(UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL);
        block(UmberstoneBlocks.UMBERGRAVEL);
        block(UmberstoneBlocks.UMBERPAVER);
        block(UmberstoneBlocks.UMBERPAVER_MOSSY);
        block(UmberstoneBlocks.UMBERPAVER_WEBBED);
        log(UmberstoneBlocks.UMBERSTONE_PILLAR);
        block(UmberstoneBlocks.VOLCANIC_ROCK);
        block(UmberstoneBlocks.DUST);
        dust(UmberstoneBlocks.DUST_LAYER);
        block(UmberstoneBlocks.PETRIFIED_WOOD_ROCK);
        block(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_2);
        block(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_3);
        block(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_4);
        block(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_5);
        block(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_6);
        block(UmberstoneBlocks.PETRIFIED_BARK_RED);
        block(UmberstoneBlocks.PETRIFIED_BARK_BROWN);
        block(UmberstoneBlocks.PETRIFIED_LOG_INNER);
        block(UmberstoneBlocks.DUNG);

        block(UmberstoneBlocks.MIR_BRICKS);
        block(UmberstoneBlocks.MUD_BRICKS);

        // MARK: Amber
        blockTranslucent(AmberBlocks.AMBER);
        blockTranslucent(AmberBlocks.AMBER_BRICKS);
        blockTranslucent(AmberBlocks.PRESERVED_AMBER, "amber");
        blockTranslucent(AmberBlocks.PRESERVED_AMBER_GLASS, "amber_glass_island");
        doorBlockWithRenderType(AmberBlocks.AMBER_DOOR.get(), modLoc("block/amber_door_lower"), modLoc("block/amber_door_upper"), "translucent");

        // MARK: Ores
        block(OreBlocks.IRON);
        block(OreBlocks.GOLD);
        block(OreBlocks.COAL);
        block(OreBlocks.DIAMOND);
        block(OreBlocks.EMERALD);
        block(OreBlocks.LAPIS);
        block(OreBlocks.QUARTZ);
        block(OreBlocks.PETRIFIED_QUARTZ);
        block(OreBlocks.COPPER);
        block(OreBlocks.SILVER);
        block(OreBlocks.TIN);
        block(OreBlocks.LEAD);
        block(OreBlocks.ALUMINUM);
        block(OreBlocks.JADE);
        block(OreBlocks.ENCRUSTED_DIAMOND);
        block(OreBlocks.FOSSIL);
        block(OreBlocks.GNEISS);
        block(OreBlocks.PETRIFIED_WOOD);
        block(OreBlocks.TEMPLE);

        // MARK: Logs
        log(WoodBlocks.LOG_BAOBAB);
        log(WoodBlocks.LOG_EUCALYPTUS);
        log(WoodBlocks.LOG_MAHOGANY);
        log(WoodBlocks.LOG_MOSSBARK);
        log(WoodBlocks.LOG_ASPER);
        log(WoodBlocks.LOG_CYPRESS);
        log(WoodBlocks.LOG_BALSAM);
        log(WoodBlocks.LOG_BALSAM_RESINLESS);
        log(WoodBlocks.LOG_ROTTEN);
        log(WoodBlocks.LOG_MARSHWOOD);
        log(WoodBlocks.LOG_SCORCHED);

        // MARK: Saplings
        crossBlock(WoodBlocks.SAPLING_BAOBAB);
        crossBlock(WoodBlocks.SAPLING_EUCALYPTUS);
        crossBlock(WoodBlocks.SAPLING_MAHOGANY);
        crossBlock(WoodBlocks.SAPLING_MOSSBARK);
        crossBlock(WoodBlocks.SAPLING_ASPER);
        crossBlock(WoodBlocks.SAPLING_CYPRESS);
        crossBlock(WoodBlocks.SAPLING_BALSAM);
        crossBlock(WoodBlocks.SAPLING_MARSHWOOD);
        crossBlock(WoodBlocks.SAPLING_BAMBOO);

        // MARK: Leaves
        cutout(WoodBlocks.LEAVES_BAOBAB);
        cutout(WoodBlocks.LEAVES_EUCALYPTUS);
        cutout(WoodBlocks.LEAVES_MAHOGANY);
        cutout(WoodBlocks.LEAVES_MOSSBARK);
        cutout(WoodBlocks.LEAVES_ASPER);
        cutout(WoodBlocks.LEAVES_CYPRESS);
        cutout(WoodBlocks.LEAVES_BALSAM);
        cutout(WoodBlocks.LEAVES_MARSHWOOD);

        // MARK: Planks
        block(WoodBlocks.PLANKS_BAOBAB);
        block(WoodBlocks.PLANKS_EUCALYPTUS);
        block(WoodBlocks.PLANKS_MAHOGANY);
        block(WoodBlocks.PLANKS_MOSSBARK);
        block(WoodBlocks.PLANKS_ASPER);
        block(WoodBlocks.PLANKS_CYPRESS);
        block(WoodBlocks.PLANKS_BALSAM);
        block(WoodBlocks.PLANKS_WHITE);
        block(WoodBlocks.PLANKS_BAMBOO);
        block(WoodBlocks.PLANKS_ROTTEN);
        block(WoodBlocks.PLANKS_MARSHWOOD);
        block(WoodBlocks.PLANKS_SCORCHED);
        block(WoodBlocks.PLANKS_VARNISHED);
        block(WoodBlocks.PLANKS_PETRIFIED);

        // MARK: Slabs Wood
        slab(SlabBlocks.PLANKS_BAOBAB, WoodBlocks.PLANKS_BAOBAB);
        slab(SlabBlocks.PLANKS_EUCALYPTUS, WoodBlocks.PLANKS_EUCALYPTUS);
        slab(SlabBlocks.PLANKS_MAHOGANY, WoodBlocks.PLANKS_MAHOGANY);
        slab(SlabBlocks.PLANKS_MOSSBARK, WoodBlocks.PLANKS_MOSSBARK);
        slab(SlabBlocks.PLANKS_ASPER, WoodBlocks.PLANKS_ASPER);
        slab(SlabBlocks.PLANKS_CYPRESS, WoodBlocks.PLANKS_CYPRESS);
        slab(SlabBlocks.PLANKS_BALSAM, WoodBlocks.PLANKS_BALSAM);
        slab(SlabBlocks.PLANKS_WHITE, WoodBlocks.PLANKS_WHITE);
        slab(SlabBlocks.PLANKS_BAMBOO, WoodBlocks.PLANKS_BAMBOO);
        slab(SlabBlocks.PLANKS_ROTTEN, WoodBlocks.PLANKS_ROTTEN);
        slab(SlabBlocks.PLANKS_MARSHWOOD, WoodBlocks.PLANKS_MARSHWOOD);
        slab(SlabBlocks.PLANKS_SCORCHED, WoodBlocks.PLANKS_SCORCHED);
        slab(SlabBlocks.PLANKS_VARNISHED, WoodBlocks.PLANKS_VARNISHED);
        slab(SlabBlocks.PLANKS_PETRIFIED, WoodBlocks.PLANKS_PETRIFIED);

        // MARK: Slabs Stone
        slab(SlabBlocks.UMBERSTONE, UmberstoneBlocks.UMBERSTONE);
        slab(SlabBlocks.UMBERCOBBLE, UmberstoneBlocks.UMBERCOBBLE);
        slab(SlabBlocks.UMBERCOBBLE_MOSSY, UmberstoneBlocks.UMBERCOBBLE_MOSSY);
        slab(SlabBlocks.UMBERCOBBLE_WEBBED, UmberstoneBlocks.UMBERCOBBLE_WEBBED);
        slab(SlabBlocks.UMBERSTONE_BRICKS, UmberstoneBlocks.UMBERSTONE_BRICKS);
        slab(SlabBlocks.UMBERTILE_SMOOTH, UmberstoneBlocks.UMBERTILE_SMOOTH);
        slab(SlabBlocks.UMBERTILE_SMOOTH_SMALL, UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL);
        slab(SlabBlocks.UMBERPAVER, UmberstoneBlocks.UMBERPAVER);
        slab(SlabBlocks.UMBERPAVER_MOSSY, UmberstoneBlocks.UMBERPAVER_MOSSY);
        slab(SlabBlocks.UMBERPAVER_WEBBED, UmberstoneBlocks.UMBERPAVER_WEBBED);
        slabTranslucent(SlabBlocks.AMBER, AmberBlocks.AMBER);
        slabTranslucent(SlabBlocks.AMBER_BRICKS, AmberBlocks.AMBER_BRICKS);
        slab(SlabBlocks.MIR_BRICKS, UmberstoneBlocks.MIR_BRICKS);
        slab(SlabBlocks.MUD_BRICKS, UmberstoneBlocks.MUD_BRICKS);

        // MARK: Stairs Wood
        stairs(StairBlocks.BAOBAB, WoodBlocks.PLANKS_BAOBAB);
        stairs(StairBlocks.EUCALYPTUS, WoodBlocks.PLANKS_EUCALYPTUS);
        stairs(StairBlocks.MAHOGANY, WoodBlocks.PLANKS_MAHOGANY);
        stairs(StairBlocks.MOSSBARK, WoodBlocks.PLANKS_MOSSBARK);
        stairs(StairBlocks.ASPER, WoodBlocks.PLANKS_ASPER);
        stairs(StairBlocks.CYPRESS, WoodBlocks.PLANKS_CYPRESS);
        stairs(StairBlocks.BALSAM, WoodBlocks.PLANKS_BALSAM);
        stairs(StairBlocks.WHITE, WoodBlocks.PLANKS_WHITE);
        stairs(StairBlocks.BAMBOO, WoodBlocks.PLANKS_BAMBOO);
        stairs(StairBlocks.ROTTEN, WoodBlocks.PLANKS_ROTTEN);
        stairs(StairBlocks.MARSHWOOD, WoodBlocks.PLANKS_MARSHWOOD);
        stairs(StairBlocks.SCORCHED, WoodBlocks.PLANKS_SCORCHED);
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
        door(DoorBlocks.BAOBAB);
        door(DoorBlocks.EUCALYPTUS);
        door(DoorBlocks.MAHOGANY);
        door(DoorBlocks.MOSSBARK);
        door(DoorBlocks.ASPER);
        door(DoorBlocks.CYPRESS);
        door(DoorBlocks.BALSAM);
        door(DoorBlocks.WHITE);
        door(DoorBlocks.ROTTEN);
        door(DoorBlocks.MARSHWOOD);
        door(DoorBlocks.SCORCHED);

        // MARK: Fences
        fence(FenceBlocks.FENCE_BAOBAB, WoodBlocks.PLANKS_BAOBAB);
        fence(FenceBlocks.FENCE_EUCALYPTUS, WoodBlocks.PLANKS_EUCALYPTUS);
        fence(FenceBlocks.FENCE_MAHOGANY, WoodBlocks.PLANKS_MAHOGANY);
        fence(FenceBlocks.FENCE_MOSSBARK, WoodBlocks.PLANKS_MOSSBARK);
        fence(FenceBlocks.FENCE_ASPER, WoodBlocks.PLANKS_ASPER);
        fence(FenceBlocks.FENCE_CYPRESS, WoodBlocks.PLANKS_CYPRESS);
        fence(FenceBlocks.FENCE_BALSAM, WoodBlocks.PLANKS_BALSAM);
        fence(FenceBlocks.FENCE_WHITE, WoodBlocks.PLANKS_WHITE);
        fence(FenceBlocks.FENCE_BAMBOO, WoodBlocks.PLANKS_BAMBOO);
        fence(FenceBlocks.FENCE_ROTTEN, WoodBlocks.PLANKS_ROTTEN);
        fence(FenceBlocks.FENCE_MARSHWOOD, WoodBlocks.PLANKS_MARSHWOOD);
        fence(FenceBlocks.FENCE_SCORCHED, WoodBlocks.PLANKS_SCORCHED);
        fence(FenceBlocks.FENCE_VARNISHED, WoodBlocks.PLANKS_VARNISHED);

        // MARK: Fence Gates
        fenceGate(FenceBlocks.FENCE_GATE_BAOBAB, WoodBlocks.PLANKS_BAOBAB);
        fenceGate(FenceBlocks.FENCE_GATE_EUCALYPTUS, WoodBlocks.PLANKS_EUCALYPTUS);
        fenceGate(FenceBlocks.FENCE_GATE_MAHOGANY, WoodBlocks.PLANKS_MAHOGANY);
        fenceGate(FenceBlocks.FENCE_GATE_MOSSBARK, WoodBlocks.PLANKS_MOSSBARK);
        fenceGate(FenceBlocks.FENCE_GATE_ASPER, WoodBlocks.PLANKS_ASPER);
        fenceGate(FenceBlocks.FENCE_GATE_CYPRESS, WoodBlocks.PLANKS_CYPRESS);
        fenceGate(FenceBlocks.FENCE_GATE_BALSAM, WoodBlocks.PLANKS_BALSAM);
        fenceGate(FenceBlocks.FENCE_GATE_WHITE, WoodBlocks.PLANKS_WHITE);
        fenceGate(FenceBlocks.FENCE_GATE_BAMBOO, WoodBlocks.PLANKS_BAMBOO);
        fenceGate(FenceBlocks.FENCE_GATE_ROTTEN, WoodBlocks.PLANKS_ROTTEN);
        fenceGate(FenceBlocks.FENCE_GATE_MARSHWOOD, WoodBlocks.PLANKS_MARSHWOOD);
        fenceGate(FenceBlocks.FENCE_GATE_SCORCHED, WoodBlocks.PLANKS_SCORCHED);
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
        crop(PlantBlocks.CROP_TURNIP);
        crop(PlantBlocks.CROP_CABBAGE);
        crop(PlantBlocks.CROP_MANDRAKE);
        bush(PlantBlocks.JADE_BERRY_BUSH, ModBerryBushBlock.AGE);
        bush(PlantBlocks.HEART_BERRY_BUSH, ModBerryBushBlock.AGE);
        bush(PlantBlocks.SWAMP_BERRY_BUSH, ModBerryBushBlock.AGE);
        bush(PlantBlocks.DARK_FRUIT_VINE, DarkFruitVineBlock.AGE);
        //crossBlock(MIRE_CORAL); // TODO: I don't think this is implemented
        crossBlock(PlantBlocks.NETTLE);
        crossBlock(PlantBlocks.NETTLE_FLOWERED);
        crossBlock(PlantBlocks.SWAMP_PLANT);
        crossBlock(PlantBlocks.FIRE_BLOOM);
        tintedCrossBlock(PlantBlocks.FIDDLE_HEAD);
        vines(PlantBlocks.THORNS);
        simpleBlock(PlantBlocks.ALGAE.get(), models().getExistingFile(modLoc("block/algae")));
        crossBlock(PlantBlocks.HANGING_WEB);
        hugeMushroom(PlantBlocks.DARK_CAPPED_MUSHROOM_BLOCK, "dark_capped", false);
        hugeMushroom(PlantBlocks.DARK_CAPPED_MUSHROOM_STEM, "dark_capped", true);
        hugeMushroom(PlantBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK, "sarcastic_czech", false);
        hugeMushroom(PlantBlocks.SARCASTIC_CZECH_MUSHROOM_STEM, "sarcastic_czech", true);
        hugeMushroom(PlantBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK, "grandmas_shoes", false);
        hugeMushroom(PlantBlocks.GRANDMAS_SHOES_MUSHROOM_STEM, "grandmas_shoes", true);
        hugeMushroom(PlantBlocks.DUTCH_CAP_MUSHROOM_BLOCK, "dutch_cap", false);
        hugeMushroom(PlantBlocks.DUTCH_CAP_MUSHROOM_STEM, "dutch_cap", true);
        hugeMushroom(PlantBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK, "kaizers_fingers", false);
        hugeMushroom(PlantBlocks.KAIZERS_FINGERS_MUSHROOM_STEM, "kaizers_fingers", true);
        //crossBlock(PlantBlocks.DARK_CAPPED_MUSHROOM, "mushroom_dark_capped");
        //crossBlock(PlantBlocks.SARCASTIC_CZECH_MUSHROOM, "mushroom_sarcastic_czech");
        //crossBlock(PlantBlocks.GRANDMAS_SHOES_MUSHROOM, "mushroom_grandmas_shoes");
        //crossBlock(PlantBlocks.DUTCH_CAP_MUSHROOM, "mushroom_dutch_cap");
        //crossBlock(PlantBlocks.KAIZERS_FINGERS_MUSHROOM, "mushroom_kaizers_fingers");
        block(PlantBlocks.GIANT_LILY_PAD);

        block(PlantBlocks.PETAL_BLACK);
        block(PlantBlocks.PETAL_RED);
        block(PlantBlocks.PETAL_BROWN);
        block(PlantBlocks.PETAL_BLUE);
        block(PlantBlocks.PETAL_PURPLE);
        block(PlantBlocks.PETAL_CYAN);
        block(PlantBlocks.PETAL_LIGHT_GRAY);
        block(PlantBlocks.PETAL_GRAY);
        block(PlantBlocks.PETAL_PINK);
        block(PlantBlocks.PETAL_YELLOW);
        block(PlantBlocks.PETAL_LIGHT_BLUE);
        block(PlantBlocks.PETAL_MAGENTA);
        block(PlantBlocks.PETAL_ORANGE);
        block(PlantBlocks.PETAL_WHITE);

        stigma(PlantBlocks.EXPLODING_STIGMA);
        block(PlantBlocks.STEM);
        stigma(PlantBlocks.STIGMA_BLACK);
        stigma(PlantBlocks.STIGMA_RED);
        stigma(PlantBlocks.STIGMA_BROWN);
        stigma(PlantBlocks.STIGMA_BLUE);
        stigma(PlantBlocks.STIGMA_PURPLE);
        stigma(PlantBlocks.STIGMA_CYAN);
        stigma(PlantBlocks.STIGMA_LIGHT_GRAY);
        stigma(PlantBlocks.STIGMA_GRAY);
        stigma(PlantBlocks.STIGMA_PINK);
        stigma(PlantBlocks.STIGMA_YELLOW);
        stigma(PlantBlocks.STIGMA_LIGHT_BLUE);
        stigma(PlantBlocks.STIGMA_MAGENTA);
        stigma(PlantBlocks.STIGMA_ORANGE);
        stigma(PlantBlocks.STIGMA_WHITE);

        doubleCrossBlock(PlantBlocks.BULLRUSH);
        doubleCrossBlock(PlantBlocks.WEEPING_BLUEBELL);
        doubleCrossBlock(PlantBlocks.SUNDEW);
        doubleCrossBlock(PlantBlocks.TALL_BLOOM);
        doubleCrossBlock(PlantBlocks.TANGLED_STALK);
        doubleCrossBlock(PlantBlocks.HIGH_CAPPED_MUSHROOM);

        // MARK: Other
        blockTranslucent(OtherBlocks.PORTAL);
        block(OtherBlocks.JADE_BLOCK);
        block(OtherBlocks.MUD);
        block(OtherBlocks.QUICK_SAND);
        block(OtherBlocks.GHOST_SAND);
        block(OtherBlocks.RED_GEM_BLOCK);
        lamp(OtherBlocks.RED_GEM_LAMP);
        crossBlock(OtherBlocks.WITHER_WEB);
        crossBlock(OtherBlocks.LAVA_WEB);
        block(OtherBlocks.GNEISS);
        block(OtherBlocks.GNEISS_CARVED);
        block(OtherBlocks.GNEISS_RELIEF);
        block(OtherBlocks.GNEISS_BRICKS);
        block(OtherBlocks.GNEISS_SMOOTH);
        block(OtherBlocks.GNEISS_TILES);
        block(OtherBlocks.GNEISS_TILES_CRACKED);
        block(OtherBlocks.TEMPLE_BRICK);
        block(OtherBlocks.TEMPLE_PILLAR);
        block(OtherBlocks.TEMPLE_TILE);
        block(OtherBlocks.SILK);
        block(OtherBlocks.REIN_EXO);
      //  log(OtherBlocks.VELOCITY_BLOCK);
       // log(OtherBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED);
        egg(OtherBlocks.ANTLION_EGG);
        egg(OtherBlocks.TARANTULA_EGG);
        cake(OtherBlocks.HONEY_TREAT);
        cakeWithCandle(OtherBlocks.CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.CANDLE);
        cakeWithCandle(OtherBlocks.WHITE_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.WHITE_CANDLE);
        cakeWithCandle(OtherBlocks.ORANGE_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.ORANGE_CANDLE);
        cakeWithCandle(OtherBlocks.MAGENTA_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.MAGENTA_CANDLE);
        cakeWithCandle(OtherBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.LIGHT_BLUE_CANDLE);
        cakeWithCandle(OtherBlocks.YELLOW_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.YELLOW_CANDLE);
        cakeWithCandle(OtherBlocks.LIME_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.LIME_CANDLE);
        cakeWithCandle(OtherBlocks.PINK_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.PINK_CANDLE);
        cakeWithCandle(OtherBlocks.GRAY_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.GRAY_CANDLE);
        cakeWithCandle(OtherBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.LIGHT_GRAY_CANDLE);
        cakeWithCandle(OtherBlocks.CYAN_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.CYAN_CANDLE);
        cakeWithCandle(OtherBlocks.PURPLE_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.PURPLE_CANDLE);
        cakeWithCandle(OtherBlocks.BLUE_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.BLUE_CANDLE);
        cakeWithCandle(OtherBlocks.BROWN_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.BROWN_CANDLE);
        cakeWithCandle(OtherBlocks.GREEN_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.GREEN_CANDLE);
        cakeWithCandle(OtherBlocks.RED_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.RED_CANDLE);
        cakeWithCandle(OtherBlocks.BLACK_CANDLE_HONEY_TREAT, OtherBlocks.HONEY_TREAT, Blocks.BLACK_CANDLE);
        block(OtherBlocks.WASP_NEST);
        stairs(OtherBlocks.STAIRS_WASP_NEST, OtherBlocks.WASP_NEST);
        //block(INSECT_REPELLENT); TODO: I'm pretty sure we don't need this

        // MARK: Spawners
        block(OtherBlocks.ANTLION_SPAWNER);
        block(OtherBlocks.DRAGON_FLY_SPAWNER);
        block(OtherBlocks.JUMPING_SPIDER_SPAWNER, "spider_spawner");
        block(OtherBlocks.SPIDER_SPAWNER);
        block(OtherBlocks.TARANTULA_SPAWNER, "spider_spawner");
        block(OtherBlocks.WASP_SPAWNER);
        block(OtherBlocks.ZOMBIE_ANT_SPAWNER);
        block(OtherBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER, "zombie_ant_spawner");
        block(OtherBlocks.MAGMA_CRAWLER_SPAWNER);
        block(OtherBlocks.LOCUST_SPAWNER);

        // MARK: Utility Blocks
        craftingTable(OtherBlocks.PETRIFIED_CRAFTING_TABLE);
        //block(PETRIFIED_WOOD_CHEST); TODO: Add in chest
       // block(OtherBlocks.SILO_ROOF);
       // block(OtherBlocks.SILO_TANK, "silo_tank_inactive");
        //block(OtherBlocks.SILO_SUPPORTS);
        furnace(OtherBlocks.UMBER_FURNACE);
        button(OtherBlocks.UMBERSTONE_BUTTON, UmberstoneBlocks.UMBERSTONE);
//        block(GLOW_GEM); TODO Implement these
//        block(MUCUS_BOMB);

        // MARK: Antlion Dungeon
        block(OtherBlocks.CAPSTONE);
        block(OtherBlocks.CAPSTONE_MUD);
        block(OtherBlocks.CAPSTONE_IRON);
        block(OtherBlocks.CAPSTONE_GOLD);
        block(OtherBlocks.CAPSTONE_JADE);
        block(OtherBlocks.TEMPLE_BRICK_UNBREAKING, "temple_brick");
        block(OtherBlocks.TEMPLE_BRICK_UNBREAKING_JADE, "temple_brick_jade");
        block(OtherBlocks.TEMPLE_BRICK_UNBREAKING_EXO, "temple_brick_exo");
        block(OtherBlocks.TEMPLE_BRICK_UNBREAKING_CREAM, "temple_brick_cream");
        block(OtherBlocks.TEMPLE_BRICK_UNBREAKING_EYE, "temple_brick_eye");
        block(OtherBlocks.TEMPLE_BRICK_UNBREAKING_STRING, "temple_brick_string");
        block(OtherBlocks.TEMPLE_TELEPORTER, "temple_teleport_0");
        block(OtherBlocks.FORCE_FIELD);
        block(OtherBlocks.FORCE_LOCK);
        block(OtherBlocks.ANT_HILL_BLOCK);
    }
}
