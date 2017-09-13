package erebus.client.render.entity;

import erebus.client.model.entity.ModelCropWeevil;
import erebus.entity.EntityCropWeevil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCropWeevil extends RenderLiving<EntityCropWeevil> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/crop_weevil.png");

	public RenderCropWeevil(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelCropWeevil(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityCropWeevil weevil, float partialTickTime) {
		GlStateManager.scale(0.6F, 0.6F, 0.6F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCropWeevil weevil) {
		return TEXTURE;
	}
}