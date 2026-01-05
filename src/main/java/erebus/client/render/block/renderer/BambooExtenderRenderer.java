package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.bamboo.BambooExtender;
import erebus.block.entity.BambooExtenderBlockEntity;
import erebus.client.render.block.model.BambooExtenderModel;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.data.ModelData;

@OnlyIn(Dist.CLIENT)
public class BambooExtenderRenderer implements BlockEntityRenderer<BambooExtenderBlockEntity> {

	private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/bamboo_extender.png");
	private final BambooExtenderModel model;
	private final BlockRenderDispatcher blockRenderDispatcher;
	
	public BambooExtenderRenderer(Context context) {
		model = new BambooExtenderModel(context.bakeLayer(ModBlockEntityRendering.BAMBOO_EXTENDER));
		blockRenderDispatcher = context.getBlockRenderDispatcher();
	}
	
	@Override
	public void render(BambooExtenderBlockEntity tile, float partialTick, PoseStack stack, MultiBufferSource buffer, int packedLight, int packedOverlay) {
		if(tile == null || !tile.hasLevel())
			return;
		BlockState state = tile.getLevel().getBlockState(tile.getBlockPos());
		if(state == null || state.getBlock() != ModBlocks.BAMBOO_EXTENDER.get())
			return;

		VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutout(TEXTURE));
		Direction facing = state.getValue(BambooExtender.FACING);

		switch (facing) {
			case DOWN:
				stack.pushPose();
				stack.translate( 0.5F, -0.5F, 0.5F);
				stack.scale(-1F, -1F, 1F);
				stack.mulPose(Axis.ZP.rotationDegrees(180F));
				stack.mulPose(Axis.YN.rotationDegrees(180F));
				model.render2(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
				stack.popPose();
				stack.pushPose();
				stack.translate(0F, 0.125F, 0F);
				stack.scale(1F, 0.875F, 1F);
				blockRenderDispatcher.renderSingleBlock(ModBlocks.PLANKS_BAMBOO.get().defaultBlockState(), stack, buffer, packedLight, packedOverlay, ModelData.EMPTY, null);
				stack.popPose();
				break;
			case UP:
				stack.pushPose();
				stack.translate(0.5F, 1.5F, 0.5F);
				stack.scale(-1F, -1F, 1F);
				stack.mulPose(Axis.YP.rotationDegrees(180F));
				model.render2(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
				stack.popPose();
				stack.pushPose();
				stack.scale(1F, 0.875F, 1F);
				blockRenderDispatcher.renderSingleBlock(ModBlocks.PLANKS_BAMBOO.get().defaultBlockState(), stack, buffer, packedLight, packedOverlay, ModelData.EMPTY, null);
				stack.popPose();
				break;
			case NORTH:
				stack.pushPose();
				stack.translate(0.5F, 1.5F, 0.5F);
				stack.scale(-1F, -1F, 1F);
				stack.mulPose(Axis.YP.rotationDegrees(0F));
				model.renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
				stack.popPose();
				break;
			case SOUTH:
				stack.pushPose();
				stack.translate(0.5F, 1.5F, 0.5F);
				stack.scale(-1F, -1F, 1F);
				stack.mulPose(Axis.YP.rotationDegrees(180F));
				model.renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
				stack.popPose();
				break;
			case WEST:
				stack.pushPose();
				stack.translate(0.5F, 1.5F, 0.5F);
				stack.scale(-1F, -1F, 1F);
				stack.mulPose(Axis.YN.rotationDegrees(90F));
				model.renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
				stack.popPose();
				break;
			case EAST:
				stack.pushPose();
				stack.translate(0.5F, 1.5F, 0.5F);
				stack.scale(-1F, -1F, 1F);
				stack.mulPose(Axis.YP.rotationDegrees(90F));
				model.renderToBuffer(stack, consumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
				stack.popPose();
				break;
		}
	}

}
