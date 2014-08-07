package erebus.world.feature.plant;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import erebus.ModBlocks;

public class WorldGenBigMushroomErebusMany extends WorldGenerator {
	private int mushroomType = -1;

	public WorldGenBigMushroomErebusMany(int type) {
		super(true);
		mushroomType = type;
	}

	public WorldGenBigMushroomErebusMany() {
		super(false);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int type = mushroomType;
		int height = rand.nextInt(8) + 4;
		boolean flag = true;
		Block block;

		if (y >= 1 && y + height + 1 < 256) {
			int xx, yy, capRad;

			for (yy = y; yy <= y + 1 + height; ++yy) {
				byte rad = 3;

				if (yy <= y + 3)
					rad = 0;

				for (xx = x - rad; xx <= x + rad && flag; ++xx)
					for (capRad = z - rad; capRad <= z + rad && flag; ++capRad)
						if (yy >= 0 && yy < 256) {
							block = world.getBlock(xx, yy, capRad);

							if (block.getMaterial() != Material.air && !block.isLeaves(world, xx, yy, capRad))
								flag = false;
						} else
							flag = false;
			}

			if (!flag)
				return false;
			else {
				block = world.getBlock(x, y - 1, z);

				if (block != Blocks.dirt && block != Blocks.grass && block != Blocks.mycelium)
					return false;
				else {
					int capLength = y + height;

					if (type == 0)
						capLength = y + height - 3;

					if (type == 1)
						capLength = y + height - 1;

					if (type == 4)
						capLength = y + height - 2;

					for (yy = capLength; yy <= y + height; ++yy) {
						capRad = 1;

						if (yy < y + height)
							++capRad;

						if (type == 2)
							capRad = 3;

						for (xx = x - capRad; xx <= x + capRad; ++xx)
							for (int zz = z - capRad; zz <= z + capRad; ++zz) {
								int meta = 5;

								if (xx == x - capRad)
									--meta;

								if (xx == x + capRad)
									++meta;

								if (zz == z - capRad)
									meta -= 3;

								if (zz == z + capRad)
									meta += 3;

								if (type == 2 || yy < y + height) {

									if ((xx == x - capRad || xx == x + capRad) && (zz == z - capRad || zz == z + capRad))
										continue;

									if (xx == x - (capRad - 1) && zz == z - capRad)
										meta = 1;

									if (xx == x - capRad && zz == z - (capRad - 1))
										meta = 1;

									if (xx == x + capRad - 1 && zz == z - capRad)
										meta = 3;

									if (xx == x + capRad && zz == z - (capRad - 1))
										meta = 3;

									if (xx == x - (capRad - 1) && zz == z + capRad)
										meta = 7;

									if (xx == x - capRad && zz == z + capRad - 1)
										meta = 7;

									if (xx == x + capRad - 1 && zz == z + capRad)
										meta = 9;

									if (xx == x + capRad && zz == z + capRad - 1)
										meta = 9;

								}

								if (meta == 5 && yy < y + height)
									meta = 0;

								block = world.getBlock(xx, yy, zz);

								if ((meta != 0 || y >= y + height - 1) && (block.getMaterial() == Material.air || block.canBeReplacedByLeaves(world, xx, yy, zz)))
									world.setBlock(xx, yy, zz, getMushroomCap(type), meta, 3);
							}
					}

					for (yy = 0; yy < height; ++yy) {
						block = world.getBlock(x, y + yy, z);

						if (block.getMaterial() == Material.air || block.canBeReplacedByLeaves(world, x, y + yy, z))
							world.setBlock(x, y + yy, z, getMushroomCap(type), 10, 3);
					}
					return true;
				}
			}
		} else
			return false;
	}

	private Block getMushroomCap(int type) {
		switch (type) {
			case 0:
				return ModBlocks.mushroomCap0;
			case 1:
				return ModBlocks.mushroomCap1;
			case 2:
				return ModBlocks.mushroomCap2;
			case 3:
				return ModBlocks.mushroomCap3;
			case 4:
				return ModBlocks.mushroomCap4;
			default:
				return null;
		}
	}
}
