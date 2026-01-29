package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.LocustModel;
import erebus.client.render.entity.renderer.layer.LocustLayer;
import erebus.entity.Locust;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class LocustRenderer extends MobRenderer<Locust, LocustModel<Locust>> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/locust.png");

	public LocustRenderer(EntityRendererProvider.Context context) {
		super(context, new LocustModel<>(context.bakeLayer(ModEntityRendering.LOCUST)), 0.75F);
		addLayer(new LocustLayer(this, context.getModelSet()));
	}

	@Override
	protected void scale(Locust locust, PoseStack stack, float partialTickTime) {
		stack.scale(1.5F, 1.5F, 1.5F);
		float jumpAngle = Mth.sin(locust.getJumpPose(partialTickTime) * (float) Math.PI);
		float flightAngle = locust.getFlyingPose(partialTickTime) * 0.001F;
		stack.mulPose(Axis.XP.rotation(-jumpAngle * 10.0F * (float) (Math.PI / 180.0)));
		if(locust.flying) {
			stack.mulPose(Axis.XP.rotation(-flightAngle * 10F));
		}
	}

	@Override
	public Identifier getTextureLocation(Locust locust) {
		return TEXTURE;
	}

}
