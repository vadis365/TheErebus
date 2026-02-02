package erebus.inventory.client.elements;

import erebus.Erebus;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;

import javax.annotation.Nonnull;

public class TankGauge extends AbstractWidget {
    private final ResourceHandler<FluidResource> tank;
    private final int tankIndex;
    private final int capacity;
    private Fluid oldFluid;
    private TextureAtlasSprite sprite;
    public TankGauge(int pX, int pY, int pWidth, int pHeight, ResourceHandler<FluidResource> tankIn, int tankIndex, int capacity) {
        super(pX, pY, pWidth, pHeight, Component.empty());
        tank = tankIn;
        this.tankIndex = tankIndex;
        this.capacity = capacity;
    }

    @Override
    protected void renderWidget(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        float fluidLevel = getFluidLevel();

        if (tank == null)
            return;

        FluidResource resource = tank.getResource(tankIndex);
        FluidStack stack = resource.toStack(tank.getAmountAsInt(tankIndex));

        if (fluidLevel > 0 && !stack.isEmpty()) {
            IClientFluidTypeExtensions fluidTypeExtension = IClientFluidTypeExtensions.of(stack.getFluid());
            int color = fluidTypeExtension.getTintColor(stack);
            Identifier stillTexture = fluidTypeExtension.getStillTexture();

            if (this.sprite == null || this.oldFluid != stack.getFluid()) {
                this.oldFluid = stack.getFluid();

                AbstractTexture texture = Minecraft.getInstance().getTextureManager().getTexture(Sheets.BLOCKS_MAPPER.sheet());
                if (texture instanceof TextureAtlas) {
                    TextureAtlasSprite sprite = ((TextureAtlas) texture).getSprite(stillTexture);
                    if (sprite != null) {
                        this.sprite = sprite;
                    }
                }
            }

            if (this.sprite != null) {
                double tankLevel = fluidLevel * height;
                guiGraphics.blit(RenderPipelines.ANIMATE_SPRITE_BLIT, Erebus.prefix("sprites/umberfurnace/tank"), getX(), (int) (getY() + height - tankLevel), 0, 0, width, height, 18, 65, ARGB.white(0));
            }
        }
    }

    public float getFluidLevel() {
        return tank != null ? ((float) tank.getAmountAsInt(tankIndex) / capacity) : 0.0f;
    }

    @Override
    protected void updateWidgetNarration(@Nonnull NarrationElementOutput narrationElementOutput) {

    }
}
