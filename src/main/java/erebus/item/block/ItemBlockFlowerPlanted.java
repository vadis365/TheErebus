package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockFlowerPlanted extends ItemBlockGeneric {

	private final Block targetBlock;

	public ItemBlockFlowerPlanted(int id) {
		super(id, "flowerPlanted");
		targetBlock = Block.blocksList[id + 256];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int damage) {
		return targetBlock.getIcon(2, damage);
	}
}
