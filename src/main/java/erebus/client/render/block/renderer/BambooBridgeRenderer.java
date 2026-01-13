package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.bamboo.BambooBridge;
import erebus.block.entity.BambooBridgeBlockEntity;
import erebus.client.render.block.model.BambooBridgeModel;
import erebus.client.render.block.state.BambooBridgeBlockEntityRenderState;
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
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class BambooBridgeRenderer implements BlockEntityRenderer<BambooBridgeBlockEntity, BambooBridgeBlockEntityRenderState> {

	public final Material TEXTURE = Sheets.BLOCK_ENTITIES_MAPPER.apply(Erebus.prefix("textures/special/tiles/bamboo_bridge.png"));
	private final BambooBridgeModel model;
	private final MaterialSet materials;

	public BambooBridgeRenderer(Context context) {
		materials = context.materials();
		model = new BambooBridgeModel(context.bakeLayer(ModBlockEntityRendering.BAMBOO_BRIDGE));
	}

	@Override
	public BambooBridgeBlockEntityRenderState createRenderState() {
		return new BambooBridgeBlockEntityRenderState();
	}

	@Override
	public void extractRenderState(BambooBridgeBlockEntity blockEntity, BambooBridgeBlockEntityRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
		state.facing = blockEntity.getBlockState().getValue(BambooBridge.FACING);
	}

	@Override
	public void submit(BambooBridgeBlockEntityRenderState renderState, PoseStack pose, @NonNull SubmitNodeCollector nodeCollector, @NonNull CameraRenderState cameraRenderState) {
		pose.pushPose();
		pose.translate(0.5F, 1.5F, 0.5F);
		pose.scale(-1F, -1F, 1F);
		switch (renderState.facing) {
			case UP:
			case DOWN:
			case NORTH:
				pose.mulPose(Axis.YP.rotationDegrees(0F));
				break;
			case SOUTH:
				pose.mulPose(Axis.YP.rotationDegrees(180F));
				break;
			case WEST:
				pose.mulPose(Axis.YN.rotationDegrees(90F));
				break;
			case EAST:
				pose.mulPose(Axis.YP.rotationDegrees(90F));
				break;
		}
		pose.popPose();

		nodeCollector.submitModel(
				model,
				renderState,
				pose,
				TEXTURE.renderType(RenderTypes::entityCutoutNoCull),
				renderState.lightCoords,
				OverlayTexture.NO_OVERLAY,
				-1,
				materials.get(TEXTURE),
				0,
				renderState.breakProgress
		);
	}
}
