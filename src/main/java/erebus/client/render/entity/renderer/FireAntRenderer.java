package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.FireAntModel;
import erebus.client.render.entity.renderer.state.FireAntRenderState;
import erebus.entity.FireAnt;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class FireAntRenderer extends ClimbingMobRenderer<FireAnt, FireAntRenderState, FireAntModel> {
    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/fire_ant.png");

    public FireAntRenderer(EntityRendererProvider.Context context) {
        super(context, new FireAntModel(context.bakeLayer(ModEntityRendering.FIRE_ANT)), 0.3F);
    }

    @Override
    public FireAntRenderState createRenderState() {
        return new FireAntRenderState();
    }

    @Override
    protected void scale(FireAntRenderState state, PoseStack pose) {
        pose.scale(0.5F, 0.5F, 0.5F);
    }

    @Override
    public Identifier getTextureLocation(FireAntRenderState state) {
        return TEXTURE;
    }
}
