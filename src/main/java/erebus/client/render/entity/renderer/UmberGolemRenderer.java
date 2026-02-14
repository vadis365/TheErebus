package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.UmberGolemModel;
import erebus.client.render.entity.renderer.state.UmberGolemRenderState;
import erebus.entity.UmberGolem;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class UmberGolemRenderer extends MobRenderer<UmberGolem, UmberGolemRenderState, UmberGolemModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/umber_golem.png");

	public UmberGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new UmberGolemModel(context.bakeLayer(ModEntityRendering.UMBER_GOLEM)), 1.0F);
	}

	@Override
	public void extractRenderState(UmberGolem entity, UmberGolemRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public UmberGolemRenderState createRenderState() {
		return new UmberGolemRenderState();
	}

	@Override
	public Identifier getTextureLocation(UmberGolemRenderState state) {
		return TEXTURE;
	}
}
