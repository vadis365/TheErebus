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
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import org.jspecify.annotations.NonNull;

import java.util.function.Consumer;

public class LiquifierBlockItem extends BlockItem {
    private final int capacity;

    public LiquifierBlockItem(Block blockIn, int capacity, Properties builder) {
        super(blockIn, builder);
        this.capacity = capacity;
    }

    @Override
    public void appendHoverText(ItemStack stack, @NonNull TooltipContext context, @NonNull TooltipDisplay display, Consumer<Component> lines, @NonNull TooltipFlag flag) {
        lines.accept(Component.translatable("tooltip.erebus.liquifier").withStyle(ChatFormatting.YELLOW));
        FluidStack fluid = stack.getOrDefault(ModDataComponents.FLUID, FluidResource.EMPTY)
                .toStack(stack.getOrDefault(ModDataComponents.FLUID_AMOUNT, 1000));
        if (!fluid.isEmpty()) {
            lines.accept(Component.literal("Contains: ").append(fluid.getHoverName()).withStyle(ChatFormatting.GREEN));
            lines.accept(Component.literal(String.format("%dMb/%dMb", fluid.getAmount(), capacity)).withStyle(ChatFormatting.BLUE));
        } else {
            lines.accept(Component.literal(String.format("Holds %dMb (%d Buckets)", capacity, capacity / 1000)).withStyle(ChatFormatting.BLUE));
        }
    }
}
