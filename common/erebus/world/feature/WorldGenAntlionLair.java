package erebus.world.feature;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.entity.EntityAntlion;
import erebus.item.ItemErebusMaterial;
import erebus.world.loot.IPostProcess;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;

public class WorldGenAntlionLair extends WorldGenerator {

	//@formatter:off
	public static final WeightedLootList chestLoot = new WeightedLootList(
	new LootItemStack(Item.book).setAmount(1, 4).setWeight(18),
	new LootItemStack(Item.paper).setAmount(2, 6).setWeight(16),
	new LootItemStack(Block.web).setAmount(2, 7).setWeight(13),
	new LootItemStack(ModItems.erebusMaterials).setAmount(1, 3).setDamage(ItemErebusMaterial.dataJade).setWeight(10),
	new LootItemStack(ModItems.erebusMaterials).setAmount(4, 8).setDamage(ItemErebusMaterial.dataExoPlate).setWeight(9),
	new LootItemStack(Item.enchantedBook).setWeight(8),
	new LootItemStack(ModBlocks.umberGolemStatue).setAmount(1).setWeight(1),
	new LootItemStack(ModItems.webSlinger).setAmount(1).setWeight(1),

	new LootItemStack(Item.pickaxeGold).setWeight(3),
	new LootItemStack(Item.pickaxeIron).setWeight(2),
	new LootItemStack(ModItems.jadePickaxe).setWeight(1),
	new LootItemStack(Item.pickaxeStone).setWeight(1),

	new LootItemStack(Item.shovelGold).setWeight(3),
	new LootItemStack(Item.shovelIron).setWeight(2),
	new LootItemStack(ModItems.jadeShovel).setWeight(1),
	new LootItemStack(Item.shovelStone).setWeight(1),

	new LootItemStack(Item.axeGold).setWeight(3),
	new LootItemStack(Item.axeIron).setWeight(2),
	new LootItemStack(ModItems.jadeAxe).setWeight(1),
	new LootItemStack(Item.axeStone).setWeight(1),

	new LootItemStack(Item.swordGold).setWeight(3),
	new LootItemStack(Item.swordIron).setWeight(2),
	new LootItemStack(ModItems.jadeSword).setWeight(1),
	new LootItemStack(Item.swordStone).setWeight(1),

	new LootItemStack(Item.plateIron).setWeight(2),
	new LootItemStack(ModItems.jadeBody).setWeight(1),
	new LootItemStack(Item.plateGold).setWeight(1),

	new LootItemStack(Item.helmetIron).setWeight(2),
	new LootItemStack(ModItems.jadeHelmet).setWeight(1),
	new LootItemStack(Item.helmetGold).setWeight(1),

	new LootItemStack(Item.legsIron).setWeight(2),
	new LootItemStack(ModItems.jadeLegs).setWeight(1),
	new LootItemStack(Item.legsGold).setWeight(1),

	new LootItemStack(Item.bootsIron).setWeight(2),
	new LootItemStack(ModItems.jadeBoots).setWeight(1),
	new LootItemStack(Item.bootsGold).setWeight(1)
	).setPostProcessor(new IPostProcess(){
		@Override
		public ItemStack postProcessItem(ItemStack is, Random rand){
			if (is.itemID==Item.enchantedBook.itemID || (is.getItem() instanceof ItemTool || is.getItem() instanceof ItemArmor || is.getItem() instanceof ItemSword) && rand.nextInt(2) == 0){
				if (is.itemID==Item.enchantedBook.itemID)is.itemID=Item.book.itemID;
				List enchList = EnchantmentHelper.buildEnchantmentList(rand,is,7+rand.nextInt(10));
				if (is.itemID==Item.book.itemID)is.itemID=Item.enchantedBook.itemID;

				if (enchList!=null && enchList.size()>0)
					if (is.itemID==Item.enchantedBook.itemID)Item.enchantedBook.addEnchantment(is,(EnchantmentData)enchList.get(rand.nextInt(enchList.size())));
					else
						for(int a=0; a<enchList.size(); ++a){
							EnchantmentData data=(EnchantmentData)enchList.get(a);
							is.addEnchantment(data.enchantmentobj,data.enchantmentLevel);
						}
			}
			return is;
		}
	});
	//@formatter:on

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		boolean found = false;

		for (int a = 0; a < 15; a++) {
			if (world.isAirBlock(x, y, z) && world.getBlockId(x, y - 1, z) == Block.sand.blockID) {
				for (int xx = x - 4; xx <= x + 4; xx++)
					for (int zz = z - 4; zz <= z + 4; zz++)
						if (!world.isAirBlock(x, y, z) || world.getBlockId(xx, y - 1, zz) != Block.sand.blockID)
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
							world.setBlock(xx, yy, zz, yy == y - 1 ? ModBlocks.ghostSand.blockID : 0);

					if (layer > 0 && world.getBlockId(xx, yy, zz) != 0)
						world.setBlock(xx, yy, zz, Block.sand.blockID);
				}

		world.setBlock(x, y - 7, z, Block.chest.blockID, 0, 2);
		TileEntityChest chest = (TileEntityChest) world.getBlockTileEntity(x, y - 7, z);
		if (chest != null)
			LootUtil.generateLoot(chest, random, chestLoot, 10, 14);

		EntityAntlion antlion = new EntityAntlion(world);
		antlion.setIsBoss(true);
		antlion.setLocationAndAngles(x, y - 5, z, random.nextFloat() * 360F, 0F);
		antlion.forceSpawn = true;
		world.spawnEntityInWorld(antlion);

		return true;
	}
}
