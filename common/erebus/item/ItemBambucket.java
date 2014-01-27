package erebus.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;

public class ItemBambucket extends Item {

	public Icon bambucket;
	public Icon waterBambucket;
	public Icon bambucketOfBeetleJuice;

	public ItemBambucket(int id) {
		super(id);
		maxStackSize = 16;
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player) {
		if (is.getItemDamage() == 2) {
			player.setItemInUse(is, getMaxItemUseDuration(is));
			return is;
		}

		boolean flag = is.getItemDamage() == 0;
		MovingObjectPosition movingobjectposition = getMovingObjectPositionFromPlayer(world, player, flag);

		if (movingobjectposition == null)
			return is;
		else if (movingobjectposition.typeOfHit == EnumMovingObjectType.TILE) {
			int i = movingobjectposition.blockX;
			int j = movingobjectposition.blockY;
			int k = movingobjectposition.blockZ;

			if (!world.canMineBlock(player, i, j, k))
				return is;

			if (is.getItemDamage() == 0) {
				if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, is))
					return is;

				if (world.getBlockMaterial(i, j, k) == Material.water && world.getBlockMetadata(i, j, k) == 0) {
					world.setBlockToAir(i, j, k);

					if (player.capabilities.isCreativeMode)
						return is;

					if (--is.stackSize <= 0)
						return new ItemStack(itemID, 1, 1);

					if (!player.inventory.addItemStackToInventory(new ItemStack(itemID, 1, 1)))
						player.dropPlayerItem(new ItemStack(itemID, 1, 1));

					return is;
				}
			} else {
				if (is.getItemDamage() < 0)
					return new ItemStack(itemID, 1, 0);

				if (movingobjectposition.sideHit == 0)
					--j;

				if (movingobjectposition.sideHit == 1)
					++j;

				if (movingobjectposition.sideHit == 2)
					--k;

				if (movingobjectposition.sideHit == 3)
					++k;

				if (movingobjectposition.sideHit == 4)
					--i;

				if (movingobjectposition.sideHit == 5)
					++i;

				if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, is))
					return is;

				if (tryPlaceContainedLiquid(world, i, j, k, is) && !player.capabilities.isCreativeMode)
					if (--is.stackSize <= 0)
						return is;
			}
		}
		return is;
	}

	@Override
	public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {
		if (is.getItemDamage() == 2) {
			if (!player.capabilities.isCreativeMode)
				is.stackSize--;

			if (!world.isRemote)
				player.curePotionEffects(new ItemStack(Item.bucketMilk));

			player.inventory.addItemStackToInventory(new ItemStack(ModItems.bamBucket));
			return is;
		} else
			return super.onEaten(is, world, player);
	}

	public boolean tryPlaceContainedLiquid(World world, int x, int y, int z, ItemStack item) {
		Material material = world.getBlockMaterial(x, y, z);
		boolean flag = !material.isSolid();
		if (!world.isRemote && item.getItemDamage() != 2)
			if (!world.isAirBlock(x, y, z) && !flag)
				return false;
			else if (world.provider.isHellWorld && item.getItemDamage() == 1) {
				world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

				for (int l = 0; l < 8; ++l)
					world.spawnParticle("largesmoke", x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
			} else {
				if (!world.isRemote && flag && !material.isLiquid())
					world.destroyBlock(x, y, z, true);

				world.setBlock(x, y, z, Block.waterMoving.blockID, 0, 3);
			}
		return true;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack is) {
		int x;
		if (is.getItemDamage() == 2)
			x = 32;
		else
			x = super.getMaxItemUseDuration(is);
		return x;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack is) {
		EnumAction x;
		if (is.getItemDamage() == 2)
			x = EnumAction.drink;
		else
			x = EnumAction.block;
		return x;
	}

	@Override
	public void registerIcons(IconRegister iconRegister) {
		bambucket = iconRegister.registerIcon("erebus:bambucket");
		waterBambucket = iconRegister.registerIcon("erebus:bambucketWater");
		bambucketOfBeetleJuice = iconRegister.registerIcon("erebus:bambucketOfBeetleJuice");
	}

	@Override
	public Icon getIconFromDamage(int meta) {
		switch (meta) {
			case 0:
				return bambucket;
			case 1:
				return waterBambucket;
			case 2:
				return bambucketOfBeetleJuice;
			default:
				return null;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int id, CreativeTabs tab, List list) {
		list.add(new ItemStack(id, 1, 0));
		list.add(new ItemStack(id, 1, 1));
		list.add(new ItemStack(id, 1, 2));
	}

	@Override
	public String getUnlocalizedName(ItemStack is) {
		int i = is.getItemDamage();
		return super.getUnlocalizedName() + "." + i;
	}
}
