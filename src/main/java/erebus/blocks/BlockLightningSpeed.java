package erebus.blocks;

public class BlockLightningSpeed extends BlockVelocity {
	public BlockLightningSpeed() {
		super();
	}

	@Override
	protected double speed() {
		return 1.5D;
	}
}