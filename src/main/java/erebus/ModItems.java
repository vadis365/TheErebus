package erebus;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

import erebus.items.ItemMaterials;
import erebus.lib.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems {

	public static final Item MATERIALS = new ItemMaterials();

	public static void init() {
		try {
			for (Field field : ModItems.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof Item) {
					Item item = (Item) obj;
					String name = field.getName().toLowerCase(Locale.ENGLISH);

					GameRegistry.register(item.setRegistryName(Reference.MOD_ID, name).setUnlocalizedName(Reference.MOD_ID + "." + name));
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static void registerRenderers() {
		try {
			for (Field field : ModItems.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof Item) {
					Item item = (Item) obj;

					if (item instanceof ISubItemsItem) {
						List<String> models = ((ISubItemsItem) item).getModels();
						for (int i = 0; i < models.size(); i++)
							ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(Reference.MOD_ID + ":" + models.get(i), "inventory"));
					} else {
						String name = field.getName().toLowerCase(Locale.ENGLISH);
						ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + name, "inventory"));
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static interface ISubItemsItem {
		List<String> getModels();
	}
}