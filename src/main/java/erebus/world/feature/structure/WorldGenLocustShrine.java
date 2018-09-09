package erebus.world.feature.structure;

import java.util.List;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModFluids;
import erebus.ModItems;
import erebus.blocks.BlockBones;
import erebus.blocks.BlockUmberstone;
import erebus.blocks.BlockUmberstone.EnumType;
import erebus.items.ItemErebusFood.EnumFoodType;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.tileentity.TileEntityBones;
import erebus.world.biomes.decorators.data.SurfaceType;
import erebus.world.loot.IPostProcess;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDirt.DirtType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class WorldGenLocustShrine extends WorldGenerator {

	public int height = 7;
	public int radius = 4;
	public IBlockState COARSE_DIRT = Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, DirtType.COARSE_DIRT);
	public IBlockState UMBERGRAVEL = ModBlocks.UMBERGRAVEL.getDefaultState();
	public IBlockState DEAD_BUSH = Blocks.DEADBUSH.getDefaultState();
	public IBlockState MOSSY_UMBERCOBBLE = ModBlocks.UMBERSTONE.getDefaultState().withProperty(BlockUmberstone.TYPE, EnumType.UMBERCOBBLE_MOSSY);
	public IBlockState MOSSY_UMBERCOBBLE_WALL = ModBlocks.WALL_UMBERCOBBLE_MOSSY.getDefaultState();
	public IBlockState SPAWNER = ModBlocks.LOCUST_SPAWNER.getDefaultState();
	public IBlockState BONES = ModBlocks.BLOCK_OF_BONES.getDefaultState();
			
	public static final WeightedLootList CHEST_LOOT = new WeightedLootList(new LootItemStack[] {
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(EnumErebusMaterialsType.JADE.ordinal()).setWeight(10),
			new LootItemStack(ModItems.MATERIALS).setAmount(4, 8).setDamage(EnumErebusMaterialsType.PLATE_EXO.ordinal()).setWeight(9),
			new LootItemStack(Items.ENCHANTED_BOOK).setWeight(8),
			new LootItemStack(Items.IRON_PICKAXE).setWeight(2),
			new LootItemStack(ModItems.JADE_PICKAXE).setWeight(1),
			new LootItemStack(Items.IRON_SHOVEL).setWeight(2),
			new LootItemStack(ModItems.JADE_SHOVEL).setWeight(1),
			new LootItemStack(Items.IRON_AXE).setWeight(2),
			new LootItemStack(ModItems.JADE_AXE).setWeight(1),
			new LootItemStack(ModItems.BAMBOO_CHESTPLATE).setWeight(2), new LootItemStack(ModItems.JADE_CHESTPLATE).setWeight(1),
			new LootItemStack(ModItems.BAMBOO_HELMET).setWeight(2),
			new LootItemStack(ModItems.JADE_HELMET).setWeight(1),
			new LootItemStack(ModItems.BAMBOO_LEGGINGS).setWeight(2), new LootItemStack(ModItems.JADE_LEGGINGS).setWeight(1),
			new LootItemStack(ModItems.BAMBOO_BOOTS).setWeight(2),
			new LootItemStack(ModItems.EXOSKELETON_BOOTS).setWeight(3),
			new LootItemStack(ModItems.BAMBOO_SHIELD).setAmount(1).setWeight(3),
			new LootItemStack(ModItems.ANTI_VENOM_BOTTLE).setAmount(1, 3).setWeight(8),
			new LootItemStack(ModItems.SPRAY_CAN).setAmount(3, 6).setWeight(5),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.FLY_WING.ordinal()).setWeight(1),
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(EnumErebusMaterialsType.REINFORCED_PLATE_EXO.ordinal()).setWeight(4),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.BEETLE_TAMING_AMULET.ordinal()).setWeight(1),
			new LootItemStack(ModItems.EREBUS_FOOD).setAmount(1, 3).setDamage(EnumFoodType.BAMBOO_SOUP.ordinal()).setWeight(4),
			new LootItemStack(ModItems.EREBUS_FOOD).setAmount(1, 2).setDamage(EnumFoodType.LARVAE_ON_STICK.ordinal()).setWeight(6),
			new LootItemStack(ModItems.BAMBUCKET).setAmount(1).setWeight(3),
			new LootItemStack(ModItems.CABBAGE_SEEDS).setAmount(3).setWeight(5),
			new LootItemStack(ModItems.EREBUS_FOOD).setAmount(1, 3).setDamage(EnumFoodType.CABBAGE.ordinal()).setWeight(10),
			new LootItemStack(ModItems.TURNIP).setAmount(1, 3).setWeight(10)
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
					int randomBucket = rand.nextInt(2);
					switch (randomBucket) {
					case 0:
						is = ModFluids.getFilledBambucket(new FluidStack(FluidRegistry.getFluid("beetle_juice"), Fluid.BUCKET_VOLUME));
						break;
					case 1:
						is = ModFluids.getFilledBambucket(new FluidStack(FluidRegistry.getFluid("honey"), Fluid.BUCKET_VOLUME));
						break;
					}
				}
				return is;
			}
		});

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		if (!aircheck(world, rand, pos))
			return false;

		for (int xx = -radius; xx <= radius; xx++) {
			for (int zz = -radius; zz <= radius; zz++) {
				double circle = Math.pow(xx, 2.0D) + Math.pow(zz, 2.0D);
				if (Math.round(Math.sqrt(circle)) < 5)
					world.setBlockState(pos.add(xx, -1, zz), rand.nextInt(5) != 0 ? COARSE_DIRT : UMBERGRAVEL, 2);
				if (world.getBlockState(pos.add(xx, -1, zz)).getBlock() instanceof BlockDirt && rand.nextInt(3) == 0)
					world.setBlockState(pos.add(xx, 0, zz), DEAD_BUSH, 2);
			}
		}
		placeSpawnerPillar(world, rand, pos);
		placePillar(world, rand, pos.add(3, 0, 3), EnumFacing.EAST); // se EAST
		placePillar(world, rand, pos.add(3, 0, -3), EnumFacing.NORTH); // ne NORTH
		placePillar(world, rand, pos.add(-3, 0, 3), EnumFacing.SOUTH);// sw SOUTH
		placePillar(world, rand, pos.add(-3, 0, -3), EnumFacing.WEST);// nw WEST
		//System.out.println("Locust Dungeon Here: "  + pos);
		return true;
	}

	private void placePillar(World world, Random rand, BlockPos pos, EnumFacing facing) {
		world.setBlockState(pos, MOSSY_UMBERCOBBLE, 2);
		world.setBlockState(pos.up(1), MOSSY_UMBERCOBBLE_WALL, 2);
		world.setBlockState(pos.up(2), MOSSY_UMBERCOBBLE, 2);
		world.setBlockState(pos.up(3), MOSSY_UMBERCOBBLE, 2);
		world.setBlockState(pos.up(4), MOSSY_UMBERCOBBLE_WALL, 2);
		world.setBlockState(pos.up(5), MOSSY_UMBERCOBBLE, 2);
		world.setBlockState(pos.up(6), BONES.withProperty(BlockBones.FACING, facing), 2);
		TileEntityBones bones = (TileEntityBones) world.getTileEntity(pos.up(6));
		if (bones != null)
			LootUtil.generateLoot(bones, rand, CHEST_LOOT, 2, 3);
	}

	private void placeSpawnerPillar(World world, Random rand, BlockPos pos) {
		world.setBlockState(pos, MOSSY_UMBERCOBBLE, 2);
		world.setBlockState(pos.up(1), MOSSY_UMBERCOBBLE, 2);
		world.setBlockState(pos.up(2), MOSSY_UMBERCOBBLE_WALL, 2);
		world.setBlockState(pos.up(3), MOSSY_UMBERCOBBLE, 2);
		world.setBlockState(pos.up(4), SPAWNER, 2);
	}

	private boolean aircheck(World world, Random rand, BlockPos pos) {
		for (int xx = -radius; xx <= radius; xx++)
			for (int zz = -radius; zz <= radius; zz++) {
				if (!checkSurface(world, SurfaceType.GRASS, pos.add(xx, -1, zz)))
					return false;
				for (int yy = 0; yy < height; yy++)
					if (!world.isAirBlock(pos.add(xx, yy, zz)))
						return false;
			}
		return true;
	}

	protected boolean checkSurface(World world, SurfaceType surfaceType, BlockPos pos) {
		return surfaceType.matchBlock(world.getBlockState(pos));
	}
}
