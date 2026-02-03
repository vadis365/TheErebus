package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.entity.LiquifierBlockEntity;
import erebus.client.render.block.model.LiquifierModel;
import erebus.client.render.block.renderer.state.LiquifierBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class LiquifierRenderer implements BlockEntityRenderer<LiquifierBlockEntity, LiquifierBlockEntityRenderState> {
	private final Identifier TEXTURE = Erebus.prefix("textures/special/tiles/liquifier.png");
	private final LiquifierModel model;
	private final ItemModelResolver itemModelResolver;
	private final MaterialSet materials;
	
	public LiquifierRenderer(Context context) {
		model = new LiquifierModel(context.bakeLayer(ModBlockEntityRendering.LIQUIFIER));
		itemModelResolver = context.itemModelResolver();
		materials = context.materials();
	}

	/*@Override
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
	}*/

	@Override
	public void submit(LiquifierBlockEntityRenderState renderState, PoseStack pose, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState cameraRenderState) {
		Material material = Sheets.BLOCK_ENTITIES_MAPPER.apply(TEXTURE);

		if (!renderState.tankResource.isEmpty() && renderState.tankAmount > 0) {
			float height = (0.375F / renderState.tankCapacity) * renderState.tankAmount;

			float xMax = 0.9921875F;
			float zMax = 0.9921875F;
			float xMin = 0.0078125F;
			float zMin = 0.0078125F;
			float yMin = 0.0078125F;

			// FluidRenderHelper.renderFluid(renderState.tankResource.toStack(renderState.tankAmount), pose, submitNodeCollector, xMin, xMax, yMin, height, zMin, zMax, renderState.lightCoords);
		}

		pose.pushPose();
		pose.translate(0.5, 0.5, 0.5);
		renderState.itemStackRenderState.submit(pose, submitNodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
		pose.popPose();

		pose.pushPose();
		pose.translate(0.5, 1.5, 0.5);
		pose.scale(-1, -1, 1);
		pose.mulPose(Axis.YP.rotationDegrees(renderState.partialTicks));
		submitNodeCollector.submitModel(
				model,
				renderState,
				pose,
				material.renderType(RenderTypes::entityCutout),
				renderState.lightCoords,
				OverlayTexture.NO_OVERLAY,
				-1,
				materials.get(material),
				0,
				renderState.breakProgress
		);
		pose.popPose();
	}

	@Override
	public LiquifierBlockEntityRenderState createRenderState() {
		return new LiquifierBlockEntityRenderState();
	}

	@Override
	public void extractRenderState(LiquifierBlockEntity blockEntity, LiquifierBlockEntityRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
		state.partialTicks = partialTicks;
		state.tankResource = blockEntity.tank.getResource(0);
		state.tankAmount = blockEntity.tank.getAmountAsInt(0);
		state.tankCapacity = net.neoforged.neoforge.fluids.FluidType.BUCKET_VOLUME * 8;
		itemModelResolver.updateForTopItem(
				state.itemStackRenderState,
				blockEntity.getSlot(0).get(),
				ItemDisplayContext.FIXED,
				blockEntity.getLevel(),
				null,
				0
		);
	}
}
