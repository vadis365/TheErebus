package erebus.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import erebus.network.PacketTypeHandler;

public abstract class CustomPacket {

	public PacketTypeHandler packetType;

	public CustomPacket(PacketTypeHandler packetType) {
		this.packetType = packetType;
	}

	public byte[] populate() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		try {
			dos.writeByte(packetType.ordinal());
			writeData(dos);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}

		return bos.toByteArray();
	}

	public void readPopulate(DataInputStream data) {
		try {
			readData(data);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	public abstract void readData(DataInputStream data) throws IOException;

	public abstract void writeData(DataOutputStream dos) throws IOException;

	public abstract void execute(World world, EntityPlayer player);
}