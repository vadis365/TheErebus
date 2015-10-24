package erebus.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import erebus.ModBlocks;
import erebus.ModFluids;
import erebus.ModItems;
import erebus.item.ItemErebusFood.FoodType;
import erebus.item.ItemFoodSmoothie.SmoothieType;
import erebus.item.ItemMaterials;

public class ErebusRecipesHandler {
	public static void init() {
		offeringAltar();
		smoothieMaker();
	}

	private static void offeringAltar() {
		OfferingAltarRecipe.addRecipe(ItemMaterials.DATA.gaeanGem.createStack(), "gemDiamond", "gemEmerald", new ItemStack(Blocks.obsidian));
	}

	private static void smoothieMaker() {
		SmoothieMakerRecipe.addRecipe(SmoothieType.greenTeaGrasshopper.makeStack(), ModFluids.beetleJuice, new ItemStack(ModItems.food, 1, FoodType.grasshopperLegRaw.ordinal()), new ItemStack(ModItems.food, 1, FoodType.grasshopperLegRaw.ordinal()), ItemMaterials.DATA.elasticFibre.createStack(), ItemMaterials.DATA.flyWing.createStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.moneyHoney.makeStack(), ModFluids.honey, ItemMaterials.DATA.honeyDrip.createStack(), ItemMaterials.DATA.honeyDrip.createStack(), ItemMaterials.DATA.nectar.createStack(), new ItemStack(Items.gold_nugget));
		SmoothieMakerRecipe.addRecipe(SmoothieType.nothingInTheMiddle.makeStack(), ModFluids.beetleJuice, ItemMaterials.DATA.camoPowder.createStack(), ItemMaterials.DATA.camoPowder.createStack(), new ItemStack(ModItems.food, 1, FoodType.darkFruit.ordinal()), new ItemStack(ModItems.food, 1, FoodType.swampBerries.ordinal()));
		SmoothieMakerRecipe.addRecipe(SmoothieType.greenGiant.makeStack(), ModFluids.antiVenom, ItemMaterials.DATA.repellent.createStack(), ItemMaterials.DATA.poisonGland.createStack(), ItemMaterials.DATA.poisonGland.createStack(), ItemMaterials.DATA.waspSting.createStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.seedyGoodness.makeStack(), ModFluids.beetleJuice, ItemMaterials.DATA.bioVelocity.createStack(), ItemMaterials.DATA.darkFruitSeeds.createStack(), new ItemStack(Items.melon_seeds), new ItemStack(Items.pumpkin_seeds));
		SmoothieMakerRecipe.addRecipe(SmoothieType.givinMeTheBlues.makeStack(), ModFluids.milk, ItemMaterials.DATA.weepingBluePetal.createStack(), ItemMaterials.DATA.weepingBluePetal.createStack(), "gemLapis", "gemLapis");
		SmoothieMakerRecipe.addRecipe(SmoothieType.hotHotBaby.makeStack(), ModFluids.antiVenom, ItemMaterials.DATA.waspSting.createStack(), ItemMaterials.DATA.snapperRoot.createStack(), new ItemStack(ModBlocks.fireBloom), new ItemStack(ModBlocks.fireBloom));
		SmoothieMakerRecipe.addRecipe(SmoothieType.dontMettleWithTheNettle.makeStack(), ModFluids.honey, ItemMaterials.DATA.nettleflowers.createStack(), ItemMaterials.DATA.nettleleaves.createStack(), ItemMaterials.DATA.jadeBerries.createStack(), ItemMaterials.DATA.plateExo.createStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.liquidGold.makeStack(), ModFluids.milk, new ItemStack(ModItems.lifeBlood), new ItemStack(ModItems.lifeBlood), ItemMaterials.DATA.bambooShoot.createStack(), new ItemStack(Items.speckled_melon));
		SmoothieMakerRecipe.addRecipe(SmoothieType.bryufsBrew.makeStack(), new Fluid[] { ModFluids.honey, ModFluids.milk, ModFluids.antiVenom, ModFluids.beetleJuice }, ItemMaterials.DATA.compoundEyes.createStack(), new ItemStack(ModBlocks.bulbCapped), new ItemStack(ModItems.turnip), new ItemStack(ModItems.heartBerries));
	}
}