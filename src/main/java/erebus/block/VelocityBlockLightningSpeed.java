package erebus.block;

import net.minecraft.world.level.block.Block;

public class VelocityBlockLightningSpeed extends VelocityBlock {
	public VelocityBlockLightningSpeed(Block.Properties properties) {
		super(properties);
	}

	@Override
	protected double speed() {
		return 1.5D;
	}
}
