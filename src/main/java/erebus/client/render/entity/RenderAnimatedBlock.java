package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.client.model.block.ModelBambooCrate;
import erebus.client.model.entity.ModelAnimatedBlock;
import erebus.entity.EntityAnimatedBlock;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderAnimatedBlock extends RenderLiving {

	private final ModelBambooCrate bambooCrateModel = new ModelBambooCrate();

	public RenderAnimatedBlock(ModelAnimatedBlock model, float scale) {
		super(model, scale);
	}

	public void renderAnimatedBlock(EntityAnimatedBlock entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		boolean alpha = entity.blockID.getRenderBlockPass() == 1;
		if (entity.blockID == ModBlocks.bambooCrate) {
			bindTexture(new ResourceLocation("erebus:textures/special/tiles/bambooCrate.png"));
			GL11.glPushMatrix();
			GL11.glTranslated(x, y + 1.75F, z);
			GL11.glScalef(1.0F, -1F, -1F);
			GL11.glRotatef(entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
			bambooCrateModel.renderModel();
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			if (alpha) {
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glEnable(GL11.GL_BLEND);
			}
			GL11.glTranslatef((float) x, (float) y, (float) z);
			GL11.glTranslatef(0.0F, 0.75F, 0.0F);
			GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
			bindTexture(TextureMap.locationBlocksTexture);
			field_147909_c.renderBlockAsItem(entity.blockID, entity.blockMeta, 1.0F);
			if (alpha)
				GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
		if (alpha) {
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glEnable(GL11.GL_BLEND);
		}
		super.doRender(entity, x, y, z, rotationYaw, partialTickTime);
		if (alpha)
			GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	public void doRender(EntityLiving entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
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
		String blockPath = animatedblock.blockID.getIcon(0, animatedblock.blockMeta).getIconName();
		String modName = "minecraft";
		if (blockPath.contains(":")) {
			modName = blockPath.split(":")[0];
			blockPath = blockPath.split(":")[1];
		}
		return new ResourceLocation(modName, "textures/blocks/" + blockPath + ".png");
	}
}