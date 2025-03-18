package erebus.registries;

import de.cech12.bucketlib.api.item.UniversalBucketItem;
import erebus.Erebus;
import erebus.item.PaxelItem;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Erebus.MODID);

    // MARK: Materials
    public static final DeferredItem<Item> PLATE_EXO = ITEMS.registerSimpleItem("plate_exo");
    public static final DeferredItem<Item> JADE = ITEMS.registerSimpleItem("jade");
    public static final DeferredItem<Item> SHARD_BONE = ITEMS.registerSimpleItem("shard_bone");
    public static final DeferredItem<Item> BAMBOO = ITEMS.registerSimpleItem("bamboo");
    public static final DeferredItem<Item> COMPOUND_EYES = ITEMS.registerSimpleItem("compound_eyes");
    public static final DeferredItem<Item> COMPOUND_LENS = ITEMS.registerSimpleItem("compound_lens");
    public static final DeferredItem<Item> FLY_WING = ITEMS.registerSimpleItem("fly_wing");
    public static final DeferredItem<Item> PETRIFIED_WOOD = ITEMS.registerSimpleItem("petrified_wood");
    public static final DeferredItem<Item> BIO_VELOCITY = ITEMS.registerSimpleItem("bio_velocity");
    public static final DeferredItem<Item> ELASTIC_FIBER = ITEMS.registerSimpleItem("elastic_fiber");
    public static final DeferredItem<Item> WASP_STING = ITEMS.registerSimpleItem("wasp_sting");
    public static final DeferredItem<Item> BAMBOO_SHOOT = ITEMS.registerSimpleItem("bamboo_shoot");
    public static final DeferredItem<Item> RED_GEM = ITEMS.registerSimpleItem("red_gem");
    public static final DeferredItem<Item> BIO_LUMINESCENCE = ITEMS.registerSimpleItem("bio_luminescence");
    public static final DeferredItem<Item> SUPERNATURAL_VELOCITY = ITEMS.registerSimpleItem("supernatural_velocity");
    public static final DeferredItem<Item> ALTAR_FRAGMENT = ITEMS.registerSimpleItem("altar_fragment");
    public static final DeferredItem<Item> REINFORCED_PLATE_EXO = ITEMS.registerSimpleItem("reinforced_plate_exo");
    public static final DeferredItem<Item> GLIDER_WING = ITEMS.registerSimpleItem("glider_wing");
    public static final DeferredItem<Item> SCORPION_PINCER = ITEMS.registerSimpleItem("scorpion_pincer");
    public static final DeferredItem<Item> CAMO_POWDER = ITEMS.registerSimpleItem("camo_powder");
    public static final DeferredItem<Item> NECTAR = ITEMS.registerSimpleItem("nectar");
    public static final DeferredItem<Item> HONEY_DRIP = ITEMS.registerSimpleItem("honey_drip");
    public static final DeferredItem<Item> POISON_GLAND = ITEMS.registerSimpleItem("poison_gland");
    public static final DeferredItem<Item> MUD_BRICK = ITEMS.registerSimpleItem("mud_brick");
    public static final DeferredItem<Item> WHETSTONE_POWDER = ITEMS.registerSimpleItem("whetstone_powder");
    public static final DeferredItem<Item> DRAGONFLY_WING = ITEMS.registerSimpleItem("dragonfly_wing");
    public static final DeferredItem<Item> BLUEBELL_PETAL = ITEMS.registerSimpleItem("bluebell_petal");
    public static final DeferredItem<Item> PAPYRUS = ITEMS.registerSimpleItem("papyrus");
    public static final DeferredItem<Item> ENHANCED_GLIDER_WING = ITEMS.registerSimpleItem("enhanced_glider_wing");
    public static final DeferredItem<Item> REPELLENT = ITEMS.registerSimpleItem("repellent");
    public static final DeferredItem<Item> MUCUS_CHARGE = ITEMS.registerSimpleItem("mucus_charge");
    public static final DeferredItem<Item> NETTLE_LEAVES = ITEMS.registerSimpleItem("nettle_leaves");
    public static final DeferredItem<Item> NETTLE_FLOWERS = ITEMS.registerSimpleItem("nettle_flowers");
    public static final DeferredItem<Item> DARK_FRUIT_SEEDS = ITEMS.registerSimpleItem("dark_fruit_seeds");
    public static final DeferredItem<Item> MOSS_BALL = ITEMS.registerSimpleItem("moss_ball");
    public static final DeferredItem<Item> GLOWSHROOM = ITEMS.registerSimpleItem("glowshroom");
    public static final DeferredItem<Item> PLATE_EXO_RHINO = ITEMS.registerSimpleItem("plate_exo_rhino");
    public static final DeferredItem<Item> RHINO_BEETLE_HORN = ITEMS.registerSimpleItem("rhino_beetle_horn");
    public static final DeferredItem<Item> ANT_PHEROMONES = ITEMS.registerSimpleItem("ant_pheromones");
    public static final DeferredItem<Item> GAEAN_GEM = ITEMS.registerSimpleItem("gaean_gem");
    public static final DeferredItem<Item> CRIMSON_HEART = ITEMS.registerSimpleItem("crimson_heart");
    public static final DeferredItem<Item> RESIN = ITEMS.registerSimpleItem("resin");
    public static final DeferredItem<Item> AMBER_STAR = ITEMS.registerSimpleItem("amber_star");
    public static final DeferredItem<Item> INGOT_ALUMINUM = ITEMS.registerSimpleItem("ingot_aluminum");
    public static final DeferredItem<Item> INGOT_LEAD = ITEMS.registerSimpleItem("ingot_lead");
    public static final DeferredItem<Item> INGOT_SILVER = ITEMS.registerSimpleItem("ingot_silver");
    public static final DeferredItem<Item> INGOT_TIN = ITEMS.registerSimpleItem("ingot_tin");
    public static final DeferredItem<Item> GNEISS_ROCK = ITEMS.registerSimpleItem("gneiss_rock");
    public static final DeferredItem<Item> HIDE_SHROOM = ITEMS.registerSimpleItem("hide_shroom");
    public static final DeferredItem<Item> BEETLE_RIDING_KIT = ITEMS.registerSimpleItem("beetle_riding_kit");
    public static final DeferredItem<Item> BEETLE_TAMING_AMULET = ITEMS.registerSimpleItem("beetle_taming_amulet");
    public static final DeferredItem<Item> UMBERGOLEM_CORE = ITEMS.registerSimpleItem("umbergolem_core");
    public static final DeferredItem<Item> UMBERGOLEM_HEAD = ITEMS.registerSimpleItem("umbergolem_head");
    public static final DeferredItem<Item> UMBERGOLEM_CLAW = ITEMS.registerSimpleItem("umbergolem_claw");
    public static final DeferredItem<Item> UMBERGOLEM_LEGS = ITEMS.registerSimpleItem("umbergolem_legs");
    public static final DeferredItem<Item> JADE_BERRIES = ITEMS.registerSimpleItem("jade_berries");
    public static final DeferredItem<Item> BOGMAW_ROOT = ITEMS.registerSimpleItem("bogmaw_root");
    public static final DeferredItem<Item> HYDROFUGE = ITEMS.registerSimpleItem("hydrofuge");
    public static final DeferredItem<Item> WATER_REPELLENT = ITEMS.registerSimpleItem("water_repellent");
    public static final DeferredItem<Item> SMOOTHIE_GLASS = ITEMS.registerSimpleItem("smoothie_glass");
    public static final DeferredItem<Item> MAGMA_CRAWLER_EYE = ITEMS.registerSimpleItem("magma_crawler_eye");
    public static final DeferredItem<Item> STEW_POT = ITEMS.registerSimpleItem("stew_pot");
    public static final DeferredItem<Item> TITAN_STEW = ITEMS.registerSimpleItem("titan_stew");
    public static final DeferredItem<Item> FORCE_KEY = ITEMS.registerSimpleItem("force_key");
    public static final DeferredItem<Item> SOUL_CRYSTAL = ITEMS.registerSimpleItem("soul_crystal");
    public static final DeferredItem<Item> PLATE_ZOMBIE_MANDIBLES = ITEMS.registerSimpleItem("plate_zombie_mandibles");
    public static final DeferredItem<Item> STAG_BEETLE_MANDIBLES = ITEMS.registerSimpleItem("stag_beetle_mandibles");
    public static final DeferredItem<Item> TERPSISHROOM = ITEMS.registerSimpleItem("terpsishroom");
    public static final DeferredItem<Item> BAMBOO_PIPE_WRENCH = ITEMS.registerSimpleItem("bamboo_pipe_wrench");
    public static final DeferredItem<Item> TEMPLE_ROCK = ITEMS.registerSimpleItem("temple_rock");

    // MARK: Food
    public static final DeferredItem<Item> BEETLE_LARVA_RAW = ITEMS.registerSimpleItem("beetle_larva_raw");
    public static final DeferredItem<Item> BEETLE_LARVA_COOKED = ITEMS.registerSimpleItem("beetle_larva_cooked");
    public static final DeferredItem<Item> GRASSHOPPER_LEG_RAW = ITEMS.registerSimpleItem("grasshopper_leg_raw");
    public static final DeferredItem<Item> GRASSHOPPER_LEG_COOKED = ITEMS.registerSimpleItem("grasshopper_leg_cooked");
    public static final DeferredItem<Item> TARANTULA_LEG_RAW = ITEMS.registerSimpleItem("tarantula_leg_raw");
    public static final DeferredItem<Item> TARANTULA_LEG_COOKED = ITEMS.registerSimpleItem("tarantula_leg_cooked");
    public static final DeferredItem<Item> BAMBOO_SOUP = ITEMS.registerSimpleItem("bamboo_soup");
    public static final DeferredItem<Item> MELONADE = ITEMS.registerSimpleItem("melonade");
    public static final DeferredItem<Item> MELONADE_SPARKLY = ITEMS.registerSimpleItem("melonade_sparkly");
    public static final DeferredItem<Item> LARVAE_ON_STICK = ITEMS.registerSimpleItem("larvae_on_stick");
    public static final DeferredItem<Item> HONEY_SANDWICH = ITEMS.registerSimpleItem("honey_sandwich");
    public static final DeferredItem<Item> DARK_FRUIT = ITEMS.registerSimpleItem("dark_fruit");
    public static final DeferredItem<Item> TITAN_CHOP_RAW = ITEMS.registerSimpleItem("titan_chop_raw");
    public static final DeferredItem<Item> TITAN_CHOP_COOKED = ITEMS.registerSimpleItem("titan_chop_cooked");
    public static final DeferredItem<Item> SWAMP_BERRIES = ITEMS.registerSimpleItem("swamp_berries");
    public static final DeferredItem<Item> CABBAGE = ITEMS.registerSimpleItem("cabbage");
    public static final DeferredItem<Item> TITAN_STEW_COOKED = ITEMS.registerSimpleItem("titan_stew_cooked");
    public static final DeferredItem<Item> PRICKLY_PEAR_RAW = ITEMS.registerSimpleItem("prickly_pear_raw");
    public static final DeferredItem<Item> PRICKLY_PEAR_COOKED = ITEMS.registerSimpleItem("prickly_pear_cooked");
    public static final DeferredItem<Item> DARK_FRUIT_PIE = ITEMS.registerSimpleItem("dark_fruit_pie");

    // MARK: Smoothies
    public static final DeferredItem<Item> GREEN_TEA_GRASSHOPPER = ITEMS.registerSimpleItem("green_tea_grasshopper");
    public static final DeferredItem<Item> MONEY_HONEY = ITEMS.registerSimpleItem("money_honey");
    public static final DeferredItem<Item> NOTHING_IN_THE_MIDDLE = ITEMS.registerSimpleItem("nothing_in_the_middle");
    public static final DeferredItem<Item> GREEN_GIANT = ITEMS.registerSimpleItem("green_giant");
    public static final DeferredItem<Item> SEEDY_GOODNESS = ITEMS.registerSimpleItem("seedy_goodness");
    public static final DeferredItem<Item> GIVIN_ME_THE_BLUES = ITEMS.registerSimpleItem("givin_me_the_blues");
    public static final DeferredItem<Item> HOT_HOT_BABY = ITEMS.registerSimpleItem("hot_hot_baby");
    public static final DeferredItem<Item> DONT_MEDDLE_WITH_THE_NETTLE = ITEMS.registerSimpleItem("dont_meddle_with_the_nettle");
    public static final DeferredItem<Item> LIQUID_GOLD = ITEMS.registerSimpleItem("liquid_gold");
    public static final DeferredItem<Item> BRYUFS_BREW = ITEMS.registerSimpleItem("bryufs_brew");

    // MARK: Bamboo Armor
    public static final DeferredItem<ArmorItem> BAMBOO_HELMET = ITEMS.register(
            "bamboo_helmet",
            () -> new ArmorItem(
                    ModArmorMaterials.BAMBOO_ARMOR_MATERIAL,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(11))
            )
    );

    public static final DeferredItem<ArmorItem> BAMBOO_CHESTPLATE = ITEMS.register(
            "bamboo_chestplate",
            () -> new ArmorItem(
                    ModArmorMaterials.BAMBOO_ARMOR_MATERIAL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(16))
            )
    );

    public static final DeferredItem<ArmorItem> BAMBOO_LEGGINGS = ITEMS.register(
            "bamboo_leggings",
            () -> new ArmorItem(
                    ModArmorMaterials.BAMBOO_ARMOR_MATERIAL,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(15))
            )
    );

    public static final DeferredItem<ArmorItem> BAMBOO_BOOTS = ITEMS.register(
            "bamboo_boots",
            () -> new ArmorItem(
                    ModArmorMaterials.BAMBOO_ARMOR_MATERIAL,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(13))
            )
    );

    // MARK: Exoskeleton Armor
    public static final DeferredItem<ArmorItem> EXOSKELETON_HELMET = ITEMS.register(
            "exoskeleton_helmet",
            () -> new ArmorItem(
                    ModArmorMaterials.EXOSKELETON_ARMOR_MATERIAL,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(11))
            )
    );

    public static final DeferredItem<ArmorItem> EXOSKELETON_CHESTPLATE = ITEMS.register(
            "exoskeleton_chestplate",
            () -> new ArmorItem(
                    ModArmorMaterials.EXOSKELETON_ARMOR_MATERIAL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(16))
            )
    );

    public static final DeferredItem<ArmorItem> EXOSKELETON_LEGGINGS = ITEMS.register(
            "exoskeleton_leggings",
            () -> new ArmorItem(
                    ModArmorMaterials.EXOSKELETON_ARMOR_MATERIAL,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(15))
            )
    );

    public static final DeferredItem<ArmorItem> EXOSKELETON_BOOTS = ITEMS.register(
            "exoskeleton_boots",
            () -> new ArmorItem(
                    ModArmorMaterials.EXOSKELETON_ARMOR_MATERIAL,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(13))
            )
    );

    // MARK: Reinforced Exoskeleton Armor
    public static final DeferredItem<ArmorItem> REIN_EXOSKELETON_HELMET = ITEMS.register(
            "rein_exoskeleton_helmet",
            () -> new ArmorItem(
                    ModArmorMaterials.REIN_EXOSKELETON_ARMOR_MATERIAL,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(11))
            )
    );

    public static final DeferredItem<ArmorItem> REIN_EXOSKELETON_CHESTPLATE = ITEMS.register(
            "rein_exoskeleton_chestplate",
            () -> new ArmorItem(
                    ModArmorMaterials.REIN_EXOSKELETON_ARMOR_MATERIAL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(16))
            )
    );

    public static final DeferredItem<ArmorItem> REIN_EXOSKELETON_LEGGINGS = ITEMS.register(
            "rein_exoskeleton_leggings",
            () -> new ArmorItem(
                    ModArmorMaterials.REIN_EXOSKELETON_ARMOR_MATERIAL,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(15))
            )
    );

    public static final DeferredItem<ArmorItem> REIN_EXOSKELETON_BOOTS = ITEMS.register(
            "rein_exoskeleton_boots",
            () -> new ArmorItem(
                    ModArmorMaterials.REIN_EXOSKELETON_ARMOR_MATERIAL,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(13))
            )
    );

    // MARK: Rhino Exoskeleton Armor
    public static final DeferredItem<ArmorItem> RHINO_EXOSKELETON_HELMET = ITEMS.register(
            "rhino_exoskeleton_helmet",
            () -> new ArmorItem(
                    ModArmorMaterials.RHINO_ARMOR_MATERIAL,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(11))
            )
    );

    public static final DeferredItem<ArmorItem> RHINO_EXOSKELETON_CHESTPLATE = ITEMS.register(
            "rhino_exoskeleton_chestplate",
            () -> new ArmorItem(
                    ModArmorMaterials.RHINO_ARMOR_MATERIAL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(16))
            )
    );

    public static final DeferredItem<ArmorItem> RHINO_EXOSKELETON_LEGGINGS = ITEMS.register(
            "rhino_exoskeleton_leggings",
            () -> new ArmorItem(
                    ModArmorMaterials.RHINO_ARMOR_MATERIAL,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(15))
            )
    );

    public static final DeferredItem<ArmorItem> RHINO_EXOSKELETON_BOOTS = ITEMS.register(
            "rhino_exoskeleton_boots",
            () -> new ArmorItem(
                    ModArmorMaterials.RHINO_ARMOR_MATERIAL,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(13))
            )
    );

    // MARK: Jade Armor
    public static final DeferredItem<ArmorItem> JADE_HELMET = ITEMS.register(
            "jade_helmet",
            () -> new ArmorItem(
                    ModArmorMaterials.JADE_ARMOR_MATERIAL,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(11))
            )
    );

    public static final DeferredItem<ArmorItem> JADE_CHESTPLATE = ITEMS.register(
            "jade_chestplate",
            () -> new ArmorItem(
                    ModArmorMaterials.JADE_ARMOR_MATERIAL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(16))
            )
    );

    public static final DeferredItem<ArmorItem> JADE_LEGGINGS = ITEMS.register(
            "jade_leggings",
            () -> new ArmorItem(
                    ModArmorMaterials.JADE_ARMOR_MATERIAL,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(15))
            )
    );

    public static final DeferredItem<ArmorItem> JADE_BOOTS = ITEMS.register(
            "jade_boots",
            () -> new ArmorItem(
                    ModArmorMaterials.JADE_ARMOR_MATERIAL,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(13))
            )
    );

    // MARK: Jade Tools
    public static final DeferredItem<SwordItem> JADE_SWORD = ITEMS.register(
            "jade_sword",
            () -> new SwordItem(
                    ModToolMaterials.JADE_TIER,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(
                                    ModToolMaterials.JADE_TIER,
                                    3,
                                    -2.4F
                            )
                    )
            )
    );

    public static final DeferredItem<PickaxeItem> JADE_PICKAXE = ITEMS.register(
            "jade_pickaxe",
            () -> new PickaxeItem(
                    ModToolMaterials.JADE_TIER,
                    new Item.Properties().attributes(
                            PickaxeItem.createAttributes(
                                    ModToolMaterials.JADE_TIER,
                                    1,
                                    -2.8F
                            )
                    )
            )
    );

    public static final DeferredItem<AxeItem> JADE_AXE = ITEMS.register(
            "jade_axe",
            () -> new AxeItem(
                    ModToolMaterials.JADE_TIER,
                    new Item.Properties().attributes(
                            AxeItem.createAttributes(
                                    ModToolMaterials.JADE_TIER,
                                    5,
                                    -3.0F
                            )
                    )
            )
    );

    public static final DeferredItem<ShovelItem> JADE_SHOVEL = ITEMS.register(
            "jade_shovel",
            () -> new ShovelItem(
                    ModToolMaterials.JADE_TIER,
                    new Item.Properties().attributes(
                            ShovelItem.createAttributes(
                                    ModToolMaterials.JADE_TIER,
                                    1.5F,
                                    -3.0F
                            )
                    )
            )
    );

    public static final DeferredItem<PaxelItem> JADE_PAXEL = ITEMS.register(
            "jade_paxel",
            () -> new PaxelItem(
                    ModToolMaterials.JADE_PAXEL_TIER,
                    new Item.Properties().attributes(
                            PaxelItem.createAttributes(
                                    ModToolMaterials.JADE_PAXEL_TIER,
                                    1.0F,
                                    -2.8F
                            )
                    )
            )
    );

    public static final DeferredItem<HoeItem> JADE_HOE = ITEMS.register(
            "jade_hoe",
            () -> new HoeItem(
                    ModToolMaterials.JADE_TIER,
                    new Item.Properties().attributes(
                            HoeItem.createAttributes(
                                    ModToolMaterials.JADE_TIER,
                                    -3.0F,
                                    0.0F
                            )
                    )
            )
    );

    // MARK: Misc Armor & Weapons
    public static final DeferredItem<ArmorItem> REIN_COMPOUND_GOGGLES = ITEMS.register(
            "rein_compound_goggles",
            () -> new ArmorItem(
                    ModArmorMaterials.REIN_COMPOUND_GOGGLES_ARMOR_MATERIAL,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(11))
            )
    );

    public static final DeferredItem<ArmorItem> COMPOUND_GOGGLES = ITEMS.register(
            "compound_goggles",
            () -> new ArmorItem(
                    ModArmorMaterials.GOGGLES_ARMOR_MATERIAL,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(11))
            )
    );

    public static final DeferredItem<ArmorItem> MUSHROOM_HELMET = ITEMS.register(
            "mushroom_helmet",
            () -> new ArmorItem(
                    ModArmorMaterials.MUSHROOM_HELM_ARMOR_MATERIAL,
                    ArmorItem.Type.HELMET,
                    new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(11))
            )
    );

    public static final DeferredItem<ArmorItem> GLIDER_CHESTPLATE = ITEMS.register(
            "glider_chestplate",
            () -> new ArmorItem(
                    ModArmorMaterials.REIN_EXOSKELETON_ARMOR_MATERIAL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(16))
            )
    );

    public static final DeferredItem<ArmorItem> GLIDER_CHESTPLATE_POWERED = ITEMS.register(
            "glider_chestplate_powered",
            () -> new ArmorItem(
                    ModArmorMaterials.REIN_EXOSKELETON_ARMOR_MATERIAL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(16))
            )
    );

    public static final DeferredItem<ArmorItem> SPIDER_T_SHIRT = ITEMS.register(
            "spider_t_shirt",
            () -> new ArmorItem(
                    ModArmorMaterials.SPIDER_T_SHIRT_ARMOR_MATERIAL,
                    ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(16))
            )
    );

    public static final DeferredItem<ArmorItem> SPRINT_LEGGINGS = ITEMS.register(
            "sprint_leggings",
            () -> new ArmorItem(
                    ModArmorMaterials.CENTIPEDE_ARMOR_MATERIAL,
                    ArmorItem.Type.LEGGINGS,
                    new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(15))
            )
    );

    public static final DeferredItem<ArmorItem> JUMP_BOOTS = ITEMS.register(
            "jump_boots",
            () -> new ArmorItem(
                    ModArmorMaterials.JUMP_BOOTS_ARMOR_MATERIAL,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(13))
            )
    );

    public static final DeferredItem<ArmorItem> WATER_STRIDERS = ITEMS.register(
            "water_striders",
            () -> new ArmorItem(
                    ModArmorMaterials.WATER_STRIDERS_ARMOR_MATERIAL,
                    ArmorItem.Type.BOOTS,
                    new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(13))
            )
    );
    public static final DeferredItem<Item> ENHANCED_SCORPION_PINCER = ITEMS.registerSimpleItem("enhanced_scorpion_pincer");
    public static final DeferredItem<Item> WAR_HAMMER = ITEMS.registerSimpleItem("war_hammer");
    public static final DeferredItem<Item> WEB_SLINGER = ITEMS.registerSimpleItem("web_slinger");
    public static final DeferredItem<Item> WEB_SLINGER_WITHER = ITEMS.registerSimpleItem("web_slinger_wither");
    public static final DeferredItem<Item> MAX_SPEED_BOW = ITEMS.registerSimpleItem("max_speed_bow");

    // MARK: Shields
    public static final DeferredItem<ShieldItem> BAMBOO_SHIELD = ITEMS.register("bamboo_shield", () -> new ShieldItem(new Item.Properties().durability(336)));
    public static final DeferredItem<ShieldItem> EXOSKELETON_SHIELD = ITEMS.register("exoskeleton_shield", () -> new ShieldItem(new Item.Properties().durability(336)));
    public static final DeferredItem<ShieldItem> JADE_SHIELD = ITEMS.register("jade_shield", () -> new ShieldItem(new Item.Properties().durability(336)));
    public static final DeferredItem<ShieldItem> REIN_EXOSKELETON_SHIELD = ITEMS.register("rein_exoskeleton_shield", () -> new ShieldItem(new Item.Properties().durability(336)));
    public static final DeferredItem<ShieldItem> RHINO_EXOSKELETON_SHIELD = ITEMS.register("rhino_exoskeleton_shield", () -> new ShieldItem(new Item.Properties().durability(336)));

    // MARK: Misc
    public static final DeferredItem<Item> WAND_OF_ANIMATION = ITEMS.registerSimpleItem("wand_of_animation");
    public static final DeferredItem<Item> WAND_OF_PRESERVATION = ITEMS.registerSimpleItem("wand_of_preservation");
    public static final DeferredItem<Item> PORTAL_ACTIVATOR = ITEMS.registerSimpleItem("portal_activator");
    public static final DeferredItem<Item> WOODLOUSE_BALL = ITEMS.registerSimpleItem("woodlouse_ball");
    public static final DeferredItem<Item> NECTAR_COLLECTOR = ITEMS.registerSimpleItem("nectar_collector");
    public static final DeferredItem<Item> ANT_TAMING_AMULET = ITEMS.registerSimpleItem("ant_taming_amulet");
    public static final DeferredItem<Item> BEE_TAMING_AMULET = ITEMS.registerSimpleItem("bee_taming_amulet");
    public static final DeferredItem<Item> WASP_SWORD = ITEMS.registerSimpleItem("wasp_sword");
    public static final DeferredItem<Item> WASP_DAGGER = ITEMS.registerSimpleItem("wasp_dagger");
    public static final DeferredItem<Item> ANTI_VENOM_BOTTLE = ITEMS.registerSimpleItem("anti_venom_bottle");
    public static final DeferredItem<Item> DEATH_COMPASS = ITEMS.registerSimpleItem("death_compass");
    public static final DeferredItem<Item> ROLLED_NEWSPAPER = ITEMS.registerSimpleItem("rolled_newspaper");
    public static final DeferredItem<Item> BAMBUCKET = ITEMS.register(
            "bambucket",
            () -> new UniversalBucketItem(
                    new UniversalBucketItem.Properties()
            )
    );
    public static final DeferredItem<Item> HOMING_BEECON = ITEMS.registerSimpleItem("homing_beecon");
    public static final DeferredItem<Item> HOMING_BEECON_ADVANCED = ITEMS.registerSimpleItem("homing_beecon_advanced");
    public static final DeferredItem<Item> SPRAY_CAN = ITEMS.registerSimpleItem("spray_can");
    public static final DeferredItem<Item> WHETSTONE = ITEMS.registerSimpleItem("whetstone");
    public static final DeferredItem<Item> COMPOST = ITEMS.registerSimpleItem("compost");
    public static final DeferredItem<Item> PLANTICIDE = ITEMS.registerSimpleItem("planticide");
    public static final DeferredItem<Item> SMOOTHIE_BOOK = ITEMS.registerSimpleItem("smoothie_book");
    public static final DeferredItem<Item> HORN_OF_SUMMONING = ITEMS.registerSimpleItem("horn_of_summoning");

    // MARK: Idols
    public static final DeferredItem<Item> MUD_SCARAB = ITEMS.registerSimpleItem("mud_scarab");
    public static final DeferredItem<Item> IRON_SCARAB = ITEMS.registerSimpleItem("iron_scarab");
    public static final DeferredItem<Item> GOLD_SCARAB = ITEMS.registerSimpleItem("gold_scarab");
    public static final DeferredItem<Item> JADE_SCARAB = ITEMS.registerSimpleItem("jade_scarab");
    public static final DeferredItem<Item> MUD_UMBERGOLEM = ITEMS.registerSimpleItem("mud_umbergolem");
    public static final DeferredItem<Item> IRON_UMBERGOLEM = ITEMS.registerSimpleItem("iron_umbergolem");
    public static final DeferredItem<Item> GOLD_UMBERGOLEM = ITEMS.registerSimpleItem("gold_umbergolem");
    public static final DeferredItem<Item> JADE_UMBERGOLEM = ITEMS.registerSimpleItem("jade_umbergolem");

    // MARK: Maps
    public static final DeferredItem<Item> EREBUS_MAP = ITEMS.registerSimpleItem("erebus_map");
    public static final DeferredItem<Item> EREBUS_MAP_FILLED = ITEMS.registerSimpleItem("erebus_map_filled");

    // MARK: Plants
    public static final DeferredItem<Item> TURNIP = ITEMS.registerSimpleItem("turnip");
    public static final DeferredItem<Item> CABBAGE_SEEDS = ITEMS.registerSimpleItem("cabbage_seeds");
    public static final DeferredItem<Item> MANDRAKE_ROOT = ITEMS.registerSimpleItem("mandrake_root");
    public static final DeferredItem<Item> SEED_BLACK = ITEMS.registerSimpleItem("seed_black");
    public static final DeferredItem<Item> SEED_RED = ITEMS.registerSimpleItem("seed_red");
    public static final DeferredItem<Item> SEED_BROWN = ITEMS.registerSimpleItem("seed_brown");
    public static final DeferredItem<Item> SEED_BLUE = ITEMS.registerSimpleItem("seed_blue");
    public static final DeferredItem<Item> SEED_PURPLE = ITEMS.registerSimpleItem("seed_purple");
    public static final DeferredItem<Item> SEED_CYAN = ITEMS.registerSimpleItem("seed_cyan");
    public static final DeferredItem<Item> SEED_LIGHT_GRAY = ITEMS.registerSimpleItem("seed_light_gray");
    public static final DeferredItem<Item> SEED_GRAY = ITEMS.registerSimpleItem("seed_gray");
    public static final DeferredItem<Item> SEED_PINK = ITEMS.registerSimpleItem("seed_pink");
    public static final DeferredItem<Item> SEED_YELLOW = ITEMS.registerSimpleItem("seed_yellow");
    public static final DeferredItem<Item> SEED_LIGHT_BLUE = ITEMS.registerSimpleItem("seed_light_blue");
    public static final DeferredItem<Item> SEED_MAGENTA = ITEMS.registerSimpleItem("seed_magenta");
    public static final DeferredItem<Item> SEED_ORANGE = ITEMS.registerSimpleItem("seed_orange");
    public static final DeferredItem<Item> SEED_WHITE = ITEMS.registerSimpleItem("seed_white");
    public static final DeferredItem<Item> SEED_RAINBOW = ITEMS.registerSimpleItem("seed_rainbow");

    public static final DeferredItem<Item> LIFE_BLOOD = ITEMS.registerSimpleItem("life_blood");
    public static final DeferredItem<Item> HEART_BERRIES = ITEMS.registerSimpleItem("heart_berries");
    public static final DeferredItem<Item> STAG_HEART_RAW = ITEMS.registerSimpleItem("stag_heart_raw");
    public static final DeferredItem<Item> STAG_HEART_COOKED = ITEMS.registerSimpleItem("stag_heart_cooked");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
