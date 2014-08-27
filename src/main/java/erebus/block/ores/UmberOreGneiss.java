package erebus.block.ores;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import erebus.ModItems;
import erebus.item.Materials;

public class UmberOreGneiss extends UmberOre
{
	public UmberOreGneiss()
	{
		super(Blocks.coal_ore, "Gneiss", 2);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return ModItems.materials;
	}

	@Override
	public int damageDropped(int meta)
	{
		return Materials.DATA.gneissRock.ordinal();
	}
}