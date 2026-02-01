package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.WeevilModel;
import erebus.client.render.entity.renderer.state.WeevilRenderState;
import erebus.entity.FungalWeevil;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class FungalWeevilRenderer extends MobRenderer<FungalWeevil, WeevilRenderState, WeevilModel> {

	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/fungal_weevil.png");

	public FungalWeevilRenderer(EntityRendererProvider.Context context) {
		super(context, new WeevilModel(context.bakeLayer(ModEntityRendering.FUNGAL_WEEVIL)), 0.5F);
	}

	@Override
	public WeevilRenderState createRenderState() {
		return new WeevilRenderState();
	}

	@Override
	protected void scale(WeevilRenderState weevil, PoseStack pose) {
		pose.scale(0.6F, 0.6F, 0.6F);
	}

	@Override
	public @NonNull Identifier getTextureLocation(WeevilRenderState weevil) {
		return TEXTURE;
	}
}
