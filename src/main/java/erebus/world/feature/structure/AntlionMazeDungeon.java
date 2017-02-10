package erebus.world.feature.structure;

import erebus.ModBiomes;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.items.ItemErebusFood;
import erebus.items.ItemMaterials;
import erebus.world.feature.util.MazeGenerator;
import erebus.world.feature.util.PerfectMazeGenerator;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class AntlionMazeDungeon {
	public static final WeightedLootList chestLoot = new WeightedLootList(
			new LootItemStack[]{
					new LootItemStack(Items.BOOK).setAmount(1, 4).setWeight(18),
					new LootItemStack(Items.PAPER).setAmount(2, 6).setWeight(16),
					new LootItemStack(Blocks.WEB).setAmount(2, 7).setWeight(13),
					new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(ItemMaterials.EnumType.JADE.ordinal()).setWeight(10),
					new LootItemStack(ModItems.MATERIALS).setAmount(4, 8).setDamage(ItemMaterials.EnumType.PLATE_EXO.ordinal()).setWeight(9),
					new LootItemStack(Items.ENCHANTED_BOOK).setWeight(8),
					new LootItemStack(Items.GOLDEN_PICKAXE).setWeight(3),
					new LootItemStack(Items.IRON_PICKAXE).setWeight(2),
					new LootItemStack(ModItems.JADE_PICKAXE).setWeight(1),
					new LootItemStack(Items.GOLDEN_SHOVEL).setWeight(3),
					new LootItemStack(Items.IRON_SHOVEL).setWeight(2),
					new LootItemStack(ModItems.JADE_SHOVEL).setWeight(1),
					new LootItemStack(Items.GOLDEN_AXE).setWeight(3),
					new LootItemStack(Items.IRON_AXE).setWeight(2),
					new LootItemStack(ModItems.JADE_AXE).setWeight(1),
					new LootItemStack(Items.GOLDEN_SWORD).setWeight(3),
					new LootItemStack(Items.IRON_SWORD).setWeight(2),
					new LootItemStack(ModItems.JADE_SWORD).setWeight(1),
					new LootItemStack(Items.IRON_CHESTPLATE).setWeight(2),
					new LootItemStack(ModItems.JADE_CHESTPLATE).setWeight(1),
					new LootItemStack(Items.GOLDEN_CHESTPLATE).setWeight(1),
					new LootItemStack(Items.IRON_HELMET).setWeight(2),
					new LootItemStack(ModItems.JADE_HELMET).setWeight(1),
					new LootItemStack(Items.GOLDEN_HELMET).setWeight(1),
					new LootItemStack(Items.IRON_LEGGINGS).setWeight(2),
					new LootItemStack(ModItems.JADE_LEGGINGS).setWeight(1),
					new LootItemStack(Items.GOLDEN_LEGGINGS).setWeight(1),
					new LootItemStack(Items.IRON_BOOTS).setWeight(2),
					new LootItemStack(ModItems.JADE_BOOTS).setWeight(1),
					new LootItemStack(Items.GOLDEN_BOOTS).setWeight(1),
					new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(ItemMaterials.EnumType.ALTAR_FRAGMENT.ordinal()).setWeight(1),
					new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(ItemMaterials.EnumType.PLATE_EXO.ordinal()).setWeight(1),
					new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(ItemMaterials.EnumType.SCORPION_PINCER.ordinal()).setWeight(1),
					new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(ItemMaterials.EnumType.WHETSTONE_POWDER.ordinal()).setWeight(3),
					new LootItemStack(ModItems.MATERIALS).setAmount(1).setDamage(ItemMaterials.EnumType.PLATE_EXO_RHINO.ordinal()).setWeight(1),
					new LootItemStack(ModItems.EREBUS_FOOD).setAmount(1, 3).setDamage(ItemErebusFood.EnumFoodType.HONEY_SANDWICH.ordinal()).setWeight(3),
					new LootItemStack(ModItems.CABBAGE_SEEDS).setAmount(1, 3).setWeight(2),
					new LootItemStack(ModItems.LIFE_BLOOD).setAmount(1, 2).setWeight(4),
					new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(ItemMaterials.EnumType.WASP_STING.ordinal()).setWeight(2)}).setPostProcessor((is, rand) -> {
		if (is.getItem() == Items.ENCHANTED_BOOK || rand.nextBoolean() && (is.getItem() instanceof ItemTool || is.getItem() instanceof ItemArmor || is.getItem() instanceof ItemSword)) {
			boolean enchBook = is.getItem() == Items.ENCHANTED_BOOK;
			if (enchBook)
				is = new ItemStack(Items.BOOK);
			List enchList = EnchantmentHelper.buildEnchantmentList(rand, is, 7 + rand.nextInt(10), false);
			if (enchBook)
				is = new ItemStack(Items.ENCHANTED_BOOK);
			if (enchList != null && enchList.size() > 0)
				for (int a = 0; a < enchList.size(); ++a) {
					EnchantmentData data = (EnchantmentData) enchList.get(a);
					if (is.getItem() == Items.ENCHANTED_BOOK)
						Items.ENCHANTED_BOOK.addEnchantment(is, data);
					else
						is.addEnchantment(data.enchantmentobj, data.enchantmentLevel);
				}
		}
		return is;
	});
	private Block solid = ModBlocks.ORE_GNEISS;

	private static void setFloorMidDecoStone(World world, int x, int y, int z) {
		for (int dx = x; dx < x + 4; dx++)
			for (int dz = z; dz < z + 4; dz++)
				world.setBlock(dx, y, dz, ModBlocks.capstone, 0, 2);
	}

	public static void setTeleporter(World world, int x, int y, int z, int metaData, int targetX, int targetY, int targetZ) {
		world.setBlock(x, y, z, ModBlocks.templeTeleporter, metaData, 2);
		TileEntityTempleTeleporter teleporter = (TileEntityTempleTeleporter) world.getTileEntity(x, y, z);
		if (teleporter != null)
			teleporter.setTargetDestination(targetX, targetY, targetZ);
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

	public void generateSurface(World world, Random rand, int chunkX, int chunkY, int chunkZ) {
		Biome biomeBase = world.getBiomeForCoordsBody(chunkX, chunkZ);
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
		buildCourtyard(world, ModBlocks.ORE_TEMPLE, 0, x + sizeX, y - 4, z + sizeZ, 52, 4, 52);
		createPyramid(world, ModBlocks.ORE_TEMPLE, 0, true, x + sizeX / 2 + 8, z + sizeZ / 2 + 8, 44, 44, y - 6);
		decoratePyramid(world, x + sizeX / 2 + 8, y - 6, z + sizeZ / 2 + 8);
		addTeleporters(world, x + sizeX / 2 + 8, y - 6, z + sizeZ / 2 + 8);
		addCapstones(world, x + sizeX - 1, y + 15, z + sizeZ - 1, ModBlocks.UMBERSTONE);
		spawnIdolGuardians(world, x, y, z);
		// System.out.println("Generated Maze At: X: " + x + " Y: " + y + " Z: " + z);
	}

	private void createAir(World world, int x, int y, int z, int w, int h, Random rand) {
		for (int i = 0; i <= h * 4; i++)
			for (int j = 0; j <= w * 4; j++)
				for (int k = 0; k <= 2; k++)
					if (world.getBlockState(new BlockPos(x + j, y + k, z + i)) != solid)
						world.setBlockToAir(new BlockPos(x + j, y + k, z + i));
	}

	private void addTeleporters(World world, int x, int y, int z) {
		// room 1
		world.setBlockState(new BlockPos(x + 13, y + 9, z + 13), ModBlocks.UMBERSTONE.getDefaultState(), 2);
		setFloorDecoStone(world, x + 14, y + 9, z + 14);
		setLockStone(world, x + 15, y + 9, z + 15, 2);
		setTeleporter(world, x + 16, y + 9, z + 16, 0, x + 30, y + 9, z + 13);
		setTeleporter(world, x + 19, y + 9, z + 19, 5, x + 19, y + 14, z + 19);
		// room 2
		world.setBlockState(new BlockPos(x + 30, y + 9, z + 13), ModBlocks.UMBERSTONE.getDefaultState(), 2);
		setFloorDecoStone(world, x + 25, y + 9, z + 14);
		setLockStone(world, x + 26, y + 9, z + 15, 3);
		setTeleporter(world, x + 27, y + 9, z + 16, 0, x + 30, y + 9, z + 30);
		setTeleporter(world, x + 24, y + 9, z + 19, 5, x + 13, y + 9, z + 13);
		// room 3
		world.setBlockState(new BlockPos(x + 30, y + 9, z + 30), ModBlocks.UMBERSTONE.getDefaultState(), 2);
		setFloorDecoStone(world, x + 25, y + 9, z + 25);
		setLockStone(world, x + 26, y + 9, z + 26, 4);
		setTeleporter(world, x + 27, y + 9, z + 27, 0, x + 13, y + 9, z + 30);
		setTeleporter(world, x + 24, y + 9, z + 24, 5, x + 30, y + 9, z + 13);
		// room 4
		world.setBlockState(new BlockPos(x + 13, y + 9, z + 30), ModBlocks.UMBERSTONE.getDefaultState(), 2);
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
		world.setBlockState(new BlockPos(x + 19, y + 14, z + 19), ModBlocks.UMBERSTONE.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x + 19, y + 15, z + 25), ModBlocks.MUD.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x + 19, y + 16, z + 25), ModBlocks.MUD.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x + 25, y + 15, z + 19), ModBlocks.MUD.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x + 25, y + 16, z + 19), ModBlocks.MUD.getDefaultState(), 2);
		setFloorDecoStone(world, x + 20, y + 14, z + 20);
		setLockStone(world, x + 21, y + 14, z + 21, 1);
		setTeleporter(world, x + 22, y + 14, z + 22, 0, x + 13, y + 9, z + 13);
		setTeleporter(world, x + 25, y + 14, z + 25, 5, x + 19, y + 14, z + 19);
	}

	private void setLockStone(World world, int x, int y, int z, int meta) {
		for (int dx = x; dx < x + 3; dx++)
			for (int dz = z; dz < z + 3; dz++)
				world.setBlockState(new BlockPos(dx, y, dz), ModBlocks.ORE_TEMPLE.getDefaultState(), 2);
	}

	private void setFloorDecoStone(World world, int x, int y, int z) {
		for (int dx = x; dx < x + 5; dx++)
			for (int dz = z; dz < z + 5; dz++)
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
							chest.setInventorySlotContents(0, ItemMaterials.DATA.jade.createStack(8));
						world.setBlockMetadataWithNotify(x + 19, yy, z + 22, 2, 3);
					}

					if (yy == y + 16) {
						// TODO Lighting?
					}
				}
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
}
