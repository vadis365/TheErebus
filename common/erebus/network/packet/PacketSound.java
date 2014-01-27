package erebus.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;

import erebus.network.IPacket;

public class PacketSound implements IPacket {
	public static final byte SOUND_VELOCITY_USE = 1;
	public static final byte SOUND_CAMO_USE = 2;

	@Override
	public void handle(INetworkManager manager, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput data) {
		String s = null;
		switch (data.readByte()) {
			case SOUND_VELOCITY_USE:
				s = "erebus:CentipedeSound";
				break;
			case SOUND_CAMO_USE:
				s = "erebus:MantisSound";
				break;
		}

		if (s != null)
			player.worldObj.playSound(data.readDouble(), data.readDouble(), data.readDouble(), s, data.readFloat(), data.readFloat(), true);
	}
}
