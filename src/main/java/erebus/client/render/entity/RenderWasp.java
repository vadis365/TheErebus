package erebus.client.render.entity;

import erebus.client.model.entity.ModelWasp;
import erebus.entity.EntityWasp;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWasp extends RenderLiving<EntityWasp> {

	private static final ResourceLocation WASP = new ResourceLocation("erebus:textures/entity/wasp.png");
	private static final ResourceLocation HORNET = new ResourceLocation("erebus:textures/entity/hornet.png");

	public RenderWasp(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelWasp(), 1F);
    }

	@Override
	protected void preRenderCallback(EntityWasp entity, float partialTickTime) {
		float size = 0.5F;
		if (entity.getIsBoss() == 1) {
			shadowSize = 1F;
			size = 1F;
		}
		else
			shadowSize = 0.5F;
		GlStateManager.scale(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWasp entity) {
		return ((EntityWasp) entity).getIsBoss() == 1 ? HORNET : WASP;
	}
}