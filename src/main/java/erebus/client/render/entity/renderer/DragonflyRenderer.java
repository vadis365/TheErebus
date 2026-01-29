package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.DragonflyModel;
import erebus.client.render.entity.renderer.layer.DragonflyLayer;
import erebus.client.render.entity.renderer.state.DragonflyRenderState;
import erebus.entity.Dragonfly;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class DragonflyRenderer extends MobRenderer<Dragonfly, DragonflyRenderState, DragonflyModel>{

	public static final Identifier TEXTURE_1 = Erebus.prefix("textures/entity/dragonfly_ender.png");
	public static final Identifier TEXTURE_2 = Erebus.prefix("textures/entity/dragonfly_green.png");
	public static final Identifier TEXTURE_3 = Erebus.prefix("textures/entity/dragonfly_red.png");
	public static final Identifier TEXTURE_4 = Erebus.prefix("textures/entity/dragonfly_purple.png");
	public static final Identifier TEXTURE_5 = Erebus.prefix("textures/entity/dragonfly_blue.png");
	public static final Identifier TEXTURE_6 = Erebus.prefix("textures/entity/dragonfly_tan.png");

	public DragonflyRenderer(EntityRendererProvider.Context context) {
		super(context, new DragonflyModel(context.bakeLayer(ModEntityRendering.DRAGON_FLY)), 0.3F);
		addLayer(new DragonflyLayer(this, context.getModelSet()));
	}

	@Override
	protected void scale(DragonflyRenderState state, PoseStack matrix) {
		matrix.scale(1.0F, 1.0F, 1.0F);
		// Other sizes to be added
	}

	@Override
	public DragonflyRenderState createRenderState() {
		return new DragonflyRenderState();
	}

	@Override
	public void extractRenderState(Dragonfly entity, DragonflyRenderState state, float partialTicks) {
		state.skin = entity.getSkin();
	}

	@Override
	public @NonNull Identifier getTextureLocation(DragonflyRenderState state) {
		if (state.skin > 0 && state.skin <= 10)
			return TEXTURE_2;
		else if (state.skin > 10 && state.skin <= 20)
			return TEXTURE_3;
		else if (state.skin > 20 && state.skin <= 30)
			return TEXTURE_4;
		else if (state.skin > 30 && state.skin <= 40)
			return TEXTURE_5;
		else if (state.skin > 40 && state.skin <= 50)
			return TEXTURE_6;
		else if (state.skin == 0)
			return TEXTURE_1;
		else
			return TEXTURE_1;
	}
}
