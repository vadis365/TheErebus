package erebus.inventory.client;

import erebus.Erebus;
import erebus.inventory.client.elements.TankGauge;
import erebus.inventory.server.LiquifierMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTextTooltip;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public class LiquifierScreen extends ErebusScreen<LiquifierMenu> {

    protected final LiquifierMenu container;
    private TankGauge tankGauge;

    public LiquifierScreen(LiquifierMenu container, Inventory playerInventory, Component name) {
        super(container, playerInventory, name, Erebus.prefix("textures/gui/container/liquifier.png"));
        this.container = container;
    }

    @Override
    public void init() {
        super.init();
        clearWidgets();
        tankGauge = new TankGauge(leftPos + 108, topPos + 24, 30, 39, container.tank, 0, FluidType.BUCKET_VOLUME * 8);
        addRenderableWidget(tankGauge);
    }

    @Override
    protected void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym) {
        graphics.text(font, title, 8, 6, 0xFFFFFFFF);
        graphics.text(font, Component.translatable("container.inventory"), 8, this.imageHeight - 94, 0xFFFFFFFF);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);

        int operationProgress = container.getOperationProgressScaled(22);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 69, topPos + 35, 176, 0, operationProgress, 16, 256, 256);

        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 105, topPos + 23, 176, 16, 36, 41, 256, 256);
    }

    @Override
    protected void extractTooltip(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        super.extractTooltip(graphics, mouseX, mouseY);
        if (tankGauge.isHovered()) {
            List<ClientTooltipComponent> tooltip = new ArrayList<>();
            FluidResource resource = container.tank.getResource(0);
            int amount = container.tank.getAmountAsInt(0);
            int capacity = FluidType.BUCKET_VOLUME * 8;
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
