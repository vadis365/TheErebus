package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.client.elements.GuiInvisibleButton;
import erebus.inventory.server.ColossalCrateMenu;
import erebus.network.server.ColossalCratePage;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nonnull;

public class ColossalCrateScreen extends AbstractContainerScreen<ColossalCrateMenu> {

	private static final Identifier GUI_BAMBOO_CRATE = Erebus.prefix("textures/gui/container/bamboo_collosal_crate.png");

	public ColossalCrateScreen(ColossalCrateMenu handler, Inventory playerInventory, Component text) {
		super(handler, playerInventory, text);
		imageHeight = 220;
		imageWidth = 230;
	}

	@Override
	public void init() {
		super.init();
		addRenderableWidget(new GuiInvisibleButton(leftPos + 7, topPos + 4, 17, 11, Component.literal(""), (button) -> {
					PacketDistributor.sendToServer(new ColossalCratePage(getPageNumber() - 1));
					getMenu().changePage(getPageNumber() - 1);
		}));

		addRenderableWidget(new GuiInvisibleButton(leftPos + 205, topPos + 4, 17, 11, Component.literal(""), (button) -> {
					PacketDistributor.sendToServer(new ColossalCratePage(getPageNumber() + 1));
					getMenu().changePage(getPageNumber() + 1);
				}));
	}

    @Override
    public void onClose() {
    	super.onClose();
	}

	public int getPageNumber() {
		return getMenu().page;
	}

    @Override
    protected void renderTooltip(@Nonnull GuiGraphics gg, int x, int y) {
        super.renderTooltip(gg, x, y);
    }

    @Override
    protected void renderLabels(@Nonnull GuiGraphics gg, int x, int y) {
    	gg.drawString(font, Component.translatable("erebus.container.colossal_crate"), 28, 6, 4210752, false);
		String str = getPageNumber() + "/3";
		gg.drawString(font, str, getXSize() / 2 - font.width(str) / 2, 6, 4210752, false);
		gg.drawString(font, Component.translatable("container.inventory"), 32, imageHeight - 96 + 3, 4210752, false);
	}

    @Override
    protected void renderBg(@Nonnull GuiGraphics gg, float partialTicks, int mouseX, int mouseY) {
    	gg.blit(GUI_BAMBOO_CRATE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
	}
}
