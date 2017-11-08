package erebus.client.render.entity;

import erebus.client.model.entity.ModelUmberGolem;
import erebus.entity.EntityUmberGolem;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderUmberGolem extends RenderLiving<EntityUmberGolem> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/umber_golem.png");

	public RenderUmberGolem(RenderManager rendermangerIn) {
		super(rendermangerIn, new ModelUmberGolem(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityUmberGolem entityliving, float f) {
		GlStateManager.scale(0.75F, 0.75F, 0.75F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityUmberGolem entity) {
		return TEXTURE;
	}
}