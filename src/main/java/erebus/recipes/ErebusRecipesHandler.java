package erebus.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import erebus.ModBlocks;
import erebus.ModFluids;
import erebus.ModItems;
import erebus.item.ItemFood.FoodType;
import erebus.item.Materials;
import erebus.item.ItemFoodSmoothie.SmoothieType;

public class ErebusRecipesHandler {
	public static void init() {
		craftingAltar();
		offeringAltar();
		smoothieMaker();
	}

	private static void offeringAltar() {
		OfferingAltarRecipe.addRecipe(Materials.DATA.gaeanGem.createStack(), "gemDiamond", "gemEmerald", new ItemStack(Blocks.obsidian));
	}

	private static void craftingAltar() {
		CraftingAltarRecipe.addRecipe(new ItemStack(ModItems.jadeHeart), "blockJade", Materials.DATA.crimsonHeart.createStack(), "ingotGold", "ingotGold", "ingotGold", "ingotGold", "ingotGold");
		CraftingAltarRecipe.addRecipe(new ItemStack(ModBlocks.lightningSpeedBlock), new ItemStack(ModBlocks.velocityBlock), getArray(Materials.DATA.supernaturalvelocity.createStack(), 8));
		CraftingAltarRecipe.addRecipe(new ItemStack(ModItems.witherWebSlinger), new ItemStack(ModItems.webSlinger), new ItemStack(Blocks.soul_sand), Materials.DATA.poisonGland.createStack(), new ItemStack(ModBlocks.witherWeb), new ItemStack(ModBlocks.witherWeb), new ItemStack(ModBlocks.witherWeb));
		CraftingAltarRecipe.addRecipe(new ItemStack(ModBlocks.umberGolemStatue), Materials.DATA.crimsonHeart.createStack(), Materials.DATA.umberGolemCore.createStack(), Materials.DATA.umberGolemClaw.createStack(), Materials.DATA.umberGolemClaw.createStack(), Materials.DATA.umberGolemHead.createStack(), Materials.DATA.umberGolemLegs.createStack());
	}

	private static void smoothieMaker() {
		SmoothieMakerRecipe.addRecipe(SmoothieType.greenTeaGrasshopper.makeStack(), ModFluids.beetleJuice, new ItemStack(ModItems.food, 1, FoodType.grasshopperLegRaw.ordinal()), new ItemStack(ModItems.food, 1, FoodType.grasshopperLegRaw.ordinal()), Materials.DATA.elasticFibre.createStack(), Materials.DATA.flyWing.createStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.moneyHoney.makeStack(), ModFluids.honey, Materials.DATA.honeyDrip.createStack(), Materials.DATA.honeyDrip.createStack(), Materials.DATA.nectar.createStack(), new ItemStack(Items.gold_nugget));
		SmoothieMakerRecipe.addRecipe(SmoothieType.nothingInTheMiddle.makeStack(), ModFluids.beetleJuice, Materials.DATA.camoPowder.createStack(), Materials.DATA.camoPowder.createStack(), new ItemStack(ModItems.food, 1, FoodType.darkFruit.ordinal()), new ItemStack(ModItems.food, 1, FoodType.swampBerries.ordinal()));
		SmoothieMakerRecipe.addRecipe(SmoothieType.greenGiant.makeStack(), ModFluids.antiVenom, Materials.DATA.repellent.createStack(), Materials.DATA.poisonGland.createStack(), Materials.DATA.poisonGland.createStack(), Materials.DATA.waspSting.createStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.seedyGoodness.makeStack(), ModFluids.beetleJuice, Materials.DATA.bioVelocity.createStack(), Materials.DATA.darkFruitSeeds.createStack(), new ItemStack(Items.melon_seeds), new ItemStack(Items.pumpkin_seeds));
		SmoothieMakerRecipe.addRecipe(SmoothieType.givinMeTheBlues.makeStack(), ModFluids.milk, Materials.DATA.weepingBluePetal.createStack(), Materials.DATA.weepingBluePetal.createStack(), "gemLapis", "gemLapis");
		SmoothieMakerRecipe.addRecipe(SmoothieType.hotHotBaby.makeStack(), ModFluids.antiVenom, Materials.DATA.waspSting.createStack(), Materials.DATA.snapperRoot.createStack(), new ItemStack(ModBlocks.fireBloom), new ItemStack(ModBlocks.fireBloom));
		SmoothieMakerRecipe.addRecipe(SmoothieType.dontMettleWithTheNettle.makeStack(), ModFluids.honey, Materials.DATA.nettleflowers.createStack(), Materials.DATA.nettleleaves.createStack(), Materials.DATA.jadeBerries.createStack(), Materials.DATA.plateExo.createStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.liquidGold.makeStack(), ModFluids.milk, new ItemStack(ModItems.lifeBlood), new ItemStack(ModItems.lifeBlood), Materials.DATA.bambooShoot.createStack(), new ItemStack(Items.speckled_melon));
		SmoothieMakerRecipe.addRecipe(SmoothieType.bryufsBrew.makeStack(), new Fluid[] { ModFluids.honey, ModFluids.milk, ModFluids.antiVenom, ModFluids.beetleJuice }, Materials.DATA.compoundEyes.createStack(), new ItemStack(ModBlocks.bulbCapped), new ItemStack(ModItems.turnip), new ItemStack(ModItems.heartBerries));
	}

	private static Object[] getArray(Object base, int size) {
		Object[] array = new Object[size];
		for (int i = 0; i < array.length; i++)
			array[i] = base;
		return array;
	}
}