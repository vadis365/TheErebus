package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelMoth;
import erebus.entity.EntityMoth;

@SideOnly(Side.CLIENT)
public class RenderMoth extends RenderLiving {
	private static final ResourceLocation resource1 = new ResourceLocation("erebus:textures/entity/moth1.png");
	private static final ResourceLocation resource2 = new ResourceLocation("erebus:textures/entity/moth2.png");
	private static final ResourceLocation resource3 = new ResourceLocation("erebus:textures/entity/moth3.png");

	public RenderMoth() {
		super(new ModelMoth(), 0.3F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f) {
		GL11.glScalef(0.5F, 0.5F, 0.5F);
		EntityMoth entityMoth = (EntityMoth) entityliving;
		if (entityMoth.getIsMothHanging())
			GL11.glRotatef(180.0F, -1.0F, 0.0F, 0.0F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityMoth entityMoth = (EntityMoth) entity;
		switch (entityMoth.getSkin()) {
			case 0:
				return resource1;
			case 1:
				return resource2;
			case 2:
				return resource3;
		}
		return resource1;
	}
}