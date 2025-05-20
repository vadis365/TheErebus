package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.BeetleLarvaModel;
import erebus.entity.BeetleLarva;
import erebus.entity.BombardierBeetleLarva;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BeetleLarvaRenderer extends MobRenderer<BeetleLarva, BeetleLarvaModel<BeetleLarva>> {

	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
			Erebus.prefix("textures/entity/beetle_larva.png"),
			Erebus.prefix("textures/entity/beetle_larva_bombardier.png"),
			Erebus.prefix("textures/entity/beetle_larva_stag.png") };

	public BeetleLarvaRenderer(EntityRendererProvider.Context context) {
		super(context, new BeetleLarvaModel<>(context.bakeLayer(ModEntityRendering.BEETLE_LARVA)), 0.3F);
		addLayer(new BeetleLarvaLayer(this, context.getModelSet()));
	}

	@Override
	protected void scale(BeetleLarva larva, PoseStack matrix, float partialTickTime) {
		float larvaSize = larva.getLarvaSize();
		if (larva instanceof BombardierBeetleLarva) {
			int size = ((BombardierBeetleLarva) larva).getInflateSize();
			matrix.scale((float) (size * 0.009 + larvaSize), (float) (size * 0.009 + larvaSize), (float) (-size * 0.0025 + larvaSize));
		}
		else
			matrix.scale(larvaSize, larvaSize, larvaSize);
	}

	@Override
	public ResourceLocation getTextureLocation(BeetleLarva larva) {
		if (larva.getLarvaType() == 4 || larva instanceof BombardierBeetleLarva)
			return TEXTURES[1];
		else
		if (larva.getLarvaType() == 5)
			return TEXTURES[2];
		else
			return TEXTURES[0];
	}
}