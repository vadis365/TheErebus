package erebus.world.feature.structure;

import java.util.List;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.entity.EntityAntlionMiniBoss;
import erebus.item.ItemMaterials.DATA;
import erebus.world.loot.IPostProcess;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;
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

public class WorldGenAntlionLair extends WorldGenerator {

	public static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] { new LootItemStack(Items.book).setAmount(1, 4).setWeight(18), new LootItemStack(Items.paper).setAmount(2, 6).setWeight(16), new LootItemStack(Blocks.web).setAmount(2, 7).setWeight(13), new LootItemStack(ModItems.materials).setAmount(1, 3).setDamage(DATA.jade.ordinal()).setWeight(10), new LootItemStack(ModItems.materials).setAmount(4, 8).setDamage(DATA.plateExo.ordinal()).setWeight(9), new LootItemStack(Items.enchanted_book).setWeight(8), new LootItemStack(ModBlocks.umberGolemStatue).setAmount(1).setWeight(1), new LootItemStack(ModItems.webSlinger).setAmount(1).setWeight(1), new LootItemStack(Items.golden_pickaxe).setWeight(3), new LootItemStack(Items.iron_pickaxe).setWeight(2),
			new LootItemStack(ModItems.jadePickaxe).setWeight(1), new LootItemStack(Items.stone_pickaxe).setWeight(1), new LootItemStack(Items.golden_shovel).setWeight(3), new LootItemStack(Items.iron_shovel).setWeight(2), new LootItemStack(ModItems.jadeShovel).setWeight(1), new LootItemStack(Items.stone_shovel).setWeight(1), new LootItemStack(Items.golden_axe).setWeight(3), new LootItemStack(Items.iron_axe).setWeight(2), new LootItemStack(ModItems.jadeAxe).setWeight(1), new LootItemStack(Items.stone_axe).setWeight(1), new LootItemStack(Items.golden_sword).setWeight(3), new LootItemStack(Items.iron_sword).setWeight(2), new LootItemStack(ModItems.jadeSword).setWeight(1), new LootItemStack(Items.stone_sword).setWeight(1), new LootItemStack(Items.iron_chestplate).setWeight(2),
			new LootItemStack(ModItems.jadeBody).setWeight(1), new LootItemStack(Items.golden_chestplate).setWeight(1), new LootItemStack(Items.iron_helmet).setWeight(2), new LootItemStack(ModItems.jadeHelmet).setWeight(1), new LootItemStack(Items.golden_helmet).setWeight(1), new LootItemStack(Items.iron_leggings).setWeight(2), new LootItemStack(ModItems.jadeLegs).setWeight(1), new LootItemStack(Items.golden_leggings).setWeight(1), new LootItemStack(Items.iron_boots).setWeight(2), new LootItemStack(ModItems.jadeBoots).setWeight(1), new LootItemStack(Items.golden_boots).setWeight(1) }).setPostProcessor(new IPostProcess() {
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
		boolean found = false;

		for (int a = 0; a < 15; a++) {
			if (world.isAirBlock(x, y, z) && world.getBlock(x, y - 1, z) == Blocks.sand) {
				for (int xx = x - 4; xx <= x + 4; xx++)
					for (int zz = z - 4; zz <= z + 4; zz++)
						if (!world.isAirBlock(x, y, z) || world.getBlock(xx, y - 1, zz) != Blocks.sand)
							return false;

				found = true;
				break;
			}

			if (--y <= 12)
				return false;
		}
		if (!found)
			return false;

		for (int xx = x - 5; xx <= x + 5; xx++)
			for (int zz = z - 5; zz <= z + 5; zz++)
				for (int yy = y - 1, layer = 0; yy >= y - 7; yy--, layer++) {
					if (Math.sqrt(Math.pow(xx - x, 2) + Math.pow(zz - z, 2)) < 4.9D && yy != y - 7)
						if (yy >= y - 3 || Math.abs(xx - x) <= 1 + 6 - layer && Math.abs(zz - z) <= 1 + 6 - layer)
							world.setBlock(xx, yy, zz, yy == y - 1 ? ModBlocks.ghostSand : Blocks.air);

					if (layer > 0 && !world.isAirBlock(xx, yy, zz))
						world.setBlock(xx, yy, zz, Blocks.sand);
				}

		world.setBlock(x, y - 7, z, Blocks.chest, 0, 2);
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(x, y - 7, z);
		if (chest != null)
			LootUtil.generateLoot(chest, rand, chestLoot, 10, 14);

		EntityAntlionMiniBoss antlion = new EntityAntlionMiniBoss(world);
		antlion.setLocationAndAngles(x, y - 5, z, rand.nextFloat() * 360F, 0F);
		antlion.forceSpawn = true;
		world.spawnEntityInWorld(antlion);

		return true;
	}
}