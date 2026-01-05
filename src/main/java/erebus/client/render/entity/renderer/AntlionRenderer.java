package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.AntlionModel;
import erebus.entity.Antlion;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AntlionRenderer extends MobRenderer<Antlion, AntlionModel<Antlion>> {

	private static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/antlion.png");

	public AntlionRenderer(EntityRendererProvider.Context context) {
		super(context, new AntlionModel<>(context.bakeLayer(ModEntityRendering.ANTLION)), 0.75F);
	}

	@Override
	protected void scale(Antlion antlion, PoseStack matrix, float partialTickTime) {
		matrix.scale(0.75F, 0.75F, 0.75F);
	}

	@Override
	public ResourceLocation getTextureLocation(Antlion antlion) {
		return TEXTURE;
	}
}
