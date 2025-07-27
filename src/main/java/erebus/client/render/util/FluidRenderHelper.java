package erebus.client.render.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;

/**
 * Helper class for rendering fluids in containers.
 */
@OnlyIn(Dist.CLIENT)
public class FluidRenderHelper {

    /**
     * Renders a fluid in a container.
     *
     * @param fluidStack    The fluid stack to render
     * @param matrixStack   The pose stack for transformations
     * @param bufferIn      The buffer source
     * @param xMin          The minimum x coordinate
     * @param xMax          The maximum x coordinate
     * @param yMin          The minimum y coordinate
     * @param height        The height of the fluid
     * @param zMin          The minimum z coordinate
     * @param zMax          The maximum z coordinate
     * @param combinedLight The combined light value
     */
    public static void renderFluid(FluidStack fluidStack, PoseStack matrixStack, MultiBufferSource bufferIn,
                                  float xMin, float xMax, float yMin, float height, float zMin, float zMax,
                                  int combinedLight) {
        if (fluidStack.isEmpty()) {
            return;
        }

        var fluidExtensions = IClientFluidTypeExtensions.of(fluidStack.getFluid());

        TextureAtlasSprite fluidStillSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS)
                .apply(fluidExtensions.getStillTexture());
        VertexConsumer buffer = bufferIn.getBuffer(RenderType.CUTOUT);
        int fluidColor = fluidExtensions.getTintColor();
        matrixStack.pushPose();
        float alpha = 1F;
        float red = (fluidColor >> 16 & 0xFF) / 255.0F;
        float green = (fluidColor >> 8 & 0xFF) / 255.0F;
        float blue = (fluidColor & 0xFF) / 255.0F;
        renderCuboid(buffer, matrixStack, xMax, xMin, yMin, height, zMin, zMax, fluidStillSprite, red, green, blue, alpha, combinedLight);
        matrixStack.popPose();
    }

    /**
     * Renders a cuboid with the given parameters.
     *
     * @param buffer            The vertex consumer
     * @param matrixStack       The pose stack for transformations
     * @param xMax              The maximum x coordinate
     * @param xMin              The minimum x coordinate
     * @param yMin              The minimum y coordinate
     * @param height            The height of the fluid
     * @param zMin              The minimum z coordinate
     * @param zMax              The maximum z coordinate
     * @param textureAtlasSprite The texture atlas sprite
     * @param red               The red color component
     * @param green             The green color component
     * @param blue              The blue color component
     * @param alpha             The alpha color component
     * @param combinedLight     The combined light value
     */
    private static void renderCuboid(VertexConsumer buffer, PoseStack matrixStack, float xMax, float xMin, float yMin, float height, float zMin, float zMax, TextureAtlasSprite textureAtlasSprite, float red, float green, float blue, float alpha, int combinedLight) {
        float uMin = textureAtlasSprite.getU0();
        float uMax = textureAtlasSprite.getU1();
        float vMin = textureAtlasSprite.getV0();
        float vMax = textureAtlasSprite.getV1();
        float vHeight = vMax - vMin;

        // Define face vertices and normals
        // Format: x, y, z, u, v, normalX, normalY, normalZ
        
        // Top face (y = height)
        renderFace(buffer, matrixStack, 
            new float[][] {
                {xMax, height, zMax, uMax, vMin, 0, 1, 0},
                {xMax, height, zMin, uMin, vMin, 0, 1, 0},
                {xMin, height, zMin, uMin, vMax, 0, 1, 0},
                {xMin, height, zMax, uMax, vMax, 0, 1, 0}
            }, 
            red, green, blue, alpha, combinedLight);

        // North face (z = zMin)
        renderFace(buffer, matrixStack, 
            new float[][] {
                {xMax, yMin, zMin, uMax, vMin, 0, 0, -1},
                {xMin, yMin, zMin, uMin, vMin, 0, 0, -1},
                {xMin, height, zMin, uMin, vMin + (vHeight * height), 0, 0, -1},
                {xMax, height, zMin, uMax, vMin + (vHeight * height), 0, 0, -1}
            }, 
            red, green, blue, alpha, combinedLight);

        // South face (z = zMax)
        renderFace(buffer, matrixStack, 
            new float[][] {
                {xMax, yMin, zMax, uMin, vMin, 0, 0, 1},
                {xMax, height, zMax, uMin, vMin + (vHeight * height), 0, 0, 1},
                {xMin, height, zMax, uMax, vMin + (vHeight * height), 0, 0, 1},
                {xMin, yMin, zMax, uMax, vMin, 0, 0, 1}
            }, 
            red, green, blue, alpha, combinedLight);

        // East face (x = xMax)
        renderFace(buffer, matrixStack, 
            new float[][] {
                {xMax, yMin, zMin, uMin, vMin, 1, 0, 0},
                {xMax, height, zMin, uMin, vMin + (vHeight * height), 1, 0, 0},
                {xMax, height, zMax, uMax, vMin + (vHeight * height), 1, 0, 0},
                {xMax, yMin, zMax, uMax, vMin, 1, 0, 0}
            }, 
            red, green, blue, alpha, combinedLight);

        // West face (x = xMin)
        renderFace(buffer, matrixStack, 
            new float[][] {
                {xMin, yMin, zMax, uMin, vMin, -1, 0, 0},
                {xMin, height, zMax, uMin, vMin + (vHeight * height), -1, 0, 0},
                {xMin, height, zMin, uMax, vMin + (vHeight * height), -1, 0, 0},
                {xMin, yMin, zMin, uMax, vMin, -1, 0, 0}
            }, 
            red, green, blue, alpha, combinedLight);

        // Bottom face (y = yMin)
        renderFace(buffer, matrixStack, 
            new float[][] {
                {xMax, yMin, zMin, uMax, vMin, 0, -1, 0},
                {xMax, yMin, zMax, uMin, vMin, 0, -1, 0},
                {xMin, yMin, zMax, uMin, vMax, 0, -1, 0},
                {xMin, yMin, zMin, uMax, vMax, 0, -1, 0}
            }, 
            red, green, blue, alpha, combinedLight);
    }

    /**
     * Renders a face with the given vertices.
     * 
     * @param buffer        The vertex consumer
     * @param matrixStack   The pose stack for transformations
     * @param vertices      Array of vertex data [x, y, z, u, v, normalX, normalY, normalZ]
     * @param red           The red color component
     * @param green         The green color component
     * @param blue          The blue color component
     * @param alpha         The alpha color component
     * @param combinedLight The combined light value
     */
    private static void renderFace(VertexConsumer buffer, PoseStack matrixStack, float[][] vertices, 
                                  float red, float green, float blue, float alpha, int combinedLight) {
        for (float[] vertex : vertices) {
            addVertex(buffer, matrixStack, 
                     vertex[0], vertex[1], vertex[2], // x, y, z
                     vertex[3], vertex[4], // u, v
                     vertex[5], vertex[6], vertex[7], // normal
                     red, green, blue, alpha, combinedLight);
        }
    }

    /**
     * Adds a vertex with UV coordinates and normal to the buffer.
     *
     * @param buffer        The vertex consumer
     * @param matrixStack   The pose stack for transformations
     * @param x             The x coordinate
     * @param y             The y coordinate
     * @param z             The z coordinate
     * @param u             The u texture coordinate
     * @param v             The v texture coordinate
     * @param normalX       The x component of the normal vector
     * @param normalY       The y component of the normal vector
     * @param normalZ       The z component of the normal vector
     * @param red           The red color component
     * @param green         The green color component
     * @param blue          The blue color component
     * @param alpha         The alpha color component
     * @param combinedLight The combined light value
     */
    private static void addVertex(VertexConsumer buffer, PoseStack matrixStack, 
                                 float x, float y, float z, float u, float v, 
                                 float normalX, float normalY, float normalZ,
                                 float red, float green, float blue, float alpha, int combinedLight) {
        buffer.addVertex(matrixStack.last().pose(), x / 2f, y, z / 2f)
              .setColor(red, green, blue, alpha)
              .setUv(u, v)
              .setUv2(combinedLight, 240)
              .setNormal(normalX, normalY, normalZ);
    }
}