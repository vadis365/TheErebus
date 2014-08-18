package erebus.core.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.api.animationmagic.EnergyType;
import erebus.api.animationmagic.IEnergyCollector;

public class EntityDeathEventHandler
{
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void dropEvent(LivingDeathEvent event)
	{
		if (event.entityLiving.worldObj.isRemote)
		{
			return;
		}

		ItemStack weapon = getWeapon(event.source);
		if (weapon != null && weapon.getItem() instanceof IEnergyCollector)
		{
			IEnergyCollector collector = (IEnergyCollector) weapon.getItem();
			Map<EnergyType, Integer> map = getEnergy(event.entityLiving);
			if (map != null && !map.isEmpty())
			{
				for (Entry<EnergyType, Integer> entry : map.entrySet())
				{
					if (entry.getValue() > 0 && collector.canStore(weapon, entry.getKey()))
					{
						collector.addEnergy(weapon, entry.getKey(), entry.getValue());
					}
				}
			}
		}
	}

	private ItemStack getWeapon(DamageSource source)
	{
		if (source instanceof EntityDamageSource)
		{
			Entity entity = ((EntityDamageSource) source).getEntity();
			if (entity instanceof EntityPlayer)
			{
				return ((EntityPlayer) entity).getCurrentEquippedItem();
			}
		}
		return null;
	}

	// Just for testing for now
	private Map<EnergyType, Integer> getEnergy(Entity entity)
	{
		Map<EnergyType, Integer> map = new HashMap<EnergyType, Integer>();
		if (entity instanceof EntityPig)
		{
			map.put(EnergyType.GAEAN, 50);
		}

		return map;
	}
}