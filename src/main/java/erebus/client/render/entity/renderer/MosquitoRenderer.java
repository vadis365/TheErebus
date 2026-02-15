package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.MosquitoModel;
import erebus.client.render.entity.model.layer.mosquito.*;
import erebus.client.render.entity.renderer.layer.mosquito.*;
import erebus.client.render.entity.renderer.state.MosquitoRenderState;
import erebus.entity.Mosquito;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class MosquitoRenderer extends MobRenderer<Mosquito, MosquitoRenderState, MosquitoModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/mosquito.png");
	private final MosquitoModel model;

	public MosquitoRenderer(EntityRendererProvider.Context context) {
        super(context, new MosquitoModel(context.bakeLayer(ModEntityRendering.MOSQUITO)), 0.5F);
		model = new MosquitoModel(context.bakeLayer(ModEntityRendering.MOSQUITO));
		addLayer(new MosquitoAppendageLayer(this, new MosquitoAppendageModel(context.bakeLayer(ModEntityRendering.MOSQUITO_APPENDAGE))));
		addLayer(new MosquitoHeadLayer(this, new MosquitoHeadModel(context.bakeLayer(ModEntityRendering.MOSQUITO_HEAD))));
		addLayer(new MosquitoHeadRidingLayer(this, new MosquitoHeadRidingModel(context.bakeLayer(ModEntityRendering.MOSQUITO_HEAD_RIDING))));
		addLayer(new MosquitoRidingLayer(this, new MosquitoRidingModel(context.bakeLayer(ModEntityRendering.MOSQUITO_RIDING))));
		addLayer(new MosquitoWingsLayer(this, new MosquitoWingsModel(context.bakeLayer(ModEntityRendering.MOSQUITO_WINGS))));
	}

	@Override
	public void submit(MosquitoRenderState state, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
		super.submit(state, pose, submit, camera);

		pose.pushPose();
		pose.scale(state.blood, 1, 1);
		submit.submitModel(model, state, pose, model.renderType(TEXTURE), state.lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor, null);
		pose.popPose();
	}

	@Override
	public void extractRenderState(Mosquito entity, MosquitoRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.wingAngle = entity.wingFloat;
		state.isRiding = entity.isPassenger();
		state.blood = entity.getBloodConsumed();
		state.suck = entity.suckFloat;
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
