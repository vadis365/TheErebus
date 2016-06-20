package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.blocks.BlockTurnip;

public class WorldGenTurnips extends WorldGenerator {

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		for (int attempt = 0; attempt < 64; ++attempt) {
			int xx = x + rand.nextInt(8) - rand.nextInt(8);
			int yy = y + rand.nextInt(4) - rand.nextInt(4);
			int zz = z + rand.nextInt(8) - rand.nextInt(8);
			BlockPos posTurnip = new BlockPos(xx, yy, zz);
			if (world.isAirBlock(posTurnip) && world.getBlockState(posTurnip.down()) == Blocks.GRASS.getDefaultState())
				world.setBlockState(posTurnip, ModBlocks.CROP_TURNIP.getDefaultState().withProperty(BlockTurnip.AGE, Integer.valueOf(7)));
		}
		return true;
	}
}