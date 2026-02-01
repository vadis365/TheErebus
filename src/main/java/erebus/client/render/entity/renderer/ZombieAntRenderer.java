package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.ZombieAntModel;
import erebus.client.render.entity.renderer.layer.ZombieAntLayer;
import erebus.client.render.entity.renderer.state.ZombieAntRenderState;
import erebus.entity.ZombieAnt;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class ZombieAntRenderer extends MobRenderer<ZombieAnt, ZombieAntRenderState, ZombieAntModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/zombie_ant.png");

	public ZombieAntRenderer(EntityRendererProvider.Context context) {
		super(context, new ZombieAntModel(context.bakeLayer(ModEntityRendering.ZOMBIE_ANT)), 0.5F);
		addLayer(new ZombieAntLayer(this, context.getModelSet()));
	}

	@Override
	public ZombieAntRenderState createRenderState() {
		return new ZombieAntRenderState();
	}

	@Override
	public void extractRenderState(ZombieAnt entity, ZombieAntRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public @NonNull Identifier getTextureLocation(ZombieAntRenderState state) {
		return TEXTURE;
	}
}
