package erebus.client.render.item;

import erebus.client.model.item.ModelWandOfAnimation;
import erebus.items.ItemWandOfAnimation;
import erebus.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderWandOfAnimation extends TileEntityItemStackRenderer {
    public final TileEntityItemStackRenderer PARENT;
    private final ModelWandOfAnimation MODEL_WAND = new ModelWandOfAnimation();
    private final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/special/items/wand_of_animation.png");

    public RenderWandOfAnimation(TileEntityItemStackRenderer previous) {
    	PARENT = previous;
    }

    @Override
    public void renderByItem(ItemStack stack) {
    	this.renderByItem(stack, 1.0F);
    }

    @Override
	public void renderByItem(ItemStack stack, float partialTicks) {
        if ((!stack.isEmpty()) && (stack.getItem() instanceof ItemWandOfAnimation)) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
            GlStateManager.pushMatrix();
            GlStateManager.scale(1, 1, 1);
            MODEL_WAND.render();
            GlStateManager.popMatrix();
        }
        else
            PARENT.renderByItem(stack);
    }
}