package erebus.datagen;

import erebus.datagen.providers.ModLangProvider;
import erebus.registries.ModItems;
import erebus.registries.blocks.providers.*;
import erebus.registries.data.ModTags;
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
        addBlock(OreBlocks.IRON, "Iron Ore");
        addBlock(OreBlocks.GOLD, "Gold Ore");
        addBlock(OreBlocks.DIAMOND, "Diamond Ore");
        addBlock(OreBlocks.EMERALD, "Emerald Ore");
        addBlock(OreBlocks.LAPIS, "Lapis Lazuli Ore");
        addBlock(OreBlocks.QUARTZ, "Quartz Ore");
        addBlock(OreBlocks.PETRIFIED_QUARTZ, "Petrified Quartz Ore");
        addBlock(OreBlocks.COAL, "Coal Ore");
        addBlock(OreBlocks.COPPER, "Copper Ore");
        addBlock(OreBlocks.TIN, "Tin Ore");
        addBlock(OreBlocks.SILVER, "Silver Ore");
        addBlock(OreBlocks.ALUMINUM, "Aluminum Ore");
        addBlock(OreBlocks.LEAD, "Lead Ore");
        addBlock(OreBlocks.JADE, "Jade Ore");
        addBlock(OreBlocks.ENCRUSTED_DIAMOND, "Volcanic Diamond Ore");
        addBlock(OreBlocks.FOSSIL, "Fossil Ore");
        addBlock(OreBlocks.GNEISS, "Gneiss Ore");
        addBlock(OreBlocks.TEMPLE, "Temple Ore");
        addBlock(OreBlocks.PETRIFIED_WOOD, "Petrified Wood Ore");

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
        addBlock(SlabBlocks.PLANKS_BAOBAB, "Baobab Slab");
        addBlock(SlabBlocks.PLANKS_EUCALYPTUS, "Eucalyptus Slab");
        addBlock(SlabBlocks.PLANKS_MAHOGANY, "Mahogany Slab");
        addBlock(SlabBlocks.PLANKS_MOSSBARK, "Mossbark Slab");
        addBlock(SlabBlocks.PLANKS_ASPER, "Asper Slab");
        addBlock(SlabBlocks.PLANKS_CYPRESS, "Cypress Slab");
        addBlock(SlabBlocks.PLANKS_BALSAM, "Balsam Slab");
        addBlock(SlabBlocks.PLANKS_WHITE, "White Slab");
        addBlock(SlabBlocks.PLANKS_BAMBOO, "Bamboo Slab");
        addBlock(SlabBlocks.PLANKS_ROTTEN, "Rotten Slab");
        addBlock(SlabBlocks.PLANKS_MARSHWOOD, "Marshwood Slab");
        addBlock(SlabBlocks.PLANKS_SCORCHED, "Scorched Slab");
        addBlock(SlabBlocks.PLANKS_VARNISHED, "Varnished Slab");
        addBlock(SlabBlocks.PLANKS_PETRIFIED, "Petrified Wood Slab");

        // MARK: Slabs Stone
        addBlock(SlabBlocks.UMBERSTONE, "Umberstone Slab");
        addBlock(SlabBlocks.UMBERCOBBLE, "Umbercobble Slab");
        addBlock(SlabBlocks.UMBERCOBBLE_MOSSY, "Mossy Umbercobble Slab");
        addBlock(SlabBlocks.UMBERCOBBLE_WEBBED, "Webbed Umbercobble Slab");
        addBlock(SlabBlocks.UMBERSTONE_BRICKS, "Umberbrick Slab");
        addBlock(SlabBlocks.UMBERTILE_SMOOTH, "Smooth Umbertile Slab");
        addBlock(SlabBlocks.UMBERTILE_SMOOTH_SMALL, "Small Umbertile Slab");
        addBlock(SlabBlocks.UMBERPAVER, "Umberpaver Slab");
        addBlock(SlabBlocks.UMBERPAVER_MOSSY, "Mossy Umberpaver Slab");
        addBlock(SlabBlocks.UMBERPAVER_WEBBED, "Webbed Umberpaver Slab");
        addBlock(SlabBlocks.AMBER, "Amber Slab");
        addBlock(SlabBlocks.AMBER_BRICKS, "Amber Brick Slab");
        addBlock(SlabBlocks.MUD_BRICKS, "Mud Brick Slab");
        addBlock(SlabBlocks.MIR_BRICKS, "Mir Brick Slab");

        // MARK: Stairs Wood
        addBlock(StairBlocks.BAOBAB, "Baobab Stairs");
        addBlock(StairBlocks.EUCALYPTUS, "Eucalyptus Stairs");
        addBlock(StairBlocks.MAHOGANY, "Mahogany Stairs");
        addBlock(StairBlocks.MOSSBARK, "Mossbark Stairs");
        addBlock(StairBlocks.ASPER, "Asper Stairs");
        addBlock(StairBlocks.CYPRESS, "Cypress Stairs");
        addBlock(StairBlocks.BALSAM, "Balsam Stairs");
        addBlock(StairBlocks.WHITE, "White Stairs");
        addBlock(StairBlocks.BAMBOO, "Bamboo Stairs");
        addBlock(StairBlocks.ROTTEN, "Rotten Stairs");
        addBlock(StairBlocks.MARSHWOOD, "Marshwood Stairs");
        addBlock(StairBlocks.SCORCHED, "Scorched Stairs");
        addBlock(StairBlocks.VARNISHED, "Varnished Stairs");
        addBlock(StairBlocks.PETRIFIED, "Petrified Wood Stairs");

        // MARK: Stairs Stone
        addBlock(StairBlocks.UMBERSTONE, "Umberstone Stairs");
        addBlock(StairBlocks.UMBERCOBBLE, "Umbercobble Stairs");
        addBlock(StairBlocks.UMBERCOBBLE_MOSSY, "Mossy Umbercobble Stairs");
        addBlock(StairBlocks.UMBERCOBBLE_WEBBED, "Webbed Umbercobble Stairs");
        addBlock(StairBlocks.UMBERSTONE_BRICKS, "Umberbrick Stairs");
        addBlock(StairBlocks.UMBERTILE_SMOOTH, "Smooth Umbertile Stairs");
        addBlock(StairBlocks.UMBERTILE_SMOOTH_SMALL, "Small Umbertile Stairs");
        addBlock(StairBlocks.UMBERPAVER, "Umberpaver Stairs");
        addBlock(StairBlocks.UMBERPAVER_MOSSY, "Mossy Umberpaver Stairs");
        addBlock(StairBlocks.UMBERPAVER_WEBBED, "Webbed Umberpaver Stairs");
        addBlock(StairBlocks.AMBER, "Amber Stairs");
        addBlock(StairBlocks.AMBER_BRICKS, "Amber Brick Stairs");
        addBlock(StairBlocks.MUD_BRICKS, "Mud Brick Stairs");
        addBlock(StairBlocks.MIR_BRICKS, "Mir Brick Stairs");

        // MARK: Doors
        addBlock(DoorBlocks.BAOBAB, "Baobab Door");
        addBlock(DoorBlocks.EUCALYPTUS, "Eucalyptus Door");
        addBlock(DoorBlocks.MAHOGANY, "Mahogany Door");
        addBlock(DoorBlocks.MOSSBARK, "Mossbark Door");
        addBlock(DoorBlocks.ASPER, "Asper Door");
        addBlock(DoorBlocks.CYPRESS, "Cypress Door");
        addBlock(DoorBlocks.BALSAM, "Balsam Door");
        addBlock(DoorBlocks.WHITE, "White Door");
        addBlock(DoorBlocks.ROTTEN, "Rotten Door");
        addBlock(DoorBlocks.MARSHWOOD, "Marshwood Door");
        addBlock(DoorBlocks.SCORCHED, "Scorched Door");

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
        addBlock(PlantBlocks.MOSS_CULTIVATED, "Cultivated Moss");
        addBlock(PlantBlocks.MOULD_CULTIVATED, "Cultivated Mould");
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
        addBlock(OtherBlocks.VELOCITY_BLOCK, "Velocity Block");
        addBlock(OtherBlocks.VELOCITY_BLOCK_LIGHTNING_SPEED, "Lightning Speed Velocity Block");
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
        addBlock(OtherBlocks.GLOW_GEM_ACTIVE, "Glowing Gem");
        addBlock(OtherBlocks.GLOW_GEM_INACTIVE, "Glowing Gem");
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
        addEntity(ModEntities.BLACK_ANT, "Black Ant");
        addEntity(ModEntities.PUNCHROOM, "Punchroom");
        addEntity(ModEntities.CROP_WEEVIL, "Crop Weevil");
        addEntity(ModEntities.FUNGAL_WEEVIL, "Fungal Weevil");
        addEntity(ModEntities.BED_BUG, "Bed Bug");
        addEntity(ModEntities.HONEY_POT_ANT, "Honey Pot Ant");
        addEntity(ModEntities.ZOMBIE_ANT, "Zombie Ant");
        addEntity(ModEntities.ZOMBIE_ANT_SOLDIER, "Zombie Ant Soldier");

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
        addContainer("petrified_wood_chest", "Petrified Wood Chest");
        addContainer("petrified_wood_double_chest", "Petrified Wood Double Chest");
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
        add(ModTags.COMPOSTABLE, "Compostable");
    }

    private void addEMITranslations() {
        add("emi.category.erebus.blender", "Blending");
        add("emi.category.erebus.offering_altar", "Offering Altar");
    }
}
