package erebus.item;

import erebus.block.bamboo.BambooExtender;
import erebus.block.bamboo.BambooPipe;
import erebus.block.bamboo.BambooPipeExtract;
import erebus.block.entity.BambooExtenderBlockEntity;
import erebus.registries.blocks.providers.OtherBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class BambooPipeWrenchItem extends Item {
	public BambooPipeWrenchItem(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(Component.translatable("tooltip.erebus.bamboo_pipe_wrench").withStyle(ChatFormatting.YELLOW));
	}

	// TODO make this nicer for use on all the pipe types
	@Override
	public InteractionResult useOn(UseOnContext context) {
		Player player = context.getPlayer();
		Level level = context.getLevel();
		if (!level.isClientSide() && player != null) {
			BlockPos pos = context.getClickedPos();
			BlockState state = level.getBlockState(pos);
			if (state != null && state.getBlock() == OtherBlocks.BAMBOO_PIPE.get()) {
				if (!player.isCrouching()) {
					BlockState stateNew = cycleState(state, BambooPipe.FACING);
					level.setBlock(pos, stateNew, Block.UPDATE_ALL);
					return InteractionResult.SUCCESS;
				} else {
					level.removeBlockEntity(pos);
					level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
					ItemStack stackDrop = new ItemStack(OtherBlocks.BAMBOO_PIPE.get());
					Block.popResource(level, pos, stackDrop);
					level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.5F, 2.0F);
					return InteractionResult.SUCCESS;
				}
			}
			
			if (state != null && state.getBlock() == OtherBlocks.BAMBOO_PIPE_EXTRACT.get()) {
				if (!player.isCrouching()) {
					BlockState stateNew = cycleState(state, BambooPipeExtract.FACING);
					level.setBlock(pos, stateNew, Block.UPDATE_ALL);
					return InteractionResult.SUCCESS;
				} else {
					level.removeBlockEntity(pos);
					level.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
					ItemStack stackDrop = new ItemStack(OtherBlocks.BAMBOO_PIPE_EXTRACT.get());
					Block.popResource(level, pos, stackDrop);
					level.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.PLAYERS, 0.5F, 2.0F);
					return InteractionResult.SUCCESS;
				}
			}

			if (state != null && state.getBlock() == OtherBlocks.BAMBOO_EXTENDER.get()) {
				BlockEntity blockEntity = level.getBlockEntity(pos);
				if (blockEntity instanceof BambooExtenderBlockEntity extender) {
					if (!player.isCrouching()) {
						BlockState stateNew = cycleState(state, BambooExtender.FACING);
						extender.direction = stateNew.getValue(BambooExtender.FACING);
						level.setBlock(pos, stateNew, Block.UPDATE_ALL);
						return InteractionResult.SUCCESS;
					}
				}
			}
		}

		return InteractionResult.sidedSuccess(level.isClientSide);
	}

	private static <T extends Comparable<T>> BlockState cycleState(BlockState state, Property<T> property) {
		return state.setValue(property, getRelative(property.getPossibleValues(), state.getValue(property)));
	}

	private static <T> T getRelative(Iterable<T> allowedValues, @Nullable T currentValue) {
		return Util.findNextInIterable(allowedValues, currentValue);
	}
}