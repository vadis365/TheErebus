package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.BeetleModel;
import erebus.client.render.entity.renderer.state.BeetleRenderState;
import erebus.entity.Beetle;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class BeetleRenderer extends MobRenderer<Beetle, BeetleRenderState, BeetleModel> {
	private static final Identifier[] TEXTURE = new Identifier[] {
			Erebus.prefix("textures/entity/beetle_rare_spawn.png"),
			Erebus.prefix("textures/entity/beetle_blue.png"),
			Erebus.prefix("textures/entity/beetle_brown.png"),
			Erebus.prefix("textures/entity/beetle_green.png"),
			Erebus.prefix("textures/entity/beetle_red.png"),
			Erebus.prefix("textures/entity/beetle_tan.png") };

	public BeetleRenderer(EntityRendererProvider.Context context) {
		super(context, new BeetleModel(context.bakeLayer(ModEntityRendering.BEETLE)), 0.5F);
	}

	@Override
	public BeetleRenderState createRenderState() {
		return new BeetleRenderState();
	}

	@Override
	public void extractRenderState(Beetle entity, BeetleRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.skin = entity.getSkin();
	}

	@Override
	public @NonNull Identifier getTextureLocation(BeetleRenderState state) {
		if (state.skin > 0 && state.skin <= 10)
			return TEXTURE[1];
		else if (state.skin > 10 && state.skin <= 20)
			return TEXTURE[2];
		else if (state.skin > 20 && state.skin <= 30)
			return TEXTURE[3];
		else if (state.skin > 30 && state.skin <= 40)
			return TEXTURE[4];
		else if (state.skin > 40 && state.skin <= 50)
			return TEXTURE[5];
		else if (state.skin == 0)
			return TEXTURE[0];
		else
			return TEXTURE[1];
	}
}
