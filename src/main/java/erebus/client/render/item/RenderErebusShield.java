package erebus.client.render.item;

import erebus.client.model.item.ModelErebusShieldParts;
import erebus.items.ItemErebusShield;
import erebus.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelShield;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderErebusShield extends TileEntityItemStackRenderer {
    public final TileEntityItemStackRenderer PARENT;
    private final ModelShield MODEL_SHIELD = new ModelErebusShieldParts();
    private final ResourceLocation SHIELD_PARTS_TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/items/shield_boss_and_handle.png");

    public RenderErebusShield(TileEntityItemStackRenderer previous) {
    	PARENT = previous;
    }

    @Override
    public void renderByItem(ItemStack stack) {
    	this.renderByItem(stack, 1.0F);
    }

    @Override
	public void renderByItem(ItemStack stack, float partialTicks) {
        if ((!stack.isEmpty()) && (stack.getItem() instanceof ItemErebusShield)) {
            ItemErebusShield item = (ItemErebusShield) stack.getItem();
            Minecraft.getMinecraft().getTextureManager().bindTexture(SHIELD_PARTS_TEXTURE);
            GlStateManager.pushMatrix();
            GlStateManager.scale(1.0, -1.0, -1.0);
            MODEL_SHIELD.render();
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            GlStateManager.scale(1.25, 1.25, 1.25);
            GlStateManager.translate(0, 0, 0.08125);
            Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            renderItem(item.getShieldType().getShieldItem());
            GlStateManager.popMatrix();
        }
        else
            PARENT.renderByItem(stack);
    }

    private void renderItem(ItemStack stack){
        Minecraft.getMinecraft().getRenderItem().renderItem(stack, Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, (World) null, (EntityLivingBase) null));
    }
}