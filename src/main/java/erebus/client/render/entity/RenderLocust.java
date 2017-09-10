package erebus.client.render.entity;

import erebus.client.model.entity.ModelLocust;
import erebus.entity.EntityLocust;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLocust extends RenderLiving<EntityLocust> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/locust.png");

	public RenderLocust(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelLocust(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityLocust locust, float partialTickTime ) {
		GlStateManager.scale(1.5F, 1.5F, 1.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityLocust locust) {
		return TEXTURE;
	}
}