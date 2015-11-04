package erebus.core.handler;

import java.util.Map;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraftforge.event.AnvilUpdateEvent;

public class AnvilEventsHandler {

	@SubscribeEvent
	@SuppressWarnings("unchecked")
	public void onAnvilChange(AnvilUpdateEvent event) {
		if (Enchantment.sharpness.canApply(event.left) && event.right != null && event.right.getItem() == ModItems.whetstone) {
			Map<Integer, Integer> currentEnchantments = EnchantmentHelper.getEnchantments(event.left);

			// Test if sharpness can be applied with current enchantments
			for (Integer enchantID : currentEnchantments.keySet()) {
				Enchantment enchantment = Enchantment.enchantmentsList[enchantID];
				if (!enchantment.canApplyTogether(Enchantment.sharpness) || !Enchantment.sharpness.canApplyTogether(enchantment))
					return;
			}

			// Test if whetstone sharpness level is greater than the current one
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, event.left) >= event.right.getItemDamage())
				return;

			// If we got this far, the sharpness can be applied
			event.output = event.left.copy();
			event.output.addEnchantment(Enchantment.sharpness, event.right.getItemDamage());
			event.cost = 5;
		}
	}
}