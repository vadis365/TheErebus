package erebus.preserved;

import erebus.api.IPreservableEntityHandler;
import erebus.preserved.PreservableEntityRegistry.EntityDimensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntitySlime;

public class PreservedSlimeHandler implements IPreservableEntityHandler {

	@Override
	public boolean handlesEntity(Entity entity) {
		return entity instanceof EntitySlime || entity instanceof EntityMagmaCube;
	}

	@Override
	public boolean canbePreserved(Entity entity) {
		return true;
	}

	@Override
	public EntityDimensions getDimensions(Entity entity) {
		EntitySlime slime = (EntitySlime) entity;
		int size = slime.getSlimeSize();
		return new EntityDimensions(0.0F, 0.25F, 0.0F, 1F / size);
	}
}