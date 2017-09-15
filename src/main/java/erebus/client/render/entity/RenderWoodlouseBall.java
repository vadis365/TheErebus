package erebus.client.render.entity;

import erebus.ModItems;
import erebus.entity.EntityWoodlouseBall;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWoodlouseBall extends Render<EntityWoodlouseBall> {
	private final ItemStack louseball = new ItemStack(ModItems.WOODLOUSE_BALL);

	public RenderWoodlouseBall(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityWoodlouseBall woodlouseball, double x, double y, double z, float yaw, float partialTickTime) {
		renderWoodlouseBall(woodlouseball, x, y, z, yaw, partialTickTime);
	}

	public void renderWoodlouseBall(EntityWoodlouseBall woodlouseball, double x, double y, double z, float rotationYaw, float partialTickTime) {
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translate(x, y + 0.4F, z);
		GlStateManager.scale(1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		GlStateManager.rotate(woodlouseball.rotationYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(0F + woodlouseball.rotationticks + (woodlouseball.rotationticks - (woodlouseball.rotationticks - 1)) * partialTickTime, -1.0F, 0.0F, 0.0F);
		Minecraft.getMinecraft().getRenderItem().renderItem(louseball, Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(louseball, (World) null, (EntityLivingBase) null));
		
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWoodlouseBall woodlouseball) {
		return null;
	}
}