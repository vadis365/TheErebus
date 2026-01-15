package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.block.entity.RepairAltarBlockEntity;
import erebus.client.render.block.model.RepairAltarModel;
import erebus.client.render.block.state.RepairAltarBlockEntityRenderState;
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

public class RepairAltarRenderer implements BlockEntityRenderer<RepairAltarBlockEntity, RepairAltarBlockEntityRenderState> {

	private final MaterialMapper MAPPER = new MaterialMapper(Erebus.prefix("textures/atlas/repair_altars.png"), "special/tiles/altar_repair_");
	private final Material STEP1 = MAPPER.apply(Erebus.prefix("1.png"));
	private final Material STEP2 = MAPPER.apply(Erebus.prefix("2.png"));
	private final Material STEP3 = MAPPER.apply(Erebus.prefix("3.png"));
	private final Material STEP4 = MAPPER.apply(Erebus.prefix("4.png"));
	private final Material STEP5 = MAPPER.apply(Erebus.prefix("5.png"));

	private final List<Material> steps = List.of(STEP1, STEP2, STEP3, STEP4, STEP5);

	private final RepairAltarModel model;
	private final MaterialSet materials;
	
	public RepairAltarRenderer(Context context) {
		model = new RepairAltarModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_REPAIR));
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
	public void submit(RepairAltarBlockEntityRenderState renderState, PoseStack stack, SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState cameraRenderState) {
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
