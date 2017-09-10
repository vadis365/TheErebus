package erebus.world.feature.structure;

import java.util.List;
import java.util.Random;

import erebus.ModBlocks;
import erebus.ModItems;
import erebus.entity.EntityAntlionMiniBoss;
import erebus.items.ItemMaterials.EnumErebusMaterialsType;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenAntlionLair extends WorldGenerator {

	public static final WeightedLootList chestLoot = new WeightedLootList(new LootItemStack[] {
			new LootItemStack(Items.BOOK).setAmount(1, 4).setWeight(18),
			new LootItemStack(Items.PAPER).setAmount(2, 6).setWeight(16),
			new LootItemStack(Blocks.WEB).setAmount(2, 7).setWeight(13),
			new LootItemStack(ModItems.MATERIALS).setAmount(1, 3).setDamage(EnumErebusMaterialsType.JADE.ordinal()).setWeight(10),
			new LootItemStack(ModItems.MATERIALS).setAmount(4, 8).setDamage(EnumErebusMaterialsType.PLATE_EXO.ordinal()).setWeight(9),
			new LootItemStack(Items.ENCHANTED_BOOK).setWeight(8),
			new LootItemStack(Items.GOLDEN_PICKAXE).setWeight(3), new LootItemStack(Items.IRON_PICKAXE).setWeight(2),
			new LootItemStack(ModItems.JADE_PICKAXE).setWeight(1), new LootItemStack(Items.STONE_PICKAXE).setWeight(1),
			new LootItemStack(Items.GOLDEN_SHOVEL).setWeight(3), new LootItemStack(Items.IRON_SHOVEL).setWeight(2),
			new LootItemStack(ModItems.JADE_SHOVEL).setWeight(1), new LootItemStack(Items.STONE_SHOVEL).setWeight(1),
			new LootItemStack(Items.GOLDEN_AXE).setWeight(3), new LootItemStack(Items.IRON_AXE).setWeight(2),
			new LootItemStack(ModItems.JADE_AXE).setWeight(1), new LootItemStack(Items.STONE_AXE).setWeight(1),
			new LootItemStack(Items.GOLDEN_SWORD).setWeight(3), new LootItemStack(Items.IRON_SWORD).setWeight(2),
			new LootItemStack(ModItems.JADE_SWORD).setWeight(1), new LootItemStack(Items.STONE_SWORD).setWeight(1),
			new LootItemStack(Items.IRON_CHESTPLATE).setWeight(2), new LootItemStack(ModItems.JADE_CHESTPLATE).setWeight(1),
			new LootItemStack(Items.GOLDEN_CHESTPLATE).setWeight(1), new LootItemStack(Items.IRON_HELMET).setWeight(2),
			new LootItemStack(ModItems.JADE_HELMET).setWeight(1), new LootItemStack(Items.GOLDEN_HELMET).setWeight(1),
			new LootItemStack(Items.IRON_LEGGINGS).setWeight(2), new LootItemStack(ModItems.JADE_LEGGINGS).setWeight(1),
			new LootItemStack(Items.GOLDEN_LEGGINGS).setWeight(1), new LootItemStack(Items.IRON_BOOTS).setWeight(2),
			new LootItemStack(ModItems.JADE_BOOTS).setWeight(1), new LootItemStack(Items.GOLDEN_BOOTS).setWeight(1),
			// TODO
			/*	new LootItemStack(ModBlocks.umberGolemStatue).setAmount(1).setWeight(1),
			new LootItemStack(ModItems.webSlinger).setAmount(1).setWeight(1)
			*/
			})
					.setPostProcessor(new IPostProcess() {
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
		boolean found = false;
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		for (int a = 0; a < 15; a++) {
			if (world.isAirBlock(pos) && world.getBlockState(pos.down()).getBlock() == Blocks.SAND) {
				for (int xx = - 4; xx <= 4; xx++)
					for (int zz = - 4; zz <= 4; zz++)
						if (!world.isAirBlock(pos.add(xx, 0, zz)) || world.getBlockState(pos.add(xx, 0, zz).down()).getBlock() != Blocks.SAND)
							return false;

				found = true;
				break;
			}

			if (pos.add(0, -a, 0).getY() <= 12)
				return false;
		}
		if (!found)
			return false;

		for (int xx = x - 5; xx <= x + 5; xx++)
			for (int zz = z - 5; zz <= z + 5; zz++)
				for (int yy = y - 1, layer = 0; yy >= y - 7; yy--, layer++) {
					if (Math.sqrt(Math.pow(xx - x, 2) + Math.pow(zz - z, 2)) < 4.9D && yy != y - 7)
						if (yy >= y - 3 || Math.abs(xx - x) <= 1 + 6 - layer && Math.abs(zz - z) <= 1 + 6 - layer)
							world.setBlockState(new BlockPos(xx, yy, zz), yy == y - 1 ? ModBlocks.GHOST_SAND.getDefaultState() : Blocks.AIR.getDefaultState());

					if (layer > 0 && !world.isAirBlock(new BlockPos(xx, yy, zz)))
						world.setBlockState(new BlockPos(xx, yy, zz), Blocks.SAND.getDefaultState());
				}

		world.setBlockState(pos.down(7), Blocks.CHEST.getDefaultState(), 2);
		TileEntityChest chest = (TileEntityChest) world.getTileEntity(pos.down(7));
		if (chest != null)
			LootUtil.generateLoot(chest, rand, chestLoot, 10, 14);

		EntityAntlionMiniBoss antlion = new EntityAntlionMiniBoss(world);
		antlion.setLocationAndAngles(x, y - 5, z, rand.nextFloat() * 360F, 0F);
		antlion.forceSpawn = true;
		world.spawnEntity(antlion);
		System.out.println("Antlion Lair Generated: " + pos);
		return true;
	}
}