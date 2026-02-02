package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.block.entity.FluidJarBlockEntity;
import erebus.client.render.block.renderer.state.FluidJarBlockEntityRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.state.CameraRenderState;

public class FluidJarRenderer implements BlockEntityRenderer<FluidJarBlockEntity, FluidJarBlockEntityRenderState> {
	
	public FluidJarRenderer(Context context) {
	}

	@Override
	public FluidJarBlockEntityRenderState createRenderState() {
		return new FluidJarBlockEntityRenderState();
	}

	@Override
	public void submit(FluidJarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector collector, CameraRenderState camera) {
		if (state.tankResource.isEmpty() || state.tankAmount <= 0)
			return;

		float height = (0.7421875F / state.tankCapacity) * state.tankAmount;

		float xMax = 0.9296875F;
		float zMax = 0.9296875F;
		float xMin = 0.0703125F;
		float zMin = 0.0703125F;
		float yMin = 0.015625F;

		// Note: FluidRenderHelper should be updated to use SubmitNodeCollector if it doesn't already.
		// For now, we assume it takes PoseStack and other standard params.
		// FluidRenderHelper.renderFluid(state.tankResource.toStack(state.tankAmount), pose, collector, xMin, xMax, yMin, height, zMin, zMax, state.lightCoords);
	}

	@Override
	public void extractRenderState(FluidJarBlockEntity tile, FluidJarBlockEntityRenderState state, float partialTicks, net.minecraft.world.phys.Vec3 cameraPos, net.minecraft.client.renderer.feature.ModelFeatureRenderer.CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(tile, state, partialTicks, cameraPos, breakProgress);
		state.tankResource = tile.tank.getResource(0);
		state.tankAmount = tile.tank.getAmountAsInt(0);
		state.tankCapacity = FluidJarBlockEntity.MAX_CAPACITY;
	}
}
