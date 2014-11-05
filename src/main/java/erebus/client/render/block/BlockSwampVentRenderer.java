package erebus.client.render.block;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class BlockSwampVentRenderer implements ISimpleBlockRenderingHandler {

	public static final int renderID = RenderingRegistry.getNextAvailableRenderId();
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		int l = block.getRenderColor(metadata);
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }
        
        renderer.enableAO = false;
        Tessellator tessellator = Tessellator.instance;

        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, -1.0F, 0.0F);
        tessellator.setColorOpaque_F(1, 1, 1);
        renderer.renderFaceYNeg(block, 0, 0, 0, block.getIcon(0, metadata));
        
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.setColorOpaque_F(f, f1, f2);
        renderer.renderFaceYPos(block, 0, 0, 0, block.getIcon(1, metadata));
        
        IIcon iicon;

        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, -1.0F);
        tessellator.setColorOpaque_F(1, 1, 1);
        iicon = block.getIcon(2, metadata);
        renderer.renderFaceZNeg(block, 0, 0, 0, iicon);

        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 0.0F, 1.0F);
        tessellator.setColorOpaque_F(1, 1, 1);
        iicon = block.getIcon(3, metadata);
        renderer.renderFaceZPos(block, 0, 0, 0, iicon);

        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(-1.0F, 0.0F, 0.0F);
        tessellator.setColorOpaque_F(1, 1, 1);
        iicon = block.getIcon(4, metadata);
        renderer.renderFaceXNeg(block, 0, 0, 0, iicon);

        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.setNormal(1.0F, 0.0F, 0.0F);
        tessellator.setColorOpaque_F(1, 1, 1);
        iicon = block.getIcon(5, metadata);
        renderer.renderFaceXPos(block, 0, 0, 0, iicon);
        
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelID, RenderBlocks renderer) {
		renderer.renderStandardBlock(block, x, y, z);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return renderID;
	}
}
