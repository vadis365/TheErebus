package erebus.client.render.entity;

import erebus.ModBlocks;
import erebus.entity.EntityTarantulaEgg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderEntityTarantulaEgg extends Render<EntityTarantulaEgg> {
	public final ItemStack EGG = new ItemStack(ModBlocks.TARANTULA_EGG.getDefaultState().getBlock());
	public RenderEntityTarantulaEgg(RenderManager rendermanagerIn) {
		super(rendermanagerIn);
	}

	@Override
	public void doRender(EntityTarantulaEgg egg, double x, double y, double z, float yaw, float tick) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 0.5F, z);
		GlStateManager.rotate(egg.prevRotationPitch + (egg.rotationPitch - egg.prevRotationPitch) * tick - egg.rotationticks, 0.0F, 1.0F, 0.0F);
		GlStateManager.scale(1.0D, 1.0D, 1.0D);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getRenderItem().renderItem(EGG, TransformType.FIXED);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityTarantulaEgg egg) {
		return null;
	}

}
