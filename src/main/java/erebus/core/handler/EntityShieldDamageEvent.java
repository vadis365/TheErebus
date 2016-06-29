package erebus.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import erebus.items.ItemErebusShield;

public class EntityShieldDamageEvent {

	@SubscribeEvent
	public void onEntityShielded(LivingHurtEvent event) {
		if (event.getEntityLiving() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			if (player.getActiveItemStack() != null && player.getActiveItemStack().getItem() instanceof ItemErebusShield) {
				int damageInflicted = 1 + MathHelper.floor_float(event.getAmount());
				((ItemErebusShield) player.getActiveItemStack().getItem()).damageShield(damageInflicted, player.getActiveItemStack(), player);
				if (player.getActiveItemStack().stackSize <= 0) {
					EnumHand enumhand = player.getActiveHand();
					net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem(player, player.getActiveItemStack(), enumhand);

					if (enumhand == EnumHand.MAIN_HAND)
						player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, null);
					else
						player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, null);
					
					player.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + player.worldObj.rand.nextFloat() * 0.4F);
				}
				float damageSustained = damageInflicted/3;
				player.attackEntityFrom(event.getSource(), damageSustained);
			}
		}
	}
}
