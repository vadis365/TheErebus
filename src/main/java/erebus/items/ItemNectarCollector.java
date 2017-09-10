package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemNectarCollector extends Item {

	public ItemNectarCollector() {
		setMaxDamage(64);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.nectarcollector").getFormattedText());
	}
	
	@Override
	 public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		player.swingArm(hand);
	 return EnumActionResult.PASS;
	}
}