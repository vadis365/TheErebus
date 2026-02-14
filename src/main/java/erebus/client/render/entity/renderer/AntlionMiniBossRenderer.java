package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.AntlionMiniBossModel;
import erebus.client.render.entity.renderer.state.AntlionMiniBossRenderState;
import erebus.entity.AntlionMiniBoss;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class AntlionMiniBossRenderer extends MobRenderer<AntlionMiniBoss, AntlionMiniBossRenderState, AntlionMiniBossModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/antlion_mini_boss.png");

	public AntlionMiniBossRenderer(EntityRendererProvider.Context context) {
        super(context, new AntlionMiniBossModel(context.bakeLayer(ModEntityRendering.ANTLION_MINI_BOSS)), 1.2F);
	}

	@Override
	public void extractRenderState(AntlionMiniBoss entity, AntlionMiniBossRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public AntlionMiniBossRenderState createRenderState() {
		return new AntlionMiniBossRenderState();
	}

	@Override
	public Identifier getTextureLocation(AntlionMiniBossRenderState state) {
		return TEXTURE;
	}
}
