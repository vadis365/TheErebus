package erebus.client.render.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import erebus.client.model.block.ModelKitchenCounter;
import erebus.lib.Reference;

public class TileEntityKitchenCounterRenderer extends TileEntitySpecialRenderer{
	
	private final ModelKitchenCounter model;
	
	public TileEntityKitchenCounterRenderer(){
		this.model = new ModelKitchenCounter();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) (z + 0.5F));
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/special/tiles/kitchenCounter.png"));
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

}
