package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.block.entity.ExperienceAltarBlockEntity;
import erebus.client.render.block.model.ExperienceAltarModel;
import erebus.client.render.block.state.ExperienceAltarBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.MaterialMapper;
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

import java.util.List;

public class ExperienceAltarRenderer implements BlockEntityRenderer<ExperienceAltarBlockEntity, ExperienceAltarBlockEntityRenderState> {

	private final MaterialMapper MAPPER = new MaterialMapper(Erebus.prefix("textures/atlas/xp_altars.png"), "special/tiles/altar_xp");
	private final Material STEP1 = MAPPER.apply(Erebus.prefix("1.png"));
	private final Material STEP2 = MAPPER.apply(Erebus.prefix("2.png"));
	private final Material STEP3 = MAPPER.apply(Erebus.prefix("3.png"));
	private final Material STEP4 = MAPPER.apply(Erebus.prefix("4.png"));
	private final Material STEP5 = MAPPER.apply(Erebus.prefix("5.png"));

	private final List<Material> steps = List.of(STEP1, STEP2, STEP3, STEP4, STEP5);

	private final ExperienceAltarModel model;
	private final MaterialSet materials;

	public ExperienceAltarRenderer(Context context) {
		model = new ExperienceAltarModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_EXPERIENCE));
		materials = context.materials();
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
	public void submit(ExperienceAltarBlockEntityRenderState renderState, @NonNull PoseStack stack, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState cameraRenderState) {
		Material material = steps.get(renderState.getStep());

		stack.pushPose();
		stack.translate(0.5D, 0.75D, 0.5D);
		stack.scale(-0.5F, -0.5F, 0.5F);
		submitNodeCollector.submitModel(
				model,
				renderState,
				stack,
				material.renderType(RenderTypes::entityCutout),
				renderState.lightCoords,
				OverlayTexture.NO_OVERLAY,
				-1,
				materials.get(material),
				0,
				renderState.breakProgress
		);
		stack.popPose();
	}
}
