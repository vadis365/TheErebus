package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.MothModel;
import erebus.entity.Moth;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MothRenderer extends MobRenderer<Moth, MothModel<Moth>> {
	public static final ResourceLocation TEXTURE_1 = Erebus.prefix("textures/entity/moth_1.png");
	public static final ResourceLocation TEXTURE_2 = Erebus.prefix("textures/entity/moth_2.png");
	public static final ResourceLocation TEXTURE_3 = Erebus.prefix("textures/entity/moth_3.png");

	public MothRenderer(EntityRendererProvider.Context context) {
        super(context, new MothModel<>(context.bakeLayer(ModEntityRendering.MOTH)), 0.3F);
	}

	@Override
	protected void scale(Moth moth, PoseStack matrix, float partialTickTime) {
		matrix.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public  ResourceLocation getTextureLocation(Moth moth) {
		switch (moth.getSkin()) {
			case 0:
				return TEXTURE_1;
			case 1:
				return TEXTURE_2;
			case 2:
				return TEXTURE_3;
		}
		return TEXTURE_1;
	}
}
