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
		System.out.println("AAAAAAAAAAAAHAHHHHHHHHHHHHHHHHHHHHHHHHGGGGGGGGGGGGGGHHHHHHH");
		System.out.println(Erebus.prefix("fluids/" + this.fluidName + "_still").toString());
		return Erebus.prefix("fluids/" + this.fluidName + "_still");
	}

	@Override
	public ResourceLocation getFlowingTexture() {
		System.out.println("AAAAAAAAAAAAHAHHHHHHHHHHHHHHHHHHHHHHHHGGGGGGGGGGGGGGHHHHHHH");
		System.out.println(Erebus.prefix("fluids/" + this.fluidName + "_flow").toString());
		return Erebus.prefix("fluids/" + this.fluidName + "_flowing");
	}
}
