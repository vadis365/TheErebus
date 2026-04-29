package erebus.registries.item;

import erebus.Erebus;
import erebus.item.*;
import erebus.item.armour.CompoundGoggles;
import erebus.item.armour.JumpBoots;
import erebus.item.armour.SprintLeggings;
import erebus.item.blocks.BambooCrateItem;
import erebus.item.blocks.FluidJarBlockItem;
import erebus.item.blocks.LiquifierBlockItem;
import erebus.item.shield.ErebusShieldItem;
import erebus.item.shield.type.*;
import erebus.item.wand.WandOfAnimationItem;
import erebus.item.wand.WandOfPreservationItem;
import erebus.registries.ModFluids;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.data.ModArmorMaterials;
import erebus.registries.data.ModToolMaterials;
import erebus.registries.entity.ModEntities;
import erebus.registries.helpers.ModItemHelpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems extends ModItemHelpers {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Erebus.MODID);

    // MARK: Materials
    public static final DeferredItem<Item> PLATE_EXO = registerItem("plate_exo");
    public static final DeferredItem<Item> JADE = registerItem("jade");
    public static final DeferredItem<Item> SHARD_BONE = registerItem("shard_bone");
    public static final DeferredItem<Item> BAMBOO = registerItem("bamboo");
    public static final DeferredItem<Item> COMPOUND_EYES = registerItem("compound_eyes");
    public static final DeferredItem<Item> COMPOUND_LENS = registerItem("compound_lens");
    public static final DeferredItem<Item> FLY_WING = registerItem("fly_wing");
    public static final DeferredItem<Item> PETRIFIED_WOOD = registerItem("petrified_wood");
    public static final DeferredItem<Item> BIO_VELOCITY = registerItem("bio_velocity");
    public static final DeferredItem<Item> ELASTIC_FIBER = registerItem("elastic_fiber");
    public static final DeferredItem<Item> WASP_STING = registerItem("wasp_sting");
    public static final DeferredItem<Item> BAMBOO_SHOOT = registerItem("bamboo_shoot");
    public static final DeferredItem<Item> RED_GEM = registerItem("red_gem");
    public static final DeferredItem<Item> BIO_LUMINESCENCE = registerItem("bio_luminescence");
    public static final DeferredItem<Item> SUPERNATURAL_VELOCITY = registerItem("supernatural_velocity");
    public static final DeferredItem<Item> ALTAR_FRAGMENT = registerItem("altar_fragment");
    public static final DeferredItem<Item> REINFORCED_PLATE_EXO = registerItem("reinforced_plate_exo");
    public static final DeferredItem<Item> GLIDER_WING = registerItem("glider_wing");
    public static final DeferredItem<Item> SCORPION_PINCER = registerItem("scorpion_pincer");
    public static final DeferredItem<Item> CAMO_POWDER = registerItem("camo_powder", CamoPowderItem::new);
    public static final DeferredItem<Item> NECTAR = registerItem("nectar");
    public static final DeferredItem<Item> HONEY_DRIP = registerItem("honey_drip");
    public static final DeferredItem<Item> POISON_GLAND = registerItem("poison_gland");
    public static final DeferredItem<Item> MUD_BRICK = registerItem("mud_brick");
    public static final DeferredItem<Item> WHETSTONE_POWDER = registerItem("whetstone_powder");
    public static final DeferredItem<Item> DRAGONFLY_WING = registerItem("dragonfly_wing");
    public static final DeferredItem<Item> BLUEBELL_PETAL = registerItem("bluebell_petal");
    public static final DeferredItem<Item> PAPYRUS = registerItem("papyrus");
    public static final DeferredItem<Item> ENHANCED_GLIDER_WING = registerItem("enhanced_glider_wing");
    public static final DeferredItem<Item> REPELLENT = registerItem("repellent");
    public static final DeferredItem<Item> MUCUS_CHARGE = registerItem("mucus_charge");
    public static final DeferredItem<Item> NETTLE_LEAVES = registerItem("nettle_leaves");
    public static final DeferredItem<Item> NETTLE_FLOWERS = registerItem("nettle_flowers");
    public static final DeferredItem<Item> DARK_FRUIT_SEEDS = registerItem("dark_fruit_seeds");
    public static final DeferredItem<Item> MOSS_BALL = registerItem("moss_ball");
    public static final DeferredItem<Item> GLOWSHROOM = registerItem("glowshroom");
    public static final DeferredItem<Item> PLATE_EXO_RHINO = registerItem("plate_exo_rhino");
    public static final DeferredItem<Item> RHINO_BEETLE_HORN = registerItem("rhino_beetle_horn");
    public static final DeferredItem<Item> ANT_PHEROMONES = registerItem("ant_pheromones");
    public static final DeferredItem<Item> GAEAN_GEM = registerItem("gaean_gem");
    public static final DeferredItem<Item> CRIMSON_HEART = registerItem("crimson_heart");
    public static final DeferredItem<Item> RESIN = registerItem("resin");
    public static final DeferredItem<Item> AMBER_STAR = registerItem("amber_star");
    public static final DeferredItem<Item> INGOT_ALUMINUM = registerItem("ingot_aluminum");
    public static final DeferredItem<Item> INGOT_LEAD = registerItem("ingot_lead");
    public static final DeferredItem<Item> INGOT_SILVER = registerItem("ingot_silver");
    public static final DeferredItem<Item> INGOT_TIN = registerItem("ingot_tin");
    public static final DeferredItem<Item> GNEISS_ROCK = registerItem("gneiss_rock");
    public static final DeferredItem<Item> HIDE_SHROOM = registerItem("hide_shroom");
    public static final DeferredItem<Item> BEETLE_RIDING_KIT = registerItem("beetle_riding_kit");
    public static final DeferredItem<Item> BEETLE_TAMING_AMULET = registerItem("beetle_taming_amulet");
    public static final DeferredItem<Item> UMBERGOLEM_CORE = registerItem("umbergolem_core");
    public static final DeferredItem<Item> UMBERGOLEM_HEAD = registerItem("umbergolem_head");
    public static final DeferredItem<Item> UMBERGOLEM_CLAW = registerItem("umbergolem_claw");
    public static final DeferredItem<Item> UMBERGOLEM_LEGS = registerItem("umbergolem_legs");
    public static final DeferredItem<Item> JADE_BERRIES = registerItem("jade_berries", () -> new BlockItem(ModBlocks.JADE_BERRY_BUSH.get(), new Item.Properties().food(ModFoods.JADE_BERRIES).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("jade_berries")))));
    public static final DeferredItem<Item> BOGMAW_ROOT = registerItem("bogmaw_root");
    public static final DeferredItem<Item> HYDROFUGE = registerItem("hydrofuge");
    public static final DeferredItem<Item> WATER_REPELLENT = registerItem("water_repellent");
    public static final DeferredItem<Item> SMOOTHIE_GLASS = registerItem("smoothie_glass");
    public static final DeferredItem<Item> MAGMA_CRAWLER_EYE = registerItem("magma_crawler_eye");
    public static final DeferredItem<Item> STEW_POT = registerItem("stew_pot");
    public static final DeferredItem<Item> TITAN_STEW = registerItem("titan_stew");
    public static final DeferredItem<Item> FORCE_KEY = registerItem("force_key");
    public static final DeferredItem<Item> SOUL_CRYSTAL = registerItem("soul_crystal");
    public static final DeferredItem<Item> PLATE_ZOMBIE_ANT = registerItem("plate_zombie_ant");
    public static final DeferredItem<Item> STAG_BEETLE_MANDIBLES = registerItem("stag_beetle_mandibles");
    public static final DeferredItem<Item> TERPSISHROOM = registerItem("terpsishroom");
    public static final DeferredItem<Item> BAMBOO_PIPE_WRENCH = registerItem("bamboo_pipe_wrench", BambooPipeWrenchItem::new);
    public static final DeferredItem<Item> TEMPLE_ROCK = registerItem("temple_rock");

    // MARK: Food
    public static final DeferredItem<Item> BEETLE_LARVA_RAW = registerFoodItem("beetle_larva_raw", ModFoods.BEETLE_LARVA_RAW, ModConsumables.BEETLE_LARVA_RAW);
    public static final DeferredItem<Item> BEETLE_LARVA_COOKED = registerFoodItem("beetle_larva_cooked", ModFoods.BEETLE_LARVA_COOKED, ModConsumables.food().build());
    public static final DeferredItem<Item> GRASSHOPPER_LEG_RAW = registerFoodItem("grasshopper_leg_raw", ModFoods.GRASSHOPPER_LEG_RAW, ModConsumables.food().build());
    public static final DeferredItem<Item> GRASSHOPPER_LEG_COOKED = registerFoodItem("grasshopper_leg_cooked", ModFoods.GRASSHOPPER_LEG_COOKED, ModConsumables.food().build());
    public static final DeferredItem<Item> TARANTULA_LEG_RAW = registerFoodItem("tarantula_leg_raw", ModFoods.TARANTULA_LEG_RAW, ModConsumables.food().build());
    public static final DeferredItem<Item> TARANTULA_LEG_COOKED = registerFoodItem("tarantula_leg_cooked", ModFoods.TARANTULA_LEG_COOKED, ModConsumables.food().build());
    public static final DeferredItem<Item> BAMBOO_SOUP = registerFoodItem("bamboo_soup", ModFoods.BAMBOO_SOUP, ModConsumables.food().build(), Items.BOWL);
    public static final DeferredItem<Item> MELONADE = registerFoodItem("melonade", ModFoods.MELONADE, ModConsumables.smoothie().build(), SMOOTHIE_GLASS);
    public static final DeferredItem<Item> MELONADE_SPARKLY = registerFoodItem("melonade_sparkly", ModFoods.MELONADE_SPARKLY, ModConsumables.MELONADE_SPARKLY, SMOOTHIE_GLASS);
    public static final DeferredItem<Item> LARVAE_ON_STICK = registerFoodItem("larvae_on_stick", ModFoods.LARVAE_ON_STICK, ModConsumables.food().build(), Items.STICK);
    public static final DeferredItem<Item> HONEY_SANDWICH = registerFoodItem("honey_sandwich", ModFoods.HONEY_SANDWICH, ModConsumables.food().build());
    public static final DeferredItem<Item> DARK_FRUIT = registerFoodItem("dark_fruit", ModFoods.DARK_FRUIT, ModConsumables.food().build());
    public static final DeferredItem<Item> TITAN_CHOP_RAW = registerFoodItem("titan_chop_raw", ModFoods.TITAN_CHOP_RAW, ModConsumables.food().build());
    public static final DeferredItem<Item> TITAN_CHOP_COOKED = registerFoodItem("titan_chop_cooked", ModFoods.TITAN_CHOP_COOKED, ModConsumables.TITAN_CHOP_COOKED);
    public static final DeferredItem<Item> SWAMP_BERRIES = ITEMS.register("swamp_berries", () -> new BlockItem(ModBlocks.SWAMP_BERRY_BUSH.get(), new Item.Properties().food(ModFoods.SWAMP_BERRIES).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("swamp_berries")))));
    public static final DeferredItem<Item> CABBAGE = registerFoodItem("cabbage", ModFoods.CABBAGE, ModConsumables.food().build());
    public static final DeferredItem<Item> TITAN_STEW_COOKED = registerFoodItem("titan_stew_cooked", ModFoods.TITAN_STEW_COOKED, ModConsumables.food().build(), STEW_POT);
    public static final DeferredItem<Item> PRICKLY_PEAR_RAW = registerFoodItem("prickly_pear_raw", ModFoods.PRICKLY_PEAR_RAW, ModConsumables.PRICKLY_PEAR);
    public static final DeferredItem<Item> PRICKLY_PEAR_COOKED = registerFoodItem("prickly_pear_cooked", ModFoods.PRICKLY_PEAR_COOKED, ModConsumables.PRICKLY_PEAR);
    public static final DeferredItem<Item> DARK_FRUIT_PIE = registerFoodItem("dark_fruit_pie", ModFoods.DARK_FRUIT_PIE, ModConsumables.food().build());

    // MARK: Smoothies
    public static final DeferredItem<Item> GREEN_TEA_GRASSHOPPER = registerFoodItem("green_tea_grasshopper", ModFoods.GREEN_TEA_GRASSHOPPER, ModConsumables.GREEN_TEA_GRASSHOPPER);
    public static final DeferredItem<Item> MONEY_HONEY = registerFoodItem("money_honey", ModFoods.MONEY_HONEY, ModConsumables.MONEY_HONEY);
    public static final DeferredItem<Item> NOTHING_IN_THE_MIDDLE = registerFoodItem("nothing_in_the_middle", ModFoods.NOTHING_IN_THE_MIDDLE, ModConsumables.NOTHING_IN_THE_MIDDLE);
    public static final DeferredItem<Item> GREEN_GIANT = registerFoodItem("green_giant", ModFoods.GREEN_GIANT, ModConsumables.GREEN_GIANT);
    public static final DeferredItem<Item> SEEDY_GOODNESS = registerFoodItem("seedy_goodness", ModFoods.SEEDY_GOODNESS, ModConsumables.SEEDY_GOODNESS);
    public static final DeferredItem<Item> GIVIN_ME_THE_BLUES = registerFoodItem("givin_me_the_blues", ModFoods.GIVIN_ME_THE_BLUES, ModConsumables.GIVIN_ME_THE_BLUES);
    public static final DeferredItem<Item> HOT_HOT_BABY = registerFoodItem("hot_hot_baby", ModFoods.HOT_HOT_BABY, ModConsumables.HOT_HOT_BABY);
    public static final DeferredItem<Item> DONT_MEDDLE_WITH_THE_NETTLE = registerFoodItem("dont_meddle_with_the_nettle", ModFoods.DONT_MEDDLE_WITH_THE_NETTLE, ModConsumables.DONT_MEDDLE_WITH_THE_NETTLE);
    public static final DeferredItem<Item> LIQUID_GOLD = registerFoodItem("liquid_gold", ModFoods.LIQUID_GOLD, ModConsumables.LIQUID_GOLD);
    public static final DeferredItem<Item> BRYUFS_BREW = registerFoodItem("bryufs_brew", ModFoods.BRYUFS_BREW, ModConsumables.BRYUFS_BREW);

    // MARK: Bamboo Armor
    public static final DeferredItem<Item> BAMBOO_HELMET = registerArmor("bamboo_helmet", ModArmorMaterials.BAMBOO, ArmorType.HELMET);
    public static final DeferredItem<Item> BAMBOO_CHESTPLATE = registerArmor("bamboo_chest", ModArmorMaterials.BAMBOO, ArmorType.CHESTPLATE);
    public static final DeferredItem<Item> BAMBOO_LEGGINGS = registerArmor("bamboo_legs", ModArmorMaterials.BAMBOO, ArmorType.LEGGINGS);
    public static final DeferredItem<Item> BAMBOO_BOOTS = registerArmor("bamboo_boots", ModArmorMaterials.BAMBOO, ArmorType.BOOTS);

    // MARK: Exoskeleton Armor
    public static final DeferredItem<Item> EXOSKELETON_HELMET = registerArmor("exoskeleton_helmet", ModArmorMaterials.EXOSKELETON, ArmorType.HELMET);
    public static final DeferredItem<Item> EXOSKELETON_CHESTPLATE = registerArmor("exoskeleton_chest", ModArmorMaterials.EXOSKELETON, ArmorType.CHESTPLATE);
    public static final DeferredItem<Item> EXOSKELETON_LEGGINGS = registerArmor("exoskeleton_legs", ModArmorMaterials.EXOSKELETON, ArmorType.LEGGINGS);
    public static final DeferredItem<Item> EXOSKELETON_BOOTS = registerArmor("exoskeleton_boots", ModArmorMaterials.EXOSKELETON, ArmorType.BOOTS);

    // MARK: Reinforced Exoskeleton Armor
    public static final DeferredItem<Item> REIN_EXOSKELETON_HELMET = registerArmor("rein_exoskeleton_helmet", ModArmorMaterials.REIN_EXOSKELETON, ArmorType.HELMET);
    public static final DeferredItem<Item> REIN_EXOSKELETON_CHESTPLATE = registerArmor("rein_exoskeleton_chest", ModArmorMaterials.REIN_EXOSKELETON, ArmorType.CHESTPLATE);
    public static final DeferredItem<Item> REIN_EXOSKELETON_LEGGINGS = registerArmor("rein_exoskeleton_legs", ModArmorMaterials.REIN_EXOSKELETON, ArmorType.LEGGINGS);
    public static final DeferredItem<Item> REIN_EXOSKELETON_BOOTS = registerArmor("rein_exoskeleton_boots", ModArmorMaterials.REIN_EXOSKELETON, ArmorType.BOOTS);

    // MARK: Rhino Exoskeleton Armor
    public static final DeferredItem<Item> RHINO_EXOSKELETON_HELMET = registerArmor("rhino_exoskeleton_helmet", ModArmorMaterials.RHINO, ArmorType.HELMET)  ;
    public static final DeferredItem<Item> RHINO_EXOSKELETON_CHESTPLATE = registerArmor("rhino_exoskeleton_chest", ModArmorMaterials.RHINO, ArmorType.CHESTPLATE);
    public static final DeferredItem<Item> RHINO_EXOSKELETON_LEGGINGS = registerArmor("rhino_exoskeleton_legs", ModArmorMaterials.RHINO, ArmorType.LEGGINGS);
    public static final DeferredItem<Item> RHINO_EXOSKELETON_BOOTS = registerArmor("rhino_exoskeleton_boots", ModArmorMaterials.RHINO, ArmorType.BOOTS);

    // MARK: Jade Armor
    public static final DeferredItem<Item> JADE_HELMET = registerArmor("jade_helmet", ModArmorMaterials.JADE, ArmorType.HELMET);
    public static final DeferredItem<Item> JADE_CHESTPLATE = registerArmor("jade_chest", ModArmorMaterials.JADE, ArmorType.CHESTPLATE);
    public static final DeferredItem<Item> JADE_LEGGINGS = registerArmor("jade_legs", ModArmorMaterials.JADE, ArmorType.LEGGINGS);
    public static final DeferredItem<Item> JADE_BOOTS = registerArmor("jade_boots", ModArmorMaterials.JADE, ArmorType.BOOTS);

    // MARK: Jade Tools
    public static final DeferredItem<Item> JADE_SWORD = registerSword("jade_sword", ModToolMaterials.JADE, 3, -2.4F);
    public static final DeferredItem<Item> JADE_PICKAXE = registerPickaxe("jade_pickaxe", ModToolMaterials.JADE, 1, -2.8F);
    public static final DeferredItem<AxeItem> JADE_AXE = registerAxe("jade_axe", ModToolMaterials.JADE, 5, -3.0F);
    public static final DeferredItem<ShovelItem> JADE_SHOVEL = registerShovel("jade_shovel", ModToolMaterials.JADE, 1.5F, -3.0F);
    public static final DeferredItem<PaxelItem> JADE_PAXEL = registerPaxel("jade_paxel", ModToolMaterials.JADE_PAXEL_TIER,1.0F, -2.8F);
    public static final DeferredItem<HoeItem> JADE_HOE = registerHoe("jade_hoe", ModToolMaterials.JADE,-3.0F, 0.0F);

    // MARK: Misc Armor & Weapons
    public static final DeferredItem<Item> REIN_COMPOUND_GOGGLES = registerItem("rein_compound_goggles", () -> new CompoundGoggles(ModArmorMaterials.REIN_COMPOUND_GOGGLES));
    public static final DeferredItem<Item> COMPOUND_GOGGLES = registerItem("compound_goggles", () -> new CompoundGoggles(ModArmorMaterials.GOGGLES));
    public static final DeferredItem<Item> MUSHROOM_HELMET = registerArmor("mushroom_helmet", ModArmorMaterials.MUSHROOM_HELM, ArmorType.HELMET);
    public static final DeferredItem<Item> GLIDER_CHESTPLATE = registerArmor("glider_chestplate", ModArmorMaterials.REIN_EXOSKELETON, ArmorType.CHESTPLATE);
    public static final DeferredItem<Item> GLIDER_CHESTPLATE_POWERED = registerArmor("glider_chestplate_powered",ModArmorMaterials.REIN_EXOSKELETON, ArmorType.CHESTPLATE);
    public static final DeferredItem<Item> SPIDER_T_SHIRT = registerArmor("spider_t_shirt", ModArmorMaterials.SPIDER_T_SHIRT, ArmorType.CHESTPLATE);
    public static final DeferredItem<Item> SPRINT_LEGGINGS = registerItem("sprint_leggings", SprintLeggings::new);
    public static final DeferredItem<Item> JUMP_BOOTS = registerItem("jump_boots", JumpBoots::new);
    public static final DeferredItem<Item> WATER_STRIDERS = registerArmor("water_striders", ModArmorMaterials.WATER_STRIDERS, ArmorType.BOOTS);
    public static final DeferredItem<Item> ENHANCED_SCORPION_PINCER = registerItem("enhanced_scorpion_pincer");
    public static final DeferredItem<Item> QUAKE_HAMMER = registerItem("quake_hammer", QuakeHammerItem::new);
    public static final DeferredItem<Item> WEB_SLINGER = registerItem("web_slinger");
    public static final DeferredItem<Item> WEB_SLINGER_WITHER = registerItem("web_slinger_wither");
    public static final DeferredItem<Item> MAX_SPEED_BOW = registerItem("max_speed_bow", MaxSpeedBowItem::new);

    // MARK: Shields
    public static final DeferredItem<ErebusShieldItem> BAMBOO_SHIELD = registerShield("bamboo_shield", 256, new BambooShieldType());
    public static final DeferredItem<ErebusShieldItem> EXOSKELETON_SHIELD = registerShield("exoskeleton_shield", 352, new ExoSkeletonShieldType());
    public static final DeferredItem<ErebusShieldItem> JADE_SHIELD = registerShield("jade_shield", 768, new JadeShieldType());
    public static final DeferredItem<ErebusShieldItem> REIN_EXOSKELETON_SHIELD = registerShield("rein_exoskeleton_shield", 1056, new ReinforcedShieldType());
    public static final DeferredItem<ErebusShieldItem> RHINO_EXOSKELETON_SHIELD = registerShield("rhino_exoskeleton_shield", 1056, new RhinoShieldType());

    // MARK: Misc
    public static final DeferredItem<Item> WAND_OF_ANIMATION = registerItem("wand_of_animation", WandOfAnimationItem::new);
    public static final DeferredItem<Item> WAND_OF_PRESERVATION = registerItem("wand_of_preservation", WandOfPreservationItem::new);
    public static final DeferredItem<Item> PORTAL_ACTIVATOR = registerItem("portal_activator");
    public static final DeferredItem<Item> WOODLOUSE_BALL = registerItem("woodlouse_ball");
    public static final DeferredItem<Item> NECTAR_COLLECTOR = registerItem("nectar_collector", () -> new Item((new Item.Properties().stacksTo(1).durability(16).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("nectar_collector"))))));
    public static final DeferredItem<Item> ANT_TAMING_AMULET = registerItem("ant_taming_amulet", AntTamingAmulet::new);
    public static final DeferredItem<Item> BEE_TAMING_AMULET = registerItem("bee_taming_amulet", BeeTamingAmulet::new);
    public static final DeferredItem<Item> WASP_SWORD = registerItem("wasp_sword", WaspSwordItem::new);
    public static final DeferredItem<Item> WASP_DAGGER = registerItem("wasp_dagger");
    public static final DeferredItem<Item> ANTI_VENOM_BOTTLE = registerItem("anti_venom_bottle");
    public static final DeferredItem<Item> DEATH_COMPASS = registerItem("death_compass", DeathCompass::new);
    public static final DeferredItem<Item> ROLLED_NEWSPAPER = registerItem("rolled_newspaper");
    public static final DeferredItem<Item> HOMING_BEECON = registerItem("homing_beecon");
    public static final DeferredItem<Item> HOMING_BEECON_ADVANCED = registerItem("homing_beecon_advanced");
    public static final DeferredItem<Item> SPRAY_CAN = registerItem("spray_can", InsectRepellentItem::new);
    public static final DeferredItem<Item> WHETSTONE = registerItem("whetstone");
    public static final DeferredItem<Item> COMPOST = registerItem("compost");
    public static final DeferredItem<Item> PLANTICIDE = registerItem("planticide", PlanticideItem::new);
    public static final DeferredItem<Item> SMOOTHIE_BOOK = registerItem("smoothie_book", SmoothieBookItem::new);
    public static final DeferredItem<Item> HORN_OF_SUMMONING = registerItem("horn_of_summoning");

    // MARK: Idols
    public static final DeferredItem<Item> MUD_SCARAB = registerItem("mud_scarab");
    public static final DeferredItem<Item> IRON_SCARAB = registerItem("iron_scarab");
    public static final DeferredItem<Item> GOLD_SCARAB = registerItem("gold_scarab");
    public static final DeferredItem<Item> JADE_SCARAB = registerItem("jade_scarab");
    public static final DeferredItem<Item> MUD_UMBERGOLEM = registerItem("mud_umbergolem");
    public static final DeferredItem<Item> IRON_UMBERGOLEM = registerItem("iron_umbergolem");
    public static final DeferredItem<Item> GOLD_UMBERGOLEM = registerItem("gold_umbergolem");
    public static final DeferredItem<Item> JADE_UMBERGOLEM = registerItem("jade_umbergolem");

    // MARK: Maps
    public static final DeferredItem<Item> EREBUS_MAP = registerItem("erebus_map");
    public static final DeferredItem<Item> EREBUS_MAP_FILLED = registerItem("erebus_map_filled");

    // MARK: Plants
    public static final DeferredItem<Item> TURNIP = registerItem("turnip", () -> new BlockItem(ModBlocks.CROP_TURNIP.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("turnip")))));
    public static final DeferredItem<Item> CABBAGE_SEEDS = registerItem("cabbage_seeds", () -> new BlockItem(ModBlocks.CROP_CABBAGE.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("cabbage_seeds")))));
    public static final DeferredItem<Item> MANDRAKE_ROOT = registerItem("mandrake_root", () -> new BlockItem(ModBlocks.CROP_MANDRAKE.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("mandrake_root")))));
    public static final DeferredItem<Item> SEED_BLACK = registerItem("seed_black");
    public static final DeferredItem<Item> SEED_RED = registerItem("seed_red");
    public static final DeferredItem<Item> SEED_BROWN = registerItem("seed_brown");
    public static final DeferredItem<Item> SEED_BLUE = registerItem("seed_blue");
    public static final DeferredItem<Item> SEED_PURPLE = registerItem("seed_purple");
    public static final DeferredItem<Item> SEED_CYAN = registerItem("seed_cyan");
    public static final DeferredItem<Item> SEED_LIGHT_GRAY = registerItem("seed_light_gray");
    public static final DeferredItem<Item> SEED_GRAY = registerItem("seed_gray");
    public static final DeferredItem<Item> SEED_PINK = registerItem("seed_pink");
    public static final DeferredItem<Item> SEED_YELLOW = registerItem("seed_yellow");
    public static final DeferredItem<Item> SEED_LIGHT_BLUE = registerItem("seed_light_blue");
    public static final DeferredItem<Item> SEED_MAGENTA = registerItem("seed_magenta");
    public static final DeferredItem<Item> SEED_ORANGE = registerItem("seed_orange");
    public static final DeferredItem<Item> SEED_WHITE = registerItem("seed_white");
    public static final DeferredItem<Item> SEED_RAINBOW = registerItem("seed_rainbow");

    public static final DeferredItem<Item> LIFE_BLOOD = registerItem("life_blood");
    public static final DeferredItem<Item> HEART_BERRIES = registerItem("heart_berries", () -> new BlockItem(ModBlocks.HEART_BERRY_BUSH.value(), new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.1F)
            .build()
    ).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("heart_berries")))));
    public static final DeferredItem<Item> STAG_HEART_RAW = registerItem("stag_heart_raw");
    public static final DeferredItem<Item> STAG_HEART_COOKED = registerItem("stag_heart_cooked");

    // MARK: Spawn Eggs
//    public static final DeferredItem<Item> BLOOD_SNAIL_SPAWN_EGG = registerSpawnEgg("blood_snail", ModEntities.BLOOD_SNAIL);
    public static final DeferredItem<Item> BEETLE_LARVA_SPAWN_EGG = registerSpawnEgg("beetle_larva", ModEntities.BEETLE_LARVA);
    public static final DeferredItem<Item> WASP_SPAWN_EGG = registerSpawnEgg("wasp", ModEntities.WASP);
    public static final DeferredItem<Item> CENTIPEDE_SPAWN_EGG = registerSpawnEgg("centipede", ModEntities.CENTIPEDE);
    public static final DeferredItem<Item> BEETLE_SPAWN_EGG = registerSpawnEgg("beetle", ModEntities.BEETLE);
    public static final DeferredItem<Item> FLY_SPAWN_EGG = registerSpawnEgg("fly", ModEntities.FLY);
    public static final DeferredItem<Item> MOSQUITO_SPAWN_EGG = registerSpawnEgg("mosquito", ModEntities.MOSQUITO);
    public static final DeferredItem<Item> TARANTULA_SPAWN_EGG = registerSpawnEgg("tarantula", ModEntities.TARANTULA);
    public static final DeferredItem<Item> BOT_FLY_SPAWN_EGG = registerSpawnEgg("bot_fly", ModEntities.BOT_FLY);
    public static final DeferredItem<Item> SCORPION_SPAWN_EGG = registerSpawnEgg("scorpion", ModEntities.SCORPION);
    public static final DeferredItem<Item> SOLIFUGE_SPAWN_EGG = registerSpawnEgg("solifuge", ModEntities.SOLIFUGE);
    public static final DeferredItem<Item> GRASSHOPPER_SPAWN_EGG = registerSpawnEgg("grasshopper", ModEntities.GRASSHOPPER);
    public static final DeferredItem<Item> LOCUST_SPAWN_EGG = registerSpawnEgg("locust", ModEntities.LOCUST);
    public static final DeferredItem<Item> MOTH_SPAWN_EGG = registerSpawnEgg("moth", ModEntities.MOTH);
    public static final DeferredItem<Item> RHINO_BEETLE_SPAWN_EGG = registerSpawnEgg("rhino_beetle", ModEntities.RHINO_BEETLE);
    public static final DeferredItem<Item> ANTLION_SPAWN_EGG = registerSpawnEgg("antlion", ModEntities.ANTLION);
    public static final DeferredItem<Item> BLACK_WIDOW_SPAWN_EGG = registerSpawnEgg("black_widow", ModEntities.BLACK_WIDOW);
    public static final DeferredItem<Item> GLOW_WORM_SPAWN_EGG = registerSpawnEgg("glow_worm", ModEntities.GLOW_WORM);
    public static final DeferredItem<Item> BOMBARDIER_BEETLE_SPAWN_EGG = registerSpawnEgg("bombardier_beetle", ModEntities.BOMBARDIER_BEETLE);
    public static final DeferredItem<Item> SCYTODES_SPAWN_EGG = registerSpawnEgg("scytodes", ModEntities.SCYTODES);
    public static final DeferredItem<Item> MONEY_SPIDER_SPAWN_EGG = registerSpawnEgg("money_spider", ModEntities.MONEY_SPIDER);
    public static final DeferredItem<Item> PRAYING_MANTIS_SPAWN_EGG = registerSpawnEgg("praying_mantis", ModEntities.PRAYING_MANTIS);
    public static final DeferredItem<Item> JUMPING_SPIDER_SPAWN_EGG = registerSpawnEgg("jumping_spider", ModEntities.JUMPING_SPIDER);
    public static final DeferredItem<Item> FIRE_ANT_SPAWN_EGG = registerSpawnEgg("fire_ant", ModEntities.FIRE_ANT);
    public static final DeferredItem<Item> WORKER_BEE_SPAWN_EGG = registerSpawnEgg("worker_bee", ModEntities.WORKER_BEE);
    public static final DeferredItem<Item> VELVET_WORM_SPAWN_EGG = registerSpawnEgg("velvet_worm", ModEntities.VELVET_WORM);
    public static final DeferredItem<Item> DRAGON_FLY_SPAWN_EGG = registerSpawnEgg("dragon_fly", ModEntities.DRAGON_FLY);
    public static final DeferredItem<Item> TITAN_BEETLE_SPAWN_EGG = registerSpawnEgg("titan_beetle", ModEntities.TITAN_BEETLE);
    public static final DeferredItem<Item> BOT_FLY_LARVA_SPAWN_EGG = registerSpawnEgg("bot_fly_larva", ModEntities.BOT_FLY_LARVA);
    public static final DeferredItem<Item> FUNGAL_WEEVIL_SPAWN_EGG = registerSpawnEgg("fungal_weevil", ModEntities.FUNGAL_WEEVIL);
    public static final DeferredItem<Item> CROP_WEEVIL_SPAWN_EGG = registerSpawnEgg("crop_weevil", ModEntities.CROP_WEEVIL);
    public static final DeferredItem<Item> WOODLOUSE_SPAWN_EGG = registerSpawnEgg("woodlouse", ModEntities.WOODLOUSE);
    public static final DeferredItem<Item> CICADA_SPAWN_EGG = registerSpawnEgg("cicada", ModEntities.CICADA);
    public static final DeferredItem<Item> FIRE_ANT_SOLDIER_SPAWN_EGG = registerSpawnEgg("fire_ant_soldier", ModEntities.FIRE_ANT_SOLDIER);
    public static final DeferredItem<Item> LAVA_WEB_SPIDER_SPAWN_EGG = registerSpawnEgg("lava_web_spider", ModEntities.LAVA_WEB_SPIDER);
    public static final DeferredItem<Item> LEECH_SPAWN_EGG = registerSpawnEgg("leech", ModEntities.LEECH);
    public static final DeferredItem<Item> ANTLION_MINI_BOSS_SPAWN_EGG = registerSpawnEgg("antlion_mini_boss", ModEntities.ANTLION_MINI_BOSS);
    public static final DeferredItem<Item> CHAMELEON_TICK_SPAWN_EGG = registerSpawnEgg("chameleon_tick", ModEntities.CHAMELEON_TICK);
    public static final DeferredItem<Item> MIDGE_SWARM_SPAWN_EGG = registerSpawnEgg("midge_swarm", ModEntities.MIDGE_SWARM);
    public static final DeferredItem<Item> PUNCHROOM_SPAWN_EGG = registerSpawnEgg("punchroom", ModEntities.PUNCHROOM);
    public static final DeferredItem<Item> CRUSHROOM_SPAWN_EGG = registerSpawnEgg("crushroom", ModEntities.CRUSHROOM);
    public static final DeferredItem<Item> BLACK_ANT_SPAWN_EGG = registerSpawnEgg("black_ant", ModEntities.BLACK_ANT);
    public static final DeferredItem<Item> ZOMBIE_ANT_SPAWN_EGG = registerSpawnEgg("zombie_ant", ModEntities.ZOMBIE_ANT);
    public static final DeferredItem<Item> TARANTULA_MINI_BOSS_SPAWN_EGG = registerSpawnEgg("tarantula_mini_boss", ModEntities.TARANTULA_MINI_BOSS);
    public static final DeferredItem<Item> TARANTULA_BABY_SPAWN_EGG = registerSpawnEgg("tarantula_baby", ModEntities.BABY_TARANTULA);
    public static final DeferredItem<Item> POND_SKATER_SPAWN_EGG = registerSpawnEgg("pond_skater", ModEntities.POND_SKATER);
    public static final DeferredItem<Item> BOG_MAW_SPAWN_EGG = registerSpawnEgg("bog_maw", ModEntities.BOG_MAW);
    public static final DeferredItem<Item> MAGMA_CRAWLER_SPAWN_EGG = registerSpawnEgg("magma_crawler", ModEntities.MAGMA_CRAWLER);
    public static final DeferredItem<Item> UMBER_GOLEM_SPAWN_EGG = registerSpawnEgg("umber_golem", ModEntities.UMBER_GOLEM);
    public static final DeferredItem<Item> ANTLION_BOSS_SPAWN_EGG = registerSpawnEgg("antlion_boss", ModEntities.ANTLION_BOSS);
    public static final DeferredItem<Item> HONEY_POT_ANT_SPAWN_EGG = registerSpawnEgg("honey_pot_ant", ModEntities.HONEY_POT_ANT);
    public static final DeferredItem<Item> BOMBARDIER_BEETLE_LARVA_SPAWN_EGG = registerSpawnEgg("bombardier_beetle_larva", ModEntities.BOMBARDIER_BEETLE_LARVA);
    public static final DeferredItem<Item> ZOMBIE_ANT_SOLDIER_SPAWN_EGG = registerSpawnEgg("zombie_ant_soldier", ModEntities.ZOMBIE_ANT_SOLDIER);
    public static final DeferredItem<Item> BED_BUG_SPAWN_EGG = registerSpawnEgg("bed_bug", ModEntities.BED_BUG);
    public static final DeferredItem<Item> STAG_BEETLE_SPAWN_EGG = registerSpawnEgg("stag_beetle", ModEntities.STAG_BEETLE);

    // MARK: Buckets
    public static final DeferredItem<Item> BAMBUCKET = registerItem("bambucket", () -> new BamBucketItem(Fluids.EMPTY, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("bambucket")))));
    public static final DeferredItem<Item> FORMIC_ACID_BUCKET = ITEMS.register("formic_acid_bucket", () -> new BamBucketItem(ModFluids.FORMIC_ACID_STILL.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("formic_acid_bucket")))));
    public static final DeferredItem<Item> HONEY_BUCKET = ITEMS.register("honey_bucket", () -> new BamBucketItem(ModFluids.HONEY_STILL.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("honey_bucket")))));
    public static final DeferredItem<Item> ANTI_VENOM_BUCKET = ITEMS.register("anti_venom_bucket", () -> new BamBucketItem(ModFluids.ANTI_VENOM_STILL.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("anti_venom_bucket")))));
    public static final DeferredItem<Item> BEETLE_JUICE_BUCKET = ITEMS.register("beetle_juice_bucket", () -> new BeetleJuiceBucketItem(ModFluids.BEETLE_JUICE_STILL.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1).setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("beetle_juice_bucket")))));

    // MARK: Block Items
    public static final DeferredItem<BlockItem> FLUID_JAR = ITEMS.register("fluid_jar", () -> new FluidJarBlockItem(ModBlocks.FLUID_JAR.get(), FluidType.BUCKET_VOLUME * 32, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("fluid_jar")))));
    public static final DeferredItem<BlockItem> LIQUIFIER = ITEMS.register("liquifier", () -> new LiquifierBlockItem(ModBlocks.LIQUIFIER.get(), FluidType.BUCKET_VOLUME * 8, new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("liquifier")))));
    public static final DeferredItem<BlockItem> BAMBOO_CRATE = ITEMS.register("bamboo_crate", () -> new BambooCrateItem(ModBlocks.BAMBOO_CRATE.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Erebus.prefix("bamboo_crate")))));
}
