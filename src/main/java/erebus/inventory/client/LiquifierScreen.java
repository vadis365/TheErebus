package erebus.inventory.client;

import erebus.Erebus;
import erebus.block.entity.LiquifierBlockEntity;
import erebus.inventory.client.elements.TankGauge;
import erebus.inventory.server.LiquifierMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class LiquifierScreen extends ErebusScreen<LiquifierMenu> {

	protected final LiquifierMenu container;
	private final LiquifierBlockEntity liquifier;
	private TankGauge tankGauge;

	public LiquifierScreen(LiquifierMenu container, Inventory playerInventory, Component name) {
		super(container, playerInventory, name, Erebus.prefix("textures/gui/container/liquifier.png"));
		this.container = container;
		this.liquifier = this.container.liquifier;
		imageHeight = 166;
		imageWidth = 176;
	}
	
	@Override
	public void init() {
		super.init();
		clearWidgets();
		tankGauge = new TankGauge(leftPos + 108, topPos + 24, 30, 39, liquifier.tank);
		addRenderableWidget(tankGauge);
	}

	@Override
	protected void renderLabels(@Nonnull GuiGraphics gg, int mouseX, int mouseY) {
		gg.drawString(font, title, 8, 6, 16777215, true);
		gg.drawString(font, Component.translatable("container.inventory"), 8, this.imageHeight - 94, 16777215, true);
	}

	@Override
	protected void renderBg(GuiGraphics gg, float partialTicks, int mouseX, int mouseY) {
        gg.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
        
		int operationProgress = liquifier.getOperationProgressScaled(22);
		gg.blit(TEXTURE, leftPos + 69, topPos + 35, 176, 0, operationProgress, 16);
		
		gg.blit(TEXTURE, leftPos + 105, topPos + 23, 176, 16, 36, 41);
	}

	@Override
	protected void renderTooltip(@Nonnull GuiGraphics gg, int x, int y) {
		super.renderTooltip(gg, x, y);
		if (tankGauge.isHovered()) {
			List<Component> tooltip = new ArrayList<>();
			tooltip.add(liquifier.tank.getFluid().getHoverName());
			tooltip.add(Component.literal(liquifier.tank.getFluidAmount() + "/" + liquifier.tank.getCapacity()));
			gg.renderComponentTooltip(font, tooltip, x, y);
		}
	}
}
