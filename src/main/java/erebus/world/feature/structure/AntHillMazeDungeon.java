package erebus.world.feature.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.util.ForgeDirection;
import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.core.helper.Utils;
import erebus.item.ItemErebusFood.FoodType;
import erebus.item.ItemMaterials;
import erebus.item.ItemMaterials.DATA;
import erebus.item.ItemSmoothie.SmoothieType;
import erebus.tileentity.TileEntityBones;
import erebus.tileentity.TileEntitySlidingBlockPuzzle;
import erebus.tileentity.TileEntitySlidingBlockPuzzle.SlidingPuzzle;
import erebus.world.feature.util.MazeGenerator;
import erebus.world.feature.util.PerfectMazeGenerator;
import erebus.world.loot.IPostProcess;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;

public class AntHillMazeDungeon {

	// TODO not all floors should be mazes. some more open ones with puzzles and maybe mini-boss fights.
	// TODO change loot chest contents to be more up to date with new mod additions.

	private Block solid = ModBlocks.anthillBlock;
	private Block stairs = ModBlocks.anthillStairs;
	private List<IInventory> chests = new ArrayList<IInventory>();

	private static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] { new LootItemStack(Items.book).setAmount(1, 4).setWeight(18), new LootItemStack(Items.paper).setAmount(2, 6).setWeight(16), new LootItemStack(Blocks.web).setAmount(2, 7).setWeight(13), new LootItemStack(ModItems.materials).setAmount(1, 3).setDamage(DATA.JADE.ordinal()).setWeight(10), new LootItemStack(ModItems.materials).setAmount(4, 8).setDamage(DATA.PLATE_EXO.ordinal()).setWeight(9), new LootItemStack(Items.enchanted_book).setWeight(8), new LootItemStack(ModBlocks.umberGolemStatue).setAmount(1).setWeight(1), new LootItemStack(ModItems.webSlinger).setAmount(1).setWeight(1), new LootItemStack(Items.golden_pickaxe).setWeight(3), new LootItemStack(Items.iron_pickaxe).setWeight(2),
			new LootItemStack(ModItems.jadePickaxe).setWeight(1), new LootItemStack(Items.golden_shovel).setWeight(3), new LootItemStack(Items.iron_shovel).setWeight(2), new LootItemStack(ModItems.jadeShovel).setWeight(1), new LootItemStack(Items.golden_axe).setWeight(3), new LootItemStack(Items.iron_axe).setWeight(2), new LootItemStack(ModItems.jadeAxe).setWeight(1), new LootItemStack(Items.golden_sword).setWeight(3), new LootItemStack(Items.iron_sword).setWeight(2), new LootItemStack(ModItems.jadeSword).setWeight(1), new LootItemStack(Items.iron_chestplate).setWeight(2), new LootItemStack(ModItems.jadeBody).setWeight(1), new LootItemStack(Items.golden_chestplate).setWeight(1), new LootItemStack(Items.iron_helmet).setWeight(2), new LootItemStack(ModItems.jadeHelmet).setWeight(1),
			new LootItemStack(Items.golden_helmet).setWeight(1), new LootItemStack(Items.iron_leggings).setWeight(2), new LootItemStack(ModItems.jadeLegs).setWeight(1), new LootItemStack(Items.golden_leggings).setWeight(1), new LootItemStack(Items.iron_boots).setWeight(2), new LootItemStack(ModItems.jadeBoots).setWeight(1), new LootItemStack(Items.golden_boots).setWeight(1), new LootItemStack(ModItems.materials).setAmount(1).setDamage(DATA.ALTAR_FRAGMENT.ordinal()).setWeight(1), new LootItemStack(ModItems.materials).setAmount(1).setDamage(DATA.REINFORCED_PLATE_EXO.ordinal()).setWeight(1), new LootItemStack(ModItems.materials).setAmount(1).setDamage(DATA.SCORPION_PINCER.ordinal()).setWeight(1),
			new LootItemStack(ModItems.materials).setAmount(1, 3).setDamage(DATA.WHETSTONE_POWDER.ordinal()).setWeight(3), new LootItemStack(ModItems.materials).setAmount(1).setDamage(DATA.PLATE_EXO_RHINO.ordinal()).setWeight(1), new LootItemStack(ModItems.food).setAmount(1, 3).setDamage(FoodType.HONEY_SANDWICH.ordinal()).setWeight(3), new LootItemStack(ModItems.cabbageSeeds).setAmount(1, 3).setWeight(2), new LootItemStack(ModItems.whetstone).setAmount(1).setDamage(0).setWeight(1), new LootItemStack(ModItems.lifeBlood).setAmount(1, 2).setWeight(4), new LootItemStack(ModItems.rolledNewspaper).setAmount(1).setWeight(1), new LootItemStack(ModItems.waspDagger).setAmount(1, 3).setWeight(2), new LootItemStack(ModItems.bucketAntiVenom).setAmount(1).setWeight(1),
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

	private void generateSurface(World world, Random rand, int chunkX, int chunkY, int chunkZ, boolean hasKey) {
		BiomeGenBase biomeBase = world.getBiomeGenForCoords(chunkX, chunkZ);
		if (biomeBase == ModBiomes.fungalForest)
			generate(world, rand, chunkX, chunkY, chunkZ, hasKey);
	}

	private void generate(World world, Random rand, int x, int y, int z, boolean hasKey) {
		int sizeX = 16;
		int sizeY = y + 5;
		int sizeZ = 16;
		int mazeWidth = sizeX / 2;
		int mazeHeight = sizeZ / 2;

		if (mazeWidth < 2 || mazeHeight < 2 || sizeY < 1)
			return;

		chests.clear();
		int[][] maze = null;
		MazeGenerator generator = new PerfectMazeGenerator(mazeWidth, mazeHeight);
		maze = generator.generateMaze();
		for (int yy = y; yy < sizeY; yy++)
			switch ((yy - y) % 5) {
				case 0:
					buildLevel(world, x, yy + 2, z, mazeWidth, mazeHeight, maze, solid, 0);
					buildLevel(world, x, yy + 3, z, mazeWidth, mazeHeight, maze, solid, 0);
					buildLevel(world, x, yy + 4, z, mazeWidth, mazeHeight, maze, solid, 0);
					createAir(world, x, yy + 3, z + 1, mazeWidth, mazeHeight, rand);
					addFeature(world, x, yy + 3, z, mazeWidth, mazeHeight, maze, rand);
					break;
				case 1:
					buildFloor(world, x, yy - 1, z, mazeWidth, mazeHeight, rand, false, false);
					buildFloor(world, x, yy, z, mazeWidth, mazeHeight, rand, true, false);
					buildRoof(world, x, yy + 4, z, mazeWidth, mazeHeight, rand);
					break;
			}

		if (hasKey && !chests.isEmpty()) {
			IInventory randomInvt = chests.get(rand.nextInt(chests.size()));
			TileEntityBones.allowInsertion = true;
			Utils.addItemStackToInventory(randomInvt, ItemMaterials.DATA.FORCE_KEY.makeStack());
			TileEntityBones.allowInsertion = false;
			System.out.println("Added key to inventory at: " + ((TileEntity) randomInvt).xCoord + ", " + ((TileEntity) randomInvt).yCoord + ", " + ((TileEntity) randomInvt).zCoord);
		}
		System.out.println("Generated Maze At: X: " + x + " Y: " + y + " Z: " + z);
	}

	public void makeMaze(World world, Random rand, int x, int y, int z) {
		int yy = y;
		for (int floors = 0; floors < 6; floors++) {
			if (floors < 4 && floors > 0)
				generate(world, rand, x, yy, z, true);

			if (floors < 4) {
				// create stairs
				if (yy - y == 5 || yy - y == 15) {
					world.setBlock(x + 1, yy + 2, z + 1, solid);
					world.setBlock(x + 1, yy + 3, z + 1, solid);
					world.setBlock(x + 1, yy + 4, z + 1, solid);
					world.setBlock(x + 1, yy + 5, z + 1, stairs, 3, 2);
					if (world.isAirBlock(x + 1, yy + 2, z + 2))
						world.setBlock(x + 1, yy + 2, z + 2, solid);
					world.setBlock(x + 1, yy + 3, z + 2, solid);
					world.setBlock(x + 1, yy + 4, z + 2, stairs, 3, 2);
					world.setBlock(x + 1, yy + 3, z + 3, stairs, 1, 2);
					world.setBlock(x + 1, yy + 2, z + 3, solid);
					world.setBlock(x + 2, yy + 2, z + 3, stairs, 1, 2);
				} else {
					world.setBlock(x + 31, yy + 2, z + 31, solid);
					world.setBlock(x + 31, yy + 3, z + 31, solid);
					world.setBlock(x + 31, yy + 4, z + 31, solid);
					world.setBlock(x + 31, yy + 5, z + 31, stairs, 2, 2);
					if (world.isAirBlock(x + 31, yy + 2, z + 30))
						world.setBlock(x + 31, yy + 2, z + 30, solid);
					world.setBlock(x + 31, yy + 3, z + +30, solid);
					world.setBlock(x + 31, yy + 4, z + +30, stairs, 2, 2);
					world.setBlock(x + 31, yy + 3, z + 29, stairs, 0, 2);
					world.setBlock(x + 31, yy + 2, z + 29, solid);
					world.setBlock(x + 30, yy + 2, z + 29, stairs, 0, 2);
				}
			}

			if (floors == 0) {
				for(int xx = x; xx <= x + 32; xx++) {
					for(int zz = z; zz <= z + 32; zz++) {
						for(int y2 = y; y2 < y + 5; y2++) {
							if(y2 > y + 1) {
								if (world.getBlock(xx, y2, zz) != solid && world.getBlock(xx, y2, zz) != stairs)
									world.setBlockToAir(xx, y2, zz);

								if (rand.nextInt(10) == 0)
									world.setBlock(xx, y2, z, ModBlocks.soldierAntTrap, 3, 2);
								else
									world.setBlock(xx, y2, z, solid);
	
								if (rand.nextInt(10) == 0)
									world.setBlock(xx, y2, z + 32, ModBlocks.soldierAntTrap, 2, 2);
								else
									world.setBlock(xx, y2, z + 32, solid);

								if (rand.nextInt(10) == 0)
									world.setBlock(x, y2, zz, ModBlocks.soldierAntTrap, 5, 2);
								else
									world.setBlock(x, y2, zz, solid);

								if (rand.nextInt(10) == 0)
									world.setBlock(x + 32, y2, zz, ModBlocks.soldierAntTrap, 4, 2);
								else
									world.setBlock(x + 32, y2, zz, solid);
								
								if (zz <= z + 6 || zz > z + 9 && zz <= z + 22 || zz > z + 25) {
									if (rand.nextInt(10) == 0)
										world.setBlock(x + 16, y2, zz, ModBlocks.soldierAntTrap, 4, 2);
									else if (rand.nextInt(10) == 0)
										world.setBlock(x + 16, y2, zz, ModBlocks.soldierAntTrap, 5, 2);
									else 
										world.setBlock(x + 16, y2, zz, solid);
								}
								if (xx <= x + 6 || xx > x + 9 && xx <= x + 22 || xx > x + 25) {
									if (rand.nextInt(10) == 0)
										world.setBlock(xx, y2, z + 16, ModBlocks.soldierAntTrap, 2, 2);
									else if (rand.nextInt(10) == 0)
										world.setBlock(xx, y2, z + 16, ModBlocks.soldierAntTrap, 3, 2);
									else 
										world.setBlock(xx, y2, z + 16, solid); 
								}
							}
						}
					}
				}
				buildFloor(world, x, yy, z, 8, 8, rand, false, false);
				buildFloor(world, x, yy + 1, z, 8, 8, rand, true, true);
				TileEntitySlidingBlockPuzzle.createPuzzleAt(world, x + 16, y + 3, z + 8, ForgeDirection.getOrientation(ForgeDirection.WEST.ordinal()), SlidingPuzzle.TEST);
				TileEntitySlidingBlockPuzzle.createPuzzleAt(world, x + 16, y + 3, z + 24, ForgeDirection.getOrientation(ForgeDirection.EAST.ordinal()), SlidingPuzzle.TEST);
				TileEntitySlidingBlockPuzzle.createPuzzleAt(world, x + 8, y + 3, z + 16, ForgeDirection.getOrientation(ForgeDirection.SOUTH.ordinal()), SlidingPuzzle.TEST);
				TileEntitySlidingBlockPuzzle.createPuzzleAt(world, x + 24, y + 3, z + 16, ForgeDirection.getOrientation(ForgeDirection.NORTH.ordinal()), SlidingPuzzle.TEST);
				
			}

			if (floors == 4) {
				generateMainDome(world, x + 16, yy + 1, z + 16);
				gererateEntrance(world, x + 2, yy + 1, z + 2);
			}

			// create air gaps above stairs using imaginary extra 2 floors
			if (yy - y == 15 || yy - y == 25) {
				world.setBlockToAir(x + 1, yy - 4, z + 1);
				world.setBlockToAir(x + 1, yy - 4, z + 2);
				world.setBlockToAir(x + 1, yy - 4, z + 3);
				world.setBlockToAir(x + 1, yy - 5, z + 2);
				world.setBlockToAir(x + 1, yy - 5, z + 3);
				world.setBlockToAir(x + 1, yy - 6, z + 3);
				world.setBlockToAir(x + 2, yy - 5, z + 3);
				world.setBlockToAir(x + 2, yy - 6, z + 3);
				if(yy - y != 25) {
					world.setBlock(x + 1, yy - 3, z + 1, ModBlocks.forceField);
					world.setBlock(x + 1, yy - 3, z + 2, ModBlocks.forceLock, 2, 2);
					world.setBlock(x + 1, yy - 3, z + 3, ModBlocks.forceField);
				}
			} else if (yy - y == 10 || yy - y == 20) {
				world.setBlockToAir(x + 31, yy - 4, z + 31);
				world.setBlockToAir(x + 31, yy - 4, z + 30);
				world.setBlockToAir(x + 31, yy - 4, z + 29);
				world.setBlockToAir(x + 31, yy - 5, z + 30);
				world.setBlockToAir(x + 31, yy - 5, z + 29);
				world.setBlockToAir(x + 31, yy - 6, z + 29);
				world.setBlockToAir(x + 30, yy - 5, z + 29);
				world.setBlockToAir(x + 30, yy - 6, z + 29);
				world.setBlock(x + 31, yy - 3, z + 31, ModBlocks.forceField);
				world.setBlock(x + 31, yy - 3, z + 30, ModBlocks.forceLock, 0, 2);
				world.setBlock(x + 31, yy - 3, z + 29, ModBlocks.forceField);
			}
			System.out.println("Y height is: " + " floor: " + (yy - y));
			yy += 5;
		}
	}

	private void gererateEntrance(World world, int x, int y, int z) {
		for (int xx = x - 5; xx <= x + 5; xx++)
			for (int zz = z - 5; zz <= z + 5; zz++)
				for (int yy = y; yy < y + 5; yy++) {
					double dSqDome = Math.pow(xx - x, 2.0D) + Math.pow(zz - z, 2.0D) + Math.pow(yy - y, 2.0D);
					if (Math.round(Math.sqrt(dSqDome)) < 5)
						if (dSqDome >= Math.pow(3, 2.0D))
							world.setBlock(xx, yy, zz, solid, 0, 2);
						else
							world.setBlock(xx, yy, zz, Blocks.air);
				}
		// entrance ways in to small dome
		world.setBlockToAir(x, y + 1, z - 3);
		world.setBlockToAir(x, y + 2, z - 3);
		world.setBlockToAir(x, y + 1, z - 4);
		world.setBlockToAir(x, y + 2, z - 4);

		world.setBlockToAir(x - 3, y + 1, z);
		world.setBlockToAir(x - 3, y + 2, z);
		world.setBlockToAir(x - 4, y + 1, z);
		world.setBlockToAir(x - 4, y + 2, z);
	}

	private void generateMainDome(World world, int x, int y, int z) {
		for (int xx = x - 17; xx <= x + 17; xx++)
			for (int zz = z - 17; zz <= z + 17; zz++)
				for (int yy = y; yy < y + 17; yy++) {
					double dSqDome = Math.pow(xx - x, 2.0D) + Math.pow(zz - z, 2.0D) + Math.pow(yy - y, 2.0D);
					if (Math.round(Math.sqrt(dSqDome)) < 17)
						if (dSqDome >= Math.pow(15, 2.0D) || yy == y)
							world.setBlock(xx, yy, zz, solid, 0, 2);
						else
							world.setBlockToAir(xx, yy, zz);
				}
	}

	private void createAir(World world, int x, int y, int z, int w, int h, Random rand) {
		for (int i = 0; i <= h * 4; i++)
			for (int j = 0; j <= w * 4; j++)
				for (int k = 0; k <= 2; k++)
					if (world.getBlock(x + j, y + k, z + i) != solid)
						world.setBlockToAir(x + j, y + k, z + i);
	}

	private void buildRoof(World world, int x, int y, int z, int w, int h, Random rand) {
		for (int i = 0; i <= h * 4; i++)
			for (int j = 0; j <= w * 4; j++)
				if (world.isAirBlock(x + j, y, z + i))
					world.setBlock(x + j, y, z + i, solid, 1, 2);
	}

	private void buildFloor(World world, int x, int y, int z, int w, int h, Random rand, boolean addFeature, boolean addSpawners) {
		for (int i = 0; i <= h * 4; i++)
			for (int j = 0; j <= w * 4; j++)
				if (rand.nextInt(15) == 0 && addFeature && world.getBlock(x + j, y + 1, z + i) != solid) {
					if (rand.nextBoolean() && rand.nextBoolean())
						world.setBlock(x + j, y, z + i, ModBlocks.formicAcid);
					else if (rand.nextInt(6) == 0 && addSpawners)
						world.setBlock(x + j, y, z + i, ModBlocks.zombieAntSpawner, 0, 2);
					else if (rand.nextInt(6) == 0 && addSpawners)
							world.setBlock(x + j, y, z + i, ModBlocks.zombieAntSoldierSpawner, 0, 2);
					else
						world.setBlock(x + j, y, z + i, ModBlocks.puffShroom, 3, 2);
				}
				else if (world.getBlock(x + j, y, z + i) != ModBlocks.zombieAntSoldierSpawner && world.getBlock(x + j, y, z + i) != ModBlocks.zombieAntSpawner)
					world.setBlock(x + j, y, z + i, solid, 0, 2);
	}

	private void addFeature(World world, int x, int y, int z, int w, int h, int[][] maze, Random rand) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 1) == 0) {
					if (world.getBlock(x + j * 4, y + 1, z + 1 + i * 4) != solid)
						world.setBlock(x + j * 4, y + 1, z + 1 + i * 4, stairs, 7, 3);
					world.setBlock(x + 1 + j * 4, y + 1, z + 1 + i * 4, stairs, 7, 3);
					world.setBlock(x + 2 + j * 4, y + 1, z + 1 + i * 4, stairs, 7, 3);
					world.setBlock(x + 3 + j * 4, y + 1, z + 1 + i * 4, stairs, 7, 3);
					if (world.getBlock(x + 4 + j * 4, y + 1, z + 1 + i * 4) != solid)
						world.setBlock(x + 4 + j * 4, y + 1, z + 1 + i * 4, stairs, 7, 3);
					if (rand.nextInt(25) == 0) {
						world.setBlock(x + 1 + j * 4, y, z + 1 + i * 4, Blocks.torch, 3, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, x + 1 + j * 4, y - 1, z + 1 + i * 4, 3, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, x + 1 + j * 4, y - 1, z + 1 + i * 4, 3, rand);
					} else if (rand.nextInt(6) == 0)
						if (rand.nextBoolean())
							world.setBlock(x + 2 + j * 4, y - 2, z + 2 + i * 4, ModBlocks.zombieAntSpawner);
						else
							world.setBlock(x + 2 + j * 4, y - 2, z + 2 + i * 4, ModBlocks.zombieAntSoldierSpawner);
					else if (rand.nextInt(10) == 0) {
						int randOffset = rand.nextInt(2);
						world.setBlock(x + 1 + j * 4, y - randOffset, z + i * 4, ModBlocks.soldierAntTrap, 3, 2);
					}
				}
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 8) == 0) {
					if (world.getBlock(x + 1 + j * 4, y + 1, z + i * 4) != solid)
						world.setBlock(x + 1 + j * 4, y + 1, z + i * 4, stairs, 5, 3);
					world.setBlock(x + 1 + j * 4, y + 1, z + 1 + i * 4, stairs, 5, 3);
					world.setBlock(x + 1 + j * 4, y + 1, z + 2 + i * 4, stairs, 5, 3);
					world.setBlock(x + 1 + j * 4, y + 1, z + 3 + i * 4, stairs, 5, 3);
					if (world.getBlock(x + 1 + j * 4, y + 1, z + 4 + i * 4) != solid)
						world.setBlock(x + 1 + j * 4, y + 1, z + 4 + i * 4, stairs, 5, 3);
					if (rand.nextInt(25) == 0) {
						world.setBlock(x + 1 + j * 4, y, z + 2 + i * 4, Blocks.torch, 1, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, x + 1 + j * 4, y - 1, z + 2 + i * 4, 1, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, x + 1 + j * 4, y - 1, z + 2 + i * 4, 5, rand);
					} else if (rand.nextInt(10) == 0) {
						int randOffset = rand.nextInt(2);
						world.setBlock(x + j * 4, y - randOffset, z + 2 + i * 4, ModBlocks.soldierAntTrap, 5, 2);
					}
				}
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 4) == 0) {
					if (world.getBlock(x + 3 + j * 4, y + 1, z + i * 4) != solid)
						world.setBlock(x + 3 + j * 4, y + 1, z + i * 4, stairs, 4, 3);
					world.setBlock(x + 3 + j * 4, y + 1, z + 1 + i * 4, stairs, 4, 3);
					world.setBlock(x + 3 + j * 4, y + 1, z + 2 + i * 4, stairs, 4, 3);
					world.setBlock(x + 3 + j * 4, y + 1, z + 3 + i * 4, stairs, 4, 3);
					if (world.getBlock(x + 3 + j * 4, y + 1, z + 4 + i * 4) != solid)
						world.setBlock(x + 3 + j * 4, y + 1, z + 4 + i * 4, stairs, 4, 3);
					if (rand.nextInt(25) == 0) {
						world.setBlock(x + 3 + j * 4, y, z + 2 + i * 4, Blocks.torch, 2, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, x + 3 + j * 4, y - 1, z + 2 + i * 4, 2, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, x + 3 + j * 4, y - 1, z + 2 + i * 4, 4, rand);
					}
					if (rand.nextInt(10) == 0) {
						int randOffset = rand.nextInt(2);
						world.setBlock(x + 4 + j * 4, y - randOffset, z + 2 + i * 4, ModBlocks.soldierAntTrap, 4, 2);
					}
				}
			for (int j = 0; j < w; j++)
				if ((maze[j][i] & 2) == 0) {
					if (world.getBlock(x + j * 4, y + 1, z + 3 + i * 4) != solid)
						world.setBlock(x + j * 4, y + 1, z + 3 + i * 4, stairs, 6, 3);
					world.setBlock(x + 1 + j * 4, y + 1, z + 3 + i * 4, stairs, 6, 3);
					world.setBlock(x + 2 + j * 4, y + 1, z + 3 + i * 4, stairs, 6, 3);
					world.setBlock(x + 3 + j * 4, y + 1, z + 3 + i * 4, stairs, 6, 3);
					if (world.getBlock(x + 4 + j * 4, y + 1, z + 3 + i * 4) != solid)
						world.setBlock(x + 4 + j * 4, y + 1, z + 3 + i * 4, stairs, 6, 3);
					if (rand.nextInt(25) == 0) {
						world.setBlock(x + 2 + j * 4, y, z + 3 + i * 4, Blocks.torch, 4, 2);
						if (rand.nextInt(4) == 0)
							placeChest(world, x + 2 + j * 4, y - 1, z + 3 + i * 4, 4, rand);
						else if (rand.nextInt(6) == 0)
							placeBones(world, x + 2 + j * 4, y - 1, z + 3 + i * 4, 2, rand);
					} else if (rand.nextInt(10) == 0) {
						int randOffset = rand.nextInt(2);
						world.setBlock(x + 1 + j * 4, y - randOffset, z + 4 + i * 4, ModBlocks.soldierAntTrap, 2, 2);
					}
				}
		}
	}

	private void placeChest(World world, int x, int y, int z, int directionMeta, Random rand) {
		world.setBlock(x, y, z, Blocks.chest, directionMeta, 2);
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(x, y, z);
		if (chest != null) {
			LootUtil.generateLoot(chest, rand, chestLoot, 3, 10);
			chests.add(chest);
		}
	}

	private void placeBones(World world, int x, int y, int z, int directionMeta, Random rand) {
		world.setBlock(x, y, z, ModBlocks.bones, directionMeta, 2);
		TileEntityBones bones = (TileEntityBones) world.getTileEntity(x, y, z);
		if (bones != null) {
			LootUtil.generateLoot(bones, rand, chestLoot, 3, 10);
			chests.add(bones);
		}
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
}