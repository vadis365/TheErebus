package erebus.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.block.GaeanKeystone;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;

@SideOnly(Side.CLIENT)
public class BlockKeystoneRenderer implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
		renderer.renderAllFaces = true;
		renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.gaeanKeystone, 0, renderer);
		renderer.renderAllFaces = false;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderKeystone(renderer, (GaeanKeystone) block, x, y, z);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockRenderIDs.KEYSTONE.id();
	}

	public boolean renderKeystone(RenderBlocks renderer, GaeanKeystone block, int x, int y, int z) {
		int l = renderer.blockAccess.getBlockMetadata(x, y, z);

		if (!GaeanKeystone.isGemActive(l)) {
			renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.uvRotateTop = 0;
			return true;
		} else {
			renderer.renderAllFaces = true;
			renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.setOverrideBlockTexture(block.getEyeIcon());
			renderer.setRenderBounds(0.25D, 0.8125D, 0.25D, 0.75D, 1.0D, 0.75D);
			renderer.renderStandardBlock(block, x, y, z);
			renderer.renderAllFaces = false;
			renderer.clearOverrideBlockTexture();
			renderer.uvRotateTop = 0;
			return true;
		}
	}
}
