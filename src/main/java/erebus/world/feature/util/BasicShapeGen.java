package erebus.world.feature.util;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BasicShapeGen
{

	public final void createSphere(World world, Block block, int metaData, boolean isHollow, int x, int y, int z, int radius)
	{
		for (int x1 = x - radius; x1 <= x + radius; x1++)
		{
			for (int z1 = z - radius; z1 <= z + radius; z1++)
			{
				for (int y1 = y - radius; y1 < y + radius; y1++)
				{
					double dSq = Math.pow(x1 - x, 2.0D) + Math.pow(z1 - z, 2.0D) + Math.pow(y1 - y, 2.0D);
					if (Math.round(Math.sqrt(dSq)) < radius)
					{
						if (dSq >= Math.pow(radius - 2, 2.0D))
						{
							world.setBlock(x1, y1, z1, block, metaData, 3);
						} else if (isHollow)
						{
							world.setBlockToAir(x1, y1, z1);
						}
					}
				}
			}
		}
	}

	public final void createSemiSphereBottom(World world, Block block, int metaData, boolean isHollow, int x, int y, int z, int radius)
	{
		for (int x1 = x - radius; x1 <= x + radius; x1++)
		{
			for (int z1 = z - radius; z1 <= z + radius; z1++)
			{
				for (int y1 = y; y1 > y - radius; y1--)
				{
					double dSq = Math.pow(x1 - x, 2.0D) + Math.pow(z1 - z, 2.0D) + Math.pow(y1 - y, 2.0D);
					if (Math.round(Math.sqrt(dSq)) < radius)
					{
						if (dSq >= Math.pow(radius - 2, 2.0D) || y1 == y)
						{
							world.setBlock(x1, y1, z1, block, metaData, 3);
						} else if (isHollow)
						{
							world.setBlockToAir(x1, y1, z1);
						}
					}
				}
			}
		}
	}

	public final void createSemiSphereTop(World world, Block block, int metaData, boolean isHollow, int x, int y, int z, int radius)
	{
		for (int x1 = x - radius; x1 <= x + radius; x1++)
		{
			for (int z1 = z - radius; z1 <= z + radius; z1++)
			{
				for (int y1 = y; y1 < y + radius; y1++)
				{
					double dSq = Math.pow(x1 - x, 2.0D) + Math.pow(z1 - z, 2.0D) + Math.pow(y1 - y, 2.0D);
					if (Math.round(Math.sqrt(dSq)) < radius)
					{
						if (dSq >= Math.pow(radius - 2, 2.0D) || y1 == y)
						{
							world.setBlock(x1, y1, z1, block, metaData, 3);
						} else if (isHollow)
						{
							world.setBlockToAir(x1, y1, z1);
						}
					}
				}
			}
		}
	}

	public final void createPyramid(World world, Block block, int metaData, boolean isHollow, int x, int z, int baseLengthX, int baseLengthZ, int yStart)
	{
		int yStop = Math.min((baseLengthZ - 1) / 2, (baseLengthX - 1) / 2) + yStart;
		for (int i = 0; i + yStart <= yStop; i++)
		{
			int y = yStart + i;
			int maxX = x + baseLengthX - 1;
			int maxZ = z + baseLengthZ - 1;
			for (int ix = 0; x + ix + i <= maxX; ix++)
			{
				for (int iz = 0; z + iz + i <= maxZ; iz++)
				{
					if (ix == 0 || ix + i + 1 == baseLengthX || iz == 0 || iz + i + 1 == baseLengthZ)
					{
						world.setBlock(ix + x + i, y, iz + z + i, block, metaData, 3);
					} else if (isHollow)
					{
						world.setBlockToAir(ix + x + i, y, iz + z + i);
					}
				}
			}
			baseLengthX--;
			baseLengthZ--;
		}
	}
}
