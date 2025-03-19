package erebus.datagen;

import erebus.datagen.providers.ModLangProvider;
import erebus.registries.ModBlocks;
import erebus.registries.ModEntities;
import erebus.registries.ModItems;
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
        addBlock(ModBlocks.UMBERSTONE, "Umberstone");
        addBlock(ModBlocks.UMBERCOBBLE, "Umbercobble");
        addBlock(ModBlocks.UMBERCOBBLE_MOSSY, "Mossy Umbercobble");
        addBlock(ModBlocks.UMBERCOBBLE_WEBBED, "Webbed Umbercobble");
        addBlock(ModBlocks.UMBERSTONE_BRICKS, "Umberbricks");
        addBlock(ModBlocks.UMBERGRAVEL, "Umberpebbles");
        addBlock(ModBlocks.UMBERPAVER, "Umberpaver");
        addBlock(ModBlocks.UMBERPAVER_MOSSY, "Mossy Umberpaver");
        addBlock(ModBlocks.UMBERPAVER_WEBBED, "Webbed Umberpaver");
        addBlock(ModBlocks.UMBERSTONE_PILLAR, "Umberstone Pillar");
        addBlock(ModBlocks.VOLCANIC_ROCK, "Volcanic Rock");
        addBlock(ModBlocks.DUST, "Dust");
        addBlock(ModBlocks.DUST_LAYER, "Dust Layer");
        addBlock(ModBlocks.PETRIFIED_WOOD_ROCK, "Petrified Wood Rock");
        addBlock(ModBlocks.PETRIFIED_WOOD_ROCK_2, "Petrified Wood Rock");
        addBlock(ModBlocks.PETRIFIED_WOOD_ROCK_3, "Petrified Wood Rock");
        addBlock(ModBlocks.PETRIFIED_WOOD_ROCK_4, "Petrified Wood Rock");
        addBlock(ModBlocks.PETRIFIED_WOOD_ROCK_5, "Petrified Wood Rock");
        addBlock(ModBlocks.PETRIFIED_WOOD_ROCK_6, "Petrified Wood Rock");
        addBlock(ModBlocks.PETRIFIED_BARK_RED, "Red Petrified Bark");
        addBlock(ModBlocks.PETRIFIED_BARK_BROWN, "Brown Petrified Bark");
        addBlock(ModBlocks.DUNG, "Dung");

        // MARK: Amber
        addBlock(ModBlocks.AMBER, "Amber");
        addBlock(ModBlocks.AMBER_BRICKS, "Amber Bricks");
        addBlock(ModBlocks.AMBER_GLASS, "Amber Glass");
        addBlock(ModBlocks.PRESERVED_AMBER, "Amber Preservation Block");
        addBlock(ModBlocks.PRESERVED_AMBER_GLASS, "Amber Preservation Block");
        addBlock(ModBlocks.GLOWING_JAR, "Glowing Jar");
        addBlock(ModBlocks.FLUID_JAR, "Fluid Jar");
        addBlock(ModBlocks.AMBER_DOOR, "Amber Door");

        // MARK: Ores
        addBlock(ModBlocks.ORE_IRON, "Iron Ore");
        addBlock(ModBlocks.ORE_GOLD, "Gold Ore");
        addBlock(ModBlocks.ORE_DIAMOND, "Diamond Ore");
        addBlock(ModBlocks.ORE_EMERALD, "Emerald Ore");
        addBlock(ModBlocks.ORE_LAPIS, "Lapis Lazuli Ore");
        addBlock(ModBlocks.ORE_QUARTZ, "Quartz Ore");
        addBlock(ModBlocks.ORE_PETRIFIED_QUARTZ, "Petrified Quartz Ore");
        addBlock(ModBlocks.ORE_COAL, "Coal Ore");
        addBlock(ModBlocks.ORE_COPPER, "Copper Ore");
        addBlock(ModBlocks.ORE_TIN, "Tin Ore");
        addBlock(ModBlocks.ORE_SILVER, "Silver Ore");
        addBlock(ModBlocks.ORE_ALUMINUM, "Aluminum Ore");
        addBlock(ModBlocks.ORE_LEAD, "Lead Ore");
        addBlock(ModBlocks.ORE_JADE, "Jade Ore");
        addBlock(ModBlocks.ORE_ENCRUSTED_DIAMOND, "Volcanic Diamond Ore");
        addBlock(ModBlocks.ORE_FOSSIL, "Fossil Ore");
        addBlock(ModBlocks.ORE_GNEISS, "Gneiss Ore");
        addBlock(ModBlocks.ORE_TEMPLE, "Temple Ore");
        addBlock(ModBlocks.ORE_PETRIFIED_WOOD, "Petrified Wood Ore");

        // MARK: Logs
        addBlock(ModBlocks.LOG_BAOBAB, "Baobab Wood");
        addBlock(ModBlocks.LOG_EUCALYPTUS, "Eucalyptus Wood");
        addBlock(ModBlocks.LOG_MAHOGANY, "Mahogany Wood");
        addBlock(ModBlocks.LOG_MOSSBARK, "Mossbark Wood");
        addBlock(ModBlocks.LOG_ASPER, "Asper Wood");
        addBlock(ModBlocks.LOG_CYPRESS, "Cypress Wood");
        addBlock(ModBlocks.LOG_BALSAM, "Balsam Wood");
        addBlock(ModBlocks.LOG_BALSAM_RESINLESS, "Balsam Wood Resinless");
        addBlock(ModBlocks.LOG_ROTTEN, "Rotten Wood");
        addBlock(ModBlocks.LOG_MARSHWOOD, "Marshwood Wood");
        addBlock(ModBlocks.LOG_SCORCHED, "Scorched Wood");
        addBlock(ModBlocks.LOG_BAMBOO, "Bamboo Wood");
        addBlock(ModBlocks.LOG_HOLLOW, "Hollow Acacia Log");

        // MARK: Saplings
        addBlock(ModBlocks.SAPLING_MOSSBARK, "Mossbark Sapling");
        addBlock(ModBlocks.SAPLING_ASPER, "Asper Sapling");
        addBlock(ModBlocks.SAPLING_EUCALYPTUS, "Eucalyptus Sapling");
        addBlock(ModBlocks.SAPLING_MAHOGANY, "Mahogany Sapling");
        addBlock(ModBlocks.SAPLING_BALSAM, "Balsam Sapling");
        addBlock(ModBlocks.SAPLING_BAOBAB, "Baobab Sapling");
        addBlock(ModBlocks.SAPLING_MARSHWOOD, "Marshwood Sapling");
        addBlock(ModBlocks.SAPLING_CYPRESS, "Cypress Sapling");
        addBlock(ModBlocks.SAPLING_BAMBOO, "Bamboo Sapling");

        // MARK: Leaves
        addBlock(ModBlocks.LEAVES_MOSSBARK, "Mossbark Leaves");
        addBlock(ModBlocks.LEAVES_ASPER, "Asper Leaves");
        addBlock(ModBlocks.LEAVES_EUCALYPTUS, "Eucalyptus Leaves");
        addBlock(ModBlocks.LEAVES_MAHOGANY, "Mahogany Leaves");
        addBlock(ModBlocks.LEAVES_BALSAM, "Balsam Leaves");
        addBlock(ModBlocks.LEAVES_BAOBAB, "Baobab Leaves");
        addBlock(ModBlocks.LEAVES_MARSHWOOD, "Marshwood Leaves");
        addBlock(ModBlocks.LEAVES_CYPRESS, "Cypress Leaves");

        // MARK: Planks
        addBlock(ModBlocks.PLANKS_BAOBAB, "Baobab Planks");
        addBlock(ModBlocks.PLANKS_EUCALYPTUS, "Eucalyptus Planks");
        addBlock(ModBlocks.PLANKS_MAHOGANY, "Mahogany Planks");
        addBlock(ModBlocks.PLANKS_MOSSBARK, "Mossbark Planks");
        addBlock(ModBlocks.PLANKS_ASPER, "Asper Planks");
        addBlock(ModBlocks.PLANKS_CYPRESS, "Cypress Planks");
        addBlock(ModBlocks.PLANKS_BALSAM, "Balsam Planks");
        addBlock(ModBlocks.PLANKS_WHITE, "White Planks");
        addBlock(ModBlocks.PLANKS_BAMBOO, "Bamboo Planks");
        addBlock(ModBlocks.PLANKS_ROTTEN, "Rotten Planks");
        addBlock(ModBlocks.PLANKS_MARSHWOOD, "Marshwood Planks");
        addBlock(ModBlocks.PLANKS_SCORCHED, "Scorched Planks");
        addBlock(ModBlocks.PLANKS_VARNISHED, "Varnished Planks");
        addBlock(ModBlocks.PLANKS_PETRIFIED, "Petrified Wood Planks");

        // MARK: Slabs Wood
        addBlock(ModBlocks.SLAB_PLANKS_BAOBAB, "Baobab Slab");
        addBlock(ModBlocks.SLAB_PLANKS_EUCALYPTUS, "Eucalyptus Slab");
        addBlock(ModBlocks.SLAB_PLANKS_MAHOGANY, "Mahogany Slab");
        addBlock(ModBlocks.SLAB_PLANKS_MOSSBARK, "Mossbark Slab");
        addBlock(ModBlocks.SLAB_PLANKS_ASPER, "Asper Slab");
        addBlock(ModBlocks.SLAB_PLANKS_CYPRESS, "Cypress Slab");
        addBlock(ModBlocks.SLAB_PLANKS_BALSAM, "Balsam Slab");
        addBlock(ModBlocks.SLAB_PLANKS_WHITE, "White Slab");
        addBlock(ModBlocks.SLAB_PLANKS_BAMBOO, "Bamboo Slab");
        addBlock(ModBlocks.SLAB_PLANKS_ROTTEN, "Rotten Slab");
        addBlock(ModBlocks.SLAB_PLANKS_MARSHWOOD, "Marshwood Slab");
        addBlock(ModBlocks.SLAB_PLANKS_SCORCHED, "Scorched Slab");
        addBlock(ModBlocks.SLAB_PLANKS_VARNISHED, "Varnished Slab");
        addBlock(ModBlocks.SLAB_PLANKS_PETRIFIED, "Petrified Wood Slab");

        // MARK: Slabs Stone
        addBlock(ModBlocks.SLAB_UMBERSTONE, "Umberstone Slab");
        addBlock(ModBlocks.SLAB_UMBERCOBBLE, "Umbercobble Slab");
        addBlock(ModBlocks.SLAB_UMBERCOBBLE_MOSSY, "Mossy Umbercobble Slab");
        addBlock(ModBlocks.SLAB_UMBERCOBBLE_WEBBED, "Webbed Umbercobble Slab");
        addBlock(ModBlocks.SLAB_UMBERSTONE_BRICKS, "Umberbrick Slab");
        addBlock(ModBlocks.SLAB_UMBERTILE_SMOOTH, "Smooth Umbertile Slab");
        addBlock(ModBlocks.SLAB_UMBERTILE_SMOOTH_SMALL, "Small Umbertile Slab");
        addBlock(ModBlocks.SLAB_UMBERPAVER, "Umberpaver Slab");
        addBlock(ModBlocks.SLAB_UMBERPAVER_MOSSY, "Mossy Umberpaver Slab");
        addBlock(ModBlocks.SLAB_UMBERPAVER_WEBBED, "Webbed Umberpaver Slab");
        addBlock(ModBlocks.SLAB_AMBER, "Amber Slab");
        addBlock(ModBlocks.SLAB_AMBER_BRICKS, "Amber Brick Slab");
        addBlock(ModBlocks.SLAB_MUD_BRICKS, "Mud Brick Slab");
        addBlock(ModBlocks.SLAB_MIR_BRICKS, "Mir Brick Slab");

        // MARK: Stairs Wood
        addBlock(ModBlocks.STAIRS_BAOBAB, "Baobab Stairs");
        addBlock(ModBlocks.STAIRS_EUCALYPTUS, "Eucalyptus Stairs");
        addBlock(ModBlocks.STAIRS_MAHOGANY, "Mahogany Stairs");
        addBlock(ModBlocks.STAIRS_MOSSBARK, "Mossbark Stairs");
        addBlock(ModBlocks.STAIRS_ASPER, "Asper Stairs");
        addBlock(ModBlocks.STAIRS_CYPRESS, "Cypress Stairs");
        addBlock(ModBlocks.STAIRS_BALSAM, "Balsam Stairs");
        addBlock(ModBlocks.STAIRS_WHITE, "White Stairs");
        addBlock(ModBlocks.STAIRS_BAMBOO, "Bamboo Stairs");
        addBlock(ModBlocks.STAIRS_ROTTEN, "Rotten Stairs");
        addBlock(ModBlocks.STAIRS_MARSHWOOD, "Marshwood Stairs");
        addBlock(ModBlocks.STAIRS_SCORCHED, "Scorched Stairs");
        addBlock(ModBlocks.STAIRS_VARNISHED, "Varnished Stairs");
        addBlock(ModBlocks.STAIRS_PETRIFIED, "Petrified Wood Stairs");

        // MARK: Stairs Stone
        addBlock(ModBlocks.STAIRS_UMBERSTONE, "Umberstone Stairs");
        addBlock(ModBlocks.STAIRS_UMBERCOBBLE, "Umbercobble Stairs");
        addBlock(ModBlocks.STAIRS_UMBERCOBBLE_MOSSY, "Mossy Umbercobble Stairs");
        addBlock(ModBlocks.STAIRS_UMBERCOBBLE_WEBBED, "Webbed Umbercobble Stairs");
        addBlock(ModBlocks.STAIRS_UMBERSTONE_BRICKS, "Umberbrick Stairs");
        addBlock(ModBlocks.STAIRS_UMBERTILE_SMOOTH, "Smooth Umbertile Stairs");
        addBlock(ModBlocks.STAIRS_UMBERTILE_SMOOTH_SMALL, "Small Umbertile Stairs");
        addBlock(ModBlocks.STAIRS_UMBERPAVER, "Umberpaver Stairs");
        addBlock(ModBlocks.STAIRS_UMBERPAVER_MOSSY, "Mossy Umberpaver Stairs");
        addBlock(ModBlocks.STAIRS_UMBERPAVER_WEBBED, "Webbed Umberpaver Stairs");
        addBlock(ModBlocks.STAIRS_AMBER, "Amber Stairs");
        addBlock(ModBlocks.STAIRS_AMBER_BRICKS, "Amber Brick Stairs");
        addBlock(ModBlocks.STAIRS_MUD_BRICKS, "Mud Brick Stairs");
        addBlock(ModBlocks.STAIRS_MIR_BRICKS, "Mir Brick Stairs");

        // MARK: Doors
        addBlock(ModBlocks.DOOR_BAOBAB, "Baobab Door");
        addBlock(ModBlocks.DOOR_EUCALYPTUS, "Eucalyptus Door");
        addBlock(ModBlocks.DOOR_MAHOGANY, "Mahogany Door");
        addBlock(ModBlocks.DOOR_MOSSBARK, "Mossbark Door");
        addBlock(ModBlocks.DOOR_ASPER, "Asper Door");
        addBlock(ModBlocks.DOOR_CYPRESS, "Cypress Door");
        addBlock(ModBlocks.DOOR_BALSAM, "Balsam Door");
        addBlock(ModBlocks.DOOR_WHITE, "White Door");
        addBlock(ModBlocks.DOOR_ROTTEN, "Rotten Door");
        addBlock(ModBlocks.DOOR_MARSHWOOD, "Marshwood Door");
        addBlock(ModBlocks.DOOR_SCORCHED, "Scorched Door");

        // MARK: Fences
        addBlock(ModBlocks.FENCE_BAOBAB, "Baobab Fence");
        addBlock(ModBlocks.FENCE_EUCALYPTUS, "Eucalyptus Fence");
        addBlock(ModBlocks.FENCE_MAHOGANY, "Mahogany Fence");
        addBlock(ModBlocks.FENCE_MOSSBARK, "Mossbark Fence");
        addBlock(ModBlocks.FENCE_ASPER, "Asper Fence");
        addBlock(ModBlocks.FENCE_CYPRESS, "Cypress Fence");
        addBlock(ModBlocks.FENCE_BALSAM, "Balsam Fence");
        addBlock(ModBlocks.FENCE_WHITE, "White Fence");
        addBlock(ModBlocks.FENCE_ROTTEN, "Rotten Fence");
        addBlock(ModBlocks.FENCE_MARSHWOOD, "Marshwood Fence");
        addBlock(ModBlocks.FENCE_SCORCHED, "Scorched Fence");
        addBlock(ModBlocks.FENCE_VARNISHED, "Varnished Fence");

        // MARK: Fence Gates
        addBlock(ModBlocks.FENCE_GATE_BAOBAB, "Baobab Fence Gate");
        addBlock(ModBlocks.FENCE_GATE_EUCALYPTUS, "Eucalyptus Fence Gate");
        addBlock(ModBlocks.FENCE_GATE_MAHOGANY, "Mahogany Fence Gate");
        addBlock(ModBlocks.FENCE_GATE_MOSSBARK, "Mossbark Fence Gate");
        addBlock(ModBlocks.FENCE_GATE_ASPER, "Asper Fence Gate");
        addBlock(ModBlocks.FENCE_GATE_CYPRESS, "Cypress Fence Gate");
        addBlock(ModBlocks.FENCE_GATE_BALSAM, "Balsam Fence Gate");
        addBlock(ModBlocks.FENCE_GATE_WHITE, "White Fence Gate");
        addBlock(ModBlocks.FENCE_GATE_ROTTEN, "Rotten Fence Gate");
        addBlock(ModBlocks.FENCE_GATE_MARSHWOOD, "Marshwood Fence Gate");
        addBlock(ModBlocks.FENCE_GATE_SCORCHED, "Scorched Fence Gate");
        addBlock(ModBlocks.FENCE_GATE_VARNISHED, "Varnished Fence Gate");

        // MARK: Walls
        addBlock(ModBlocks.WALL_UMBERSTONE, "Umberstone Wall");
        addBlock(ModBlocks.WALL_UMBERCOBBLE, "Umbercobble Wall");
        addBlock(ModBlocks.WALL_UMBERCOBBLE_MOSSY, "Mossy Umbercobble Wall");
        addBlock(ModBlocks.WALL_UMBERCOBBLE_WEBBED, "Webbed Umbercobble Wall");
        addBlock(ModBlocks.WALL_UMBERSTONE_BRICKS, "Umberbrick Wall");
        addBlock(ModBlocks.WALL_UMBERTILE_SMOOTH, "Smooth Umbertile Wall");
        addBlock(ModBlocks.WALL_UMBERTILE_SMOOTH_SMALL, "Small Umbertile Wall");
        addBlock(ModBlocks.WALL_UMBERPAVER, "Umberpaver Wall");
        addBlock(ModBlocks.WALL_UMBERPAVER_MOSSY, "Mossy Umberpaver Wall");
        addBlock(ModBlocks.WALL_UMBERPAVER_WEBBED, "Webbed Umberpaver Wall");
        addBlock(ModBlocks.WALL_AMBER, "Amber Wall");
        addBlock(ModBlocks.WALL_AMBER_BRICKS, "Amber Brick Wall");

        // MARK: Plants
        addBlock(ModBlocks.CROP_TURNIP, "Turnips");
        addBlock(ModBlocks.CROP_CABBAGE, "Cabbage");
        addBlock(ModBlocks.CROP_MANDRAKE, "Mandrake");
        addBlock(ModBlocks.HEART_BERRY_BUSH, "Heartberry Bush");
        addBlock(ModBlocks.JADE_BERRY_BUSH, "Jadeberry Bush");
        addBlock(ModBlocks.SWAMP_BERRY_BUSH, "Swamp Berry Bush");
        addBlock(ModBlocks.DARK_FRUIT_VINE, "Dark Fruit Vine");
        addBlock(ModBlocks.PRICKLY_PEAR, "Prickly Pear Cactus");
        addBlock(ModBlocks.DARK_CAPPED_MUSHROOM, "Dark Capped Mushroom");
        addBlock(ModBlocks.DUTCH_CAP_MUSHROOM, "Dutch Capped Mushroom");
        addBlock(ModBlocks.GRANDMAS_SHOES_MUSHROOM, "Grandma's Shoes Mushroom");
        addBlock(ModBlocks.KAIZERS_FINGERS_MUSHROOM, "Kaizer's Fingers Mushroom");
        addBlock(ModBlocks.SARCASTIC_CZECH_MUSHROOM, "Sarcastic Czech Mushroom");
        addBlock(ModBlocks.DARK_CAPPED_MUSHROOM_BLOCK, "Dark Capped Mushroom Block");
        addBlock(ModBlocks.DUTCH_CAP_MUSHROOM_BLOCK, "Dutch Capped Mushroom Block");
        addBlock(ModBlocks.GRANDMAS_SHOES_MUSHROOM_BLOCK, "Grandma's Shoes Mushroom Block");
        addBlock(ModBlocks.KAIZERS_FINGERS_MUSHROOM_BLOCK, "Kaizer's Fingers Mushroom Block");
        addBlock(ModBlocks.SARCASTIC_CZECH_MUSHROOM_BLOCK, "Sarcastic Czech Mushroom Block");
        addBlock(ModBlocks.DESERT_SHRUB, "Desert Shrub");
        addBlock(ModBlocks.MIRE_CORAL, "Glowing Swamp Weed");
        addBlock(ModBlocks.NETTLE, "Nettle");
        addBlock(ModBlocks.NETTLE_FLOWERED, "Blooming Nettle");
        addBlock(ModBlocks.SWAMP_PLANT, "Swamp Plant");
        addBlock(ModBlocks.FIRE_BLOOM, "Firebloom");
        addBlock(ModBlocks.FERN, "Fern");
        addBlock(ModBlocks.FIDDLE_HEAD, "Fiddlehead");
        addBlock(ModBlocks.THORNS, "Thorns");
        addBlock(ModBlocks.MOSS_DOWN, "Moss");
        addBlock(ModBlocks.MOULD_DOWN, "Mould");
        addBlock(ModBlocks.CULTIVATED_MOSS_DOWN, "Cultivated Moss");
        addBlock(ModBlocks.CULTIVATED_MOULD_DOWN, "Cultivated Mould");
        addBlock(ModBlocks.ALGAE, "Swamp Algae");
        addBlock(ModBlocks.GLOWSHROOM_BLOCK, "Glowshroom");
        addBlock(ModBlocks.GLOWSHROOM_STALK, "Glowshroom Stalk");
        addBlock(ModBlocks.HANGING_WEB, "Hanging Webs");

        // MARK: Flowers
        addBlock(ModBlocks.PETAL_BLACK, "Black Petal Block");
        addBlock(ModBlocks.PETAL_RED, "Red Petal Block");
        addBlock(ModBlocks.PETAL_BROWN, "Brown Petal Block");
        addBlock(ModBlocks.PETAL_BLUE, "Blue Petal Block");
        addBlock(ModBlocks.PETAL_PURPLE, "Purple Petal Block");
        addBlock(ModBlocks.PETAL_CYAN, "Cyan Petal Block");
        addBlock(ModBlocks.PETAL_LIGHT_GRAY, "Light Gray Petal Block");
        addBlock(ModBlocks.PETAL_GRAY, "Gray Petal Block");
        addBlock(ModBlocks.PETAL_PINK, "Pink Petal Block");
        addBlock(ModBlocks.PETAL_YELLOW, "Yellow Petal Block");
        addBlock(ModBlocks.PETAL_LIGHT_BLUE, "Light Blue Petal Block");
        addBlock(ModBlocks.PETAL_MAGENTA, "Magenta Petal Block");
        addBlock(ModBlocks.PETAL_ORANGE, "Orange Petal Block");
        addBlock(ModBlocks.PETAL_WHITE, "White Petal Block");
        addBlock(ModBlocks.STIGMA_BLACK, "Black Stigma");
        addBlock(ModBlocks.STIGMA_RED, "Red Stigma");
        addBlock(ModBlocks.STIGMA_BROWN, "Brown Stigma");
        addBlock(ModBlocks.STIGMA_BLUE, "Blue Stigma");
        addBlock(ModBlocks.STIGMA_PURPLE, "Purple Stigma");
        addBlock(ModBlocks.STIGMA_CYAN, "Cyan Stigma");
        addBlock(ModBlocks.STIGMA_LIGHT_GRAY, "Light Gray Stigma");
        addBlock(ModBlocks.STIGMA_GRAY, "Gray Stigma");
        addBlock(ModBlocks.STIGMA_PINK, "Pink Stigma");
        addBlock(ModBlocks.STIGMA_YELLOW, "Yellow Stigma");
        addBlock(ModBlocks.STIGMA_LIGHT_BLUE, "Light Blue Stigma");
        addBlock(ModBlocks.STIGMA_MAGENTA, "Magenta Stigma");
        addBlock(ModBlocks.STIGMA_ORANGE, "Orange Stigma");
        addBlock(ModBlocks.STIGMA_WHITE, "White Stigma");
        addBlock(ModBlocks.EXPLODING_STIGMA, "Exploding Stigma");
        addBlock(ModBlocks.FLOWER_BLACK, "Black Flower");
        addBlock(ModBlocks.FLOWER_RED, "Red Flower");
        addBlock(ModBlocks.FLOWER_BROWN, "Brown Flower");
        addBlock(ModBlocks.FLOWER_BLUE, "Blue Flower");
        addBlock(ModBlocks.FLOWER_PURPLE, "Purple Flower");
        addBlock(ModBlocks.FLOWER_CYAN, "Cyan Flower");
        addBlock(ModBlocks.FLOWER_LIGHT_GRAY, "Light Gray Flower");
        addBlock(ModBlocks.FLOWER_GRAY, "Gray Flower");
        addBlock(ModBlocks.FLOWER_PINK, "Pink Flower");
        addBlock(ModBlocks.FLOWER_YELLOW, "Yellow Flower");
        addBlock(ModBlocks.FLOWER_LIGHT_BLUE, "Light Blue Flower");
        addBlock(ModBlocks.FLOWER_MAGENTA, "Magenta Flower");
        addBlock(ModBlocks.FLOWER_ORANGE, "Orange Flower");
        addBlock(ModBlocks.FLOWER_WHITE, "White Flower");
        addBlock(ModBlocks.FLOWER_RAINBOW, "Rainbow Flower");
        addBlock(ModBlocks.STEM, "Stem");

        // MARK: Flowers Double Height
        addBlock(ModBlocks.BULLRUSH, "Bullrush");
        addBlock(ModBlocks.WEEPING_BLUEBELL, "Weeping Bluebell");
        addBlock(ModBlocks.SUNDEW, "Sundew");
        addBlock(ModBlocks.DROUGHTED_SHRUB, "Droughted Shrub");
        addBlock(ModBlocks.TALL_BLOOM, "Tall Bloom");
        addBlock(ModBlocks.TANGLED_STALK, "Tangled Stalk Mushroom");
        addBlock(ModBlocks.HIGH_CAPPED_MUSHROOM, "High Capped Mushroom");
        addBlock(ModBlocks.TALL_FERN, "Tall Fern");

        // MARK: Other
        addBlock(ModBlocks.PORTAL, "Erebus Portal Block");
        addBlock(ModBlocks.GAEAN_KEYSTONE, "Gaean Keystone");
        addBlock(ModBlocks.JADE_BLOCK, "Block of Jade");
        addBlock(ModBlocks.MUD, "Mud");
        addBlock(ModBlocks.QUICK_SAND, "Quicksand");
        addBlock(ModBlocks.GHOST_SAND, "Ghost Sand");
        addBlock(ModBlocks.SWAMP_VENT, "Swamp Vent");
        addBlock(ModBlocks.GNEISS_VENT, "Gneiss Vent");
        addBlock(ModBlocks.RED_GEM_BLOCK, "Red Gem");
        addBlock(ModBlocks.RED_GEM_LAMP, "Red Gem Lamp");
        addBlock(ModBlocks.WITHER_WEB, "Wither Web");
        addBlock(ModBlocks.GNEISS, "Gneiss");
        addBlock(ModBlocks.GNEISS_CARVED, "Carved Gneiss");
        addBlock(ModBlocks.GNEISS_RELIEF, "Layered Gneiss");
        addBlock(ModBlocks.GNEISS_BRICKS, "Gneiss Brick");
        addBlock(ModBlocks.GNEISS_SMOOTH, "Smooth Gneiss");
        addBlock(ModBlocks.GNEISS_TILES, "Gneiss Tiles");
        addBlock(ModBlocks.GNEISS_TILES_CRACKED, "Cracked Gneiss Tiles");
        addBlock(ModBlocks.TEMPLE_BRICK, "Temple Brick");
        addBlock(ModBlocks.TEMPLE_PILLAR, "Temple Pillar");
        addBlock(ModBlocks.TEMPLE_TILE, "Temple Tile");
        addBlock(ModBlocks.SILK, "Silk");
        addBlock(ModBlocks.MIR_BRICKS, "Mirbrick");
        addBlock(ModBlocks.MUD_BRICKS, "Mud Brick");
        addBlock(ModBlocks.REIN_EXO, "Reinforced Exo Blocks");
        addBlock(ModBlocks.VELOCITY, "Velocity Block");
        addBlock(ModBlocks.LIGHTNING_SPEED, "Lightning Velocity Block");
        addBlock(ModBlocks.BLOCK_OF_BONES, "Block O' Bones");
        addBlock(ModBlocks.ANTLION_EGG, "Antlion Egg");
        addBlock(ModBlocks.TARANTULA_EGG, "Tarantula Egg");
        addBlock(ModBlocks.HONEY_TREAT, "Honey Treat");
        addBlock(ModBlocks.WASP_NEST, "Wasp Nest");
        addBlock(ModBlocks.STAIRS_WASP_NEST, "Wasp Nest Stairs");
        addBlock(ModBlocks.INSECT_REPELLENT, "Insect Repellent");

        // MARK: Spawners
        addBlock(ModBlocks.ANTLION_SPAWNER, "Antlion Spawner");
        addBlock(ModBlocks.DRAGON_FLY_SPAWNER, "Dragon Fly Spawner");
        addBlock(ModBlocks.JUMPING_SPIDER_SPAWNER, "Jumping Spider Spawner");
        addBlock(ModBlocks.TARANTULA_SPAWNER, "Tarantula Spawner");
        addBlock(ModBlocks.WASP_SPAWNER, "Wasp Spawner");
        addBlock(ModBlocks.ZOMBIE_ANT_SPAWNER, "Zombie Ant Spawner");
        addBlock(ModBlocks.ZOMBIE_ANT_SOLDIER_SPAWNER, "Zombie Ant Soldier Spawner");
        addBlock(ModBlocks.MAGMA_CRAWLER_SPAWNER, "Magma Crawler Spawner");
        addBlock(ModBlocks.DUNG_SPAWNER_FLY, "Fly Spawner");
        addBlock(ModBlocks.DUNG_SPAWNER_BOT_FLY, "Bot Fly Spawner");
        addBlock(ModBlocks.LOCUST_SPAWNER, "Locust Spawner");

        // MARK: Utility Blocks
        addBlock(ModBlocks.PETRIFIED_CRAFTING_TABLE, "Petrified Crafting Table");
        addBlock(ModBlocks.PETRIFIED_WOOD_CHEST, "Petrified Wood Chest");
        addBlock(ModBlocks.BAMBOO_CRATE, "Bamboo Crate");
        addBlock(ModBlocks.BAMBOO_BRIDGE, "Bamboo Bridge");
        addBlock(ModBlocks.BAMBOO_LADDER, "Bamboo Ladder");
        addBlock(ModBlocks.BAMBOO_NERD_POLE, "Bamboo Nerd Pole");
        addBlock(ModBlocks.BAMBOO_EXTENDER, "Bamboo Extender");
        addBlock(ModBlocks.BAMBOO_TORCH, "Bamboo Torch");
        addBlock(ModBlocks.BAMBOO_PIPE, "Bamboo Fluid Pipe");
        addBlock(ModBlocks.BAMBOO_PIPE_EXTRACT, "Bamboo Fluid Extraction Pipe");
        addBlock(ModBlocks.BAMBOO_PIPE_EXTRACT_ACTIVE, "Bamboo Fluid Extraction Pipe");
        addBlock(ModBlocks.SILO_ROOF, "Silo Roof");
        addBlock(ModBlocks.SILO_TANK, "Silo Tank");
        addBlock(ModBlocks.SILO_SUPPORTS, "Silo Supports");
        addBlock(ModBlocks.HONEY_COMB, "Honeycomb");
        addBlock(ModBlocks.COMPOSTER, "Organic Composter");
        addBlock(ModBlocks.BLENDER, "ErebusCo. Smoothie-matic 2000");
        addBlock(ModBlocks.UMBER_FURNACE, "Umber Furnace");
        addBlock(ModBlocks.UMBERSTONE_BUTTON, "Umberstone Button");
        addBlock(ModBlocks.LIQUIFIER, "Honey Liquifier");
        addBlock(ModBlocks.GLOW_GEM, "Glowing Gem");
        addBlock(ModBlocks.MUCUS_BOMB, "Mucus Bomb");
        addBlock(ModBlocks.UMBER_GOLEM_STATUE, "Umbergolem Statue");
        addBlock(ModBlocks.ALTAR_BASE, "Altar Base");
        addBlock(ModBlocks.ALTAR_LIGHTNING, "Altar of Lightning");
        addBlock(ModBlocks.ALTAR_HEALING, "Altar of Healing");
        addBlock(ModBlocks.ALTAR_XP, "Altar of Experience");
        addBlock(ModBlocks.ALTAR_REPAIR, "Altar of Repair");
        addBlock(ModBlocks.ALTAR_OFFERING, "Offering Altar");

        // MARK: Antlion Dungeon
        addBlock(ModBlocks.CAPSTONE, "Capstone");
        addBlock(ModBlocks.CAPSTONE_MUD, "Mud Capstone");
        addBlock(ModBlocks.CAPSTONE_IRON, "Iron Capstone");
        addBlock(ModBlocks.CAPSTONE_GOLD, "Gold Capstone");
        addBlock(ModBlocks.CAPSTONE_JADE, "Jade Capstone");
        addBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING, "Temple Brick");
        addBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_CREAM, "Jade Slot");
        addBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_EXO, "Exoskeleton Slot");
        addBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_EYE, "Eye Slot");
        addBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_JADE, "Jade Slot");
        addBlock(ModBlocks.TEMPLE_BRICK_UNBREAKING_STRING, "String Slot");
        addBlock(ModBlocks.TEMPLE_TELEPORTER, "Temple Teleporter");
        addBlock(ModBlocks.FORCE_FIELD, "Force Field");
        addBlock(ModBlocks.FORCE_LOCK, "Force Lock");
        addBlock(ModBlocks.ANT_HILL_BLOCK, "Ant Hill");
    }

    private void addEntityTranslations() {
        addEntityType(ModEntities.WASP, "Wasp");
        addEntityType(ModEntities.ANIMATED_BLOCK, "Animated Block");
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

    }

    private void addBookTranslations() {

    }

    private void addAdvancementTranslations() {

    }

    private void addBiomeTranslations() {

    }
}
