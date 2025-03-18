package erebus.datagen;

import static erebus.registries.ModBlocks.*;

import erebus.datagen.providers.ModItemModelProvider;
import erebus.registries.ModEntities;
import erebus.registries.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModItemModels extends ModItemModelProvider {

    public ModItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        registerItemModels();
        registerBlockItemModels();
    }

    private void registerItemModels() {

    	// Mob Spawn Eggs
		for (DeferredHolder<Item, ?> item : ModEntities.SPAWN_EGGS.getEntries()) {
			if (item.get() instanceof SpawnEggItem) {
				this.getBuilder(item.getId().getPath()).parent(this.getExistingFile(ResourceLocation.withDefaultNamespace("item/template_spawn_egg")));
			}
		}

        // MARK: Materials
        normalItem(ModItems.PLATE_EXO);
        normalItem(ModItems.JADE);
        normalItem(ModItems.SHARD_BONE);
        normalItem(ModItems.BAMBOO);
        normalItem(ModItems.COMPOUND_EYES);
        normalItem(ModItems.COMPOUND_LENS);
        normalItem(ModItems.FLY_WING);
        normalItem(ModItems.PETRIFIED_WOOD);
        normalItem(ModItems.BIO_VELOCITY);
        normalItem(ModItems.ELASTIC_FIBER);
        normalItem(ModItems.WASP_STING);
        //normalItem(ModItems.BAMBOO_SHOOT); TODO: Figure out what this is
        normalItem(ModItems.RED_GEM);
        normalItem(ModItems.BIO_LUMINESCENCE);
        normalItem(ModItems.SUPERNATURAL_VELOCITY);
        normalItem(ModItems.ALTAR_FRAGMENT);
        normalItem(ModItems.REINFORCED_PLATE_EXO);
        normalItem(ModItems.GLIDER_WING);
        normalItem(ModItems.SCORPION_PINCER);
        normalItem(ModItems.CAMO_POWDER);
        normalItem(ModItems.NECTAR);
        normalItem(ModItems.HONEY_DRIP);
        normalItem(ModItems.POISON_GLAND);
        normalItem(ModItems.MUD_BRICK);
        normalItem(ModItems.WHETSTONE_POWDER);
        normalItem(ModItems.DRAGONFLY_WING);
        normalItem(ModItems.BLUEBELL_PETAL);
        normalItem(ModItems.PAPYRUS);
        normalItem(ModItems.ENHANCED_GLIDER_WING);
        normalItem(ModItems.REPELLENT);
        normalItem(ModItems.MUCUS_CHARGE);
        normalItem(ModItems.NETTLE_LEAVES);
        normalItem(ModItems.NETTLE_FLOWERS);
        normalItem(ModItems.DARK_FRUIT_SEEDS);
        normalItem(ModItems.MOSS_BALL);
        normalItem(ModItems.GLOWSHROOM);
        normalItem(ModItems.PLATE_EXO_RHINO);
        normalItem(ModItems.RHINO_BEETLE_HORN);
        normalItem(ModItems.ANT_PHEROMONES);
        normalItem(ModItems.GAEAN_GEM);
        normalItem(ModItems.CRIMSON_HEART);
        normalItem(ModItems.RESIN);
        normalItem(ModItems.AMBER_STAR);
        normalItem(ModItems.INGOT_ALUMINUM);
        normalItem(ModItems.INGOT_LEAD);
        normalItem(ModItems.INGOT_SILVER);
        normalItem(ModItems.INGOT_TIN);
        normalItem(ModItems.GNEISS_ROCK);
        normalItem(ModItems.HIDE_SHROOM);
        normalItem(ModItems.BEETLE_RIDING_KIT);
        normalItem(ModItems.BEETLE_TAMING_AMULET);
        normalItem(ModItems.UMBERGOLEM_CORE);
        normalItem(ModItems.UMBERGOLEM_HEAD);
        normalItem(ModItems.UMBERGOLEM_CLAW);
        normalItem(ModItems.UMBERGOLEM_LEGS);
        normalItem(ModItems.JADE_BERRIES);
        normalItem(ModItems.BOGMAW_ROOT);
        normalItem(ModItems.HYDROFUGE);
        normalItem(ModItems.WATER_REPELLENT);
        normalItem(ModItems.SMOOTHIE_GLASS);
        normalItem(ModItems.MAGMA_CRAWLER_EYE);
        normalItem(ModItems.STEW_POT);
        normalItem(ModItems.TITAN_STEW);
        normalItem(ModItems.FORCE_KEY);
        normalItem(ModItems.SOUL_CRYSTAL);
        normalItem(ModItems.PLATE_ZOMBIE_MANDIBLES);
        normalItem(ModItems.STAG_BEETLE_MANDIBLES);
        normalItem(ModItems.TERPSISHROOM);
        normalItem(ModItems.BAMBOO_PIPE_WRENCH);
        normalItem(ModItems.TEMPLE_ROCK);

        // MARK: Food
        normalItem(ModItems.BEETLE_LARVA_RAW);
        normalItem(ModItems.BEETLE_LARVA_COOKED);
        normalItem(ModItems.GRASSHOPPER_LEG_RAW);
        normalItem(ModItems.GRASSHOPPER_LEG_COOKED);
        normalItem(ModItems.TARANTULA_LEG_RAW);
        normalItem(ModItems.TARANTULA_LEG_COOKED);
        normalItem(ModItems.BAMBOO_SOUP);
        normalItem(ModItems.MELONADE);
        normalItem(ModItems.MELONADE_SPARKLY);
        normalItem(ModItems.LARVAE_ON_STICK);
        normalItem(ModItems.HONEY_SANDWICH);
        normalItem(ModItems.DARK_FRUIT);
        normalItem(ModItems.TITAN_CHOP_RAW);
        normalItem(ModItems.TITAN_CHOP_COOKED);
        normalItem(ModItems.SWAMP_BERRIES);
        normalItem(ModItems.CABBAGE);
        normalItem(ModItems.TITAN_STEW_COOKED);
        normalItem(ModItems.PRICKLY_PEAR_RAW);
        normalItem(ModItems.PRICKLY_PEAR_COOKED);
        normalItem(ModItems.DARK_FRUIT_PIE);

        // MARK: Smoothies
        normalItem(ModItems.GREEN_TEA_GRASSHOPPER);
        normalItem(ModItems.MONEY_HONEY);
        normalItem(ModItems.NOTHING_IN_THE_MIDDLE);
        normalItem(ModItems.GREEN_GIANT);
        normalItem(ModItems.SEEDY_GOODNESS);
        normalItem(ModItems.GIVIN_ME_THE_BLUES);
        normalItem(ModItems.HOT_HOT_BABY);
        normalItem(ModItems.DONT_MEDDLE_WITH_THE_NETTLE);
        normalItem(ModItems.LIQUID_GOLD);
        normalItem(ModItems.BRYUFS_BREW);

        // MARK: Bamboo Armor
        normalItem(ModItems.BAMBOO_HELMET);
        normalItem(ModItems.BAMBOO_CHESTPLATE);
        normalItem(ModItems.BAMBOO_LEGGINGS);
        normalItem(ModItems.BAMBOO_BOOTS);

        // MARK: Exoskeleton Armor
        normalItem(ModItems.EXOSKELETON_HELMET);
        normalItem(ModItems.EXOSKELETON_CHESTPLATE);
        normalItem(ModItems.EXOSKELETON_LEGGINGS);
        normalItem(ModItems.EXOSKELETON_BOOTS);

        // MARK: Reinforced Exoskeleton Armor
        normalItem(ModItems.REIN_EXOSKELETON_HELMET);
        normalItem(ModItems.REIN_EXOSKELETON_CHESTPLATE);
        normalItem(ModItems.REIN_EXOSKELETON_LEGGINGS);
        normalItem(ModItems.REIN_EXOSKELETON_BOOTS);

        // MARK: Rhino Exoskeleton Armor
        normalItem(ModItems.RHINO_EXOSKELETON_HELMET);
        normalItem(ModItems.RHINO_EXOSKELETON_CHESTPLATE);
        normalItem(ModItems.RHINO_EXOSKELETON_LEGGINGS);
        normalItem(ModItems.RHINO_EXOSKELETON_BOOTS);

        // MARK: Jade Armor
        normalItem(ModItems.JADE_HELMET);
        normalItem(ModItems.JADE_CHESTPLATE);
        normalItem(ModItems.JADE_LEGGINGS);
        normalItem(ModItems.JADE_BOOTS);

        // MARK: Jade Tools
        toolItem(ModItems.JADE_SWORD);
        toolItem(ModItems.JADE_PICKAXE);
        toolItem(ModItems.JADE_AXE);
        toolItem(ModItems.JADE_SHOVEL);
        toolItem(ModItems.JADE_PAXEL);
        toolItem(ModItems.JADE_HOE);

        // MARK: Misc Armor & Weapons
        normalItem(ModItems.REIN_COMPOUND_GOGGLES);
        normalItem(ModItems.COMPOUND_GOGGLES);
        normalItem(ModItems.MUSHROOM_HELMET);
        normalItem(ModItems.GLIDER_CHESTPLATE);
        normalItem(ModItems.GLIDER_CHESTPLATE_POWERED);
        normalItem(ModItems.SPIDER_T_SHIRT);
        normalItem(ModItems.SPRINT_LEGGINGS);
        normalItem(ModItems.JUMP_BOOTS);
        normalItem(ModItems.WATER_STRIDERS);
        //normalItem(ModItems.ENHANCED_SCORPION_PINCER); TODO: Figure out models
        //normalItem(ModItems.WAR_HAMMER);
        //normalItem(ModItems.WEB_SLINGER);
        //normalItem(ModItems.WEB_SLINGER_WITHER);
        //normalItem(ModItems.MAX_SPEED_BOW);

        // MARK: Shields
        normalItem(ModItems.BAMBOO_SHIELD);
        normalItem(ModItems.EXOSKELETON_SHIELD);
        normalItem(ModItems.JADE_SHIELD);
        normalItem(ModItems.REIN_EXOSKELETON_SHIELD);
        normalItem(ModItems.RHINO_EXOSKELETON_SHIELD);

        // MARK: Misc
        //normalItem(ModItems.WAND_OF_ANIMATION);
        //normalItem(ModItems.WAND_OF_PRESERVATION);
        //normalItem(ModItems.PORTAL_ACTIVATOR);
        //normalItem(ModItems.WOODLOUSE_BALL);
        normalItem(ModItems.NECTAR_COLLECTOR);
        normalItem(ModItems.ANT_TAMING_AMULET);
        normalItem(ModItems.BEE_TAMING_AMULET);
        normalItem(ModItems.WASP_SWORD);
        //normalItem(ModItems.WASP_DAGGER);
        normalItem(ModItems.ANTI_VENOM_BOTTLE);
        //normalItem(ModItems.DEATH_COMPASS);
        normalItem(ModItems.ROLLED_NEWSPAPER);
        //bucket(ModItems.BAMBUCKET);
        //normalItem(ModItems.HOMING_BEECON);
        //normalItem(ModItems.HOMING_BEECON_ADVANCED);
        normalItem(ModItems.SPRAY_CAN);
        normalItem(ModItems.WHETSTONE);
        normalItem(ModItems.COMPOST);
        normalItem(ModItems.PLANTICIDE);
        normalItem(ModItems.SMOOTHIE_BOOK);
        normalItem(ModItems.HORN_OF_SUMMONING);

        // MARK: Idols
        normalItem(ModItems.MUD_SCARAB);
        normalItem(ModItems.IRON_SCARAB);
        normalItem(ModItems.GOLD_SCARAB);
        normalItem(ModItems.JADE_SCARAB);
        normalItem(ModItems.MUD_UMBERGOLEM);
        normalItem(ModItems.IRON_UMBERGOLEM);
        normalItem(ModItems.GOLD_UMBERGOLEM);
        normalItem(ModItems.JADE_UMBERGOLEM);

        // MARK: Maps
        //normalItem(ModItems.EREBUS_MAP);
        //normalItem(ModItems.EREBUS_MAP_FILLED);

        // MARK: Plants
        normalItem(ModItems.TURNIP);
        normalItem(ModItems.CABBAGE_SEEDS);
        normalItem(ModItems.MANDRAKE_ROOT);
        normalItem(ModItems.SEED_BLACK);
        normalItem(ModItems.SEED_RED);
        normalItem(ModItems.SEED_BROWN);
        normalItem(ModItems.SEED_BLUE);
        normalItem(ModItems.SEED_PURPLE);
        normalItem(ModItems.SEED_CYAN);
        normalItem(ModItems.SEED_LIGHT_GRAY);
        normalItem(ModItems.SEED_GRAY);
        normalItem(ModItems.SEED_PINK);
        normalItem(ModItems.SEED_YELLOW);
        normalItem(ModItems.SEED_LIGHT_BLUE);
        normalItem(ModItems.SEED_MAGENTA);
        normalItem(ModItems.SEED_ORANGE);
        normalItem(ModItems.SEED_WHITE);
        normalItem(ModItems.SEED_RAINBOW);

        normalItem(ModItems.LIFE_BLOOD);
        normalItem(ModItems.HEART_BERRIES);
        normalItem(ModItems.STAG_HEART_RAW);
        normalItem(ModItems.STAG_HEART_COOKED);
    }

    private void registerBlockItemModels() {
        // MARK: Umberstone
        block(UMBERSTONE);
        block(UMBERSTONE_BRICKS);
        block(UMBERCOBBLE);
        block(UMBERCOBBLE_MOSSY);
        block(UMBERCOBBLE_WEBBED);
        block(UMBERTILE_SMOOTH);
        block(UMBERTILE_SMOOTH_SMALL);
        block(UMBERGRAVEL);
        block(UMBERPAVER);
        block(UMBERPAVER_MOSSY);
        block(UMBERPAVER_WEBBED);
        block(UMBERSTONE_PILLAR);
        block(VOLCANIC_ROCK);
        block(DUST);
        block(DUST_LAYER);
        block(PETRIFIED_WOOD_ROCK);
        block(PETRIFIED_WOOD_ROCK_2);
        block(PETRIFIED_WOOD_ROCK_3);
        block(PETRIFIED_WOOD_ROCK_4);
        block(PETRIFIED_WOOD_ROCK_5);
        block(PETRIFIED_WOOD_ROCK_6);
        block(PETRIFIED_BARK_RED);
        block(PETRIFIED_BARK_BROWN);
        block(PETRIFIED_LOG_INNER);
        block(DUNG);

        // MARK: Amber
        block(AMBER);
        block(AMBER_BRICKS);
        block(AMBER_GLASS);
        //block(PRESERVED_AMBER);
        //block(PRESERVED_AMBER_GLASS);
        block(GLOWING_JAR);
        block(FLUID_JAR);
        blockFlatWithItemTexture(AMBER_DOOR, "door_amber");

        // MARK: Ores
        block(ORE_IRON);
        block(ORE_GOLD);
        block(ORE_COAL);
        block(ORE_DIAMOND);
        block(ORE_EMERALD);
        block(ORE_LAPIS);
        block(ORE_QUARTZ);
        block(ORE_PETRIFIED_QUARTZ);
        block(ORE_COPPER);
        block(ORE_SILVER);
        block(ORE_TIN);
        block(ORE_LEAD);
        block(ORE_ALUMINUM);
        block(ORE_JADE);
        block(ORE_ENCRUSTED_DIAMOND);
        block(ORE_FOSSIL);
        block(ORE_GNEISS);
        block(ORE_PETRIFIED_WOOD);
        block(ORE_TEMPLE);

        // MARK: Logs
        block(LOG_BAOBAB);
        block(LOG_EUCALYPTUS);
        block(LOG_MAHOGANY);
        block(LOG_MOSSBARK);
        block(LOG_ASPER);
        block(LOG_CYPRESS);
        block(LOG_BALSAM);
        block(LOG_BALSAM_RESINLESS);
        block(LOG_ROTTEN);
        block(LOG_MARSHWOOD);
        block(LOG_SCORCHED);
        block(LOG_BAMBOO);
        block(LOG_HOLLOW);

        // MARK: Saplings
        block(SAPLING_BAOBAB);
        block(SAPLING_EUCALYPTUS);
        block(SAPLING_MAHOGANY);
        block(SAPLING_MOSSBARK);
        block(SAPLING_ASPER);
        block(SAPLING_CYPRESS);
        block(SAPLING_BALSAM);
        block(SAPLING_MARSHWOOD);
        block(SAPLING_BAMBOO);

        // MARK: Leaves
        block(LEAVES_BAOBAB);
        block(LEAVES_EUCALYPTUS);
        block(LEAVES_MAHOGANY);
        block(LEAVES_MOSSBARK);
        block(LEAVES_ASPER);
        block(LEAVES_CYPRESS);
        block(LEAVES_BALSAM);
        block(LEAVES_MARSHWOOD);

        // MARK: Planks
        block(PLANKS_BAOBAB);
        block(PLANKS_EUCALYPTUS);
        block(PLANKS_MAHOGANY);
        block(PLANKS_MOSSBARK);
        block(PLANKS_ASPER);
        block(PLANKS_CYPRESS);
        block(PLANKS_BALSAM);
        block(PLANKS_WHITE);
        block(PLANKS_BAMBOO);
        block(PLANKS_ROTTEN);
        block(PLANKS_MARSHWOOD);
        block(PLANKS_SCORCHED);
        block(PLANKS_VARNISHED);
        block(PLANKS_PETRIFIED);

        // MARK: Slabs Wood
        slab(SLAB_PLANKS_BAOBAB, PLANKS_BAOBAB);
        slab(SLAB_PLANKS_EUCALYPTUS, PLANKS_EUCALYPTUS);
        slab(SLAB_PLANKS_MAHOGANY, PLANKS_MAHOGANY);
        slab(SLAB_PLANKS_MOSSBARK, PLANKS_MOSSBARK);
        slab(SLAB_PLANKS_ASPER, PLANKS_ASPER);
        slab(SLAB_PLANKS_CYPRESS, PLANKS_CYPRESS);
        slab(SLAB_PLANKS_BALSAM, PLANKS_BALSAM);
        slab(SLAB_PLANKS_WHITE, PLANKS_WHITE);
        slab(SLAB_PLANKS_BAMBOO, PLANKS_BAMBOO);
        slab(SLAB_PLANKS_ROTTEN, PLANKS_ROTTEN);
        slab(SLAB_PLANKS_MARSHWOOD, PLANKS_MARSHWOOD);
        slab(SLAB_PLANKS_SCORCHED, PLANKS_SCORCHED);
        slab(SLAB_PLANKS_VARNISHED, PLANKS_VARNISHED);
        slab(SLAB_PLANKS_PETRIFIED, PLANKS_PETRIFIED);

        // MARK: Slabs Stone
        slab(SLAB_UMBERSTONE, UMBERSTONE);
        slab(SLAB_UMBERCOBBLE, UMBERCOBBLE);
        slab(SLAB_UMBERCOBBLE_MOSSY, UMBERCOBBLE_MOSSY);
        slab(SLAB_UMBERCOBBLE_WEBBED, UMBERCOBBLE_WEBBED);
        slab(SLAB_UMBERSTONE_BRICKS, UMBERSTONE_BRICKS);
        slab(SLAB_UMBERTILE_SMOOTH, UMBERTILE_SMOOTH);
        slab(SLAB_UMBERTILE_SMOOTH_SMALL, UMBERTILE_SMOOTH_SMALL);
        slab(SLAB_UMBERPAVER, UMBERPAVER);
        slab(SLAB_UMBERPAVER_MOSSY, UMBERPAVER_MOSSY);
        slab(SLAB_UMBERPAVER_WEBBED, UMBERPAVER_WEBBED);
        slab(SLAB_AMBER, AMBER);
        slab(SLAB_AMBER_BRICKS, AMBER_BRICKS);
        slab(SLAB_MIR_BRICKS, MIR_BRICKS);
        slab(SLAB_MUD_BRICKS, MUD_BRICKS);

        // MARK: Stairs Wood
        stairs(STAIRS_BAOBAB, PLANKS_BAOBAB);
        stairs(STAIRS_EUCALYPTUS, PLANKS_EUCALYPTUS);
        stairs(STAIRS_MAHOGANY, PLANKS_MAHOGANY);
        stairs(STAIRS_MOSSBARK, PLANKS_MOSSBARK);
        stairs(STAIRS_ASPER, PLANKS_ASPER);
        stairs(STAIRS_CYPRESS, PLANKS_CYPRESS);
        stairs(STAIRS_BALSAM, PLANKS_BALSAM);
        stairs(STAIRS_WHITE, PLANKS_WHITE);
        stairs(STAIRS_BAMBOO, PLANKS_BAMBOO);
        stairs(STAIRS_ROTTEN, PLANKS_ROTTEN);
        stairs(STAIRS_MARSHWOOD, PLANKS_MARSHWOOD);
        stairs(STAIRS_SCORCHED, PLANKS_SCORCHED);
        stairs(STAIRS_VARNISHED, PLANKS_VARNISHED);
        stairs(STAIRS_PETRIFIED, PLANKS_PETRIFIED);

        // MARK: Stairs
        stairs(STAIRS_UMBERSTONE, UMBERSTONE);
        stairs(STAIRS_UMBERCOBBLE, UMBERCOBBLE);
        stairs(STAIRS_UMBERCOBBLE_MOSSY, UMBERCOBBLE_MOSSY);
        stairs(STAIRS_UMBERCOBBLE_WEBBED, UMBERCOBBLE_WEBBED);
        stairs(STAIRS_UMBERSTONE_BRICKS, UMBERSTONE_BRICKS);
        stairs(STAIRS_UMBERTILE_SMOOTH, UMBERTILE_SMOOTH);
        stairs(STAIRS_UMBERTILE_SMOOTH_SMALL, UMBERTILE_SMOOTH_SMALL);
        stairs(STAIRS_UMBERPAVER, UMBERPAVER);
        stairs(STAIRS_UMBERPAVER_MOSSY, UMBERPAVER_MOSSY);
        stairs(STAIRS_UMBERPAVER_WEBBED, UMBERPAVER_WEBBED);
        stairs(STAIRS_AMBER, AMBER);
        stairs(STAIRS_AMBER_BRICKS, AMBER_BRICKS);
        stairs(STAIRS_MUD_BRICKS, MUD_BRICKS);
        stairs(STAIRS_MIR_BRICKS, MIR_BRICKS);

        // MARK: Doors
        blockFlatWithItemTexture(DOOR_BAOBAB, "door_baobab");
        blockFlatWithItemTexture(DOOR_EUCALYPTUS, "door_eucalyptus");
        blockFlatWithItemTexture(DOOR_MAHOGANY, "door_mahogany");
        blockFlatWithItemTexture(DOOR_MOSSBARK, "door_mossbark");
        blockFlatWithItemTexture(DOOR_ASPER, "door_asper");
        blockFlatWithItemTexture(DOOR_CYPRESS, "door_cypress");
        blockFlatWithItemTexture(DOOR_BALSAM, "door_balsam");
        blockFlatWithItemTexture(DOOR_WHITE, "door_white");
        blockFlatWithItemTexture(DOOR_ROTTEN, "door_rotten");
        blockFlatWithItemTexture(DOOR_MARSHWOOD, "door_marshwood");
        blockFlatWithItemTexture(DOOR_SCORCHED, "door_scorched");

        // MARK: Fences
        itemFence(FENCE_BAOBAB, PLANKS_BAOBAB);
        itemFence(FENCE_EUCALYPTUS, PLANKS_EUCALYPTUS);
        itemFence(FENCE_MAHOGANY, PLANKS_MAHOGANY);
        itemFence(FENCE_MOSSBARK, PLANKS_MOSSBARK);
        itemFence(FENCE_ASPER, PLANKS_ASPER);
        itemFence(FENCE_CYPRESS, PLANKS_CYPRESS);
        itemFence(FENCE_BALSAM, PLANKS_BALSAM);
        itemFence(FENCE_WHITE, PLANKS_WHITE);
        itemFence(FENCE_BAMBOO, PLANKS_BAMBOO);
        itemFence(FENCE_ROTTEN, PLANKS_ROTTEN);
        itemFence(FENCE_MARSHWOOD, PLANKS_MARSHWOOD);
        itemFence(FENCE_SCORCHED, PLANKS_SCORCHED);
        itemFence(FENCE_VARNISHED, PLANKS_VARNISHED);

        // MARK: Fence Gates
        fenceGate(FENCE_GATE_BAOBAB, PLANKS_BAOBAB);
        fenceGate(FENCE_GATE_EUCALYPTUS, PLANKS_EUCALYPTUS);
        fenceGate(FENCE_GATE_MAHOGANY, PLANKS_MAHOGANY);
        fenceGate(FENCE_GATE_MOSSBARK, PLANKS_MOSSBARK);
        fenceGate(FENCE_GATE_ASPER, PLANKS_ASPER);
        fenceGate(FENCE_GATE_CYPRESS, PLANKS_CYPRESS);
        fenceGate(FENCE_GATE_BALSAM, PLANKS_BALSAM);
        fenceGate(FENCE_GATE_WHITE, PLANKS_WHITE);
        fenceGate(FENCE_GATE_BAMBOO, PLANKS_BAMBOO);
        fenceGate(FENCE_GATE_ROTTEN, PLANKS_ROTTEN);
        fenceGate(FENCE_GATE_MARSHWOOD, PLANKS_MARSHWOOD);
        fenceGate(FENCE_GATE_SCORCHED, PLANKS_SCORCHED);
        fenceGate(FENCE_GATE_VARNISHED, PLANKS_VARNISHED);

        // MARK: Walls
        wall(WALL_UMBERSTONE, UMBERSTONE);
        wall(WALL_UMBERCOBBLE, UMBERCOBBLE);
        wall(WALL_UMBERCOBBLE_MOSSY, UMBERCOBBLE_MOSSY);
        wall(WALL_UMBERCOBBLE_WEBBED, UMBERCOBBLE_WEBBED);
        wall(WALL_UMBERSTONE_BRICKS, UMBERSTONE_BRICKS);
        wall(WALL_UMBERTILE_SMOOTH, UMBERTILE_SMOOTH);
        wall(WALL_UMBERTILE_SMOOTH_SMALL, UMBERTILE_SMOOTH_SMALL);
        wall(WALL_UMBERPAVER, UMBERPAVER);
        wall(WALL_UMBERPAVER_MOSSY, UMBERPAVER_MOSSY);
        wall(WALL_UMBERPAVER_WEBBED, UMBERPAVER_WEBBED);
        wall(WALL_AMBER, AMBER);
        wall(WALL_AMBER_BRICKS, AMBER_BRICKS);

        // MARK: Plants
        blockFlat(DESERT_SHRUB);
        //blockFlat(MIRE_CORAL);
        blockFlat(NETTLE);
        blockFlat(NETTLE_FLOWERED);
        blockFlat(SWAMP_PLANT);
        blockFlat(FIRE_BLOOM);
        blockFlat(FERN);
        blockFlat(FIDDLE_HEAD);
        blockFlat(THORNS);
        blockFlat(MOSS_DOWN);
        blockFlat(MOULD_DOWN);
        blockFlat(CULTIVATED_MOSS_DOWN);
        blockFlat(CULTIVATED_MOULD_DOWN);
        blockFlat(ALGAE);
        blockFlat(HANGING_WEB);

        block(PETAL_BLACK);
        block(PETAL_RED);
        block(PETAL_BROWN);
        block(PETAL_BLUE);
        block(PETAL_PURPLE);
        block(PETAL_CYAN);
        block(PETAL_LIGHT_GRAY);
        block(PETAL_GRAY);
        block(PETAL_PINK);
        block(PETAL_YELLOW);
        block(PETAL_LIGHT_BLUE);
        block(PETAL_MAGENTA);
        block(PETAL_ORANGE);
        block(PETAL_WHITE);

        block(EXPLODING_STIGMA);
        block(STEM);
        block(STIGMA_BLACK);
        block(STIGMA_RED);
        block(STIGMA_BROWN);
        block(STIGMA_BLUE);
        block(STIGMA_PURPLE);
        block(STIGMA_CYAN);
        block(STIGMA_LIGHT_GRAY);
        block(STIGMA_GRAY);
        block(STIGMA_PINK);
        block(STIGMA_YELLOW);
        block(STIGMA_LIGHT_BLUE);
        block(STIGMA_MAGENTA);
        block(STIGMA_ORANGE);
        block(STIGMA_WHITE);

        block(FLOWER_BLACK);
        block(FLOWER_RED);
        block(FLOWER_BROWN);
        block(FLOWER_BLUE);
        block(FLOWER_PURPLE);
        block(FLOWER_CYAN);
        block(FLOWER_LIGHT_GRAY);
        block(FLOWER_GRAY);
        block(FLOWER_PINK);
        block(FLOWER_YELLOW);
        block(FLOWER_LIGHT_BLUE);
        block(FLOWER_MAGENTA);
        block(FLOWER_ORANGE);
        block(FLOWER_WHITE);
        block(FLOWER_RAINBOW);

//        blockFlat(BULLRUSH);
//        blockFlat(WEEPING_BLUEBELL);
//        blockFlat(SUNDEW);
//        blockFlat(DROUGHTED_SHRUB);
//        blockFlat(TALL_BLOOM);
//        blockFlat(TANGLED_STALK);
//        blockFlat(HIGH_CAPPED_MUSHROOM);
//        blockFlat(TALL_FERN);

        // MARK: Other
        block(GAEAN_KEYSTONE);
        block(JADE_BLOCK);
        block(MUD);
        block(QUICK_SAND);
        block(GHOST_SAND);
        block(SWAMP_VENT);
        block(GNEISS_VENT);
        block(RED_GEM_BLOCK);
        //block(RED_GEM_LAMP);
        //block(WITHER_WEB);
        block(GNEISS);
        block(GNEISS_CARVED);
        block(GNEISS_RELIEF);
        block(GNEISS_BRICKS);
        block(GNEISS_SMOOTH);
        block(GNEISS_TILES);
        block(GNEISS_TILES_CRACKED);
        block(TEMPLE_BRICK);
        block(TEMPLE_PILLAR);
        block(TEMPLE_TILE);
        block(SILK);
        block(MIR_BRICKS);
        block(MUD_BRICKS);
        block(REIN_EXO);
        block(VELOCITY);
        block(LIGHTNING_SPEED);
        block(BLOCK_OF_BONES);
        block(ANTLION_EGG);
        block(TARANTULA_EGG);
        //block(HONEY_TREAT);
        block(WASP_NEST);
        block(STAIRS_WASP_NEST);
        blockFlatWithItemTexture(INSECT_REPELLENT, "repellent");

        // MARK: Spawners
        block(ANTLION_SPAWNER);
        block(DRAGON_FLY_SPAWNER);
        block(JUMPING_SPIDER_SPAWNER);
        block(SPIDER_SPAWNER);
        block(TARANTULA_SPAWNER);
        block(WASP_SPAWNER);
        block(ZOMBIE_ANT_SPAWNER);
        block(ZOMBIE_ANT_SOLDIER_SPAWNER);
        block(MAGMA_CRAWLER_SPAWNER);
        block(DUNG_SPAWNER_FLY);
        block(DUNG_SPAWNER_BOT_FLY);
        block(LOCUST_SPAWNER);

        // MARK: Utility Blocks
        //block(PETRIFIED_CRAFTING_TABLE);
        //block(PETRIFIED_WOOD_CHEST);
        //block(BAMBOO_CRATE);
        //block(BAMBOO_BRIDGE);
        //block(BAMBOO_LADDER);
        //block(BAMBOO_NERD_POLE);
        //block(BAMBOO_EXTENDER);
        //blockFlat(BAMBOO_TORCH);
        //block(BAMBOO_PIPE);
        //block(BAMBOO_PIPE_EXTRACT);
        //block(BAMBOO_PIPE_EXTRACT_ACTIVE);
        block(SILO_ROOF);
        block(SILO_TANK);
        block(SILO_SUPPORTS);
        //block(HONEY_COMB);
        block(COMPOSTER);
        block(BLENDER);
        //block(UMBER_FURNACE);
        block(UMBERSTONE_BUTTON);
        //block(LIQUIFIER);
        //block(GLOW_GEM);
        //block(MUCUS_BOMB);
        //block(UMBER_GOLEM_STATUE);

//        block(ALTAR_BASE);
//        block(ALTAR_LIGHTNING);
//        block(ALTAR_HEALING);
//        block(ALTAR_XP);
//        block(ALTAR_REPAIR);
//        block(ALTAR_OFFERING);

        // MARK: Antlion Dungeon
        block(CAPSTONE);
        block(CAPSTONE_MUD);
        block(CAPSTONE_IRON);
        block(CAPSTONE_GOLD);
        block(CAPSTONE_JADE);
        block(TEMPLE_BRICK_UNBREAKING);
        block(TEMPLE_BRICK_UNBREAKING_JADE);
        block(TEMPLE_BRICK_UNBREAKING_EXO);
        block(TEMPLE_BRICK_UNBREAKING_CREAM);
        block(TEMPLE_BRICK_UNBREAKING_EYE);
        block(TEMPLE_BRICK_UNBREAKING_STRING);
        block(TEMPLE_TELEPORTER);
        block(FORCE_FIELD);
        block(FORCE_LOCK);
        block(ANT_HILL_BLOCK);
    }
}
