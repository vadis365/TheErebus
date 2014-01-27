package erebus.core.teleport;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import erebus.core.handler.ConfigurationHandler;
import erebus.network.PacketHandler;
import erebus.world.TeleporterErebus;

public class TeleportServer {

	private final EntityPlayerMP player;
	public float prevTimeInPortal;
	public float timeInPortal;
	public boolean inPortal = false;
	public Random random = new Random();
	public int timeUntilPortal = 20;

	public TeleportServer(EntityPlayerMP player) {
		this.player = player;
	}

	public EntityPlayerMP getPlayer() {
		return player;
	}

	public void sendPacket(Packet packet) {
		player.playerNetServerHandler.sendPacketToPlayer(packet);
	}

	public void onTick() {
		if (inPortal) {
			timeInPortal += 0.0125F;
			if (timeInPortal >= 1.0F) {
				timeInPortal = 1.0F;
				timeUntilPortal = 10;
				PacketDispatcher.sendPacketToPlayer(PacketHandler.buildPacket(0, player.dimension != ConfigurationHandler.erebusDimensionID), (Player) player);

				if (player.dimension == (byte) ConfigurationHandler.erebusDimensionID)
					player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, TeleporterErebus.TELEPORTER_TO_OVERWORLD);
				else if (player.dimension == (byte) 0)
					player.mcServer.getConfigurationManager().transferPlayerToDimension(player, ConfigurationHandler.erebusDimensionID, TeleporterErebus.TELEPORTER_TO_EREBUS);
			}
			inPortal = false;
		} else {
			if (timeInPortal > 0.0F)
				timeInPortal -= 0.05F;
			if (timeInPortal < 0.0F)
				timeInPortal = 0.0F;
		}
		if (timeUntilPortal > 0)
			--timeUntilPortal;
	}

	public void setInPortal() {
		if (timeUntilPortal > 0)
			timeUntilPortal = 10;
		else
			inPortal = true;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof TeleportServer)
			return ((TeleportServer) o).player.username.equals(player.username);
		return false;
	}

	@Override
	public int hashCode() {
		return player.username.hashCode();
	}
}