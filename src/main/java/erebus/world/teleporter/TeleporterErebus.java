package erebus.world.teleporter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import erebus.ModBlocks;

final class TeleporterErebus extends Teleporter
{
	private final WorldServer worldServerInstance;
	private final Random rand;
	private final LongHashMap destinationCoordinateCache = new LongHashMap();
	private final List<Long> destinationCoordinateKeys = new ArrayList<Long>();

	TeleporterErebus(WorldServer worldServer)
	{
		super(worldServer);
		worldServerInstance = worldServer;
		rand = new Random(worldServer.getSeed() + 1);
	}

	@Override
	public void placeInPortal(Entity entity, double x, double y, double z, float par8)
	{
		if (!placeInExistingPortal(entity, x, y, z, par8))
		{
			makePortal(entity);
			placeInExistingPortal(entity, x, y, z, par8);
		}
	}

	@Override
	public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float par8)
	{
		short checkRad = 32;
		double d3 = -1.0D;
		int i = 0;
		int j = 0;
		int k = 0;
		int int_x = MathHelper.floor_double(entity.posX);
		int int_z = MathHelper.floor_double(entity.posZ);
		long j1 = ChunkCoordIntPair.chunkXZ2Int(int_x, int_z);
		boolean flag = true;
		double d4;
		int k1;

		if (destinationCoordinateCache.containsItem(j1))
		{
			PortalPosition portalposition = (PortalPosition) destinationCoordinateCache.getValueByKey(j1);
			d3 = 0.0D;
			i = portalposition.posX;
			j = portalposition.posY;
			k = portalposition.posZ;
			portalposition.lastUpdateTime = worldServerInstance.getTotalWorldTime();
			flag = false;
		} else
		{
			for (k1 = int_x - checkRad; k1 <= int_x + checkRad; ++k1)
			{
				double d5 = k1 + 0.5D - entity.posX;

				for (int l1 = int_z - checkRad; l1 <= int_z + checkRad; ++l1)
				{
					double d6 = l1 + 0.5D - entity.posZ;

					for (int i2 = worldServerInstance.getActualHeight() - 1; i2 >= 0; --i2)
					{
						if (worldServerInstance.getBlock(k1, i2, l1) == ModBlocks.portal)
						{
							while (worldServerInstance.getBlock(k1, i2 - 1, l1) == ModBlocks.portal)
							{
								--i2;
							}

							d4 = i2 + 0.5D - entity.posY;
							double d7 = d5 * d5 + d4 * d4 + d6 * d6;

							if (d3 < 0.0D || d7 < d3)
							{
								d3 = d7;
								i = k1;
								j = i2;
								k = l1;
							}
						}
					}
				}
			}
		}

		if (d3 >= 0.0D)
		{
			if (flag)
			{
				destinationCoordinateCache.add(j1, new PortalPosition(i, j, k, worldServerInstance.getTotalWorldTime()));
				destinationCoordinateKeys.add(Long.valueOf(j1));
			}

			double d8 = i + 0.5D;
			double d9 = j + 0.5D;
			d4 = k + 0.5D;
			int j2 = -1;

			if (worldServerInstance.getBlock(i - 1, j, k) == ModBlocks.portal)
			{
				j2 = 2;
			}

			if (worldServerInstance.getBlock(i + 1, j, k) == ModBlocks.portal)
			{
				j2 = 0;
			}

			if (worldServerInstance.getBlock(i, j, k - 1) == ModBlocks.portal)
			{
				j2 = 3;
			}

			if (worldServerInstance.getBlock(i, j, k + 1) == ModBlocks.portal)
			{
				j2 = 1;
			}

			int k2 = entity.getTeleportDirection();

			if (j2 > -1)
			{
				int l2 = Direction.rotateLeft[j2];
				int i3 = Direction.offsetX[j2];
				int j3 = Direction.offsetZ[j2];
				int k3 = Direction.offsetX[l2];
				int l3 = Direction.offsetZ[l2];
				boolean flag1 = !worldServerInstance.isAirBlock(i + i3 + k3, j, k + j3 + l3) || !worldServerInstance.isAirBlock(i + i3 + k3, j + 1, k + j3 + l3);
				boolean flag2 = !worldServerInstance.isAirBlock(i + i3, j, k + j3) || !worldServerInstance.isAirBlock(i + i3, j + 1, k + j3);

				if (flag1 && flag2)
				{
					j2 = Direction.rotateOpposite[j2];
					l2 = Direction.rotateOpposite[l2];
					i3 = Direction.offsetX[j2];
					j3 = Direction.offsetZ[j2];
					k3 = Direction.offsetX[l2];
					l3 = Direction.offsetZ[l2];
					k1 = i - k3;
					d8 -= k3;
					int i4 = k - l3;
					d4 -= l3;
					flag1 = !worldServerInstance.isAirBlock(k1 + i3 + k3, j, i4 + j3 + l3) || !worldServerInstance.isAirBlock(k1 + i3 + k3, j + 1, i4 + j3 + l3);
					flag2 = !worldServerInstance.isAirBlock(k1 + i3, j, i4 + j3) || !worldServerInstance.isAirBlock(k1 + i3, j + 1, i4 + j3);
				}

				float f1 = 0.5F;
				float f2 = 0.5F;

				if (!flag1 && flag2)
				{
					f1 = 1.0F;
				} else if (flag1 && !flag2)
				{
					f1 = 0.0F;
				} else if (flag1 && flag2)
				{
					f2 = 0.0F;
				}

				d8 += k3 * f1 + f2 * i3;
				d4 += l3 * f1 + f2 * j3;
				float f3 = 0.0F;
				float f4 = 0.0F;
				float f5 = 0.0F;
				float f6 = 0.0F;

				if (j2 == k2)
				{
					f3 = 1.0F;
					f4 = 1.0F;
				} else if (j2 == Direction.rotateOpposite[k2])
				{
					f3 = -1.0F;
					f4 = -1.0F;
				} else if (j2 == Direction.rotateRight[k2])
				{
					f5 = 1.0F;
					f6 = -1.0F;
				} else
				{
					f5 = -1.0F;
					f6 = 1.0F;
				}

				double d10 = entity.motionX;
				double d11 = entity.motionZ;
				entity.motionX = d10 * f3 + d11 * f6;
				entity.motionZ = d10 * f5 + d11 * f4;
				entity.rotationYaw = par8 - k2 * 90 + j2 * 90;
			} else
			{
				entity.motionX = entity.motionY = entity.motionZ = 0.0D;
			}

			entity.setLocationAndAngles(d8, d9, d4, entity.rotationYaw, entity.rotationPitch);
			return true;
		} else
		{
			return false;
		}
	}

	@Override
	public boolean makePortal(Entity entity)
	{
		int x = MathHelper.floor_double(entity.posX);
		int y = MathHelper.floor_double(entity.posY) - 2;
		int z = MathHelper.floor_double(entity.posZ);

		for (int i = -2; i <= 2; i++)
		{
			for (int j = 0; j <= 3; j++)
			{
				for (int k = -2; k <= 2; k++)
				{
					worldServerInstance.setBlockToAir(x + i, y + j, z + k);
				}
			}
		}

		// Layer 0
		worldServerInstance.setBlock(x, y - 1, z, Blocks.stonebrick, 3, 3);

		// Layer 1
		worldServerInstance.setBlock(x - 1, y, z + 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x + 1, y, z - 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x - 1, y, z - 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x + 1, y, z + 1, Blocks.stonebrick);

		//Layer 2
		worldServerInstance.setBlock(x - 1, y + 1, z + 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x + 1, y + 1, z - 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x - 1, y + 1, z - 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x + 1, y + 1, z + 1, Blocks.stonebrick);

		// Layer 3
		for (int i = -1; i <= 1; i++)
		{
			for (int j = -1; j <= 1; j++)
			{
				if (i == 0 && j == 0)
				{
					worldServerInstance.setBlock(x + i, y + 2, z + j, ModBlocks.gaeanKeystone);
				} else
				{
					worldServerInstance.setBlock(x + i, y + 2, z + j, Blocks.stone_slab, 5, 3);
				}
			}
		}

		return true;
	}

	@Override
	public void removeStalePortalLocations(long par1)
	{
		if (par1 % 100L == 0L)
		{
			Iterator<Long> iterator = destinationCoordinateKeys.iterator();
			long j = par1 - 600L;

			while (iterator.hasNext())
			{
				Long olong = iterator.next();
				PortalPosition portalposition = (PortalPosition) destinationCoordinateCache.getValueByKey(olong.longValue());

				if (portalposition == null || portalposition.lastUpdateTime < j)
				{
					iterator.remove();
					destinationCoordinateCache.remove(olong.longValue());
				}
			}
		}
	}
}