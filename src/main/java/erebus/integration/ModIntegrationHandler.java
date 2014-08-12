package erebus.integration;

import java.util.ArrayList;
import java.util.List;
import cpw.mods.fml.common.Loader;

public class ModIntegrationHandler {
	private static List<IModIntegration> integratedMods = new ArrayList<IModIntegration>();

	public static void addMod(Class<? extends IModIntegration> cls) {
		try{
			String runFile = ModIntegrationHandler.class.getProtectionDomain().getCodeSource().getLocation().getFile();
			if (runFile.equals("/D:/MCP/1.7.10-EREBUS/bin/erebus/integration/ModIntegrationHandler.class"))return;
			// fuck the integration, I'm disabling all integration loading in my workspace
		}catch(Throwable t){}
		
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
