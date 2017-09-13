package erebus.client.render.entity;

import erebus.client.model.entity.ModelFireAntSoldier;
import erebus.entity.EntityZombieAntSoldier;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderZombieAntSoldier extends RenderLiving<EntityZombieAntSoldier> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/zombie_ant_soldier.png");

	public RenderZombieAntSoldier(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelFireAntSoldier(), 1.5F);
	}

	@Override
	protected void preRenderCallback(EntityZombieAntSoldier ant, float partialTickTime) {
		GlStateManager.scale(1.125F, 1.125F, 1.125F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityZombieAntSoldier ant) {
		return TEXTURE;
	}
}