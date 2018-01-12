package erebus.world.feature.structure;

import java.util.List;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModFluids;
import erebus.ModItems;
import erebus.blocks.BlockGiantFlower;
import erebus.blocks.BlockGiantFlower.EnumType;
import erebus.items.ItemErebusFood.EnumFoodType;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.items.ItemSmoothie.SmoothieType;
import erebus.world.loot.IPostProcess;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class WorldGenDragonflyDungeon extends WorldGenerator {
	private IBlockState AIR = Blocks.AIR.getDefaultState();
	private IBlockState GIANT_LILY_PAD = ModBlocks.GIANT_LILY_PAD.getDefaultState();
	private IBlockState GIANT_FLOWER = ModBlocks.GIANT_FLOWER.getDefaultState().withProperty(BlockGiantFlower.TYPE, EnumType.PETAL_WHITE);
	private IBlockState DRAGON_FLY_SPAWNER = ModBlocks.DRAGON_FLY_SPAWNER.getDefaultState();

	public static final WeightedLootList CHEST_LOOT = new WeightedLootList(new LootItemStack[] {
			new LootItemStack(Items.BOOK).setAmount(1, 4).setWeight(18),
			new LootItemStack(Items.PAPER).setAmount(2, 6).setWeight(16),
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 2).setDamage(EnumErebusMaterialsType.WATER_REPELLENT.ordinal()).setWeight(3),
			new LootItemStack(ModItems.MATERIALS).setAmount(4, 8).setDamage(EnumErebusMaterialsType.PLATE_EXO.ordinal()).setWeight(9),
			new LootItemStack(Items.ENCHANTED_BOOK).setWeight(8), new LootItemStack(Items.GOLDEN_PICKAXE).setWeight(3),
			new LootItemStack(Items.IRON_PICKAXE).setWeight(2), new LootItemStack(ModItems.JADE_PICKAXE).setWeight(1),
			new LootItemStack(Items.GOLDEN_SHOVEL).setWeight(3), new LootItemStack(Items.IRON_SHOVEL).setWeight(2),
			new LootItemStack(ModItems.JADE_SHOVEL).setWeight(1), new LootItemStack(Items.GOLDEN_AXE).setWeight(3),
			new LootItemStack(Items.IRON_AXE).setWeight(2), new LootItemStack(ModItems.JADE_AXE).setWeight(1),
			new LootItemStack(Items.GOLDEN_SWORD).setWeight(3), new LootItemStack(Items.IRON_SWORD).setWeight(2),
			new LootItemStack(ModItems.JADE_SWORD).setWeight(1), new LootItemStack(Items.IRON_CHESTPLATE).setWeight(2),
			new LootItemStack(ModItems.JADE_CHESTPLATE).setWeight(1), new LootItemStack(Items.GOLDEN_CHESTPLATE).setWeight(1),
			new LootItemStack(Items.IRON_HELMET).setWeight(2), new LootItemStack(ModItems.JADE_HELMET).setWeight(1),
			new LootItemStack(Items.GOLDEN_HELMET).setWeight(1), new LootItemStack(Items.IRON_LEGGINGS).setWeight(2),
			new LootItemStack(ModItems.JADE_LEGGINGS).setWeight(1), new LootItemStack(Items.GOLDEN_LEGGINGS).setWeight(1),
			new LootItemStack(Items.IRON_BOOTS).setWeight(2), new LootItemStack(ModItems.JADE_BOOTS).setWeight(1),
			new LootItemStack(Items.GOLDEN_BOOTS).setWeight(1),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.ALTAR_FRAGMENT.ordinal()).setWeight(1),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.REINFORCED_PLATE_EXO.ordinal()).setWeight(1),
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(EnumErebusMaterialsType.HYDROFUGE.ordinal()).setWeight(3),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.PLATE_EXO_RHINO.ordinal()).setWeight(1),
			new LootItemStack(ModItems.EREBUS_FOOD).setAmount(1, 3).setDamage(EnumFoodType.HONEY_SANDWICH.ordinal()).setWeight(3),
			new LootItemStack(ModItems.CABBAGE_SEEDS).setAmount(1, 3).setWeight(2),
			new LootItemStack(ModItems.LIFE_BLOOD).setAmount(1, 2).setWeight(4),
			new LootItemStack(ModItems.ROLLED_NEWSPAPER).setAmount(1).setWeight(1),
			new LootItemStack(ModItems.BAMBUCKET).setAmount(1).setWeight(6),
			new LootItemStack(ModItems.WHETSTONE).setAmount(1).setDamage(0).setWeight(1),
			new LootItemStack(ModBlocks.GLOW_GEM_ACTIVE).setAmount(1, 3).setWeight(5),
			new LootItemStack(ModItems.SMOOTHIE).setAmount(1, 3).setDamage(SmoothieType.NOTHING_IN_THE_MIDDLE.ordinal()).setWeight(3),
			new LootItemStack(ModItems.SMOOTHIE).setAmount(1).setDamage(SmoothieType.BRYUFS_BREW.ordinal()).setWeight(1)
			//new LootItemStack(ModItems.waspDagger).setAmount(1, 3).setWeight(2),

			}).setPostProcessor(new IPostProcess() {
				@SuppressWarnings("rawtypes")
				@Override
				public ItemStack postProcessItem(ItemStack is, Random rand) {
					if (is.getItem() == Items.ENCHANTED_BOOK || rand.nextBoolean() && (is.getItem() instanceof ItemTool || is.getItem() instanceof ItemArmor || is.getItem() instanceof ItemSword)) {
						boolean enchBook = is.getItem() == Items.ENCHANTED_BOOK;
						if (enchBook)
							is = new ItemStack(Items.BOOK);
						List enchList = EnchantmentHelper.buildEnchantmentList(rand, is, 7 + rand.nextInt(10), true);
						if (enchBook)
							is = new ItemStack(Items.ENCHANTED_BOOK);

						if (enchList != null && enchList.size() > 0)
							for (int a = 0; a < enchList.size(); ++a) {
								EnchantmentData data = (EnchantmentData) enchList.get(a);
								is.addEnchantment(data.enchantment, data.enchantmentLevel);
							}
					}
					if (is.getItem() == ModItems.BAMBUCKET) {
						int randomBucket = rand.nextInt(3);
						switch (randomBucket) {
						case 0:
							is = ModFluids.getFilledBambucket(new FluidStack(FluidRegistry.getFluid("beetle_juice"), Fluid.BUCKET_VOLUME));
							break;
						case 1:
							is = ModFluids.getFilledBambucket(new FluidStack(FluidRegistry.getFluid("anti_venom"), Fluid.BUCKET_VOLUME));
							break;
						case 2:
							is = ModFluids.getFilledBambucket(new FluidStack(FluidRegistry.getFluid("honey"), Fluid.BUCKET_VOLUME));
							break;
						}
					}
					return is;
				}
			});

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		// space check
			for (int xx = - 6; xx <= 6; xx++)
				for (int zz = - 6; zz <=  6; zz++)
					for (int yy = 1 ; yy < 5; yy++) {
						if (!world.isAirBlock(pos.add(xx, yy, zz)) || world.getBlockState(pos).getMaterial() != Material.WATER)
							return false;
					}

		// main lily pad
		for (int xx = - 6; xx <= 6; xx++)
			for (int zz = - 6; zz <= 6; zz++) {
				double dSqCylinder = Math.pow(xx , 2.0D) + Math.pow(zz, 2.0D);
				if (Math.round(Math.sqrt(dSqCylinder)) < 6)
					if (dSqCylinder <= Math.pow(6, 2.0D))
						world.setBlockState(pos.add(xx, 0, zz), GIANT_LILY_PAD, 2);
				if (Math.round(Math.sqrt(dSqCylinder)) == 5)
					if (dSqCylinder <= Math.pow(6, 2.0D))
						world.setBlockState(pos.add(xx, 1, zz), GIANT_LILY_PAD, 2);
			}

		// air gap in lily pad
		int direction = rand.nextInt(4);

		if (direction == 0)
			for (int airX = 5; airX > 1; airX--) {
				world.setBlockState(pos.add(airX, 0, 0), AIR, 2);
				world.setBlockState(pos.add(airX, 1, 0), AIR, 2);
				world.setBlockState(pos.add(airX, 1, - 1), GIANT_LILY_PAD, 2);
				world.setBlockState(pos.add(airX, 1, 1), GIANT_LILY_PAD, 2);
			}

		if (direction == 1)
			for (int airZ = 5; airZ > 1; airZ--) {
				world.setBlockState(pos.add(0, 0, airZ), AIR, 2);
				world.setBlockState(pos.add(0, 1, airZ), AIR, 2);
				world.setBlockState(pos.add(1, 1, airZ), GIANT_LILY_PAD, 2);
				world.setBlockState(pos.add(- 1, 1, airZ), GIANT_LILY_PAD, 2);
			}

		if (direction == 2)
			for (int airX = -5; airX < -1; airX++) {
				world.setBlockState(pos.add(airX, 0, 0), AIR, 2);
				world.setBlockState(pos.add(airX, 1, 0), AIR, 2);
				world.setBlockState(pos.add(airX, 1, - 1), GIANT_LILY_PAD, 2);
				world.setBlockState(pos.add(airX, 1, 1), GIANT_LILY_PAD, 2);
			}

		if (direction == 3)
			for (int airZ = -5; airZ < -1; airZ++) {
				world.setBlockState(pos.add(0, 0, airZ), AIR, 2);
				world.setBlockState(pos.add(0, 1, airZ), AIR, 2);
				world.setBlockState(pos.add(1, 1, airZ), GIANT_LILY_PAD, 2);
				world.setBlockState(pos.add(- 1, 1, airZ), GIANT_LILY_PAD, 2);
			}

		// flower stem
		world.setBlockState(pos.add(1, 1, 0), GIANT_LILY_PAD, 2);
		world.setBlockState(pos.add(0, 1, 1), GIANT_LILY_PAD, 2);
		world.setBlockState(pos.add(- 1, 1, 0), GIANT_LILY_PAD, 2);
		world.setBlockState(pos.add(0, 1, - 1), GIANT_LILY_PAD, 2);

		// flower
		world.setBlockState(pos.add(1, 2, 0), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(0, 2, 1), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(- 1, 2, 0), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(0, 2, - 1), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(1, 2, 1), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(1, 2, - 1), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(- 1, 2, 1), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(- 1, 2, - 1), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(2, 2, 0), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(0, 2, 2), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(- 2, 2, 0), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(0, 2, - 2), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(2, 3, 0), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(0, 3, 2), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(- 2, 3, 0), GIANT_FLOWER, 2);
		world.setBlockState(pos.add(0, 3, - 2), GIANT_FLOWER, 2);

		// chest and spawners
		world.setBlockState(pos.up(), Blocks.CHEST.getDefaultState());
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(pos.up());
		if (chest != null)
			LootUtil.generateLoot(chest, rand, CHEST_LOOT, 5, 15);
		world.setBlockState(pos.up(2), DRAGON_FLY_SPAWNER, 3);
		world.setBlockState(pos.up(3), DRAGON_FLY_SPAWNER, 3);
		//System.out.println("Dragon Fly Dungeon Here: "  + pos);
		return true;
	}
}