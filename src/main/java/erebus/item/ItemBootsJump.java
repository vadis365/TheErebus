package erebus.item;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.ModTabs;
import erebus.item.ItemMaterials.DATA;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

public class ItemBootsJump extends ItemArmor {

	public ItemBootsJump(ArmorMaterial material, int k) {
		super(material, 2, k);
		setCreativeTab(ModTabs.gears);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type) {
		if (is.getItem() == ModItems.jumpBoots)
			return "erebus:textures/models/armor/hopper1.png";
		else
			return null;
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.materials && material.getItemDamage() == DATA.elasticFibre.ordinal();
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		player.fallDistance = 0.0F;
	}

	@SubscribeEvent
	public void onEntityJump(LivingJumpEvent e) {
		if (e.entityLiving instanceof EntityPlayer) {
			ItemStack is = ((EntityPlayer) e.entityLiving).inventory.armorInventory[0];
			if (is != null && is.getItem() == this && !e.entityLiving.isSneaking())
				e.entityLiving.motionY += 0.4D;
		}
	}
}