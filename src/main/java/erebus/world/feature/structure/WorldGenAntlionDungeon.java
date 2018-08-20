package erebus.world.feature.structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.ModFluids;
import erebus.ModItems;
import erebus.block.bamboo.BlockBambooTorch;
import erebus.block.bamboo.BlockBambooTorch.EnumBlockTorchHalf;
import erebus.blocks.BlockBones;
import erebus.blocks.BlockCapstone;
import erebus.blocks.BlockCapstone.EnumCapstoneType;
import erebus.blocks.BlockGneiss;
import erebus.blocks.BlockGneiss.EnumGneissType;
import erebus.blocks.BlockTempleBrickUnbreaking;
import erebus.blocks.BlockTempleBrickUnbreaking.EnumTempleBrickType;
import erebus.blocks.BlockTempleTeleporter;
import erebus.blocks.BlockTempleTeleporter.EnumTeleporterType;
import erebus.entity.EntityAntlionBoss;
import erebus.entity.EntityUmberGolemDungeonTypes;
import erebus.items.ItemErebusFood.EnumFoodType;
import erebus.items.ItemMaterials;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
import erebus.items.ItemSmoothie.SmoothieType;
import erebus.tileentity.TileEntityBones;
import erebus.tileentity.TileEntityTempleTeleporter;
import erebus.world.feature.structure.worlddata.WorldDataAntlionMaze;
import erebus.world.feature.util.MazeGenerator;
import erebus.world.feature.util.PerfectMazeGenerator;
import erebus.world.loot.IPostProcess;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockTorch;
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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
// 1691502390890771781
// X: 272 Y: 18 Z: 576 // -542 43 209
public class WorldGenAntlionDungeon extends WorldGenerator {
	private IBlockState GNEISS = ModBlocks.GNEISS.getDefaultState();
	private IBlockState GNEISS_RELIEF = ModBlocks.GNEISS.getDefaultState().withProperty(BlockGneiss.TYPE, EnumGneissType.GNEISS_RELIEF);
	private IBlockState GNEISS_CARVED = ModBlocks.GNEISS.getDefaultState().withProperty(BlockGneiss.TYPE, EnumGneissType.GNEISS_CARVED);
	private IBlockState GNEISS_BRICKS = ModBlocks.GNEISS.getDefaultState().withProperty(BlockGneiss.TYPE, EnumGneissType.GNEISS_BRICKS);
	private IBlockState GNEISS_TILES = ModBlocks.GNEISS.getDefaultState().withProperty(BlockGneiss.TYPE, EnumGneissType.GNEISS_TILES);
	private IBlockState TEMPLE_BRICK_UNBREAKING = ModBlocks.TEMPLE_BRICK_UNBREAKING.getDefaultState();
	private IBlockState TEMPLE_PILLAR = ModBlocks.TEMPLE_PILLAR.getDefaultState();
	private IBlockState CAPSTONE = ModBlocks.CAPSTONE.getDefaultState();
	private static IBlockState BAMBOO_TORCH_LOWER = ModBlocks.BAMBOO_TORCH.getDefaultState().withProperty(BlockBambooTorch.HALF, EnumBlockTorchHalf.LOWER);
	private static IBlockState BAMBOO_TORCH_UPPER = ModBlocks.BAMBOO_TORCH.getDefaultState().withProperty(BlockBambooTorch.HALF, EnumBlockTorchHalf.UPPER);
	private IBlockState GNEISS_VENT = ModBlocks.GNEISS_VENT.getDefaultState();
	private IBlockState FORCE_FIELD = ModBlocks.FORCE_FIELD.getDefaultState();
	private IBlockState TEMPLE_BRICK = ModBlocks.TEMPLE_BRICK.getDefaultState();
	private IBlockState ANTLION_SPAWNER = ModBlocks.ANTLION_SPAWNER.getDefaultState();
	private IBlockState MAGMA_CRAWLER_SPAWNER = ModBlocks.MAGMA_CRAWLER_SPAWNER.getDefaultState();
	private IBlockState TORCH_EAST = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST);
	private IBlockState TORCH_WEST = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST);
	private IBlockState TORCH_SOUTH = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH);
	private IBlockState TORCH_NORTH = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH);
	
	private final Map<IBlockState, Boolean> STRUCTURE_BLOCKS = new HashMap<IBlockState, Boolean>();
	
	public WorldGenAntlionDungeon() {
		initStuctureBlockMap();
	}
	
	public boolean isSolidStructureBlock(IBlockState state) {
		return STRUCTURE_BLOCKS.get(state) != null;
	}

	private void initStuctureBlockMap() {
		if (STRUCTURE_BLOCKS.isEmpty()) {
			STRUCTURE_BLOCKS.put(GNEISS, true);
			STRUCTURE_BLOCKS.put(GNEISS_RELIEF, true);
			STRUCTURE_BLOCKS.put(GNEISS_CARVED, true);
			STRUCTURE_BLOCKS.put(GNEISS_BRICKS, true);
			STRUCTURE_BLOCKS.put(GNEISS_TILES, true);
			STRUCTURE_BLOCKS.put(TEMPLE_BRICK_UNBREAKING, true);
			STRUCTURE_BLOCKS.put(TEMPLE_PILLAR, true);
			STRUCTURE_BLOCKS.put(CAPSTONE, true);
			STRUCTURE_BLOCKS.put(BAMBOO_TORCH_LOWER, true);
			STRUCTURE_BLOCKS.put(BAMBOO_TORCH_UPPER, true);
			STRUCTURE_BLOCKS.put(GNEISS_VENT, true);
			STRUCTURE_BLOCKS.put(FORCE_FIELD, true);
			STRUCTURE_BLOCKS.put(TEMPLE_BRICK, true);
			STRUCTURE_BLOCKS.put(ANTLION_SPAWNER, true);
			STRUCTURE_BLOCKS.put(MAGMA_CRAWLER_SPAWNER, true);
			STRUCTURE_BLOCKS.put(TORCH_EAST, true);
			STRUCTURE_BLOCKS.put(TORCH_WEST, true);
			STRUCTURE_BLOCKS.put(TORCH_SOUTH, true);
			STRUCTURE_BLOCKS.put(TORCH_NORTH, true);
			STRUCTURE_BLOCKS.put(Blocks.LAVA.getDefaultState(), true);
		}
}

	public static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] {
			new LootItemStack(Items.BOOK).setAmount(1, 4).setWeight(18),
			new LootItemStack(Items.PAPER).setAmount(2, 6).setWeight(16),
			new LootItemStack(Blocks.WEB).setAmount(2, 7).setWeight(13),
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(EnumErebusMaterialsType.JADE.ordinal()).setWeight(10),
			new LootItemStack(ModItems.MATERIALS).setAmount(4, 8).setDamage(EnumErebusMaterialsType.PLATE_EXO.ordinal()).setWeight(9),
			new LootItemStack(Items.ENCHANTED_BOOK).setWeight(8),
			new LootItemStack(ModBlocks.UMBER_GOLEM_STATUE).setAmount(1).setWeight(1),
			new LootItemStack(ModItems.WEB_SLINGER).setAmount(1).setWeight(1),
			new LootItemStack(Items.GOLDEN_PICKAXE).setWeight(3), new LootItemStack(Items.IRON_PICKAXE).setWeight(2),
			new LootItemStack(ModItems.JADE_PICKAXE).setWeight(1), new LootItemStack(Items.GOLDEN_SHOVEL).setWeight(3),
			new LootItemStack(Items.IRON_SHOVEL).setWeight(2), new LootItemStack(ModItems.JADE_SHOVEL).setWeight(1),
			new LootItemStack(Items.GOLDEN_AXE).setWeight(3), new LootItemStack(Items.IRON_AXE).setWeight(2),
			new LootItemStack(ModItems.JADE_AXE).setWeight(1), new LootItemStack(Items.GOLDEN_SWORD).setWeight(3),
			new LootItemStack(Items.IRON_SWORD).setWeight(2), new LootItemStack(ModItems.JADE_SWORD).setWeight(1),
			new LootItemStack(Items.IRON_CHESTPLATE).setWeight(2), new LootItemStack(ModItems.JADE_CHESTPLATE).setWeight(1),
			new LootItemStack(Items.GOLDEN_CHESTPLATE).setWeight(1), new LootItemStack(Items.IRON_HELMET).setWeight(2),
			new LootItemStack(ModItems.JADE_HELMET).setWeight(1), new LootItemStack(Items.GOLDEN_HELMET).setWeight(1),
			new LootItemStack(Items.IRON_LEGGINGS).setWeight(2), new LootItemStack(ModItems.JADE_LEGGINGS).setWeight(1),
			new LootItemStack(Items.GOLDEN_LEGGINGS).setWeight(1), new LootItemStack(Items.IRON_BOOTS).setWeight(2),
			new LootItemStack(ModItems.JADE_BOOTS).setWeight(1), new LootItemStack(Items.GOLDEN_BOOTS).setWeight(1),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.ALTAR_FRAGMENT.ordinal()).setWeight(1),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.REINFORCED_PLATE_EXO.ordinal()).setWeight(1),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.SCORPION_PINCER.ordinal()).setWeight(1),
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(EnumErebusMaterialsType.WHETSTONE_POWDER.ordinal()).setWeight(3),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.PLATE_EXO_RHINO.ordinal()).setWeight(1),
			new LootItemStack(ModItems.EREBUS_FOOD).setAmount(1, 3).setDamage(EnumFoodType.HONEY_SANDWICH.ordinal()).setWeight(3),
			new LootItemStack(ModItems.CABBAGE_SEEDS).setAmount(1, 3).setWeight(2),
			new LootItemStack(ModItems.WHETSTONE).setAmount(1).setDamage(0).setWeight(1),
			new LootItemStack(ModItems.LIFE_BLOOD).setAmount(1, 2).setWeight(4),
			new LootItemStack(ModItems.ROLLED_NEWSPAPER).setAmount(1).setWeight(1),
			new LootItemStack(ModItems.BAMBUCKET).setAmount(1).setWeight(6),
			new LootItemStack(ModItems.HOMING_BEECON).setAmount(1).setWeight(1),
			new LootItemStack(ModBlocks.GLOW_GEM_ACTIVE).setAmount(1, 3).setWeight(5),
			new LootItemStack(ModItems.SMOOTHIE).setAmount(1, 3).setDamage(SmoothieType.GIVIN_ME_THE_BLUES.ordinal()).setWeight(3),
			new LootItemStack(ModItems.SMOOTHIE).setAmount(1).setDamage(SmoothieType.BRYUFS_BREW.ordinal()).setWeight(1),
			new LootItemStack(ModItems.WASP_DAGGER).setAmount(1, 3).setWeight(2)

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
		if (checkWorldLocation(world, pos)) {
			generateStructure(world, rand, pos);
			return true;
		}
		return false;
	}

	public boolean checkBiome(Biome biome) {
		return biome == ModBiomes.VOLCANIC_DESERT;
		
	}

	public boolean checkWorldLocation(World world, BlockPos pos) {
		// TODO A better Biome radius check
		if (!checkBiome(world.getBiome(pos.add(64, 0, 0))) || !checkBiome(world.getBiome(pos.add(-64, 0, 0))) || !checkBiome(world.getBiome(pos.add(0, 0, 64))) || !checkBiome(world.getBiome(pos.add(0, 0, -64))))
			return false;
		
		WorldDataAntlionMaze data = WorldDataAntlionMaze.forWorld(world);
		if (data.getData().isEmpty()) {
			data.addStructurePosition(pos);
			data.markDirty(); //Save the data to the disc.
			return true;
		}
		if (!data.getData().isEmpty()) {
			for (int i = 0; i < data.getData().size(); i++) {
				int x_origin = data.getData().get(i).getX();
				int z_origin = data.getData().get(i).getZ();
				if ((pos.getX() >= x_origin - 800 && pos.getX() <= x_origin + 800) && (pos.getZ() >= z_origin - 800 && pos.getZ() <= z_origin + 800)) {
					//System.out.println("Structure Clashes with already generated Maze");
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public void generateStructure(World world, Random rand, BlockPos pos) {
		WorldDataAntlionMaze data = WorldDataAntlionMaze.forWorld(world);
		data.addStructurePosition(pos);
		data.markDirty(); //Save the data to the disc.

		int sizeX = 60;
		int sizeY = 4;
		int sizeZ = 60;
		int mazeWidth = sizeX / 2;
		int mazeHeight = sizeZ / 2;

		int[][] maze = null;
		MazeGenerator generator = new PerfectMazeGenerator(mazeWidth, mazeHeight);
		maze = generator.generateMaze();

		buildFloor(world, pos.add(0, -4, 0), mazeWidth, mazeHeight, rand);
		buildRoof(world, pos, mazeWidth, mazeHeight, rand);

		buildLevel(world, pos.add(-60, -3, -60), mazeWidth , mazeHeight, maze, GNEISS_RELIEF);
		buildLevel(world, pos.add(-60, -2, -60), mazeWidth, mazeHeight, maze, GNEISS_CARVED);
		buildLevel(world, pos.add(-60, -1, -60), mazeWidth, mazeHeight, maze, GNEISS_RELIEF);
		createAir(world, pos.add(0, -3, 0), mazeWidth, mazeHeight, rand);
		addFeature(world, pos.add(-60, -2, -60), mazeWidth, mazeHeight, maze, rand);
		buildCourtyard(world, TEMPLE_PILLAR, pos.add(0, - 4, 0), 52, 4, 52);
		createPyramid(world, pos.add(0, - 4, 0), TEMPLE_BRICK_UNBREAKING, true, 22, 22);
		decoratePyramid(world, pos.add(-22, - 4, -22));
		addTeleporters(world, pos.add(-22, - 4, -22));
		addCapstones(world, pos.add(0, 17, 0));
		spawnIdolGuardians(world, pos.add(-60, -3, -60));
		//System.out.println("Generated Maze At: X: " + pos.getX() + " Y: " + pos.getY() + " Z: " + pos.getZ());
	}

	private void createAir(World world, BlockPos pos, int w, int h, Random rand) {
		for (int i = - h * 2; i <= h * 2; i++)
			for (int j = - w * 2; j <= w * 2; j++)
				for (int k = 0; k <= 2; k++)
					if (canPlaceFeatureAt(world, pos, pos.add(j, k, i)))
						if (!isSolidStructureBlock(world.getBlockState(pos.add(j, k, i))))
							world.setBlockToAir(pos.add(j, k, i));
	}

	private void addTeleporters(World world, BlockPos pos) {
		// room 1
		world.setBlockState(pos.add(13, 9, 13), CAPSTONE, 2);
		setFloorDecoStone(world, pos.add(14, 9, 14));
		setLockStone(world, pos.add(15, 9, 15), 2);
		setTeleporter(world, pos.add(16, 9, 16), 0, pos.add(30, 9, 13));
		setTeleporter(world, pos.add(19, 9, 19), 5, pos.add(19, 14, 19));
		// room 2
		world.setBlockState(pos.add(30, 9, 13), CAPSTONE, 2);
		setFloorDecoStone(world, pos.add(25, 9, 14));
		setLockStone(world, pos.add(26, 9, 15), 3);
		setTeleporter(world, pos.add(27, 9, 16), 0, pos.add(30, 9, 30));
		setTeleporter(world, pos.add(24, 9, 19), 5, pos.add(13, 9, 13));
		// room 3
		world.setBlockState(pos.add(30, 9, 30), CAPSTONE, 2);
		setFloorDecoStone(world, pos.add(25, 9, 25));
		setLockStone(world, pos.add(26, 9, 26), 4);
		setTeleporter(world, pos.add(27, 9, 27), 0, pos.add(13, 9, 30));
		setTeleporter(world, pos.add(24, 9, 24), 5, pos.add(30, 9, 13));
		// room 4
		world.setBlockState(pos.add(13, 9, 30), CAPSTONE, 2);
		setFloorDecoStone(world, pos.add(14, 9, 25));
		setLockStone(world, pos.add(15, 9, 26), 5);
		setTeleporter(world, pos.add(16, 9, 27), 0, pos.add(13, 9, 13));
		setTeleporter(world, pos.add(19, 9, 24), 5, pos.add(30, 9, 30));
		// centre of pyramid - these teleport you to the boss arena (1 of the 4 corners)
		setFloorMidDecoStone(world, pos.add(20, 9, 20));
		setTeleporter(world, pos.add(22, 9, 21), 6, pos.add(5, 1, 5));
		setTeleporter(world, pos.add(21, 9, 21), 7, pos.add(38, 1, 5));
		setTeleporter(world, pos.add(22, 9, 22), 8, pos.add(38, 1, 38));
		setTeleporter(world, pos.add(21, 9, 22), 9, pos.add(5, 1, 38));
		// Top level
		world.setBlockState(pos.add(19, 14, 19), CAPSTONE, 2);
		world.setBlockState(pos.add(19, 15, 25), BAMBOO_TORCH_LOWER, 3);
		world.setBlockState(pos.add(19, 16, 25), BAMBOO_TORCH_UPPER, 3);
		world.setBlockState(pos.add(25, 15, 19), BAMBOO_TORCH_LOWER, 3);
		world.setBlockState(pos.add(25, 16, 19), BAMBOO_TORCH_UPPER, 3);
		setFloorDecoStone(world, pos.add(20, 14, 20));
		setLockStone(world, pos.add(21, 14, 21), 1);
		setTeleporter(world, pos.add(22, 14, 22), 0, pos.add(13, 9, 13));
		setTeleporter(world, pos.add(25, 14, 25), 5, pos.add(19, 14, 19));
	}

	private void setLockStone(World world, BlockPos pos, int meta) {
		for (int dx = 0; dx < 3; dx++)
			for (int dz = 0; dz < 3; dz++)
				world.setBlockState(pos.add(dx, 0, dz), ModBlocks.TEMPLE_BRICK_UNBREAKING.getDefaultState().withProperty(BlockTempleBrickUnbreaking.TYPE, EnumTempleBrickType.values()[meta]), 2);
	}

	private void setFloorDecoStone(World world, BlockPos pos) {
		for (int dx = 0; dx < 5; dx++)
			for (int dz = 0; dz < 5; dz++)
				world.setBlockState(pos.add(dx, 0, dz), CAPSTONE, 2);
	}

	private void setFloorMidDecoStone(World world, BlockPos pos) {
		for (int dx = 0; dx < 4; dx++)
			for (int dz = 0; dz < 4; dz++)
				world.setBlockState(pos.add(dx, 0, dz), CAPSTONE, 2);
	}

	private void decoratePyramid(World world, BlockPos pos) {
		// create floors
		boolean forcefieldSet = false;
		boolean topchestSet = false;
		for (int yy = 0; yy < 30; yy++)
			for (int xx = 0; xx < 44; xx++)
				for (int zz = 0; zz < 44; zz++) {
					if (yy == 0)
						world.setBlockState(pos.add(xx, yy, zz), TEMPLE_BRICK_UNBREAKING, 2);
					if (yy == 1) {
						if (xx > 1 && xx < 42 && zz > 1 && zz < 42)
							world.setBlockState(pos.add(xx, yy, zz), Blocks.SAND.getDefaultState(), 2);
						if (xx > 4 && xx < 39 && zz > 4 && zz < 39)
							if ((xx) % 11 == 5 || (zz) % 11 == 5)
								world.setBlockState(pos.add(xx, yy, zz), GNEISS_VENT, 2);
							else {
								if (xx >= 21 && xx <= 22 && zz >= 21 && zz <= 22)
									world.setBlockState(pos.add(pos.add(xx, yy, zz)), GNEISS_VENT, 2);
								else
									world.setBlockState(pos.add(xx, yy, zz), Blocks.SAND.getDefaultState(), 2);
							}
						
					}

					if (yy == 9)
						if (xx > 9 && xx < 34 && zz > 9 && zz < 34)
							world.setBlockState(pos.add(xx, yy, zz), TEMPLE_BRICK_UNBREAKING, 2);

					if (yy == 10 && !forcefieldSet) {
						for (int d = 0; d < 4; d++) {
							for (int wx = 0 + d; wx < 9; wx++) {
								world.setBlockState(pos.add(11 + wx, yy + d, 21), FORCE_FIELD, 2);
								world.setBlockState(pos.add(11 + wx, yy + d, 22), FORCE_FIELD, 2);
								world.setBlockState(pos.add(21, yy + d, 11 + wx), FORCE_FIELD, 2);
								world.setBlockState(pos.add(22, yy + d, 11 + wx), FORCE_FIELD, 2);
								world.setBlockState(pos.add(21, yy + d, 32 - wx), FORCE_FIELD, 2);
								world.setBlockState(pos.add(22, yy + d, 32 - wx), FORCE_FIELD, 2);
								world.setBlockState(pos.add(32 - wx, yy + d, 21), FORCE_FIELD, 2);
								world.setBlockState(pos.add(32 - wx, yy + d, 22), FORCE_FIELD, 2);
							}

							for (int dx = 20; dx < 24; dx++)
								for (int dz = 20; dz < 24; dz++)
									world.setBlockState(pos.add(dx, yy + d, dz), FORCE_FIELD, 2);

							for (int dx1 = 21; dx1 < 23; dx1++)
								for (int dz1 = 21; dz1 < 23; dz1++)
									world.setBlockToAir(pos.add(dx1, yy + d, dz1));
						}
						forcefieldSet = true;
					}
					if (yy == 14)
						if (xx > 14 && xx < 29 && zz > 14 && zz < 29)
							world.setBlockState(pos.add(xx, yy, zz), TEMPLE_BRICK_UNBREAKING, 2);

					if (yy == 15 && !topchestSet) {
						// contents is 8 pieces of jade
						world.setBlockState(pos.add(19, yy, 22), Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.NORTH), 2);
						TileEntityChest chest = (TileEntityChest) world.getTileEntity(pos.add(19, yy, 22));
						if (chest != null) {
							chest.setInventorySlotContents(0, ItemMaterials.EnumErebusMaterialsType.JADE.createStack(8));
							chest.markDirty();
						}
					}
					if (yy == 16) {
						// TODO Lighting?
					}
				}
	}

	public static void setTeleporter(World world, BlockPos pos, int meta, BlockPos posTarget) {
		world.setBlockState(pos, ModBlocks.TEMPLE_TELEPORTER.getDefaultState().withProperty(BlockTempleTeleporter.TYPE, EnumTeleporterType.values()[meta]), 2);
		TileEntityTempleTeleporter teleporter = (TileEntityTempleTeleporter) world.getTileEntity(pos);
		if (teleporter != null)
			teleporter.setTargetDestination(posTarget);
	}

	private void addCapstones(World world, BlockPos pos) {
		world.setBlockState(pos.add(-1, 0, -1), CAPSTONE.withProperty(BlockCapstone.TYPE, EnumCapstoneType.CAPSTONE_MUD), 3);
		world.setBlockState(pos.add(0, 0, -1), CAPSTONE.withProperty(BlockCapstone.TYPE, EnumCapstoneType.CAPSTONE_IRON), 3);
		world.setBlockState(pos.add(-1, 0, 0), CAPSTONE.withProperty(BlockCapstone.TYPE, EnumCapstoneType.CAPSTONE_GOLD), 3);
		world.setBlockState(pos.add(0, 0, 0), CAPSTONE.withProperty(BlockCapstone.TYPE, EnumCapstoneType.CAPSTONE_JADE), 3);
	}

	private void createPyramid(World world, BlockPos pos, IBlockState block, boolean isHollow, int baseLengthX, int baseLengthZ) {
		for (int i = 0; i < 21; i++) {
			int maxX = baseLengthX - 1;
			int maxZ = baseLengthZ - 1;
			for (int ix = -baseLengthX; ix  <= maxX; ix++)
				for (int iz = - baseLengthZ; iz <= maxZ; iz++)
					if (ix == - baseLengthX || ix == maxX || iz == - baseLengthZ || iz == maxZ) {
						if (!isSolidStructureBlock(world.getBlockState(pos.add(ix, i, iz))))
								world.setBlockState(pos.add(ix, i, iz), block, 2);
					}
					else if (isHollow) {
						if (!isSolidStructureBlock(world.getBlockState(pos.add(ix, i, iz))))
							if (!world.isAirBlock(pos.add(ix , i, iz)))
								world.setBlockToAir(pos.add(ix, i, iz));
					}

			baseLengthX--;
			baseLengthZ--;
		}
	}

	private void spawnIdolGuardians(World world, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		if (!world.isRemote)
			for (byte spawn = 0; spawn < 4; spawn++) {
				EntityUmberGolemDungeonTypes entityUmberGolem;
				entityUmberGolem = new EntityUmberGolemDungeonTypes(world);
				entityUmberGolem.setType(spawn);
				entityUmberGolem.setHealth(entityUmberGolem.getMaxHealth());
				switch (spawn) {
					case 0:
						entityUmberGolem.setPosition(x + 2.5D, y, z + 2.5D);
						break;
					case 1:
						entityUmberGolem.setPosition(x + 118.5D, y, z + 2.5D);
						break;
					case 2:
						entityUmberGolem.setPosition(x + 2.5D, y, z + 118.5D);
						break;
					case 3:
						entityUmberGolem.setPosition(x + 118.5D, y, z + 118.5D);
						break;
				}
				world.spawnEntity(entityUmberGolem);
			}
	}

	private void buildCourtyard(World world, IBlockState block, BlockPos pos, int baseLengthX, int heightY, int baseLengthZ) {
		for (int yy = 0; yy <= heightY ; yy++)
			for (int xx = - baseLengthX / 2; xx <  baseLengthX / 2; xx++)
				for (int zz = - baseLengthZ / 2; zz < baseLengthZ / 2; zz++)
					if (yy > 0)
						if (yy <= 4) {
							if (!world.isAirBlock(pos.add(xx, yy, zz)))
								world.setBlockToAir(pos.add(xx, yy, zz));

							if (xx == - baseLengthX / 2 || xx == baseLengthX / 2 - 1)
								if (zz > - baseLengthZ / 2 && zz < baseLengthZ / 2) {
									if (yy <= 3)
										for (int i = 3; i < 49; i += 5)
											world.setBlockState(pos.add(xx, yy, - baseLengthZ / 2 + i), block, 2);
									if (yy == 4)
										for (int i = 0; i < 52; i++)
											world.setBlockState(pos.add(xx, yy, - baseLengthZ / 2 + i), TEMPLE_BRICK, 2);
								}

							if (zz == - baseLengthZ / 2 || zz == + baseLengthZ / 2 - 1)
								if (xx > - baseLengthX / 2 && xx <  baseLengthX / 2) {
									if (yy <= 3)
										for (int i = 3; i < 49; i += 5)
											world.setBlockState(pos.add(- baseLengthZ / 2 + i, yy, zz), block, 2);
									if (yy == 4)
										for (int i = 0; i < 52; i++)
											world.setBlockState(pos.add(- baseLengthZ / 2 + i, yy, zz), TEMPLE_BRICK, 2);
								}
						}
	}

	private void buildRoof(World world, BlockPos pos, int w, int h, Random rand) {
		for (int i = - h * 2; i <= h * 2; i++)
			for (int j = - w * 2; j <= w * 2; j++)
				if (canPlaceFeatureAt(world, pos, pos.add(j, 0, i)))
					world.setBlockState(pos.add(j, 0, i), GNEISS_BRICKS, 2);
	}

	private void buildFloor(World world, BlockPos pos, int w, int h, Random rand) {
		createPyramid(world, pos.add(0, 5, 0), Blocks.AIR.getDefaultState(), true, 24, 24);
		for (int i = -h * 2; i <= h * 2; i++)
			for (int j = -w * 2; j <= w * 2; j++)
				if (canPlaceFloorAt(world, pos, pos.add(j, 0, i))) {
					if (rand.nextInt(15) == 0)
						if (rand.nextBoolean() && rand.nextBoolean())
							world.setBlockState(pos.add(j, 0, i), Blocks.LAVA.getDefaultState());
						else
							world.setBlockState(pos.add(j, 0, i), GNEISS_VENT);
					else
						world.setBlockState(pos.add(j, 0, i), GNEISS_TILES, 2);
				}
	}

	private boolean canPlaceFloorAt(World world, BlockPos pos, BlockPos posFeature) {
		for (int xx = pos.getX() - 22; xx < pos.getX() + 22; xx++)
			for (int zz = pos.getZ() - 22; zz < pos.getZ() + 22; zz++)
				if (xx == posFeature.getX() && zz == posFeature.getZ())
					return false;
		return true;
	}

	private boolean canPlaceFeatureAt(World world, BlockPos pos, BlockPos posFeature) {
		for (int xx = pos.getX() - 26; xx < pos.getX() + 26; xx++)
			for (int zz = pos.getZ() - 26; zz < pos.getZ() + 26; zz++)
				if (xx == posFeature.getX() && zz == posFeature.getZ())
					return false;
		return true;
	}

	private void addFeature(World world, BlockPos pos, int w, int h, int[][] maze, Random rand) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 1) == 0)
					if (rand.nextInt(25) == 0 && canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(1 + j * 4, - 1, 1 + i * 4))) {
						world.setBlockState(pos.add(1 + j * 4, 0, 1 + i * 4), TORCH_SOUTH, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, pos.add(1 + j * 4, - 1, 1 + i * 4), EnumFacing.SOUTH, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, pos.add(1 + j * 4, - 1, 1 + i * 4), EnumFacing.SOUTH, rand);
					} else if (rand.nextInt(10) == 0)
						if (rand.nextBoolean())
							world.setBlockState(pos.add(2 + j * 4, - 2, 2 + i * 4), ANTLION_SPAWNER);
						else
							world.setBlockState(pos.add(2 + j * 4, 2, 2 + i * 4), MAGMA_CRAWLER_SPAWNER);
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 8) == 0)
					if (rand.nextInt(25) == 0 && canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(1 + j * 4, - 1, 2 + i * 4))) {
						world.setBlockState(pos.add(1 + j * 4, 0, 2 + i * 4), TORCH_EAST, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, pos.add(1 + j * 4, - 1, 2 + i * 4), EnumFacing.EAST, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, pos.add(1 + j * 4, - 1, 2 + i * 4), EnumFacing.EAST, rand);
					}
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 4) == 0)
					if (rand.nextInt(25) == 0 && canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(3 + j * 4, - 1, 2 + i * 4))) {
						world.setBlockState(pos.add(3 + j * 4, 0, 2 + i * 4), TORCH_WEST, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, pos.add(3 + j * 4, - 1, 2 + i * 4), EnumFacing.WEST, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, pos.add(3 + j * 4, - 1, 2 + i * 4), EnumFacing.WEST, rand);
					}
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 2) == 0)
					if (rand.nextInt(25) == 0 && canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(2 + j * 4, - 1, 3 + i * 4))) {
						world.setBlockState(new BlockPos(2 + j * 4, 0, 3 + i * 4), TORCH_NORTH, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, pos.add(2 + j * 4, - 1, 3 + i * 4), EnumFacing.NORTH, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, pos.add(2 + j * 4, - 1, 3 + i * 4), EnumFacing.NORTH, rand);
					}
		}
	}

	private void placeChest(World world, BlockPos pos, EnumFacing facing, Random rand) {
		world.setBlockState(pos, Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, facing), 2);
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(pos);
		if (chest != null)
			LootUtil.generateLoot(chest, rand, chestLoot, 3, 10);
	}

	private void placeBones(World world, BlockPos pos, EnumFacing facing, Random rand) {
		world.setBlockState(pos, ModBlocks.BLOCK_OF_BONES.getDefaultState().withProperty(BlockBones.FACING, facing), 2);
		TileEntityBones bones = (TileEntityBones) world.getTileEntity(pos);
		if (bones != null)
			LootUtil.generateLoot(bones, rand, chestLoot, 3, 10);
			
	}

	private void buildLevel(World world, BlockPos pos, int w, int h, int[][] maze, IBlockState blockType) {
		for (int i = 0; i < h; i++) {
			// draw the north edge
			for (int j = 0; j < w; j++) {
				
				if ((maze[j][i] & 1) == 0) {
					if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(j * 4, 1, i * 4)))
						if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(j * 4 + 1, 0, i * 4)))
							if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(j * 4 + 2, 0, i * 4)))
								if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(j * 4 + 3, 0, i * 4))) {
									world.setBlockState(pos.add(j * 4, 0, i * 4), blockType, 2);
									world.setBlockState(pos.add(j * 4 + 1, 0, i * 4), blockType, 2);
									world.setBlockState(pos.add(j * 4 + 2, 0, i * 4), blockType, 2);
									world.setBlockState(pos.add(j * 4 + 3, 0, i * 4), blockType, 2);
								}
					}else if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(j * 4, 1, i * 4)))
						world.setBlockState(pos.add(j * 4, 0, i * 4), blockType, 2);
			
			}
			// draw the west edge
			for (int j = 0; j < w; j++) {
				
				if ((maze[j][i] & 8) == 0) {
					if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(j * 4, 0, i * 4 + 1)))
						if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(j * 4, 0, i * 4 + 2)))
							if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(j * 4, 0, i * 4 + 3))) {
					world.setBlockState(pos.add(j * 4, 0, i * 4 + 1), blockType, 2);
					world.setBlockState(pos.add(j * 4, 0, i * 4 + 2), blockType, 2);
					world.setBlockState(pos.add(j * 4, 0, i * 4 + 3), blockType, 2);
				}
				}
			}
			if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(w * 4, 0, i * 4))) 
				if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(w * 4, 0, i * 4 + 1)))
					if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(w * 4, 0, i * 4 + 2)))
						if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(w * 4, 0, i * 4 + 3))) {
						world.setBlockState(pos.add(w * 4, 0, i * 4), blockType,  2);
						world.setBlockState(pos.add(w * 4, 0, i * 4 + 1), blockType, 2);
						world.setBlockState(pos.add(w * 4, 0, i * 4 + 2), blockType, 2);
						world.setBlockState(pos.add(w * 4, 0, i * 4 + 3), blockType, 2);
			}
		}
		// draw the bottom line
		for (int j = 0; j <= w * 4; j++) {
			if (canPlaceFeatureAt(world, pos.add(60, 0, 60), pos.add(j * 4, 0, h * 4)))
			world.setBlockState(pos.add(j, 0, h * 4), blockType, 2);
		}
	}

	public static void breakForceField(World world, int x, int y, int z) {
		BlockPos pos = (new BlockPos(x, y, z));
		for (int d = 0; d < 4; d++) {
			for (int wx = 0 + d; wx < 9; wx++) {
				world.setBlockToAir(pos.add(11 + wx, d, 21));
				world.setBlockToAir(pos.add(11 + wx, d, 22));
				world.setBlockToAir(pos.add(21, d, 11 + wx));
				world.setBlockToAir(pos.add(22, d, 11 + wx));
				world.setBlockToAir(pos.add(21, d, 32 - wx));
				world.setBlockToAir(pos.add(22, d, 32 - wx));
				world.setBlockToAir(pos.add(32 - wx, d, 21));
				world.setBlockToAir(pos.add(32 - wx, d, 22));
			}
			for (int dx = 20; dx < 24; dx++)
				for (int dz = 20; dz < 24; dz++)
					if (!world.isAirBlock(pos.add(dx, d, dz))) {
						world.playEvent(null, 2001, pos.add(dx, d, dz), Block.getIdFromBlock(ModBlocks.FORCE_FIELD));
						world.setBlockToAir(pos.add(dx, d, dz));
					}
		}
		world.setBlockState(pos.add(20, 0, 20), BAMBOO_TORCH_LOWER, 3);
		world.setBlockState(pos.add(20, 1, 20), BAMBOO_TORCH_UPPER, 3);
		world.setBlockState(pos.add(20, 0, 23), BAMBOO_TORCH_LOWER, 3);
		world.setBlockState(pos.add(20, 1, 23), BAMBOO_TORCH_UPPER, 3);
		world.setBlockState(pos.add(23, 0, 23), BAMBOO_TORCH_LOWER, 3);
		world.setBlockState(pos.add(23, 1, 23), BAMBOO_TORCH_UPPER, 3);
		world.setBlockState(pos.add(23, 0, 20), BAMBOO_TORCH_LOWER, 3);
		world.setBlockState(pos.add(23, 1, 20), BAMBOO_TORCH_UPPER, 3);

		EntityAntlionBoss antlionboss = new EntityAntlionBoss(world);
		antlionboss.setPosition(x + 21, y - 8, z + 21);
		antlionboss.setInPyramid((byte) 1);
		antlionboss.setSpawnPoint(x + 21, y - 8, z + 21);
		world.spawnEntity(antlionboss);
	}

}
