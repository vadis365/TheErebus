package erebus.preserved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import erebus.api.IPreservableEntityHandler;
import erebus.api.IPreservableEntityRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;

public class PreservableEntityRegistry implements IPreservableEntityRegistry {

	private Map<Class<? extends Entity>, EntityDimensions> MAP = new HashMap<Class<? extends Entity>, EntityDimensions>();
	private List<IPreservableEntityHandler> HANDLERS = new ArrayList<IPreservableEntityHandler>();
	public static final PreservableEntityRegistry INSTANCE = new PreservableEntityRegistry();

	private PreservableEntityRegistry() {
		registerHandler(new PreservedSkeletonHandler());
		registerHandler(new PreservedItemsHandler());
		registerHandler(new PreservedSlimeHandler());
		registerHandler(new PreservedWaspHandler());
	}

	@SuppressWarnings("unchecked")
	public void readFile(BufferedReader br, boolean clearMap) {
		if (clearMap)
			MAP.clear();

		try {
			String str;
			while ((str = br.readLine()) != null)
				if (!str.isEmpty() && !str.startsWith("#")) {
					String[] entry = str.trim().split("=");
					if (entry.length != 2)
						throw new IllegalArgumentException("Illegal entry found when reading Entity Dimensions file: " + str);
					String[] dims = entry[1].split(",");
					if (dims.length != 4)
						throw new IllegalArgumentException("Illegal entry found when reading Entity Dimensions file: " + str);

					Class<? extends Entity> cls = (Class<? extends Entity>) Class.forName(entry[0]);
					EntityDimensions dimensions = new EntityDimensions(Float.parseFloat(dims[0].trim()), Float.parseFloat(dims[1].trim()), Float.parseFloat(dims[2].trim()), Float.parseFloat(dims[3].trim()));
					MAP.put(cls, dimensions);
				}

		} catch (ClassNotFoundException e) {
			// ignore silently
		} catch (Exception e) {
			throw new IllegalArgumentException("Error reading file", e);
		}
	}

	public void writeConfigFile(File file) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write("# entity.class=xOffset, yOffset, zOffset, scale");
			bw.newLine();
			bw.write("# Example:");
			bw.newLine();
			bw.write("# net.minecraft.entity.passive.EntityCow=0.5, 0.125, 0.5, 0.5");
			bw.newLine();

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registerHandler(IPreservableEntityHandler handler) {
		HANDLERS.add(handler);
	}

	@Override
	public void registerEntity(Class<? extends Entity> entityCls, EntityDimensions dimensions) {
		MAP.put(entityCls, dimensions);
	}

	@Override
	public EntityDimensions getEntityDimensions(Entity entity) {
		for (IPreservableEntityHandler handler : HANDLERS)
			if (handler.handlesEntity(entity))
				return handler.getDimensions(entity);

		EntityDimensions dimensions = EntityDimensions.DEFAULT;
		for (Entry<Class<? extends Entity>, EntityDimensions> entry : MAP.entrySet())
			if (entry.getKey().isAssignableFrom(entity.getClass()))
				return dimensions = entry.getValue();

		return dimensions;
	}

	@Override
	public boolean canBePreserved(Entity entity) {
		for (IPreservableEntityHandler handler : HANDLERS)
			if (handler.handlesEntity(entity))
				return handler.canbePreserved(entity);

		for (Entry<Class<? extends Entity>, EntityDimensions> entry : MAP.entrySet())
			if (entry.getKey().isAssignableFrom(entity.getClass()))
				return true;

		return entity instanceof EntityLivingBase && !(entity instanceof IBossDisplayData);
	}

	public static class EntityDimensions {

		public static final EntityDimensions DEFAULT = new EntityDimensions(0.0F, 0.25F, 0.0F, 0.5F);

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