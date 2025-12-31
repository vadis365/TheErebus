package erebus.block.fluid;

import erebus.Erebus;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import org.jspecify.annotations.NonNull;

public class BasicFluidType implements IClientFluidTypeExtensions {

	private final String fluidName;

	public BasicFluidType(String fluidName) {
		this.fluidName = fluidName;
	}

	@Override
	public @NonNull Identifier getStillTexture() {
		return Erebus.prefix("block/" + this.fluidName + "_still");
	}

	@Override
	public @NonNull Identifier getFlowingTexture() {
		return Erebus.prefix("block/" + this.fluidName + "_flowing");
	}
}
