package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import erebus.ModBlocks;
import erebus.client.model.entity.ModelFireAnt;
import erebus.entity.EntityZombieAnt;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

public class RenderZombieAnt extends RenderLiving {

	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/entity/blackAntFungal.png");

	private final RenderBlocks blockRenderer = new RenderBlocks();

	public RenderZombieAnt() {
		super(new ModelFireAnt(), 1.0F);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float partialTickTime) {
		GL11.glScalef(1.5F, 1.5F, 1.5F);
	}

	public void renderZombieAnt(EntityZombieAnt entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		double a = Math.toRadians(entity.renderYawOffset);
		double offSetX = -Math.sin(a) * entity.width * 0.3D;
		double offSetZ = Math.cos(a) * entity.width * 0.3D;

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glTranslated(0D - offSetX, entity.height + 0.15D, 0D - offSetZ);
		GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
		bindTexture(TextureMap.locationBlocksTexture);
		GL11.glScaled(entity.width * 0.2F, entity.width * 0.2F, entity.width * 0.2F);
		blockRenderer.renderBlockAsItem(ModBlocks.dutchCap, 0, 10.0F);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glTranslated(0D + offSetX, entity.height + 0.1D, 0D + offSetZ);
		GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
		bindTexture(TextureMap.locationBlocksTexture);
		GL11.glScaled(entity.width * 0.2F, entity.width * 0.2F, entity.width * 0.2F);
		blockRenderer.renderBlockAsItem(ModBlocks.greenMushroom, 0, 10.0F);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glTranslated(0D - offSetX * 1.4D, entity.height + 0.125D, 0D - offSetZ * 1.4D);
		GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
		bindTexture(TextureMap.locationBlocksTexture);
		GL11.glScaled(entity.width * 0.2F, entity.width * 0.2F, entity.width * 0.2F);
		blockRenderer.renderBlockAsItem(ModBlocks.kaizerfinger, 0, 10.0F);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glTranslated(0D - offSetX * 0.75D, entity.height + 0.2D, 0D - offSetZ * 0.75D);
		GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
		bindTexture(TextureMap.locationBlocksTexture);
		GL11.glScaled(entity.width * 0.2F, entity.width * 0.2F, entity.width * 0.2F);
		blockRenderer.renderBlockAsItem(ModBlocks.bundleshroom, 0, 10.0F);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glTranslated(0D - offSetX * 0.3D, entity.height + 0.14D, 0D - offSetZ * 0.3D);
		GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
		bindTexture(TextureMap.locationBlocksTexture);
		GL11.glScaled(entity.width * 0.2F, entity.width * 0.2F, entity.width * 0.2F);
		blockRenderer.renderBlockAsItem(ModBlocks.bulbCapped, 0, 10.0F);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glTranslated(0D + offSetX * 0.7D, entity.height + 0.15D, 0D + offSetZ * 0.7D);
		GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
		bindTexture(TextureMap.locationBlocksTexture);
		GL11.glScaled(entity.width * 0.25F, entity.width * 0.25F, entity.width * 0.25F);
		blockRenderer.renderBlockAsItem(Blocks.red_mushroom, 0, 10.0F);
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glTranslated(0D + offSetX * 0.3D, entity.height + 0.15D, 0D + offSetZ * 0.3D);
		GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
		bindTexture(TextureMap.locationBlocksTexture);
		GL11.glScaled(entity.width * 0.25F, entity.width * 0.25F, entity.width * 0.25F);
		blockRenderer.renderBlockAsItem(Blocks.brown_mushroom, 0, 10.0F);
		GL11.glPopMatrix();

		super.doRender(entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderZombieAnt((EntityZombieAnt) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}