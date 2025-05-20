package erebus.client.render.block.renderer.stack;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.Erebus;
import erebus.client.render.block.model.LiquifierModel;
import erebus.registries.client.ModBlockEntityRendering;
import erebus.registries.data.FluidContents;
import erebus.registries.data.ModDataComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
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

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class LiquifierStackItemRenderer extends BlockEntityWithoutLevelRenderer {
	private final ResourceLocation TEXTURE = Erebus.prefix("textures/special/tiles/liquifier.png");
	private final LiquifierModel model;
	private FluidStack fluidStack;

	public LiquifierStackItemRenderer(BlockEntityRenderDispatcher renderer, EntityModelSet modelSet) {
		super(renderer, modelSet);
		EntityModelSet EntityModelSetThatIsntNULL = Minecraft.getInstance().getEntityModels();
		model = new LiquifierModel(EntityModelSetThatIsntNULL.bakeLayer(ModBlockEntityRendering.LIQUIFIER));
	}

	@Override
	public void renderByItem(ItemStack stack, @Nonnull ItemDisplayContext transformType, PoseStack matrixStack, MultiBufferSource bufferIn, int combinedLight, int combinedOverlayIn) {
		fluidStack = stack.getOrDefault(ModDataComponents.FLUID.get(), FluidContents.EMPTY).get();
		float fluidLevel = fluidStack.getAmount();
		if (fluidLevel  > 0) {
			float tankMax = 8000F;
			float height = (0.375F/ tankMax) * fluidLevel;
			var fluidExtensions = IClientFluidTypeExtensions.of(fluidStack.getFluid());
			TextureAtlasSprite fluidStillSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidExtensions.getStillTexture());
			VertexConsumer buffer = bufferIn.getBuffer(RenderType.CUTOUT);
			int fluidColor = fluidExtensions.getTintColor();
	
			matrixStack.pushPose();
			matrixStack.translate(0D, 0D, 0D);
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
			renderCuboid(buffer, matrixStack, xMax, xMin, yMin, height, zMin, zMax, fluidStillSprite, red, green, blue, alpha, combinedLight);
			matrixStack.popPose();
		}

		VertexConsumer consumer = bufferIn.getBuffer(RenderType.entityTranslucent(TEXTURE));

		matrixStack.pushPose();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		matrixStack.translate(0.5D, 1.5D, 0.5D);
		matrixStack.scale(-1, -1, 1);
		model.renderToBuffer(matrixStack, consumer, combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		model.renderBlades(matrixStack, consumer, combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		model.renderLidStatic(matrixStack, consumer, combinedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
		RenderSystem.disableBlend();
		RenderSystem.depthMask(true);
		matrixStack.popPose();
	}

	private void renderCuboid(VertexConsumer buffer, PoseStack matrixStack, float xMax, float xMin, float yMin, float height, float zMin, float zMax, TextureAtlasSprite textureAtlasSprite, float red, float green, float blue, float alpha, int combinedLight) {

		float uMin = textureAtlasSprite.getU0();
		float uMax = textureAtlasSprite.getU1();
		float vMin = textureAtlasSprite.getV0();
		float vMax = textureAtlasSprite.getV1();

		float vHeight = vMax - vMin;

		// top
		addVertexWithUV(buffer, matrixStack, xMax, height, zMax, uMax, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMax, height, zMin, uMin, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMin, height, zMin, uMin, vMax, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMin, height, zMax, uMax, vMax, red, green, blue, alpha, combinedLight);

		// north
		addVertexWithUV(buffer, matrixStack, xMax, yMin, zMin, uMax, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMin, yMin, zMin, uMin, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMin, height, zMin, uMin, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMax, height, zMin, uMax, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);

		// south
		addVertexWithUV(buffer, matrixStack, xMax, yMin, zMax, uMin, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMax, height, zMax, uMin, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMin, height, zMax, uMax, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMin, yMin, zMax, uMax, vMin, red, green, blue, alpha, combinedLight);

		// east
		addVertexWithUV(buffer, matrixStack, xMax, yMin, zMin, uMin, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMax, height, zMin, uMin, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMax, height, zMax, uMax, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMax, yMin, zMax, uMax, vMin, red, green, blue, alpha, combinedLight);

		// west
		addVertexWithUV(buffer, matrixStack, xMin, yMin, zMax, uMin, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMin, height, zMax, uMin, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMin, height, zMin, uMax, vMin + (vHeight * height), red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMin, yMin, zMin, uMax, vMin, red, green, blue, alpha, combinedLight);

		// down
		addVertexWithUV(buffer, matrixStack, xMax, yMin, zMin, uMax, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMax, yMin, zMax, uMin, vMin, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMin, yMin, zMax, uMin, vMax, red, green, blue, alpha, combinedLight);
		addVertexWithUV(buffer, matrixStack, xMin, yMin, zMin, uMax, vMax, red, green, blue, alpha, combinedLight);
	}

	private void addVertexWithUV(VertexConsumer buffer, PoseStack matrixStack, float x, float y, float z, float u, float v, float red, float green, float blue, float alpha, int combinedLight) {
		buffer.addVertex(matrixStack.last().pose(), x / 2f, y, z / 2f).setColor(red, green, blue, alpha).setUv(u, v).setUv2(combinedLight, 240).setNormal(1, 0, 0);
	}
}
