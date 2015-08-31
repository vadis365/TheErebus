package erebus.item.block;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.core.helper.Utils;
import erebus.lib.EnumColour;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class ItemBlockFlowerPlanted extends ItemBlockGeneric {

	public ItemBlockFlowerPlanted(Block block) {
		super(block);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIconFromDamage(int damage) {
		return getBlock().getIcon(2, damage);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		String colour = EnumColour.values()[Utils.getFlowerMetadata(stack)].getTranslatedName();
		return String.format(StatCollector.translateToLocal(getUnlocalizedName() + ".name"), colour);
	}
}