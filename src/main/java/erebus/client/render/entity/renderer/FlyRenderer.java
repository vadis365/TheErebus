package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.FlyModel;
import erebus.client.render.entity.renderer.layer.FlyLayer;
import erebus.client.render.entity.renderer.state.FlyRenderState;
import erebus.entity.Fly;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class FlyRenderer extends MobRenderer<Fly, FlyRenderState, FlyModel> {

	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/fly.png");

	public FlyRenderer(EntityRendererProvider.Context context) {
		super(context, new FlyModel(context.bakeLayer(ModEntityRendering.FLY)), 0.25F);
		addLayer(new FlyLayer(this, context.getModelSet()));
	}

	@Override
	public FlyRenderState createRenderState() {
		return new FlyRenderState();
	}

	@Override
	public void extractRenderState(Fly entity, FlyRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.isHanging = entity.getIsFlyHanging();
	}

	@Override
	public @NonNull Identifier getTextureLocation(FlyRenderState state) {
		return TEXTURE;
	}

	@Override
	protected void scale(FlyRenderState state, PoseStack stack) {
		stack.scale(0.75F, 0.75F, 0.75F);
	}

	@Override
	protected void setupRotations(FlyRenderState state, PoseStack pose, float bodyRot, float entityScale) {
		if(state.isHanging) {
			pose.translate(0F, 0.5F, 0F);
			pose.mulPose(Axis.XP.rotationDegrees(180F));
		} else {
			pose.translate(0.0F, Math.cos(state.y * 0.3F) * 0.1F, 0.0F);
		}

		super.setupRotations(state, pose, bodyRot, entityScale);
	}
}
