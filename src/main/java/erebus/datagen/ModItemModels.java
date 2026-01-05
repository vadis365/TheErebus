package erebus.datagen;

import erebus.datagen.providers.ModItemModelProvider;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
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
        normalItemCutout(ModItems.HONEY_DRIP);
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
        normalItem(ModItems.PLATE_ZOMBIE_ANT);
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
        //normalItem(ModItems.MAX_SPEED_BOW);

        // MARK: Misc
        //normalItem(ModItems.PORTAL_ACTIVATOR);
        //normalItem(ModItems.WOODLOUSE_BALL);
        normalItem(ModItems.NECTAR_COLLECTOR);
        normalItem(ModItems.ANT_TAMING_AMULET);
        normalItem(ModItems.BEE_TAMING_AMULET);
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
        block(ModBlocks.UMBERSTONE);
        block(ModBlocks.UMBERSTONE_BRICKS);
        block(ModBlocks.UMBERCOBBLE);
        block(ModBlocks.UMBERCOBBLE_MOSSY);
        block(ModBlocks.UMBERCOBBLE_WEBBED);
        block(ModBlocks.UMBERTILE_SMOOTH);
        block(ModBlocks.UMBERTILE_SMOOTH_SMALL);
        block(ModBlocks.UMBERGRAVEL);
        block(ModBlocks.UMBERPAVER);
        block(ModBlocks.UMBERPAVER_MOSSY);
        block(ModBlocks.UMBERPAVER_WEBBED);
        block(ModBlocks.UMBERSTONE_PILLAR);
        block(ModBlocks.VOLCANIC_ROCK);
        block(ModBlocks.DUST);
        block(ModBlocks.DUST_LAYER, "dust");
        block(ModBlocks.PETRIFIED_WOOD_ROCK);
        block(ModBlocks.PETRIFIED_WOOD_ROCK_2);
        block(ModBlocks.PETRIFIED_WOOD_ROCK_3);
        block(ModBlocks.PETRIFIED_WOOD_ROCK_4);
        block(ModBlocks.PETRIFIED_WOOD_ROCK_5);
        block(ModBlocks.PETRIFIED_WOOD_ROCK_6);
        block(ModBlocks.PETRIFIED_BARK_RED);
        block(ModBlocks.PETRIFIED_BARK_BROWN);
        block(ModBlocks.PETRIFIED_LOG_INNER);
        block(ModBlocks.DUNG);

        // MARK: Amber
        block(ModBlocks.AMBER);
        block(ModBlocks.AMBER_BRICKS);
        block(ModBlocks.AMBER_GLASS);
        block(ModBlocks.PRESERVED_AMBER);
        block(ModBlocks.PRESERVED_AMBER_GLASS);
        block(ModBlocks.FLUID_JAR);
        blockFlatWithItemTexture(ModBlocks.AMBER_DOOR, "door_amber");

        // MARK: Ores
        block(ModBlocks.ORE_IRON);
        block(ModBlocks.ORE_GOLD);
        block(ModBlocks.ORE_COAL);
        block(ModBlocks.ORE_DIAMOND);
        block(ModBlocks.ORE_EMERALD);
        block(ModBlocks.ORE_LAPIS);
        block(ModBlocks.ORE_QUARTZ);
        block(ModBlocks.ORE_PETRIFIED_QUARTZ);
        block(ModBlocks.ORE_COPPER);
        block(ModBlocks.ORE_SILVER);
        block(ModBlocks.ORE_TIN);
        block(ModBlocks.ORE_LEAD);
        block(ModBlocks.ORE_ALUMINUM);
        block(ModBlocks.ORE_JADE);
        block(ModBlocks.ORE_ENCRUSTED_DIAMOND);
        block(ModBlocks.ORE_FOSSIL);
        block(ModBlocks.ORE_GNEISS);
        block(ModBlocks.ORE_PETRIFIED_WOOD);
        block(ModBlocks.ORE_TEMPLE);

        // MARK: Logs
        block(ModBlocks.LOG_BAOBAB);
        block(ModBlocks.LOG_EUCALYPTUS);
        block(ModBlocks.LOG_MAHOGANY);
        block(ModBlocks.LOG_MOSSBARK);
        block(ModBlocks.LOG_ASPER);
        block(ModBlocks.LOG_CYPRESS);
        block(ModBlocks.LOG_BALSAM);
        block(ModBlocks.LOG_BALSAM_RESINLESS);
        block(ModBlocks.LOG_ROTTEN);
        block(ModBlocks.LOG_MARSHWOOD);
        block(ModBlocks.LOG_SCORCHED);

        // MARK: Saplings
        blockFlat(ModBlocks.SAPLING_BAOBAB);
        blockFlat(ModBlocks.SAPLING_EUCALYPTUS);
        blockFlat(ModBlocks.SAPLING_MAHOGANY);
        blockFlat(ModBlocks.SAPLING_MOSSBARK);
        blockFlat(ModBlocks.SAPLING_ASPER);
        blockFlat(ModBlocks.SAPLING_CYPRESS);
        blockFlat(ModBlocks.SAPLING_BALSAM);
        blockFlat(ModBlocks.SAPLING_MARSHWOOD);
        blockFlat(ModBlocks.SAPLING_BAMBOO);

        // MARK: Leaves
        block(ModBlocks.LEAVES_BAOBAB);
        block(ModBlocks.LEAVES_EUCALYPTUS);
        block(ModBlocks.LEAVES_MAHOGANY);
        block(ModBlocks.LEAVES_MOSSBARK);
        block(ModBlocks.LEAVES_ASPER);
        block(ModBlocks.LEAVES_CYPRESS);
        block(ModBlocks.LEAVES_BALSAM);
        block(ModBlocks.LEAVES_MARSHWOOD);

        // MARK: Planks
        block(ModBlocks.PLANKS_BAOBAB);
        block(ModBlocks.PLANKS_EUCALYPTUS);
        block(ModBlocks.PLANKS_MAHOGANY);
        block(ModBlocks.PLANKS_MOSSBARK);
        block(ModBlocks.PLANKS_ASPER);
        block(ModBlocks.PLANKS_CYPRESS);
        block(ModBlocks.PLANKS_BALSAM);
        block(ModBlocks.PLANKS_WHITE);
        block(ModBlocks.PLANKS_BAMBOO);
        block(ModBlocks.PLANKS_ROTTEN);
        block(ModBlocks.PLANKS_MARSHWOOD);
        block(ModBlocks.PLANKS_SCORCHED);
        block(ModBlocks.PLANKS_VARNISHED);
        block(ModBlocks.PLANKS_PETRIFIED);

        // MARK: Slabs Wood
        slab(ModBlocks.SLAB_PLANKS_BAOBAB, ModBlocks.PLANKS_BAOBAB);
        slab(ModBlocks.SLAB_PLANKS_EUCALYPTUS, ModBlocks.PLANKS_EUCALYPTUS);
        slab(ModBlocks.SLAB_PLANKS_MAHOGANY, ModBlocks.PLANKS_MAHOGANY);
        slab(ModBlocks.SLAB_PLANKS_MOSSBARK, ModBlocks.PLANKS_MOSSBARK);
        slab(ModBlocks.SLAB_PLANKS_ASPER, ModBlocks.PLANKS_ASPER);
        slab(ModBlocks.SLAB_PLANKS_CYPRESS, ModBlocks.PLANKS_CYPRESS);
        slab(ModBlocks.SLAB_PLANKS_BALSAM, ModBlocks.PLANKS_BALSAM);
        slab(ModBlocks.SLAB_PLANKS_WHITE, ModBlocks.PLANKS_WHITE);
        slab(ModBlocks.SLAB_PLANKS_BAMBOO, ModBlocks.PLANKS_BAMBOO);
        slab(ModBlocks.SLAB_PLANKS_ROTTEN, ModBlocks.PLANKS_ROTTEN);
        slab(ModBlocks.SLAB_PLANKS_MARSHWOOD, ModBlocks.PLANKS_MARSHWOOD);
        slab(ModBlocks.SLAB_PLANKS_SCORCHED, ModBlocks.PLANKS_SCORCHED);
        slab(ModBlocks.SLAB_PLANKS_VARNISHED, ModBlocks.PLANKS_VARNISHED);
        slab(ModBlocks.SLAB_PLANKS_PETRIFIED, ModBlocks.PLANKS_PETRIFIED);

        // MARK: Slabs Stone
        slab(ModBlocks.SLAB_UMBERSTONE, ModBlocks.UMBERSTONE);
        slab(ModBlocks.SLAB_UMBERCOBBLE, ModBlocks.UMBERCOBBLE);
        slab(ModBlocks.SLAB_UMBERCOBBLE_MOSSY, ModBlocks.UMBERCOBBLE_MOSSY);
        slab(ModBlocks.SLAB_UMBERCOBBLE_WEBBED, ModBlocks.UMBERCOBBLE_WEBBED);
        slab(ModBlocks.SLAB_UMBERSTONE_BRICKS, ModBlocks.UMBERSTONE_BRICKS);
        slab(ModBlocks.SLAB_UMBERTILE_SMOOTH, ModBlocks.UMBERTILE_SMOOTH);
        slab(ModBlocks.SLAB_UMBERTILE_SMOOTH_SMALL, ModBlocks.UMBERTILE_SMOOTH_SMALL);
        slab(ModBlocks.SLAB_UMBERPAVER, ModBlocks.UMBERPAVER);
        slab(ModBlocks.SLAB_UMBERPAVER_MOSSY, ModBlocks.UMBERPAVER_MOSSY);
        slab(ModBlocks.SLAB_UMBERPAVER_WEBBED, ModBlocks.UMBERPAVER_WEBBED);
        slab(ModBlocks.SLAB_AMBER, ModBlocks.AMBER);
        slab(ModBlocks.SLAB_AMBER_BRICKS, ModBlocks.AMBER_BRICKS);
        slab(ModBlocks.SLAB_MIR_BRICKS, ModBlocks.MIR_BRICKS);
        slab(ModBlocks.SLAB_MUD_BRICKS, ModBlocks.MUD_BRICKS);

        // MARK: Stairs Wood
        stairs(ModBlocks.STAIRS_BAOBAB, ModBlocks.PLANKS_BAOBAB);
        stairs(ModBlocks.STAIRS_EUCALYPTUS, ModBlocks.PLANKS_EUCALYPTUS);
        stairs(ModBlocks.STAIRS_MAHOGANY, ModBlocks.PLANKS_MAHOGANY);
        stairs(ModBlocks.STAIRS_MOSSBARK, ModBlocks.PLANKS_MOSSBARK);
        stairs(ModBlocks.STAIRS_ASPER, ModBlocks.PLANKS_ASPER);
        stairs(ModBlocks.STAIRS_CYPRESS, ModBlocks.PLANKS_CYPRESS);
        stairs(ModBlocks.STAIRS_BALSAM, ModBlocks.PLANKS_BALSAM);
        stairs(ModBlocks.STAIRS_WHITE, ModBlocks.PLANKS_WHITE);
        stairs(ModBlocks.STAIRS_BAMBOO, ModBlocks.PLANKS_BAMBOO);
        stairs(ModBlocks.STAIRS_ROTTEN, ModBlocks.PLANKS_ROTTEN);
        stairs(ModBlocks.STAIRS_MARSHWOOD, ModBlocks.PLANKS_MARSHWOOD);
        stairs(ModBlocks.STAIRS_SCORCHED, ModBlocks.PLANKS_SCORCHED);
        stairs(ModBlocks.STAIRS_VARNISHED, ModBlocks.PLANKS_VARNISHED);
        stairs(ModBlocks.STAIRS_PETRIFIED, ModBlocks.PLANKS_PETRIFIED);

        // MARK: Stairs
        stairs(ModBlocks.STAIRS_UMBERSTONE, ModBlocks.UMBERSTONE);
        stairs(ModBlocks.STAIRS_UMBERCOBBLE, ModBlocks.UMBERCOBBLE);
        stairs(ModBlocks.STAIRS_UMBERCOBBLE_MOSSY, ModBlocks.UMBERCOBBLE_MOSSY);
        stairs(ModBlocks.STAIRS_UMBERCOBBLE_WEBBED, ModBlocks.UMBERCOBBLE_WEBBED);
        stairs(ModBlocks.STAIRS_UMBERSTONE_BRICKS, ModBlocks.UMBERSTONE_BRICKS);
        stairs(ModBlocks.STAIRS_UMBERTILE_SMOOTH, ModBlocks.UMBERTILE_SMOOTH);
        stairs(ModBlocks.STAIRS_UMBERTILE_SMOOTH_SMALL, ModBlocks.UMBERTILE_SMOOTH_SMALL);
        stairs(ModBlocks.STAIRS_UMBERPAVER, ModBlocks.UMBERPAVER);
        stairs(ModBlocks.STAIRS_UMBERPAVER_MOSSY, ModBlocks.UMBERPAVER_MOSSY);
        stairs(ModBlocks.STAIRS_UMBERPAVER_WEBBED, ModBlocks.UMBERPAVER_WEBBED);
        stairs(ModBlocks.STAIRS_AMBER, ModBlocks.AMBER);
        stairs(ModBlocks.STAIRS_AMBER_BRICKS, ModBlocks.AMBER_BRICKS);
        stairs(ModBlocks.STAIRS_MUD_BRICKS, ModBlocks.MUD_BRICKS);
        stairs(ModBlocks.STAIRS_MIR_BRICKS, ModBlocks.MIR_BRICKS);

        // MARK: Doors
        blockFlatWithItemTexture(ModBlocks.DOOR_BAOBAB, "door_baobab");
        blockFlatWithItemTexture(ModBlocks.DOOR_EUCALYPTUS, "door_eucalyptus");
        blockFlatWithItemTexture(ModBlocks.DOOR_MAHOGANY, "door_mahogany");
        blockFlatWithItemTexture(ModBlocks.DOOR_MOSSBARK, "door_mossbark");
        blockFlatWithItemTexture(ModBlocks.DOOR_ASPER, "door_asper");
        blockFlatWithItemTexture(ModBlocks.DOOR_CYPRESS, "door_cypress");
        blockFlatWithItemTexture(ModBlocks.DOOR_BALSAM, "door_balsam");
        blockFlatWithItemTexture(ModBlocks.DOOR_WHITE, "door_white");
        blockFlatWithItemTexture(ModBlocks.DOOR_ROTTEN, "door_rotten");
        blockFlatWithItemTexture(ModBlocks.DOOR_MARSHWOOD, "door_marshwood");
        blockFlatWithItemTexture(ModBlocks.DOOR_SCORCHED, "door_scorched");

        // MARK: Fences
        itemFence(ModBlocks.FENCE_BAOBAB, ModBlocks.PLANKS_BAOBAB);
        itemFence(ModBlocks.FENCE_EUCALYPTUS, ModBlocks.PLANKS_EUCALYPTUS);
        itemFence(ModBlocks.FENCE_MAHOGANY, ModBlocks.PLANKS_MAHOGANY);
        itemFence(ModBlocks.FENCE_MOSSBARK, ModBlocks.PLANKS_MOSSBARK);
        itemFence(ModBlocks.FENCE_ASPER, ModBlocks.PLANKS_ASPER);
        itemFence(ModBlocks.FENCE_CYPRESS, ModBlocks.PLANKS_CYPRESS);
        itemFence(ModBlocks.FENCE_BALSAM, ModBlocks.PLANKS_BALSAM);
        itemFence(ModBlocks.FENCE_WHITE, ModBlocks.PLANKS_WHITE);
        itemFence(ModBlocks.FENCE_BAMBOO, ModBlocks.PLANKS_BAMBOO);
        itemFence(ModBlocks.FENCE_ROTTEN, ModBlocks.PLANKS_ROTTEN);
        itemFence(ModBlocks.FENCE_MARSHWOOD, ModBlocks.PLANKS_MARSHWOOD);
        itemFence(ModBlocks.FENCE_SCORCHED, ModBlocks.PLANKS_SCORCHED);
        itemFence(ModBlocks.FENCE_VARNISHED, ModBlocks.PLANKS_VARNISHED);

        // MARK: Fence Gates
        fenceGate(ModBlocks.FENCE_GATE_BAOBAB, ModBlocks.PLANKS_BAOBAB);
        fenceGate(ModBlocks.FENCE_GATE_EUCALYPTUS, ModBlocks.PLANKS_EUCALYPTUS);
        fenceGate(ModBlocks.FENCE_GATE_MAHOGANY, ModBlocks.PLANKS_MAHOGANY);
        fenceGate(ModBlocks.FENCE_GATE_MOSSBARK, ModBlocks.PLANKS_MOSSBARK);
        fenceGate(ModBlocks.FENCE_GATE_ASPER, ModBlocks.PLANKS_ASPER);
        fenceGate(ModBlocks.FENCE_GATE_CYPRESS, ModBlocks.PLANKS_CYPRESS);
        fenceGate(ModBlocks.FENCE_GATE_BALSAM, ModBlocks.PLANKS_BALSAM);
        fenceGate(ModBlocks.FENCE_GATE_WHITE, ModBlocks.PLANKS_WHITE);
        fenceGate(ModBlocks.FENCE_GATE_BAMBOO, ModBlocks.PLANKS_BAMBOO);
        fenceGate(ModBlocks.FENCE_GATE_ROTTEN, ModBlocks.PLANKS_ROTTEN);
        fenceGate(ModBlocks.FENCE_GATE_MARSHWOOD, ModBlocks.PLANKS_MARSHWOOD);
        fenceGate(ModBlocks.FENCE_GATE_SCORCHED, ModBlocks.PLANKS_SCORCHED);
        fenceGate(ModBlocks.FENCE_GATE_VARNISHED, ModBlocks.PLANKS_VARNISHED);

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
        wall(ModBlocks.WALL_AMBER, ModBlocks.AMBER);
        wall(ModBlocks.WALL_AMBER_BRICKS, ModBlocks.AMBER_BRICKS);

        // MARK: Plants
        blockFlatWithBlockTexture(ModBlocks.HEART_BERRY_BUSH, "heart_berry_bush_3");
        blockFlatWithBlockTexture(ModBlocks.JADE_BERRY_BUSH, "jade_berry_bush_3");
        blockFlatWithBlockTexture(ModBlocks.SWAMP_BERRY_BUSH, "swamp_berry_bush_3");
        blockFlatWithBlockTexture(ModBlocks.DARK_FRUIT_VINE, "dark_fruit_vine_5");
        blockFlat(ModBlocks.DESERT_SHRUB);
        //blockFlat(MIRE_CORAL);
        blockFlat(ModBlocks.NETTLE);
        blockFlat(ModBlocks.NETTLE_FLOWERED);
        blockFlat(ModBlocks.SWAMP_PLANT);
        blockFlat(ModBlocks.FIRE_BLOOM);
        blockFlat(ModBlocks.FERN);
        blockFlat(ModBlocks.FIDDLE_HEAD);
        blockFlat(ModBlocks.THORNS);
        blockFlat(ModBlocks.ALGAE);
        blockFlat(ModBlocks.HANGING_WEB);
        block(ModBlocks.GIANT_LILY_PAD);

        block(ModBlocks.PETAL_BLACK);
        block(ModBlocks.PETAL_RED);
        block(ModBlocks.PETAL_BROWN);
        block(ModBlocks.PETAL_BLUE);
        block(ModBlocks.PETAL_PURPLE);
        block(ModBlocks.PETAL_CYAN);
        block(ModBlocks.PETAL_LIGHT_GRAY);
        block(ModBlocks.PETAL_GRAY);
        block(ModBlocks.PETAL_PINK);
        block(ModBlocks.PETAL_YELLOW);
        block(ModBlocks.PETAL_LIGHT_BLUE);
        block(ModBlocks.PETAL_MAGENTA);
        block(ModBlocks.PETAL_ORANGE);
        block(ModBlocks.PETAL_WHITE);

        block(ModBlocks.EXPLODING_STIGMA);
        block(ModBlocks.STEM);
        block(ModBlocks.STIGMA_BLACK);
        block(ModBlocks.STIGMA_RED);
        block(ModBlocks.STIGMA_BROWN);
        block(ModBlocks.STIGMA_BLUE);
        block(ModBlocks.STIGMA_PURPLE);
        block(ModBlocks.STIGMA_CYAN);
        block(ModBlocks.STIGMA_LIGHT_GRAY);
        block(ModBlocks.STIGMA_GRAY);
        block(ModBlocks.STIGMA_PINK);
        block(ModBlocks.STIGMA_YELLOW);
        block(ModBlocks.STIGMA_LIGHT_BLUE);
        block(ModBlocks.STIGMA_MAGENTA);
        block(ModBlocks.STIGMA_ORANGE);
        block(ModBlocks.STIGMA_WHITE);

        block(ModBlocks.FLOWER_BLACK);
        block(ModBlocks.FLOWER_RED);
        block(ModBlocks.FLOWER_BROWN);
        block(ModBlocks.FLOWER_BLUE);
        block(ModBlocks.FLOWER_PURPLE);
        block(ModBlocks.FLOWER_CYAN);
        block(ModBlocks.FLOWER_LIGHT_GRAY);
        block(ModBlocks.FLOWER_GRAY);
        block(ModBlocks.FLOWER_PINK);
        block(ModBlocks.FLOWER_YELLOW);
        block(ModBlocks.FLOWER_LIGHT_BLUE);
        block(ModBlocks.FLOWER_MAGENTA);
        block(ModBlocks.FLOWER_ORANGE);
        block(ModBlocks.FLOWER_WHITE);
        block(ModBlocks.FLOWER_RAINBOW);

        blockFlatWithBlockTexture(ModBlocks.BULLRUSH, "bullrush_upper");
        blockFlatWithBlockTexture(ModBlocks.WEEPING_BLUEBELL, "weeping_bluebell_upper");
        blockFlatWithBlockTexture(ModBlocks.SUNDEW, "sundew_upper");
        blockFlatWithBlockTexture(ModBlocks.DROUGHTED_SHRUB, "droughted_shrub_upper");
        blockFlatWithBlockTexture(ModBlocks.TALL_BLOOM, "tall_bloom_upper");
        blockFlatWithBlockTexture(ModBlocks.TANGLED_STALK, "tangled_stalk_upper");
        blockFlatWithBlockTexture(ModBlocks.HIGH_CAPPED_MUSHROOM, "high_capped_mushroom_upper");
        blockFlatWithBlockTexture(ModBlocks.TALL_FERN, "tall_fern_upper");

        hugeMushroom(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK, ModBlocks.DARK_CAPPED_MUSHROOM_STEM);
        hugeMushroom(ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK, ModBlocks.SARCASTIC_CZECH_MUSHROOM_STEM);
        hugeMushroom(ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK, ModBlocks.GRANDMAS_SHOES_MUSHROOM_STEM);
        hugeMushroom(ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK, ModBlocks.DUTCH_CAP_MUSHROOM_STEM);
        hugeMushroom(ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK, ModBlocks.KAIZERS_FINGERS_MUSHROOM_STEM);
        //blockFlatWithBlockTexture(ModBlocks.DARK_CAPPED_MUSHROOM, "mushroom_dark_capped");
        //blockFlatWithBlockTexture(ModBlocks.SARCASTIC_CZECH_MUSHROOM, "mushroom_sarcastic_czech");
        //blockFlatWithBlockTexture(ModBlocks.GRANDMAS_SHOES_MUSHROOM, "mushroom_grandmas_shoes");
        //blockFlatWithBlockTexture(ModBlocks.DUTCH_CAP_MUSHROOM, "mushroom_dutch_cap");
        //blockFlatWithBlockTexture(ModBlocks.KAIZERS_FINGERS_MUSHROOM, "mushroom_kaizers_fingers");
        block(ModBlocks.GLOWSHROOM_BLOCK, "glowshroom");
        block(ModBlocks.GLOWSHROOM_STALK, "glowshroom_stalk_main");

        // MARK: Other
        block(ModBlocks.PORTAL);
        block(ModBlocks.GAEAN_KEYSTONE);
        block(ModBlocks.JADE_BLOCK);
        block(ModBlocks.MUD);
        block(ModBlocks.QUICK_SAND);
        block(ModBlocks.GHOST_SAND);
        block(ModBlocks.SWAMP_VENT);
        block(ModBlocks.GNEISS_VENT);
        block(ModBlocks.RED_GEM_BLOCK);
        block(ModBlocks.RED_GEM_LAMP, "red_gem_lamp_off");
        blockFlatWithBlockTexture(ModBlocks.WITHER_WEB, "wither_web");
        blockFlatWithBlockTexture(ModBlocks.LAVA_WEB, "lava_web");
        block(ModBlocks.GNEISS);
        block(ModBlocks.GNEISS_CARVED);
        block(ModBlocks.GNEISS_RELIEF);
        block(ModBlocks.GNEISS_BRICKS);
        block(ModBlocks.GNEISS_SMOOTH);
        block(ModBlocks.GNEISS_TILES);
        block(ModBlocks.GNEISS_TILES_CRACKED);
        block(ModBlocks.TEMPLE_BRICK);
        block(ModBlocks.TEMPLE_PILLAR);
        block(ModBlocks.TEMPLE_TILE);
        block(ModBlocks.SILK);
        block(ModBlocks.MIR_BRICKS);
        block(ModBlocks.MUD_BRICKS);
        block(ModBlocks.REIN_EXO);
       // block(ModBlocks.VELOCITY_BLOCK);
       // block(ModBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED);
        block(ModBlocks.ANTLION_EGG);
        block(ModBlocks.TARANTULA_EGG);
        block(ModBlocks.HONEY_TREAT);
        block(ModBlocks.CANDLE_HONEY_TREAT);
        block(ModBlocks.WHITE_CANDLE_HONEY_TREAT);
        block(ModBlocks.ORANGE_CANDLE_HONEY_TREAT);
        block(ModBlocks.MAGENTA_CANDLE_HONEY_TREAT);
        block(ModBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT);
        block(ModBlocks.YELLOW_CANDLE_HONEY_TREAT);
        block(ModBlocks.LIME_CANDLE_HONEY_TREAT);
        block(ModBlocks.PINK_CANDLE_HONEY_TREAT);
        block(ModBlocks.GRAY_CANDLE_HONEY_TREAT);
        block(ModBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT);
        block(ModBlocks.CYAN_CANDLE_HONEY_TREAT);
        block(ModBlocks.PURPLE_CANDLE_HONEY_TREAT);
        block(ModBlocks.BLUE_CANDLE_HONEY_TREAT);
        block(ModBlocks.BROWN_CANDLE_HONEY_TREAT);
        block(ModBlocks.GREEN_CANDLE_HONEY_TREAT);
        block(ModBlocks.RED_CANDLE_HONEY_TREAT);
        block(ModBlocks.BLACK_CANDLE_HONEY_TREAT);
        block(ModBlocks.WASP_NEST);
        block(ModBlocks.STAIRS_WASP_NEST);
        blockFlatWithItemTexture(ModBlocks.INSECT_REPELLENT, "repellent");

        // MARK: Spawners
        block(ModBlocks.ANTLION_SPAWNER);
        block(ModBlocks.DRAGON_FLY_SPAWNER);
        block(ModBlocks.JUMPING_SPIDER_SPAWNER);
        block(ModBlocks.SPIDER_SPAWNER);
        block(ModBlocks.TARANTULA_SPAWNER);
        block(ModBlocks.WASP_SPAWNER);
        block(ModBlocks.ZOMBIE_ANT_SPAWNER);
        block(ModBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER);
        block(ModBlocks.MAGMA_CRAWLER_SPAWNER);
        block(ModBlocks.DUNG_SPAWNER_FLY);
        block(ModBlocks.DUNG_SPAWNER_BOT_FLY);
        block(ModBlocks.LOCUST_SPAWNER);

        // MARK: Utility Blocks
        block(ModBlocks.PETRIFIED_CRAFTING_TABLE);
        //block(PETRIFIED_WOOD_CHEST);
        block(ModBlocks.BAMBOO_CRATE, "bamboo_crate_default");
        //block(BAMBOO_BRIDGE);
        block(ModBlocks.BAMBOO_LADDER);
        //block(BAMBOO_NERD_POLE);
        //block(BAMBOO_EXTENDER);
        block(ModBlocks.BAMBOO_TORCH, "bamboo_torch_upper");
        //block(BAMBOO_PIPE);
        //block(BAMBOO_PIPE_EXTRACT);
        //block(BAMBOO_PIPE_EXTRACT_ACTIVE);
        //block(ModBlocks.SILO_ROOF);
        //block(ModBlocks.SILO_TANK);
        //block(ModBlocks.SILO_SUPPORTS);
        //block(HONEY_COMB);
        //block(ModBlocks.COMPOSTER);
        //block(ModBlocks.BLENDER);
        block(ModBlocks.UMBER_FURNACE);
        button(ModBlocks.UMBERSTONE_BUTTON, ModBlocks.UMBERSTONE);
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
        block(ModBlocks.CAPSTONE);
        block(ModBlocks.CAPSTONE_MUD);
        block(ModBlocks.CAPSTONE_IRON);
        block(ModBlocks.CAPSTONE_GOLD);
        block(ModBlocks.CAPSTONE_JADE);
        block(ModBlocks.TEMPLE_BRICK_UNBREAKING);
        block(ModBlocks.TEMPLE_BRICK_UNBREAKING_JADE);
        block(ModBlocks.TEMPLE_BRICK_UNBREAKING_EXO);
        block(ModBlocks.TEMPLE_BRICK_UNBREAKING_CREAM);
        block(ModBlocks.TEMPLE_BRICK_UNBREAKING_EYE);
        block(ModBlocks.TEMPLE_BRICK_UNBREAKING_STRING);
        block(ModBlocks.TEMPLE_TELEPORTER);
        block(ModBlocks.FORCE_FIELD);
        block(ModBlocks.FORCE_LOCK);
        block(ModBlocks.ANT_HILL_BLOCK);
    }
}
