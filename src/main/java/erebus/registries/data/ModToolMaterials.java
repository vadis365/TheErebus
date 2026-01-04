package erebus.registries.data;

import erebus.registries.data.tags.ModBlockTags;
import erebus.registries.data.tags.ModItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

public class ModToolMaterials {

    public static final ToolMaterial JADE = new ToolMaterial(
            ModBlockTags.INCORRECT_FOR_JADE_TOOL,
            863,
            10.0F,
            2.0F,
            18,
            ModItemTags.JADE_TOOL_MATERIALS
    );

    public static final ToolMaterial JADE_PAXEL_TIER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            1079,
            8.0F,
            4.0F,
            14,
            ModItemTags.JADE_TOOL_MATERIALS
    );

    public static final ToolMaterial WASP_SWORD = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            863,
            1.0F,
            4.0F,
            18,
            ModItemTags.WASP_SWORD_TOOL_MATERIALS
    );

    public static final ToolMaterial WASP_DAGGER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            1,
            1,
            0,
            12,
            ModItemTags.WASP_DAGGER_TOOL_MATERIALS
    );

    public static final ToolMaterial ROLLED_NEWSPAPER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            64,
            1,
            0,
            0,
            ModItemTags.ROLLED_NEWSPAPER_TOOL_MATERIALS
    );

    public static final ToolMaterial SCORPION_PINCER =  new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            863,
            1,
            6,
            18,
            ModItemTags.SCORPION_PINCER_TOOL_MATERIALS
    );

    public static final ToolMaterial QUAKE_HAMMER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            512,
            1,
            0,
            18,
            ModItemTags.QUAKE_HAMMER_TOOL_MATERIALS
    );

    public static void init() {
    }
}
