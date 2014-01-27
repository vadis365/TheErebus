package erebus.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenTurnips extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, int par3, int par4, int par5) {
		for (int var6 = 0; var6 < 64; ++var6) {
			int var7 = par3 + rand.nextInt(8) - rand.nextInt(8);
			int var8 = par4 + rand.nextInt(4) - rand.nextInt(4);
			int var9 = par5 + rand.nextInt(8) - rand.nextInt(8);

			if (world.isAirBlock(var7, var8, var9) && world.getBlockId(var7, var8 - 1, var9) == Block.grass.blockID)
				world.setBlock(var7, var8, var9, ModBlocks.blockTurnip.blockID, 10, 3);
		}

		return true;
	}
}
