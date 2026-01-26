package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.FlyModel;
import erebus.client.render.entity.renderer.layer.FlyLayer;
import erebus.entity.Fly;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FlyRenderer extends MobRenderer<Fly, FlyModel<Fly>> {

	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/fly.png");

	public FlyRenderer(EntityRendererProvider.Context context) {
		super(context, new FlyModel<>(context.bakeLayer(ModEntityRendering.FLY)), 0.25F);
		addLayer(new FlyLayer(this, context.getModelSet()));
	}

	@Override
	public  ResourceLocation getTextureLocation(Fly entity) {
		return TEXTURE;
	}

	@Override
	protected void scale(Fly fly, PoseStack stack, float partialTickTime) {
		stack.scale(0.75F, 0.75F, 0.75F);
	}

	@Override
	protected void setupRotations(Fly entity, PoseStack stack, float bob, float yBodyRot, float partialTick, float scale) {
		Fly fly = entity;
		if (fly.getIsFlyHanging()) {
			stack.translate(0F, 0.5F, 0F);
			stack.mulPose(Axis.XP.rotationDegrees(180F));
		}
		else
			stack.translate(0.0F, Math.cos(bob * 0.3F) * 0.1F, 0.0F); //dunno yet
		super.setupRotations(entity, stack, bob, yBodyRot, partialTick, scale);
	}

}
