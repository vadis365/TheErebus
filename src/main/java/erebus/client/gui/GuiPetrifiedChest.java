package erebus.client.gui;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;
import erebus.inventory.ContainerPetrifiedWoodChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiPetrifiedChest extends GuiErebus {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/gui/container/petrifiedContainerBig.png");
	private final IInventory upperChestInventory, lowerChestInventory;

	private final int inventoryRows;

	public GuiPetrifiedChest(IInventory upperInventory, IInventory lowerInventory) {
		super(new ContainerPetrifiedWoodChest(upperInventory, lowerInventory));
		upperChestInventory = upperInventory;
		lowerChestInventory = lowerInventory;
		allowUserInput = false;
		inventoryRows = lowerInventory.getSizeInventory() / 9;
		ySize = 112 + inventoryRows * 18;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRendererObj.drawString(lowerChestInventory.hasCustomInventoryName() ? lowerChestInventory.getInventoryName() : StatCollector.translateToLocal(lowerChestInventory.getInventoryName()), 8, 6, Utils.getColour(255, 255, 255));
		fontRendererObj.drawString(upperChestInventory.hasCustomInventoryName() ? upperChestInventory.getInventoryName() : StatCollector.translateToLocal(upperChestInventory.getInventoryName()), 8, ySize - 96 + 2, Utils.getColour(255, 255, 255));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(TEXTURE);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, inventoryRows * 18 + 17);
		drawTexturedModalRect(guiLeft, guiTop + inventoryRows * 18 + 17, 0, 124 + 36, xSize, 96);
	}
}