package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.AntlionBossModel;
import erebus.client.render.entity.renderer.state.AntlionBossRenderState;
import erebus.entity.AntlionBoss;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class AntlionBossRenderer extends MobRenderer<AntlionBoss, AntlionBossRenderState, AntlionBossModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/antlionOverlord.png");

	public AntlionBossRenderer(EntityRendererProvider.Context context) {
        super(context, new AntlionBossModel(context.bakeLayer(ModEntityRendering.ANTLION_BOSS)), 2.5F);
	}

	@Override
	public void extractRenderState(AntlionBoss entity, AntlionBossRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public AntlionBossRenderState createRenderState() {
		return new AntlionBossRenderState();
	}

	@Override
	public Identifier getTextureLocation(AntlionBossRenderState state) {
		return TEXTURE;
	}
}
