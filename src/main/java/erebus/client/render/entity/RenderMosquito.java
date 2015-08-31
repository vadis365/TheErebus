package erebus.client.render.entity;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelMosquito;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderMosquito extends RenderLiving {
	private final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/mosquito.png");

	public RenderMosquito() {
		super(new ModelMosquito(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glTranslatef(0.0F, -1.4F, -0.5F);
		if (entityliving.ridingEntity != null)
			GL11.glTranslatef(0.0F, 0.0F, 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}