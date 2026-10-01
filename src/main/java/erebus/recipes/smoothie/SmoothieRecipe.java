package erebus.recipes.smoothie;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.ModCustomRecipes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class SmoothieRecipe implements Recipe<SmoothieRecipeInput> {

    public static final MapCodec<SmoothieRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
            (instance) -> instance.group(
                            CommonInfo.MAP_CODEC.forGetter((recipe) -> recipe.info),
                            CraftingRecipe.CraftingBookInfo.MAP_CODEC.forGetter((recipe) -> recipe.bookInfo),
                            ItemStackTemplate.CODEC.fieldOf("result").forGetter((recipe) -> recipe.result),
                            Codec.lazyInitialized(() -> Ingredient.CODEC.listOf(1, 6))
                                    .fieldOf("ingredients")
                                    .forGetter((recipe) -> recipe.ingredients),
                            Codec.lazyInitialized(() -> SizedFluidIngredient.CODEC.listOf(1, 4))
                                    .fieldOf("fluidIngredients")
                                    .forGetter((recipe) -> recipe.fluidIngredients)
                    )
                    .apply(instance, SmoothieRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, SmoothieRecipe> STREAM_CODEC = StreamCodec.composite(
            CommonInfo.STREAM_CODEC,
            (recipe) -> recipe.info,
            CraftingRecipe.CraftingBookInfo.STREAM_CODEC,
            (recipe) -> recipe.bookInfo,
            ItemStackTemplate.STREAM_CODEC,
            (recipe) -> recipe.result,
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            (recipe) -> recipe.ingredients,
            SizedFluidIngredient.STREAM_CODEC.apply(ByteBufCodecs.list()),
            (recipe) -> recipe.fluidIngredients,
            SmoothieRecipe::new
    );

    public static final RecipeSerializer<SmoothieRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
    protected final CommonInfo info;
    protected final CraftingRecipe.CraftingBookInfo bookInfo;
    private final ItemStackTemplate result;
    private final List<Ingredient> ingredients;
    private final List<SizedFluidIngredient> fluidIngredients;

    public SmoothieRecipe(CommonInfo info, CraftingRecipe.CraftingBookInfo bookInfo, ItemStackTemplate result, List<Ingredient> ingredients, List<SizedFluidIngredient> fluidIngredients) {
        this.info = info;
        this.bookInfo = bookInfo;
        this.result = result;
        this.ingredients = ingredients;
        this.fluidIngredients = fluidIngredients;
    }

    @Override
    public @NonNull RecipeType<? extends Recipe<SmoothieRecipeInput>> getType() {
        return ModCustomRecipes.SMOOTHIE_RECIPE.get();
    }

    @Override
    public @NonNull PlacementInfo placementInfo() {
        return PlacementInfo.create(ingredients);
    }

    @Override
    public @NonNull RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    @Override
    public boolean matches(@NotNull SmoothieRecipeInput input, @NotNull Level level) {
        return itemConsumption(input) != null && fluidAssignments(input) != null;
    }

    public int[] itemConsumption(SmoothieRecipeInput input) {
        if (input.size() != 5 || input.getItem(4).getCount() != 1 || !ingredients.getFirst().test(input.getItem(4))) return null;
        int[] used = new int[5];
        used[4] = 1;
        return matchItems(input, 1, used) ? used : null;
    }

    private boolean matchItems(SmoothieRecipeInput input, int ingredient, int[] used) {
        if (ingredient == ingredients.size()) return true;
        for (int slot = 0; slot < 4; slot++) {
            ItemStack stack = input.getItem(slot);
            if (used[slot] < stack.getCount() && ingredients.get(ingredient).test(stack)) {
                used[slot]++;
                if (matchItems(input, ingredient + 1, used)) return true;
                used[slot]--;
            }
        }
        return false;
    }

    public int[] fluidAssignments(SmoothieRecipeInput input) {
        int[] remaining = input.fluids().stream().mapToInt(FluidStack::getAmount).toArray();
        int[] assignments = new int[fluidIngredients.size()];
        return matchFluids(input, 0, remaining, assignments) ? assignments : null;
    }

    private boolean matchFluids(SmoothieRecipeInput input, int ingredient, int[] remaining, int[] assignments) {
        if (ingredient == fluidIngredients.size()) return true;
        var required = fluidIngredients.get(ingredient);
        for (int tank = 0; tank < remaining.length; tank++) {
            if (remaining[tank] >= required.amount() && required.test(input.fluids().get(tank))) {
                remaining[tank] -= required.amount();
                assignments[ingredient] = tank;
                if (matchFluids(input, ingredient + 1, remaining, assignments)) return true;
                remaining[tank] += required.amount();
            }
        }
        return false;
    }

    @Override
    public @NonNull ItemStack assemble(SmoothieRecipeInput input) {
        return result.create();
    }

    @Override
    public boolean showNotification() {
        return info.showNotification();
    }

    @Override
    public @NonNull String group() {
        return bookInfo.group();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public @NonNull RecipeSerializer<? extends Recipe<SmoothieRecipeInput>> getSerializer() {
        return SERIALIZER;
    }

    public List<SizedFluidIngredient> getFluidIngredients() {
        return fluidIngredients;
    }
}
