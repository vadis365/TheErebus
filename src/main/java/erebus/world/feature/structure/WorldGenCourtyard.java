package erebus.world.feature.structure;

import java.util.Random;

import erebus.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
// Helper class to edit - to test the gen of each part
public class WorldGenCourtyard extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		buildCourtyard(world, ModBlocks.templePillar, 0, x, y, z, 32, 4, 32);
		return true;
	}
	
	private void buildCourtyard(World world, Block block, int metaData, int x, int y, int z, int baseLengthX, int heightY, int baseLengthZ) {
		for (int yy = y; yy < heightY + y; yy++) {
			for (int xx = x - baseLengthX / 2; xx < x + baseLengthX / 2; xx++)
				for (int zz = z - baseLengthZ / 2; zz < z + baseLengthZ / 2; zz++) {
					if (yy > y) {
						world.setBlockToAir(xx, yy, zz);
						if (xx == x - baseLengthX / 2 || xx == x + baseLengthX / 2 - 1)
							if (zz > z - baseLengthZ / 2 && zz < z + baseLengthZ / 2)
								for(int i = 3; i < 29; i += 5)
									world.setBlock(xx, yy, z - baseLengthZ / 2 + i, block, metaData, 3);
						
						if (zz == z - baseLengthZ / 2 || zz == z + baseLengthZ / 2 - 1)
							if (xx > x - baseLengthX / 2 && xx < x + baseLengthX / 2)
								for(int i = 3; i < 29; i += 5)
									world.setBlock(x - baseLengthZ / 2 + i, yy, zz, block, metaData, 3);
					}
					world.setBlock(xx, y + 4, zz, ModBlocks.templeBrick, 0, 3);

					if (xx > x - baseLengthX / 2 && xx < x + baseLengthX / 2 - 1)
						if (zz > z - baseLengthZ / 2 && zz < z + baseLengthZ / 2 - 1)
							world.setBlockToAir(xx, y + 4, zz);
				}
		}
	}
}