package erebus.client.render.entity;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelBlackWidow;
import erebus.entity.EntityBlackWidow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RenderBlackWidow extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/blackWidow.png");

	public RenderBlackWidow() {
		super(new ModelBlackWidow(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		shadowSize = ((EntityBlackWidow) entityliving).getWidowSize() * 0.3F;
		GL11.glScalef(shadowSize, shadowSize, shadowSize);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}