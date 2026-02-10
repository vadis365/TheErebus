package erebus.datagen.providers.recipes;

import erebus.recipes.altar.OfferingAltarRecipeBuilder;
import erebus.recipes.smoothie.SmoothieRecipeBuilder;
import erebus.registries.ModFluids;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.item.ModItems;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.NeoForgeMod;

public class CustomRecipeProvider extends ErebusRecipeProvider {

	private final HolderGetter<Item> items;

	public CustomRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
		super(registries, output);
		items = registries.lookupOrThrow(Registries.ITEM);
	}

	public void buildRecipes() {
		OfferingAltarRecipeBuilder.altarRecipe(items, ModItems.GAEAN_GEM)
				.requires(Items.DIAMOND)
				.requires(Items.EMERALD)
				.requires(Blocks.OBSIDIAN)
				.unlockedBy("has_altar", has(ModBlocks.OFFERING_ALTAR))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.GREEN_TEA_GRASSHOPPER)
				.requires(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.requires(Ingredient.of(ModItems.GRASSHOPPER_LEG_RAW))
				.requires(Ingredient.of(ModItems.GRASSHOPPER_LEG_RAW))
				.requires(Ingredient.of(ModItems.ELASTIC_FIBER))
				.requires(Ingredient.of(ModItems.FLY_WING))
				.requires(Ingredient.of(ModItems.NECTAR))
				.requires(ModFluids.BEETLE_JUICE_STILL.get())
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.MONEY_HONEY)
				.requires(ModFluids.HONEY_STILL.get())
				.requires(Ingredient.of(ModItems.HONEY_DRIP))
				.requires(Ingredient.of(ModItems.HONEY_DRIP))
				.requires(Ingredient.of(ModItems.NECTAR))
				.requires(Ingredient.of(Items.GOLD_NUGGET))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.NOTHING_IN_THE_MIDDLE)
				.requires(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.requires(ModFluids.BEETLE_JUICE_STILL.get())
				.requires(Ingredient.of(ModItems.CAMO_POWDER))
				.requires(Ingredient.of(ModItems.CAMO_POWDER))
				.requires(Ingredient.of(ModItems.DARK_FRUIT))
				.requires(Ingredient.of(ModItems.SWAMP_BERRIES))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.GREEN_GIANT)
				.requires(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.requires(ModFluids.ANTI_VENOM_STILL.get())
				.requires(Ingredient.of(ModItems.REPELLENT))
				.requires(Ingredient.of(ModItems.POISON_GLAND))
				.requires(Ingredient.of(ModItems.POISON_GLAND))
				.requires(Ingredient.of(ModItems.WASP_STING))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.SEEDY_GOODNESS)
				.requires(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.requires(ModFluids.BEETLE_JUICE_STILL.get())
				.requires(Ingredient.of(ModItems.BIO_VELOCITY))
				.requires(Ingredient.of(ModItems.DARK_FRUIT_SEEDS))
				.requires(Ingredient.of(Items.MELON_SEEDS))
				.requires(Ingredient.of(Items.PUMPKIN_SEEDS))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.GIVIN_ME_THE_BLUES)
				.requires(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.requires(NeoForgeMod.MILK.get())
				.requires(Ingredient.of(ModItems.BLUEBELL_PETAL))
				.requires(Ingredient.of(ModItems.BLUEBELL_PETAL))
				.requires(Ingredient.of(Items.LAPIS_LAZULI))
				.requires(Ingredient.of(Items.LAPIS_LAZULI))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.HOT_HOT_BABY)
				.requires(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.requires(ModFluids.ANTI_VENOM_STILL.get())
				.requires(Ingredient.of(ModItems.WASP_STING))
				.requires(Ingredient.of(ModItems.BOGMAW_ROOT))
				.requires(Ingredient.of(ModBlocks.FIRE_BLOOM))
				.requires(Ingredient.of(ModBlocks.FIRE_BLOOM))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.DONT_MEDDLE_WITH_THE_NETTLE)
				.requires(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.requires(ModFluids.HONEY_STILL.get())
				.requires(Ingredient.of(ModItems.NETTLE_FLOWERS))
				.requires(Ingredient.of(ModItems.NETTLE_FLOWERS))
				.requires(Ingredient.of(ModItems.JADE_BERRIES))
				.requires(Ingredient.of(ModItems.PLATE_EXO))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.LIQUID_GOLD)
				.requires(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.requires(NeoForgeMod.MILK.get())
				.requires(Ingredient.of(ModItems.LIFE_BLOOD))
				.requires(Ingredient.of(ModItems.LIFE_BLOOD))
				.requires(Ingredient.of(ModItems.BAMBOO))
				.requires(Ingredient.of(Items.GLISTERING_MELON_SLICE))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.BRYUFS_BREW)
				.requires(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.requires(ModFluids.BEETLE_JUICE_STILL.get())
				.requires(ModFluids.HONEY_STILL.get())
				.requires(ModFluids.ANTI_VENOM_STILL.get())
				.requires(NeoForgeMod.MILK.get())
				.requires(Ingredient.of(ModItems.COMPOUND_EYES))
				.requires(Ingredient.of(ModItems.TERPSISHROOM))
				.requires(Ingredient.of(ModItems.TURNIP))
				.requires(Ingredient.of(ModItems.HEART_BERRIES))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.MELONADE)
				.requires(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.requires(Fluids.WATER)
				.requires(Ingredient.of(Items.MELON_SLICE))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.MELONADE_SPARKLY)
				.requires(Ingredient.of(ModItems.SMOOTHIE_GLASS))
				.requires(Fluids.WATER)
				.requires(Ingredient.of(Items.GLISTERING_MELON_SLICE))
				.unlockedBy("has_smoothie_glass", has(ModItems.SMOOTHIE_GLASS))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.ANTI_VENOM_BUCKET)
				.requires(Ingredient.of(Items.BUCKET))
				.requires(ModFluids.BEETLE_JUICE_STILL.get())
				.requires(Ingredient.of(ModItems.POISON_GLAND))
				.requires(Ingredient.of(ModItems.POISON_GLAND))
				.requires(Ingredient.of(ModItems.NETTLE_LEAVES))
				.requires(Ingredient.of(ModItems.NETTLE_LEAVES))
				.unlockedBy("has_poison_gland", has(ModItems.POISON_GLAND))
				.save(output);

		SmoothieRecipeBuilder.smoothieRecipe(items, ModItems.ANTI_VENOM_BOTTLE)
				.requires(Ingredient.of(Items.GLASS_BOTTLE))
				.requires(ModFluids.BEETLE_JUICE_STILL.get())
				.requires(Ingredient.of(ModItems.POISON_GLAND))
				.requires(Ingredient.of(ModItems.NETTLE_LEAVES))
				.unlockedBy("has_poison_gland", has(ModItems.POISON_GLAND))
				.save(output);
	}
}
