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
public class BlockBambooCropRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		renderer.renderAllFaces = true;
		renderer.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.95D, 0.75D);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.bambooCrop, 0, renderer);
		renderer.setRenderBounds(0.22D, 0.45D, 0.2D, 0.78D, 0.49D, 0.78D);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.bambooCrop, 0, renderer);
		renderer.setRenderBounds(0.22D, 0.95D, 0.2D, 0.78D, 0.99D, 0.78D);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.bambooCrop, 0, renderer);
		renderer.setRenderBounds(0.25D, 0.99D, 0.25D, 0.75D, 1D, 0.75D);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.bambooCrop, 0, renderer);
		renderer.renderAllFaces = false;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.renderAllFaces = true;
		renderer.setRenderBounds(0.25D, 0.0D, 0.25D, 0.75D, 0.95D, 0.75D);
		renderer.renderStandardBlock(ModBlocks.bambooCrop, x, y, z);
		renderer.setRenderBounds(0.22D, 0.45D, 0.2D, 0.78D, 0.49D, 0.78D);
		renderer.renderStandardBlock(ModBlocks.bambooCrop, x, y, z);
		renderer.setRenderBounds(0.22D, 0.95D, 0.2D, 0.78D, 0.99D, 0.78D);
		renderer.renderStandardBlock(ModBlocks.bambooCrop, x, y, z);
		renderer.setRenderBounds(0.25D, 0.99D, 0.25D, 0.75D, 1D, 0.75D);
		renderer.renderStandardBlock(ModBlocks.bambooCrop, x, y, z);
		renderer.renderAllFaces = false;
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockRenderIDs.BAMBOO_CROP.id();
	}
}