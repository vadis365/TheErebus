package erebus.client.render.entity;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.entity.ModelSporeling;
import erebus.entity.EntitySporeling;

public class RenderSporeling extends RenderLiving
{
	 private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/sporeling.png");
	 private static final ResourceLocation eyeTexture = new ResourceLocation("erebus:textures/entity/sporelingGlow.png");

    public RenderSporeling(ModelSporeling ModelSporeling, float shadow)
    {
        super(ModelSporeling, shadow);
        this.setRenderPassModel(new ModelSporeling());
    }

    protected int setSporelingEyeBrightness(EntitySporeling entityLiving, int par2, float par3) {
        if (par2 != 0) {
            return -1;
        }
        else {
        	this.bindTexture(eyeTexture);
            float var4 = 1.0F;
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
            char var5 = 61680;
            int var6 = var5 % 65536;
            int var7 = var5 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var6 / 1.0F, (float)var7 / 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, var4);
            return 1;
        }
    }


    protected int shouldRenderPass(EntityLiving entityLiving, int par2, float par3) {
        return this.setSporelingEyeBrightness((EntitySporeling)entityLiving, par2, par3);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
