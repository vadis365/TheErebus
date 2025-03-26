package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import erebus.Erebus;
import erebus.client.render.entity.model.WaspModel;
import erebus.entity.Wasp;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WaspRenderer extends MobRenderer<Wasp, WaspModel<Wasp>> {
	public static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/wasp.png");

	public WaspRenderer(EntityRendererProvider.Context context) {
        super(context, new WaspModel<>(context.bakeLayer(ModEntityRendering.WASP)), 0.5F);
        addLayer(new WaspLayer(this, context.getModelSet()));
    }

	@Override
	protected void scale(Wasp entity, PoseStack matrix, float partialTickTime) {
		matrix.translate(0F, 0F, -0.25F);
		matrix.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public  ResourceLocation getTextureLocation(Wasp entity) {
		return TEXTURE;
	}
}
