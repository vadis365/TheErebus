package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.LeechModel;
import erebus.client.render.entity.renderer.state.LeechRenderState;
import erebus.entity.Leech;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class LeechRenderer extends MobRenderer<Leech, LeechRenderState, LeechModel> {
	public static final Identifier TEXTURE = Erebus.prefix("textures/entity/leech.png");

	public LeechRenderer(EntityRendererProvider.Context context) {
        super(context, new LeechModel(context.bakeLayer(ModEntityRendering.LEECH)), 0.5F);
    }

	@Override
	public @NonNull LeechRenderState createRenderState() {
		return new LeechRenderState();
	}

	@Override
	public void extractRenderState(@NonNull Leech entity, @NonNull LeechRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.isFeeding = entity.isFeeding();
		state.bloodConsumed = entity.getBloodConsumed();
	}

	@Override
	protected void scale(@NonNull LeechRenderState state, PoseStack pose) {
		if(state.isFeeding) {
			pose.rotateAround(Axis.YP.rotationDegrees(180F), 0, 1, 0);
			pose.translate(0, 0, 0.5F);
		} else {
			float scale = 1 + state.bloodConsumed * 0.1F;
			pose.scale(scale, scale, 1F);
		}
	}

	@Override
	public @NonNull Identifier getTextureLocation(@NonNull LeechRenderState state) {
		return TEXTURE;
	}
}
