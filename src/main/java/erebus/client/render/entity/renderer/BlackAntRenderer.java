package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.BlackAntModel;
import erebus.client.render.entity.renderer.layer.BlackAntLayer;
import erebus.client.render.entity.renderer.state.BlackAntRenderState;
import erebus.entity.BlackAnt;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class BlackAntRenderer extends MobRenderer<BlackAnt, BlackAntRenderState, BlackAntModel> {

	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/black_ant_kit.png");

	public BlackAntRenderer(EntityRendererProvider.Context context) {
		super(context, new BlackAntModel(context.bakeLayer(ModEntityRendering.BLACK_ANT)), 0.5F);
		addLayer(new BlackAntLayer(this, context.getModelSet()));
	}

	@Override
	protected void scale(BlackAntRenderState state, PoseStack pose) {
		pose.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public BlackAntRenderState createRenderState() {
		return new BlackAntRenderState();
	}

	@Override
	public @NonNull Identifier getTextureLocation(BlackAntRenderState state) {
		return TEXTURE;
	}
}
