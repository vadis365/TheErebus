package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.FireAntSoldierModel;
import erebus.client.render.entity.renderer.state.FireAntSoldierRenderState;
import erebus.entity.FireAntSoldier;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class FireAntSoldierRenderer extends MobRenderer<FireAntSoldier, FireAntSoldierRenderState, FireAntSoldierModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/fire_ant_soldier.png");

	public FireAntSoldierRenderer(EntityRendererProvider.Context context) {
        super(context, new FireAntSoldierModel(context.bakeLayer(ModEntityRendering.FIRE_ANT_SOLDIER)), 0.5F);
	}

	@Override
	public void extractRenderState(FireAntSoldier entity, FireAntSoldierRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	public FireAntSoldierRenderState createRenderState() {
		return new FireAntSoldierRenderState();
	}

	@Override
	public Identifier getTextureLocation(FireAntSoldierRenderState state) {
		return TEXTURE;
	}
}
