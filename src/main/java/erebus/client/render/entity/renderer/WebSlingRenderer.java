package erebus.client.render.entity.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import erebus.entity.projectile.ThrownBlockAsItem;
import erebus.registries.ModBlocks;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.data.ModelData;

@OnlyIn(Dist.CLIENT)
public class WebSlingRenderer extends EntityRenderer<ThrownBlockAsItem> {

	private final BlockRenderDispatcher blockRenderer;
	private BlockState blockState;

	public WebSlingRenderer(EntityRendererProvider.Context renderContext) {
		super(renderContext);
		this.blockRenderer = renderContext.getBlockRenderDispatcher();
	}

	@Override
	public void render(ThrownBlockAsItem entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		poseStack.pushPose();
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		poseStack.translate(0.5F, 0.625F, 0.5F);
		poseStack.mulPose(Axis.XP.rotationDegrees(180F));
		poseStack.mulPose(Axis.YN.rotationDegrees(90F));
		poseStack.scale(1F, 1F, 1F);
		blockState = entity.getBlockType();
		blockRenderer.renderSingleBlock(blockState, poseStack, buffer, packedLight, packedLight, ModelData.EMPTY, RenderType.CUTOUT);
		RenderSystem.disableBlend();
		poseStack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(ThrownBlockAsItem entity) {
		return null;
	}
}
