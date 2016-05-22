package erebus;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static void init() {
		try {
			for (Field f : ModBlocks.class.getDeclaredFields()) {
				Object obj = f.get(null);
				if (obj instanceof Block)
					registerBlock((Block) obj);
				else if (obj instanceof Block[])
					for (Block block : (Block[]) obj)
						registerBlock(block);
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private static void registerBlock(Block block) {
		String name = block.getUnlocalizedName();
		String[] strings = name.split("\\.");

		if (block instanceof IHasCustomItemBlock)
			GameRegistry.registerBlock(block, ((IHasCustomItemBlock) block).getItemBlockClass(), strings[strings.length - 1]);
		else
			GameRegistry.registerBlock(block, strings[strings.length - 1]);
	}

	public static interface IHasCustomItemBlock {

		Class<? extends ItemBlock> getItemBlockClass();
	}
}