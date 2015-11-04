package erebus.network.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import erebus.core.helper.Utils;
import erebus.network.AbstractClientPacket;
import erebus.tileentity.TileEntityOfferingAltar;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class PacketOfferingAltar extends AbstractClientPacket {

	private int x, y, z;
	private NBTTagCompound nbt;

	public PacketOfferingAltar() {
	}

	public PacketOfferingAltar(int x, int y, int z, NBTTagCompound nbt) {
		this();
		this.x = x;
		this.y = y;
		this.z = z;
		this.nbt = nbt;
	}

	@Override
	protected void handle(World world, EntityClientPlayerMP player) {
		TileEntityOfferingAltar tile = Utils.getTileEntity(world, x, y, z, TileEntityOfferingAltar.class);
		if (tile != null)
			tile.readFromNBT(nbt);
	}

	@Override
	public void write(ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		ByteBufUtils.writeTag(buffer, nbt);
	}

	@Override
	public void read(ByteBuf buffer) {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		nbt = ByteBufUtils.readTag(buffer);
	}
}