package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.BlockOfBonesBlock;
import erebus.block.entity.BlockOfBonesBlockEntity;
import erebus.client.render.block.model.BlockOfBonesModel;
import erebus.client.render.block.state.BlockOfBonesBlockEntityRenderState;
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

public class BlockOfBonesRenderer implements BlockEntityRenderer<BlockOfBonesBlockEntity, BlockOfBonesBlockEntityRenderState> {

    private final Material TEXTURE = Sheets.BLOCK_ENTITIES_MAPPER.apply(Erebus.prefix("textures/special/tiles/bone_block.png"));
    private final BlockOfBonesModel model;
    private final MaterialSet materials;

    public BlockOfBonesRenderer(Context context) {
        model = new BlockOfBonesModel(context.bakeLayer(ModBlockEntityRendering.BLOCK_OF_BONES));
        materials = context.materials();
    }

    @Override
    public BlockOfBonesBlockEntityRenderState createRenderState() {
        return new BlockOfBonesBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(BlockOfBonesBlockEntity blockEntity, BlockOfBonesBlockEntityRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.facing = blockEntity.getBlockState().getValue(BlockOfBonesBlock.FACING);
        state.nametag = blockEntity.displayName;
        state.nametagAttachment = blockEntity.getBlockPos().getCenter().add(0, 1, 0);
        state.distanceToCameraSq = cameraPosition.distanceToSqr(blockEntity.getBlockPos().getCenter());
    }

    @Override
    public void submit(BlockOfBonesBlockEntityRenderState renderState, PoseStack pose, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState camera) {
        pose.pushPose();
        pose.translate(0.5D, 1.5F, 0.5D);
        pose.scale(1, -1, -1);

        switch (renderState.facing) {
            case NORTH:
                pose.rotateAround(Axis.YP.rotationDegrees(180), 0, 1, 0);
                break;
            case SOUTH:
                pose.rotateAround(Axis.YP.rotationDegrees(0), 0, 1, 0);
                break;
            case WEST:
                pose.rotateAround(Axis.YP.rotationDegrees(90), 0, 1, 0);
                break;
            case EAST:
                pose.rotateAround(Axis.YN.rotationDegrees(90), 0, 1, 0);
                break;
        }
        submitNodeCollector.submitModel(model, renderState, pose, TEXTURE.renderType(RenderTypes::entitySolid), renderState.lightCoords, OverlayTexture.NO_OVERLAY, -1, materials.get(TEXTURE), 0, renderState.breakProgress);
        submitNodeCollector.submitNameTag(pose, renderState.nametagAttachment, 0, renderState.nametag, false, renderState.lightCoords, renderState.distanceToCameraSq, camera);
        pose.popPose();
    }
}
