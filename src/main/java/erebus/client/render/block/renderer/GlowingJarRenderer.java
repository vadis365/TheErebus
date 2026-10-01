package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
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
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class GlowingJarRenderer implements BlockEntityRenderer<GlowingJarBlockEntity, GlowingJarBlockEntityRenderState> {

    private static final net.minecraft.resources.Identifier WISP = Erebus.prefix("textures/particle/wisp.png");
    private final SpriteId TEXTURE = Sheets.BLOCKS_MAPPER.apply(Erebus.prefix("glowing_jar"));
    private final GlowingJarModel model;
    private final SpriteGetter sprites;

    public GlowingJarRenderer(BlockEntityRendererProvider.Context context) {
        model = new GlowingJarModel(context.bakeLayer(ModBlockEntityRendering.GLOWING_JAR));
        sprites = context.sprites();
    }

    public static void submitWisp(PoseStack pose, SubmitNodeCollector collector, float particleSize, float yaw) {
        pose.pushPose();
        pose.translate(0.5F, -particleSize / 4, 0.5F);
        float scale = particleSize / 3 + 0.5F;
        pose.scale(scale, scale, scale);
        pose.mulPose(Axis.YN.rotationDegrees(yaw));
        // Emit the glow before glass writes depth; custom geometry otherwise runs after model parts.
        collector.order(-1).submitCustomGeometry(pose, RenderTypes.entityTranslucentEmissive(WISP), (matrix, buffer) -> {
            // The original upright, double-sided glow keeps its center at jar height while pulsing.
            float[][] vertices = {{0.25F, 0.5F, 1, 0}, {-0.25F, 0.5F, 0, 0},
                    {-0.25F, 1, 0, 1}, {0.25F, 1, 1, 1}};
            for (float[] vertex : vertices) {
                buffer.addVertex(matrix, vertex[0], vertex[1], 0).setColor(0xFFEC810E)
                        .setUv(vertex[2], vertex[3]).setOverlay(OverlayTexture.NO_OVERLAY)
                        .setLight(net.minecraft.util.LightCoordsUtil.FULL_BRIGHT).setNormal(matrix, 0, 0, 1);
            }
        });
        pose.popPose();
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
        submitWisp(poseStack, submitNodeCollector, renderState.particleSize, 180F + camera.yRot);

        poseStack.pushPose();
        poseStack.translate(0.5F, 0.75F, 0.5F);
        poseStack.scale(0.7125F, -0.9999F, -0.7125F);

        model.submit(poseStack, submitNodeCollector, TEXTURE.renderType(RenderTypes::entityTranslucent), renderState.lightCoords, OverlayTexture.NO_OVERLAY, sprites.get(TEXTURE), 0, renderState.breakProgress);
        poseStack.popPose();
    }
}
