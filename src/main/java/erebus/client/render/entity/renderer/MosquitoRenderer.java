package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.MosquitoModel;
import erebus.client.render.entity.model.layer.mosquito.*;
import erebus.client.render.entity.renderer.layer.mosquito.*;
import erebus.client.render.entity.renderer.state.MosquitoRenderState;
import erebus.entity.Mosquito;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jspecify.annotations.NonNull;

public class MosquitoRenderer extends MobRenderer<Mosquito, MosquitoRenderState, MosquitoModel> {
    private static final Identifier TEXTURE = Erebus.prefix("textures/entity/mosquito.png");

    public MosquitoRenderer(EntityRendererProvider.Context context) {
        super(context, new MosquitoModel(context.bakeLayer(ModEntityRendering.MOSQUITO)), 0.5F);
        addLayer(new MosquitoAppendageLayer(this, new MosquitoAppendageModel(context.bakeLayer(ModEntityRendering.MOSQUITO_APPENDAGE))));
        addLayer(new MosquitoHeadLayer(this, new MosquitoHeadModel(context.bakeLayer(ModEntityRendering.MOSQUITO_HEAD))));
        addLayer(new MosquitoHeadRidingLayer(this, new MosquitoHeadRidingModel(context.bakeLayer(ModEntityRendering.MOSQUITO_HEAD_RIDING))));
        addLayer(new MosquitoRidingLayer(this, new MosquitoRidingModel(context.bakeLayer(ModEntityRendering.MOSQUITO_RIDING))));
        addLayer(new MosquitoWingsLayer(this, new MosquitoWingsModel(context.bakeLayer(ModEntityRendering.MOSQUITO_WINGS))));
    }

    @Override
    protected void scale(MosquitoRenderState state, PoseStack pose) {
        pose.scale(0.5F, 0.5F, 0.5F);
        pose.translate(0, -1.4F, state.isRiding ? 0 : -0.5F);
    }

    @Override
    public void extractRenderState(Mosquito entity, MosquitoRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.wingAngle = Mth.lerp(partialTicks, entity.wingFloatO, entity.wingFloat);
        state.isRiding = entity.isFeeding();
        state.blood = entity.getBloodConsumed() / 10F;
        state.suck = Mth.lerp(partialTicks, entity.suckFloatO, entity.suckFloat);
    }

    @Override
    public MosquitoRenderState createRenderState() {
        return new MosquitoRenderState();
    }

    @Override
    public @NonNull Identifier getTextureLocation(MosquitoRenderState state) {
        return TEXTURE;
    }
}
