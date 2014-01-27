package erebus.world.loot;

import java.util.Random;

import net.minecraft.item.ItemStack;

public class WeightedLootList extends WeightedList<LootItemStack> {

	private static final IPostProcess genericPostProcessor = new IPostProcess() {
		@Override
		public ItemStack postProcessItem(ItemStack is, Random rand) {
			return is;
		}
	};

	private IPostProcess postProcessor = genericPostProcessor;

	public WeightedLootList(LootItemStack... items) {
		for (LootItemStack item : items)
			add(item);
	}

	public WeightedLootList setPostProcessor(IPostProcess postProcessor) {
		this.postProcessor = postProcessor;
		if (this.postProcessor == null)
			this.postProcessor = genericPostProcessor;
		return this;
	}

	public ItemStack generateIS(Random rand) {
		LootItemStack is = super.getRandomItem(rand);
		return is == null ? null : postProcessor.postProcessItem(is.getIS(rand), rand);
	}
}