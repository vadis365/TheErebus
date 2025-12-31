package erebus.block.entity;

import erebus.block.bamboo.BambooCrateBlock;
import erebus.block.types.EnumCrateType;
import erebus.inventory.server.BambooCrateMenu;
import erebus.inventory.server.ColossalCrateMenu;
import erebus.registries.blocks.ModBlockEntities;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class BambooCrateBlockEntity extends BlockEntityInventoryHelper implements MenuProvider {

	public BambooCrateBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.BAMBOO_CRATE.get(), 27,  pos, state);
	}

	@Override
	public boolean canPlaceItem(int slot, @NotNull ItemStack stack) {
		return true;
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, @NotNull ItemStack stack, Direction direction) {
		return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, @NotNull ItemStack stack, @NotNull Direction direction) {
		return true;
	}

	@Override
	public @NotNull ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(getItems(), slot);
	}

	@Override
	public AbstractContainerMenu createMenu(int containerId, @NotNull Inventory playerInventory, @NotNull Player player) {
		if (this.getBlockState().getValue(BambooCrateBlock.CRATE_TYPE) != EnumCrateType.DEFAULT) {
			return new ColossalCrateMenu(containerId, playerInventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
		}
		return new BambooCrateMenu(containerId, playerInventory, new FriendlyByteBuf(Unpooled.buffer()).writeBlockPos(worldPosition));
	}

	@Override
	public @NotNull Component getDisplayName() {
		return Component.translatable("erebus.container.bamboo_crate");
	}

    @Override
    protected void applyImplicitComponents(@NonNull DataComponentGetter getter) {
        super.applyImplicitComponents(getter);
		getter.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyInto(this.getItems());
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.@NotNull Builder builder) {
        super.collectImplicitComponents(builder);
        builder.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(this.getItems()));
    }

}