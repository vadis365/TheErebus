package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import erebus.Erebus;
import erebus.client.render.entity.model.WeevilModel;
import erebus.entity.CropWeevil;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CropWeevilRenderer extends MobRenderer<CropWeevil, WeevilModel<CropWeevil>> {

	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/crop_weevil.png");

	public CropWeevilRenderer(EntityRendererProvider.Context context) {
		super(context, new WeevilModel<>(context.bakeLayer(ModEntityRendering.CROP_WEEVIL)), 0.5F);
	}

	@Override
	protected void scale(CropWeevil weevil, PoseStack matrix, float partialTickTime) {
		matrix.scale(0.6F, 0.6F, 0.6F);
	}

	@Override
	public ResourceLocation getTextureLocation(CropWeevil weevil) {
		return TEXTURE;
	}
}