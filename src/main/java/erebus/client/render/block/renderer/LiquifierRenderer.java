package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.LiquifierBlock;
import erebus.block.entity.LiquifierBlockEntity;
import erebus.client.render.block.model.LiquifierModel;
import erebus.client.render.block.renderer.state.LiquifierBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class LiquifierRenderer implements BlockEntityRenderer<LiquifierBlockEntity, LiquifierBlockEntityRenderState> {
    private final Identifier TEXTURE = Erebus.prefix("liquifier");
    private final LiquifierModel model;
    private final ItemModelResolver itemModelResolver;
    private final SpriteGetter sprites;

    public LiquifierRenderer(Context context) {
        model = new LiquifierModel(context.bakeLayer(ModBlockEntityRendering.LIQUIFIER));
        itemModelResolver = context.itemModelResolver();
        sprites = context.sprites();
    }


    @Override
    public void submit(LiquifierBlockEntityRenderState renderState, PoseStack pose, @NonNull SubmitNodeCollector submitNodeCollector, @NonNull CameraRenderState cameraRenderState) {
        SpriteId sprite = Sheets.BLOCKS_MAPPER.apply(TEXTURE);

        if (!renderState.tankResource.isEmpty() && renderState.tankAmount > 0) {
            float height = (0.375F / renderState.tankCapacity) * renderState.tankAmount;

            float xMax = 0.9921875F;
            float zMax = 0.9921875F;
            float xMin = 0.0078125F;
            float zMin = 0.0078125F;
            float yMin = 0.0078125F;

            FluidRenderHelper.renderFluid(renderState.tankResource.toStack(renderState.tankAmount), pose, submitNodeCollector, xMin, xMax, yMin, yMin + height, zMin, zMax, renderState.lightCoords);
        }

        pose.pushPose();
        pose.translate(0.5, 0.5, 0.5);
        pose.mulPose(Axis.YP.rotationDegrees(-renderState.animationRotation));
        pose.scale(0.175F, 0.175F, 0.175F);
        renderState.itemStackRenderState.submit(pose, submitNodeCollector, renderState.lightCoords, OverlayTexture.NO_OVERLAY, 0);
        pose.popPose();

        pose.pushPose();
        pose.translate(0.5, 1.5, 0.5);
        pose.scale(-1, -1, 1);
        pose.mulPose(Axis.YP.rotationDegrees(renderState.facingRotation));
        submitNodeCollector.submitModel(
                model,
                renderState,
                pose,
                sprite.renderType(RenderTypes::entityTranslucent),
                renderState.lightCoords,
                OverlayTexture.NO_OVERLAY,
                -1,
                sprites.get(sprite),
                0,
                renderState.breakProgress
        );
        pose.popPose();
    }

    @Override
    public LiquifierBlockEntityRenderState createRenderState() {
        return new LiquifierBlockEntityRenderState();
    }

    @Override
    public void extractRenderState(LiquifierBlockEntity blockEntity, LiquifierBlockEntityRenderState state, float partialTicks, @NonNull Vec3 cameraPosition, ModelFeatureRenderer.@Nullable CrumblingOverlay breakProgress) {
        BlockEntityRenderer.super.extractRenderState(blockEntity, state, partialTicks, cameraPosition, breakProgress);
        state.facingRotation = blockEntity.getBlockState().getValue(LiquifierBlock.FACING).toYRot();
        state.animationRotation = Mth.lerp(partialTicks, blockEntity.prevAnimationTicks, blockEntity.animationTicks);
        state.tankResource = blockEntity.tank.getResource(0);
        state.tankAmount = blockEntity.tank.getAmountAsInt(0);
        state.tankCapacity = FluidType.BUCKET_VOLUME * 8;
        itemModelResolver.updateForTopItem(
                state.itemStackRenderState,
                blockEntity.getItem(0),
                ItemDisplayContext.FIXED,
                blockEntity.getLevel(),
                null,
                0
        );
    }
}
