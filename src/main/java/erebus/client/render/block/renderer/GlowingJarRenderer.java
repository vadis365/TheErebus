package erebus.client.render.block.renderer;

import org.jetbrains.annotations.NotNull;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import erebus.Erebus;
import erebus.block.entity.GlowingJarBlockEntity;
import erebus.client.render.block.model.GlowingJarModel;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class GlowingJarRenderer implements BlockEntityRenderer<GlowingJarBlockEntity> {

    private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/glowing_jar.png");
    private final ResourceLocation WISP = Erebus.prefix("textures/particle/wisp.png");
    private final GlowingJarModel model;
    protected final BlockEntityRenderDispatcher renderDispatcher;

    public GlowingJarRenderer(BlockEntityRendererProvider.Context context) {
        model = new GlowingJarModel(context.bakeLayer(ModBlockEntityRendering.GLOWING_JAR));
        renderDispatcher = context.getBlockEntityRenderDispatcher();
    }

    @Override
    public void render(@NotNull GlowingJarBlockEntity jar, float partialTick, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
    	VertexConsumer vertex = buffer.getBuffer(RenderType.entityTranslucentEmissive(WISP, true));
		poseStack.pushPose();
		poseStack.translate(0.5F, 0F - jar.particleSize / 4, 0.5F);
		poseStack.scale(jar.particleSize / 3 + 0.5F, jar.particleSize / 3 + 0.5F, jar.particleSize / 3 + 0.5F);
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		poseStack.mulPose(Axis.YN.rotationDegrees(renderDispatcher.camera.getYRot()));
		renderQuads(poseStack, vertex, 0.5F, -0.5F, 0.5F, 1, 0, 0, combinedLight);
		RenderSystem.disableBlend();
		poseStack.popPose();

		VertexConsumer consumer = buffer.getBuffer(model.renderType(TEXTURE));
		poseStack.pushPose();
		poseStack.translate(0.5F, 0.75F, 0.5F);
		poseStack.scale(0.7125F, -0.9999F, -0.7125F);
		RenderSystem.disableCull();
		model.renderToBuffer(poseStack, consumer, combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		model.renderGlassParts(poseStack, consumer, combinedLight, OverlayTexture.NO_OVERLAY, 0x7FFFFFFF);
		RenderSystem.enableCull();
		poseStack.popPose();
    }

    private void renderQuads(PoseStack poseStack, VertexConsumer consumer, float xMax, float xMin, float yMin, float height, float zMin, float zMax, int combinedLight) {
        float uMin = 0;
        float uMax = 1;
        float vMin = 0;
        float vMax = 1;
        float vHeight = vMax - vMin;

        // north
        addVertexWithUV(poseStack, consumer, xMax, yMin, zMin, uMax, vMin, combinedLight);
        addVertexWithUV(poseStack, consumer, xMin, yMin, zMin, uMin, vMin, combinedLight);
        addVertexWithUV(poseStack, consumer, xMin, height, zMin, uMin, vMin + (vHeight * height), combinedLight);
        addVertexWithUV(poseStack, consumer, xMax, height, zMin, uMax, vMin + (vHeight * height), combinedLight);
        // south
        addVertexWithUV(poseStack, consumer, xMax, yMin, zMax, uMin, vMin, combinedLight);
        addVertexWithUV(poseStack, consumer, xMax, height, zMax, uMin, vMin + (vHeight * height), combinedLight);
        addVertexWithUV(poseStack, consumer, xMin, height, zMax, uMax, vMin + (vHeight * height), combinedLight);
        addVertexWithUV(poseStack, consumer, xMin, yMin, zMax, uMax, vMin, combinedLight);
    }

    private void addVertexWithUV(PoseStack poseStack, VertexConsumer consumer, float x, float y, float z, float u, float v, int combinedLight) {
        //Extract RGB from 16776960 (0xFFFF00)
    	float red = ((float) (((16776960) >> 16) & 0xFF)) / 255.0f;    // ~1
    	float green = ((float) (((16776960) >> 8) & 0xFF)) / 255.0f;   // ~1
    	float blue = ((float) ((16776960) & 0xFF)) / 255.0f;           // ~0
    	float alpha = 0.6f;

        consumer.addVertex(poseStack.last().pose(), x * 0.5F, y, z * 0.5F)
                .setColor(red, green, blue, alpha)
                .setUv(u, v)
                .setOverlay(OverlayTexture.NO_OVERLAY)
                .setUv2(combinedLight, 240)
                .setNormal(1, 0, 0);
    }
}
