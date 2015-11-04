package erebus.world.teleporter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import erebus.ModAchievements;
import erebus.ModBlocks;
import erebus.block.GaeanKeystone;
import erebus.core.handler.configs.ConfigHandler;
import erebus.tileentity.TileEntityGaeanKeystone;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

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
			if (worldServerInstance.provider.dimensionId == ConfigHandler.INSTANCE.erebusDimensionID) {
				makePortal(entity);
				placeInExistingPortal(entity, x, y, z, rotationYaw);
			}
		}
		moveToEmptyArea(entity);
	}

	private void moveToEmptyArea(Entity entity) {
		while (!isClear(entity)) {
			entity.setPosition(entity.posX, entity.posY + 1, entity.posZ);
		}
	}

	private boolean isClear(Entity ent) {
		AxisAlignedBB box = ent.boundingBox;
		int minX = MathHelper.floor_double(box.minX);
		int maxX = MathHelper.floor_double(box.maxX + 1.0D);
		int minY = MathHelper.floor_double(box.minY);
		int maxY = MathHelper.floor_double(box.maxY + 1.0D);
		int minZ = MathHelper.floor_double(box.minZ);
		int maxZ = MathHelper.floor_double(box.maxZ + 1.0D);

		if (minX < 0.0D)
			--minX;
		if (minY < 0.0D)
			--minY;
		if (box.minZ < 0.0D)
			--minZ;

		for (int x = minX; x < maxX; ++x)
			for (int z = minZ; z < maxZ; ++z) {
				for (int y = minY; y < maxY; ++y) {
					Block block = worldServerInstance.getBlock(x, y, z);

					final Material mat = block.getMaterial();
					if (mat.isLiquid() || mat.isSolid() || mat.getMaterialMobility() == 2)
						return false;
				}
			}

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean placeInExistingPortal(Entity entity, double x, double y, double z, float rotationYaw) {
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).triggerAchievement(ModAchievements.welcome);

		int checkRadius = 32;
		double distToPortal = Double.POSITIVE_INFINITY;
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
		} else {
			for (int chunkX = roundX - checkRadius; chunkX <= roundX + checkRadius; chunkX += 16)
				for (int chunkZ = roundZ - checkRadius; chunkZ <= roundZ + checkRadius; chunkZ += 16) {
					Chunk chunk = worldServerInstance.getChunkFromBlockCoords(chunkX, chunkZ);
					for (TileEntity te : (Iterable<TileEntity>) chunk.chunkTileEntityMap.values()) {
						if (!(te instanceof TileEntityGaeanKeystone))
							continue;
						double dx = entity.posX - te.xCoord;
						double dy = 0; //entity.posY - te.yCoord;
						double dz = entity.posZ - te.zCoord;
						double dSq = dx * dx + dy * dy + dz * dz;
						if (dSq > distToPortal)
							continue;
						distToPortal = dSq;
						posX = te.xCoord;
						posY = te.yCoord;
						posZ = te.zCoord;
					}
				}
			distToPortal = Math.sqrt(distToPortal);
		}

		if (distToPortal < checkRadius) {
			if (portalNotSaved) {
				destinationCoordinateCache.add(coordPair, new PortalPosition(posX, posY, posZ, worldServerInstance.getTotalWorldTime()));
				destinationCoordinateKeys.add(Long.valueOf(coordPair));
			}

			entity.motionX = entity.motionY = entity.motionZ = 0.0;

			int entityFacing = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			float entityRotation = 0;

			switch (entityFacing) {
				case 0:
					entityRotation = 180;
					break;
				case 1:
					entityRotation = 270;
					break;
				case 2:
					entityRotation = 0;
					break;
				case 3:
					entityRotation = 90;
					break;
			}
			double ex = posX + 0.5;
			double ey = posY + 0.125 /* prevents glitchiness */;
			double ez = posZ + 0.5;
			entity.setLocationAndAngles(ex, ey, ez, entityRotation, entity.rotationPitch);
			return true;
		}
		return false;
	}

	@Override
	public boolean makePortal(Entity entity) {
		if (entity instanceof EntityPlayer)
			((EntityPlayer) entity).triggerAchievement(ModAchievements.welcome);

		//attempt at constraining the portal height in the Erebus
		double safeHeight = Math.min(Math.max(entity.posY * 0.5D, 12), 116);

		int x = MathHelper.floor_double(entity.posX);
		int y = MathHelper.floor_double(safeHeight) - 2;
		int z = MathHelper.floor_double(entity.posZ);

		((GaeanKeystone) ModBlocks.gaeanKeystone).buildDestinationPortal(worldServerInstance, x, y, z);

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