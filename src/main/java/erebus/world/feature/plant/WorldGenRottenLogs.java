package erebus.world.feature.plant;

import java.util.List;
import java.util.Random;

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
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.item.Materials.DATA;
import erebus.world.loot.IPostProcess;
import erebus.world.loot.LootItemStack;
import erebus.world.loot.LootUtil;
import erebus.world.loot.WeightedLootList;

public class WorldGenRottenLogs extends WorldGenerator {
	
	private int length = -1;
	private int baseRadius = -1;
	private int	maxRadius =	6;
	private int	maxHeight =	15;
	private int direction;
	public WorldGenRottenLogs(int length, int baseRadius, byte direction)
	{
		this.length = length;
		this.baseRadius = baseRadius;
		this.direction = direction;
	}
	
	public boolean generate(World world, Random rand, int x, int y, int z) {
	/*	//Not done this bit yet - do not touch
		for (int x1 = x - baseRadius; x1 <= x + baseRadius; x1++)
			for (int z1 = z - baseRadius; z1 <= z + baseRadius; z1++)
				for (int y1 = y + 1; y1 < y + length; y1++) {
					if (!world.isAirBlock(x1, y1, z1))
						return false;
				}
				
		*/

		// Trunk N/S
		if(direction == 1) {
			
		for (int zz = z-length; length + z >= zz; zz++) {
			for (int i = baseRadius * - 1; i <= baseRadius; ++i) {
				for (int j = baseRadius * -1; j <= baseRadius; ++j) {
					double dSq = (i * i) + (j * j);
					if (Math.round(Math.sqrt(dSq)) == baseRadius) {
						world.setBlock(x + i, y + j + baseRadius, zz, ModBlocks.rottenWood);
						if(rand.nextInt(12) == 0)
							world.setBlock(x + i, y + j + baseRadius,  zz, Blocks.air);
						if(zz == z - length && rand.nextInt(2) == 0||zz == z + length && rand.nextInt(2) == 0)
							world.setBlock(x + i,  y + j + baseRadius, zz, Blocks.air);
					} else {
						world.setBlock(x + i, y + j + baseRadius, zz, Blocks.air);
					}
				}
			}
		}
		
		}
		else {
		// Trunk E/W
		for (int xx = x-length; length + x >= xx; xx++) {
			for (int i = baseRadius * - 1; i <= baseRadius; ++i) {
				for (int j = baseRadius * -1; j <= baseRadius; ++j) {
					double dSq = (i * i) + (j * j);
					if (Math.round(Math.sqrt(dSq)) == baseRadius) {
						world.setBlock(xx , y + j +baseRadius, z + i, ModBlocks.rottenWood);
						if(rand.nextInt(12) == 0)
							world.setBlock(xx, y + j + baseRadius, z + i, Blocks.air);
						if(xx == x - length && rand.nextInt(2) == 0||xx == x + length && rand.nextInt(2) == 0)
							world.setBlock(xx, y + j + baseRadius, z + i, Blocks.air);
					} else {
						world.setBlock(xx , y+j+baseRadius, z + i, Blocks.air);
					}
				}
			}
		}
			
		}
		return true;
	}
}
