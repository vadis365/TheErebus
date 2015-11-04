package erebus.client.render.tileentity;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelOfferingAltar;
import erebus.tileentity.TileEntityOfferingAltar;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityOfferingAltarRenderer extends TileEntitySpecialRenderer {
	private final ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/offeringAltar.png");
	private final ModelOfferingAltar model = new ModelOfferingAltar();
	private final RenderItem renderItem;

	public TileEntityOfferingAltarRenderer() {
		renderItem = new RenderItem() {
			@Override
			public boolean shouldBob() {
				return true;
			}
		};
		renderItem.setRenderManager(RenderManager.instance);
	}

	@Override
	public void renderTileEntityAt(TileEntity t, double x, double y, double z, float m) {
		TileEntityOfferingAltar tile = (TileEntityOfferingAltar) t;
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GL11.glScaled(-1, -1, 1);
		bindTexture(texture);
		model.render();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.75F, (float) y + 0.75F, (float) z + 0.5F);
		renderItems(tile);
		GL11.glPopMatrix();
	}

	private void renderItems(TileEntityOfferingAltar tile) {
		float angle = tile.time;
		if (tile.getStackInSlot(3) == null) {
			GL11.glTranslated(-0.25, 0.5, 0);
			for (int i = 0; i < 3; i++) {
				EntityItem item = tile.getItemForRendering(i);
				if (item != null) {
					GL11.glPushMatrix();
					GL11.glRotated(120 * (i + 1) + tile.getWorldObj().getWorldTime(), 0, 1, 0);
					GL11.glTranslated(Math.cos(Math.toRadians(angle)), 0, 0);
					renderItem.doRender(item, 0, 0, 0, 0, 0);
					GL11.glPopMatrix();
				}
			}
		} else {
			GL11.glPushMatrix();
			GL11.glTranslatef(-0.25F, 0.25F, 0);
			GL11.glRotatef(tile.getWorldObj().getWorldTime(), 0, 1, 0);
			GL11.glScaled(1.5, 1.5, 1.5);
			renderItem.doRender(tile.getItemForRendering(3), 0, 0, 0, 0, 0);
			GL11.glPopMatrix();
		}
	}
}