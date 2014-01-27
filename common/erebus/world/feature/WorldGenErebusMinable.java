package erebus.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenErebusMinable extends WorldGenerator {

	private final int minableBlockId;
	private int minableBlockMeta = 0;

	private int numberOfBlocks;
	private final int blockToReplace;

	public WorldGenErebusMinable(int id, int meta, int numberOfBlocks) {
		this(id, meta, numberOfBlocks, ModBlocks.umberstone.blockID);
	}

	public WorldGenErebusMinable(int id, int meta, int numberOfBlocks, int blockToReplace) {
		minableBlockId = id;
		minableBlockMeta = meta;
		this.numberOfBlocks = numberOfBlocks;
		this.blockToReplace = blockToReplace;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		float f = rand.nextFloat() * (float) Math.PI;
		double d0 = x + 8 + MathHelper.sin(f) * numberOfBlocks / 8.0F;
		double d1 = x + 8 - MathHelper.sin(f) * numberOfBlocks / 8.0F;
		double d2 = z + 8 + MathHelper.cos(f) * numberOfBlocks / 8.0F;
		double d3 = z + 8 - MathHelper.cos(f) * numberOfBlocks / 8.0F;
		double d4 = y + rand.nextInt(3) - 2;
		double d5 = y + rand.nextInt(3) - 2;

		int realNumberOfBlocks = numberOfBlocks;
		numberOfBlocks = (int) Math.ceil(numberOfBlocks * (1.15F + rand.nextFloat() * 0.25F));

		for (int attempt = 0, placed = 0; attempt <= numberOfBlocks && placed <= realNumberOfBlocks; ++attempt) {
			double d6 = d0 + (d1 - d0) * placed / numberOfBlocks;
			double d7 = d4 + (d5 - d4) * placed / numberOfBlocks;
			double d8 = d2 + (d3 - d2) * placed / numberOfBlocks;
			double d9 = rand.nextDouble() * numberOfBlocks / 16.0D;
			double d10 = (MathHelper.sin(placed * (float) Math.PI / numberOfBlocks) + 1.0F) * d9 + 1.0D;
			double d11 = (MathHelper.sin(placed * (float) Math.PI / numberOfBlocks) + 1.0F) * d9 + 1.0D;
			int i1 = MathHelper.floor_double(d6 - d10 / 2.0D);
			int j1 = MathHelper.floor_double(d7 - d11 / 2.0D);
			int k1 = MathHelper.floor_double(d8 - d10 / 2.0D);
			int l1 = MathHelper.floor_double(d6 + d10 / 2.0D);
			int i2 = MathHelper.floor_double(d7 + d11 / 2.0D);
			int j2 = MathHelper.floor_double(d8 + d10 / 2.0D);

			for (int k2 = i1; k2 <= l1; ++k2) {
				double d12 = (k2 + 0.5D - d6) / (d10 / 2.0D);

				if (d12 * d12 < 1.0D)
					for (int l2 = j1; l2 <= i2; ++l2) {
						double d13 = (l2 + 0.5D - d7) / (d11 / 2.0D);

						if (d12 * d12 + d13 * d13 < 1.0D)
							for (int i3 = k1; i3 <= j2; ++i3) {
								double d14 = (i3 + 0.5D - d8) / (d10 / 2.0D);

								Block block = Block.blocksList[world.getBlockId(k2, l2, i3)];
								if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && block != null && block.isGenMineableReplaceable(world, k2, l2, i3, blockToReplace)) {
									world.setBlock(k2, l2, i3, minableBlockId, minableBlockMeta, 2);
									++placed;
								}
							}
					}
			}
		}

		return true;
	}
}
