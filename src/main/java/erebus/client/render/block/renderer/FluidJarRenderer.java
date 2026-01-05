package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.block.entity.FluidJarBlockEntity;
import erebus.client.render.util.FluidRenderHelper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class FluidJarRenderer implements BlockEntityRenderer<FluidJarBlockEntity> {
	
	public FluidJarRenderer(Context context) {
	}

	@Override
	public void render(@NotNull FluidJarBlockEntity tile, float partialTick, @NotNull PoseStack stack, @NotNull MultiBufferSource bufferIn, int combinedLight, int combinedOverlay) {
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
