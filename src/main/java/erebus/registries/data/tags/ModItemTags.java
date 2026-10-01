package erebus.registries.data.tags;

import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> ORES_ALUMINUM = common("ores/aluminum");
    public static final TagKey<Item> ORES_LEAD = common("ores/lead");
    public static final TagKey<Item> ORES_SILVER = common("ores/silver");
    public static final TagKey<Item> ORES_TIN = common("ores/tin");
    public static final TagKey<Item> ORES_JADE = common("ores/jade");
    public static final TagKey<Item> ORES_PETRIFIED_WOOD = common("ores/petrified_wood");
    public static final TagKey<Item> ORES_FOSSIL = common("ores/fossil");
    public static final TagKey<Item> ORES_GNEISS = common("ores/gneiss");
    public static final TagKey<Item> INGOTS_ALUMINUM = common("ingots/aluminum");
    public static final TagKey<Item> INGOTS_LEAD = common("ingots/lead");
    public static final TagKey<Item> INGOTS_SILVER = common("ingots/silver");
    public static final TagKey<Item> INGOTS_TIN = common("ingots/tin");
    public static final TagKey<Item> GEMS_JADE = common("gems/jade");
    public static final TagKey<Item> STORAGE_BLOCKS_JADE = common("storage_blocks/jade");
    public static final TagKey<Item> EXPERIENCE_ALTAR_FUEL = create("experience_altar_fuel");
    public static final TagKey<Item> REPAIRS_GLIDER = create("repairs_glider");
    public static final TagKey<Item> GLIDER_FUEL = create("glider_fuel");
    public static final TagKey<Item> REPAIRS_JADE_ARMOR = create("repairs_jade_armor");
    public static final TagKey<Item> REPAIRS_EXOSKELETON_ARMOR = create("repairs_exoskeleton_armor");
    public static final TagKey<Item> REPAIRS_REINFORCED_EXOSKELETON_ARMOR = create("repairs_reinforced_exoskeleton_armor");
    public static final TagKey<Item> REPAIRS_RHINO_ARMOR = create("repairs_rhino_armor");
    public static final TagKey<Item> REPAIRS_BAMBOO_ARMOR = create("repairs_bamboo_armor");
    public static final TagKey<Item> REPAIRS_REINFORCED_COMPOUND_GOGGLES = create("repairs_reinforced_compound_goggles");
    public static final TagKey<Item> REPAIRS_MUSHROOM_HELM = create("repairs_mushroom_helm");
    public static final TagKey<Item> REPAIRS_SPIDER_T_SHIRT = create("repairs_spider_t_shirt");
    public static final TagKey<Item> REPAIRS_WATER_STRIDERS = create("repairs_water_striders");
    public static final TagKey<Item> REPAIRS_JUMP_BOOTS = create("repairs_jump_boots");
    public static final TagKey<Item> REPAIRS_SPRINT_LEGGINGS = create("repairs_sprint_leggings");

    public static final TagKey<Item> JADE_TOOL_MATERIALS = create("jade_tool_materials");
    public static final TagKey<Item> WASP_SWORD_TOOL_MATERIALS = create("wasp_sword_tool_materials");
    public static final TagKey<Item> WASP_DAGGER_TOOL_MATERIALS = create("wasp_dagger_tool_materials");
    public static final TagKey<Item> ROLLED_NEWSPAPER_TOOL_MATERIALS = create("rolled_newspaper_tool_materials");
    public static final TagKey<Item> SCORPION_PINCER_TOOL_MATERIALS = create("scorpion_pincer_tool_materials");
    public static final TagKey<Item> QUAKE_HAMMER_TOOL_MATERIALS = create("quake_hammer_tool_materials");

    public static final TagKey<Item> COMPOSTABLE = create("compostable");
    public static final TagKey<Item> TITAN_BEETLE_FOOD = create("titan_beetle_food");
    public static final TagKey<Item> TITAN_BEETLE_CHESTS = create("titan_beetle_chests");

    private static TagKey<Item> common(String name) {
        return TagKey.create(BuiltInRegistries.ITEM.key(), net.minecraft.resources.Identifier.fromNamespaceAndPath("c", name));
    }

    private static TagKey<Item> create(String name) {
        return TagKey.create(BuiltInRegistries.ITEM.key(), Erebus.prefix(name));
    }
}
