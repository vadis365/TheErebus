package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.AntModel;
import erebus.entity.ZombieAnt;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ZombieAntRenderer extends MobRenderer<ZombieAnt, AntModel<ZombieAnt>> {

	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/zombie_ant.png");

	public ZombieAntRenderer(EntityRendererProvider.Context context) {
		super(context, new AntModel<>(context.bakeLayer(ModEntityRendering.ZOMBIE_ANT)), 0.5F);
		addLayer(new ZombieAntLayer(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(ZombieAnt ant) {
		return TEXTURE;
	}
}
