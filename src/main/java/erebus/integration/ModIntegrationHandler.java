package erebus.integration;

import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.List;

public class ModIntegrationHandler {
	private static List<IModIntegration> integratedMods = new ArrayList<IModIntegration>();

	public static void addMod(Class<? extends IModIntegration> cls) {
		try {
			IModIntegration obj = cls.newInstance();
			if (Loader.isModLoaded(obj.getModId()))
				integratedMods.add(obj);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static void init() {
		try {
			for (IModIntegration integration : integratedMods)
				integration.onInit();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public static void postInit() {
		try {
			for (IModIntegration integration : integratedMods)
				integration.onPostInit();
		} catch (Throwable t) {
			t.fillInStackTrace();
		}
	}

	static interface IModIntegration {
		String getModId();

		void onInit();

		void onPostInit();
	}
}
