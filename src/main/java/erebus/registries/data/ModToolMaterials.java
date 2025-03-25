package erebus.registries.data;

import erebus.registries.ModItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolMaterials {

    // Name, harvest level, max uses, efficiency, damage, enchanting

    public static final Tier JADE_TIER = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            863,
            10.0F,
            2.0F,
            18,
            () -> Ingredient.of(ModItems.JADE)
    );

    public static final Tier JADE_PAXEL_TIER = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            1079,
            8.0F,
            4.0F,
            14,
            () -> Ingredient.of(ModItems.JADE)
    );

    public static void init() {
    }
}
