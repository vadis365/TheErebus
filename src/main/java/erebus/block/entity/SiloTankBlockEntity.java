package erebus.block.entity;

import erebus.inventory.server.SiloTankMenu;
import erebus.registries.blocks.ModBlockEntities;
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

public class SiloTankBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {

	public SiloTankBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.SILO_TANK.get(), 104,  pos, state);
	}

	@Override
	public Component getDisplayName() {
		return Component.translatable("erebus.container.silo").append(Component.literal(" X:" + getBlockPos().getX() + " Y:" + getBlockPos().getY() + " Z:" + getBlockPos().getZ()));
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		int[] SLOTS = new int[getContainerSize()];
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
		return new SiloTankMenu(containerId, playerInventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
	}
}
