package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import erebus.Erebus;
import erebus.client.render.entity.model.FireAntSoldierModel;
import erebus.client.render.entity.renderer.state.FireAntSoldierRenderState;
import erebus.entity.FireAntSoldier;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class FireAntSoldierRenderer extends MobRenderer<FireAntSoldier, FireAntSoldierRenderState, FireAntSoldierModel> {
    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/fire_ant_soldier.png");

    public FireAntSoldierRenderer(EntityRendererProvider.Context context) {
        super(context, new FireAntSoldierModel(context.bakeLayer(ModEntityRendering.FIRE_ANT_SOLDIER)), 0.5F);
    }

    @Override
    public void extractRenderState(FireAntSoldier entity, FireAntSoldierRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.climbing = entity.isClimbing();
    }

    @Override
    public FireAntSoldierRenderState createRenderState() {
        return new FireAntSoldierRenderState();
    }

    @Override
    protected void scale(FireAntSoldierRenderState state, PoseStack pose) {
        pose.scale(0.75F, 0.75F, 0.75F);
        if (state.climbing) pose.mulPose(Axis.XP.rotationDegrees(-90));
    }

    @Override
    public Identifier getTextureLocation(FireAntSoldierRenderState state) {
        return TEXTURE;
    }
}
