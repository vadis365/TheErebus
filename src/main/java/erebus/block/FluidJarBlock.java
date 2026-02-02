package erebus.block;

import com.mojang.serialization.MapCodec;
import erebus.block.entity.FluidJarBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.neoforged.neoforge.transfer.fluid.FluidUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FluidJarBlock extends BaseEntityBlock {
	
	public static final MapCodec<FluidJarBlock> CODEC = simpleCodec(FluidJarBlock::new);
	
	public FluidJarBlock(Properties properties) {
		super(properties);
	}
	
	@Nonnull
	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	public BlockEntity newBlockEntity(@Nonnull BlockPos pos, @Nonnull BlockState state) {
		return new FluidJarBlockEntity(pos, state);
	}

	@Nullable
	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, @Nonnull BlockState pState, @Nonnull BlockEntityType<T> pBlockEntityType) {
		return pLevel.isClientSide() ? null : FluidJarBlockEntity::serverTick;
	}

	@Nonnull
	@Override
	public RenderShape getRenderShape(@Nonnull BlockState state) {
		return RenderShape.MODEL;
	}

	@Nonnull
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (world.isClientSide())
			return InteractionResult.SUCCESS;
		BlockEntity tileentity = world.getBlockEntity(pos);
		if (tileentity instanceof FluidJarBlockEntity) {
			ResourceHandler<FluidResource> handler = world.getCapability(Capabilities.Fluid.BLOCK, pos, hit.getDirection());
			if (handler != null) {
				if (player.getItemInHand(hand).isEmpty() || !FluidUtil.interactWithFluidHandler(player, hand, world, pos, hit.getDirection())) {
					FluidResource resource = handler.getResource(0);
					if (!resource.isEmpty())
						player.displayClientMessage(Component.literal(resource.getHoverName().getString() + ": " + handler.getAmountAsInt(0) + "/" + FluidJarBlockEntity.MAX_CAPACITY), true);
					else
						player.displayClientMessage(Component.literal("Empty: 0/" + FluidJarBlockEntity.MAX_CAPACITY), true);
				}
			}
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.PASS;
	}
}
