package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockSapling extends ItemBlockGeneric {

	private final Block targetBlock;

	public ItemBlockSapling(int id) {
		super(id, "erebusSapling");
		targetBlock = Block.blocksList[id + 256];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int damage) {
		return targetBlock.getIcon(2, damage);
	}
}
