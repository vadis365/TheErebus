package erebus.world.feature.tree;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenErebusHugeTree extends WorldGenerator { // TODO

	private int baseHeight;
	private final int woodMetadata;
	private final int leavesMetadata;
	private final boolean thorns;
	private final int woodID;
	private final int leavesID;

	public WorldGenErebusHugeTree(boolean par1, int par3, int par4, boolean par5, int par6, int par7) {
		super(par1);
		woodMetadata = par3;
		leavesMetadata = par4;
		thorns = par5;
		woodID = par6;
		leavesID = par7;
	}

	public void prepare(int baseHeight) {
		this.baseHeight = baseHeight;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int var6 = rand.nextInt(3) + baseHeight;
		boolean var7 = true;

		if (y >= 1 && y + var6 + 1 <= 256) {
			int var8;
			int var10;
			int var11;
			int var12;

			for (var8 = y; var8 <= y + 1 + var6; ++var8) {
				byte var9 = 2;

				if (var8 == y)
					var9 = 1;

				if (var8 >= y + 1 + var6 - 2)
					var9 = 2;

				for (var10 = x - var9; var10 <= x + var9 && var7; ++var10)
					for (var11 = z - var9; var11 <= z + var9 && var7; ++var11)
						if (var8 >= 0 && var8 < 256) {
							var12 = world.getBlock(var10, var8, var11);

							if (var12 != 0 && Blocks.blocksList[var12] != null && !Blocks.blocksList[var12].isLeaves(world, var10, var8, var11) && var12 != Blocks.grass && var12 != Blocks.dirt && Blocks.blocksList[var12] != null && !Blocks.blocksList[var12].isWood(world, var10, var8, var11) && var12 != Blocks.sapling)
								var7 = false;
						} else
							var7 = false;
			}

			if (!var7)
				return false;
			else {
				var8 = world.getBlock(x, y - 1, z);

				if ((var8 == Blocks.grass || var8 == Blocks.dirt) && y < 256 - var6 - 1) {
					world.setBlock(x, y - 1, z, Blocks.dirt);
					world.setBlock(x + 1, y - 1, z, Blocks.dirt);
					world.setBlock(x, y - 1, z + 1, Blocks.dirt);
					world.setBlock(x + 1, y - 1, z + 1, Blocks.dirt);
					growLeaves(world, x, z, y + var6, 2, rand);

					for (int var14 = y + var6 - 2 - rand.nextInt(4); var14 > y + var6 / 2; var14 -= 2 + rand.nextInt(4)) {
						float var15 = rand.nextFloat() * (float) Math.PI * 2.0F;
						var11 = x + (int) (0.5F + MathHelper.cos(var15) * 4.0F);
						var12 = z + (int) (0.5F + MathHelper.sin(var15) * 4.0F);
						growLeaves(world, var11, var12, var14, 0, rand);

						for (int var13 = 0; var13 < 5; ++var13) {
							var11 = x + (int) (1.5F + MathHelper.cos(var15) * var13);
							var12 = z + (int) (1.5F + MathHelper.sin(var15) * var13);
							setBlockAndMetadata(world, var11, var14 - 3 + var13 / 2, var12, woodID, woodMetadata);
						}
					}

					for (var10 = 0; var10 < var6; ++var10) {
						var11 = world.getBlock(x, y + var10, z);

						if (var11 == 0 || Blocks.blocksList[var11] == null || Blocks.blocksList[var11].isLeaves(world, x, y + var10, z)) {
							setBlockAndMetadata(world, x, y + var10, z, woodID, woodMetadata);

							if (var10 > 0) {
								if (rand.nextInt(3) > 0 && world.isAirBlock(x - 1, y + var10, z) && thorns == true)
									setBlockAndMetadata(world, x - 1, y + var10, z, ModBlocks.thorns, 8);

								if (rand.nextInt(3) > 0 && world.isAirBlock(x, y + var10, z - 1) && thorns == true)
									setBlockAndMetadata(world, x, y + var10, z - 1, ModBlocks.thorns, 1);
							}
						}

						if (var10 < var6 - 1) {
							var11 = world.getBlock(x + 1, y + var10, z);

							if (var11 == 0 || Blocks.blocksList[var11] == null || Blocks.blocksList[var11].isLeaves(world, x + 1, y + var10, z)) {
								setBlockAndMetadata(world, x + 1, y + var10, z, woodID, woodMetadata);

								if (var10 > 0) {
									if (rand.nextInt(3) > 0 && world.isAirBlock(x + 2, y + var10, z) && thorns == true)
										setBlockAndMetadata(world, x + 2, y + var10, z, ModBlocks.thorns, 2);

									if (rand.nextInt(3) > 0 && world.isAirBlock(x + 1, y + var10, z - 1) && thorns == true)
										setBlockAndMetadata(world, x + 1, y + var10, z - 1, ModBlocks.thorns, 1);
								}
							}

							var11 = world.getBlock(x + 1, y + var10, z + 1);

							if (var11 == 0 || Blocks.blocksList[var11] == null || Blocks.blocksList[var11].isLeaves(world, x + 1, y + var10, z + 1)) {
								setBlockAndMetadata(world, x + 1, y + var10, z + 1, woodID, woodMetadata);

								if (var10 > 0) {
									if (rand.nextInt(3) > 0 && world.isAirBlock(x + 2, y + var10, z + 1) && thorns == true)
										setBlockAndMetadata(world, x + 2, y + var10, z + 1, ModBlocks.thorns, 2);

									if (rand.nextInt(3) > 0 && world.isAirBlock(x + 1, y + var10, z + 2) && thorns == true)
										setBlockAndMetadata(world, x + 1, y + var10, z + 2, ModBlocks.thorns, 4);
								}
							}

							var11 = world.getBlock(x, y + var10, z + 1);

							if (var11 == 0 || Blocks.blocksList[var11] == null || Blocks.blocksList[var11].isLeaves(world, x, y + var10, z + 1)) {
								setBlockAndMetadata(world, x, y + var10, z + 1, woodID, woodMetadata);

								if (var10 > 0) {
									if (rand.nextInt(3) > 0 && world.isAirBlock(x - 1, y + var10, z + 1) && thorns == true)
										setBlockAndMetadata(world, x - 1, y + var10, z + 1, ModBlocks.thorns, 8);

									if (rand.nextInt(3) > 0 && world.isAirBlock(x, y + var10, z + 2) && thorns == true)
										setBlockAndMetadata(world, x, y + var10, z + 2, ModBlocks.thorns, 4);
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

					Block block = Blocks.blocksList[world.getBlock(var11, var8, var13)];

					if ((var12 >= 0 || var14 >= 0 || var12 * var12 + var14 * var14 <= var10 * var10) && (var12 <= 0 && var14 <= 0 || var12 * var12 + var14 * var14 <= (var10 + 1) * (var10 + 1)) && (par6Random.nextInt(4) != 0 || var12 * var12 + var14 * var14 <= (var10 - 1) * (var10 - 1)) && (block == null || block.canBeReplacedByLeaves(world, var11, var8, var13)))
						setBlockAndMetadata(world, var11, var8, var13, leavesID, leavesMetadata);
				}
			}
		}
	}
}