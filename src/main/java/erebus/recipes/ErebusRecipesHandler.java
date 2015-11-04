package erebus.recipes;

import erebus.ModBlocks;
import erebus.ModFluids;
import erebus.ModItems;
import erebus.item.ItemErebusFood.FoodType;
import erebus.item.ItemMaterials;
import erebus.item.ItemSmoothie.SmoothieType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ErebusRecipesHandler {
	public static void init() {
		offeringAltar();
		smoothieMaker();
	}

	private static void offeringAltar() {
		OfferingAltarRecipe.addRecipe(ItemMaterials.DATA.gaeanGem.makeStack(), "gemDiamond", "gemEmerald", new ItemStack(Blocks.obsidian));
	}

	private static void smoothieMaker() {
		SmoothieMakerRecipe.addRecipe(SmoothieType.GREEN_TEA_GRASSHOPPER.makeStack(), ItemMaterials.DATA.smoothieGlass.makeStack(), ModFluids.beetleJuice, new ItemStack(ModItems.food, 1, FoodType.GRASSHOPPER_LEG_RAW.ordinal()), new ItemStack(ModItems.food, 1, FoodType.GRASSHOPPER_LEG_RAW.ordinal()), ItemMaterials.DATA.elasticFibre.makeStack(), ItemMaterials.DATA.flyWing.makeStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.MONEY_HONEY.makeStack(), ItemMaterials.DATA.smoothieGlass.makeStack(), ModFluids.honey, ItemMaterials.DATA.honeyDrip.makeStack(), ItemMaterials.DATA.honeyDrip.makeStack(), ItemMaterials.DATA.nectar.makeStack(), new ItemStack(Items.gold_nugget));
		SmoothieMakerRecipe.addRecipe(SmoothieType.NOTHING_IN_THE_MIDDLE.makeStack(), ItemMaterials.DATA.smoothieGlass.makeStack(), ModFluids.beetleJuice, ItemMaterials.DATA.camoPowder.makeStack(), ItemMaterials.DATA.camoPowder.makeStack(), new ItemStack(ModItems.food, 1, FoodType.DARK_FRUIT.ordinal()), new ItemStack(ModItems.food, 1, FoodType.SWAMPBERRIES.ordinal()));
		SmoothieMakerRecipe.addRecipe(SmoothieType.GREEN_GIANT.makeStack(), ItemMaterials.DATA.smoothieGlass.makeStack(), ModFluids.antiVenom, ItemMaterials.DATA.repellent.makeStack(), ItemMaterials.DATA.poisonGland.makeStack(), ItemMaterials.DATA.poisonGland.makeStack(), ItemMaterials.DATA.waspSting.makeStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.SEEDY_GOODNESS.makeStack(), ItemMaterials.DATA.smoothieGlass.makeStack(), ModFluids.beetleJuice, ItemMaterials.DATA.bioVelocity.makeStack(), ItemMaterials.DATA.darkFruitSeeds.makeStack(), new ItemStack(Items.melon_seeds), new ItemStack(Items.pumpkin_seeds));
		SmoothieMakerRecipe.addRecipe(SmoothieType.GIVIN_ME_THE_BLUES.makeStack(), ItemMaterials.DATA.smoothieGlass.makeStack(), ModFluids.milk, ItemMaterials.DATA.weepingBluePetal.makeStack(), ItemMaterials.DATA.weepingBluePetal.makeStack(), "gemLapis", "gemLapis");
		SmoothieMakerRecipe.addRecipe(SmoothieType.HOT_HOT_BABY.makeStack(), ItemMaterials.DATA.smoothieGlass.makeStack(), ModFluids.antiVenom, ItemMaterials.DATA.waspSting.makeStack(), ItemMaterials.DATA.snapperRoot.makeStack(), new ItemStack(ModBlocks.fireBloom), new ItemStack(ModBlocks.fireBloom));
		SmoothieMakerRecipe.addRecipe(SmoothieType.DONT_MEDDLE_WITH_THE_NETTLE.makeStack(), ItemMaterials.DATA.smoothieGlass.makeStack(), ModFluids.honey, ItemMaterials.DATA.nettleflowers.makeStack(), ItemMaterials.DATA.nettleleaves.makeStack(), ItemMaterials.DATA.jadeBerries.makeStack(), ItemMaterials.DATA.plateExo.makeStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.LIQUID_GOLD.makeStack(), ItemMaterials.DATA.smoothieGlass.makeStack(), ModFluids.milk, new ItemStack(ModItems.lifeBlood), new ItemStack(ModItems.lifeBlood), ItemMaterials.DATA.bambooShoot.makeStack(), new ItemStack(Items.speckled_melon));
		SmoothieMakerRecipe.addRecipe(SmoothieType.BRYUFS_BREW.makeStack(), ItemMaterials.DATA.smoothieGlass.makeStack(), new Fluid[] { ModFluids.honey, ModFluids.milk, ModFluids.antiVenom, ModFluids.beetleJuice }, ItemMaterials.DATA.compoundEyes.makeStack(), new ItemStack(ModBlocks.bulbCapped), new ItemStack(ModItems.turnip), new ItemStack(ModItems.heartBerries));

		SmoothieMakerRecipe.addRecipe(FoodType.MELONADE.makeStack(), ItemMaterials.DATA.smoothieGlass.makeStack(), FluidRegistry.WATER, new ItemStack(Items.melon));
		SmoothieMakerRecipe.addRecipe(FoodType.MELONADE_SPARKLY.makeStack(), ItemMaterials.DATA.smoothieGlass.makeStack(), FluidRegistry.WATER, new ItemStack(Items.speckled_melon));
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.bambucketAntiVenom), new ItemStack(ModItems.bambucket), ModFluids.beetleJuice, ItemMaterials.DATA.poisonGland.makeStack(), ItemMaterials.DATA.nettleleaves.makeStack(), ItemMaterials.DATA.nettleleaves.makeStack());
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.bucketAntiVenom), new ItemStack(Items.bucket), ModFluids.beetleJuice, ItemMaterials.DATA.poisonGland.makeStack(), ItemMaterials.DATA.nettleleaves.makeStack(), ItemMaterials.DATA.nettleleaves.makeStack());
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.bottleAntiVenom, 2), new ItemStack(Items.glass_bottle, 2), ModFluids.beetleJuice, ItemMaterials.DATA.poisonGland.makeStack(), ItemMaterials.DATA.nettleleaves.makeStack(), ItemMaterials.DATA.nettleleaves.makeStack());
	}
}