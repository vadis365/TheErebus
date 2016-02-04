package erebus.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.block.BlockArmchair;
import erebus.core.handler.configs.ConfigHandler;
import erebus.network.PacketPipeline;
import erebus.network.server.PacketArmchairClientMessages;

public class EntityPlayerSpawnHandler {

	@SuppressWarnings("unchecked")
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onClonePlayer(PlayerEvent.Clone event) {

		World world = event.entity.worldObj;
		if (world.isRemote)
			return;

		if (event.wasDeath) {
			if(event.entityPlayer.dimension == ConfigHandler.INSTANCE.erebusDimensionID) {
				if(event.original.getEntityData().hasKey("armchairX")) {
					int aX = event.original.getEntityData().getInteger("armchairX");
					int aY = event.original.getEntityData().getInteger("armchairY");
					int aZ = event.original.getEntityData().getInteger("armchairZ");
					event.entityPlayer.getEntityData().setInteger("armchairX", aX);
					event.entityPlayer.getEntityData().setInteger("armchairY", aY);
					event.entityPlayer.getEntityData().setInteger("armchairZ", aZ);
					event.entityPlayer.getEntityData().setBoolean("armchairSpawn", true);
				}

				if(event.original.getEntityData().hasKey("erebusPortalX")) {
					int pX = event.original.getEntityData().getInteger("erebusPortalX");
					int pY = event.original.getEntityData().getInteger("erebusPortalY");
					int pZ = event.original.getEntityData().getInteger("erebusPortalZ");
					event.entityPlayer.getEntityData().setInteger("erebusPortalX", pX);
					event.entityPlayer.getEntityData().setInteger("erebusPortalY", pY);
					event.entityPlayer.getEntityData().setInteger("erebusPortalZ", pZ);
				}

				if(event.entityPlayer.getEntityData().hasKey("armchairSpawn")) {
					int aX = event.entityPlayer.getEntityData().getInteger("armchairX");
					int aY = event.entityPlayer.getEntityData().getInteger("armchairY");
					int aZ = event.entityPlayer.getEntityData().getInteger("armchairZ");
					if(world.getBlock(aX, aY, aZ) instanceof BlockArmchair) {
						if(world.isAirBlock(aX, aY + 1, aZ) && world.isAirBlock(aX, aY + 2, aZ))
							event.entityPlayer.setLocationAndAngles(aX + 0.5D, aY + 1D, aZ + 0.5D, event.entityPlayer.rotationYaw, event.entityPlayer.rotationPitch);
						else {
							PacketPipeline.sendToPlayer(event.entityPlayer, new PacketArmchairClientMessages((byte)2));
							spawnAtPortal(world, event.entityPlayer);
						}
					}

					if(!(world.getBlock(aX, aY, aZ) instanceof BlockArmchair)) {
						PacketPipeline.sendToPlayer(event.entityPlayer, new PacketArmchairClientMessages((byte)3));
						spawnAtPortal(world, event.entityPlayer);
						event.entityPlayer.getEntityData().setBoolean("armchairSpawn", false);
					}	
				}

				if(!event.entityPlayer.getEntityData().hasKey("armchairSpawn"))
					spawnAtPortal(world, event.entityPlayer);
			}
		}
	}

	private void spawnAtPortal(World world, EntityPlayer player) {
		int pX = player.getEntityData().getInteger("erebusPortalX");
		int pY = player.getEntityData().getInteger("erebusPortalY");
		int pZ = player.getEntityData().getInteger("erebusPortalZ");
		player.setLocationAndAngles(pX + 0.5D, pY + 1.25D, pZ + 0.5D, player.rotationYaw, player.rotationPitch);
	}
}

