package erebus.client.gui;

import erebus.core.helper.Utils;
import erebus.inventory.ContainerLiquifier;
import erebus.tileentity.TileEntityLiquifier;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiLiquifier extends GuiErebus {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/gui/container/liquifier.png");
	private final TileEntityLiquifier liquifier;
	private int tankXMin, tankYMin, tankXMax, tankYMax;

	public GuiLiquifier(InventoryPlayer inventory, TileEntityLiquifier tile) {
		super(new ContainerLiquifier(inventory, tile));
		liquifier = tile;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		int colour = Utils.getColour(0, 0, 0);
		fontRenderer.drawString(I18n.format(new TextComponentTranslation(liquifier.getName()).getFormattedText()), xSize / 2 - fontRenderer.getStringWidth(I18n.format(new TextComponentTranslation(liquifier.getName()).getFormattedText())) / 2 + 1, 7, colour);
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.inventory").getFormattedText()), xSize - 167, ySize - 93, colour);
		fontRenderer.drawString(I18n.format(new TextComponentTranslation(liquifier.getName()).getFormattedText()), xSize / 2 - fontRenderer.getStringWidth(I18n.format(new TextComponentTranslation(liquifier.getName()).getFormattedText())) / 2, 6, Utils.getColour(255, 255, 255));
		fontRenderer.drawString(I18n.format(new TextComponentTranslation("container.inventory").getFormattedText()), xSize - 168, ySize - 94, Utils.getColour(255, 255, 255));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(TEXTURE);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		FluidTank tank = liquifier.tank;
			if (tank.getFluidAmount() > 0)
				drawFluid(tank.getFluid(), (int) (39 * (float) tank.getFluidAmount() / tank.getCapacity()), 108 + guiLeft, 24 + guiTop, 30, 39);

		mc.renderEngine.bindTexture(TEXTURE);

		int operationProgress = liquifier.getOperationProgressScaled(22);
		drawTexturedModalRect(guiLeft + 69, guiTop + 35, 176, 0, operationProgress, 16);

		drawTexturedModalRect(guiLeft + 105, guiTop + 23, 176, 16, 36, 41);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTickTime) {
		super.drawScreen(mouseX, mouseY, partialTickTime);
		tankXMin = (width - xSize) / 2 + 105;
		tankYMin = (height - ySize) / 2 + 23;
		tankXMax = tankXMin + 36;
		tankYMax = tankYMin + 41;
		if (mouseX >= tankXMin && mouseX <= tankXMax)
			if (mouseY >= tankYMin && mouseY <= tankYMax) {
				FluidTank tank = liquifier.tank;
				if (tank.getFluidAmount() > 0)
					drawToolTip(mouseX, mouseY, "Contains: "+ liquifier.tank.getFluidAmount()+"Mb of "+ liquifier.tank.getFluid().getLocalizedName());
			}
				
	}
}