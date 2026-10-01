package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

import erebus.Erebus;
import erebus.client.render.entity.model.JumpingSpiderModel;
import erebus.client.render.entity.renderer.state.JumpingSpiderRenderState;
import erebus.entity.JumpingSpider;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class JumpingSpiderRenderer extends MobRenderer<JumpingSpider, JumpingSpiderRenderState, JumpingSpiderModel> {
    private static final Identifier[] TEXTURES = {Erebus.prefix("textures/entity/jumping_spider.png"), Erebus.prefix("textures/entity/jumping_spider_green.png"), Erebus.prefix("textures/entity/jumping_spider_red.png")};

    public JumpingSpiderRenderer(EntityRendererProvider.Context context) {
        super(context, new JumpingSpiderModel(context.bakeLayer(ModEntityRendering.JUMPING_SPIDER)), 0.35F);
    }

    @Override
    public void extractRenderState(JumpingSpider entity, JumpingSpiderRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.skin = entity.getSkin();
    }

    @Override
    public JumpingSpiderRenderState createRenderState() {
        return new JumpingSpiderRenderState();
    }

    @Override
    public Identifier getTextureLocation(JumpingSpiderRenderState state) {
        return TEXTURES[state.skin];
    }

    @Override
    protected void scale(JumpingSpiderRenderState state, PoseStack pose) {
        pose.scale(0.7F, 0.7F, 0.7F);
    }
}
