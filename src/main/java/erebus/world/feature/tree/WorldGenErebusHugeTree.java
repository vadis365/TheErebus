package erebus.world.feature.tree;

import java.util.Random;

import erebus.ModBlocks;
import erebus.lib.EnumWood;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenErebusHugeTree extends WorldGenerator {
	private int baseHeight;
	private final int leavesMetadata;
	private final boolean thorns;
	private final Block woodBlock;
	private final Block leafBlock;

	public WorldGenErebusHugeTree(boolean par1, boolean par5, EnumWood wood) {
		super(par1);
		leavesMetadata = 0;
		thorns = par5;
		woodBlock = wood.getLog();
		leafBlock = wood.getLeaves();
	}

	public void prepare(int baseHeight) {
		this.baseHeight = baseHeight;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int height = rand.nextInt(3) + baseHeight;
		boolean var7 = true;
		Block block;

		if (y >= 1 && y + height + 1 <= 256) {
			int var8;
			int var10;
			int var11;
			int var12;

			for (var8 = y; var8 <= y + 1 + height; ++var8) {
				byte var9 = 2;

				if (var8 == y)
					var9 = 1;

				if (var8 >= y + 1 + height - 2)
					var9 = 2;

				for (var10 = x - var9; var10 <= x + var9 && var7; ++var10)
					for (var11 = z - var9; var11 <= z + var9 && var7; ++var11)
						if (var8 >= 0 && var8 < 256) {
							block = world.getBlock(var10, var8, var11);
							if (!block.isLeaves(world, var10, var8, var11) && block != Blocks.grass && block != Blocks.dirt && !block.isWood(world, var10, var8, var11) && !block.isReplaceable(world, var10, var8, var11))
								var7 = false;
						} else
							var7 = false;
			}

			if (!var7)
				return false;
			else {
				block = world.getBlock(x, y - 1, z);

				if ((block == Blocks.grass || block == Blocks.dirt) && y < 256 - height - 1) {
					world.setBlock(x, y - 1, z, Blocks.dirt);
					world.setBlock(x + 1, y - 1, z, Blocks.dirt);
					world.setBlock(x, y - 1, z + 1, Blocks.dirt);
					world.setBlock(x + 1, y - 1, z + 1, Blocks.dirt);
					growLeaves(world, x, z, y + height, 2, rand);

					for (int var14 = y + height - 2 - rand.nextInt(4); var14 > y + height / 2; var14 -= 2 + rand.nextInt(4)) {
						float var15 = rand.nextFloat() * (float) Math.PI * 2.0F;
						var11 = x + (int) (0.5F + MathHelper.cos(var15) * 4.0F);
						var12 = z + (int) (0.5F + MathHelper.sin(var15) * 4.0F);
						growLeaves(world, var11, var12, var14, 0, rand);

						for (int var13 = 0; var13 < 5; ++var13) {
							var11 = x + (int) (1.5F + MathHelper.cos(var15) * var13);
							var12 = z + (int) (1.5F + MathHelper.sin(var15) * var13);
							world.setBlock(var11, var14 - 3 + var13 / 2, var12, woodBlock, 0, 2);
						}
					}

					for (var10 = 0; var10 < height; ++var10) {
						block = world.getBlock(x, y + var10, z);

						if (block.isReplaceable(world, x, y + var10, z) || block.isLeaves(world, x, y + var10, z)) {
							world.setBlock(x, y + var10, z, woodBlock, 0, 2);

							if (var10 > 0) {
								if (rand.nextInt(3) > 0 && world.isAirBlock(x - 1, y + var10, z) && thorns == true)
									world.setBlock(x - 1, y + var10, z, ModBlocks.thorns, 8, 2);

								if (rand.nextInt(3) > 0 && world.isAirBlock(x, y + var10, z - 1) && thorns == true)
									world.setBlock(x, y + var10, z - 1, ModBlocks.thorns, 1, 2);
							}
						}

						if (var10 < height - 1) {
							block = world.getBlock(x + 1, y + var10, z);

							if (block.isReplaceable(world, x + 1, y + var10, z) || block.isLeaves(world, x + 1, y + var10, z)) {
								world.setBlock(x + 1, y + var10, z, woodBlock, 0, 2);

								if (var10 > 0) {
									if (rand.nextInt(3) > 0 && world.isAirBlock(x + 2, y + var10, z) && thorns == true)
										world.setBlock(x + 2, y + var10, z, ModBlocks.thorns, 2, 2);

									if (rand.nextInt(3) > 0 && world.isAirBlock(x + 1, y + var10, z - 1) && thorns == true)
										world.setBlock(x + 1, y + var10, z - 1, ModBlocks.thorns, 1, 2);
								}
							}

							block = world.getBlock(x + 1, y + var10, z + 1);

							if (block.isReplaceable(world, x + 1, y + var10, z + 1) || block.isLeaves(world, x + 1, y + var10, z + 1)) {
								world.setBlock(x + 1, y + var10, z + 1, woodBlock, 0, 2);

								if (var10 > 0) {
									if (rand.nextInt(3) > 0 && world.isAirBlock(x + 2, y + var10, z + 1) && thorns == true)
										world.setBlock(x + 2, y + var10, z + 1, ModBlocks.thorns, 2, 2);

									if (rand.nextInt(3) > 0 && world.isAirBlock(x + 1, y + var10, z + 2) && thorns == true)
										world.setBlock(x + 1, y + var10, z + 2, ModBlocks.thorns, 4, 2);
								}
							}

							block = world.getBlock(x, y + var10, z + 1);

							if (block.isReplaceable(world, x, y + var10, z + 1) || block.isLeaves(world, x, y + var10, z + 1)) {
								world.setBlock(x, y + var10, z + 1, woodBlock, 0, 2);

								if (var10 > 0) {
									if (rand.nextInt(3) > 0 && world.isAirBlock(x - 1, y + var10, z + 1) && thorns == true)
										world.setBlock(x - 1, y + var10, z + 1, ModBlocks.thorns, 8, 2);

									if (rand.nextInt(3) > 0 && world.isAirBlock(x, y + var10, z + 2) && thorns == true)
										world.setBlock(x, y + var10, z + 2, ModBlocks.thorns, 4, 2);
								}
							}
						}
					}

					return true;
				} else
					return false;
			}
		} else
			return false;
	}

	private void growLeaves(World world, int par2, int par3, int par4, int par5, Random par6Random) {
		byte var7 = 2;

		for (int var8 = par4 - var7; var8 <= par4; ++var8) {
			int var9 = var8 - par4;
			int var10 = par5 + 1 - var9;

			for (int var11 = par2 - var10; var11 <= par2 + var10 + 1; ++var11) {
				int var12 = var11 - par2;

				for (int var13 = par3 - var10; var13 <= par3 + var10 + 1; ++var13) {
					int var14 = var13 - par3;

					Block block = world.getBlock(var11, var8, var13);

					if ((var12 >= 0 || var14 >= 0 || var12 * var12 + var14 * var14 <= var10 * var10) && (var12 <= 0 && var14 <= 0 || var12 * var12 + var14 * var14 <= (var10 + 1) * (var10 + 1)) && (par6Random.nextInt(4) != 0 || var12 * var12 + var14 * var14 <= (var10 - 1) * (var10 - 1)) && (block == null || block.canBeReplacedByLeaves(world, var11, var8, var13)))
						world.setBlock(var11, var8, var13, leafBlock, leavesMetadata, 2);
				}
			}
		}
	}
}