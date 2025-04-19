package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import erebus.Erebus;
import erebus.client.render.entity.model.LocustModel;
import erebus.entity.Locust;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LocustRenderer extends MobRenderer<Locust, LocustModel<Locust>> {
	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/locust.png");

	public LocustRenderer(EntityRendererProvider.Context context) {
		super(context, new LocustModel<>(context.bakeLayer(ModEntityRendering.LOCUST)), 0.75F);
	}

	@Override
	protected void scale(Locust locust, PoseStack matrix,  float partialTickTime ) {
		matrix.scale(1.5F, 1.5F, 1.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(Locust locust) {
		return TEXTURE;
	}

}