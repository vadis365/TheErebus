package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.WorkerBeeModel;
import erebus.entity.WorkerBee;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WorkerBeeRenderer extends MobRenderer<WorkerBee, WorkerBeeModel<WorkerBee>> {
	public static final ResourceLocation TEXTURE = Erebus.prefix("textures/entity/worker_bee.png");

	public WorkerBeeRenderer(EntityRendererProvider.Context context) {
        super(context, new WorkerBeeModel<>(context.bakeLayer(ModEntityRendering.WORKER_BEE)), 0.5F);
	}

	@Override
	protected void scale(WorkerBee bee, PoseStack matrix, float partialTickTime) {
		matrix.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public  ResourceLocation getTextureLocation(WorkerBee bee) {
		return TEXTURE;
	}
}