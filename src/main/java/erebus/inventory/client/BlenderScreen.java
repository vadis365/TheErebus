package erebus.inventory.client;

import erebus.Erebus;
import erebus.block.entity.BlenderBlockEntity;
import erebus.inventory.client.elements.TankGauge;
import erebus.inventory.server.BlenderMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
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
        imageHeight = 166;
        imageWidth = 176;
    }

    @Override
    protected void init() {
        super.init();
        clearWidgets();
        for(int c = 0; c < tankPositions.length; c++) {
            tankGauges[c] = new TankGauge(leftPos + tankPositions[c].x, topPos + tankPositions[c].y, tankPositions[c].width, tankPositions[c].height, container.blender.tanks[c]);
            addRenderableWidget(tankGauges[c]);
        }
    }

    @Override
    protected void renderBg(GuiGraphics gg, float partialTicks, int mX, int mY) {
        super.renderBg(gg, partialTicks, mX, mY);
        gg.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        for(Rectangle tank : tankPositions) {
            gg.blit(TEXTURE, leftPos + tank.x, topPos + 3 + tank.y, 176, 41, tank.width, tank.height);
        }

        BlenderBlockEntity blender = container.blender;
        if(blender.isBlending()) {
            float currentProgress = blender.getBlendProgress();
            float prevProgress = blender.getPrevBlendProgress();
            int progress = (int) (currentProgress + (currentProgress - prevProgress) * partialTicks);
            gg.blit(TEXTURE, leftPos + 52, topPos + 26, 176, 0, 73, progress + 1);
        }
    }

    @Override
    protected void renderTooltip(@NotNull GuiGraphics gg, int x, int y) {
        super.renderTooltip(gg, x, y);

        for(int c = 0; c < tankGauges.length; c++) {
            if(tankGauges[c].isHovered()) {
                renderTankTooltip(gg, container.blender.tanks[c], x, y);
            }
        }
    }

    private void renderTankTooltip(GuiGraphics gg, FluidTank tank, int x, int y) {
        List<Component> tooltip = new ArrayList<>();
        tooltip.add(tank.getFluid().getHoverName());
        tooltip.add(Component.literal(tank.getFluidAmount() + "/" + tank.getCapacity()));
        gg.renderComponentTooltip(font, tooltip, x, y);
    }
}
