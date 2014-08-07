package erebus.item;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.ModMaterials;
import erebus.entity.EntityBotFlyLarva;

public class Bambucket extends Item {

	@SideOnly(Side.CLIENT)
	private IIcon bambucket, waterBambucket, bambucketOfBeetleJuice, bambucketHoney;

	public Bambucket() {
		setMaxDamage(0);
		setMaxStackSize(16);
		setHasSubtypes(true);
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		return new ItemStack(this);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if (stack.getItemDamage() == 2) {
			player.setItemInUse(stack, getMaxItemUseDuration(stack));
			return stack;
		}

		boolean isEmtpy = stack.getItemDamage() == 0;
		MovingObjectPosition pos = getMovingObjectPositionFromPlayer(world, player, isEmtpy);

		if (pos == null)
			return stack;
		else if (pos.typeOfHit == MovingObjectType.BLOCK) {
			int x = pos.blockX;
			int y = pos.blockY;
			int z = pos.blockZ;

			if (!world.canMineBlock(player, x, y, z))
				return stack;

			if (isEmtpy) {
				if (!player.canPlayerEdit(x, y, z, pos.sideHit, stack))
					return stack;

				if (world.getBlock(x, y, z).getMaterial() == Material.water && world.getBlockMetadata(x, y, z) == 0) {
					world.setBlockToAir(x, y, z);

					if (player.capabilities.isCreativeMode)
						return stack;

					if (--stack.stackSize <= 0)
						return new ItemStack(this, 1, 1);

					if (!player.inventory.addItemStackToInventory(new ItemStack(this, 1, 1)))
						player.entityDropItem(new ItemStack(this, 1, 1), 0.0F);

					return stack;
				}

				if (world.getBlock(x, y, z).getMaterial() == ModMaterials.honey && world.getBlockMetadata(x, y, z) == 0) {
					world.setBlockToAir(x, y, z);

					if (player.capabilities.isCreativeMode)
						return stack;

					if (--stack.stackSize <= 0)
						return new ItemStack(this, 1, 3);

					if (!player.inventory.addItemStackToInventory(new ItemStack(this, 1, 3)))
						player.entityDropItem(new ItemStack(this, 1, 3), 0.0F);

					return stack;
				}
			} else {
				if (pos.sideHit == 0)
					y--;
				if (pos.sideHit == 1)
					y++;
				if (pos.sideHit == 2)
					z--;
				if (pos.sideHit == 3)
					z++;
				if (pos.sideHit == 4)
					x--;
				if (pos.sideHit == 5)
					x++;

				if (!player.canPlayerEdit(x, y, z, pos.sideHit, stack))
					return stack;

				if (tryPlaceContainedLiquid(world, x, y, z, stack) && !player.capabilities.isCreativeMode)
					if (--stack.stackSize <= 0)
						return stack;
			}
		}
		return stack;
	}

	@Override
	public ItemStack onEaten(ItemStack is, World world, EntityPlayer player) {
		if (is.getItemDamage() == 2) {
			if (!player.capabilities.isCreativeMode)
				is.stackSize--;

			if (!world.isRemote)
				player.curePotionEffects(new ItemStack(Items.milk_bucket));

			if (player.riddenByEntity != null && player.riddenByEntity instanceof EntityBotFlyLarva)
				if (((EntityBotFlyLarva) player.riddenByEntity).getParasiteCount() > 0)
					((EntityBotFlyLarva) player.riddenByEntity).setABitDead();

			player.inventory.addItemStackToInventory(new ItemStack(ModItems.bamBucket));
			return is;
		}
		return super.onEaten(is, world, player);
	}

	public boolean tryPlaceContainedLiquid(World world, int x, int y, int z, ItemStack stack) {
		Material material = world.getBlock(x, y, z).getMaterial();
		boolean isSolid = !material.isSolid();
		if (!world.isRemote && stack.getItemDamage() != 2)
			if (!world.isAirBlock(x, y, z) && !isSolid)
				return false;
			else if (world.provider.isHellWorld && stack.getItemDamage() == 1) {
				world.playSoundEffect(x + 0.5F, y + 0.5F, z + 0.5F, "random.fizz", 0.5F, 2.6F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.8F);

				for (int l = 0; l < 8; ++l)
					world.spawnParticle("largesmoke", x + Math.random(), y + Math.random(), z + Math.random(), 0.0D, 0.0D, 0.0D);
			} else {
				if (!world.isRemote && isSolid && !material.isLiquid())
					world.func_147480_a(x, y, z, true);//destroyBlock
				if (stack.getItemDamage() == 1)
					world.setBlock(x, y, z, Blocks.flowing_water, 0, 3);
				else if (stack.getItemDamage() == 3)
					world.setBlock(x, y, z, ModBlocks.erebusHoneyBlock, 0, 3);
			}
		return true;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return stack.getItemDamage() == 2 ? 32 : super.getMaxItemUseDuration(stack);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return stack.getItemDamage() == 2 ? EnumAction.drink : EnumAction.none;
	}

	@Override
	public void registerIcons(IIconRegister reg) {
		bambucket = reg.registerIcon("erebus:bambucket");
		waterBambucket = reg.registerIcon("erebus:bambucketWater");
		bambucketOfBeetleJuice = reg.registerIcon("erebus:bambucketOfBeetleJuice");
		bambucketHoney = reg.registerIcon("erebus:bambucketHoney");
	}

	@Override
	public IIcon getIconFromDamage(int meta) {
		switch (meta) {
			case 0:
				return bambucket;
			case 1:
				return waterBambucket;
			case 2:
				return bambucketOfBeetleJuice;
			case 3:
				return bambucketHoney;
			default:
				return null;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item id, CreativeTabs tab, List list) {
		for (int i = 0; i <= 3; i++)
			list.add(new ItemStack(id, 1, i));
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + stack.getItemDamage();
	}
}