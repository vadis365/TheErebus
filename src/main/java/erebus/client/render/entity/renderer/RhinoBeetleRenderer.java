package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.RhinoBeetleModel;
import erebus.client.render.entity.renderer.state.RhinoBeetleRenderState;
import erebus.entity.RhinoBeetle;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class RhinoBeetleRenderer extends MobRenderer<RhinoBeetle, RhinoBeetleRenderState, RhinoBeetleModel> {
    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/rhino_beetle.png");

    public RhinoBeetleRenderer(EntityRendererProvider.Context context) {
        super(context, new RhinoBeetleModel(context.bakeLayer(ModEntityRendering.RHINO_BEETLE)), 0.5F);
    }

    @Override
    public void extractRenderState(RhinoBeetle entity, RhinoBeetleRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.ramCharge = entity.getRammingCharge();
        state.saddled = entity.hasSaddle();
    }

    @Override
    public RhinoBeetleRenderState createRenderState() {
        return new RhinoBeetleRenderState();
    }

    @Override
    protected void scale(RhinoBeetleRenderState state, PoseStack pose) {
        pose.scale(1.5F, 1.5F, 1.5F);
    }

    @Override
    public Identifier getTextureLocation(RhinoBeetleRenderState state) {
        return state.saddled ? Erebus.prefix("textures/entity/rhino_beetle_kit.png") : TEXTURE;
    }
}
