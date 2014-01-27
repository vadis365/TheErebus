package erebus.client.gui;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.inventory.ContainerColossalCrate;
import erebus.network.PacketHandler;
import erebus.tileentity.TileEntityBambooCrate;

@SideOnly(Side.CLIENT)
public class GuiColossalCrate extends GuiContainer {

	public static final boolean hasInventoryTweaks = Loader.isModLoaded("inventorytweaks");
	private static final ResourceLocation GUI_BAMBOO_CRATE = new ResourceLocation("erebus:textures/gui/container/collosalcrate.png");

	/*
	 * public TileEntityBambooCrate crate1; public TileEntityBambooCrate crate2;
	 * public TileEntityBambooCrate crate3; public TileEntityBambooCrate crate4;
	 * public TileEntityBambooCrate crate5; public TileEntityBambooCrate crate6;
	 * public TileEntityBambooCrate crate7; public TileEntityBambooCrate crate8;
	 */

	public GuiColossalCrate(InventoryPlayer playerInventory, List<TileEntityBambooCrate> list) {
		super(new ContainerColossalCrate(playerInventory, list));
		/*
		 * crate1 = list.get(0); crate2 = list.get(1); crate3 = list.get(2);
		 * crate4 = list.get(3); crate5 = list.get(4); crate6 = list.get(5);
		 * crate7 = list.get(6); crate8 = list.get(7);
		 */
		allowUserInput = false;
		ySize = 220;
		xSize = 230;
	}

	@Override
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
					mc.getNetHandler().addToSendQueue(PacketHandler.buildPacket(1, newPage));
					((ContainerColossalCrate) inventorySlots).changePage(newPage);
					break;
				case 1:
					newPage = getPageNumber() + 1;
					mc.getNetHandler().addToSendQueue(PacketHandler.buildPacket(1, newPage));
					((ContainerColossalCrate) inventorySlots).changePage(newPage);
					break;
			}
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		fontRenderer.drawString(I18n.getString("container.colossalCrate"), 28, 6, 4210752);
		String str = getPageNumber() + "/3";
		fontRenderer.drawString(str, xSize / 2 - fontRenderer.getStringWidth(str) / 2, 6, 4210752);
		fontRenderer.drawString(I18n.getString("container.inventory"), 32, ySize - 96 + 3, 4210752);
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
