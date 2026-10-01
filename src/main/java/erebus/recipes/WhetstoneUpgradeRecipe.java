package erebus.recipes;

import com.mojang.serialization.MapCodec;
import erebus.item.WhetstoneItem;
import erebus.registries.data.ModDataComponents;
import erebus.registries.item.ModItems;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public final class WhetstoneUpgradeRecipe extends CustomRecipe {
    public static final WhetstoneUpgradeRecipe INSTANCE = new WhetstoneUpgradeRecipe();
    public static final RecipeSerializer<WhetstoneUpgradeRecipe> SERIALIZER = new RecipeSerializer<>(
            MapCodec.unit(INSTANCE), StreamCodec.unit(INSTANCE));

    private boolean valid(CraftingInput input) {
        if (input.width() != 3 || input.height() != 3 || input.ingredientCount() != 9) return false;
        var stone = input.getItem(4);
        if (!stone.is(ModItems.WHETSTONE) || WhetstoneItem.level(stone) >= 5) return false;
        for (int i = 0; i < 9; i++)
            if (i != 4 && !input.getItem(i).is(ModItems.WHETSTONE_POWDER)) return false;
        return true;
    }

    @Override
    public boolean matches(@NonNull CraftingInput input, @NonNull Level level) {
        return valid(input);
    }

    @Override
    public @NonNull ItemStack assemble(@NonNull CraftingInput input) {
        if (!valid(input)) return ItemStack.EMPTY;
        var result = input.getItem(4).copyWithCount(1);
        result.set(ModDataComponents.WHETSTONE_LEVEL, WhetstoneItem.level(result) + 1);
        return result;
    }

    @Override
    public @NonNull RecipeSerializer<WhetstoneUpgradeRecipe> getSerializer() {
        return SERIALIZER;
    }
}
