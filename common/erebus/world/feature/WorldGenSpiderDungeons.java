package erebus.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;

public class WorldGenSpiderDungeons extends WorldGenerator {

	//@formatter:off
	public static final WeightedLootList chestLoot = new WeightedLootList(
	new LootItemStack(Item.silk).setAmount(5, 10).setWeight(13),
	new LootItemStack(Block.web).setAmount(3, 8).setWeight(13),
	new LootItemStack(Item.stick).setAmount(1, 8).setWeight(12),
	new LootItemStack(Item.goldNugget).setAmount(3, 11).setWeight(12),
	new LootItemStack(ModItems.erebusMaterials).setAmount(3, 8).setDamage(ItemErebusMaterial.dataBoneShard).setWeight(12),
	new LootItemStack(Item.bone).setAmount(1, 3).setWeight(11),
	new LootItemStack(Item.ingotIron).setAmount(1, 3).setWeight(10),
	new LootItemStack(Item.ingotGold).setAmount(1, 2).setWeight(10),
	new LootItemStack(ModItems.erebusMaterials).setAmount(1, 5).setDamage(ItemErebusMaterial.dataFlyWing).setWeight(10),
	new LootItemStack(ModItems.erebusMaterials).setAmount(1).setDamage(ItemErebusMaterial.dataJade).setWeight(9),
	new LootItemStack(ModItems.erebusMaterials).setAmount(3, 6).setDamage(ItemErebusMaterial.dataExoPlate).setWeight(8),
	new LootItemStack(ModItems.erebusMaterials).setAmount(2, 6).setDamage(ItemErebusMaterial.dataCompoundEyes).setWeight(7),
	new LootItemStack(ModItems.erebusMaterials).setAmount(1, 1).setDamage(ItemErebusMaterial.dataCompoundLens).setWeight(2),
	new LootItemStack(ModItems.maxSpeedBow).setAmount(1).setWeight(1),
	new LootItemStack(ModBlocks.umberGolemStatue).setAmount(1).setWeight(1),
	new LootItemStack(ModItems.webSlinger).setAmount(1).setWeight(1)
	);
	//@formatter:on

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		byte b0 = 3;
		int l = rand.nextInt(2) + 2;
		int i1 = rand.nextInt(2) + 2;
		int j1 = 0;
		int k1;
		int l1;
		int i2;

		for (k1 = x - l - 1; k1 <= x + l + 1; ++k1)
			for (l1 = y - 1; l1 <= y + b0 + 1; ++l1)
				for (i2 = z - i1 - 1; i2 <= z + i1 + 1; ++i2) {
					Material material = world.getBlockMaterial(k1, l1, i2);

					if (l1 == y - 1 && !material.isSolid())
						return false;

					if (l1 == y + b0 + 1 && !material.isSolid())
						return false;

					if ((k1 == x - l - 1 || k1 == x + l + 1 || i2 == z - i1 - 1 || i2 == z + i1 + 1) && l1 == y && world.isAirBlock(k1, l1, i2) && world.isAirBlock(k1, l1 + 1, i2))
						++j1;
				}

		if (j1 >= 1 && j1 <= 5) {
			for (k1 = x - l - 1; k1 <= x + l + 1; ++k1)
				for (l1 = y + b0; l1 >= y - 1; --l1)
					for (i2 = z - i1 - 1; i2 <= z + i1 + 1; ++i2)
						if (k1 != x - l - 1 && l1 != y - 1 && i2 != z - i1 - 1 && k1 != x + l + 1 && l1 != y + b0 + 1 && i2 != z + i1 + 1)
							world.setBlockToAir(k1, l1, i2);
						else if (l1 >= 0 && !world.getBlockMaterial(k1, l1 - 1, i2).isSolid())
							world.setBlockToAir(k1, l1, i2);
						else if (world.getBlockMaterial(k1, l1, i2).isSolid())
							if (l1 == y - 1 && rand.nextInt(4) == 0)
								world.setBlock(k1, l1, i2, ModBlocks.umberstone.blockID, 3, 2); // umbercobbleWebbed
							else if (l1 == y - 1 && rand.nextInt(4) != 0)
								world.setBlock(k1, l1, i2, ModBlocks.umberstone.blockID, 2, 2); // umbercobbleMossy
							else
								world.setBlock(k1, l1, i2, ModBlocks.umberstone.blockID, 1, 2); // umbercobble

			k1 = 0;

			while (k1 < 2) {
				l1 = 0;

				while (true) {
					if (l1 < 3)
						label101: {
							i2 = x + rand.nextInt(l * 2 + 1) - l;
							int j2 = z + rand.nextInt(i1 * 2 + 1) - i1;

							if (world.isAirBlock(i2, y, j2)) {
								int k2 = 0;

								if (world.getBlockMaterial(i2 - 1, y, j2).isSolid())
									++k2;

								if (world.getBlockMaterial(i2 + 1, y, j2).isSolid())
									++k2;

								if (world.getBlockMaterial(i2, y, j2 - 1).isSolid())
									++k2;

								if (world.getBlockMaterial(i2, y, j2 + 1).isSolid())
									++k2;

								if (k2 == 1) {
									world.setBlock(i2, y, j2, Block.chest.blockID, 0, 2);
									TileEntityChest tileentitychest = (TileEntityChest) world.getBlockTileEntity(i2, y, j2);

									if (tileentitychest != null)
										LootUtil.generateLoot(tileentitychest, rand, chestLoot, 3, 10);

									break label101;
								}
							}

							++l1;
							continue;
						}

					++k1;
					break;
				}
			}

			world.setBlock(x + 1, y, z, Block.web.blockID, 0, 2);
			world.setBlock(x - 1, y, z, Block.web.blockID, 0, 2);
			world.setBlock(x, y, z - 1, Block.web.blockID, 0, 2);
			world.setBlock(x, y, z + 1, Block.web.blockID, 0, 2);
			world.setBlock(x, y + 1, z, Block.web.blockID, 0, 2);
			world.setBlock(x, y - 1, z, Block.web.blockID, 0, 2);

			world.setBlock(x, y, z, ModBlocks.spiderSpawner.blockID, 0, 2);
			return true;
		} else
			return false;
	}

}
