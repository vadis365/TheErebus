package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemFoodStagHeart extends ItemFood {

	String type;

	public ItemFoodStagHeart(int healAmount, float saturation, boolean favorite, String type) {
		super(healAmount, saturation, favorite);
		setMaxDamage(0);
		setAlwaysEdible();
		setCreativeTab(ModTabs.ITEMS);
		this.type = type;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		if (type.equals("raw"))
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.heals").getFormattedText());
		else
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.feeds").getFormattedText());
	}

	@Override
	protected void onFoodEaten(ItemStack is, World world, EntityPlayer player) {
		if(type.equals("raw"))
			player.heal(20);
		else
			super.onFoodEaten(is, world, player);
	}
}