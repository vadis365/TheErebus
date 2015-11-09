package erebus.api;

import erebus.preserved.PreservableEntityRegistry.EntityDimensions;
import net.minecraft.entity.Entity;

public interface IPreservableEntityRegistry {

	void registerHandler(IPreservableEntityHandler handler);

	void registerEntity(Class<? extends Entity> entityCls, EntityDimensions dimensions);

	EntityDimensions getEntityDimensions(Entity entity);

	boolean canBePreserved(Entity entity);
}