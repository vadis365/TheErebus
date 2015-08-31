package erebus.client.render.item;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelTarantulaEgg;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ItemTarantulaEggRenderer implements IItemRenderer {
	private final ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/tarantulaEgg.png");
	private final ModelTarantulaEgg model = new ModelTarantulaEgg();

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
		model.renderAll();
		GL11.glPopMatrix();
	}
}