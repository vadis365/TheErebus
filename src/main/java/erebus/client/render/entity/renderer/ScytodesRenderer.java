package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.ScytodesModel;
import erebus.entity.Scytodes;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ScytodesRenderer extends MobRenderer<Scytodes, ScytodesModel<Scytodes>> {

	private static final ResourceLocation[] TEXTURES = new ResourceLocation[] {
			Erebus.prefix("textures/entity/scytodes_1.png"),
			Erebus.prefix("textures/entity/scytodes_2.png"),
			Erebus.prefix("textures/entity/scytodes_3.png"),
			Erebus.prefix("textures/entity/scytodes_4.png") };

	public ScytodesRenderer(EntityRendererProvider.Context context) {
		super(context, new ScytodesModel<>(context.bakeLayer(ModEntityRendering.SCYTODES)), 1F);
	}

	@Override
	public  ResourceLocation getTextureLocation(Scytodes scytodes) {
		return TEXTURES[scytodes.getSkin()];
	}
}
