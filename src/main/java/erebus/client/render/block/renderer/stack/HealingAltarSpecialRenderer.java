package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.Erebus;
import erebus.client.render.block.model.altar.healing.HealingAltarBaseModel;
import erebus.client.render.block.model.altar.healing.HealingAltarMidModel;
import erebus.client.render.block.model.altar.healing.HealingAltarRoseModel;
import erebus.client.render.block.renderer.state.HealingAltarBlockEntityRenderState;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MaterialMapper;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemDisplayContext;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public final class HealingAltarSpecialRenderer implements NoDataSpecialModelRenderer {
	private final MaterialMapper MAPPER = new MaterialMapper(TextureAtlas.LOCATION_BLOCKS, "altar_healing");
	private final Material material = MAPPER.apply(Erebus.prefix("5"));
	private final HealingAltarBaseModel base;
	private final HealingAltarMidModel mid;
	private final HealingAltarRoseModel rose;
	private final MaterialSet materials;

	public HealingAltarSpecialRenderer(BakingContext context) {
		materials = context.materials();
		base = new HealingAltarBaseModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.ALTAR_HEALING_BASE));
		mid = new HealingAltarMidModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.ALTAR_HEALING_MID));
		rose = new HealingAltarRoseModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.ALTAR_HEALING_ROSE));
	}

	@Override
	public void submit(@NonNull ItemDisplayContext context, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		HealingAltarBlockEntityRenderState state = new HealingAltarBlockEntityRenderState();
		state.lightCoords = lightCoords;
		submit(base, state, pose, submit);
		submit(mid, state, pose, submit);
		submit(rose, state, pose, submit);
	}

	private void submit(Model<HealingAltarBlockEntityRenderState> model, HealingAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit) {
		pose.pushPose();
		pose.translate(0.5D, 0.75D, 0.5D);
		pose.scale(-0.5F, -0.5F, 0.5F);
		pose.scale(0.5F, 0.5F, 0.5F);
		submit.submitModel(model, state, pose, material.renderType(RenderTypes::entityCutout), state.lightCoords, OverlayTexture.NO_OVERLAY, -1, materials.get(material), 0, null);
		pose.popPose();
	}

	@Override
	public void getExtents(@NonNull Consumer<Vector3fc> consumer) {
		PoseStack pose = new PoseStack();
		base.setupAnim(new HealingAltarBlockEntityRenderState());
		base.root().getExtentsForGui(pose, consumer);
		mid.setupAnim(new HealingAltarBlockEntityRenderState());
		mid.root().getExtentsForGui(pose, consumer);
		rose.setupAnim(new HealingAltarBlockEntityRenderState());
		rose.root().getExtentsForGui(pose, consumer);
	}

	public record Unbaked(Identifier texture) implements SpecialModelRenderer.Unbaked {

		public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
				i -> i.group(
						Identifier.CODEC.fieldOf("texture").forGetter(HealingAltarSpecialRenderer.Unbaked::texture)
				).apply(i, HealingAltarSpecialRenderer.Unbaked::new)
		);

		@Override
		public @Nullable SpecialModelRenderer<?> bake(@NonNull BakingContext bakingContext) {
			return new HealingAltarSpecialRenderer(bakingContext);
		}

		@Override
		public @NonNull MapCodec<? extends SpecialModelRenderer.Unbaked> type() {
			return MAP_CODEC;
		}
	}
}
