package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.TarantulaBabyModel;
import erebus.client.render.entity.renderer.state.TarantulaBabyRenderState;
import erebus.entity.BabyTarantula;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class BabyTarantulaRenderer extends MobRenderer<BabyTarantula, TarantulaBabyRenderState, TarantulaBabyModel> {
    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/tarantula.png");
    private static final Identifier TURQUOISE = Erebus.prefix("textures/entity/tarantula_turquoise.png");
    private static final Identifier YELLOW = Erebus.prefix("textures/entity/tarantula_yellow.png");

    public BabyTarantulaRenderer(EntityRendererProvider.Context context) {
        super(context, new TarantulaBabyModel(context.bakeLayer(ModEntityRendering.TARANTULA_BABY)), 0.25F);
    }

    @Override
    public void extractRenderState(BabyTarantula entity, TarantulaBabyRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.skin = entity.getSkin();
    }

    @Override
    protected void scale(TarantulaBabyRenderState state, PoseStack pose) {
        pose.scale(0.3F, 0.3F, 0.3F);
    }

    @Override
    public TarantulaBabyRenderState createRenderState() {
        return new TarantulaBabyRenderState();
    }

    @Override
    public Identifier getTextureLocation(TarantulaBabyRenderState state) {
        return switch (state.skin) {
            case 1 -> TURQUOISE;
            case 2 -> YELLOW;
            default -> TEXTURE;
        };
    }
}
