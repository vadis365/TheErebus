package erebus.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import erebus.network.PacketTypeHandler;
import erebus.tileentity.TileEntityJarOHoney;

public class PacketJarOHoney extends CustomPacket {

	private int x, y, z;
	private FluidStack fluid;
	private String name;

	public PacketJarOHoney() {
		super(PacketTypeHandler.JAR_O_HONEY);
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
	public void readData(DataInputStream data) throws IOException {
		x = data.readInt();
		y = data.readInt();
		z = data.readInt();
		fluid = PacketTypeHandler.readFluidStack(data);
		name = data.readUTF();
	}

	@Override
	public void writeData(DataOutputStream dos) throws IOException {
		dos.writeInt(x);
		dos.writeInt(y);
		dos.writeInt(z);
		PacketTypeHandler.writeFluidStack(fluid, dos);
		dos.writeUTF(name);
	}

	@Override
	public void execute(World world, EntityPlayer player) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (tile instanceof TileEntityJarOHoney) {
			((TileEntityJarOHoney) tile).tank.setFluid(fluid);
			((TileEntityJarOHoney) tile).setOwner(name);
		}
	}
}