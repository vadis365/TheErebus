package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.PunchroomModel;
import erebus.client.render.entity.renderer.state.PunchroomRenderState;
import erebus.entity.Punchroom;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class PunchroomRenderer extends MobRenderer<Punchroom, PunchroomRenderState, PunchroomModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/punchroom.png");
	private static final Identifier TEXTURE_SPECIAL = Erebus.prefix("textures/entity/punchroom_rubby.png");

	public PunchroomRenderer(EntityRendererProvider.Context context) {
		super(context, new PunchroomModel(context.bakeLayer(ModEntityRendering.PUNCHROOM)), 1.0F);
	}

	@Override
	public PunchroomRenderState createRenderState() {
		return new PunchroomRenderState();
	}

	@Override
	public void extractRenderState(Punchroom entity, PunchroomRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.hasCustomName = entity.hasCustomName();
		state.name = entity.getCustomName();
		state.squishFactor = entity.squishFactor;
		state.prevSquishFactor = entity.prevSquishFactor;
	}

	@Override
	protected void scale(PunchroomRenderState state, PoseStack matrix) {
		int i = 1;
		float f1 = (state.prevSquishFactor + (state.squishFactor - state.prevSquishFactor) * state.partialTick) / (i * 0.5F + 1.0F);
		float f2 = 1.0F / (f1 + 1.0F);
        matrix.scale(f2 * (float) i, 1.0F / f2 * (float) i, f2 * (float) i);
	}

	@Override
	public @NonNull Identifier getTextureLocation(PunchroomRenderState state) {
		if (state.hasCustomName)
			if (state.name.getString().equals("Bryuf"))
				return TEXTURE_SPECIAL;
		return TEXTURE;
	}
}
