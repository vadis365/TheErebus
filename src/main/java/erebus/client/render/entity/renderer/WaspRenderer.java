package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.WaspModel;
import erebus.client.render.entity.renderer.layer.WaspLayer;
import erebus.client.render.entity.renderer.state.WaspRenderState;
import erebus.entity.Wasp;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class WaspRenderer extends MobRenderer<Wasp, WaspRenderState, WaspModel> {
	public static final Identifier WASP = Erebus.prefix("textures/entity/wasp.png");
	private static final Identifier HORNET = Erebus.prefix("textures/entity/hornet.png");

	public WaspRenderer(EntityRendererProvider.Context context) {
		super(context, new WaspModel(context.bakeLayer(ModEntityRendering.WASP)), 0.5F);
		addLayer(new WaspLayer(this, context.getModelSet()));
	}

	@Override
	public WaspRenderState createRenderState() {
		return new WaspRenderState();
	}

	@Override
	public void extractRenderState(Wasp entity, WaspRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.isBoss = entity.getIsBoss();
	}

	@Override
	protected void scale(WaspRenderState state, PoseStack matrix) {
		float size = state.isBoss ? 1F : 0.5F;
		shadowRadius = size;
		matrix.translate(0F, 0F, -size * 0.5F);
		matrix.scale(size, size, size);
	}

	@Override
	public @NonNull Identifier getTextureLocation(WaspRenderState state) {
		return state.isBoss ? HORNET : WASP;
	}
}
