package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelWasp;
import erebus.entity.EntityWasp;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderWasp extends RenderLiving {

	private static final ResourceLocation WASP = new ResourceLocation("erebus:textures/entity/wasp.png");
	private static final ResourceLocation HORNET = new ResourceLocation("erebus:textures/entity/hornet.png");

	public RenderWasp() {
		super(new ModelWasp(), 1F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
		if (((EntityWasp) entity).getIsBoss() == 1) {
			shadowSize = 2;
			GL11.glScalef(2, 2, 2);
		} else
			shadowSize = 1;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((EntityWasp) entity).getIsBoss() == 1 ? HORNET : WASP;
	}
}