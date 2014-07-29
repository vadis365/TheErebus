package erebus.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.block.silo.TileEntitySiloTankPart;

@SideOnly(Side.CLIENT)
public class TileEntityRenderSiloTank extends TileEntitySpecialRenderer {

	public static ResourceLocation bigTankTexture  = new ResourceLocation("erebus:textures/special/tiles/siloSides.png");
	public static ResourceLocation tankPartTexture= new ResourceLocation("erebus:textures/blocks/siloTankPart.png");
	
	public void renderSilo(TileEntitySiloTankPart silo, float x, float y, float z) {
		// Nothing added here yet
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float rotation) {
		renderSilo((TileEntitySiloTankPart) tile, (float) x, (float) y, (float) z);
	}
}