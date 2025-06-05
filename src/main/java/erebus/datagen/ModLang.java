package erebus.datagen;

import erebus.datagen.providers.ModLangProvider;
import erebus.registries.ModItems;
import erebus.registries.blocks.providers.AmberBlocks;
import erebus.registries.blocks.providers.DoorBlocks;
import erebus.registries.blocks.providers.FenceBlocks;
import erebus.registries.blocks.providers.OreBlocks;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.PlantBlocks;
import erebus.registries.blocks.providers.SlabBlocks;
import erebus.registries.blocks.providers.StairBlocks;
import erebus.registries.blocks.providers.UmberstoneBlocks;
import erebus.registries.blocks.providers.WallBlocks;
import erebus.registries.blocks.providers.WoodBlocks;
import erebus.registries.entity.ModEntities;
import net.minecraft.data.PackOutput;

public class ModLang extends ModLangProvider {
    public ModLang(PackOutput output) {
        super(output);
    }

    @Override
    protected void addTranslations() {
        addItemTranslations();
        addBlockTranslations();
        addEntityTranslations();
        addFluidTranslations();
        addCreativeTabTranslations();
        addToolTipTranslations();
        addBookTranslations();
        addAdvancementTranslations();
        addBiomeTranslations();
        addContainerTranslations();
    }

    private void addItemTranslations() {
        // MARK: Materials
        addItem(ModItems.PLATE_EXO, "Exoskeleton Plate");
        addItem(ModItems.JADE, "Jade");
        addItem(ModItems.SHARD_BONE, "Bone Shard");
        addItem(ModItems.BAMBOO, "Bamboo");
        addItem(ModItems.COMPOUND_EYES, "Compound Eyes");
        addItem(ModItems.COMPOUND_LENS, "Compound Goggle Lens");
        addItem(ModItems.FLY_WING, "Fly Wing");
        addItem(ModItems.PETRIFIED_WOOD, "Petrified Wood");
        addItem(ModItems.BIO_VELOCITY, "Bio Velocity");
        addItem(ModItems.ELASTIC_FIBER, "Elastic Fiber");
        addItem(ModItems.WASP_STING,"Wasp Sting");
        addItem(ModItems.BAMBOO_SHOOT, "Colossal Bamboo Shoot");
        addItem(ModItems.RED_GEM, "Redgem");
        addItem(ModItems.BIO_LUMINESCENCE, "Bioluminescence");
        addItem(ModItems.SUPERNATURAL_VELOCITY, "Supernatural Velocity");
        addItem(ModItems.ALTAR_FRAGMENT, "Altar Fragment");
        addItem(ModItems.REINFORCED_PLATE_EXO, "Reinforced Exoskeleton Plate");
        addItem(ModItems.GLIDER_WING, "Glider Wing");
        addItem(ModItems.SCORPION_PINCER, "Scorpion Pincer");
        addItem(ModItems.CAMO_POWDER, "Camouflage Powder");
        addItem(ModItems.NECTAR, "Nectar");
        addItem(ModItems.HONEY_DRIP, "Honey Drip");
        addItem(ModItems.POISON_GLAND, "Poison Gland");
        addItem(ModItems.MUD_BRICK, "Mud Brick");
        addItem(ModItems.WHETSTONE_POWDER, "Whetstone Powder");
        addItem(ModItems.DRAGONFLY_WING, "Dragonfly Wing");
        addItem(ModItems.BLUEBELL_PETAL, "Bluebell Petal");
        addItem(ModItems.PAPYRUS, "Papyrus");
        addItem(ModItems.ENHANCED_GLIDER_WING, "Enhanced Glider Wing");
        addItem(ModItems.REPELLENT, "Repellent");
        addItem(ModItems.MUCUS_CHARGE, "Mucus Charge");
        addItem(ModItems.NETTLE_LEAVES, "Nettle Leaves");
        addItem(ModItems.NETTLE_FLOWERS, "Nettle Flowers");
        addItem(ModItems.DARK_FRUIT_SEEDS, "Dark Fruit Seeds");
        addItem(ModItems.MOSS_BALL, "Moss Ball");
        addItem(ModItems.GLOWSHROOM, "Glowshroom");
        addItem(ModItems.PLATE_EXO_RHINO, "Rhino Beetle Plate");
        addItem(ModItems.RHINO_BEETLE_HORN, "Rhino Beetle Horn");
        addItem(ModItems.ANT_PHEROMONES, "Ant Pheromones");
        addItem(ModItems.GAEAN_GEM, "Gaean Gem");
        addItem(ModItems.CRIMSON_HEART, "Crimson Heart");
        addItem(ModItems.RESIN, "Tree Resin");
        addItem(ModItems.AMBER_STAR, "Amber Star");
        addItem(ModItems.INGOT_ALUMINUM, "Aluminum Ingot");
        addItem(ModItems.INGOT_LEAD, "Lead Ingot");
        addItem(ModItems.INGOT_SILVER, "Silver Ingot");
        addItem(ModItems.INGOT_TIN, "Tin Ingot");
        addItem(ModItems.GNEISS_ROCK, "Gneiss Rock");
        addItem(ModItems.HIDE_SHROOM, "Mushroom Hide");
        addItem(ModItems.BEETLE_RIDING_KIT, "Beetle Riding Kit");
        addItem(ModItems.BEETLE_TAMING_AMULET, "Beetle Amulet");
        addItem(ModItems.UMBERGOLEM_CORE, "Umbergolem Torso");
        addItem(ModItems.UMBERGOLEM_HEAD, "Umbergolem Head");
        addItem(ModItems.UMBERGOLEM_CLAW, "Umbergolem Claw");
        addItem(ModItems.UMBERGOLEM_LEGS, "Umbergolem Legs");
        addItem(ModItems.JADE_BERRIES, "Jadeberries");
        addItem(ModItems.BOGMAW_ROOT, "Bogmaw Root");
        addItem(ModItems.HYDROFUGE, "Hydrofuge");
        addItem(ModItems.WATER_REPELLENT, "Water Repellent");
        addItem(ModItems.SMOOTHIE_GLASS, "Empty Smoothie Glass");
        addItem(ModItems.MAGMA_CRAWLER_EYE, "Magma Crawler Eye");
        addItem(ModItems.STEW_POT, "Stew Pot");
        addItem(ModItems.TITAN_STEW, "Un-cooked Titan Stew");
        addItem(ModItems.FORCE_KEY, "Force Key");
        addItem(ModItems.SOUL_CRYSTAL, "Soul Crystal");
        addItem(ModItems.PLATE_ZOMBIE_ANT, "Zombie Ant Plate");
        addItem(ModItems.STAG_BEETLE_MANDIBLES, "Stag Beetle Mandibles");
        addItem(ModItems.TERPSISHROOM, "Terpsishroom");
        addItem(ModItems.BAMBOO_PIPE_WRENCH, "Bamboo Pipe Wrench");
        addItem(ModItems.TEMPLE_ROCK, "Temple Rock");

        // MARK: Food
        addItem(ModItems.BEETLE_LARVA_RAW, "Beetle Larva");
        addItem(ModItems.BEETLE_LARVA_COOKED, "Cooked Beetle Larva");
        addItem(ModItems.GRASSHOPPER_LEG_RAW, "Grasshopper Leg");
        addItem(ModItems.GRASSHOPPER_LEG_COOKED, "Cooked Grasshopper Leg");
        addItem(ModItems.TARANTULA_LEG_RAW, "Tarantula Leg");
        addItem(ModItems.TARANTULA_LEG_COOKED, "Crispy Tarantula Leg");
        addItem(ModItems.BAMBOO_SOUP, "Bamboo Soup");
        addItem(ModItems.MELONADE, "Melonade");
        addItem(ModItems.MELONADE_SPARKLY, "Sparkly Melonade");
        addItem(ModItems.LARVAE_ON_STICK, "Larvae-on-a-Stick");
        addItem(ModItems.HONEY_SANDWICH, "Honey Sandwich");
        addItem(ModItems.DARK_FRUIT, "Dark Fruit");
        addItem(ModItems.TITAN_CHOP_RAW, "Raw Titan Chop");
        addItem(ModItems.TITAN_CHOP_COOKED, "Cooked Titan Chop");
        addItem(ModItems.SWAMP_BERRIES, "Swampberries");
        addItem(ModItems.CABBAGE, "Cabbage");
        addItem(ModItems.TITAN_STEW_COOKED, "Cooked Titan Stew");
        addItem(ModItems.PRICKLY_PEAR_RAW, "Raw Prickly Pear");
        addItem(ModItems.PRICKLY_PEAR_COOKED, "Cooked Prickly Pear");
        addItem(ModItems.DARK_FRUIT_PIE, "Dark Fruit Pie");

        // MARK: Smoothies
        addItem(ModItems.GREEN_TEA_GRASSHOPPER, "Green Tea Grasshopper Smoothie");
        addItem(ModItems.MONEY_HONEY, "Money Honey Smoothie");
        addItem(ModItems.NOTHING_IN_THE_MIDDLE, "Nothing In The Middle Smoothie");
        addItem(ModItems.GREEN_GIANT, "Green Giant Smoothie");
        addItem(ModItems.SEEDY_GOODNESS, "Seedy Goodness Smoothie");
        addItem(ModItems.GIVIN_ME_THE_BLUES, "Givin' Me The Blues Smoothie");
        addItem(ModItems.HOT_HOT_BABY, "Hot Hot Baby Smoothie");
        addItem(ModItems.DONT_MEDDLE_WITH_THE_NETTLE, "Don't Meddle With The Nettle Smoothie");
        addItem(ModItems.LIQUID_GOLD, "Liquid Gold Smoothie");
        addItem(ModItems.BRYUFS_BREW, "Bryuf's Brew Smoothie");

        // MARK: Armor, Weapons and Tools
        addItem(ModItems.BAMBOO_HELMET, "Bamboo Helmet");
        addItem(ModItems.BAMBOO_CHESTPLATE, "Bamboo Chestplate");
        addItem(ModItems.BAMBOO_LEGGINGS, "Bamboo Leggings");
        addItem(ModItems.BAMBOO_BOOTS, "Bamboo Boots");
        addItem(ModItems.EXOSKELETON_HELMET, "Exoskeleton Helmet");
        addItem(ModItems.EXOSKELETON_CHESTPLATE, "Exoskeleton Chestplate");
        addItem(ModItems.EXOSKELETON_LEGGINGS, "Exoskeleton Leggings");
        addItem(ModItems.EXOSKELETON_BOOTS, "Exoskeleton Boots");
        addItem(ModItems.REIN_EXOSKELETON_HELMET, "Reinforced Exoskeleton Helmet");
        addItem(ModItems.REIN_EXOSKELETON_CHESTPLATE, "Reinforced Exoskeleton Chestplate");
        addItem(ModItems.REIN_EXOSKELETON_LEGGINGS, "Reinforced Exoskeleton Leggings");
        addItem(ModItems.REIN_EXOSKELETON_BOOTS, "Reinforced Exoskeleton Boots");
        addItem(ModItems.RHINO_EXOSKELETON_HELMET, "Rhino Plate Helmet");
        addItem(ModItems.RHINO_EXOSKELETON_CHESTPLATE, "Rhino Plate Chestplate");
        addItem(ModItems.RHINO_EXOSKELETON_LEGGINGS, "Rhino Plate Leggings");
        addItem(ModItems.RHINO_EXOSKELETON_BOOTS, "Rhino Plate Boots");
        addItem(ModItems.JADE_HELMET, "Jade Helmet");
        addItem(ModItems.JADE_CHESTPLATE, "Jade Chestplate");
        addItem(ModItems.JADE_LEGGINGS, "Jade Leggings");
        addItem(ModItems.JADE_BOOTS, "Jade Boots");
        addItem(ModItems.JADE_SWORD, "Jade Sword");
        addItem(ModItems.JADE_PICKAXE, "Jade Pickaxe");
        addItem(ModItems.JADE_AXE, "Jade Axe");
        addItem(ModItems.JADE_SHOVEL, "Jade Shovel");
        addItem(ModItems.JADE_PAXEL, "Jade Paxel");
        addItem(ModItems.JADE_HOE, "Jade Hoe");
        addItem(ModItems.COMPOUND_GOGGLES, "Compound Goggles");
        addItem(ModItems.REIN_COMPOUND_GOGGLES, "Reinforced Compound Goggles");
        addItem(ModItems.JUMP_BOOTS, "Jump Boots");
        addItem(ModItems.SPRINT_LEGGINGS, "Sprint Leggings");
        addItem(ModItems.SPIDER_T_SHIRT, "Tarantula Boss Trophy");
        addItem(ModItems.MUSHROOM_HELMET, "Mush Helm");
        addItem(ModItems.WATER_STRIDERS, "Water Striders");
        addItem(ModItems.GLIDER_CHESTPLATE, "Glider Chestplate");
        addItem(ModItems.GLIDER_CHESTPLATE_POWERED, "Powered Glider Chestplate");
        addItem(ModItems.ENHANCED_SCORPION_PINCER, "Augmented Scorpion Pincer");
        addItem(ModItems.WAR_HAMMER, "Quake Hammer");
        addItem(ModItems.WEB_SLINGER, "Web Slinger");
        addItem(ModItems.WEB_SLINGER_WITHER, "Wither Web Slinger");
        addItem(ModItems.MAX_SPEED_BOW, "Maximum Speed Bow");
        addItem(ModItems.BAMBOO_SHIELD, "Bamboo Shield");
        addItem(ModItems.EXOSKELETON_SHIELD, "Exoskeleton Shield");
        addItem(ModItems.JADE_SHIELD, "Jade Shield");
        addItem(ModItems.REIN_EXOSKELETON_SHIELD, "Reinforced Exoskeleton Shield");
        addItem(ModItems.RHINO_EXOSKELETON_SHIELD, "Rhino Plate Shield");

        // MARK: Misc
        addItem(ModItems.WAND_OF_ANIMATION, "Wand of Animation");
        addItem(ModItems.WAND_OF_PRESERVATION, "Wand of Preservation");
        addItem(ModItems.PORTAL_ACTIVATOR, "Staff of Gaea");
        addItem(ModItems.WOODLOUSE_BALL, "Woodlouse Ball");
        addItem(ModItems.NECTAR_COLLECTOR, "Nectar Collector");
        addItem(ModItems.ANT_TAMING_AMULET, "Ant Taming Amulet");
        addItem(ModItems.BEE_TAMING_AMULET, "Bee Taming Amulet");
        addItem(ModItems.WASP_SWORD, "Wasp Sword");
        addItem(ModItems.WASP_DAGGER, "Wasp Dagger");
        addItem(ModItems.ANTI_VENOM_BOTTLE, "Bottle of Anti-Venom");
        addItem(ModItems.DEATH_COMPASS, "Death Compass");
        addItem(ModItems.ROLLED_NEWSPAPER, "Rolled-up Newspaper");
        addItem(ModItems.BAMBUCKET, "Bambucket");
        addItem(ModItems.HOMING_BEECON, "Homing Beecon");
        addItem(ModItems.HOMING_BEECON_ADVANCED, "Advanced Homing Beecon");
        addItem(ModItems.SPRAY_CAN, "Insect Repellent");
        addItem(ModItems.WHETSTONE, "Whetstone");
        addItem(ModItems.COMPOST, "Compost");
        addItem(ModItems.PLANTICIDE, "Planticide");
        addItem(ModItems.SMOOTHIE_BOOK, "Smoothie-matic 2000");
        addItem(ModItems.HORN_OF_SUMMONING, "Horn of Bee Summoning");
        addItem(ModItems.MUD_SCARAB, "Mud Scarab");
        addItem(ModItems.IRON_SCARAB, "Iron Scarab");
        addItem(ModItems.GOLD_SCARAB, "Gold Scarab");
        addItem(ModItems.JADE_SCARAB, "Jade Scarab");
        addItem(ModItems.MUD_UMBERGOLEM, "Spawn Mud Umbergolem");
        addItem(ModItems.IRON_UMBERGOLEM, "Spawn Iron Umbergolem");
        addItem(ModItems.GOLD_UMBERGOLEM, "Spawn Gold Umbergolem");
        addItem(ModItems.JADE_UMBERGOLEM, "Spawn Jade Umbergolem");
        addItem(ModItems.EREBUS_MAP, "Empty Papyrus Map");
        addItem(ModItems.EREBUS_MAP_FILLED, "Papyrus Map");

        // MARK: Plants
        addItem(ModItems.TURNIP, "Turnips");
        addItem(ModItems.CABBAGE_SEEDS, "Cabbage Seeds");
        addItem(ModItems.MANDRAKE_ROOT, "Mandrake Root");
        addItem(ModItems.SEED_BLACK, "Black Flower Seeds");
        addItem(ModItems.SEED_RED, "Red Flower Seeds");
        addItem(ModItems.SEED_BROWN, "Brown Flower Seeds");
        addItem(ModItems.SEED_BLUE, "Blue Flower Seeds");
        addItem(ModItems.SEED_PURPLE, "Purple Flower Seeds");
        addItem(ModItems.SEED_CYAN, "Cyan Flower Seeds");
        addItem(ModItems.SEED_LIGHT_GRAY, "Light Gray Flower Seeds");
        addItem(ModItems.SEED_GRAY, "Gray Flower Seeds");
        addItem(ModItems.SEED_PINK, "Pink Flower Seeds");
        addItem(ModItems.SEED_YELLOW, "Yellow Flower Seeds");
        addItem(ModItems.SEED_LIGHT_BLUE, "Light Blue Flower Seeds");
        addItem(ModItems.SEED_MAGENTA, "Magenta Flower Seeds");
        addItem(ModItems.SEED_ORANGE, "Orange Flower Seeds");
        addItem(ModItems.SEED_WHITE, "White Flower Seeds");
        addItem(ModItems.SEED_RAINBOW, "Rainbow Flower Seeds");
        addItem(ModItems.LIFE_BLOOD, "Life Blood");
        addItem(ModItems.HEART_BERRIES, "Heart Berries");
        addItem(ModItems.STAG_HEART_RAW, "Heart of the Stag");
        addItem(ModItems.STAG_HEART_COOKED, "Roasted Heart of the Stag");
    }

    private void addBlockTranslations() {
        // MARK: Umberstone
        addBlock(UmberstoneBlocks.UMBERSTONE, "Umberstone");
        addBlock(UmberstoneBlocks.UMBERCOBBLE, "Umbercobble");
        addBlock(UmberstoneBlocks.UMBERCOBBLE_MOSSY, "Mossy Umbercobble");
        addBlock(UmberstoneBlocks.UMBERCOBBLE_WEBBED, "Webbed Umbercobble");
        addBlock(UmberstoneBlocks.UMBERSTONE_BRICKS, "Umberbricks");
        addBlock(UmberstoneBlocks.UMBERTILE_SMOOTH, "Smooth Umbertile");
        addBlock(UmberstoneBlocks.UMBERTILE_SMOOTH_SMALL, "Small Umbertiles");
        addBlock(UmberstoneBlocks.UMBERGRAVEL, "Umberpebbles");
        addBlock(UmberstoneBlocks.UMBERPAVER, "Umberpaver");
        addBlock(UmberstoneBlocks.UMBERPAVER_MOSSY, "Mossy Umberpaver");
        addBlock(UmberstoneBlocks.UMBERPAVER_WEBBED, "Webbed Umberpaver");
        addBlock(UmberstoneBlocks.UMBERSTONE_PILLAR, "Umberstone Pillar");
        addBlock(UmberstoneBlocks.VOLCANIC_ROCK, "Volcanic Rock");
        addBlock(UmberstoneBlocks.DUST, "Dust");
        addBlock(UmberstoneBlocks.DUST_LAYER, "Dust Layer");
        addBlock(UmberstoneBlocks.PETRIFIED_WOOD_ROCK, "Petrified Wood Rock");
        addBlock(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_2, "Petrified Wood Rock");
        addBlock(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_3, "Petrified Wood Rock");
        addBlock(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_4, "Petrified Wood Rock");
        addBlock(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_5, "Petrified Wood Rock");
        addBlock(UmberstoneBlocks.PETRIFIED_WOOD_ROCK_6, "Petrified Wood Rock");
        addBlock(UmberstoneBlocks.PETRIFIED_BARK_RED, "Red Petrified Bark");
        addBlock(UmberstoneBlocks.PETRIFIED_BARK_BROWN, "Brown Petrified Bark");
        addBlock(UmberstoneBlocks.DUNG, "Dung");
        addBlock(UmberstoneBlocks.MIR_BRICKS, "Mirbrick");
        addBlock(UmberstoneBlocks.MUD_BRICKS, "Mud Brick");

        // MARK: Amber
        addBlock(AmberBlocks.AMBER, "Amber");
        addBlock(AmberBlocks.AMBER_BRICKS, "Amber Bricks");
        addBlock(AmberBlocks.AMBER_GLASS, "Amber Glass");
        addBlock(AmberBlocks.PRESERVED_AMBER, "Amber Preservation Block");
        addBlock(AmberBlocks.PRESERVED_AMBER_GLASS, "Amber Preservation Block");
        addBlock(AmberBlocks.GLOWING_JAR, "Glowing Jar");
        addBlock(AmberBlocks.FLUID_JAR, "Fluid Jar");
        addBlock(AmberBlocks.AMBER_DOOR, "Amber Door");

        // MARK: Ores
        addBlock(OreBlocks.ORE_IRON, "Iron Ore");
        addBlock(OreBlocks.ORE_GOLD, "Gold Ore");
        addBlock(OreBlocks.ORE_DIAMOND, "Diamond Ore");
        addBlock(OreBlocks.ORE_EMERALD, "Emerald Ore");
        addBlock(OreBlocks.ORE_LAPIS, "Lapis Lazuli Ore");
        addBlock(OreBlocks.ORE_QUARTZ, "Quartz Ore");
        addBlock(OreBlocks.ORE_PETRIFIED_QUARTZ, "Petrified Quartz Ore");
        addBlock(OreBlocks.ORE_COAL, "Coal Ore");
        addBlock(OreBlocks.ORE_COPPER, "Copper Ore");
        addBlock(OreBlocks.ORE_TIN, "Tin Ore");
        addBlock(OreBlocks.ORE_SILVER, "Silver Ore");
        addBlock(OreBlocks.ORE_ALUMINUM, "Aluminum Ore");
        addBlock(OreBlocks.ORE_LEAD, "Lead Ore");
        addBlock(OreBlocks.ORE_JADE, "Jade Ore");
        addBlock(OreBlocks.ORE_ENCRUSTED_DIAMOND, "Volcanic Diamond Ore");
        addBlock(OreBlocks.ORE_FOSSIL, "Fossil Ore");
        addBlock(OreBlocks.ORE_GNEISS, "Gneiss Ore");
        addBlock(OreBlocks.ORE_TEMPLE, "Temple Ore");
        addBlock(OreBlocks.ORE_PETRIFIED_WOOD, "Petrified Wood Ore");

        // MARK: Logs
        addBlock(WoodBlocks.LOG_BAOBAB, "Baobab Wood");
        addBlock(WoodBlocks.LOG_EUCALYPTUS, "Eucalyptus Wood");
        addBlock(WoodBlocks.LOG_MAHOGANY, "Mahogany Wood");
        addBlock(WoodBlocks.LOG_MOSSBARK, "Mossbark Wood");
        addBlock(WoodBlocks.LOG_ASPER, "Asper Wood");
        addBlock(WoodBlocks.LOG_CYPRESS, "Cypress Wood");
        addBlock(WoodBlocks.LOG_BALSAM, "Balsam Wood");
        addBlock(WoodBlocks.LOG_BALSAM_RESINLESS, "Balsam Wood Resinless");
        addBlock(WoodBlocks.LOG_ROTTEN, "Rotten Wood");
        addBlock(WoodBlocks.LOG_MARSHWOOD, "Marshwood Wood");
        addBlock(WoodBlocks.LOG_SCORCHED, "Scorched Wood");
        addBlock(WoodBlocks.LOG_HOLLOW, "Hollow Acacia Log");

        // MARK: Saplings
        addBlock(WoodBlocks.SAPLING_MOSSBARK, "Mossbark Sapling");
        addBlock(WoodBlocks.SAPLING_ASPER, "Asper Sapling");
        addBlock(WoodBlocks.SAPLING_EUCALYPTUS, "Eucalyptus Sapling");
        addBlock(WoodBlocks.SAPLING_MAHOGANY, "Mahogany Sapling");
        addBlock(WoodBlocks.SAPLING_BALSAM, "Balsam Sapling");
        addBlock(WoodBlocks.SAPLING_BAOBAB, "Baobab Sapling");
        addBlock(WoodBlocks.SAPLING_MARSHWOOD, "Marshwood Sapling");
        addBlock(WoodBlocks.SAPLING_CYPRESS, "Cypress Sapling");
        addBlock(WoodBlocks.SAPLING_BAMBOO, "Bamboo Sapling");

        // MARK: Leaves
        addBlock(WoodBlocks.LEAVES_MOSSBARK, "Mossbark Leaves");
        addBlock(WoodBlocks.LEAVES_ASPER, "Asper Leaves");
        addBlock(WoodBlocks.LEAVES_EUCALYPTUS, "Eucalyptus Leaves");
        addBlock(WoodBlocks.LEAVES_MAHOGANY, "Mahogany Leaves");
        addBlock(WoodBlocks.LEAVES_BALSAM, "Balsam Leaves");
        addBlock(WoodBlocks.LEAVES_BAOBAB, "Baobab Leaves");
        addBlock(WoodBlocks.LEAVES_MARSHWOOD, "Marshwood Leaves");
        addBlock(WoodBlocks.LEAVES_CYPRESS, "Cypress Leaves");

        // MARK: Planks
        addBlock(WoodBlocks.PLANKS_BAOBAB, "Baobab Planks");
        addBlock(WoodBlocks.PLANKS_EUCALYPTUS, "Eucalyptus Planks");
        addBlock(WoodBlocks.PLANKS_MAHOGANY, "Mahogany Planks");
        addBlock(WoodBlocks.PLANKS_MOSSBARK, "Mossbark Planks");
        addBlock(WoodBlocks.PLANKS_ASPER, "Asper Planks");
        addBlock(WoodBlocks.PLANKS_CYPRESS, "Cypress Planks");
        addBlock(WoodBlocks.PLANKS_BALSAM, "Balsam Planks");
        addBlock(WoodBlocks.PLANKS_WHITE, "White Planks");
        addBlock(WoodBlocks.PLANKS_BAMBOO, "Bamboo Planks");
        addBlock(WoodBlocks.PLANKS_ROTTEN, "Rotten Planks");
        addBlock(WoodBlocks.PLANKS_MARSHWOOD, "Marshwood Planks");
        addBlock(WoodBlocks.PLANKS_SCORCHED, "Scorched Planks");
        addBlock(WoodBlocks.PLANKS_VARNISHED, "Varnished Planks");
        addBlock(WoodBlocks.PLANKS_PETRIFIED, "Petrified Wood Planks");

        // MARK: Slabs Wood
        addBlock(SlabBlocks.SLAB_PLANKS_BAOBAB, "Baobab Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_EUCALYPTUS, "Eucalyptus Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_MAHOGANY, "Mahogany Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_MOSSBARK, "Mossbark Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_ASPER, "Asper Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_CYPRESS, "Cypress Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_BALSAM, "Balsam Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_WHITE, "White Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_BAMBOO, "Bamboo Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_ROTTEN, "Rotten Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_MARSHWOOD, "Marshwood Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_SCORCHED, "Scorched Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_VARNISHED, "Varnished Slab");
        addBlock(SlabBlocks.SLAB_PLANKS_PETRIFIED, "Petrified Wood Slab");

        // MARK: Slabs Stone
        addBlock(SlabBlocks.SLAB_UMBERSTONE, "Umberstone Slab");
        addBlock(SlabBlocks.SLAB_UMBERCOBBLE, "Umbercobble Slab");
        addBlock(SlabBlocks.SLAB_UMBERCOBBLE_MOSSY, "Mossy Umbercobble Slab");
        addBlock(SlabBlocks.SLAB_UMBERCOBBLE_WEBBED, "Webbed Umbercobble Slab");
        addBlock(SlabBlocks.SLAB_UMBERSTONE_BRICKS, "Umberbrick Slab");
        addBlock(SlabBlocks.SLAB_UMBERTILE_SMOOTH, "Smooth Umbertile Slab");
        addBlock(SlabBlocks.SLAB_UMBERTILE_SMOOTH_SMALL, "Small Umbertile Slab");
        addBlock(SlabBlocks.SLAB_UMBERPAVER, "Umberpaver Slab");
        addBlock(SlabBlocks.SLAB_UMBERPAVER_MOSSY, "Mossy Umberpaver Slab");
        addBlock(SlabBlocks.SLAB_UMBERPAVER_WEBBED, "Webbed Umberpaver Slab");
        addBlock(SlabBlocks.SLAB_AMBER, "Amber Slab");
        addBlock(SlabBlocks.SLAB_AMBER_BRICKS, "Amber Brick Slab");
        addBlock(SlabBlocks.SLAB_MUD_BRICKS, "Mud Brick Slab");
        addBlock(SlabBlocks.SLAB_MIR_BRICKS, "Mir Brick Slab");

        // MARK: Stairs Wood
        addBlock(StairBlocks.STAIRS_BAOBAB, "Baobab Stairs");
        addBlock(StairBlocks.STAIRS_EUCALYPTUS, "Eucalyptus Stairs");
        addBlock(StairBlocks.STAIRS_MAHOGANY, "Mahogany Stairs");
        addBlock(StairBlocks.STAIRS_MOSSBARK, "Mossbark Stairs");
        addBlock(StairBlocks.STAIRS_ASPER, "Asper Stairs");
        addBlock(StairBlocks.STAIRS_CYPRESS, "Cypress Stairs");
        addBlock(StairBlocks.STAIRS_BALSAM, "Balsam Stairs");
        addBlock(StairBlocks.STAIRS_WHITE, "White Stairs");
        addBlock(StairBlocks.STAIRS_BAMBOO, "Bamboo Stairs");
        addBlock(StairBlocks.STAIRS_ROTTEN, "Rotten Stairs");
        addBlock(StairBlocks.STAIRS_MARSHWOOD, "Marshwood Stairs");
        addBlock(StairBlocks.STAIRS_SCORCHED, "Scorched Stairs");
        addBlock(StairBlocks.STAIRS_VARNISHED, "Varnished Stairs");
        addBlock(StairBlocks.STAIRS_PETRIFIED, "Petrified Wood Stairs");

        // MARK: Stairs Stone
        addBlock(StairBlocks.STAIRS_UMBERSTONE, "Umberstone Stairs");
        addBlock(StairBlocks.STAIRS_UMBERCOBBLE, "Umbercobble Stairs");
        addBlock(StairBlocks.STAIRS_UMBERCOBBLE_MOSSY, "Mossy Umbercobble Stairs");
        addBlock(StairBlocks.STAIRS_UMBERCOBBLE_WEBBED, "Webbed Umbercobble Stairs");
        addBlock(StairBlocks.STAIRS_UMBERSTONE_BRICKS, "Umberbrick Stairs");
        addBlock(StairBlocks.STAIRS_UMBERTILE_SMOOTH, "Smooth Umbertile Stairs");
        addBlock(StairBlocks.STAIRS_UMBERTILE_SMOOTH_SMALL, "Small Umbertile Stairs");
        addBlock(StairBlocks.STAIRS_UMBERPAVER, "Umberpaver Stairs");
        addBlock(StairBlocks.STAIRS_UMBERPAVER_MOSSY, "Mossy Umberpaver Stairs");
        addBlock(StairBlocks.STAIRS_UMBERPAVER_WEBBED, "Webbed Umberpaver Stairs");
        addBlock(StairBlocks.STAIRS_AMBER, "Amber Stairs");
        addBlock(StairBlocks.STAIRS_AMBER_BRICKS, "Amber Brick Stairs");
        addBlock(StairBlocks.STAIRS_MUD_BRICKS, "Mud Brick Stairs");
        addBlock(StairBlocks.STAIRS_MIR_BRICKS, "Mir Brick Stairs");

        // MARK: Doors
        addBlock(DoorBlocks.DOOR_BAOBAB, "Baobab Door");
        addBlock(DoorBlocks.DOOR_EUCALYPTUS, "Eucalyptus Door");
        addBlock(DoorBlocks.DOOR_MAHOGANY, "Mahogany Door");
        addBlock(DoorBlocks.DOOR_MOSSBARK, "Mossbark Door");
        addBlock(DoorBlocks.DOOR_ASPER, "Asper Door");
        addBlock(DoorBlocks.DOOR_CYPRESS, "Cypress Door");
        addBlock(DoorBlocks.DOOR_BALSAM, "Balsam Door");
        addBlock(DoorBlocks.DOOR_WHITE, "White Door");
        addBlock(DoorBlocks.DOOR_ROTTEN, "Rotten Door");
        addBlock(DoorBlocks.DOOR_MARSHWOOD, "Marshwood Door");
        addBlock(DoorBlocks.DOOR_SCORCHED, "Scorched Door");

        // MARK: Fences
        addBlock(FenceBlocks.FENCE_BAOBAB, "Baobab Fence");
        addBlock(FenceBlocks.FENCE_EUCALYPTUS, "Eucalyptus Fence");
        addBlock(FenceBlocks.FENCE_MAHOGANY, "Mahogany Fence");
        addBlock(FenceBlocks.FENCE_MOSSBARK, "Mossbark Fence");
        addBlock(FenceBlocks.FENCE_ASPER, "Asper Fence");
        addBlock(FenceBlocks.FENCE_CYPRESS, "Cypress Fence");
        addBlock(FenceBlocks.FENCE_BALSAM, "Balsam Fence");
        addBlock(FenceBlocks.FENCE_WHITE, "White Fence");
        addBlock(FenceBlocks.FENCE_ROTTEN, "Rotten Fence");
        addBlock(FenceBlocks.FENCE_MARSHWOOD, "Marshwood Fence");
        addBlock(FenceBlocks.FENCE_SCORCHED, "Scorched Fence");
        addBlock(FenceBlocks.FENCE_VARNISHED, "Varnished Fence");

        // MARK: Fence Gates
        addBlock(FenceBlocks.FENCE_GATE_BAOBAB, "Baobab Fence Gate");
        addBlock(FenceBlocks.FENCE_GATE_EUCALYPTUS, "Eucalyptus Fence Gate");
        addBlock(FenceBlocks.FENCE_GATE_MAHOGANY, "Mahogany Fence Gate");
        addBlock(FenceBlocks.FENCE_GATE_MOSSBARK, "Mossbark Fence Gate");
        addBlock(FenceBlocks.FENCE_GATE_ASPER, "Asper Fence Gate");
        addBlock(FenceBlocks.FENCE_GATE_CYPRESS, "Cypress Fence Gate");
        addBlock(FenceBlocks.FENCE_GATE_BALSAM, "Balsam Fence Gate");
        addBlock(FenceBlocks.FENCE_GATE_WHITE, "White Fence Gate");
        addBlock(FenceBlocks.FENCE_GATE_ROTTEN, "Rotten Fence Gate");
        addBlock(FenceBlocks.FENCE_GATE_MARSHWOOD, "Marshwood Fence Gate");
        addBlock(FenceBlocks.FENCE_GATE_SCORCHED, "Scorched Fence Gate");
        addBlock(FenceBlocks.FENCE_GATE_VARNISHED, "Varnished Fence Gate");

        // MARK: Walls
        addBlock(WallBlocks.WALL_UMBERSTONE, "Umberstone Wall");
        addBlock(WallBlocks.WALL_UMBERCOBBLE, "Umbercobble Wall");
        addBlock(WallBlocks.WALL_UMBERCOBBLE_MOSSY, "Mossy Umbercobble Wall");
        addBlock(WallBlocks.WALL_UMBERCOBBLE_WEBBED, "Webbed Umbercobble Wall");
        addBlock(WallBlocks.WALL_UMBERSTONE_BRICKS, "Umberbrick Wall");
        addBlock(WallBlocks.WALL_UMBERTILE_SMOOTH, "Smooth Umbertile Wall");
        addBlock(WallBlocks.WALL_UMBERTILE_SMOOTH_SMALL, "Small Umbertile Wall");
        addBlock(WallBlocks.WALL_UMBERPAVER, "Umberpaver Wall");
        addBlock(WallBlocks.WALL_UMBERPAVER_MOSSY, "Mossy Umberpaver Wall");
        addBlock(WallBlocks.WALL_UMBERPAVER_WEBBED, "Webbed Umberpaver Wall");
        addBlock(WallBlocks.WALL_AMBER, "Amber Wall");
        addBlock(WallBlocks.WALL_AMBER_BRICKS, "Amber Brick Wall");

        // MARK: Plants
        addBlock(PlantBlocks.CROP_TURNIP, "Turnips");
        addBlock(PlantBlocks.CROP_CABBAGE, "Cabbage");
        addBlock(PlantBlocks.CROP_MANDRAKE, "Mandrake");
        addBlock(PlantBlocks.HEART_BERRY_BUSH, "Heartberry Bush");
        addBlock(PlantBlocks.JADE_BERRY_BUSH, "Jadeberry Bush");
        addBlock(PlantBlocks.SWAMP_BERRY_BUSH, "Swamp Berry Bush");
        addBlock(PlantBlocks.DARK_FRUIT_VINE, "Dark Fruit Vine");
        addBlock(PlantBlocks.PRICKLY_PEAR, "Prickly Pear Cactus");
        addBlock(PlantBlocks.COLOSSAL_BAMBOO, "Colossal Bamboo");
        addBlock(PlantBlocks.DARK_CAPPED_MUSHROOM, "Dark Capped Mushroom");
        addBlock(PlantBlocks.DUTCH_CAP_MUSHROOM, "Dutch Capped Mushroom");
        addBlock(PlantBlocks.GRANDMAS_SHOES_MUSHROOM, "Grandma's Shoes Mushroom");
        addBlock(PlantBlocks.KAIZERS_FINGERS_MUSHROOM, "Kaizer's Fingers Mushroom");
        addBlock(PlantBlocks.SARCASTIC_CZECH_MUSHROOM, "Sarcastic Czech Mushroom");
        addBlock(PlantBlocks.DARK_CAPPED_MUSHROOM_BLOCK, "Dark Capped Mushroom Block");
        addBlock(PlantBlocks.DUTCH_CAP_MUSHROOM_BLOCK, "Dutch Capped Mushroom Block");
        addBlock(PlantBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK, "Grandma's Shoes Mushroom Block");
        addBlock(PlantBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK, "Kaizer's Fingers Mushroom Block");
        addBlock(PlantBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK, "Sarcastic Czech Mushroom Block");
        addBlock(PlantBlocks.DESERT_SHRUB, "Desert Shrub");
        addBlock(PlantBlocks.MIRE_CORAL, "Glowing Swamp Weed");
        addBlock(PlantBlocks.NETTLE, "Nettle");
        addBlock(PlantBlocks.NETTLE_FLOWERED, "Blooming Nettle");
        addBlock(PlantBlocks.SWAMP_PLANT, "Swamp Plant");
        addBlock(PlantBlocks.FIRE_BLOOM, "Firebloom");
        addBlock(PlantBlocks.FERN, "Fern");
        addBlock(PlantBlocks.FIDDLE_HEAD, "Fiddlehead");
        addBlock(PlantBlocks.THORNS, "Thorns");
        addBlock(PlantBlocks.MOSS, "Moss");
        addBlock(PlantBlocks.MOULD, "Mould");
        addBlock(PlantBlocks.CULTIVATED_MOSS, "Cultivated Moss");
        addBlock(PlantBlocks.CULTIVATED_MOULD, "Cultivated Mould");
        addBlock(PlantBlocks.ALGAE, "Swamp Algae");
        addBlock(PlantBlocks.GLOWSHROOM_BLOCK, "Glowshroom");
        addBlock(PlantBlocks.GLOWSHROOM_STALK, "Glowshroom Stalk");
        addBlock(PlantBlocks.HANGING_WEB, "Hanging Webs");
        addBlock(PlantBlocks.GIANT_LILY_PAD, "Giant Lily Pad");

        // MARK: Flowers
        addBlock(PlantBlocks.PETAL_BLACK, "Black Petal Block");
        addBlock(PlantBlocks.PETAL_RED, "Red Petal Block");
        addBlock(PlantBlocks.PETAL_BROWN, "Brown Petal Block");
        addBlock(PlantBlocks.PETAL_BLUE, "Blue Petal Block");
        addBlock(PlantBlocks.PETAL_PURPLE, "Purple Petal Block");
        addBlock(PlantBlocks.PETAL_CYAN, "Cyan Petal Block");
        addBlock(PlantBlocks.PETAL_LIGHT_GRAY, "Light Gray Petal Block");
        addBlock(PlantBlocks.PETAL_GRAY, "Gray Petal Block");
        addBlock(PlantBlocks.PETAL_PINK, "Pink Petal Block");
        addBlock(PlantBlocks.PETAL_YELLOW, "Yellow Petal Block");
        addBlock(PlantBlocks.PETAL_LIGHT_BLUE, "Light Blue Petal Block");
        addBlock(PlantBlocks.PETAL_MAGENTA, "Magenta Petal Block");
        addBlock(PlantBlocks.PETAL_ORANGE, "Orange Petal Block");
        addBlock(PlantBlocks.PETAL_WHITE, "White Petal Block");
        addBlock(PlantBlocks.STIGMA_BLACK, "Black Stigma");
        addBlock(PlantBlocks.STIGMA_RED, "Red Stigma");
        addBlock(PlantBlocks.STIGMA_BROWN, "Brown Stigma");
        addBlock(PlantBlocks.STIGMA_BLUE, "Blue Stigma");
        addBlock(PlantBlocks.STIGMA_PURPLE, "Purple Stigma");
        addBlock(PlantBlocks.STIGMA_CYAN, "Cyan Stigma");
        addBlock(PlantBlocks.STIGMA_LIGHT_GRAY, "Light Gray Stigma");
        addBlock(PlantBlocks.STIGMA_GRAY, "Gray Stigma");
        addBlock(PlantBlocks.STIGMA_PINK, "Pink Stigma");
        addBlock(PlantBlocks.STIGMA_YELLOW, "Yellow Stigma");
        addBlock(PlantBlocks.STIGMA_LIGHT_BLUE, "Light Blue Stigma");
        addBlock(PlantBlocks.STIGMA_MAGENTA, "Magenta Stigma");
        addBlock(PlantBlocks.STIGMA_ORANGE, "Orange Stigma");
        addBlock(PlantBlocks.STIGMA_WHITE, "White Stigma");
        addBlock(PlantBlocks.EXPLODING_STIGMA, "Exploding Stigma");
        addBlock(PlantBlocks.FLOWER_BLACK, "Black Flower");
        addBlock(PlantBlocks.FLOWER_RED, "Red Flower");
        addBlock(PlantBlocks.FLOWER_BROWN, "Brown Flower");
        addBlock(PlantBlocks.FLOWER_BLUE, "Blue Flower");
        addBlock(PlantBlocks.FLOWER_PURPLE, "Purple Flower");
        addBlock(PlantBlocks.FLOWER_CYAN, "Cyan Flower");
        addBlock(PlantBlocks.FLOWER_LIGHT_GRAY, "Light Gray Flower");
        addBlock(PlantBlocks.FLOWER_GRAY, "Gray Flower");
        addBlock(PlantBlocks.FLOWER_PINK, "Pink Flower");
        addBlock(PlantBlocks.FLOWER_YELLOW, "Yellow Flower");
        addBlock(PlantBlocks.FLOWER_LIGHT_BLUE, "Light Blue Flower");
        addBlock(PlantBlocks.FLOWER_MAGENTA, "Magenta Flower");
        addBlock(PlantBlocks.FLOWER_ORANGE, "Orange Flower");
        addBlock(PlantBlocks.FLOWER_WHITE, "White Flower");
        addBlock(PlantBlocks.FLOWER_RAINBOW, "Rainbow Flower");
        addBlock(PlantBlocks.STEM, "Stem");

        // MARK: Flowers Double Height
        addBlock(PlantBlocks.BULLRUSH, "Bullrush");
        addBlock(PlantBlocks.WEEPING_BLUEBELL, "Weeping Bluebell");
        addBlock(PlantBlocks.SUNDEW, "Sundew");
        addBlock(PlantBlocks.DROUGHTED_SHRUB, "Droughted Shrub");
        addBlock(PlantBlocks.TALL_BLOOM, "Tall Bloom");
        addBlock(PlantBlocks.TANGLED_STALK, "Tangled Stalk Mushroom");
        addBlock(PlantBlocks.HIGH_CAPPED_MUSHROOM, "High Capped Mushroom");
        addBlock(PlantBlocks.TALL_FERN, "Tall Fern");

        // MARK: Other
        addBlock(OtherBlocks.PORTAL, "Erebus Portal Block");
        addBlock(OtherBlocks.GAEAN_KEYSTONE, "Gaean Keystone");
        addBlock(OtherBlocks.JADE_BLOCK, "Block of Jade");
        addBlock(OtherBlocks.MUD, "Mud");
        addBlock(OtherBlocks.QUICK_SAND, "Quicksand");
        addBlock(OtherBlocks.GHOST_SAND, "Ghost Sand");
        addBlock(OtherBlocks.SWAMP_VENT, "Swamp Vent");
        addBlock(OtherBlocks.GNEISS_VENT, "Gneiss Vent");
        addBlock(OtherBlocks.RED_GEM_BLOCK, "Red Gem");
        addBlock(OtherBlocks.RED_GEM_LAMP, "Red Gem Lamp");
        addBlock(OtherBlocks.WITHER_WEB, "Wither Web");
        addBlock(OtherBlocks.GNEISS, "Gneiss");
        addBlock(OtherBlocks.GNEISS_CARVED, "Carved Gneiss");
        addBlock(OtherBlocks.GNEISS_RELIEF, "Layered Gneiss");
        addBlock(OtherBlocks.GNEISS_BRICKS, "Gneiss Brick");
        addBlock(OtherBlocks.GNEISS_SMOOTH, "Smooth Gneiss");
        addBlock(OtherBlocks.GNEISS_TILES, "Gneiss Tiles");
        addBlock(OtherBlocks.GNEISS_TILES_CRACKED, "Cracked Gneiss Tiles");
        addBlock(OtherBlocks.TEMPLE_BRICK, "Temple Brick");
        addBlock(OtherBlocks.TEMPLE_PILLAR, "Temple Pillar");
        addBlock(OtherBlocks.TEMPLE_TILE, "Temple Tile");
        addBlock(OtherBlocks.SILK, "Silk");
        addBlock(OtherBlocks.REIN_EXO, "Reinforced Exo Blocks");
        addBlock(OtherBlocks.VELOCITY, "Velocity Block");
        addBlock(OtherBlocks.LIGHTNING_SPEED, "Lightning Velocity Block");
        addBlock(OtherBlocks.BLOCK_OF_BONES, "Block O' Bones");
        addBlock(OtherBlocks.ANTLION_EGG, "Antlion Egg");
        addBlock(OtherBlocks.TARANTULA_EGG, "Tarantula Egg");
        addBlock(OtherBlocks.HONEY_TREAT, "Honey Treat");
        addBlock(OtherBlocks.WASP_NEST, "Wasp Nest");
        addBlock(OtherBlocks.STAIRS_WASP_NEST, "Wasp Nest Stairs");
        addBlock(OtherBlocks.INSECT_REPELLENT, "Insect Repellent");

        // MARK: Spawners
        addBlock(OtherBlocks.ANTLION_SPAWNER, "Antlion Spawner");
        addBlock(OtherBlocks.DRAGON_FLY_SPAWNER, "Dragon Fly Spawner");
        addBlock(OtherBlocks.JUMPING_SPIDER_SPAWNER, "Jumping Spider Spawner");
        addBlock(OtherBlocks.TARANTULA_SPAWNER, "Tarantula Spawner");
        addBlock(OtherBlocks.WASP_SPAWNER, "Wasp Spawner");
        addBlock(OtherBlocks.ZOMBIE_ANT_SPAWNER, "Zombie Ant Spawner");
        addBlock(OtherBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER, "Zombie Ant Soldier Spawner");
        addBlock(OtherBlocks.MAGMA_CRAWLER_SPAWNER, "Magma Crawler Spawner");
        addBlock(OtherBlocks.DUNG_SPAWNER_FLY, "Fly Spawner");
        addBlock(OtherBlocks.DUNG_SPAWNER_BOT_FLY, "Bot Fly Spawner");
        addBlock(OtherBlocks.LOCUST_SPAWNER, "Locust Spawner");

        // MARK: Utility Blocks
        addBlock(OtherBlocks.PETRIFIED_CRAFTING_TABLE, "Petrified Crafting Table");
        addBlock(OtherBlocks.PETRIFIED_WOOD_CHEST, "Petrified Wood Chest");
        addBlock(OtherBlocks.BAMBOO_CRATE, "Bamboo Crate");
        addBlock(OtherBlocks.BAMBOO_BRIDGE, "Bamboo Bridge");
        addBlock(OtherBlocks.BAMBOO_LADDER, "Bamboo Ladder");
        addBlock(OtherBlocks.BAMBOO_NERD_POLE, "Bamboo Nerd Pole");
        addBlock(OtherBlocks.BAMBOO_EXTENDER, "Bamboo Extender");
        addBlock(OtherBlocks.BAMBOO_TORCH, "Bamboo Torch");
        addBlock(OtherBlocks.BAMBOO_PIPE, "Bamboo Fluid Pipe");
        addBlock(OtherBlocks.BAMBOO_PIPE_EXTRACT, "Bamboo Fluid Extraction Pipe");
        addBlock(OtherBlocks.SILO_ROOF, "Silo Roof");
        addBlock(OtherBlocks.SILO_TANK, "Silo Tank");
        addBlock(OtherBlocks.SILO_SUPPORTS, "Silo Supports");
        addBlock(OtherBlocks.HONEY_COMB, "Honeycomb Cell");
        addBlock(OtherBlocks.COMPOSTER, "Organic Composter");
        addBlock(OtherBlocks.BLENDER, "ErebusCo. Smoothie-matic 2000");
        addBlock(OtherBlocks.UMBER_FURNACE, "Umber Furnace");
        addBlock(OtherBlocks.UMBERSTONE_BUTTON, "Umberstone Button");
        addBlock(OtherBlocks.LIQUIFIER, "Honey Liquifier");
        addBlock(OtherBlocks.GLOW_GEM, "Glowing Gem");
        addBlock(OtherBlocks.MUCUS_BOMB, "Mucus Bomb");
        addBlock(OtherBlocks.UMBER_GOLEM_STATUE, "Umbergolem Statue");
        addBlock(OtherBlocks.ALTAR_BASE, "Altar Base");
        addBlock(OtherBlocks.ALTAR_LIGHTNING, "Altar of Lightning");
        addBlock(OtherBlocks.ALTAR_HEALING, "Altar of Healing");
        addBlock(OtherBlocks.ALTAR_EXPERIENCE, "Altar of Experience");
        addBlock(OtherBlocks.ALTAR_REPAIR, "Altar of Repair");
        addBlock(OtherBlocks.OFFERING_ALTAR, "Offering Altar");

        // MARK: Antlion Dungeon
        addBlock(OtherBlocks.CAPSTONE, "Capstone");
        addBlock(OtherBlocks.CAPSTONE_MUD, "Mud Capstone");
        addBlock(OtherBlocks.CAPSTONE_IRON, "Iron Capstone");
        addBlock(OtherBlocks.CAPSTONE_GOLD, "Gold Capstone");
        addBlock(OtherBlocks.CAPSTONE_JADE, "Jade Capstone");
        addBlock(OtherBlocks.TEMPLE_BRICK_UNBREAKING, "Temple Brick");
        addBlock(OtherBlocks.TEMPLE_BRICK_UNBREAKING_CREAM, "Jade Slot");
        addBlock(OtherBlocks.TEMPLE_BRICK_UNBREAKING_EXO, "Exoskeleton Slot");
        addBlock(OtherBlocks.TEMPLE_BRICK_UNBREAKING_EYE, "Eye Slot");
        addBlock(OtherBlocks.TEMPLE_BRICK_UNBREAKING_JADE, "Jade Slot");
        addBlock(OtherBlocks.TEMPLE_BRICK_UNBREAKING_STRING, "String Slot");
        addBlock(OtherBlocks.TEMPLE_TELEPORTER, "Temple Teleporter");
        addBlock(OtherBlocks.FORCE_FIELD, "Force Field");
        addBlock(OtherBlocks.FORCE_LOCK, "Force Lock");
        addBlock(OtherBlocks.ANT_HILL_BLOCK, "Ant Hill");
    }

    private void addEntityTranslations() {
        addEntity(ModEntities.WASP, "Wasp");
        addEntityNoEgg(ModEntities.ANIMATED_BLOCK, "Animated Block");
        addEntity(ModEntities.SCYTODES, "Scytodes");
        addEntity(ModEntities.BLACK_WIDOW, "Black Widow");
        addEntity(ModEntities.MONEY_SPIDER, "Money Spider");
        addEntity(ModEntities.LAVA_WEB_SPIDER, "Lava Web Spider");
        addEntity(ModEntities.MOTH, "Moth");
        addEntity(ModEntities.VELVET_WORM, "Velvet Worm");
        addEntity(ModEntities.ANTLION, "Antlion");
        addEntity(ModEntities.FLY, "Fly");
        addEntity(ModEntities.BOT_FLY, "Botfly");
        addEntity(ModEntities.BOT_FLY_LARVA, "Botfly Larva");
        addEntity(ModEntities.DRAGON_FLY, "Dragonfly");
        addEntity(ModEntities.CENTIPEDE, "Centipede");
        addEntity(ModEntities.GRASSHOPPER, "Grasshopper");
        addEntity(ModEntities.LOCUST, "Locust");
        addEntity(ModEntities.BEETLE_LARVA, "Beetle Larva");
        addEntity(ModEntities.BOMBARDIER_BEETLE_LARVA, "Bombardier Beetle Larva");
        addEntity(ModEntities.BEETLE, "Beetle");
        addEntity(ModEntities.WORKER_BEE, "Worker Bee");
        addEntity(ModEntities.BOMBARDIER_BEETLE, "Bombardier Beetle");

        addEntityNoEgg(ModEntities.THROWN_BLOCK_AS_ITEM, "Mob Projectile"); // needs a better name
        addEntityNoEgg(ModEntities.GOO_BALL, "Goo Ball");
    }

    private void addFluidTranslations() {

    }

    private void addCreativeTabTranslations() {
        add("erebus.blocks", "Erebus Blocks");
        add("erebus.gear", "Erebus Gear");
        add("erebus.items", "Erebus Items");
        add("erebus.plants", "Erebus Plants");
    }

    private void addToolTipTranslations() {
        add("tooltip.death_compass.pos", "Death Pos: ");
        add("tooltip.shield.damage", "Damage: ");
        add("tooltip.shield.repair", "Anvil Repair: ");
		add("tooltip.erebus.wand_of_animation", "Right click blocks to animate them.");
		add("tooltip.erebus.bamboo_pipe", "A very basic directional fluid pipe.");
		add("tooltip.erebus.bamboo_pipe_extract", "Extracts fluids from fluid containers and pushes them in to pipes and fluid containers.");
		add("tooltip.erebus.bamboo_pipe_wrench", "Use on any Bamboo Pipe to rotate them or Use + Sneak to insta-break them.");
		add("tooltip.erebus.honeycomb_x", "Honeycomb Cell X: %s");
		add("tooltip.erebus.honeycomb_y", "Honeycomb Cell Y: %s");
		add("tooltip.erebus.honeycomb_z", "Honeycomb Cell Z: %s");
		add("tooltip.erebus.bee_taming_amulet_1", "Click on a Honeycomb Cell to set as target for Bee drops.");
		add("tooltip.erebus.bee_taming_amulet_2", "Then click on Bee to tame.");
	}

    private void addBookTranslations() {

    }

    private void addAdvancementTranslations() {

    }

    private void addBiomeTranslations() {

    }

    private void addContainerTranslations() {
        addContainer("petrified_crafting_table", "Petrified Crafting Table");
        addContainer("umberfurnace", "Umber Furnace");
        addContainer("liquifier", "Honey Liquifier");
        addContainer("honeycomb_cell", "Honeycomb Cell");
        addContainer("bamboo_extender", "Bamboo Extender");
        addContainer("bamboo_crate", "Bamboo Crate");
        addContainer("colossal_crate", "Colossal Crate");
    }
}
