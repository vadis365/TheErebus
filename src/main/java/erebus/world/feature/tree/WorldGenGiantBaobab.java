package erebus.world.feature.tree;

import java.util.List;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.entity.EntityTarantulaMiniboss;
import erebus.item.ItemMaterials.DATA;
import erebus.lib.EnumWood;
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
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGiantBaobab extends WorldGenerator {

	private int height = -1;
	private int baseRadius = -1;
	protected Block log, leaves;

	public WorldGenGiantBaobab() {
		height = 28;
		baseRadius = 14;
		log = EnumWood.Baobab.getLog();
		leaves = EnumWood.Baobab.getLeaves();
	}

	public static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] { new LootItemStack(Items.bow).setWeight(1), new LootItemStack(Items.arrow).setAmount(3, 10).setWeight(18), new LootItemStack(Items.book).setAmount(1, 4).setWeight(18), new LootItemStack(Items.paper).setAmount(2, 6).setWeight(16), new LootItemStack(Blocks.web).setAmount(2, 7).setWeight(13), new LootItemStack(ModItems.materials).setAmount(1, 3).setDamage(DATA.jade.ordinal()).setWeight(10), new LootItemStack(ModItems.materials).setAmount(4, 8).setDamage(DATA.plateExo.ordinal()).setWeight(9), new LootItemStack(Items.enchanted_book).setWeight(8), new LootItemStack(ModBlocks.umberGolemStatue).setAmount(1).setWeight(1), new LootItemStack(ModItems.webSlinger).setAmount(1).setWeight(1),
			new LootItemStack(Items.golden_pickaxe).setWeight(3), new LootItemStack(Items.iron_pickaxe).setWeight(2), new LootItemStack(ModItems.jadePickaxe).setWeight(1), new LootItemStack(Items.stone_pickaxe).setWeight(1), new LootItemStack(Items.golden_shovel).setWeight(3), new LootItemStack(Items.iron_shovel).setWeight(2), new LootItemStack(ModItems.jadeShovel).setWeight(1), new LootItemStack(Items.stone_shovel).setWeight(1), new LootItemStack(Items.golden_axe).setWeight(3), new LootItemStack(Items.iron_axe).setWeight(2), new LootItemStack(ModItems.jadeAxe).setWeight(1), new LootItemStack(Items.stone_axe).setWeight(1), new LootItemStack(Items.golden_sword).setWeight(3), new LootItemStack(Items.iron_sword).setWeight(2), new LootItemStack(ModItems.jadeSword).setWeight(1),
			new LootItemStack(Items.stone_sword).setWeight(1), new LootItemStack(Items.iron_chestplate).setWeight(2), new LootItemStack(ModItems.jadeBody).setWeight(1), new LootItemStack(Items.golden_chestplate).setWeight(1), new LootItemStack(Items.iron_helmet).setWeight(2), new LootItemStack(ModItems.jadeHelmet).setWeight(1), new LootItemStack(Items.golden_helmet).setWeight(1), new LootItemStack(Items.iron_leggings).setWeight(2), new LootItemStack(ModItems.jadeLegs).setWeight(1), new LootItemStack(Items.golden_leggings).setWeight(1), new LootItemStack(Items.iron_boots).setWeight(2), new LootItemStack(ModItems.jadeBoots).setWeight(1), new LootItemStack(Items.golden_boots).setWeight(1) }).setPostProcessor(new IPostProcess() {

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

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {

		for (int xx = x - baseRadius; xx <= x + baseRadius; xx++)
			for (int zz = z - baseRadius; zz <= z + baseRadius; zz++)
				for (int yy = y + 1; yy < y + height; yy++)
					if (!world.isAirBlock(xx, yy, zz))
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
							world.setBlock(x + i, yy, z + j, log);
						world.setBlock(x, yy, z, log);
					}

					if (yy <= y + layer2 && yy > y + layer1) {
						if (Math.round(Math.sqrt(dSq)) == radius - 1)
							world.setBlock(x + i, yy, z + j, log);
						world.setBlock(x, yy, z, log);
					}

					if (yy <= y + layer3 && yy > y + layer2) {
						if (Math.round(Math.sqrt(dSq)) == radius - 2)
							world.setBlock(x + i, yy, z + j, log);
						world.setBlock(x, yy, z, log);
					}

					if (yy <= y + layer4 && yy > y + layer3) {
						world.setBlock(x, y + 10, z, log);
						world.setBlock(x, y + 11, z, log);

						if (Math.round(Math.sqrt(dSq)) <= radius - 12 && Math.round(Math.sqrt(dSq)) > radius - 13)
							world.setBlock(x + i, y + 12, z + j, Blocks.web);
						if (Math.round(Math.sqrt(dSq)) == radius - 3 || Math.round(Math.sqrt(dSq)) <= radius - 3 && Math.round(Math.sqrt(dSq)) > radius - 12 && yy >= y + 9 && yy <= y + 12)
							world.setBlock(x + i, yy, z + j, log);
					}

					if (yy <= y + layer5 && yy > y + layer4) {
						if (Math.round(Math.sqrt(dSq)) == radius - 2)
							world.setBlock(x + i, yy, z + j, log);

						if (Math.round(Math.sqrt(dSq)) <= radius - 3 && yy == y + 20)
							world.setBlock(x + i, yy, z + j, log);

						if (Math.round(Math.sqrt(dSq)) <= radius - 3 && yy == y + 21)
							world.setBlock(x + i, yy, z + j, ModBlocks.blockSilk);

					}

					// 2nd floor gap in web shape
					if (Math.round(Math.sqrt(dSq)) < radius - 3 && Math.round(Math.sqrt(dSq)) % 2 == 0 && yy == y + 21)
						if (x + i != x && z + j != z)
							world.setBlock(x + i, yy, z + j, Blocks.web);
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
			world.setBlock(x + 1, yy, z, Blocks.vine, 2, 3);
			world.setBlock(x - 1, yy, z, Blocks.vine, 8, 3);
			world.setBlock(x, yy, z + 1, Blocks.vine, 4, 3);
			world.setBlock(x, yy, z - 1, Blocks.vine, 1, 3);
		}

		// 2nd floor holes for ladders
		world.setBlock(x + 9, y + 20, z, Blocks.air);
		world.setBlock(x - 9, y + 20, z, Blocks.air);
		world.setBlock(x, y + 20, z + 9, Blocks.air);
		world.setBlock(x, y + 20, z - 9, Blocks.air);
		world.setBlock(x + 9, y + 21, z, Blocks.air);
		world.setBlock(x - 9, y + 21, z, Blocks.air);
		world.setBlock(x, y + 21, z + 9, Blocks.air);
		world.setBlock(x, y + 21, z - 9, Blocks.air);

		// 1st floor vines
		for (int yy = y + 13; 21 + y >= yy; yy++) {
			world.setBlock(x - 9, yy, z, Blocks.vine, 2, 3);
			world.setBlock(x + 9, yy, z, Blocks.vine, 8, 3);
			world.setBlock(x, yy, z - 9, Blocks.vine, 4, 3);
			world.setBlock(x, yy, z + 9, Blocks.vine, 1, 3);
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

		// boss mob
		EntityTarantulaMiniboss boss = new EntityTarantulaMiniboss(world);
		boss.setLocationAndAngles(x + 0.5D, y + 22, z + 0.5D, rand.nextFloat() * 360F, 0F);
		boss.forceSpawn = true;
		world.spawnEntityInWorld(boss);
		//System.out.println("Added Dungeon at: " + x + " " + z);
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
								world.setBlock(xx, yy, zz, log);
							else
								world.setBlock(xx, yy, zz, leaves);
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
							world.setBlock(x + i, yy, z + j, log, 15, 2);
					if (yy % 4 == 0)
						radius -= 0.02;
				}
	}

	public void placeSpawner(World world, Random rand, int x, int y, int z) {
		world.setBlock(x + 1, y, z, Blocks.web, 0, 2);
		world.setBlock(x - 1, y, z, Blocks.web, 0, 2);
		world.setBlock(x, y, z - 1, Blocks.web, 0, 2);
		world.setBlock(x, y, z + 1, Blocks.web, 0, 2);
		world.setBlock(x, y + 1, z, Blocks.web, 0, 2);
		world.setBlock(x, y, z, ModBlocks.tarantulaSpawner);
		world.setBlock(x, y - 1, z, Blocks.chest, 0, 2);
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(x, y - 1, z);
		if (chest != null)
			LootUtil.generateLoot(chest, rand, chestLoot, 3, 10);
	}
}
