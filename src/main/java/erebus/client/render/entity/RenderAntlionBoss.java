package erebus.client.render.entity;

import erebus.client.model.entity.ModelAntlionBoss;
import erebus.entity.EntityAntlionBoss;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class RenderAntlionBoss extends RenderLiving<EntityAntlionBoss>{

	private static ResourceLocation TEXTUE = new ResourceLocation("erebus:textures/entity/antlion_sandstone.png");

	public RenderAntlionBoss(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelAntlionBoss(), 3.0F);
	}

	@Override
	protected void preRenderCallback(EntityAntlionBoss antlionBoss, float partialTickTime) {
		GlStateManager.scale(2.0F, 2.0F, 2.0F);
		if (antlionBoss.getHealth() <= 0) {
			GlStateManager.translate(0, (antlionBoss.deathTicks) * 0.006F, 0);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAntlionBoss antlionBoss) {
		return TEXTUE;
	}
}
