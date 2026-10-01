package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.UmberGolemStatueBlock;
import erebus.block.entity.UmberGolemStatueBlockEntity;
import erebus.client.render.block.model.UmberGolemStatueModel;
import erebus.client.render.block.renderer.state.UmberGolemStatueRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

public class UmberGolemStatueRenderer implements BlockEntityRenderer<UmberGolemStatueBlockEntity, UmberGolemStatueRenderState> {
    public static final Identifier TEXTURE = Erebus.prefix("textures/special/tiles/umber_golem_statue.png");
    private final UmberGolemStatueModel model;

    public UmberGolemStatueRenderer(BlockEntityRendererProvider.Context context) {
        model = new UmberGolemStatueModel(context.bakeLayer(ModBlockEntityRendering.UMBER_GOLEM_STATUE));
    }

    @Override
    public UmberGolemStatueRenderState createRenderState() {
        return new UmberGolemStatueRenderState();
    }

    @Override
    public void extractRenderState(UmberGolemStatueBlockEntity entity, UmberGolemStatueRenderState state, float partialTick, Vec3 camera, ModelFeatureRenderer.@Nullable CrumblingOverlay breaking) {
        BlockEntityRenderer.super.extractRenderState(entity, state, partialTick, camera, breaking);
        state.facing = entity.getBlockState().getValue(UmberGolemStatueBlock.FACING);
    }

    @Override
    public void submit(UmberGolemStatueRenderState state, PoseStack pose, SubmitNodeCollector collector, CameraRenderState camera) {
        pose.pushPose();
        pose.translate(0.5 - state.facing.getStepX() * 0.25, 1.1, 0.5 - state.facing.getStepZ() * 0.25);
        pose.scale(0.75F, -0.75F, -0.75F);
        pose.mulPose(Axis.YP.rotationDegrees(state.facing.toYRot()));
        collector.submitModelPart(model.root(), pose, RenderTypes.entityCutout(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, null, false, false, -1, state.breakProgress, 0);
        pose.popPose();
    }
}
