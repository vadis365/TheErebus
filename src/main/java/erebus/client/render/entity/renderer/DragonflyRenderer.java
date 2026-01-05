package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.DragonflyModel;
import erebus.entity.Dragonfly;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DragonflyRenderer extends MobRenderer<Dragonfly, DragonflyModel <Dragonfly>>{

	public static final ResourceLocation TEXTURE_1 = Erebus.prefix("textures/entity/dragonfly_ender.png");
	public static final ResourceLocation TEXTURE_2 = Erebus.prefix("textures/entity/dragonfly_green.png");
	public static final ResourceLocation TEXTURE_3 = Erebus.prefix("textures/entity/dragonfly_red.png");
	public static final ResourceLocation TEXTURE_4 = Erebus.prefix("textures/entity/dragonfly_purple.png");
	public static final ResourceLocation TEXTURE_5 = Erebus.prefix("textures/entity/dragonfly_blue.png");
	public static final ResourceLocation TEXTURE_6 = Erebus.prefix("textures/entity/dragonfly_tan.png");

	public DragonflyRenderer(EntityRendererProvider.Context context) {
		super(context, new DragonflyModel<>(context.bakeLayer(ModEntityRendering.DRAGON_FLY)), 0.3F);
		addLayer(new DragonflyLayer(this, context.getModelSet()));
	}

	@Override
	protected void scale(Dragonfly dragonfly, PoseStack matrix, float partialTickTime) {
		matrix.scale(1.0F, 1.0F, 1.0F);
		// Other sizes to be added
	}

	@Override
	public  ResourceLocation getTextureLocation(Dragonfly dragonfly) {
		if (dragonfly.getSkin() > 0 && dragonfly.getSkin() <= 10)
			return TEXTURE_2;
		else if (dragonfly.getSkin() > 10 && dragonfly.getSkin() <= 20)
			return TEXTURE_3;
		else if (dragonfly.getSkin() > 20 && dragonfly.getSkin() <= 30)
			return TEXTURE_4;
		else if (dragonfly.getSkin() > 30 && dragonfly.getSkin() <= 40)
			return TEXTURE_5;
		else if (dragonfly.getSkin() > 40 && dragonfly.getSkin() <= 50)
			return TEXTURE_6;
		else if (dragonfly.getSkin() == 0)
			return TEXTURE_1;
		else
			return TEXTURE_1;
	}
}
