package erebus.client.render.entity;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.ModBlocks;
import erebus.client.model.entity.ModelFireAnt;
import erebus.entity.EntityBlackAnt;

public class RenderBlackAnt extends RenderLiving {
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/blackAnt.png");
	private static final ResourceLocation textureFungal = new ResourceLocation("erebus:textures/entity/blackAntFungal.png");
	
	private final RenderBlocks blockRenderer = new RenderBlocks();
	public RenderBlackAnt() {
		super(new ModelFireAnt(), 1.0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		scaleBlackAnt((EntityBlackAnt) entityliving, partialTickTime);
	}
	
	public void renderBlackAnt(EntityBlackAnt entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		double a = Math.toRadians(entity.renderYawOffset);
		double offSetX = -Math.sin(a) * entity.width * 0.3D;
		double offSetZ = Math.cos(a) * entity.width * 0.3D;
		if(entity.getDataWatcher().getWatchableObjectByte(20) == 1) {
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			GL11.glTranslated(0D - offSetX, entity.height + 0.15D, 0D - offSetZ);
			GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
			bindTexture(TextureMap.locationBlocksTexture);
			GL11.glScaled(entity.width *0.2F, entity.width *0.2F, entity.width *0.2F);
			blockRenderer.renderBlockAsItem(ModBlocks.erebusPlantSmall, 0, 10.0F);
			GL11.glPopMatrix();
		}
		if(entity.getDataWatcher().getWatchableObjectByte(21) == 1) {
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			GL11.glTranslated(0D + offSetX, entity.height + 0.1D, 0D + offSetZ);
			GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
			bindTexture(TextureMap.locationBlocksTexture);
			GL11.glScaled(entity.width *0.2F, entity.width *0.2F, entity.width *0.2F);
			blockRenderer.renderBlockAsItem(ModBlocks.erebusPlantSmall, 1, 10.0F);
			GL11.glPopMatrix();
		}
		if(entity.getDataWatcher().getWatchableObjectByte(22) == 1) {
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			GL11.glTranslated(0D - offSetX * 1.4D, entity.height + 0.125D, 0D - offSetZ * 1.4D);
			GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
			bindTexture(TextureMap.locationBlocksTexture);
			GL11.glScaled(entity.width *0.2F, entity.width *0.2F, entity.width *0.2F);
			blockRenderer.renderBlockAsItem(ModBlocks.erebusPlantSmall, 2, 10.0F);
			GL11.glPopMatrix();
		}
		if(entity.getDataWatcher().getWatchableObjectByte(23) == 1) {
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			GL11.glTranslated(0D - offSetX * 0.75D, entity.height + 0.2D, 0D - offSetZ * 0.75D);
			GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
			bindTexture(TextureMap.locationBlocksTexture);
			GL11.glScaled(entity.width *0.2F, entity.width *0.2F, entity.width *0.2F);
			blockRenderer.renderBlockAsItem(ModBlocks.erebusPlantSmall, 3, 10.0F);
			GL11.glPopMatrix();
		}
		if(entity.getDataWatcher().getWatchableObjectByte(24) == 1) {
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			GL11.glTranslated(0D - offSetX * 0.3D, entity.height + 0.14D, 0D - offSetZ* 0.3D);
			GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
			bindTexture(TextureMap.locationBlocksTexture);
			GL11.glScaled(entity.width *0.2F, entity.width *0.2F, entity.width *0.2F);
			blockRenderer.renderBlockAsItem(ModBlocks.erebusPlantSmall, 4, 10.0F);
			GL11.glPopMatrix();
		}
		if(entity.getDataWatcher().getWatchableObjectByte(25) == 1) {
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			GL11.glTranslated(0D + offSetX *0.7D, entity.height + 0.15D, 0D + offSetZ *0.7D);
			GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
			bindTexture(TextureMap.locationBlocksTexture);
			GL11.glScaled(entity.width *0.25F, entity.width *0.25F, entity.width *0.25F);
			blockRenderer.renderBlockAsItem(Blocks.red_mushroom, 0, 10.0F);
			GL11.glPopMatrix();
		}
		if(entity.getDataWatcher().getWatchableObjectByte(26) == 1) {
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			GL11.glTranslated(0D + offSetX *0.3D, entity.height + 0.15D, 0D + offSetZ *0.3D);
			GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
			bindTexture(TextureMap.locationBlocksTexture);
			GL11.glScaled(entity.width *0.25F, entity.width *0.25F, entity.width *0.25F);
			blockRenderer.renderBlockAsItem(Blocks.brown_mushroom, 0, 10.0F);
			GL11.glPopMatrix();
		}
		super.doRender(entity, x, y, z, rotationYaw, partialTickTime);
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderBlackAnt((EntityBlackAnt) entity, x, y, z, rotationYaw, partialTickTime);
	}

	protected void scaleBlackAnt(EntityBlackAnt entityBlackAnt, float partialTickTime) {
		float size = 0.75F;
		if(entityBlackAnt.getCanZombie() == 7)
			size = 1.5F;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if (((EntityBlackAnt) entity).getCanZombie() == 7)
			return textureFungal;
		else
			return texture;
	}
}