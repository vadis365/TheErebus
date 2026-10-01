package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.client.render.entity.model.ChameleonTickModel;
import erebus.client.render.entity.renderer.state.ChameleonTickRenderState;
import erebus.entity.ChameleonTick;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

public class ChameleonTickRenderer extends MobRenderer<ChameleonTick, ChameleonTickRenderState, ChameleonTickModel> {
    public ChameleonTickRenderer(EntityRendererProvider.Context context) {
        super(context, new ChameleonTickModel(context.bakeLayer(ModEntityRendering.CHAMELEON_TICK)), 0.3F);
    }

    @Override
    public void extractRenderState(ChameleonTick entity, ChameleonTickRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.unfold = entity.getUnfold(partialTicks);
        var block = state.camouflage;
        block.blockState = entity.getCamouflage();
        block.blockPos = entity.blockPosition();
        block.randomSeedPos = block.blockPos;
        if (entity.level() instanceof ClientLevel level) {
            block.biome = level.getBiome(block.blockPos);
            block.cardinalLighting = level.cardinalLighting();
            block.lightEngine = level.getLightEngine();
        }
        var sprite = Minecraft.getInstance().getModelManager().getBlockStateModelSet().getParticleMaterial(block.blockState).sprite();
        state.texture = sprite.contents().name().withPath(path -> "textures/" + path + ".png");
    }

    @Override
    public void submit(ChameleonTickRenderState state, PoseStack pose, SubmitNodeCollector collector, CameraRenderState camera) {
        if (!state.isInvisible) {
            pose.pushPose();
            pose.mulPose(Axis.YN.rotationDegrees(state.bodyRot));
            pose.translate(-0.5, 0, -0.5);
            pose.scale(1, 1 - state.unfold * 0.01F, 1);
            collector.submitMovingBlock(pose, state.camouflage);
            pose.popPose();
        }
        super.submit(state, pose, collector, camera);
    }

    @Override
    protected float getFlipDegrees() {
        return 0;
    }

    @Override
    public ChameleonTickRenderState createRenderState() {
        return new ChameleonTickRenderState();
    }

    @Override
    public Identifier getTextureLocation(ChameleonTickRenderState state) {
        return state.texture;
    }
}
