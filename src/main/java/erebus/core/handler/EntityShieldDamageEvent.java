package erebus.core.handler;

import erebus.items.ItemErebusShield;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntityShieldDamageEvent {

	@SubscribeEvent
	public void onEntityShielded(LivingHurtEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			ItemStack stack = player.getActiveItemStack();

			if (!stack.isEmpty() && stack.getItem() instanceof ItemErebusShield) {
				ItemErebusShield shield = (ItemErebusShield) stack.getItem();

				if (canBlockDamageSource(player, event.getSource()) && !player.isEntityInvulnerable(event.getSource())) {
					int damageInflicted = 1 + MathHelper.floor(event.getAmount());

					shield.damageShield(damageInflicted, stack, player);

					if (stack.getCount() <= 0) {
						EnumHand hand = player.getActiveHand();

						if (player instanceof EntityPlayerMP) {
						//	Erebus.NETWORK_WRAPPER.sendTo(new ShieldDestroyMessage(stack), (EntityPlayerMP) player);
						}

						ForgeEventFactory.onPlayerDestroyItem(player, stack, hand);
						player.setHeldItem(hand, ItemStack.EMPTY);
					}
				}
			}
		}
	}
	
	public boolean canBlockDamageSource(EntityLivingBase player, DamageSource source) {
		if (!source.isUnblockable() && player.isActiveItemStackBlocking()) {
			Vec3d sourceLocation = source.getDamageLocation();

			if (sourceLocation != null) {
				Vec3d playerLook = player.getLook(1.0F);
				Vec3d sourceToPlayer = sourceLocation.subtractReverse(new Vec3d(player.posX, player.posY, player.posZ))
						.normalize();
				sourceToPlayer = new Vec3d(sourceToPlayer.x, 0.0, sourceToPlayer.z);

				if (sourceToPlayer.dotProduct(playerLook) < 0.0D) {
					return true;
				}
			}
		}

		return false;
	}
}
