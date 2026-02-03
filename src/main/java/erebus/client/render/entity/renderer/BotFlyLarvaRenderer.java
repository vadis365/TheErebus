package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.BotFlyLarvaModel;
import erebus.client.render.entity.renderer.state.BotFlyLarvaRenderState;
import erebus.entity.BotFlyLarva;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class BotFlyLarvaRenderer extends MobRenderer<BotFlyLarva, BotFlyLarvaRenderState, BotFlyLarvaModel> {

	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/bot_fly_larva.png");

	public BotFlyLarvaRenderer(EntityRendererProvider.Context context) {
		super(context, new BotFlyLarvaModel(context.bakeLayer(ModEntityRendering.BOT_FLY_LARVA)), 0.3F);
	}

	@Override
	public void submit(BotFlyLarvaRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
		boolean isTranslucentToPlayer = !isBodyVisible(state) && !state.isInvisibleToPlayer;
		int overlay = getOverlayCoords(state, getWhiteOverlayProgress(state));
		int colour = isTranslucentToPlayer ? 654311423 : -1;
		RenderType renderType = getRenderType(state, isBodyVisible(state), isTranslucentToPlayer, state.appearsGlowing());

		if (renderType != null) {
			pose.pushPose();
			pose.mulPose(Axis.YN.rotationDegrees(state.yRot));
			pose.scale(-0.6F, -0.6F, 0.6F);
			pose.translate(0F, 0F, 0.2F);

			if (state.parasiteCount > 0)
				submit.submitModel(
						model,
						state,
						pose,
						renderType,
						state.lightCoords,
						OverlayTexture.NO_OVERLAY,
						colour,
						null,
						state.outlineColor,
						null
				);
			if (state.parasiteCount > 1) {
				pose.pushPose();
				pose.translate(0.5F, -0.4F, 0.0F);
				submit.submitModel(
						model,
						state,
						pose,
						renderType,
						state.lightCoords,
						OverlayTexture.NO_OVERLAY,
						colour,
						null,
						state.outlineColor,
						null
				);
				pose.popPose();
			}
			if (state.parasiteCount > 2) {
				pose.pushPose();
				pose.translate(-0.5F, -0.4F, 0.0F);
				submit.submitModel(
						model,
						state,
						pose,
						renderType,
						state.lightCoords,
						OverlayTexture.NO_OVERLAY,
						colour,
						null,
						state.outlineColor,
						null
				);
				pose.popPose();
			}
			pose.popPose();
		}
	}

	@Override
	public BotFlyLarvaRenderState createRenderState() {
		return new BotFlyLarvaRenderState();
	}

	@Override
	public void extractRenderState(BotFlyLarva entity, BotFlyLarvaRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.parasiteCount = entity.getParasiteCount();
	}

	@Override
	protected @Nullable RenderType getRenderType(BotFlyLarvaRenderState state, boolean isBodyVisible, boolean forceTransparent, boolean appearGlowing) {
		if (!forceTransparent)
			return RenderTypes.entityTranslucentEmissive(getTextureLocation(state));
		else if (isBodyVisible)
			return RenderTypes.entityCutout(getTextureLocation(state));
		else
			return appearGlowing ? RenderTypes.outline(getTextureLocation(state)) : null;
	}

	@Override
	protected float getFlipDegrees() {
		return 180F;
	}

	@Override
	public @NonNull Identifier getTextureLocation(BotFlyLarvaRenderState state) {
		return TEXTURE;
	}
}
