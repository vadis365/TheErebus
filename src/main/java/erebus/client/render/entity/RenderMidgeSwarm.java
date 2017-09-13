package erebus.client.render.entity;

import erebus.client.model.entity.ModelMidgeSwarm;
import erebus.entity.EntityMidgeSwarm;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMidgeSwarm extends RenderLiving<EntityMidgeSwarm> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/midge_swarm.png");

	public RenderMidgeSwarm(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelMidgeSwarm(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityMidgeSwarm swarm, float partialTickTime) {
		GlStateManager.scale(0.4F, 0.4F, 0.4F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMidgeSwarm swarm) {
		return TEXTURE;
	}
}