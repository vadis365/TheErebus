package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import erebus.Erebus;
import erebus.block.altars.AltarAbstract;
import erebus.block.entity.ExperienceAltarBlockEntity;
import erebus.client.render.block.model.ExperienceAltarModel;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ExperienceAltarRenderer implements BlockEntityRenderer<ExperienceAltarBlockEntity> {
	private static final ResourceLocation[] TEXTURE = new ResourceLocation[] {
			Erebus.prefix("textures/special/tiles/altar_xp_1.png"),
			Erebus.prefix("textures/special/tiles/altar_xp_2.png"),
			Erebus.prefix("textures/special/tiles/altar_xp_3.png"),
			Erebus.prefix("textures/special/tiles/altar_xp_4.png"),
			Erebus.prefix("textures/special/tiles/altar_xp_5.png") };

	private final ExperienceAltarModel model;

	public ExperienceAltarRenderer(Context context) {
		model = new ExperienceAltarModel(context.bakeLayer(ModBlockEntityRendering.ALTAR_EXPERIENCE));
	}

	@Override
    public void render(ExperienceAltarBlockEntity tile, float partialTick, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		if(tile == null || !tile.hasLevel())
			return;
		Direction facing = tile.getBlockState().getValue(AltarAbstract.FACING);
		VertexConsumer consumer = buffer.getBuffer(RenderType.entitySolid(getAltarTexture(tile)));

		stack.pushPose();
		stack.translate(0.5D, 0.75D, 0.5D);
		stack.scale(0.5F, -0.5F, -0.5F);

		switch (facing) {
		case UP:
		case DOWN:
		case NORTH:
			stack.mulPose(Axis.YP.rotationDegrees(180F));
			break;
		case SOUTH:
			break;
		case WEST:
			stack.mulPose(Axis.YP.rotationDegrees(90F));
			break;
		case EAST:
			stack.mulPose(Axis.YN.rotationDegrees(90F));
			break;
		}

		model.renderWithTile(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF, tile, partialTick);
		stack.popPose();
	}

	protected ResourceLocation getAltarTexture(ExperienceAltarBlockEntity tile) {
		if (tile.animationTicks <= 4)
			return TEXTURE[0];
		else if (tile.animationTicks > 4 && tile.animationTicks <= 8)
			return TEXTURE[1];
		else if (tile.animationTicks > 8 && tile.animationTicks <= 12)
			return TEXTURE[2];
		else if (tile.animationTicks > 12 && tile.animationTicks <= 16)
			return TEXTURE[3];
		else if (tile.animationTicks > 16 && tile.animationTicks <= 20)
			return TEXTURE[4];
		else
			return TEXTURE[0];
	}
}