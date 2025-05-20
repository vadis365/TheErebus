package erebus.client.render.block.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import erebus.block.entity.FluidJarBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.inventory.InventoryMenu;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidStack;

@OnlyIn(Dist.CLIENT)
public class FluidJarRenderer implements BlockEntityRenderer<FluidJarBlockEntity> {
	
	public FluidJarRenderer(Context context) {
	}

	@Override
	public void render(FluidJarBlockEntity tile, float partialTick, PoseStack stack, MultiBufferSource bufferIn, int combinedLight, int combinedOverlay) {
		if (tile == null || !tile.hasLevel())
			return;
		if (tile.tank.getFluid().isEmpty())
			return;
		float fluidLevel = tile.tank.getFluidAmount();
		if (fluidLevel < 1)
			return;
		FluidStack fluidStack = new FluidStack(tile.tank.getFluid().getFluidHolder(), 100);
		float height = (0.7421875F / tile.tank.getCapacity()) * tile.tank.getFluidAmount();

		var fluidExtensions = IClientFluidTypeExtensions.of(fluidStack.getFluid());
		
		TextureAtlasSprite fluidStillSprite = Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(fluidExtensions.getStillTexture());
		VertexConsumer buffer = bufferIn.getBuffer(RenderType.CUTOUT);
		int fluidColor = fluidExtensions.getTintColor();
		stack.pushPose();
		stack.translate(0D, 0D, 0D);
		float xMax, zMax, xMin, zMin, yMin = 0;
		xMax = 1.859375F;
		zMax = 1.859375F;
		xMin = 0.140625F;
		zMin = 0.140625F;
		yMin = 0.015625F;
		float alpha = 1F;
		float red = (fluidColor >> 16 & 0xFF) / 255.0F;
		float green = (fluidColor >> 8 & 0xFF) / 255.0F;
		float blue = (fluidColor & 0xFF) / 255.0F;
		renderCuboid(buffer, stack, xMax, xMin, yMin, height, zMin, zMax, fluidStillSprite, red, green, blue, alpha, combinedLight);
		stack.popPose();

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