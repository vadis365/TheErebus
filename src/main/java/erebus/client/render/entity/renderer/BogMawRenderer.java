package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.BogMawModel;
import erebus.client.render.entity.renderer.state.BogMawRenderState;
import erebus.entity.BogMaw;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class BogMawRenderer extends MobRenderer<BogMaw, BogMawRenderState, BogMawModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/bog_maw.png");

	public BogMawRenderer(EntityRendererProvider.Context context) {
        super(context, new BogMawModel(context.bakeLayer(ModEntityRendering.BOG_MAW)), 0.5F);
	}

	@Override
	public void extractRenderState(BogMaw entity, BogMawRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public BogMawRenderState createRenderState() {
		return new BogMawRenderState();
	}

	@Override
	public Identifier getTextureLocation(BogMawRenderState state) {
		return TEXTURE;
	}
}
