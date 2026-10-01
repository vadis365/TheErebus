package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.CentipedeModel;
import erebus.client.render.entity.renderer.state.CentipedeRenderState;
import erebus.entity.Centipede;
import erebus.entity.helper.CentipedeMultipart;
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

public class CentipedeRenderer extends MobRenderer<Centipede, CentipedeRenderState, CentipedeModel> {
    private static final Identifier[] TEXTURES = new Identifier[]{
            Erebus.prefix("textures/entity/centipede.png"),
            Erebus.prefix("textures/entity/centipede_light.png"),
            Erebus.prefix("textures/entity/centipede_black.png")
    };

    public CentipedeRenderer(EntityRendererProvider.Context context) {
        super(context, new CentipedeModel(context.bakeLayer(ModEntityRendering.CENTIPEDE)), 0F);
    }

    @Override
    public void submit(CentipedeRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
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

    private void submitPart(CentipedeRenderState state, CentipedeRenderState.PartState part, PoseStack pose,
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
    protected AABB getBoundingBoxForCulling(Centipede entity) {
        return entity.getHitbox();
    }

    @Override
    public CentipedeRenderState createRenderState() {
        return new CentipedeRenderState();
    }

    @Override
    public void extractRenderState(Centipede entity, CentipedeRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.skin = entity.getSkin();

        double ex = entity.xOld + (entity.getX() - entity.xOld) * partialTicks;
        double ey = entity.yOld + (entity.getY() - entity.yOld) * partialTicks;
        double ez = entity.zOld + (entity.getZ() - entity.zOld) * partialTicks;

        float totalAngleDiff = 0.0F;

        for (int c = 0; c < entity.parts.length; c++) {
            Entity prevPart = c > 0 ? entity.parts[c - 1] : entity;
            CentipedeMultipart part = entity.parts[c];
            double yawDiff = (prevPart.getYRot() - part.getYRot()) % 360.0F;
            double yawInterpolant = 2 * yawDiff % 360.0F - yawDiff;
            totalAngleDiff += (float) Math.abs(yawInterpolant);
        }

        float avgAngleDiff = entity.parts.length > 1 ? totalAngleDiff / (entity.parts.length - 1) : totalAngleDiff;
        state.avgWibbleStrength = Math.clamp(1.0F - avgAngleDiff / 60.0F, 0, 1);

        state.bodyParts = new CentipedeRenderState.PartState[entity.parts.length - 1];

        for (int c = 0; c < entity.parts.length - 1; c++) {
            CentipedeMultipart part = entity.parts[c];
            Entity prevPart = c > 0 ? entity.parts[c - 1] : entity;
            state.bodyParts[c] = extractPartState(part, prevPart, ex, ey, ez, c, state.avgWibbleStrength, partialTicks, c % 2 == 0);
        }

        CentipedeMultipart tail = entity.parts[entity.parts.length - 1];
        Entity prevTail = entity.parts[entity.parts.length - 2];
        state.tail = extractPartState(tail, prevTail, ex, ey, ez, entity.parts.length - 1, state.avgWibbleStrength, partialTicks, false);
        state.tail.tail = true;
    }

    private CentipedeRenderState.PartState extractPartState(CentipedeMultipart part, Entity prevPart, double ex, double ey, double ez, int frame, float avgWibbleStrength, float partialTicks, boolean isPartA) {
        CentipedeRenderState.PartState ps = new CentipedeRenderState.PartState();
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
    protected RenderType getRenderType(CentipedeRenderState state, boolean isVisible, boolean isTranslucentToPlayer, boolean isGlowing) {
        if (isTranslucentToPlayer)
            return RenderTypes.entityTranslucent(getTextureLocation(state));
        else if (isVisible)
            return RenderTypes.entityCutout(getTextureLocation(state));
        else
            return isGlowing ? RenderTypes.outline(getTextureLocation(state)) : null;
    }

    @Override
    public @NonNull Identifier getTextureLocation(CentipedeRenderState state) {
        return TEXTURES[Math.clamp(state.skin, 0, TEXTURES.length - 1)];
    }
}
