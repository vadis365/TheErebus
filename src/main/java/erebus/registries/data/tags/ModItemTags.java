package erebus.registries.data.tags;

import erebus.Erebus;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
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

    private static TagKey<Item> create(String name) {
        return TagKey.create(BuiltInRegistries.ITEM.key(), Erebus.prefix(name));
    }
}
