package erebus.client.render.block.renderer;

import com.mojang.blaze3d.opengl.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.entity.GlowingJarBlockEntity;
import erebus.client.render.block.model.GlowingJarModel;
import erebus.client.render.block.renderer.state.GlowingJarBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class GlowingJarRenderer implements BlockEntityRenderer<GlowingJarBlockEntity, GlowingJarBlockEntityRenderState> {

    private final Material TEXTURE = Sheets.BLOCK_ENTITIES_MAPPER.apply(Erebus.prefix("textures/special/tiles/glowing_jar.png"));
    private final Material WISP = Sheets.BLOCK_ENTITIES_MAPPER.apply(Erebus.prefix("textures/particle/wisp.png"));
    private final GlowingJarModel model;
    private final MaterialSet materials;

    public GlowingJarRenderer(BlockEntityRendererProvider.Context context) {
        model = new GlowingJarModel(context.bakeLayer(ModBlockEntityRendering.GLOWING_JAR));
        materials = context.materials();
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

    @Override
    public GlowingJarBlockEntityRenderState createRenderState() {
        return new GlowingJarBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(GlowingJarBlockEntity blockEntity, GlowingJarBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.particleSize = blockEntity.particleSize;
    }

    @Override
    public void submit(GlowingJarBlockEntityRenderState renderState, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        poseStack.pushPose();
        poseStack.translate(0.5F, 0F - renderState.particleSize / 4, 0.5F);
        poseStack.scale(renderState.particleSize / 3 + 0.5F, renderState.particleSize / 3 + 0.5F, renderState.particleSize / 3 + 0.5F);
        GlStateManager._enableBlend();
        poseStack.mulPose(Axis.YN.rotationDegrees(camera.orientation.y));

        //renderQuads(poseStack, vertex, 0.5F, -0.5F, 0.5F, 1, 0, 0, renderState.lightCoords);
        GlStateManager._disableBlend();
        poseStack.popPose();

        poseStack.pushPose();
        poseStack.translate(0.5F, 0.75F, 0.5F);
        poseStack.scale(0.7125F, -0.9999F, -0.7125F);

        GlStateManager._disableCull();
        submitNodeCollector.submitModel(model, renderState, poseStack, TEXTURE.renderType(RenderTypes::entityTranslucent), renderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, materials.get(TEXTURE), 0, renderState.breakProgress);
        GlStateManager._enableCull();
        poseStack.popPose();
    }
}
