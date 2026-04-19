package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.Erebus;
import erebus.client.render.block.model.altar.lightning.LightningAltarBaseModel;
import erebus.client.render.block.model.altar.lightning.LightningAltarElectrodeModel;
import erebus.client.render.block.model.altar.lightning.LightningAltarMidModel;
import erebus.client.render.block.renderer.state.LightningAltarBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.SpriteMapper;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public final class LightningAltarSpecialRenderer implements NoDataSpecialModelRenderer {
	private final SpriteMapper MAPPER = new SpriteMapper(TextureAtlas.LOCATION_BLOCKS, "altar_lightning");
	private final SpriteId material = MAPPER.apply(Erebus.prefix("5"));
	private final LightningAltarBaseModel base;
	private final LightningAltarMidModel mid;
	private final LightningAltarElectrodeModel electrode;
	private final SpriteGetter sprites;

	public LightningAltarSpecialRenderer(BakingContext context) {
		sprites = context.sprites();
		this.base = new LightningAltarBaseModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.ALTAR_LIGHTNING_BASE));
		this.mid = new LightningAltarMidModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.ALTAR_LIGHTNING_MID));
		this.electrode = new LightningAltarElectrodeModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.ALTAR_LIGHTNING_ELECTRODE));
	}

	@Override
	public void submit(PoseStack pose, SubmitNodeCollector submit, int light, int overlay, boolean hasFoil, int outlineColor) {
		LightningAltarBlockEntityRenderState state = new LightningAltarBlockEntityRenderState();
		state.lightCoords = light;
		submit(base, state, pose, submit);
		submit(mid, state, pose, submit);
		submit(electrode, state, pose, submit);
	}

	private void submit(Model<LightningAltarBlockEntityRenderState> model, LightningAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit) {
		pose.pushPose();
		pose.translate(0.5D, 0.75D, 0.5D);
		pose.scale(-0.5F, -0.5F, 0.5F);
		pose.scale(0.5F, 0.5F, 0.5F);
		submit.submitModel(model, state, pose, material.renderType(RenderTypes::entityCutout), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, sprites.get(material), 0, null);
		pose.popPose();
	}

	@Override
	public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
		PoseStack pose = new PoseStack();
		base.setupAnim(new LightningAltarBlockEntityRenderState());
		base.root().getExtentsForGui(pose, consumer);
		mid.setupAnim(new LightningAltarBlockEntityRenderState());
		mid.root().getExtentsForGui(pose, consumer);
		electrode.setupAnim(new LightningAltarBlockEntityRenderState());
		electrode.root().getExtentsForGui(pose, consumer);
	}

	public record Unbaked(Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {

		public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
				i -> i.group(
						Identifier.CODEC.fieldOf("texture").forGetter(LightningAltarSpecialRenderer.Unbaked::texture)
				).apply(i, LightningAltarSpecialRenderer.Unbaked::new)
		);

		@Override
		public @NonNull SpecialModelRenderer<Void> bake(@NonNull BakingContext bakingContext) {
			return new LightningAltarSpecialRenderer(bakingContext);
		}

		@Override
		public @NonNull MapCodec<Unbaked> type() {
			return MAP_CODEC;
		}
	}
}
