package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelFireAntSoldier;

public class RenderZombieAntSoldier extends RenderLiving {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/fungal_ant_soldier.png");

	public RenderZombieAntSoldier() {
		super(new ModelFireAntSoldier(), 1.5F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		GL11.glScalef(1.125F, 1.125F, 1.125F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return TEXTURE;
	}
}