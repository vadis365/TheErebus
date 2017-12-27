package erebus.client.render.entity;

import erebus.client.model.entity.ModelTarantula;
import erebus.entity.EntityTarantulaMiniboss;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderTarantulaMiniboss extends RenderLiving <EntityTarantulaMiniboss> {

	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/entity/tarantula.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/entity/tarantula_turqoise.png");

	public RenderTarantulaMiniboss(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelTarantula(), 3F);
		this.addLayer(new LayerTarantulaMiniboss (this));

	}

	@Override
	protected void preRenderCallback(EntityTarantulaMiniboss tarantula, float partialTickTime) {
		float size = 2.0F;
		shadowSize = 2.5F;
		GlStateManager.scale(size, size, size);
		if (tarantula.getHealth() > 150)
			GlStateManager.rotate(180F, 0F, 1F, 0F);
		if (tarantula.getHealth() <= 0) {
			GlStateManager.translate(0F, -0.7F, 0F);
			GlStateManager.rotate(180F, 0F, 0F, 1F);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTarantulaMiniboss tarantula) {
		return tarantula.getHealth() >= 150 ? resource2 : resource1;
	}
}