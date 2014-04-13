package erebus.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import erebus.network.PacketTypeHandler;

public class PacketSound extends CustomPacket {

	public static final byte SOUND_VELOCITY_USE = 0;
	public static final byte SOUND_CAMO_USE = 1;

	private byte type;
	private double x, y, z;
	private float loudness, pitch;

	public PacketSound() {
		super(PacketTypeHandler.SOUND);
	}

	public PacketSound(byte type, double x, double y, double z, float loudness, float pitch) {
		this();
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
		this.loudness = loudness;
		this.pitch = pitch;
	}

	@Override
	public void readData(DataInputStream data) throws IOException {
		type = data.readByte();
		x = data.readDouble();
		y = data.readDouble();
		z = data.readDouble();
		loudness = data.readFloat();
		pitch = data.readFloat();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeByte(type);
		dos.writeDouble(x);
		dos.writeDouble(y);
		dos.writeDouble(z);
		dos.writeFloat(loudness);
		dos.writeFloat(pitch);
	}

	@Override
	public void execute(World world, EntityPlayer player) {
		String s = null;

		switch (type) {
			case SOUND_VELOCITY_USE:
				s = "erebus:CentipedeSound";
				break;
			case SOUND_CAMO_USE:
				s = "erebus:MantisSound";
				break;
		}

		if (s != null)
			player.worldObj.playSound(x, y, z, s, loudness, pitch, true);
	}
}