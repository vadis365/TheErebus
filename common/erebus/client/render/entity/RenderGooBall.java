package erebus.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.entity.EntityGooBall;

@SideOnly(Side.CLIENT)
public class RenderGooBall extends Render {
	private final RenderItem renderItem = new RenderItem();
	private final ItemStack gooBall = new ItemStack(Item.slimeBall,1);
	private static ResourceLocation texture;
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float tick) {
		renderGooBall((EntityGooBall) entity, x, y, z, yaw, tick);
	}

	public void renderGooBall(EntityGooBall entityGooBall, double x, double y, double z, float yaw, float tick) {
		renderItem.setRenderManager(RenderManager.instance);
		EntityItem slimeballItem = new EntityItem(entityGooBall.worldObj);
		slimeballItem.hoverStart = 0.0F;
		slimeballItem.setEntityItemStack(gooBall);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) (y + 0.2F), (float) z + 0.5F);
		GL11.glScalef(1.2F, 1.2F, 1.2F);
		renderItem.doRenderItem(slimeballItem, 0, 0, 0, 0, 0);
		GL11.glRotatef(90, 0, 1, 0);
		renderItem.doRenderItem(slimeballItem, 0, 0, 0, 0, 0);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
			texture = new ResourceLocation("");
		return texture;
	}
}
