package erebus.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class BlockComposterRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		renderer.renderAllFaces = true;
		renderer.setRenderBounds(0.0625D, 0.0D, 0.0625D, 0.9375D, 1D, 0.9375D);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.composter, 0, renderer);
		renderer.setRenderBounds(0D, 0.125D, 0D, 1D, 0.25D, 1D);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.composter, 0, renderer);
		renderer.setRenderBounds(0D, 0.75D, 0D, 1D, 0.875D, 1D);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.composter, 0, renderer);
		renderer.renderAllFaces = false;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.renderAllFaces = true;
		renderer.setRenderBounds(0.0625D, 0.0D, 0.0625D, 0.9375D, 1D, 0.9375D);
		renderer.renderStandardBlock(ModBlocks.composter, x, y, z);
		renderer.setRenderBounds(0D, 0.125D, 0D, 1D, 0.25D, 1D);
		renderer.renderStandardBlock(ModBlocks.composter, x, y, z);
		renderer.setRenderBounds(0D, 0.75D, 0D, 1D, 0.875D, 1D);
		renderer.renderStandardBlock(ModBlocks.composter, x, y, z);
		renderer.renderAllFaces = false;
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockRenderIDs.COMPOSTER.id();
	}
}