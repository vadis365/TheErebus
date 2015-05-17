package erebus.item.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.util.IIcon;

public class ItemBlockErebusPlantSmall extends ItemBlockGeneric {

	public ItemBlockErebusPlantSmall(Block block) {
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage) {
		return getBlock().getIcon(2, damage);
	}
}