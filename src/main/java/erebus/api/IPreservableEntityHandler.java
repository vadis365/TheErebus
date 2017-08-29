package erebus.api;

import erebus.preserved.PreservableEntityRegistry.EntityDimensions;
import net.minecraft.entity.Entity;

/**
 * Used for advanced handling of one or more entities
 */
public interface IPreservableEntityHandler {

	/**
	 * Returns whether or not this handler should determine the destiny of the passed Entity
	 */
	boolean handlesEntity(Entity entity);

	/**
	 * Returns whether or not the passed Entity can be preserved.
	 * Only called if handlesEntity(entity) returns true
	 */
	boolean canbePreserved(Entity entity);

	/**
	 * Returns the passed Entity's dimensions
	 * Only called if handlesEntity(entity) returns true
	 */
	EntityDimensions getDimensions(Entity entity);
}