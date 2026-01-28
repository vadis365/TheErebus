package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import erebus.Erebus;
import erebus.client.render.entity.model.BedBugModel;
import erebus.client.render.entity.renderer.state.BedBugRenderState;
import erebus.entity.BedBug;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

public class BedBugRenderer extends MobRenderer<BedBug, BedBugRenderState, BedBugModel> {

	public BedBugRenderer(EntityRendererProvider.Context context) {
		super(context, new BedBugModel(context.bakeLayer(ModEntityRendering.BED_BUG)), 0.5F);
		this.model = new BedBugModel(context.bakeLayer(ModEntityRendering.BED_BUG));
	}

	@Override
	public BedBugRenderState createRenderState() {
		return new BedBugRenderState();
	}

	@Override
	protected void scale(BedBugRenderState state, PoseStack pose) {
		pose.scale(0.5F, 0.5F, 0.5F);
	}

	@Override
	public @NonNull Identifier getTextureLocation(BedBugRenderState state) {
		return Erebus.prefix("textures/entity/bed_bug.png");
	}
}
