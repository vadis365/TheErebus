package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.WorkerBeeModel;
import erebus.client.render.entity.renderer.state.WorkerBeeRenderState;
import erebus.entity.WorkerBee;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class WorkerBeeRenderer extends MobRenderer<WorkerBee, WorkerBeeRenderState, WorkerBeeModel> {
	public static final Identifier TEXTURE = Erebus.prefix("textures/entity/worker_bee.png");

	public WorkerBeeRenderer(EntityRendererProvider.Context context) {
		super(context, new WorkerBeeModel(context.bakeLayer(ModEntityRendering.WORKER_BEE)), 0.5F);
	}

	@Override
	public WorkerBeeRenderState createRenderState() {
		return new WorkerBeeRenderState();
	}

	@Override
	public void extractRenderState(WorkerBee entity, WorkerBeeRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
	}

	@Override
	protected void scale(WorkerBeeRenderState state, PoseStack matrix) {
		matrix.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public @NonNull Identifier getTextureLocation(WorkerBeeRenderState state) {
		return TEXTURE;
	}
}
