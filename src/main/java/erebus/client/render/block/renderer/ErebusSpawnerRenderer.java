package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.block.entity.ErebusSpawnerBlockEntity;
import erebus.client.render.block.renderer.state.ErebusSpawnerBlockEntityRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ErebusSpawnerRenderer implements BlockEntityRenderer<ErebusSpawnerBlockEntity, ErebusSpawnerBlockEntityRenderState> {
    private final EntityRenderDispatcher dispatcher;

    public ErebusSpawnerRenderer(BlockEntityRendererProvider.Context context) {
        dispatcher = context.entityRenderer();
    }

    @Override
    public ErebusSpawnerBlockEntityRenderState createRenderState() {
        return new ErebusSpawnerBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(ErebusSpawnerBlockEntity entity, ErebusSpawnerBlockEntityRenderState state, float partialTicks, @NonNull Vec3 camera, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(entity, state, partialTicks, camera, breakProgress);
        if(entity.getLevel() != null) {
            BaseSpawner spawner = entity.getSpawner();
            Entity displayEntity = spawner.getOrCreateDisplayEntity(entity.getLevel(), entity.getBlockPos());

            if (displayEntity != null) {
                state.displayEntity = dispatcher.extractEntity(displayEntity, partialTicks);
                state.displayEntity.lightCoords = state.lightCoords;
                state.spin = (float) Mth.lerp(partialTicks, spawner.getOSpin(), spawner.getSpin()) * 10.0F;
                state.scale = 0.53125F;
                float maxLength = Math.max(displayEntity.getBbWidth(), displayEntity.getBbHeight());
                if ((double)maxLength > (double)1.0F) {
                    state.scale /= maxLength;
                }
            }
        }
    }

    @Override
    public void submit(ErebusSpawnerBlockEntityRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
        if(state.displayEntity != null) {
            pose.pushPose();
            pose.translate(0.5F, 0.4F, 0.5F);
            pose.mulPose(Axis.YP.rotationDegrees(state.spin));
            pose.translate(0.0F, -0.2F, 0.0F);
            pose.mulPose(Axis.XP.rotationDegrees(-30.0F));
            pose.scale(state.scale, state.scale, state.scale);
            dispatcher.submit(state.displayEntity, camera, 0, 0, 0, pose, submit);
            pose.popPose();
        }
    }

    @Override
    public @NonNull AABB getRenderBoundingBox(ErebusSpawnerBlockEntity blockEntity) {
        BlockPos pos = blockEntity.getBlockPos();
        return new AABB(pos.getX() - 1.0, pos.getY() - 1.0, pos.getZ() - 1.0, pos.getX() + 2.0, pos.getY() + 2.0, pos.getZ() + 2.0);
    }
}
