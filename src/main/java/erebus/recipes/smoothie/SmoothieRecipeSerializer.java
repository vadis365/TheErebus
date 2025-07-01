package erebus.recipes.smoothie;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class SmoothieRecipeSerializer implements RecipeSerializer<SmoothieRecipe> {
    @Override
    public @NotNull MapCodec<SmoothieRecipe> codec() {
        return null;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, SmoothieRecipe> streamCodec() {
        return null;
    }
}
