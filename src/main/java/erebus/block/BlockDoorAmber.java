package erebus.block;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class BlockDoorAmber extends BlockDoor
{

	@SideOnly(Side.CLIENT)
	public IIcon topDoorIcon, crapFixIcon;
	@SideOnly(Side.CLIENT)
	public IIcon[] flippedIcons;

	public BlockDoorAmber()
	{
		super(Material.glass);
		float f = 0.5F;
		float f1 = 1.0F;
		setLightOpacity(0);
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
		setBlockName("erebus.doorAmber");
		setBlockTextureName("erebus:doorAmber");
	}

	@Override
	public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z, int direction)
	{
		if (direction == 1)
		{
			return blockIcon;
		}
		if (direction == 0)
		{
			return crapFixIcon;
		}
		int meta = func_150012_g(iBlockAccess, x, y, z);// getFullMetadata
		boolean flag = (meta & 4) != 0;
		int halfMeta = meta & 3;
		boolean flipped = false;

		if (flag)
		{
			if (halfMeta == 0 && direction == 2)
			{
				flipped = !flipped;
			} else if (halfMeta == 1 && direction == 5)
			{
				flipped = !flipped;
			} else if (halfMeta == 2 && direction == 3)
			{
				flipped = !flipped;
			} else if (halfMeta == 3 && direction == 4)
			{
				flipped = !flipped;
			}
		} else
		{
			if (halfMeta == 0 && direction == 5)
			{
				flipped = !flipped;
			} else if (halfMeta == 1 && direction == 3)
			{
				flipped = !flipped;
			} else if (halfMeta == 2 && direction == 4)
			{
				flipped = !flipped;
			} else if (halfMeta == 3 && direction == 2)
			{
				flipped = !flipped;
			}
			if ((meta & 16) != 0)
			{
				flipped = !flipped;
			}
		}

		if (flipped)
		{
			return flippedIcons[(meta & 8) != 0 ? 1 : 0];
		} else
		{
			return (meta & 8) != 0 ? topDoorIcon : blockIcon;
		}
	}

	@Override
	public IIcon getIcon(int par1, int par2)
	{
		return blockIcon;
	}

	@Override
	public void registerBlockIcons(IIconRegister icon)
	{
		blockIcon = icon.registerIcon("erebus:doorAmberLower");
		topDoorIcon = icon.registerIcon("erebus:doorAmberUpper");
		crapFixIcon = icon.registerIcon("erebus:doorAmberBlank");

		flippedIcons = new IIcon[2];
		flippedIcons[0] = new IconFlipped(blockIcon, true, false);
		flippedIcons[1] = new IconFlipped(topDoorIcon, true, false);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return ModItems.doorAmberItem;
	}

	@Override
	public Item getItemDropped(int id, Random random, int something)
	{
		return (id & 8) != 0 ? null : ModItems.doorAmberItem;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}
}