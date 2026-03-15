package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.Erebus;
import erebus.client.render.block.model.OfferingAltarModel;
import erebus.client.render.block.renderer.state.OfferingAltarBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public final class OfferingAltarSpecialRenderer implements NoDataSpecialModelRenderer {
	private final OfferingAltarModel model;
	private final SpriteGetter sprites;

	public OfferingAltarSpecialRenderer(BakingContext context) {
		this.model = new OfferingAltarModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.OFFERING_ALTAR));
		this.sprites = context.sprites();
	}

	@Override
	public void submit(@NonNull ItemDisplayContext context, PoseStack pose, SubmitNodeCollector submit, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		OfferingAltarBlockEntityRenderState state = new OfferingAltarBlockEntityRenderState();
		SpriteId sprite = Sheets.BLOCKS_MAPPER.apply(Erebus.prefix("offering_altar"));

		pose.pushPose();
		pose.translate(0.5D, 1D, 0.5D);
		pose.scale(-0.5F, -0.5F, 0.5F);
		submit.submitModel(model, state, pose, sprite.renderType(RenderTypes::entitySolid), lightCoords, OverlayTexture.NO_OVERLAY, -1, sprites.get(sprite), 0, null);
		pose.popPose();
	}

	@Override
	public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
		PoseStack poseStack = new PoseStack();
		model.setupAnim(new OfferingAltarBlockEntityRenderState());
		model.root().getExtentsForGui(poseStack, consumer);
	}

	public record Unbaked(Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {

		public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
				i -> i.group(
						Identifier.CODEC.fieldOf("texture").forGetter(OfferingAltarSpecialRenderer.Unbaked::texture)
				).apply(i, OfferingAltarSpecialRenderer.Unbaked::new)
		);

		@Override
		public @NonNull SpecialModelRenderer<Void> bake(@NonNull BakingContext bakingContext) {
			return new OfferingAltarSpecialRenderer(bakingContext);
		}

		@Override
		public @NonNull MapCodec<Unbaked> type() {
			return MAP_CODEC;
		}
	}
}
