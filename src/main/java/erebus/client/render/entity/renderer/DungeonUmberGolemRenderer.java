package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.UmberGolemModel;
import erebus.client.render.entity.renderer.state.UmberGolemRenderState;
import erebus.entity.DungeonUmberGolem;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class DungeonUmberGolemRenderer extends MobRenderer<DungeonUmberGolem, UmberGolemRenderState, UmberGolemModel> {
    private static final Identifier[] TEXTURES = {
            Erebus.prefix("textures/entity/umber_golem_mud.png"), Erebus.prefix("textures/entity/umber_golem_iron.png"),
            Erebus.prefix("textures/entity/umber_golem_gold.png"), Erebus.prefix("textures/entity/umber_golem_jade.png")
    };

    public DungeonUmberGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new UmberGolemModel(context.bakeLayer(ModEntityRendering.UMBER_GOLEM)), 1);
    }

    @Override
    public UmberGolemRenderState createRenderState() {
        return new UmberGolemRenderState();
    }

    @Override
    public void extractRenderState(DungeonUmberGolem entity, UmberGolemRenderState state, float partialTick) {
        super.extractRenderState(entity, state, partialTick);
        state.guardianVariant = entity.getVariant();
    }

    @Override
    public Identifier getTextureLocation(UmberGolemRenderState state) {
        return TEXTURES[state.guardianVariant];
    }

    @Override
    protected void scale(UmberGolemRenderState state, PoseStack pose) {
        pose.scale(1.4F, 1.4F, 1.4F);
    }
}
