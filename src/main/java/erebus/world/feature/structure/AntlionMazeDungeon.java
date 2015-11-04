package erebus.world.feature.structure;

import java.util.List;
import java.util.Random;

import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.entity.EntityAntlionBoss;
import erebus.entity.EntityUmberGolemDungeonTypes;
import erebus.item.ItemErebusFood.FoodType;
import erebus.item.ItemMaterials;
import erebus.item.ItemMaterials.DATA;
import erebus.item.ItemSmoothie.SmoothieType;
import erebus.tileentity.TileEntityBones;
import erebus.tileentity.TileEntityTempleTeleporter;
import erebus.world.feature.util.MazeGenerator;
import erebus.world.feature.util.PerfectMazeGenerator;
import erebus.world.loot.IPostProcess;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class AntlionMazeDungeon {
	private Block solid = ModBlocks.gneiss;
	public static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] { new LootItemStack(Items.book).setAmount(1, 4).setWeight(18), new LootItemStack(Items.paper).setAmount(2, 6).setWeight(16), new LootItemStack(Blocks.web).setAmount(2, 7).setWeight(13), new LootItemStack(ModItems.materials).setAmount(1, 3).setDamage(DATA.jade.ordinal()).setWeight(10), new LootItemStack(ModItems.materials).setAmount(4, 8).setDamage(DATA.plateExo.ordinal()).setWeight(9), new LootItemStack(Items.enchanted_book).setWeight(8), new LootItemStack(ModBlocks.umberGolemStatue).setAmount(1).setWeight(1), new LootItemStack(ModItems.webSlinger).setAmount(1).setWeight(1), new LootItemStack(Items.golden_pickaxe).setWeight(3), new LootItemStack(Items.iron_pickaxe).setWeight(2),
			new LootItemStack(ModItems.jadePickaxe).setWeight(1), new LootItemStack(Items.golden_shovel).setWeight(3), new LootItemStack(Items.iron_shovel).setWeight(2), new LootItemStack(ModItems.jadeShovel).setWeight(1), new LootItemStack(Items.golden_axe).setWeight(3), new LootItemStack(Items.iron_axe).setWeight(2), new LootItemStack(ModItems.jadeAxe).setWeight(1), new LootItemStack(Items.golden_sword).setWeight(3), new LootItemStack(Items.iron_sword).setWeight(2), new LootItemStack(ModItems.jadeSword).setWeight(1), new LootItemStack(Items.iron_chestplate).setWeight(2), new LootItemStack(ModItems.jadeBody).setWeight(1), new LootItemStack(Items.golden_chestplate).setWeight(1), new LootItemStack(Items.iron_helmet).setWeight(2), new LootItemStack(ModItems.jadeHelmet).setWeight(1),
			new LootItemStack(Items.golden_helmet).setWeight(1), new LootItemStack(Items.iron_leggings).setWeight(2), new LootItemStack(ModItems.jadeLegs).setWeight(1), new LootItemStack(Items.golden_leggings).setWeight(1), new LootItemStack(Items.iron_boots).setWeight(2), new LootItemStack(ModItems.jadeBoots).setWeight(1), new LootItemStack(Items.golden_boots).setWeight(1), new LootItemStack(ModItems.materials).setAmount(1).setDamage(DATA.altarFragment.ordinal()).setWeight(1), new LootItemStack(ModItems.materials).setAmount(1).setDamage(DATA.reinforcedPlateExo.ordinal()).setWeight(1), new LootItemStack(ModItems.materials).setAmount(1).setDamage(DATA.scorpionPincer.ordinal()).setWeight(1),
			new LootItemStack(ModItems.materials).setAmount(1, 3).setDamage(DATA.whetstonePowder.ordinal()).setWeight(3), new LootItemStack(ModItems.materials).setAmount(1).setDamage(DATA.plateExoRhino.ordinal()).setWeight(1), new LootItemStack(ModItems.food).setAmount(1, 3).setDamage(FoodType.HONEY_SANDWICH.ordinal()).setWeight(3), new LootItemStack(ModItems.cabbageSeeds).setAmount(1, 3).setWeight(2), new LootItemStack(ModItems.whetstone).setAmount(1).setDamage(0).setWeight(1), new LootItemStack(ModItems.lifeBlood).setAmount(1, 2).setWeight(4), new LootItemStack(ModItems.rolledNewspaper).setAmount(1).setWeight(1), new LootItemStack(ModItems.waspDagger).setAmount(1, 3).setWeight(2), new LootItemStack(ModItems.bucketAntiVenom).setAmount(1).setWeight(1),
			new LootItemStack(ModItems.bucketBeetleJuice).setAmount(1).setWeight(1), new LootItemStack(ModItems.bucketHoney).setAmount(1).setWeight(1), new LootItemStack(ModBlocks.glowGemBlock).setAmount(1, 3).setWeight(5), new LootItemStack(ModItems.homingBeecon).setAmount(1).setWeight(1), new LootItemStack(ModItems.smoothie).setAmount(1, 3).setDamage(SmoothieType.GIVIN_ME_THE_BLUES.ordinal()).setWeight(3), new LootItemStack(ModItems.smoothie).setAmount(1).setDamage(SmoothieType.BRYUFS_BREW.ordinal()).setWeight(1) }).setPostProcessor(new IPostProcess() {
				@SuppressWarnings("rawtypes")
				@Override
				public ItemStack postProcessItem(ItemStack is, Random rand) {
					if (is.getItem() == Items.enchanted_book || rand.nextBoolean() && (is.getItem() instanceof ItemTool || is.getItem() instanceof ItemArmor || is.getItem() instanceof ItemSword)) {
						boolean enchBook = is.getItem() == Items.enchanted_book;
						if (enchBook)
							is.func_150996_a(Items.book);
						List enchList = EnchantmentHelper.buildEnchantmentList(rand, is, 7 + rand.nextInt(10));
						if (enchBook)
							is.func_150996_a(Items.enchanted_book);
						if (enchList != null && enchList.size() > 0)
							for (int a = 0; a < enchList.size(); ++a) {
								EnchantmentData data = (EnchantmentData) enchList.get(a);
								if (is.getItem() == Items.enchanted_book)
									Items.enchanted_book.addEnchantment(is, data);
								else
									is.addEnchantment(data.enchantmentobj, data.enchantmentLevel);
							}
					}
					return is;
				}
			});

	public void generateSurface(World world, Random rand, int chunkX, int chunkY, int chunkZ) {
		BiomeGenBase biomeBase = world.getBiomeGenForCoords(chunkX, chunkZ);
		if (biomeBase == ModBiomes.volcanicDesert)
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
					buildLevel(world, x, yy - 4, z, mazeWidth, mazeHeight, maze, solid, 2);
					buildLevel(world, x, yy - 3, z, mazeWidth, mazeHeight, maze, solid, 1);
					buildLevel(world, x, yy - 2, z, mazeWidth, mazeHeight, maze, solid, 2);
					createAir(world, x, yy - 4, z, mazeWidth, mazeHeight, rand);
					addFeature(world, x, yy - 3, z, mazeWidth, mazeHeight, maze, rand);
					break;
			}
		buildCourtyard(world, ModBlocks.templePillar, 0, x + sizeX, y - 4, z + sizeZ, 52, 4, 52);
		createPyramid(world, ModBlocks.templeBrickUnbreaking, 0, true, x + sizeX / 2 + 8, z + sizeZ / 2 + 8, 44, 44, y - 6);
		decoratePyramid(world, x + sizeX / 2 + 8, y - 6, z + sizeZ / 2 + 8);
		addTeleporters(world, x + sizeX / 2 + 8, y - 6, z + sizeZ / 2 + 8);
		addCapstones(world, x + sizeX - 1, y + 15, z + sizeZ - 1, ModBlocks.capstone);
		spawnIdolGuardians(world, x, y, z);
		//	System.out.println("Generated Maze At: X: " + x + " Y: " + y + " Z: " + z);
	}

	private void createAir(World world, int x, int y, int z, int w, int h, Random rand) {
		for (int i = 0; i <= h * 4; i++)
			for (int j = 0; j <= w * 4; j++)
				for (int k = 0; k <= 2; k++)
					if (world.getBlock(x + j, y + k, z + i) != solid)
						world.setBlockToAir(x + j, y + k, z + i);
	}

	private void addTeleporters(World world, int x, int y, int z) {
		// room 1
		world.setBlock(x + 13, y + 9, z + 13, ModBlocks.capstone, 0, 2);
		setFloorDecoStone(world, x + 14, y + 9, z + 14);
		setLockStone(world, x + 15, y + 9, z + 15, 2);
		setTeleporter(world, x + 16, y + 9, z + 16, 0, x + 30, y + 9, z + 13);
		setTeleporter(world, x + 19, y + 9, z + 19, 5, x + 19, y + 14, z + 19);
		// room 2
		world.setBlock(x + 30, y + 9, z + 13, ModBlocks.capstone, 0, 2);
		setFloorDecoStone(world, x + 25, y + 9, z + 14);
		setLockStone(world, x + 26, y + 9, z + 15, 3);
		setTeleporter(world, x + 27, y + 9, z + 16, 0, x + 30, y + 9, z + 30);
		setTeleporter(world, x + 24, y + 9, z + 19, 5, x + 13, y + 9, z + 13);
		// room 3
		world.setBlock(x + 30, y + 9, z + 30, ModBlocks.capstone, 0, 2);
		setFloorDecoStone(world, x + 25, y + 9, z + 25);
		setLockStone(world, x + 26, y + 9, z + 26, 4);
		setTeleporter(world, x + 27, y + 9, z + 27, 0, x + 13, y + 9, z + 30);
		setTeleporter(world, x + 24, y + 9, z + 24, 5, x + 30, y + 9, z + 13);
		// room 4
		world.setBlock(x + 13, y + 9, z + 30, ModBlocks.capstone, 0, 2);
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
		world.setBlock(x + 19, y + 14, z + 19, ModBlocks.capstone, 0, 2);
		world.setBlock(x + 19, y + 15, z + 25, ModBlocks.bambooTorch, 0, 3);
		world.setBlock(x + 19, y + 16, z + 25, ModBlocks.bambooTorch, 1, 3);
		world.setBlock(x + 25, y + 15, z + 19, ModBlocks.bambooTorch, 0, 3);
		world.setBlock(x + 25, y + 16, z + 19, ModBlocks.bambooTorch, 1, 3);
		setFloorDecoStone(world, x + 20, y + 14, z + 20);
		setLockStone(world, x + 21, y + 14, z + 21, 1);
		setTeleporter(world, x + 22, y + 14, z + 22, 0, x + 13, y + 9, z + 13);
		setTeleporter(world, x + 25, y + 14, z + 25, 5, x + 19, y + 14, z + 19);
	}

	private void setLockStone(World world, int x, int y, int z, int meta) {
		for (int dx = x; dx < x + 3; dx++)
			for (int dz = z; dz < z + 3; dz++)
				world.setBlock(dx, y, dz, ModBlocks.templeBrickUnbreaking, meta, 2);
	}

	private void setFloorDecoStone(World world, int x, int y, int z) {
		for (int dx = x; dx < x + 5; dx++)
			for (int dz = z; dz < z + 5; dz++)
				world.setBlock(dx, y, dz, ModBlocks.capstone, 0, 2);
	}

	private static void setFloorMidDecoStone(World world, int x, int y, int z) {
		for (int dx = x; dx < x + 4; dx++)
			for (int dz = z; dz < z + 4; dz++)
				world.setBlock(dx, y, dz, ModBlocks.capstone, 0, 2);
	}

	private void decoratePyramid(World world, int x, int y, int z) {
		// create floors
		boolean forcefieldSet = false;
		boolean topchestSet = false;
		for (int yy = y; yy < y + 30; yy++)
			for (int xx = x; xx < x + 44; xx++)
				for (int zz = z; zz < z + 44; zz++) {
					if (yy == y)
						world.setBlock(xx, yy, zz, ModBlocks.templeBrickUnbreaking, 0, 2);
					if (yy == y + 1) {
						if (xx > x + 1 && xx < x + 42 && zz > z + 1 && zz < z + 42)
							world.setBlock(xx, yy, zz, Blocks.sand, 0, 2);
						if (xx > x + 4 && xx < x + 39 && zz > z + 4 && zz < z + 39)
							if ((xx - x) % 11 == 5 || (zz - z) % 11 == 5)
								world.setBlock(xx, yy, zz, ModBlocks.gneissVent, 0, 2);
							else
								world.setBlock(xx, yy, zz, Blocks.sand, 0, 2);
					}

					if (yy == y + 9)
						if (xx > x + 9 && xx < x + 34 && zz > z + 9 && zz < z + 34)
							world.setBlock(xx, yy, zz, ModBlocks.templeBrickUnbreaking, 0, 2);

					if (yy == y + 10 && !forcefieldSet) {
						for (int d = 0; d < 4; d++) {
							for (int wx = 0 + d; wx < 9; wx++) {
								world.setBlock(x + 11 + wx, yy + d, z + 21, ModBlocks.forceField, 0, 2);
								world.setBlock(x + 11 + wx, yy + d, z + 22, ModBlocks.forceField, 0, 2);
								world.setBlock(x + 21, yy + d, z + 11 + wx, ModBlocks.forceField, 0, 2);
								world.setBlock(x + 22, yy + d, z + 11 + wx, ModBlocks.forceField, 0, 2);
								world.setBlock(x + 21, yy + d, z + 32 - wx, ModBlocks.forceField, 0, 2);
								world.setBlock(x + 22, yy + d, z + 32 - wx, ModBlocks.forceField, 0, 2);
								world.setBlock(x + 32 - wx, yy + d, z + 21, ModBlocks.forceField, 0, 2);
								world.setBlock(x + 32 - wx, yy + d, z + 22, ModBlocks.forceField, 0, 2);
							}

							for (int dx = x + 20; dx < x + 24; dx++)
								for (int dz = z + 20; dz < z + 24; dz++)
									world.setBlock(dx, yy + d, dz, ModBlocks.forceField, 0, 2);

							for (int dx1 = x + 21; dx1 < x + 23; dx1++)
								for (int dz1 = z + 21; dz1 < z + 23; dz1++)
									world.setBlockToAir(dx1, yy + d, dz1);
						}
						forcefieldSet = true;
					}

					if (yy == y + 14)
						if (xx > x + 14 && xx < x + 29 && zz > z + 14 && zz < z + 29)
							world.setBlock(xx, yy, zz, ModBlocks.templeBrickUnbreaking, 0, 2);

					if (yy == y + 15 && !topchestSet) {
						// contents is 8 pieces of jade
						world.setBlock(x + 19, yy, z + 22, Blocks.chest, 2, 2);
						TileEntityChest chest = (TileEntityChest) world.getTileEntity(x + 19, yy, z + 22);
						if (chest != null)
							chest.setInventorySlotContents(0, ItemMaterials.DATA.jade.makeStack(8));
						world.setBlockMetadataWithNotify(x + 19, yy, z + 22, 2, 3);
					}

					if (yy == y + 16) {
						// TODO Lighting?
					}
				}
	}

	public static void setTeleporter(World world, int x, int y, int z, int metaData, int targetX, int targetY, int targetZ) {
		world.setBlock(x, y, z, ModBlocks.templeTeleporter, metaData, 2);
		TileEntityTempleTeleporter teleporter = (TileEntityTempleTeleporter) world.getTileEntity(x, y, z);
		if (teleporter != null)
			teleporter.setTargetDestination(targetX, targetY, targetZ);
	}

	private void addCapstones(World world, int x, int y, int z, Block capstone) {
		world.setBlock(x, y, z, capstone, 1, 3);
		world.setBlock(x + 1, y, z, capstone, 2, 3);
		world.setBlock(x, y, z + 1, capstone, 3, 3);
		world.setBlock(x + 1, y, z + 1, capstone, 4, 3);
	}

	private void createPyramid(World world, Block block, int metaData, boolean isHollow, int x, int z, int baseLengthX, int baseLengthZ, int yStart) {
		int yStop = Math.min((baseLengthZ - 1) / 2, (baseLengthX - 1) / 2) + yStart;
		for (int i = 0; i + yStart <= yStop - 1; i++) {
			int y = yStart + i;
			int maxX = x + baseLengthX - 1;
			int maxZ = z + baseLengthZ - 1;
			for (int ix = 0; x + ix + i <= maxX; ix++)
				for (int iz = 0; z + iz + i <= maxZ; iz++)
					if (ix == 0 || ix + i + 1 == baseLengthX || iz == 0 || iz + i + 1 == baseLengthZ)
						world.setBlock(ix + x + i, y, iz + z + i, block, metaData, 2);
					else if (isHollow)
						if (!world.isAirBlock(ix + x + i, y, iz + z + i))
							world.setBlockToAir(ix + x + i, y, iz + z + i);

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
						entityUmberGolem.setPosition(x + 118.5D, y - 3.0D, z + 118.5D);
						break;
					case 3:
						entityUmberGolem.setPosition(x + 2.5D, y - 3.0D, z + 118.5D);
						break;
				}
				world.spawnEntityInWorld(entityUmberGolem);
			}
	}

	private void buildCourtyard(World world, Block block, int metaData, int x, int y, int z, int baseLengthX, int heightY, int baseLengthZ) {
		for (int yy = y; yy <= heightY + y; yy++)
			for (int xx = x - baseLengthX / 2; xx < x + baseLengthX / 2; xx++)
				for (int zz = z - baseLengthZ / 2; zz < z + baseLengthZ / 2; zz++)
					if (yy > y)
						if (yy <= y + 4) {
							if (!world.isAirBlock(xx, yy, zz))
								world.setBlockToAir(xx, yy, zz);

							if (xx == x - baseLengthX / 2 || xx == x + baseLengthX / 2 - 1)
								if (zz > z - baseLengthZ / 2 && zz < z + baseLengthZ / 2) {
									if (yy <= y + 3)
										for (int i = 3; i < 49; i += 5)
											world.setBlock(xx, yy, z - baseLengthZ / 2 + i, block, metaData, 2);
									if (yy == y + 4)
										for (int i = 0; i < 52; i++)
											world.setBlock(xx, yy, z - baseLengthZ / 2 + i, ModBlocks.templeBrick, 0, 2);
								}

							if (zz == z - baseLengthZ / 2 || zz == z + baseLengthZ / 2 - 1)
								if (xx > x - baseLengthX / 2 && xx < x + baseLengthX / 2) {
									if (yy <= y + 3)
										for (int i = 3; i < 49; i += 5)
											world.setBlock(x - baseLengthZ / 2 + i, yy, zz, block, metaData, 2);
									if (yy == y + 4)
										for (int i = 0; i < 52; i++)
											world.setBlock(x - baseLengthZ / 2 + i, yy, zz, ModBlocks.templeBrick, 0, 2);
								}
						}
	}

	private void buildRoof(World world, int x, int y, int z, int w, int h, Random rand) {
		for (int i = 0; i <= h * 4; i++)
			for (int j = 0; j <= w * 4; j++)
				if (canPlaceFeatureAt(world, x, y, z, x + j, y, z + i))
					world.setBlock(x + j, y, z + i, solid, 3, 2);
	}

	private void buildFloor(World world, int x, int y, int z, int w, int h, Random rand) {
		createPyramid(world, Blocks.air, 0, true, x + 36, z + 36, 48, 48, y + 5);
		for (int i = 0; i <= h * 4; i++)
			for (int j = 0; j <= w * 4; j++)
				if (rand.nextInt(15) == 0)
					if (rand.nextBoolean() && rand.nextBoolean())
						world.setBlock(x + j, y, z + i, Blocks.lava);
					else
						world.setBlock(x + j, y, z + i, ModBlocks.gneissVent);
				else
					world.setBlock(x + j, y, z + i, solid, 5, 2);
	}

	private void addFeature(World world, int x, int y, int z, int w, int h, int[][] maze, Random rand) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 1) == 0)
					if (rand.nextInt(25) == 0 && canPlaceFeatureAt(world, x, y, z, x + 1 + j * 4, y - 1, z + 1 + i * 4)) {
						world.setBlock(x + 1 + j * 4, y, z + 1 + i * 4, Blocks.torch, 3, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, x + 1 + j * 4, y - 1, z + 1 + i * 4, 3, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, x + 1 + j * 4, y - 1, z + 1 + i * 4, 3, rand);
					} else if (rand.nextInt(10) == 0)
						if (rand.nextBoolean())
							world.setBlock(x + 2 + j * 4, y - 2, z + 2 + i * 4, ModBlocks.antlionSpawner);
						else
							world.setBlock(x + 2 + j * 4, y + 2, z + 2 + i * 4, ModBlocks.magmaCrawlerSpawner);
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 8) == 0)
					if (rand.nextInt(25) == 0 && canPlaceFeatureAt(world, x, y, z, x + 1 + j * 4, y - 1, z + 2 + i * 4)) {
						world.setBlock(x + 1 + j * 4, y, z + 2 + i * 4, Blocks.torch, 1, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, x + 1 + j * 4, y - 1, z + 2 + i * 4, 1, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, x + 1 + j * 4, y - 1, z + 2 + i * 4, 5, rand);
					}
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 4) == 0)
					if (rand.nextInt(25) == 0 && canPlaceFeatureAt(world, x, y, z, x + 3 + j * 4, y - 1, z + 2 + i * 4)) {
						world.setBlock(x + 3 + j * 4, y, z + 2 + i * 4, Blocks.torch, 2, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, x + 3 + j * 4, y - 1, z + 2 + i * 4, 2, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, x + 3 + j * 4, y - 1, z + 2 + i * 4, 4, rand);
					}
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 2) == 0)
					if (rand.nextInt(25) == 0 && canPlaceFeatureAt(world, x, y, z, x + 2 + j * 4, y - 1, z + 3 + i * 4)) {
						world.setBlock(x + 2 + j * 4, y, z + 3 + i * 4, Blocks.torch, 4, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, x + 2 + j * 4, y - 1, z + 3 + i * 4, 4, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, x + 2 + j * 4, y - 1, z + 3 + i * 4, 2, rand);
					}
		}
	}

	private void placeChest(World world, int x, int y, int z, int directionMeta, Random rand) {
		world.setBlock(x, y, z, Blocks.chest, directionMeta, 2);
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(x, y, z);
		if (chest != null)
			LootUtil.generateLoot(chest, rand, chestLoot, 3, 10);
	}

	private void placeBones(World world, int x, int y, int z, int directionMeta, Random rand) {
		world.setBlock(x, y, z, ModBlocks.bones, directionMeta, 2);
		TileEntityBones bones = (TileEntityBones) world.getTileEntity(x, y, z);
		if (bones != null)
			LootUtil.generateLoot(bones, rand, chestLoot, 3, 10);
	}

	private boolean canPlaceFeatureAt(World world, int x, int y, int z, int featureX, int featureY, int featureZ) {
		for (int xx = x + 34; xx < x + 86; xx++)
			for (int zz = z + 34; zz < z + 86; zz++)
				if (featureX == xx && featureZ == zz)
					return false;
		return true;
	}

	private void buildLevel(World world, int x, int y, int z, int w, int h, int[][] maze, Block blockType, int blockMeta) {
		for (int i = 0; i < h; i++) {
			// draw the north edge
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 1) == 0) {
					world.setBlock(x + j * 4, y, z + i * 4, blockType, blockMeta, 2);
					world.setBlock(x + j * 4 + 1, y, z + i * 4, blockType, blockMeta, 2);
					world.setBlock(x + j * 4 + 2, y, z + i * 4, blockType, blockMeta, 2);
					world.setBlock(x + j * 4 + 3, y, z + i * 4, blockType, blockMeta, 2);
				} else
					world.setBlock(x + j * 4, y, z + i * 4, blockType, blockMeta, 2);
			// draw the west edge
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 8) == 0) {
					world.setBlock(x + j * 4, y, z + i * 4 + 1, blockType, blockMeta, 2);
					world.setBlock(x + j * 4, y, z + i * 4 + 2, blockType, blockMeta, 2);
					world.setBlock(x + j * 4, y, z + i * 4 + 3, blockType, blockMeta, 2);
				}
			world.setBlock(x + w * 4, y, z + i * 4, blockType, blockMeta, 2);
			world.setBlock(x + w * 4, y, z + i * 4 + 1, blockType, blockMeta, 2);
			world.setBlock(x + w * 4, y, z + i * 4 + 2, blockType, blockMeta, 2);
			world.setBlock(x + w * 4, y, z + i * 4 + 3, blockType, blockMeta, 2);
		}
		// draw the bottom line
		for (int j = 0; j <= w * 4; j++)
			world.setBlock(x + j, y, z + h * 4, blockType, blockMeta, 2);
	}

	public static void breakForceField(World world, int x, int y, int z) {
		for (int d = 0; d < 4; d++) {
			for (int wx = 0 + d; wx < 9; wx++) {
				world.setBlockToAir(x + 11 + wx, y + d, z + 21);
				world.setBlockToAir(x + 11 + wx, y + d, z + 22);
				world.setBlockToAir(x + 21, y + d, z + 11 + wx);
				world.setBlockToAir(x + 22, y + d, z + 11 + wx);
				world.setBlockToAir(x + 21, y + d, z + 32 - wx);
				world.setBlockToAir(x + 22, y + d, z + 32 - wx);
				world.setBlockToAir(x + 32 - wx, y + d, z + 21);
				world.setBlockToAir(x + 32 - wx, y + d, z + 22);
			}
			for (int dx = x + 20; dx < x + 24; dx++)
				for (int dz = z + 20; dz < z + 24; dz++)
					if (!world.isAirBlock(dx, y + d, dz)) {
						world.playAuxSFXAtEntity(null, 2001, dx, y + d, dz, Block.getIdFromBlock(world.getBlock(dx, y + d, dz)));
						world.setBlockToAir(dx, y + d, dz);
					}
		}
		world.setBlock(x + 20, y, z + 20, ModBlocks.bambooTorch, 0, 3);
		world.setBlock(x + 20, y + 1, z + 20, ModBlocks.bambooTorch, 1, 3);
		world.setBlock(x + 20, y, z + 23, ModBlocks.bambooTorch, 0, 3);
		world.setBlock(x + 20, y + 1, z + 23, ModBlocks.bambooTorch, 1, 3);
		world.setBlock(x + 23, y, z + 23, ModBlocks.bambooTorch, 0, 3);
		world.setBlock(x + 23, y + 1, z + 23, ModBlocks.bambooTorch, 1, 3);
		world.setBlock(x + 23, y, z + 20, ModBlocks.bambooTorch, 0, 3);
		world.setBlock(x + 23, y + 1, z + 20, ModBlocks.bambooTorch, 1, 3);
		EntityAntlionBoss antlionboss = new EntityAntlionBoss(world);
		antlionboss.setPosition(x + 21, y - 8, z + 21);
		antlionboss.setInPyramid((byte) 1);
		antlionboss.setSpawnPoint(x + 21, y - 8, z + 21);
		world.spawnEntityInWorld(antlionboss);
	}
}
