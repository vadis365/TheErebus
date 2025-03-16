package erebus.client.entity.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import erebus.client.entity.model.ModelAnimatedBlock;
import erebus.entity.AnimatedBlock;
import erebus.registries.ModEntityRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderAnimatedBlock extends MobRenderer<AnimatedBlock, ModelAnimatedBlock<AnimatedBlock>> {

	private final ItemRenderer itemRenderer;
	public ItemStack stackRenderer = new ItemStack(Blocks.STONE.defaultBlockState().getBlock());

	
	public RenderAnimatedBlock(EntityRendererProvider.Context renderContext) {
        super(renderContext, new ModelAnimatedBlock<>(renderContext.bakeLayer(ModEntityRendering.ANIMATED_BLOCK)), 0.75F);
		this.itemRenderer = renderContext.getItemRenderer();
	}

	@Override
	public void render(AnimatedBlock entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
		stackRenderer = new ItemStack(entity.getBlockType().getBlock());
		poseStack.pushPose();
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		poseStack.translate(0F, 0F, 0F);
		poseStack.pushPose();
		poseStack.mulPose(Axis.YN.rotationDegrees(entity.yBodyRot));
		poseStack.scale(4F, 4F, 4F); 
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		this.itemRenderer.renderStatic(stackRenderer, ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, null, entity.getId());
		poseStack.popPose();
		RenderSystem.disableBlend();
		poseStack.popPose();
		super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ResourceLocation getTextureLocation(AnimatedBlock animatedblock) {
		String blockPath = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getParticleIcon(animatedblock.getBlockType()).atlasLocation().toString();
		String modName = "minecraft";
		if (blockPath.contains(":")) {
			modName = blockPath.split(":")[0];
			blockPath = blockPath.split(":")[1];
		}
		return ResourceLocation.fromNamespaceAndPath(modName, "textures/" + blockPath + ".png");
	}
}