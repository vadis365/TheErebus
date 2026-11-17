package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.TarantulaMiniBossModel;
import erebus.client.render.entity.renderer.state.TarantulaMiniBossRenderState;
import erebus.entity.TarantulaMiniBoss;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class TarantulaMiniBossRenderer extends MobRenderer<TarantulaMiniBoss, TarantulaMiniBossRenderState, TarantulaMiniBossModel> {
    private static final Identifier BASE = Erebus.prefix("textures/entity/tarantula.png");
    private static final Identifier DESPERATION = Erebus.prefix("textures/entity/tarantula_turquoise.png");
    private static final Identifier DISSOLVE = Erebus.prefix("textures/entity/tarantula_dissolve.png");
    private static final RenderType DYING_RENDER_TYPE = RenderTypes.entityCutoutDissolve(DESPERATION, DISSOLVE);
    private static final float HALF_SQRT_3 = (float) (Math.sqrt(3.0F) / (double) 2.0F);

    public TarantulaMiniBossRenderer(EntityRendererProvider.Context context) {
        super(context, new TarantulaMiniBossModel(context.bakeLayer(ModEntityRendering.TARANTULA_MINI_BOSS)), 1.2F);
        addLayer(new TarantulaMiniBossRenderLayer(this, context.getModelSet()));
    }

    private static void submitRays(PoseStack pose, float deathTime, SubmitNodeCollector submit, RenderType renderType) {
        submit.submitCustomGeometry(pose, renderType, (p, buffer) -> {
            float overDrive = Math.min(deathTime > 0.8F ? (deathTime - 0.8F) / 0.2F : 0.0F, 1.0F);
            int innerColor = ARGB.colorFromFloat(1.0F - overDrive, 1.0F, 1.0F, 1.0F);
            int outerColor = ARGB.color(241, 92, 14);
            RandomSource random = RandomSource.create(432L);
            Vector3f origin = new Vector3f();
            Vector3f outerLeft = new Vector3f();
            Vector3f outerRight = new Vector3f();
            Vector3f outerBottom = new Vector3f();
            Quaternionf rayRotation = new Quaternionf();
            int rayCount = Mth.floor((deathTime + deathTime * deathTime) / 2.0F * 60.0F);

            for (int i = 0; i < rayCount; ++i) {
                rayRotation.rotationXYZ(random.nextFloat() * ((float) Math.PI * 2F), random.nextFloat() * ((float) Math.PI * 2F), random.nextFloat() * ((float) Math.PI * 2F)).rotateXYZ(random.nextFloat() * ((float) Math.PI * 2F), random.nextFloat() * ((float) Math.PI * 2F), random.nextFloat() * ((float) Math.PI * 2F) + deathTime * ((float) Math.PI / 2F));
                p.rotate(rayRotation);
                float length = random.nextFloat() * 20.0F + 5.0F + overDrive * 10.0F;
                float width = random.nextFloat() * 2.0F + 1.0F + overDrive * 2.0F;
                outerLeft.set(-HALF_SQRT_3 * width, length, -0.5F * width);
                outerRight.set(HALF_SQRT_3 * width, length, -0.5F * width);
                outerBottom.set(0.0F, length, width);
                buffer.addVertex(p, origin).setColor(innerColor);
                buffer.addVertex(p, outerLeft).setColor(outerColor);
                buffer.addVertex(p, outerRight).setColor(outerColor);
                buffer.addVertex(p, origin).setColor(innerColor);
                buffer.addVertex(p, outerRight).setColor(outerColor);
                buffer.addVertex(p, outerBottom).setColor(outerColor);
                buffer.addVertex(p, origin).setColor(innerColor);
                buffer.addVertex(p, outerBottom).setColor(outerColor);
                buffer.addVertex(p, outerLeft).setColor(outerColor);
            }

        });
    }

    @Override
    public void extractRenderState(@NonNull TarantulaMiniBoss entity, @NonNull TarantulaMiniBossRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.desperate = entity.isInDesperation();
        state.scale = 2.0F;
        state.isDeadOrDying = entity.isDeadOrDying();
        state.hasFancyOverlay = entity.getFancyRenderOverlay();
        state.deathTime = entity.deathTicks > 0 ? (float) entity.deathTicks + partialTicks : 0.0F;
    }

    @Override
    public void submit(@NonNull TarantulaMiniBossRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
        super.submit(state, pose, submit, camera);

        if (state.deathTime > 0.0F) {
            float deathTime = state.deathTime / 200.0F;
            pose.pushPose();
            pose.translate(0.0F, -1.0F, -2.0F);
            submitRays(pose, deathTime, submit, RenderTypes.dragonRays());
            submitRays(pose, deathTime, submit, RenderTypes.dragonRaysDepth());
            pose.popPose();
        }
    }

    @Override
    protected @Nullable RenderType getRenderType(TarantulaMiniBossRenderState state, boolean isBodyVisible, boolean forceTransparent, boolean appearGlowing) {
        if (state.deathTime > 0.0F && isBodyVisible) return DYING_RENDER_TYPE;
        return super.getRenderType(state, isBodyVisible, forceTransparent, appearGlowing);
    }

    @Override
    protected int getModelTint(TarantulaMiniBossRenderState state) {
        if (state.deathTime > 0.0F)
            return ARGB.white(1.0F - Mth.clamp(state.deathTime / 200.0F, 0.0F, 1.0F));
        return super.getModelTint(state);
    }

    @Override
    public @NonNull TarantulaMiniBossRenderState createRenderState() {
        return new TarantulaMiniBossRenderState();
    }

    @Override
    protected float getShadowRadius(@NonNull TarantulaMiniBossRenderState state) {
        return 2.5F;
    }

    @Override
    public @NonNull Identifier getTextureLocation(TarantulaMiniBossRenderState state) {
        return state.desperate ? BASE : DESPERATION;
    }
}
