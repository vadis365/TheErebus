package erebus.recipes.altar;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.ModCustomRecipes;
import erebus.recipes.MultiStackInput;
import erebus.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;

import java.util.ArrayList;
import java.util.List;

public record OfferingAltarRecipeMaker(NonNullList<Ingredient> items, ItemStack result) implements OfferingAltarRecipe {

	@Override
	public boolean matches(MultiStackInput input, Level level) {
		if (input.ingredientCount() != this.items.size()) {
			return false;
		}
		List<ItemStack> nonEmptyItems = new ArrayList<>(input.ingredientCount());
		for (var item : input.items()) {
			if (!item.isEmpty()) {
				nonEmptyItems.add(item);
			}
		}
		return RecipeMatcher.findMatches(nonEmptyItems, this.items) != null;
	}

	@Override
	public ItemStack assemble(MultiStackInput input, HolderLookup.Provider registries) {
		return this.result.copy();
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return width * height >= this.items.size();
	}

	@Override
	public ItemStack getResultItem(HolderLookup.Provider registries) {
		return this.result;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return ModCustomRecipes.OFFERING_ALTAR_RECIPES_SERIALIZER.get();
	}

	@Override
	public NonNullList<Ingredient> getIngredients() {
		return this.items();
	}

	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(ModItems.GAEAN_GEM.get());
	}

	public static class Serializer implements RecipeSerializer<OfferingAltarRecipeMaker> {

		public static final MapCodec<OfferingAltarRecipeMaker> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
			Ingredient.CODEC_NONEMPTY.listOf().fieldOf("ingredients").flatXmap(ingredients -> {
				Ingredient[] aingredient = ingredients.toArray(Ingredient[]::new);
				if (aingredient.length == 0) {
					return DataResult.error(() -> "No ingredients for offering altar recipe");
				} else {
					return aingredient.length > 3
						? DataResult.error(() -> "Too many ingredients for offering altar recipe. The maximum is 3")
						: DataResult.success(NonNullList.of(Ingredient.EMPTY, aingredient));
				}
			}, DataResult::success).forGetter(OfferingAltarRecipeMaker::items),
			ItemStack.STRICT_CODEC.fieldOf("result").forGetter(OfferingAltarRecipeMaker::result)
		).apply(instance, OfferingAltarRecipeMaker::new));

		public static final StreamCodec<RegistryFriendlyByteBuf, OfferingAltarRecipeMaker> STREAM_CODEC = StreamCodec.of(OfferingAltarRecipeMaker.Serializer::toNetwork, OfferingAltarRecipeMaker.Serializer::fromNetwork);

		@Override
		public MapCodec<OfferingAltarRecipeMaker> codec() {
			return CODEC;
		}

		@Override
		public StreamCodec<RegistryFriendlyByteBuf, OfferingAltarRecipeMaker> streamCodec() {
			return STREAM_CODEC;
		}

		private static OfferingAltarRecipeMaker fromNetwork(RegistryFriendlyByteBuf buffer) {
			int i = buffer.readVarInt();
			NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);
			nonnulllist.replaceAll(ingredient -> Ingredient.CONTENTS_STREAM_CODEC.decode(buffer));
			ItemStack result = ItemStack.STREAM_CODEC.decode(buffer);
			return new OfferingAltarRecipeMaker(nonnulllist, result);
		}

		private static void toNetwork(RegistryFriendlyByteBuf buffer, OfferingAltarRecipeMaker recipe) {
			buffer.writeVarInt(recipe.items().size());

			for (Ingredient ingredient : recipe.items()) {
				Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, ingredient);
			}

			ItemStack.STREAM_CODEC.encode(buffer, recipe.result());
		}
	}
}