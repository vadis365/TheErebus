package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModTabs;
import erebus.core.helper.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDirt.DirtType;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemPlanticide extends Item {

	public ItemPlanticide() {
		setCreativeTab(ModTabs.ITEMS);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.RED + new TextComponentTranslation("tooltip.erebus.planticide").getFormattedText());
	}

	@Override
	 public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote)
			for (int i = -2; i <= 2; i++)
				for (int j = -2; j <= 2; j++)
					for (int k = -2; k <= 2; k++) {
						IBlockState state = world.getBlockState(pos.add(i, j, k));
						if (state.getBlock().isLeaves(state, world, pos.add(i, j, k)))
							Utils.breakBlockWithParticles(world, pos.add(i, j, k));
						else if (state.getBlock() == Blocks.GRASS || state.getBlock() == Blocks.DIRT || state.getBlock() == Blocks.MYCELIUM || state.getBlock() == Blocks.FARMLAND) {
							Utils.playBreakParticles(world, pos.add(i, j, k));
							world.setBlockState(pos.add(i, j, k), Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, DirtType.COARSE_DIRT), 3);
						} else if (state.getBlock() instanceof IPlantable || state.getBlock() instanceof IGrowable)
							Utils.breakBlockWithParticles(world, pos.add(i, j, k));
					}
		else
			player.swingArm(hand);

		if (!player.capabilities.isCreativeMode)
			stack.shrink(1);

		return EnumActionResult.SUCCESS;
	}
}