package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelStagBeetle;
import erebus.entity.EntityStagBeetle;

@SideOnly(Side.CLIENT)
public class RenderStagBeetle extends RenderLiving {
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/entity/stagBeetle.png");
	private static final ResourceLocation TEXTURE_TAME = new ResourceLocation("erebus:textures/entity/stagBeetleKit.png");

	public RenderStagBeetle() {
		super(new ModelStagBeetle(), 1F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		GL11.glScalef(1.2F, 1.2F, 1.2F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return ((EntityStagBeetle) entity).getTameState() < 2 ? TEXTURE : TEXTURE_TAME;
	}
}