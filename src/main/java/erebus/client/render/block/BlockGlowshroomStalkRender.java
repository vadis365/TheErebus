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
public class BlockGlowshroomStalkRender implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		renderer.renderAllFaces = true;
		renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
		BlockRenderHelper.renderSimpleBlock(ModBlocks.erebusGlowshroomStalk, 0, renderer);
		renderer.renderAllFaces = false;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
		renderer.renderAllFaces = true;
		int meta = world.getBlockMetadata(x, y, z);
		float widthMin = 0, heightMin = 0, depthMin = 0;
		float widthMax = 0, heightMax = 0, depthMax = 0;
		switch (meta) {
		case 0:
			widthMin= 0F;
			heightMin = 0F;
			depthMin= 0F;
			widthMax= 0F;
			heightMax = 0F;
			depthMax= 0F;
			renderer.setRenderBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
	//down
		case 1:
			widthMin= 0.1875F;
			heightMin = 0.875F;
			depthMin= 0.1875F;
			widthMax= 0.1875F;
			heightMax = 0F;
			depthMax= 0.1875F;
			renderer.setRenderBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
		case 2:
			widthMin= 0.1875F;
			heightMin = 0.375F;
			depthMin= 0.1875F;
			widthMax= 0.1875F;
			heightMax = 0F;
			depthMax= 0.1875F;
			renderer.setRenderBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
		case 3:
			widthMin= 0.1875F;
			heightMin = 0F;
			depthMin= 0.1875F;
			widthMax= 0.1875F;
			heightMax = 0F;
			depthMax= 0.1875F;
			renderer.setRenderBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
	//north		
		case 4:
			widthMin = 0.3125F;
			heightMin = 0.3125F;
			depthMin = 0.3125F;
			widthMax = 0.3125F;
			heightMax = 0.3125F;
			depthMax = 0F;
			renderer.setRenderBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
		case 5:
			widthMin = 0.1875F;
			heightMin = 0.1875F;
			depthMin = 0F;
			widthMax = 0.1875F;
			heightMax = 0.1875F;
			depthMax = 0F;
			renderer.setRenderBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
		case 6:
			renderer.setRenderBounds(0.3125F, 0.3125F, 0.3125F, 0.6875F, 0.6875F, 1F);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			renderer.setRenderBounds(0.3125F, 0.6875F, 0.3125F, 0.6875F, 1F , 0.6875F);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
			
	//south
		case 7:
			widthMin = 0.3125F;
			heightMin = 0.3125F;
			depthMin = 0F;
			widthMax = 0.3125F;
			heightMax = 0.3125F;
			depthMax = 0.3125F;
			renderer.setRenderBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
		case 8:
			widthMin = 0.1875F;
			heightMin = 0.1875F;
			depthMin = 0F;
			widthMax = 0.1875F;
			heightMax = 0.1875F;
			depthMax = 0F;
			renderer.setRenderBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
		case 9:
			renderer.setRenderBounds(0.3125F, 0.3125F, 0F, 0.6875F, 0.6875F, 0.6875F);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			renderer.setRenderBounds(0.3125F, 0.6875F, 0.3125F, 0.6875F, 1F, 0.6875F );
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
	//west
		case 10:
			widthMin = 0.3125F;
			heightMin = 0.3125F;
			depthMin = 0.3125F;
			widthMax = 0F;
			heightMax = 0.3125F;
			depthMax = 0.3125F;
			renderer.setRenderBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
		case 11:
			widthMin = 0F;
			heightMin = 0.1875F;
			depthMin = 0.1875F;
			widthMax = 0F;
			heightMax = 0.1875F;
			depthMax = 0.1875F;
			renderer.setRenderBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
		case 12:
			widthMin = 0.3125F;
			heightMin = 0.3125F;
			depthMin = 0.3125F;
			widthMax = 0F;
			heightMax = 0F;
			depthMax = 0.3125F;
			renderer.setRenderBounds(0.3125F, 0.3125F, 0.3125F, 1F, 0.6875F, 0.6875F);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			renderer.setRenderBounds(0.3125F, 0.6875F, 0.3125F, 0.6875F, 1F, 0.6875F );
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
	//east
		case 13:
			widthMin = 0F;
			heightMin = 0.3125F;
			depthMin = 0.3125F;
			widthMax = 0.3125F;
			heightMax = 0.3125F;
			depthMax = 0.3125F;
			renderer.setRenderBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
		case 14:
			widthMin = 0F;
			heightMin = 0.1875F;
			depthMin = 0.1875F;
			widthMax = 0F;
			heightMax = 0.1875F;
			depthMax = 0.1875F;
			renderer.setRenderBounds(0F + widthMin, 0F + heightMin, 0F + depthMin, 1F - widthMax, 1F - heightMax, 1F - depthMax);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
		case 15:
			renderer.setRenderBounds(0, 0.3125F, 0.3125F, 0.6875F, 0.6875F, 0.6875F);
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			renderer.setRenderBounds(0.3125F, 0.6875F, 0.3125F, 0.6875F, 1F, 0.6875F );
			renderer.renderStandardBlock(ModBlocks.erebusGlowshroomStalk, x, y, z);
			break;
			}	
		renderer.renderAllFaces = false;
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