package erebus.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fluids.FluidStack;

import org.lwjgl.opengl.GL11;

import erebus.inventory.ContainerKitchenCounter;
import erebus.tileentity.TileEntityKitchenCounter;

public class GuiKitchenCounter extends GuiContainer{
	private TileEntityKitchenCounter kitchen;
	private static final ResourceLocation gui = new ResourceLocation("erebus:textures/gui/container/kitchenCounter.png");
	
	public GuiKitchenCounter(InventoryPlayer inv, TileEntityKitchenCounter tile) {
		super(new ContainerKitchenCounter(inv, tile));
		kitchen = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(gui);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		//drawFluid(kitchen.getFluidTank().getFluid(), x + 104, y + 122, 16, 58, 0x404040);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2){
		fontRendererObj.drawString("Kitchen Counter", 8, 6, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}
	
	public void drawFluid(FluidStack fluid, int x, int y, int width, int height, int maxCapacity){
		if(fluid == null || fluid.getFluid() == null){
			return;
		}
		
		IIcon icon = fluid.getFluid().getFlowingIcon();
		mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		setGLColorFromInt(fluid.getFluid().getColor(fluid));
		int fullX = width / 16;
		int fullY = height / 16;
		int lastX = width - fullX * 16;
		int lastY = height - fullY * 16;
		int level = fluid.amount * height / maxCapacity;
		int fullLvl = (height - level) / 16;
		int lastLvl = (height - level) - fullLvl * 16;
		
		for(int c= 0; c < fullX; c++){
			for(int d = 0; d < fullY; d++){
				if(d >= fullLvl){
					drawCutIcon(icon, x + c * 16, y + d * 16, 16, 16, d == fullLvl ? lastLvl : 0);
				}
			}
		}
		
		for(int c = 0; c < fullX; c++){
			drawCutIcon(icon, x + c * 16, y + fullY * 16, 16, lastY, fullLvl == fullY ? lastLvl : 0);
		}
		
		for(int c = 0; c < fullY; c++){
			if(c >= fullLvl){
				drawCutIcon(icon, x + fullX * 16, y + c * 16, lastX, 16, c == fullLvl ? lastLvl : 0);
			}
		}
		
		drawCutIcon(icon, x + fullX * 16, y + fullY * 16, lastX, lastY, fullLvl == fullY ? lastLvl : 0);	
	}
	
	private void drawCutIcon(IIcon icon, int x, int y, int width, int height, int cut){
		Tessellator tess = Tessellator.instance;
		tess.startDrawingQuads();
		tess.addVertexWithUV(x, y + height, zLevel, icon.getMinU(), icon.getInterpolatedV(height));
		tess.addVertexWithUV(x + width, y + height, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
		tess.addVertexWithUV(x + width, y + cut, zLevel, icon.getInterpolatedU(width), icon.getInterpolatedV(cut));
		tess.addVertexWithUV(x, y + cut, zLevel, icon.getMinU(), icon.getInterpolatedV(cut));
		tess.draw();
	}
	
	private static void setGLColorFromInt(int color){
		float red = (color >> 16 & 255) / 255.0F;
		float green = (color >> 8 & 255) / 255.0F;
		float blue = (color & 255) / 255.0F;
		GL11.glColor4f(red, green, blue, 1.0F);
	}
}
