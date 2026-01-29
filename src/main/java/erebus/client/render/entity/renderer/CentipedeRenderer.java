package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.CentipedeModel;
import erebus.client.render.entity.renderer.state.CentipedeRenderState;
import erebus.entity.Centipede;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import javax.annotation.Nullable;

public class CentipedeRenderer extends MobRenderer<Centipede, CentipedeRenderState, CentipedeModel> {
	private static final Identifier[] TEXTURES = new Identifier[] {
			Erebus.prefix("textures/entity/centipede.png"),
			Erebus.prefix("textures/entity/centipede_light.png"),
			Erebus.prefix("textures/entity/centipede_black.png")
	};

	public CentipedeRenderer(EntityRendererProvider.Context context) {
		super(context, new CentipedeModel(context.bakeLayer(ModEntityRendering.CENTIPEDE)), 0F);
	}

	@Override
	public void submit(CentipedeRenderState state, @NonNull PoseStack pose, SubmitNodeCollector submit, @NonNull CameraRenderState camera) {
		boolean isVisible = isBodyVisible(state);
		boolean isTranslucentToPlayer = !isVisible && !state.isInvisibleToPlayer;
		int overlay = getOverlayCoords(state, this.getWhiteOverlayProgress(state));
		int colour = isTranslucentToPlayer ? 654311423 : -1;
		RenderType renderType = getRenderType(state, isVisible, isTranslucentToPlayer, state.appearsGlowing());

		submit.submitModel(
				model,
				state,
				pose,
				renderType,
				state.lightCoords,
				overlay,
				colour,
				null,
				state.outlineColor,
				null
		);
	}

	@Override
	public CentipedeRenderState createRenderState() {
		return new CentipedeRenderState();
	}

	@Override
	public void extractRenderState(Centipede entity, CentipedeRenderState state, float partialTicks) {
		state.skin = entity.getSkin();
	}

	@Nullable
	protected RenderType getRenderType(CentipedeRenderState state, boolean isVisible, boolean isTranslucentToPlayer, boolean isGlowing) {
		if (isTranslucentToPlayer)
			return RenderTypes.entityTranslucent(getTextureLocation(state));
		else if (isVisible)
			return RenderTypes.entityCutout(getTextureLocation(state));
		else
			return isGlowing ? RenderTypes.outline(getTextureLocation(state)) : null;
	}

	@Override
	public @NonNull Identifier getTextureLocation(CentipedeRenderState state) {
		return TEXTURES[state.skin];
	}
}
