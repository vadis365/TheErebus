package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.CropWeevilModel;
import erebus.client.render.entity.renderer.state.CropWeevilRenderState;
import erebus.entity.CropWeevil;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class CropWeevilRenderer extends MobRenderer<CropWeevil, CropWeevilRenderState, CropWeevilModel> {

	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/crop_weevil.png");

	public CropWeevilRenderer(EntityRendererProvider.Context context) {
		super(context, new CropWeevilModel(context.bakeLayer(ModEntityRendering.CROP_WEEVIL)), 0.5F);
	}

	@Override
	public CropWeevilRenderState createRenderState() {
		return new CropWeevilRenderState();
	}

	@Override
	protected void scale(CropWeevilRenderState state, PoseStack matrix) {
		matrix.scale(0.6F, 0.6F, 0.6F);
	}

	@Override
	public @NonNull Identifier getTextureLocation(CropWeevilRenderState state) {
		return TEXTURE;
	}
}
