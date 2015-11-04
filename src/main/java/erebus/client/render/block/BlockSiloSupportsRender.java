package erebus.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

@SideOnly(Side.CLIENT)
public class BlockSiloSupportsRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		renderer.setRenderBounds(0F, 0.0625F, 0F, 0.1875F, 0.8125F, 0.1875F);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.siloSupports, 0, renderer);
		renderer.setRenderBounds(0.8125F, 0.0625F, 0.8125F, 1F, 0.8125F, 1F);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.siloSupports, 0, renderer);
		renderer.setRenderBounds(0F, 0.0625F, 0.8125F, 0.1875F, 0.8125F, 1F);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.siloSupports, 0, renderer);
		renderer.setRenderBounds(0.8125F, 0.0625F, 0F, 1F, 0.8125F, 0.1875F);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.siloSupports, 0, renderer);
		renderer.setRenderBounds(0, 0.8125F, 0F, 1F, 1F, 1F);
		BlockRenderHelper.renderSimpleBlock(Blocks.planks, 0, renderer);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.renderAllFaces = true;
		renderer.setRenderBounds(0F, 0.0625F, 0F, 0.1875F, 0.8125F, 0.1875F);
		renderer.renderStandardBlock(ModBlocks.siloSupports, x, y, z);
		renderer.setRenderBounds(0.8125F, 0.0625F, 0.8125F, 1F, 0.8125F, 1F);
		renderer.renderStandardBlock(ModBlocks.siloSupports, x, y, z);
		renderer.setRenderBounds(0F, 0.0625F, 0.8125F, 0.1875F, 0.8125F, 1F);
		renderer.renderStandardBlock(ModBlocks.siloSupports, x, y, z);
		renderer.setRenderBounds(0.8125F, 0.0625F, 0F, 1F, 0.8125F, 0.1875F);
		renderer.renderStandardBlock(ModBlocks.siloSupports, x, y, z);
		renderer.setRenderBounds(0, 0.8125F, 0F, 1F, 1F, 1F);
		renderer.renderStandardBlock(Blocks.planks, x, y, z);

		renderer.setRenderBounds(0F, 0F, 0F, 0.1875F, 0.0625F, 0.1875F);
		renderer.renderStandardBlock(Blocks.planks, x, y, z);
		renderer.setRenderBounds(0.8125F, 0F, 0.8125F, 1F, 0.0625F, 1F);
		renderer.renderStandardBlock(Blocks.planks, x, y, z);
		renderer.setRenderBounds(0F, 0F, 0.8125F, 0.1875F, 0.0625F, 1F);
		renderer.renderStandardBlock(Blocks.planks, x, y, z);
		renderer.setRenderBounds(0.8125F, 0F, 0F, 1F, 0.0625F, 0.1875F);
		renderer.renderStandardBlock(Blocks.planks, x, y, z);
		renderer.renderAllFaces = false;
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockRenderIDs.SILO_SUPPORTS.id();
	}
}