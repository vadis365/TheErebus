package erebus.item;

import erebus.network.data.QuakeHammerData;
import erebus.network.data.QuakeHammerDataHolder;
import erebus.registries.ModSounds;
import erebus.registries.data.ModDataComponents;
import erebus.registries.data.ModToolMaterials;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.NonNull;

import java.util.List;

public class QuakeHammerItem extends Item {

	public QuakeHammerItem() {
		super(new Item.Properties().sword(ModToolMaterials.QUAKE_HAMMER, 10, -1));
	}

	@Override
	public @NonNull Component getHighlightTip(@NonNull ItemStack item, @NonNull Component displayName) {
		return Component.translatable("tooltip.erebus.quake_hammer_1").withStyle(ChatFormatting.YELLOW).append(Component.translatable("tooltip.erebus.quake_hammer_2").withStyle(ChatFormatting.YELLOW));
	}

    @Override
    public void postHurtEnemy(ItemStack stack, @NonNull LivingEntity target, @NonNull LivingEntity attacker) {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    @Override
    public int getUseDuration(@NonNull ItemStack stack, @NonNull LivingEntity entity) {
		return 1000;
	}

    // TODO - going to change this now to be something different - old stuff will be new stuff soon(tm)
	@Override
	public @NonNull InteractionResult onItemUseFirst(ItemStack stack, @NonNull UseOnContext context) {
		int charge = (!stack.has(ModDataComponents.QUAKE_HAMMER) ? 0 : stack.get(ModDataComponents.QUAKE_HAMMER).charge());
		if (charge < 25)
			stack.set(ModDataComponents.QUAKE_HAMMER, new QuakeHammerData(charge + 1));
		return InteractionResult.PASS;
	}

	@Override
	public @NonNull InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		Player player = context.getPlayer();
		InteractionHand hand = context.getHand();
		BlockPos pos = context.getClickedPos();
		ItemStack stack = player.getItemInHand(hand);
//		TODO This will be more than particles.
		if (!player.mayUseItemAt(pos, context.getClickedFace(), stack))
			return InteractionResult.FAIL;
		else {
			BlockState state = level.getBlockState(pos);
			if (!level.isClientSide() && !state.isAir()) {
				int charge = (!stack.has(ModDataComponents.QUAKE_HAMMER) ? 0 : stack.get(ModDataComponents.QUAKE_HAMMER).charge());
				if (player.isCrouching() && charge > 0) {
					// Erebus.NETWORK_WRAPPER.sendToAll(new PacketParticle(ParticleType.HAMMER_BLAM,
					// (float) player.posX, (float)player.posY, (float)player.posZ));
					level.playSound(null, pos, ModSounds.BLAM_SOUND.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
					areaOfEffect(level, stack, player, charge);
					stack.set(ModDataComponents.QUAKE_HAMMER, QuakeHammerDataHolder.DEFAULT);
					return InteractionResult.SUCCESS;
				}
			}
		}
		return InteractionResult.PASS;
	}

	public void areaOfEffect(Level level, ItemStack stack, Player player, int charge) {
		List<?> list = level.getEntitiesOfClass(LivingEntity.class, new AABB(player.getBoundingBox().minX, player.getBoundingBox().minY, player.getBoundingBox().minZ, player.getBoundingBox().maxX, player.getBoundingBox().maxY, player.getBoundingBox().maxZ).inflate(charge * 0.25D, 1D, charge* 0.25D));
		for (int i = 0; i < list.size(); i++) {
			Entity entity = (Entity) list.get(i);
			if (entity != null)
				if (entity instanceof LivingEntity && entity != player) {
					System.out.println("Hit Entity: "+ entity.getName());
					//float Knockback = (float) (stack.getTagCompound().getInteger("charge") * 0.025D);
					//entity.attackEntityFrom(DamageSource.causeMobDamage(player), stack.getTagCompound().getInteger("charge") * 0.25F);
					//entity.addVelocity(-MathHelper.sin(player.rotationYaw * -3.141593F + world.rand.nextInt(3) + 0.141593F / 180.0F) * Knockback, 0.01D, MathHelper.cos(player.rotationYaw * -3.141593F + world.rand.nextInt(3) + 0.141593F / 180.0F) * Knockback);
				}
		}
	}
}
