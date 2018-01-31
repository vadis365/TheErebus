package erebus.tileentity;

import erebus.Erebus;
import erebus.core.helper.Utils;
import erebus.network.client.PacketOfferingAltar;
import erebus.network.client.PacketOfferingAltarTimer;
import erebus.recipes.OfferingAltarRecipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityOfferingAltar extends TileEntityBasicInventory implements ITickable {
	@SideOnly(Side.CLIENT)
	protected ItemStack ghostItem;
	public int time = 0;
	protected ItemStack output;

	private static final int MAX_TIME = 450;

	public TileEntityOfferingAltar() {
		this(4, "offeringAltar");
		ghostItem = ItemStack.EMPTY;
		output = ItemStack.EMPTY;
	}

	protected TileEntityOfferingAltar(int size, String name) {
		super(size, name);
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@SideOnly(Side.CLIENT)
	public ItemStack getItemForRendering(int slot) {
		if (getInventory().get(slot).isEmpty())
			return ItemStack.EMPTY;
		else {
			return getInventory().get(slot);
		}
	}

	public void popStack() {
		if (!getWorld().isRemote)
			for (int i = getSizeInventory() - 1; i >= 0; i--)
				if (!getInventory().get(i).isEmpty()) {
					Utils.dropStackNoRandom(getWorld(), getPos().up(), getInventory().get(i).copy());
					getInventory().set(i, ItemStack.EMPTY);
					updateBlock();
					return;
				}
	}

	public void addStack(ItemStack stack) {
		if (stack.isEmpty() || stack.getCount() <= 0)
			return;
		if (getInventory().get(getSizeInventory() - 1).isEmpty())
			for (int i = 0; i < getSizeInventory() - 1; i++)
				if (getInventory().get(i).isEmpty()) {
					addStack(i, stack);
					return;
				}
	}

	private void addStack(int slot, ItemStack stack) {
		if (!getWorld().isRemote) {
			getInventory().set(slot, stack.copy());
			getInventory().get(slot).setCount(1);
			stack.shrink(1);
			updateBlock();
		}
	}

	@Override
	public void update() {
		if (getWorld().isRemote)
			return;

		if (output.isEmpty())
			time = 0;
		else {
			time++;
			Erebus.NETWORK_WRAPPER.sendToAll(new PacketOfferingAltarTimer(getPos().getX(), getPos().getY(), getPos().getZ(), time));

			if (time == 90 || time == 270 || time == 450) {
				for(int count = 0; count < 5; count++)
					getWorld().playEvent(2005, getPos().up(), 4);
				if (time >= MAX_TIME)
					getWorld().playEvent(2004, getPos().up(), 0);
			}
			if (time >= MAX_TIME) {
				getInventory().set(3, output.copy());
				for (int i = 0; i < 3; i++)
					if (!getInventory().get(i).isEmpty()) {
						getInventory().get(i).shrink(1);
						if (getInventory().get(i).getCount() <= 0)
							getInventory().set(i, ItemStack.EMPTY);
					}
				time = 0;
				updateBlock();
			}
		}
	}

	@Override
    public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = new NBTTagCompound();
        return writeToNBT(nbt);
    }

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
		return new SPacketUpdateTileEntity(pos, 0, nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		readFromNBT(packet.getNbtCompound());
	}

	public void updateBlock() {
		final IBlockState state = this.getWorld().getBlockState(this.getPos());
		this.getWorld().notifyBlockUpdate(this.getPos(), state, state, 3);
		this.markDirty();
	}

	@Override
	public void markDirty() {
		super.markDirty();

		output = OfferingAltarRecipe.getOutput(getInventory().get(0), getInventory().get(1), getInventory().get(2));

		if (getWorld() != null && !getWorld().isRemote) {
			NBTTagCompound nbt = new NBTTagCompound();
			writeToNBT(nbt);
			Erebus.NETWORK_WRAPPER.sendToAll(new PacketOfferingAltar(getPos().getX(), getPos().getY(), getPos().getZ(), nbt));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos()).grow(2);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		time = nbt.getInteger("time");
		markDirty();
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("time", time);
		return nbt;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot != 3;
	}

	@Override
	public int[] getSlotsForFace(EnumFacing side) {
		return null;
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
		return index != 3;
	}

	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
		return index == 3;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		return null;
	}
}