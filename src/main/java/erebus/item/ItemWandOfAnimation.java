package erebus.item;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.entity.EntityAnimatedBambooCrate;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityAnimatedChest;
import erebus.tileentity.TileEntityBambooCrate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

import java.util.List;

public class ItemWandOfAnimation extends Item {
	public ItemWandOfAnimation() {
		setFull3D();
		setNoRepair();
		setMaxDamage(64);
		setMaxStackSize(1);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	@SideOnly(Side.CLIENT)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		list.add("Right click blocks to animate them");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (!player.canPlayerEdit(pos, side, stack))
			return false;
		 else {
			Block block = world.getBlockState(pos).getBlock();
			int blockMeta = block.getMetaFromState(world.getBlockState(pos));
			if (!world.isRemote && block != null && canAnimate(block, world, pos)) {
				EntityAnimatedBlock entityAnimatedBlock;
				if (block == Blocks.chest)
					entityAnimatedBlock = new EntityAnimatedChest(world).setContents(Utils.getTileEntity(world, pos, TileEntityChest.class));
				else if (block == ModBlocks.bambooCrate)
					entityAnimatedBlock = new EntityAnimatedBambooCrate(world).setContents(Utils.getTileEntity(world, pos, TileEntityBambooCrate.class));
				else
					entityAnimatedBlock = new EntityAnimatedBlock(world);
				world.setBlockToAir(pos);
				entityAnimatedBlock.setLocationAndAngles((double) pos.getX() + 0.5F, pos.getY(), (double) pos.getZ() + 0.5F, 0.0F, 0.0F);
				entityAnimatedBlock.setBlock(block, blockMeta);
				world.spawnEntityInWorld(entityAnimatedBlock);
				entityAnimatedBlock.setOwnerName(player.getUniqueID().toString());
				world.playSoundEffect(pos.getX(), pos.getY(), pos.getZ(), "erebus:altaroffering", 0.2F, 1.0F);
				stack.damageItem(1, player);
				return true;
			}
		}
		return false;
	}

	private boolean canAnimate(Block block, World world, BlockPos pos) {
		return block == Blocks.chest || !(block instanceof BlockContainer) && block.getBlockHardness(world, pos) >= 0 && block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() >= 0.7F && block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() >= 0.7F && block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() >= 0.7F || block == ModBlocks.bambooCrate;
	}
}