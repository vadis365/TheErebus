package erebus.datagen.providers.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeOutput;

public class CustomRecipeProvider extends ErebusRecipeProvider {

	public CustomRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
		super(registries, output);
	}

	public void buildRecipes() {
		/*OfferingAltarRecipeBuilder.assembly(ModItems.GAEAN_GEM)
			.requires(Items.DIAMOND)
			.requires(Items.EMERALD)
			.requires(Blocks.OBSIDIAN)
			.save(output);*/

		/*new SmoothieRecipeBuilder(ModItems.GREEN_TEA_GRASSHOPPER)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addItemIngredient(Ingredient.of(ModItems.GRASSHOPPER_LEG_RAW))
				.addItemIngredient(Ingredient.of(ModItems.GRASSHOPPER_LEG_RAW))
				.addItemIngredient(Ingredient.of(ModItems.ELASTIC_FIBER))
				.addItemIngredient(Ingredient.of(ModItems.FLY_WING))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.BEETLE_JUICE_STILL.get(), FluidType.BUCKET_VOLUME))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.MONEY_HONEY)
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.HONEY_STILL.get(), FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(ModItems.HONEY_DRIP))
				.addItemIngredient(Ingredient.of(ModItems.HONEY_DRIP))
				.addItemIngredient(Ingredient.of(ModItems.NECTAR))
				.addItemIngredient(Ingredient.of(Items.GOLD_NUGGET))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.NOTHING_IN_THE_MIDDLE)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.BEETLE_JUICE_STILL.get(), FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(ModItems.CAMO_POWDER))
				.addItemIngredient(Ingredient.of(ModItems.CAMO_POWDER))
				.addItemIngredient(Ingredient.of(ModItems.DARK_FRUIT))
				.addItemIngredient(Ingredient.of(ModItems.SWAMP_BERRIES))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.GREEN_GIANT)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.ANTI_VENOM_STILL.get(), FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(ModItems.REPELLENT))
				.addItemIngredient(Ingredient.of(ModItems.POISON_GLAND))
				.addItemIngredient(Ingredient.of(ModItems.POISON_GLAND))
				.addItemIngredient(Ingredient.of(ModItems.WASP_STING))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.SEEDY_GOODNESS)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.BEETLE_JUICE_STILL.get(), FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(ModItems.BIO_VELOCITY))
				.addItemIngredient(Ingredient.of(ModItems.DARK_FRUIT_SEEDS))
				.addItemIngredient(Ingredient.of(Items.MELON_SEEDS))
				.addItemIngredient(Ingredient.of(Items.PUMPKIN_SEEDS))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.GIVIN_ME_THE_BLUES)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(NeoForgeMod.MILK.get(), FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(ModItems.BLUEBELL_PETAL))
				.addItemIngredient(Ingredient.of(ModItems.BLUEBELL_PETAL))
				.addItemIngredient(Ingredient.of(Items.LAPIS_LAZULI))
				.addItemIngredient(Ingredient.of(Items.LAPIS_LAZULI))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.HOT_HOT_BABY)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.ANTI_VENOM_STILL.get(), FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(ModItems.WASP_STING))
				.addItemIngredient(Ingredient.of(ModItems.BOGMAW_ROOT))
				.addItemIngredient(Ingredient.of(ModBlocks.FIRE_BLOOM))
				.addItemIngredient(Ingredient.of(ModBlocks.FIRE_BLOOM))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.DONT_MEDDLE_WITH_THE_NETTLE)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.HONEY_STILL.get(), FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(ModItems.NETTLE_FLOWERS))
				.addItemIngredient(Ingredient.of(ModItems.NETTLE_FLOWERS))
				.addItemIngredient(Ingredient.of(ModItems.JADE_BERRIES))
				.addItemIngredient(Ingredient.of(ModItems.PLATE_EXO))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.LIQUID_GOLD)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(NeoForgeMod.MILK.get(), FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(ModItems.LIFE_BLOOD))
				.addItemIngredient(Ingredient.of(ModItems.LIFE_BLOOD))
				.addItemIngredient(Ingredient.of(ModItems.BAMBOO))
				.addItemIngredient(Ingredient.of(Items.GLISTERING_MELON_SLICE))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.BRYUFS_BREW)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.BEETLE_JUICE_STILL.get(), FluidType.BUCKET_VOLUME))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.HONEY_STILL.get(), FluidType.BUCKET_VOLUME))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.ANTI_VENOM_STILL.get(), FluidType.BUCKET_VOLUME))
				.addFluidIngredient(SizedFluidIngredient.of(NeoForgeMod.MILK.get(), FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(ModItems.COMPOUND_EYES))
				.addItemIngredient(Ingredient.of(ModItems.TERPSISHROOM))
				.addItemIngredient(Ingredient.of(ModItems.TURNIP))
				.addItemIngredient(Ingredient.of(ModItems.HEART_BERRIES))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.MELONADE)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(Fluids.WATER, FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(Items.MELON_SLICE))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.MELONADE_SPARKLY)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(Fluids.WATER, FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(Items.GLISTERING_MELON_SLICE))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.ANTI_VENOM_BUCKET)
				.addItemIngredient(Ingredient.of(Items.BUCKET))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.BEETLE_JUICE_STILL.get(), FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(ModItems.POISON_GLAND))
				.addItemIngredient(Ingredient.of(ModItems.POISON_GLAND))
				.addItemIngredient(Ingredient.of(ModItems.NETTLE_LEAVES))
				.addItemIngredient(Ingredient.of(ModItems.NETTLE_LEAVES))
				.unlockedBy("has_poison_gland", has(ModItems.POISON_GLAND))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.ANTI_VENOM_BOTTLE)
				.addItemIngredient(Ingredient.of(Items.GLASS_BOTTLE))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.BEETLE_JUICE_STILL.get(), FluidType.BUCKET_VOLUME))
				.addItemIngredient(Ingredient.of(ModItems.POISON_GLAND))
				.addItemIngredient(Ingredient.of(ModItems.NETTLE_LEAVES))
				.unlockedBy("has_poison_gland", has(ModItems.POISON_GLAND))
				.save(output);*/
	}
}
