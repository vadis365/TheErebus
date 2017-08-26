package erebus.world.teleporter;

import java.util.ArrayList;
import java.util.List;

import erebus.ModBlocks;
import erebus.core.handler.configs.ConfigHandler;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.block.BlockStoneBrick;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

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
		int entityX = MathHelper.floor(entityIn.posX);
		int entityZ = MathHelper.floor(entityIn.posZ);
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
			BlockPos blockpos3 = new BlockPos(entityIn);
			for (int i = -32; i <= 32; ++i) {
				BlockPos blockpos2;

				for (int j = -32; j <= 32; ++j) {
					for (BlockPos blockpos1 = blockpos3.add(i, this.worldServerInstance.getActualHeight() - 1 - blockpos3.getY(), j); blockpos1 .getY() >= 0; blockpos1 = blockpos2) {
						blockpos2 = blockpos1.down();

						if (this.worldServerInstance.getBlockState(blockpos1).getBlock() == ModBlocks.GAEAN_KEYSTONE) {
							for (blockpos2 = blockpos1.down(); this.worldServerInstance.getBlockState(blockpos2).getBlock() == Blocks.PORTAL; blockpos2 = blockpos2.down()) {
								blockpos1 = blockpos2;
							}

							double d1 = blockpos1.distanceSq(blockpos3);

							if (distToPortal < 0.0D || d1 < distToPortal) {
								distToPortal = d1;
								blockpos = blockpos1;
							}
						}
					}
				}
			}
		}

		if (distToPortal >= 0.0D) {
			if (portalNotSaved)
				this.destinationCoordinateCache.put(coordPair, new TeleporterErebus.PortalPosition(blockpos, this.worldServerInstance.getTotalWorldTime()));

			entityIn.motionX = entityIn.motionY = entityIn.motionZ = 0.0;

			EnumFacing entityFacing = entityIn.getHorizontalFacing();

			BlockPos offsetPos = blockpos.offset(entityFacing);
			double newPosX = (double)offsetPos.getX() + 0.5D;
            double newPosY = (double)offsetPos.getY() + 0.5D;
            double newPosZ = (double)offsetPos.getZ() + 0.5D;

            if (entityIn instanceof EntityPlayerMP)
                ((EntityPlayerMP)entityIn).connection.setPlayerLocation(newPosX, newPosY - 2.5D, newPosZ, entityIn.rotationYaw, entityIn.rotationPitch);
            else
            	entityIn.setLocationAndAngles(newPosX, newPosY - 2.5D, newPosZ, entityIn.rotationYaw, entityIn.rotationPitch);
            return true;
		}
		else
			return false;
	}

	@Override
	public boolean makePortal(Entity entity) {
		//attempt at constraining the portal height in the Erebus
		double safeHeight = Math.min(Math.max(entity.posY, 12), 244);
		if(entity.dimension == ConfigHandler.INSTANCE.erebusDimensionID)
			safeHeight = Math.min(Math.max(entity.posY * 0.5D, 12), 116);
		int x = MathHelper.floor(entity.posX);
		int y = MathHelper.floor(safeHeight);
		int z = MathHelper.floor(entity.posZ);
		BlockPos pos = new BlockPos(x, y, z);
		for (int i = -2; i <= 2; i++)
			for (int j = 0; j <= 3; j++)
				for (int k = -2; k <= 2; k++)
					worldServerInstance.setBlockToAir(pos.add(i, j, k));
		
				// Layer -1
				for (int i = -1; i <= 1; i++)
					for (int j = -1; j <= 1; j++)
						if (worldServerInstance.getBlockState(pos.add(i, - 1, j)).getBlockHardness(worldServerInstance, pos.add(i, - 2, j)) >= 0)
							worldServerInstance.setBlockState(pos.add(i, - 2, j), Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.byMetadata(3)), 3);

				// Layer 0
				worldServerInstance.setBlockState(pos.add(0, - 1, 0), Blocks.STONEBRICK.getDefaultState().withProperty(BlockStoneBrick.VARIANT, BlockStoneBrick.EnumType.byMetadata(3)), 3);
				worldServerInstance.setBlockState(pos.add(- 1, - 1, 0), ModBlocks.RED_GEM.getDefaultState());
				worldServerInstance.setBlockState(pos.add(0, - 1, - 1), ModBlocks.RED_GEM.getDefaultState());
				worldServerInstance.setBlockState(pos.add(1, - 1, 0), ModBlocks.RED_GEM.getDefaultState());
				worldServerInstance.setBlockState(pos.add(0, - 1, 1), ModBlocks.RED_GEM.getDefaultState());
				worldServerInstance.setBlockState(pos.add(- 1, - 1, 1), Blocks.STONEBRICK.getDefaultState());
				worldServerInstance.setBlockState(pos.add(1, - 1, - 1), Blocks.STONEBRICK.getDefaultState());
				worldServerInstance.setBlockState(pos.add(- 1, - 1, - 1), Blocks.STONEBRICK.getDefaultState());
				worldServerInstance.setBlockState(pos.add(1, - 1, 1), Blocks.STONEBRICK.getDefaultState());

				// Layer 1
				worldServerInstance.setBlockState(pos.add(- 1, 0, 1), Blocks.STONEBRICK.getDefaultState());
				worldServerInstance.setBlockState(pos.add(1, 0, - 1), Blocks.STONEBRICK.getDefaultState());
				worldServerInstance.setBlockState(pos.add(- 1, 0, - 1), Blocks.STONEBRICK.getDefaultState());
				worldServerInstance.setBlockState(pos.add(1, 0, 1), Blocks.STONEBRICK.getDefaultState());

				// Layer 2
				worldServerInstance.setBlockState(pos.add(- 1, 1, 1), Blocks.STONEBRICK.getDefaultState());
				worldServerInstance.setBlockState(pos.add(1, 1, - 1), Blocks.STONEBRICK.getDefaultState());
				worldServerInstance.setBlockState(pos.add(- 1, 1, - 1), Blocks.STONEBRICK.getDefaultState());
				worldServerInstance.setBlockState(pos.add(1, 1, 1), Blocks.STONEBRICK.getDefaultState());

				// Layer 3
				for (int i = -1; i <= 1; i++)
					for (int j = -1; j <= 1; j++)
						if (i == 0 && j == 0)
							worldServerInstance.setBlockState(pos.add(i, 2, j), ModBlocks.GAEAN_KEYSTONE.getDefaultState());
						else
							worldServerInstance.setBlockState(pos.add(i, 2, j), Blocks.STONE_SLAB.getDefaultState().withProperty(BlockStoneSlab.HALF, BlockStoneSlab.EnumBlockHalf.BOTTOM).withProperty(BlockStoneSlab.VARIANT, BlockStoneSlab.EnumType.SMOOTHBRICK), 3);
		if (entity.dimension == ConfigHandler.INSTANCE.erebusDimensionID) {
			int height = 3;
			while (worldServerInstance.getBlockState(pos.add(0, height, 0)).getBlockHardness(worldServerInstance, pos.add(0, height, 0)) >= 0) {
				worldServerInstance.setBlockToAir(pos.add(0, height, 0));
				height++;
			}
		}
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