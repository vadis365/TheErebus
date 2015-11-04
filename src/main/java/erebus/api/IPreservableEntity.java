package erebus.api;

import erebus.api.PreservableEntityRegistry.EntityDimensions;

public interface IPreservableEntity {

	boolean canbePreserved();

	EntityDimensions getDimensions();
}