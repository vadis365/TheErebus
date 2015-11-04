package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.item.ModelScorpionPincer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class ScorpionPincerItemRenderer implements IItemRenderer {
	private final ModelScorpionPincer model;
	public static ResourceLocation texture = new ResourceLocation("erebus:textures/special/items/scorpionPincer.png");

	public ScorpionPincerItemRenderer() {
		model = new ModelScorpionPincer();
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
		switch (type) {
			case ENTITY:
				renderPincer(0.0F, 0.0F, 0.0F, 1.0D);
				break;
			case EQUIPPED:
				renderEquipped(0.6F, -0.5F, 0.5F, 2.5D);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderPincerFirstPerson(0.0F, 0.5F, 0.5F, 2.5D);
				break;
			case INVENTORY:
				renderPincerInventory(0F, -0.5F, 0F, 2.0D);
				break;
			default:
				break;
		}
	}

	private void renderEquipped(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
		GL11.glTranslatef(x, y, z);
		GL11.glScaled(size, size, size);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}

	private void renderPincer(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x - 0.45F, y, z + 0.1F);
			GL11.glRotatef(-60F, 0, 0, 1F);
			GL11.glScaled(1.8F, 1.8F, 1.8F);
			model.render(0.0625F);
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glScaled(size, size, size);
			model.render(0.0625F);
			GL11.glPopMatrix();
		}
	}

	private void renderPincerFirstPerson(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(135.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(225.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(size, size, size);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}

	private void renderPincerInventory(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glScaled(size, size, size);
		model.render(0.0625F);
		GL11.glPopMatrix();
	}
}
