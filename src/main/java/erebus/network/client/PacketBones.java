package erebus.network.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import erebus.core.helper.Utils;
import erebus.network.AbstractClientPacket;
import erebus.tileentity.TileEntityBones;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.world.World;

public class PacketBones extends AbstractClientPacket {

	private int x, y, z;;
	private String name;

	public PacketBones() {
	}

	public PacketBones(int x, int y, int z, String name) {
		this();
		this.x = x;
		this.y = y;
		this.z = z;
		this.name = name;
	}

	@Override
	protected void handle(World world, EntityClientPlayerMP player) {
		TileEntityBones tile = Utils.getTileEntity(world, x, y, z, TileEntityBones.class);
		if (tile != null)
			tile.setOwner(name);
	}

	@Override
	public void write(ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		ByteBufUtils.writeUTF8String(buffer, name);
	}

	@Override
	public void read(ByteBuf buffer) {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		name = ByteBufUtils.readUTF8String(buffer);
	}
}