package erebus.block.plants;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.world.feature.plant.WorldGenGiantMushrooms;
import erebus.world.feature.plant.WorldGenGiantMushrooms.MushroomType;

public class SmallMushroom extends BlockMushroom
{
	private final String name;

	public SmallMushroom(String name)
	{
		super();
		this.name = name;
		setHardness(0.0F);
		setStepSound(soundTypeGrass);
		setBlockName("erebus." + name);
		setCreativeTab(ModTabs.plants);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon("erebus:" + name);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemIconName()
	{
		return "erebus:" + name;
	}

	@SubscribeEvent
	public void onBonemeal(BonemealEvent event)
	{
		if (!event.world.isRemote && event.block == this)
		{
			if (event.world.rand.nextFloat() < 0.45D)
			{
				int xx = event.x;
				int yy = event.y;
				int zz = event.z;
				if (isMushroom(event.world, xx, yy, zz))
				{
					if (isMushroom(event.world, xx + 1, yy, zz) && isMushroom(event.world, xx + 1, yy, zz + 1) && isMushroom(event.world, xx, yy, zz + 1))
					{
						growPlants(event.world, event.x + 1, event.y, event.z, event.world.rand);
					}

					if (isMushroom(event.world, xx - 1, yy, zz) && isMushroom(event.world, xx - 1, yy, zz + 1) && isMushroom(event.world, xx, yy, zz + 1))
					{
						growPlants(event.world, event.x, event.y, event.z, event.world.rand);
					}

					if (isMushroom(event.world, xx + 1, yy, zz) && isMushroom(event.world, xx + 1, yy, zz - 1) && isMushroom(event.world, xx, yy, zz - 1))
					{
						growPlants(event.world, event.x + 1, event.y, event.z - 1, event.world.rand);
					}

					if (isMushroom(event.world, xx - 1, yy, zz) && isMushroom(event.world, xx - 1, yy, zz - 1) && isMushroom(event.world, xx, yy, zz - 1))
					{
						growPlants(event.world, event.x, event.y, event.z - 1, event.world.rand);
					}
				}
			}
			event.setResult(Result.ALLOW);
		}
	}

	public void growPlants(World world, int x, int y, int z, Random rand)
	{
		if (isMushroom(world, x, y, z))
		{
			if (isMushroom(world, x + 1, y, z) && isMushroom(world, x + 1, y, z + 1) && isMushroom(world, x, y, z + 1))
			{
				world.setBlockToAir(x, y, z);
				world.setBlockToAir(x + 1, y, z);
				world.setBlockToAir(x + 1, y, z + 1);
				world.setBlockToAir(x, y, z + 1);
			}

			if (isMushroom(world, x - 1, y, z) && isMushroom(world, x - 1, y, z + 1) && isMushroom(world, x, y, z + 1))
			{
				world.setBlockToAir(x, y, z);
				world.setBlockToAir(x - 1, y, z);
				world.setBlockToAir(x - 1, y, z + 1);
				world.setBlockToAir(x, y, z + 1);
			}

			if (isMushroom(world, x + 1, y, z) && isMushroom(world, x + 1, y, z - 1) && isMushroom(world, x, y, z - 1))
			{
				world.setBlockToAir(x, y, z);
				world.setBlockToAir(x + 1, y, z);
				world.setBlockToAir(x + 1, y, z - 1);
				world.setBlockToAir(x, y, z - 1);
			}

			if (isMushroom(world, x - 1, y, z) && isMushroom(world, x - 1, y, z - 1) && isMushroom(world, x, y, z - 1))
			{
				world.setBlockToAir(x, y, z);
				world.setBlockToAir(x - 1, y, z);
				world.setBlockToAir(x - 1, y, z - 1);
				world.setBlockToAir(x, y, z - 1);
			}

			WorldGenGiantMushrooms genGiantMushrooms = new WorldGenGiantMushrooms();
			genGiantMushrooms.setMushroomType(MushroomType.values()[getShroomData(this)]);

			if (!genGiantMushrooms.generate(world, rand, x, y, z))
			{
				world.setBlock(x, y, z, this);
			}
		}
	}

	private boolean isMushroom(World world, int x, int y, int z)
	{
		return world.getBlock(x, y, z) == this;
	}

	private int getShroomData(Block block)
	{
		if (block == ModBlocks.bulbCappedShroom)
		{
			return 0;
		} else if (block == ModBlocks.greenMushroom)
		{
			return 1;
		} else if (block == ModBlocks.bundleshroom)
		{
			return 2;
		} else if (block == ModBlocks.kaizerfinger)
		{
			return 3;
		} else if (block == ModBlocks.dutchCap)
		{
			return 4;
		} else
		{
			return -1;
		}
	}
}