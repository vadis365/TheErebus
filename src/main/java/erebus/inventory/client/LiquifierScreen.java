package erebus.inventory.client;

import erebus.Erebus;
import erebus.block.entity.LiquifierBlockEntity;
import erebus.inventory.client.elements.TankGauge;
import erebus.inventory.server.LiquifierMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTextTooltip;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class LiquifierScreen extends ErebusScreen<LiquifierMenu> {

	protected final LiquifierMenu container;
	private final LiquifierBlockEntity liquifier;
	private TankGauge tankGauge;

	public LiquifierScreen(LiquifierMenu container, Inventory playerInventory, Component name) {
		super(container, playerInventory, name, Erebus.prefix("textures/gui/container/liquifier.png"));
		this.container = container;
		this.liquifier = this.container.liquifier;
	}
	
	@Override
	public void init() {
		super.init();
		clearWidgets();
		tankGauge = new TankGauge(leftPos + 108, topPos + 24, 30, 39, liquifier.tank, 0, net.neoforged.neoforge.fluids.FluidType.BUCKET_VOLUME * 8);
		addRenderableWidget(tankGauge);
	}

	@Override
	protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
		super.extractLabels(graphics, xm, ym);
		graphics.text(font, title, 8, 6, 16777215);
		graphics.text(font, Component.translatable("container.inventory"), 8, this.imageHeight - 94, 16777215);
	}

	@Override
	public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
		super.extractBackground(graphics, mouseX, mouseY, a);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);
        
		int operationProgress = liquifier.getOperationProgressScaled(22);
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 69, topPos + 35, 176, 0, operationProgress, 16, 256, 256);
		
		graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 105, topPos + 23, 176, 16, 36, 41, 256, 256);
	}

	@Override
	protected void extractTooltip(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
		super.extractTooltip(graphics, mouseX, mouseY);
		if(tankGauge.isHovered()) {
			List<ClientTooltipComponent> tooltip = new ArrayList<>();
			FluidResource resource = liquifier.tank.getResource(0);
			int amount = liquifier.tank.getAmountAsInt(0);
			int capacity = net.neoforged.neoforge.fluids.FluidType.BUCKET_VOLUME * 8;
			if (!resource.isEmpty()) {
				tooltip.add(new ClientTextTooltip(resource.toStack(amount).getHoverName().getVisualOrderText()));
				tooltip.add(new ClientTextTooltip(Component.literal("%d/%d".formatted(amount, capacity)).getVisualOrderText()));
			} else {
				tooltip.add(new ClientTextTooltip(Component.literal("Empty").getVisualOrderText()));
				tooltip.add(new ClientTextTooltip(Component.literal("0/%d".formatted(capacity)).getVisualOrderText()));
			}

			graphics.tooltip(font, tooltip, mouseX, mouseY, DefaultTooltipPositioner.INSTANCE, null);
		}
	}
}
