package erebus.inventory.client;

import erebus.Erebus;
import erebus.block.entity.BlenderBlockEntity;
import erebus.inventory.client.elements.TankGauge;
import erebus.inventory.server.BlenderMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTextTooltip;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("AccessStaticViaInstance")
public class BlenderScreen extends ErebusScreen<BlenderMenu> {

    private final BlenderMenu container;
    public static final Rectangle[] tankPositions = new Rectangle[] {
            new Rectangle(8, 6, 9, 73),
            new Rectangle(25, 6, 8, 73),
            new Rectangle(142, 6, 9, 73),
            new Rectangle(159, 6, 9, 73)
    };

    private final TankGauge[] tankGauges = new TankGauge[tankPositions.length];

    public BlenderScreen(BlenderMenu container, Inventory inventory, Component title) {
        super(container, inventory, title, Erebus.prefix("textures/gui/container/smoothie_maker.png"));
        this.container = container;
    }

    @Override
    protected void init() {
        super.init();
        clearWidgets();
        for(int c = 0; c < tankPositions.length; c++) {
            tankGauges[c] = new TankGauge(leftPos + tankPositions[c].x, topPos + tankPositions[c].y, tankPositions[c].width, tankPositions[c].height, container.blender.tanks, c, FluidType.BUCKET_VOLUME * 8);
            addRenderableWidget(tankGauges[c]);
        }
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);

        for(Rectangle tank : tankPositions) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + tank.x, topPos + 3 + tank.y, 176, 41, tank.width, tank.height, 256, 256);
        }

        BlenderBlockEntity blender = container.blender;
        if(blender.isBlending()) {
            float currentProgress = blender.getBlendProgress();
            float prevProgress = blender.getPrevBlendProgress();
            int progress = (int) (currentProgress + (currentProgress - prevProgress) * a);
            graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 52, topPos + 26, 176, 0, 73, progress + 1, 256, 256);
        }
    }

    @Override
    protected void extractTooltip(@NotNull GuiGraphicsExtractor graphics, int x, int y) {
        super.extractTooltip(graphics, x, y);

        for(int c = 0; c < tankGauges.length; c++) {
            if(tankGauges[c].isHovered()) {
                renderTankTooltip(graphics, c, x, y);
            }
        }
    }

    private void renderTankTooltip(GuiGraphicsExtractor graphics, int index, int x, int y) {
        List<ClientTooltipComponent> tooltip = new ArrayList<>();
        FluidResource resource = container.blender.tanks.getResource(index);
        int amount = container.blender.tanks.getAmountAsInt(index);
        int capacity = FluidType.BUCKET_VOLUME * 8;
        if (!resource.isEmpty()) {
            tooltip.add(new ClientTextTooltip(resource.toStack(amount).getHoverName().getVisualOrderText()));
            tooltip.add(new ClientTextTooltip(Component.literal(amount + "/" + capacity).getVisualOrderText()));
        } else {
            tooltip.add(new ClientTextTooltip(Component.literal("Empty").getVisualOrderText()));
            tooltip.add(new ClientTextTooltip(Component.literal("0/" + capacity).getVisualOrderText()));
        }
        graphics.tooltip(font, tooltip, x, y, DefaultTooltipPositioner.INSTANCE, null);
    }
}
