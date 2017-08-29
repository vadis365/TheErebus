package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelDragonfly;
import erebus.entity.EntityDragonfly;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderDragonfly extends RenderLiving {
	private static final ResourceLocation[] textures = new ResourceLocation[] { new ResourceLocation("erebus:textures/entity/dragonflyEnder.png"), new ResourceLocation("erebus:textures/entity/dragonflyGreen.png"), new ResourceLocation("erebus:textures/entity/dragonflyRed.png"), new ResourceLocation("erebus:textures/entity/dragonflyPurple.png"), new ResourceLocation("erebus:textures/entity/dragonflyBlue.png"), new ResourceLocation("erebus:textures/entity/dragonflyTan.png") };

	public RenderDragonfly() {
		super(new ModelDragonfly(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		// Other sizes to be added
		GL11.glScalef(1.0F, 1.0F, 1.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityDragonfly dragonfly = (EntityDragonfly) entity;
		if (dragonfly.getSkin() > 0 && dragonfly.getSkin() <= 10)
			return textures[1];
		else if (dragonfly.getSkin() > 10 && dragonfly.getSkin() <= 20)
			return textures[2];
		else if (dragonfly.getSkin() > 20 && dragonfly.getSkin() <= 30)
			return textures[3];
		else if (dragonfly.getSkin() > 30 && dragonfly.getSkin() <= 40)
			return textures[4];
		else if (dragonfly.getSkin() > 40 && dragonfly.getSkin() <= 50)
			return textures[5];
		else if (dragonfly.getSkin() == 0)
			return textures[0];
		else
			return textures[1];
	}
}