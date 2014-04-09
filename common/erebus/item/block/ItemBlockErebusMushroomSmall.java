package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemBlockErebusMushroomSmall extends ItemBlockGeneric {

	public ItemBlockErebusMushroomSmall(int id) {
		super(id, "erebusMushroomSmall");
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage) {
		return Block.blocksList[getBlockID()].getIcon(2, damage);
	}
}
