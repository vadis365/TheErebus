package erebus.world.feature.tree;

import java.util.Random;

import erebus.lib.EnumWood;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenErebusTrees extends WorldGenerator { // TODO
	private final int minTreeHeight;
	private final boolean vinesGrow;
	private final int metaLeaves;
	private final Block woodBlock;
	private final Block leafBlock;
	private final Block vineBlock;

	public WorldGenErebusTrees(boolean par1, int par2, boolean par5, EnumWood wood, Block vineBlock) {
		super(par1);
		minTreeHeight = par2;
		metaLeaves = 0;
		vinesGrow = par5;
		woodBlock = wood.getLog();
		leafBlock = wood.getLeaves();
		this.vineBlock = vineBlock;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int var6 = rand.nextInt(3) + minTreeHeight;
		boolean var7 = true;
		Block block;

		if (y >= 1 && y + var6 + 1 <= 256) {
			int var8;
			byte var9;
			int var11;
			int var12;

			for (var8 = y; var8 <= y + 1 + var6; ++var8) {
				var9 = 1;

				if (var8 == y)
					var9 = 0;

				if (var8 >= y + 1 + var6 - 2)
					var9 = 2;

				for (int var10 = x - var9; var10 <= x + var9 && var7; ++var10)
					for (var11 = z - var9; var11 <= z + var9 && var7; ++var11)
						if (var8 >= 0 && var8 < 256) {
							block = world.getBlock(var10, var8, var11);

							if (!block.isLeaves(world, var10, var8, var11) && block != Blocks.grass && block != Blocks.dirt && !block.isWood(world, var10, var8, var11))
								var7 = false;
						} else
							var7 = false;
			}

			if (!var7)
				return false;
			else {
				block = world.getBlock(x, y - 1, z);

				if ((block == Blocks.grass || block == Blocks.dirt) && y < 256 - var6 - 1) {
					world.setBlock(x, y - 1, z, Blocks.dirt);
					var9 = 3;
					byte var18 = 0;
					int var13;
					int var14;
					int var15;

					for (var11 = y - var9 + var6; var11 <= y + var6; ++var11) {
						var12 = var11 - (y + var6);
						var13 = var18 + 1 - var12 / 2;

						for (var14 = x - var13; var14 <= x + var13; ++var14) {
							var15 = var14 - x;

							for (int var16 = z - var13; var16 <= z + var13; ++var16) {
								int var17 = var16 - z;

								block = world.getBlock(var14, var11, var16);

								if ((Math.abs(var15) != var13 || Math.abs(var17) != var13 || rand.nextInt(2) != 0 && var12 != 0) && (block.getMaterial() == Material.air || block.canBeReplacedByLeaves(world, var14, var11, var16)))
									world.setBlock(var14, var11, var16, leafBlock, metaLeaves, 2);
							}
						}
					}

					for (var11 = 0; var11 < var6; ++var11) {
						block = world.getBlock(x, y + var11, z);

						if (block.getMaterial() == Material.air || block.isLeaves(world, x, y + var11, z)) {
							world.setBlock(x, y + var11, z, woodBlock, 0, 2);

							if (vinesGrow && var11 > 0) {
								if (rand.nextInt(3) > 0 && world.isAirBlock(x - 1, y + var11, z))
									world.setBlock(x - 1, y + var11, z, vineBlock, 8, 2);

								if (rand.nextInt(3) > 0 && world.isAirBlock(x + 1, y + var11, z))
									world.setBlock(x + 1, y + var11, z, vineBlock, 2, 2);

								if (rand.nextInt(3) > 0 && world.isAirBlock(x, y + var11, z - 1))
									world.setBlock(x, y + var11, z - 1, vineBlock, 1, 2);

								if (rand.nextInt(3) > 0 && world.isAirBlock(x, y + var11, z + 1))
									world.setBlock(x, y + var11, z + 1, vineBlock, 4, 2);
							}
						}
					}

					if (vinesGrow) {
						for (var11 = y - 3 + var6; var11 <= y + var6; ++var11) {
							var12 = var11 - (y + var6);
							var13 = 2 - var12 / 2;

							for (var14 = x - var13; var14 <= x + var13; ++var14)
								for (var15 = z - var13; var15 <= z + var13; ++var15) {
									block = world.getBlock(var14, var11, var15);

									if (block.isLeaves(world, var14, var11, var15)) {
										if (rand.nextInt(4) == 0 && world.isAirBlock(var14 - 1, var11, var15))
											growVines(world, var14 - 1, var11, var15, 8);

										if (rand.nextInt(4) == 0 && world.isAirBlock(var14 + 1, var11, var15))
											growVines(world, var14 + 1, var11, var15, 2);

										if (rand.nextInt(4) == 0 && world.isAirBlock(var14, var11, var15 - 1))
											growVines(world, var14, var11, var15 - 1, 1);

										if (rand.nextInt(4) == 0 && world.isAirBlock(var14, var11, var15 + 1))
											growVines(world, var14, var11, var15 + 1, 4);
									}
								}
						}

						if (rand.nextInt(5) == 0 && var6 > 5)
							for (var11 = 0; var11 < 2; ++var11)
								for (var12 = 0; var12 < 4; ++var12)
									if (rand.nextInt(4 - var11) == 0) {
										var13 = rand.nextInt(3);
										world.setBlock(x + Direction.offsetX[ForgeDirection.OPPOSITES[var12]], y + var6 - 5 + var11, z + Direction.offsetZ[ForgeDirection.OPPOSITES[var12]], Blocks.cocoa, var13 << 2 | var12, 2);
									}
					}

					return true;
				} else
					return false;
			}
		} else
			return false;
	}

	private void growVines(World world, int x, int y, int z, int flags) {
		world.setBlock(x, y, z, vineBlock, flags, 2);
		int var6 = 4;

		while (true) {
			--y;

			if (!world.isAirBlock(x, y, z) || var6 <= 0)
				return;

			world.setBlock(x, y, z, vineBlock, flags, 2);
			--var6;
		}
	}
}