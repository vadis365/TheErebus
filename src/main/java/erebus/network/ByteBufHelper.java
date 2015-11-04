package erebus.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class ByteBufHelper {

	public static void writeFluidStack(FluidStack fluid, ByteBuf buffer) {
		if (fluid == null)
			ByteBufUtils.writeUTF8String(buffer, "");
		else {
			ByteBufUtils.writeUTF8String(buffer, FluidRegistry.getFluidName(fluid.getFluid()));
			buffer.writeInt(fluid.amount);
			ByteBufUtils.writeTag(buffer, fluid.tag);
		}
	}

	public static FluidStack readFluidStack(ByteBuf buffer) {
		FluidStack fluid = null;

		String fluidName = ByteBufUtils.readUTF8String(buffer);
		if (StringUtils.isNullOrEmpty(fluidName))
			fluid = new FluidStack(FluidRegistry.getFluid(fluidName), buffer.readInt(), ByteBufUtils.readTag(buffer));

		return fluid;
	}
}