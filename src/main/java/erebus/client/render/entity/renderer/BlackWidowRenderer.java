package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.BlackWidowModel;
import erebus.client.render.entity.renderer.state.BlackWidowRenderState;
import erebus.entity.BlackWidow;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class BlackWidowRenderer extends  MobRenderer<BlackWidow, BlackWidowRenderState, BlackWidowModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/black_widow.png");

	public BlackWidowRenderer(EntityRendererProvider.Context context) {
		super(context, new BlackWidowModel(context.bakeLayer(ModEntityRendering.BLACK_WIDOW)), 0.3F);
	}

	@Override
	protected void scale(BlackWidowRenderState widow, PoseStack matrix) {
		shadowRadius = widow.size * 0.3F;
		matrix.scale(shadowRadius, shadowRadius, shadowRadius);
	}

	@Override
	public BlackWidowRenderState createRenderState() {
		return new BlackWidowRenderState();
	}

	@Override
	public void extractRenderState(BlackWidow entity, BlackWidowRenderState state, float partialTicks) {
		state.size = entity.getWidowSize();
	}

	@Override
	public @NonNull Identifier getTextureLocation(BlackWidowRenderState state) {
		return TEXTURE;
	}
}
