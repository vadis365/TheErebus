package erebus.client.render.item;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelOfferingAltar;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class ItemOfferingAltarRenderer implements IItemRenderer {
	private final ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/offeringAltar.png");
	private final ModelOfferingAltar model = new ModelOfferingAltar();

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
				renderBlock(0.0F, 1.0F, 0.0F);
				break;
			case EQUIPPED:
				renderBlock(0.5F, 1.5F, 0.5F);
				break;
			case EQUIPPED_FIRST_PERSON:
				renderBlock(0.5F, 1.5F, 0.5F);
				break;
			case INVENTORY:
				renderBlock(0.0F, 1.0F, 0.0F);
				break;
			default:
				break;
		}
	}

	private void renderBlock(float x, float y, float z) {
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, z);
		GL11.glScaled(-1, -1, 1);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
		model.render();
		GL11.glPopMatrix();
	}
}