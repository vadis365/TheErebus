package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.client.elements.GuiInvisibleButton;
import erebus.inventory.server.ColossalCrateMenu;
import erebus.network.server.ColossalCratePage;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

import javax.annotation.Nonnull;

public class ColossalCrateScreen extends ErebusScreen<ColossalCrateMenu> {

	private static final Identifier GUI_BAMBOO_CRATE = Erebus.prefix("textures/gui/container/bamboo_collosal_crate.png");

	public ColossalCrateScreen(ColossalCrateMenu handler, Inventory playerInventory, Component text) {
		super(handler, playerInventory, text, GUI_BAMBOO_CRATE);
	}

	@Override
	protected void init() {
		super.init();
		addRenderableWidget(new GuiInvisibleButton(leftPos + 7, topPos + 4, 17, 11, Component.literal(""), (button) -> {
					net.minecraft.client.Minecraft.getInstance().getConnection().send(new ColossalCratePage(getPageNumber() - 1));
					getMenu().changePage(getPageNumber() - 1);
		}));

		addRenderableWidget(new GuiInvisibleButton(leftPos + 205, topPos + 4, 17, 11, Component.literal(""), (button) -> {
					net.minecraft.client.Minecraft.getInstance().getConnection().send(new ColossalCratePage(getPageNumber() + 1));
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
    protected void extractTooltip(@Nonnull GuiGraphicsExtractor graphics, int x, int y) {
        super.extractTooltip(graphics, x, y);
    }

    @Override
    protected void extractLabels(@Nonnull GuiGraphicsExtractor graphics, int x, int y) {
    	super.extractLabels(graphics, x, y);
    	graphics.text(font, Component.translatable("erebus.container.colossal_crate"), 28, 6, 4210752);
		String str = getPageNumber() + "/3";
		graphics.text(font, str, imageWidth / 2 - font.width(str) / 2, 6, 4210752);
		graphics.text(font, Component.translatable("container.inventory"), 32, imageHeight - 96 + 3, 4210752);
	}

    @Override
    public void extractBackground(@Nonnull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
    	super.extractBackground(graphics, mouseX, mouseY, a);
    	graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_BAMBOO_CRATE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
	}
}
