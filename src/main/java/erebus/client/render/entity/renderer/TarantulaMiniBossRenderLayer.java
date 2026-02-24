package erebus.client.render.entity.renderer;

import erebus.Erebus;
import erebus.client.render.entity.model.TarantulaMiniBossModel;
import erebus.client.render.entity.renderer.state.TarantulaMiniBossRenderState;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jspecify.annotations.NonNull;

public class TarantulaMiniBossRenderLayer extends EnergySwirlLayer<TarantulaMiniBossRenderState, TarantulaMiniBossModel> {

    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/power.png");
    private final TarantulaMiniBossModel model;

    public TarantulaMiniBossRenderLayer(RenderLayerParent<TarantulaMiniBossRenderState, TarantulaMiniBossModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.model = new TarantulaMiniBossModel(modelSet.bakeLayer(ModEntityRendering.TARANTULA_MINI_BOSS));
    }

    @Override
    protected boolean isPowered(TarantulaMiniBossRenderState state) {
        return state.hasFancyOverlay;
    }

    @Override
    protected float xOffset(float v) {
        return Mth.cos(v * 0.02F) * 3.0F;
    }

    @Override
    protected @NonNull Identifier getTextureLocation() {
        return TEXTURE;
    }

    @Override
    protected TarantulaMiniBossModel model() {
        return model;
    }
}
