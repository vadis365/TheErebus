package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import erebus.Erebus;
import erebus.client.render.entity.model.MagmaCrawlerModel;
import erebus.client.render.entity.renderer.state.MagmaCrawlerRenderState;
import erebus.entity.MagmaCrawler;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class MagmaCrawlerRenderer extends MobRenderer<MagmaCrawler, MagmaCrawlerRenderState, MagmaCrawlerModel> {
    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/magma_crawler.png");

    public MagmaCrawlerRenderer(EntityRendererProvider.Context context) {
        super(context, new MagmaCrawlerModel(context.bakeLayer(ModEntityRendering.MAGMA_CRAWLER)), 0.45F);
    }

    @Override
    public void extractRenderState(MagmaCrawler entity, MagmaCrawlerRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.onCeiling = entity.getOnCeiling();
    }

    @Override
    public MagmaCrawlerRenderState createRenderState() {
        return new MagmaCrawlerRenderState();
    }

    @Override
    protected void scale(MagmaCrawlerRenderState state, PoseStack pose) {
        pose.scale(0.75F, 0.75F, 0.75F);
        if (state.onCeiling) {
            pose.mulPose(Axis.XP.rotationDegrees(180));
            pose.mulPose(Axis.YP.rotationDegrees(180));
            pose.translate(0, 1.2F, 0);
        }
    }

    @Override
    public Identifier getTextureLocation(MagmaCrawlerRenderState state) {
        return TEXTURE;
    }
}
