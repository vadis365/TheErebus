package erebus.registries;

import erebus.Erebus;
import erebus.recipes.altar.OfferingAltarRecipe;
import erebus.recipes.altar.OfferingAltarRecipeMaker;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCustomRecipes {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, Erebus.MODID);
	public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, Erebus.MODID);
	
	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<OfferingAltarRecipeMaker>> OFFERING_ALTAR_RECIPES_SERIALIZER = RECIPE_SERIALIZERS.register("offering_altar_recipes", OfferingAltarRecipeMaker.Serializer::new);
	public static final DeferredHolder<RecipeType<?>, RecipeType<OfferingAltarRecipe>> OFFERING_ALTAR_RECIPE = RECIPE_TYPES.register("offering_altar", () -> RecipeType.simple(Erebus.prefix("offering_altar")));
}
