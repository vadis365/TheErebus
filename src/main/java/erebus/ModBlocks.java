package erebus;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

import erebus.blocks.BlockUmberstone;
import erebus.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static final Block UMBERSTONE = new BlockUmberstone();

	public static void init() {
		try {
			for (Field field : ModBlocks.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof Block) {
					Block block = (Block) obj;
					String name = field.getName().toLowerCase(Locale.ENGLISH);

					GameRegistry.register(block.setRegistryName(Reference.MOD_ID, name).setUnlocalizedName(Reference.MOD_ID + "." + name));
					if (block instanceof IHasCustomItem) {
						ItemBlock item = (ItemBlock) ((IHasCustomItem) block).getItemBlock().setRegistryName(Reference.MOD_ID, name).setUnlocalizedName(Reference.MOD_ID + "." + name);
						GameRegistry.register(item);
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static void registerRenderers() {
		try {
			for (Field field : ModBlocks.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof Block) {
					Block block = (Block) obj;

					if (block instanceof ISubBlocksBlock) {
						List<String> models = ((ISubBlocksBlock) block).getModels();
						for (int i = 0; i < models.size(); i++)
							ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), i, new ModelResourceLocation(Reference.MOD_ID + ":blocks/" + models.get(i), "inventory"));
					} else {
						String name = field.getName().toLowerCase(Locale.ENGLISH);
						ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MOD_ID + ":blocks/" + name, "inventory"));
					}
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static interface IHasCustomItem {
		ItemBlock getItemBlock();
	}

	public static interface ISubBlocksBlock {
		List<String> getModels();
	}
}