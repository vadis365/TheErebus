package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.item.ModelWandOfPreservation;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class ItemWandOfPreservationRenderer implements IItemRenderer {

	private final ModelWandOfPreservation model;

	public ItemWandOfPreservationRenderer() {
		model = new ModelWandOfPreservation();
	}

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
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(new ResourceLocation("erebus:textures/special/items/wandOfPreservation.png"));
		switch (type) {
			case ENTITY:
				renderWand(0.0F, 0.0F, 0.0F, 0.75D);
				break;
			case EQUIPPED:
				renderEquipped(0.4F, 1.8F, 1F, 1.5D);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderWandFirstPerson(0.5F, 1.75F, 0.5F, 1.5D);
				break;
			case INVENTORY:
				renderWandInventory(0.9F, 0.9F, 0.0F, 1.2D);
				break;
			default:
				break;
		}
	}

	private void renderEquipped(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(-size, -size, size);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}

	private void renderWand(float x, float y, float z, double size) {
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x + 0.4F, y + 0.6F, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(45F, 0, 0, 1F);
			GL11.glScaled(0.8F, 0.8F, 0.8F);
			model.render(0.0625F);
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y + 0.5F, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(-135F, 0, 1F, 0);
			GL11.glRotatef(70F, 0, 0, 1F);
			GL11.glScaled(size, size, size);
			model.render(0.0625F);
			GL11.glPopMatrix();
		}
	}

	private void renderWandFirstPerson(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glRotatef(-45F, 0, 1F, 0);
		GL11.glScaled(size, size, size);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}

	private void renderWandInventory(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(135F, 1F, 0, 0);
		GL11.glRotatef(135F, 0, 1F, 0);
		GL11.glRotatef(-20F, 0, 0, 1F);
		GL11.glScaled(size, size, size);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}
}