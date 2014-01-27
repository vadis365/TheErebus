package erebus.world.feature;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenAmberUmberstone extends WorldGenerator {

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		if (world.getBlockId(x, y, z) != ModBlocks.umberstone.blockID)
			return false;

		float rad = random.nextFloat() + 2.6F;
		int ceilRad = 1 + (int) Math.ceil(rad);

		for (int xx = -ceilRad; xx <= ceilRad; xx++)
			for (int yy = -ceilRad; yy <= ceilRad; yy++)
				for (int zz = -ceilRad; zz <= ceilRad; zz++)
					if (Math.sqrt(xx * xx + yy * yy + zz * zz) <= rad + random.nextFloat() * 0.4F && world.getBlockId(x + xx, y + yy, z + zz) == ModBlocks.umberstone.blockID)
						world.setBlock(x + xx, y + yy, z + zz, ModBlocks.blockAmber.blockID);

		return true;
	}
}
