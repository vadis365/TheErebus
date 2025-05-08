package erebus.block.fluid;

import erebus.Erebus;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;

public class BasicFluidType implements IClientFluidTypeExtensions {

	private final String fluidName;

	public BasicFluidType(String fluidName) {
		this.fluidName = fluidName;
	}

	@Override
	public ResourceLocation getStillTexture() {
		return Erebus.prefix("block/" + this.fluidName + "_still");
	}

	@Override
	public ResourceLocation getFlowingTexture() {
		return Erebus.prefix("block/" + this.fluidName + "_flowing");
	}
}
