package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockSapling extends ItemBlockGeneric {

	public ItemBlockSapling(Block block) {
		super(block, "erebusSapling");
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int damage) {
		return getBlock().getIcon(2, damage);
	}
}