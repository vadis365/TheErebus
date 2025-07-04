package erebus.datagen.providers.recipes;

import erebus.recipes.altar.OfferingAltarRecipeBuilder;
import erebus.recipes.smoothie.SmoothieRecipeBuilder;
import erebus.registries.ModFluids;
import erebus.registries.ModItems;
import erebus.registries.blocks.providers.PlantBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidUtil;
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
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.HONEY_STILL.get(), 1000))
				.addItemIngredient(Ingredient.of(ModItems.HONEY_DRIP))
				.addItemIngredient(Ingredient.of(ModItems.HONEY_DRIP))
				.addItemIngredient(Ingredient.of(ModItems.NECTAR))
				.addItemIngredient(Ingredient.of(Items.GOLD_NUGGET))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.NOTHING_IN_THE_MIDDLE)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.BEETLE_JUICE_STILL.get(), 1000))
				.addItemIngredient(Ingredient.of(ModItems.CAMO_POWDER))
				.addItemIngredient(Ingredient.of(ModItems.CAMO_POWDER))
				.addItemIngredient(Ingredient.of(ModItems.DARK_FRUIT))
				.addItemIngredient(Ingredient.of(ModItems.SWAMP_BERRIES))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.GREEN_GIANT)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.ANTI_VENOM_STILL.get(), 1000))
				.addItemIngredient(Ingredient.of(ModItems.REPELLENT))
				.addItemIngredient(Ingredient.of(ModItems.POISON_GLAND))
				.addItemIngredient(Ingredient.of(ModItems.POISON_GLAND))
				.addItemIngredient(Ingredient.of(ModItems.WASP_STING))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.SEEDY_GOODNESS)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.BEETLE_JUICE_STILL.get(), 1000))
				.addItemIngredient(Ingredient.of(ModItems.BIO_VELOCITY))
				.addItemIngredient(Ingredient.of(ModItems.DARK_FRUIT_SEEDS))
				.addItemIngredient(Ingredient.of(Items.MELON_SEEDS))
				.addItemIngredient(Ingredient.of(Items.PUMPKIN_SEEDS))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.GIVIN_ME_THE_BLUES)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(Tags.Fluids.MILK, 1000))
				.addItemIngredient(Ingredient.of(ModItems.BLUEBELL_PETAL))
				.addItemIngredient(Ingredient.of(ModItems.BLUEBELL_PETAL))
				.addItemIngredient(Ingredient.of(Items.LAPIS_LAZULI))
				.addItemIngredient(Ingredient.of(Items.LAPIS_LAZULI))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.HOT_HOT_BABY)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.ANTI_VENOM_STILL.get(), 1000))
				.addItemIngredient(Ingredient.of(ModItems.WASP_STING))
				.addItemIngredient(Ingredient.of(ModItems.BOGMAW_ROOT))
				.addItemIngredient(Ingredient.of(PlantBlocks.FIRE_BLOOM))
				.addItemIngredient(Ingredient.of(PlantBlocks.FIRE_BLOOM))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.DONT_MEDDLE_WITH_THE_NETTLE)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.HONEY_STILL.get(), 1000))
				.addItemIngredient(Ingredient.of(ModItems.NETTLE_FLOWERS))
				.addItemIngredient(Ingredient.of(ModItems.NETTLE_FLOWERS))
				.addItemIngredient(Ingredient.of(ModItems.JADE_BERRIES))
				.addItemIngredient(Ingredient.of(ModItems.PLATE_EXO))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.LIQUID_GOLD)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(Tags.Fluids.MILK, 1000))
				.addItemIngredient(Ingredient.of(ModItems.LIFE_BLOOD))
				.addItemIngredient(Ingredient.of(ModItems.LIFE_BLOOD))
				.addItemIngredient(Ingredient.of(ModItems.BAMBOO))
				.addItemIngredient(Ingredient.of(Items.GLISTERING_MELON_SLICE))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.BRYUFS_BREW)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.BEETLE_JUICE_STILL.get(), 1000))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.HONEY_STILL.get(), 1000))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.ANTI_VENOM_STILL.get(), 1000))
				.addFluidIngredient(SizedFluidIngredient.of(Tags.Fluids.MILK, 1000))
				.addItemIngredient(Ingredient.of(ModItems.COMPOUND_EYES))
				.addItemIngredient(Ingredient.of(ModItems.TERPSISHROOM))
				.addItemIngredient(Ingredient.of(ModItems.TURNIP))
				.addItemIngredient(Ingredient.of(ModItems.HEART_BERRIES))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.MELONADE)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(Fluids.WATER, 1000))
				.addItemIngredient(Ingredient.of(Items.MELON_SLICE))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.MELONADE_SPARKLY)
				.addItemIngredient(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.addFluidIngredient(SizedFluidIngredient.of(Fluids.WATER, 1000))
				.addItemIngredient(Ingredient.of(Items.GLISTERING_MELON_SLICE))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		new SmoothieRecipeBuilder(FluidUtil.getFilledBucket(new FluidStack(ModFluids.ANTI_VENOM_STILL.get().getSource(), 1000)).getItem())
				.addItemIngredient(Ingredient.of(Items.BUCKET))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.BEETLE_JUICE_STILL.get(), 1000))
				.addItemIngredient(Ingredient.of(ModItems.POISON_GLAND))
				.addItemIngredient(Ingredient.of(ModItems.POISON_GLAND))
				.addItemIngredient(Ingredient.of(ModItems.NETTLE_LEAVES))
				.addItemIngredient(Ingredient.of(ModItems.NETTLE_LEAVES))
				.unlockedBy("has_poison_gland", has(ModItems.POISON_GLAND))
				.save(output);

		new SmoothieRecipeBuilder(ModItems.ANTI_VENOM_BOTTLE)
				.addItemIngredient(Ingredient.of(Items.GLASS_BOTTLE))
				.addFluidIngredient(SizedFluidIngredient.of(ModFluids.BEETLE_JUICE_STILL.get(), 1000))
				.addItemIngredient(Ingredient.of(ModItems.POISON_GLAND))
				.addItemIngredient(Ingredient.of(ModItems.NETTLE_LEAVES))
				.unlockedBy("has_poison_gland", has(ModItems.POISON_GLAND))
				.save(output);
	}
}
