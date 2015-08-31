package erebus.item.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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