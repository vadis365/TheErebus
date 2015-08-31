package erebus.client.render.entity;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelPondSkater;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderPondSkater extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/pondSkater.png");

	public RenderPondSkater() {
		super(new ModelPondSkater(), 0.5F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityLiving, float partialTickTime) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		GL11.glTranslatef(0F, -0.5F, 0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}