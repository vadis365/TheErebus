package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.WeevilModel;
import erebus.client.render.entity.renderer.state.WeevilRenderState;
import erebus.entity.CropWeevil;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class CropWeevilRenderer extends MobRenderer<CropWeevil, WeevilRenderState, WeevilModel> {

	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/crop_weevil.png");

	public CropWeevilRenderer(EntityRendererProvider.Context context) {
		super(context, new WeevilModel(context.bakeLayer(ModEntityRendering.CROP_WEEVIL)), 0.5F);
	}

	@Override
	public WeevilRenderState createRenderState() {
		return new WeevilRenderState();
	}

	@Override
	protected void scale(WeevilRenderState state, PoseStack matrix) {
		matrix.scale(0.6F, 0.6F, 0.6F);
	}

	@Override
	public @NonNull Identifier getTextureLocation(WeevilRenderState state) {
		return TEXTURE;
	}
}
