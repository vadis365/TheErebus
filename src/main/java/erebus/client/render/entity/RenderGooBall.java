package erebus.client.render.entity;

import erebus.entity.EntityGooBall;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderGooBall extends Render<EntityGooBall> {

	private final ItemStack gooBall = new ItemStack(Items.SLIME_BALL);

	public RenderGooBall(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(EntityGooBall goo_ball, double x, double y, double z, float yaw, float tick) {
		renderGooBall(goo_ball, x, y, z, yaw, tick);
	}

	public void renderGooBall(EntityGooBall goo_ball, double x, double y, double z, float yaw, float tick) {
		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.translate(x, y + 0.4F, z);
		GlStateManager.scale(1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		Minecraft.getMinecraft().getRenderItem().renderItem(gooBall, Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(gooBall, (World) null, (EntityLivingBase) null));
		GlStateManager.rotate(90F, 0F, 1F, 0F);
		Minecraft.getMinecraft().getRenderItem().renderItem(gooBall, Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(gooBall, (World) null, (EntityLivingBase) null));
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityGooBall goo_ball) {
		return null;
	}
}