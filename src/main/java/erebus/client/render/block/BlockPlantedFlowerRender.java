package erebus.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class BlockPlantedFlowerRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		Tessellator tess = Tessellator.instance;
		tess.setBrightness(block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z));
		int colour = block.colorMultiplier(renderer.blockAccess, x, y, z);
		float r = (colour >> 16 & 255) / 255.0F;
		float g = (colour >> 8 & 255) / 255.0F;
		float b = (colour & 255) / 255.0F;

		if (EntityRenderer.anaglyphEnable) {
			float r2 = (r * 30.0F + g * 59.0F + b * 11.0F) / 100.0F;
			float g2 = (r * 30.0F + g * 70.0F) / 100.0F;
			float b2 = (r * 30.0F + b * 70.0F) / 100.0F;
			r = r2;
			g = g2;
			b = b2;
		}

		tess.setColorOpaque_F(r, g, b);
		drawFlower(world.getBlockMetadata(x, y, z), x, y, z);
		return true;
	}

	private void setPetalColour(int meta) {
		Tessellator tess = Tessellator.instance;
		float[] colour = EntitySheep.fleeceColorTable[BlockColored.func_150032_b(Utils.getFlowerMetadata(meta))];
		tess.setColorOpaque_F(colour[0], colour[1], colour[2]);
	}

	private void drawFlower(int meta, int x, int y, int z) {
		drawCrossedSquares(ModBlocks.flowerPlanted.getIcon(0, meta), x, y, z);
		setPetalColour(meta);
		drawCrossedSquares(ModBlocks.flowerPlanted.getIcon(1, meta), x, y, z);
	}

	private void drawCrossedSquares(IIcon icon, int x, int y, int z) {
		Tessellator tess = Tessellator.instance;

		double minU = icon.getMinU();
		double minV = icon.getMinV();
		double maxU = icon.getMaxU();
		double maxV = icon.getMaxV();
		double minX = x + 0.05D;
		double maxX = x + 0.95D;
		double minZ = z + 0.05D;
		double maxZ = z + 0.95D;
		tess.addVertexWithUV(minX, y + 1.0F, minZ, minU, minV);
		tess.addVertexWithUV(minX, y + 0.0D, minZ, minU, maxV);
		tess.addVertexWithUV(maxX, y + 0.0D, maxZ, maxU, maxV);
		tess.addVertexWithUV(maxX, y + 1.0F, maxZ, maxU, minV);
		tess.addVertexWithUV(maxX, y + 1.0F, maxZ, minU, minV);
		tess.addVertexWithUV(maxX, y + 0.0D, maxZ, minU, maxV);
		tess.addVertexWithUV(minX, y + 0.0D, minZ, maxU, maxV);
		tess.addVertexWithUV(minX, y + 1.0F, minZ, maxU, minV);
		tess.addVertexWithUV(minX, y + 1.0F, maxZ, minU, minV);
		tess.addVertexWithUV(minX, y + 0.0D, maxZ, minU, maxV);
		tess.addVertexWithUV(maxX, y + 0.0D, minZ, maxU, maxV);
		tess.addVertexWithUV(maxX, y + 1.0F, minZ, maxU, minV);
		tess.addVertexWithUV(maxX, y + 1.0F, minZ, minU, minV);
		tess.addVertexWithUV(maxX, y + 0.0D, minZ, minU, maxV);
		tess.addVertexWithUV(minX, y + 0.0D, maxZ, maxU, maxV);
		tess.addVertexWithUV(minX, y + 1.0F, maxZ, maxU, minV);
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return false;
	}

	@Override
	public int getRenderId() {
		return BlockRenderIDs.PLANTED_FLOWER.id();
	}
}