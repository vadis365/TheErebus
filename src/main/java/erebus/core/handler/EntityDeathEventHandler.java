package erebus.core.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import codechicken.lib.math.MathHelper;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import erebus.ModAchievements;
import erebus.ModBlocks;
import erebus.ModItems;
import erebus.api.animationmagic.EnergyType;
import erebus.api.animationmagic.IEnergyCollector;
import erebus.core.helper.Utils;
import erebus.entity.EntityBeetleLarva;
import erebus.tileentity.TileEntityBones;

public class EntityDeathEventHandler
{
	@SubscribeEvent
	public void dropEvent(LivingDropsEvent event)
	{
		if (event.entity instanceof EntityBeetleLarva)
		{
			EntityBeetleLarva beetle = (EntityBeetleLarva) event.entity;
			if (event.source.getSourceOfDamage() instanceof EntityPlayer)
			{
				event.entity.worldObj.getClosestPlayer(event.entity.posX, event.entity.posY, event.entity.posZ, 30).triggerAchievement(ModAchievements.beetle);

				if (beetle.isSquashed && dropedItem(event.drops, new ItemStack(Items.diamond)))
				{
					event.entity.worldObj.getClosestPlayer(event.entity.posX, event.entity.posY, event.entity.posZ, 30).triggerAchievement(ModAchievements.diamond);
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public void deathEvent(LivingDeathEvent event)
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

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void playerDeath(LivingDeathEvent event)
	{
		World world = event.entityLiving.worldObj;
		if (world.isRemote)
		{
			return;
		}

		if (event.entityLiving instanceof EntityPlayer && !world.getGameRules().getGameRuleBooleanValue("keepInventory"))
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			int x = MathHelper.floor_double(player.posX);
			int y = MathHelper.floor_double(player.posY);
			int z = MathHelper.floor_double(player.posZ);
			if (!world.isAirBlock(x, y, z))
			{
				y++;
			}
			world.setBlock(x, y, z, ModBlocks.bones);
			TileEntityBones tile = Utils.getTileEntity(world, x, y, z, TileEntityBones.class);
			if (tile != null)
			{
				for (int i = 0; i < player.inventory.mainInventory.length; i++)
				{
					ItemStack cont = player.inventory.mainInventory[i];
					if (cont != null)
					{
						tile.setInventorySlotContents(i, cont.copy());
						player.inventory.mainInventory[i] = null;
					}
				}
				for (int i = 0; i < player.inventory.armorInventory.length; i++)
				{
					ItemStack cont = player.inventory.armorInventory[i];
					if (cont != null)
					{
						tile.setInventorySlotContents(i, cont.copy());
						player.inventory.armorInventory[i] = null;
					}
				}
			}
		}
	}

	private boolean dropedItem(ArrayList<EntityItem> drops, ItemStack target)
	{
		for (EntityItem entity : drops)
		{
			if (entity != null && Utils.areStacksTheSame(entity.getEntityItem(), target, false))
			{
				return true;
			}
		}
		return false;
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