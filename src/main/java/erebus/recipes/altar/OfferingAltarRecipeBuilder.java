package erebus.recipes.altar;

import net.minecraft.advancements.Criterion;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeUnlockAdvancementBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OfferingAltarRecipeBuilder implements RecipeBuilder {

	private final HolderGetter<Item> items;
	private final ItemStackTemplate result;
	private final List<Ingredient> ingredients = new ArrayList<>();
	private final RecipeUnlockAdvancementBuilder advancementBuilder = new RecipeUnlockAdvancementBuilder();
	private String group;

	private OfferingAltarRecipeBuilder(HolderGetter<Item> items, ItemStackTemplate result) {
		this.items = items;
		this.result = result;
	}

	public static OfferingAltarRecipeBuilder altarRecipe(HolderGetter<Item> items, ItemStackTemplate result) {
		return new OfferingAltarRecipeBuilder(items, result);
	}

	public static OfferingAltarRecipeBuilder altarRecipe(HolderGetter<Item> items, ItemLike result) {
		return altarRecipe(items, result, 1);
	}

	public static OfferingAltarRecipeBuilder altarRecipe(HolderGetter<Item> items, ItemLike result, int count) {
		return new OfferingAltarRecipeBuilder(items, new ItemStackTemplate(result.asItem(), count));
	}

	public OfferingAltarRecipeBuilder requires(TagKey<Item> tag) {
		return requires(Ingredient.of(items.getOrThrow(tag)));
	}

	public OfferingAltarRecipeBuilder requires(ItemLike item) {
		return requires(item, 1);
	}

	public OfferingAltarRecipeBuilder requires(ItemLike item, int count) {
		for (int i = 0; i < count; i++) {
			requires(Ingredient.of(item));
		}

		return this;
	}

	public OfferingAltarRecipeBuilder requires(Ingredient ingredient) {
		return requires(ingredient, 1);
	}

	public OfferingAltarRecipeBuilder requires(Ingredient ingredient, int count) {
		for (int i = 0; i < count; i++) {
			ingredients.add(ingredient);
		}

		return this;
	}

	public @NonNull OfferingAltarRecipeBuilder unlockedBy(@NonNull String name, @NonNull Criterion<?> criterion) {
		advancementBuilder.unlockedBy(name, criterion);
		return this;
	}

	public @NonNull OfferingAltarRecipeBuilder group(@Nullable String group) {
		this.group = group;
		return this;
	}

	@Override
	public @NonNull ResourceKey<Recipe<?>> defaultId() {
		return RecipeBuilder.getDefaultRecipeId(result);
	}

	@Override
	public void save(RecipeOutput output, @NonNull ResourceKey<Recipe<?>> id) {
		OfferingAltarRecipe recipe = new OfferingAltarRecipe(
				RecipeBuilder.createCraftingCommonInfo(true),
				RecipeBuilder.createCraftingBookInfo(RecipeCategory.MISC, group),
				result,
				ingredients
		);
		output.accept(id, recipe, advancementBuilder.build(output, id, RecipeCategory.MISC));
	}
}
