package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.GrasshopperModel;
import erebus.entity.Grasshopper;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GrasshopperRenderer extends  MobRenderer<Grasshopper, GrasshopperModel<Grasshopper>> {
	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/grasshopper.png");

	public GrasshopperRenderer(EntityRendererProvider.Context context) {
		super(context, new GrasshopperModel<>(context.bakeLayer(ModEntityRendering.GRASSHOPPER)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(Grasshopper grasshopper) {
		return TEXTURE;
	}
}