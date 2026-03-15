package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.CentipedeModel;
import erebus.client.render.entity.renderer.state.CentipedeRenderState;
import erebus.entity.Centipede;
import erebus.entity.helper.CentipedeMultipart;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public class CentipedeRenderer extends MobRenderer<Centipede, CentipedeRenderState, CentipedeModel> {
	private static final Identifier[] TEXTURES = new Identifier[] {
			Erebus.prefix("textures/entity/centipede.png"),
			Erebus.prefix("textures/entity/centipede_light.png"),
			Erebus.prefix("textures/entity/centipede_black.png")
	};

	public CentipedeRenderer(EntityRendererProvider.Context context) {
		super(context, new CentipedeModel(context.bakeLayer(ModEntityRendering.CENTIPEDE)), 0F);
	}

	@Override
	public void submit(CentipedeRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
		boolean isVisible = isBodyVisible(state);
		boolean isTranslucentToPlayer = !isVisible && !state.isInvisibleToPlayer;
		int overlay = getOverlayCoords(state, this.getWhiteOverlayProgress(state));
		int colour = isTranslucentToPlayer ? 654311423 : -1;
		RenderType renderType = getRenderType(state, isVisible, isTranslucentToPlayer, state.appearsGlowing());

		pose.pushPose();
		pose.translate(state.head.x, state.head.y + 1.525F, state.head.z);
		pose.scale(-1F, -1F, 1F);
		pose.mulPose(Axis.YP.rotationDegrees(180F + state.head.yaw));
		submit.submitModel(model, state, pose, renderType, state.lightCoords, overlay, colour, null, state.outlineColor, null);
		pose.popPose();

		for(int c = 0; c < state.bodyParts.length; c++) {
			CentipedeRenderState.PartState part = state.bodyParts[c];
			pose.pushPose();
			pose.scale(1, -1, 1);
			pose.translate(part.x, part.y - 1.525F, part.z);
			pose.mulPose(Axis.YN.rotationDegrees(part.yaw));
			submit.submitModel(model, state, pose, renderType, state.lightCoords, overlay, colour, null, state.outlineColor, null);
			pose.popPose();
		}

		pose.pushPose();
		pose.translate(state.tail.x, state.tail.y + 1.525F, state.tail.z);
		pose.scale(-1F, -1F, 1F);
		pose.mulPose(Axis.YP.rotationDegrees(180F + state.tail.yaw));
		submit.submitModel(model, state, pose, renderType, state.lightCoords, overlay, colour, null, state.outlineColor, null);
		pose.popPose();
	}

	@Override
	public CentipedeRenderState createRenderState() {
		return new CentipedeRenderState();
	}

	@Override
	public void extractRenderState(Centipede entity, CentipedeRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.skin = entity.getSkin();

		double ex = entity.xOld + (entity.getX() - entity.xOld) * partialTicks;
		double ey = entity.yOld + (entity.getY() - entity.yOld) * partialTicks;
		double ez = entity.zOld + (entity.getZ() - entity.zOld) * partialTicks;

		float totalAngleDiff = 0.0F;

		for(int c = 0; c < entity.parts.length; c++) {
			Entity prevPart = c > 0 ? entity.parts[c - 1] : entity;
			CentipedeMultipart part = entity.parts[c];
			double yawDiff = (prevPart.getYRot() - part.getYRot()) % 360.0F;
			double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
			totalAngleDiff += (float) yawInterpolant;
		}

		float avgAngleDiff = entity.parts.length > 1 ? totalAngleDiff / (entity.parts.length - 1) : totalAngleDiff;
		state.avgWibbleStrength = Math.clamp(1.0F - avgAngleDiff / 60.0F, 0, 1);

		state.head = new CentipedeRenderState.PartState();
		state.head.x = 0;
		state.head.y = 0;
		state.head.z = 0;
		state.head.yaw = entity.getViewYRot(partialTicks);

		double headYawDiff = (state.head.yaw - entity.parts[1].getYRot()) % 360.0F;
		double headYawInterpolant = 2 * headYawDiff % 360.0F - headYawDiff;
		state.head.wibbleStrength = Math.min(state.avgWibbleStrength, Math.clamp(1.0F - (float)Math.abs(headYawInterpolant) / 60.0F, 0, 1));

		state.bodyParts = new CentipedeRenderState.PartState[entity.parts.length - 2];

		for(int c = 0; c < entity.parts.length - 2; c++) {
			CentipedeMultipart part = entity.parts[c];
			Entity prevPart = c > 0 ? entity.parts[c - 1] : entity;
			state.bodyParts[c] = extractPartState(part, prevPart, ex, ey, ez, c, state.avgWibbleStrength, partialTicks, c % 2 == 0);
		}

		CentipedeMultipart tail = entity.parts[entity.parts.length - 1];
		Entity prevTail = entity.parts[entity.parts.length - 2];
		state.tail = extractPartState(tail, prevTail, ex, ey, ez, entity.parts.length - 1, state.avgWibbleStrength, partialTicks, false);
	}

	private CentipedeRenderState.PartState extractPartState(CentipedeMultipart part, Entity prevPart, double ex, double ey, double ez, int frame, float avgWibbleStrength, float partialTicks, boolean isPartA) {
		CentipedeRenderState.PartState ps = new CentipedeRenderState.PartState();
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
	protected RenderType getRenderType(CentipedeRenderState state, boolean isVisible, boolean isTranslucentToPlayer, boolean isGlowing) {
		if (isTranslucentToPlayer)
			return RenderTypes.entityTranslucent(getTextureLocation(state));
		else if (isVisible)
			return RenderTypes.entityCutout(getTextureLocation(state));
		else
			return isGlowing ? RenderTypes.outline(getTextureLocation(state)) : null;
	}

	@Override
	public @NonNull Identifier getTextureLocation(CentipedeRenderState state) {
		return TEXTURES[state.skin];
	}
}
