package erebus.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.item.Food.FoodType;
import erebus.item.Materials;
import erebus.item.Smoothie.SmoothieType;

public class ErebusRecipesHandler {
	public static void init() {
		craftingAltar();
		offeringAltar();
		smoothieMaker();
	}

	private static void offeringAltar() {
		OfferingAltarRecipe.addRecipe(Materials.createStack(Materials.DATA.gaeanGem), "gemDiamond", "gemEmerald", new ItemStack(Blocks.obsidian));
	}

	private static void craftingAltar() {
		CraftingAltarRecipe.addRecipe(new ItemStack(ModItems.jadeHeart), "blockJade", Materials.createStack(Materials.DATA.crimsonHeart), "ingotGold", "ingotGold", "ingotGold", "ingotGold", "ingotGold");
		CraftingAltarRecipe.addRecipe(new ItemStack(ModBlocks.lightningSpeedBlock), new ItemStack(ModBlocks.velocityBlock), getArray(Materials.createStack(Materials.DATA.supernaturalvelocity), 8));
		CraftingAltarRecipe.addRecipe(new ItemStack(ModItems.witherWebSlinger), new ItemStack(ModItems.webSlinger), new ItemStack(Blocks.soul_sand), Materials.createStack(Materials.DATA.poisonGland), new ItemStack(ModBlocks.witherWeb), new ItemStack(ModBlocks.witherWeb), new ItemStack(ModBlocks.witherWeb));
		CraftingAltarRecipe.addRecipe(new ItemStack(ModBlocks.umberGolemStatue), Materials.createStack(Materials.DATA.crimsonHeart), Materials.createStack(Materials.DATA.umberGolemCore), Materials.createStack(Materials.DATA.umberGolemClaw), Materials.createStack(Materials.DATA.umberGolemClaw), Materials.createStack(Materials.DATA.umberGolemHead), Materials.createStack(Materials.DATA.umberGolemLegs));
	}

	private static void smoothieMaker() {
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.smoothie, 1, SmoothieType.greenTeaGrasshopper.ordinal()), new ItemStack(ModItems.food, 1, FoodType.grasshopperLegRaw.ordinal()), new ItemStack(ModItems.food, 1, FoodType.grasshopperLegRaw.ordinal()), Materials.createStack(Materials.DATA.elasticFibre), Materials.createStack(Materials.DATA.flyWing));
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.smoothie, 1, SmoothieType.moneyHoney.ordinal()), Materials.createStack(Materials.DATA.honeyDrip), Materials.createStack(Materials.DATA.honeyDrip), Materials.createStack(Materials.DATA.nectar), new ItemStack(Items.gold_nugget));
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.smoothie, 1, SmoothieType.nothingInTheMiddle.ordinal()), Materials.createStack(Materials.DATA.camoPowder), Materials.createStack(Materials.DATA.camoPowder), new ItemStack(ModItems.food, 1, FoodType.darkFruit.ordinal()), new ItemStack(ModItems.food, 1, FoodType.swampBerries.ordinal()));
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.smoothie, 1, SmoothieType.greenGiant.ordinal()), Materials.createStack(Materials.DATA.repellent), Materials.createStack(Materials.DATA.poisonGland), Materials.createStack(Materials.DATA.poisonGland), Materials.createStack(Materials.DATA.waspSting));
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.smoothie, 1, SmoothieType.seedyGoodness.ordinal()), Materials.createStack(Materials.DATA.bioVelocity), Materials.createStack(Materials.DATA.darkFruitSeeds), new ItemStack(Items.melon_seeds), new ItemStack(Items.pumpkin_seeds));
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.smoothie, 1, SmoothieType.givinMeTheBlues.ordinal()), Materials.createStack(Materials.DATA.weepingBluePetal), Materials.createStack(Materials.DATA.weepingBluePetal), new ItemStack(Items.dye, 1, 4), new ItemStack(Items.dye, 1, 4));
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.smoothie, 1, SmoothieType.hotHotBaby.ordinal()), Materials.createStack(Materials.DATA.waspSting), Materials.createStack(Materials.DATA.snapperRoot), new ItemStack(ModBlocks.fireBloom), new ItemStack(ModBlocks.fireBloom));
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.smoothie, 1, SmoothieType.dontMettleWithTheNettle.ordinal()), Materials.createStack(Materials.DATA.nettleflowers), Materials.createStack(Materials.DATA.nettleleaves), Materials.createStack(Materials.DATA.jadeBerries), Materials.createStack(Materials.DATA.plateExo));
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.smoothie, 1, SmoothieType.liquidGold.ordinal()), new ItemStack(ModItems.lifeBlood), new ItemStack(ModItems.lifeBlood), Materials.createStack(Materials.DATA.bambooShoot), new ItemStack(Items.speckled_melon));
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.smoothie, 1, SmoothieType.bryufsBrew.ordinal()), Materials.createStack(Materials.DATA.compoundEyes), new ItemStack(ModBlocks.bulbCapped), new ItemStack(ModItems.turnip), new ItemStack(ModItems.heartBerries));	
	}

	private static Object[] getArray(Object base, int size) {
		Object[] array = new Object[size];
		for (int i = 0; i < array.length; i++)
			array[i] = base;
		return array;
	}
}