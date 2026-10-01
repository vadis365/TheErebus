package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.GlowWormModel;
import erebus.client.render.entity.renderer.state.GlowWormRenderState;
import erebus.entity.GlowWorm;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.attribute.EnvironmentAttributes;

public class GlowWormRenderer extends MobRenderer<GlowWorm, GlowWormRenderState, GlowWormModel> {
    private static final Identifier GLOW_TEXTURE = Erebus.prefix("textures/entity/glow_worm_glow.png");
    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/glow_worm.png");

    public GlowWormRenderer(EntityRendererProvider.Context context) {
        super(context, new GlowWormModel(context.bakeLayer(ModEntityRendering.GLOW_WORM)), 0.0F);
    }

    @Override
    public void extractRenderState(GlowWorm entity, GlowWormRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.bioluminescent = entity.isNearPlayer() && entity.level().environmentAttributes().getValue(EnvironmentAttributes.SKY_LIGHT_FACTOR, entity.position(), null) < 0.5F;
    }

    @Override
    public GlowWormRenderState createRenderState() {
        return new GlowWormRenderState();
    }

    @Override
    protected void scale(GlowWormRenderState state, PoseStack pose) {
        pose.scale(0.75F, 0.75F, 0.75F);
    }

    @Override
    public Identifier getTextureLocation(GlowWormRenderState state) {
        return state.bioluminescent ? GLOW_TEXTURE : TEXTURE;
    }
}
