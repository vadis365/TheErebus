package erebus.client.render.entity;

import erebus.client.model.entity.ModelWoodlouse;
import erebus.entity.EntityWoodlouse;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWoodlouse extends RenderLiving<EntityWoodlouse> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/woodlouse.png");

	public RenderWoodlouse(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelWoodlouse(), 0.0F);
	}

	@Override
	protected void preRenderCallback(EntityWoodlouse woodlouse, float partialTickTime) {
		GlStateManager.scale(0.3F, 0.3F, 0.3F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWoodlouse woodlouse) {
		return TEXTURE;
	}
}