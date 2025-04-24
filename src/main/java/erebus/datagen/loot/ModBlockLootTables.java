package erebus.datagen.loot;

import erebus.datagen.providers.ModBlockLootTableProvider;
import erebus.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import org.jetbrains.annotations.NotNull;

import static erebus.registries.ModBlocks.*;

public class ModBlockLootTables extends ModBlockLootTableProvider {

    public ModBlockLootTables(HolderLookup.Provider provider) {
        super(provider);
    }

    @Override
    protected void generate() {
        // MARK: Umberstone
        dropWhenSilkTouch(UMBERSTONE.get());
        dropOther(UMBERSTONE, UMBERCOBBLE);
        dropSelf(UMBERSTONE_BRICKS);
        dropSelf(UMBERCOBBLE);
        dropSelf(UMBERCOBBLE_MOSSY);
        dropSelf(UMBERCOBBLE_WEBBED);
        dropSelf(UMBERTILE_SMOOTH);
        dropSelf(UMBERTILE_SMOOTH_SMALL);
        dropSelf(UMBERGRAVEL);
        dropSelf(UMBERPAVER);
        dropSelf(UMBERPAVER_MOSSY);
        dropSelf(UMBERPAVER_WEBBED);
        dropSelf(UMBERSTONE_PILLAR);
        dropSelf(VOLCANIC_ROCK);
        dropSelf(DUST);
        dropSelf(DUST_LAYER);
        dropSelf(PETRIFIED_WOOD_ROCK);
        dropSelf(PETRIFIED_WOOD_ROCK_2);
        dropSelf(PETRIFIED_WOOD_ROCK_3);
        dropSelf(PETRIFIED_WOOD_ROCK_4);
        dropSelf(PETRIFIED_WOOD_ROCK_5);
        dropSelf(PETRIFIED_WOOD_ROCK_6);
        dropSelf(PETRIFIED_BARK_RED);
        dropSelf(PETRIFIED_BARK_BROWN);
        dropSelf(PETRIFIED_LOG_INNER);
        dropSelf(DUNG);

        // MARK: Amber
        dropSelf(AMBER);
        dropSelf(AMBER_GLASS);
        dropSelf(AMBER_BRICKS);
        dropSelf(PRESERVED_AMBER);
        dropSelf(PRESERVED_AMBER_GLASS);
        dropSelf(GLOWING_JAR);
        dropSelf(FLUID_JAR);
        dropSelf(AMBER_DOOR);

        dropSelf(MIR_BRICKS);
        dropSelf(MUD_BRICKS);

        // MARK: Ores
        ore(ORE_IRON, Items.RAW_IRON);
        ore(ORE_GOLD, Items.RAW_GOLD);
        ore(ORE_COAL, Items.COAL);
        ore(ORE_DIAMOND, Items.DIAMOND);
        ore(ORE_EMERALD, Items.EMERALD);
        ore(ORE_LAPIS, Items.LAPIS_LAZULI);
        ore(ORE_QUARTZ, Items.QUARTZ);
        ore(ORE_PETRIFIED_QUARTZ, Items.QUARTZ);
        ore(ORE_COPPER, Items.RAW_COPPER);
        ore(ORE_SILVER, ModItems.INGOT_SILVER);
        ore(ORE_TIN, ModItems.INGOT_TIN);
        ore(ORE_LEAD, ModItems.INGOT_LEAD);
        ore(ORE_ALUMINUM, ModItems.INGOT_ALUMINUM);
        ore(ORE_JADE, ModItems.JADE);
        ore(ORE_ENCRUSTED_DIAMOND, Items.DIAMOND);
        ore(ORE_FOSSIL, Items.BONE);
        ore(ORE_GNEISS, ModItems.GNEISS_ROCK);
        ore(ORE_PETRIFIED_WOOD, ModItems.PETRIFIED_WOOD);
        ore(ORE_TEMPLE, ModItems.TEMPLE_ROCK);

        // MARK: Logs
        dropSelf(LOG_BAOBAB);
        dropSelf(LOG_EUCALYPTUS);
        dropSelf(LOG_MAHOGANY);
        dropSelf(LOG_MOSSBARK);
        dropSelf(LOG_ASPER);
        dropSelf(LOG_CYPRESS);
        dropSelf(LOG_BALSAM);
        dropSelf(LOG_BALSAM_RESINLESS);
        dropSelf(LOG_ROTTEN);
        dropSelf(LOG_MARSHWOOD);
        dropSelf(LOG_SCORCHED);
        dropSelf(LOG_BAMBOO);
        dropSelf(LOG_HOLLOW);

        // MARK: Saplings
        dropSelf(SAPLING_BAOBAB);
        dropSelf(SAPLING_EUCALYPTUS);
        dropSelf(SAPLING_MAHOGANY);
        dropSelf(SAPLING_MOSSBARK);
        dropSelf(SAPLING_ASPER);
        dropSelf(SAPLING_CYPRESS);
        dropSelf(SAPLING_BALSAM);
        dropSelf(SAPLING_MARSHWOOD);
        dropSelf(SAPLING_BAMBOO);

        // MARK: Leaves
        dropSelf(LEAVES_BAOBAB);
        dropSelf(LEAVES_EUCALYPTUS);
        dropSelf(LEAVES_MAHOGANY);
        dropSelf(LEAVES_MOSSBARK);
        dropSelf(LEAVES_ASPER);
        dropSelf(LEAVES_CYPRESS);
        dropSelf(LEAVES_BALSAM);
        dropSelf(LEAVES_MARSHWOOD);

        // MARK: Planks
        dropSelf(PLANKS_BAOBAB);
        dropSelf(PLANKS_EUCALYPTUS);
        dropSelf(PLANKS_MAHOGANY);
        dropSelf(PLANKS_MOSSBARK);
        dropSelf(PLANKS_ASPER);
        dropSelf(PLANKS_CYPRESS);
        dropSelf(PLANKS_BALSAM);
        dropSelf(PLANKS_WHITE);
        dropSelf(PLANKS_BAMBOO);
        dropSelf(PLANKS_ROTTEN);
        dropSelf(PLANKS_MARSHWOOD);
        dropSelf(PLANKS_SCORCHED);
        dropSelf(PLANKS_VARNISHED);
        dropSelf(PLANKS_PETRIFIED);

        // MARK: Slabs Wood
        dropSelf(SLAB_PLANKS_BAOBAB);
        dropSelf(SLAB_PLANKS_EUCALYPTUS);
        dropSelf(SLAB_PLANKS_MAHOGANY);
        dropSelf(SLAB_PLANKS_MOSSBARK);
        dropSelf(SLAB_PLANKS_ASPER);
        dropSelf(SLAB_PLANKS_CYPRESS);
        dropSelf(SLAB_PLANKS_BALSAM);
        dropSelf(SLAB_PLANKS_WHITE);
        dropSelf(SLAB_PLANKS_BAMBOO);
        dropSelf(SLAB_PLANKS_ROTTEN);
        dropSelf(SLAB_PLANKS_MARSHWOOD);
        dropSelf(SLAB_PLANKS_SCORCHED);
        dropSelf(SLAB_PLANKS_VARNISHED);
        dropSelf(SLAB_PLANKS_PETRIFIED);

        // MARK: Slabs Stone
        dropSelf(SLAB_UMBERSTONE);
        dropSelf(SLAB_UMBERCOBBLE);
        dropSelf(SLAB_UMBERCOBBLE_MOSSY);
        dropSelf(SLAB_UMBERCOBBLE_WEBBED);
        dropSelf(SLAB_UMBERSTONE_BRICKS);
        dropSelf(SLAB_UMBERTILE_SMOOTH);
        dropSelf(SLAB_UMBERTILE_SMOOTH_SMALL);
        dropSelf(SLAB_UMBERPAVER);
        dropSelf(SLAB_AMBER);
        dropSelf(SLAB_AMBER_BRICKS);
        dropSelf(SLAB_UMBERPAVER_MOSSY);
        dropSelf(SLAB_UMBERPAVER_WEBBED);
        dropSelf(SLAB_MIR_BRICKS);
        dropSelf(SLAB_MUD_BRICKS);

        // MARK: Stairs Wood
        dropSelf(STAIRS_BAOBAB);
        dropSelf(STAIRS_EUCALYPTUS);
        dropSelf(STAIRS_MAHOGANY);
        dropSelf(STAIRS_MOSSBARK);
        dropSelf(STAIRS_ASPER);
        dropSelf(STAIRS_CYPRESS);
        dropSelf(STAIRS_BALSAM);
        dropSelf(STAIRS_WHITE);
        dropSelf(STAIRS_BAMBOO);
        dropSelf(STAIRS_ROTTEN);
        dropSelf(STAIRS_MARSHWOOD);
        dropSelf(STAIRS_SCORCHED);
        dropSelf(STAIRS_VARNISHED);
        dropSelf(STAIRS_PETRIFIED);

        // MARK: Stairs Stone
        dropSelf(STAIRS_UMBERSTONE);
        dropSelf(STAIRS_UMBERCOBBLE);
        dropSelf(STAIRS_UMBERCOBBLE_MOSSY);
        dropSelf(STAIRS_UMBERCOBBLE_WEBBED);
        dropSelf(STAIRS_UMBERSTONE_BRICKS);
        dropSelf(STAIRS_UMBERTILE_SMOOTH);
        dropSelf(STAIRS_UMBERTILE_SMOOTH_SMALL);
        dropSelf(STAIRS_UMBERPAVER);
        dropSelf(STAIRS_UMBERPAVER_MOSSY);
        dropSelf(STAIRS_UMBERPAVER_WEBBED);
        dropSelf(STAIRS_AMBER);
        dropSelf(STAIRS_AMBER_BRICKS);
        dropSelf(STAIRS_MUD_BRICKS);
        dropSelf(STAIRS_MIR_BRICKS);
        dropSelf(STAIRS_WASP_NEST);

        // MARK: Doors
        dropSelf(DOOR_BAOBAB);
        dropSelf(DOOR_EUCALYPTUS);
        dropSelf(DOOR_MAHOGANY);
        dropSelf(DOOR_MOSSBARK);
        dropSelf(DOOR_ASPER);
        dropSelf(DOOR_CYPRESS);
        dropSelf(DOOR_BALSAM);
        dropSelf(DOOR_WHITE);
        dropSelf(DOOR_ROTTEN);
        dropSelf(DOOR_MARSHWOOD);
        dropSelf(DOOR_SCORCHED);

        // MARK: Fences
        dropSelf(FENCE_BAOBAB);
        dropSelf(FENCE_EUCALYPTUS);
        dropSelf(FENCE_MAHOGANY);
        dropSelf(FENCE_MOSSBARK);
        dropSelf(FENCE_ASPER);
        dropSelf(FENCE_CYPRESS);
        dropSelf(FENCE_BALSAM);
        dropSelf(FENCE_WHITE);
        dropSelf(FENCE_BAMBOO);
        dropSelf(FENCE_ROTTEN);
        dropSelf(FENCE_MARSHWOOD);
        dropSelf(FENCE_SCORCHED);
        dropSelf(FENCE_VARNISHED);

        // MARK: Fence Gates
        dropSelf(FENCE_GATE_BAOBAB);
        dropSelf(FENCE_GATE_EUCALYPTUS);
        dropSelf(FENCE_GATE_MAHOGANY);
        dropSelf(FENCE_GATE_MOSSBARK);
        dropSelf(FENCE_GATE_ASPER);
        dropSelf(FENCE_GATE_CYPRESS);
        dropSelf(FENCE_GATE_BALSAM);
        dropSelf(FENCE_GATE_WHITE);
        dropSelf(FENCE_GATE_BAMBOO);
        dropSelf(FENCE_GATE_ROTTEN);
        dropSelf(FENCE_GATE_MARSHWOOD);
        dropSelf(FENCE_GATE_SCORCHED);
        dropSelf(FENCE_GATE_VARNISHED);

        // MARK: Walls
        dropSelf(WALL_UMBERSTONE);
        dropSelf(WALL_UMBERCOBBLE);
        dropSelf(WALL_UMBERCOBBLE_MOSSY);
        dropSelf(WALL_UMBERCOBBLE_WEBBED);
        dropSelf(WALL_UMBERSTONE_BRICKS);
        dropSelf(WALL_UMBERTILE_SMOOTH);
        dropSelf(WALL_UMBERTILE_SMOOTH_SMALL);
        dropSelf(WALL_UMBERPAVER);
        dropSelf(WALL_UMBERPAVER_MOSSY);
        dropSelf(WALL_UMBERPAVER_WEBBED);
        dropSelf(WALL_AMBER);
        dropSelf(WALL_AMBER_BRICKS);

        // MARK: Plants
        dropCropBasedOffCondition(CROP_TURNIP, ModItems.TURNIP, ModItems.TURNIP);
        dropCropBasedOffCondition(CROP_CABBAGE, ModItems.CABBAGE, ModItems.CABBAGE_SEEDS);
        dropCropBasedOffCondition(CROP_MANDRAKE, ModItems.MANDRAKE_ROOT, ModItems.MANDRAKE_ROOT);
        dropSelf(JADE_BERRY_BUSH);
        dropSelf(HEART_BERRY_BUSH);
        dropSelf(SWAMP_BERRY_BUSH);
        dropSelf(DARK_FRUIT_VINE);
        dropSelf(PRICKLY_PEAR);
        dropSelf(DARK_CAPPED_MUSHROOM);
        dropSelf(DUTCH_CAP_MUSHROOM);
        dropSelf(GRANDMAS_SHOES_MUSHROOM);
        dropSelf(KAIZERS_FINGERS_MUSHROOM);
        dropSelf(SARCASTIC_CZECH_MUSHROOM);
        dropSelf(DARK_CAPPED_MUSHROOM_BLOCK);
        dropSelf(DUTCH_CAP_MUSHROOM_BLOCK);
        dropSelf(GRANDMAS_SHOES_MUSHROOM_BLOCK);
        dropSelf(KAIZERS_FINGERS_MUSHROOM_BLOCK);
        dropSelf(SARCASTIC_CZECH_MUSHROOM_BLOCK);
        dropSelf(DESERT_SHRUB);
        dropSelf(MIRE_CORAL);
        dropSelf(NETTLE);
        dropSelf(NETTLE_FLOWERED);
        dropSelf(SWAMP_PLANT);
        dropSelf(FIRE_BLOOM);
        dropSelf(FERN);
        dropSelf(FIDDLE_HEAD);
        dropSelf(THORNS);
        dropSelf(MOSS_DOWN);
        dropSelf(MOULD_DOWN);
        dropSelf(CULTIVATED_MOSS_DOWN);
        dropSelf(CULTIVATED_MOULD_DOWN);
        dropSelf(ALGAE);
        dropSelf(GLOWSHROOM_BLOCK);
        dropSelf(GLOWSHROOM_STALK);
        dropSelf(HANGING_WEB);

        // MARK: Flowers
        dropSelf(PETAL_BLACK);
        dropSelf(PETAL_RED);
        dropSelf(PETAL_BROWN);
        dropSelf(PETAL_BLUE);
        dropSelf(PETAL_PURPLE);
        dropSelf(PETAL_CYAN);
        dropSelf(PETAL_LIGHT_GRAY);
        dropSelf(PETAL_GRAY);
        dropSelf(PETAL_PINK);
        dropSelf(PETAL_YELLOW);
        dropSelf(PETAL_LIGHT_BLUE);
        dropSelf(PETAL_MAGENTA);
        dropSelf(PETAL_ORANGE);
        dropSelf(PETAL_WHITE);

        dropSelf(EXPLODING_STIGMA);
        dropSelf(STEM);
        dropSelf(STIGMA_BLACK);
        dropSelf(STIGMA_RED);
        dropSelf(STIGMA_BROWN);
        dropSelf(STIGMA_BLUE);
        dropSelf(STIGMA_PURPLE);
        dropSelf(STIGMA_CYAN);
        dropSelf(STIGMA_LIGHT_GRAY);
        dropSelf(STIGMA_GRAY);
        dropSelf(STIGMA_PINK);
        dropSelf(STIGMA_YELLOW);
        dropSelf(STIGMA_LIGHT_BLUE);
        dropSelf(STIGMA_MAGENTA);
        dropSelf(STIGMA_ORANGE);
        dropSelf(STIGMA_WHITE);

        dropSelf(FLOWER_BLACK);
        dropSelf(FLOWER_RED);
        dropSelf(FLOWER_BROWN);
        dropSelf(FLOWER_BLUE);
        dropSelf(FLOWER_PURPLE);
        dropSelf(FLOWER_CYAN);
        dropSelf(FLOWER_LIGHT_GRAY);
        dropSelf(FLOWER_GRAY);
        dropSelf(FLOWER_PINK);
        dropSelf(FLOWER_YELLOW);
        dropSelf(FLOWER_LIGHT_BLUE);
        dropSelf(FLOWER_MAGENTA);
        dropSelf(FLOWER_ORANGE);
        dropSelf(FLOWER_WHITE);
        dropSelf(FLOWER_RAINBOW);

        // MARK: Flowers Double Height
        dropSelf(BULLRUSH);
        dropSelf(WEEPING_BLUEBELL);
        dropSelf(SUNDEW);
        dropSelf(DROUGHTED_SHRUB);
        dropSelf(TALL_BLOOM);
        dropSelf(TANGLED_STALK);
        dropSelf(HIGH_CAPPED_MUSHROOM);
        dropSelf(TALL_FERN);

        dropSelf(PORTAL);
        dropSelf(JADE_BLOCK);
        dropSelf(MUD);
        dropSelf(QUICK_SAND);
        dropSelf(GHOST_SAND);
        dropSelf(SWAMP_VENT);
        dropSelf(GNEISS_VENT);
        dropSelf(RED_GEM_BLOCK);
        dropSelf(RED_GEM_LAMP);
        dropSelf(GNEISS);
        dropSelf(GNEISS_CARVED);
        dropSelf(GNEISS_RELIEF);
        dropSelf(GNEISS_BRICKS);
        dropSelf(GNEISS_SMOOTH);
        dropSelf(GNEISS_TILES);
        dropSelf(GNEISS_TILES_CRACKED);
        dropSelf(TEMPLE_BRICK);
        dropSelf(TEMPLE_PILLAR);
        dropSelf(TEMPLE_TILE);
        dropSelf(SILK);
        dropSelf(MIR_BRICKS);
        dropSelf(MUD_BRICKS);
        dropSelf(REIN_EXO);
        dropSelf(VELOCITY);
        dropSelf(LIGHTNING_SPEED);
        dropSelf(BLOCK_OF_BONES);
        dropSelf(ANTLION_EGG);
        dropSelf(TARANTULA_EGG);
        dropSelf(HONEY_TREAT);
        dropSelf(CANDLE_HONEY_TREAT);
        dropSelf(WHITE_CANDLE_HONEY_TREAT);
        dropSelf(ORANGE_CANDLE_HONEY_TREAT);
        dropSelf(MAGENTA_CANDLE_HONEY_TREAT);
        dropSelf(LIGHT_BLUE_CANDLE_HONEY_TREAT);
        dropSelf(YELLOW_CANDLE_HONEY_TREAT);
        dropSelf(LIME_CANDLE_HONEY_TREAT);
        dropSelf(PINK_CANDLE_HONEY_TREAT);
        dropSelf(GRAY_CANDLE_HONEY_TREAT);
        dropSelf(LIGHT_GRAY_CANDLE_HONEY_TREAT);
        dropSelf(CYAN_CANDLE_HONEY_TREAT);
        dropSelf(PURPLE_CANDLE_HONEY_TREAT);
        dropSelf(BLUE_CANDLE_HONEY_TREAT);
        dropSelf(BROWN_CANDLE_HONEY_TREAT);
        dropSelf(GREEN_CANDLE_HONEY_TREAT);
        dropSelf(RED_CANDLE_HONEY_TREAT);
        dropSelf(BLACK_CANDLE_HONEY_TREAT);
        dropSelf(WASP_NEST);
        dropSelf(STAIRS_WASP_NEST);
        dropSelf(INSECT_REPELLENT);

        // MARK: Spawners
        dropSelf(ANTLION_SPAWNER);
        dropSelf(DRAGON_FLY_SPAWNER);
        dropSelf(JUMPING_SPIDER_SPAWNER);
        dropSelf(SPIDER_SPAWNER);
        dropSelf(TARANTULA_SPAWNER);
        dropSelf(WASP_SPAWNER);
        dropSelf(ZOMBIE_ANT_SPAWNER);
        dropSelf(ZOMBIE_ANT_SOLDIER_SPAWNER);
        dropSelf(MAGMA_CRAWLER_SPAWNER);
        dropSelf(DUNG_SPAWNER_FLY);
        dropSelf(DUNG_SPAWNER_BOT_FLY);
        dropSelf(LOCUST_SPAWNER);

        // MARK: Utility Blocks
        dropSelf(PETRIFIED_CRAFTING_TABLE);
        dropSelf(PETRIFIED_WOOD_CHEST);
        dropSelf(BAMBOO_CRATE);
        dropSelf(BAMBOO_BRIDGE);
        dropSelf(BAMBOO_LADDER);
        dropSelf(BAMBOO_NERD_POLE);
        dropSelf(BAMBOO_EXTENDER);
        dropSelf(BAMBOO_TORCH);
        dropSelf(BAMBOO_PIPE);
        dropSelf(BAMBOO_PIPE_EXTRACT);
        dropSelf(BAMBOO_PIPE_EXTRACT_ACTIVE);
        dropSelf(SILO_ROOF);
        dropSelf(SILO_TANK);
        dropSelf(SILO_SUPPORTS);
        dropSelf(HONEY_COMB);
        dropSelf(COMPOSTER);
        dropSelf(BLENDER);
        dropSelf(UMBER_FURNACE);
        dropSelf(UMBERSTONE_BUTTON);
        dropSelf(LIQUIFIER);
        dropSelf(GLOW_GEM);
        dropSelf(MUCUS_BOMB);
        dropSelf(UMBER_GOLEM_STATUE);

        dropSelf(ALTAR_BASE);
        dropSelf(ALTAR_LIGHTNING);
        dropSelf(ALTAR_HEALING);
        dropSelf(ALTAR_XP);
        dropSelf(ALTAR_REPAIR);
        dropSelf(OFFERING_ALTAR);

        dropSelf(CAPSTONE);
        dropSelf(CAPSTONE_MUD);
        dropSelf(CAPSTONE_IRON);
        dropSelf(CAPSTONE_GOLD);
        dropSelf(CAPSTONE_JADE);
        dropSelf(TEMPLE_BRICK_UNBREAKING);
        dropSelf(TEMPLE_BRICK_UNBREAKING_JADE);
        dropSelf(TEMPLE_BRICK_UNBREAKING_EXO);
        dropSelf(TEMPLE_BRICK_UNBREAKING_CREAM);
        dropSelf(TEMPLE_BRICK_UNBREAKING_EYE);
        dropSelf(TEMPLE_BRICK_UNBREAKING_STRING);
        dropSelf(TEMPLE_TELEPORTER);
        dropSelf(FORCE_FIELD);
        dropSelf(FORCE_LOCK);
        dropSelf(ANT_HILL_BLOCK);
        
        //Webs
        add(WITHER_WEB.get(), createSilkTouchOrShearsDispatchTable(WITHER_WEB.get(), applyExplosionCondition(WITHER_WEB, LootItem.lootTableItem(Items.STRING))));
        add(LAVA_WEB.get(), createSilkTouchOrShearsDispatchTable(LAVA_WEB.get(), applyExplosionCondition(WITHER_WEB, LootItem.lootTableItem(Items.STRING))));
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return BLOCKS.getEntries().stream().map(e -> (Block) e.value()).toList();
    }
}
