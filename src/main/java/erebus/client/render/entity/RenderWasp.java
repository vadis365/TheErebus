package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelWasp;
import erebus.entity.EntityWasp;
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
		if (((EntityWasp) entity).getIsBoss() == 1) {
			shadowSize = 2;
			GL11.glScalef(2, 2, 2);
		} else
			shadowSize = 1;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWasp entity) {
		return ((EntityWasp) entity).getIsBoss() == 1 ? HORNET : WASP;
	}
}