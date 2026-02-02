package erebus.inventory.client;

import erebus.Erebus;
import erebus.block.entity.BlenderBlockEntity;
import erebus.inventory.client.elements.TankGauge;
import erebus.inventory.server.BlenderMenu;
import net.minecraft.client.gui.GuiGraphics;
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
        super(container, inventory, title, Erebus.prefix("textures/gui/container/smoothie_maker.png"), 176, 166);
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
    protected void renderBg(GuiGraphics gg, float partialTicks, int mX, int mY) {
        super.renderBg(gg, partialTicks, mX, mY);
        gg.blit(net.minecraft.client.renderer.RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight, 256, 256);

        for(Rectangle tank : tankPositions) {
            gg.blit(net.minecraft.client.renderer.RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + tank.x, topPos + 3 + tank.y, 176, 41, tank.width, tank.height, 256, 256);
        }

        BlenderBlockEntity blender = container.blender;
        if(blender.isBlending()) {
            float currentProgress = blender.getBlendProgress();
            float prevProgress = blender.getPrevBlendProgress();
            int progress = (int) (currentProgress + (currentProgress - prevProgress) * partialTicks);
            gg.blit(net.minecraft.client.renderer.RenderPipelines.GUI_TEXTURED, TEXTURE, leftPos + 52, topPos + 26, 176, 0, 73, progress + 1, 256, 256);
        }
    }

    @Override
    protected void renderTooltip(@NotNull GuiGraphics gg, int x, int y) {
        super.renderTooltip(gg, x, y);

        for(int c = 0; c < tankGauges.length; c++) {
            if(tankGauges[c].isHovered()) {
                renderTankTooltip(gg, c, x, y);
            }
        }
    }

    private void renderTankTooltip(GuiGraphics gg, int index, int x, int y) {
        List<Component> tooltip = new ArrayList<>();
        FluidResource resource = container.blender.tanks.getResource(index);
        int amount = container.blender.tanks.getAmountAsInt(index);
        int capacity = FluidType.BUCKET_VOLUME * 8;
        if (!resource.isEmpty()) {
            tooltip.add(resource.toStack(amount).getHoverName());
            tooltip.add(Component.literal(amount + "/" + capacity));
        } else {
            tooltip.add(Component.literal("Empty"));
            tooltip.add(Component.literal("0/" + capacity));
        }
        gg.renderTooltip(font, tooltip.stream().map(Component::getVisualOrderText).map(net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent::create).toList(), x, y, net.minecraft.client.gui.screens.inventory.tooltip.DefaultTooltipPositioner.INSTANCE, null);
    }
}
