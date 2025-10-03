package erebus.registries.data;

import erebus.registries.ModItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolMaterials {

    // Old info
    // Name, harvest level, max uses, efficiency, damage, enchanting

    public static final Tier JADE_TIER = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            863, 10.0F, 2.0F, 18,
            () -> Ingredient.of(ModItems.JADE)
    );

    public static final Tier JADE_PAXEL_TIER = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            1079, 8.0F, 4.0F, 14,
            () -> Ingredient.of(ModItems.JADE)
    );

    public static final Tier WASP_SWORD = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            863, 1.0F, 4.0F, 18,
            () -> Ingredient.of(ModItems.WASP_STING)
    );

    public static final Tier WASP_DAGGER = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            1, 1, 0, 12,
            () -> Ingredient.of(ModItems.WASP_STING)
    );

    public static final Tier ROLLED_NEWSPAPER = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            64, 1, 0, 0,
            () -> Ingredient.of(Items.PAPER)
    );

    public static final Tier SCORPION_PINCER =  new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            863, 1, 6, 18,
            () -> Ingredient.of(Items.PAPER)
    );

    public static final Tier QUAKE_HAMMER = new SimpleTier(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            512, 1, 0, 18,
            () -> Ingredient.of(ModItems.REINFORCED_PLATE_EXO.get())
    );

    public static void init() {
    }
}
