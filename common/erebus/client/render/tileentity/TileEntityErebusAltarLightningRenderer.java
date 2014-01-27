package erebus.client.render.tileentity;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelAltarLightning;
import erebus.tileentity.TileEntityErebusAltar;
import erebus.tileentity.TileEntityErebusAltarLightning;

@SideOnly(Side.CLIENT)
public class TileEntityErebusAltarLightningRenderer extends TileEntityErebusAltarRenderer {
	private static final ResourceLocation[] tex = new ResourceLocation[] { new ResourceLocation("erebus:textures/special/tiles/EngineOfIllapa1.png"), new ResourceLocation("erebus:textures/special/tiles/EngineOfIllapa2.png"), new ResourceLocation("erebus:textures/special/tiles/EngineOfIllapa3.png"),
	new ResourceLocation("erebus:textures/special/tiles/EngineOfIllapa4.png"), new ResourceLocation("erebus:textures/special/tiles/EngineOfIllapa5.png"), new ResourceLocation("erebus:textures/special/tiles/EngineOfIllapa6.png"),
	new ResourceLocation("erebus:textures/special/tiles/EngineOfIllapa7.png") };

	private final ModelAltarLightning model = new ModelAltarLightning();

	@Override
	protected void renderModel(TileEntityErebusAltar altar) {
		model.render((TileEntityErebusAltarLightning) altar);
	}

	@Override
	protected ResourceLocation getAltarTexture(TileEntityErebusAltar altar) {
		TileEntityErebusAltarLightning tile = (TileEntityErebusAltarLightning) altar;

		if (tile.animationTicks <= 5)
			return tex[0];
		else if (tile.animationTicks > 5 && tile.animationTicks <= 10)
			return tex[1];
		else if (tile.animationTicks > 10 && tile.animationTicks <= 15)
			return tex[2];
		else if (tile.animationTicks > 15 && tile.animationTicks <= 20)
			return tex[3];
		else if (tile.animationTicks > 20 && tile.animationTicks < 25)
			return tex[4];
		else if (tile.animationTicks == 25 && tile.fuzz <= 5)
			return tex[4];
		else if (tile.animationTicks == 25 && tile.fuzz > 5 && tile.fuzz <= 10 || tile.animationTicks == 25 && tile.fuzz > 15 && tile.fuzz <= 20)
			return tex[5];
		else if (tile.animationTicks == 25 && tile.fuzz > 10 && tile.fuzz <= 15)
			return tex[6];
		else
			return null;
	}
}