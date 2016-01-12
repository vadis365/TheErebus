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
		OfferingAltarRecipe.addRecipe(ItemMaterials.DATA.GAEAN_GEM.makeStack(), "gemDiamond", "gemEmerald", new ItemStack(Blocks.obsidian));
	}

	private static void smoothieMaker() {
		SmoothieMakerRecipe.addRecipe(SmoothieType.GREEN_TEA_GRASSHOPPER.makeStack(), ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), ModFluids.beetleJuice, new ItemStack(ModItems.food, 1, FoodType.GRASSHOPPER_LEG_RAW.ordinal()), new ItemStack(ModItems.food, 1, FoodType.GRASSHOPPER_LEG_RAW.ordinal()), ItemMaterials.DATA.ELASTIC_FIBRE.makeStack(), ItemMaterials.DATA.FLY_WING.makeStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.MONEY_HONEY.makeStack(), ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), ModFluids.honey, ItemMaterials.DATA.HONEY_DRIP.makeStack(), ItemMaterials.DATA.HONEY_DRIP.makeStack(), ItemMaterials.DATA.NECTAR.makeStack(), new ItemStack(Items.gold_nugget));
		SmoothieMakerRecipe.addRecipe(SmoothieType.NOTHING_IN_THE_MIDDLE.makeStack(), ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), ModFluids.beetleJuice, ItemMaterials.DATA.CAMO_POWDER.makeStack(), ItemMaterials.DATA.CAMO_POWDER.makeStack(), new ItemStack(ModItems.food, 1, FoodType.DARK_FRUIT.ordinal()), new ItemStack(ModItems.food, 1, FoodType.SWAMPBERRIES.ordinal()));
		SmoothieMakerRecipe.addRecipe(SmoothieType.GREEN_GIANT.makeStack(), ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), ModFluids.antiVenom, ItemMaterials.DATA.REPELLENT.makeStack(), ItemMaterials.DATA.POISON_GLAND.makeStack(), ItemMaterials.DATA.POISON_GLAND.makeStack(), ItemMaterials.DATA.WASP_STING.makeStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.SEEDY_GOODNESS.makeStack(), ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), ModFluids.beetleJuice, ItemMaterials.DATA.BIO_VELOCITY.makeStack(), ItemMaterials.DATA.DARK_FRUIT_SEEDS.makeStack(), new ItemStack(Items.melon_seeds), new ItemStack(Items.pumpkin_seeds));
		SmoothieMakerRecipe.addRecipe(SmoothieType.GIVIN_ME_THE_BLUES.makeStack(), ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), ModFluids.milk, ItemMaterials.DATA.WEEPING_BLUE_PETAL.makeStack(), ItemMaterials.DATA.WEEPING_BLUE_PETAL.makeStack(), "gemLapis", "gemLapis");
		SmoothieMakerRecipe.addRecipe(SmoothieType.HOT_HOT_BABY.makeStack(), ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), ModFluids.antiVenom, ItemMaterials.DATA.WASP_STING.makeStack(), ItemMaterials.DATA.SNAPPER_ROOT.makeStack(), new ItemStack(ModBlocks.fireBloom), new ItemStack(ModBlocks.fireBloom));
		SmoothieMakerRecipe.addRecipe(SmoothieType.DONT_MEDDLE_WITH_THE_NETTLE.makeStack(), ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), ModFluids.honey, ItemMaterials.DATA.NETTLE_FLOWERS.makeStack(), ItemMaterials.DATA.NETTLE_LEAVES.makeStack(), ItemMaterials.DATA.JADE_BERRIES.makeStack(), ItemMaterials.DATA.PLATE_EXO.makeStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.LIQUID_GOLD.makeStack(), ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), ModFluids.milk, new ItemStack(ModItems.lifeBlood), new ItemStack(ModItems.lifeBlood), ItemMaterials.DATA.BAMBOO_SHOOT.makeStack(), new ItemStack(Items.speckled_melon));
		SmoothieMakerRecipe.addRecipe(SmoothieType.BRYUFS_BREW.makeStack(), ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), new Fluid[] { ModFluids.honey, ModFluids.milk, ModFluids.antiVenom, ModFluids.beetleJuice }, ItemMaterials.DATA.COMPOUND_EYES.makeStack(), ItemMaterials.DATA.TERPSISHROOM.makeStack(), new ItemStack(ModItems.turnip), new ItemStack(ModItems.heartBerries));

		SmoothieMakerRecipe.addRecipe(FoodType.MELONADE.makeStack(), ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), FluidRegistry.WATER, new ItemStack(Items.melon));
		SmoothieMakerRecipe.addRecipe(FoodType.MELONADE_SPARKLY.makeStack(), ItemMaterials.DATA.SMOOTHIE_GLASS.makeStack(), FluidRegistry.WATER, new ItemStack(Items.speckled_melon));
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.bambucketAntiVenom), new ItemStack(ModItems.bambucket), ModFluids.beetleJuice, ItemMaterials.DATA.POISON_GLAND.makeStack(), ItemMaterials.DATA.NETTLE_LEAVES.makeStack(), ItemMaterials.DATA.NETTLE_LEAVES.makeStack());
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.bucketAntiVenom), new ItemStack(Items.bucket), ModFluids.beetleJuice, ItemMaterials.DATA.POISON_GLAND.makeStack(), ItemMaterials.DATA.NETTLE_LEAVES.makeStack(), ItemMaterials.DATA.NETTLE_LEAVES.makeStack());
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.bottleAntiVenom, 2), new ItemStack(Items.glass_bottle, 2), ModFluids.beetleJuice, ItemMaterials.DATA.POISON_GLAND.makeStack(), ItemMaterials.DATA.NETTLE_LEAVES.makeStack(), ItemMaterials.DATA.NETTLE_LEAVES.makeStack());
	}
}