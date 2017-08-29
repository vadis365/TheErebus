package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelTarantula;
import erebus.entity.EntityTarantulaMiniboss;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderTarantulaMiniboss extends RenderLiving {

	private final ResourceLocation resource1 = new ResourceLocation("erebus:textures/entity/tarantula.png");
	private final ResourceLocation resource2 = new ResourceLocation("erebus:textures/entity/tarantulaTurqoise.png");
	private final ResourceLocation resource3 = new ResourceLocation("erebus:textures/entity/power.png");
	private final ModelBase overlayModel = new ModelTarantula(1.0F);

	public RenderTarantulaMiniboss() {
		super(new ModelTarantula(), 0.5F);

	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		EntityTarantulaMiniboss tarantula = (EntityTarantulaMiniboss) entityliving;
		BossStatus.setBossStatus(tarantula, false);
		float size = 2.0F;
		shadowSize = 2.5F;
		GL11.glScalef(size, size, size);
		if (tarantula.getHealth() > 150)
			GL11.glRotatef(180, 0F, 1F, 0F);
		if (tarantula.getHealth() <= 0) {
			GL11.glTranslatef(0F, -0.7F, 0F);
			GL11.glRotatef(180, 0F, 0F, 1F);
		}
	}

	@Override
	protected int shouldRenderPass(EntityLivingBase entityliving, int pass, float partialTickTime) {
		EntityTarantulaMiniboss tarantula = (EntityTarantulaMiniboss) entityliving;
		if (tarantula.getFancyRenderOverlay()) {
			if (tarantula.isInvisible())
				GL11.glDepthMask(false);
			else
				GL11.glDepthMask(true);

			if (pass == 1) {
				float scrollTimer = tarantula.ticksExisted + partialTickTime;
				bindTexture(resource3);
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				float yScroll = scrollTimer * 0.02F;
				GL11.glTranslatef(0F, yScroll, 0.0F);
				setRenderPassModel(overlayModel);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_BLEND);
				float colour = 0.5F;
				GL11.glColor4f(colour, colour, colour, 1.0F);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				return 1;
			}

			if (pass == 2) {
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_BLEND);
			}
		}

		return -1;
	}

	@Override
	protected int inheritRenderPass(EntityLivingBase entityliving, int pass, float partialTickTime) {
		return -1;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((EntityTarantulaMiniboss) entity).getHealth() >= 150 ? resource2 : resource1;
	}
}