package erebus.tileentity;

import net.minecraft.tileentity.TileEntity;

public class TileEntityGlowingJar extends TileEntity {
	public int animationTicks;

	public TileEntityGlowingJar() {
	}

	@Override
	public void updateEntity() {
		if (animationTicks <= 24)
			animationTicks++;
		if (animationTicks > 24)
			animationTicks = 0;
	}
}