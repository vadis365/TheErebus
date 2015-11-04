package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.item.ModelWandOfAnimation;
import erebus.item.ItemWandOfAnimation;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class ItemWandOfAnimationRenderer implements IItemRenderer {

	private final ModelWandOfAnimation model;

	public ItemWandOfAnimationRenderer() {
		model = new ModelWandOfAnimation();
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
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(new ResourceLocation("erebus:textures/special/items/wandOfAnimation.png"));
		switch (type) {
			case ENTITY:
				renderWand(0.0F, 0.0F, 0.0F, 0.5D, (ItemWandOfAnimation) item.getItem());
				break;
			case EQUIPPED:
				renderEquipped(0.3F, 0.5F, 0.4F, 0.75D, (ItemWandOfAnimation) item.getItem());
				break;
			case EQUIPPED_FIRST_PERSON:
				renderWandFirstPerson(0.5F, 0.9F, 0.5F, 1.0D, (ItemWandOfAnimation) item.getItem());
				break;
			case INVENTORY:
				renderWandInventory(0.1F, 0.1F, 0.0F, 0.75D, (ItemWandOfAnimation) item.getItem());
				break;
			default:
				break;
		}
	}

	private void renderEquipped(float x, float y, float z, double size, ItemWandOfAnimation item) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y + 0.6F, z + 0.5F);
		GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(225.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(-size, -size, size);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}

	private void renderWand(float x, float y, float z, double size, ItemWandOfAnimation item) {
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x + 0.1F, y + 0.25F, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(0F, 0, 1F, 0);
			GL11.glRotatef(45F, 0, 0, 1F);
			GL11.glScaled(0.6F, 0.6F, 0.6F);
			model.render(0.0625F);
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(-135F, 0, 1F, 0);
			GL11.glRotatef(70F, 0, 0, 1F);
			GL11.glScaled(size, size, size);
			model.render(0.0625F);
			GL11.glPopMatrix();
		}
	}

	private void renderWandFirstPerson(float x, float y, float z, double size, ItemWandOfAnimation item) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glRotatef(-45F, 0, 1F, 0);
		GL11.glScaled(size, size, size);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}

	private void renderWandInventory(float x, float y, float z, double size, ItemWandOfAnimation item) {
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