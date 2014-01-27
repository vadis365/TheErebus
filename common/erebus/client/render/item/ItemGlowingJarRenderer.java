package erebus.client.render.item;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelGlowingJar;

@SideOnly(Side.CLIENT)
public class ItemGlowingJarRenderer implements IItemRenderer {

	private final ModelGlowingJar ModelGlowingJar = new ModelGlowingJar();

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
				renderBlock(0.0F, 1.75F, 0.0F, 1.0D);
				break;
			case EQUIPPED:
				renderBlock(0.5F, 2.0F, 0.5F, 1.0D);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderBlock(0.5F, 2.0F, 0.5F, 1.0D);
				break;
			case INVENTORY:
				renderBlock(0.0F, 1.1F, 0.0F, 0.9D);
				break;
			default:
				break;
		}
	}

	private void renderBlock(float x, float y, float z, double size) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(new ResourceLocation("erebus:textures/special/tiles/ModelGlowingJar1.png"));
		if (RenderItem.renderInFrame) {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y - 0.625F, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glScaled(0.75F, 1.0F, 0.75F);
			ModelGlowingJar.render();
			GL11.glPopMatrix();
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(180F, 1F, 0, 0);
			GL11.glRotatef(-90F, 0, 1F, 0);
			GL11.glScaled(size, size * 1.25F, size);
			ModelGlowingJar.render();
			GL11.glPopMatrix();
		}
	}
}