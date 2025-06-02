package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;

public class BambooBridgeBlockEntity extends BlockEntity {

	public boolean renderSide1, renderSide2;
	
    public BambooBridgeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BAMBOO_BRIDGE.get(), pos, state);
    }

	public void setRenderSide1(boolean side1) {
		renderSide1 = side1;
	}

	public void setRenderSide2(boolean side2) {
		renderSide2 = side2;
	}

	public boolean getRenderSide1() {
		return renderSide1;
	}

	public boolean getRenderSide2() {
		return renderSide2;
	}

	@Override
	public void saveAdditional(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
		super.saveAdditional(nbt, registries);
		nbt.putBoolean("renderSide1", renderSide1);
		nbt.putBoolean("renderSide2", renderSide2);
	}

	@Override
	public void loadAdditional(@Nonnull CompoundTag nbt, @Nonnull HolderLookup.Provider registries) {
		super.loadAdditional(nbt, registries);
		renderSide1 = nbt.getBoolean("renderSide1");
		renderSide2 = nbt.getBoolean("renderSide2");
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
}