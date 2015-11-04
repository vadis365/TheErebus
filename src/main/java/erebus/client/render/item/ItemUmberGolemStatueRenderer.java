package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelUmberGolemStatue;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class ItemUmberGolemStatueRenderer implements IItemRenderer {

	private final ModelUmberGolemStatue ModelUmberGolemStatue = new ModelUmberGolemStatue();

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
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(new ResourceLocation("erebus:textures/special/tiles/umberGolemStatue.png"));
		switch (type) {
			case ENTITY:
				renderBlock(0.0F, 0.5F, 0.0F, 1.0D);
				break;
			case EQUIPPED:
				renderEquipped(0.3F, 2.3F, 0.7F, 1.0D);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderFirstPerson(0.5F, 1.5F, 0.5F, 1.0D);
				break;
			case INVENTORY:
				renderInventory(-0.25F, 0.75F, 0.0F, 0.8D);
				break;
			case FIRST_PERSON_MAP:
				break;
		}
	}

	private void renderBlock(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glScaled(0.5D, 0.5D, 0.5D);
		ModelUmberGolemStatue.render();
		GL11.glPopMatrix();
	}

	private void renderEquipped(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glRotatef(90F, 0, 1F, 0);
		GL11.glScaled(size, size * 1.25F, size);
		ModelUmberGolemStatue.render();
		GL11.glPopMatrix();
	}

	private void renderFirstPerson(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glRotatef(90F, 0, 1F, 0);
		GL11.glScaled(size, size * 1.25F, size);
		ModelUmberGolemStatue.render();
		GL11.glPopMatrix();
	}

	private void renderInventory(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glRotatef(-90F, 0, 1F, 0);
		GL11.glScaled(size, size * 1.25F, size);
		ModelUmberGolemStatue.render();
		GL11.glPopMatrix();
	}
}