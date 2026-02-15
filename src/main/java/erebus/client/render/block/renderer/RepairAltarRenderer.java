package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.entity.RepairAltarBlockEntity;
import erebus.client.render.block.model.altar.repair.RepairAltarAnvilModel;
import erebus.client.render.block.model.altar.repair.RepairAltarBaseModel;
import erebus.client.render.block.model.altar.repair.RepairAltarMidModel;
import erebus.client.render.block.renderer.state.RepairAltarBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.MaterialMapper;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class RepairAltarRenderer implements BlockEntityRenderer<RepairAltarBlockEntity, RepairAltarBlockEntityRenderState> {

	private final MaterialMapper MAPPER = new MaterialMapper(TextureAtlas.LOCATION_BLOCKS, "altar_repair");
	private final Material STEP1 = MAPPER.apply(Erebus.prefix("1"));
	private final Material STEP2 = MAPPER.apply(Erebus.prefix("2"));
	private final Material STEP3 = MAPPER.apply(Erebus.prefix("3"));
	private final Material STEP4 = MAPPER.apply(Erebus.prefix("4"));
	private final Material STEP5 = MAPPER.apply(Erebus.prefix("5"));

	private final List<Material> steps = List.of(STEP1, STEP2, STEP3, STEP4, STEP5);

	private final RepairAltarBaseModel base;
	private final RepairAltarMidModel middle;
	private final RepairAltarAnvilModel anvil;
	private final MaterialSet materials;
	
	public RepairAltarRenderer(Context context) {
		base = new RepairAltarBaseModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_REPAIR_BASE));
		middle = new RepairAltarMidModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_REPAIR_MID));
		anvil = new RepairAltarAnvilModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_REPAIR_ANVIL));
		materials = context.materials();
	}

	@Override
	public RepairAltarBlockEntityRenderState createRenderState() {
		return new RepairAltarBlockEntityRenderState();
	}

	@Override
	public void extractRenderState(RepairAltarBlockEntity blockEntity, RepairAltarBlockEntityRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
		state.animationTicks = blockEntity.animationTicks;
	}

	@Override
	public void submit(RepairAltarBlockEntityRenderState state, PoseStack pose, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
		Material material = steps.get(state.getStep());

		pose.pushPose();
		pose.translate(0.5D, 0.75D, 0.5D);
		pose.scale(-0.5F, -0.5F, 0.5F);
		submitAnvil(state, pose, submit, material);
		submitMiddle(state, pose, submit, material);
		submitBase(state, pose, submit, material);
		pose.popPose();
	}

	private void submitAnvil(RepairAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit, Material material) {
		pose.pushPose();
		pose.scale(0.04F * state.animationTicks, 0.04F * state.animationTicks, 0.04F * state.animationTicks);
		submit.submitModel(anvil, state, pose, material.renderType(RenderTypes::entityCutout), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, materials.get(material), 0, state.breakProgress);
		pose.popPose();
	}

	private void submitMiddle(RepairAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit, Material material) {
		pose.pushPose();
		pose.rotateAround(Axis.YP.rotation(-state.animationTicks * 7.2F), 0, 1, 0);
		submit.submitModel(middle, state, pose, material.renderType(RenderTypes::entityCutout), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, materials.get(material), 0, state.breakProgress);
		pose.popPose();
	}

	private void submitBase(RepairAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit, Material material) {
		pose.pushPose();
		pose.rotateAround(Axis.YP.rotation(state.animationTicks * 7.2F), 0, 1, 0);
		submit.submitModel(base, state, pose, material.renderType(RenderTypes::entityCutout), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, materials.get(material), 0, state.breakProgress);
		pose.popPose();
	}
}
