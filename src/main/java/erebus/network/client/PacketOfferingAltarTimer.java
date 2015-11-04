package erebus.network.client;

import erebus.core.helper.Utils;
import erebus.network.AbstractClientPacket;
import erebus.tileentity.TileEntityOfferingAltar;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.world.World;

public class PacketOfferingAltarTimer extends AbstractClientPacket {

	private int x, y, z, time;

	public PacketOfferingAltarTimer() {
	}

	public PacketOfferingAltarTimer(int x, int y, int z, int time) {
		this();
		this.x = x;
		this.y = y;
		this.z = z;
		this.time = time;
	}

	@Override
	protected void handle(World world, EntityClientPlayerMP player) {
		TileEntityOfferingAltar tile = Utils.getTileEntity(world, x, y, z, TileEntityOfferingAltar.class);
		if (tile != null)
			tile.time = time;
	}

	@Override
	public void write(ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		buffer.writeInt(time);
	}

	@Override
	public void read(ByteBuf buffer) {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		time = buffer.readInt();
	}
}