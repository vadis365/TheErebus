package erebus.item;

import java.util.List;

import erebus.entity.AnimatedBlock;
import erebus.registries.ModBlocks;
import erebus.registries.ModEntities;
import erebus.registries.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class WandOfAnimationItem extends Item {
	public WandOfAnimationItem(Properties properties) {
		super(properties);
//		setFull3D();
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	   public void appendHoverText(ItemStack stack,TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(Component.translatable("tooltip.erebus.wandofanimation"));
		}


	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		Player player = context.getPlayer();
		InteractionHand hand = context.getHand();
		BlockPos pos = context.getClickedPos();
		ItemStack stack = player.getItemInHand(hand);
		if (!player.mayUseItemAt(pos, context.getClickedFace(), stack))
			return InteractionResult.FAIL;
		else {
			BlockState state = level.getBlockState(pos);
			if (!level.isClientSide && !state.isAir() && canAnimate(state, level, pos)) {
				AnimatedBlock entity = new AnimatedBlock(ModEntities.ANIMATED_BLOCK.get(), level);
				if (state.getBlock() == Blocks.CHEST) {
					System.out.println("Spawn Chester");
					//entity = new EntityAnimatedChest(world).setContents(Utils.getTileEntity(world, pos, TileEntityChest.class));
				}
				else if (state.getBlock() == ModBlocks.BAMBOO_CRATE.get()) {
					System.out.println("Spawn Bamber");
					//entityk = new EntityAnimatedBambooCrate(world).setContents(Utils.getTileEntity(world, pos, TileEntityBambooCrate.class));
					}
				level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
				entity.setPos((double) pos.getX() + 0.5F, pos.getY(), (double) pos.getZ() + 0.5F);
				entity.setBlockType(state);
				level.addFreshEntity(entity);
				//entity.setOwnerId(player.getUniqueID());
				level.playSound(null, pos, ModSounds.ALTAR_OFFERING.get(), SoundSource.BLOCKS, 0.2F, 1.0F);
				//stack.damageItem(1, player);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.PASS;
	}
/*
	//CBA with meta as this is changing in 1.13 anyway 
	private Boolean isBlacklisted(Block block) {
		List<Block> blockList = new ArrayList<Block>();
		for (int blocks = 0; blocks < ConfigHandler.WOA_BLACKLISTED_BLOCKS.length; blocks++) {
			String entry = ConfigHandler.WOA_BLACKLISTED_BLOCKS[blocks].trim();
			Block outBlock = Block.REGISTRY.getObject(new ResourceLocation(entry));
			blockList.add(outBlock);
		}
		if(blockList.contains(block))
			return true;
		return false;
	}
*/
	//TODO Make this more sane one day...
	private boolean canAnimate(BlockState state, Level level, BlockPos pos) {
		return /*!isBlacklisted(state.getBlock()) && !(state.getBlock() instanceof BlockGaeanKeystone) && !(state.getBlock() instanceof AltarBase) && !(state.getBlock() instanceof BlockDoublePlant) && !(state.getBlock() instanceof BlockDoubleHeightPlant) && !(state.getBlock() instanceof BlockPreservedBlock) && */
				!(state.getBlock() instanceof Container) && state.getDestroySpeed(level, pos) >= 0F && state.isCollisionShapeFullBlock(level, pos) || state.getBlock() == Blocks.CHEST || state.getBlock() == ModBlocks.BAMBOO_CRATE.get();
	}
}