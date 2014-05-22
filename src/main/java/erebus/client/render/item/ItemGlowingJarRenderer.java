package erebus.client.render.item;

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

	private final ModelGlowingJar model = new ModelGlowingJar();

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
				renderBlock(0.0F, 1.5F, 0.0F);
				break;
			case EQUIPPED:
				renderBlock(0.5F, 1.0F, 0.5F);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderBlock(0.5F, 1.5F, 0.5F);
				break;
			case INVENTORY:
				GL11.glScaled(0.9D, 0.9D, 0.9D);
				renderBlock(0.0F, 1.25F, 0.0F);
				break;
			default:
				break;
		}
	}

	private void renderBlock(float x, float y, float z) {
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(new ResourceLocation("erebus:textures/special/tiles/glowingJar.png"));

		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glRotatef(180F, 1F, 0, 0);
		GL11.glScaled(1.0D, 1.25D, 1.0D);
		model.render();
		GL11.glPopMatrix();
	}
}