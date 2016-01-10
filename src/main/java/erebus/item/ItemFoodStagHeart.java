package erebus.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModTabs;

public class ItemFoodStagHeart extends ItemFood {

	String type;

	public ItemFoodStagHeart(int healAmount, float saturation, boolean favorite, String type) {
		super(healAmount, saturation, favorite);
		setMaxDamage(0);
		setAlwaysEdible();
		setCreativeTab(ModTabs.items);
		this.type = type;
		setTextureName("erebus:heart_stag_" + type);
		setUnlocalizedName("erebus.heart_stag_"+ type);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		if (type.equals("raw"))
			list.add(StatCollector.translateToLocalFormatted("tooltip.erebus.heals"));
		else
			list.add(StatCollector.translateToLocalFormatted("tooltip.erebus.feeds"));
	}

	@Override
	protected void onFoodEaten(ItemStack is, World world, EntityPlayer player) {
		if(type.equals("raw"))
			player.heal(20);
		else
			super.onFoodEaten(is, world, player);
	}
}