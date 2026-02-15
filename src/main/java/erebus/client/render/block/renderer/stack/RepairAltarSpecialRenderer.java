package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erebus.Erebus;
import erebus.client.render.block.model.altar.repair.RepairAltarAnvilModel;
import erebus.client.render.block.model.altar.repair.RepairAltarBaseModel;
import erebus.client.render.block.model.altar.repair.RepairAltarMidModel;
import erebus.client.render.block.renderer.state.RepairAltarBlockEntityRenderState;
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

public final class RepairAltarSpecialRenderer implements NoDataSpecialModelRenderer {
	private final MaterialMapper MAPPER = new MaterialMapper(TextureAtlas.LOCATION_BLOCKS, "altar_repair");
	private final Material material = MAPPER.apply(Erebus.prefix("5"));
	private final RepairAltarBaseModel base;
	private final RepairAltarMidModel mid;
	private final RepairAltarAnvilModel anvil;
	private final MaterialSet materials;

	public RepairAltarSpecialRenderer(BakingContext context) {
		materials = context.materials();
		this.base = new RepairAltarBaseModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.ALTAR_REPAIR_BASE));
		this.mid = new RepairAltarMidModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.ALTAR_REPAIR_MID));
		this.anvil = new RepairAltarAnvilModel(context.entityModelSet().bakeLayer(ModBlockEntityRendering.ALTAR_REPAIR_ANVIL));
	}

	@Override
	public void submit(@NonNull ItemDisplayContext context, @NonNull PoseStack pose, @NonNull SubmitNodeCollector submit, int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		RepairAltarBlockEntityRenderState state = new RepairAltarBlockEntityRenderState();
		state.lightCoords = lightCoords;
		submit(base, state, pose, submit);
		submit(mid, state, pose, submit);
		submit(anvil, state, pose, submit);
	}

	private void submit(Model<RepairAltarBlockEntityRenderState> model, RepairAltarBlockEntityRenderState state, PoseStack pose, SubmitNodeCollector submit) {
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
		base.setupAnim(new RepairAltarBlockEntityRenderState());
		base.root().getExtentsForGui(pose, consumer);
		mid.setupAnim(new RepairAltarBlockEntityRenderState());
		mid.root().getExtentsForGui(pose, consumer);
		anvil.setupAnim(new RepairAltarBlockEntityRenderState());
		anvil.root().getExtentsForGui(pose, consumer);
	}

	public record Unbaked(Identifier texture) implements SpecialModelRenderer.Unbaked {

		public static final MapCodec<RepairAltarSpecialRenderer.Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(
				i -> i.group(
						Identifier.CODEC.fieldOf("texture").forGetter(RepairAltarSpecialRenderer.Unbaked::texture)
				).apply(i, RepairAltarSpecialRenderer.Unbaked::new)
		);

		@Override
		public @Nullable SpecialModelRenderer<?> bake(@NonNull BakingContext bakingContext) {
			return new RepairAltarSpecialRenderer(bakingContext);
		}

		@Override
		public @NonNull MapCodec<? extends SpecialModelRenderer.Unbaked> type() {
			return MAP_CODEC;
		}
	}
}
