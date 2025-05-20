package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.BotFlyLarvaModel;
import erebus.entity.BotFlyLarva;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class BotFlyLarvaRenderer extends MobRenderer<BotFlyLarva, BotFlyLarvaModel<BotFlyLarva>> {

	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/bot_fly_larva.png");

	public BotFlyLarvaRenderer(EntityRendererProvider.Context context) {
		super(context, new BotFlyLarvaModel<>(context.bakeLayer(ModEntityRendering.BOT_FLY_LARVA)), 0.3F);
	}

	@Override
	public void render(BotFlyLarva larva, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		Minecraft minecraft = Minecraft.getInstance();
		boolean isVisible = this.isBodyVisible(larva);
		boolean isTranslucentToPlayer = !isVisible && !larva.isInvisibleTo(minecraft.player);
		boolean isGlowing = minecraft.shouldEntityAppearGlowing(larva);
		int overlay = getOverlayCoords(larva, this.getWhiteOverlayProgress(larva, partialTicks));
		int colour = isTranslucentToPlayer ? 654311423 : -1;
		RenderType renderType = getRenderType(larva, isVisible, isTranslucentToPlayer, isGlowing);

		if (renderType != null) {
			stack.pushPose();
			stack.mulPose(Axis.YN.rotationDegrees(larva.getYRot()));
			stack.scale(-0.6F, -0.6F, 0.6F);
			stack.translate(0F, 0F, 0.2F);
			
			model.setupAnim(larva, larva.walkAnimation.position(partialTicks), larva.walkAnimation.speed(partialTicks), larva.tickCount, entityYaw, 0F);
			if (larva.getParasiteCount() > 0)
				model.renderToBuffer(stack, buffer.getBuffer(renderType), packedLight, overlay, colour);
			if (larva.getParasiteCount() > 1) {
				stack.pushPose();
				stack.translate(0.5F, -0.4F, 0.0F);
				model.renderToBuffer(stack, buffer.getBuffer(renderType), packedLight, overlay, colour);
				stack.popPose();
			}
			if (larva.getParasiteCount() > 1) {
				stack.pushPose();
				stack.translate(-0.5F, -0.4F, 0.0F);
				model.renderToBuffer(stack, buffer.getBuffer(renderType), packedLight, overlay, colour);
				stack.popPose();
			}
			stack.popPose();
		}
	}

	@Nullable
	protected RenderType getRenderType(BotFlyLarva entity, boolean isVisible, boolean isTranslucentToPlayer, boolean isGlowing) {
		if (isTranslucentToPlayer)
			return RenderType.entityTranslucentCull(getTextureLocation(entity));
		else if (isVisible)
			return RenderType.entityCutout(getTextureLocation(entity));
		else
			return isGlowing ? RenderType.outline(getTextureLocation(entity)) : null;
	}

	@Override
	protected float getFlipDegrees(BotFlyLarva larva) {
		return 180F;
	}

    public ResourceLocation getTextureLocation(BotFlyLarva larva) {
		return TEXTURE;
	}
}