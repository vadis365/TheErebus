package erebus.block.entity;

import javax.annotation.Nonnull;

import erebus.registries.ModBlockEntities;
import erebus.registries.ModBlocks;
import erebus.registries.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class OfferingAltarBlockEntity extends BlockEntityInventoryHelper {
	public int time = 0;
	protected ItemStack output;
	private static final int MAX_TIME = 450;

	public OfferingAltarBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.OFFERING_ALTAR.get(), 4, pos, state);
		output = ItemStack.EMPTY;
	}

	//@Override
	//public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
	//	return oldState.getBlock() != newState.getBlock();
	//}

	@OnlyIn(Dist.CLIENT)
	public ItemStack getItemForRendering(int slot) {
		if (getItems().get(slot).isEmpty())
			return ItemStack.EMPTY;
		else {
			return getItems().get(slot);
		}
	}

	public void popStack() {
		if (!getLevel().isClientSide())
			for (int i = getItems().size() - 1; i >= 0; i--)
				if (!getItems().get(i).isEmpty()) {
					
					Block.popResource(getLevel(), getBlockPos().above(), getItems().get(i).copy());
					getItems().set(i, ItemStack.EMPTY);
					updateBlockWhenChanged();
					return;
				}
	}

	public void addStack(ItemStack stack) {
		if (stack.isEmpty() || stack.getCount() <= 0)
			return;
		if (getItems().get(getItems().size() - 1).isEmpty())
			for (int i = 0; i < getItems().size() - 1; i++)
				if (getItems().get(i).isEmpty()) {
					addStack(i, stack);
					return;
				}
	}

	private void addStack(int slot, ItemStack stack) {
		if (!getLevel().isClientSide()) {
			getItems().set(slot, stack.copy());
			getItems().get(slot).setCount(1);
			stack.shrink(1);
			updateBlockWhenChanged();
		}
	}

	public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T blockEntity) {
		if(blockEntity instanceof OfferingAltarBlockEntity altar) {
		if (level.isClientSide())
			return;

		if (altar.output.isEmpty())
			altar.time = 0;
		else {
			altar.time++;
			//Erebus.NETWORK_WRAPPER.sendToAll(new PacketOfferingAltarTimer(getPos().getX(), getPos().getY(), getPos().getZ(), time));
			altar.updateBlockWhenChanged(); // REMOVE! Just need to see if things go bang or not 
			if (altar.time == 90 || altar.time == 270 || altar.time == 450) {
				for(int count = 0; count < 5; count++)
					level.levelEvent(2005, pos.above(), 4);
				if (altar.time >= MAX_TIME)
					level.levelEvent(2004, pos.above(), 0);
			}
			if (altar.time >= MAX_TIME) {
				altar.getItems().set(3, altar.output.copy());
				for (int i = 0; i < 3; i++)
					if (!altar.getItems().get(i).isEmpty()) {
						altar.getItems().get(i).shrink(1);
						if (altar.getItems().get(i).getCount() <= 0)
							altar.getItems().set(i, ItemStack.EMPTY);
					}
				altar.time = 0;
				altar.updateBlockWhenChanged();
			}
		}
		}
	}

	@Nonnull
	@Override
	public CompoundTag getUpdateTag(@Nonnull HolderLookup.Provider registries) {
		CompoundTag nbt = new CompoundTag();
		saveAdditional(nbt, registries);
		return nbt;
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		CompoundTag nbt = new CompoundTag();
		saveAdditional(nbt, level.registryAccess());
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(@Nonnull Connection net, ClientboundBlockEntityDataPacket packet, @Nonnull HolderLookup.Provider registries) {
		loadAdditional(packet.getTag(), registries);
		updateBlockWhenChanged();
	}

	public void updateBlockWhenChanged() {
		output = new ItemStack(ModItems.GAEAN_GEM.get()); // just to see if it renders
		//output = OfferingAltarRecipe.getOutput(getItems().get(0), getItems().get(1), getItems().get(2));

		if (!getLevel().isClientSide()) {
			final BlockState state = getLevel().getBlockState(getBlockPos());
			getLevel().sendBlockUpdated(getBlockPos(), state, state, 8);
			setChanged();
			//Erebus.NETWORK_WRAPPER.sendToAll(new PacketOfferingAltar(getPos().getX(), getPos().getY(), getPos().getZ(), nbt));
		}
	}
/*
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(getPos()).grow(2);
	}
*/
	@Override
	public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
		super.loadAdditional(nbt, registries);
		time = nbt.getInt("time");
	}

	@Override
	public void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
		super.saveAdditional(nbt, registries);
		nbt.putInt("time", time);
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return slot != 3;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return null;
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, @Nonnull ItemStack itemStackIn, Direction direction) {
		return index != 3;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, @Nonnull ItemStack stack, @Nonnull Direction direction) {
		return index == 3;
	}

	@Nonnull
	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(getItems(), slot);
	}
}