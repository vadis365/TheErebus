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

    // MARK: Implemented


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
                    ModToolMaterials.JADE_TIER,
                    new Item.Properties().attributes(
                            PaxelItem.createAttributes(
                                    ModToolMaterials.JADE_TIER,
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

    // MARK: Misc Armor

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

    /**
     * Bamboo Stuff
     */
    public static final DeferredItem<Item> BAMBUCKET = ITEMS.register(
            "bambucket",
            () -> new UniversalBucketItem(
                    new UniversalBucketItem.Properties()
            )
    );

    /**
     * General
     */
    public static final DeferredItem<Item> HEART_BERRIES = ITEMS.registerSimpleItem("heart_berries");
    public static final DeferredItem<Item> LIFE_BLOOD = ITEMS.registerSimpleItem("life_blood");
    public static final DeferredItem<Item> TURNIP = ITEMS.registerSimpleItem("turnip");
    public static final DeferredItem<Item> CABBAGE_SEEDS = ITEMS.registerSimpleItem("cabbage_seeds");
    public static final DeferredItem<Item> MANDRAKE_ROOT = ITEMS.registerSimpleItem("mandrake_root");
    public static final DeferredItem<Item> STAG_HEART_RAW = ITEMS.registerSimpleItem("stag_heart_raw");
    public static final DeferredItem<Item> STAG_HEART_COOKED = ITEMS.registerSimpleItem("stag_heart_cooked");
    public static final DeferredItem<Item> SMOOTHIE = ITEMS.registerSimpleItem("smoothie");
    public static final DeferredItem<Item> IDOLS = ITEMS.registerSimpleItem("idols");

    /**
     * Shields
     */
    public static final DeferredItem<Item> BAMBOO_SHIELD = ITEMS.registerSimpleItem("bamboo_shield");
    public static final DeferredItem<Item> EXOSKELETON_SHIELD = ITEMS.registerSimpleItem("exoskeleton_shield");
    public static final DeferredItem<Item> JADE_SHIELD = ITEMS.registerSimpleItem("jade_shield");
    public static final DeferredItem<Item> REIN_EXOSKELETON_SHIELD = ITEMS.registerSimpleItem("rein_exoskeleton_shield");
    public static final DeferredItem<Item> RHINO_EXOSKELETON_SHIELD = ITEMS.registerSimpleItem("rhino_exoskeleton_shield");

    /**
     * Misc Weapons
     */
    public static final DeferredItem<Item> ROLLED_NEWSPAPER = ITEMS.registerSimpleItem("rolled_newspaper");
    public static final DeferredItem<Item> WASP_SWORD = ITEMS.registerSimpleItem("wasp_sword");
    public static final DeferredItem<Item> WASP_DAGGER = ITEMS.registerSimpleItem("wasp_dagger");
    public static final DeferredItem<Item> ENHANCED_SCORPION_PINCER = ITEMS.registerSimpleItem("enhanced_scorpion_pincer");
    public static final DeferredItem<Item> WAR_HAMMER = ITEMS.registerSimpleItem("war_hammer");
    public static final DeferredItem<Item> WEB_SLINGER = ITEMS.registerSimpleItem("web_slinger");
    public static final DeferredItem<Item> WEB_SLINGER_WITHER = ITEMS.registerSimpleItem("web_slinger_wither");
    public static final DeferredItem<Item> MAX_SPEED_BOW = ITEMS.registerSimpleItem("max_speed_bow");

    /**
     * Random Stuff
     */
    public static final DeferredItem<Item> NECTAR_COLLECTOR = ITEMS.registerSimpleItem("nectar_collector");
    public static final DeferredItem<Item> ANT_TAMING_AMULET = ITEMS.registerSimpleItem("ant_taming_amulet");
    public static final DeferredItem<Item> BEE_TAMING_AMULET = ITEMS.registerSimpleItem("bee_taming_amulet");
    public static final DeferredItem<Item> WOODLOUSE_BALL = ITEMS.registerSimpleItem("woodlouse_ball");
    public static final DeferredItem<Item> WAND_OF_ANIMATION = ITEMS.registerSimpleItem("wand_of_animation");
    public static final DeferredItem<Item> ANTI_VENOM_BOTTLE = ITEMS.registerSimpleItem("anti_venom_bottle");
    public static final DeferredItem<Item> FLOWER_SEED = ITEMS.registerSimpleItem("flower_seed");
    public static final DeferredItem<Item> DEATH_COMPASS = ITEMS.registerSimpleItem("death_compass");
    public static final DeferredItem<Item> SPRAY_CAN = ITEMS.registerSimpleItem("spray_can");
    public static final DeferredItem<Item> WHETSTONE = ITEMS.registerSimpleItem("whetstone");
    public static final DeferredItem<Item> PORTAL_ACTIVATOR = ITEMS.registerSimpleItem("portal_activator");
    public static final DeferredItem<Item> HOMING_BEECON = ITEMS.registerSimpleItem("homing_beecon");
    public static final DeferredItem<Item> HOMING_BEECON_ADVANCED = ITEMS.registerSimpleItem("homing_beecon_advanced");
    public static final DeferredItem<Item> WAND_OF_PRESERVATION = ITEMS.registerSimpleItem("wand_of_preservation");
    public static final DeferredItem<Item> COMPOST = ITEMS.registerSimpleItem("compost");
    public static final DeferredItem<Item> PLANTICIDE = ITEMS.registerSimpleItem("planticide");
    public static final DeferredItem<Item> SMOOTHIE_BOOK = ITEMS.registerSimpleItem("smoothie_book");
    public static final DeferredItem<Item> HORN_OF_SUMMONING = ITEMS.registerSimpleItem("horn_of_summoning");
    public static final DeferredItem<Item> EREBUS_MAP = ITEMS.registerSimpleItem("erebus_map");
    public static final DeferredItem<Item> EREBUS_MAP_FILLED = ITEMS.registerSimpleItem("erebus_map_filled");


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
