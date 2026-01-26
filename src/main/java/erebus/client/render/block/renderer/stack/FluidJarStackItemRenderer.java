package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.util.FluidRenderHelper;
import erebus.registries.data.ModDataComponents;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidResource;

import javax.annotation.Nonnull;

public class FluidJarStackItemRenderer extends BlockEntityWithoutLevelRenderer {

	private FluidStack fluidStack;

	public FluidJarStackItemRenderer(BlockEntityRenderDispatcher renderer, EntityModelSet modelSet) {
		super(renderer, modelSet);
	}

	@Override
	public void renderByItem(ItemStack stack, @Nonnull ItemDisplayContext transformType, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLight, int combinedOverlayIn) {
		fluidStack = stack.getOrDefault(ModDataComponents.FLUID.get(), FluidResource.EMPTY).toStack(1000);

		float fluidLevel = fluidStack.getAmount();
		if (fluidLevel < 1)
			return;
		float tankMax = 32000F;
		float height = (0.7421875F / tankMax) * fluidLevel; // volumes hardcoded until config

		float xMax = 1.859375F;
		float zMax = 1.859375F;
		float xMin = 0.140625F;
		float zMin = 0.140625F;
		float yMin = 0.015625F;

		FluidRenderHelper.renderFluid(fluidStack, matrixStack, bufferIn, xMin, xMax, yMin, height, zMin, zMax, combinedLight);
	}
}
