package erebus.client.render.block.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.entity.LiquifierBlockEntity;
import erebus.client.render.block.model.LiquifierModel;
import erebus.client.render.util.FluidRenderHelper;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

public class LiquifierRenderer implements BlockEntityRenderer<LiquifierBlockEntity> {
	private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/liquifier.png");
	private final LiquifierModel model;
	private final ItemRenderer itemRenderer;
	
	public LiquifierRenderer(Context context) {
		model = new LiquifierModel(context.bakeLayer(ModBlockEntityRendering.LIQUIFIER));
		itemRenderer = context.getItemRenderer();
	}

	@Override
    public void render(@NotNull LiquifierBlockEntity tile, float partialTick, @NotNull PoseStack stack, @NotNull MultiBufferSource bufferIn, int combinedLight, int combinedOverlay) {
		if(!tile.hasLevel())
			return;

		if (!tile.tank.getFluid().isEmpty()) {
			float fluidLevel = tile.tank.getFluidAmount();
			if (fluidLevel > 0) {
				FluidStack fluidStack = new FluidStack(tile.tank.getFluid().getFluidHolder(), 100);
				float height = (0.375F / tile.tank.getCapacity()) * tile.tank.getFluidAmount();
				
				float xMax = 1.984375F;
				float zMax = 1.984375F;
				float xMin = 0.015625F;
				float zMin = 0.015625F;
				float yMin = 0.015625F;
				
				FluidRenderHelper.renderFluid(fluidStack, stack, bufferIn, xMin, xMax, yMin, height, zMin, zMax, combinedLight);
			}
		}

		float ticks = tile.animationTicks + (tile.animationTicks - tile.prevAnimationTicks) * partialTick;

		stack.pushPose();
		stack.translate(0.5D, 0.5D, 0.5D);
		if(!tile.getItems().getFirst().isEmpty()) {
			stack.mulPose(Axis.YP.rotationDegrees(ticks));
			renderItemInSlot(tile, partialTick, stack, bufferIn, combinedLight, combinedOverlay, tile.getItems().get(0), 0D, 0D, 0D, 0.25F);
		}
		stack.popPose();

		VertexConsumer buffer = bufferIn.getBuffer(RenderType.entityTranslucent(TEXTURE));

		stack.pushPose();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		stack.translate(0.5D, 1.5D, 0.5D);
		stack.scale(-1, -1, 1);
		model.renderLidAnimated(stack, buffer, combinedLight, combinedOverlay, 0xFFFFFFFF, tile, partialTick);
		stack.pushPose();
		stack.mulPose(Axis.YP.rotationDegrees(ticks));
		model.renderBlades(stack, buffer, combinedLight, combinedOverlay, 0xFFFFFFFF);
		stack.popPose();
		model.renderToBuffer(stack, buffer, combinedLight, combinedOverlay, 0xFFFFFFFF);
		RenderSystem.disableBlend();
		RenderSystem.depthMask(true);
		stack.popPose();
	}

	public void renderItemInSlot(LiquifierBlockEntity tile, float partialTick, PoseStack stack, MultiBufferSource bufferIn, int packedLight, int packedOverlay, ItemStack itemStack, double x, double y, double z, float scale) {
		if (!itemStack.isEmpty()) {
			stack.pushPose();
			stack.translate(x, y, z);
			stack.scale(-scale, -scale, scale);
			stack.mulPose(Axis.YP.rotationDegrees(180));
			stack.mulPose(Axis.XP.rotationDegrees(180));
			itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, stack, bufferIn, tile.getLevel(), 1);
			stack.popPose();
		}
	}


}
