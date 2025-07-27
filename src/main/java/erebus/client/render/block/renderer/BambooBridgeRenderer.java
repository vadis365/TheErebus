package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.bamboo.BambooBridge;
import erebus.block.entity.BambooBridgeBlockEntity;
import erebus.client.render.block.model.BambooBridgeModel;
import erebus.registries.blocks.providers.OtherBlocks;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BambooBridgeRenderer implements BlockEntityRenderer<BambooBridgeBlockEntity> {

	private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/bamboo_bridge.png");
	private final BambooBridgeModel model;

	public BambooBridgeRenderer(Context context) {
		model = new BambooBridgeModel(context.bakeLayer(ModBlockEntityRendering.BAMBOO_BRIDGE));
	}

	@Override
    public void render(BambooBridgeBlockEntity tile, float partialTick, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		if(tile == null || !tile.hasLevel())
			return;
		BlockState state = tile.getLevel().getBlockState(tile.getBlockPos());
		if(state == null || state.getBlock() != OtherBlocks.BAMBOO_BRIDGE.get())
			return;

		Direction facing = state.getValue(BambooBridge.FACING);
		VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutout(TEXTURE));
		stack.pushPose();
		stack.translate(0.5F, 1.5F, 0.5F);
		stack.scale(-1F, -1F, 1F);
		switch (facing) {
		case UP:
		case DOWN:
		case NORTH:
			stack.mulPose(Axis.YP.rotationDegrees(0F));
			break;
		case SOUTH:
			stack.mulPose(Axis.YP.rotationDegrees(180F));
			break;
		case WEST:
			stack.mulPose(Axis.YN.rotationDegrees(90F));
			break;
		case EAST:
			stack.mulPose(Axis.YP.rotationDegrees(90F));
			break;
		}
		model.renderWithTile(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF, tile);
		stack.popPose();
	}

}