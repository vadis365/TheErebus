package erebus.block.entity;

import javax.annotation.Nonnull;

import erebus.block.bamboo.BambooBridge;
import erebus.block.bamboo.BambooExtender;
import erebus.registries.ModBlockEntities;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BambooExtenderBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {

	private boolean extending;
	public Direction direction;

	public BambooExtenderBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.BAMBOO_EXTENDER.get(), 6,  pos, state);
	}

	public static <T extends BlockEntity> void serverTick(Level level, BlockPos pos, BlockState state, T t) {
		if (t instanceof BambooExtenderBlockEntity tile) {
		if (tile.direction == null)
			tile.direction = state.getValue(BambooExtender.FACING);

		BlockState blockID;
		BlockState extension = tile.getExtension(tile.direction);
		int index = tile.getIndex(extension);

		if (tile.extending)
			blockID = extension;
		else {
			blockID = null;
			index--;
		}

		int x = tile.getBlockPos().getX() + index * tile.direction.getStepX();
		int y = tile.getBlockPos().getY() + index * tile.direction.getStepY();
		int z = tile.getBlockPos().getZ() + index * tile.direction.getStepZ();
		if (x == tile.getBlockPos().getX() && y == tile.getBlockPos().getY() && z == tile.getBlockPos().getZ())
			return;

		if (state.getBlock() == null || tile.getLevel().getBlockState(new BlockPos(x, y, z)).is(BlockTags.REPLACEABLE) || !tile.extending)
			if (tile.decreaseInventory(blockID))
				if (tile.addToInventory(new BlockPos(x, y, z)))
					if (tile.extending) {
						tile.getLevel().setBlock(new BlockPos(x, y, z), tile.getStateFromDirection(tile.direction), 3);
						tile.getLevel().playSound(null, new BlockPos(x, y, z), extension.getSoundType().getBreakSound(), SoundSource.BLOCKS, (extension.getSoundType().getVolume() + 1.0F) / 2.0F, extension.getSoundType().getPitch() * 0.8F);
					} else {
						tile.getLevel().setBlock(new BlockPos(x, y, z), Blocks.AIR.defaultBlockState(), 3);
						tile.getLevel().levelEvent(null, 2001, new BlockPos(x, y, z), Block.getId(extension));
					}
		}
	}

	private int getIndex(BlockState extension) {
		int index = 1;

		int x = getBlockPos().getX() + index * direction.getStepX();
		int y = getBlockPos().getY() + index * direction.getStepY();
		int z = getBlockPos().getZ() + index * direction.getStepZ();

		while (getLevel().getBlockState(new BlockPos (x, y, z)) == extension) {
			index++;
			x = getBlockPos().getX() + index * direction.getStepX();
			y = getBlockPos().getY() + index * direction.getStepY();
			z = getBlockPos().getZ() + index * direction.getStepZ();
		}
		BlockState state = getLevel().getBlockState(new BlockPos(x, y, z));
		if (state.getBlock() == null || state.is(BlockTags.REPLACEABLE) || !extending)
			return index;

		return index - 1;
	}

	private boolean addToInventory(BlockPos pos) {
		BlockState state = getLevel().getBlockState(pos);

		if (getLevel().isEmptyBlock(pos) || getLevel().getBlockState(pos).is(BlockTags.REPLACEABLE))
			return true;
		for (int i = 0; i < getItems().size(); i++)
			if (getItems().get(i).isEmpty()) {
				getItems().set(i, new ItemStack(state.getBlock(), 1));
				return true;
			} else if (getItems().get(i).getItem() == Item.byBlock(state.getBlock()) && getItems().get(i).getCount() < getItems().get(i).getMaxStackSize()) {
				getItems().get(i).grow(1);
				return true;
			}
		return false;
	}

	private boolean decreaseInventory(BlockState blockID) {
		if (blockID == null)
			return true;
		for (int i = 0; i < getItems().size(); i++)
			if (!getItems().get(i).isEmpty() && getItems().get(i).getItem() == Item.byBlock(blockID.getBlock())) {
				getItems().get(i).shrink(1);
				if (getItems().get(i).getCount() <= 0)
					getItems().set(i, ItemStack.EMPTY);
				return true;
			}
		return false;
	}

	private BlockState getExtension(Direction facing) {
		return facing == Direction.UP || facing == Direction.DOWN ? OtherBlocks.BAMBOO_NERD_POLE.get().defaultBlockState() : OtherBlocks.BAMBOO_BRIDGE.get().defaultBlockState() ;
	}

	private BlockState getStateFromDirection(Direction facing) {
		switch (facing) {
			case UP:
				return OtherBlocks.BAMBOO_NERD_POLE.get().defaultBlockState();
			case DOWN:
				return OtherBlocks.BAMBOO_NERD_POLE.get().defaultBlockState();
			case EAST:
			case WEST:
			case NORTH:
			case SOUTH:
				return OtherBlocks.BAMBOO_BRIDGE.get().defaultBlockState().setValue(BambooBridge.FACING, facing);
		}
		return OtherBlocks.BAMBOO_BRIDGE.get().defaultBlockState();
	}

	public void setExtending(boolean extending) {
		this.extending = extending;
		System.out.println("Extending: " + extending);
	}

	@Override
	public void saveAdditional(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
		super.saveAdditional(nbt, registries);
		nbt.putBoolean("extending", extending);
	}

	@Override
	public void loadAdditional(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
		super.loadAdditional(nbt, registries);
		extending = nbt.getBoolean("extending");
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
	public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket packet, @Nonnull HolderLookup.Provider registries) {
		super.onDataPacket(net, packet, registries);
		loadAdditional(packet.getTag(), registries);
	}

	@Override
	public int getMaxStackSize() {
		return 64;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		int[] SLOTS = new int[getContainerSize()];
		for (int index = 0; index < SLOTS.length; index++)
			SLOTS[index] = index;
		return SLOTS;
	}
	
	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		 return !stack.isEmpty() && (stack.getItem() == Item.byBlock(OtherBlocks.BAMBOO_NERD_POLE.get()) || stack.getItem() == Item.byBlock(OtherBlocks.BAMBOO_BRIDGE.get()));
	}

	@Override
	public boolean canPlaceItemThroughFace(int index,ItemStack itemStack, Direction direction) {
		return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return null;
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("erebus.containers.bamboo_extender");
	}	

	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		// TODO Auto-generated method stub
		return null;
	}


}