package erebus.recipes.smoothie;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;

public class SmoothieRecipeSerializer implements RecipeSerializer<SmoothieRecipe> {

    public static final MapCodec<SmoothieRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            SizedFluidIngredient.FLAT_CODEC.listOf().fieldOf("fluids").flatXmap(fluids -> {
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
                    return aingredient.length > 4
                            ? DataResult.error(() -> "Too many ingredients for smoothie recipe. The maximum is 3")
                            : DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
                }
            }, DataResult::success).forGetter(SmoothieRecipe::getItemIngredients),
            ItemStack.STRICT_CODEC.fieldOf("result").forGetter(SmoothieRecipe::result)
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
        buffer.writeInt(recipe.getFluidIngredients().size());
        buffer.writeShort(recipe.getItemIngredients().size());
        recipe.fluids().forEach(fluid -> SizedFluidIngredient.STREAM_CODEC.encode(buffer, fluid));
        recipe.items().forEach(item -> Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, item));
        ItemStack.STREAM_CODEC.encode(buffer, recipe.result());
    }

    public static SmoothieRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
        NonNullList<SizedFluidIngredient> fluids = NonNullList.withSize(buffer.readInt(), SizedFluidIngredient.of(FluidStack.EMPTY));
        fluids.replaceAll(fluid -> SizedFluidIngredient.STREAM_CODEC.decode(buffer));

        NonNullList<Ingredient> items = NonNullList.withSize(buffer.readShort(), Ingredient.EMPTY);
        items.replaceAll(item -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));

        ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);

        return new SmoothieRecipe(fluids, items, result);
    }
}
