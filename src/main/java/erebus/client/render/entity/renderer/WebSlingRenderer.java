package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.entity.projectile.WebSling;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.FallingBlockRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;

public class WebSlingRenderer extends EntityRenderer<WebSling, FallingBlockRenderState> {
    public WebSlingRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public FallingBlockRenderState createRenderState() {
        return new FallingBlockRenderState();
    }

    @Override
    public void extractRenderState(WebSling entity, FallingBlockRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        var block = state.movingBlockRenderState;
        block.randomSeedPos = entity.blockPosition();
        block.blockPos = entity.blockPosition();
        block.blockState = entity.getWebState();
        if (entity.level() instanceof ClientLevel level) {
            block.biome = level.getBiome(entity.blockPosition());
            block.cardinalLighting = level.cardinalLighting();
            block.lightEngine = level.getLightEngine();
        }
    }

    @Override
    public void submit(FallingBlockRenderState state, PoseStack pose, SubmitNodeCollector collector, CameraRenderState camera) {
        pose.pushPose();
        pose.translate(-0.5, 0, -0.5);
        collector.submitMovingBlock(pose, state.movingBlockRenderState);
        pose.popPose();
        super.submit(state, pose, collector, camera);
    }
}
