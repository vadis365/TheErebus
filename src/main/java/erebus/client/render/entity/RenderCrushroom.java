package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelCrushroom;
import erebus.entity.EntityCrushroom;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderCrushroom extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/crushroom.png");

	public RenderCrushroom() {
		super(new ModelCrushroom(), 1.5F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entity, float partialTickTime) {
		BossStatus.setBossStatus((EntityCrushroom) entity, false);
		GL11.glScalef(2F, 2F, 2F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}
