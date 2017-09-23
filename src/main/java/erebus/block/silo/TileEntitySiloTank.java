package erebus.block.silo;

import erebus.tileentity.TileEntityBasicInventory;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class TileEntitySiloTank extends TileEntityBasicInventory {

	private boolean active;
	private IItemHandler itemHandler;
	public TileEntitySiloTank() {
		super(104, "");
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public String getName() {
		return I18n.format(new TextComponentTranslation("container.silo").getFormattedText()) + " X:" + getPos().getX() + " Y:" + getPos().getY() + " Z:" + getPos().getZ();
	}

	public void setActive(boolean state) {
		active = state;
	}

	public boolean getActive() {
		return active;
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

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return true;
	}
	
	// INVENTORY CAPABILITIES STUFF

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
