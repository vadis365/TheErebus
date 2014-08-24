package erebus.block.plants;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.item.ErebusMaterial;

public class BlockDoubleHeightPlant extends BlockDoublePlant
{
	@SideOnly(Side.CLIENT)
	private IIcon top, bottom;
	private final String name;

	public BlockDoubleHeightPlant(String name)
	{
		this.name = name;
		setCreativeTab(ModTabs.blocks);
		setStepSound(Block.soundTypeGrass);
		setBlockName("erebus." + name.substring(0, 1).toLowerCase() + name.substring(1));
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player)
	{
		if (world.rand.nextInt(4) == 0)
		{
			if ("Sundew".equals(name))
			{
				Utils.dropStack(world, x, y, z, new ItemStack(ModItems.erebusMaterials, 1, ErebusMaterial.DATA.bioLuminescence.ordinal()));
				return;
			} else if ("WeepingBlue".equals(name))
			{
				Utils.dropStack(world, x, y, z, new ItemStack(ModItems.erebusMaterials, 1, ErebusMaterial.DATA.weepingBluePetal.ordinal()));
				return;
			} else if ("Bullrush".equals(name))
			{
				Utils.dropStack(world, x, y, z, new ItemStack(ModItems.erebusMaterials, 1, ErebusMaterial.DATA.papyrus.ordinal()));
				return;
			}
		}

		ItemStack stack = ForgeHooks.getGrassSeed(world);
		if (stack != null)
		{
			Utils.dropStack(world, x, y, z, stack);
		}

	}

	@Override
	public int getRenderType()
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return func_149887_c(meta) ? top : bottom;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		top = reg.registerIcon("erebus:doublePlant" + name + "Top");
		bottom = reg.registerIcon("erebus:doublePlant" + name + "Bottom");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess access, int x, int y, int z)
	{
		return 0xFFFFFF;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item));
	}
}