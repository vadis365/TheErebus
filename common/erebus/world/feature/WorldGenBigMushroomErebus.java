package erebus.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenBigMushroomErebus extends WorldGenerator {

	private int mushroomType = -1;

	public WorldGenBigMushroomErebus(int par1) {
		super(true);
		mushroomType = par1;
	}

	public WorldGenBigMushroomErebus() {
		super(false);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int var6 = rand.nextInt(2);

		if (mushroomType >= 0)
			var6 = mushroomType;

		int var7 = rand.nextInt(3) + 4;
		boolean var8 = true;

		if (y >= 1 && y + var7 + 1 < 256) {
			int var9;
			int var11;
			int var12;
			int var13;

			for (var9 = y; var9 <= y + 1 + var7; ++var9) {
				byte var10 = 3;

				if (var9 <= y + 3)
					var10 = 0;

				for (var11 = x - var10; var11 <= x + var10 && var8; ++var11)
					for (var12 = z - var10; var12 <= z + var10 && var8; ++var12)
						if (var9 >= 0 && var9 < 256) {
							var13 = world.getBlockId(var11, var9, var12);

							Block block = Block.blocksList[var13];

							if (var13 != 0 && block != null && !block.isLeaves(world, var11, var9, var12))
								var8 = false;
						} else
							var8 = false;
			}

			if (!var8)
				return false;
			else {
				var9 = world.getBlockId(x, y - 1, z);

				if (var9 != ModBlocks.umberstone.blockID && var9 != Block.obsidian.blockID && var9 != Block.glowStone.blockID)
					return false;
				else {
					int var16 = y + var7;

					if (var6 == 1)
						var16 = y + var7 - 3;

					for (var11 = var16; var11 <= y + var7; ++var11) {
						var12 = 1;

						if (var11 < y + var7)
							++var12;

						if (var6 == 0)
							var12 = 3;

						for (var13 = x - var12; var13 <= x + var12; ++var13)
							for (int var14 = z - var12; var14 <= z + var12; ++var14) {
								int var15 = 5;

								if (var13 == x - var12)
									--var15;

								if (var13 == x + var12)
									++var15;

								if (var14 == z - var12)
									var15 -= 3;

								if (var14 == z + var12)
									var15 += 3;

								if (var6 == 0 || var11 < y + var7) {
									if ((var13 == x - var12 || var13 == x + var12) && (var14 == z - var12 || var14 == z + var12))
										continue;

									if (var13 == x - (var12 - 1) && var14 == z - var12)
										var15 = 1;

									if (var13 == x - var12 && var14 == z - (var12 - 1))
										var15 = 1;

									if (var13 == x + var12 - 1 && var14 == z - var12)
										var15 = 3;

									if (var13 == x + var12 && var14 == z - (var12 - 1))
										var15 = 3;

									if (var13 == x - (var12 - 1) && var14 == z + var12)
										var15 = 7;

									if (var13 == x - var12 && var14 == z + var12 - 1)
										var15 = 7;

									if (var13 == x + var12 - 1 && var14 == z + var12)
										var15 = 9;

									if (var13 == x + var12 && var14 == z + var12 - 1)
										var15 = 9;
								}

								if (var15 == 5 && var11 < y + var7)
									var15 = 0;

								Block block = Block.blocksList[world.getBlockId(var13, var11, var14)];

								if ((var15 != 0 || y >= y + var7 - 1) && (block == null || block.canBeReplacedByLeaves(world, var13, var11, var14)))
									setBlockAndMetadata(world, var13, var11, var14, Block.mushroomCapBrown.blockID + var6, var15);
							}
					}

					for (var11 = 0; var11 < var7; ++var11) {
						var12 = world.getBlockId(x, y + var11, z);

						Block block = Block.blocksList[var12];

						if (block == null || block.canBeReplacedByLeaves(world, x, y + var11, z))
							setBlockAndMetadata(world, x, y + var11, z, Block.mushroomCapBrown.blockID + var6, 10);
					}

					return true;
				}
			}
		} else
			return false;
	}
}
