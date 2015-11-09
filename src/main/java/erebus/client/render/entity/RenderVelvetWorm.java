package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelVelvetWorm;
import erebus.entity.EntityVelvetWorm;

@SideOnly(Side.CLIENT)
public class RenderVelvetWorm extends RenderLiving {
	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/entity/velvetworm.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/entity/velvetworm2.png");

	public RenderVelvetWorm() {
		super(new ModelVelvetWorm(), 0.6F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLiving, float par2) {
		EntityVelvetWorm worm = (EntityVelvetWorm) entityLiving;
		int size = worm.getInflateSize();
		GL11.glScalef((float) (size * 0.009 + 1F), (float) (size * 0.009 + 1F), (float) (-size * 0.0025 + 1F));
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if (((EntityVelvetWorm) entity).getSkin() == 0)
			return resource2;
		else
			return resource1;
	}
}