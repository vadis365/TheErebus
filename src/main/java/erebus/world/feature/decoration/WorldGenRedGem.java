package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

//@formatter:off
public class WorldGenRedGem extends WorldGenerator{

	private static final byte[] offsetX = new byte[]{ -1, 1, 0, 0, 0, 0 }, offsetY = new byte[]{ 0, 0, -1, 1, 0, 0 }, offsetZ = new byte[]{ 0, 0, 0, 0, -1, 1 };

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z){
		if (!world.isAirBlock(x,y,z) || world.getBlock(x,y + 1,z) != ModBlocks.umberstone)
			return false;

		world.setBlock(x,y,z,ModBlocks.redGem);

		for(int attempt = 0, xx, yy, zz, dist = 2, distUpd = 0, attCount = rand.nextInt(100) + 300; attempt < attCount; ++attempt){
			xx = x + rand.nextInt(dist) - rand.nextInt(dist);
			yy = y - rand.nextInt(dist + 4);
			zz = z + rand.nextInt(dist) - rand.nextInt(dist);

			if (world.isAirBlock(xx,yy,zz)){
				int adjacent = 0;

				for(int side = 0; side < 6; ++side)
					if (world.getBlock(xx + offsetX[side],yy + offsetY[side],zz + offsetZ[side]) == ModBlocks.redGem)
						++adjacent;

				if (adjacent == 1)
					world.setBlock(xx,yy,zz,ModBlocks.redGem);
			}

			if (++distUpd > 22 + dist * 30){
				dist = Math.min(8,dist + 1);
				distUpd = 0;
			}
		}

		return true;
	}
}
// @formatter:on