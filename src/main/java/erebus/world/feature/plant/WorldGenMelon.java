package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMelon extends WorldGenerator {
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		boolean canSpawn = rand.nextBoolean() && rand.nextBoolean();
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if (!canSpawn)
			for (int waterAttempt = 0; waterAttempt < 30; waterAttempt++)
				if (world.getBlockState(new BlockPos(x + rand.nextInt(8) - rand.nextInt(8), y + rand.nextInt(4) - rand.nextInt(4), z + rand.nextInt(8) - rand.nextInt(8))).getMaterial() == Material.WATER) {
					canSpawn = true;
					break;
				}

		if (!canSpawn)
			return false;

		for (int attempt = 0, xx, yy, zz; attempt < 64; ++attempt) {
			xx = x + rand.nextInt(8) - rand.nextInt(8);
			yy = y + rand.nextInt(4) - rand.nextInt(4);
			zz = z + rand.nextInt(8) - rand.nextInt(8);
			BlockPos posMelon = new BlockPos(xx, yy, zz);
			if (world.isAirBlock(posMelon) && world.getBlockState(posMelon.down()) == Blocks.GRASS.getDefaultState() && Blocks.MELON_BLOCK.canPlaceBlockAt(world, posMelon))
				world.setBlockState(posMelon, Blocks.MELON_BLOCK.getDefaultState(), 3);
		}

		return true;
	}

}