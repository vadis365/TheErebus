package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.VelvetWormModel;
import erebus.client.render.entity.renderer.state.VelvetWormRenderState;
import erebus.entity.VelvetWorm;
import erebus.entity.helper.VelvetWormMultipart;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public class VelvetWormRenderer extends MobRenderer<VelvetWorm, VelvetWormRenderState, VelvetWormModel> {
    private static final Identifier[] TEXTURES = new Identifier[]{
            Erebus.prefix("textures/entity/velvetworm_1.png"),
            Erebus.prefix("textures/entity/velvetworm_2.png"),
            Erebus.prefix("textures/entity/velvetworm_3.png"),
            Erebus.prefix("textures/entity/velvetworm_4.png"),
            Erebus.prefix("textures/entity/velvetworm_5.png")
    };

    public VelvetWormRenderer(EntityRendererProvider.Context context) {
        super(context, new VelvetWormModel(context.bakeLayer(ModEntityRendering.VELVET_WORM)), 0F);
    }

    @Override
    public void submit(VelvetWormRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
        boolean visible = isBodyVisible(state);
        boolean translucent = !visible && !state.isInvisibleToPlayer;
        RenderType type = getRenderType(state, visible, translucent, state.appearsGlowing());
        if (type != null) {
            for (var part : state.bodyParts) submitPart(state, part, pose, submit, type, translucent);
            submitPart(state, state.tail, pose, submit, type, translucent);
        }
        // Native living rendering supplies the head, hurt overlay, name and leash.
        super.submit(state, pose, submit, camera);
    }

    private void submitPart(VelvetWormRenderState state, VelvetWormRenderState.PartState part, PoseStack pose,
                            SubmitNodeCollector submit, RenderType type, boolean translucent) {
        pose.pushPose();
        pose.translate(part.x, part.y + 1.501F, part.z);
        pose.mulPose(Axis.YP.rotationDegrees(180F - part.yaw));
        pose.scale(-1F, -1F, 1F);
        submit.submitModel(model.segments, part, pose, type, state.lightCoords,
                getOverlayCoords(state, getWhiteOverlayProgress(state)), translucent ? 654311423 : -1,
                null, state.outlineColor, null);
        pose.popPose();
    }

    @Override
    protected AABB getBoundingBoxForCulling(VelvetWorm entity) {
        return entity.getHitbox();
    }

    @Override
    public VelvetWormRenderState createRenderState() {
        return new VelvetWormRenderState();
    }

    @Override
    public void extractRenderState(VelvetWorm entity, VelvetWormRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.skin = entity.getSkin();

        double ex = entity.xOld + (entity.getX() - entity.xOld) * partialTicks;
        double ey = entity.yOld + (entity.getY() - entity.yOld) * partialTicks;
        double ez = entity.zOld + (entity.getZ() - entity.zOld) * partialTicks;

        float totalAngleDiff = 0.0f;
        for (int i = 0; i < entity.parts.length; i++) {
            Entity prevPart = i > 0 ? entity.parts[i - 1] : entity;
            VelvetWormMultipart part = entity.parts[i];
            double yawDiff = (prevPart.getYRot() - part.getYRot()) % 360.0F;
            double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
            totalAngleDiff += (float) Math.abs(yawInterpolant);
        }
        float avgAngleDiff = entity.parts.length > 1 ? totalAngleDiff / (entity.parts.length - 1) : totalAngleDiff;
        state.avgWibbleStrength = Math.clamp(1.0F - avgAngleDiff / 60.0F, 0, 1);

        state.bodyParts = new VelvetWormRenderState.PartState[entity.parts.length - 1];
        for (int i = 0; i < entity.parts.length - 1; i++) {
            VelvetWormMultipart part = entity.parts[i];
            Entity prevPart = i > 0 ? entity.parts[i - 1] : entity;
            state.bodyParts[i] = extractPartState(part, prevPart, ex, ey, ez, i, state.avgWibbleStrength, partialTicks, i > 0 && i % 2 != 0);
        }

        VelvetWormMultipart tailPart = entity.parts[entity.parts.length - 1];
        Entity prevTailPart = entity.parts[entity.parts.length - 2];
        state.tail = extractPartState(tailPart, prevTailPart, ex, ey, ez, entity.parts.length - 1, state.avgWibbleStrength, partialTicks, false);
        state.tail.tail = true;
    }

    private VelvetWormRenderState.PartState extractPartState(VelvetWormMultipart part, Entity prevPart, double ex, double ey, double ez, int frame, float avgWibbleStrength, float partialTicks, boolean isPartA) {
        VelvetWormRenderState.PartState ps = new VelvetWormRenderState.PartState();
        ps.x = part.xOld + (part.getX() - part.xOld) * partialTicks - ex;
        ps.y = part.yOld + (part.getY() - part.yOld) * partialTicks - ey;
        ps.z = part.zOld + (part.getZ() - part.zOld) * partialTicks - ez;
        ps.yaw = Mth.rotLerp(partialTicks, part.yRotO, part.getYRot());
        ps.ageInTicks = part.getParent().tickCount + partialTicks;
        double yawDiff = (prevPart.getYRot() - part.getYRot()) % 360.0F;
        double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
        ps.wibbleStrength = Math.min(avgWibbleStrength, Math.clamp(1.0F - (float) Math.abs(yawInterpolant) / 60.0F, 0, 1));
        ps.frame = frame;
        ps.isPartA = isPartA;
        return ps;
    }

    @Nullable
    protected RenderType getRenderType(VelvetWormRenderState state, boolean isVisible, boolean isTranslucentToPlayer, boolean isGlowing) {
        if (isTranslucentToPlayer)
            return RenderTypes.entityTranslucent(getTextureLocation(state));
        else if (isVisible)
            return RenderTypes.entityCutout(getTextureLocation(state));
        else
            return isGlowing ? RenderTypes.outline(getTextureLocation(state)) : null;
    }

    @Override
    public @NonNull Identifier getTextureLocation(VelvetWormRenderState state) {
        return TEXTURES[Math.clamp(state.skin, 0, TEXTURES.length - 1)];
    }
}
