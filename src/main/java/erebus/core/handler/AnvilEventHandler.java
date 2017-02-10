package erebus.core.handler;

import erebus.items.ItemErebusShield;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class AnvilEventHandler {

    @SubscribeEvent
    public void onAnvilUpdate(AnvilUpdateEvent event) {
	    if (event.getLeft().getItem() instanceof ItemErebusShield) {
		    if (event.getLeft().getItem().getIsRepairable(event.getLeft(), event.getRight())) {
			    if (event.getLeft().getTagCompound() != null && event.getLeft().getTagCompound().hasKey("damage")) {
                    int damage = event.getLeft().getTagCompound().getInteger("damage");
	                int repairPerItem = event.getLeft().getItem().getMaxDamage() / 3;
				    if (damage == 0) {
                        ItemStack output = event.getLeft().copy();
                        if (event.getName() != null)
                            output.setStackDisplayName(event.getName());
                        event.setOutput(output);

                        event.setCost((int) (event.getName() != null ? event.getName().length() * 0.25D : 0));
                        event.setMaterialCost(0);
                    } else {
                        int amount = (int) Math.ceil(((double) damage / (double) repairPerItem) + .5d);
                        ItemStack output = event.getLeft().copy();
                        if (event.getName() != null)
                            output.setStackDisplayName(event.getName());
                        if (output.getTagCompound() == null)
                            output.setTagCompound(event.getLeft().getTagCompound());
                        output.getTagCompound().setInteger("damage", damage - repairPerItem * amount < 0 ? 0 : damage - repairPerItem * amount);
                        event.setOutput(output);
                        int lengthDifference = event.getName() != null ? event.getName().length() - event.getLeft().getDisplayName().length() : 0;
                        event.setCost(amount + (int) (event.getName() != null ? lengthDifference * 0.25D : 0));
                        event.setMaterialCost(amount);
                    }
                }
            }
        }
    }
}
