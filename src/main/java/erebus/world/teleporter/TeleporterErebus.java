package erebus.world.teleporter;

import java.util.ArrayList;
import java.util.List;

import erebus.ModBlocks;
import erebus.blocks.BlockGaeanKeystone;
import erebus.core.handler.configs.ConfigHandler;
import erebus.tileentity.TileEntityGaeanKeystone;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

final class TeleporterErebus extends Teleporter {

	private final WorldServer worldServerInstance;
	private final Long2ObjectMap<Teleporter.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap<Teleporter.PortalPosition>(4096);
	private final List<Long> destinationCoordinateKeys = new ArrayList<Long>();

	TeleporterErebus(WorldServer worldServer) {
		super(worldServer);
		worldServerInstance = worldServer;
	}
	
	@Override
	public void placeInPortal(Entity entity, float rotationYaw) {
		if (!placeInExistingPortal(entity, rotationYaw)) {
			makePortal(entity);
			if (worldServerInstance.provider.getDimension() == ConfigHandler.INSTANCE.erebusDimensionID) {
			placeInExistingPortal(entity, rotationYaw);
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
		AxisAlignedBB box = ent.getEntityBoundingBox();
		int minX = MathHelper.floor(box.minX);
		int maxX = MathHelper.floor(box.maxX + 1.0D);
		int minY = MathHelper.floor(box.minY);
		int maxY = MathHelper.floor(box.maxY + 1.0D);
		int minZ = MathHelper.floor(box.minZ);
		int maxZ = MathHelper.floor(box.maxZ + 1.0D);

		if (minX < 0.0D)
			--minX;
		if (minY < 0.0D)
			--minY;
		if (minZ < 0.0D)
			--minZ;

		for (int x = minX; x < maxX; ++x)
			for (int z = minZ; z < maxZ; ++z) {
				for (int y = minY; y < maxY; ++y) {
					IBlockState block = worldServerInstance.getBlockState(new BlockPos(x, y, z));

					final Material mat = block.getMaterial();
					if (mat.isLiquid() || mat.isSolid() || mat.getMobilityFlag() == EnumPushReaction.BLOCK)
						return false;
				}
			}
		return true;
	}

	@Override
	public boolean placeInExistingPortal(Entity entity, float rotationYaw) {
		int checkRadius = 32;
		double distToPortal = Double.POSITIVE_INFINITY;
		int entityX = MathHelper.floor(entity.posX);
		int entityY = MathHelper.floor(entity.posY);
		int entityZ = MathHelper.floor(entity.posZ);
		long coordPair = ChunkPos.asLong(entityX, entityZ);
		boolean portalNotSaved = true;
		BlockPos blockpos = BlockPos.ORIGIN;
		if (destinationCoordinateCache.containsKey(coordPair)) {
			TeleporterErebus.PortalPosition portalPosition = (TeleporterErebus.PortalPosition)this.destinationCoordinateCache.get(coordPair);
			distToPortal = 0.0;
			blockpos = portalPosition;
			portalPosition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
			portalNotSaved = false;
		} else {
			for (int chunkX = entityX - checkRadius; chunkX <= entityX + checkRadius; chunkX += 16)
				for (int chunkZ = entityZ - checkRadius; chunkZ <= entityZ + checkRadius; chunkZ += 16) {
					Chunk chunk = worldServerInstance.getChunkFromBlockCoords(new BlockPos(chunkX, entityY, chunkZ));
					for (TileEntity te : (Iterable<TileEntity>) chunk.getTileEntityMap().values()) {
						if (!(te instanceof TileEntityGaeanKeystone))
							continue;
						double dx = entity.posX - te.getPos().getX();
						double dy = 0; //entity.posY - te.yCoord;
						double dz = entity.posZ - te.getPos().getZ();
						double dSq = dx * dx + dy * dy + dz * dz;
						if (dSq > distToPortal)
							continue;
						distToPortal = dSq;
						blockpos = te.getPos();
					}
				}
			distToPortal = Math.sqrt(distToPortal);
		}

		if (distToPortal < checkRadius) {
			if (portalNotSaved) {
				this.destinationCoordinateCache.put(coordPair, new TeleporterErebus.PortalPosition(blockpos, this.worldServerInstance.getTotalWorldTime()));
				destinationCoordinateKeys.add(Long.valueOf(coordPair));
			}
			entity.motionX = entity.motionY = entity.motionZ = 0.0;
			EnumFacing entityFacing = entity.getHorizontalFacing();

			BlockPos offsetPos = blockpos.offset(entityFacing);
			double newPosX = (double)offsetPos.getX() + 0.5D;
            double newPosY = (double)offsetPos.getY() + 0.125D;
            double newPosZ = (double)offsetPos.getZ() + 0.5D;

            if (entity instanceof EntityPlayerMP)
                ((EntityPlayerMP)entity).connection.setPlayerLocation(newPosX, newPosY, newPosZ, entity.rotationYaw, entity.rotationPitch);
            else
            	entity.setLocationAndAngles(newPosX, newPosY, newPosZ, entity.rotationYaw, entity.rotationPitch);
            return true;
		}
		else
			return false;
	}

	@Override
	public boolean makePortal(Entity entity) {
		//attempt at constraining the portal height in the Erebus
		double safeHeight = Math.min(Math.max(entity.posY * 0.5D, 12), 116);

		int x = MathHelper.floor(entity.posX);
		int y = MathHelper.floor(safeHeight) - 2;
		int z = MathHelper.floor(entity.posZ);

		((BlockGaeanKeystone) ModBlocks.GAEAN_KEYSTONE).buildDestinationPortal(worldServerInstance, x, y, z);

		return true;
	}

	@Override
	public void removeStalePortalLocations(long worldTime) {
		if (worldTime % 100L == 0L) {
			ObjectIterator<Teleporter.PortalPosition> objectIterator = this.destinationCoordinateCache.values().iterator();
			while (objectIterator.hasNext()) {
				TeleporterErebus.PortalPosition portalPosition = (TeleporterErebus.PortalPosition) objectIterator.next();
				if (portalPosition == null || portalPosition.lastUpdateTime < worldTime - 300L) {
					objectIterator.remove();
				}
			}
		}
	}
}