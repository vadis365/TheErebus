package erebus.client.gui;

import java.awt.Rectangle;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.inventory.ContainerSmoothieMaker;
import erebus.tileentity.TileEntitySmoothieMaker;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidTank;

@SideOnly(Side.CLIENT)
public class GuiSmoothieMaker extends GuiErebus {

	private TileEntitySmoothieMaker tile;
	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/gui/container/smoothieMaker.png");
	public static final Rectangle[] tankPositions = new Rectangle[] { new Rectangle(8, 6, 9, 73), new Rectangle(25, 6, 9, 73), new Rectangle(142, 6, 9, 73), new Rectangle(159, 6, 9, 73) };

	public GuiSmoothieMaker(InventoryPlayer inv, TileEntitySmoothieMaker tile) {
		super(new ContainerSmoothieMaker(inv, tile));
		this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(TEXTURE);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		FluidTank[] tanks = tile.getTanks();
		for (int i = 0; i < tankPositions.length; i++)
			if (tanks[i].getFluidAmount() > 0) {
				Rectangle rect = tankPositions[i];
				drawFluid(tanks[i].getFluid(), (int) (rect.height * (float) tanks[i].getFluidAmount() / tanks[i].getCapacity()), rect.x + guiLeft, rect.y + guiTop, rect.width, rect.height);
			}

		mc.renderEngine.bindTexture(TEXTURE);
		for (Rectangle rect : tankPositions)
			drawTexturedModalRect(guiLeft + rect.x, guiTop + 3 + rect.y, 176, 41, rect.width, rect.height);

		if (tile.isBlending()) {
			float currentProgress = tile.getBlendProgress();
			float prevProgress = tile.getPrevBlendProgress();
			float progress = currentProgress + (currentProgress - prevProgress) * partialTickTime;
			drawTexturedModalRectFloat(guiLeft + 52, guiTop + 26, 176, 0, 73, progress + 1);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
	}
}