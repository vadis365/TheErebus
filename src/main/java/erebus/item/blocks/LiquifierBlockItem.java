package erebus.item.blocks;

import erebus.registries.data.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import org.jspecify.annotations.NonNull;

public class LiquifierBlockItem extends BlockItem {
	private final int capacity;

	public LiquifierBlockItem(Block blockIn, int capacity, Properties builder) {
		super(blockIn, builder);
		this.capacity = capacity;
	}

	@Override
	public @NonNull Component getHighlightTip(@NonNull ItemStack stack, @NonNull Component displayName) {
		MutableComponent component = Component.empty();

		if (stack.has(ModDataComponents.FLUID)) {
			FluidStack fluid = stack.getOrDefault(ModDataComponents.FLUID, FluidResource.EMPTY).toStack(1000);
			if (!fluid.isEmpty()) {
				component.append(Component.literal("Contains: " + fluid.getHoverName().getString()).withStyle(ChatFormatting.GREEN));
				component.append(Component.literal(String.format("%dMb/%dMb", fluid.getAmount(),capacity)).withStyle(ChatFormatting.BLUE));
			}
		}
		else
			component.append(Component.literal(String.format("Holds %dMb (%d Buckets)", capacity, capacity / 1000)).withStyle(ChatFormatting.BLUE));

		return component;
	}
}
