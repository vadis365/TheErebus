package erebus.client.render.entity;

import erebus.client.model.entity.ModelHoneyPotAnt;
import erebus.entity.EntityHoneyPotAnt;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHoneyPotAnt extends RenderLiving<EntityHoneyPotAnt> {

	private static ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/honey_pot_ant.png");

	public RenderHoneyPotAnt(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelHoneyPotAnt(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityHoneyPotAnt ant, float partialTickTime) {
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityHoneyPotAnt ant) {
		return TEXTURE;
	}
}