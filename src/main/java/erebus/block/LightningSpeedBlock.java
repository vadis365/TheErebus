package erebus.block;

public class LightningSpeedBlock extends VelocityBlock {
	public LightningSpeedBlock() {
		super();
		setUnlocalizedName("erebus.lightningSpeedBlock");
		setBlockTextureName("erebus:lightningSpeedBlock");
	}

	@Override
	protected double speed() {
		return 2.0D;
	}
}