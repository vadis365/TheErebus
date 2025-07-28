package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.Erebus;
import erebus.block.entity.GlowingJarBlockEntity;
import erebus.client.render.block.model.GlowingJarModel;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

public class GlowingJarRenderer implements BlockEntityRenderer<GlowingJarBlockEntity> {

    private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/glowing_jar.png");
    private final ResourceLocation WISP = Erebus.prefix("textures/particle/wisp.png");
    private final GlowingJarModel model;

    public GlowingJarRenderer(BlockEntityRendererProvider.Context context) {
        model = new GlowingJarModel(context.bakeLayer(ModBlockEntityRendering.GLOWING_JAR));
    }

    @Override
    public void render(@NotNull GlowingJarBlockEntity jar, float partialTick, @NotNull PoseStack pose, @NotNull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        VertexConsumer consumer = buffer.getBuffer(model.renderType(TEXTURE));
        pose.pushPose();
        pose.translate(0.5F, 0.75F, 0.5F);
        pose.scale(0.7125F, -1.069F, -0.7125F);
        model.renderToBuffer(pose, consumer, combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
        pose.popPose();
    }

    private void setGLColorFromInt(int color) {
        float red = (color >> 16 & 0xFF) / 255.0F;
        float green = (color >> 8 & 0xFF) / 255.0F;
        float blue = (color & 0xFF) / 255.0F;
        GL11.glColor4f(red, green, blue, 1F);
    }

    private void renderQuads(BufferBuilder buffer, float xMax, float xMin, float yMin, float height, float zMin, float zMax, TextureAtlasSprite sprite) {
        float uMin = sprite.getU0();
        float uMax = sprite.getU1();
        float vMin = sprite.getV0();
        float vMax = sprite.getV1();
        final double vHeight = vMax - vMin;

        // north
        addVertexWithUV(buffer, xMax, yMin, zMin, uMax, vMin);
        addVertexWithUV(buffer, xMin, yMin, zMin, uMin, vMin);
        addVertexWithUV(buffer, xMin, height, zMin, uMin, (float) (vMin + (vHeight * height)));
        addVertexWithUV(buffer, xMax, height, zMin, uMax, (float) (vMin + (vHeight * height)));
        // south
        addVertexWithUV(buffer, xMax, yMin, zMax, uMin, vMin);
        addVertexWithUV(buffer, xMax, height, zMax, uMin, (float) (vMin + (vHeight * height)));
        addVertexWithUV(buffer, xMin, height, zMax, uMax, (float) (vMin + (vHeight * height)));
        addVertexWithUV(buffer, xMin, yMin, zMax, uMax, vMin);
    }

    private void addVertexWithUV(BufferBuilder buffer, float x, float y, float z, float u, float v) {
        buffer.addVertex(x, y, z).setUv(u, v);
    }
}
