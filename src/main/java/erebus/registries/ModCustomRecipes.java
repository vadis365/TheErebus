package erebus.registries;

import erebus.Erebus;
import erebus.recipes.altar.OfferingAltarRecipe;
import erebus.recipes.smoothie.SmoothieRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCustomRecipes {
	private static final String OFFERING_ALTAR_ID = "offering_altar";
	private static final String SMOOTHIE_ID = "smoothie";

	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Erebus.MODID);
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Erebus.MODID);
	public static final DeferredRegister<RecipeBookCategory> RECIPE_BOOK_CATEGORIES = DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, Erebus.MODID);

	public static final DeferredHolder<RecipeType<?>, RecipeType<OfferingAltarRecipe>> OFFERING_ALTAR_RECIPE;
	public static final DeferredHolder<RecipeType<?>, RecipeType<SmoothieRecipe>> SMOOTHIE_RECIPE;

	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<OfferingAltarRecipe>> OFFERING_ALTER_RECIPE_SERIALIZER;
	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<SmoothieRecipe>> SMOOTHIE_RECIPE_SERIALIZER;

	public static final Supplier<RecipeBookCategory> OFFERING_ALTAR_RECIPE_CATEGORY;
	public static final Supplier<RecipeBookCategory> SMOOTHIE_RECIPE_CATEGORY;

	public static <T extends Recipe<?>> DeferredHolder<RecipeType<?>, RecipeType<T>> registerRecipeType(String name) {
		return RECIPE_TYPES.register(name, () -> RecipeType.simple(Erebus.prefix(name)));
	}

	private static <T extends Recipe<?>> DeferredHolder<RecipeSerializer<?>, RecipeSerializer<T>> registerRecipeSerializer(String name, Supplier<RecipeSerializer<T>> serializerSupplier) {
		return RECIPE_SERIALIZERS.register(name, serializerSupplier);
	}

	private static DeferredHolder<RecipeBookCategory, RecipeBookCategory> registerRecipeBookCategory(String name) {
		return RECIPE_BOOK_CATEGORIES.register(name, RecipeBookCategory::new);
	}

	static {
		OFFERING_ALTAR_RECIPE = registerRecipeType(OFFERING_ALTAR_ID);
		SMOOTHIE_RECIPE = registerRecipeType(SMOOTHIE_ID);

		OFFERING_ALTER_RECIPE_SERIALIZER = registerRecipeSerializer(OFFERING_ALTAR_ID, () -> OfferingAltarRecipe.SERIALIZER);
		SMOOTHIE_RECIPE_SERIALIZER = registerRecipeSerializer(SMOOTHIE_ID, () -> SmoothieRecipe.SERIALIZER);

		SMOOTHIE_RECIPE_CATEGORY = registerRecipeBookCategory(SMOOTHIE_ID);
		OFFERING_ALTAR_RECIPE_CATEGORY = registerRecipeBookCategory(OFFERING_ALTAR_ID);
	}
}