package erebus.client.render.entity.renderer;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import erebus.Erebus;
import erebus.client.render.entity.model.CentipedeModel;
import erebus.entity.Centipede;
import erebus.entity.CentipedeMultipart;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CentipedeRenderer extends MobRenderer<Centipede, CentipedeModel<Centipede>> {
	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
			Erebus.prefix("textures/entity/centipede.png"),
			Erebus.prefix("textures/entity/centipede_light.png"),
			Erebus.prefix("textures/entity/centipede_black.png")
	};

	public CentipedeRenderer(EntityRendererProvider.Context context) {
		super(context, new CentipedeModel<>(context.bakeLayer(ModEntityRendering.CENTIPEDE)), 0F);
	}
	
	@Override
	public void render(Centipede entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		Minecraft minecraft = Minecraft.getInstance();
		boolean isVisible = this.isBodyVisible(entity);
		boolean isTranslucentToPlayer = !isVisible && !entity.isInvisibleTo(minecraft.player);
		boolean isGlowing = minecraft.shouldEntityAppearGlowing(entity);
		int overlay = getOverlayCoords(entity, this.getWhiteOverlayProgress(entity, partialTicks));
		int colour = isTranslucentToPlayer ? 654311423 : -1;
		RenderType renderType = getRenderType(entity, isVisible, isTranslucentToPlayer, isGlowing);
		double rx = entity.xOld + (entity.getX() - entity.xOld) * (double)partialTicks;
		double ry = entity.yOld + (entity.getY() - entity.yOld) * (double)partialTicks;
		double rz = entity.zOld + (entity.getZ() - entity.zOld) * (double)partialTicks;
		float yOffset = 0;

		if(renderType != null) {
			renderHead(stack, buffer.getBuffer(renderType), packedLight, overlay, colour, entity, 0F, 0F + 1.5F, 0F, entityYaw, partialTicks);
			for(int i = 0; i < entity.parts.length - 1; i++)
				renderBody(stack, buffer.getBuffer(renderType), packedLight, overlay, colour, entity, entity.parts[i], rx, ry, rz, i, yOffset -= 0.001F, partialTicks, i > 0 && i%2 != 0 ? true : false);
			renderTailPart(stack, buffer.getBuffer(renderType), packedLight, overlay, colour, entity, entity.parts[entity.parts.length - 1], rx, ry, rz, partialTicks);
		}
	}
	
	private void renderHead(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, Centipede entity, double x, double y, double z, float yaw, float partialTicks) {
		stack.pushPose();
		stack.translate(x, y, z);
		stack.scale(-1F, -1F, 1F);
		stack.mulPose(Axis.YP.rotationDegrees(180F + yaw));
		model.renderHead(stack, consumer, light, overlay, colour, entity, partialTicks);
		stack.popPose();
	}
	
	protected void renderBody(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, Centipede entity, CentipedeMultipart part, double rx, double ry, double rz, int frame, float yOffset, float partialTicks, boolean isPartA) {
		double x = part.xOld + (part.xo - part.xOld) * (double)partialTicks - rx;
		double y = part.yOld + (part.yo - part.yOld) * (double)partialTicks - ry;
		double z = part.zOld + (part.zo - part.zOld) * (double)partialTicks - rz;
		float yaw = part.yRotO + (part.getYRot() - part.yRotO) * partialTicks;
		stack.pushPose();
		stack.translate(x, y + 1.525f + yOffset, z);
		stack.scale(-1F, -1F, 1F);
		stack.mulPose(Axis.YN.rotationDegrees(-yaw));
		model.renderBody(stack, consumer, light, overlay, colour, entity, frame, partialTicks, isPartA);
		stack.popPose();
	}

	protected void renderTailPart(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, Centipede entity, CentipedeMultipart part, double rx, double ry, double rz, float partialTicks) {
		double x = part.xOld + (part.xo - part.xOld) * (double)partialTicks - rx;
		double y = part.yOld + (part.yo - part.yOld) * (double)partialTicks - ry;
		double z = part.zOld + (part.zo - part.zOld) * (double)partialTicks - rz;
		float yaw = part.yRotO + (part.getYRot() - part.yRotO) * partialTicks;
		stack.pushPose();
		stack.translate(x, y + 1.525f, z);
		stack.scale(-1F, -1F, 1F);
		stack.mulPose(Axis.YP.rotationDegrees(180F + yaw));
		model.renderTail(stack, consumer, light, overlay, colour);
		stack.popPose();
	}

	@Nullable
	protected RenderType getRenderType(Centipede entity, boolean isVisible, boolean isTranslucentToPlayer, boolean isGlowing) {
		if (isTranslucentToPlayer)
			return RenderType.entityTranslucentCull(getTextureLocation(entity));
		else if (isVisible)
			return RenderType.entityCutout(getTextureLocation(entity));
		else
			return isGlowing ? RenderType.outline(getTextureLocation(entity)) : null;
	}

	@Override
	public  ResourceLocation getTextureLocation(Centipede centipede) {
		return TEXTURES[centipede.getSkin()];
	}
}