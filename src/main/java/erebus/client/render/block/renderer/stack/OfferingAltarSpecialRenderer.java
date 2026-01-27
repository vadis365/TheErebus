package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.client.render.block.model.OfferingAltarModel;
import erebus.client.render.block.renderer.state.OfferingAltarBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public final class OfferingAltarSpecialRenderer implements NoDataSpecialModelRenderer {
	private final OfferingAltarModel model;
	private final Identifier texture;

	public OfferingAltarSpecialRenderer(OfferingAltarModel model, Identifier texture) {
		this.model = model;
		this.texture = texture;
	}

	@Override
	public void submit(@NonNull ItemDisplayContext context, PoseStack pose, SubmitNodeCollector submit, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		pose.pushPose();
		pose.translate(0.5D, 1.5D, 0.5D);
		pose.scale(-1, -1, 1);
		submit.submitModel(
				model,
				new OfferingAltarBlockEntityRenderState(),
				pose,
				RenderTypes.entitySolid(texture),
				lightCoords,
				overlayCoords,
				outlineColor,
				null
		);
		pose.popPose();
	}

	@Override
	public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
		PoseStack poseStack = new PoseStack();
		model.setupAnim(new OfferingAltarBlockEntityRenderState());
		model.root().getExtentsForGui(poseStack, consumer);
	}

	public record Unbaked(Identifier texture) implements SpecialModelRenderer.Unbaked {

		public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
				i -> i.group(
						Identifier.CODEC.fieldOf("texture").forGetter(OfferingAltarSpecialRenderer.Unbaked::texture)
				).apply(i, OfferingAltarSpecialRenderer.Unbaked::new)
		);

		@Override
		public @Nullable SpecialModelRenderer<?> bake(BakingContext bakingContext) {
			return new OfferingAltarSpecialRenderer(
					new OfferingAltarModel(
							bakingContext
									.entityModelSet()
									.bakeLayer(ModBlockEntityRendering.OFFERING_ALTAR)
					),
					texture
			);
		}

		@Override
		public @NonNull MapCodec<? extends SpecialModelRenderer.Unbaked> type() {
			return MAP_CODEC;
		}
	}
}
