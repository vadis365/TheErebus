package erebus.core.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.ModAchievements;
import erebus.api.animationmagic.EnergyType;
import erebus.api.animationmagic.IEnergyCollector;
import erebus.entity.EntityBeetleLarva;

public class EntityDeathEventHandler
{
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void dropEvent(LivingDeathEvent event)
	{
		if (event.entityLiving.worldObj.isRemote)
		{
			return;
		}

		if (event.entity instanceof EntityBeetleLarva)
		{
			EntityBeetleLarva beetle = (EntityBeetleLarva) event.entity;
			if (event.source.getSourceOfDamage() instanceof EntityPlayer)
			{
				event.entity.worldObj.getClosestPlayer(event.entity.posX, event.entity.posY, event.entity.posZ, 30).triggerAchievement(ModAchievements.beetle);
			}

			if (beetle.isSquashed && beetle.hasDroppedDiamond)
			{
				if (event.source.getSourceOfDamage() instanceof EntityPlayer)
				{
					event.entity.worldObj.getClosestPlayer(event.entity.posX, event.entity.posY, event.entity.posZ, 30).triggerAchievement(ModAchievements.diamond);
				}
			}
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

	private Map<EnergyType, Integer> getEnergy(Entity entity)
	{
		Map<EnergyType, Integer> map = new HashMap<EnergyType, Integer>();
		if (entity instanceof EntityLiving)
		{
			EntityLiving living = (EntityLiving) entity;
			int health = (int) (2 * living.getMaxHealth());

			if (living instanceof EntityVillager)
			{
				map.put(EnergyType.SENTIENT, health);
			} else if (living instanceof EntityAnimal || living instanceof EntityAmbientCreature)
			{
				map.put(EnergyType.GAEAN, health);
			} else if (living instanceof IMob)
			{
				map.put(EnergyType.INFERNAL, health);
			} else if (living instanceof IBossDisplayData)
			{
				map.put(EnergyType.DIVINE, health);
			}
		}

		return map;
	}
}