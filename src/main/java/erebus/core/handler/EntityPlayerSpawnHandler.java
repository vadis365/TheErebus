package erebus.core.handler;

import erebus.core.handler.configs.ConfigHandler;
import erebus.world.teleporter.TeleporterHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketSpawnPosition;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

public class EntityPlayerSpawnHandler {

		public static final String RESPAWN_IN_EREBUS_NBT = "erebus.respawn_in_erebus";

		@SubscribeEvent
		public void onPlayerDeath(LivingDeathEvent event) {
			if(event.getEntityLiving() instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();
				NBTTagCompound dataNbt = player.getEntityData();
				NBTTagCompound persistentNbt = dataNbt.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
				BlockPos spawnPos = player.getBedLocation(player.dimension);
				BlockPos adjustedSpawnPos = spawnPos == null ? null : EntityPlayer.getBedSpawnLocation(player.world, spawnPos, player.isSpawnForced(player.dimension));
				boolean respawnInErebus = ConfigHandler.INSTANCE.allowRespawning && (!player.world.provider.canRespawnHere() || adjustedSpawnPos == null);
				persistentNbt.setBoolean(RESPAWN_IN_EREBUS_NBT, respawnInErebus);
				dataNbt.setTag(EntityPlayer.PERSISTED_NBT_TAG, persistentNbt);
			}
		}

		@SubscribeEvent
		public void onRespawn(PlayerRespawnEvent event) {
			if(!event.player.world.isRemote) {
				BlockPos spawnPos = event.player.getBedLocation(event.player.dimension);
				BlockPos adjustedSpawnPos = spawnPos == null ? null : EntityPlayer.getBedSpawnLocation(event.player.world, spawnPos, event.player.isSpawnForced(event.player.dimension));
				NBTTagCompound dataNbt = event.player.getEntityData();
				NBTTagCompound persistentNbt = dataNbt.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
				boolean shouldTeleportToErebus = ConfigHandler.INSTANCE.allowRespawning || persistentNbt.getBoolean(RESPAWN_IN_EREBUS_NBT);

				if(shouldTeleportToErebus && event.player.world instanceof WorldServer) {
					WorldServer erebusWorld = ((WorldServer) event.player.world).getMinecraftServer().getWorld(ConfigHandler.INSTANCE.erebusDimensionID);
					TeleporterHandler.transferToErebus(event.player);
					spawnPos = event.player.getBedLocation(event.player.dimension);
					adjustedSpawnPos = spawnPos == null ? null : EntityPlayer.getBedSpawnLocation(event.player.world, spawnPos, event.player.isSpawnForced(event.player.dimension));
					if(adjustedSpawnPos != null) {
						event.player.setPosition(adjustedSpawnPos.getX() + 0.5D, adjustedSpawnPos.getY(), adjustedSpawnPos.getZ() + 0.5D);
						if(event.player instanceof EntityPlayerMP) {
							EntityPlayerMP playerMP = (EntityPlayerMP) event.player;
							playerMP.connection.setPlayerLocation(playerMP.posX, playerMP.posY, playerMP.posZ, playerMP.rotationYaw, playerMP.rotationPitch);
							playerMP.connection.sendPacket(new SPacketSpawnPosition(adjustedSpawnPos));
						}
					}
				}

				if(shouldTeleportToErebus || event.player.dimension == ConfigHandler.INSTANCE.erebusDimensionID) {
					boolean newRespawn = false;
					if(adjustedSpawnPos == null) {
						newRespawn = true;
					} else {
						IBlockState stateDown = event.player.world.getBlockState(adjustedSpawnPos.down());
						boolean isValidSpawnBlock = stateDown.getMaterial().blocksMovement() && !stateDown.getBlock().isLeaves(stateDown, event.player.world, adjustedSpawnPos.down()) && !stateDown.getBlock().isFoliage(event.player.world, adjustedSpawnPos.down());
						if(!isValidSpawnBlock) {
							newRespawn = true;
						}
					}
					if(newRespawn) {
						if(persistentNbt.hasKey("erebus.last_portal_location", Constants.NBT.TAG_LONG)) {
							BlockPos lastPortal = BlockPos.fromLong(persistentNbt.getLong("erebus.last_portal_location"));
							respawnAtPortal(event.player, lastPortal);
						}
					}
				}
			}
		}

		public static void respawnAtPortal(Entity entity, BlockPos newSpawn) {
			entity.setLocationAndAngles(newSpawn.getX() + 0.5D, newSpawn.getY(), newSpawn.getZ() + 0.5D, entity.rotationYaw, entity.rotationPitch);
			newSpawn = new BlockPos(entity);
			if(entity instanceof EntityPlayer)
				((EntityPlayer)entity).setSpawnPoint(newSpawn, true);

			if(entity instanceof EntityPlayerMP) {
				EntityPlayerMP playerMP = (EntityPlayerMP) entity;
				playerMP.connection.setPlayerLocation(playerMP.posX, playerMP.posY, playerMP.posZ, playerMP.rotationYaw, playerMP.rotationPitch);
				playerMP.connection.sendPacket(new SPacketSpawnPosition(newSpawn));
			}
		}
}