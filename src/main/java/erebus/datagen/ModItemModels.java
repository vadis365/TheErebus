package erebus.datagen;

import erebus.datagen.providers.ModItemModelProvider;
import erebus.registries.ModItems;
import erebus.registries.blocks.providers.*;
import erebus.registries.entity.ModEntities;
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
        block(UmberstoneBlocks.UMBERSTONE_PILLAR);
        block(UmberstoneBlocks.VOLCANIC_ROCK);
        block(UmberstoneBlocks.DUST);
        block(UmberstoneBlocks.DUST_LAYER, "dust");
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

        // MARK: Amber
        block(AmberBlocks.AMBER);
        block(AmberBlocks.AMBER_BRICKS);
        block(AmberBlocks.AMBER_GLASS);
        block(AmberBlocks.PRESERVED_AMBER);
        block(AmberBlocks.PRESERVED_AMBER_GLASS);
        block(AmberBlocks.GLOWING_JAR);
        block(AmberBlocks.FLUID_JAR);
        blockFlatWithItemTexture(AmberBlocks.AMBER_DOOR, "door_amber");

        // MARK: Ores
        block(OreBlocks.ORE_IRON);
        block(OreBlocks.ORE_GOLD);
        block(OreBlocks.ORE_COAL);
        block(OreBlocks.ORE_DIAMOND);
        block(OreBlocks.ORE_EMERALD);
        block(OreBlocks.ORE_LAPIS);
        block(OreBlocks.ORE_QUARTZ);
        block(OreBlocks.ORE_PETRIFIED_QUARTZ);
        block(OreBlocks.ORE_COPPER);
        block(OreBlocks.ORE_SILVER);
        block(OreBlocks.ORE_TIN);
        block(OreBlocks.ORE_LEAD);
        block(OreBlocks.ORE_ALUMINUM);
        block(OreBlocks.ORE_JADE);
        block(OreBlocks.ORE_ENCRUSTED_DIAMOND);
        block(OreBlocks.ORE_FOSSIL);
        block(OreBlocks.ORE_GNEISS);
        block(OreBlocks.ORE_PETRIFIED_WOOD);
        block(OreBlocks.ORE_TEMPLE);

        // MARK: Logs
        block(WoodBlocks.LOG_BAOBAB);
        block(WoodBlocks.LOG_EUCALYPTUS);
        block(WoodBlocks.LOG_MAHOGANY);
        block(WoodBlocks.LOG_MOSSBARK);
        block(WoodBlocks.LOG_ASPER);
        block(WoodBlocks.LOG_CYPRESS);
        block(WoodBlocks.LOG_BALSAM);
        block(WoodBlocks.LOG_BALSAM_RESINLESS);
        block(WoodBlocks.LOG_ROTTEN);
        block(WoodBlocks.LOG_MARSHWOOD);
        block(WoodBlocks.LOG_SCORCHED);

        // MARK: Saplings
        blockFlat(WoodBlocks.SAPLING_BAOBAB);
        blockFlat(WoodBlocks.SAPLING_EUCALYPTUS);
        blockFlat(WoodBlocks.SAPLING_MAHOGANY);
        blockFlat(WoodBlocks.SAPLING_MOSSBARK);
        blockFlat(WoodBlocks.SAPLING_ASPER);
        blockFlat(WoodBlocks.SAPLING_CYPRESS);
        blockFlat(WoodBlocks.SAPLING_BALSAM);
        blockFlat(WoodBlocks.SAPLING_MARSHWOOD);
        blockFlat(WoodBlocks.SAPLING_BAMBOO);

        // MARK: Leaves
        block(WoodBlocks.LEAVES_BAOBAB);
        block(WoodBlocks.LEAVES_EUCALYPTUS);
        block(WoodBlocks.LEAVES_MAHOGANY);
        block(WoodBlocks.LEAVES_MOSSBARK);
        block(WoodBlocks.LEAVES_ASPER);
        block(WoodBlocks.LEAVES_CYPRESS);
        block(WoodBlocks.LEAVES_BALSAM);
        block(WoodBlocks.LEAVES_MARSHWOOD);

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
        slab(SlabBlocks.SLAB_PLANKS_BAOBAB, WoodBlocks.PLANKS_BAOBAB);
        slab(SlabBlocks.SLAB_PLANKS_EUCALYPTUS, WoodBlocks.PLANKS_EUCALYPTUS);
        slab(SlabBlocks.SLAB_PLANKS_MAHOGANY, WoodBlocks.PLANKS_MAHOGANY);
        slab(SlabBlocks.SLAB_PLANKS_MOSSBARK, WoodBlocks.PLANKS_MOSSBARK);
        slab(SlabBlocks.SLAB_PLANKS_ASPER, WoodBlocks.PLANKS_ASPER);
        slab(SlabBlocks.SLAB_PLANKS_CYPRESS, WoodBlocks.PLANKS_CYPRESS);
        slab(SlabBlocks.SLAB_PLANKS_BALSAM, WoodBlocks.PLANKS_BALSAM);
        slab(SlabBlocks.SLAB_PLANKS_WHITE, WoodBlocks.PLANKS_WHITE);
        slab(SlabBlocks.SLAB_PLANKS_BAMBOO, WoodBlocks.PLANKS_BAMBOO);
        slab(SlabBlocks.SLAB_PLANKS_ROTTEN, WoodBlocks.PLANKS_ROTTEN);
        slab(SlabBlocks.SLAB_PLANKS_MARSHWOOD, WoodBlocks.PLANKS_MARSHWOOD);
        slab(SlabBlocks.SLAB_PLANKS_SCORCHED, WoodBlocks.PLANKS_SCORCHED);
        slab(SlabBlocks.SLAB_PLANKS_VARNISHED, WoodBlocks.PLANKS_VARNISHED);
        slab(SlabBlocks.SLAB_PLANKS_PETRIFIED, WoodBlocks.PLANKS_PETRIFIED);

        // MARK: Slabs Stone
        slab(SlabBlocks.SLAB_UMBERSTONE, UmberstoneBlocks.UMBERSTONE);
        slab(SlabBlocks.SLAB_UMBERCOBBLE, UmberstoneBlocks.UMBERCOBBLE);
        slab(SlabBlocks.SLAB_UMBERCOBBLE_MOSSY, UmberstoneBlocks.UMBERCOBBLE_MOSSY);
        slab(SlabBlocks.SLAB_UMBERCOBBLE_WEBBED, UmberstoneBlocks.UMBERCOBBLE_WEBBED);
        slab(SlabBlocks.SLAB_UMBERSTONE_BRICKS, UmberstoneBlocks.UMBERSTONE_BRICKS);
        slab(SlabBlocks.SLAB_UMBERTILE_SMOOTH, UmberstoneBlocks.UMBERTILE_SMOOTH);
        slab(SlabBlocks.SLAB_UMBERTILE_SMOOTH_SMALL, UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL);
        slab(SlabBlocks.SLAB_UMBERPAVER, UmberstoneBlocks.UMBERPAVER);
        slab(SlabBlocks.SLAB_UMBERPAVER_MOSSY, UmberstoneBlocks.UMBERPAVER_MOSSY);
        slab(SlabBlocks.SLAB_UMBERPAVER_WEBBED, UmberstoneBlocks.UMBERPAVER_WEBBED);
        slab(SlabBlocks.SLAB_AMBER, AmberBlocks.AMBER);
        slab(SlabBlocks.SLAB_AMBER_BRICKS, AmberBlocks.AMBER_BRICKS);
        slab(SlabBlocks.SLAB_MIR_BRICKS, UmberstoneBlocks.MIR_BRICKS);
        slab(SlabBlocks.SLAB_MUD_BRICKS, UmberstoneBlocks.MUD_BRICKS);

        // MARK: Stairs Wood
        stairs(StairBlocks.STAIRS_BAOBAB, WoodBlocks.PLANKS_BAOBAB);
        stairs(StairBlocks.STAIRS_EUCALYPTUS, WoodBlocks.PLANKS_EUCALYPTUS);
        stairs(StairBlocks.STAIRS_MAHOGANY, WoodBlocks.PLANKS_MAHOGANY);
        stairs(StairBlocks.STAIRS_MOSSBARK, WoodBlocks.PLANKS_MOSSBARK);
        stairs(StairBlocks.STAIRS_ASPER, WoodBlocks.PLANKS_ASPER);
        stairs(StairBlocks.STAIRS_CYPRESS, WoodBlocks.PLANKS_CYPRESS);
        stairs(StairBlocks.STAIRS_BALSAM, WoodBlocks.PLANKS_BALSAM);
        stairs(StairBlocks.STAIRS_WHITE, WoodBlocks.PLANKS_WHITE);
        stairs(StairBlocks.STAIRS_BAMBOO, WoodBlocks.PLANKS_BAMBOO);
        stairs(StairBlocks.STAIRS_ROTTEN, WoodBlocks.PLANKS_ROTTEN);
        stairs(StairBlocks.STAIRS_MARSHWOOD, WoodBlocks.PLANKS_MARSHWOOD);
        stairs(StairBlocks.STAIRS_SCORCHED, WoodBlocks.PLANKS_SCORCHED);
        stairs(StairBlocks.STAIRS_VARNISHED, WoodBlocks.PLANKS_VARNISHED);
        stairs(StairBlocks.STAIRS_PETRIFIED, WoodBlocks.PLANKS_PETRIFIED);

        // MARK: Stairs
        stairs(StairBlocks.STAIRS_UMBERSTONE, UmberstoneBlocks.UMBERSTONE);
        stairs(StairBlocks.STAIRS_UMBERCOBBLE, UmberstoneBlocks.UMBERCOBBLE);
        stairs(StairBlocks.STAIRS_UMBERCOBBLE_MOSSY, UmberstoneBlocks.UMBERCOBBLE_MOSSY);
        stairs(StairBlocks.STAIRS_UMBERCOBBLE_WEBBED, UmberstoneBlocks.UMBERCOBBLE_WEBBED);
        stairs(StairBlocks.STAIRS_UMBERSTONE_BRICKS, UmberstoneBlocks.UMBERSTONE_BRICKS);
        stairs(StairBlocks.STAIRS_UMBERTILE_SMOOTH, UmberstoneBlocks.UMBERTILE_SMOOTH);
        stairs(StairBlocks.STAIRS_UMBERTILE_SMOOTH_SMALL, UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL);
        stairs(StairBlocks.STAIRS_UMBERPAVER, UmberstoneBlocks.UMBERPAVER);
        stairs(StairBlocks.STAIRS_UMBERPAVER_MOSSY, UmberstoneBlocks.UMBERPAVER_MOSSY);
        stairs(StairBlocks.STAIRS_UMBERPAVER_WEBBED, UmberstoneBlocks.UMBERPAVER_WEBBED);
        stairs(StairBlocks.STAIRS_AMBER, AmberBlocks.AMBER);
        stairs(StairBlocks.STAIRS_AMBER_BRICKS, AmberBlocks.AMBER_BRICKS);
        stairs(StairBlocks.STAIRS_MUD_BRICKS, UmberstoneBlocks.MUD_BRICKS);
        stairs(StairBlocks.STAIRS_MIR_BRICKS, UmberstoneBlocks.MIR_BRICKS);

        // MARK: Doors
        blockFlatWithItemTexture(DoorBlocks.DOOR_BAOBAB, "door_baobab");
        blockFlatWithItemTexture(DoorBlocks.DOOR_EUCALYPTUS, "door_eucalyptus");
        blockFlatWithItemTexture(DoorBlocks.DOOR_MAHOGANY, "door_mahogany");
        blockFlatWithItemTexture(DoorBlocks.DOOR_MOSSBARK, "door_mossbark");
        blockFlatWithItemTexture(DoorBlocks.DOOR_ASPER, "door_asper");
        blockFlatWithItemTexture(DoorBlocks.DOOR_CYPRESS, "door_cypress");
        blockFlatWithItemTexture(DoorBlocks.DOOR_BALSAM, "door_balsam");
        blockFlatWithItemTexture(DoorBlocks.DOOR_WHITE, "door_white");
        blockFlatWithItemTexture(DoorBlocks.DOOR_ROTTEN, "door_rotten");
        blockFlatWithItemTexture(DoorBlocks.DOOR_MARSHWOOD, "door_marshwood");
        blockFlatWithItemTexture(DoorBlocks.DOOR_SCORCHED, "door_scorched");

        // MARK: Fences
        itemFence(FenceBlocks.FENCE_BAOBAB, WoodBlocks.PLANKS_BAOBAB);
        itemFence(FenceBlocks.FENCE_EUCALYPTUS, WoodBlocks.PLANKS_EUCALYPTUS);
        itemFence(FenceBlocks.FENCE_MAHOGANY, WoodBlocks.PLANKS_MAHOGANY);
        itemFence(FenceBlocks.FENCE_MOSSBARK, WoodBlocks.PLANKS_MOSSBARK);
        itemFence(FenceBlocks.FENCE_ASPER, WoodBlocks.PLANKS_ASPER);
        itemFence(FenceBlocks.FENCE_CYPRESS, WoodBlocks.PLANKS_CYPRESS);
        itemFence(FenceBlocks.FENCE_BALSAM, WoodBlocks.PLANKS_BALSAM);
        itemFence(FenceBlocks.FENCE_WHITE, WoodBlocks.PLANKS_WHITE);
        itemFence(FenceBlocks.FENCE_BAMBOO, WoodBlocks.PLANKS_BAMBOO);
        itemFence(FenceBlocks.FENCE_ROTTEN, WoodBlocks.PLANKS_ROTTEN);
        itemFence(FenceBlocks.FENCE_MARSHWOOD, WoodBlocks.PLANKS_MARSHWOOD);
        itemFence(FenceBlocks.FENCE_SCORCHED, WoodBlocks.PLANKS_SCORCHED);
        itemFence(FenceBlocks.FENCE_VARNISHED, WoodBlocks.PLANKS_VARNISHED);

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
        wall(WallBlocks.WALL_AMBER, AmberBlocks.AMBER);
        wall(WallBlocks.WALL_AMBER_BRICKS, AmberBlocks.AMBER_BRICKS);

        // MARK: Plants
        blockFlatWithBlockTexture(PlantBlocks.HEART_BERRY_BUSH, "heart_berry_bush_3");
        blockFlatWithBlockTexture(PlantBlocks.JADE_BERRY_BUSH, "jade_berry_bush_3");
        blockFlatWithBlockTexture(PlantBlocks.SWAMP_BERRY_BUSH, "swamp_berry_bush_3");
        blockFlatWithBlockTexture(PlantBlocks.DARK_FRUIT_VINE, "dark_fruit_vine_5");
        blockFlat(PlantBlocks.DESERT_SHRUB);
        //blockFlat(MIRE_CORAL);
        blockFlat(PlantBlocks.NETTLE);
        blockFlat(PlantBlocks.NETTLE_FLOWERED);
        blockFlat(PlantBlocks.SWAMP_PLANT);
        blockFlat(PlantBlocks.FIRE_BLOOM);
        blockFlat(PlantBlocks.FERN);
        blockFlat(PlantBlocks.FIDDLE_HEAD);
        blockFlat(PlantBlocks.THORNS);
        blockFlat(PlantBlocks.ALGAE);
        blockFlat(PlantBlocks.HANGING_WEB);
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

        block(PlantBlocks.EXPLODING_STIGMA);
        block(PlantBlocks.STEM);
        block(PlantBlocks.STIGMA_BLACK);
        block(PlantBlocks.STIGMA_RED);
        block(PlantBlocks.STIGMA_BROWN);
        block(PlantBlocks.STIGMA_BLUE);
        block(PlantBlocks.STIGMA_PURPLE);
        block(PlantBlocks.STIGMA_CYAN);
        block(PlantBlocks.STIGMA_LIGHT_GRAY);
        block(PlantBlocks.STIGMA_GRAY);
        block(PlantBlocks.STIGMA_PINK);
        block(PlantBlocks.STIGMA_YELLOW);
        block(PlantBlocks.STIGMA_LIGHT_BLUE);
        block(PlantBlocks.STIGMA_MAGENTA);
        block(PlantBlocks.STIGMA_ORANGE);
        block(PlantBlocks.STIGMA_WHITE);

        block(PlantBlocks.FLOWER_BLACK);
        block(PlantBlocks.FLOWER_RED);
        block(PlantBlocks.FLOWER_BROWN);
        block(PlantBlocks.FLOWER_BLUE);
        block(PlantBlocks.FLOWER_PURPLE);
        block(PlantBlocks.FLOWER_CYAN);
        block(PlantBlocks.FLOWER_LIGHT_GRAY);
        block(PlantBlocks.FLOWER_GRAY);
        block(PlantBlocks.FLOWER_PINK);
        block(PlantBlocks.FLOWER_YELLOW);
        block(PlantBlocks.FLOWER_LIGHT_BLUE);
        block(PlantBlocks.FLOWER_MAGENTA);
        block(PlantBlocks.FLOWER_ORANGE);
        block(PlantBlocks.FLOWER_WHITE);
        block(PlantBlocks.FLOWER_RAINBOW);

        blockFlatWithBlockTexture(PlantBlocks.BULLRUSH, "bullrush_upper");
        blockFlatWithBlockTexture(PlantBlocks.WEEPING_BLUEBELL, "weeping_bluebell_upper");
        blockFlatWithBlockTexture(PlantBlocks.SUNDEW, "sundew_upper");
        blockFlatWithBlockTexture(PlantBlocks.DROUGHTED_SHRUB, "droughted_shrub_upper");
        blockFlatWithBlockTexture(PlantBlocks.TALL_BLOOM, "tall_bloom_upper");
        blockFlatWithBlockTexture(PlantBlocks.TANGLED_STALK, "tangled_stalk_upper");
        blockFlatWithBlockTexture(PlantBlocks.HIGH_CAPPED_MUSHROOM, "high_capped_mushroom_upper");
        blockFlatWithBlockTexture(PlantBlocks.TALL_FERN, "tall_fern_upper");

        hugeMushroom(PlantBlocks.DARK_CAPPED_MUSHROOM_BLOCK, PlantBlocks.DARK_CAPPED_MUSHROOM_STEM);
        hugeMushroom(PlantBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK, PlantBlocks.SARCASTIC_CZECH_MUSHROOM_STEM);
        hugeMushroom(PlantBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK, PlantBlocks.GRANDMAS_SHOES_MUSHROOM_STEM);
        hugeMushroom(PlantBlocks.DUTCH_CAP_MUSHROOM_BLOCK, PlantBlocks.DUTCH_CAP_MUSHROOM_STEM);
        hugeMushroom(PlantBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK, PlantBlocks.KAIZERS_FINGERS_MUSHROOM_STEM);
        //blockFlatWithBlockTexture(PlantBlocks.DARK_CAPPED_MUSHROOM, "mushroom_dark_capped");
        blockFlatWithBlockTexture(PlantBlocks.SARCASTIC_CZECH_MUSHROOM, "mushroom_sarcastic_czech");
        blockFlatWithBlockTexture(PlantBlocks.GRANDMAS_SHOES_MUSHROOM, "mushroom_grandmas_shoes");
        //blockFlatWithBlockTexture(PlantBlocks.DUTCH_CAP_MUSHROOM, "mushroom_dutch_cap");
        blockFlatWithBlockTexture(PlantBlocks.KAIZERS_FINGERS_MUSHROOM, "mushroom_kaizers_fingers");
        block(PlantBlocks.GLOWSHROOM_BLOCK, "glowshroom");
        block(PlantBlocks.GLOWSHROOM_STALK, "glowshroom_stalk_main");

        // MARK: Other
        block(OtherBlocks.PORTAL);
        block(OtherBlocks.GAEAN_KEYSTONE);
        block(OtherBlocks.JADE_BLOCK);
        block(OtherBlocks.MUD);
        block(OtherBlocks.QUICK_SAND);
        block(OtherBlocks.GHOST_SAND);
        block(OtherBlocks.SWAMP_VENT);
        block(OtherBlocks.GNEISS_VENT);
        block(OtherBlocks.RED_GEM_BLOCK);
        block(OtherBlocks.RED_GEM_LAMP, "red_gem_lamp_off");
        blockFlatWithBlockTexture(OtherBlocks.WITHER_WEB, "wither_web");
        blockFlatWithBlockTexture(OtherBlocks.LAVA_WEB, "lava_web");
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
        block(UmberstoneBlocks.MIR_BRICKS);
        block(UmberstoneBlocks.MUD_BRICKS);
        block(OtherBlocks.REIN_EXO);
        block(OtherBlocks.VELOCITY);
        block(OtherBlocks.LIGHTNING_SPEED);
        block(OtherBlocks.ANTLION_EGG);
        block(OtherBlocks.TARANTULA_EGG);
        block(OtherBlocks.HONEY_TREAT);
        block(OtherBlocks.CANDLE_HONEY_TREAT);
        block(OtherBlocks.WHITE_CANDLE_HONEY_TREAT);
        block(OtherBlocks.ORANGE_CANDLE_HONEY_TREAT);
        block(OtherBlocks.MAGENTA_CANDLE_HONEY_TREAT);
        block(OtherBlocks.LIGHT_BLUE_CANDLE_HONEY_TREAT);
        block(OtherBlocks.YELLOW_CANDLE_HONEY_TREAT);
        block(OtherBlocks.LIME_CANDLE_HONEY_TREAT);
        block(OtherBlocks.PINK_CANDLE_HONEY_TREAT);
        block(OtherBlocks.GRAY_CANDLE_HONEY_TREAT);
        block(OtherBlocks.LIGHT_GRAY_CANDLE_HONEY_TREAT);
        block(OtherBlocks.CYAN_CANDLE_HONEY_TREAT);
        block(OtherBlocks.PURPLE_CANDLE_HONEY_TREAT);
        block(OtherBlocks.BLUE_CANDLE_HONEY_TREAT);
        block(OtherBlocks.BROWN_CANDLE_HONEY_TREAT);
        block(OtherBlocks.GREEN_CANDLE_HONEY_TREAT);
        block(OtherBlocks.RED_CANDLE_HONEY_TREAT);
        block(OtherBlocks.BLACK_CANDLE_HONEY_TREAT);
        block(OtherBlocks.WASP_NEST);
        block(OtherBlocks.STAIRS_WASP_NEST);
        blockFlatWithItemTexture(OtherBlocks.INSECT_REPELLENT, "repellent");

        // MARK: Spawners
        block(OtherBlocks.ANTLION_SPAWNER);
        block(OtherBlocks.DRAGON_FLY_SPAWNER);
        block(OtherBlocks.JUMPING_SPIDER_SPAWNER);
        block(OtherBlocks.SPIDER_SPAWNER);
        block(OtherBlocks.TARANTULA_SPAWNER);
        block(OtherBlocks.WASP_SPAWNER);
        block(OtherBlocks.ZOMBIE_ANT_SPAWNER);
        block(OtherBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER);
        block(OtherBlocks.MAGMA_CRAWLER_SPAWNER);
        block(OtherBlocks.DUNG_SPAWNER_FLY);
        block(OtherBlocks.DUNG_SPAWNER_BOT_FLY);
        block(OtherBlocks.LOCUST_SPAWNER);

        // MARK: Utility Blocks
        block(OtherBlocks.PETRIFIED_CRAFTING_TABLE);
        //block(PETRIFIED_WOOD_CHEST);
        block(OtherBlocks.BAMBOO_CRATE, "bamboo_crate_default");
        //block(BAMBOO_BRIDGE);
        block(OtherBlocks.BAMBOO_LADDER);
        //block(BAMBOO_NERD_POLE);
        //block(BAMBOO_EXTENDER);
        block(OtherBlocks.BAMBOO_TORCH, "bamboo_torch_upper");
        //block(BAMBOO_PIPE);
        //block(BAMBOO_PIPE_EXTRACT);
        //block(BAMBOO_PIPE_EXTRACT_ACTIVE);
        block(OtherBlocks.SILO_ROOF);
        block(OtherBlocks.SILO_TANK);
        block(OtherBlocks.SILO_SUPPORTS);
        //block(HONEY_COMB);
        block(OtherBlocks.COMPOSTER);
        block(OtherBlocks.BLENDER);
        block(OtherBlocks.UMBER_FURNACE);
        button(OtherBlocks.UMBERSTONE_BUTTON, UmberstoneBlocks.UMBERSTONE);
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
        block(OtherBlocks.CAPSTONE);
        block(OtherBlocks.CAPSTONE_MUD);
        block(OtherBlocks.CAPSTONE_IRON);
        block(OtherBlocks.CAPSTONE_GOLD);
        block(OtherBlocks.CAPSTONE_JADE);
        block(OtherBlocks.TEMPLE_BRICK_UNBREAKING);
        block(OtherBlocks.TEMPLE_BRICK_UNBREAKING_JADE);
        block(OtherBlocks.TEMPLE_BRICK_UNBREAKING_EXO);
        block(OtherBlocks.TEMPLE_BRICK_UNBREAKING_CREAM);
        block(OtherBlocks.TEMPLE_BRICK_UNBREAKING_EYE);
        block(OtherBlocks.TEMPLE_BRICK_UNBREAKING_STRING);
        block(OtherBlocks.TEMPLE_TELEPORTER);
        block(OtherBlocks.FORCE_FIELD);
        block(OtherBlocks.FORCE_LOCK);
        block(OtherBlocks.ANT_HILL_BLOCK);
    }
}
