package erebus.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.core.handler.HomingBeeconTextureHandler;

public class ItemDeathCompass extends ItemHomingBeecon {

	public ItemDeathCompass() {
		setCreativeTab(null);
		setUnlocalizedName("erebus.deathCompass");
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
			list.add(StatCollector.translateToLocal("tooltip.erebus.deathcompass"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return HomingBeeconTextureHandler.deathCompasss;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
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