package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.WaspModel;
import erebus.client.render.entity.renderer.layer.WaspLayer;
import erebus.entity.Wasp;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class WaspRenderer extends MobRenderer<Wasp, WaspModel<Wasp>> {
	public static final Identifier WASP = Erebus.prefix("textures/entity/wasp.png");
	private static final Identifier HORNET = Erebus.prefix("textures/entity/hornet.png");

	public WaspRenderer(EntityRendererProvider.Context context) {
        super(context, new WaspModel<>(context.bakeLayer(ModEntityRendering.WASP)), 0.5F);
        addLayer(new WaspLayer(this, context.getModelSet()));
    }

	@Override
	protected void scale(Wasp wasp, PoseStack matrix, float partialTickTime) {
		float size = 0.5F;
		if (wasp.getIsBoss())
			size = 1F;
		shadowRadius = size;
		matrix.translate(0F, 0F, -size * 0.5F);
		matrix.scale(size, size, size);
	}

	@Override
	public  Identifier getTextureLocation(Wasp wasp) {
		return wasp.getIsBoss() ? HORNET : WASP;
	}
}
