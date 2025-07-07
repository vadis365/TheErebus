package erebus.recipes.smoothie;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.recipes.util.SmoothieIngredientCounts;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.fluids.crafting.FluidIngredient;
import org.jetbrains.annotations.NotNull;

public class SmoothieRecipeSerializer implements RecipeSerializer<SmoothieRecipe> {

    public static final MapCodec<SmoothieRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            FluidIngredient.CODEC.listOf().fieldOf("fluids").flatXmap(fluids -> {
                if(fluids.size() > 4) {
                    return DataResult.error(() -> "Too many fluids for smoothie recipe. The maximum is 4");
                } else {
                    return DataResult.success(NonNullList.copyOf(fluids));
                }
            }, DataResult::success).forGetter(SmoothieRecipe::getFluidIngredients),
            Ingredient.CODEC_NONEMPTY.listOf().fieldOf("items").flatXmap(items -> {
                Ingredient[] aingredient = items.toArray(Ingredient[]::new);
                if (aingredient.length == 0) {
                    return DataResult.error(() -> "No ingredients for smoothie recipe");
                } else {
                    return aingredient.length > 5
                            ? DataResult.error(() -> "Too many ingredients for smoothie recipe. The maximum is 5")
                            : DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
                }
            }, DataResult::success).forGetter(SmoothieRecipe::getItemIngredients),
            SmoothieIngredientCounts.CODEC.fieldOf("counts").forGetter(SmoothieRecipe::getCounts),
            ItemStack.STRICT_CODEC.fieldOf("result").forGetter(SmoothieRecipe::getResult)
    ).apply(instance, SmoothieRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SmoothieRecipe> STREAM_CODEC = StreamCodec.of(SmoothieRecipeSerializer::toNetwork, SmoothieRecipeSerializer::fromNetwork);

    @Override
    public @NotNull MapCodec<SmoothieRecipe> codec() {
        return CODEC;
    }

    @Override
    public @NotNull StreamCodec<RegistryFriendlyByteBuf, SmoothieRecipe> streamCodec() {
        return STREAM_CODEC;
    }

    public static void toNetwork(RegistryFriendlyByteBuf buffer, SmoothieRecipe recipe) {
        SmoothieIngredientCounts.STREAM_CODEC.encode(buffer, recipe.getCounts());
        recipe.getFluidIngredients().forEach(fluid -> FluidIngredient.STREAM_CODEC.encode(buffer, fluid));
        recipe.getItemIngredients().forEach(item -> Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, item));
        ItemStack.STREAM_CODEC.encode(buffer, recipe.getResult());
    }

    public static SmoothieRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
        SmoothieIngredientCounts counts = SmoothieIngredientCounts.STREAM_CODEC.decode(buffer);

        NonNullList<FluidIngredient> fluids = NonNullList.withSize(counts.fluidCount(), FluidIngredient.empty());
        fluids.replaceAll(fluid -> FluidIngredient.STREAM_CODEC.decode(buffer));

        NonNullList<Ingredient> items = NonNullList.withSize(counts.itemCount(), Ingredient.EMPTY);
        items.replaceAll(item -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));

        ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);

        return new SmoothieRecipe(fluids, items, result);
    }
}
