package erebus.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public class ModelFireFly extends ModelBase
{
  public ModelFireFly()
  {
  }

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel) {
		super.render(entity, limbSwing, limbSwingAngle, entityTickTime, rotationYaw, rotationPitch, unitPixel);
	}
}
