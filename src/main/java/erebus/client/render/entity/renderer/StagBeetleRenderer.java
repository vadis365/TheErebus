package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.StagBeetleModel;
import erebus.client.render.entity.renderer.state.StagBeetleRenderState;
import erebus.entity.StagBeetle;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class StagBeetleRenderer extends MobRenderer<StagBeetle, StagBeetleRenderState, StagBeetleModel> {
    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/stag_beetle.png");

    public StagBeetleRenderer(EntityRendererProvider.Context context) {
        super(context, new StagBeetleModel(context.bakeLayer(ModEntityRendering.STAG_BEETLE)), 1F);
    }

    @Override
    public void extractRenderState(StagBeetle entity, StagBeetleRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.saddled = entity.hasSaddle();
        state.digging = entity.getJawTicks() > 0;
        state.jawTicks = entity.getJawTicks(partialTicks);
        state.headPosition = entity.getHeadPosition();
    }

    @Override
    public StagBeetleRenderState createRenderState() {
        return new StagBeetleRenderState();
    }

    @Override
    protected void scale(StagBeetleRenderState state, PoseStack pose) {
        pose.scale(1.2F, 1.2F, 1.2F);
    }

    @Override
    public Identifier getTextureLocation(StagBeetleRenderState state) {
        return state.saddled ? Erebus.prefix("textures/entity/stag_beetle_kit.png") : TEXTURE;
    }
}
