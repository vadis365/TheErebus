package erebus.inventory.client.elements;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nonnull;

public class TankGauge extends AbstractWidget {
    private final FluidTank tank;
    private Fluid oldFluid;
    private TextureAtlasSprite sprite;
    public TankGauge(int pX, int pY, int pWidth, int pHeight, FluidTank tankIn) {
        super(pX, pY, pWidth, pHeight, Component.empty());
        tank = tankIn;
    }

    @Override
    protected void renderWidget(@Nonnull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        // Byscos fix from AA

        float fluidLevel = getFluidLevel();

        if (tank == null)
            return;

        FluidStack stack = tank.getFluid();

        if (fluidLevel > 0) {
            IClientFluidTypeExtensions fluidTypeExtension = IClientFluidTypeExtensions.of(stack.getFluid());
            int color = fluidTypeExtension.getTintColor(stack);
            float red = (float)(FastColor.ARGB32.red(color) / 255.0);
            float green = (float)(FastColor.ARGB32.green(color) / 255.0);
            float blue = (float)(FastColor.ARGB32.blue(color) / 255.0);
            float alpha = (float)(FastColor.ARGB32.alpha(color) / 255.0);
            ResourceLocation stillTexture = fluidTypeExtension.getStillTexture();

            if (this.sprite == null || this.oldFluid != stack.getFluid()) {
                this.oldFluid = stack.getFluid();

                AbstractTexture texture = Minecraft.getInstance().getTextureManager().getTexture(InventoryMenu.BLOCK_ATLAS);
                if (texture instanceof TextureAtlas) {
                    TextureAtlasSprite sprite = ((TextureAtlas) texture).getSprite(stillTexture);
                    if (sprite != null) {
                        this.sprite = sprite;
                    }
                }
            }

            if (this.sprite != null) {
                float minU = sprite.getU0();
                float maxU = sprite.getU1();
                float minV = sprite.getV0();
                float maxV = sprite.getV1();
                float deltaV = maxV - minV;

                double tankLevel = fluidLevel * height;

                RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
                RenderSystem.setShaderColor(red, green, blue, alpha);
                RenderSystem.enableBlend();
                int count = 1 + ((int) Math.ceil(tankLevel)) / 16;
                for (int i = 0; i < count; i++) {
                    float subHeight = (float) Math.min(16.0f, tankLevel - (16.0f * i));
                    double offsetY = height - 16.0 * i - subHeight;
                    drawQuad(getX(), (float)(getY() + offsetY), width, subHeight, minU, (float) (maxV - deltaV * (subHeight / 16.0)), maxU, maxV);
                }
                RenderSystem.disableBlend();
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }

    private void drawQuad(float x, float y, float width, float height, float minU, float minV, float maxU, float maxV) {
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder buffer = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        buffer.addVertex(x, y + height, 0).setUv(minU, maxV);
        buffer.addVertex(x + width, y + height, 0).setUv(maxU, maxV);
        buffer.addVertex(x + width, y, 0).setUv(maxU, minV);
        buffer.addVertex(x, y, 0).setUv(minU, minV);

        //buffer.build();
        BufferUploader.drawWithShader(buffer.buildOrThrow());
    }

    public float getFluidLevel() {
        return tank != null ? ((float) tank.getFluid().getAmount() / tank.getCapacity()) : 0.0f;
    }

    @Override
    protected void updateWidgetNarration(@Nonnull NarrationElementOutput narrationElementOutput) {

    }
}
