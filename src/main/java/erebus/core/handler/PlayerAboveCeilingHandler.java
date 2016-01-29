package erebus.core.handler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.block.BlockArmchair;
import erebus.core.handler.configs.ConfigHandler;
import erebus.network.PacketPipeline;
import erebus.network.server.PacketArmchairClientMessages;

public class PlayerAboveCeilingHandler {
	@SubscribeEvent
	public void onEntityOnCeiling(PlayerEvent event) {
		World world = event.entity.worldObj;
		if (world.isRemote)
			return;
		if (event.entityPlayer.dimension == ConfigHandler.INSTANCE.erebusDimensionID) {
			if(event.entityPlayer.posY >= 128) {
				if (event.entityPlayer.getEntityData().hasKey("armchairSpawn")) {
					int aX = event.entityPlayer.getEntityData().getInteger("armchairX");
					int aY = event.entityPlayer.getEntityData().getInteger("armchairY");
					int aZ = event.entityPlayer.getEntityData().getInteger("armchairZ");
					if (world.getBlock(aX, aY, aZ) instanceof BlockArmchair) {
						if (world.isAirBlock(aX, aY + 1, aZ) && world.isAirBlock(aX, aY + 2, aZ))
							event.entityPlayer.setLocationAndAngles(aX + 0.5D, aY + 0.5D, aZ + 0.5D, event.entityPlayer.rotationYaw, event.entityPlayer.rotationPitch);
						else {
							PacketPipeline.sendToPlayer(event.entityPlayer, new PacketArmchairClientMessages((byte) 2));
							spawnAtPortal(world, (EntityPlayerMP) event.entityPlayer);
						}
					}
					if (!(world.getBlock(aX, aY, aZ) instanceof BlockArmchair)) {
						PacketPipeline.sendToPlayer(event.entityPlayer, new PacketArmchairClientMessages((byte) 3));
						spawnAtPortal(world, (EntityPlayerMP) event.entityPlayer);
						event.entityPlayer.getEntityData().setBoolean("armchairSpawn", false);
					}
				}
				if (!event.entityPlayer.getEntityData().hasKey("armchairSpawn"))
					spawnAtPortal(world, (EntityPlayerMP) event.entityPlayer);
			}
		}
	}

	private void spawnAtPortal(World world, EntityPlayerMP player) {
		int pX = player.getEntityData().getInteger("erebusPortalX");
		int pY = player.getEntityData().getInteger("erebusPortalY");
		int pZ = player.getEntityData().getInteger("erebusPortalZ");
		player.playerNetServerHandler.setPlayerLocation(pX + 0.5D, pY + 1.25D, pZ + 0.5D, player.rotationYaw, player.rotationPitch);
	}
}