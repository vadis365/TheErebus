package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.Erebus;
import erebus.ModItems;
import erebus.item.ItemErebusMaterial.DATA;

public class ItemJumpBoots extends ItemArmor {

	public ItemJumpBoots(ArmorMaterial enumarmormaterial, int k) {
		super(enumarmormaterial, 2, k);
		setCreativeTab(Erebus.tabErebusGear);
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
		return material.getItem() == ModItems.erebusMaterials && material.getItemDamage() == DATA.elasticFibre.ordinal();
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
		} else if (e.entityLiving instanceof EntityLiving) {
			ItemStack is = ((EntityLiving) e.entityLiving).getCurrentItemOrArmor(1);
			if (is != null && is.getItem() == this)
				e.entityLiving.motionY += 0.4D;
		}
	}
}