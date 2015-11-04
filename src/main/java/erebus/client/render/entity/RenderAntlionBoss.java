package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelAntlionBoss;
import erebus.entity.EntityAntlionBoss;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

public class RenderAntlionBoss extends RenderLiving {

	private static ResourceLocation texture = new ResourceLocation("erebus:textures/entity/antlionSandstone.png");

	public RenderAntlionBoss() {
		super(new ModelAntlionBoss(), 3.0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		EntityAntlionBoss antlionBoss = (EntityAntlionBoss) entityliving;
		BossStatus.setBossStatus(antlionBoss, false);
		float f1 = 2.0F;
		GL11.glScalef(f1, f1, f1);
		if (antlionBoss.getHealth() <= 0) {
			GL11.glTranslatef(0, (antlionBoss.deathTicks) * 0.006F, 0);
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
