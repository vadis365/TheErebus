package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.BlackAntModel;
import erebus.client.render.entity.model.layer.BlackAntCollectorModel;
import erebus.client.render.entity.model.layer.BlackAntFertilizerModel;
import erebus.client.render.entity.model.layer.BlackAntHarvesterModel;
import erebus.client.render.entity.model.layer.BlackAntPlanterModel;
import erebus.client.render.entity.renderer.layer.BlackAntLayer;
import erebus.client.render.entity.renderer.state.BlackAntRenderState;
import erebus.entity.BlackAnt;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class BlackAntRenderer extends MobRenderer<BlackAnt, BlackAntRenderState, BlackAntModel> {

	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/black_ant_kit.png");

	public BlackAntRenderer(EntityRendererProvider.Context context) {
		super(context, new BlackAntModel(context.bakeLayer(ModEntityRendering.BLACK_ANT)), 0.5F);
		addLayer(
				new BlackAntLayer(
						this,
						new BlackAntCollectorModel(context.bakeLayer(ModEntityRendering.BLACK_ANT_COLLECTOR)),
						new BlackAntFertilizerModel(context.bakeLayer(ModEntityRendering.BLACK_ANT_FERTILIZER)),
						new BlackAntHarvesterModel(context.bakeLayer(ModEntityRendering.BLACK_ANT_HARVESTER)),
						new BlackAntPlanterModel(context.bakeLayer(ModEntityRendering.BLACK_ANT_PLANTER))
				)
		);
	}

	@Override
	protected void scale(BlackAntRenderState state, PoseStack pose) {
		pose.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public BlackAntRenderState createRenderState() {
		return new BlackAntRenderState();
	}

	@Override
	public void extractRenderState(BlackAnt entity, BlackAntRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.isCollector = entity.getAntRole() == entity.COLLECTOR;
		state.isFertilizer = entity.getAntRole() == entity.FERTILIZER;
		state.isHarvester = entity.getAntRole() == entity.HARVESTER;
		state.isPlanter = entity.getAntRole() == entity.PLANTER;
	}

	@Override
	public @NonNull Identifier getTextureLocation(BlackAntRenderState state) {
		return TEXTURE;
	}
}
