package erebus.world.feature.structure;

import java.util.List;
import java.util.Random;

import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.block.bamboo.BlockBambooTorch;
import erebus.block.bamboo.BlockBambooTorch.EnumBlockTorchHalf;
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
import erebus.tileentity.TileEntityTempleTeleporter;
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
// 1691502390890771781
// X: 272 Y: 18 Z: 576 // 272 36 576
public class AntlionMazeDungeon {
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
	private IBlockState TORCH_EAST = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.EAST);
	private IBlockState TORCH_WEST = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.WEST);
	private IBlockState TORCH_SOUTH = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.SOUTH);
	private IBlockState TORCH_NORTH = Blocks.TORCH.getDefaultState().withProperty(BlockTorch.FACING, EnumFacing.NORTH);
	
	public static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] {
			new LootItemStack(Items.BOOK).setAmount(1, 4).setWeight(18),
			new LootItemStack(Items.PAPER).setAmount(2, 6).setWeight(16),
			new LootItemStack(Blocks.WEB).setAmount(2, 7).setWeight(13),
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(EnumErebusMaterialsType.JADE.ordinal()).setWeight(10),
			new LootItemStack(ModItems.MATERIALS).setAmount(4, 8).setDamage(EnumErebusMaterialsType.PLATE_EXO.ordinal()).setWeight(9),
			new LootItemStack(Items.ENCHANTED_BOOK).setWeight(8),
			//new LootItemStack(ModBlocks.umberGolemStatue).setAmount(1).setWeight(1),
			//new LootItemStack(ModItems.webSlinger).setAmount(1).setWeight(1),
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
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.REINFORCED_PLATE_EXO.ordinal())
					.setWeight(1),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.SCORPION_PINCER.ordinal()).setWeight(1),
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(EnumErebusMaterialsType.WHETSTONE_POWDER.ordinal())
					.setWeight(3),
			new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(EnumErebusMaterialsType.PLATE_EXO_RHINO.ordinal()).setWeight(1),
			new LootItemStack(ModItems.EREBUS_FOOD).setAmount(1, 3).setDamage(EnumFoodType.HONEY_SANDWICH.ordinal()).setWeight(3),
			new LootItemStack(ModItems.CABBAGE_SEEDS).setAmount(1, 3).setWeight(2),
			//new LootItemStack(ModItems.whetstone).setAmount(1).setDamage(0).setWeight(1),
			new LootItemStack(ModItems.LIFE_BLOOD).setAmount(1, 2).setWeight(4),
			/*new LootItemStack(ModItems.rolledNewspaper).setAmount(1).setWeight(1),
			new LootItemStack(ModItems.waspDagger).setAmount(1, 3).setWeight(2),
			new LootItemStack(ModItems.bucketAntiVenom).setAmount(1).setWeight(1),
			new LootItemStack(ModItems.bucketBeetleJuice).setAmount(1).setWeight(1),
			new LootItemStack(ModItems.bucketHoney).setAmount(1).setWeight(1),
			new LootItemStack(ModBlocks.glowGemBlock).setAmount(1, 3).setWeight(5),
			new LootItemStack(ModItems.homingBeecon).setAmount(1).setWeight(1),
			new LootItemStack(ModItems.smoothie).setAmount(1, 3).setDamage(SmoothieType.givinMeTheBlues.ordinal())
					.setWeight(3),
			new LootItemStack(ModItems.smoothie).setAmount(1).setDamage(SmoothieType.bryufsBrew.ordinal())
					.setWeight(1) */}).setPostProcessor(new IPostProcess() {
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
							return is;
						}
					});

	public void generateSurface(World world, Random rand, int chunkX, int chunkY, int chunkZ) {
		Biome biomeBase = world.getBiome(new BlockPos(chunkX, chunkY, chunkZ));
		if (biomeBase == ModBiomes.VOLCANIC_DESERT)
			if(world.isBlockLoaded(new BlockPos(chunkX, chunkY, chunkZ)) && world.getBlockState(new BlockPos(chunkX, chunkY, chunkZ)).getBlock() != ModBlocks.GNEISS)
				generate(world, rand, chunkX, chunkY, chunkZ);
	}

	public void generate(World world, Random rand, int x, int y, int z) {
		int sizeX = 60;
		int sizeY = y + 4;
		int sizeZ = 60;
		int mazeWidth = sizeX / 2;
		int mazeHeight = sizeZ / 2;

		if (mazeWidth < 2 || mazeHeight < 2 || sizeY < 1)
			return;

		int[][] maze = null;
		MazeGenerator generator = new PerfectMazeGenerator(mazeWidth, mazeHeight);
		maze = generator.generateMaze();

		for (int yy = y; yy < sizeY; yy++)
			switch ((yy - y) % 4) {
				case 0:
					buildFloor(world, x, yy - 4, z, mazeWidth, mazeHeight, rand);
					buildRoof(world, x, yy, z, mazeWidth, mazeHeight, rand);
					break;
				case 1:
					buildLevel(world, x, yy - 4, z, mazeWidth, mazeHeight, maze, GNEISS_RELIEF);
					buildLevel(world, x, yy - 3, z, mazeWidth, mazeHeight, maze, GNEISS_CARVED);
					buildLevel(world, x, yy - 2, z, mazeWidth, mazeHeight, maze, GNEISS_RELIEF);
					createAir(world, x, yy - 4, z, mazeWidth, mazeHeight, rand);
					addFeature(world, x, yy - 3, z, mazeWidth, mazeHeight, maze, rand);
					break;
			}
		buildCourtyard(world, TEMPLE_PILLAR, x + sizeX, y - 4, z + sizeZ, 52, 4, 52);
		createPyramid(world, TEMPLE_BRICK_UNBREAKING, true, x + sizeX / 2 + 8, z + sizeZ / 2 + 8, 44, 44, y - 6);
		decoratePyramid(world, x + sizeX / 2 + 8, y - 6, z + sizeZ / 2 + 8);
		addTeleporters(world, x + sizeX / 2 + 8, y - 6, z + sizeZ / 2 + 8);
		addCapstones(world, x + sizeX - 1, y + 15, z + sizeZ - 1);
		spawnIdolGuardians(world, x, y, z);
		System.out.println("Generated Maze At: X: " + x + " Y: " + y + " Z: " + z);
	}

	private void createAir(World world, int x, int y, int z, int w, int h, Random rand) {
		for (int i = 0; i <= h * 4; i++)
			for (int j = 0; j <= w * 4; j++)
				for (int k = 0; k <= 2; k++)
					if (world.getBlockState(new BlockPos(x + j, y + k, z + i)).getBlock() != ModBlocks.GNEISS)
						world.setBlockToAir(new BlockPos(x + j, y + k, z + i));
	}

	private void addTeleporters(World world, int x, int y, int z) {
		// room 1
		world.setBlockState(new BlockPos(x + 13, y + 9, z + 13), CAPSTONE, 2);
		setFloorDecoStone(world, x + 14, y + 9, z + 14);
		setLockStone(world, x + 15, y + 9, z + 15, 2);
		setTeleporter(world, x + 16, y + 9, z + 16, 0, x + 30, y + 9, z + 13);
		setTeleporter(world, x + 19, y + 9, z + 19, 5, x + 19, y + 14, z + 19);
		// room 2
		world.setBlockState(new BlockPos(x + 30, y + 9, z + 13), CAPSTONE, 2);
		setFloorDecoStone(world, x + 25, y + 9, z + 14);
		setLockStone(world, x + 26, y + 9, z + 15, 3);
		setTeleporter(world, x + 27, y + 9, z + 16, 0, x + 30, y + 9, z + 30);
		setTeleporter(world, x + 24, y + 9, z + 19, 5, x + 13, y + 9, z + 13);
		// room 3
		world.setBlockState(new BlockPos(x + 30, y + 9, z + 30), CAPSTONE, 2);
		setFloorDecoStone(world, x + 25, y + 9, z + 25);
		setLockStone(world, x + 26, y + 9, z + 26, 4);
		setTeleporter(world, x + 27, y + 9, z + 27, 0, x + 13, y + 9, z + 30);
		setTeleporter(world, x + 24, y + 9, z + 24, 5, x + 30, y + 9, z + 13);
		// room 4
		world.setBlockState(new BlockPos(x + 13, y + 9, z + 30), CAPSTONE, 2);
		setFloorDecoStone(world, x + 14, y + 9, z + 25);
		setLockStone(world, x + 15, y + 9, z + 26, 5);
		setTeleporter(world, x + 16, y + 9, z + 27, 0, x + 13, y + 9, z + 13);
		setTeleporter(world, x + 19, y + 9, z + 24, 5, x + 30, y + 9, z + 30);
		// centre of pyramid - these teleport you to the boss arena (1 of the 4 corners)
		setFloorMidDecoStone(world, x + 20, y + 9, z + 20);
		setTeleporter(world, x + 22, y + 9, z + 21, 6, x + 5, y + 1, z + 5);
		setTeleporter(world, x + 21, y + 9, z + 21, 7, x + 38, y + 1, z + 5);
		setTeleporter(world, x + 22, y + 9, z + 22, 8, x + 38, y + 1, z + 38);
		setTeleporter(world, x + 21, y + 9, z + 22, 9, x + 5, y + 1, z + 38);
		// Top level
		world.setBlockState(new BlockPos(x + 19, y + 14, z + 19), CAPSTONE, 2);
		world.setBlockState(new BlockPos(x + 19, y + 15, z + 25), BAMBOO_TORCH_LOWER, 3);
		world.setBlockState(new BlockPos(x + 19, y + 16, z + 25), BAMBOO_TORCH_UPPER, 3);
		world.setBlockState(new BlockPos(x + 25, y + 15, z + 19), BAMBOO_TORCH_LOWER, 3);
		world.setBlockState(new BlockPos(x + 25, y + 16, z + 19), BAMBOO_TORCH_UPPER, 3);
		setFloorDecoStone(world, x + 20, y + 14, z + 20);
		setLockStone(world, x + 21, y + 14, z + 21, 1);
		setTeleporter(world, x + 22, y + 14, z + 22, 0, x + 13, y + 9, z + 13);
		setTeleporter(world, x + 25, y + 14, z + 25, 5, x + 19, y + 14, z + 19);
	}

	private void setLockStone(World world, int x, int y, int z, int meta) {
		for (int dx = x; dx < x + 3; dx++)
			for (int dz = z; dz < z + 3; dz++)
				world.setBlockState(new BlockPos(dx, y, dz), ModBlocks.TEMPLE_BRICK_UNBREAKING.getDefaultState().withProperty(BlockTempleBrickUnbreaking.TYPE, EnumTempleBrickType.values()[meta]), 2);
	}

	private void setFloorDecoStone(World world, int x, int y, int z) {
		for (int dx = x; dx < x + 5; dx++)
			for (int dz = z; dz < z + 5; dz++)
				world.setBlockState(new BlockPos(dx, y, dz), CAPSTONE, 2);
	}

	private void setFloorMidDecoStone(World world, int x, int y, int z) {
		for (int dx = x; dx < x + 4; dx++)
			for (int dz = z; dz < z + 4; dz++)
				world.setBlockState(new BlockPos(dx, y, dz), CAPSTONE, 2);
	}

	private void decoratePyramid(World world, int x, int y, int z) {
		// create floors
		boolean forcefieldSet = false;
		boolean topchestSet = false;
		for (int yy = y; yy < y + 30; yy++)
			for (int xx = x; xx < x + 44; xx++)
				for (int zz = z; zz < z + 44; zz++) {
					if (yy == y)
						world.setBlockState(new BlockPos(xx, yy, zz), TEMPLE_BRICK_UNBREAKING, 2);
					if (yy == y + 1) {
						if (xx > x + 1 && xx < x + 42 && zz > z + 1 && zz < z + 42)
							world.setBlockState(new BlockPos(xx, yy, zz), Blocks.SAND.getDefaultState(), 2);
						if (xx > x + 4 && xx < x + 39 && zz > z + 4 && zz < z + 39)
							if ((xx - x) % 11 == 5 || (zz - z) % 11 == 5)
								world.setBlockState(new BlockPos(xx, yy, zz), GNEISS_VENT, 2);
							else
								world.setBlockState(new BlockPos(xx, yy, zz), Blocks.SAND.getDefaultState(), 2);
					}

					if (yy == y + 9)
						if (xx > x + 9 && xx < x + 34 && zz > z + 9 && zz < z + 34)
							world.setBlockState(new BlockPos(xx, yy, zz), TEMPLE_BRICK_UNBREAKING, 2);

					if (yy == y + 10 && !forcefieldSet) {
						for (int d = 0; d < 4; d++) {
							for (int wx = 0 + d; wx < 9; wx++) {
								world.setBlockState(new BlockPos(x + 11 + wx, yy + d, z + 21), FORCE_FIELD, 2);
								world.setBlockState(new BlockPos(x + 11 + wx, yy + d, z + 22), FORCE_FIELD, 2);
								world.setBlockState(new BlockPos(x + 21, yy + d, z + 11 + wx), FORCE_FIELD, 2);
								world.setBlockState(new BlockPos(x + 22, yy + d, z + 11 + wx), FORCE_FIELD, 2);
								world.setBlockState(new BlockPos(x + 21, yy + d, z + 32 - wx), FORCE_FIELD, 2);
								world.setBlockState(new BlockPos(x + 22, yy + d, z + 32 - wx), FORCE_FIELD, 2);
								world.setBlockState(new BlockPos(x + 32 - wx, yy + d, z + 21), FORCE_FIELD, 2);
								world.setBlockState(new BlockPos(x + 32 - wx, yy + d, z + 22), FORCE_FIELD, 2);
							}

							for (int dx = x + 20; dx < x + 24; dx++)
								for (int dz = z + 20; dz < z + 24; dz++)
									world.setBlockState(new BlockPos(dx, yy + d, dz), FORCE_FIELD, 2);

							for (int dx1 = x + 21; dx1 < x + 23; dx1++)
								for (int dz1 = z + 21; dz1 < z + 23; dz1++)
									world.setBlockToAir(new BlockPos(dx1, yy + d, dz1));
						}
						forcefieldSet = true;
					}
					if (yy == y + 14)
						if (xx > x + 14 && xx < x + 29 && zz > z + 14 && zz < z + 29)
							world.setBlockState(new BlockPos(xx, yy, zz), TEMPLE_BRICK_UNBREAKING, 2);

					if (yy == y + 15 && !topchestSet) {
						// contents is 8 pieces of jade
						world.setBlockState(new BlockPos(x + 19, yy, z + 22), Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.NORTH), 2);
						TileEntityChest chest = (TileEntityChest) world.getTileEntity(new BlockPos(x + 19, yy, z + 22));
						if (chest != null) {
							chest.setInventorySlotContents(0, ItemMaterials.EnumErebusMaterialsType.JADE.createStack(8));
							chest.markDirty();
						}
					}
					if (yy == y + 16) {
						// TODO Lighting?
					}
				}
	}

	public static void setTeleporter(World world, int x, int y, int z, int meta, int targetX, int targetY, int targetZ) {
		world.setBlockState(new BlockPos(x, y, z), ModBlocks.TEMPLE_TELEPORTER.getDefaultState().withProperty(BlockTempleTeleporter.TYPE, EnumTeleporterType.values()[meta]), 2);
		TileEntityTempleTeleporter teleporter = (TileEntityTempleTeleporter) world.getTileEntity(new BlockPos(x, y, z));
		if (teleporter != null)
			teleporter.setTargetDestination(targetX, targetY, targetZ);
	}

	private void addCapstones(World world, int x, int y, int z) {
		world.setBlockState(new BlockPos(x, y, z), CAPSTONE.withProperty(BlockCapstone.TYPE, EnumCapstoneType.CAPSTONE_MUD), 3);
		world.setBlockState(new BlockPos(x + 1, y, z), CAPSTONE.withProperty(BlockCapstone.TYPE, EnumCapstoneType.CAPSTONE_IRON), 3);
		world.setBlockState(new BlockPos(x, y, z + 1), CAPSTONE.withProperty(BlockCapstone.TYPE, EnumCapstoneType.CAPSTONE_GOLD), 3);
		world.setBlockState(new BlockPos(x + 1, y, z + 1), CAPSTONE.withProperty(BlockCapstone.TYPE, EnumCapstoneType.CAPSTONE_JADE), 3);
	}

	private void createPyramid(World world, IBlockState block, boolean isHollow, int x, int z, int baseLengthX, int baseLengthZ, int yStart) {
		int yStop = Math.min((baseLengthZ - 1) / 2, (baseLengthX - 1) / 2) + yStart;
		for (int i = 0; i + yStart <= yStop - 1; i++) {
			int y = yStart + i;
			int maxX = x + baseLengthX - 1;
			int maxZ = z + baseLengthZ - 1;
			for (int ix = 0; x + ix + i <= maxX; ix++)
				for (int iz = 0; z + iz + i <= maxZ; iz++)
					if (ix == 0 || ix + i + 1 == baseLengthX || iz == 0 || iz + i + 1 == baseLengthZ)
						world.setBlockState(new BlockPos(ix + x + i, y, iz + z + i), block, 2);
					else if (isHollow)
						if (!world.isAirBlock(new BlockPos(ix + x + i, y, iz + z + i)))
							world.setBlockToAir(new BlockPos(ix + x + i, y, iz + z + i));

			baseLengthX--;
			baseLengthZ--;
		}
	}

	private void spawnIdolGuardians(World world, int x, int y, int z) {
		if (!world.isRemote)
			for (byte spawn = 0; spawn < 4; spawn++) {
				EntityUmberGolemDungeonTypes entityUmberGolem;
				entityUmberGolem = new EntityUmberGolemDungeonTypes(world);
				entityUmberGolem.setType(spawn);
				entityUmberGolem.setHealth(entityUmberGolem.getMaxHealth());
				switch (spawn) {
					case 0:
						entityUmberGolem.setPosition(x + 2.5D, y - 3.0D, z + 2.5D);
						break;
					case 1:
						entityUmberGolem.setPosition(x + 118.5D, y - 3.0D, z + 2.5D);
						break;
					case 2:
						entityUmberGolem.setPosition(x + 2.5D, y - 3.0D, z + 118.5D);
						break;
					case 3:
						entityUmberGolem.setPosition(x + 118.5D, y - 3.0D, z + 118.5D);
						break;
				}
				world.spawnEntity(entityUmberGolem);
			}
	}

	private void buildCourtyard(World world, IBlockState block, int x, int y, int z, int baseLengthX, int heightY, int baseLengthZ) {
		for (int yy = y; yy <= heightY + y; yy++)
			for (int xx = x - baseLengthX / 2; xx < x + baseLengthX / 2; xx++)
				for (int zz = z - baseLengthZ / 2; zz < z + baseLengthZ / 2; zz++)
					if (yy > y)
						if (yy <= y + 4) {
							if (!world.isAirBlock(new BlockPos(xx, yy, zz)))
								world.setBlockToAir(new BlockPos(xx, yy, zz));

							if (xx == x - baseLengthX / 2 || xx == x + baseLengthX / 2 - 1)
								if (zz > z - baseLengthZ / 2 && zz < z + baseLengthZ / 2) {
									if (yy <= y + 3)
										for (int i = 3; i < 49; i += 5)
											world.setBlockState(new BlockPos(xx, yy, z - baseLengthZ / 2 + i), block, 2);
									if (yy == y + 4)
										for (int i = 0; i < 52; i++)
											world.setBlockState(new BlockPos(xx, yy, z - baseLengthZ / 2 + i), TEMPLE_BRICK, 2);
								}

							if (zz == z - baseLengthZ / 2 || zz == z + baseLengthZ / 2 - 1)
								if (xx > x - baseLengthX / 2 && xx < x + baseLengthX / 2) {
									if (yy <= y + 3)
										for (int i = 3; i < 49; i += 5)
											world.setBlockState(new BlockPos(x - baseLengthZ / 2 + i, yy, zz), block, 2);
									if (yy == y + 4)
										for (int i = 0; i < 52; i++)
											world.setBlockState(new BlockPos(x - baseLengthZ / 2 + i, yy, zz), TEMPLE_BRICK, 2);
								}
						}
	}

	private void buildRoof(World world, int x, int y, int z, int w, int h, Random rand) {
		for (int i = 0; i <= h * 4; i++)
			for (int j = 0; j <= w * 4; j++)
				if (canPlaceFeatureAt(world, x, y, z, x + j, y, z + i))
					world.setBlockState(new BlockPos(x + j, y, z + i), GNEISS_BRICKS, 2);
	}

	private void buildFloor(World world, int x, int y, int z, int w, int h, Random rand) {
		createPyramid(world, Blocks.AIR.getDefaultState(), true, x + 36, z + 36, 48, 48, y + 5);
		for (int i = 0; i <= h * 4; i++)
			for (int j = 0; j <= w * 4; j++)
				if (rand.nextInt(15) == 0)
					if (rand.nextBoolean() && rand.nextBoolean())
						world.setBlockState(new BlockPos(x + j, y, z + i), Blocks.LAVA.getDefaultState());
					else
						world.setBlockState(new BlockPos(x + j, y, z + i), GNEISS_VENT);
				else
					world.setBlockState(new BlockPos(x + j, y, z + i), GNEISS_TILES, 2);
	}

	private void addFeature(World world, int x, int y, int z, int w, int h, int[][] maze, Random rand) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 1) == 0)
					if (rand.nextInt(25) == 0 && canPlaceFeatureAt(world, x, y, z, x + 1 + j * 4, y - 1, z + 1 + i * 4)) {
						world.setBlockState(new BlockPos(x + 1 + j * 4, y, z + 1 + i * 4), TORCH_SOUTH, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, x + 1 + j * 4, y - 1, z + 1 + i * 4, EnumFacing.SOUTH, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, x + 1 + j * 4, y - 1, z + 1 + i * 4, 3, rand);
					} else if (rand.nextInt(10) == 0)
						if (rand.nextBoolean())
							world.setBlockState(new BlockPos(x + 2 + j * 4, y - 2, z + 2 + i * 4), ANTLION_SPAWNER);
						else
							world.setBlockState(new BlockPos(x + 2 + j * 4, y + 2, z + 2 + i * 4), ModBlocks.ZOMBIE_ANT_SPAWNER.getDefaultState());//ModBlocks.magmaCrawlerSpawner
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 8) == 0)
					if (rand.nextInt(25) == 0 && canPlaceFeatureAt(world, x, y, z, x + 1 + j * 4, y - 1, z + 2 + i * 4)) {
						world.setBlockState(new BlockPos(x + 1 + j * 4, y, z + 2 + i * 4), TORCH_EAST, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, x + 1 + j * 4, y - 1, z + 2 + i * 4, EnumFacing.EAST, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, x + 1 + j * 4, y - 1, z + 2 + i * 4, 5, rand);
					}
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 4) == 0)
					if (rand.nextInt(25) == 0 && canPlaceFeatureAt(world, x, y, z, x + 3 + j * 4, y - 1, z + 2 + i * 4)) {
						world.setBlockState(new BlockPos(x + 3 + j * 4, y, z + 2 + i * 4), TORCH_WEST, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, x + 3 + j * 4, y - 1, z + 2 + i * 4, EnumFacing.WEST, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, x + 3 + j * 4, y - 1, z + 2 + i * 4, 4, rand);
					}
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 2) == 0)
					if (rand.nextInt(25) == 0 && canPlaceFeatureAt(world, x, y, z, x + 2 + j * 4, y - 1, z + 3 + i * 4)) {
						world.setBlockState(new BlockPos(x + 2 + j * 4, y, z + 3 + i * 4), TORCH_NORTH, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, x + 2 + j * 4, y - 1, z + 3 + i * 4, EnumFacing.NORTH, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, x + 2 + j * 4, y - 1, z + 3 + i * 4, 2, rand);
					}
		}
	}

	private void placeChest(World world, int x, int y, int z, EnumFacing facing, Random rand) {
		world.setBlockState(new BlockPos(x, y, z), Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, facing), 2);
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(new BlockPos(x, y, z));
		if (chest != null)
			LootUtil.generateLoot(chest, rand, chestLoot, 3, 10);
	}

	private void placeBones(World world, int x, int y, int z, int directionMeta, Random rand) {
		/*world.setBlock(x, y, z, ModBlocks.bones, directionMeta, 2);
		TileEntityBones bones = (TileEntityBones) world.getTileEntity(x, y, z);
		if (bones != null)
			LootUtil.generateLoot(bones, rand, chestLoot, 3, 10);
			*/
	}

	private boolean canPlaceFeatureAt(World world, int x, int y, int z, int featureX, int featureY, int featureZ) {
		for (int xx = x + 34; xx < x + 86; xx++)
			for (int zz = z + 34; zz < z + 86; zz++)
				if (featureX == xx && featureZ == zz)
					return false;
		return true;
	}

	private void buildLevel(World world, int x, int y, int z, int w, int h, int[][] maze, IBlockState blockType) {
		for (int i = 0; i < h; i++) {
			// draw the north edge
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 1) == 0) {
					world.setBlockState(new BlockPos(x + j * 4, y, z + i * 4), blockType, 2);
					world.setBlockState(new BlockPos(x + j * 4 + 1, y, z + i * 4), blockType, 2);
					world.setBlockState(new BlockPos(x + j * 4 + 2, y, z + i * 4), blockType, 2);
					world.setBlockState(new BlockPos(x + j * 4 + 3, y, z + i * 4), blockType, 2);
				} else
					world.setBlockState(new BlockPos(x + j * 4, y, z + i * 4), blockType, 2);
			// draw the west edge
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 8) == 0) {
					world.setBlockState(new BlockPos(x + j * 4, y, z + i * 4 + 1), blockType, 2);
					world.setBlockState(new BlockPos(x + j * 4, y, z + i * 4 + 2), blockType, 2);
					world.setBlockState(new BlockPos(x + j * 4, y, z + i * 4 + 3), blockType, 2);
				}
			world.setBlockState(new BlockPos(x + w * 4, y, z + i * 4), blockType,  2);
			world.setBlockState(new BlockPos(x + w * 4, y, z + i * 4 + 1), blockType, 2);
			world.setBlockState(new BlockPos(x + w * 4, y, z + i * 4 + 2), blockType, 2);
			world.setBlockState(new BlockPos(x + w * 4, y, z + i * 4 + 3), blockType, 2);
		}
		// draw the bottom line
		for (int j = 0; j <= w * 4; j++)
			world.setBlockState(new BlockPos(x + j, y, z + h * 4), blockType, 2);
	}

	public static void breakForceField(World world, int x, int y, int z) {
		for (int d = 0; d < 4; d++) {
			for (int wx = 0 + d; wx < 9; wx++) {
				world.setBlockToAir(new BlockPos(x + 11 + wx, y + d, z + 21));
				world.setBlockToAir(new BlockPos(x + 11 + wx, y + d, z + 22));
				world.setBlockToAir(new BlockPos(x + 21, y + d, z + 11 + wx));
				world.setBlockToAir(new BlockPos(x + 22, y + d, z + 11 + wx));
				world.setBlockToAir(new BlockPos(x + 21, y + d, z + 32 - wx));
				world.setBlockToAir(new BlockPos(x + 22, y + d, z + 32 - wx));
				world.setBlockToAir(new BlockPos(x + 32 - wx, y + d, z + 21));
				world.setBlockToAir(new BlockPos(x + 32 - wx, y + d, z + 22));
			}
			for (int dx = x + 20; dx < x + 24; dx++)
				for (int dz = z + 20; dz < z + 24; dz++)
					if (!world.isAirBlock(new BlockPos(dx, y + d, dz))) {
						world.playEvent(null, 2001, new BlockPos(dx, y + d, dz), Block.getIdFromBlock(ModBlocks.FORCE_FIELD));
						world.setBlockToAir(new BlockPos(dx, y + d, dz));
					}
		}
		world.setBlockState(new BlockPos(x + 20, y, z + 20), BAMBOO_TORCH_LOWER, 3);
		world.setBlockState(new BlockPos(x + 20, y + 1, z + 20), BAMBOO_TORCH_UPPER, 3);
		world.setBlockState(new BlockPos(x + 20, y, z + 23), BAMBOO_TORCH_LOWER, 3);
		world.setBlockState(new BlockPos(x + 20, y + 1, z + 23), BAMBOO_TORCH_UPPER, 3);
		world.setBlockState(new BlockPos(x + 23, y, z + 23), BAMBOO_TORCH_LOWER, 3);
		world.setBlockState(new BlockPos(x + 23, y + 1, z + 23), BAMBOO_TORCH_UPPER, 3);
		world.setBlockState(new BlockPos(x + 23, y, z + 20), BAMBOO_TORCH_LOWER, 3);
		world.setBlockState(new BlockPos(x + 23, y + 1, z + 20), BAMBOO_TORCH_UPPER, 3);

		EntityAntlionBoss antlionboss = new EntityAntlionBoss(world);
		antlionboss.setPosition(x + 21, y - 8, z + 21);
		antlionboss.setInPyramid((byte) 1);
		antlionboss.setSpawnPoint(x + 21, y - 8, z + 21);
		world.spawnEntity(antlionboss);
	}
}
