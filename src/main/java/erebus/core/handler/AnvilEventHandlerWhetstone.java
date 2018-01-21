package erebus.core.handler;

import java.util.Map;

import erebus.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AnvilEventHandlerWhetstone {

	@SubscribeEvent
	@SuppressWarnings("unchecked")
	public void onAnvilChange(AnvilUpdateEvent event) {

		if (Enchantment.getEnchantmentByLocation("sharpness").canApply(event.getLeft()) && !event.getRight().isEmpty() && event.getRight().getItem() == ModItems.WHETSTONE) {
			Map<Enchantment, Integer> currentEnchantments = EnchantmentHelper.getEnchantments(event.getLeft());

			// Test if sharpness can be applied with current enchantments
			for (Enchantment enchantID : currentEnchantments.keySet()) {
				Enchantment enchantment = enchantID;
				if (enchantment != Enchantment.getEnchantmentByLocation("sharpness") && !enchantment.isCompatibleWith(Enchantment.getEnchantmentByLocation("sharpness")))
					return;

				// Test if whetstone sharpness level is greater than the current one
				if (EnchantmentHelper.getEnchantmentLevel(Enchantment.getEnchantmentByLocation("sharpness"), event.getLeft()) >= event.getRight().getItemDamage())
					return;

				// Does it already have Sharpness? If so replace value
				if (currentEnchantments.containsKey(enchantID))
					currentEnchantments.replace(enchantID, event.getRight().getItemDamage());
			}

			// If we got this far, the sharpness can be applied
			if (currentEnchantments.containsKey(Enchantment.getEnchantmentByLocation("sharpness"))) {
				event.setOutput(event.getLeft().copy());
				EnchantmentHelper.setEnchantments(currentEnchantments, event.getOutput());
			} else {
				event.setOutput(event.getLeft().copy());
				event.getOutput().addEnchantment(Enchantment.getEnchantmentByLocation("sharpness"), event.getRight().getItemDamage());
			}

			event.setCost(5);
		}
	}
}