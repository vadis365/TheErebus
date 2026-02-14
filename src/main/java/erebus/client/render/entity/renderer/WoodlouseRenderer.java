package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.WoodlouseModel;
import erebus.client.render.entity.renderer.state.WoodlouseRenderState;
import erebus.entity.Woodlouse;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class WoodlouseRenderer extends MobRenderer<Woodlouse, WoodlouseRenderState, WoodlouseModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/woodlouse.png");

	public WoodlouseRenderer(EntityRendererProvider.Context context) {
        super(context, new WoodlouseModel(context.bakeLayer(ModEntityRendering.WOODLOUSE)), 0.3F);
	}

	@Override
	public void extractRenderState(Woodlouse entity, WoodlouseRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public WoodlouseRenderState createRenderState() {
		return new WoodlouseRenderState();
	}

	@Override
	public Identifier getTextureLocation(WoodlouseRenderState state) {
		return TEXTURE;
	}
}
