package erebus.world.feature.plant;

import java.util.List;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
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

public class WorldGenRottenTreeStump extends WorldGenerator {

	private int height = -1;
	private int baseRadius = -1;
	private int maxRadius = 6;
	private int maxHeight = 15;
	protected Block log;

	public WorldGenRottenTreeStump(int height, int baseRadius) {
		this.height = height;
		this.baseRadius = baseRadius;
		log = EnumWood.Rotten.getLog();
	}

	public static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] { new LootItemStack(Items.book).setAmount(1, 4).setWeight(18), new LootItemStack(Items.paper).setAmount(2, 6).setWeight(16), new LootItemStack(Blocks.web).setAmount(2, 7).setWeight(13), new LootItemStack(ModItems.materials).setAmount(1, 3).setDamage(DATA.jade.ordinal()).setWeight(10), new LootItemStack(ModItems.materials).setAmount(4, 8).setDamage(DATA.plateExo.ordinal()).setWeight(9), new LootItemStack(Items.enchanted_book).setWeight(8), new LootItemStack(ModBlocks.umberGolemStatue).setAmount(1).setWeight(1), new LootItemStack(ModItems.webSlinger).setAmount(1).setWeight(1), new LootItemStack(Items.golden_pickaxe).setWeight(3), new LootItemStack(Items.iron_pickaxe).setWeight(2),
			new LootItemStack(ModItems.jadePickaxe).setWeight(1), new LootItemStack(Items.stone_pickaxe).setWeight(1), new LootItemStack(Items.golden_shovel).setWeight(3), new LootItemStack(Items.iron_shovel).setWeight(2), new LootItemStack(ModItems.jadeShovel).setWeight(1), new LootItemStack(Items.stone_shovel).setWeight(1), new LootItemStack(Items.golden_axe).setWeight(3), new LootItemStack(Items.iron_axe).setWeight(2), new LootItemStack(ModItems.jadeAxe).setWeight(1), new LootItemStack(Items.stone_axe).setWeight(1), new LootItemStack(Items.golden_sword).setWeight(3), new LootItemStack(Items.iron_sword).setWeight(2), new LootItemStack(ModItems.jadeSword).setWeight(1), new LootItemStack(Items.stone_sword).setWeight(1), new LootItemStack(Items.iron_chestplate).setWeight(2),
			new LootItemStack(ModItems.jadeBody).setWeight(1), new LootItemStack(Items.golden_chestplate).setWeight(1), new LootItemStack(Items.iron_helmet).setWeight(2), new LootItemStack(ModItems.jadeHelmet).setWeight(1), new LootItemStack(Items.golden_helmet).setWeight(1), new LootItemStack(Items.iron_leggings).setWeight(2), new LootItemStack(ModItems.jadeLegs).setWeight(1), new LootItemStack(Items.golden_leggings).setWeight(1), new LootItemStack(Items.iron_boots).setWeight(2), new LootItemStack(ModItems.jadeBoots).setWeight(1), new LootItemStack(Items.golden_boots).setWeight(1) }).setPostProcessor(new IPostProcess() {

				@SuppressWarnings("rawtypes")
				@Override
				public ItemStack postProcessItem(ItemStack is, Random rand) {
					if (rand.nextBoolean() && (is.getItem() == Items.enchanted_book || is.getItem() instanceof ItemTool || is.getItem() instanceof ItemArmor || is.getItem() instanceof ItemSword)) {
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

		for (int x1 = x - baseRadius; x1 <= x + baseRadius; x1++)
			for (int z1 = z - baseRadius; z1 <= z + baseRadius; z1++)
				for (int y1 = y + 1; y1 < y + height; y1++)
					if (!world.isAirBlock(x1, y1, z1))
						return false;

		int radius = baseRadius - 1;

		// Trunk
		for (int yy = y; height + y >= yy; yy++)
			for (int i = radius * -1; i <= radius; ++i)
				for (int j = radius * -1; j <= radius; ++j) {
					double dSq = i * i + j * j;
					if (Math.round(Math.sqrt(dSq)) == radius) {
						world.setBlock(x + i, yy, z + j, log);
						if (yy >= y + 5 && rand.nextInt(20) == 0)
							world.setBlock(x + i, yy, z + j, Blocks.air);
						if (yy == y + height && rand.nextInt(2) == 0)
							world.setBlock(x + i, yy, z + j, Blocks.air);
					} else
						world.setBlock(x + i, yy, z + j, Blocks.air);
					// floor at 10 high
					if (Math.round(Math.sqrt(dSq)) <= radius - 1 && baseRadius >= maxRadius && height >= maxHeight)
						if (yy == y + 10)
							world.setBlock(x + i, yy, z + j, log);
				}

		// Randomised root Base
		for (int i = baseRadius * -1; i <= baseRadius; ++i)
			for (int j = baseRadius * -1; j <= baseRadius; ++j) {
				double dSq = i * i + j * j;
				if (Math.round(Math.sqrt(dSq)) <= baseRadius) {
					world.setBlock(x + i, y, z + j, log, 15, 2);
					world.setBlock(x + i + rand.nextInt(2) - 1, y, z + j + rand.nextInt(2) - 1, log, 15, 2);
					world.setBlock(x + i, y + rand.nextInt(2), z + j, log, 15, 2);
				}
				// Branches sort of
				if (Math.round(Math.sqrt(dSq)) == baseRadius)
					for (int yy = y; height + y >= yy; yy++)
						if (yy < y + height - 1 && yy > y + 5 && rand.nextInt(12) == 0) {
							world.setBlock(x + i, yy, z + j, log, 15, 2);
							world.setBlock(x + i + rand.nextInt(2) - 1, yy, z + j + rand.nextInt(2) - 1, log, 15, 2);
						}
			}

		if (baseRadius >= maxRadius) {
			// Lower Spawner
			world.setBlock(x + 1, y + 2, z, Blocks.web, 0, 2);
			world.setBlock(x - 1, y + 2, z, Blocks.web, 0, 2);
			world.setBlock(x, y + 2, z - 1, Blocks.web, 0, 2);
			world.setBlock(x, y + 2, z + 1, Blocks.web, 0, 2);
			world.setBlock(x, y + 3, z, Blocks.web, 0, 2);
			world.setBlock(x, y + 1, z, Blocks.web, 0, 2);
			world.setBlock(x, y + 2, z, ModBlocks.jumpingSpiderSpawner);
			// Loot Chest
			world.setBlock(x, y, z, Blocks.chest, 0, 2);
			TileEntityChest chest1 = (TileEntityChest) world.getTileEntity(x, y, z);
			if (chest1 != null)
				LootUtil.generateLoot(chest1, rand, chestLoot, 3, 10);
			if (height >= maxHeight) {
				// Upper spawner
				world.setBlock(x + 1, y + 12, z, Blocks.web, 0, 2);
				world.setBlock(x - 1, y + 12, z, Blocks.web, 0, 2);
				world.setBlock(x, y + 12, z - 1, Blocks.web, 0, 2);
				world.setBlock(x, y + 12, z + 1, Blocks.web, 0, 2);
				world.setBlock(x, y + 11, z, Blocks.web, 0, 2);
				world.setBlock(x, y + 13, z, Blocks.web, 0, 2);
				world.setBlock(x, y + 12, z, ModBlocks.tarantulaSpawner);
				// Loot Chests
				int moveyChest = rand.nextInt(5) - 2;
				world.setBlock(x - 4, y + 11, z + moveyChest, Blocks.chest, 0, 2);
				world.setBlock(x + 4, y + 11, z + moveyChest, Blocks.chest, 0, 2);
				world.setBlock(x - 4, y + 12, z + moveyChest, log, 0, 2);
				world.setBlock(x + 4, y + 12, z + moveyChest, log, 0, 2);
				TileEntityChest chest2 = (TileEntityChest) world.getTileEntity(x - 4, y + 11, z + moveyChest);
				TileEntityChest chest3 = (TileEntityChest) world.getTileEntity(x + 4, y + 11, z + moveyChest);
				if (chest2 != null && chest3 != null) {
					LootUtil.generateLoot(chest1, rand, chestLoot, 3, 10);
					LootUtil.generateLoot(chest2, rand, chestLoot, 3, 10);
					LootUtil.generateLoot(chest3, rand, chestLoot, 3, 10);
				}
			}
		}
		return true;
	}
}
