package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockDoubleHeightPlant extends ItemBlockGeneric {
	private final Block blockID;
	
	public ItemBlockDoubleHeightPlant(int id) {
		super(id, "doubleHeightPlant");
		blockID = Block.blocksList[id + 256];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int damage) {
		return blockID.getIcon(2, damage+8);
	}
}