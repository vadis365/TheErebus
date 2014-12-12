package erebus.item;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erebus.ModItems;
import erebus.item.Materials.DATA;

public class WaterStriders extends ItemArmor {

	public WaterStriders(ArmorMaterial material, int k) {
		super(material, 2, k);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type) {
		if (is.getItem() == ModItems.waterStriders)
			return "erebus:textures/models/armor/striders1.png";
		else
			return null;
	}

	@Override
	public boolean getIsRepairable(ItemStack armour, ItemStack material) {
		return material.getItem() == ModItems.materials && material.getItemDamage() == DATA.elasticFibre.ordinal();
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.boundingBox.minY);
		int z = MathHelper.floor_double(player.posZ);
		
			player.posY += -player.motionY;
		
		if(world.getBlock(x, y, z).getMaterial() == Material.water) {
			if(!Minecraft.getMinecraft().gameSettings.keyBindJump.getIsKeyPressed()) {
				if(player.motionY < 0.0D)
					player.motionY = 0.0D;
				player.fallDistance = 0.0F;
			}else
				player.motionY = 0.5D;
		}
	}
}