package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.entity.ExperienceAltarBlockEntity;
import erebus.client.render.block.model.altar.experience.ExperienceAltarBaseModel;
import erebus.client.render.block.model.altar.experience.ExperienceAltarGlassModel;
import erebus.client.render.block.model.altar.experience.ExperienceAltarMidModel;
import erebus.client.render.block.renderer.state.ExperienceAltarBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.SpriteMapper;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class ExperienceAltarRenderer implements BlockEntityRenderer<ExperienceAltarBlockEntity, ExperienceAltarBlockEntityRenderState> {

	private final SpriteMapper MAPPER = new SpriteMapper(TextureAtlas.LOCATION_BLOCKS, "altar_xp");
	private final SpriteId STEP1 = MAPPER.apply(Erebus.prefix("1"));
	private final SpriteId STEP2 = MAPPER.apply(Erebus.prefix("2"));
	private final SpriteId STEP3 = MAPPER.apply(Erebus.prefix("3"));
	private final SpriteId STEP4 = MAPPER.apply(Erebus.prefix("4"));
	private final SpriteId STEP5 = MAPPER.apply(Erebus.prefix("5"));

	private final List<SpriteId> steps = List.of(STEP1, STEP2, STEP3, STEP4, STEP5);

	private final ExperienceAltarBaseModel base;
	private final ExperienceAltarMidModel middle;
	private final ExperienceAltarGlassModel glass;
	private final SpriteGetter sprites;

	public ExperienceAltarRenderer(Context context) {
		base = new ExperienceAltarBaseModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_EXPERIENCE_BASE));
		middle = new ExperienceAltarMidModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_EXPERIENCE_MID));
		glass = new ExperienceAltarGlassModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_EXPERIENCE_GLASS));
		sprites = context.sprites();
	}

	@Override
	public ExperienceAltarBlockEntityRenderState createRenderState() {
		return new ExperienceAltarBlockEntityRenderState();
	}

	@Override
	public void extractRenderState(ExperienceAltarBlockEntity blockEntity, ExperienceAltarBlockEntityRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
		state.animationTicks = blockEntity.animationTicks;
	}

	@Override
	public void submit(ExperienceAltarBlockEntityRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
		SpriteId material = steps.get(state.getStep());

		pose.pushPose();
		pose.translate(0.5D, 0.75D, 0.5D);
		pose.scale(-0.5F, -0.5F, 0.5F);
		submitGlass(state, pose, submit, material);
		submitMiddle(state, pose, submit, material);
		submitBase(state, pose, submit, material);
		pose.popPose();
	}

	private void submitGlass(ExperienceAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit, SpriteId material) {
		pose.pushPose();
		pose.scale(0.04F * state.animationTicks, 0.04F * state.animationTicks, 0.04F * state.animationTicks);
		submit.submitModel(glass, state, pose, material.renderType(RenderTypes::entityCutout), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, sprites.get(material), 0, state.breakProgress);
		pose.popPose();
	}

	private void submitMiddle(ExperienceAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit, SpriteId material) {
		pose.pushPose();
		pose.rotateAround(Axis.YP.rotation(-state.animationTicks * 7.2F), 0, 1, 0);
		submit.submitModel(middle, state, pose, material.renderType(RenderTypes::entityCutout), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, sprites.get(material), 0, state.breakProgress);
		pose.popPose();
	}

	private void submitBase(ExperienceAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit, SpriteId material) {
		pose.pushPose();
		pose.rotateAround(Axis.YP.rotation(state.animationTicks * 7.2F), 0, 1, 0);
		submit.submitModel(base, state, pose, material.renderType(RenderTypes::entityCutout), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, sprites.get(material), 0, state.breakProgress);
		pose.popPose();
	}
}
