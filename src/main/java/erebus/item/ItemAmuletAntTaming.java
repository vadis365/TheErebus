package erebus.item;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import java.util.List;

public class ItemAmuletAntTaming extends Item {

	public ItemAmuletAntTaming() {
		setMaxDamage(16);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.specials);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) {
		if (hasTag(is))
			if (is.stackTagCompound != null && is.stackTagCompound.hasKey("homeX")) {
				list.add("Silo Block X: " + is.getTagCompound().getInteger("homeX"));
				list.add("Silo Block Y: " + is.getTagCompound().getInteger("homeY"));
				list.add("Silo Block Z: " + is.getTagCompound().getInteger("homeZ"));
			} else {
				list.add("Click on a Silo Block to");
				list.add("set as target for Ants.");
				list.add("Then click on Ant link it.");
			}
	}

	@Override
	public boolean onItemUseFirst(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && hasTag(is)) {
			Block block = world.getBlock(x, y, z);
			if (!world.isRemote && block != null) {
				if (block == ModBlocks.siloTank) {
					is.getTagCompound().setInteger("homeX", x);
					is.getTagCompound().setInteger("homeY", y);
					is.getTagCompound().setInteger("homeZ", z);
				}
				player.swingItem();
				is.damageItem(1, player);
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