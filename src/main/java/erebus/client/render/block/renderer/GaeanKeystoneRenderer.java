package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.block.GaeanKeystoneBlock;
import erebus.block.entity.GaeanKeystoneBlockEntity;
import erebus.client.render.block.state.GaeanKeystoneBlockEntityRenderState;
import erebus.registries.item.ModItems;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class GaeanKeystoneRenderer implements BlockEntityRenderer<GaeanKeystoneBlockEntity, GaeanKeystoneBlockEntityRenderState> {

    private final ItemModelResolver itemModelResolver;

    public GaeanKeystoneRenderer(Context context) {
        itemModelResolver = context.itemModelResolver();
    }

    @Override
    public GaeanKeystoneBlockEntityRenderState createRenderState() {
        return new GaeanKeystoneBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(GaeanKeystoneBlockEntity blockEntity, GaeanKeystoneBlockEntityRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.now = (blockEntity.getLevel().getGameTime() & Short.MAX_VALUE) + partialTicks;
        state.rotation = blockEntity.getRenderingRotation();
        itemModelResolver.updateForTopItem(
                state.itemStackRenderState,
                blockEntity.getBlockState().getValue(GaeanKeystoneBlock.ACTIVE) ? new ItemStack(ModItems.PORTAL_ACTIVATOR.get()) : ItemStack.EMPTY,
                ItemDisplayContext.FIXED,
                blockEntity.getLevel(),
                null,
                0
        );
    }

    @Override
    public void submit(GaeanKeystoneBlockEntityRenderState renderState, PoseStack poseStack, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState cameraRenderState) {
        double hover = (Math.sin(renderState.now / 40) + 1) / 16;
        float scale = 1.25F;

        double x = 0.5F;
        double y = scale + 12F / 18F + hover;
        double z = 0.5F;

        poseStack.pushPose();
        poseStack.translate(x, y, z);
        poseStack.rotateAround(Axis.YP.rotationDegrees(renderState.rotation), 0, 0, 0);
        poseStack.rotateAround(Axis.ZN.rotationDegrees(45), 0, 0, 0);
        poseStack.scale(scale, scale, scale);
        renderState.itemStackRenderState.submit(poseStack, submitNodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        poseStack.popPose();
    }
}
