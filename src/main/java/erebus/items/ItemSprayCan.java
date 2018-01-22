package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModBlocks;
import erebus.ModSounds;
import erebus.ModTabs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSprayCan extends Item {

	public ItemSprayCan() {
		setMaxStackSize(9);
		setCreativeTab(ModTabs.GEAR);
	}

	private SoundEvent getSprayCanSound() {
		return ModSounds.SPRAY_CAN_SOUND;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.YELLOW + new TextComponentTranslation("tooltip.erebus.spraycan").getFormattedText());
	}

	@Override
	 public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (facing.getIndex() != 1)
			return EnumActionResult.FAIL;
		else if (player.canPlayerEdit(pos, EnumFacing.UP, stack) && player.canPlayerEdit(pos.up(), EnumFacing.UP, stack)) {
			IBlockState state = world.getBlockState(pos);
			if (state.getBlock() != null && state.isSideSolid(world, pos, EnumFacing.UP) && state.getBlock() != ModBlocks.INSECT_REPELLENT) {
				world.setBlockState(pos.up(), ModBlocks.INSECT_REPELLENT.getDefaultState());
				stack.shrink(1);
				world.playSound(null, pos.up(), getSprayCanSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
				return EnumActionResult.SUCCESS;
			} else
				return EnumActionResult.FAIL;
		} else
			return EnumActionResult.FAIL;
	}
}