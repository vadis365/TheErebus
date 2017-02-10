package erebus.world.teleporter;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class TeleporterErebus extends Teleporter {

	private final WorldServer worldServerInstance;
	private final Long2ObjectMap<Teleporter.PortalPosition> destinationCoordinateCache = new Long2ObjectOpenHashMap<Teleporter.PortalPosition>(4096);
	private final List<Long> destinationCoordinateKeys = new ArrayList<Long>();

	TeleporterErebus(WorldServer worldServer) {
		super(worldServer);
		worldServerInstance = worldServer;
	}

	@Override
	public void placeInPortal(Entity entityIn, float rotationYaw) {
		if (!placeInExistingPortal(entityIn, rotationYaw)) {
			makePortal(entityIn);
			placeInExistingPortal(entityIn, rotationYaw);
		}
	}

	@Override
	public boolean placeInExistingPortal(Entity entityIn, float rotationYaw) {
		int checkRadius = 32;
		double distToPortal = -1.0;
		int posX = 0;
		int posY = 0;
		int posZ = 0;
		int roundX = MathHelper.floor(entityIn.posX);
		int roundZ = MathHelper.floor(entityIn.posZ);
		long coordPair = ChunkPos.asLong(roundX, roundZ);
		boolean portalNotSaved = true;
		BlockPos blockpos = BlockPos.ORIGIN;
		if (destinationCoordinateCache.containsKey(coordPair)) {
			PortalPosition pos = destinationCoordinateCache.get(coordPair);

			distToPortal = 0.0;
			posX = pos.getX();
			posY = pos.getY();
			posZ = pos.getZ();
			pos.lastUpdateTime = worldServerInstance.getTotalWorldTime();
			portalNotSaved = false;
		} else
			for (int i = roundX - checkRadius; i <= roundX + checkRadius; i++)

				for (int j = roundZ - checkRadius; j <= roundZ + checkRadius; j++)
					for (int h = worldServerInstance.getActualHeight() - 1; h >= 0; h--)
						if (worldServerInstance.getBlockState(new BlockPos(i, h, j)).getBlock() == Blocks.STONE) { //ModBlocks.gaeanKeystone
							double X = i + 0.5 - entityIn.posX;
							double Z = j + 0.5 - entityIn.posZ;
							double Y = h - 2 + 0.5 - entityIn.posY;
							double dist = X * X + Z * Z + Y * Y;

							if (distToPortal < 0.0 || dist < distToPortal) {
								distToPortal = dist;
								blockpos.add(i, h, j);
								posX = i;
								posY = h;
								posZ = j;
							}
						}
		if (distToPortal >= 0.0) {
			if (portalNotSaved) {
				destinationCoordinateCache.put(coordPair, new Teleporter.PortalPosition(blockpos, worldServerInstance.getTotalWorldTime()));
				destinationCoordinateKeys.add(coordPair);
			}

			entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0;

			int entityFacing = MathHelper.floor(entityIn.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
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

			entityIn.setLocationAndAngles(posX + offsetX, posY, posZ + offsetZ, entityRotation, entityIn.rotationPitch);
			return true;
		}
		return false;
	}

	@Override
	public boolean makePortal(Entity entity) {
		//attempt at constraining the portal height in the Erebus
		double safeHeight = Math.min(Math.max(entity.posY * 0.5D, 12), 116);

		int x = MathHelper.floor(entity.posX);
		int y = MathHelper.floor(safeHeight) - 2;
		int z = MathHelper.floor(entity.posZ);

		for (int i = -2; i <= 2; i++)
			for (int j = 0; j <= 3; j++)
				for (int k = -2; k <= 2; k++)
					worldServerInstance.setBlockToAir(new BlockPos(x + i, y + j, z + k));
		/*
				// Layer -1
				for (int i = -1; i <= 1; i++)
					for (int j = -1; j <= 1; j++)
						if (worldServerInstance.getBlockState(x + i, y - 1, z + j).getBlockHardness(worldServerInstance, x + i, y - 2, z + j) >= 0)
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
				while (worldServerInstance.getBlockState(x, height, z).getBlockHardness(worldServerInstance, x, height, z) >= 0) {
					worldServerInstance.setBlockToAir(x, height, z);
					height++;
				}
		*/
		return true;
	}

	@Override
	public void removeStalePortalLocations(long timer) {
		if (timer % 100L == 0L) {
			Iterator<Long> iterator = destinationCoordinateKeys.iterator();
			while (iterator.hasNext()) {
				Long hashedPortalPos = iterator.next();
				PortalPosition position = destinationCoordinateCache.get(hashedPortalPos.longValue());

				if (position == null || position.lastUpdateTime < timer - 600L) {
					iterator.remove();
					destinationCoordinateCache.remove(hashedPortalPos.longValue());
				}
			}
		}
	}
}