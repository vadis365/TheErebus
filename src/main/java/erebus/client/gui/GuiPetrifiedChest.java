package erebus.client.gui;

import erebus.core.helper.Utils;
import erebus.inventory.ContainerPetrifiedWoodChest;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiPetrifiedChest extends GuiErebus {

	private static final ResourceLocation TEXTURE = new ResourceLocation("erebus:textures/gui/container/petrified_container_big.png");
	private final IInventory upperChestInventory, lowerChestInventory;

	private final int inventoryRows;

	public GuiPetrifiedChest(IInventory upperInventory, IInventory lowerInventory, EntityPlayer player) {
		super(new ContainerPetrifiedWoodChest(upperInventory, lowerInventory, player));
		upperChestInventory = upperInventory;
		lowerChestInventory = lowerInventory;
		allowUserInput = false;
		inventoryRows = lowerInventory.getSizeInventory() / 9;
		ySize = 112 + inventoryRows * 18;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 9, 7, Utils.getColour(0, 0, 0));
		fontRenderer.drawString(this.upperChestInventory.getDisplayName().getUnformattedText(), 9, this.ySize - 96 + 5, Utils.getColour(0, 0, 0));
		
		fontRenderer.drawString(this.lowerChestInventory.getDisplayName().getUnformattedText(), 8, 6, Utils.getColour(255, 255, 255));
		fontRenderer.drawString(this.upperChestInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 4, Utils.getColour(255, 255, 255));
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTickTime, int mouseX, int mouseY) {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(TEXTURE);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, inventoryRows * 18 + 17);
		drawTexturedModalRect(guiLeft, guiTop + inventoryRows * 18 + 17, 0, 124 + 36, xSize, 96);
	}
}