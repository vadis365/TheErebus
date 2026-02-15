package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.entity.HealingAltarBlockEntity;
import erebus.client.render.block.model.altar.healing.HealingAltarBaseModel;
import erebus.client.render.block.model.altar.healing.HealingAltarMidModel;
import erebus.client.render.block.model.altar.healing.HealingAltarRoseModel;
import erebus.client.render.block.renderer.state.HealingAltarBlockEntityRenderState;
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

public class HealingAltarRenderer implements BlockEntityRenderer<HealingAltarBlockEntity, HealingAltarBlockEntityRenderState> {
	private final MaterialMapper MAPPER = new MaterialMapper(TextureAtlas.LOCATION_BLOCKS, "altar_healing");
	private final Material STEP1 = MAPPER.apply(Erebus.prefix("1"));
	private final Material STEP2 = MAPPER.apply(Erebus.prefix("2"));
	private final Material STEP3 = MAPPER.apply(Erebus.prefix("3"));
	private final Material STEP4 = MAPPER.apply(Erebus.prefix("4"));
	private final Material STEP5 = MAPPER.apply(Erebus.prefix("5"));

	private final List<Material> steps = List.of(STEP1, STEP2, STEP3, STEP4, STEP5);

	private final HealingAltarBaseModel base;
	private final HealingAltarMidModel middle;
	private final HealingAltarRoseModel rose;
	private final MaterialSet materials;
	
	public HealingAltarRenderer(Context context) {
		base = new HealingAltarBaseModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_HEALING_BASE));
		middle = new HealingAltarMidModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_HEALING_MID));
		rose = new HealingAltarRoseModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_HEALING_ROSE));
		materials = context.materials();
	}

	@Override
	public HealingAltarBlockEntityRenderState createRenderState() {
		return new HealingAltarBlockEntityRenderState();
	}

	@Override
	public void extractRenderState(HealingAltarBlockEntity blockEntity, HealingAltarBlockEntityRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
		state.animationTicks = blockEntity.animationTicks;
	}

	@Override
	public void submit(HealingAltarBlockEntityRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
		Material material = steps.get(state.getStep());

		pose.pushPose();
		pose.translate(0.5D, 0.75D, 0.5D);
		pose.scale(-0.5F, -0.5F, 0.5F);
		submitRose(state, pose, submit, material);
		submitMiddle(state, pose, submit, material);
		submitBase(state, pose, submit, material);
		pose.popPose();
	}

	private void submitRose(HealingAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit, Material material) {
		pose.pushPose();
		pose.scale(0.04F * state.animationTicks, 0.04F * state.animationTicks, 0.04F * state.animationTicks);
		submit.submitModel(rose, state, pose, material.renderType(RenderTypes::entityCutout), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, materials.get(material), 0, state.breakProgress);
		pose.popPose();
	}

	private void submitMiddle(HealingAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit, Material material) {
		pose.pushPose();
		pose.rotateAround(Axis.YP.rotation(-state.animationTicks * 7.2F), 0, 1, 0);
		submit.submitModel(middle, state, pose, material.renderType(RenderTypes::entityCutout), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, materials.get(material), 0, state.breakProgress);
		pose.popPose();
	}

	private void submitBase(HealingAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit, Material material) {
		pose.pushPose();
		pose.rotateAround(Axis.YP.rotation(state.animationTicks * 7.2F), 0, 1, 0);
		submit.submitModel(base, state, pose, material.renderType(RenderTypes::entityCutout), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, materials.get(material), 0, state.breakProgress);
		pose.popPose();
	}
}
