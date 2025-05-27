package erebus.item.blocks;

import java.util.List;

import javax.annotation.Nonnull;

import erebus.block.bamboo.BambooCrateBlock;
import erebus.block.bamboo.EnumCrateType;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class BambooCrateItem extends BlockItem {
	
	public BambooCrateItem(BambooCrateBlock blockIn, Properties builder) {
		super(blockIn, builder);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nonnull TooltipContext context, @Nonnull List<Component> list, @Nonnull TooltipFlag flag) {
		//if (stack.has(ModDataComponents.ITEMS)) {
			list.add(Component.literal("Stores Items When Broken"));
		/*	if (stack.hasTagCompound() && stack.getTagCompound().getTagList("Items", 10) != null) {
				NBTTagList tags = stack.getTagCompound().getTagList("Items", 10);

				for (int i = 0; i < tags.tagCount(); i++) {
					NBTTagCompound data = tags.getCompoundTagAt(i);
					int j = data.getByte("Slot") & 255;
					list.add(Component.literal("Slot " + (j + 1) + ": " + TextFormatting.GREEN + new ItemStack(data).getDisplayName() + " x " + new ItemStack(data).getCount());
				}
		}*/
	}

    @Override
    protected boolean canPlace(BlockPlaceContext context, BlockState state) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		for (Direction dir : Direction.values()) {
			BlockState state2 = level.getBlockState(pos.offset(dir.getStepX(), dir.getStepY(), dir.getStepZ()));
			if (state2.is(OtherBlocks.BAMBOO_CRATE.get())) {
				EnumCrateType type = state2.getValue(BambooCrateBlock.CRATE_TYPE);
				if (type != EnumCrateType.DEFAULT)
					return false;
				if (level.getBlockState(pos.offset(dir.getOpposite().getStepX(), dir.getOpposite().getStepY(), dir.getOpposite().getStepZ())).is(OtherBlocks.BAMBOO_CRATE.get()))
					return false;
			}
		}
		return super.canPlace(context, state);
	}
}
