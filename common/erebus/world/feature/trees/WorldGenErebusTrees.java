package erebus.world.feature.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ForgeDirection;
import erebus.ModBlocks;
import erebus.block.BlockLeavesErebus;
import erebus.block.BlockLogErebus;

public class WorldGenErebusTrees extends WorldGenerator {

	private final int minTreeHeight;
	private final boolean vinesGrow;
	private final int metaWood;
	private final int metaLeaves;
	private final int woodID;
	private final int leavesID;
	private final int vinesID;

	public WorldGenErebusTrees(boolean par1) {
		this(par1, 6, BlockLogErebus.dataMahogany, BlockLeavesErebus.dataMahoganyDecay, false, ModBlocks.logErebusGroup1.blockID, ModBlocks.leavesErebus.blockID, ModBlocks.thorns.blockID);
	}

	public WorldGenErebusTrees(boolean par1, int par2, int par3, int par4, boolean par5, int par6, int par7, int par8) {
		super(par1);
		minTreeHeight = par2;
		metaWood = par3;
		metaLeaves = par4;
		vinesGrow = par5;
		woodID = par6;
		leavesID = par7;
		vinesID = par8;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int var6 = rand.nextInt(3) + minTreeHeight;
		boolean var7 = true;

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
							var12 = world.getBlockId(var10, var8, var11);

							Block block = Block.blocksList[var12];

							if (var12 != 0 && !block.isLeaves(world, var10, var8, var11) && var12 != Block.grass.blockID && var12 != Block.dirt.blockID && !block.isWood(world, var10, var8, var11))
								var7 = false;
						} else
							var7 = false;
			}

			if (!var7)
				return false;
			else {
				var8 = world.getBlockId(x, y - 1, z);

				if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && y < 256 - var6 - 1) {
					setBlock(world, x, y - 1, z, Block.dirt.blockID);
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

								Block block = Block.blocksList[world.getBlockId(var14, var11, var16)];

								if ((Math.abs(var15) != var13 || Math.abs(var17) != var13 || rand.nextInt(2) != 0 && var12 != 0) && (block == null || block.canBeReplacedByLeaves(world, var14, var11, var16)))
									setBlockAndMetadata(world, var14, var11, var16, leavesID, metaLeaves);
							}
						}
					}

					for (var11 = 0; var11 < var6; ++var11) {
						var12 = world.getBlockId(x, y + var11, z);

						Block block = Block.blocksList[var12];

						if (var12 == 0 || block == null || block.isLeaves(world, x, y + var11, z)) {
							setBlockAndMetadata(world, x, y + var11, z, woodID, metaWood);

							if (vinesGrow && var11 > 0) {
								if (rand.nextInt(3) > 0 && world.isAirBlock(x - 1, y + var11, z))
									setBlockAndMetadata(world, x - 1, y + var11, z, vinesID, 8);

								if (rand.nextInt(3) > 0 && world.isAirBlock(x + 1, y + var11, z))
									setBlockAndMetadata(world, x + 1, y + var11, z, vinesID, 2);

								if (rand.nextInt(3) > 0 && world.isAirBlock(x, y + var11, z - 1))
									setBlockAndMetadata(world, x, y + var11, z - 1, vinesID, 1);

								if (rand.nextInt(3) > 0 && world.isAirBlock(x, y + var11, z + 1))
									setBlockAndMetadata(world, x, y + var11, z + 1, vinesID, 4);
							}
						}
					}

					if (vinesGrow) {
						for (var11 = y - 3 + var6; var11 <= y + var6; ++var11) {
							var12 = var11 - (y + var6);
							var13 = 2 - var12 / 2;

							for (var14 = x - var13; var14 <= x + var13; ++var14)
								for (var15 = z - var13; var15 <= z + var13; ++var15) {
									Block block = Block.blocksList[world.getBlockId(var14, var11, var15)];
									if (block != null && block.isLeaves(world, var14, var11, var15)) {
										if (rand.nextInt(4) == 0 && world.getBlockId(var14 - 1, var11, var15) == 0)
											growVines(world, var14 - 1, var11, var15, 8);

										if (rand.nextInt(4) == 0 && world.getBlockId(var14 + 1, var11, var15) == 0)
											growVines(world, var14 + 1, var11, var15, 2);

										if (rand.nextInt(4) == 0 && world.getBlockId(var14, var11, var15 - 1) == 0)
											growVines(world, var14, var11, var15 - 1, 1);

										if (rand.nextInt(4) == 0 && world.getBlockId(var14, var11, var15 + 1) == 0)
											growVines(world, var14, var11, var15 + 1, 4);
									}
								}
						}

						if (rand.nextInt(5) == 0 && var6 > 5)
							for (var11 = 0; var11 < 2; ++var11)
								for (var12 = 0; var12 < 4; ++var12)
									if (rand.nextInt(4 - var11) == 0) {
										var13 = rand.nextInt(3);
										setBlockAndMetadata(world, x + Direction.offsetX[ForgeDirection.OPPOSITES[var12]], y + var6 - 5 + var11, z + Direction.offsetZ[ForgeDirection.OPPOSITES[var12]], Block.cocoaPlant.blockID, var13 << 2 | var12);
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
		setBlockAndMetadata(world, x, y, z, vinesID, flags);
		int var6 = 4;

		while (true) {
			--y;

			if (world.getBlockId(x, y, z) != 0 || var6 <= 0)
				return;

			setBlockAndMetadata(world, x, y, z, vinesID, flags);
			--var6;
		}
	}
}
