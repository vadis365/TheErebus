package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModBlocks;
import erebus.ModTabs;
import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAmuletBeeTaming extends Item {

	public ItemAmuletBeeTaming() {
		setMaxDamage(16);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		if (hasTag(stack) && stack.getTagCompound().hasKey("homeX")) {
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.honeycombx", stack.getTagCompound().getInteger("homeX")).getFormattedText());
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.honeycomby", stack.getTagCompound().getInteger("homeY")).getFormattedText());
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.honeycombz", stack.getTagCompound().getInteger("homeZ")).getFormattedText());
		} else {
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.beetamingamulet_1").getFormattedText());
			list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.beetamingamulet_2").getFormattedText());
		}
	}

	@Override
	 public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote && hasTag(stack)) {
			Block block = world.getBlockState(pos).getBlock();
			if (!world.isRemote && block != null) {
				if (block == ModBlocks.HONEY_COMB) {
					stack.getTagCompound().setInteger("homeX", pos.getX());
					stack.getTagCompound().setInteger("homeY", pos.getY());
					stack.getTagCompound().setInteger("homeZ", pos.getZ());
				}
				player.swingArm(hand);
				stack.damageItem(1, player);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.PASS;
	}

	private boolean hasTag(ItemStack stack) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return false;
		}
		return true;
	}
}