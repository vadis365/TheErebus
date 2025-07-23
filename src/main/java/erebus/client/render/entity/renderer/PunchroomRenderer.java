package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import erebus.Erebus;
import erebus.client.render.entity.model.PunchroomModel;
import erebus.entity.Punchroom;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class PunchroomRenderer extends MobRenderer<Punchroom, PunchroomModel<Punchroom>> {
	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/punchroom.png");
	private static final ResourceLocation TEXTURE_SPECIAL = Erebus.prefix("textures/entity/punchroom_rubby.png");

	public PunchroomRenderer(EntityRendererProvider.Context context) {
		super(context, new PunchroomModel<>(context.bakeLayer(ModEntityRendering.PUNCHROOM)), 1.0F);
	}

	@Override
	protected void scale(Punchroom punchroom, PoseStack matrix, float partialTickTime) {
		int i = 1;
		float f1 = (punchroom.prevSquishFactor + (punchroom.squishFactor - punchroom.prevSquishFactor) * partialTickTime) / (i * 0.5F + 1.0F);
		float f2 = 1.0F / (f1 + 1.0F);
		float f3 = i;
		matrix.scale(f2 * f3, 1.0F / f2 * f3, f2 * f3);
	}

	@Override
	public ResourceLocation getTextureLocation(Punchroom punchroom) {
		if (punchroom.hasCustomName())
			if (punchroom.getCustomName().equals("Bryuf"))
				return TEXTURE_SPECIAL;
		return TEXTURE;
	}
}
