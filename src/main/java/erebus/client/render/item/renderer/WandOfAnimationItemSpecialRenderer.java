package erebus.client.render.item.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import erebus.Erebus;
import erebus.client.render.item.model.WandOfAnimationItemModel;
import erebus.registries.client.ModItemRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class WandOfAnimationItemSpecialRenderer implements NoDataSpecialModelRenderer {

	private final WandOfAnimationItemModel model;

	public WandOfAnimationItemSpecialRenderer(WandOfAnimationItemModel model) {
		this.model = model;
	}

	@Override
	public void submit(@NonNull ItemDisplayContext context, PoseStack pose, SubmitNodeCollector submit, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		pose.pushPose();
		pose.scale(0.9999F, 0.9999F, 0.9999F);
		WandOfAnimationItemModel.State state = new WandOfAnimationItemModel.State();
		state.animationTick = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

		submit.submitModel(
				model,
                state,
                pose,
				model.renderType(Erebus.prefix("textures/special/items/wand_of_animation.png")),
				lightCoords,
				overlayCoords,
				-1,
				null,
                outlineColor,
				null
			);
		
		pose.popPose();
	}

	@Override
	public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
		PoseStack pose = new PoseStack();
		pose.scale(0.9999F, 0.9999F, 0.9999F);
		
		WandOfAnimationItemModel.State state = new WandOfAnimationItemModel.State();
		state.animationTick = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
		model.setupAnim(state);
		model.root().getExtentsForGui(pose, consumer);
	}

	public record Unbaked() implements SpecialModelRenderer.Unbaked {

		public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(new Unbaked());

		@Override
		public @NonNull SpecialModelRenderer<?> bake(BakingContext context) {
			return new WandOfAnimationItemSpecialRenderer(new WandOfAnimationItemModel(context.entityModelSet().bakeLayer(ModItemRendering.WAND_OF_ANIMATION)));
		}

		@Override
		public @NonNull MapCodec<? extends SpecialModelRenderer.Unbaked> type() {
			return MAP_CODEC;
		}
	}

}
