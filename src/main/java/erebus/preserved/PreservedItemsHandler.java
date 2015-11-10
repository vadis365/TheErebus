package erebus.preserved;

import erebus.api.IPreservableEntityHandler;
import erebus.preserved.PreservableEntityRegistry.EntityDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;

public class PreservedItemsHandler implements IPreservableEntityHandler {

	private final EntityDimensions dimensions = new EntityDimensions(0.25F, 0.25F, 0.25F, 1.5F);

	@Override
	public boolean handlesEntity(Entity entity) {
		return entity instanceof EntityItem;
	}

	@Override
	public boolean canbePreserved(Entity entity) {
		return false;
	}

	@Override
	public EntityDimensions getDimensions(Entity entity) {
		return dimensions;
	}
}