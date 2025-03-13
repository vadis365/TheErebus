package erebus.registries;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolMaterials {

    // Name, harvest level, max uses, efficiency, damage, enchanting

    // TODO: Add in an actual repair item
    public static final Tier JADE_TIER = new SimpleTier(
            ModTags.INCORRECT_FOR_JADE_TOOL,
            863,
            10.0F,
            2.0F,
            18,
            () -> Ingredient.of(Items.IRON_INGOT)
    );

    // TODO: Add in an actual repair item
    public static final Tier JADE_PAXEL_TIER = new SimpleTier(
            ModTags.INCORRECT_FOR_JADE_TOOL,
            1079,
            8.0F,
            4.0F,
            14,
            () -> Ingredient.of(Items.IRON_INGOT)
    );

    public static void init() {
    }
}
