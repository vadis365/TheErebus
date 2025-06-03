package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.block.entity.PreservedBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class PreservedBlockRenderer implements BlockEntityRenderer<PreservedBlockEntity> {

    private final EntityRenderDispatcher entityRenderer;

    public PreservedBlockRenderer(Context context) {
        entityRenderer = context.getEntityRenderer();
    }

    @Override
    public void render(@NotNull PreservedBlockEntity blockEntity, float partialTick, @NotNull PoseStack pose, @NotNull MultiBufferSource buffer, int packedLight, int packedOverlay) {
        Entity entity = blockEntity.getTrappedEntity();

        if(entity != null) {
            renderEntityInAmber(partialTick, pose, buffer, packedLight, entity, entityRenderer);
        }
    }

    public static void renderEntityInAmber(float partialTick, PoseStack pose, MultiBufferSource buffer, int packedLight, Entity entity, EntityRenderDispatcher entityRenderer) {
        pose.pushPose();
        pose.translate(0.5F, 0.0F, 0.5F);
        float f = 0.53125F;
        float f1 = Math.max(entity.getBbWidth(), entity.getBbHeight());
        if ((double) f1 > (double) 1.0F) {
            f /= f1;
        }

        pose.translate(0.0F, 0.4F, 0.0F);
        pose.translate(0.0F, -0.2F, 0.0F);
        pose.mulPose(Axis.XP.rotationDegrees(-30.0F));
        pose.scale(f, f, f);
        entityRenderer.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, partialTick, pose, buffer, packedLight);
        pose.popPose();
    }
}
