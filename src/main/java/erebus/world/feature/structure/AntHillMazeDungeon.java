package erebus.world.feature.structure;

import java.util.List;
import java.util.Random;

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
import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.item.ItemErebusFood.FoodType;
import erebus.item.ItemMaterials.DATA;
import erebus.item.ItemSmoothie.SmoothieType;
import erebus.tileentity.TileEntityBones;
import erebus.world.feature.util.MazeGenerator;
import erebus.world.feature.util.PerfectMazeGenerator;
import erebus.world.loot.IPostProcess;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;

public class AntHillMazeDungeon {
	private Block solid = Blocks.dirt;
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
		if (biomeBase == ModBiomes.fungalForest)
			generate(world, rand, chunkX, chunkY, chunkZ);
	}

	public void generate(World world, Random rand, int x, int y, int z) {
		int sizeX = 16;
		int sizeY = y + 16;
		int sizeZ = 16;
		int mazeWidth = sizeX / 2;
		int mazeHeight = sizeZ / 2;

		if (mazeWidth < 2 || mazeHeight < 2 || sizeY < 1)
			return;

		int[][] maze = null;
		MazeGenerator generator = new PerfectMazeGenerator(mazeWidth, mazeHeight);
		maze = generator.generateMaze();

		for (int yy = y; yy < sizeY; yy++) {
			switch ((yy - y) % 4) {
				case 0:
					buildFloor(world, x, yy - 4, z, mazeWidth, mazeHeight, rand);
				//	buildRoof(world, x, yy, z, mazeWidth, mazeHeight, rand);
					break;
				case 1:
					buildLevel(world, x, yy - 4, z, mazeWidth, mazeHeight, maze, solid, 0);
					buildLevel(world, x, yy - 3, z, mazeWidth, mazeHeight, maze, solid, 0);
					buildLevel(world, x, yy - 2, z, mazeWidth, mazeHeight, maze, solid, 0);
					createAir(world, x, yy - 4, z, mazeWidth, mazeHeight, rand);
					addFeature(world, x, yy - 3, z, mazeWidth, mazeHeight, maze, rand);
					break;
			}
			world.setBlock(x + 1, yy - 4, z + 1, Blocks.air); //just an air gap to see levels
			world.setBlock(x + 31, yy - 4, z + 31, Blocks.air);
		}
		System.out.println("Generated Maze At: X: " + x + " Y: " + y + " Z: " + z);
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
				if (canPlaceFeatureAt(world, x, y, z, x + j, y, z + i))
					world.setBlock(x + j, y, z + i, solid, 0, 2);
	}

	private void buildFloor(World world, int x, int y, int z, int w, int h, Random rand) {
		for (int i = 0; i <= h * 4; i++)
			for (int j = 0; j <= w * 4; j++)
				if (rand.nextInt(15) == 0)
					if (rand.nextBoolean() && rand.nextBoolean())
						world.setBlock(x + j, y, z + i, Blocks.mycelium);
					else
						world.setBlock(x + j, y, z + i, ModBlocks.swampVent);
				else
					world.setBlock(x + j, y, z + i, solid, 0, 2);
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
							world.setBlock(x + 2 + j * 4, y - 2, z + 2 + i * 4, Blocks.wool);
			
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
}
