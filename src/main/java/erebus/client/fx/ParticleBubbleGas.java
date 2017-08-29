package erebus.client.fx;

import net.minecraft.client.particle.ParticleBubble;
import net.minecraft.world.World;

public class ParticleBubbleGas extends ParticleBubble {

	public ParticleBubbleGas(World world, double x, double y, double z, double vecX, double vecY, double vecZ) {
		super(world, x, y, z, vecX, vecY, vecZ);
	}

	@Override
	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		motionY += 0.002D;
		move(motionX, motionY, motionZ);
		motionX *= 0.8500000238418579D;
		motionY *= 0.3500000238418579D;
		motionZ *= 0.8500000238418579D;

		if (particleMaxAge-- <= 0)
			setExpired();
	}
}