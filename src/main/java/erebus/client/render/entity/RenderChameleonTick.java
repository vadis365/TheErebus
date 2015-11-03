package erebus.client.render.entity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.entity.ModelChameleonTick;
import erebus.entity.EntityChameleonTick;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderChameleonTick extends RenderLiving {

	private final RenderBlocks blockRenderer = new RenderBlocks();

	public RenderChameleonTick() {
		super(new ModelChameleonTick(), 0.3F);
	}

	public void renderChameleonTick(EntityChameleonTick entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		boolean alpha = entity.blockID.getRenderBlockPass() == 1;
		float animationSize = entity.animation;
		GL11.glPushMatrix();
		if (alpha) {
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glEnable(GL11.GL_BLEND);
		}
		GL11.glTranslatef((float) x, (float) y, (float) z);
		GL11.glTranslatef(0.0F, 0.5F, 0.0F);
		GL11.glRotatef(-entity.renderYawOffset, 0.0F, 1.0F, 0.0F);
		bindTexture(TextureMap.locationBlocksTexture);
		GL11.glPushMatrix();
		GL11.glScaled(1F, 1F - 0.02F * animationSize, 1F);
		blockRenderer.renderBlockAsItem(entity.blockID, entity.blockMeta, 1.0F);
		GL11.glPopMatrix();
		if (alpha)
			GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
		super.doRender(entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderChameleonTick((EntityChameleonTick) entity, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected float getDeathMaxRotation(EntityLivingBase entityLivingBase) {
		return 0F;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		EntityChameleonTick chameleonTick = (EntityChameleonTick) entity;
		String blockPath = chameleonTick.blockID.getIcon(0, chameleonTick.blockMeta).getIconName();
		String modName = "minecraft";
		if (blockPath.contains(":")) {
			modName = blockPath.split(":")[0];
			blockPath = blockPath.split(":")[1];
		}
		return new ResourceLocation(modName, "textures/blocks/" + blockPath + ".png");
	}
}