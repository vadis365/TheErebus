package erebus.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import erebus.ErebusMod;
import erebus.ModItems;

public class ItemJumpBoots extends ItemArmor {

	public ItemJumpBoots(int i, EnumArmorMaterial enumarmormaterial, int k) {
		super(i, enumarmormaterial, 2, k);
		setCreativeTab(ErebusMod.tabErebusGear);
	}

	@Override
	public String getArmorTexture(ItemStack is, Entity entity, int slot, int layer) {
		if (is.itemID == ModItems.jumpBoots.itemID)
			return "erebus:textures/models/armor/hopper_1.png";
		else
			return null;
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack itemStack) {
		player.fallDistance = 0.0F;
	}

	@ForgeSubscribe
	public void onEntityJump(LivingJumpEvent e) {
		if (e.entityLiving instanceof EntityPlayer) {
			ItemStack is = ((EntityPlayer) e.entityLiving).inventory.armorInventory[0];
			if (is != null && is.itemID == itemID && !e.entityLiving.isSneaking())
				e.entityLiving.motionY += 0.4D;
		} else if (e.entityLiving instanceof EntityLiving) {
			ItemStack is = ((EntityLiving) e.entityLiving).getCurrentItemOrArmor(1);
			if (is != null && is.itemID == itemID)
				e.entityLiving.motionY += 0.4D;
		}
	}
}
