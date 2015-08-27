package erebus.world.teleporter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import erebus.ModBlocks;

final class TeleporterErebus extends Teleporter {
	private final WorldServer worldServerInstance;
	private final LongHashMap destinationCoordinateCache = new LongHashMap();
	private final List<Long> destinationCoordinateKeys = new ArrayList<Long>();

	TeleporterErebus(WorldServer worldServer) {
		super(worldServer);
		worldServerInstance = worldServer;
	}

	@Override
	public void placeInPortal(Entity entity, double x, double y, double z, float rotationYaw) {
		if (!placeInExistingPortal(entity, x, y, z, rotationYaw)) {
			makePortal(entity);
			placeInExistingPortal(entity, x, y, z, rotationYaw);
		}
	}

	@Override
	public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float rotationYaw) {
		int checkRadius = 32;
		double distToPortal = -1.0;
		int posX = 0;
		int posY = 0;
		int posZ = 0;
		int roundX = MathHelper.floor_double(entity.posX);
		int roundZ = MathHelper.floor_double(entity.posZ);
		long coordPair = ChunkCoordIntPair.chunkXZ2Int(roundX, roundZ);
		boolean portalNotSaved = true;

		if (destinationCoordinateCache.containsItem(coordPair)) {
			PortalPosition pos = (PortalPosition) destinationCoordinateCache.getValueByKey(coordPair);
			distToPortal = 0.0;
			posX = pos.posX;
			posY = pos.posY;
			posZ = pos.posZ;
			pos.lastUpdateTime = worldServerInstance.getTotalWorldTime();
			portalNotSaved = false;
		} else
			for (int i = roundX - checkRadius; i <= roundX + checkRadius; i++)
				for (int j = roundZ - checkRadius; j <= roundZ + checkRadius; j++)
					for (int h = worldServerInstance.getActualHeight() - 1; h >= 0; h--)
						if (worldServerInstance.getBlock(i, h, j) == ModBlocks.gaeanKeystone) {
							double X = i + 0.5 - entity.posX;
							double Y = j + 0.5 - entity.posZ;
							double Z = h - 2 + 0.5 - entity.posY;
							double dist = X * X + Z * Z + Y * Y;

							if (distToPortal < 0.0 || dist < distToPortal) {
								distToPortal = dist;
								posX = i;
								posY = h;
								posZ = j;
							}
						}

		if (distToPortal >= 0.0) {
			if (portalNotSaved) {
				destinationCoordinateCache.add(coordPair, new PortalPosition(posX, posY, posZ, worldServerInstance.getTotalWorldTime()));
				destinationCoordinateKeys.add(Long.valueOf(coordPair));
			}

			entity.motionX = entity.motionY = entity.motionZ = 0.0;

			int entityFacing = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			float entityRotation = 0;
			double offsetX = 0;
			double offsetZ = 0;

			switch (entityFacing) {
				case 0:
					entityRotation = 180;
					offsetX = 0.5D;
					offsetZ = -0.5D;
					break;
				case 1:
					entityRotation = 270;
					offsetX = 1.5D;
					offsetZ = 0.5D;
					break;
				case 2:
					entityRotation = 0;
					offsetX = 0.5D;
					offsetZ = 1.5D;
					break;
				case 3:
					entityRotation = 90;
					offsetX = -0.5D;
					offsetZ = 0.5D;
					break;
			}

			entity.setLocationAndAngles(posX + offsetX, posY, posZ + offsetZ, entityRotation, entity.rotationPitch);
			return true;
		}
		return false;
	}

	@Override
	public boolean makePortal(Entity entity) {
		int x = MathHelper.floor_double(entity.posX);
		int y = MathHelper.floor_double(entity.posY) - 2;
		int z = MathHelper.floor_double(entity.posZ);

		for (int i = -2; i <= 2; i++)
			for (int j = 0; j <= 3; j++)
				for (int k = -2; k <= 2; k++)
					worldServerInstance.setBlockToAir(x + i, y + j, z + k);

		// Layer -1
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				if (worldServerInstance.getBlock(x + i, y - 1, z + j).getBlockHardness(worldServerInstance, x + i, y - 2, z + j) >= 0)
					worldServerInstance.setBlock(x + i, y - 2, z + j, Blocks.stonebrick, 3, 3);

		// Layer 0
		worldServerInstance.setBlock(x, y - 1, z, Blocks.stonebrick, 3, 3);
		worldServerInstance.setBlock(x - 1, y - 1, z, ModBlocks.redGem);
		worldServerInstance.setBlock(x, y - 1, z - 1, ModBlocks.redGem);
		worldServerInstance.setBlock(x + 1, y - 1, z, ModBlocks.redGem);
		worldServerInstance.setBlock(x, y - 1, z + 1, ModBlocks.redGem);
		worldServerInstance.setBlock(x - 1, y - 1, z + 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x + 1, y - 1, z - 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x - 1, y - 1, z - 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x + 1, y - 1, z + 1, Blocks.stonebrick);

		// Layer 1
		worldServerInstance.setBlock(x - 1, y, z + 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x + 1, y, z - 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x - 1, y, z - 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x + 1, y, z + 1, Blocks.stonebrick);

		// Layer 2
		worldServerInstance.setBlock(x - 1, y + 1, z + 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x + 1, y + 1, z - 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x - 1, y + 1, z - 1, Blocks.stonebrick);
		worldServerInstance.setBlock(x + 1, y + 1, z + 1, Blocks.stonebrick);

		// Layer 3
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++)
				if (i == 0 && j == 0)
					worldServerInstance.setBlock(x + i, y + 2, z + j, ModBlocks.gaeanKeystone);
				else
					worldServerInstance.setBlock(x + i, y + 2, z + j, Blocks.stone_slab, 5, 3);

		int height = y + 3;
		while (worldServerInstance.getBlock(x, height, z).getBlockHardness(worldServerInstance, x, height, z) >= 0) {
			worldServerInstance.setBlockToAir(x, height, z);
			height++;
		}

		return true;
	}

	@Override
	public void removeStalePortalLocations(long timer) {
		if (timer % 100L == 0L) {
			Iterator<Long> iterator = destinationCoordinateKeys.iterator();
			while (iterator.hasNext()) {
				Long hashedPortalPos = iterator.next();
				PortalPosition position = (PortalPosition) destinationCoordinateCache.getValueByKey(hashedPortalPos.longValue());

				if (position == null || position.lastUpdateTime < timer - 600L) {
					iterator.remove();
					destinationCoordinateCache.remove(hashedPortalPos.longValue());
				}
			}
		}
	}
}