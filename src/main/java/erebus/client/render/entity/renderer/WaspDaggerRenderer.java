package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.renderer.state.WaspDaggerRenderState;
import erebus.client.render.item.model.WaspDaggerModel;
import erebus.entity.projectile.WaspDagger;
import erebus.registries.client.ModItemRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.Mth;

public final class WaspDaggerRenderer extends EntityRenderer<WaspDagger, WaspDaggerRenderState> {
    private final WaspDaggerModel model;

    public WaspDaggerRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new WaspDaggerModel(context.bakeLayer(ModItemRendering.WASP_DAGGER));
    }

    @Override
    public WaspDaggerRenderState createRenderState() {
        return new WaspDaggerRenderState();
    }

    @Override
    public void extractRenderState(WaspDagger entity, WaspDaggerRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.yaw = Mth.rotLerp(partialTick, entity.yRotO, entity.getYRot());
        state.pitch = Mth.lerp(partialTick, entity.xRotO, entity.getXRot());
        state.foil = entity.getItem().hasFoil();
    }

    @Override
    public void submit(WaspDaggerRenderState state, PoseStack pose, SubmitNodeCollector submit, CameraRenderState camera) {
        pose.pushPose();
        pose.mulPose(Axis.YP.rotationDegrees(state.yaw - 90));
        pose.mulPose(Axis.ZP.rotationDegrees(state.pitch - state.ageInTicks * 20));
        pose.scale(0.4F, 0.4F, 0.4F);
        submit.submitModelPart(model.root(), pose, model.renderType(Erebus.prefix("textures/special/items/wasp_sword.png")),
                state.lightCoords, OverlayTexture.NO_OVERLAY, null, false, state.foil, -1, null, state.outlineColor);
        pose.popPose();
        super.submit(state, pose, submit, camera);
    }
}
