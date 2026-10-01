package erebus.recipes;

import com.mojang.serialization.MapCodec;
import erebus.item.armour.SprintLeggings;
import erebus.network.data.SprintLeggingsData;
import erebus.registries.data.ModDataComponents;
import erebus.registries.item.ModItems;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public final class SprintLeggingsUpgradeRecipe extends CustomRecipe {
    public static final SprintLeggingsUpgradeRecipe INSTANCE = new SprintLeggingsUpgradeRecipe();
    public static final RecipeSerializer<SprintLeggingsUpgradeRecipe> SERIALIZER = new RecipeSerializer<>(
            MapCodec.unit(INSTANCE), StreamCodec.unit(INSTANCE));

    private ItemStack leggings(CraftingInput input) {
        ItemStack leggings = ItemStack.EMPTY;
        if (input.ingredientCount() == 2) {
            boolean velocity = false;
            for (int i = 0; i < input.size(); i++) {
                var stack = input.getItem(i);
                if (stack.isEmpty()) continue;
                if (stack.is(ModItems.SPRINT_LEGGINGS) && leggings.isEmpty()) leggings = stack;
                else if (stack.is(ModItems.SUPERNATURAL_VELOCITY) && !velocity) velocity = true;
                else return ItemStack.EMPTY;
            }
            if (!velocity) return ItemStack.EMPTY;
        } else if (input.width() == 3 && input.height() == 3 && input.ingredientCount() == 9) {
            leggings = input.getItem(4);
            if (!leggings.is(ModItems.SPRINT_LEGGINGS)) return ItemStack.EMPTY;
            for (int i = 0; i < 9; i++)
                if (i != 4 && !input.getItem(i).is(ModItems.BIO_VELOCITY)) return ItemStack.EMPTY;
        }
        return leggings.isEmpty() || SprintLeggings.upgrades(leggings) >= SprintLeggings.MAX_UPGRADES ? ItemStack.EMPTY : leggings;
    }

    @Override
    public boolean matches(@NonNull CraftingInput input, @NonNull Level level) {
        return !leggings(input).isEmpty();
    }

    @Override
    public @NonNull ItemStack assemble(@NonNull CraftingInput input) {
        var original = leggings(input);
        if (original.isEmpty()) return ItemStack.EMPTY;
        var result = original.copyWithCount(1);
        result.set(ModDataComponents.SPRINT_LEGGINGS, new SprintLeggingsData(SprintLeggings.upgrades(original) + 1));
        return result;
    }

    @Override
    public @NonNull RecipeSerializer<SprintLeggingsUpgradeRecipe> getSerializer() {
        return SERIALIZER;
    }
}
