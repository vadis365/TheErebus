package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.client.render.entity.model.GrasshopperModel;
import erebus.client.render.entity.renderer.state.GrasshopperRenderState;
import erebus.entity.Grasshopper;
import erebus.registries.entity.ModEntityRendering;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import org.jspecify.annotations.NonNull;

public class GrasshopperRenderer extends  MobRenderer<Grasshopper, GrasshopperRenderState, GrasshopperModel> {
	private static final Identifier TEXTURE = Erebus.prefix("textures/entity/grasshopper.png");

	public GrasshopperRenderer(EntityRendererProvider.Context context) {
		super(context, new GrasshopperModel(context.bakeLayer(ModEntityRendering.GRASSHOPPER)), 0.5F);
	}
	
	@Override
	protected void scale(GrasshopperRenderState state, PoseStack pose) {
		float jumpAngle = Mth.sin(state.jump * (float) Math.PI);
		pose.mulPose(Axis.XP.rotation(-jumpAngle * 10.0F * (float) (Math.PI / 180.0)));
	}

	@Override
	public GrasshopperRenderState createRenderState() {
		return new GrasshopperRenderState();
	}

	@Override
	public void extractRenderState(Grasshopper entity, GrasshopperRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.jump = entity.getJumpCompletion(partialTicks);
	}

	@Override
	public @NonNull Identifier getTextureLocation(GrasshopperRenderState state) {
		return TEXTURE;
	}
}
