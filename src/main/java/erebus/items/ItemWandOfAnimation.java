package erebus.items;

import java.util.List;

import javax.annotation.Nullable;

import erebus.ModBlocks;
import erebus.ModSounds;
import erebus.ModTabs;
import erebus.blocks.BlockDoubleHeightPlant;
import erebus.blocks.BlockPreservedBlock;
import erebus.core.helper.Utils;
import erebus.entity.EntityAnimatedBambooCrate;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityAnimatedChest;
import erebus.tileentity.TileEntityBambooCrate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemWandOfAnimation extends Item {
	public ItemWandOfAnimation() {
		setFull3D();
		setNoRepair();
		setMaxDamage(64);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.GEAR);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag flag) {
		list.add(TextFormatting.GRAY + new TextComponentTranslation("tooltip.erebus.wandofanimation").getFormattedText());
	}

	@Override
	 public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack stack = player.getHeldItem(hand);
		if (!player.canPlayerEdit(pos, facing, stack))
			return EnumActionResult.FAIL;
		else {
			IBlockState state = world.getBlockState(pos);
			Block block = state.getBlock();
			int blockMeta = block.getMetaFromState(state);
			if (!world.isRemote && block != null && canAnimate(state, world, pos)) {
				EntityAnimatedBlock entityAnimatedBlock;
				if (block == Blocks.CHEST)
					entityAnimatedBlock = new EntityAnimatedChest(world).setContents(Utils.getTileEntity(world, pos, TileEntityChest.class));
				else if (block == ModBlocks.BAMBOO_CRATE) {
					entityAnimatedBlock = new EntityAnimatedBambooCrate(world).setContents(Utils.getTileEntity(world, pos, TileEntityBambooCrate.class));
						blockMeta = 0;
					}
				else
					entityAnimatedBlock = new EntityAnimatedBlock(world);
				
				if(block == Blocks.LIT_PUMPKIN || block == Blocks.PUMPKIN)
					blockMeta = 1;

				world.setBlockToAir(pos);
				entityAnimatedBlock.setLocationAndAngles((double) pos.getX() + 0.5F, pos.getY(), (double) pos.getZ() + 0.5F, 0.0F, 0.0F);
				entityAnimatedBlock.setBlock(block, blockMeta);
				world.spawnEntity(entityAnimatedBlock);
				entityAnimatedBlock.setOwnerId(player.getUniqueID());
				world.playSound((EntityPlayer) null, pos, ModSounds.ALTAR_OFFERING, SoundCategory.BLOCKS, 0.2F, 1.0F);
				stack.damageItem(1, player);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}

	private boolean canAnimate(IBlockState state, World world, BlockPos pos) {
		return !(state.getBlock() instanceof BlockDoublePlant) || !(state.getBlock() instanceof BlockDoubleHeightPlant) || !(state.getBlock() instanceof BlockPreservedBlock) || state.getBlock() == Blocks.CHEST || !(state.getBlock() instanceof BlockContainer) && state.getBlockHardness(world, pos) >= 0 && state.getBoundingBox(world, pos).maxX - state.getBoundingBox(world, pos).minX >= 0.7F && state.getBoundingBox(world, pos).maxZ - state.getBoundingBox(world, pos).minZ >= 0.7F && state.getBoundingBox(world, pos).maxY - state.getBoundingBox(world, pos).minY >= 0.7F || state.getBlock() == ModBlocks.BAMBOO_CRATE;
	}
}