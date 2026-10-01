package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.client.render.entity.renderer.state.WoodlouseBallRenderState;
import erebus.entity.projectile.WoodlouseBall;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemDisplayContext;

public final class WoodlouseBallRenderer extends EntityRenderer<WoodlouseBall, WoodlouseBallRenderState> {
    private final ItemModelResolver itemModels;

    public WoodlouseBallRenderer(EntityRendererProvider.Context context) {
        super(context);
        itemModels = context.getItemModelResolver();
    }

    @Override
    public WoodlouseBallRenderState createRenderState() {
        return new WoodlouseBallRenderState();
    }

    @Override
    public void extractRenderState(WoodlouseBall entity, WoodlouseBallRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.yaw = Mth.rotLerp(partialTick, entity.yRotO, entity.getYRot());
        itemModels.updateForNonLiving(state.item, entity.getItem(), ItemDisplayContext.NONE, entity);
    }

    @Override
    public void submit(WoodlouseBallRenderState state, PoseStack pose, SubmitNodeCollector submit, CameraRenderState camera) {
        pose.pushPose();
        pose.translate(0, 0.4, 0);
        pose.mulPose(Axis.YP.rotationDegrees(state.yaw));
        pose.mulPose(Axis.XN.rotationDegrees(state.ageInTicks * 20));
        state.item.submit(pose, submit, state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);
        pose.popPose();
        super.submit(state, pose, submit, camera);
    }
}
