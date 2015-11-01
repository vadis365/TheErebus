package erebus.block;

import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModBlocks;
import erebus.core.helper.Utils;
import erebus.tileentity.TileEntityPreservedBlock;

public class BlockPreservedBlock extends BlockContainer {

	public BlockPreservedBlock() {
		super(Material.glass);
		setHardness(1.0F);
		setBlockName("erebus.preservedBlock");
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack) {
		if (!world.isRemote && stack.hasTagCompound()) {
			TileEntityPreservedBlock tile = Utils.getTileEntity(world, x, y, z, TileEntityPreservedBlock.class);
			if (tile != null)
				tile.setEntityNBT(stack.getTagCompound().getCompoundTag("EntityNBT"));
		}
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityPreservedBlock();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return ModBlocks.amber.getIcon(side, 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderBlockPass() {
		return 1;
	}

	@SubscribeEvent
	public void onBreakEvent(BlockEvent.BreakEvent event) {
		World world = event.world;
		int x = event.x;
		int y = event.y;
		int z = event.z;

		TileEntityPreservedBlock tile = Utils.getTileEntity(world, x, y, z, TileEntityPreservedBlock.class);
		if (tile != null)
			if (EnchantmentHelper.getSilkTouchModifier(event.getPlayer())) {
				ItemStack stack = new ItemStack(this);
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setTag("EntityNBT", tile.getEntityNBT());
				stack.setTagCompound(nbt);
				this.dropBlockAsItem(world, x, y, z, stack);
			} else
				tile.spawnTrappedEntity();
	}
}