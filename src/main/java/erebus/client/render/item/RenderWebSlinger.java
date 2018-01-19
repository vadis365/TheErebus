package erebus.client.render.item;

import erebus.ModItems;
import erebus.client.model.item.ModelWebSlinger;
import erebus.items.ItemWebSlinger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWebSlinger extends TileEntityItemStackRenderer {
	private final ModelWebSlinger MODEL = new ModelWebSlinger();
	private final ResourceLocation TEXTURE_WEB_SLINGER = new ResourceLocation("erebus:textures/special/items/web_slinger.png");
	private final ResourceLocation TEXTURE_WEB_SLINGER_WITHER = new ResourceLocation("erebus:textures/special/items/web_slinger_wither.png");
	public final TileEntityItemStackRenderer PARENT;

	public RenderWebSlinger(TileEntityItemStackRenderer previous) {
		PARENT = previous;
	}

	@Override
	public void renderByItem(ItemStack stack) {
		if ((!stack.isEmpty()) && (stack.getItem() instanceof ItemWebSlinger)) {
			ResourceLocation texture = stack.getItem() == ModItems.WEB_SLINGER ? TEXTURE_WEB_SLINGER : TEXTURE_WEB_SLINGER_WITHER;
			Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
			GlStateManager.pushMatrix();
			GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.rotate(-45.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(80.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.translate(0F, 0.0625F, - 0.25F);
			GlStateManager.scale(1.5F, 1.5F, 1.5F);
			MODEL.render();
			GlStateManager.popMatrix();
		} else
			PARENT.renderByItem(stack);
	}
}