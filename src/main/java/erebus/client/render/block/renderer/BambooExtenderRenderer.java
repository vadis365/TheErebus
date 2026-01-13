package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.entity.BambooExtenderBlockEntity;
import erebus.client.render.block.model.BambooExtenderModel;
import erebus.client.render.block.state.BambooExtenderBlockEntityRenderState;
import erebus.registries.blocks.ModBlocks;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

@OnlyIn(Dist.CLIENT)
public class BambooExtenderRenderer implements BlockEntityRenderer<BambooExtenderBlockEntity, BambooExtenderBlockEntityRenderState> {

	private final Material TEXTURE = Sheets.BLOCK_ENTITIES_MAPPER.apply(Erebus.prefix("textures/special/tiles/bamboo_extender.png"));
	private final BambooExtenderModel model;
	private final MaterialSet materials;

    public BambooExtenderRenderer(Context context) {
		model = new BambooExtenderModel(context.bakeLayer(ModBlockEntityRendering.BAMBOO_EXTENDER));
		materials = context.materials();
    }

	@Override
	public BambooExtenderBlockEntityRenderState createRenderState() {
		return new BambooExtenderBlockEntityRenderState();
	}

	@Override
	public void extractRenderState(BambooExtenderBlockEntity blockEntity, BambooExtenderBlockEntityRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
		state.facing = blockEntity.direction;
	}

	@Override
	public void submit(BambooExtenderBlockEntityRenderState renderState, @NonNull PoseStack stack, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState cameraRenderState) {
		switch (renderState.facing) {
			case DOWN:
				stack.pushPose();
				stack.translate(0.5F, -0.5F, 0.5F);
				stack.scale(-1F, -1F, 1F);
				stack.mulPose(Axis.ZP.rotationDegrees(180F));
				stack.mulPose(Axis.YN.rotationDegrees(180F));
				submitNodeCollector.submitModel(
						model,
						renderState,
						stack,
						TEXTURE.renderType(RenderTypes::entityCutout),
						renderState.lightCoords,
						OverlayTexture.NO_OVERLAY,
						-1,
						materials.get(TEXTURE),
						0,
						renderState.breakProgress
				);
				stack.popPose();
				stack.pushPose();
				stack.translate(0F, 0.125F, 0F);
				stack.scale(1F, 0.875F, 1F);
				submitNodeCollector.submitBlock(
						stack,
						ModBlocks.PLANKS_BAMBOO.get().defaultBlockState(),
						renderState.lightCoords,
						OverlayTexture.NO_OVERLAY,
						0
				);
				stack.popPose();
				break;
			case UP:
				stack.pushPose();
				stack.translate(0.5F, 1.5F, 0.5F);
				stack.scale(-1F, -1F, 1F);
				stack.mulPose(Axis.YP.rotationDegrees(180F));
				submitNodeCollector.submitModel(
						model,
						renderState,
						stack,
						TEXTURE.renderType(RenderTypes::entityCutout),
						renderState.lightCoords,
						OverlayTexture.NO_OVERLAY,
						-1,
						materials.get(TEXTURE),
						0,
						renderState.breakProgress
				);
				stack.popPose();
				stack.pushPose();
				stack.scale(1F, 0.875F, 1F);
				submitNodeCollector.submitBlock(
						stack,
						ModBlocks.PLANKS_BAMBOO.get().defaultBlockState(),
						renderState.lightCoords,
						OverlayTexture.NO_OVERLAY,
						0
				);
				stack.popPose();
				break;
			case NORTH:
				stack.pushPose();
				stack.translate(0.5F, 1.5F, 0.5F);
				stack.scale(-1F, -1F, 1F);
				stack.mulPose(Axis.YP.rotationDegrees(0F));
				submitNodeCollector.submitModel(
						model,
						renderState,
						stack,
						TEXTURE.renderType(RenderTypes::entityCutout),
						renderState.lightCoords,
						OverlayTexture.NO_OVERLAY,
						-1,
						materials.get(TEXTURE),
						0,
						renderState.breakProgress
				);
				stack.popPose();
				break;
			case SOUTH:
				stack.pushPose();
				stack.translate(0.5F, 1.5F, 0.5F);
				stack.scale(-1F, -1F, 1F);
				stack.mulPose(Axis.YP.rotationDegrees(180F));
				submitNodeCollector.submitModel(
						model,
						renderState,
						stack,
						TEXTURE.renderType(RenderTypes::entityCutout),
						renderState.lightCoords,
						OverlayTexture.NO_OVERLAY,
						-1,
						materials.get(TEXTURE),
						0,
						renderState.breakProgress
				);
				stack.popPose();
				break;
			case WEST:
				stack.pushPose();
				stack.translate(0.5F, 1.5F, 0.5F);
				stack.scale(-1F, -1F, 1F);
				stack.mulPose(Axis.YN.rotationDegrees(90F));
				submitNodeCollector.submitModel(
						model,
						renderState,
						stack,
						TEXTURE.renderType(RenderTypes::entityCutout),
						renderState.lightCoords,
						OverlayTexture.NO_OVERLAY,
						-1,
						materials.get(TEXTURE),
						0,
						renderState.breakProgress
				);
				stack.popPose();
				break;
			case EAST:
				stack.pushPose();
				stack.translate(0.5F, 1.5F, 0.5F);
				stack.scale(-1F, -1F, 1F);
				stack.mulPose(Axis.YP.rotationDegrees(90F));
				submitNodeCollector.submitModel(
						model,
						renderState,
						stack,
						TEXTURE.renderType(RenderTypes::entityCutout),
						renderState.lightCoords,
						OverlayTexture.NO_OVERLAY,
						-1,
						materials.get(TEXTURE),
						0,
						renderState.breakProgress
				);
				stack.popPose();
				break;
		}
	}
}
