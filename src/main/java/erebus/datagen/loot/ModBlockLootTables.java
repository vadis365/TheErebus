package erebus.datagen.loot;

import erebus.datagen.providers.ModBlockLootTableProvider;
import erebus.registries.ModItems;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.blocks.providers.*;
import erebus.registries.data.ModDataComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import org.jetbrains.annotations.NotNull;

public class ModBlockLootTables extends ModBlockLootTableProvider {

    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(provider);
    }

    @Override
    protected void generate() {
        // MARK: Umberstone
        dropWhenSilkTouch(UmberstoneBlocks.UMBERSTONE.get());
        dropOther(UmberstoneBlocks.UMBERSTONE, UmberstoneBlocks.UMBERCOBBLE);
        dropSelf(UmberstoneBlocks.UMBERSTONE_BRICKS);
        dropSelf(UmberstoneBlocks.UMBERCOBBLE);
        dropSelf(UmberstoneBlocks.UMBERCOBBLE_MOSSY);
        dropSelf(UmberstoneBlocks.UMBERCOBBLE_WEBBED);
        dropSelf(UmberstoneBlocks.UMBERTILE_SMOOTH);
        dropSelf(UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL);
        dropSelf(UmberstoneBlocks.UMBERGRAVEL);
        dropSelf(UmberstoneBlocks.UMBERPAVER);
        dropSelf(UmberstoneBlocks.UMBERPAVER_MOSSY);
        dropSelf(UmberstoneBlocks.UMBERPAVER_WEBBED);
        dropSelf(UmberstoneBlocks.UMBERSTONE_PILLAR);
        dropSelf(UmberstoneBlocks.VOLCANIC_ROCK);
        dropSelf(UmberstoneBlocks.DUST);
        dropSelf(UmberstoneBlocks.DUST_LAYER);
        dropSelf(UmberstoneBlocks.PETRIFIED_WOOD_ROCK);
        dropSelf(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_2);
        dropSelf(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_3);
        dropSelf(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_4);
        dropSelf(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_5);
        dropSelf(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_6);
        dropSelf(UmberstoneBlocks.PETRIFIED_BARK_RED);
        dropSelf(UmberstoneBlocks.PETRIFIED_BARK_BROWN);
        dropSelf(UmberstoneBlocks.PETRIFIED_LOG_INNER);
        dropSelf(UmberstoneBlocks.DUNG);

        // MARK: Amber
        dropSelf(AmberBlocks.AMBER);
        dropSelf(AmberBlocks.AMBER_GLASS);
        dropSelf(AmberBlocks.AMBER_BRICKS);
        dropSelf(AmberBlocks.PRESERVED_AMBER);
        dropSelf(AmberBlocks.PRESERVED_AMBER_GLASS);
        dropSelf(AmberBlocks.GLOWING_JAR);
        dropSelf(AmberBlocks.AMBER_DOOR);

        dropSelf(UmberstoneBlocks.MIR_BRICKS);
        dropSelf(UmberstoneBlocks.MUD_BRICKS);

        // MARK: Ores
        ore(OreBlocks.IRON, Items.RAW_IRON);
        ore(OreBlocks.GOLD, Items.RAW_GOLD);
        ore(OreBlocks.COAL, Items.COAL);
        ore(OreBlocks.DIAMOND, Items.DIAMOND);
        ore(OreBlocks.EMERALD, Items.EMERALD);
        ore(OreBlocks.LAPIS, Items.LAPIS_LAZULI);
        ore(OreBlocks.QUARTZ, Items.QUARTZ);
        ore(OreBlocks.PETRIFIED_QUARTZ, Items.QUARTZ);
        ore(OreBlocks.COPPER, Items.RAW_COPPER);
        ore(OreBlocks.SILVER, ModItems.INGOT_SILVER);
        ore(OreBlocks.TIN, ModItems.INGOT_TIN);
        ore(OreBlocks.LEAD, ModItems.INGOT_LEAD);
        ore(OreBlocks.ALUMINUM, ModItems.INGOT_ALUMINUM);
        ore(OreBlocks.JADE, ModItems.JADE);
        ore(OreBlocks.ENCRUSTED_DIAMOND, Items.DIAMOND);
        ore(OreBlocks.FOSSIL, Items.BONE);
        ore(OreBlocks.GNEISS, ModItems.GNEISS_ROCK);
        ore(OreBlocks.PETRIFIED_WOOD, ModItems.PETRIFIED_WOOD);
        ore(OreBlocks.TEMPLE, ModItems.TEMPLE_ROCK);

        // MARK: Logs
        dropSelf(WoodBlocks.LOG_BAOBAB);
        dropSelf(WoodBlocks.LOG_EUCALYPTUS);
        dropSelf(WoodBlocks.LOG_MAHOGANY);
        dropSelf(WoodBlocks.LOG_MOSSBARK);
        dropSelf(WoodBlocks.LOG_ASPER);
        dropSelf(WoodBlocks.LOG_CYPRESS);
        dropSelf(WoodBlocks.LOG_BALSAM);
        dropSelf(WoodBlocks.LOG_BALSAM_RESINLESS);
        dropSelf(WoodBlocks.LOG_ROTTEN);
        dropSelf(WoodBlocks.LOG_MARSHWOOD);
        dropSelf(WoodBlocks.LOG_SCORCHED);
        dropSelf(WoodBlocks.LOG_HOLLOW);

        // MARK: Saplings
        dropSelf(WoodBlocks.SAPLING_BAOBAB);
        dropSelf(WoodBlocks.SAPLING_EUCALYPTUS);
        dropSelf(WoodBlocks.SAPLING_MAHOGANY);
        dropSelf(WoodBlocks.SAPLING_MOSSBARK);
        dropSelf(WoodBlocks.SAPLING_ASPER);
        dropSelf(WoodBlocks.SAPLING_CYPRESS);
        dropSelf(WoodBlocks.SAPLING_BALSAM);
        dropSelf(WoodBlocks.SAPLING_MARSHWOOD);
        dropSelf(WoodBlocks.SAPLING_BAMBOO);

        // MARK: Leaves
        dropSelf(WoodBlocks.LEAVES_BAOBAB);
        dropSelf(WoodBlocks.LEAVES_EUCALYPTUS);
        dropSelf(WoodBlocks.LEAVES_MAHOGANY);
        dropSelf(WoodBlocks.LEAVES_MOSSBARK);
        dropSelf(WoodBlocks.LEAVES_ASPER);
        dropSelf(WoodBlocks.LEAVES_CYPRESS);
        dropSelf(WoodBlocks.LEAVES_BALSAM);
        dropSelf(WoodBlocks.LEAVES_MARSHWOOD);

        // MARK: Planks
        dropSelf(WoodBlocks.PLANKS_BAOBAB);
        dropSelf(WoodBlocks.PLANKS_EUCALYPTUS);
        dropSelf(WoodBlocks.PLANKS_MAHOGANY);
        dropSelf(WoodBlocks.PLANKS_MOSSBARK);
        dropSelf(WoodBlocks.PLANKS_ASPER);
        dropSelf(WoodBlocks.PLANKS_CYPRESS);
        dropSelf(WoodBlocks.PLANKS_BALSAM);
        dropSelf(WoodBlocks.PLANKS_WHITE);
        dropSelf(WoodBlocks.PLANKS_BAMBOO);
        dropSelf(WoodBlocks.PLANKS_ROTTEN);
        dropSelf(WoodBlocks.PLANKS_MARSHWOOD);
        dropSelf(WoodBlocks.PLANKS_SCORCHED);
        dropSelf(WoodBlocks.PLANKS_VARNISHED);
        dropSelf(WoodBlocks.PLANKS_PETRIFIED);

        // MARK: Slabs Wood
        dropSelf(SlabBlocks.PLANKS_BAOBAB);
        dropSelf(SlabBlocks.PLANKS_EUCALYPTUS);
        dropSelf(SlabBlocks.PLANKS_MAHOGANY);
        dropSelf(SlabBlocks.PLANKS_MOSSBARK);
        dropSelf(SlabBlocks.PLANKS_ASPER);
        dropSelf(SlabBlocks.PLANKS_CYPRESS);
        dropSelf(SlabBlocks.PLANKS_BALSAM);
        dropSelf(SlabBlocks.PLANKS_WHITE);
        dropSelf(SlabBlocks.PLANKS_BAMBOO);
        dropSelf(SlabBlocks.PLANKS_ROTTEN);
        dropSelf(SlabBlocks.PLANKS_MARSHWOOD);
        dropSelf(SlabBlocks.PLANKS_SCORCHED);
        dropSelf(SlabBlocks.PLANKS_VARNISHED);
        dropSelf(SlabBlocks.PLANKS_PETRIFIED);

        // MARK: Slabs Stone
        dropSelf(SlabBlocks.UMBERSTONE);
        dropSelf(SlabBlocks.UMBERCOBBLE);
        dropSelf(SlabBlocks.UMBERCOBBLE_MOSSY);
        dropSelf(SlabBlocks.UMBERCOBBLE_WEBBED);
        dropSelf(SlabBlocks.UMBERSTONE_BRICKS);
        dropSelf(SlabBlocks.UMBERTILE_SMOOTH);
        dropSelf(SlabBlocks.UMBERTILE_SMOOTH_SMALL);
        dropSelf(SlabBlocks.UMBERPAVER);
        dropSelf(SlabBlocks.AMBER);
        dropSelf(SlabBlocks.AMBER_BRICKS);
        dropSelf(SlabBlocks.UMBERPAVER_MOSSY);
        dropSelf(SlabBlocks.UMBERPAVER_WEBBED);
        dropSelf(SlabBlocks.MIR_BRICKS);
        dropSelf(SlabBlocks.MUD_BRICKS);

        // MARK: Stairs Wood
        dropSelf(StairBlocks.BAOBAB);
        dropSelf(StairBlocks.EUCALYPTUS);
        dropSelf(StairBlocks.MAHOGANY);
        dropSelf(StairBlocks.MOSSBARK);
        dropSelf(StairBlocks.ASPER);
        dropSelf(StairBlocks.CYPRESS);
        dropSelf(StairBlocks.BALSAM);
        dropSelf(StairBlocks.WHITE);
        dropSelf(StairBlocks.BAMBOO);
        dropSelf(StairBlocks.ROTTEN);
        dropSelf(StairBlocks.MARSHWOOD);
        dropSelf(StairBlocks.SCORCHED);
        dropSelf(StairBlocks.VARNISHED);
        dropSelf(StairBlocks.PETRIFIED);

        // MARK: Stairs Stone
        dropSelf(StairBlocks.UMBERSTONE);
        dropSelf(StairBlocks.UMBERCOBBLE);
        dropSelf(StairBlocks.UMBERCOBBLE_MOSSY);
        dropSelf(StairBlocks.UMBERCOBBLE_WEBBED);
        dropSelf(StairBlocks.UMBERSTONE_BRICKS);
        dropSelf(StairBlocks.UMBERTILE_SMOOTH);
        dropSelf(StairBlocks.UMBERTILE_SMOOTH_SMALL);
        dropSelf(StairBlocks.UMBERPAVER);
        dropSelf(StairBlocks.UMBERPAVER_MOSSY);
        dropSelf(StairBlocks.UMBERPAVER_WEBBED);
        dropSelf(StairBlocks.AMBER);
        dropSelf(StairBlocks.AMBER_BRICKS);
        dropSelf(StairBlocks.MUD_BRICKS);
        dropSelf(StairBlocks.MIR_BRICKS);
        dropSelf(OtherBlocks.STAIRS_WASP_NEST);

        // MARK: Doors
        dropSelf(DoorBlocks.BAOBAB);
        dropSelf(DoorBlocks.EUCALYPTUS);
        dropSelf(DoorBlocks.MAHOGANY);
        dropSelf(DoorBlocks.MOSSBARK);
        dropSelf(DoorBlocks.ASPER);
        dropSelf(DoorBlocks.CYPRESS);
        dropSelf(DoorBlocks.BALSAM);
        dropSelf(DoorBlocks.WHITE);
        dropSelf(DoorBlocks.ROTTEN);
        dropSelf(DoorBlocks.MARSHWOOD);
        dropSelf(DoorBlocks.SCORCHED);

        // MARK: Fences
        dropSelf(FenceBlocks.FENCE_BAOBAB);
        dropSelf(FenceBlocks.FENCE_EUCALYPTUS);
        dropSelf(FenceBlocks.FENCE_MAHOGANY);
        dropSelf(FenceBlocks.FENCE_MOSSBARK);
        dropSelf(FenceBlocks.FENCE_ASPER);
        dropSelf(FenceBlocks.FENCE_CYPRESS);
        dropSelf(FenceBlocks.FENCE_BALSAM);
        dropSelf(FenceBlocks.FENCE_WHITE);
        dropSelf(FenceBlocks.FENCE_BAMBOO);
        dropSelf(FenceBlocks.FENCE_ROTTEN);
        dropSelf(FenceBlocks.FENCE_MARSHWOOD);
        dropSelf(FenceBlocks.FENCE_SCORCHED);
        dropSelf(FenceBlocks.FENCE_VARNISHED);

        // MARK: Fence Gates
        dropSelf(FenceBlocks.FENCE_GATE_BAOBAB);
        dropSelf(FenceBlocks.FENCE_GATE_EUCALYPTUS);
        dropSelf(FenceBlocks.FENCE_GATE_MAHOGANY);
        dropSelf(FenceBlocks.FENCE_GATE_MOSSBARK);
        dropSelf(FenceBlocks.FENCE_GATE_ASPER);
        dropSelf(FenceBlocks.FENCE_GATE_CYPRESS);
        dropSelf(FenceBlocks.FENCE_GATE_BALSAM);
        dropSelf(FenceBlocks.FENCE_GATE_WHITE);
        dropSelf(FenceBlocks.FENCE_GATE_BAMBOO);
        dropSelf(FenceBlocks.FENCE_GATE_ROTTEN);
        dropSelf(FenceBlocks.FENCE_GATE_MARSHWOOD);
        dropSelf(FenceBlocks.FENCE_GATE_SCORCHED);
        dropSelf(FenceBlocks.FENCE_GATE_VARNISHED);

        // MARK: Walls
        dropSelf(WallBlocks.WALL_UMBERSTONE);
        dropSelf(WallBlocks.WALL_UMBERCOBBLE);
        dropSelf(WallBlocks.WALL_UMBERCOBBLE_MOSSY);
        dropSelf(WallBlocks.WALL_UMBERCOBBLE_WEBBED);
        dropSelf(WallBlocks.WALL_UMBERSTONE_BRICKS);
        dropSelf(WallBlocks.WALL_UMBERTILE_SMOOTH);
        dropSelf(WallBlocks.WALL_UMBERTILE_SMOOTH_SMALL);
        dropSelf(WallBlocks.WALL_UMBERPAVER);
        dropSelf(WallBlocks.WALL_UMBERPAVER_MOSSY);
        dropSelf(WallBlocks.WALL_UMBERPAVER_WEBBED);
        dropSelf(WallBlocks.WALL_AMBER);
        dropSelf(WallBlocks.WALL_AMBER_BRICKS);

        // MARK: Plants
        dropCropBasedOffCondition(PlantBlocks.CROP_TURNIP, ModItems.TURNIP, ModItems.TURNIP);
        dropCropBasedOffCondition(PlantBlocks.CROP_CABBAGE, ModItems.CABBAGE, ModItems.CABBAGE_SEEDS);
        dropCropBasedOffCondition(PlantBlocks.CROP_MANDRAKE, ModItems.MANDRAKE_ROOT, ModItems.MANDRAKE_ROOT);
        dropSelf(PlantBlocks.JADE_BERRY_BUSH);
        dropSelf(PlantBlocks.HEART_BERRY_BUSH);
        dropSelf(PlantBlocks.SWAMP_BERRY_BUSH);
        dropSelf(PlantBlocks.DARK_FRUIT_VINE);
        dropPricklyPearBasedOffCondition(PlantBlocks.PRICKLY_PEAR);
        dropColossalBambooBasedOffCondition(PlantBlocks.COLOSSAL_BAMBOO);
        dropSelf(PlantBlocks.DARK_CAPPED_MUSHROOM);
        dropSelf(PlantBlocks.DUTCH_CAP_MUSHROOM);
        dropSelf(PlantBlocks.GRANDMAS_SHOES_MUSHROOM);
        dropSelf(PlantBlocks.KAIZERS_FINGERS_MUSHROOM);
        dropSelf(PlantBlocks.SARCASTIC_CZECH_MUSHROOM);
        dropSelf(PlantBlocks.DARK_CAPPED_MUSHROOM_BLOCK);
        dropSelf(PlantBlocks.DARK_CAPPED_MUSHROOM_STEM);
        dropSelf(PlantBlocks.DUTCH_CAP_MUSHROOM_BLOCK);
        dropSelf(PlantBlocks.DUTCH_CAP_MUSHROOM_STEM);
        dropSelf(PlantBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK);
        dropSelf(PlantBlocks.GRANDMAS_SHOES_MUSHROOM_STEM);
        dropSelf(PlantBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK);
        dropSelf(PlantBlocks.KAIZERS_FINGERS_MUSHROOM_STEM);
        dropSelf(PlantBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK);
        dropSelf(PlantBlocks.SARCASTIC_CZECH_MUSHROOM_STEM);
        dropSelf(PlantBlocks.DESERT_SHRUB);
        dropSelf(PlantBlocks.MIRE_CORAL);
        dropSelf(PlantBlocks.NETTLE);
        dropSelf(PlantBlocks.NETTLE_FLOWERED);
        dropSelf(PlantBlocks.SWAMP_PLANT);
        dropSelf(PlantBlocks.FIRE_BLOOM);
        dropSelf(PlantBlocks.FERN);
        dropSelf(PlantBlocks.FIDDLE_HEAD);
        dropSelf(PlantBlocks.THORNS);
        dropSelf(PlantBlocks.MOSS);
        dropSelf(PlantBlocks.MOULD);
        dropSelf(PlantBlocks.MOSS_CULTIVATED);
        dropSelf(PlantBlocks.MOULD_CULTIVATED);
        dropSelf(PlantBlocks.ALGAE);
        dropSelf(PlantBlocks.GLOWSHROOM_BLOCK);
        dropSelf(PlantBlocks.GLOWSHROOM_STALK);
        dropSelf(PlantBlocks.HANGING_WEB);
        dropSelf(PlantBlocks.GIANT_LILY_PAD);

        // MARK: Flowers
        dropSelf(PlantBlocks.PETAL_BLACK);
        dropSelf(PlantBlocks.PETAL_RED);
        dropSelf(PlantBlocks.PETAL_BROWN);
        dropSelf(PlantBlocks.PETAL_BLUE);
        dropSelf(PlantBlocks.PETAL_PURPLE);
        dropSelf(PlantBlocks.PETAL_CYAN);
        dropSelf(PlantBlocks.PETAL_LIGHT_GRAY);
        dropSelf(PlantBlocks.PETAL_GRAY);
        dropSelf(PlantBlocks.PETAL_PINK);
        dropSelf(PlantBlocks.PETAL_YELLOW);
        dropSelf(PlantBlocks.PETAL_LIGHT_BLUE);
        dropSelf(PlantBlocks.PETAL_MAGENTA);
        dropSelf(PlantBlocks.PETAL_ORANGE);
        dropSelf(PlantBlocks.PETAL_WHITE);

        dropSelf(PlantBlocks.EXPLODING_STIGMA);
        dropSelf(PlantBlocks.STEM);
        dropSelf(PlantBlocks.STIGMA_BLACK);
        dropSelf(PlantBlocks.STIGMA_RED);
        dropSelf(PlantBlocks.STIGMA_BROWN);
        dropSelf(PlantBlocks.STIGMA_BLUE);
        dropSelf(PlantBlocks.STIGMA_PURPLE);
        dropSelf(PlantBlocks.STIGMA_CYAN);
        dropSelf(PlantBlocks.STIGMA_LIGHT_GRAY);
        dropSelf(PlantBlocks.STIGMA_GRAY);
        dropSelf(PlantBlocks.STIGMA_PINK);
        dropSelf(PlantBlocks.STIGMA_YELLOW);
        dropSelf(PlantBlocks.STIGMA_LIGHT_BLUE);
        dropSelf(PlantBlocks.STIGMA_MAGENTA);
        dropSelf(PlantBlocks.STIGMA_ORANGE);
        dropSelf(PlantBlocks.STIGMA_WHITE);

        dropSelf(PlantBlocks.FLOWER_BLACK);
        dropSelf(PlantBlocks.FLOWER_RED);
        dropSelf(PlantBlocks.FLOWER_BROWN);
        dropSelf(PlantBlocks.FLOWER_BLUE);
        dropSelf(PlantBlocks.FLOWER_PURPLE);
        dropSelf(PlantBlocks.FLOWER_CYAN);
        dropSelf(PlantBlocks.FLOWER_LIGHT_GRAY);
        dropSelf(PlantBlocks.FLOWER_GRAY);
        dropSelf(PlantBlocks.FLOWER_PINK);
        dropSelf(PlantBlocks.FLOWER_YELLOW);
        dropSelf(PlantBlocks.FLOWER_LIGHT_BLUE);
        dropSelf(PlantBlocks.FLOWER_MAGENTA);
        dropSelf(PlantBlocks.FLOWER_ORANGE);
        dropSelf(PlantBlocks.FLOWER_WHITE);
        dropSelf(PlantBlocks.FLOWER_RAINBOW);

        // MARK: Flowers Double Height
        dropSelf(PlantBlocks.BULLRUSH);
        dropSelf(PlantBlocks.WEEPING_BLUEBELL);
        dropSelf(PlantBlocks.SUNDEW);
        dropSelf(PlantBlocks.DROUGHTED_SHRUB);
        dropSelf(PlantBlocks.TALL_BLOOM);
        dropSelf(PlantBlocks.TANGLED_STALK);
        dropSelf(PlantBlocks.HIGH_CAPPED_MUSHROOM);
        dropSelf(PlantBlocks.TALL_FERN);

        dropSelf(OtherBlocks.PORTAL);
        dropSelf(OtherBlocks.JADE_BLOCK);
        dropSelf(OtherBlocks.MUD);
        dropSelf(OtherBlocks.QUICK_SAND);
        dropSelf(OtherBlocks.GHOST_SAND);
        dropSelf(OtherBlocks.SWAMP_VENT);
        dropSelf(OtherBlocks.GNEISS_VENT);
        dropSelf(OtherBlocks.RED_GEM_BLOCK);
        dropSelf(OtherBlocks.RED_GEM_LAMP);
        dropSelf(OtherBlocks.GNEISS);
        dropSelf(OtherBlocks.GNEISS_CARVED);
        dropSelf(OtherBlocks.GNEISS_RELIEF);
        dropSelf(OtherBlocks.GNEISS_BRICKS);
        dropSelf(OtherBlocks.GNEISS_SMOOTH);
        dropSelf(OtherBlocks.GNEISS_TILES);
        dropSelf(OtherBlocks.GNEISS_TILES_CRACKED);
        dropSelf(OtherBlocks.TEMPLE_BRICK);
        dropSelf(OtherBlocks.TEMPLE_PILLAR);
        dropSelf(OtherBlocks.TEMPLE_TILE);
        dropSelf(OtherBlocks.SILK);
        dropSelf(OtherBlocks.REIN_EXO);
        dropSelf(OtherBlocks.VELOCITY_BLOCK);
        dropSelf(OtherBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED);
        dropSelf(OtherBlocks.BLOCK_OF_BONES);
        dropSelf(OtherBlocks.ANTLION_EGG);
        dropSelf(OtherBlocks.TARANTULA_EGG);
        dropSelf(OtherBlocks.HONEY_TREAT);
        dropSelf(OtherBlocks.CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.WHITE_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.ORANGE_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.MAGENTA_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.YELLOW_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.LIME_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.PINK_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.GRAY_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.CYAN_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.PURPLE_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.BLUE_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.BROWN_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.GREEN_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.RED_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.BLACK_CANDLE_HONEY_TREAT);
        dropSelf(OtherBlocks.WASP_NEST);
        dropSelf(OtherBlocks.STAIRS_WASP_NEST);
        dropSelf(OtherBlocks.INSECT_REPELLENT);

        // MARK: Spawners
        dropSelf(OtherBlocks.ANTLION_SPAWNER);
        dropSelf(OtherBlocks.DRAGON_FLY_SPAWNER);
        dropSelf(OtherBlocks.JUMPING_SPIDER_SPAWNER);
        dropSelf(OtherBlocks.SPIDER_SPAWNER);
        dropSelf(OtherBlocks.TARANTULA_SPAWNER);
        dropSelf(OtherBlocks.WASP_SPAWNER);
        dropSelf(OtherBlocks.ZOMBIE_ANT_SPAWNER);
        dropSelf(OtherBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER);
        dropSelf(OtherBlocks.MAGMA_CRAWLER_SPAWNER);
        dropSelf(OtherBlocks.DUNG_SPAWNER_FLY);
        dropSelf(OtherBlocks.DUNG_SPAWNER_BOT_FLY);
        dropSelf(OtherBlocks.LOCUST_SPAWNER);

        // MARK: Utility Blocks
        dropSelf(OtherBlocks.PETRIFIED_CRAFTING_TABLE);
        dropSelf(OtherBlocks.BAMBOO_CRATE);
        dropSelf(OtherBlocks.BAMBOO_BRIDGE);
        dropSelf(OtherBlocks.BAMBOO_LADDER);
        dropSelf(OtherBlocks.BAMBOO_NERD_POLE);
        dropSelf(OtherBlocks.BAMBOO_EXTENDER);
        dropSingleBambooTorchCondition(OtherBlocks.BAMBOO_TORCH);
        dropSelf(OtherBlocks.BAMBOO_PIPE);
        dropSelf(OtherBlocks.BAMBOO_PIPE_EXTRACT);
        dropSelf(OtherBlocks.SILO_ROOF);
        dropSelf(OtherBlocks.SILO_TANK);
        dropSelf(OtherBlocks.SILO_SUPPORTS);
        dropSelf(OtherBlocks.HONEY_COMB);
        dropSelf(OtherBlocks.COMPOSTER);
        dropSelf(OtherBlocks.BLENDER);
        dropSelf(OtherBlocks.UMBER_FURNACE);
        dropSelf(OtherBlocks.UMBERSTONE_BUTTON);
        dropSelf(OtherBlocks.LIQUIFIER);
        dropOther(OtherBlocks.GLOW_GEM_ACTIVE, OtherBlocks.GLOW_GEM_INACTIVE);
        dropSelf(OtherBlocks.GLOW_GEM_INACTIVE);
        dropSelf(OtherBlocks.MUCUS_BOMB);
        dropSelf(OtherBlocks.UMBER_GOLEM_STATUE);

        // MARK: Chests
        dropSelf(ChestBlocks.CHEST_ASPER);
        dropSelf(ChestBlocks.CHEST_BALSAM);
        dropSelf(ChestBlocks.CHEST_BAOBAB);
        dropSelf(ChestBlocks.CHEST_BAMBOO);
        dropSelf(ChestBlocks.CHEST_CYPRESS);
        dropSelf(ChestBlocks.CHEST_EUCALYPTUS);
        dropSelf(ChestBlocks.CHEST_MAHOGANY);
        dropSelf(ChestBlocks.CHEST_MARSHWOOD);
        dropSelf(ChestBlocks.CHEST_MOSSBARK);
        dropSelf(ChestBlocks.CHEST_PETRIFIED);
        dropSelf(ChestBlocks.CHEST_ROTTEN);
        dropSelf(ChestBlocks.CHEST_SCORCHED);
        dropSelf(ChestBlocks.CHEST_VARNISHED);
        dropSelf(ChestBlocks.CHEST_WHITE);

        dropSelf(OtherBlocks.ALTAR_BASE);
        dropSelf(OtherBlocks.ALTAR_LIGHTNING);
        dropSelf(OtherBlocks.ALTAR_HEALING);
        dropSelf(OtherBlocks.ALTAR_EXPERIENCE);
        dropSelf(OtherBlocks.ALTAR_REPAIR);
        dropSelf(OtherBlocks.OFFERING_ALTAR);
        dropSelf(OtherBlocks.GAEAN_KEYSTONE);

        dropSelf(OtherBlocks.CAPSTONE);
        dropSelf(OtherBlocks.CAPSTONE_MUD);
        dropSelf(OtherBlocks.CAPSTONE_IRON);
        dropSelf(OtherBlocks.CAPSTONE_GOLD);
        dropSelf(OtherBlocks.CAPSTONE_JADE);
        dropSelf(OtherBlocks.TEMPLE_BRICK_UNBREAKING);
        dropSelf(OtherBlocks.TEMPLE_BRICK_UNBREAKING_JADE);
        dropSelf(OtherBlocks.TEMPLE_BRICK_UNBREAKING_EXO);
        dropSelf(OtherBlocks.TEMPLE_BRICK_UNBREAKING_CREAM);
        dropSelf(OtherBlocks.TEMPLE_BRICK_UNBREAKING_EYE);
        dropSelf(OtherBlocks.TEMPLE_BRICK_UNBREAKING_STRING);
        dropSelf(OtherBlocks.TEMPLE_TELEPORTER);
        dropSelf(OtherBlocks.FORCE_FIELD);
        dropSelf(OtherBlocks.FORCE_LOCK);
        dropSelf(OtherBlocks.ANT_HILL_BLOCK);
        
        //Webs
        add(OtherBlocks.WITHER_WEB.get(), createSilkTouchOrShearsDispatchTable(OtherBlocks.WITHER_WEB.get(), applyExplosionCondition(OtherBlocks.WITHER_WEB, LootItem.lootTableItem(Items.STRING))));
        add(OtherBlocks.LAVA_WEB.get(), createSilkTouchOrShearsDispatchTable(OtherBlocks.LAVA_WEB.get(), applyExplosionCondition(OtherBlocks.WITHER_WEB, LootItem.lootTableItem(Items.STRING))));
    
        // Fluid Tank Blocks
        CopyComponentsFunction.Builder copyFluid = CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                .include(ModDataComponents.FLUID.get());
        
      // Portable Inventory Storage Blocks
        CopyComponentsFunction.Builder copyItems = CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY)
                .include(DataComponents.CONTAINER);

        dropComponents(AmberBlocks.FLUID_JAR, $ -> $.apply(copyFluid));
        dropComponents(OtherBlocks.LIQUIFIER, $ -> $.apply(copyFluid));
        dropComponents(OtherBlocks.BAMBOO_CRATE, $ -> $.apply(copyItems));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(e -> (Block) e.value()).toList();
    }
}
