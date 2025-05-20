package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.BlackWidowModel;
import erebus.entity.BlackWidow;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlackWidowRenderer extends  MobRenderer<BlackWidow, BlackWidowModel<BlackWidow>> {
	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/black_widow.png");

	public BlackWidowRenderer(EntityRendererProvider.Context context) {
		super(context, new BlackWidowModel<>(context.bakeLayer(ModEntityRendering.BLACK_WIDOW)), 0.3F);
	}

	@Override
	protected void scale(BlackWidow widow, PoseStack matrix, float partialTickTime) {
		shadowRadius = widow.getWidowSize() * 0.3F;
		matrix.scale(shadowRadius, shadowRadius, shadowRadius);
	}

	@Override
	public ResourceLocation getTextureLocation(BlackWidow widow) {
		return TEXTURE;
	}
}