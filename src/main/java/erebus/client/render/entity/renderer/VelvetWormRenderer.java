package erebus.client.render.entity.renderer;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import erebus.Erebus;
import erebus.client.render.entity.model.VelvetWormModel;
import erebus.entity.VelvetWorm;
import erebus.entity.VelvetWormMultipart;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VelvetWormRenderer extends MobRenderer<VelvetWorm, VelvetWormModel<VelvetWorm>> {
	public static final ResourceLocation TEXTURE_1 = Erebus.prefix("textures/entity/velvetworm_1.png");
	public static final ResourceLocation TEXTURE_2 = Erebus.prefix("textures/entity/velvetworm_2.png");


	public VelvetWormRenderer(EntityRendererProvider.Context context) {
		super(context, new VelvetWormModel<>(context.bakeLayer(ModEntityRendering.VELVET_WORM)), 0.1F);
		//TODO remake renderer and use layers for body sections parts
	}

	@Override
	public void render(VelvetWorm entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource buffer, int packedLight) {
		Minecraft minecraft = Minecraft.getInstance();
		boolean isVisible = this.isBodyVisible(entity);
		boolean isTranslucentToPlayer = !isVisible && !entity.isInvisibleTo(minecraft.player);
		boolean isGlowing = minecraft.shouldEntityAppearGlowing(entity);
		int overlay = getOverlayCoords(entity, this.getWhiteOverlayProgress(entity, partialTicks));
		int colour = isTranslucentToPlayer ? 654311423 : -1;
		
		stack.pushPose();
		float totalAngleDiff = 0.0f;

		for(int i = 0; i < entity.parts.length; i++) {
			Entity prevPart = entity;
			if (i > 0 )
				prevPart = entity.parts[i - 1];
			VelvetWormMultipart part = entity.parts[i];
			double yawDiff = (prevPart.getYRot() - part.getYRot()) % 360.0F;
			double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
			totalAngleDiff += (float) Math.abs(yawInterpolant);
		}

		float avgAngleDiff = totalAngleDiff;

		if(entity.parts.length > 0)
			avgAngleDiff /= (entity.parts.length - 1);

		float avgWibbleStrength = Math.clamp(1.0F - avgAngleDiff / 60.0F, 0, 1);
		float x = 0F;
		float y = 0F;
		float z = 0F;
		RenderType renderType = getRenderType(entity, true, isVisible, isTranslucentToPlayer, isGlowing);
		VertexConsumer consumer;
		if(renderType != null) {
			consumer = buffer.getBuffer(renderType);
			renderHead(stack, consumer, packedLight, overlay, colour, entity, 1, x, y + 1.5F, z, entityYaw, avgWibbleStrength, partialTicks);
		}
		double ex = entity.xOld + (entity.getX() - entity.xOld) * (double)partialTicks;
		double ey = entity.yOld + (entity.getY() - entity.yOld) * (double)partialTicks;
		double ez = entity.zOld + (entity.getZ() - entity.zOld) * (double)partialTicks;
		double rx = ex - x;
		double ry = ey - y;
		double rz = ez - z;
		float zOffset = 0;
		renderType = getRenderType(entity, false, isVisible, isTranslucentToPlayer, isGlowing);
		if(renderType != null) {
			consumer = buffer.getBuffer(renderType);
			for(int i = 0; i < entity.parts.length - 1; i++)
				renderBodyPart(stack, consumer, packedLight, overlay, colour, entity, entity.parts[i], i > 0 ? entity.parts[i - 1] : entity, rx, ry, rz, i, avgWibbleStrength, zOffset -= 0.001F, partialTicks, i > 0 && i%2 != 0 ? true : false);
			renderTailPart(stack, consumer, packedLight, overlay, colour, entity, entity.parts[entity.parts.length - 1], entity.parts[entity.parts.length - 2], rx, ry, rz, entity.parts.length - 1, avgWibbleStrength, partialTicks);
		}
		stack.popPose();
	}

	private void renderHead(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, VelvetWorm entity, int frame, double x, double y, double z, float yaw, float avgWibbleStrength, float partialTicks) {
		double yawDiff = (yaw - entity.parts[1].getYRot()) % 360.0F;
		double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
		float wibbleStrength = Math.min(avgWibbleStrength, Math.clamp(1.0F - (float)Math.abs(yawInterpolant) / 60.0F, 0, 1));
		stack.pushPose();
		stack.translate(x, y, z);
		stack.scale(-1F, -1F, 1F);
		stack.mulPose(Axis.YP.rotationDegrees(180F + yaw));
		model.renderHead(stack, consumer, light, overlay, colour, entity, frame, wibbleStrength, partialTicks);
		stack.popPose();
	}

	@Nullable
	protected RenderType getRenderType(VelvetWorm entity, boolean isHeadPart, boolean isVisible, boolean isTranslucentToPlayer, boolean isGlowing) {
		if (isTranslucentToPlayer)
			return RenderType.entityTranslucentCull(getTextureLocation(entity));
		else if (isVisible)
			return this.model.renderType(getTextureLocation(entity));
		else
			return isGlowing ? RenderType.outline(getTextureLocation(entity)) : null;
	}

	protected void renderBodyPart(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, VelvetWorm entity, VelvetWormMultipart part, Entity prevPart, double rx, double ry, double rz, int frame, float avgWibbleStrength, float zOffset, float partialTicks, boolean isPartA) {
		double x = part.xOld + (part.xo - part.xOld) * (double)partialTicks - rx;
		double y = part.yOld + (part.yo - part.yOld) * (double)partialTicks - ry;
		double z = part.zOld + (part.zo - part.zOld) * (double)partialTicks - rz;
		float yaw = part.yRotO + (part.getYRot() - part.yRotO) * partialTicks;
		double yawDiff = (prevPart.getYRot() - part.getYRot()) % 360.0F;
		double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
		float wibbleStrength = Math.min(avgWibbleStrength, Math.clamp(1.0F - (float)Math.abs(yawInterpolant) / 60.0F, 0, 1));

		stack.pushPose();
		stack.translate(x, y + 1.525f + zOffset, z);
		stack.scale(-1F, -1F, 1F);
		stack.mulPose(Axis.YN.rotationDegrees(-yaw));
		model.renderBody(stack, consumer, light, overlay, colour, entity, frame, wibbleStrength, partialTicks, isPartA);
		stack.popPose();
	}

	protected void renderTailPart(PoseStack stack, VertexConsumer consumer, int light, int overlay, int colour, VelvetWorm entity, VelvetWormMultipart part, Entity prevPart, double rx, double ry, double rz, int frame, float avgWibbleStrength, float partialTicks) {
		double x = part.xOld + (part.xo - part.xOld) * (double)partialTicks - rx;
		double y = part.yOld + (part.yo - part.yOld) * (double)partialTicks - ry;
		double z = part.zOld + (part.zo - part.zOld) * (double)partialTicks - rz;
		float yaw = part.yRotO + (part.getYRot() - part.yRotO) * partialTicks;
		double yawDiff = (prevPart.getYRot() - part.getYRot()) % 360.0F;
		double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
		float wibbleStrength = Math.min(avgWibbleStrength, Math.clamp(1.0F - (float)Math.abs(yawInterpolant) / 60.0F, 0, 1));

		stack.pushPose();
		stack.translate(x, y + 1.525f, z);
		stack.scale(-1F, -1F, 1F);
		stack.mulPose(Axis.YP.rotationDegrees(180F + yaw));
		model.renderTail(stack, consumer, light, overlay, colour, entity, frame, wibbleStrength, partialTicks);
		stack.popPose();
	}

	@Override
	public  ResourceLocation getTextureLocation(VelvetWorm velvetworm) {
		if (velvetworm.getSkin() == 0)
			return TEXTURE_1;
		else
			return TEXTURE_2;
	}
}
