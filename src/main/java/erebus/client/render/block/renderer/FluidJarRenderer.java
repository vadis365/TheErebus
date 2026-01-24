package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.block.entity.FluidJarBlockEntity;
import erebus.client.render.block.renderer.state.FluidJarBlockEntityRenderState;
import erebus.client.render.util.FluidRenderHelper;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.neoforged.neoforge.fluids.FluidStack;

public class FluidJarRenderer implements BlockEntityRenderer<FluidJarBlockEntity, FluidJarBlockEntityRenderState> {
	
	public FluidJarRenderer(Context context) {
	}

	@Override
	public FluidJarBlockEntityRenderState createRenderState() {
		return new FluidJarBlockEntityRenderState();
	}

	@Override
	public void submit(FluidJarBlockEntityRenderState fluidJarBlockEntityRenderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
		if (!tile.hasLevel())
			return;
		if (tile.tank.getFluid().isEmpty())
			return;
		float fluidLevel = tile.tank.getFluidAmount();
		if (fluidLevel < 1)
			return;
		FluidStack fluidStack = new FluidStack(tile.tank.getFluid().getFluidHolder(), 100);
		float height = (0.7421875F / tile.tank.getCapacity()) * tile.tank.getFluidAmount();

		float xMax = 1.859375F;
		float zMax = 1.859375F;
		float xMin = 0.140625F;
		float zMin = 0.140625F;
		float yMin = 0.015625F;

		FluidRenderHelper.renderFluid(fluidStack, stack, bufferIn, xMin, xMax, yMin, height, zMin, zMax, combinedLight);
	}
}
