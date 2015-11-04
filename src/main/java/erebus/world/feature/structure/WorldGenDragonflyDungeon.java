package erebus.world.feature.structure;

import java.util.List;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.item.ItemErebusFood.FoodType;
import erebus.item.ItemMaterials.DATA;
import erebus.item.ItemSmoothie.SmoothieType;
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

public class WorldGenDragonflyDungeon extends WorldGenerator {
	private Block lillyPad = ModBlocks.giantLilyPad;

	public static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] { new LootItemStack(Items.book).setAmount(1, 4).setWeight(18), new LootItemStack(Items.paper).setAmount(2, 6).setWeight(16), new LootItemStack(ModItems.materials).setAmount(1, 2).setDamage(DATA.waterRepellent.ordinal()).setWeight(3), new LootItemStack(ModItems.materials).setAmount(4, 8).setDamage(DATA.plateExo.ordinal()).setWeight(9), new LootItemStack(Items.enchanted_book).setWeight(8), new LootItemStack(Items.golden_pickaxe).setWeight(3), new LootItemStack(Items.iron_pickaxe).setWeight(2), new LootItemStack(ModItems.jadePickaxe).setWeight(1), new LootItemStack(Items.golden_shovel).setWeight(3), new LootItemStack(Items.iron_shovel).setWeight(2),
			new LootItemStack(ModItems.jadeShovel).setWeight(1), new LootItemStack(Items.golden_axe).setWeight(3), new LootItemStack(Items.iron_axe).setWeight(2), new LootItemStack(ModItems.jadeAxe).setWeight(1), new LootItemStack(Items.golden_sword).setWeight(3), new LootItemStack(Items.iron_sword).setWeight(2), new LootItemStack(ModItems.jadeSword).setWeight(1), new LootItemStack(Items.iron_chestplate).setWeight(2), new LootItemStack(ModItems.jadeBody).setWeight(1), new LootItemStack(Items.golden_chestplate).setWeight(1), new LootItemStack(Items.iron_helmet).setWeight(2), new LootItemStack(ModItems.jadeHelmet).setWeight(1), new LootItemStack(Items.golden_helmet).setWeight(1), new LootItemStack(Items.iron_leggings).setWeight(2), new LootItemStack(ModItems.jadeLegs).setWeight(1),
			new LootItemStack(Items.golden_leggings).setWeight(1), new LootItemStack(Items.iron_boots).setWeight(2), new LootItemStack(ModItems.jadeBoots).setWeight(1), new LootItemStack(Items.golden_boots).setWeight(1), new LootItemStack(ModItems.materials).setAmount(1).setDamage(DATA.altarFragment.ordinal()).setWeight(1), new LootItemStack(ModItems.materials).setAmount(1).setDamage(DATA.reinforcedPlateExo.ordinal()).setWeight(1), new LootItemStack(ModItems.materials).setAmount(1, 3).setDamage(DATA.hydrofuge.ordinal()).setWeight(3), new LootItemStack(ModItems.materials).setAmount(1).setDamage(DATA.plateExoRhino.ordinal()).setWeight(1), new LootItemStack(ModItems.food).setAmount(1, 3).setDamage(FoodType.HONEY_SANDWICH.ordinal()).setWeight(3),
			new LootItemStack(ModItems.cabbageSeeds).setAmount(1, 3).setWeight(2), new LootItemStack(ModItems.whetstone).setAmount(1).setDamage(0).setWeight(1), new LootItemStack(ModItems.lifeBlood).setAmount(1, 2).setWeight(4), new LootItemStack(ModItems.rolledNewspaper).setAmount(1).setWeight(1), new LootItemStack(ModItems.waspDagger).setAmount(1, 3).setWeight(2), new LootItemStack(ModItems.bucketAntiVenom).setAmount(1).setWeight(1), new LootItemStack(ModItems.bucketBeetleJuice).setAmount(1).setWeight(1), new LootItemStack(ModItems.bucketHoney).setAmount(1).setWeight(1), new LootItemStack(ModBlocks.glowGemBlock).setAmount(1, 3).setWeight(5), new LootItemStack(ModItems.smoothie).setAmount(1, 3).setDamage(SmoothieType.NOTHING_IN_THE_MIDDLE.ordinal()).setWeight(3),
			new LootItemStack(ModItems.smoothie).setAmount(1).setDamage(SmoothieType.BRYUFS_BREW.ordinal()).setWeight(1) }).setPostProcessor(new IPostProcess() {
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
		// space check
		for (int attempt = 0; attempt < 15; attempt++) {
			for (int xx = x - 6; xx <= x + 6; xx++)
				for (int zz = z - 6; zz <= z + 6; zz++)
					for (int yy = y + 1; yy < y + 5; yy++) {
						if (!world.isAirBlock(xx, yy, zz) || world.getBlock(xx, y - 1, zz) != Blocks.water)
							return false;
					}
		}

		// main lily pad
		for (int xx = x - 6; xx <= x + 6; xx++)
			for (int zz = z - 6; zz <= z + 6; zz++) {
				double dSqCylinder = Math.pow(xx - x, 2.0D) + Math.pow(zz - z, 2.0D);
				if (Math.round(Math.sqrt(dSqCylinder)) < 6)
					if (dSqCylinder <= Math.pow(6, 2.0D))
						world.setBlock(xx, y, zz, lillyPad, 0, 3);
				if (Math.round(Math.sqrt(dSqCylinder)) == 5)
					if (dSqCylinder <= Math.pow(6, 2.0D))
						world.setBlock(xx, y + 1, zz, lillyPad, 0, 3);
			}

		// air gap in lily pad
		int direction = rand.nextInt(4);

		if (direction == 0)
			for (int airX = 5; airX > 1; airX--) {
				world.setBlock(x + airX, y, z, Blocks.air, 0, 3);
				world.setBlock(x + airX, y + 1, z, Blocks.air, 0, 3);
				world.setBlock(x + airX, y + 1, z - 1, lillyPad, 0, 3);
				world.setBlock(x + airX, y + 1, z + 1, lillyPad, 0, 3);
			}

		if (direction == 1)
			for (int airZ = 5; airZ > 1; airZ--) {
				world.setBlock(x, y, z + airZ, Blocks.air, 0, 3);
				world.setBlock(x, y + 1, z + airZ, Blocks.air, 0, 3);
				world.setBlock(x + 1, y + 1, z + airZ, lillyPad, 0, 3);
				world.setBlock(x - 1, y + 1, z + airZ, lillyPad, 0, 3);
			}

		if (direction == 2)
			for (int airX = -5; airX < -1; airX++) {
				world.setBlock(x + airX, y, z, Blocks.air, 0, 3);
				world.setBlock(x + airX, y + 1, z, Blocks.air, 0, 3);
				world.setBlock(x + airX, y + 1, z - 1, lillyPad, 0, 3);
				world.setBlock(x + airX, y + 1, z + 1, lillyPad, 0, 3);
			}

		if (direction == 3)
			for (int airZ = -5; airZ < -1; airZ++) {
				world.setBlock(x, y, z + airZ, Blocks.air, 0, 3);
				world.setBlock(x, y + 1, z + airZ, Blocks.air, 0, 3);
				world.setBlock(x + 1, y + 1, z + airZ, lillyPad, 0, 3);
				world.setBlock(x - 1, y + 1, z + airZ, lillyPad, 0, 3);
			}

		// flower stem
		world.setBlock(x + 1, y + 1, z, lillyPad, 0, 3);
		world.setBlock(x, y + 1, z + 1, lillyPad, 0, 3);
		world.setBlock(x - 1, y + 1, z, lillyPad, 0, 3);
		world.setBlock(x, y + 1, z - 1, lillyPad, 0, 3);

		// flower
		world.setBlock(x + 1, y + 2, z, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x, y + 2, z + 1, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x - 1, y + 2, z, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x, y + 2, z - 1, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x + 1, y + 2, z + 1, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x + 1, y + 2, z - 1, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x - 1, y + 2, z + 1, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x - 1, y + 2, z - 1, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x + 2, y + 2, z, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x, y + 2, z + 2, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x - 2, y + 2, z, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x, y + 2, z - 2, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x + 2, y + 3, z, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x, y + 3, z + 2, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x - 2, y + 3, z, ModBlocks.erebusFlower, 13, 3);
		world.setBlock(x, y + 3, z - 2, ModBlocks.erebusFlower, 13, 3);

		// chest and spawners
		world.setBlock(x, y + 1, z, Blocks.chest, 0, 3);
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(x, y + 1, z);
		if (chest != null)
			LootUtil.generateLoot(chest, rand, chestLoot, 5, 15);
		world.setBlock(x, y + 2, z, ModBlocks.dragonflySpawner, 0, 3);
		world.setBlock(x, y + 3, z, ModBlocks.dragonflySpawner, 0, 3);
		return true;
	}
}