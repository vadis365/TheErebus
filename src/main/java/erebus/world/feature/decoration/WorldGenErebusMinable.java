package erebus.world.feature.decoration;

import java.util.Random;

import erebus.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenErebusMinable extends WorldGenerator {
	private Block minableBlock;
	private int minableBlockMeta = 0;
	private int numberOfBlocks;
	private Block blockToReplace;

	public void prepare(Block block, int meta, int numberOfBlocks) {
		minableBlock = block;
		minableBlockMeta = meta;
		this.numberOfBlocks = numberOfBlocks;
		blockToReplace = ModBlocks.umberstone;
	}

	public void prepare(Block block, int meta, int numberOfBlocks, Block blockToReplace) {
		minableBlock = block;
		minableBlockMeta = meta;
		this.numberOfBlocks = numberOfBlocks;
		this.blockToReplace = blockToReplace;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		float f = rand.nextFloat() * (float) Math.PI;
		double d0 = x + 8 + MathHelper.sin(f) * numberOfBlocks * 0.125F;
		double d1 = x + 8 - MathHelper.sin(f) * numberOfBlocks * 0.125F;
		double d2 = z + 8 + MathHelper.cos(f) * numberOfBlocks * 0.125F;
		double d3 = z + 8 - MathHelper.cos(f) * numberOfBlocks * 0.125F;
		double d4 = y + rand.nextInt(3) - 2;
		double d5 = y + rand.nextInt(3) - 2;

		int realNumberOfBlocks = numberOfBlocks;
		numberOfBlocks = (int) Math.ceil(numberOfBlocks * (1.15F + rand.nextFloat() * 0.25F));

		for (int attempt = 0, placed = 0; attempt <= numberOfBlocks && placed <= realNumberOfBlocks; ++attempt) {
			double centerX = d0 + (d1 - d0) * placed / numberOfBlocks;
			double centerY = d4 + (d5 - d4) * placed / numberOfBlocks;
			double centerZ = d2 + (d3 - d2) * placed / numberOfBlocks;
			double spreadFactor = rand.nextDouble() * numberOfBlocks * 0.0625D;
			double maxDistXZ = (MathHelper.sin(placed * (float) Math.PI / numberOfBlocks) + 1F) * spreadFactor + 1D;
			double maxDistY = (MathHelper.sin(placed * (float) Math.PI / numberOfBlocks) + 1F) * spreadFactor + 1D;
			int minX = MathHelper.floor_double(centerX - maxDistXZ * 0.5D);
			int minY = MathHelper.floor_double(centerY - maxDistY * 0.5D);
			int minZ = MathHelper.floor_double(centerZ - maxDistXZ * 0.5D);
			int maxX = MathHelper.floor_double(centerX + maxDistXZ * 0.5D);
			int maxY = MathHelper.floor_double(centerY + maxDistY * 0.5D);
			int maxZ = MathHelper.floor_double(centerZ + maxDistXZ * 0.5D);

			for (int xx = minX; xx <= maxX; ++xx) {
				double d12 = (xx + 0.5D - centerX) / (maxDistXZ * 0.5D);

				if (d12 * d12 < 1.0D)
					for (int yy = minY; yy <= maxY; ++yy) {
						double d13 = (yy + 0.5D - centerY) / (maxDistY * 0.5D);

						if (d12 * d12 + d13 * d13 < 1.0D)
							for (int zz = minZ; zz <= maxZ; ++zz) {
								double d14 = (zz + 0.5D - centerZ) / (maxDistXZ * 0.5D);

								if (d12 * d12 + d13 * d13 + d14 * d14 >= 1D)
									continue;

								Block block = world.getBlock(xx, yy, zz);
								if (block != null && block.isReplaceableOreGen(world, xx, yy, zz, blockToReplace)) {
									world.setBlock(xx, yy, zz, minableBlock, minableBlockMeta, 2);
									++placed;
								}
							}
					}
			}
		}

		return true;
	}
}