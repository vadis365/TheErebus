package erebus.client.render.entity;

import erebus.client.model.entity.ModelMosquito;
import erebus.entity.EntityMosquito;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMosquito extends RenderLiving<EntityMosquito> {
	private final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/mosquito.png");

	public RenderMosquito(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelMosquito(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityMosquito mosquito, float partialTickTime ) {
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		GlStateManager.translate(0.0F, -1.4F, -0.5F);
		if (mosquito.getRidingEntity() != null)
			GlStateManager.translate(0.0F, 0.0F, 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMosquito mosquito) {
		return TEXTURE;
	}
}