package erebus.world.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;
import erebus.lib.EnumWood;

public class WorldGenErebusHugeTree extends WorldGenerator {
	private int baseHeight;
	private final boolean thorns;
	private final Block woodBlock;
	private final Block leafBlock;

	public WorldGenErebusHugeTree(boolean notify, boolean genThorns, EnumWood wood) {
		super(notify);
		thorns = genThorns;
		woodBlock  = ModBlocks.log_mahogany;
		leafBlock = ModBlocks.leaves_mahogany;
	//	woodBlock = wood.getLog();
	//	leafBlock = wood.getLeaves();
	}

	public void prepare(int baseHeight) {
		this.baseHeight = baseHeight;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		int height = rand.nextInt(3) + baseHeight;
		boolean var7 = true;
		IBlockState block;

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
							block = world.getBlockState(new BlockPos(var10, var8, var11));
							if (!(block.getBlock() instanceof BlockLeaves) && block != Blocks.grass.getDefaultState() && block != Blocks.dirt.getDefaultState() && block.getMaterial() != Material.wood && !block.getBlock().isReplaceable(world, new BlockPos(var10, var8, var11)))
								var7 = false;
						} else
							var7 = false;
			}

			if (!var7)
				return false;
			else {
				block = world.getBlockState(new BlockPos(x, y - 1, z));

				if ((block == Blocks.grass.getDefaultState() || block == Blocks.dirt.getDefaultState()) && y < 256 - height - 1) {
					world.setBlockState(new BlockPos(x, y - 1, z), Blocks.dirt.getDefaultState());
					world.setBlockState(new BlockPos(x + 1, y - 1, z), Blocks.dirt.getDefaultState());
					world.setBlockState(new BlockPos(x, y - 1, z + 1), Blocks.dirt.getDefaultState());
					world.setBlockState(new BlockPos(x + 1, y - 1, z + 1), Blocks.dirt.getDefaultState());
					growLeaves(world, x, z, y + height, 2, rand);

					for (int var14 = y + height - 2 - rand.nextInt(4); var14 > y + height / 2; var14 -= 2 + rand.nextInt(4)) {
						float var15 = rand.nextFloat() * (float) Math.PI * 2.0F;
						var11 = x + (int) (0.5F + MathHelper.cos(var15) * 4.0F);
						var12 = z + (int) (0.5F + MathHelper.sin(var15) * 4.0F);
						growLeaves(world, var11, var12, var14, 0, rand);

						for (int var13 = 0; var13 < 5; ++var13) {
							var11 = x + (int) (1.5F + MathHelper.cos(var15) * var13);
							var12 = z + (int) (1.5F + MathHelper.sin(var15) * var13);
							world.setBlockState(new BlockPos(var11, var14 - 3 + var13 / 2, var12), woodBlock.getStateFromMeta(0), 2);
						}
					}

					for (var10 = 0; var10 < height; ++var10) {
						pos = new BlockPos(x, y + var10, z);
						block = world.getBlockState(pos);


						if (block.getBlock().isReplaceable(world, pos) || block.getBlock().isLeaves(block, world, pos)) {
							world.setBlockState(new BlockPos(x, y + var10, z), woodBlock.getStateFromMeta(0), 2);
							/* TODO FIX THORNS
							if (var10 > 0) {
								if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(x - 1, y + var10, z)) && thorns == true)
									world.setBlockState(new BlockPos(x - 1, y + var10, z), ModBlocks.thorns, 8, 2);

								if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(x, y + var10, z - 1)) && thorns == true)
									world.setBlockState(new BlockPos(x, y + var10, z - 1), ModBlocks.thorns, 1, 2);
							}*/
						}

						if (var10 < height - 1) {
							pos = new BlockPos(x + 1, y + var10, z);
							block = world.getBlockState(pos);
							if (block.getBlock().isReplaceable(world, pos) || block.getBlock().isLeaves(block, world, pos)) {
								world.setBlockState(new BlockPos(x + 1, y + var10, z), woodBlock.getStateFromMeta(0), 2);
								/* TODO FIX THORNS
								if (var10 > 0) {
									if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(x + 2, y + var10, z)) && thorns == true)
										world.setBlockState(new BlockPos(x + 2, y + var10, z), ModBlocks.thorns, 2, 2);

									if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(x + 1, y + var10, z - 1)) && thorns == true)
										world.setBlockState(new BlockPos(x + 1, y + var10, z - 1), ModBlocks.thorns, 1, 2);
								}*/
							}
							pos = new BlockPos(x + 1, y + var10, z + 1);
							block = world.getBlockState(pos);

							if (block.getBlock().isReplaceable(world, pos) || block.getBlock().isLeaves(block, world, pos)) {
								world.setBlockState(new BlockPos(x + 1, y + var10, z + 1), woodBlock.getStateFromMeta(0), 2);
								/* TODO FIX THORNS
								if (var10 > 0) {
									if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(x + 2, y + var10, z + 1)) && thorns == true)
										world.setBlockState(new BlockPos(x + 2, y + var10, z + 1), ModBlocks.thorns, 2, 2);

									if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(x + 1, y + var10, z + 2)) && thorns == true)
										world.setBlockState(new BlockPos(x + 1, y + var10, z + 2), ModBlocks.thorns, 4, 2);
								}*/
							}

							pos = new BlockPos(x, y + var10, z + 1);
							block = world.getBlockState(pos);
							
							if (block.getBlock().isReplaceable(world, pos) || block.getBlock().isLeaves(block, world, pos)) {
								world.setBlockState(new BlockPos(x, y + var10, z + 1), woodBlock.getStateFromMeta(0), 2);
								/* TODO FIX THORNS
								if (var10 > 0) {
									if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(x - 1, y + var10, z + 1)) && thorns == true)
										world.setBlockState(new BlockPos(x - 1, y + var10, z + 1), ModBlocks.thorns, 8, 2);

									if (rand.nextInt(3) > 0 && world.isAirBlock(new BlockPos(x, y + var10, z + 2)) && thorns == true)
										world.setBlockState(new BlockPos(x, y + var10, z + 2), ModBlocks.thorns, 4, 2);
								}*/
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

					IBlockState block = world.getBlockState(new BlockPos(var11, var8, var13));

					if ((var12 >= 0 || var14 >= 0 || var12 * var12 + var14 * var14 <= var10 * var10) && (var12 <= 0 && var14 <= 0 || var12 * var12 + var14 * var14 <= (var10 + 1) * (var10 + 1)) && (par6Random.nextInt(4) != 0 || var12 * var12 + var14 * var14 <= (var10 - 1) * (var10 - 1)) && (block == null || block.getBlock().canBeReplacedByLeaves(block, world, new BlockPos(var11, var8, var13))))
						world.setBlockState(new BlockPos(var11, var8, var13), leafBlock.getStateFromMeta(0), 2);
				}
			}
		}
	}
}