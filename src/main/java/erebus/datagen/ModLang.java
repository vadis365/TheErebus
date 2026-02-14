package erebus.datagen;

import erebus.datagen.providers.ModLangProvider;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.tags.ModItemTags;
import erebus.registries.entity.ModEntities;
import erebus.registries.item.ModItems;
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
        addTagTranslations();
        addEMITranslations();
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
        addItem(ModItems.QUAKE_HAMMER, "Quake Hammer");
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
        addBlock(ModBlocks.UMBERTILE_SMOOTH, "Smooth Umbertile");
        addBlock(ModBlocks.UMBERTILE_SMOOTH_SMALL, "Small Umbertiles");
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
        addBlock(ModBlocks.MIR_BRICKS, "Mirbrick");
        addBlock(ModBlocks.MUD_BRICKS, "Mud Brick");

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
        addBlock(ModBlocks.COLOSSAL_BAMBOO, "Colossal Bamboo");
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
        addBlock(ModBlocks.MOSS, "Moss");
        addBlock(ModBlocks.MOULD, "Mould");
        addBlock(ModBlocks.MOSS_CULTIVATED, "Cultivated Moss");
        addBlock(ModBlocks.MOULD_CULTIVATED, "Cultivated Mould");
        addBlock(ModBlocks.ALGAE, "Swamp Algae");
        addBlock(ModBlocks.GLOWSHROOM_BLOCK, "Glowshroom");
        addBlock(ModBlocks.GLOWSHROOM_STALK, "Glowshroom Stalk");
        addBlock(ModBlocks.HANGING_WEB, "Hanging Webs");
        addBlock(ModBlocks.GIANT_LILY_PAD, "Giant Lily Pad");

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
        addBlock(ModBlocks.REIN_EXO, "Reinforced Exo Blocks");
        addBlock(ModBlocks.VELOCITY_BLOCK, "Velocity Block");
        addBlock(ModBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED, "Lightning Speed Velocity Block");
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
        addBlock(ModBlocks.BAMBOO_CRATE, "Bamboo Crate");
        addBlock(ModBlocks.BAMBOO_BRIDGE, "Bamboo Bridge");
        addBlock(ModBlocks.BAMBOO_LADDER, "Bamboo Ladder");
        addBlock(ModBlocks.BAMBOO_NERD_POLE, "Bamboo Nerd Pole");
        addBlock(ModBlocks.BAMBOO_EXTENDER, "Bamboo Extender");
        addBlock(ModBlocks.BAMBOO_TORCH, "Bamboo Torch");
        addBlock(ModBlocks.BAMBOO_PIPE, "Bamboo Fluid Pipe");
        addBlock(ModBlocks.BAMBOO_PIPE_EXTRACT, "Bamboo Fluid Extraction Pipe");
        addBlock(ModBlocks.SILO_ROOF, "Silo Roof");
        addBlock(ModBlocks.SILO_TANK, "Silo Tank");
        addBlock(ModBlocks.SILO_SUPPORTS, "Silo Supports");
        addBlock(ModBlocks.HONEY_COMB, "Honeycomb Cell");
        addBlock(ModBlocks.COMPOSTER, "Organic Composter");
        addBlock(ModBlocks.BLENDER, "ErebusCo. Smoothie-matic 2000");
        addBlock(ModBlocks.UMBER_FURNACE, "Umber Furnace");
        addBlock(ModBlocks.UMBERSTONE_BUTTON, "Umberstone Button");
        addBlock(ModBlocks.LIQUIFIER, "Honey Liquifier");
        addBlock(ModBlocks.GLOW_GEM_ACTIVE, "Glowing Gem");
        addBlock(ModBlocks.GLOW_GEM_INACTIVE, "Glowing Gem");
        addBlock(ModBlocks.MUCUS_BOMB, "Mucus Bomb");
        addBlock(ModBlocks.UMBER_GOLEM_STATUE, "Umbergolem Statue");
        addBlock(ModBlocks.ALTAR_BASE, "Altar Base");
        addBlock(ModBlocks.ALTAR_LIGHTNING, "Altar of Lightning");
        addBlock(ModBlocks.ALTAR_HEALING, "Altar of Healing");
        addBlock(ModBlocks.ALTAR_EXPERIENCE, "Altar of Experience");
        addBlock(ModBlocks.ALTAR_REPAIR, "Altar of Repair");
        addBlock(ModBlocks.OFFERING_ALTAR, "Offering Altar");

        // MARK: Chests
        addBlock(ModBlocks.CHEST_ASPER, "Asper Chest");
        addBlock(ModBlocks.CHEST_BAMBOO, "Bamboo Chest");
        addBlock(ModBlocks.CHEST_BAOBAB, "Baobab Chest");
        addBlock(ModBlocks.CHEST_BALSAM, "Balsam Chest");
        addBlock(ModBlocks.CHEST_CYPRESS, "Cypress Chest");
        addBlock(ModBlocks.CHEST_EUCALYPTUS, "Eucalyptus Chest");
        addBlock(ModBlocks.CHEST_MAHOGANY, "Mahogany Chest");
        addBlock(ModBlocks.CHEST_MARSHWOOD, "Marshwood Chest");
        addBlock(ModBlocks.CHEST_MOSSBARK, "Mossbark Chest");
        addBlock(ModBlocks.CHEST_PETRIFIED, "Petrified Wood Chest");
        addBlock(ModBlocks.CHEST_ROTTEN, "Rotten Chest");
        addBlock(ModBlocks.CHEST_SCORCHED, "Scorched Chest");
        addBlock(ModBlocks.CHEST_VARNISHED, "Varnished Chest");
        addBlock(ModBlocks.CHEST_WHITE, "White Chest");

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
        addEntity(ModEntities.ANIMATED_BLOCK, "Animated Block");
        addEntity(ModEntities.ANTLION, "Antlion");
        addEntity(ModEntities.ANTLION_BOSS, "Antlion Overlord");
        addEntity(ModEntities.ANTLION_MINI_BOSS, "Antlion Warden");
        addEntityNoEgg(ModEntities.BABY_SOLIFUGE, "Baby Solifuge");
        addEntity(ModEntities.BABY_TARANTULA, "Baby Tarantula");
        addEntity(ModEntities.BED_BUG, "Bed Bug");
        addEntity(ModEntities.BEETLE, "Beetle");
        addEntity(ModEntities.BEETLE_LARVA, "Beetle Larva");
        addEntity(ModEntities.BLACK_ANT, "Black Ant");
        addEntity(ModEntities.BLACK_WIDOW, "Black Widow");
        addEntity(ModEntities.BOG_MAW, "Bog Maw");
        addEntity(ModEntities.BOMBARDIER_BEETLE, "Bombardier Beetle");
        addEntity(ModEntities.BOMBARDIER_BEETLE_LARVA, "Bombardier Beetle Larva");
        addEntity(ModEntities.BOT_FLY, "Botfly");
        addEntity(ModEntities.BOT_FLY_LARVA, "Botfly Larva");
        addEntity(ModEntities.CENTIPEDE, "Centipede");
        addEntity(ModEntities.CHAMELEON_TICK, "Chameleon Tick");
        addEntity(ModEntities.CICADA, "Cicada");
        addEntity(ModEntities.CROP_WEEVIL, "Crop Weevil");
        addEntity(ModEntities.CRUSHROOM, "Crushroom");
        addEntity(ModEntities.DRAGON_FLY, "Dragonfly");
        addEntity(ModEntities.FIRE_ANT, "Fire Ant");
        addEntity(ModEntities.FIRE_ANT_SOLDIER, "Fire Ant Soldier");
        addEntity(ModEntities.FLY, "Fly");
        addEntity(ModEntities.FUNGAL_WEEVIL, "Fungal Weevil");
        addEntity(ModEntities.GLOW_WORM, "Glow Worm");
        addEntity(ModEntities.GRASSHOPPER, "Grasshopper");
        addEntity(ModEntities.HONEY_POT_ANT, "Honey Pot Ant");
        addEntity(ModEntities.JUMPING_SPIDER, "Jumping Spider");
        addEntity(ModEntities.LAVA_WEB_SPIDER, "Lava Web Spider");
        addEntity(ModEntities.LOCUST, "Locust");
        addEntity(ModEntities.MAGMA_CRAWLER, "Magma Crawler");
        addEntity(ModEntities.MIDGE_SWARM, "Midge Swarm");
        addEntity(ModEntities.MONEY_SPIDER, "Money Spider");
        addEntity(ModEntities.MOSQUITO, "Mosquito");
        addEntity(ModEntities.MOTH, "Moth");
        addEntity(ModEntities.POND_SKATER, "Pond Skater");
        addEntity(ModEntities.PRAYING_MANTIS, "Praying Mantis");
        addEntity(ModEntities.PUNCHROOM, "Punchroom");
        addEntity(ModEntities.RHINO_BEETLE, "Rhino Beetle");
        addEntity(ModEntities.SCORPION, "Scorpion");
        addEntity(ModEntities.SCYTODES, "Scytodes");
        addEntity(ModEntities.SOLIFUGE, "Solifuge");
        addEntity(ModEntities.STAG_BEETLE, "Stag Beetle");
        addEntity(ModEntities.TARANTULA, "Tarantula");
        addEntity(ModEntities.TARANTULA_MINI_BOSS, "Tarantula Mini-Boss");
        addEntity(ModEntities.TITAN_BEETLE, "Titan Beetle");
        addEntity(ModEntities.UMBER_GOLEM, "Umber Golem");
        addEntity(ModEntities.VELVET_WORM, "Velvet Worm");
        addEntity(ModEntities.WASP, "Wasp");
        addEntity(ModEntities.WOODLOUSE, "Woodlouse");
        addEntity(ModEntities.WORKER_BEE, "Worker Bee");
        addEntity(ModEntities.ZOMBIE_ANT, "Zombie Ant");
        addEntity(ModEntities.ZOMBIE_ANT_SOLDIER, "Zombie Ant Soldier");

        addEntityNoEgg(ModEntities.AMBER_STAR, "Amber Star");
        addEntityNoEgg(ModEntities.GOO_BALL, "Goo Ball");
        addEntityNoEgg(ModEntities.THROWN_BLOCK_AS_ITEM, "Mob Projectile"); // needs a better name
    }

    private void addFluidTranslations() {

    }

    private void addCreativeTabTranslations() {
        add("erebus.blocks", "Erebus Blocks");
        add("erebus.gear", "Erebus Gear");
        add("erebus.items", "Erebus Items");
        add("erebus.plants", "Erebus Plants");
        add("erebus.spawn_eggs", "Erebus Spawn Eggs");
    }

    private void addToolTipTranslations() {
    	add("tooltip.erebus.death_compass", "Given to Player after death to find Block o' Bones."); // Death compass needs more work - eg dimension and time of death
    	add("tooltip.erebus.dimension", "Dimension: %s");
        add("tooltip.death_compass.pos", "Death Pos: ");
        add("tooltip.erebus.time_of_death", "Time of Death: %s");
        add("tooltip.erebus.shield.damage", "Damage: ");
        add("tooltip.erebus.shield.repair", "Anvil Repair: ");
		add("tooltip.erebus.wand_of_animation", "Right click blocks to animate them.");
		add("tooltip.erebus.bamboo_pipe", "A very basic directional fluid pipe.");
		add("tooltip.erebus.bamboo_pipe_extract", "Extracts fluids from fluid containers and pushes them in to pipes and fluid containers.");
		add("tooltip.erebus.bamboo_pipe_wrench", "Use on any Bamboo Pipe to rotate them or Use + Sneak to insta-break them.");
		add("tooltip.erebus.honeycomb_x", "Honeycomb Cell X: %s");
		add("tooltip.erebus.honeycomb_y", "Honeycomb Cell Y: %s");
		add("tooltip.erebus.honeycomb_z", "Honeycomb Cell Z: %s");
		add("tooltip.erebus.bee_taming_amulet_1", "Click on a Honeycomb Cell to set as target for Bee drops.");
		add("tooltip.erebus.bee_taming_amulet_2", "Then click on Bee to tame.");
		add("tooltip.erebus.sprint_leggings_tier", "Tier %s");
		add("tooltip.erebus.jump_boots", "Enables you to Jump 4 blocks high.");
		add("tooltip.erebus.silo_x", "Silo Block X: %s");
		add("tooltip.erebus.silo_y", "Silo Block Y: %s");
		add("tooltip.erebus.silo_z", "Silo Block Z: %s");
		add("tooltip.erebus.ant_taming_amulet_1", "Click on a Silo Block to set as target for Ants.");
		add("tooltip.erebus.ant_taming_amulet_2", "Then click on Ant to link it.");
		add("tooltip.erebus.spray_can", "Use on the top of blocks to keep those pesky critters away.");
		add("tooltip.erebus.wand_of_preservation", "Shoots Amber stars and captures mobs. Silk touch the block to collect or break to free the mob.");
		add("tooltip.erebus.liquifier", "Turns Honey Drips in to Fluid Honey. Redstone activated.");
		add("tooltip.erebus.glow_gem", "Place on ANY side of a solid block as a light source. Right click to turn off and on.");
		add("tooltip.erebus.planticide", "Removes Plants, Leaves and Crops in a 4x4x4 area!"); // I think this is right - used to be 5x5x5
		add("tooltip.erebus.night_vision", "Enables you to see in the dark.");

	//	TODO - NYI, either the thing isn't added yet or it doesn't have the tool-tip added to it yet.
		add("tooltip.erebus.nectar_collector", "Right click Bees or Honeypot Ants to collect nectar.");
		add("tooltip.erebus.homingbeecon_advanced_1", "Sneak + Right click on a block to set as target.");
		add("tooltip.erebus.homingbeecon_advanced_2", "Right click to teleport.");
		add("tooltip.erebus.homingbeecon", "Sneak + Click on a block to set as target.");
		add("tooltip.erebus.quake_hammer_1", "Hold Right mouse button to charge up.");
		add("tooltip.erebus.quake_hammer_2", "Sneak + Right Click on top of block to use A.O.E. attack.");
		add("tooltip.erebus.wasp_dagger_1", "One use.");
		add("tooltip.erebus.wasp_dagger_2", "Can be thrown.");
		add("tooltip.erebus.whetstone_sharpness", "Sharpness Level %s");
		add("tooltip.erebus.whetstone_1", "Apply to tools using an Anvil.");
		add("tooltip.erebus.whetstone_2", "Un-enchanted");
		add("tooltip.erebus.whetstone_3", "Surround with Whetstone Powder to increase levels.");
		add("tooltip.erebus.extractor", "Point at blocks and hold down the right mouse button to extract them.");
		add("tooltip.erebus.scorpion_pincer", "Can Shoot Fire Charges if in your Inventory.");
		add("tooltip.erebus.heals", "Heals 10 Hearts when consumed. Always Edible.");
		add("tooltip.erebus.feeds", "Fills Hunger Bar when consumed. Always Edible.");
		add("tooltip.erebus.powered_glider", "Needs redgem blocks at the cost of fuel to flight.");
		add("tooltip.erebus.glider_glide_key", "Gliding Key");
		add("tooltip.erebus.glider_powered_key", "Flying Key");
		add("tooltip.erebus.mush_helm", "Keeps you fed whilst worn at the cost of durability. Repaired in Anvil with Big Mushroom Blocks.");
		add("tooltip.erebus.healing_hearts", "Heals you a small amount when consumed.");
		add("tooltip.erebus.water_striders", "Enables you to Walk on Water.");
		add("tooltip.erebus.compost", "Works just like bonemeal Mr.");
		add("tooltip.erebus.horn_summon", "Summons some bees.");
		add("tooltip.erebus.web_slinger", "Can Shoot Webs if in your Inventory.");
		add("tooltip.erebus.force_key", "N.Y.I.");
	}

    private void addBookTranslations() {
        addPage(0, "§0Thank you for using the new ErebusCo. Smoothie-matic 2000.\nIn the following pages you will find some delicious recipes.\n\nEach recipe will require a different base fluid. The fluid bars shown in the gui are for: Honey, Beetle Juice, Anti-Venom and Milk.");
        addPage(1, "§0Fluids can be added to the Smoothie-matic 2000 by right clicking on the block with a full bucket.\n\nThe Four top slots in the gui are for the required ingredients and the lower slot must contain a Smoothie Glass.");
        addPage(2, "§9Green Tea Grasshopper\n\n§0Grasshopper Leg x2\nElastic Fibre\nFly Wing\n\n§dBeetle Juice\n\n§4Effects:\nJumping");
        addPage(3, "§9Money Honey\n\n§0Honey drip x2\nNectar\nGold Nugget\n\n§dHoney\n\n§4Effects:\nRegeneration");
        addPage(4, "§9Darkness In The Middle\n\n§0Camo powder x2\nDark Fruit\nSwamp Berries\n\n§dBeetle Juice\n\n§4Effects:\nInvisibility");
        addPage(5, "§9Green Giant\n\n§0Repellent\nPoison Gland x2\nWasp Sting\n\n§dAnti-Venom\n\n§4Effects:\nNegates Potions");
        addPage(6, "§9Seedy Goodness\n\n§0Pumpkin Seeds\nMelon Seeds\nDark Fruit Seeds\nBio-Velocity\n\n§dBeetle Juice\n\n§4Effects:\nDig Speed");
        addPage(7, "§9Givin' Me The Blues\n\n§0Blue Bell Petal x2\nLapis Lazuli x2\n\n§dMilk\n\n§4Effects:\nExtinguish");
        addPage(8, "§9Hot Hot Baby\n\n§0Fire Bloom x2\nWasp Sting\nBog Maw Root\n\n§dAnti-Venom\n\n§4Effects:\nStrength");
        addPage(9, "§9Don't Meddle With The Nettle\n\n§0Nettle Leaf\nNettle Flower\nJade Berries\nExo Skeleton\n\n§dHoney\n\n§4Effects:\nResistance");
        addPage(10, "§9Liquid Gold\n\n§0Life Blood x2\nBamboo Shoot\nGlistering Melon\n\n§dMilk\n\n§4Effects:\nSaturation");
        addPage(11, "§9Bryuf's Brew\n\n§0Terpsishroom\nTurnip\nCompound Eyes\nHeart Berries\n\n§dHoney\n§dBeetle Juice\n§dAnti-Venom\n§dMilk\n\n§4Effects:\nMighty Buffs");
        addPage(12, "§9Melonade\n\n§0Melon Slice\n\n§dWater\n\n§4Effects:\nRefreshing");
        addPage(13, "§9Sparkling Melonade\n\n§0Glistening Melon Slice\n\n§dWater\n\n§4Effects:\nSmall Regen");
        addPage(14, "§9Antivenom Bambucket\n\n§0Bambucket\n\n§0Nettle Leaves x2\n\n§0Poison Glands x2\n\n§dBeetle Juice\n\n§4Effects:\nImmunity to Poison");
        addPage(15, "§9Antivenom Bottle\n\n§0Glass Bottle\n\n§0Nettle Leaf\n\n§0Poison Gland\n\n§dBeetle Juice\n\n§4Effects:\nImmunity to Poison");
        addPage(16, "§9Antivenom Bucket\n\n§0Bucket\n\n§0Nettle Leaves x2\n\n§0Poison Glands x2\n\n§dBeetle Juice\n\n§4Effects:\nFilled Bucket");
    }

    private void addAdvancementTranslations() {
        addExploration("root", "The Erebus", "Travel to a long lost world, overrun by gargantuan arthropods: The Erebus.");
        addExploration("arborist", "Obsessive Arborist", "Gather all the different saplings of trees native to the Erebus.");
        addExploration("traveller", "A True Traveller Never Arrives", "Travel to all biomes the Erebus has to offer.");
        addExploration("quicksand", "Let That Sink In", "Get yourself stuck in some Quicksand.");
        addExploration("smoothie_all", "Smoothie Enthusiast", "Brew all 10 smoothies.");
        addExploration("smoothie_book", "The Menu", "Get the smoothie guide book by right-clicking the Smoothie-matic 2000 with a blank book.");
        addExploration("smoothie_blender", "For The Juiceheads", "Craft the ErebusCo. Smoothie-matic 2000, all rights reserved.");
        addExploration("antivenom", "Just Open Your Mouth", "Brew some Anti-venom in the Smoothie Maker.");
        addExploration("jade_ore", "What Shade of Green Is This?", "Obtain Jade.");
        addExploration("jade_set", "Extremely Jaded", "Obtain all four pieces of the Jade armour set and the Jade Paxel.");
        addExploration("petrified_chest", "I Love Big Chests", "Craft a Petrified Wood Chest, an expensive but more spaceous chest.");
        addExploration("petrified_wood", "Very Hard Wood", "Acquire Petrified Wood.");
        addExploration("entomology", "Aggressive Entomology", "Slay a creature native to the Erebus.");
        addExploration("kill_all", "Pest Control", "Kill all regular mobs the Erebus has to offer.");
        addExploration("find_beetles", "Meet the Beetles!", "Have a somewhat unkind encounter with any of the Beetles or their Larvae.");
        addExploration("beetlejuice", "Alpha Orionis", "Milk a Beetle to obtain Beetle Juice, the Erebus counterpart to milk.");
        addExploration("beetledrink", "Pour Up, Drank", "Down an entire Bambucket of Beetle Juice. Yes, you heard that right.");
        addExploration("titan_beetle", "Titanomachy", "Slay a Titan Beetle and obtain its flesh.");
        addExploration("titan_stew", "Itadakimasu", "Cook yourself some Titan Stew, the most nourishing meal you'll ever eat.");
        addExploration("rhino_beetle", "That's Poaching", "Kill the Rhinoceros Beetle and obtain its exoskeleton and horn.");
        addExploration("rhino_exo_set", "Heavy-handed", "Craft the entire Rhino Plate set, including the shield.");
        addExploration("exo_set", "Feelin' Invertebrate", "Obtain all four pieces of the Exoskeleton armour set and the Exoskeleton Shield.");
        addExploration("reinexo_set", "Truth Is... I Am Exo Man", "Obtain all four pieces of the Reinforced Exoskeleton armour set and the Reinforced Exoskeleton Shield.");
        addExploration("planticide", "More Effective Than Herbicide", "Mix together some Planticide to destroy some flora.");
        addExploration("poison_sac", "Your Cruel Device", "Snag a Poison Sac from the innards of one of the many venomous critters that inhabit the Erebus.");
        addExploration("repellent", "Utterly Repulsive", "Get your hands on some Repellent.");
        addExploration("spray_can", "Begone Arthrothot", "Craft some Insect Repellent.");
        addExploration("stung", "The Boy with the Thorn in His Side", "Get stung by a Wasp or a Scorpion.");
        addExploration("water_repellent", "Why So Hydrophobic, Bigot?", "Acquire some Hydrofuge to craft Water Repellent.");
        addExploration("water_striders", "Personal Jesus", "Craft some Water Striders.");
        addExploration("whetstone", "Sharpen Your Wits", "Get your hands on some Whetstone Powder and apply it to an unenchanted Whetstone.");
        addExploration("woodlouse", "A Surprise to be Sure", "Encounter and kill a Woodlouse.");
        addExploration("woodlouse_ball", "Killer Ball", "Catch a Woodlouse by right-clicking it with an empty hand.");
        addExploration("newspaper", "News of the World", "Enhance some Papyrus with Ink and Whetstone Powder to roll a newspaper with extreme swatting capabilities.");

        // Agriculture advancements
        addAgriculture("root", "Subterranean Agriculture", "Even though the sun does not shine down here, there are still many plants and other growables to be found. Dive into the wonderful world of subterranean agriculture!");
        addAgriculture("varnished_planks", "Resist Those Pesky Larvae", "Craft some Varnished Planks.");
        addAgriculture("silo", "Every Farm Needs One", "Craft the three parts of the Silo multiblock.");
        addAgriculture("ant_amulet", "Getting Antsy", "Craft the Ant Taming Amulet and tame an Ant.");
        addAgriculture("nectar", "Sweet Victory", "Obtain some Nectar by asking some Bees with the blade of your weapon.");
        addAgriculture("honey", "I Love You, Honeybear", "Use the Nectar you obtained to create Honey.");
        addAgriculture("honeyfoods", "What's For Dinner, Honey?", "Prepare yourself a wonderful meal by crafting all Honey-related foods.");
        addAgriculture("honeycomb", "Beauty Is...", "Construct a Honeycomb.");
        addAgriculture("bee_amulet", "...In The Eye of the Bee-holder", "Fashion a Bee Taming Amulet from some Jade and Nectar.");
        addAgriculture("spoon", "The Big Spoon", "Craft a Nectar Collector, a simple yet ingenious tool to easily collect Nectar from Bees.");
        addAgriculture("beecon", "Right Where I Bee-long", "Use the Nectar you procured to craft a Homing Beecon, a device that allows you to always find your way back.");
        addAgriculture("turnip", "SuperRutabaga", "Obtain some Turnips somewhere.");
        addAgriculture("beetle_breed", "Turnip to Make 'Em Turn Up", "Get two Beetles in the mood by feeding them the most erotic of vegetables - to them at least, we don't judge.");
        addAgriculture("bamboo", "Colossally Bamboozled", "Get your hands on some Colossal Bamboo and Colossal Bamboo Shoots.");
        addAgriculture("bamboo_extender", "Fully Extended UwU", "Craft the Bamboo Extender.");
        addAgriculture("bamboo_bridge", "Seven Bridges Road", "Craft the Bamboo Bridge.");
        addAgriculture("nerd_pole", "Piercing the Heavens", "Craft the Bamboo Nerd Pole.");
        addAgriculture("bamboo_crate", "Make Erebus Crate Again", "Craft yourself a Bamboo Crate.");
        addAgriculture("bamboo_soup", "When eating bamboo sprouts...", "Craft yourself some Bamboo Soup.");
        addAgriculture("bamboo_plant", "... remember who planted them.", "Plant a Colossal Bamboo Shoot.");

        // Portal advancements
        addPortalGuide("root", "The Portal", "A world below our own, ready to be accessed...");
        addPortalGuide("altar", "Contacting the Gods", "Create an Offering Altar.");
        addPortalGuide("gaean_gem", "A Gift from our Mother", "Offer a Diamond, an Emerald and a piece of Obsidian on the Offering Altar and obtain the Gaean Gem.");
        addPortalGuide("portal_activator", "The Key and the Lock", "Obtain the Staff of Gaea and the Gaean Keystone.");
        addPortalGuide("portal", "Constructing the Gate", "Construct the Erebus portal by placing any type of leaf blocks in a frame and activate it by inserting the Staff of Gaea into the Gaean Keystone near it.");
    }

    private void addBiomeTranslations() {

    }

    private void addContainerTranslations() {
        addContainer("petrified_crafting_table", "Petrified Crafting Table");
        addChest("chest_asper", "Asper Chest", "Large Asper Chest");
        addChest("chest_bamboo", "Bamboo Chest", "Large Bamboo Chest");
        addChest("chest_baobab", "Baobab Chest", "Large Baobab Chest");
        addChest("chest_balsam", "Balsam Chest", "Large Balsam Chest");
        addChest("chest_cypress", "Cypress Chest", "Large Cypress Chest");
        addChest("chest_eucalyptus", "Eucalyptus Chest", "Large Eucalyptus Chest");
        addChest("chest_mahogany", "Mahogany Chest", "Large Mahogany Chest");
        addChest("chest_marshwood", "Marshwood Chest", "Large Marshwood Chest");
        addChest("chest_mossbark", "Mossbark Chest", "Large Mossbark Chest");
        addChest("chest_petrified", "Petrified Wood Chest", "Large Petrified Wood Chest");
        addChest("chest_rotten", "Rotten Chest", "Large Rotten Chest");
        addChest("chest_scorched", "Scorched Chest", "Large Scorched Chest");
        addChest("chest_varnished", "Varnished Chest", "Large Varnished Chest");
        addChest("chest_white", "White Chest", "Large White Chest");
        addContainer("umberfurnace", "Umber Furnace");
        addContainer("liquifier", "Honey Liquifier");
        addContainer("honeycomb_cell", "Honeycomb Cell");
        addContainer("bamboo_extender", "Bamboo Extender");
        addContainer("bamboo_crate", "Bamboo Crate");
        addContainer("colossal_crate", "Colossal Crate");
        addContainer("composter", "Organic Composter");
        addContainer("silo", "Silo Location");
        addContainer("blender", "ErebusCo. Smoothie-matic 2000");
    }

    private void addTagTranslations() {
        add(ModItemTags.COMPOSTABLE, "Compostable");
    }

    private void addEMITranslations() {
        add("emi.category.erebus.blender", "Blending");
        add("emi.category.erebus.offering_altar", "Offering Altar");
    }
}
