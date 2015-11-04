package erebus.network.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import erebus.core.helper.Utils;
import erebus.network.AbstractClientPacket;
import erebus.network.ByteBufHelper;
import erebus.tileentity.TileEntityJarOHoney;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class PacketJarOHoney extends AbstractClientPacket {

	private int x, y, z;
	private FluidStack fluid;
	private String name;

	public PacketJarOHoney() {
	}

	public PacketJarOHoney(int x, int y, int z, FluidStack fluid, String name) {
		this();
		this.x = x;
		this.y = y;
		this.z = z;
		this.fluid = fluid;
		this.name = name;
	}

	@Override
	protected void handle(World world, EntityClientPlayerMP player) {
		TileEntityJarOHoney tile = Utils.getTileEntity(world, x, y, z, TileEntityJarOHoney.class);
		if (tile != null) {
			tile.tank.setFluid(fluid);
			tile.setOwner(name);
		}
	}

	@Override
	public void write(ByteBuf buffer) {
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
		ByteBufHelper.writeFluidStack(fluid, buffer);
		ByteBufUtils.writeUTF8String(buffer, name);
	}

	@Override
	public void read(ByteBuf buffer) {
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
		fluid = ByteBufHelper.readFluidStack(buffer);
		name = ByteBufUtils.readUTF8String(buffer);
	}
}