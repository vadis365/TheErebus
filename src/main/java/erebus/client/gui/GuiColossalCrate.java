package erebus.client.gui;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.gui.elements.GuiInvisibleButton;
import erebus.inventory.ContainerColossalCrate;
import erebus.network.PacketPipeline;
import erebus.network.server.S00ColossalCratePage;
import erebus.tileentity.TileEntityBambooCrate;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

@SideOnly(Side.CLIENT)
public class GuiColossalCrate extends GuiContainer {

	public static final boolean hasInventoryTweaks = Loader.isModLoaded("inventorytweaks");
	private static final ResourceLocation GUI_BAMBOO_CRATE = new ResourceLocation("erebus:textures/gui/container/bambooCollosalCrate.png");

	public GuiColossalCrate(InventoryPlayer playerInventory, List<TileEntityBambooCrate> list) {
		super(new ContainerColossalCrate(playerInventory, list));
		allowUserInput = false;
		ySize = 220;
		xSize = 230;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void initGui() {
		super.initGui();
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		buttonList.add(new GuiInvisibleButton(0, guiLeft + 7, guiTop + 4, 17, 11));
		buttonList.add(new GuiInvisibleButton(1, guiLeft + 205 - (hasInventoryTweaks ? 50 : 0), guiTop + 4, 17, 11));
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}

	public int getPageNumber() {
		return ((ContainerColossalCrate) inventorySlots).page;
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			int newPage = 1;
			switch (button.id) {
				case 0:
					newPage = getPageNumber() - 1;
					PacketPipeline.sendToServer(new S00ColossalCratePage(newPage));
					((ContainerColossalCrate) inventorySlots).changePage(newPage);
					break;
				case 1:
					newPage = getPageNumber() + 1;
					PacketPipeline.sendToServer(new S00ColossalCratePage(newPage));
					((ContainerColossalCrate) inventorySlots).changePage(newPage);
					break;
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRendererObj.drawString(StatCollector.translateToLocal("container.colossalCrate"), 28, 6, 4210752);
		String str = getPageNumber() + "/3";
		fontRendererObj.drawString(str, xSize / 2 - fontRendererObj.getStringWidth(str) / 2, 6, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 32, ySize - 96 + 3, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(GUI_BAMBOO_CRATE);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}