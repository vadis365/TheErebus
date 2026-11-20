package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.client.render.entity.renderer.state.ClimbingRenderState;
import erebus.entity.helper.SurfaceClimbing;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.Vec3;

public abstract class ClimbingMobRenderer<T extends Mob, S extends ClimbingRenderState, M extends EntityModel<? super S>>
        extends MobRenderer<T, S, M> {
    protected ClimbingMobRenderer(EntityRendererProvider.Context context, M model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Override
    public void extractRenderState(T entity, S state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        Direction surface = SurfaceClimbing.surface(entity);
        boolean attached = surface != Direction.DOWN && entity.isAlive() && !entity.onGround() && !state.isUpsideDown;
        state.climbingSurface = attached ? surface : Direction.DOWN;
    }

    @Override
    protected void setupRotations(S state, PoseStack pose, float bodyYaw, float entityScale) {
        orientClimbingModel(state, pose, bodyYaw, entityScale);
        super.setupRotations(state, pose, bodyYaw, entityScale);
    }

    protected void orientClimbingModel(S state, PoseStack pose, float bodyYaw, float entityScale) {
        Direction surface = state.climbingSurface;
        if (surface == Direction.DOWN) return;

        Vec3 offset = SurfaceClimbing.contactOffset(surface, state.boundingBoxWidth, state.boundingBoxHeight);
        pose.translate(offset.x / entityScale, offset.y / entityScale, offset.z / entityScale);
        pose.mulPose(SurfaceClimbing.rotation(surface, bodyYaw));
    }
}
