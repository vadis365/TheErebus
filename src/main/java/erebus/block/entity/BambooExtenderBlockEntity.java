package erebus.block.entity;

import erebus.block.bamboo.BambooBridge;
import erebus.block.bamboo.BambooExtender;
import erebus.inventory.server.BambooExtenderMenu;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.blocks.ModBlocks;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

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

		BlockPos blockIndex = tile.getBlockPos().relative(tile.direction, index);
		if (blockIndex == tile.getBlockPos())
			return;

		if (state == null || tile.getLevel().getBlockState(blockIndex).is(BlockTags.REPLACEABLE) || !tile.extending)
			if (tile.decreaseInventory(blockID))
				if (tile.addToInventory(blockIndex))
					if (tile.extending) {
						tile.getLevel().setBlock(blockIndex, tile.getStateFromDirection(tile.direction), 3);
						tile.getLevel().playSound(null, blockIndex, extension.getSoundType().getBreakSound(), SoundSource.BLOCKS, (extension.getSoundType().getVolume() + 1.0F) / 2.0F, extension.getSoundType().getPitch() * 0.8F);
					} else {
						tile.getLevel().setBlock(blockIndex, Blocks.AIR.defaultBlockState(), 3);
						tile.getLevel().levelEvent(null, 2001, blockIndex, Block.getId(extension));
					}
		}
	}

	private int getIndex(BlockState extension) {
		int index = 1;
		BlockPos blockIndex = getBlockPos().relative(direction, index);
		while (getLevel().getBlockState(blockIndex).is(extension.getBlock())) {
			index++;
			blockIndex = getBlockPos().relative(direction, index);
		}
		BlockState state = getLevel().getBlockState(blockIndex);
        state.getBlock();
        if (state.is(BlockTags.REPLACEABLE) || !extending)
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
			} else if (getItems().get(i).getItem() == state.getBlock().asItem() && getItems().get(i).getCount() < getItems().get(i).getMaxStackSize()) {
				getItems().get(i).grow(1);
				return true;
			}
		return false;
	}

	private boolean decreaseInventory(BlockState blockID) {
		if (blockID == null)
			return true;
		for (int i = 0; i < getItems().size(); i++)
			if (!getItems().get(i).isEmpty() && getItems().get(i).getItem() == blockID.getBlock().asItem()) {
				getItems().get(i).shrink(1);
				if (getItems().get(i).getCount() <= 0)
					getItems().set(i, ItemStack.EMPTY);
				return true;
			}
		return false;
	}

	private BlockState getExtension(Direction facing) {
		return facing == Direction.UP || facing == Direction.DOWN ? ModBlocks.BAMBOO_NERD_POLE.get().defaultBlockState() : ModBlocks.BAMBOO_BRIDGE.get().defaultBlockState() ;
	}

	private BlockState getStateFromDirection(Direction facing) {
        return switch (facing) {
            case UP, DOWN -> ModBlocks.BAMBOO_NERD_POLE.get().defaultBlockState();
            case EAST, WEST, NORTH, SOUTH ->
                    ModBlocks.BAMBOO_BRIDGE.get().defaultBlockState().setValue(BambooBridge.FACING, facing);
        };
    }

	public void setExtending(boolean extending) {
		this.extending = extending;
		System.out.println("Extending: " + extending);
	}

	@Override
	public void saveAdditional(@NonNull ValueOutput output) {
		super.saveAdditional(output);
		output.putBoolean("extending", extending);
	}

	@Override
	public void loadAdditional(@NonNull ValueInput input) {
		super.loadAdditional(input);
		extending = input.getBooleanOr("extending", false);
	}

    @Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		 return !stack.isEmpty() && (stack.is(ModBlocks.BAMBOO_NERD_POLE.asItem()) || stack.is(ModBlocks.BAMBOO_BRIDGE.asItem()));
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, @NonNull ItemStack itemStack, Direction direction) {
		return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, @NonNull ItemStack stack, @NonNull Direction direction) {
		return true;
	}

	@Override
	public @NonNull ItemStack removeItemNoUpdate(int slot) {
		return null;
	}

	@Override
	public @NonNull Component getDisplayName() {
		return Component.translatable("erebus.container.bamboo_extender");
	}	

	@Override
	public AbstractContainerMenu createMenu(int containerId, @NonNull Inventory playerInventory, @NonNull Player player) {
		return new BambooExtenderMenu(containerId, playerInventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
	}


}
