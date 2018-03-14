package erebus.client.render.item;

import erebus.client.model.item.ModelPortalActivator;
import erebus.items.ItemPortalActivator;
import erebus.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderPortalActivator extends TileEntityItemStackRenderer {

    public final TileEntityItemStackRenderer PARENT;

    private final ModelPortalActivator MODEL_PORTAL_ACTIVATOR = new ModelPortalActivator();
    private final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/special/items/portal_activator.png");

    public RenderPortalActivator(TileEntityItemStackRenderer previous) {
    	PARENT = previous;
    }

    @Override
    public void renderByItem(ItemStack stack) {
        if ((!stack.isEmpty()) && (stack.getItem() instanceof ItemPortalActivator)) {
            Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
            GlStateManager.pushMatrix();
            GlStateManager.color(1, 1, 1, 1);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.enableTexture2D();
            GlStateManager.scale(1F, 1F, 1F);
            MODEL_PORTAL_ACTIVATOR.render();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
        else
            PARENT.renderByItem(stack);
    }
}
