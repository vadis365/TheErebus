package erebus.inventory.client;

import javax.annotation.Nonnull;

import erebus.Erebus;
import erebus.inventory.server.ColossalCrateMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ColossalCrateScreen extends AbstractContainerScreen<ColossalCrateMenu> {

	//public static final boolean hasInventoryTweaks = Loader.isModLoaded("inventorytweaks");
	private static final ResourceLocation GUI_BAMBOO_CRATE = Erebus.prefix("textures/gui/container/bamboo_collosal_crate.png");

	public ColossalCrateScreen(ColossalCrateMenu handler, Inventory playerInventory, Component text) {
		super(handler, playerInventory, text);
		//allowUserInput = false;
		imageHeight = 220;
		imageWidth = 230;
	}

	@Override
	public void init() {
		super.init();
//		Keyboard.enableRepeatEvents(true);
//		buttonList.clear();
//		buttonList.add(new GuiInvisibleButton(0, guiLeft + 7, guiTop + 4, 17, 11));
//		buttonList.add(new GuiInvisibleButton(1, guiLeft + 205, guiTop + 4, 17, 11));
		//This never worked!
		//buttonList.add(new GuiInvisibleButton(1, guiLeft + 205 - (hasInventoryTweaks ? 50 : 0), guiTop + 4, 17, 11));
	}

    @Override
    public void onClose() {
    	super.onClose();
	//	Keyboard.enableRepeatEvents(false);
	}

	public int getPageNumber() {
		return ((ColossalCrateMenu)getMenu()).page;
	}
/*
	@Override
	protected void actionPerformed(GuiButton button) {
		if (button.enabled) {
			int newPage = 1;
			switch (button.id) {
				case 0:
					newPage = getPageNumber() - 1;
					Erebus.NETWORK_WRAPPER.sendToServer(new ColossalCratePage(newPage));
					((ContainerColossalCrate) inventorySlots).changePage(newPage);
					break;
				case 1:
					newPage = getPageNumber() + 1;
					Erebus.NETWORK_WRAPPER.sendToServer(new ColossalCratePage(newPage));
					((ContainerColossalCrate) inventorySlots).changePage(newPage);
					break;
			}
		}
	}
*/
    @Override
    protected void renderTooltip(@Nonnull GuiGraphics gg, int x, int y) {
        super.renderTooltip(gg, x, y);
    }

    @Override
    protected void renderLabels(@Nonnull GuiGraphics gg, int x, int y) {
    	gg.drawString(font, Component.translatable("erebus.container.colossalCrate"), 28, 6, 4210752);
		String str = getPageNumber() + "/3";
		gg.drawCenteredString(font, str, getXSize() / 2, 6, 4210752);
		gg.drawString(font, Component.translatable("container.inventory"), 32, imageHeight - 96 + 3, 4210752);
	}

    @Override
    protected void renderBg(@Nonnull GuiGraphics gg, float partialTicks, int mouseX, int mouseY) {
    	gg.blit(GUI_BAMBOO_CRATE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
	}
}