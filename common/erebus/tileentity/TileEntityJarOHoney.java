package erebus.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import cpw.mods.fml.common.network.PacketDispatcher;
import erebus.ModBlocks;
import erebus.network.PacketTypeHandler;
import erebus.network.packet.PacketJarOHoney;

public class TileEntityJarOHoney extends TileEntityGlowingJar {

	public static final int HONEY_MAX_AMOUNT = 4000;
	public final FluidTank tank = new FluidTank(ModBlocks.erebusHoney, 0, HONEY_MAX_AMOUNT);

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
			tank.setFluid(new FluidStack(ModBlocks.erebusHoney, 0));
		return tank.getFluid();
	}

	private void sendUpdatesToClients() {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		PacketDispatcher.sendPacketToAllPlayers(getDescriptionPacket());
	}

	@Override
	public Packet getDescriptionPacket() {
		return PacketTypeHandler.populatePacket(new PacketJarOHoney(xCoord, yCoord, zCoord, tank.getFluid()));
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		tank.readFromNBT(data);
	}

	@Override
	public void writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		tank.writeToNBT(data);
	}
}