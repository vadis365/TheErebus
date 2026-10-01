package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import erebus.Erebus;
import erebus.client.render.entity.model.SolifugeModel;
import erebus.client.render.entity.renderer.state.SolifugeRenderState;
import erebus.entity.BabySolifuge;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class BabySolifugeRenderer extends MobRenderer<BabySolifuge, SolifugeRenderState, SolifugeModel> {
    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/solifuge.png");

    public BabySolifugeRenderer(EntityRendererProvider.Context context) {
        super(context, new SolifugeModel(context.bakeLayer(ModEntityRendering.SOLIFUGE)), 0.5F);
    }

    @Override
    public void extractRenderState(BabySolifuge entity, SolifugeRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
    }

    @Override
    public SolifugeRenderState createRenderState() {
        return new SolifugeRenderState();
    }

    @Override
    protected void scale(SolifugeRenderState state, PoseStack pose) {
        pose.scale(0.5F, 0.5F, 0.5F);
    }

    @Override
    public Identifier getTextureLocation(SolifugeRenderState state) {
        return TEXTURE;
    }
}
