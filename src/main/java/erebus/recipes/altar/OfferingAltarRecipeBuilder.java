package erebus.recipes.altar;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public class OfferingAltarRecipeBuilder implements RecipeBuilder {

	@Nullable
	private final ItemStack resultStack;
	private final NonNullList<Ingredient> ingredients = NonNullList.create();

	private OfferingAltarRecipeBuilder(@Nullable ItemStack result) {
		this.resultStack = result;
	}

	public static OfferingAltarRecipeBuilder assembly(ItemLike result) {
		return new OfferingAltarRecipeBuilder(new ItemStack(result, 1));
	}

	public static OfferingAltarRecipeBuilder assembly(ItemStack result) {
		return new OfferingAltarRecipeBuilder(result);
	}

	public static OfferingAltarRecipeBuilder reversion() {
		return new OfferingAltarRecipeBuilder(null);
	}

	public OfferingAltarRecipeBuilder requires(ItemLike item) {
		return this.requires(item, 1);
	}

	public OfferingAltarRecipeBuilder requires(ItemLike item, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.requires(Ingredient.of(item));
		}

		return this;
	}

	public OfferingAltarRecipeBuilder requires(Ingredient ingredient) {
		return this.requires(ingredient, 1);
	}

	public OfferingAltarRecipeBuilder requires(Ingredient ingredient, int quantity) {
		for (int i = 0; i < quantity; i++) {
			this.ingredients.add(ingredient);
		}

		return this;
	}

	@Override
	public @NonNull RecipeBuilder unlockedBy(@NonNull String name, @NonNull Criterion<?> criterion) {
		return this;
	}

	@Override
	public @NonNull RecipeBuilder group(@Nullable String groupName) {
		return this;
	}

	@Override
	public @NonNull ResourceKey<Recipe<?>> defaultId() {
		return null;
	}

	public Item getResult() {
		return this.resultStack != null ? this.resultStack.getItem() : Items.AIR;
	}

	@Override
	public void save(@NonNull RecipeOutput output, @NonNull ResourceKey<Recipe<?>> key) {
		OfferingAltarRecipe recipe = null;
		if (this.resultStack != null)
			recipe = new OfferingAltarRecipeMaker(this.ingredients, this.resultStack);
		output.accept(key, recipe, null);
	}
}
