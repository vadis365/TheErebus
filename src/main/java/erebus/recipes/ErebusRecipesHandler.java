package erebus.recipes;

import erebus.ModBlocks;
import erebus.ModFluids;
import erebus.ModItems;
import erebus.blocks.BlockSmallPlant;
import erebus.blocks.BlockSmallPlant.EnumSmallPlantType;
import erebus.blocks.EnumWood;
import erebus.items.ItemErebusFood.EnumFoodType;
import erebus.items.ItemMaterials;
import erebus.items.ItemSmoothie.SmoothieType;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

public class ErebusRecipesHandler {
	public static void init() {
		offeringAltar();
		smoothieMaker();
	}

	private static void offeringAltar() {
		OfferingAltarRecipe.addRecipe(ItemMaterials.EnumErebusMaterialsType.GAEAN_GEM.createStack(), "gemDiamond", "gemEmerald", new ItemStack(Blocks.OBSIDIAN));
	}

	private static void smoothieMaker() {
		SmoothieMakerRecipe.addRecipe(SmoothieType.GREEN_TEA_GRASSHOPPER.createStack(), ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), ModFluids.BEETLE_JUICE, new ItemStack(ModItems.EREBUS_FOOD, 1, EnumFoodType.GRASSHOPPER_LEG_RAW.ordinal()), new ItemStack(ModItems.EREBUS_FOOD, 1, EnumFoodType.GRASSHOPPER_LEG_RAW.ordinal()), ItemMaterials.EnumErebusMaterialsType.ELASTIC_FIBRE.createStack(), ItemMaterials.EnumErebusMaterialsType.FLY_WING.createStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.MONEY_HONEY.createStack(), ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), ModFluids.HONEY, ItemMaterials.EnumErebusMaterialsType.HONEY_DRIP.createStack(), ItemMaterials.EnumErebusMaterialsType.HONEY_DRIP.createStack(), ItemMaterials.EnumErebusMaterialsType.NECTAR.createStack(), new ItemStack(Items.GOLD_NUGGET));
		SmoothieMakerRecipe.addRecipe(SmoothieType.NOTHING_IN_THE_MIDDLE.createStack(), ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), ModFluids.BEETLE_JUICE, ItemMaterials.EnumErebusMaterialsType.CAMO_POWDER.createStack(), ItemMaterials.EnumErebusMaterialsType.CAMO_POWDER.createStack(), new ItemStack(ModItems.EREBUS_FOOD, 1, EnumFoodType.DARK_FRUIT.ordinal()), new ItemStack(ModItems.EREBUS_FOOD, 1, EnumFoodType.SWAMP_BERRIES.ordinal()));
		SmoothieMakerRecipe.addRecipe(SmoothieType.GREEN_GIANT.createStack(), ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), ModFluids.ANTI_VENOM, ItemMaterials.EnumErebusMaterialsType.REPELLENT.createStack(), ItemMaterials.EnumErebusMaterialsType.POISON_GLAND.createStack(), ItemMaterials.EnumErebusMaterialsType.POISON_GLAND.createStack(), ItemMaterials.EnumErebusMaterialsType.WASP_STING.createStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.SEEDY_GOODNESS.createStack(), ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), ModFluids.BEETLE_JUICE, ItemMaterials.EnumErebusMaterialsType.BIO_VELOCITY.createStack(), ItemMaterials.EnumErebusMaterialsType.DARK_FRUIT_SEEDS.createStack(), new ItemStack(Items.MELON_SEEDS), new ItemStack(Items.PUMPKIN_SEEDS));
		SmoothieMakerRecipe.addRecipe(SmoothieType.GIVIN_ME_THE_BLUES.createStack(), ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), ModFluids.MILK, ItemMaterials.EnumErebusMaterialsType.BLUEBELL_PETAL.createStack(), ItemMaterials.EnumErebusMaterialsType.BLUEBELL_PETAL.createStack(), "gemLapis", "gemLapis");
		SmoothieMakerRecipe.addRecipe(SmoothieType.HOT_HOT_BABY.createStack(), ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), ModFluids.ANTI_VENOM, ItemMaterials.EnumErebusMaterialsType.WASP_STING.createStack(), ItemMaterials.EnumErebusMaterialsType.BOGMAW_ROOT.createStack(), new ItemStack(ModBlocks.SMALL_PLANT.getDefaultState().withProperty(BlockSmallPlant.PLANT_TYPE, EnumSmallPlantType.FIRE_BLOOM).getBlock()), new ItemStack(ModBlocks.SMALL_PLANT.getDefaultState().withProperty(BlockSmallPlant.PLANT_TYPE, EnumSmallPlantType.FIRE_BLOOM).getBlock()));
		SmoothieMakerRecipe.addRecipe(SmoothieType.DONT_MEDDLE_WITH_THE_NETTLE.createStack(), ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), ModFluids.HONEY, ItemMaterials.EnumErebusMaterialsType.NETTLE_FLOWERS.createStack(), ItemMaterials.EnumErebusMaterialsType.NETTLE_LEAVES.createStack(), ItemMaterials.EnumErebusMaterialsType.JADE_BERRIES.createStack(), ItemMaterials.EnumErebusMaterialsType.PLATE_EXO.createStack());
		SmoothieMakerRecipe.addRecipe(SmoothieType.LIQUID_GOLD.createStack(), ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), ModFluids.MILK, new ItemStack(ModItems.LIFE_BLOOD), new ItemStack(ModItems.LIFE_BLOOD), new ItemStack(EnumWood.BAMBOO.getSapling()), new ItemStack(Items.SPECKLED_MELON));
		SmoothieMakerRecipe.addRecipe(SmoothieType.BRYUFS_BREW.createStack(), ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), new Fluid[] { ModFluids.HONEY, ModFluids.MILK, ModFluids.ANTI_VENOM, ModFluids.BEETLE_JUICE }, ItemMaterials.EnumErebusMaterialsType.COMPOUND_EYES.createStack(), ItemMaterials.EnumErebusMaterialsType.TERPSISHROOM.createStack(), new ItemStack(ModItems.TURNIP), new ItemStack(ModItems.HEART_BERRIES));

		SmoothieMakerRecipe.addRecipe(EnumFoodType.MELONADE.createStack(), ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), FluidRegistry.WATER, new ItemStack(Items.MELON));
		SmoothieMakerRecipe.addRecipe(EnumFoodType.MELONADE_SPARKLY.createStack(), ItemMaterials.EnumErebusMaterialsType.SMOOTHIE_GLASS.createStack(), FluidRegistry.WATER, new ItemStack(Items.SPECKLED_MELON));
		SmoothieMakerRecipe.addRecipe(ModFluids.getFilledBambucket(new FluidStack(FluidRegistry.getFluid("anti_venom"), Fluid.BUCKET_VOLUME)), new ItemStack(ModItems.BAMBUCKET), ModFluids.BEETLE_JUICE, ItemMaterials.EnumErebusMaterialsType.POISON_GLAND.createStack(), ItemMaterials.EnumErebusMaterialsType.NETTLE_LEAVES.createStack(), ItemMaterials.EnumErebusMaterialsType.NETTLE_LEAVES.createStack());
		SmoothieMakerRecipe.addRecipe(FluidUtil.getFilledBucket(new FluidStack(FluidRegistry.getFluid("anti_venom"), Fluid.BUCKET_VOLUME)), new ItemStack(Items.BUCKET), ModFluids.BEETLE_JUICE, ItemMaterials.EnumErebusMaterialsType.POISON_GLAND.createStack(), ItemMaterials.EnumErebusMaterialsType.NETTLE_LEAVES.createStack(), ItemMaterials.EnumErebusMaterialsType.NETTLE_LEAVES.createStack());
		SmoothieMakerRecipe.addRecipe(new ItemStack(ModItems.ANTI_VENOM_BOTTLE, 2), new ItemStack(Items.GLASS_BOTTLE, 2), ModFluids.BEETLE_JUICE, ItemMaterials.EnumErebusMaterialsType.POISON_GLAND.createStack(), ItemMaterials.EnumErebusMaterialsType.NETTLE_LEAVES.createStack(), ItemMaterials.EnumErebusMaterialsType.NETTLE_LEAVES.createStack());
	}
}