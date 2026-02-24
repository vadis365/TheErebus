package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.TitanBeetleModel;
import erebus.client.render.entity.renderer.state.TitanBeetleRenderState;
import erebus.entity.TitanBeetle;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class TitanBeetleRenderer extends MobRenderer<TitanBeetle, TitanBeetleRenderState, TitanBeetleModel> {
	private static final Identifier DEFAULT = Erebus.prefix("textures/entity/titan_beetle.png");
	private static final Identifier SADDLE = Erebus.prefix("textures/entity/titan_beetle_kit.png");
	private static final Identifier CHEST = Erebus.prefix("textures/entity/titan_beetle_chested.png");
	private static final Identifier ENDER_CHEST = Erebus.prefix("textures/entity/titan_beetle_ender_chested.png");


	public TitanBeetleRenderer(EntityRendererProvider.Context context) {
        super(context, new TitanBeetleModel(context.bakeLayer(ModEntityRendering.TITAN_BEETLE)), 1.2F);
	}

	@Override
	public void extractRenderState(TitanBeetle entity, TitanBeetleRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.hasSaddle = entity.hasSaddle();
		state.hasChest = entity.hasChest();
		state.hasEnderChest = entity.hasEnderChest();
		state.isTame = entity.isTame();
		state.smoothedTicks = entity.getPrevOpenTicks() + (entity.getOpenTicks() - entity.getPrevOpenTicks()) * partialTicks;
		state.smoothedTicks = 1.0F - state.smoothedTicks;
		state.smoothedTicks = (float) (1.0F - Math.pow(state.smoothedTicks, 3));
	}

	@Override
	public TitanBeetleRenderState createRenderState() {
		return new TitanBeetleRenderState();
	}

	@Override
	protected void scale(TitanBeetleRenderState state, PoseStack pose) {
		pose.scale(1.5F, 1.5F, 1.5F);
	}

	@Override
	public @NonNull Identifier getTextureLocation(TitanBeetleRenderState state) {
		if(state.hasChest) return CHEST;
		if(state.hasEnderChest) return ENDER_CHEST;
		if(state.hasSaddle) return SADDLE;
		return DEFAULT;
	}
}
