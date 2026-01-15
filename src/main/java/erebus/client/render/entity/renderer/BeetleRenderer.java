package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.BeetleModel;
import erebus.entity.Beetle;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BeetleRenderer extends MobRenderer<Beetle, BeetleModel<Beetle>> {
	private static final ResourceLocation[] TEXTURE = new ResourceLocation[] {
			Erebus.prefix("textures/entity/beetle_rare_spawn.png"),
			Erebus.prefix("textures/entity/beetle_blue.png"),
			Erebus.prefix("textures/entity/beetle_brown.png"),
			Erebus.prefix("textures/entity/beetle_green.png"),
			Erebus.prefix("textures/entity/beetle_red.png"),
			Erebus.prefix("textures/entity/beetle_tan.png") };

	public BeetleRenderer(EntityRendererProvider.Context context) {
		super(context, new BeetleModel<>(context.bakeLayer(ModEntityRendering.BEETLE)), 0.5F);
	}

	@Override
	public  ResourceLocation getTextureLocation(Beetle beetle) {
		if (beetle.getSkin() > 0 && beetle.getSkin() <= 10)
			return TEXTURE[1];
		else if (beetle.getSkin() > 10 && beetle.getSkin() <= 20)
			return TEXTURE[2];
		else if (beetle.getSkin() > 20 && beetle.getSkin() <= 30)
			return TEXTURE[3];
		else if (beetle.getSkin() > 30 && beetle.getSkin() <= 40)
			return TEXTURE[4];
		else if (beetle.getSkin() > 40 && beetle.getSkin() <= 50)
			return TEXTURE[5];
		else if (beetle.getSkin() == 0)
			return TEXTURE[0];
		else
			return TEXTURE[1];
	}
}
