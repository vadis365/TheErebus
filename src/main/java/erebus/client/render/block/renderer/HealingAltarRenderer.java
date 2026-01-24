package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.block.entity.HealingAltarBlockEntity;
import erebus.client.render.block.model.HealingAltarModel;
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
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class HealingAltarRenderer implements BlockEntityRenderer<HealingAltarBlockEntity, HealingAltarBlockEntityRenderState> {
	private final MaterialMapper MAPPER = new MaterialMapper(Erebus.prefix("textures/atlas/healing_altars.png"), "special/tiles/altar_healing");
	private final Material STEP1 = MAPPER.apply(Erebus.prefix("1.png"));
	private final Material STEP2 = MAPPER.apply(Erebus.prefix("2.png"));
	private final Material STEP3 = MAPPER.apply(Erebus.prefix("3.png"));
	private final Material STEP4 = MAPPER.apply(Erebus.prefix("4.png"));
	private final Material STEP5 = MAPPER.apply(Erebus.prefix("5.png"));

	private final List<Material> steps = List.of(STEP1, STEP2, STEP3, STEP4, STEP5);

	private final HealingAltarModel model;
	private final MaterialSet materials;
	
	public HealingAltarRenderer(Context context) {
		model = new HealingAltarModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_HEALING));
		materials = context.materials();
	}

	@Override
	public HealingAltarBlockEntityRenderState createRenderState() {
		return new HealingAltarBlockEntityRenderState();
	}

	@Override
	public void extractRenderState(HealingAltarBlockEntity blockEntity, HealingAltarBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
		BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
		state.animationTicks = blockEntity.animationTicks;
	}

	@Override
	public void submit(HealingAltarBlockEntityRenderState renderState, PoseStack stack, SubmitNodeCollector submitNodeCollector, CameraRenderState cameraRenderState) {
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
