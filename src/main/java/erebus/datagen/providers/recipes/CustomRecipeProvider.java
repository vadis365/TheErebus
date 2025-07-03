package erebus.datagen.providers.recipes;

import erebus.recipes.altar.OfferingAltarRecipeBuilder;
import erebus.recipes.smoothie.SmoothieRecipeBuilder;
import erebus.registries.ModFluids;
import erebus.registries.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.fluids.crafting.SizedFluidIngredient;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class CustomRecipeProvider extends ErebusRecipeProvider {

	public CustomRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
		super(packOutput, registries);
	}

	public void buildRecipes(@NotNull RecipeOutput output) {
		OfferingAltarRecipeBuilder.assembly(ModItems.GAEAN_GEM)
			.requires(Items.DIAMOND)
			.requires(Items.EMERALD)
			.requires(Blocks.OBSIDIAN)
			.save(output);

		new SmoothieRecipeBuilder(ModItems.GREEN_TEA_GRASSHOPPER)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addItemIngredient(Ingredient.of(ModItems.GRASSHOPPER_LEG_RAW))
				.addItemIngredient(Ingredient.of(ModItems.GRASSHOPPER_LEG_RAW))
				.addItemIngredient(Ingredient.of(ModItems.ELASTIC_FIBER))
				.addItemIngredient(Ingredient.of(ModItems.FLY_WING))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.BEETLE_JUICE_STILL.get(), 1000))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.MONEY_HONEY)
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.NOTHING_IN_THE_MIDDLE)
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.GREEN_GIANT)
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.SEEDY_GOODNESS)
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.GIVIN_ME_THE_BLUES)
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.HOT_HOT_BABY)
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.DONT_MEDDLE_WITH_THE_NETTLE)
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.LIQUID_GOLD)
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.BRYUFS_BREW)
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.MELONADE)
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.MELONADE_SPARKLY)
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);
	}
}
