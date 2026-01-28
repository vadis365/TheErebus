package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.BeetleLarvaModel;
import erebus.client.render.entity.renderer.layer.BeetleLarvaLayer;
import erebus.client.render.entity.renderer.state.BeetleLarvaRenderState;
import erebus.entity.BeetleLarva;
import erebus.entity.BombardierBeetleLarva;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class BeetleLarvaRenderer extends MobRenderer<BeetleLarva, BeetleLarvaRenderState, BeetleLarvaModel> {

	private static final Identifier[] TEXTURES = new Identifier[] {
			Erebus.prefix("textures/entity/beetle_larva.png"),
			Erebus.prefix("textures/entity/beetle_larva_bombardier.png"),
			Erebus.prefix("textures/entity/beetle_larva_stag.png") };

	public BeetleLarvaRenderer(EntityRendererProvider.Context context) {
		super(context, new BeetleLarvaModel(context.bakeLayer(ModEntityRendering.BEETLE_LARVA)), 0.3F);
		addLayer(new BeetleLarvaLayer(this, context.getModelSet()));
		this.model = new BeetleLarvaModel(context.bakeLayer(ModEntityRendering.BEETLE_LARVA));
	}

	@Override
	public BeetleLarvaRenderState createRenderState() {
		return new BeetleLarvaRenderState();
	}

	@Override
	public void extractRenderState(BeetleLarva entity, BeetleLarvaRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.type = entity.getLarvaType();
		state.size = entity.getLarvaSize();
		if(entity instanceof BombardierBeetleLarva bomb) {
			state.inflateSize = bomb.getInflateSize();
			state.isBombardier = true;
		}
	}

	@Override
	protected void scale(BeetleLarvaRenderState state, @NonNull PoseStack pose) {
		if (state.isBombardier) {
			pose.scale((float) (state.inflateSize * 0.009 + state.size), (float) (state.inflateSize * 0.009 + state.size), (float) (-state.inflateSize * 0.0025 + state.size));
		} else {
			pose.scale(state.size, state.size, state.size);
		}
	}

	@Override
	public @NonNull Identifier getTextureLocation(BeetleLarvaRenderState state) {
        return switch (state.type) {
            case 5 -> TEXTURES[2];
            case 4 -> TEXTURES[1];
            default -> TEXTURES[0];
        };
	}
}
