package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.AntModel;
import erebus.entity.HoneyPotAnt;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HoneyPotAntRenderer extends MobRenderer<HoneyPotAnt, AntModel<HoneyPotAnt>> {

	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/honey_pot_ant.png");

	public HoneyPotAntRenderer(EntityRendererProvider.Context context) {
		super(context, new AntModel<>(context.bakeLayer(ModEntityRendering.HONEY_POT_ANT)), 0.5F);
		addLayer(new HoneyPotAntLayer(this, context.getModelSet()));
	}

	@Override
	protected void scale(HoneyPotAnt ant, PoseStack matrix, float partialTickTime) {
		matrix.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(HoneyPotAnt ant) {
		return TEXTURE;
	}
}
