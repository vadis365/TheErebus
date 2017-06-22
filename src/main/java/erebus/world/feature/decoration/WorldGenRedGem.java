package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

//@formatter:off
public class WorldGenRedGem extends WorldGenerator{

	private static final byte[] offsetX = new byte[]{ -1, 1, 0, 0, 0, 0 }, offsetY = new byte[]{ 0, 0, -1, 1, 0, 0 }, offsetZ = new byte[]{ 0, 0, 0, 0, -1, 1 };

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		if (!world.isAirBlock(pos) || world.getBlockState(pos.up()).getBlock() != ModBlocks.UMBERSTONE)
			return false;

		world.setBlockState(pos, ModBlocks.RED_GEM.getDefaultState());

		for(int attempt = 0, xx, yy, zz, dist = 2, distUpd = 0, attCount = rand.nextInt(100) + 300; attempt < attCount; ++attempt){
			xx = pos.getX() + rand.nextInt(dist) - rand.nextInt(dist);
			yy = pos.getY() - rand.nextInt(dist + 4);
			zz = pos.getZ() + rand.nextInt(dist) - rand.nextInt(dist);

			if (world.isAirBlock(new BlockPos(xx, yy, zz))){
				int adjacent = 0;

				for(int side = 0; side < 6; ++side)
					if (world.getBlockState(new BlockPos(xx + offsetX[side],yy + offsetY[side],zz + offsetZ[side])).getBlock() == ModBlocks.RED_GEM)
						++adjacent;

				if (adjacent == 1)
					if(world.isBlockLoaded(new BlockPos(xx,yy,zz)))
						world.setBlockState(new BlockPos(xx, yy, zz), ModBlocks.RED_GEM.getDefaultState());
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