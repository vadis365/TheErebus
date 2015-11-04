package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.item.ModelWarHammer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class WarHammerItemRenderer implements IItemRenderer {
	private final ModelWarHammer model;
	public static ResourceLocation texture = new ResourceLocation("erebus:textures/special/items/warHammer.png");

	public WarHammerItemRenderer() {
		model = new ModelWarHammer();
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
				renderHammer(0.0F, 1.5F, 0.0F, 1.0D);
				break;
			case EQUIPPED:
				renderEquipped(0.3F, (float) (0.5F + item.getTagCompound().getInteger("charge") * 0.05), 0.4F, 1.5D + item.getTagCompound().getInteger("charge") * 0.04);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderHammerFirstPerson(0.5F, (float) (0.9F + item.getTagCompound().getInteger("charge") * 0.02), 0.5F, 1.75D + item.getTagCompound().getInteger("charge") * 0.02);
				break;
			case INVENTORY:
				renderHammerInventory(0.825F, 1.2F, 0.0F, 1.3D);
				break;
			default:
				break;
		}
	}

	private void renderEquipped(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y + 1.8F, z + 0.3F);
		GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
		GL11.glScaled(-size, -size, size);
		model.render();
		GL11.glPopMatrix();
	}

	private void renderHammer(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x + 0.6F, y - 0.8F, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(45F, 0, 0, 1F);
			GL11.glRotatef(-90F, 0, 1F, 0);
			GL11.glScaled(1F, 1F, 1F);
			model.render();
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(45F, 0, 1F, 0);
			GL11.glRotatef(0F, 0, 0, 1F);
			GL11.glScaled(size, size, size);
			model.render();
			GL11.glPopMatrix();
		}
	}

	private void renderHammerFirstPerson(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y + 1.25F, z);
		GL11.glRotatef(-45F, 0, 1F, 0);
		GL11.glRotatef(180F, 0, 0, 1F);
		GL11.glScaled(size, size, size);
		model.render();
		GL11.glPopMatrix();
	}

	private void renderHammerInventory(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(120F, 1F, 0, 0);
		GL11.glRotatef(45F, 0, 1F, 0);
		GL11.glScaled(size, size, size);
		model.render();
		GL11.glPopMatrix();
	}
}
