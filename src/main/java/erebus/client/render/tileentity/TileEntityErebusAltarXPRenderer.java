package erebus.client.render.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelAltarXP;
import erebus.tileentity.TileEntityErebusAltar;
import erebus.tileentity.TileEntityErebusAltarXP;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class TileEntityErebusAltarXPRenderer extends TileEntityErebusAltarRenderer {
	private static final ResourceLocation[] tex = new ResourceLocation[] { new ResourceLocation("erebus:textures/special/tiles/altarXP1.png"), new ResourceLocation("erebus:textures/special/tiles/altarXP2.png"), new ResourceLocation("erebus:textures/special/tiles/altarXP3.png"), new ResourceLocation("erebus:textures/special/tiles/altarXP4.png"), new ResourceLocation("erebus:textures/special/tiles/altarXP5.png") };

	private final ModelAltarXP model = new ModelAltarXP();

	@Override
	protected void renderModel(TileEntityErebusAltar altar) {
		model.render((TileEntityErebusAltarXP) altar);
	}

	@Override
	protected ResourceLocation getAltarTexture(TileEntityErebusAltar altar) {
		TileEntityErebusAltarXP tile = (TileEntityErebusAltarXP) altar;

		if (tile.animationTicks <= 5)
			return tex[0];
		else if (tile.animationTicks > 5 && tile.animationTicks <= 10)
			return tex[1];
		else if (tile.animationTicks > 10 && tile.animationTicks <= 15)
			return tex[2];
		else if (tile.animationTicks > 15 && tile.animationTicks <= 20)
			return tex[3];
		else if (tile.animationTicks > 20 && tile.animationTicks <= 25)
			return tex[4];
		else
			return null;
	}
}