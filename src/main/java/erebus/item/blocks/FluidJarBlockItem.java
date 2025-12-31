package erebus.item.blocks;

import erebus.block.FluidJarBlock;
import erebus.registries.data.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import java.util.List;

public class FluidJarBlockItem extends BlockItem {
	private final int capacity;

	public FluidJarBlockItem(FluidJarBlock blockIn, int capacity, Properties builder) {
		super(blockIn, builder);
		this.capacity = capacity;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nonnull TooltipContext context, @Nonnull List<Component> list, @Nonnull TooltipFlag flag) {
		if (stack.has(ModDataComponents.FLUID)) {
			FluidStack fluid = stack.getOrDefault(ModDataComponents.FLUID, FluidContents.EMPTY).get();
			if (!fluid.isEmpty()) {
				list.add(Component.literal("Contains: " + fluid.getHoverName().getString()).withStyle(ChatFormatting.GREEN));
				list.add(Component.literal(String.format("%dMb/%dMb", fluid.getAmount(),capacity)).withStyle(ChatFormatting.BLUE));
			}
		}
		else
			list.add(Component.literal(String.format("Holds %dMb (%d Buckets)", capacity, capacity / 1000)).withStyle(ChatFormatting.BLUE));
	}

}
