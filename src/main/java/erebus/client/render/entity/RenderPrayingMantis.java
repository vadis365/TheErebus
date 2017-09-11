package erebus.client.render.entity;

import erebus.client.model.entity.ModelPrayingMantis;
import erebus.entity.EntityPrayingMantis;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPrayingMantis extends RenderLiving<EntityPrayingMantis> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/praying_mantis.png");

	public RenderPrayingMantis(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelPrayingMantis(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityPrayingMantis mantis, float partialTickTime) {
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	//	GlStateManager.depthMask(!mantis.isInvisible());
		GlStateManager.color(1F, 1F, 1F, mantis.getAlpha());
		//System.out.println("Mantis Alpha: " + mantis.getAlpha());
	//	GlStateManager.depthMask(true);
	//	GlStateManager.disableBlend();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPrayingMantis entity) {
		return TEXTURE;
	}
}