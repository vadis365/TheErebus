package erebus.registries;

import erebus.Erebus;
import erebus.recipes.altar.OfferingAltarRecipe;
import erebus.recipes.altar.OfferingAltarRecipeMaker;
import erebus.recipes.smoothie.SmoothieRecipe;
import erebus.recipes.smoothie.SmoothieRecipeSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCustomRecipes {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Erebus.MODID);
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Erebus.MODID);

	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<OfferingAltarRecipeMaker>> OFFERING_ALTAR_RECIPES_SERIALIZER;
    public static final DeferredHolder<RecipeSerializer<?>, SmoothieRecipeSerializer> SMOOTHIE_RECIPE_SERIALIZER;

    public static final DeferredHolder<RecipeType<?>, RecipeType<OfferingAltarRecipe>> OFFERING_ALTAR_RECIPE;
    public static final DeferredHolder<RecipeType<?>, RecipeType<SmoothieRecipe>> SMOOTHIE_RECIPE;

	static {
		OFFERING_ALTAR_RECIPES_SERIALIZER = registerRecipeSerializer("offering_altar_recipes", OfferingAltarRecipeMaker.Serializer::new);
		SMOOTHIE_RECIPE_SERIALIZER = registerRecipeSerializer("smoothie_recipes", SmoothieRecipeSerializer::new);

		OFFERING_ALTAR_RECIPE = registerRecipeType("offering_altar");
		SMOOTHIE_RECIPE = registerRecipeType("smoothie");
	}

    /**
	 * Generic method to register a recipe type
	 * @param name The name of the recipe type
	 * @return A DeferredHolder for the registered recipe type
	 */
	public static <T extends Recipe<?>> DeferredHolder<RecipeType<?>, RecipeType<T>> registerRecipeType(String name) {
		return RECIPE_TYPES.register(name, () -> RecipeType.simple(Erebus.prefix(name)));
	}

	/**
	 * Generic method to register a recipe serializer
	 * @param name The name of the recipe serializer
	 * @param serializerSupplier A supplier for the serializer instance
	 * @return A DeferredHolder for the registered recipe serializer
	 */
	public static <T extends RecipeSerializer<?>> DeferredHolder<RecipeSerializer<?>, T> registerRecipeSerializer(String name, Supplier<T> serializerSupplier) {
		return RECIPE_SERIALIZERS.register(name, serializerSupplier);
	}
}
