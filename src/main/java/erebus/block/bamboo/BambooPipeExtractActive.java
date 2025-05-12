package erebus.block.bamboo;

import javax.annotation.Nonnull;

import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class BambooPipeExtractActive extends BambooPipeExtract {

	public BambooPipeExtractActive(Properties properties) {
		super(properties);
		registerDefaultState(this.stateDefinition.any().setValue(CONNECTED_DOWN, Boolean.FALSE).setValue(CONNECTED_EAST, Boolean.FALSE).setValue(CONNECTED_NORTH, Boolean.FALSE).setValue(CONNECTED_SOUTH, Boolean.FALSE).setValue(CONNECTED_UP, Boolean.FALSE).setValue(CONNECTED_WEST, Boolean.FALSE));
	}
/*
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(ModBlocks.BAMBOO_PIPE_EXTRACT);
	}

	@Override
	public ItemStack getItem(World world, BlockPos pos, IBlockState state) {
		return new ItemStack(Item.getItemFromBlock(ModBlocks.BAMBOO_PIPE_EXTRACT));
	}

*/
    @Nonnull
	@Override
    public ItemInteractionResult useItemOn(@Nonnull ItemStack stack, @Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
    	if (level.isClientSide()) {
			return ItemInteractionResult.SUCCESS;
		}
		 else {
			 if (stack.isEmpty()) {
				BlockState activeState = OtherBlocks.BAMBOO_PIPE_EXTRACT.get().defaultBlockState().setValue(BambooPipeExtract.FACING, state.getValue(FACING));
				level.setBlock(pos, activeState, 3);
				level.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.3F, 0.5F);
				return ItemInteractionResult.SUCCESS;
			}
		}
    	return ItemInteractionResult.FAIL;
	}
	
}
