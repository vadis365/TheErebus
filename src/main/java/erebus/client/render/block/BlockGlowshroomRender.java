package erebus.client.render.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.core.proxy.ClientProxy.BlockRenderIDs;

@SideOnly(Side.CLIENT)
public class BlockGlowshroomRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		renderer.renderAllFaces = true;
		renderer.setRenderBounds(0.375F, 0.6875F, 0.375F, 0.625F, 0.75F, 0.625F);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.erebusGlowshroom, 0, renderer);
		renderer.setRenderBounds(0.25F, 0.5625F, 0.25F, 0.75F, 0.6875F, 0.75F);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.erebusGlowshroom, 0, renderer);
		renderer.setRenderBounds(0.0625F, 0.3125F, 0.0625F, 0.9375F, 0.5625F, 0.9375F);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.erebusGlowshroom, 0, renderer);
		renderer.setRenderBounds(0.3125F, 0F, 0.3125F, 0.6875F, 0.3125F, 0.6875F);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.erebusGlowshroomStalk, 0, renderer);
		renderer.renderAllFaces = false;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.renderAllFaces = true;
		renderer.setRenderBounds(0.375F, 0.6875F, 0.375F, 0.625F, 0.75F, 0.625F);
		renderer.renderStandardBlock(ModBlocks.erebusGlowshroom, x, y, z);
		renderer.setRenderBounds(0.25F, 0.5625F, 0.25F, 0.75F, 0.6875F, 0.75F);
		renderer.renderStandardBlock(ModBlocks.erebusGlowshroom, x, y, z);
		renderer.setRenderBounds(0.0625F, 0.3125F, 0.0625F, 0.9375F, 0.5625F, 0.9375F);
		renderer.renderStandardBlock(ModBlocks.erebusGlowshroom, x, y, z);
		renderer.setRenderBounds(0.3125F, 0F, 0.3125F, 0.6875F, 0.3125F, 0.6875F);
		renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
		renderer.renderAllFaces = false;
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockRenderIDs.GLOWSHROOM_CAPS.id();
	}
}