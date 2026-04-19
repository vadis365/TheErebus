package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.Erebus;
import erebus.client.render.block.model.altar.experience.ExperienceAltarBaseModel;
import erebus.client.render.block.model.altar.experience.ExperienceAltarGlassModel;
import erebus.client.render.block.model.altar.experience.ExperienceAltarMidModel;
import erebus.client.render.block.renderer.state.ExperienceAltarBlockEntityRenderState;
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

public final class ExperienceAltarSpecialRenderer implements NoDataSpecialModelRenderer {
	private final SpriteMapper MAPPER = new SpriteMapper(TextureAtlas.LOCATION_BLOCKS, "altar_xp");
	private final SpriteId material = MAPPER.apply(Erebus.prefix("5"));
	private final ExperienceAltarBaseModel base;
	private final ExperienceAltarMidModel mid;
	private final ExperienceAltarGlassModel electrode;
	private final SpriteGetter sprites;

	public ExperienceAltarSpecialRenderer(BakingContext context) {
		sprites = context.sprites();
		this.base = new ExperienceAltarBaseModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.ALTAR_EXPERIENCE_BASE));
		this.mid = new ExperienceAltarMidModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.ALTAR_EXPERIENCE_MID));
		this.electrode = new ExperienceAltarGlassModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.ALTAR_EXPERIENCE_GLASS));
	}

	@Override
	public void submit(PoseStack pose, SubmitNodeCollector submit, int light, int overlay, boolean hasFoil, int outlineColor) {
		ExperienceAltarBlockEntityRenderState state = new ExperienceAltarBlockEntityRenderState();
		state.lightCoords = light;
		submit(base, state, pose, submit);
		submit(mid, state, pose, submit);
		submit(electrode, state, pose, submit);
	}

	private void submit(Model<ExperienceAltarBlockEntityRenderState> model, ExperienceAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit) {
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
		base.setupAnim(new ExperienceAltarBlockEntityRenderState());
		base.root().getExtentsForGui(pose, consumer);
		mid.setupAnim(new ExperienceAltarBlockEntityRenderState());
		mid.root().getExtentsForGui(pose, consumer);
		electrode.setupAnim(new ExperienceAltarBlockEntityRenderState());
		electrode.root().getExtentsForGui(pose, consumer);
	}

	public record Unbaked(Identifier texture) implements NoDataSpecialModelRenderer.Unbaked {

		public static final MapCodec<ExperienceAltarSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
				i -> i.group(
						Identifier.CODEC.fieldOf("texture").forGetter(ExperienceAltarSpecialRenderer.Unbaked::texture)
				).apply(i, ExperienceAltarSpecialRenderer.Unbaked::new)
		);

		@Override
		public @NonNull SpecialModelRenderer<Void> bake(@NonNull BakingContext bakingContext) {
			return new ExperienceAltarSpecialRenderer(bakingContext);
		}

		@Override
		public @NonNull MapCodec<Unbaked> type() {
			return MAP_CODEC;
		}
	}
}
