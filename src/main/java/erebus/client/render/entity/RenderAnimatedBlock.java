package erebus.client.render.entity;

import erebus.client.model.entity.ModelAnimatedBlock;
import erebus.entity.EntityAnimatedBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderAnimatedBlock extends RenderLiving<EntityAnimatedBlock> {

	public RenderAnimatedBlock(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelAnimatedBlock(), 0.3F);
	}

	@Override
	public void doRender(EntityAnimatedBlock animatedblock, double x, double y, double z, float rotationYaw, float partialTickTime) {
		GlStateManager.pushMatrix();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.enableBlend();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.translate(0.0F, 0.75F, 0.0F);
		GlStateManager.pushMatrix();
		GlStateManager.rotate(-animatedblock.renderYawOffset, 0.0F, 1.0F, 0.0F);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(animatedblock.blockID.getStateFromMeta(animatedblock.blockMeta), 1.0F);
		GlStateManager.popMatrix();
		GlStateManager.popMatrix();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
		super.doRender(animatedblock, x, y, z, rotationYaw, partialTickTime);
	}

	@Override
	protected void preRenderCallback(EntityAnimatedBlock animatedblock, float scale) {
		if (animatedblock.isClimbing())
			GlStateManager.rotate(90.0F, -1.0F, 0.0F, 0.0F);
	}

	@Override
	protected float getDeathMaxRotation(EntityAnimatedBlock entity) {
		return 0F;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityAnimatedBlock animatedblock) {
		String blockPath = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getTexture(animatedblock.blockID.getStateFromMeta(animatedblock.blockMeta)).getIconName();
		String modName = "minecraft";
		if (blockPath.contains(":")) {
			modName = blockPath.split(":")[0];
			blockPath = blockPath.split(":")[1];
		}
		return new ResourceLocation(modName, "textures/" + blockPath + ".png");
	}
}