package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.LocustModel;
import erebus.client.render.entity.renderer.layer.LocustLayer;
import erebus.client.render.entity.renderer.state.LocustRenderState;
import erebus.entity.Locust;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jspecify.annotations.NonNull;

public class LocustRenderer extends MobRenderer<Locust, LocustRenderState, LocustModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/locust.png");

	public LocustRenderer(EntityRendererProvider.Context context) {
		super(context, new LocustModel(context.bakeLayer(ModEntityRendering.LOCUST)), 0.75F);
		addLayer(new LocustLayer(this, context.getModelSet()));
	}

	@Override
	public LocustRenderState createRenderState() {
		return new LocustRenderState();
	}

	@Override
	public void extractRenderState(Locust entity, LocustRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.jumpPose = entity.getJumpPose(partialTicks);
		state.flyingPose = entity.getFlyingPose(partialTicks);
		state.isFlying = entity.flying;

		float smoothedTicks = entity.animationTicks + (entity.animationTicks - entity.prevAnimationTicks) * partialTicks;
		state.flapSin = (float) (Math.sin((smoothedTicks) * 0.85F) * 0.5F);
		state.flapCos = (float) (Math.cos((smoothedTicks) * 0.85F) * 0.5F);
		state.antSin = Mth.sin((smoothedTicks) * 0.25F) * 0.125F;
		state.antCos = Mth.cos((smoothedTicks) * 0.25F) * 0.125F;
		state.jumpAngle = Mth.sin(entity.getJumpPose(partialTicks) * (float) Math.PI);
		state.flightAngle = entity.getFlyingPose(partialTicks) * 0.001F;
		state.isOnGround = entity.onGround();
	}

	@Override
	protected void scale(LocustRenderState state, PoseStack stack) {
		stack.scale(1.5F, 1.5F, 1.5F);
		float jumpAngle = Mth.sin(state.jumpPose * (float) Math.PI);
		float flightAngle = state.flyingPose * 0.001F;
		stack.mulPose(Axis.XP.rotation(-jumpAngle * 10.0F * (float) (Math.PI / 180.0)));
		if(state.isFlying) {
			stack.mulPose(Axis.XP.rotation(-flightAngle * 10F));
		}
	}

	@Override
	public @NonNull Identifier getTextureLocation(LocustRenderState state) {
		return TEXTURE;
	}

}
