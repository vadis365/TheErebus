package erebus.world.feature.tree;

import java.util.List;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.blocks.EnumWood;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGiantBaobab extends WorldGenerator {

	private int height = -1;
	private int baseRadius = -1;
	protected Block log, leaves;

	public WorldGenGiantBaobab() {
		height = 28;
		baseRadius = 14;
		log = EnumWood.BAOBAB.getLog();
		leaves = EnumWood.BAOBAB.getLeaves();
	}

	public static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] {
			new LootItemStack(Items.BOW).setWeight(1), new LootItemStack(Items.ARROW).setAmount(3, 10).setWeight(18),
			new LootItemStack(Items.BOOK).setAmount(1, 4).setWeight(18),
			new LootItemStack(Items.PAPER).setAmount(2, 6).setWeight(16),
			new LootItemStack(Blocks.WEB).setAmount(2, 7).setWeight(13),
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(EnumErebusMaterialsType.JADE.ordinal()).setWeight(10),
			new LootItemStack(ModItems.MATERIALS).setAmount(4, 8).setDamage(EnumErebusMaterialsType.PLATE_EXO.ordinal()).setWeight(9),
			new LootItemStack(Items.ENCHANTED_BOOK).setWeight(8),
			/*
			 * new LootItemStack(ModBlocks.umberGolemStatue).setAmount(1).setWeight(1), new
			 * LootItemStack(ModItems.webSlinger).setAmount(1).setWeight(1),
			 */ new LootItemStack(Items.GOLDEN_PICKAXE).setWeight(3),
			new LootItemStack(Items.IRON_PICKAXE).setWeight(2), new LootItemStack(ModItems.JADE_PICKAXE).setWeight(1),
			new LootItemStack(Items.STONE_PICKAXE).setWeight(1), new LootItemStack(Items.GOLDEN_SHOVEL).setWeight(3),
			new LootItemStack(Items.IRON_SHOVEL).setWeight(2), new LootItemStack(ModItems.JADE_SHOVEL).setWeight(1),
			new LootItemStack(Items.STONE_SHOVEL).setWeight(1), new LootItemStack(Items.GOLDEN_AXE).setWeight(3),
			new LootItemStack(Items.IRON_AXE).setWeight(2), new LootItemStack(ModItems.JADE_AXE).setWeight(1),
			new LootItemStack(Items.STONE_AXE).setWeight(1), new LootItemStack(Items.GOLDEN_SWORD).setWeight(3),
			new LootItemStack(Items.IRON_SWORD).setWeight(2), new LootItemStack(ModItems.JADE_SWORD).setWeight(1),
			new LootItemStack(Items.STONE_SWORD).setWeight(1), new LootItemStack(Items.IRON_CHESTPLATE).setWeight(2),
			new LootItemStack(ModItems.JADE_CHESTPLATE).setWeight(1),
			new LootItemStack(Items.GOLDEN_CHESTPLATE).setWeight(1), new LootItemStack(Items.IRON_HELMET).setWeight(2),
			new LootItemStack(ModItems.JADE_HELMET).setWeight(1), new LootItemStack(Items.GOLDEN_HELMET).setWeight(1),
			new LootItemStack(Items.IRON_LEGGINGS).setWeight(2), new LootItemStack(ModItems.JADE_LEGGINGS).setWeight(1),
			new LootItemStack(Items.GOLDEN_LEGGINGS).setWeight(1), new LootItemStack(Items.IRON_BOOTS).setWeight(2),
			new LootItemStack(ModItems.JADE_BOOTS).setWeight(1), new LootItemStack(Items.GOLDEN_BOOTS).setWeight(1) }).setPostProcessor(new IPostProcess() {

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

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int xx = x - baseRadius; xx <= x + baseRadius; xx++)
			for (int zz = z - baseRadius; zz <= z + baseRadius; zz++)
				for (int yy = y + 1; yy < y + height; yy++)
					if (!world.isAirBlock(new BlockPos(xx, yy, zz)))
						return false;

		int radius = baseRadius - 1;
		int layer1 = 4;
		int layer2 = 7;
		int layer3 = 9;
		int layer4 = 19;
		int layer5 = 27;

		// trunk
		for (int yy = y; height + y >= yy; yy++)
			for (int i = radius * -1; i <= radius; ++i)
				for (int j = radius * -1; j <= radius; ++j) {
					double dSq = i * i + j * j;
					if (yy <= y + layer1 && yy < y + layer2) {
						if (Math.round(Math.sqrt(dSq)) == radius || Math.round(Math.sqrt(dSq)) <= radius - 1 && yy <= y + 2)
							world.setBlockState(new BlockPos(x + i, yy, z + j), log.getDefaultState());
						world.setBlockState(new BlockPos(x, yy, z), log.getDefaultState());
					}

					if (yy <= y + layer2 && yy > y + layer1) {
						if (Math.round(Math.sqrt(dSq)) == radius - 1)
							world.setBlockState(new BlockPos(x + i, yy, z + j), log.getDefaultState());
						world.setBlockState(new BlockPos(x, yy, z), log.getDefaultState());
					}

					if (yy <= y + layer3 && yy > y + layer2) {
						if (Math.round(Math.sqrt(dSq)) == radius - 2)
							world.setBlockState(new BlockPos(x + i, yy, z + j), log.getDefaultState());
						world.setBlockState(new BlockPos(x, yy, z), log.getDefaultState());
					}

					if (yy <= y + layer4 && yy > y + layer3) {
						world.setBlockState(new BlockPos(x, y + 10, z), log.getDefaultState());
						world.setBlockState(new BlockPos(x, y + 11, z), log.getDefaultState());

						if (Math.round(Math.sqrt(dSq)) <= radius - 12 && Math.round(Math.sqrt(dSq)) > radius - 13)
							world.setBlockState(new BlockPos(x + i, y + 12, z + j), Blocks.WEB.getDefaultState());
						if (Math.round(Math.sqrt(dSq)) == radius - 3 || Math.round(Math.sqrt(dSq)) <= radius - 3 && Math.round(Math.sqrt(dSq)) > radius - 12 && yy >= y + 9 && yy <= y + 12)
							world.setBlockState(new BlockPos(x + i, yy, z + j), log.getDefaultState());
					}

					if (yy <= y + layer5 && yy > y + layer4) {
						if (Math.round(Math.sqrt(dSq)) == radius - 2)
							world.setBlockState(new BlockPos(x + i, yy, z + j), log.getDefaultState());

						if (Math.round(Math.sqrt(dSq)) <= radius - 3 && yy == y + 20)
							world.setBlockState(new BlockPos(x + i, yy, z + j), log.getDefaultState());

						if (Math.round(Math.sqrt(dSq)) <= radius - 3 && yy == y + 21)
							world.setBlockState(new BlockPos(x + i, yy, z + j), ModBlocks.SILK.getDefaultState());

					}

					// 2nd floor gap in web shape
					if (Math.round(Math.sqrt(dSq)) < radius - 3 && Math.round(Math.sqrt(dSq)) % 2 == 0 && yy == y + 21)
						if (x + i != x && z + j != z)
							world.setBlockState(new BlockPos(x + i, yy, z + j), Blocks.WEB.getDefaultState());
				}

		// leaves
		createLeaves(world, rand, x + 10, y + 29, z);
		createLeaves(world, rand, x - 10, y + 29, z);
		createLeaves(world, rand, x, y + 29, z + 10);
		createLeaves(world, rand, x, y + 29, z - 10);
		createLeaves(world, rand, x + 7, y + 27, z + 7);
		createLeaves(world, rand, x + 7, y + 27, z - 7);
		createLeaves(world, rand, x - 7, y + 27, z + 7);
		createLeaves(world, rand, x - 7, y + 27, z - 7);

		// roots
		createRoots(world, rand, x, y, z);
		createRoots(world, rand, x + 9, y, z);
		createRoots(world, rand, x - 9, y, z);
		createRoots(world, rand, x, y, z + 9);
		createRoots(world, rand, x, y, z - 9);
		createRoots(world, rand, x + 8, y, z + 8);
		createRoots(world, rand, x + 8, y, z - 8);
		createRoots(world, rand, x - 8, y, z + 8);
		createRoots(world, rand, x - 8, y, z - 8);

		// other internal bits like vines and holes needed

		// ground floor vines
		for (int yy = y + 3; 11 + y >= yy; yy++) {
			world.setBlockState(new BlockPos(x + 1, yy, z), Blocks.VINE.getStateFromMeta(2), 3);
			world.setBlockState(new BlockPos(x - 1, yy, z), Blocks.VINE.getStateFromMeta(8), 3);
			world.setBlockState(new BlockPos(x, yy, z + 1), Blocks.VINE.getStateFromMeta(4), 3);
			world.setBlockState(new BlockPos(x, yy, z - 1), Blocks.VINE.getStateFromMeta(1), 3);
		}

		// 2nd floor holes for ladders
		world.setBlockState(new BlockPos(x + 9, y + 20, z), Blocks.AIR.getDefaultState());
		world.setBlockState(new BlockPos(x - 9, y + 20, z), Blocks.AIR.getDefaultState());
		world.setBlockState(new BlockPos(x, y + 20, z + 9), Blocks.AIR.getDefaultState());
		world.setBlockState(new BlockPos(x, y + 20, z - 9), Blocks.AIR.getDefaultState());
		world.setBlockState(new BlockPos(x + 9, y + 21, z), Blocks.AIR.getDefaultState());
		world.setBlockState(new BlockPos(x - 9, y + 21, z), Blocks.AIR.getDefaultState());
		world.setBlockState(new BlockPos(x, y + 21, z + 9), Blocks.AIR.getDefaultState());
		world.setBlockState(new BlockPos(x, y + 21, z - 9), Blocks.AIR.getDefaultState());

		// 1st floor vines
		for (int yy = y + 13; 21 + y >= yy; yy++) {
			world.setBlockState(new BlockPos(x - 9, yy, z), Blocks.VINE.getStateFromMeta(2), 3);
			world.setBlockState(new BlockPos(x + 9, yy, z), Blocks.VINE.getStateFromMeta(8), 3);
			world.setBlockState(new BlockPos(x, yy, z - 9), Blocks.VINE.getStateFromMeta(4), 3);
			world.setBlockState(new BlockPos(x, yy, z + 9), Blocks.VINE.getStateFromMeta(1), 3);
		}

		// spawners, chests with loot and webs
		// ground floor
		placeSpawner(world, rand, x - 7, y + 3, z);
		placeSpawner(world, rand, x + 7, y + 3, z);
		placeSpawner(world, rand, x, y + 3, z - 7);
		placeSpawner(world, rand, x, y + 3, z + 7);
		// 1st floor
		if (rand.nextBoolean()) {
			placeSpawner(world, rand, x - 5, y + 13, z);
			placeSpawner(world, rand, x + 5, y + 13, z);
		} else {
			placeSpawner(world, rand, x, y + 13, z - 5);
			placeSpawner(world, rand, x, y + 13, z + 5);
		}

		// TODO boss mob
		/*
		EntityTarantulaMiniboss boss = new EntityTarantulaMiniboss(world);
		boss.setLocationAndAngles(x + 0.5D, y + 22, z + 0.5D, rand.nextFloat() * 360F, 0F);
		boss.forceSpawn = true;
		world.spawnEntityInWorld(boss);
		*/
		System.out.println("Added Dungeon at: " + x + " " + z);
		return true;
	}

	public void createLeaves(World world, Random rand, int x, int y, int z) {
		int radius = 5;
		int height = 3;
		for (int xx = x - radius; xx <= x + radius; xx++)
			for (int zz = z - radius; zz <= z + radius; zz++)
				for (int yy = y - height; yy < y + height; yy++) {
					double dSq = Math.pow(xx - x, 2.0D) + Math.pow(zz - z, 2.0D) + Math.pow(yy - y, 2.0D);
					if (Math.round(Math.sqrt(dSq)) < radius)
						if (dSq >= Math.pow(radius - 2, 2.0D))
							if (rand.nextInt(10) == 0)
								world.setBlockState(new BlockPos(xx, yy, zz), log.getDefaultState());
							else
								world.setBlockState(new BlockPos(xx, yy, zz), leaves.getDefaultState());
				}
	}

	public void createRoots(World world, Random rand, int x, int y, int z) {
		float radius = 4;
		int height = rand.nextInt(6) + 10;
		for (int yy = y - 1; yy > y - height; --yy)
			for (int i = (int) (radius * -1); i <= radius; ++i)
				for (int j = (int) (radius * -1); j <= radius; ++j) {
					double dSq = i * i + j * j;
					if (Math.round(Math.sqrt(dSq)) <= radius)
						if (rand.nextInt(5) != 0)
							world.setBlockState(new BlockPos(x + i, yy, z + j), log.getStateFromMeta(15), 2);
					if (yy % 4 == 0)
						radius -= 0.02;
				}
	}

	public void placeSpawner(World world, Random rand, int x, int y, int z) {
		world.setBlockState(new BlockPos(x + 1, y, z), Blocks.WEB.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x - 1, y, z), Blocks.WEB.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x, y, z - 1), Blocks.WEB.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x, y, z + 1), Blocks.WEB.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x, y + 1, z), Blocks.WEB.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x, y, z), ModBlocks.TARANTULA_SPAWNER.getDefaultState(), 2);
		world.setBlockState(new BlockPos(x, y - 1, z), Blocks.CHEST.getStateFromMeta(0), 2);
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(new BlockPos(x, y - 1, z));
		if (chest != null)
			LootUtil.generateLoot(chest, rand, chestLoot, 3, 10);
	}
}
