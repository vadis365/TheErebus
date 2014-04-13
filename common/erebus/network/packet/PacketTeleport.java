package erebus.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import erebus.client.sound.AmbientMusicManager;
import erebus.core.teleport.TeleportClient;
import erebus.network.PacketTypeHandler;

public class PacketTeleport extends CustomPacket {

	private boolean something;

	public PacketTeleport() {
		super(PacketTypeHandler.TELEPORT);
	}

	public PacketTeleport(boolean something) {
		this();
		this.something = something;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		something = data.readBoolean();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeBoolean(something);
	}

	@Override
	public void execute(World world, EntityPlayer player) {
		TeleportClient.timeInPortal = 0;
		TeleportClient.prevTimeInPortal = 0;
		TeleportClient.inPortal = false;
		TeleportClient.timeUntilPortal = 20;

		if (something) {
			Entry<String, URL> entry = AmbientMusicManager.getInstance().getEntry("feint_sleepless.ogg");
			if (entry != null)
				AmbientMusicManager.getInstance().play(entry);
		}
	}
}