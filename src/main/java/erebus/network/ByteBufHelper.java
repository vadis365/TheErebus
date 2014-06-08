package erebus.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fluids.FluidStack;
import cpw.mods.fml.common.network.ByteBufUtils;

public class ByteBufHelper {

	public static void writeFluidStack(FluidStack fluid, ByteBuf buffer) {
		if (fluid == null)
			buffer.writeInt(-1);
		else {
			buffer.writeInt(fluid.fluidID);
			buffer.writeInt(fluid.amount);
			ByteBufUtils.writeTag(buffer, fluid.tag);
		}
	}

	public static FluidStack readFluidStack(ByteBuf buffer) {
		FluidStack fluid = null;

		int fluidID = buffer.readInt();
		if (fluidID > -1)
			fluid = new FluidStack(fluidID, buffer.readInt(), ByteBufUtils.readTag(buffer));

		return fluid;
	}
}