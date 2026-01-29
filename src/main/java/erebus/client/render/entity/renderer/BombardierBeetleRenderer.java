package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.BombardierBeetleModel;
import erebus.client.render.entity.renderer.state.BombardierBeetleRenderState;
import erebus.entity.BombardierBeetle;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class BombardierBeetleRenderer extends MobRenderer<BombardierBeetle, BombardierBeetleRenderState, BombardierBeetleModel> {

	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/beetle_bombardier.png");

	public BombardierBeetleRenderer(EntityRendererProvider.Context context) {
		super(context, new BombardierBeetleModel(context.bakeLayer(ModEntityRendering.BOMBARDIER_BEETLE)), 0.6F);
	}

	@Override
	protected void scale(BombardierBeetleRenderState state, PoseStack matrix) {
		matrix.scale(1.5F, 1.5F, 1.5F);
	}

	@Override
	public BombardierBeetleRenderState createRenderState() {
		return new BombardierBeetleRenderState();
	}

	@Override
	public @NonNull Identifier getTextureLocation(BombardierBeetleRenderState state) {
		return TEXTURE;
	}
}
