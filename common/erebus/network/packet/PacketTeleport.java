package erebus.network.packet;

import java.net.URL;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;

import erebus.client.sound.AmbientMusicManager;
import erebus.core.teleport.TeleportClient;
import erebus.network.IPacket;

public class PacketTeleport implements IPacket {

	@Override
	public void handle(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data) {
		TeleportClient.timeInPortal = 0;
		TeleportClient.prevTimeInPortal = 0;
		TeleportClient.inPortal = false;
		TeleportClient.timeUntilPortal = 20;

		if (data.readBoolean()) {
			Entry<String, URL> entry = AmbientMusicManager.getInstance().getEntry("feint_sleepless.ogg");
			if (entry != null)
				AmbientMusicManager.getInstance().play(entry);
		}
	}
}
