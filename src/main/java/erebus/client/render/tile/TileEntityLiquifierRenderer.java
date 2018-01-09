package erebus.client.render.tile;

import org.lwjgl.opengl.GL11;

import erebus.ModBlocks;
import erebus.blocks.BlockLiquifier;
import erebus.client.model.block.ModelLiquifier;
import erebus.tileentity.TileEntityLiquifier;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityLiquifierRenderer extends TileEntitySpecialRenderer<TileEntityLiquifier> {
	private static final ResourceLocation BASE_TEXTURE = new ResourceLocation("erebus:textures/special/tiles/liquifier.png");
	private final ModelLiquifier MODEL = new ModelLiquifier();

	public void renderTile(TileEntityLiquifier tile, double x, double y, double z, float partialTick, int destroyStage) {
		IBlockState state = tile.getWorld().getBlockState(tile.getPos());

		if (state == null || state.getBlock() != ModBlocks.LIQUIFIER)
			return;

		float fluidLevel = tile.tank.getFluidAmount();
		if (fluidLevel > 0) {
			FluidStack fluidStack = new FluidStack(tile.tank.getFluid(), 100);
			float height = (0.375F / tile.tank.getCapacity()) * fluidLevel;

			TextureAtlasSprite fluidStillSprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluidStack.getFluid().getStill().toString());
			Tessellator tessellator = Tessellator.getInstance();
			BufferBuilder buffer = tessellator.getBuffer();
			int fluidColor = fluidStack.getFluid().getColor(fluidStack);

			GlStateManager.disableLighting();
			GlStateManager.pushMatrix();
			GlStateManager.translate(x, y, z);
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			setGLColorFromInt(fluidColor);
			buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
			float xMax, zMax, xMin, zMin, yMin = 0;
			xMax = 1.984375F;
			zMax = 1.984375F;
			xMin = 0.015625F;
			zMin = 0.015625F;
			yMin = 0.015625F;

			renderCuboid(buffer, xMax, xMin, yMin, height, zMin, zMax, fluidStillSprite);
			tessellator.draw();
			GlStateManager.popMatrix();
			GlStateManager.enableLighting();
		}
		float ticks = tile.animationTicks + (tile.animationTicks - tile.prevAnimationTicks) * partialTick;
		
		GlStateManager.pushMatrix();
		GlStateManager.translate(x + 0.5D, y , z + 0.5D);
		if(!tile.getStackInSlot(0).isEmpty()) {
			GlStateManager.rotate(ticks, 0F, -1F, 0F);
			renderItemInSlot(tile, tile.getStackInSlot(0), 0F, 0.5F, 0F, 0.175F);
		}
		GlStateManager.popMatrix();
		
		EnumFacing facing = state.getValue(BlockLiquifier.FACING);

		bindTexture(BASE_TEXTURE);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
		GlStateManager.scale(-1, -1, 1);

		switch (facing) {
		case NORTH:
			GlStateManager.rotate(180F, 0.0F, 1F, 0F);
			break;
		case SOUTH:
			GlStateManager.rotate(0F, 0.0F, 1F, 0F);
			break;
		case WEST:
			GlStateManager.rotate(90F, 0.0F, 1F, 0F);
			break;
		case EAST:
			GlStateManager.rotate(-90F, 0.0F, 1F, 0F);
			break;
		default:
			break;
		}

		GlStateManager.translate(0F, -1F, 0F);
		MODEL.renderLidAnimated(tile, partialTick);
		GlStateManager.pushMatrix();
		GlStateManager.rotate(ticks, 0.0F, 1F, 0F);
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.color(1F, 1F, 1F, 1F);
		MODEL.renderBlades();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
		MODEL.renderTank();
		GlStateManager.popMatrix();
	}

	@Override
	public void render(TileEntityLiquifier te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		if (te == null || !te.hasWorld()) {
			renderTileAsItem(x, y, z);
			return;
		}
		renderTile(te, x, y, z, partialTicks, destroyStage);
	}

	private void renderTileAsItem(double x, double y, double z) {
		GlStateManager.pushMatrix();
		bindTexture(BASE_TEXTURE);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		GlStateManager.scale(-1, -1, 1);
		MODEL.renderLid();
		MODEL.renderBlades();
		GlStateManager.popMatrix();
		GlStateManager.popMatrix();
	}

	public void renderItemInSlot(TileEntityLiquifier tile, ItemStack stack, float x, float y, float z, float scale) {
		if (!stack.isEmpty()) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(x, y, z);
			GlStateManager.scale(-scale, -scale, scale);
			GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(180F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(0F, 0.0F, 0.0F, 1.0F);
			bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			Minecraft.getMinecraft().getTextureManager().getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Minecraft.getMinecraft().getRenderItem().renderItem(stack, Minecraft.getMinecraft().getRenderItem().getItemModelWithOverrides(stack, (World) null, (EntityLivingBase) null));
			GlStateManager.disableBlend();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.popMatrix();
		}
	}

	private void setGLColorFromInt(int color) {
		float red = (color >> 16 & 0xFF) / 255.0F;
		float green = (color >> 8 & 0xFF) / 255.0F;
		float blue = (color & 0xFF) / 255.0F;
		GlStateManager.color(red, green, blue, 1.0F);
	}

	private void renderCuboid(BufferBuilder buffer, float xMax, float xMin, float yMin, float height, float zMin, float zMax, TextureAtlasSprite textureAtlasSprite) {

		double uMin = (double) textureAtlasSprite.getMinU();
		double uMax = (double) textureAtlasSprite.getMaxU();
		double vMin = (double) textureAtlasSprite.getMinV();
		double vMax = (double) textureAtlasSprite.getMaxV();

		final double vHeight = vMax - vMin;

		// top
		addVertexWithUV(buffer, xMax, height, zMax, uMax, vMin);
		addVertexWithUV(buffer, xMax, height, zMin, uMin, vMin);
		addVertexWithUV(buffer, xMin, height, zMin, uMin, vMax);
		addVertexWithUV(buffer, xMin, height, zMax, uMax, vMax);

		// north
		addVertexWithUV(buffer, xMax, yMin, zMin, uMax, vMin);
		addVertexWithUV(buffer, xMin, yMin, zMin, uMin, vMin);
		addVertexWithUV(buffer, xMin, height, zMin, uMin, vMin + (vHeight * height));
		addVertexWithUV(buffer, xMax, height, zMin, uMax, vMin + (vHeight * height));

		// south
		addVertexWithUV(buffer, xMax, yMin, zMax, uMin, vMin);
		addVertexWithUV(buffer, xMax, height, zMax, uMin, vMin + (vHeight * height));
		addVertexWithUV(buffer, xMin, height, zMax, uMax, vMin + (vHeight * height));
		addVertexWithUV(buffer, xMin, yMin, zMax, uMax, vMin);

		// east
		addVertexWithUV(buffer, xMax, yMin, zMin, uMin, vMin);
		addVertexWithUV(buffer, xMax, height, zMin, uMin, vMin + (vHeight * height));
		addVertexWithUV(buffer, xMax, height, zMax, uMax, vMin + (vHeight * height));
		addVertexWithUV(buffer, xMax, yMin, zMax, uMax, vMin);

		// west
		addVertexWithUV(buffer, xMin, yMin, zMax, uMin, vMin);
		addVertexWithUV(buffer, xMin, height, zMax, uMin, vMin + (vHeight * height));
		addVertexWithUV(buffer, xMin, height, zMin, uMax, vMin + (vHeight * height));
		addVertexWithUV(buffer, xMin, yMin, zMin, uMax, vMin);

		// down
		addVertexWithUV(buffer, xMax, yMin, zMin, uMax, vMin);
		addVertexWithUV(buffer, xMax, yMin, zMax, uMin, vMin);
		addVertexWithUV(buffer, xMin, yMin, zMax, uMin, vMax);
		addVertexWithUV(buffer, xMin, yMin, zMin, uMax, vMax);

	}

	private void addVertexWithUV(BufferBuilder buffer, float x, float y, float z, double u, double v) {
		buffer.pos(x / 2f, y, z / 2f).tex(u, v).endVertex();
	}

	private void addVertexWithColor(BufferBuilder buffer, float x, float y, float z, float red, float green, float blue, float alpha) {
		buffer.pos(x / 2f, y, z / 2f).color(red, green, blue, alpha).endVertex();
	}

}