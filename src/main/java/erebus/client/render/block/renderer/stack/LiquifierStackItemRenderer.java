package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.Erebus;
import erebus.client.render.block.model.LiquifierModel;
import erebus.client.render.util.FluidRenderHelper;
import erebus.registries.client.ModBlockEntityRendering;
import erebus.registries.data.ModDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

import javax.annotation.Nonnull;

public class LiquifierStackItemRenderer extends BlockEntityWithoutLevelRenderer {
	private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/liquifier.png");
	private final LiquifierModel model;
	private FluidStack fluidStack;

	public LiquifierStackItemRenderer(BlockEntityRenderDispatcher renderer, EntityModelSet modelSet) {
		super(renderer, modelSet);
		EntityModelSet EntityModelSetThatIsntNULL = Minecraft.getInstance().getEntityModels();
		model = new LiquifierModel(EntityModelSetThatIsntNULL.bakeLayer(ModBlockEntityRendering.LIQUIFIER));
	}

	@Override
	public void renderByItem(ItemStack stack, @Nonnull ItemDisplayContext transformType, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLight, int combinedOverlayIn) {
		fluidStack = stack.getOrDefault(ModDataComponents.FLUID.get(), FluidContents.EMPTY).get();
		float fluidLevel = fluidStack.getAmount();
		if (fluidLevel > 0) {
			float tankMax = 8000F;
			float height = (0.375F / tankMax) * fluidLevel;
			
			float xMax = 1.984375F;
			float zMax = 1.984375F;
			float xMin = 0.015625F;
			float zMin = 0.015625F;
			float yMin = 0.015625F;
			
			FluidRenderHelper.renderFluid(fluidStack, matrixStack, bufferIn, xMin, xMax, yMin, height, zMin, zMax, combinedLight);
		}

		VertexConsumer consumer = bufferIn.getBuffer(RenderType.entityTranslucent(TEXTURE));

		matrixStack.pushPose();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		matrixStack.translate(0.5D, 1.5D, 0.5D);
		matrixStack.scale(-1, -1, 1);
		model.renderToBuffer(matrixStack, consumer, combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		model.renderBlades(matrixStack, consumer, combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		model.renderLidStatic(matrixStack, consumer, combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		RenderSystem.disableBlend();
		RenderSystem.depthMask(true);
		matrixStack.popPose();
	}

}
