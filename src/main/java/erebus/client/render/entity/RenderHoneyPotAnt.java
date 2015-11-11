package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelHoneyPotAnt;

@SideOnly(Side.CLIENT)
public class RenderHoneyPotAnt extends RenderLiving {

	private static ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/honeyPotAnt.png");

	public RenderHoneyPotAnt() {
		super(new ModelHoneyPotAnt(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}
}