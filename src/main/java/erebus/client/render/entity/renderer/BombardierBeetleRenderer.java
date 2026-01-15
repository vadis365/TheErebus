package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.BombardierBeetleModel;
import erebus.entity.BombardierBeetle;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BombardierBeetleRenderer extends MobRenderer<BombardierBeetle, BombardierBeetleModel<BombardierBeetle>> {

	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/beetle_bombardier.png");

	public BombardierBeetleRenderer(EntityRendererProvider.Context context) {
		super(context, new BombardierBeetleModel<>(context.bakeLayer(ModEntityRendering.BOMBARDIER_BEETLE)), 0.6F);
	}

	@Override
	protected void scale(BombardierBeetle BombardierBeetle, PoseStack matrix, float partialTickTime) {
		matrix.scale(1.5F, 1.5F, 1.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(BombardierBeetle BombardierBeetle) {
		return TEXTURE;
	}
}
