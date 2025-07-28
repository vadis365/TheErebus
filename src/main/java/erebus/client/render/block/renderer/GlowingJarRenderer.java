package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.Erebus;
import erebus.block.entity.GlowingJarBlockEntity;
import erebus.client.render.block.model.GlowingJarModel;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import org.jetbrains.annotations.NotNull;

public class GlowingJarRenderer implements BlockEntityRenderer<GlowingJarBlockEntity> {

    private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/glowing_jar.png");
    private final ResourceLocation WISP = Erebus.prefix("textures/particle/wisp.png");
    private final GlowingJarModel model;

    public GlowingJarRenderer(BlockEntityRendererProvider.Context context) {
        model = new GlowingJarModel(context.bakeLayer(ModBlockEntityRendering.GLOWING_JAR));
    }

    @Override
    public void render(@NotNull GlowingJarBlockEntity jar, float partialTick, @NotNull PoseStack pose, @NotNull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        VertexConsumer vertex = buffer.getBuffer(RenderType.entityTranslucent(WISP));
        pose.pushPose();
        pose.translate(0.5F, jar.particleSize / 4, 0.5F);
        pose.scale(jar.particleSize / 3 + 0.5F, jar.particleSize / 3 + 0.5F, jar.particleSize / 3 + 0.5F);
        TextureAtlas atlas = Minecraft.getInstance().getModelManager().getAtlas(InventoryMenu.BLOCK_ATLAS);
        TextureAtlasSprite sprite = atlas.getSprite(WISP);
        renderQuads(vertex, 0.5F, -0.5F, 0.5F, 1, 0, 0, sprite);
        pose.popPose();

        VertexConsumer consumer = buffer.getBuffer(model.renderType(TEXTURE));
        pose.pushPose();
        pose.translate(0.5F, 0.75F, 0.5F);
        pose.scale(0.7125F, -1.069F, -0.7125F);
        model.renderToBuffer(pose, consumer, combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
        pose.popPose();
    }

    private void renderQuads(VertexConsumer consumer, float xMax, float xMin, float yMin, float height, float zMin, float zMax, TextureAtlasSprite sprite) {
        float uMin = sprite.getU0();
        float uMax = sprite.getU1();
        float vMin = sprite.getV0();
        float vMax = sprite.getV1();
        final double vHeight = vMax - vMin;

        // north
        addVertexWithUV(consumer, xMax, yMin, zMin, uMax, vMin);
        addVertexWithUV(consumer, xMin, yMin, zMin, uMin, vMin);
        addVertexWithUV(consumer, xMin, height, zMin, uMin, (float) (vMin + (vHeight * height)));
        addVertexWithUV(consumer, xMax, height, zMin, uMax, (float) (vMin + (vHeight * height)));
        // south
        addVertexWithUV(consumer, xMax, yMin, zMax, uMin, vMin);
        addVertexWithUV(consumer, xMax, height, zMax, uMin, (float) (vMin + (vHeight * height)));
        addVertexWithUV(consumer, xMin, height, zMax, uMax, (float) (vMin + (vHeight * height)));
        addVertexWithUV(consumer, xMin, yMin, zMax, uMax, vMin);
    }

    private void addVertexWithUV(VertexConsumer consumer, float x, float y, float z, float u, float v) {
        // Extract RGB from -1277682 (0xFFEC7E6E)
        float red = ((float) (((-1277682) >> 16) & 0xFF)) / 255.0f;    // ~0.93
        float green = ((float) (((-1277682) >> 8) & 0xFF)) / 255.0f;   // ~0.49
        float blue = ((float) ((-1277682) & 0xFF)) / 255.0f;           // ~0.43
        float alpha = 1.0f;

        consumer.addVertex(x, y, z)
                .setColor(red, green, blue, alpha)
                .setUv(u, v)
                .setUv1(0, 0)
                .setUv2(240, 240)  // Full brightness
                .setNormal(0.0f, 1.0f, 0.0f);  // Default upward normal
    }
}
