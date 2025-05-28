package erebus.block.entity;

import erebus.block.bamboo.BambooCrateBlock;
import erebus.block.bamboo.EnumCrateType;
import erebus.inventory.server.BambooCrateMenu;
import erebus.inventory.server.ColossalCrateMenu;
import erebus.registries.ModBlockEntities;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class BambooCrateBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {

	public BambooCrateBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.BAMBOO_CRATE.get(), 27,  pos, state);
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
		return true;
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(getItems(), slot);
	}

	@Override
	public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
		if (this.getBlockState().getValue(BambooCrateBlock.CRATE_TYPE) != EnumCrateType.DEFAULT) {
			return new ColossalCrateMenu(containerId, playerInventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
		}
		return new BambooCrateMenu(containerId, playerInventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("erebus.container.bamboo_crate");
	}

}