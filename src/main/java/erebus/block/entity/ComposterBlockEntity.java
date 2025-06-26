package erebus.block.entity;

import javax.annotation.Nonnull;

import erebus.inventory.server.ComposterMenu;
import erebus.registries.ModItems;
import erebus.registries.blocks.ModBlockEntities;
import erebus.registries.blocks.providers.PlantBlocks;
import erebus.registries.data.ModTags;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class ComposterBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {
	public int composterBurnTime;
	public int currentItemBurnTime;
	public int composterCookTime;
	private final int SMELT_SLOT = 0;
	private final int FUEL_SLOT = 1;
	private final int RESULT_SLOT = 2;

	public ComposterBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.COMPOSTER.get(), 3,  pos, state);
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
	public void loadAdditional(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
		super.loadAdditional(nbt, registries);

		composterBurnTime = nbt.getShort("BurnTime");
		composterCookTime = nbt.getShort("CookTime");
		currentItemBurnTime = getItemBurnTime(getItems().get(1));
	}

	@Override
	public void saveAdditional(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
		super.saveAdditional(nbt, registries);
		nbt.putShort("BurnTime", (short) composterBurnTime);
		nbt.putShort("CookTime", (short) composterCookTime);
	}

	@OnlyIn(Dist.CLIENT)
	public int getCookProgressScaled(int cookTime) {
		return composterCookTime * cookTime / 200;
	}

	@OnlyIn(Dist.CLIENT)
	public int getBurnTimeRemainingScaled(int burnTime) {
		if (currentItemBurnTime == 0)
			currentItemBurnTime = 200;

		return composterBurnTime * burnTime / currentItemBurnTime;
	}

	public boolean isBurning() {
		return composterBurnTime > 0;
	}

	public static <T extends BlockEntity> void serverTick(Level level, BlockPos pos, BlockState state, T t) {
		if (t instanceof ComposterBlockEntity tile) {
		boolean flag = tile.composterBurnTime > 0;
		boolean isDirty = true; // shit, but needed to test 

		if (tile.composterBurnTime > 0)
			tile.composterBurnTime--;

			if (tile.composterBurnTime != 0 || !tile.getItems().get(1).isEmpty() && !tile.getItems().get(0).isEmpty()) {
				if (tile.composterBurnTime == 0 && tile.canSmelt()) {
					tile.currentItemBurnTime = tile.composterBurnTime = getItemBurnTime(tile.getItems().get(1));

					if (tile.composterBurnTime > 0) {
						isDirty = true;

						if (!tile.getItems().get(1).isEmpty()) {
							tile.getItems().get(1).shrink(1);

							if (tile.getItems().get(1).getCount() == 0)
								tile.getItems().set(1, tile.getItems().get(1).getItem().getCraftingRemainingItem(tile.getItems().get(1)));
						}
					}
				}

				if (tile.isBurning() && tile.canSmelt()) {
					++tile.composterCookTime;

					if (tile.composterCookTime == 200) {
						tile.composterCookTime = 0;
						tile.smeltItem();
						isDirty = true;
					}
				} else
					tile.composterCookTime = 0;
			}

			if (flag != tile.composterBurnTime > 0) {
				isDirty = true;
			}


		if (isDirty)
			tile.updateBlock();
		}
	}
	
	public void updateBlock() {
		getLevel().sendBlockUpdated(worldPosition, getLevel().getBlockState(worldPosition), getLevel().getBlockState(worldPosition), 3);
	}

	private boolean canSmelt() {
		if (getItems().get(0).isEmpty())
			return false;
		else {
			ItemStack itemstack = getItems().get(0);
			if (itemstack.isEmpty() || !itemstack.is(ModTags.COMPOSTABLE))
				return false;
			if (getItems().get(2).isEmpty())
				return true;
			if (!ItemStack.isSameItem(getItems().get(2), itemstack))
				return false;
			int result = getItems().get(2).getCount() + itemstack.getCount();
			return result <= getItems().get(2).getMaxStackSize(); // Forge
			// BugFix:
			// Make
			// it
			// respect
			// stack
			// sizes
			// properly.
		}
	}

	public void smeltItem() {
		if (canSmelt()) {
			ItemStack itemstack = new ItemStack(ModItems.COMPOST.get());

			if (getItems().get(2).isEmpty())
				getItems().set(2, itemstack.copy());
			else if (getItems().get(2).getItem() == itemstack.getItem())
				getItems().get(2).grow(itemstack.getCount());// Forge BugFix:
			// Results may
			// have multiple
			// items

			getItems().get(0).shrink(1);

			if (getItems().get(0).getCount() <= 0)
				getItems().set(0, ItemStack.EMPTY);
		}
	}

	public static int getItemBurnTime(ItemStack itemStack) {
		if (itemStack.isEmpty())
			return 0;
		else {
			if (itemStack.is(PlantBlocks.MOULD.asItem()))
				return 800;

			if (itemStack.is(PlantBlocks.MOULD_CULTIVATED.asItem()))
				return 400;
		}

		return 0;
	}

	public boolean isItemFuel(ItemStack is) {
		return getItemBurnTime(is) > 0;
	}

	public boolean isItemValidForSlot(int slot, ItemStack is) {
		return slot == RESULT_SLOT ? false : slot == FUEL_SLOT ? isItemFuel(is) : true;
	}

	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		return new ComposterMenu(containerId, playerInventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return side == Direction.DOWN ? new int[] { RESULT_SLOT} : new int[] {FUEL_SLOT, SMELT_SLOT };
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, Direction direction) {
		return isItemValidForSlot(index, itemStack);
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return index == RESULT_SLOT;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return null;
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("erebus.container.composter");
	}
}