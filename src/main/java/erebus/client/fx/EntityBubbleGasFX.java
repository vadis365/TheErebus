package erebus.client.fx;

import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.world.World;

public class EntityBubbleGasFX extends EntityBubbleFX {

	public EntityBubbleGasFX(World world, double x, double y, double z, double vecX, double vecY, double vecZ) {
		super(world, x, y, z, vecX, vecY, vecZ);
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY += 0.002D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.8500000238418579D;
		this.motionY *= 0.3500000238418579D;
		this.motionZ *= 0.8500000238418579D;

		if (this.particleMaxAge-- <= 0) {
			this.setDead();
		}
	}
}
