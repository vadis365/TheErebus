package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelAltar;
import erebus.client.model.block.ModelStone4;
import erebus.tileentity.TileEntityCraftingAltar;

@SideOnly(Side.CLIENT)
public class TileEntityCraftingAltarRenderer extends TileEntitySpecialRenderer
{
	private final ModelAltar model = new ModelAltar();
	private final ModelStone4 stone4 = new ModelStone4();

	public void renderAModelAt(TileEntityCraftingAltar tile, double x, double y, double z, float partialTick)
	{
		if (tile.blockMetadata == 1)
		{
			bindTexture(new ResourceLocation("erebus:textures/special/tiles/craftingAltarActive.png"));
		} else
		{
			bindTexture(new ResourceLocation("erebus:textures/special/tiles/craftingAltar.png"));
		}

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 1.5F, z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.renderAll();
		GL11.glPopMatrix();

		GL11.glPushMatrix();
		GL11.glTranslated(x + 0.5F, y + 1.5F, z + 0.5F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(tile.rotation, 0.0F, 1.0F, 0.0F);
		stone4.renderAll();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float partialTick)
	{
		renderAModelAt((TileEntityCraftingAltar) tile, x, y, z, partialTick);
	}
}