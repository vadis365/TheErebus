package erebus.inventory.client.elements;

import erebus.Erebus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.block.FluidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.client.fluid.FluidTintSource;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nonnull;

public class TankGauge extends AbstractWidget {
    private final Identifier texture = Erebus.prefix("sprites/umberfurnace/tank");
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
        float fluidLevel = getFluidLevel();
        if(tank == null) return;

        FluidResource resource = tank.getResource(tankIndex);
        FluidStack stack = resource.toStack(tank.getAmountAsInt(tankIndex));
        FluidState fluid = stack.getFluid().defaultFluidState();
        FluidModel model = Minecraft.getInstance().getModelManager().getFluidStateModelSet().get(fluid);
        FluidTintSource tintSource = model.fluidTintSource();
        if (tintSource != null) {
            tintSource.color(fluid);
        }

        if(fluidLevel > 0 && !stack.isEmpty()) {
            double tankLevel = fluidLevel * height;
            graphics.blit(RenderPipelines.ANIMATE_SPRITE_BLIT, texture, getX(), (int) (getY() + height - tankLevel), 0, 0, width, height, 18, 65, ARGB.white(0));
        }
    }

    public float getFluidLevel() {
        return tank != null ? ((float) tank.getAmountAsInt(tankIndex) / capacity) : 0.0f;
    }

    @Override
    protected void updateWidgetNarration(@Nonnull NarrationElementOutput narrationElementOutput) {

    }
}
