package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.MidgeSwarmModel;
import erebus.client.render.entity.renderer.layer.MidgeSwarmWingLayer;
import erebus.client.render.entity.renderer.state.MidgeSwarmRenderState;
import erebus.entity.MidgeSwarm;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class MidgeSwarmRenderer extends MobRenderer<MidgeSwarm, MidgeSwarmRenderState, MidgeSwarmModel> {
    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/midge_swarm.png");

    public MidgeSwarmRenderer(EntityRendererProvider.Context context) {
        super(context, new MidgeSwarmModel(context.bakeLayer(ModEntityRendering.MIDGE_SWARM), false), 0.45F);
        addLayer(new MidgeSwarmWingLayer(this, context.getModelSet()));
    }

    @Override
    public void extractRenderState(MidgeSwarm entity, MidgeSwarmRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.health = entity.getHealth();
    }

    @Override
    public MidgeSwarmRenderState createRenderState() {
        return new MidgeSwarmRenderState();
    }

    @Override
    public Identifier getTextureLocation(MidgeSwarmRenderState state) {
        return TEXTURE;
    }

    @Override
    protected void scale(MidgeSwarmRenderState state, PoseStack pose) {
        pose.scale(0.4F, 0.4F, 0.4F);
    }
}
