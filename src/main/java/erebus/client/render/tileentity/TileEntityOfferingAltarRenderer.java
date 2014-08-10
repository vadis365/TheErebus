package erebus.client.render.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelOfferingAltar;
import erebus.tileentity.TileEntityOfferingAltar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityOfferingAltarRenderer extends TileEntitySpecialRenderer {
    public ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/offeringAltar.png");
	public ModelOfferingAltar model = new ModelOfferingAltar();
    public EntityItem[] items = new EntityItem[3];
    public Minecraft mc = Minecraft.getMinecraft();
    public RenderItem renderItem;

    public TileEntityOfferingAltarRenderer() {
        renderItem = new RenderItem();
        renderItem.setRenderManager(RenderManager.instance);
    }

	@Override
	public void renderTileEntityAt(TileEntity t, double x, double y, double z, float m) {
        TileEntityOfferingAltar tile = (TileEntityOfferingAltar) t;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.55F, (float) z + 0.5F);
        GL11.glRotatef(180f, 1f, 0f, 0f);
        bindTexture(texture);
		model.render();
        renderItem(tile);
        GL11.glPopMatrix();
	}

    public void renderItem(TileEntityOfferingAltar tile) {
        for (int i = 0; i < tile.items.length; i++) {
            EntityItem item = tile.getEntityItem(i);
            if (item != null) {
                renderItem.doRender(item, 0d, 0d, 0d, 0f, 0f);
            }
        }
    }
}
