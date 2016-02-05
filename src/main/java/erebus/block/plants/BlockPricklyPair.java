package erebus.block.plants;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.item.ItemErebusFood;

public class BlockPricklyPair extends Block implements IPlantable {
	@SideOnly(Side.CLIENT)
	private IIcon top, bottom;

	public BlockPricklyPair() {
		super(Material.plants);
		setBlockName("erebus.pricklyPair");
		setBlockTextureName("erebus:prickly_pair");
		setCreativeTab(ModTabs.plants);
		float f = 0.375F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		setTickRandomly(true);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand) {
		if (world.getBlock(x, y - 1, z) == ModBlocks.pricklyPair || dropBlockIfCantStay(world, x, y, z)) {
			if (world.isAirBlock(x, y + 1, z)) {
				int height;

				for (height = 1; world.getBlock(x, y - height, z) == this; ++height);

				if (height < 3) {
					int meta = world.getBlockMetadata(x, y, z);

					if (meta == 10) {
						world.setBlock(x, y + 1, z, this);
						if(world.getBlock(x, y, z) == this && world.getBlock(x, y - 1, z) == this)
							world.setBlockMetadataWithNotify(x, y + 1, z, 11, 4);
						else
							world.setBlockMetadataWithNotify(x, y, z, 0, 4);

					} else if (meta < 10){
						world.setBlockMetadataWithNotify(x, y, z, meta + 1, 4);
					}
				}
			}
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y - 1, z);
		return block.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		dropBlockIfCantStay(world, x, y, z);
	}

	protected final boolean dropBlockIfCantStay(World world, int x, int y, int z) {
		if (!canBlockStay(world, x, y, z)) {
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		return canPlaceBlockAt(world, x, y, z) || world.getBlock(x, y - 1, z) == this;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if(entity instanceof EntityLivingBase)
			entity.attackEntityFrom(DamageSource.cactus, 1);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		if (meta <= 10)
			drops.add(new ItemStack(Item.getItemFromBlock(this)));
		else
			drops.add(new ItemStack(ModItems.food, 1, ItemErebusFood.FoodType.PRICKLY_PAIR_RAW.ordinal()));
		return drops;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return meta <= 10 ? bottom : top ;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		top = reg.registerIcon("erebus:prickly_pair_fruited");
		bottom = reg.registerIcon("erebus:prickly_pair");
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Desert;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return world.getBlockMetadata(x, y, z);
	}
}
