package erebus.item.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemStack;

public class ItemErebusLeaves extends ItemLeaves
{

	public ItemErebusLeaves(Block block)
	{
		super((BlockLeaves) block);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		return field_150939_a.getUnlocalizedName();
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		return field_150939_a.getLocalizedName();
	}
}