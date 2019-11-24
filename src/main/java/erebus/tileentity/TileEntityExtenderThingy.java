package erebus.tileentity;

import erebus.ModBlocks;
import erebus.block.bamboo.BlockBambooBridge;
import erebus.block.bamboo.BlockExtenderThingy;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class TileEntityExtenderThingy extends TileEntityBasicInventory implements ITickable {

	private boolean extending;
	public EnumFacing direction;
	private IItemHandler itemHandler;

	public TileEntityExtenderThingy() {
		super(6, "container.extenderThingy");
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public void update() {
		if (getWorld().isRemote)
			return;
		IBlockState state = getWorld().getBlockState(getPos());
		if (direction == null)
			direction = state.getValue(BlockExtenderThingy.FACING);

		Block blockID;
		Block extension = getExtension(direction);
		int index = getIndex(extension);

		if (extending)
			blockID = extension;
		else {
			blockID = null;
			index--;
		}

		int x = getPos().getX() + index * direction.getXOffset();
		int y = getPos().getY() + index * direction.getYOffset();
		int z = getPos().getZ() + index * direction.getZOffset();
		if (x == getPos().getX() && y == getPos().getY() && z == getPos().getZ())
			return;


		if (state.getBlock() == null || state.getBlock().isReplaceable(getWorld(), new BlockPos(x, y, z)) || !extending)
			if (decreaseInventory(blockID))
				if (addToInventory(new BlockPos(x, y, z)))
					if (extending) {
						getWorld().setBlockState(new BlockPos(x, y, z), getStateFromDirection(direction), 3);
						getWorld().playSound((EntityPlayer)null, new BlockPos(x, y, z), extension.getSoundType().getBreakSound(), SoundCategory.BLOCKS, (extension.getSoundType().getVolume() + 1.0F) / 2.0F, extension.getSoundType().getPitch() * 0.8F);
					} else {
						getWorld().setBlockToAir(new BlockPos(x, y, z));
						getWorld().playEvent((EntityPlayer)null, 2001, new BlockPos(x, y, z), Block.getIdFromBlock(extension));
					}
	}

	private int getIndex(Block extension) {
		int index = 1;

		int x = getPos().getX() + index * direction.getXOffset();
		int y = getPos().getY() + index * direction.getYOffset();
		int z = getPos().getZ() + index * direction.getZOffset();

		while (getWorld().getBlockState(new BlockPos (x, y, z)).getBlock() == extension) {
			index++;
			x = getPos().getX() + index * direction.getXOffset();
			y = getPos().getY() + index * direction.getYOffset();
			z = getPos().getZ() + index * direction.getZOffset();
		}
		IBlockState state = getWorld().getBlockState(new BlockPos(x, y, z));
		if (state.getBlock() == null || state.getBlock().isReplaceable(getWorld(), new BlockPos(x, y, z)) || !extending)
			return index;

		return index - 1;
	}

	private boolean addToInventory(BlockPos pos) {
		IBlockState state = getWorld().getBlockState(pos);

		if (getWorld().isAirBlock(pos) || state.getBlock().isReplaceable(getWorld(), pos))
			return true;
		for (int i = 0; i < getInventory().size(); i++)
			if (getInventory().get(i).isEmpty()) {
				getInventory().set(i, new ItemStack(state.getBlock(), 1, 0));
				return true;
			} else if (getInventory().get(i).getItem() == Item.getItemFromBlock(state.getBlock()) && getInventory().get(i).getCount() < getInventory().get(i).getMaxStackSize() && getInventory().get(i).getCount() < getInventoryStackLimit()) {
				getInventory().get(i).grow(1);
				return true;
			}
		return false;
	}

	private boolean decreaseInventory(Block blockID) {
		if (blockID == null)
			return true;
		for (int i = 0; i < getInventory().size(); i++)
			if (!getInventory().get(i).isEmpty() && getInventory().get(i).getItem() == Item.getItemFromBlock(blockID)) {
				getInventory().get(i).shrink(1);
				if (getInventory().get(i).getCount() <= 0)
					getInventory().set(i, ItemStack.EMPTY);
				return true;
			}
		return false;
	}

	private Block getExtension(EnumFacing facing) {
		return facing == EnumFacing.UP || facing == EnumFacing.DOWN ? ModBlocks.BAMBOO_NERD_POLE : ModBlocks.BAMBOO_BRIDGE;
	}

	private IBlockState getStateFromDirection(EnumFacing facing) {
		switch (facing) {
			case UP:
				return ModBlocks.BAMBOO_NERD_POLE.getDefaultState();
			case DOWN:
				return ModBlocks.BAMBOO_NERD_POLE.getDefaultState();
			case EAST:
			case WEST:
			case NORTH:
			case SOUTH:
				return ModBlocks.BAMBOO_BRIDGE.getDefaultState().withProperty(BlockBambooBridge.FACING, facing);
		}
		return ModBlocks.BAMBOO_BRIDGE.getDefaultState();
	}

	public void setExtending(boolean extending) {
		this.extending = extending;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return !stack.isEmpty() && (stack.getItem() == Item.getItemFromBlock(ModBlocks.BAMBOO_NERD_POLE) || stack.getItem() == Item.getItemFromBlock(ModBlocks.BAMBOO_BRIDGE));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound data) {
		super.writeToNBT(data);
		data.setBoolean("extending", extending);
		return data;
	}

	@Override
	public void readFromNBT(NBTTagCompound data) {
		super.readFromNBT(data);
		extending = data.getBoolean("extending");
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		int[] SLOTS = new int[getSizeInventory()];
		for (int index = 0; index < SLOTS.length; index++)
			SLOTS[index] = index;
		return SLOTS;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return true;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return true;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}
	
	protected IItemHandler createUnSidedHandler() {
		return new InvWrapper(this);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) (itemHandler == null ? (itemHandler = createUnSidedHandler()) : itemHandler);
		return super.getCapability(capability, facing);
	}
}