package erebus.client.render.entity;

import erebus.client.model.entity.ModelVelvetWorm;
import erebus.entity.EntityVelvetWorm;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderVelvetWorm extends RenderLiving<EntityVelvetWorm> {
	private final ResourceLocation TEXTURE_1 = new ResourceLocation("erebus:textures/entity/velvetworm.png");
	private final ResourceLocation TEXTURE_2 = new ResourceLocation("erebus:textures/entity/velvetworm_2.png");

	public RenderVelvetWorm(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelVelvetWorm(), 0.6F);
	}

	@Override
	protected void preRenderCallback(EntityVelvetWorm velvetworm, float partialTickTime) {
		int size = velvetworm.getInflateSize();
		GlStateManager.scale((float) (size * 0.009 + 1F), (float) (size * 0.009 + 1F), (float) (-size * 0.0025 + 1F));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityVelvetWorm velvetworm) {
		if (velvetworm.getSkin() == 0)
			return TEXTURE_1;
		else
			return TEXTURE_2;
	}
}