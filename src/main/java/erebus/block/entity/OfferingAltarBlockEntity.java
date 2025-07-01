package erebus.block.entity;

import erebus.network.client.OfferingAltarNBTPacket;
import erebus.registries.ModCustomRecipes;
import erebus.recipes.MultiStackInput;
import erebus.recipes.altar.OfferingAltarRecipe;
import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.network.PacketDistributor;

import javax.annotation.Nonnull;

public class OfferingAltarBlockEntity extends BlockEntityInventoryHelper {
	public int time = 0;
	public int prevTime;
	protected ItemStack output;
	private static final int MAX_TIME = 450;
	public final RecipeManager.CachedCheck<MultiStackInput, OfferingAltarRecipe> quickCheck = RecipeManager.createCheck(ModCustomRecipes.OFFERING_ALTAR_RECIPE.get());
	public int rotation;
	public int prevRotation;
	public boolean isCrafting = false;

	public OfferingAltarBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.OFFERING_ALTAR.get(), 4, pos, state);
		output = ItemStack.EMPTY;
	}

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
		if (blockEntity instanceof OfferingAltarBlockEntity altar) {
			if (level.isClientSide()) {
				altar.prevRotation = altar.rotation;
				altar.prevTime = altar.time;
				altar.rotation += 2;
				if (altar.rotation >= 360) {
					altar.rotation -= 360;
					altar.prevRotation -= 360;
				}
				if(altar.isCrafting)
					altar.time += 2;
				else
					altar.time = 0;
			} else {
				MultiStackInput input = new MultiStackInput(altar.getItems().subList(0, 3));
				RecipeHolder<OfferingAltarRecipe> recipe = altar.quickCheck.getRecipeFor(input, level).orElse(null);
				if (recipe != null && !altar.isCrafting) {
					altar.output = recipe.value().assemble(input, level.registryAccess());
					altar.isCrafting = true;
					altar.updateBlockWhenChanged();
				}

				if ((altar.output.isEmpty() || recipe == null) && (altar.isCrafting || altar.time > 0)) {
					altar.time = 0;
					altar.isCrafting = false;
					altar.updateBlockWhenChanged();
					}
				else {
					altar.isCrafting = true;
					altar.time += 2;
					//TODO not sure this is even needed tbh - won't know until server testing 
					//PacketDistributor.sendToPlayersNear((ServerLevel) altar.getLevel(), null, altar.getBlockPos().getX(), altar.getBlockPos().getY(), altar.getBlockPos().getZ(), 30, new OfferingAltarTimerPacket(altar.getBlockPos().getX(), altar.getBlockPos().getY(), altar.getBlockPos().getZ(), altar.time, true));
					if (altar.time == 90 || altar.time == 270 || altar.time == 450)
						level.levelEvent(2011, pos.above(), 15);

					if (altar.time >= MAX_TIME) {
						level.levelEvent(2004, pos.above(), 0);
						altar.getItems().set(3, altar.output.copy());
						for (int i = 0; i < 3; i++)
							if (!altar.getItems().get(i).isEmpty()) {
								altar.getItems().get(i).shrink(1);
								if (altar.getItems().get(i).getCount() <= 0)
									altar.getItems().set(i, ItemStack.EMPTY);
							}
						altar.isCrafting = false;
						altar.time = 0;
						altar.updateBlockWhenChanged();
					}
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
		super.onDataPacket(net, packet, registries);
		loadAdditional(packet.getTag(), registries);
		updateBlockWhenChanged();
	}

	public void updateBlockWhenChanged() {
		if (!getLevel().isClientSide()) {
			CompoundTag nbt = new CompoundTag();
			saveAdditional(nbt, level.registryAccess());
			PacketDistributor.sendToPlayersNear((ServerLevel) getLevel(), null, getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), 30, new OfferingAltarNBTPacket(getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ(), nbt));
			final BlockState state = getLevel().getBlockState(getBlockPos());
			getLevel().sendBlockUpdated(getBlockPos(), state, state, 8);
			setChanged();
		}
	}
	
	public void updateBlock() {
		getLevel().sendBlockUpdated(worldPosition, getLevel().getBlockState(worldPosition), getLevel().getBlockState(worldPosition), 3);
	}

	@Override
	public void loadAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
		super.loadAdditional(nbt, registries);
		time = nbt.getInt("time");
		isCrafting = nbt.getBoolean("isCrafting");
	}

	@Override
	public void saveAdditional(CompoundTag nbt, HolderLookup.Provider registries) {
		super.saveAdditional(nbt, registries);
		nbt.putInt("time", time);
		nbt.putBoolean("isCrafting", isCrafting);
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