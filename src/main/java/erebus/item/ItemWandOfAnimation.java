package erebus.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.ModTabs;
import erebus.core.helper.Utils;
import erebus.entity.EntityAnimatedBambooCrate;
import erebus.entity.EntityAnimatedBlock;
import erebus.entity.EntityAnimatedChest;
import erebus.tileentity.TileEntityBambooCrate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

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
		list.add(StatCollector.translateToLocal("tooltip.erebus.wandofanimation"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister reg) {
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (!player.canPlayerEdit(x, y, z, side, stack))
			return false;
		else {
			Block block = world.getBlock(x, y, z);
			int blockMeta = world.getBlockMetadata(x, y, z);
			if (!world.isRemote && block != null && canAnimate(block, world, x, y, z)) {
				EntityAnimatedBlock entityAnimatedBlock;
				if (block == Blocks.chest)
					entityAnimatedBlock = new EntityAnimatedChest(world).setContents(Utils.getTileEntity(world, x, y, z, TileEntityChest.class));
				else if (block == ModBlocks.bambooCrate)
					entityAnimatedBlock = new EntityAnimatedBambooCrate(world).setContents(Utils.getTileEntity(world, x, y, z, TileEntityBambooCrate.class));
				else
					entityAnimatedBlock = new EntityAnimatedBlock(world);
				world.setBlockToAir(x, y, z);
				entityAnimatedBlock.setLocationAndAngles((double) x + 0.5F, y, (double) z + 0.5F, 0.0F, 0.0F);
				entityAnimatedBlock.setBlock(block, blockMeta);
				world.spawnEntityInWorld(entityAnimatedBlock);
				entityAnimatedBlock.setOwnerName(player.getUniqueID().toString());
				world.playSoundEffect(x, y, z, "erebus:altaroffering", 0.2F, 1.0F);
				stack.damageItem(1, player);
				return true;
			}
		}
		return false;
	}

	private boolean canAnimate(Block block, World world, int x, int y, int z) {
		return block == Blocks.chest || !(block instanceof BlockContainer) && block.getBlockHardness(world, x, y, z) >= 0 && block.getBlockBoundsMaxX() - block.getBlockBoundsMinX() >= 0.7F && block.getBlockBoundsMaxZ() - block.getBlockBoundsMinZ() >= 0.7F && block.getBlockBoundsMaxY() - block.getBlockBoundsMinY() >= 0.7F || block == ModBlocks.bambooCrate;
	}
}