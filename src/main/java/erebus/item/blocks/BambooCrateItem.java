package erebus.item.blocks;

import erebus.block.bamboo.BambooCrateBlock;
import erebus.block.types.EnumCrateType;
import erebus.registries.blocks.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nonnull;
import java.util.List;

public class BambooCrateItem extends BlockItem {
	
	public BambooCrateItem(BambooCrateBlock blockIn, Properties builder) {
		super(blockIn, builder);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nonnull TooltipContext context, @Nonnull List<Component> list, @Nonnull TooltipFlag flag) {
		list.add(Component.literal("Stores Items When Broken"));
		if (stack.has(DataComponents.CONTAINER)) {
			ItemContainerContents contents = stack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY);
			for (int i = 0; i < contents.getSlots(); i++)
				if (!contents.getStackInSlot(i).isEmpty())
					list.add(Component.literal("Slot " + (i + 1) + ": " + contents.getStackInSlot(i).getHoverName().getString() + " x " + contents.getStackInSlot(i).getCount()).withStyle(ChatFormatting.GREEN));
		}
	}

    @Override
    protected boolean canPlace(BlockPlaceContext context, BlockState state) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		for (Direction dir : Direction.values()) {
			BlockState state2 = level.getBlockState(pos.offset(dir.getStepX(), dir.getStepY(), dir.getStepZ()));
			if (state2.is(ModBlocks.BAMBOO_CRATE.get())) {
				EnumCrateType type = state2.getValue(BambooCrateBlock.CRATE_TYPE);
				if (type != EnumCrateType.DEFAULT)
					return false;
				if (level.getBlockState(pos.offset(dir.getOpposite().getStepX(), dir.getOpposite().getStepY(), dir.getOpposite().getStepZ())).is(ModBlocks.BAMBOO_CRATE.get()))
					return false;
			}
		}
		return super.canPlace(context, state);
	}
}
