package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.VelvetWormModel;
import erebus.client.render.entity.renderer.state.VelvetWormRenderState;
import erebus.entity.VelvetWorm;
import erebus.entity.VelvetWormMultipart;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public class VelvetWormRenderer extends MobRenderer<VelvetWorm, VelvetWormRenderState, VelvetWormModel> {
	private static final Identifier[] TEXTURES = new Identifier[] {
			Erebus.prefix("textures/entity/velvetworm_1.png"),
			Erebus.prefix("textures/entity/velvetworm_2.png"),
			Erebus.prefix("textures/entity/velvetworm_3.png"),
			Erebus.prefix("textures/entity/velvetworm_4.png"),
			Erebus.prefix("textures/entity/velvetworm_5.png")
	};

	public VelvetWormRenderer(EntityRendererProvider.Context context) {
		super(context, new VelvetWormModel(context.bakeLayer(ModEntityRendering.VELVET_WORM)), 0F);
	}

	@Override
	public void submit(VelvetWormRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
		RenderType rt = model.renderType(getTextureLocation(state));

		for (int i = 0; i < state.bodyParts.length; i++) {
			VelvetWormRenderState.PartState part = state.bodyParts[i];
			pose.pushPose();
			pose.translate(part.x, part.y + 1.525f + (part.frame * -0.001f), part.z);
			pose.scale(-1F, -1F, 1F);
			pose.mulPose(Axis.YN.rotationDegrees(-part.yaw));

			submit.submitModel(
					model,
					state,
					pose,
					rt,
					state.lightCoords,
					OverlayTexture.NO_OVERLAY,
					-1,
					null,
					state.outlineColor,
					null
			);
			pose.popPose();
		}

		pose.pushPose();
		pose.translate(state.tail.x, state.tail.y + 1.525f, state.tail.z);
		pose.scale(-1F, -1F, 1F);
		pose.mulPose(Axis.YP.rotationDegrees(180F + state.tail.yaw));
		submit.submitModel(
				model,
				state,
				pose,
				rt,
				state.lightCoords,
				OverlayTexture.NO_OVERLAY,
				-1,
				null,
				state.outlineColor,
				null
		);
		pose.popPose();
	}

	@Override
	protected void setupRotations(VelvetWormRenderState state, PoseStack pose, float bodyRot, float entityScale) {
		super.setupRotations(state, pose, bodyRot, entityScale);
		pose.translate(state.head.x, state.head.y, state.head.z);
		pose.mulPose(Axis.YP.rotationDegrees(state.head.yaw - state.yRot));
	}

	@Override
	public VelvetWormRenderState createRenderState() {
		return new VelvetWormRenderState();
	}

	@Override
	public void extractRenderState(VelvetWorm entity, VelvetWormRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.skin = entity.getSkin();

		double ex = entity.xOld + (entity.getX() - entity.xOld) * partialTicks;
		double ey = entity.yOld + (entity.getY() - entity.yOld) * partialTicks;
		double ez = entity.zOld + (entity.getZ() - entity.zOld) * partialTicks;

		float totalAngleDiff = 0.0f;
		for(int i = 0; i < entity.parts.length; i++) {
			Entity prevPart = i > 0 ? entity.parts[i - 1] : entity;
			VelvetWormMultipart part = entity.parts[i];
			double yawDiff = (prevPart.getYRot() - part.getYRot()) % 360.0F;
			double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
			totalAngleDiff += (float) Math.abs(yawInterpolant);
		}
		float avgAngleDiff = entity.parts.length > 1 ? totalAngleDiff / (entity.parts.length - 1) : totalAngleDiff;
		state.avgWibbleStrength = Math.clamp(1.0F - avgAngleDiff / 60.0F, 0, 1);

		state.head = new VelvetWormRenderState.PartState();
		state.head.x = 0;
		state.head.y = 0;
		state.head.z = 0;
		state.head.yaw = entity.getViewYRot(partialTicks);
		double headYawDiff = (state.head.yaw - entity.parts[1].getYRot()) % 360.0F;
		double headYawInterpolant = 2 * headYawDiff % 360.0F - headYawDiff;
		state.head.wibbleStrength = Math.min(state.avgWibbleStrength, Math.clamp(1.0F - (float)Math.abs(headYawInterpolant) / 60.0F, 0, 1));

		state.bodyParts = new VelvetWormRenderState.PartState[entity.parts.length - 1];
		for(int i = 0; i < entity.parts.length - 1; i++) {
			VelvetWormMultipart part = entity.parts[i];
			Entity prevPart = i > 0 ? entity.parts[i - 1] : entity;
			state.bodyParts[i] = extractPartState(part, prevPart, ex, ey, ez, i, state.avgWibbleStrength, partialTicks, i > 0 && i % 2 != 0);
		}

		VelvetWormMultipart tailPart = entity.parts[entity.parts.length - 1];
		Entity prevTailPart = entity.parts[entity.parts.length - 2];
		state.tail = extractPartState(tailPart, prevTailPart, ex, ey, ez, entity.parts.length - 1, state.avgWibbleStrength, partialTicks, false);
	}

	private VelvetWormRenderState.PartState extractPartState(VelvetWormMultipart part, Entity prevPart, double ex, double ey, double ez, int frame, float avgWibbleStrength, float partialTicks, boolean isPartA) {
		VelvetWormRenderState.PartState ps = new VelvetWormRenderState.PartState();
		ps.x = part.xOld + (part.getX() - part.xOld) * partialTicks - ex;
		ps.y = part.yOld + (part.getY() - part.yOld) * partialTicks - ey;
		ps.z = part.zOld + (part.getZ() - part.zOld) * partialTicks - ez;
		ps.yaw = part.yRotO + (part.getYRot() - part.yRotO) * partialTicks;
		double yawDiff = (prevPart.getYRot() - part.getYRot()) % 360.0F;
		double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
		ps.wibbleStrength = Math.min(avgWibbleStrength, Math.clamp(1.0F - (float)Math.abs(yawInterpolant) / 60.0F, 0, 1));
		ps.frame = frame;
		ps.isPartA = isPartA;
		return ps;
	}

	@Nullable
	protected RenderType getRenderType(VelvetWormRenderState state, boolean isVisible, boolean isTranslucentToPlayer, boolean isGlowing) {
		if (isTranslucentToPlayer)
			return RenderTypes.entityTranslucent(getTextureLocation(state));
		else if (isVisible)
			return RenderTypes.entityCutout(getTextureLocation(state));
		else
			return isGlowing ? RenderTypes.outline(getTextureLocation(state)) : null;
	}

	@Override
	public @NonNull Identifier getTextureLocation(VelvetWormRenderState state) {
		return TEXTURES[state.skin];
	}
}
