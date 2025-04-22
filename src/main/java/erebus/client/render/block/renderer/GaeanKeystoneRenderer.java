package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.block.GaeanKeystoneBlock;
import erebus.block.entity.GaeanKeystoneBlockEntity;
import erebus.registries.ModItems;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class GaeanKeystoneRenderer implements BlockEntityRenderer<GaeanKeystoneBlockEntity> {

    private final ItemRenderer itemRenderer;

    public GaeanKeystoneRenderer(BlockEntityRendererProvider.Context context) {
        itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(GaeanKeystoneBlockEntity entity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        if (entity.getBlockState().getValue(GaeanKeystoneBlock.ACTIVE)) {
            ItemStack stack = new ItemStack(ModItems.PORTAL_ACTIVATOR.get());

            double now = (entity.getLevel().getGameTime() % Short.MAX_VALUE) + partialTick;
            double hover = (Math.sin(now / 40) + 1) / 16;
            float scale = 1.25F;
            BlockPos pos = entity.getBlockPos();

            double x = 0.5F;
            double y = scale + 12F / 18F + hover;
            double z = 0.5F;

            poseStack.pushPose();
            poseStack.translate(x, y, z);
            poseStack.rotateAround(Axis.YP.rotationDegrees(entity.getRenderingRotation()), 0, 0, 0);
            poseStack.rotateAround(Axis.ZN.rotationDegrees(45), 0, 0, 0);
            poseStack.scale(scale, scale, scale);
            itemRenderer.renderStatic(
                    stack,
                    ItemDisplayContext.FIXED,
                    LightTexture.FULL_BRIGHT,
                    OverlayTexture.NO_OVERLAY,
                    poseStack,
                    multiBufferSource,
                    entity.getLevel(),
                    1
            );
            poseStack.popPose();
        }
    }
}
