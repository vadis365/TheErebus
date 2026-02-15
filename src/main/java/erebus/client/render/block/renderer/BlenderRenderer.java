package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.BlenderBlock;
import erebus.block.entity.BlenderBlockEntity;
import erebus.client.render.block.model.BlenderModel;
import erebus.client.render.block.renderer.state.BlenderBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class BlenderRenderer implements BlockEntityRenderer<BlenderBlockEntity, BlenderBlockEntityRenderState> {

    private final Material TEXTURE = Sheets.BLOCKS_MAPPER.apply(Erebus.prefix("blender"));
    private final BlenderModel model;
    private final MaterialSet materials;

    public BlenderRenderer(Context context) {
        model = new BlenderModel(context.bakeLayer(ModBlockEntityRendering.BLENDER));
        materials = context.materials();
    }

    @Override
    public BlenderBlockEntityRenderState createRenderState() {
        return new BlenderBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(BlenderBlockEntity blockEntity, BlenderBlockEntityRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.facing = blockEntity.getBlockState().getValue(BlenderBlock.FACING);
    }

    @Override
    public void submit(BlenderBlockEntityRenderState renderState, PoseStack pose, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState cameraRenderState) {
        pose.pushPose();
        pose.translate(0.5F, 1.5F, 0.5F);
        pose.scale(1, -1, -1);
        pose.scale(0.89F, 1, 0.89F);

        switch (renderState.facing) {
            case NORTH -> pose.rotateAround(Axis.YP.rotationDegrees(90), 0, 1, 0);
            case SOUTH -> pose.rotateAround(Axis.YP.rotationDegrees(270), 0, 1, 0);
            case WEST -> pose.rotateAround(Axis.YP.rotationDegrees(0), 0, 1, 0);
            case EAST -> pose.rotateAround(Axis.YP.rotationDegrees(180), 0, 1, 0);
        }

        submitNodeCollector.submitModel(
                model,
                renderState,
                pose,
                TEXTURE.renderType(RenderTypes::entityCutout),
                renderState.lightCoords,
                OverlayTexture.NO_OVERLAY,
                -1,
                materials.get(TEXTURE),
                0,
                renderState.breakProgress
        );
        pose.popPose();
    }
}
