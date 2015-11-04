package erebus.tileentity;

import erebus.ModFluids;
import erebus.network.AbstractPacket;
import erebus.network.PacketPipeline;
import erebus.network.client.PacketJarOHoney;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class TileEntityJarOHoney extends TileEntityGlowingJar {

	public static final int HONEY_MAX_AMOUNT = 4000;
	public final FluidTank tank = new FluidTank(ModFluids.honey, 0, HONEY_MAX_AMOUNT);
	private String owner = "Boo Boo";

	public int addHoney(int amount) {
		int result = tank.fill(FluidRegistry.getFluidStack("honey", amount), true);
		sendUpdatesToClients();
		return result;
	}

	public int drainHoney(int amount) {
		FluidStack fluid = tank.drain(amount, true);
		sendUpdatesToClients();
		return fluid == null ? 0 : fluid.amount;
	}

	public FluidStack getHoney() {
		if (tank.getFluid() == null)
			tank.setFluid(new FluidStack(ModFluids.honey, 0));
		return tank.getFluid();
	}

	private void sendUpdatesToClients() {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		PacketPipeline.sendToAll(getPacket());
	}

	public void setOwner(String name) {
		owner = name;
		if (!worldObj.isRemote)
			PacketPipeline.sendToAll(getPacket());
	}

	public String getOwnerName() {
		return owner;
	}

	public AbstractPacket getPacket() {
		return new PacketJarOHoney(xCoord, yCoord, zCoord, tank.getFluid(), owner);
	}

	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		NBTTagCompound nbt = packet.func_148857_g();
		if (packet.func_148853_f() == 0)
			readFromNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		tank.readFromNBT(data);
		owner = data.getString("owner");
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		tank.writeToNBT(data);
		data.setString("owner", owner);
	}
}