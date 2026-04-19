package erebus.block.fluid;

import erebus.Erebus;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;

public class BasicFluidType implements IClientFluidTypeExtensions {

	private final String fluidName;

	public BasicFluidType(String fluidName) {
		this.fluidName = fluidName;
	}

	public Identifier getStillTexture() {
		return Erebus.prefix("block/" + this.fluidName + "_still");
	}

	public Identifier getFlowingTexture() {
		return Erebus.prefix("block/" + this.fluidName + "_flowing");
	}
}
