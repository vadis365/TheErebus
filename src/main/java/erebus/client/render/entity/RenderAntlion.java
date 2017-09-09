package erebus.client.render.entity;

import erebus.client.model.entity.ModelAntlion;
import erebus.entity.EntityAntlion;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAntlion extends RenderLiving<EntityAntlion> {

	private static ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/antlion.png");

	public RenderAntlion(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelAntlion(), 0.75F);
	}

	@Override
	protected void preRenderCallback(EntityAntlion antlion, float partialTickTime) {
		GlStateManager.scale(0.75F, 0.75F, 0.75F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAntlion antlion) {
		return TEXTURE;
	}
}