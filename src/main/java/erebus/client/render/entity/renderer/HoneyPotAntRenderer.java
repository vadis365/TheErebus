package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.HoneyPotAntModel;
import erebus.client.render.entity.renderer.state.HoneyPotAntRenderState;
import erebus.entity.HoneyPotAnt;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class HoneyPotAntRenderer extends MobRenderer<HoneyPotAnt, HoneyPotAntRenderState, HoneyPotAntModel> {

	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/honey_pot_ant.png");

	public HoneyPotAntRenderer(EntityRendererProvider.Context context) {
		super(context, new HoneyPotAntModel(context.bakeLayer(ModEntityRendering.HONEY_POT_ANT)), 0.5F);
	}

	@Override
	public HoneyPotAntRenderState createRenderState() {
		return new HoneyPotAntRenderState();
	}

	@Override
	public void extractRenderState(HoneyPotAnt entity, HoneyPotAntRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.belly = entity.getHoneyBelly();
	}

	@Override
	protected void scale(HoneyPotAntRenderState state, PoseStack pose) {
		pose.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public @NonNull Identifier getTextureLocation(HoneyPotAntRenderState state) {
		return TEXTURE;
	}
}
