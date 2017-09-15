package erebus.client.render.entity;

import erebus.client.model.entity.ModelChameleonTick;
import erebus.entity.EntityChameleonTick;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderChameleonTick extends RenderLiving<EntityChameleonTick> {

	public RenderChameleonTick(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelChameleonTick(), 0.3F);
	}

	public void renderChameleonTick(EntityChameleonTick tick, double x, double y, double z, float rotationYaw, float partialTickTime) {
		float animationSize = tick.animation;
		GlStateManager.pushMatrix();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.enableBlend();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.pushMatrix();
		GlStateManager.rotate(-tick.renderYawOffset, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(-0.5F, 0F, 0.475F);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.pushMatrix();
		GlStateManager.scale(1F, 1F - 0.01F * animationSize, 1F);
		Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(tick.blockID.getStateFromMeta(tick.blockMeta), 1.0F);
		GlStateManager.popMatrix();
		GlStateManager.popMatrix();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
		super.doRender(tick, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	public void doRender(EntityChameleonTick tick, double x, double y, double z, float rotationYaw, float partialTickTime) {
		renderChameleonTick(tick, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected float getDeathMaxRotation(EntityChameleonTick tick) {
		return 0F;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityChameleonTick tick) {
		String blockPath = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(tick.blockID.getStateFromMeta(tick.blockMeta)).getIconName();
		String modName = "minecraft";
		if (blockPath.contains(":")) {
			modName = blockPath.split(":")[0];
			blockPath = blockPath.split(":")[1];
		}
		return new ResourceLocation(modName, "textures/" + blockPath + ".png");
	}
}