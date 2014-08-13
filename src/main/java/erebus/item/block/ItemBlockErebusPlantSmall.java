package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockErebusPlantSmall extends ItemBlockGeneric
{

	public ItemBlockErebusPlantSmall(Block block)
	{
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int damage)
	{
		return getBlock().getIcon(2, damage);
	}
}