package erebus.client.render.entity;

import erebus.client.model.entity.ModelCropWeevil;
import erebus.entity.EntityFungalWeevil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFungalWeevil extends RenderLiving<EntityFungalWeevil> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/fungal_weevil.png");

	public RenderFungalWeevil(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelCropWeevil(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityFungalWeevil weevil, float partialTickTime) {
		GlStateManager.scale(0.6F, 0.6F, 0.6F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFungalWeevil weevil) {
		return TEXTURE;
	}
}