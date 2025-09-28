package erebus.registries;

import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import erebus.Erebus;
import erebus.item.AntTamingAmulet;
import erebus.item.BambooPipeWrenchItem;
import erebus.item.BeeTamingAmulet;
import erebus.item.BeettleJuiceBucketItem;
import erebus.item.CamoPowderItem;
import erebus.item.InsectRepellentItem;
import erebus.item.MaxSpeedBowItem;
import erebus.item.PaxelItem;
import erebus.item.PlanticideItem;
import erebus.item.SmoothieBookItem;
import erebus.item.WaspSwordItem;
import erebus.item.armour.CompoundGoggles;
import erebus.item.armour.JumpBoots;
import erebus.item.armour.SprintLeggings;
import erebus.item.blocks.BambooCrateItem;
import erebus.item.blocks.FluidJarBlockItem;
import erebus.item.blocks.LiquifierBlockItem;
import erebus.item.shield.ErebusShieldItem;
import erebus.item.shield.type.BambooShieldType;
import erebus.item.shield.type.ExoSkeletonShieldType;
import erebus.item.shield.type.JadeShieldType;
import erebus.item.shield.type.ReinforcedShieldType;
import erebus.item.shield.type.RhinoShieldType;
import erebus.item.wand.WandOfAnimationItem;
import erebus.item.wand.WandOfPreservationItem;
import erebus.network.data.DeathCompassData;
import erebus.registries.blocks.providers.AmberBlocks;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.blocks.providers.PlantBlocks;
import erebus.registries.data.ModArmorMaterials;
import erebus.registries.data.ModDataComponents;
import erebus.registries.data.ModToolMaterials;
import erebus.registries.helpers.ModItemHelpers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
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
    public static final DeferredItem<Item> CAMO_POWDER = registerItem("camo_powder", () -> new CamoPowderItem(new Item.Properties()));
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
    public static final DeferredItem<Item> JADE_BERRIES = registerItem("jade_berries", () -> new ItemNameBlockItem(PlantBlocks.JADE_BERRY_BUSH.get(), new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.1F)
            .build()
    )));
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
    public static final DeferredItem<BambooPipeWrenchItem> BAMBOO_PIPE_WRENCH = ITEMS.register("bamboo_pipe_wrench", () -> new BambooPipeWrenchItem(new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> TEMPLE_ROCK = registerItem("temple_rock");

    // MARK: Food
    public static final DeferredItem<Item> BEETLE_LARVA_RAW = registerFoodItem("beetle_larva_raw", 1, 0.1F, MobEffects.HUNGER, 300, 2);
    public static final DeferredItem<Item> BEETLE_LARVA_COOKED = registerFoodItem("beetle_larva_cooked", 3, 0.4F);
    public static final DeferredItem<Item> GRASSHOPPER_LEG_RAW = registerFoodItem("grasshopper_leg_raw", 1, 0.1F);
    public static final DeferredItem<Item> GRASSHOPPER_LEG_COOKED = registerFoodItem("grasshopper_leg_cooked", 4, 0.4F);
    public static final DeferredItem<Item> TARANTULA_LEG_RAW = registerFoodItem("tarantula_leg_raw", 1, 0.1F);
    public static final DeferredItem<Item> TARANTULA_LEG_COOKED = registerFoodItem("tarantula_leg_cooked", 5, 0.4F);
    public static final DeferredItem<Item> BAMBOO_SOUP = registerFoodItem("bamboo_soup", 3, 0.2F, Items.BOWL);
    public static final DeferredItem<Item> MELONADE = registerFoodItem("melonade", 3, 0.2F, SMOOTHIE_GLASS);
    public static final DeferredItem<Item> MELONADE_SPARKLY = registerFoodItem("melonade_sparkly", 5, 0.4F, MobEffects.REGENERATION, 200, 0, SMOOTHIE_GLASS);
    public static final DeferredItem<Item> LARVAE_ON_STICK = registerFoodItem("larvae_on_stick", 9, 0.5F, MobEffects.HUNGER, 100, 1, Items.STICK);
    public static final DeferredItem<Item> HONEY_SANDWICH = registerFoodItem("honey_sandwich", 6, 0.5F);
    public static final DeferredItem<Item> DARK_FRUIT = registerFoodItem("dark_fruit", 2, 0.3F);
    public static final DeferredItem<Item> TITAN_CHOP_RAW = registerFoodItem("titan_chop_raw", 4, 0.3F);
    public static final DeferredItem<Item> TITAN_CHOP_COOKED = registerFoodItem("titan_chop_cooked", 8, 0.8F, MobEffects.DAMAGE_BOOST, 600, 1);
    public static final DeferredItem<Item> SWAMP_BERRIES = ITEMS.register("swamp_berries", () -> new ItemNameBlockItem(PlantBlocks.SWAMP_BERRY_BUSH.get(), new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.1F)
            .build()
    )));
    public static final DeferredItem<Item> CABBAGE = registerFoodItem("cabbage", 1, 0.3F);
    public static final DeferredItem<Item> TITAN_STEW_COOKED = registerFoodItem("titan_stew_cooked", 20, 4.0F, STEW_POT);
    public static final DeferredItem<Item> PRICKLY_PEAR_RAW = registerFoodItem("prickly_pear_raw", 3, 0.3F, MobEffects.HARM, 1, 1);
    public static final DeferredItem<Item> PRICKLY_PEAR_COOKED = registerFoodItem("prickly_pear_cooked", 4, 0.5F, MobEffects.HARM, 1, 1);
    public static final DeferredItem<Item> DARK_FRUIT_PIE = registerFoodItem("dark_fruit_pie", 8, 0.3F);

    // MARK: Smoothies
    public static final DeferredItem<Item> GREEN_TEA_GRASSHOPPER = registerSmoothieItem("green_tea_grasshopper", 5, 0.4F, MobEffects.JUMP, 1000, 2);
    public static final DeferredItem<Item> MONEY_HONEY = registerSmoothieItem("money_honey", 3, 0.2F, MobEffects.REGENERATION, 200, 2);
    public static final DeferredItem<Item> NOTHING_IN_THE_MIDDLE = registerSmoothieItem("nothing_in_the_middle", 1, 0.0F, MobEffects.INVISIBILITY, 500, 1);
    public static final DeferredItem<Item> GREEN_GIANT = registerSmoothieItem("green_giant", 2, 0.1F);
    public static final DeferredItem<Item> SEEDY_GOODNESS = registerSmoothieItem("seedy_goodness", 1, 1.1F, MobEffects.DIG_SPEED, 500, 1);
    public static final DeferredItem<Item> GIVIN_ME_THE_BLUES = registerSmoothieItem("givin_me_the_blues", 3, 0.2F, MobEffects.MOVEMENT_SLOWDOWN, 500, 2);
    public static final DeferredItem<Item> HOT_HOT_BABY = registerSmoothieItem("hot_hot_baby", 2, 0.1F, MobEffects.DAMAGE_BOOST, 1000, 1);
    public static final DeferredItem<Item> DONT_MEDDLE_WITH_THE_NETTLE = registerSmoothieItem("dont_meddle_with_the_nettle", 2, 0.1F, MobEffects.DAMAGE_RESISTANCE, 1000, 1);
    public static final DeferredItem<Item> LIQUID_GOLD = registerSmoothieItem("liquid_gold", 0, 0.0F, MobEffects.REGENERATION, 1000, 1);
    public static final DeferredItem<Item> BRYUFS_BREW = registerBryufsBrew();

    // MARK: Bamboo Armor
    public static final DeferredItem<ArmorItem> BAMBOO_HELMET;
    public static final DeferredItem<ArmorItem> BAMBOO_CHESTPLATE;
    public static final DeferredItem<ArmorItem> BAMBOO_LEGGINGS;
    public static final DeferredItem<ArmorItem> BAMBOO_BOOTS;

    // MARK: Exoskeleton Armor
    public static final DeferredItem<ArmorItem> EXOSKELETON_HELMET;
    public static final DeferredItem<ArmorItem> EXOSKELETON_CHESTPLATE;
    public static final DeferredItem<ArmorItem> EXOSKELETON_LEGGINGS;
    public static final DeferredItem<ArmorItem> EXOSKELETON_BOOTS;

    // MARK: Reinforced Exoskeleton Armor
    public static final DeferredItem<ArmorItem> REIN_EXOSKELETON_HELMET;
    public static final DeferredItem<ArmorItem> REIN_EXOSKELETON_CHESTPLATE;
    public static final DeferredItem<ArmorItem> REIN_EXOSKELETON_LEGGINGS;
    public static final DeferredItem<ArmorItem> REIN_EXOSKELETON_BOOTS;

    // MARK: Rhino Exoskeleton Armor
    public static final DeferredItem<ArmorItem> RHINO_EXOSKELETON_HELMET;
    public static final DeferredItem<ArmorItem> RHINO_EXOSKELETON_CHESTPLATE;
    public static final DeferredItem<ArmorItem> RHINO_EXOSKELETON_LEGGINGS;
    public static final DeferredItem<ArmorItem> RHINO_EXOSKELETON_BOOTS;

    // MARK: Jade Armor
    public static final DeferredItem<ArmorItem> JADE_HELMET;
    public static final DeferredItem<ArmorItem> JADE_CHESTPLATE;
    public static final DeferredItem<ArmorItem> JADE_LEGGINGS;
    public static final DeferredItem<ArmorItem> JADE_BOOTS;

    // MARK: Jade Tools
    public static final DeferredItem<SwordItem> JADE_SWORD = registerSword("jade_sword", ModToolMaterials.JADE_TIER, 3, -2.4F);
    public static final DeferredItem<PickaxeItem> JADE_PICKAXE = registerPickaxe("jade_pickaxe", ModToolMaterials.JADE_TIER, 1, -2.8F);
    public static final DeferredItem<AxeItem> JADE_AXE = registerAxe("jade_axe", ModToolMaterials.JADE_TIER, 5, -3.0F);
    public static final DeferredItem<ShovelItem> JADE_SHOVEL = registerShovel("jade_shovel", ModToolMaterials.JADE_TIER, 1.5F, -3.0F);
    public static final DeferredItem<PaxelItem> JADE_PAXEL = registerPaxel("jade_paxel", ModToolMaterials.JADE_PAXEL_TIER,1.0F, -2.8F);
    public static final DeferredItem<HoeItem> JADE_HOE = registerHoe("jade_hoe", ModToolMaterials.JADE_TIER,-3.0F, 0.0F);

    // MARK: Misc Armor & Weapons
    public static final DeferredItem<Item> REIN_COMPOUND_GOGGLES = registerItem("rein_compound_goggles", () -> new CompoundGoggles(ModArmorMaterials.REIN_COMPOUND_GOGGLES_ARMOR_MATERIAL, ArmorItem.Type.HELMET,  new Item.Properties().stacksTo(1).durability(256)));
    public static final DeferredItem<Item> COMPOUND_GOGGLES = registerItem("compound_goggles", () -> new CompoundGoggles(ModArmorMaterials.GOGGLES_ARMOR_MATERIAL, ArmorItem.Type.HELMET,  new Item.Properties().stacksTo(1).durability(256)));
    public static final DeferredItem<ArmorItem> MUSHROOM_HELMET = registerHelmet("mushroom_helmet", ModArmorMaterials.MUSHROOM_HELM_ARMOR_MATERIAL);
    public static final DeferredItem<ArmorItem> GLIDER_CHESTPLATE = registerChestplate("glider_chestplate", ModArmorMaterials.REIN_EXOSKELETON_ARMOR_MATERIAL);
    public static final DeferredItem<ArmorItem> GLIDER_CHESTPLATE_POWERED = registerChestplate("glider_chestplate_powered",ModArmorMaterials.REIN_EXOSKELETON_ARMOR_MATERIAL);
    public static final DeferredItem<ArmorItem> SPIDER_T_SHIRT = registerChestplate("spider_t_shirt", ModArmorMaterials.SPIDER_T_SHIRT_ARMOR_MATERIAL);
    public static final DeferredItem<Item> SPRINT_LEGGINGS = registerItem("sprint_leggings", () -> new SprintLeggings(ModArmorMaterials.CENTIPEDE_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS,  new Item.Properties().stacksTo(1).durability(256)));
    public static final DeferredItem<Item> JUMP_BOOTS = registerItem("jump_boots", () -> new JumpBoots(ModArmorMaterials.JUMP_BOOTS_ARMOR_MATERIAL, ArmorItem.Type.BOOTS,  new Item.Properties().stacksTo(1).durability(256)));
    public static final DeferredItem<ArmorItem> WATER_STRIDERS = registerBoots("water_striders", ModArmorMaterials.WATER_STRIDERS_ARMOR_MATERIAL);
    public static final DeferredItem<Item> ENHANCED_SCORPION_PINCER = registerItem("enhanced_scorpion_pincer");
    public static final DeferredItem<Item> WAR_HAMMER = registerItem("war_hammer");
    public static final DeferredItem<Item> WEB_SLINGER = registerItem("web_slinger");
    public static final DeferredItem<Item> WEB_SLINGER_WITHER = registerItem("web_slinger_wither");
    public static final DeferredItem<Item> MAX_SPEED_BOW = registerItem("max_speed_bow", () -> new MaxSpeedBowItem(new Item.Properties()
            .durability(500)
            .rarity(Rarity.RARE)
    ));

    // MARK: Shields
    public static final DeferredItem<ErebusShieldItem> BAMBOO_SHIELD = registerShield("bamboo_shield", 256, new BambooShieldType());
    public static final DeferredItem<ErebusShieldItem> EXOSKELETON_SHIELD = registerShield("exoskeleton_shield", 352, new ExoSkeletonShieldType());
    public static final DeferredItem<ErebusShieldItem> JADE_SHIELD = registerShield("jade_shield", 768, new JadeShieldType());
    public static final DeferredItem<ErebusShieldItem> REIN_EXOSKELETON_SHIELD = registerShield("rein_exoskeleton_shield", 1056, new ReinforcedShieldType());
    public static final DeferredItem<ErebusShieldItem> RHINO_EXOSKELETON_SHIELD = registerShield("rhino_exoskeleton_shield", 1056, new RhinoShieldType());

    // MARK: Misc
    public static final DeferredItem<Item> WAND_OF_ANIMATION = registerItem("wand_of_animation", () -> new WandOfAnimationItem(new Item.Properties().stacksTo(1).durability(64).setNoRepair()));
    public static final DeferredItem<Item> WAND_OF_PRESERVATION = registerItem("wand_of_preservation", () -> new WandOfPreservationItem(new Item.Properties().stacksTo(1).durability(64).setNoRepair()));
    public static final DeferredItem<Item> PORTAL_ACTIVATOR = registerItem("portal_activator");
    public static final DeferredItem<Item> WOODLOUSE_BALL = registerItem("woodlouse_ball");
    public static final DeferredItem<Item> NECTAR_COLLECTOR = registerItem("nectar_collector", () -> new Item((new Item.Properties().stacksTo(1).durability(16))));
    public static final DeferredItem<Item> ANT_TAMING_AMULET = registerItem("ant_taming_amulet", () -> new AntTamingAmulet(new Item.Properties().stacksTo(1).durability(16)));
    public static final DeferredItem<Item> BEE_TAMING_AMULET = registerItem("bee_taming_amulet", () -> new BeeTamingAmulet(new Item.Properties().stacksTo(1).durability(16)));
    public static final DeferredItem<Item> WASP_SWORD = registerItem("wasp_sword", () -> new WaspSwordItem(ModToolMaterials.WASP_SWORD, new Item.Properties()
            .stacksTo(1)
            .attributes(SwordItem.createAttributes(ModToolMaterials.WASP_SWORD, 6, -1)
    )));
    public static final DeferredItem<Item> WASP_DAGGER = registerItem("wasp_dagger");
    public static final DeferredItem<Item> ANTI_VENOM_BOTTLE = registerItem("anti_venom_bottle");
    public static final DeferredItem<Item> DEATH_COMPASS = registerItem("death_compass", () -> new Item(new Item.Properties()) {
        @Override
        public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull List<Component> components, @NotNull TooltipFlag flag) {
            DeathCompassData data = stack.getComponents().get(ModDataComponents.DEATH_COMPASS.get());
            if (data != null) {
                components.add(
                        Component
                                .translatable("tooltip.death_compass.pos")
                                .append("%d, %d, %d".formatted(data.x(), data.y(), data.z()))
                                .withStyle(ChatFormatting.YELLOW)
                );
            }
        }
    });
    public static final DeferredItem<Item> ROLLED_NEWSPAPER = registerItem("rolled_newspaper");
    public static final DeferredItem<Item> BAMBUCKET = registerBucket("bambucket");
    public static final DeferredItem<Item> HOMING_BEECON = registerItem("homing_beecon");
    public static final DeferredItem<Item> HOMING_BEECON_ADVANCED = registerItem("homing_beecon_advanced");
    public static final DeferredItem<Item> SPRAY_CAN = registerItem("spray_can", () -> new InsectRepellentItem(new Item.Properties()));
    public static final DeferredItem<Item> WHETSTONE = registerItem("whetstone");
    public static final DeferredItem<Item> COMPOST = registerItem("compost");
    public static final DeferredItem<Item> PLANTICIDE = registerItem("planticide", () -> new PlanticideItem(new Item.Properties().stacksTo(64)));
    public static final DeferredItem<Item> SMOOTHIE_BOOK = registerItem("smoothie_book", () -> new SmoothieBookItem(new Item.Properties()));
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
    public static final DeferredItem<Item> TURNIP = registerItem("turnip", () -> new ItemNameBlockItem(PlantBlocks.CROP_TURNIP.get(), new Item.Properties()));
    public static final DeferredItem<Item> CABBAGE_SEEDS = registerItem("cabbage_seeds", () -> new ItemNameBlockItem(PlantBlocks.CROP_CABBAGE.get(), new Item.Properties()));
    public static final DeferredItem<Item> MANDRAKE_ROOT = registerItem("mandrake_root", () -> new ItemNameBlockItem(PlantBlocks.CROP_MANDRAKE.get(), new Item.Properties()));
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
    public static final DeferredItem<Item> HEART_BERRIES = registerItem("heart_berries", () -> new ItemNameBlockItem(PlantBlocks.HEART_BERRY_BUSH.get(), new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(1)
            .saturationModifier(0.1F)
            .build()
    )));
    public static final DeferredItem<Item> STAG_HEART_RAW = registerItem("stag_heart_raw");
    public static final DeferredItem<Item> STAG_HEART_COOKED = registerItem("stag_heart_cooked");

    static {
        Map<String, DeferredItem<ArmorItem>> bambooSet = registerArmorSet("bamboo", ModArmorMaterials.BAMBOO_ARMOR_MATERIAL);
        BAMBOO_HELMET = bambooSet.get("helm");
        BAMBOO_CHESTPLATE = bambooSet.get("chest");
        BAMBOO_LEGGINGS = bambooSet.get("legs");
        BAMBOO_BOOTS = bambooSet.get("boots");

        Map<String, DeferredItem<ArmorItem>> exoskeletonSet = registerArmorSet("exoskeleton", ModArmorMaterials.EXOSKELETON_ARMOR_MATERIAL);
        EXOSKELETON_HELMET = exoskeletonSet.get("helm");
        EXOSKELETON_CHESTPLATE = exoskeletonSet.get("chest");
        EXOSKELETON_LEGGINGS = exoskeletonSet.get("legs");
        EXOSKELETON_BOOTS = exoskeletonSet.get("boots");

        Map<String, DeferredItem<ArmorItem>> reinSet = registerArmorSet("rein_exoskeleton", ModArmorMaterials.REIN_EXOSKELETON_ARMOR_MATERIAL);
        REIN_EXOSKELETON_HELMET = reinSet.get("helm");
        REIN_EXOSKELETON_CHESTPLATE = reinSet.get("chest");
        REIN_EXOSKELETON_LEGGINGS = reinSet.get("legs");
        REIN_EXOSKELETON_BOOTS = reinSet.get("boots");

        Map<String, DeferredItem<ArmorItem>> rhinoSet = registerArmorSet("rhino_exoskeleton", ModArmorMaterials.RHINO_ARMOR_MATERIAL);
        RHINO_EXOSKELETON_HELMET = rhinoSet.get("helm");
        RHINO_EXOSKELETON_CHESTPLATE = rhinoSet.get("chest");
        RHINO_EXOSKELETON_LEGGINGS = rhinoSet.get("legs");
        RHINO_EXOSKELETON_BOOTS = rhinoSet.get("boots");

        Map<String, DeferredItem<ArmorItem>> jadeSet = registerArmorSet("jade", ModArmorMaterials.JADE_ARMOR_MATERIAL);
        JADE_HELMET = jadeSet.get("helm");
        JADE_CHESTPLATE = jadeSet.get("chest");
        JADE_LEGGINGS = jadeSet.get("legs");
        JADE_BOOTS = jadeSet.get("boots");
    }

    // buckets
    public static final DeferredItem<BucketItem> FORMIC_ACID_BUCKET = ITEMS.register("formic_acid_bucket", () -> new BucketItem(ModFluids.FORMIC_ACID_STILL.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final DeferredItem<BucketItem> HONEY_BUCKET = ITEMS.register("honey_bucket", () -> new BucketItem(ModFluids.HONEY_STILL.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final DeferredItem<BucketItem> ANTI_VENOM_BUCKET = ITEMS.register("anti_venom_bucket", () -> new BucketItem(ModFluids.ANTI_VENOM_STILL.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final DeferredItem<BucketItem> BEETLE_JUICE_BUCKET = ITEMS.register("beetle_juice_bucket", () -> new BeettleJuiceBucketItem(ModFluids.BEETLE_JUICE_STILL.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    // Block Items
    public static final DeferredItem<BlockItem> FLUID_JAR = ITEMS.register("fluid_jar", () -> new FluidJarBlockItem(AmberBlocks.FLUID_JAR.get(), FluidType.BUCKET_VOLUME * 32, new Item.Properties()));
    public static final DeferredItem<BlockItem> LIQUIFIER = ITEMS.register("liquifier", () -> new LiquifierBlockItem(OtherBlocks.LIQUIFIER.get(), FluidType.BUCKET_VOLUME * 8, new Item.Properties()));
    public static final DeferredItem<BlockItem> BAMBOO_CRATE = ITEMS.register("bamboo_crate", () -> new BambooCrateItem(OtherBlocks.BAMBOO_CRATE.get(), new Item.Properties()));
}
