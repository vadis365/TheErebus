package erebus.item.blocks;

import erebus.registries.data.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class FluidJarBlockItem extends BlockItem {
	private final int capacity;

	public FluidJarBlockItem(Block blockIn, int capacity, Properties builder) {
		super(blockIn, builder);
		this.capacity = capacity;
	}

	@Override
	public void appendHoverText(ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, @NonNull Consumer<Component> builder, @NonNull TooltipFlag tooltipFlag) {
		if (stack.has(ModDataComponents.FLUID)) {
			FluidStack fluid = stack.get(ModDataComponents.FLUID).toStack(FluidType.BUCKET_VOLUME);
			if (!fluid.isEmpty()) {
				builder.accept(Component.literal("Contains: " + fluid.getHoverName().getString()).withStyle(ChatFormatting.GREEN));
				builder.accept(Component.literal(String.format("%dMb/%dMb", fluid.getAmount(),capacity)).withStyle(ChatFormatting.BLUE));
			}
		}
		else
			builder.accept(Component.literal(String.format("Holds %dMb (%d Buckets)", capacity, capacity / 1000)).withStyle(ChatFormatting.BLUE));
	}

}
