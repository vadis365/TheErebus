package erebus.recipes.altar;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.registries.ModCustomRecipes;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapelessCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class OfferingAltarRecipe implements Recipe<MultiStackInput> {

	public static final MapCodec<OfferingAltarRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
			(instance) -> instance
					.group(
							CommonInfo.MAP_CODEC.forGetter((recipe) -> recipe.info),
							CraftingRecipe.CraftingBookInfo.MAP_CODEC.forGetter((recipe) -> recipe.bookInfo),
							ItemStackTemplate.CODEC.fieldOf("result").forGetter((recipe) -> recipe.result),
							Codec.lazyInitialized(() -> Ingredient.CODEC.listOf(1, 3))
									.fieldOf("ingredients")
									.forGetter((recipe) -> recipe.ingredients)
					)
					.apply(instance, OfferingAltarRecipe::new)
	);

	public static final StreamCodec<RegistryFriendlyByteBuf, OfferingAltarRecipe> STREAM_CODEC = StreamCodec.composite(
			CommonInfo.STREAM_CODEC,
			(recipe) -> recipe.info,
			CraftingRecipe.CraftingBookInfo.STREAM_CODEC,
			(recipe) -> recipe.bookInfo,
			ItemStackTemplate.STREAM_CODEC,
			(recipe) -> recipe.result,
			Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
			(recipe) -> recipe.ingredients,
			OfferingAltarRecipe::new
	);

	public static final RecipeSerializer<OfferingAltarRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
	private final ItemStackTemplate result;
	private final List<Ingredient> ingredients;

	protected final CommonInfo info;
	protected final CraftingRecipe.CraftingBookInfo bookInfo;
	private final boolean isSimple;

	public OfferingAltarRecipe(CommonInfo info, CraftingRecipe.CraftingBookInfo bookInfo, ItemStackTemplate result, List<Ingredient> ingredients) {
		this.info = info;
		this.bookInfo = bookInfo;
		this.result = result;
		this.ingredients = ingredients;
		this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
	}

	@Override
	public @NonNull List<RecipeDisplay> display() {
		return List.of(new ShapelessCraftingRecipeDisplay(ingredients.stream().map(Ingredient::display).toList(), new SlotDisplay.ItemStackSlotDisplay(result), new SlotDisplay.ItemSlotDisplay(ModBlocks.OFFERING_ALTAR.asItem())));
	}

	@Override
	public boolean isSpecial() {
		return true;
	}

	@Override
	public boolean matches(MultiStackInput input, @NonNull Level level) {
		if (input.ingredientCount() != this.ingredients.size()) {
			return false;
		} else if (!this.isSimple) {
			ArrayList<ItemStack> nonEmptyItems = new ArrayList<>(input.ingredientCount());

			for (ItemStack item : input.items()) {
				if (!item.isEmpty()) {
					nonEmptyItems.add(item);
				}
			}

			return RecipeMatcher.findMatches(nonEmptyItems, ingredients) != null;
		} else {
			return input.size() == 1 && this.ingredients.size() == 1 ? ingredients.getFirst().test(input.getItem(0)) : input.stackedContents().canCraft(this, null);
		}
	}

	@Override
	public @NonNull ItemStack assemble(MultiStackInput multiStackInput) {
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
	public @NonNull RecipeSerializer<? extends Recipe<MultiStackInput>> getSerializer() {
		return SERIALIZER;
	}

	@Override
	public @NonNull RecipeType<? extends Recipe<MultiStackInput>> getType() {
		return ModCustomRecipes.OFFERING_ALTAR_RECIPE.get();
	}

	@Override
	public @NonNull PlacementInfo placementInfo() {
		return PlacementInfo.create(ingredients);
	}

	@Override
	public @NonNull RecipeBookCategory recipeBookCategory() {
		return ModCustomRecipes.OFFERING_ALTAR_RECIPE_CATEGORY.get();
	}
}
