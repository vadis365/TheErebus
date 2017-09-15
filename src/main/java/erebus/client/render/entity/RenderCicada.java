package erebus.client.render.entity;

import erebus.client.model.entity.ModelCicada;
import erebus.entity.EntityCicada;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCicada extends RenderLiving<EntityCicada> {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/cicada.png");

	public RenderCicada(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelCicada(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityCicada cicada, float partialTickTime) {
		GlStateManager.scale(0.7F, 0.7F, 0.7F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCicada cicada) {
		return TEXTURE;
	}
}