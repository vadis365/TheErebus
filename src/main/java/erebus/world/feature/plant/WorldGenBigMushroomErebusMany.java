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

	public WorldGenBigMushroomErebusMany(int par1) {
		super(true);
		this.mushroomType = par1;
	}

	public WorldGenBigMushroomErebusMany() {
		super(false);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) {
		int type = this.mushroomType;

		int i1 = rand.nextInt(3) + 4;
		boolean flag = true;
		Block block;

		if (y >= 1 && y + i1 + 1 < 256) {
			int j1;
			int k1;
			int l1;
			int i2;

			for (j1 = y; j1 <= y + 1 + i1; ++j1) {
				byte b0 = 3;

				if (j1 <= y + 3) {
					b0 = 0;
				}

				for (k1 = x - b0; k1 <= x + b0 && flag; ++k1) {
					for (l1 = z - b0; l1 <= z + b0 && flag; ++l1) {
						if (j1 >= 0 && j1 < 256) {
							block = world.getBlock(k1, j1, l1);

							if (block.getMaterial() != Material.air && !block.isLeaves(world, k1, j1, l1)) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}

			if (!flag) {
				return false;
			} else {
				block = world.getBlock(x, y - 1, z);

				if (block != Blocks.dirt && block != Blocks.grass && block != Blocks.mycelium) {
					return false;
				} else {
					int j2 = y + i1;

					if (type == 0) {
						j2 = y + i1 - 3;
					}
					
					if (type == 1) {
						j2 = y + i1 - 1;
					}
					
					if (type == 4) {
						j2 = y + i1 - 1;
					}

					for (k1 = j2; k1 <= y + i1; ++k1) {
						l1 = 1;

						if (k1 < y + i1) {
							++l1;
						}

						if (type == 2) {
							l1 = 3;
						}
						
						if (type == 4) {
							l1 = 2;
						}


						for (i2 = x - l1; i2 <= x + l1; ++i2) {
							for (int k2 = z - l1; k2 <= z + l1; ++k2) {
								int l2 = 5;

								if (i2 == x - l1) {
									--l2;
								}

								if (i2 == x + l1) {
									++l2;
								}

								if (k2 == z - l1) {
									l2 -= 3;
								}

								if (k2 == z + l1) {
									l2 += 3;
								}

								if (type == 2 || type == 4 || k1 < y + i1) {
									if ((i2 == x - l1 || i2 == x + l1) && (k2 == z - l1 || k2 == z + l1)) {
										continue;
									}

									if (i2 == x - (l1 - 1) && k2 == z - l1) {
										l2 = 1;
									}

									if (i2 == x - l1 && k2 == z - (l1 - 1)) {
										l2 = 1;
									}

									if (i2 == x + (l1 - 1) && k2 == z - l1) {
										l2 = 3;
									}

									if (i2 == x + l1 && k2 == z - (l1 - 1)) {
										l2 = 3;
									}

									if (i2 == x - (l1 - 1) && k2 == z + l1) {
										l2 = 7;
									}

									if (i2 == x - l1 && k2 == z + (l1 - 1)) {
										l2 = 7;
									}

									if (i2 == x + (l1 - 1) && k2 == z + l1) {
										l2 = 9;
									}

									if (i2 == x + l1 && k2 == z + (l1 - 1)) {
										l2 = 9;
									}
								}

								if (l2 == 5 && k1 < y + i1) {
									l2 = 0;
								}

								block = world.getBlock(i2, k1, k2);

								if ((l2 != 0 || y >= y + i1 - 1) && (block.getMaterial() == Material.air || block.canBeReplacedByLeaves(world, i2, k1, k2))) {
									world.setBlock(i2, k1, k2, getMushroomCap(type), l2, 3);
								}
							}
						}
					}

					for (k1 = 0; k1 < i1; ++k1) {
						block = world.getBlock(x, y + k1, z);

						if (block.getMaterial() == Material.air || block.canBeReplacedByLeaves(world, x, y + k1, z)) {
							world.setBlock(x, y + k1, z, getMushroomCap(type), 10, 3);
						}
					}

					return true;
				}
			}
		} else {
			return false;
		}
	}
	
	private Block getMushroomCap(int type){
		switch(type){
			case 0: return ModBlocks.erebusMushroomCap0;
			case 1: return ModBlocks.erebusMushroomCap1;
			case 2: return ModBlocks.erebusMushroomCap2;
			case 3: return ModBlocks.erebusMushroomCap3;
			case 4: return ModBlocks.erebusMushroomCap4;
			default: return null;
		}
	}
}
