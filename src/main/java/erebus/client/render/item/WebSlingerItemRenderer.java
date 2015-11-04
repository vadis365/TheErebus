package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.client.model.item.ModelWebSlinger;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class WebSlingerItemRenderer implements IItemRenderer {
	private final ModelWebSlinger model = new ModelWebSlinger();
	private final ResourceLocation WEB_SLINGER = new ResourceLocation("erebus:textures/special/items/webSlinger.png");
	private final ResourceLocation WITHER_WEB_SLINGER = new ResourceLocation("erebus:textures/special/items/webWitherSlinger.png");

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return helper != ItemRendererHelper.BLOCK_3D;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		ResourceLocation texture = item.getItem() == ModItems.webSlinger ? WEB_SLINGER : WITHER_WEB_SLINGER;
		switch (type) {
			case ENTITY:
				renderWebSlinger(0.0F, 0.5F, 0.0F, 0.5D, texture);
				break;
			case EQUIPPED:
				renderEquipped(-0.8F, -1.6F, -1.25F, 1.5D, texture);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderWebSlingerFirstPerson(1.0F, 1.0F, 0.0F, 2.5D, texture);
				break;
			case INVENTORY:
				renderWebSlingerInventory(-0.3F, 0.3F, 0F, 0.9D, texture);
				break;
			default:
				break;
		}
	}

	private void renderEquipped(float x, float y, float z, double size, ResourceLocation texture) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(120.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(x, y, z);
		GL11.glScaled(size, size, size);
		model.render();
		GL11.glPopMatrix();
	}

	private void renderWebSlinger(float x, float y, float z, double size, ResourceLocation texture) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(180F, 1, 0, -1F);
			GL11.glScaled(0.70F, 0.70F, 0.70F);
			model.render();
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(180F, 1F, 0F, 0F);
			GL11.glScaled(size, size, size);
			model.render();
			GL11.glPopMatrix();
		}
	}

	private void renderWebSlingerFirstPerson(float x, float y, float z, double size, ResourceLocation texture) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-160.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(size, size, size);
		model.render();
		GL11.glPopMatrix();
	}

	private void renderWebSlingerInventory(float x, float y, float z, double size, ResourceLocation texture) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(45F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-135.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(size, size, size);
		model.render();
		GL11.glPopMatrix();
	}
}