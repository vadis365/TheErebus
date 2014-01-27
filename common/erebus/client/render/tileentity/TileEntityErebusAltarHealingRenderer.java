package erebus.client.render.tileentity;

import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.client.model.block.ModelAltarHealing;
import erebus.tileentity.TileEntityErebusAltar;
import erebus.tileentity.TileEntityErebusAltarHealing;

@SideOnly(Side.CLIENT)
public class TileEntityErebusAltarHealingRenderer extends TileEntityErebusAltarRenderer {
	private static final ResourceLocation[] tex = new ResourceLocation[] { new ResourceLocation("erebus:textures/special/tiles/HealingAltar1.png"), new ResourceLocation("erebus:textures/special/tiles/HealingAltar2.png"), new ResourceLocation("erebus:textures/special/tiles/HealingAltar3.png"),
	new ResourceLocation("erebus:textures/special/tiles/HealingAltar4.png"), new ResourceLocation("erebus:textures/special/tiles/HealingAltar5.png") };

	private final ModelAltarHealing model = new ModelAltarHealing();

	@Override
	protected void renderModel(TileEntityErebusAltar altar) {
		model.render((TileEntityErebusAltarHealing) altar);
	}

	@Override
	protected ResourceLocation getAltarTexture(TileEntityErebusAltar altar) {
		TileEntityErebusAltarHealing tile = (TileEntityErebusAltarHealing) altar;

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