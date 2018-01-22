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

public class ItemFoodHeartBerries extends ItemFood {

	public ItemFoodHeartBerries(int healAmount, float saturation, boolean favorite) {
		super(healAmount, saturation, favorite);
		setMaxDamage(0);
		setAlwaysEdible();
		setCreativeTab(ModTabs.ITEMS);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.healinghearts").getFormattedText());
	}

	@Override
	protected void onFoodEaten(ItemStack is, World world, EntityPlayer player) {
		player.heal(1);
	}
}