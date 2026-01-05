package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.BlackAntModel;
import erebus.entity.BlackAnt;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BlackAntRenderer extends MobRenderer<BlackAnt, BlackAntModel<BlackAnt>> {

	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/black_ant_kit.png");

	public BlackAntRenderer(EntityRendererProvider.Context context) {
		super(context, new BlackAntModel<>(context.bakeLayer(ModEntityRendering.BLACK_ANT)), 0.5F);
		addLayer(new BlackAntLayer(this, context.getModelSet()));
	}

	@Override
	protected void scale(BlackAnt blackAnt, PoseStack matrix, float partialTickTime) {
		matrix.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(BlackAnt ant) {
		return TEXTURE;
	}
}
