package erebus.client.render.entity;

import erebus.ModBlocks;
import erebus.entity.EntityPreservedBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEntityPreservedBlock extends Render<EntityPreservedBlock> {
	
	public final ItemStack amberBlock = new ItemStack(ModBlocks.AMBER.getDefaultState().getBlock());
	
	public RenderEntityPreservedBlock(RenderManager rendermanagerIn) {
		super(rendermanagerIn);
	}

	@Override
	public void doRender(EntityPreservedBlock entity, double x, double y, double z, float yaw, float tick) {
		renderPreservedBlock(entity, x, y, z, yaw, tick);
	}

	public void renderPreservedBlock(EntityPreservedBlock entity, double x, double y, double z, float yaw, float tick) {
		float rotation = entity.ticksExisted * 20 + tick;
		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 0.5D, z);
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.rotate(rotation, 0F, 1F, 0F);
		GlStateManager.scale(1D, 1D, 1D);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getRenderItem().renderItem(amberBlock, TransformType.FIXED);
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 0.5D, z);
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.rotate(45F + rotation, 1F, 1F, 0F);
		GlStateManager.scale(1D, 1D, 1D);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getRenderItem().renderItem(amberBlock, TransformType.FIXED);
		GlStateManager.popMatrix();

		GlStateManager.pushMatrix();
		GlStateManager.translate(x, y + 0.5D, z);
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.rotate(-45F + rotation, 1F, 1F, 0F);
		GlStateManager.scale(1D, 1D, 1D);
		bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getRenderItem().renderItem(amberBlock, TransformType.FIXED);
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityPreservedBlock entity) {
		return null;
	}

}