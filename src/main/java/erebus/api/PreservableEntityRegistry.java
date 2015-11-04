package erebus.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;

public class PreservableEntityRegistry {

	public static Map<Class<? extends Entity>, EntityDimensions> MAP = new HashMap<Class<? extends Entity>, EntityDimensions>();

	public static void registerEntity(Class<? extends Entity> entityCls, EntityDimensions dimensions) {
		MAP.put(entityCls, dimensions);
	}

	public static EntityDimensions getEntityDimensions(Entity entity) {
		if (entity instanceof IPreservableEntity)
			return ((IPreservableEntity) entity).getDimensions();

		EntityDimensions dimensions = EntityDimensions.DEFAULT;
		for (Entry<Class<? extends Entity>, EntityDimensions> entry : MAP.entrySet())
			if (entry.getKey().isAssignableFrom(entity.getClass()))
				return dimensions = entry.getValue();

		return dimensions;
	}

	public static boolean canBePreserved(Entity entity) {
		if (entity instanceof IPreservableEntity)
			return ((IPreservableEntity) entity).canbePreserved();

		for (Entry<Class<? extends Entity>, EntityDimensions> entry : MAP.entrySet())
			if (entry.getKey().isAssignableFrom(entity.getClass()))
				return true;

		return entity instanceof EntityLivingBase && !(entity instanceof IBossDisplayData);
	}

	public static class EntityDimensions {

		public static final EntityDimensions DEFAULT = new EntityDimensions(0.5F, 0.0F, 0.5F, 0.5F);

		final float xOff, yOff, zOff, scale;

		public EntityDimensions(float xOff, float yOff, float zOff, float scale) {
			this.xOff = xOff;
			this.yOff = yOff;
			this.zOff = zOff;
			this.scale = scale;
		}

		public float getX() {
			return xOff;
		}

		public float getY() {
			return yOff;
		}

		public float getZ() {
			return zOff;
		}

		public float getScale() {
			return scale;
		}
	}
}