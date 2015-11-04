package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.item.ModelPortalStaff;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class PortalActivatorRenderer implements IItemRenderer {

	private static final ModelPortalStaff model = new ModelPortalStaff();
	private static final ResourceLocation texture = new ResourceLocation("erebus:textures/special/items/portalStaff.png");

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
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		switch (type) {
			case ENTITY:
				renderWand(-0.5F, 0.2F, -0.5F, 1.5);
				break;
			case EQUIPPED:
				renderEquipped(0.3F, 1.4F, 0.9F, 1.5);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderWandFirstPerson(0.5F, 0.9F, 0.5F, 2);
				break;
			case INVENTORY:
				renderWandInventory(0.75F, 0.8F, 0.25F, 1.8);
				break;
			default:
				break;
		}
	}

	private void renderEquipped(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(225.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(-size, -size, size);
		model.render();
		GL11.glPopMatrix();
	}

	private void renderWand(float x, float y, float z, double size) {
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x + 0.75F, y + 0.25F, z + 0.5F);
			GL11.glRotatef(180F, 1, 0, 0);
			GL11.glRotatef(45F, 0, 0, 1);
			GL11.glScaled(size * 2F / 3F, size * 2F / 3F, size * 2F / 3F);
			model.render();
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(-135F, 0, 1F, 0);
			GL11.glRotatef(70F, 0, 0, 1F);
			GL11.glScaled(size, size, size);
			model.render();
			GL11.glPopMatrix();
		}
	}

	private void renderWandFirstPerson(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y + 0.5F, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glRotatef(-45F, 0, 1F, 0);
		GL11.glScaled(size, size, size);
		model.render();
		GL11.glPopMatrix();
	}

	private void renderWandInventory(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(-45F, 1, 0, 1);

		GL11.glRotatef(180F, 1, 0, 0);
		GL11.glScaled(size, size, size);
		model.render();
		GL11.glPopMatrix();
	}
}