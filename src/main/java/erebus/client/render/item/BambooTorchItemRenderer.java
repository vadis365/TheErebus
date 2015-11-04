package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class BambooTorchItemRenderer implements IItemRenderer {

	private final RenderBlocks blockRenderer = new RenderBlocks();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type != ItemRenderType.FIRST_PERSON_MAP;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
			case ENTITY:
				renderTorch(0.0F, 0.5F, 0.0F, 1.0D);
				break;
			case EQUIPPED:
				renderEquipped(0.7F, 0.7F, 0.7F, 1.0D);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderTorchFirstPerson(0.5F, 0.9F, 0.5F, 1.0D);
				break;
			case INVENTORY:
				renderTorchInventory(0.0F, -0.45F, 0.0F, 0.75D);
				break;
			default:
				break;
		}
	}

	private void renderEquipped(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScaled(size, size, size);
		blockRenderer.renderBlockAsItem(ModBlocks.bambooTorch, 0, 1.0F);
		GL11.glTranslatef(x - 0.7F, y + 0.3F, z - 0.7F);
		blockRenderer.renderBlockAsItem(ModBlocks.bambooTorch, 1, 1.0F);
		GL11.glPopMatrix();
	}

	private void renderTorch(float x, float y, float z, double size) {
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y - 0.625F, z);
			GL11.glScaled(0.75F, 0.75F, 0.75F);
			blockRenderer.renderBlockAsItem(ModBlocks.bambooTorch, 0, 1.0F);
			GL11.glTranslatef(x, y, z);
			blockRenderer.renderBlockAsItem(ModBlocks.bambooTorch, 1, 1.0F);
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glScaled(size, size, size);
			blockRenderer.renderBlockAsItem(ModBlocks.bambooTorch, 0, 1.0F);
			GL11.glTranslatef(x, y, z);
			blockRenderer.renderBlockAsItem(ModBlocks.bambooTorch, 1, 1.0F);
			GL11.glPopMatrix();
		}
	}

	private void renderTorchFirstPerson(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(-45F, 0, 1F, 0);
		GL11.glScaled(size, size, size);
		blockRenderer.renderBlockAsItem(ModBlocks.bambooTorch, 0, 1.0F);
		GL11.glTranslatef(x - 0.5F, y + 0.1F, z - 0.5F);
		blockRenderer.renderBlockAsItem(ModBlocks.bambooTorch, 1, 1.0F);
		GL11.glPopMatrix();
	}

	private void renderTorchInventory(float x, float y, float z, double size) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glScaled(size, size, size);
		blockRenderer.renderBlockAsItem(ModBlocks.bambooTorch, 0, 1.0F);
		GL11.glTranslatef(x, y + 1.4F, z);
		blockRenderer.renderBlockAsItem(ModBlocks.bambooTorch, 1, 1.0F);
		GL11.glPopMatrix();
	}

}