package erebus.client.render.entity;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelGlowWorm;
import erebus.entity.EntityGlowWorm;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderGlowWorm extends RenderLiving {
	private static final ResourceLocation texGlowing = new ResourceLocation("erebus:textures/entity/glowWormGlow.png"), texNormal = new ResourceLocation("erebus:textures/entity/glowWorm.png");

	public RenderGlowWorm(ModelGlowWorm model, float shadowSize) {
		super(model, shadowSize);
		setRenderPassModel(new ModelGlowWorm());
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		float f1 = 0.75F;
		shadowSize = 0.0F;
		GL11.glScalef(f1, f1, f1);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((EntityGlowWorm) entity).isGlowing() ? texGlowing : texNormal;
	}
}