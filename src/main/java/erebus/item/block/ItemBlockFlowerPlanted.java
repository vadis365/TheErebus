package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockFlowerPlanted extends ItemBlockGeneric {

	public ItemBlockFlowerPlanted(Block block) {
		super(block, "flowerPlanted");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int damage) {
		return getBlock().getIcon(2, damage);
	}
}