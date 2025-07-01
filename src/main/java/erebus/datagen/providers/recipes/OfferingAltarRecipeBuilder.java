package erebus.datagen.providers.recipes;

import erebus.recipes.altar.OfferingAltarRecipe;
import erebus.recipes.altar.OfferingAltarRecipeMaker;
import net.minecraft.advancements.Criterion;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

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

	public OfferingAltarRecipeBuilder requires(TagKey<Item> tag) {
		return this.requires(Ingredient.of(tag));
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
	public RecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
		return this;
	}

	@Override
	public RecipeBuilder group(@Nullable String groupName) {
		return this;
	}

	@Override
	public Item getResult() {
		return this.resultStack != null ? this.resultStack.getItem() : Items.AIR;
	}

	@Override
	public void save(RecipeOutput recipeOutput, ResourceLocation id) {
		OfferingAltarRecipe recipe = null;
		if (this.resultStack != null)
			recipe = new OfferingAltarRecipeMaker(this.ingredients, this.resultStack);
		recipeOutput.accept(id.withPrefix("offering_altar/"), recipe, null);
	}
}
