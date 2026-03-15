package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.block.entity.PreservedBlockEntity;
import erebus.client.render.block.renderer.state.PreservedBlockEntityRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class PreservedBlockRenderer implements BlockEntityRenderer <PreservedBlockEntity, PreservedBlockEntityRenderState> {

    private final EntityRenderDispatcher entityRenderer;

    public PreservedBlockRenderer(Context context) {
        entityRenderer = context.entityRenderer();
    }

    @Override
    public PreservedBlockEntityRenderState createRenderState() {
        return new PreservedBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(PreservedBlockEntity blockEntity, PreservedBlockEntityRenderState state, float partialTicks, Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        if(blockEntity.getTrappedEntity() != null) {
            state.displayEntity = entityRenderer.extractEntity(blockEntity.getTrappedEntity(), partialTicks);
            state.displayEntity.lightCoords = state.lightCoords;
        }
    }

    @Override
    public void submit(PreservedBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit, CameraRenderState camera) {
        if(state.displayEntity != null) {
            pose.pushPose();
            pose.translate(0.5F, 0.0F, 0.5F);
            float f = 0.53125F;
            float f1 = Math.max(state.displayEntity.boundingBoxWidth, state.displayEntity.boundingBoxHeight);
            if ((double) f1 > (double) 1.0F) {
                f /= f1;
            }

            pose.translate(0.0F, 0.4F, 0.0F);
            pose.translate(0.0F, -0.2F, 0.0F);
            pose.mulPose(Axis.XP.rotationDegrees(-30.0F));
            pose.scale(f, f, f);
            entityRenderer.submit(
                    state.displayEntity,
                    camera,
                    0,
                    0,
                    0,
                    pose,
                    submit
            );
            pose.popPose();
        }
    }
}
