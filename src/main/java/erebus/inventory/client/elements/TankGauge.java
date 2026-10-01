package erebus.inventory.client.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.neoforged.neoforge.client.fluid.FluidTintSource;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class TankGauge extends AbstractWidget {
    private final ResourceHandler<FluidResource> tank;
    private final int tankIndex;
    private final int capacity;

    public TankGauge(int pX, int pY, int pWidth, int pHeight, ResourceHandler<FluidResource> tankIn, int tankIndex, int capacity) {
        super(pX, pY, pWidth, pHeight, Component.empty());
        tank = tankIn;
        this.tankIndex = tankIndex;
        this.capacity = capacity;
    }

    @Override
    protected void extractWidgetRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTicks) {
        if (tank == null || tank.getAmountAsInt(tankIndex) <= 0) return;
        FluidResource resource = tank.getResource(tankIndex);
        if (resource.isEmpty()) return;
        FluidStack stack = resource.toStack(tank.getAmountAsInt(tankIndex));
        FluidModel model = Minecraft.getInstance().getModelManager().getFluidStateModelSet().get(stack.getFluid().defaultFluidState());
        FluidTintSource tintSource = model.fluidTintSource();
        int color = tintSource == null ? -1 : ARGB.opaque(tintSource.colorAsStack(stack));
        int filled = Math.clamp(Math.round(getFluidLevel() * height), 0, height);
        graphics.enableScissor(getX(), getY() + height - filled, getX() + width, getY() + height);
        for (int y = getY() + height - 16; y > getY() + height - filled - 16; y -= 16) {
            for (int x = getX(); x < getX() + width; x += 16) {
                graphics.blitSprite(RenderPipelines.GUI_TEXTURED, model.stillMaterial().sprite(), x, y, 16, 16, color);
            }
        }
        graphics.disableScissor();
    }

    public float getFluidLevel() {
        return tank != null ? ((float) tank.getAmountAsInt(tankIndex) / capacity) : 0.0f;
    }

    @Override
    protected void updateWidgetNarration(@Nonnull NarrationElementOutput narrationElementOutput) {

    }
}
