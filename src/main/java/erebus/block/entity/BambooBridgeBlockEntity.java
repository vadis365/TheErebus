package erebus.block.entity;

import erebus.registries.blocks.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.NonNull;

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
	protected void saveAdditional(@NonNull ValueOutput output) {
		super.saveAdditional(output);
		output.putBoolean("renderSide1", renderSide1);
		output.putBoolean("renderSide2", renderSide2);
	}

	@Override
	protected void loadAdditional(@NonNull ValueInput input) {
		super.loadAdditional(input);
		renderSide1 = input.getBooleanOr("renderSide1", false);
		renderSide2 = input.getBooleanOr("renderSide2", false);
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	public void onDataPacket(@NonNull Connection net, @NonNull ValueInput valueInput) {
		super.onDataPacket(net, valueInput);
		loadAdditional(valueInput);
	}
}
