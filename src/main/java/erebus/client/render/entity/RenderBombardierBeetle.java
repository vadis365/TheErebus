package erebus.client.render.entity;

import erebus.client.model.entity.ModelBombardierBeetle;
import erebus.entity.EntityBombardierBeetle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBombardierBeetle extends RenderLiving<EntityBombardierBeetle> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/beetle_bombardier.png");

	public RenderBombardierBeetle(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelBombardierBeetle(), 0.6F);
	}

	@Override
	protected void preRenderCallback(EntityBombardierBeetle beetle, float partialTickTime) {
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityBombardierBeetle beetle) {
		return TEXTURE;
	}
}
