package erebus.api;

import erebus.api.PreservableEntityRegistry.EntityDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;

public class PreservedSkeletonHandler implements IPreservableEntityHandler {

	private final EntityDimensions dimensions = new EntityDimensions(0.5F, 0.0F, 0.5F, 0.35F);

	@Override
	public boolean handlesEntity(Entity entity) {
		return entity instanceof EntitySkeleton && ((EntitySkeleton) entity).getSkeletonType() == 1;
	}

	@Override
	public boolean canbePreserved(Entity entity) {
		return true;
	}

	@Override
	public EntityDimensions getDimensions(Entity entity) {
		return dimensions;
	}
}