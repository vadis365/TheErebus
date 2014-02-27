package erebus.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.handler.HomingBeeconTextureHandler;

public class ItemHomingBeecon extends Item {

	public ItemHomingBeecon(int id) {
		super(id);
		setMaxStackSize(1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta) {
		return HomingBeeconTextureHandler.beecon;
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) {
		if (hasTag(is))
			if (is.stackTagCompound!=null && is.stackTagCompound.hasKey("homeX")) {
				list.add("Target X: "+is.getTagCompound().getInteger("homeX"));
				list.add("Target Z: "+is.getTagCompound().getInteger("homeZ"));
			}
			else {
				list.add("Click on a Block");
				list.add("to set as target.");
			}
	}
	
	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && hasTag(is)) {
			int blockID = world.getBlockId(x, y, z);
			Block block = Block.blocksList[blockID];
			if (!world.isRemote && block !=null) {
					is.getTagCompound().setInteger("homeX", x);
					is.getTagCompound().setInteger("homeZ", z);
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