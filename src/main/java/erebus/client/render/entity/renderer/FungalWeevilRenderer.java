package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.WeevilModel;
import erebus.entity.FungalWeevil;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FungalWeevilRenderer extends MobRenderer<FungalWeevil, WeevilModel<FungalWeevil>> {

	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/fungal_weevil.png");

	public FungalWeevilRenderer(EntityRendererProvider.Context context) {
		super(context, new WeevilModel<>(context.bakeLayer(ModEntityRendering.FUNGAL_WEEVIL)), 0.5F);
	}

	@Override
	protected void scale(FungalWeevil weevil, PoseStack matrix, float partialTickTime) {
		matrix.scale(0.6F, 0.6F, 0.6F);
	}

	@Override
	public ResourceLocation getTextureLocation(FungalWeevil weevil) {
		return TEXTURE;
	}
}
