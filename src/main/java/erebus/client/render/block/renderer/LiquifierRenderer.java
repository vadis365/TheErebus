package erebus.client.render.block.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import erebus.Erebus;
import erebus.block.entity.LiquifierBlockEntity;
import erebus.client.render.block.model.LiquifierModel;
import erebus.registries.client.ModBlockEntityRendering;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;

@OnlyIn(Dist.CLIENT)
public class LiquifierRenderer implements BlockEntityRenderer<LiquifierBlockEntity> {
	private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/liquifier.png");
	private final LiquifierModel model;
	private final ItemRenderer itemRenderer;
	
	public LiquifierRenderer(Context context) {
		model = new LiquifierModel(context.bakeLayer(ModBlockEntityRendering.LIQUIFIER));
		itemRenderer = context.getItemRenderer();
	}

	@Override
    public void render(LiquifierBlockEntity tile, float partialTick, PoseStack stack, MultiBufferSource bufferIn, int combinedLight, int combinedOverlay) {
		if(tile == null || !tile.hasLevel())
			return;

		if (!tile.tank.getFluid().isEmpty()) {
			float fluidLevel = tile.tank.getFluidAmount();
			if (fluidLevel > 0) {
				FluidStack fluidStack = new FluidStack(tile.tank.getFluid().getFluidHolder(), 100);
				float height = (0.375F / tile.tank.getCapacity()) * tile.tank.getFluidAmount();
				var fluidExtensions = IClientFluidTypeExtensions.of(fluidStack.getFluid());
				TextureAtlasSprite fluidStillSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidExtensions.getStillTexture());
				VertexConsumer buffer = bufferIn.getBuffer(RenderType.CUTOUT);
				int fluidColor = fluidExtensions.getTintColor();
				stack.pushPose();
				
				stack.translate(0D, 0D, 0D);
				float xMax, zMax, xMin, zMin, yMin = 0;
				xMax = 1.984375F;
				zMax = 1.984375F;
				xMin = 0.015625F;
				zMin = 0.015625F;
				yMin = 0.015625F;
		
				float alpha = 1F;
				float red = (fluidColor >> 16 & 0xFF) / 255.0F;
				float green = (fluidColor >> 8 & 0xFF) / 255.0F;
				float blue = (fluidColor & 0xFF) / 255.0F;
				renderCuboid(buffer, stack, xMax, xMin, yMin, height, zMin, zMax, fluidStillSprite, red, green, blue, alpha, combinedLight);
				stack.popPose();
			}
		}

		float ticks = tile.animationTicks + (tile.animationTicks - tile.prevAnimationTicks) * partialTick;

		stack.pushPose();
		stack.translate(0.5D, 0.5D, 0.5D);
		if(!tile.getItems().get(0).isEmpty()) {
			stack.mulPose(Axis.YP.rotationDegrees(ticks));
			renderItemInSlot(tile, partialTick, stack, bufferIn, combinedLight, combinedOverlay, tile.getItems().get(0), 0D, 0D, 0D, 0.25F);
		}
		stack.popPose();

		VertexConsumer buffer = bufferIn.getBuffer(RenderType.entityTranslucent(TEXTURE));

		stack.pushPose();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		stack.translate(0.5D, 1.5D, 0.5D);
		stack.scale(-1, -1, 1);
		model.renderLidAnimated(stack, buffer, combinedLight, combinedOverlay, 0xFFFFFFFF, tile, partialTick);
		stack.pushPose();
		stack.mulPose(Axis.YP.rotationDegrees(ticks));
		model.renderBlades(stack, buffer, combinedLight, combinedOverlay, 0xFFFFFFFF);
		stack.popPose();
		model.renderToBuffer(stack, buffer, combinedLight, combinedOverlay, 0xFFFFFFFF);
		RenderSystem.disableBlend();
		RenderSystem.depthMask(true);
		stack.popPose();
	}

	public void renderItemInSlot(LiquifierBlockEntity tile, float partialTick, PoseStack stack, MultiBufferSource bufferIn, int packedLight, int packedOverlay, ItemStack itemStack, double x, double y, double z, float scale) {
		if (!itemStack.isEmpty()) {
			stack.pushPose();
			stack.translate(x, y, z);
			stack.scale(-scale, -scale, scale);
			stack.mulPose(Axis.YP.rotationDegrees(180));
			stack.mulPose(Axis.XP.rotationDegrees(180));
			itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, stack, bufferIn, tile.getLevel(), 1);
			stack.popPose();
		}
	}

	private void renderCuboid(VertexConsumer buffer, PoseStack stack, float xMax, float xMin, float yMin, float height, float zMin, float zMax, TextureAtlasSprite textureAtlasSprite, float red, float green, float blue, float alpha, int combinedLight) {

		float uMin = textureAtlasSprite.getU0();
		float uMax = textureAtlasSprite.getU1();
		float vMin = textureAtlasSprite.getV0();
		float vMax = textureAtlasSprite.getV1();

		float vHeight = vMax - vMin;

		// top
		addVertexWithUV(buffer, stack, xMax, height, zMax, uMax, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMax, height, zMin, uMin, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMin, height, zMin, uMin, vMax, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMin, height, zMax, uMax, vMax, red, green, blue, alpha, combinedLight);

		// north
		addVertexWithUV(buffer, stack, xMax, yMin, zMin, uMax, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMin, yMin, zMin, uMin, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMin, height, zMin, uMin, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMax, height, zMin, uMax, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);

		// south
		addVertexWithUV(buffer, stack, xMax, yMin, zMax, uMin, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMax, height, zMax, uMin, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMin, height, zMax, uMax, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMin, yMin, zMax, uMax, vMin, red, green, blue, alpha, combinedLight);

		// east
		addVertexWithUV(buffer, stack, xMax, yMin, zMin, uMin, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMax, height, zMin, uMin, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMax, height, zMax, uMax, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMax, yMin, zMax, uMax, vMin, red, green, blue, alpha, combinedLight);

		// west
		addVertexWithUV(buffer, stack, xMin, yMin, zMax, uMin, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMin, height, zMax, uMin, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMin, height, zMin, uMax, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMin, yMin, zMin, uMax, vMin, red, green, blue, alpha, combinedLight);

		// down
		addVertexWithUV(buffer, stack, xMax, yMin, zMin, uMax, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMax, yMin, zMax, uMin, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMin, yMin, zMax, uMin, vMax, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, stack, xMin, yMin, zMin, uMax, vMax, red, green, blue, alpha, combinedLight);
	}

	private void addVertexWithUV(VertexConsumer buffer, PoseStack stack, float x, float y, float z, float u, float v, float red, float green, float blue, float alpha, int combinedLight) {
		buffer.addVertex(stack.last().pose(), x / 2f, y, z / 2f).setColor(red, green, blue, alpha).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setUv2(combinedLight, 240).setNormal(1, 0, 0);
	}

}