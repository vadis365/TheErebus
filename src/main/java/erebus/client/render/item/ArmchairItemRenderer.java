package erebus.client.render.item;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelArmchair;

@SideOnly(Side.CLIENT)
public class ArmchairItemRenderer implements IItemRenderer {

	private final ModelArmchair armchairModel = new ModelArmchair();
	private final ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/armchair.png");

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
				renderBlock(0.0F, 0.5F, 0.0F, 0.5D);
				break;
			case EQUIPPED:
				renderBlock(0.5F, 2.0F, 1.0F, 1.0D);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderBlock(0.5F, 1.5F, 0.5F, 1.0D);
				break;
			case INVENTORY:
				GL11.glPushMatrix();
				GL11.glRotatef(90F, 0, 1F, 0);
				renderBlock(0.0F, 1.0F, 0.0F, 1.0D);
				GL11.glPopMatrix();
				break;
			default:
				break;
		}
	}

	private void renderBlock(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y + 0.4F, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(-90F, 0, 1F, 0);
			GL11.glScaled(size + 0.2D, size + 0.2D, size + 0.2D);
			armchairModel.render();
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(90F, 0, 1F, 0);
			GL11.glScaled(size, size, size);
			armchairModel.render();
			GL11.glPopMatrix();
		}
	}
}