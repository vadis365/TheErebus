package erebus.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import erebus.lib.Reference;
import erebus.network.packet.PacketColossalCratePage;
import erebus.network.packet.PacketGlider;
import erebus.network.packet.PacketParticle;
import erebus.network.packet.PacketSound;
import erebus.network.packet.PacketTeleport;

public class PacketHandler implements IPacketHandler {

	private final Map<Short, IPacket> map = new HashMap<Short, IPacket>();

	public PacketHandler() {
		register(0, new PacketTeleport());
		register(1, new PacketColossalCratePage());
		register(2, new PacketParticle());
		register(3, new PacketSound());
		register(4, new PacketGlider());
	}

	private void register(int packetID, IPacket packet) {
		map.put((short) packetID, packet);
	}

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if (packet.channel.equals(Reference.CHANNEL)) {
			ByteArrayDataInput data = ByteStreams.newDataInput(packet.data);
			map.get(data.readShort()).handle(manager, packet, (EntityPlayer) player, data);
		}
	}

	public static Packet250CustomPayload buildPacket(int packetID, Object... data) {
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = Reference.CHANNEL;

		Object[] finalData = new Object[data.length + 1];
		finalData[0] = Short.valueOf((short) packetID);
		for (int a = 0; a < data.length; a++)
			finalData[a + 1] = data[a];

		ByteArrayOutputStream ns = PacketHandler.createDataStream(finalData);
		packet.data = ns.toByteArray();
		packet.length = ns.size();
		return packet;
	}

	public static ByteArrayOutputStream createDataStream(Object... data) {
		int bytes = 0;
		for (Object o : data)
			if (o instanceof String)
				bytes += ((String) o).length() * 2;
			else if (o instanceof Integer || o instanceof Float)
				bytes += 4;
			else if (o instanceof Boolean || o instanceof Byte)
				bytes += 1;
			else if (o instanceof Short)
				bytes += 2;
			else if (o instanceof Double)
				bytes += 8;

		ByteArrayOutputStream bos = new ByteArrayOutputStream(bytes);
		DataOutputStream dos = new DataOutputStream(bos);
		for (Object o : data)
			try {
				if (o instanceof String)
					dos.writeChars((String) o);
				else if (o instanceof Integer)
					dos.writeInt((Integer) o);
				else if (o instanceof Boolean)
					dos.writeBoolean((Boolean) o);
				else if (o instanceof Byte)
					dos.writeByte((Byte) o);
				else if (o instanceof Short)
					dos.writeShort((Short) o);
				else if (o instanceof Double)
					dos.writeDouble((Double) o);
				else if (o instanceof Float)
					dos.writeFloat((Float) o);
			} catch (IOException e) {
				e.printStackTrace();
			}
		return bos;
	}
}
