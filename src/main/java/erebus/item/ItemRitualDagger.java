package erebus.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModMaterials;
import erebus.ModTabs;
import erebus.api.animationmagic.EnergyType;
import erebus.api.animationmagic.IEnergyCollector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class ItemRitualDagger extends ItemSword implements IEnergyCollector {

	public ItemRitualDagger() {
		super(ModMaterials.ritualDagger);
		setCreativeTab(ModTabs.gears);
		setTextureName("erebus:ritualDagger");
		setUnlocalizedName("erebus.ritualDagger");
	}

	@Override
	public boolean getIsRepairable(ItemStack item, ItemStack material) {
		for (int id : OreDictionary.getOreIDs(material))
			if ("ingotGold".equals(OreDictionary.getOreName(id)))
				return true;
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void addInformation(ItemStack stack, EntityPlayer player, List tooltip, boolean isComplex) {
		tooltip.add(EnumChatFormatting.LIGHT_PURPLE + "Not Implemented Yet.");
		for (EnergyType type : EnergyType.values())
			if (canStore(stack, type))
				tooltip.add(type.toString() + " - " + getCurrentStorage(stack, type) + "/" + getMaxStorage(stack, type));
	}

	@Override
	public int getMaxStorage(ItemStack stack, EnergyType type) {
		return 500;
	}

	@Override
	public int getCurrentStorage(ItemStack stack, EnergyType type) {
		NBTTagCompound nbt = getNBT(stack);
		if (nbt.hasKey(type.toString()))
			return nbt.getInteger(type.toString());
		return 0;
	}

	@Override
	public int addEnergy(ItemStack stack, EnergyType type, int amount) {
		NBTTagCompound nbt = getNBT(stack);
		int current = Math.max(0, nbt.getInteger(type.toString()));
		int newValue = Math.min(getMaxStorage(stack, type), current + amount);
		nbt.setInteger(type.toString(), newValue);

		return newValue - current;
	}

	@Override
	public int extractEnergy(ItemStack stack, EnergyType type, int amount) {
		NBTTagCompound nbt = getNBT(stack);
		int current = Math.max(0, nbt.getInteger(type.toString()));
		int newValue = Math.max(0, current - amount);
		nbt.setInteger(type.toString(), newValue);

		return current - newValue;
	}

	@Override
	public boolean canStore(ItemStack stack, EnergyType type) {
		return true;
	}

	private NBTTagCompound getNBT(ItemStack stack) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		return stack.getTagCompound();
	}
}