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
public class BlockGlowshroomStalkRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		if (block == ModBlocks.glowshroomStalkN3) {
			renderer.setRenderBounds(0.3125F, 0.3125F, 0.3125F, 0.6875F, 0.6875F, 1F);
			BlockRenderHelper.renderSimpleBlock(ModBlocks.glowshroomStalkMain, 0, renderer);
			renderer.setRenderBounds(0.3125F, 0.6875F, 0.3125F, 0.6875F, 1F, 0.6875F);
			BlockRenderHelper.renderSimpleBlock(ModBlocks.glowshroomStalkMain, 0, renderer);
		}

		if (block == ModBlocks.glowshroomStalkS3) {
			renderer.setRenderBounds(0.3125F, 0.3125F, 0F, 0.6875F, 0.6875F, 0.6875F);
			BlockRenderHelper.renderSimpleBlock(ModBlocks.glowshroomStalkMain, 0, renderer);
			renderer.setRenderBounds(0.3125F, 0.6875F, 0.3125F, 0.6875F, 1F, 0.6875F);
			BlockRenderHelper.renderSimpleBlock(ModBlocks.glowshroomStalkMain, 0, renderer);
		}

		if (block == ModBlocks.glowshroomStalkW3) {
			renderer.setRenderBounds(0.3125F, 0.3125F, 0.3125F, 1F, 0.6875F, 0.6875F);
			BlockRenderHelper.renderSimpleBlock(ModBlocks.glowshroomStalkMain, 0, renderer);
			renderer.setRenderBounds(0.3125F, 0.6875F, 0.3125F, 0.6875F, 1F, 0.6875F);
			BlockRenderHelper.renderSimpleBlock(ModBlocks.glowshroomStalkMain, 0, renderer);
		}

		if (block == ModBlocks.glowshroomStalkE3) {
			renderer.setRenderBounds(0, 0.3125F, 0.3125F, 0.6875F, 0.6875F, 0.6875F);
			BlockRenderHelper.renderSimpleBlock(ModBlocks.glowshroomStalkMain, 0, renderer);
			renderer.setRenderBounds(0.3125F, 0.6875F, 0.3125F, 0.6875F, 1F, 0.6875F);
			BlockRenderHelper.renderSimpleBlock(ModBlocks.glowshroomStalkMain, 0, renderer);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		if (block == ModBlocks.glowshroomStalkN3) {
			renderer.setRenderBounds(0.3125F, 0.3125F, 0.3125F, 0.6875F, 0.6875F, 1F);
			renderer.renderStandardBlock(ModBlocks.glowshroomStalkMain, x, y, z);
			renderer.setRenderBounds(0.3125F, 0.6875F, 0.3125F, 0.6875F, 1F, 0.6875F);
			renderer.renderStandardBlock(ModBlocks.glowshroomStalkMain, x, y, z);
		}

		if (block == ModBlocks.glowshroomStalkS3) {
			renderer.setRenderBounds(0.3125F, 0.3125F, 0F, 0.6875F, 0.6875F, 0.6875F);
			renderer.renderStandardBlock(ModBlocks.glowshroomStalkMain, x, y, z);
			renderer.setRenderBounds(0.3125F, 0.6875F, 0.3125F, 0.6875F, 1F, 0.6875F);
			renderer.renderStandardBlock(ModBlocks.glowshroomStalkMain, x, y, z);
		}

		if (block == ModBlocks.glowshroomStalkW3) {
			renderer.setRenderBounds(0.3125F, 0.3125F, 0.3125F, 1F, 0.6875F, 0.6875F);
			renderer.renderStandardBlock(ModBlocks.glowshroomStalkMain, x, y, z);
			renderer.setRenderBounds(0.3125F, 0.6875F, 0.3125F, 0.6875F, 1F, 0.6875F);
			renderer.renderStandardBlock(ModBlocks.glowshroomStalkMain, x, y, z);
		}

		if (block == ModBlocks.glowshroomStalkE3) {
			renderer.setRenderBounds(0, 0.3125F, 0.3125F, 0.6875F, 0.6875F, 0.6875F);
			renderer.renderStandardBlock(ModBlocks.glowshroomStalkMain, x, y, z);
			renderer.setRenderBounds(0.3125F, 0.6875F, 0.3125F, 0.6875F, 1F, 0.6875F);
			renderer.renderStandardBlock(ModBlocks.glowshroomStalkMain, x, y, z);
		}

		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return BlockRenderIDs.GLOWSHROOM_STALK.id();
	}
}