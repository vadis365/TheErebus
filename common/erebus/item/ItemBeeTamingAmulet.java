package erebus.item;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBeeTamingAmulet extends Item {

	public ItemBeeTamingAmulet(int id) {
		super(id);
		setMaxDamage(16);
		setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) {
		if (hasTag(is))
			if (is.stackTagCompound!=null && is.stackTagCompound.hasKey("homeX")) {
				list.add("Honey Comb Block X: "+is.getTagCompound().getInteger("homeX"));
				list.add("Honey Comb Block Y: "+is.getTagCompound().getInteger("homeY"));
				list.add("Honey Comb Block Z: "+is.getTagCompound().getInteger("homeZ"));
			}
			else {
				list.add("Click on a Chest to set");
				list.add("as target for Bee drops.");
				list.add("Then click on Bee to tame.");
			}
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && hasTag(is)) {
			int blockID = world.getBlockId(x, y, z);
			Block block = Block.blocksList[blockID];
			if (!world.isRemote && block !=null) {
				if (block.blockID == Block.chest.blockID){
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