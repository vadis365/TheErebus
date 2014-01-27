package erebus.client.render.entity;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.client.model.block.ModelBambooCrate;
import erebus.client.model.entity.ModelAnimatedBlock;
import erebus.entity.EntityAnimatedBlock;

@SideOnly(Side.CLIENT)
public class RenderAnimatedBlock extends RenderLiving {

	private final ModelBambooCrate bambooCrateModel = new ModelBambooCrate();

	public RenderAnimatedBlock(ModelAnimatedBlock model, float scale) {
		super(model, scale);
	}

	public void renderAnimatedBlock(EntityAnimatedBlock entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		if (entity.blockID == ModBlocks.bambooCrate.blockID) {
			bindTexture(new ResourceLocation("erebus:textures/special/tiles/bambooCrate.png"));
			GL11.glPushMatrix();
			GL11.glTranslated(x, y + 1.75F, z);
			GL11.glScalef(1.0F, -1F, -1F);
			GL11.glRotatef(entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
			bambooCrateModel.renderModel();
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glTranslatef(0.0F, 0.75F, 0.0F);
			GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
			bindTexture(TextureMap.locationBlocksTexture);
			renderBlocks.renderBlockAsItem(Block.blocksList[entity.blockID], entity.blockMeta, 1.0F);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glPopMatrix();
		}
		super.doRenderLiving(entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRenderLiving(EntityLiving entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderAnimatedBlock((EntityAnimatedBlock) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderAnimatedBlock((EntityAnimatedBlock) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityLivingBase entityliving, float scale) {
		shadowSize = 0.3F;
		GL11.glScalef(1.0F, 1.0F, 1.0F);
		EntityAnimatedBlock entityAnimatedBlock = (EntityAnimatedBlock) entityliving;
		if (entityAnimatedBlock.isClimbing())
			GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase entityLivingBase) {
		return 0F;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityAnimatedBlock animatedblock = (EntityAnimatedBlock) entity;
		String blockPath = Block.blocksList[animatedblock.blockID].getIcon(0, animatedblock.blockMeta).getIconName();
		String modName = "minecraft";
		if (blockPath.contains(":")) {
			modName = blockPath.split(":")[0];
			blockPath = blockPath.split(":")[1];
		}
		return new ResourceLocation(modName, "textures/blocks/" + blockPath + ".png");
	}
}