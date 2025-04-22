package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import erebus.Erebus;
import erebus.client.render.entity.model.LocustModel;
import erebus.entity.Locust;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LocustRenderer extends MobRenderer<Locust, LocustModel<Locust>> {
	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/locust.png");

	public LocustRenderer(EntityRendererProvider.Context context) {
		super(context, new LocustModel<>(context.bakeLayer(ModEntityRendering.LOCUST)), 0.75F);
	}

	@Override
	protected void scale(Locust locust, PoseStack stack, float partialTickTime) {
		stack.scale(1.5F, 1.5F, 1.5F);
		float jumpAngle = Mth.sin(locust.getJumpPose(partialTickTime) * (float) Math.PI);
		float flightAngle = locust.getFlyingPose(partialTickTime) * 0.1F;
		stack.mulPose(Axis.XP.rotation(-jumpAngle * 10.0F * (float) (Math.PI / 180.0)));
		if(locust.flying) {
			stack.mulPose(Axis.XP.rotation(-flightAngle * 0.1F));
		}
	}

	@Override
	public ResourceLocation getTextureLocation(Locust locust) {
		return TEXTURE;
	}

}