package erebus.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;
import erebus.core.handler.HomingBeeconTextureHandler;

public class ItemHomingBeecon extends Item {

	public ItemHomingBeecon() {
		setMaxStackSize(1);
		setCreativeTab(ModTabs.specials);
		setUnlocalizedName("erebus.homingBeecon");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return HomingBeeconTextureHandler.beecon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		if (hasTag(stack) && stack.stackTagCompound.hasKey("homeX")) {
			list.add(StatCollector.translateToLocalFormatted("tooltip.erebus.dimension", stack.getTagCompound().getString("dimName")));
			list.add(StatCollector.translateToLocalFormatted("tooltip.erebus.targetx", stack.getTagCompound().getInteger("homeX")));
			list.add(StatCollector.translateToLocalFormatted("tooltip.erebus.targetz", stack.getTagCompound().getInteger("homeZ")));
		} else
			list.add(StatCollector.translateToLocal("tooltip.erebus.homingbeecon"));
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && hasTag(stack) && player.isSneaking()) {
			Block block = world.getBlock(x, y, z);
			if (!world.isRemote && block != null) {
				stack.getTagCompound().setString("dimName", player.worldObj.provider.getDimensionName());
				stack.getTagCompound().setInteger("homeX", x);
				stack.getTagCompound().setInteger("homeZ", z);
				player.swingItem();
				return true;
			}
		}
		return false;
	}

	private boolean hasTag(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return false;
		}
		return true;
	}
}