package erebus.datagen.loot;

import erebus.datagen.providers.ModBlockLootTableProvider;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.ModDataComponents;
import erebus.registries.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.jetbrains.annotations.NotNull;

public class ModBlockLootTables extends ModBlockLootTableProvider {

    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(provider);
    }

    @Override
    protected void generate() {
        // MARK: Umberstone
        dropWhenSilkTouch(ModBlocks.UMBERSTONE.get());
        dropOther(ModBlocks.UMBERSTONE, ModBlocks.UMBERCOBBLE);
        dropSelf(ModBlocks.UMBERSTONE_BRICKS);
        dropSelf(ModBlocks.UMBERCOBBLE);
        dropSelf(ModBlocks.UMBERCOBBLE_MOSSY);
        dropSelf(ModBlocks.UMBERCOBBLE_WEBBED);
        dropSelf(ModBlocks.UMBERTILE_SMOOTH);
        dropSelf(ModBlocks.UMBERTILE_SMOOTH_SMALL);
        dropSelf(ModBlocks.UMBERGRAVEL);
        dropSelf(ModBlocks.UMBERPAVER);
        dropSelf(ModBlocks.UMBERPAVER_MOSSY);
        dropSelf(ModBlocks.UMBERPAVER_WEBBED);
        dropSelf(ModBlocks.UMBERSTONE_PILLAR);
        dropSelf(ModBlocks.VOLCANIC_ROCK);
        dropSelf(ModBlocks.DUST);
        dropSelf(ModBlocks.DUST_LAYER);
        dropSelf(ModBlocks.PETRIFIED_WOOD_ROCK);
        dropSelf(ModBlocks.PETRIFIED_WOOD_ROCK_2);
        dropSelf(ModBlocks.PETRIFIED_WOOD_ROCK_3);
        dropSelf(ModBlocks.PETRIFIED_WOOD_ROCK_4);
        dropSelf(ModBlocks.PETRIFIED_WOOD_ROCK_5);
        dropSelf(ModBlocks.PETRIFIED_WOOD_ROCK_6);
        dropSelf(ModBlocks.PETRIFIED_BARK_RED);
        dropSelf(ModBlocks.PETRIFIED_BARK_BROWN);
        dropSelf(ModBlocks.PETRIFIED_LOG_INNER);
        dropSelf(ModBlocks.DUNG);

        // MARK: Amber
        dropSelf(ModBlocks.AMBER);
        dropSelf(ModBlocks.AMBER_GLASS);
        dropSelf(ModBlocks.AMBER_BRICKS);
        dropSelf(ModBlocks.PRESERVED_AMBER);
        dropSelf(ModBlocks.PRESERVED_AMBER_GLASS);
        dropSelf(ModBlocks.GLOWING_JAR);
        dropSelf(ModBlocks.AMBER_DOOR);

        dropSelf(ModBlocks.MIR_BRICKS);
        dropSelf(ModBlocks.MUD_BRICKS);

        // MARK: Ores
        ore(ModBlocks.ORE_IRON, Items.RAW_IRON);
        ore(ModBlocks.ORE_GOLD, Items.RAW_GOLD);
        ore(ModBlocks.ORE_COAL, Items.COAL);
        ore(ModBlocks.ORE_DIAMOND, Items.DIAMOND);
        ore(ModBlocks.ORE_EMERALD, Items.EMERALD);
        ore(ModBlocks.ORE_LAPIS, Items.LAPIS_LAZULI);
        ore(ModBlocks.ORE_QUARTZ, Items.QUARTZ);
        ore(ModBlocks.ORE_PETRIFIED_QUARTZ, Items.QUARTZ);
        ore(ModBlocks.ORE_COPPER, Items.RAW_COPPER);
        ore(ModBlocks.ORE_SILVER, ModItems.INGOT_SILVER);
        ore(ModBlocks.ORE_TIN, ModItems.INGOT_TIN);
        ore(ModBlocks.ORE_LEAD, ModItems.INGOT_LEAD);
        ore(ModBlocks.ORE_ALUMINUM, ModItems.INGOT_ALUMINUM);
        ore(ModBlocks.ORE_JADE, ModItems.JADE);
        ore(ModBlocks.ORE_ENCRUSTED_DIAMOND, Items.DIAMOND);
        ore(ModBlocks.ORE_FOSSIL, Items.BONE);
        ore(ModBlocks.ORE_GNEISS, ModItems.GNEISS_ROCK);
        ore(ModBlocks.ORE_PETRIFIED_WOOD, ModItems.PETRIFIED_WOOD);
        ore(ModBlocks.ORE_TEMPLE, ModItems.TEMPLE_ROCK);

        // MARK: Logs
        dropSelf(ModBlocks.LOG_BAOBAB);
        dropSelf(ModBlocks.LOG_EUCALYPTUS);
        dropSelf(ModBlocks.LOG_MAHOGANY);
        dropSelf(ModBlocks.LOG_MOSSBARK);
        dropSelf(ModBlocks.LOG_ASPER);
        dropSelf(ModBlocks.LOG_CYPRESS);
        dropSelf(ModBlocks.LOG_BALSAM);
        dropSelf(ModBlocks.LOG_BALSAM_RESINLESS);
        dropSelf(ModBlocks.LOG_ROTTEN);
        dropSelf(ModBlocks.LOG_MARSHWOOD);
        dropSelf(ModBlocks.LOG_SCORCHED);
        dropSelf(ModBlocks.LOG_HOLLOW);

        // MARK: Saplings
        dropSelf(ModBlocks.SAPLING_BAOBAB);
        dropSelf(ModBlocks.SAPLING_EUCALYPTUS);
        dropSelf(ModBlocks.SAPLING_MAHOGANY);
        dropSelf(ModBlocks.SAPLING_MOSSBARK);
        dropSelf(ModBlocks.SAPLING_ASPER);
        dropSelf(ModBlocks.SAPLING_CYPRESS);
        dropSelf(ModBlocks.SAPLING_BALSAM);
        dropSelf(ModBlocks.SAPLING_MARSHWOOD);
        dropSelf(ModBlocks.SAPLING_BAMBOO);

        // MARK: Leaves
        dropSelf(ModBlocks.LEAVES_BAOBAB);
        dropSelf(ModBlocks.LEAVES_EUCALYPTUS);
        dropSelf(ModBlocks.LEAVES_MAHOGANY);
        dropSelf(ModBlocks.LEAVES_MOSSBARK);
        dropSelf(ModBlocks.LEAVES_ASPER);
        dropSelf(ModBlocks.LEAVES_CYPRESS);
        dropSelf(ModBlocks.LEAVES_BALSAM);
        dropSelf(ModBlocks.LEAVES_MARSHWOOD);

        // MARK: Planks
        dropSelf(ModBlocks.PLANKS_BAOBAB);
        dropSelf(ModBlocks.PLANKS_EUCALYPTUS);
        dropSelf(ModBlocks.PLANKS_MAHOGANY);
        dropSelf(ModBlocks.PLANKS_MOSSBARK);
        dropSelf(ModBlocks.PLANKS_ASPER);
        dropSelf(ModBlocks.PLANKS_CYPRESS);
        dropSelf(ModBlocks.PLANKS_BALSAM);
        dropSelf(ModBlocks.PLANKS_WHITE);
        dropSelf(ModBlocks.PLANKS_BAMBOO);
        dropSelf(ModBlocks.PLANKS_ROTTEN);
        dropSelf(ModBlocks.PLANKS_MARSHWOOD);
        dropSelf(ModBlocks.PLANKS_SCORCHED);
        dropSelf(ModBlocks.PLANKS_VARNISHED);
        dropSelf(ModBlocks.PLANKS_PETRIFIED);

        // MARK: Slabs Wood
        dropSelf(ModBlocks.SLAB_PLANKS_BAOBAB);
        dropSelf(ModBlocks.SLAB_PLANKS_EUCALYPTUS);
        dropSelf(ModBlocks.SLAB_PLANKS_MAHOGANY);
        dropSelf(ModBlocks.SLAB_PLANKS_MOSSBARK);
        dropSelf(ModBlocks.SLAB_PLANKS_ASPER);
        dropSelf(ModBlocks.SLAB_PLANKS_CYPRESS);
        dropSelf(ModBlocks.SLAB_PLANKS_BALSAM);
        dropSelf(ModBlocks.SLAB_PLANKS_WHITE);
        dropSelf(ModBlocks.SLAB_PLANKS_BAMBOO);
        dropSelf(ModBlocks.SLAB_PLANKS_ROTTEN);
        dropSelf(ModBlocks.SLAB_PLANKS_MARSHWOOD);
        dropSelf(ModBlocks.SLAB_PLANKS_SCORCHED);
        dropSelf(ModBlocks.SLAB_PLANKS_VARNISHED);
        dropSelf(ModBlocks.SLAB_PLANKS_PETRIFIED);

        // MARK: Slabs Stone
        dropSelf(ModBlocks.SLAB_UMBERSTONE);
        dropSelf(ModBlocks.SLAB_UMBERCOBBLE);
        dropSelf(ModBlocks.SLAB_UMBERCOBBLE_MOSSY);
        dropSelf(ModBlocks.SLAB_UMBERCOBBLE_WEBBED);
        dropSelf(ModBlocks.SLAB_UMBERSTONE_BRICKS);
        dropSelf(ModBlocks.SLAB_UMBERTILE_SMOOTH);
        dropSelf(ModBlocks.SLAB_UMBERTILE_SMOOTH_SMALL);
        dropSelf(ModBlocks.SLAB_UMBERPAVER);
        dropSelf(ModBlocks.SLAB_AMBER);
        dropSelf(ModBlocks.SLAB_AMBER_BRICKS);
        dropSelf(ModBlocks.SLAB_UMBERPAVER_MOSSY);
        dropSelf(ModBlocks.SLAB_UMBERPAVER_WEBBED);
        dropSelf(ModBlocks.SLAB_MIR_BRICKS);
        dropSelf(ModBlocks.SLAB_MUD_BRICKS);

        // MARK: Stairs Wood
        dropSelf(ModBlocks.STAIRS_BAOBAB);
        dropSelf(ModBlocks.STAIRS_EUCALYPTUS);
        dropSelf(ModBlocks.STAIRS_MAHOGANY);
        dropSelf(ModBlocks.STAIRS_MOSSBARK);
        dropSelf(ModBlocks.STAIRS_ASPER);
        dropSelf(ModBlocks.STAIRS_CYPRESS);
        dropSelf(ModBlocks.STAIRS_BALSAM);
        dropSelf(ModBlocks.STAIRS_WHITE);
        dropSelf(ModBlocks.STAIRS_BAMBOO);
        dropSelf(ModBlocks.STAIRS_ROTTEN);
        dropSelf(ModBlocks.STAIRS_MARSHWOOD);
        dropSelf(ModBlocks.STAIRS_SCORCHED);
        dropSelf(ModBlocks.STAIRS_VARNISHED);
        dropSelf(ModBlocks.STAIRS_PETRIFIED);

        // MARK: Stairs Stone
        dropSelf(ModBlocks.STAIRS_UMBERSTONE);
        dropSelf(ModBlocks.STAIRS_UMBERCOBBLE);
        dropSelf(ModBlocks.STAIRS_UMBERCOBBLE_MOSSY);
        dropSelf(ModBlocks.STAIRS_UMBERCOBBLE_WEBBED);
        dropSelf(ModBlocks.STAIRS_UMBERSTONE_BRICKS);
        dropSelf(ModBlocks.STAIRS_UMBERTILE_SMOOTH);
        dropSelf(ModBlocks.STAIRS_UMBERTILE_SMOOTH_SMALL);
        dropSelf(ModBlocks.STAIRS_UMBERPAVER);
        dropSelf(ModBlocks.STAIRS_UMBERPAVER_MOSSY);
        dropSelf(ModBlocks.STAIRS_UMBERPAVER_WEBBED);
        dropSelf(ModBlocks.STAIRS_AMBER);
        dropSelf(ModBlocks.STAIRS_AMBER_BRICKS);
        dropSelf(ModBlocks.STAIRS_MUD_BRICKS);
        dropSelf(ModBlocks.STAIRS_MIR_BRICKS);
        dropSelf(ModBlocks.STAIRS_WASP_NEST);

        // MARK: Doors
        dropSelf(ModBlocks.DOOR_BAOBAB);
        dropSelf(ModBlocks.DOOR_EUCALYPTUS);
        dropSelf(ModBlocks.DOOR_MAHOGANY);
        dropSelf(ModBlocks.DOOR_MOSSBARK);
        dropSelf(ModBlocks.DOOR_ASPER);
        dropSelf(ModBlocks.DOOR_CYPRESS);
        dropSelf(ModBlocks.DOOR_BALSAM);
        dropSelf(ModBlocks.DOOR_WHITE);
        dropSelf(ModBlocks.DOOR_ROTTEN);
        dropSelf(ModBlocks.DOOR_MARSHWOOD);
        dropSelf(ModBlocks.DOOR_SCORCHED);

        // MARK: Fences
        dropSelf(ModBlocks.FENCE_BAOBAB);
        dropSelf(ModBlocks.FENCE_EUCALYPTUS);
        dropSelf(ModBlocks.FENCE_MAHOGANY);
        dropSelf(ModBlocks.FENCE_MOSSBARK);
        dropSelf(ModBlocks.FENCE_ASPER);
        dropSelf(ModBlocks.FENCE_CYPRESS);
        dropSelf(ModBlocks.FENCE_BALSAM);
        dropSelf(ModBlocks.FENCE_WHITE);
        dropSelf(ModBlocks.FENCE_BAMBOO);
        dropSelf(ModBlocks.FENCE_ROTTEN);
        dropSelf(ModBlocks.FENCE_MARSHWOOD);
        dropSelf(ModBlocks.FENCE_SCORCHED);
        dropSelf(ModBlocks.FENCE_VARNISHED);

        // MARK: Fence Gates
        dropSelf(ModBlocks.FENCE_GATE_BAOBAB);
        dropSelf(ModBlocks.FENCE_GATE_EUCALYPTUS);
        dropSelf(ModBlocks.FENCE_GATE_MAHOGANY);
        dropSelf(ModBlocks.FENCE_GATE_MOSSBARK);
        dropSelf(ModBlocks.FENCE_GATE_ASPER);
        dropSelf(ModBlocks.FENCE_GATE_CYPRESS);
        dropSelf(ModBlocks.FENCE_GATE_BALSAM);
        dropSelf(ModBlocks.FENCE_GATE_WHITE);
        dropSelf(ModBlocks.FENCE_GATE_BAMBOO);
        dropSelf(ModBlocks.FENCE_GATE_ROTTEN);
        dropSelf(ModBlocks.FENCE_GATE_MARSHWOOD);
        dropSelf(ModBlocks.FENCE_GATE_SCORCHED);
        dropSelf(ModBlocks.FENCE_GATE_VARNISHED);

        // MARK: Walls
        dropSelf(ModBlocks.WALL_UMBERSTONE);
        dropSelf(ModBlocks.WALL_UMBERCOBBLE);
        dropSelf(ModBlocks.WALL_UMBERCOBBLE_MOSSY);
        dropSelf(ModBlocks.WALL_UMBERCOBBLE_WEBBED);
        dropSelf(ModBlocks.WALL_UMBERSTONE_BRICKS);
        dropSelf(ModBlocks.WALL_UMBERTILE_SMOOTH);
        dropSelf(ModBlocks.WALL_UMBERTILE_SMOOTH_SMALL);
        dropSelf(ModBlocks.WALL_UMBERPAVER);
        dropSelf(ModBlocks.WALL_UMBERPAVER_MOSSY);
        dropSelf(ModBlocks.WALL_UMBERPAVER_WEBBED);
        dropSelf(ModBlocks.WALL_AMBER);
        dropSelf(ModBlocks.WALL_AMBER_BRICKS);

        // MARK: Plants
        dropCropBasedOffCondition(ModBlocks.CROP_TURNIP, ModItems.TURNIP, ModItems.TURNIP);
        dropCropBasedOffCondition(ModBlocks.CROP_CABBAGE, ModItems.CABBAGE, ModItems.CABBAGE_SEEDS);
        dropCropBasedOffCondition(ModBlocks.CROP_MANDRAKE, ModItems.MANDRAKE_ROOT, ModItems.MANDRAKE_ROOT);
        dropSelf(ModBlocks.JADE_BERRY_BUSH);
        dropSelf(ModBlocks.HEART_BERRY_BUSH);
        dropSelf(ModBlocks.SWAMP_BERRY_BUSH);
        dropSelf(ModBlocks.DARK_FRUIT_VINE);
        dropPricklyPearBasedOffCondition(ModBlocks.PRICKLY_PEAR);
        dropColossalBambooBasedOffCondition(ModBlocks.COLOSSAL_BAMBOO);
        dropSelf(ModBlocks.DARK_CAPPED_MUSHROOM);
        dropSelf(ModBlocks.DUTCH_CAP_MUSHROOM);
        dropSelf(ModBlocks.GRANDMAS_SHOES_MUSHROOM);
        dropSelf(ModBlocks.KAIZERS_FINGERS_MUSHROOM);
        dropSelf(ModBlocks.SARCASTIC_CZECH_MUSHROOM);
        dropSelf(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK);
        dropSelf(ModBlocks.DARK_CAPPED_MUSHROOM_STEM);
        dropSelf(ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK);
        dropSelf(ModBlocks.DUTCH_CAP_MUSHROOM_STEM);
        dropSelf(ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK);
        dropSelf(ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM);
        dropSelf(ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK);
        dropSelf(ModBlocks.KAIZERS_FINGERS_MUSHROOM_STEM);
        dropSelf(ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK);
        dropSelf(ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM);
        dropSelf(ModBlocks.DESERT_SHRUB);
        dropSelf(ModBlocks.MIRE_CORAL);
        dropSelf(ModBlocks.NETTLE);
        dropSelf(ModBlocks.NETTLE_FLOWERED);
        dropSelf(ModBlocks.SWAMP_PLANT);
        dropSelf(ModBlocks.FIRE_BLOOM);
        dropSelf(ModBlocks.FERN);
        dropSelf(ModBlocks.FIDDLE_HEAD);
        dropSelf(ModBlocks.THORNS);
        dropSelf(ModBlocks.MOSS);
        dropSelf(ModBlocks.MOULD);
        dropSelf(ModBlocks.MOSS_CULTIVATED);
        dropSelf(ModBlocks.MOULD_CULTIVATED);
        dropSelf(ModBlocks.ALGAE);
        dropSelf(ModBlocks.GLOWSHROOM_BLOCK);
        dropSelf(ModBlocks.GLOWSHROOM_STALK);
        dropSelf(ModBlocks.HANGING_WEB);
        dropSelf(ModBlocks.GIANT_LILY_PAD);

        // MARK: Flowers
        dropSelf(ModBlocks.PETAL_BLACK);
        dropSelf(ModBlocks.PETAL_RED);
        dropSelf(ModBlocks.PETAL_BROWN);
        dropSelf(ModBlocks.PETAL_BLUE);
        dropSelf(ModBlocks.PETAL_PURPLE);
        dropSelf(ModBlocks.PETAL_CYAN);
        dropSelf(ModBlocks.PETAL_LIGHT_GRAY);
        dropSelf(ModBlocks.PETAL_GRAY);
        dropSelf(ModBlocks.PETAL_PINK);
        dropSelf(ModBlocks.PETAL_YELLOW);
        dropSelf(ModBlocks.PETAL_LIGHT_BLUE);
        dropSelf(ModBlocks.PETAL_MAGENTA);
        dropSelf(ModBlocks.PETAL_ORANGE);
        dropSelf(ModBlocks.PETAL_WHITE);
        dropSelf(ModBlocks.PETAL_RAINBOW);
        dropSelf(ModBlocks.PETAL_RAINBOW_CHASE);

        dropSelf(ModBlocks.EXPLODING_STIGMA);
        dropSelf(ModBlocks.STEM);
        dropSelf(ModBlocks.STIGMA_BLACK);
        dropSelf(ModBlocks.STIGMA_RED);
        dropSelf(ModBlocks.STIGMA_BROWN);
        dropSelf(ModBlocks.STIGMA_BLUE);
        dropSelf(ModBlocks.STIGMA_PURPLE);
        dropSelf(ModBlocks.STIGMA_CYAN);
        dropSelf(ModBlocks.STIGMA_LIGHT_GRAY);
        dropSelf(ModBlocks.STIGMA_GRAY);
        dropSelf(ModBlocks.STIGMA_PINK);
        dropSelf(ModBlocks.STIGMA_YELLOW);
        dropSelf(ModBlocks.STIGMA_LIGHT_BLUE);
        dropSelf(ModBlocks.STIGMA_MAGENTA);
        dropSelf(ModBlocks.STIGMA_ORANGE);
        dropSelf(ModBlocks.STIGMA_WHITE);

        dropSelf(ModBlocks.FLOWER_BLACK);
        dropSelf(ModBlocks.FLOWER_RED);
        dropSelf(ModBlocks.FLOWER_BROWN);
        dropSelf(ModBlocks.FLOWER_BLUE);
        dropSelf(ModBlocks.FLOWER_PURPLE);
        dropSelf(ModBlocks.FLOWER_CYAN);
        dropSelf(ModBlocks.FLOWER_LIGHT_GRAY);
        dropSelf(ModBlocks.FLOWER_GRAY);
        dropSelf(ModBlocks.FLOWER_PINK);
        dropSelf(ModBlocks.FLOWER_YELLOW);
        dropSelf(ModBlocks.FLOWER_LIGHT_BLUE);
        dropSelf(ModBlocks.FLOWER_MAGENTA);
        dropSelf(ModBlocks.FLOWER_ORANGE);
        dropSelf(ModBlocks.FLOWER_WHITE);
        dropSelf(ModBlocks.FLOWER_RAINBOW);

        // MARK: Flowers Double Height
        dropSelf(ModBlocks.BULLRUSH);
        dropSelf(ModBlocks.WEEPING_BLUEBELL);
        dropSelf(ModBlocks.SUNDEW);
        dropSelf(ModBlocks.DROUGHTED_SHRUB);
        dropSelf(ModBlocks.TALL_BLOOM);
        dropSelf(ModBlocks.TANGLED_STALK);
        dropSelf(ModBlocks.HIGH_CAPPED_MUSHROOM);
        dropSelf(ModBlocks.TALL_FERN);

        dropSelf(ModBlocks.PORTAL);
        dropSelf(ModBlocks.JADE_BLOCK);
        dropSelf(ModBlocks.MUD);
        dropSelf(ModBlocks.QUICK_SAND);
        dropSelf(ModBlocks.GHOST_SAND);
        dropSelf(ModBlocks.SWAMP_VENT);
        dropSelf(ModBlocks.GNEISS_VENT);
        dropSelf(ModBlocks.RED_GEM_BLOCK);
        dropSelf(ModBlocks.RED_GEM_LAMP);
        dropSelf(ModBlocks.GNEISS);
        dropSelf(ModBlocks.GNEISS_CARVED);
        dropSelf(ModBlocks.GNEISS_RELIEF);
        dropSelf(ModBlocks.GNEISS_BRICKS);
        dropSelf(ModBlocks.GNEISS_SMOOTH);
        dropSelf(ModBlocks.GNEISS_TILES);
        dropSelf(ModBlocks.GNEISS_TILES_CRACKED);
        dropSelf(ModBlocks.TEMPLE_BRICK);
        dropSelf(ModBlocks.TEMPLE_PILLAR);
        dropSelf(ModBlocks.TEMPLE_TILE);
        dropSelf(ModBlocks.SILK);
        dropSelf(ModBlocks.REIN_EXO);
        dropSelf(ModBlocks.VELOCITY_BLOCK);
        dropSelf(ModBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED);
        dropSelf(ModBlocks.BLOCK_OF_BONES);
        dropSelf(ModBlocks.ANTLION_EGG);
        dropSelf(ModBlocks.TARANTULA_EGG);
        dropSelf(ModBlocks.HONEY_TREAT);
        dropSelf(ModBlocks.CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.WHITE_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.ORANGE_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.MAGENTA_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.YELLOW_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.LIME_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.PINK_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.GRAY_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.CYAN_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.PURPLE_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.BLUE_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.BROWN_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.GREEN_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.RED_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.BLACK_CANDLE_HONEY_TREAT);
        dropSelf(ModBlocks.WASP_NEST);
        dropSelf(ModBlocks.STAIRS_WASP_NEST);
        dropSelf(ModBlocks.INSECT_REPELLENT);

        // MARK: Spawners
        dropSelf(ModBlocks.ANTLION_SPAWNER);
        dropSelf(ModBlocks.DRAGON_FLY_SPAWNER);
        dropSelf(ModBlocks.JUMPING_SPIDER_SPAWNER);
        dropSelf(ModBlocks.SPIDER_SPAWNER);
        dropSelf(ModBlocks.TARANTULA_SPAWNER);
        dropSelf(ModBlocks.WASP_SPAWNER);
        dropSelf(ModBlocks.ZOMBIE_ANT_SPAWNER);
        dropSelf(ModBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER);
        dropSelf(ModBlocks.MAGMA_CRAWLER_SPAWNER);
        dropSelf(ModBlocks.DUNG_SPAWNER_FLY);
        dropSelf(ModBlocks.DUNG_SPAWNER_BOT_FLY);
        dropSelf(ModBlocks.LOCUST_SPAWNER);

        // MARK: Utility Blocks
        dropSelf(ModBlocks.PETRIFIED_CRAFTING_TABLE);
        dropSelf(ModBlocks.BAMBOO_CRATE);
        dropSelf(ModBlocks.BAMBOO_BRIDGE);
        dropSelf(ModBlocks.BAMBOO_LADDER);
        dropSelf(ModBlocks.BAMBOO_NERD_POLE);
        dropSelf(ModBlocks.BAMBOO_EXTENDER);
        dropSingleBambooTorchCondition(ModBlocks.BAMBOO_TORCH);
        dropSelf(ModBlocks.BAMBOO_PIPE);
        dropSelf(ModBlocks.BAMBOO_PIPE_EXTRACT);
        dropSelf(ModBlocks.SILO_ROOF);
        dropSelf(ModBlocks.SILO_TANK);
        dropSelf(ModBlocks.SILO_SUPPORTS);
        dropSelf(ModBlocks.HONEY_COMB);
        dropSelf(ModBlocks.COMPOSTER);
        dropSelf(ModBlocks.BLENDER);
        dropSelf(ModBlocks.UMBER_FURNACE);
        dropSelf(ModBlocks.UMBERSTONE_BUTTON);
        dropSelf(ModBlocks.LIQUIFIER);
        dropOther(ModBlocks.GLOW_GEM_ACTIVE, ModBlocks.GLOW_GEM_INACTIVE);
        dropSelf(ModBlocks.GLOW_GEM_INACTIVE);
        dropSelf(ModBlocks.MUCUS_BOMB);
        dropSelf(ModBlocks.UMBER_GOLEM_STATUE);

        // MARK: Chests
        dropSelf(ModBlocks.CHEST_ASPER);
        dropSelf(ModBlocks.CHEST_BALSAM);
        dropSelf(ModBlocks.CHEST_BAOBAB);
        dropSelf(ModBlocks.CHEST_BAMBOO);
        dropSelf(ModBlocks.CHEST_CYPRESS);
        dropSelf(ModBlocks.CHEST_EUCALYPTUS);
        dropSelf(ModBlocks.CHEST_MAHOGANY);
        dropSelf(ModBlocks.CHEST_MARSHWOOD);
        dropSelf(ModBlocks.CHEST_MOSSBARK);
        dropSelf(ModBlocks.CHEST_PETRIFIED);
        dropSelf(ModBlocks.CHEST_ROTTEN);
        dropSelf(ModBlocks.CHEST_SCORCHED);
        dropSelf(ModBlocks.CHEST_VARNISHED);
        dropSelf(ModBlocks.CHEST_WHITE);

        dropSelf(ModBlocks.ALTAR_BASE);
        dropSelf(ModBlocks.ALTAR_LIGHTNING);
        dropSelf(ModBlocks.ALTAR_HEALING);
        dropSelf(ModBlocks.ALTAR_EXPERIENCE);
        dropSelf(ModBlocks.ALTAR_REPAIR);
        dropSelf(ModBlocks.OFFERING_ALTAR);
        dropSelf(ModBlocks.GAEAN_KEYSTONE);

        dropSelf(ModBlocks.CAPSTONE);
        dropSelf(ModBlocks.CAPSTONE_MUD);
        dropSelf(ModBlocks.CAPSTONE_IRON);
        dropSelf(ModBlocks.CAPSTONE_GOLD);
        dropSelf(ModBlocks.CAPSTONE_JADE);
        dropSelf(ModBlocks.TEMPLE_BRICK_UNBREAKING);
        dropSelf(ModBlocks.TEMPLE_BRICK_UNBREAKING_JADE);
        dropSelf(ModBlocks.TEMPLE_BRICK_UNBREAKING_EXO);
        dropSelf(ModBlocks.TEMPLE_BRICK_UNBREAKING_CREAM);
        dropSelf(ModBlocks.TEMPLE_BRICK_UNBREAKING_EYE);
        dropSelf(ModBlocks.TEMPLE_BRICK_UNBREAKING_STRING);
        dropSelf(ModBlocks.TEMPLE_TELEPORTER);
        dropSelf(ModBlocks.FORCE_FIELD);
        dropSelf(ModBlocks.FORCE_LOCK);
        dropSelf(ModBlocks.ANT_HILL_BLOCK);
        
        //Webs
        add(ModBlocks.WITHER_WEB.get(), createSilkTouchOrShearsDispatchTable(ModBlocks.WITHER_WEB.get(), applyExplosionCondition(ModBlocks.WITHER_WEB, LootItem.lootTableItem(Items.STRING))));
        add(ModBlocks.LAVA_WEB.get(), createSilkTouchOrShearsDispatchTable(ModBlocks.LAVA_WEB.get(), applyExplosionCondition(ModBlocks.WITHER_WEB, LootItem.lootTableItem(Items.STRING))));
    
        // Fluid Tank Blocks
        CopyComponentsFunction.Builder copyFluid = CopyComponentsFunction.copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
                .include(ModDataComponents.FLUID.get());
        
      // Portable Inventory Storage Blocks
        CopyComponentsFunction.Builder copyItems = CopyComponentsFunction.copyComponentsFromBlockEntity(LootContextParams.BLOCK_ENTITY)
                .include(DataComponents.CONTAINER);

        dropComponents(ModBlocks.FLUID_JAR, $ -> $.apply(copyFluid));
        dropComponents(ModBlocks.LIQUIFIER, $ -> $.apply(copyFluid));
        dropComponents(ModBlocks.BAMBOO_CRATE, $ -> $.apply(copyItems));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(e -> (Block) e.value()).toList();
    }
}
