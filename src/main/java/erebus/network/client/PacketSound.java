package erebus.network.client;

import erebus.network.AbstractClientPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.world.World;

public class PacketSound extends AbstractClientPacket {

	public static final byte SOUND_VELOCITY_USE = 0;
	public static final byte SOUND_CAMO_USE = 1;

	private byte type;
	private double x, y, z;
	private float loudness, pitch;

	public PacketSound() {
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
	protected void handle(World world, EntityClientPlayerMP player) {
		String s = null;

		switch (type) {
			case SOUND_VELOCITY_USE:
				s = "erebus:centipedesound";
				break;
			case SOUND_CAMO_USE:
				s = "erebus:mantissound";
				break;
		}

		if (s != null)
			player.worldObj.playSound(x, y, z, s, loudness, pitch, true);
	}

	@Override
	public void write(ByteBuf buffer) {
		buffer.writeByte(type);
		buffer.writeDouble(x);
		buffer.writeDouble(y);
		buffer.writeDouble(z);
		buffer.writeFloat(loudness);
		buffer.writeFloat(pitch);
	}

	@Override
	public void read(ByteBuf buffer) {
		type = buffer.readByte();
		x = buffer.readDouble();
		y = buffer.readDouble();
		z = buffer.readDouble();
		loudness = buffer.readFloat();
		pitch = buffer.readFloat();
	}
}