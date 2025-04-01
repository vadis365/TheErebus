package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import erebus.Erebus;
import erebus.client.render.entity.model.VelvetWormModel;
import erebus.entity.VelvetWorm;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VelvetWormRenderer extends MobRenderer<VelvetWorm, VelvetWormModel<VelvetWorm>> {
	public static final ResourceLocation TEXTURE_1 = Erebus.prefix("textures/entity/velvetworm.png");
	public static final ResourceLocation TEXTURE_2 = Erebus.prefix("textures/entity/velvetworm_2.png");

	public VelvetWormRenderer(EntityRendererProvider.Context context) {
        super(context, new VelvetWormModel<>(context.bakeLayer(ModEntityRendering.VELVET_WORM)), 0.6F);
	}

	@Override
	protected void scale(VelvetWorm velvetworm, PoseStack matrix, float partialTickTime) {
		int size = velvetworm.getInflateSize();
		matrix.scale((float) (size * 0.009 + 1F), (float) (size * 0.009 + 1F), (float) (-size * 0.0025 + 1F));
	}

	@Override
	public  ResourceLocation getTextureLocation(VelvetWorm velvetworm) {
		if (velvetworm.getSkin() == 0)
			return TEXTURE_1;
		else
			return TEXTURE_2;
	}
}