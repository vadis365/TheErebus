package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.render.tileentity.TileEntityGlowingJarRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class ItemGlowingJarRenderer implements IItemRenderer {

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
				GL11.glScaled(0.5D, 0.5D, 0.5D);
				renderBlock(-0.5F, 0.0F, -0.5F);
				break;
			case EQUIPPED:
				renderBlock(0.0F, 0.0F, 0.5F);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderBlock(0.0F, 0.0F, 0.0F);
				break;
			case INVENTORY:
				GL11.glScaled(0.9D, 0.9D, 0.9D);
				renderBlock(0.0F, 0.0F, 0.0F);
				break;
			default:
				break;
		}
	}

	private void renderBlock(float x, float y, float z) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		TileEntityGlowingJarRenderer.instance.renderJar(0, 0, 0);
		GL11.glPopMatrix();
	}
}