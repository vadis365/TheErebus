package erebus.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import erebus.block.BlockGaeanKeystone;
import erebus.core.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;

public class BlockKeystoneRenderer implements ISimpleBlockRenderingHandler
{
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
    {

    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
    {
        renderKeystone(renderer, (BlockGaeanKeystone) block, x, y, z);
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId)
    {
        return false;
    }

    public int getRenderId()
    {
        return ClientProxy.BlockRenderIDs.KEYSTONE.id();
    }

    public boolean renderKeystone(RenderBlocks renderer, BlockGaeanKeystone block, int x, int y, int z)
    {
        int l = renderer.blockAccess.getBlockMetadata(x, y, z);

        if (!BlockGaeanKeystone.isGemActive(l))
        {
            renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 0.8125D, 1.0D);
            renderer.renderStandardBlock(block, x, y, z);
            renderer.uvRotateTop = 0;
            return true;
        }
        else
        {
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
