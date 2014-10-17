package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.block.ModelKitchenCounter;

public class TileEntityKitchenCounterRenderer extends TileEntitySpecialRenderer{
	
	private final ModelKitchenCounter model;
	private final ResourceLocation texture = new ResourceLocation("erebus:textures/special/tiles/kitchenCounter.png");

	public TileEntityKitchenCounterRenderer(){
		this.model = new ModelKitchenCounter();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
		bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) (z + 0.5F));
		GL11.glPushMatrix();
		GL11.glScalef(0.89F, 1.0F, 0.89F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.renderAll();
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
