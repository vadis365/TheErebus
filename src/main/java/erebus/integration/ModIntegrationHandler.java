package erebus.integration;
import java.util.ArrayList;
import java.util.List;
import cpw.mods.fml.common.Loader;

public class ModIntegrationHandler{
	private static List<IModIntegration> integratedMods = new ArrayList<IModIntegration>();
	
	public static void addMod(Class<? extends IModIntegration> cls){
		try {
			IModIntegration obj = cls.newInstance();
			if (Loader.isModLoaded(obj.getModId()))integratedMods.add(obj);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public static void init(){
		try{
			for(IModIntegration integration:integratedMods)integration.onInit();
		}catch(Throwable t){}
	}
	
	public static void postInit(){
		try{
			for(IModIntegration integration:integratedMods)integration.onPostInit();
		}catch(Throwable t){}
	}
	
	static interface IModIntegration {
		public String getModId();
		public void onInit();
		public void onPostInit();
	}
}
